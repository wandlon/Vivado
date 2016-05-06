//当前请求的路径
var pathname;
//应用名称
var context;
//完整的url
var href;
//ajax请求没有权限后的处理
var noperms = function(xhr, textStatus, errorThrown) {
	var response = xhr.responseText;
	if (response.indexOf("noperms") !== -1) {
		window.location.href = context + "noperms";
	}
}

$(function(){
	initContext();
	//根据当前用户的功能权限生成功能菜单
	createUserRoleMenu();
	
	//清除复选框选中状态
	clearChecked();
	
	///// DROP DOWN BUTTON /////
	jQuery('.dropdown').each(function(){
		var t = jQuery(this);
		t.find('a.dropdown_label').click(function(){
			if(!t.hasClass('open')) {
				var h = t.height();
				t.find('ul').show().css({top: h+2+'px'});	
				t.addClass('open');
			} else {
				t.find('ul').hide();	
				t.removeClass('open');				   
			}
			return false;
		});
		
	});
	
	jQuery(document).click(function(){
		jQuery('.dropdown').removeClass('open').find('ul').hide();
	});
	
});

function initContext(){
	//获取当前请求的路径
	pathname = window.location.pathname;
	var path = pathname.split('/');
	context = "/" + path[1] + "/";
	href = window.location.href;
	if (href.lastIndexOf("#") !== -1) {
		href = href.substring(0, href.lastIndexOf("#"));
	}
}

/**
 * 根据当前用户的功能权限生成功能菜单
 */
function createUserRoleMenu(){
	//获取当前用户的功能权限
	$.ajax({
		type: "get",
		url: context + "menu/userMenuList",
		dataType: "json",
		success: function(data, status){
			if (data.msg === "success") {
				var menuList = data.menuList;
				if (menuList.length > 0) {
					//生成功能菜单
					createMenu(menuList);
					focusMenu();
				} else {
					showMsg("当前用户没有任何权限");
				}
			} else {
				//显示异常信息
				showMsg(data.msg);
			}
		}
	});
}

/**
 * 生成功能菜单
 * @param menuList 功能集合
 */
function createMenu(menuList) {
	//清空左侧边栏
	$(".iconmenu ul").empty();
	for (var i = 0; i < menuList.length; i++) {
		var menu = menuList[i];
		//把功能菜单添加到左侧边栏
		appendMenu(menu);
	}
}

/**
 * 把功能菜单添加到左侧边栏
 * @param menu 功能菜单
 */
function appendMenu(menu) {
	var menuBox = '<li id="menu_' + menu.menuTag + '"><a href="' + 
		menu.menuUrl + '" class="widgets">' + menu.menuName + '</a></li>';
	$(".iconmenu ul").append(menuBox);
}

/**
 * 在侧边栏显示异常信息
 * @param msg 异常信息
 */
function showMsg(msg) {
	var msgBox = '<li>' + msg + '</li>';
	$(".iconmenu ul").empty();
	$(".iconmenu ul").append(msgBox);
}

/**
 * 清除复选框选中状态
 */
function clearChecked(){
	var check = $("table input");
	check.removeAttr('checked');
}

/**
 * 获取所选记录的ID集合
 */
function selectIds(){
	var ids = new Array();
	var idTags = $("#dyntable2 tbody input");
	for (var i = 0; i < idTags.length; i++) {
		var idTag = idTags[i];
		if (idTag.checked) {
			var id = idTag.value;
			ids.push(id);
		}
	}
	return ids;
}
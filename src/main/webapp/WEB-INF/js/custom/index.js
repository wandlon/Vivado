jQuery(document).ready(function(){
								
	///// TRANSFORM CHECKBOX /////							
	jQuery('input:checkbox').uniform();
	
	///// LOGIN FORM SUBMIT /////
	//阻止表单默认提交行为
//	jQuery('#login').submit(function(){
//		return false;
//	});
	
	///// ADD PLACEHOLDER /////
	jQuery('#username').attr('placeholder','用户名');
	jQuery('#password').attr('placeholder','密码');
	
	//登录验证
	$("#loginButton").click(function(){
		if(jQuery('#username').val() == '') {
			showLoginMsg("请输入用户名.");
			return false;
		} else if(jQuery('#password').val() == '') {
			showLoginMsg("请输入密码.");
			return false;
		}
		return true;
	});
	
	//默认焦点在用户名输入框
	if (jQuery('#username').val() === '') {
		jQuery('#username').focus();
	} else if (jQuery('#password').val() === '') {
		jQuery('#password').focus();
	}
	
	
	//alert(window.location.pathname.indexOf("login"));
});

////登录验证
//function checkLogin(){
//	$.ajax({
//		type: "post",
//		url: context + "checkLogin",
//		data: $("#login").serialize(),
//		dataType: "json",
//		success: function(data, status){
//			if (data.msg == "success") {
//				if (pathname.indexOf("login") === -1 && pathname.indexOf("logout") === -1) {
//					window.location.href = href;
//				} else {
//					window.location.href = context + "common/welcome";
//				}
//			} else {
//				showLoginMsg(data.msg);
//			}
//		}
//	});
//}

//显示登录提示信息
function showLoginMsg(msg){
	jQuery('.nousername .loginmsg').empty();
	jQuery('.nousername').hide();
	jQuery('.nousername').fadeIn();
	jQuery('.nousername .loginmsg').text(msg);
}


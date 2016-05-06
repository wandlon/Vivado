$(function(){
	//表单验证并提交表单数据申请虚拟机
	checkApplyServer();
});

function checkApplyServer() {
	jQuery("#applyServer").validate({
		rules: {
			serverName: {
				required: true,
				maxlength: 32
			},
			imageId: {
				required: true
			},
			flavorId: {
				required: true
			}
		},
		messages: {
			roleName: {
				required: "请输入角色名称",
				maxlength: "长度不能超过32",
			},
			imageId: {
				required: "请选择虚拟机模板"
			},
			flavorId: {
				required: "请选择虚拟机规格"
			}
		},
		submitHandler: function(form){
			$.ajax({
				type: "post",
				url: "../server/boot",
				data: $(form).serialize(),
				dataType: "json",
				success: function(data, status){
					if (data.msg === "success") {
						window.location.href = "../server/list";
					} else {
						jQuery.jGrowl(data.msg);
					}
				}
			});
		}
	});
}
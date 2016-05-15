/**
 * 刪除虚拟机
 * @param instanceId 虚拟机实例ID
 */
function deleteServer(instanceId) {
	jConfirm('确认要删除虚拟机吗?', '系统消息', function(r) {
		if (r) {
			//发送删除角色的请求
			deleteServerPost(instanceId);
		}
	});
}

function displayServer(instanceId) {
//	window.location.href = "../server/display?instanceId=" + instanceId;
	$.ajax({
		type: "post",
		url: "../server/connect",
		data: {
			'instanceId': instanceId
		},
		async: false,
		dataType: 'json',
		success: function(data){
			if (data.msg == "success") {
//				var vncModel = data.vnc;
//				UI.connect(vncModel.host, vncModel.port);
				window.location.href = data.vnc;
			}else {
				jQuery.jGrowl(data.result);
			}
		}
	});
}

function deleteServerPost(instanceId){
	$.ajax({
		type: "post",
		url: "../server/delete",
		data: {
			instanceId: instanceId
		},
		dataType: "json",
		success: function(data, status){
			if (data.msg === "success") {
				window.location.reload();
			} else {
				jQuery.jGrowl(data.msg);
			}
		}
	});
}
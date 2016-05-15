$(function(){
	displayServer($("#instanceId").val());
});

function displayServer(instanceId){
	$.ajax({
		type: "post",
		url: "../server/connect",
		data: {
			'instanceId': instanceId
		},
		async: false,
		dataType: 'json',
		success: function(data){
			if (data.result == "success") {
//				var vncModel = data.vnc;
//				UI.connect(vncModel.host, vncModel.port);
				window.location.href = data.vnc;
			}else {
				jQuery.jGrowl(data.result);
			}
		}
	});
}
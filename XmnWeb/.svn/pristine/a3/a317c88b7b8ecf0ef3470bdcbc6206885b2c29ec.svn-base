var searchChosen = undefined;
var modelUrl = "";
$(document).ready(function() {
	if(!searchChosen){
		searchChosen = $("#sellerids").searchChosen({
		url : "marketingManagement/activityManagement/init/choseSeller/init.jhtml"
	});
		startValidate();
 }	
});
function startValidate(){
	validate("editAppPushForm",{
		rules:{
			sellerids:{
				required:true
			}
		},
		messages:{
			sellerids:{
				required:"不能为空!"
			}
		}},formAjax);
}
function formAjax(){
	var data = jsonFromt($('#editAppPushForm').serializeArray());
	if(!checkData(data.sellerids, searchChosen.next(), "不能为空")){
		return false;
	}
	showSmConfirmWindow(function(){
		var url = 'marketingManagement/activityManagement/activityManagerSeller.jhtml' + '?t=' + Math.random();
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				if (data.success) {
					$("#tishi").html("");
					showSmReslutWindow(data.success, data.msg);
					window.history.back();
				}else{
				    $("#tishi").html("<font color=red>"+data.data+"</font>");
					showSmReslutWindow(data.success, data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus,
					errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	},"请确认信息是否正确！");
}
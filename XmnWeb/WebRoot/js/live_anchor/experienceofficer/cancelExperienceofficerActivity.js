var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
var liveRecordSelected;
$(function() {

	validate(formId, {
		rules : {
		},
		messages:{
		}
	}, save);
	
	//初始化活动场次下拉框
	initActivityId();
});




/**
 * 保存信息
 */
function save() {
	if($("input[name='isChange']:checked").val()==1){
		if($('#newActivityId').val()==null ||$('#newActivityId').val()==''){
			showSmResultTipWindow("请设置新的场次");
			return false;
		}
	}
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	url = "experienceofficer/activity/cancel" + suffix;
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
		
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#' + formId).serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
			//提交后禁用提交按钮，防止重复提交表单
			$('#submitBtn').attr("disabled","disabled");
		},
		success : function(data) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
			if (data.success) {
				recordList.reset();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});

}


//初始化活动场次下拉框
function initActivityId(){
	$("#newActivityId").chosenObject({
		hideValue : "id",
		showValue : "zhiboTitle",
		showType:"multiple",//选项显示形式
		showParams:["planStartDateStr","zhiboTitle"],
		url : "experienceofficer/activity/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

$("input[name='isChange']").on("change",function(){
	activityShowHide($(this).val()==1);
});
function activityShowHide(isChange){
	if(isChange){
		$('#isChangeActivity').show();
	}else{
		$('#isChangeActivity').hide();
	}
}


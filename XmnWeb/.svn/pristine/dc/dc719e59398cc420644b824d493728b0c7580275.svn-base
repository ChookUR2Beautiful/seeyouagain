var formId = "editFrom";
$(function() {
	validate(formId, {
		rules : {
			startTime : {
				required : true
			},
			endTime : {
				required : true
			}
		},
		messages:{
			startTime:{
				required : "请选择开始时间"
			},
			endTime:{
				required : "请选择结束时间"
			}
		}
	}, exportInfo);
	
	liveDateInit();
	
});


/**
 * 导出信息
 */
function exportInfo() {
	//导出
	var path="liveGivedGift/manage/export.jhtml";
	$form = $("#"+formId).attr("action",path);
	$form[0].submit();
	$('#triggerModal').modal('hide');
}

/**
 * 自定义校验函数
 */
function customerValidate(){
	var result=true;
	return result;
}

/**
 * 日期控件初始化
 */
function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
		});
}

/*$(document).ready(function() {
	 $('.form-datetime').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
//			startDate : new Date()
		});
	 startValidate();
});

$('#save').click(function() {
	formAjax();
});


function formAjax(){
	
	var data = jsonFromt($('#editExtensionSetForm').serializeArray());
	
	if(!(checkData(dateCompare(data.dateStart, new Date()) >= 0, "input[name='dateStart']", "活动开始时间应大于当前时间") &&
			checkData(dateCompare(data.dateEnd, data.dateStart) >= 0, "input[name='dateEnd']", "活动结束时间应晚于活动开始时间"))){
			return false;
	}
	
	
	$.ajax({
		type : 'post',
		url : 'marketingManagement/extensionSet/update.jhtml' + '?t=' + Math.random(),
		data : data,
		dataType : 'json',
		async: false,
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
			if (data.success) {
				extensionSetList.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}
function startValidate(){
	validate("editExtensionSetForm",{
		rules : {
			sort : {
				required : true,
				digits:true,
				max:9999999,
			}
//			,
//			dateStart : {
//				required : true				
//			}
		},
		messages : {
			sort : {
				required : "排序不能为空,且必需为整数",
				digits: "排序必需为整数",
				max:"排序长度不能超过7位"
			}
//			,
//			dateStart : {
//				required : "推广开始时间不能为空"
//			}
		}
	});
}




 */


$(document).ready(function() {
	 $('.form-datetime').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
//			startDate : new Date()
		}).on('changeDate', function(ev){
			$(this).blur();
		});
	 startValidate();
});

function startValidate(){
	validate("editExtensionSetForm",{
		rules : {
			sort : {
				required : true,
				digits:true,
				max:9999999,
			}
		},
		messages : {
			sort : {
				required : "排序不能为空,且必需为整数",
				digits: "排序必需为整数",
				max:"排序长度不能超过7位"
			}
		}
	},formAjax);
}

function formAjax(){
	
	var data = jsonFromt($('#editExtensionSetForm').serializeArray());
	
	if(!(checkData(dateCompare(data.dateStart, new Date()) >= 0, "input[name='dateStart']", "活动开始时间应大于当前时间") &&
			checkData(dateCompare(data.dateEnd, data.dateStart) >= 0, "input[name='dateEnd']", "活动结束时间应晚于活动开始时间"))){
			return false;
	}
	
	$.ajax({
		type : 'post',
		url : 'marketingManagement/extensionSet/update.jhtml' + '?t=' + Math.random(),
		data : data,
		dataType : 'json',
		async: false,
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
			if (data.success) {
				extensionSetList.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}
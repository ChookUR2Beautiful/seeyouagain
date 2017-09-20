var formId = "editForm";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	//初始化日期控件
	initDates();
	
	validate(formId, {
		rules : {
			autoPassFirst : {
				required : true
			},
			autoPassSecond : {
				required : true
			}
		},
		messages:{
			
		}
	}, save);
	
	
});


/**
 * 保存信息
 */
function save() {
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "VstarEnroll/manage/auditSet/add" + suffix;
	} else {// 修改操作
		url = "VstarEnroll/manage/auditSet/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var validateResult=customerValidate();
	if(!validateResult){
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 新增或修改数据才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(true, "没做任何修改！");
	}
}

/**
 * 自定义校验函数
 */
function customerValidate(){
	var result=true;
	
	var enrollStartTime=$("#enrollStartTime").val();
	if(enrollStartTime == "" || enrollStartTime ==undefined ){
		showWarningWindow("warning", "报名开始时间不能为空！",9999);
		result=false;
		return result;
	}
	
	var enrollEndTime=$("#enrollEndTime").val();
	if(enrollEndTime == "" || enrollEndTime ==undefined ){
		showWarningWindow("warning", "报名结束时间不能为空！",9999);
		result=false;
		return result;
	}
	
	var contestStartTime=$("#contestStartTime").val();
	if(contestStartTime == "" || contestStartTime ==undefined ){
		showWarningWindow("warning", "复赛开始时间不能为空！",9999);
		result=false;
		return result;
	}
	
	var contestEndTime=$("#contestEndTime").val();
	if(contestEndTime == "" || contestEndTime ==undefined ){
		showWarningWindow("warning", "复赛结束时间不能为空！",9999);
		result=false;
		return result;
	}
	
	var liveStartTime=$("#liveStartTime").val();
	if(liveStartTime == "" || liveStartTime ==undefined ){
		showWarningWindow("warning", "试播开始时间不能为空！",9999);
		result=false;
		return result;
	}
	
	var liveEndTime=$("#liveEndTime").val();
	if(liveEndTime == "" || liveEndTime ==undefined ){
		showWarningWindow("warning", "试播结束时间不能为空！",9999);
		result=false;
		return result;
	}
	
	return result;
}




/**
 * 初始化日期控件
 */
function initDates(){
	var date=new Date();//new Date(yyyy,mth,dd,hh,mm,ss); 
	var year= date.getFullYear();
	var month=date.getMonth();
	var day=date.getDate();
	
	
	//限定报名时段
	limitedDate({
		form:"#editForm",
		startDateName:"enrollStartTime",
		endDateName:"enrollEndTime",
		overlap:true,
		format : 'yyyy-mm-dd',
		minuteStep:1,
		startView: 2,
	    minView: 2,
	});
	
	//限定复赛时段
	limitedDate({
		form:"#editForm",
		startDateName:"contestStartTime",
		endDateName:"contestEndTime",
		overlap:true,
		format : 'yyyy-mm-dd',
		minuteStep:1,
		startView: 2,
	    minView: 2,
	});
	
	//限定试播时段
	$('input[name="liveStartTime"]').datetimepicker({
		language:  "zh-CN",
	    weekStart: 1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 1,
	    minView: 0,
	    maxView: 1,
	    forceParse: 0,
	    format: 'hh:ii',
	    startDate : new Date(year,month,day,0,0,0),
	});
	
	$('input[name="liveEndTime').datetimepicker({
		language:  "zh-CN",
	    weekStart: 1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 1,
	    minView: 0,
	    maxView: 1,
	    forceParse: 0,
	    format: 'hh:ii',
	    startDate : new Date(year,month,day,0,0,0),
	});
}

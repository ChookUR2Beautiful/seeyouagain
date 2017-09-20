var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//初始化日期控件
	initDate();
	
	//初始化商家选择控件
	initSellerid();
	
	//直播类型初始化
	liveTopicInit();
	
	//初始化是否指定观众
	telphonesInit();
	
	validate(formId, {
		rules : {
			zhiboTitle : {
				required : true
			},
			zhiboAddress : {
				required : true
			},
			telphones :{
				required:true,
				telRule :true
			},	
		},
		messages:{
			telphones :{
				required: "请填写手机号码",
				telRule : "多个手机号以英文逗号分隔"
			}
		}
	}, save);
	
	
});


/**
 * 保存信息
 */
function save() {
	var url;
	var suffix = ".jhtml";
	url = "liveRecord/manage/updateBaseInfoBatch" + suffix;
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var validateResult=customerValidate();
	if(!validateResult){
		return ;
	}
	
	if (jsonTextInit != jsonText) {// 新增或修改数据才会发送请求
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
					recordList1.reload();
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
	}
}

/**
 * 自定义校验函数
 */
function customerValidate(){
	var result=true;
	return result;
}

/**
 * 手机号校验(只做长度校验)
 */
$.validator.addMethod("telRule", function(value, element) {
	var phone =  /^\d{11}(,\d{11})*$/;//多个手机号已逗号分隔
	return this.optional(element) || (phone.test(value));
}, "请输入正确的手机号！");


/**
 * 绑定"是否指定观众"单击事件
 */
$("input[name='isAppoint']").on("change",function(){
	telphonesInit();
});

/**
 * 指定观众手机号码显示初始化
 */
function telphonesInit(){
	var isAppoint = $("input[name='isAppoint']:checked").val();
	if (isAppoint == 1) {
		$("#telphonesDiv").css("display","block");
	} else {
		$("#telphonesDiv").css("display","none");
		$("#telphones").val('');
	}
}

/**
 * 绑定"直播类型,1商家、2活动"change事件
 */
$("input[name='liveTopic']").on("change",function(){
	liveTopicInit();
});

/**
 * 商家别名 / 活动主题初始化
 * 
 */
function liveTopicInit(){
	var liveTopic = $("input[name='liveTopic']:checked").val();
	if (liveTopic == 1) {
		$("#sellerDiv").css("display","block");
		$("#sellerAliasLabel").text("商家别名：");
	} else {
		$("#sellerDiv").css("display","none");
		$("#sellerAliasLabel").text("活动主题：");
	}
}

//初始化商家下拉框
function initSellerid(){
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/getSellerIdAndName.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//选择商家后，修改商家名称 
$('body').on("click",'#sellerid_chosen .chosen-results li',function(){
//	debugger;
	var sellerid =  $("#sellerid").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "businessman/seller/getSellerLandmarkInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"sellerid":sellerid},
		success : function(data){
			if(data != null){
				$("#sellername").val(data.sellername);
				$("#sellerAlias").val(data.sellername);
				$("#longitude").val(data.longitude);
				$("#latitude").val(data.latitude);
				$("#zhiboAddress").val(data.address);
			}
		}
	});
	
});

//初始化日期控件
function initDate(){
	
	
	$('input[name="planStartDate"]').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		endDate: $("input[name='planEndDate']").val()
	}).on("changeDate",function() {
			$("input[name='planEndDate']").datetimepicker("setStartDate",$("input[name='planStartDate']").val());
		});
	
	$('input[name="planEndDate').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate: $("input[name='planStartDate']").val()
	}).on( "changeDate", function() {
				$("input[name='planStartDate']").datetimepicker("setEndDate", $("input[name='planEndDate']").val());
			});
	
};

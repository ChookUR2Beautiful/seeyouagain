var ISTYPE;
$(document).ready(function() {
	ISTYPE = $("#isType").val();
//	var id = $('#id').val();
	if(ISTYPE == "add"){
//		inserTitle(' > <span><a href="floatAdvert/manage/add/init.jhtml ?sellerid='+id+'&isType=add" target="right">添加悬浮广告信息</a>','addSellerPackage', false);
		inserTitle(' > 添加悬浮广告信息','addFloatAvertList', false);  
	}else{
		inserTitle(' > 编辑悬浮广告信息','editFloatAvertList', false);
	}
	
	initData();
	
	$("#cid").trigger("change");
	/**
	 * 返回
	 */
	$("#backId").on("click",function(){
		 muBack();
	});

});


//初始化粉丝券下拉框
function initliveCoupon(){
	$("#cid").chosenObject({
		hideValue : "cid",
		showValue : "cname",
		url : "liveCoupon/manage/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:true,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化粉丝券下拉框
function initAnchor(){
	$("#anchorIdNames").multipleChosen({
		hideValue : "uid",
		showValue : "nickname",
		url : "anchor/manage/init/list.j" +
				"html",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%",
		needJoinValue:false
	});
}

/**
 * 商家信息上传
 */
function uploadImg(){
	//微信群二维码图片
	$("#picUrlid").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	
}

function initData(){
	initliveCoupon();
	initAnchor();
	//添加图片信息
	uploadImg();
//	debugger;
    /* 销售时间*/
	$('input[name="startTime"]').datetimepicker({
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
		endDate: $("input[name='endTime']").val()
	}).on("changeDate",function() {
			$("input[name='endTime']").datetimepicker("setStartDate",$("input[name='startTime']").val());
	});
	
	$("input[name='endTime']").datetimepicker({
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
		startDate: $("input[name='saleStartTime']").val()
	}).on( "changeDate", function() {
				$("input[name='saleStartTime']").datetimepicker("setEndDate", $("input[name='endTime']").val());
	});
	
}




//***********************************自定义事件区域****************************************
$("#cid").on("change",function(){
	var pid=$(this).val();
	var url;
	url = "liveCoupon/manage/init/list.jhtml";
	var coupon = {
			cid:pid};
	$.ajax({
		type : 'post',
		url : url,
		data : coupon,
		dataType : 'json',
		success : function(data) {
			var startDate=(undefined == data.content[0].startDateStr ? "-" : data.content[0].startDateStr);
			var endDate=(undefined == data.content[0].endDateStr ? "-" : data.content[0].endDateStr);
			
			//活动添加页面
			$("#couponTime").html(startDate+'--'+endDate);
		}
	});
});


/*$("#anchorIdNames").on("change",function(){
	var pid=$(this).val();
	var attrsss= $("#anchorIdNames").chosen();
	alert(attrsss);
	
	alert(pid);
});*/

//****************************************保存数据方法********************************
validate("floatAdvertForm",{
	rules : {
		cid : {
			required : true
		},
		startTime : {
			required : true
		},
		picUrl : {
			required : true
		},
		anchorId : {
			required : true
		}
	},
	messages:{
		cid:{
			required:"请选择粉丝券",
		},
		startTime:{
			required:"广告有效时间",
		},
		picUrl:{
			required:"请设置广告图",
		},
		anchorId:{
			required:"请设置投放主播",
		}
	}
}, save);



/**
 * 自定义校验方法
 */
function validateCustomer(isAdd) {
	var cid = $("#cid").val() || $("#cid").attr("initValue");
	if (cid == null || cid == "") {
		showWarningWindow("warning", "请选择粉丝券!", 9999);
		return false;
	}
	
	var attrs = $("#anchorIdNames").val();
	if (!attrs) {
		showWarningWindow("warning", "请选择投放主播!", 9999);
		return false;
	}

	var startTime=$("input[name='startTime']").val();
	if(startTime == null || startTime==""){
		showWarningWindow("warning","请选择广告开始时间!",9999);
		return false;
	}
	
	var endTime=$("input[name='endTime']").val();
	if(endTime == null || endTime==""){
		showWarningWindow("warning","请选择广告结束时间!",9999);
		return false;
	}
	
	var picUrl = $("#picUrl").val();
	if (!picUrl) {
		showWarningWindow("warning", "请选择投放广告图!", 9999);
		return false;
	}
	
	return true;
}


//保存套餐数据
function save(){
	var url;
	if(ISTYPE == "add"){
		url = "floatAdvert/manage/add.jhtml";
	}else{
		url = "floatAdvert/manage/update.jhtml";
	}
	var result = validateCustomer(ISTYPE=="add");
	if(!result){//自定义校验不通过
		return ;
	}
	var attrs=$("#anchorIdNames").val();
	if(attrs){
		attrs=attrs.join();
		$("#anchorIds").val(attrs);
	}
	var data = $('#floatAdvertForm').serializeArray();
	data = jsonFromt(data);
	data.anchorIds=attrs;
//	debugger
	
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href="floatAdvert/manage/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
	
}
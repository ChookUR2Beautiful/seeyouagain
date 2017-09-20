var dataCount = 0;//当前添加抵扣券序号
var dataSize =10;//最多可添加抵扣券张数
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	var id = $('#id').val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="sellerPackage/manage/add/init.jhtml?sellerid='+id+'&isType=add" target="right">添加套餐信息</a>','addSellerPackage',false);
		dataCount = $("#datas").find(".input-group").size()>0?$("#datas").find(".input-group").size(): 0;
	}else{
		inserTitle(' > 编辑套餐信息', 'editSellerPackage', false);
		dataCount = $("#datas").find(".input-group").size()>0?$("#datas").find(".input-group").size(): 0;
	}
	/**
	 * 返回
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
	 
	initsellerId(); //加载商家列表信息	
	initData();  //初始化选择
	
	// 清除ckEditor实例
	if (CKEDITOR.instances['content']) {
		CKEDITOR.instances['content'].destroy(true);
	}
	// 初始化富文本编辑器
	$("textarea#content").ckeditor({

	});
	
	$("input[name='ledgerType']").trigger("change");
	
});

//初始化商家下拉框
function initsellerId(){
	$("#sellerId").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/init/list.jhtml?isonline="+ 1,   //上线商户
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
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
	$("#picUrl2id").uploadImg({
		urlId : "picUrl2",
		showImg : $('#picUrl2').val()
	});
	
	$("#picUrl3id").uploadImg({
		urlId : "picUrl3",
		showImg : $('#picUrl3').val()
	});
	
	$("#picUrl4id").uploadImg({
		urlId : "picUrl4",
		showImg : $('#picUrl4').val()
	});	
	$("#picUrl5id").uploadImg({
		urlId : "picUrl5",
		showImg : $('#picUrl5').val()
	});
	$("#picUrl6id").uploadImg({
		urlId : "picUrl6",
		showImg : $('#picUrl6').val()
	});


}

//***********************************页面控制******************************************

/**
 * 绑定单击添加抵用券事件
 */
$("#datas").on("click", ".icon-plus", function() {
	if(dataCount == dataSize-1){
		showWarningWindow("warning","最多可添加"+dataSize+"张抵用券!",9999);
		return;
	}
	if ($(this).parents(".plandiv").find(".input-group").size() < dataSize) {
		$(this).parents(".input-group").after( $(".dataTemp").html().replace(/index/g, dataCount));
		dataCount++;
	}
});

/**
 * 绑定单击删除抵用券事件
 */
$("#datas").on("click",".icon-minus",function() {
		if ($(this).parents(".plandiv").find(".input-group").size() > 1) {
			$(this).parents(".input-group").remove();
			dataCount--;
		}
});


function initData(){
	//添加图片信息
	uploadImg();
//	debugger;
	initVoucherData();

    /* 销售时间 */
	$("input[name='saleStartTime']").datetimepicker({
		weekStart : 0,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		endDate: $("input[name='saleEndTime']").val()
	}).on("changeDate",function() {
			$("input[name='saleEndTime']").datetimepicker("setStartDate",$("input[name='saleStartTime']").val());
		});
	
	$("input[name='saleEndTime']").datetimepicker({
		weekStart : 0,
		todayBtn : 1,
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
				$("input[name='saleStartTime']").datetimepicker("setEndDate", $("input[name='saleEndTime']").val());
			});
	
	// 使用时间
	$("input[name='useStartTime']").datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		endDate: $("input[name='useEndTime']").val()
	}).on("changeDate",function() {
			$("input[name='useEndTime']").datetimepicker("setStartDate",$("input[name='useStartTime']").val());
		});
	
	$("input[name='useEndTime']").datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate: $("input[name='useStartTime']").val()
	}).on( "changeDate", function() {
				$("input[name='useStartTime']").datetimepicker("setEndDate", $("input[name='useEndTime']").val());
			});
	
	// 不可用时间
	$("input[name='forbidStartTime']").datetimepicker({
		weekStart : 0,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		endDate: $("input[name='forbidEndTime']").val()
	}).on("changeDate",function() {
			$("input[name='forbidEndTime']").datetimepicker("setStartDate",$("input[name='forbidStartTime']").val());
		});
	
	$("input[name='forbidEndTime']").datetimepicker({
		weekStart : 0,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate: $("input[name='forbidStartTime']").val()
	}).on( "changeDate", function() {
				$("input[name='forbidStartTime']").datetimepicker("setEndDate", $("input[name='forbidEndTime']").val());
			});
}

//选择是否赠送抵用券
function initVoucherData(){
	var haveVoucher = $("input[name='sendercoupon']:checked").val();
	//haveFree 1 赠送 2 不赠送
	haveVoucher == 1 ? $("#voucherTr").show(): $("#voucherTr").hide();
	haveVoucher == 1 ? $("input[name='sendercoupon'][value='1']").attr("checked",true) : $("input[name='sendercoupon'][value='2']").attr("checked",true) ;

}

/**
 * 将.dataTemp中的index替换成具体的数值
 */
function replaceDataTempIndex(index) {
	return $(".dataTemp").html().replace(/index/g, index);
}

//赠送抵用券
$("input[name='sendercoupon']").change(function() {
	initVoucherData();
	var haveVoucher = $("input[name='sendercoupon']:checked").val();
	if(haveVoucher == '1'){  //选择赠送
		$("#plandiv").html(replaceDataTempIndex(0));
		dataCount = currentDataSize();
	}else{
		$("#plandiv").empty(); //清空信息
	}
	
});


/**
 * 返回当前抵用券数量
 */
function currentDataSize(){
	return $("#datas").find(".input-group").size();
}

//****************************************保存数据方法********************************
validate("sellerPackageForm",{
	rules : {
		title : {
			required : true
		},
		originalPrice : {
			required : true
		},
		sellingPrice : {
			required : true
		},
		sellingCoinPrice : {
			required : true
		},
		stock : {
			required : true
		}
	},
	messages:{
		typeId:{
			required:"请输入专题标题",
		},
		originalPrice:{
			required:"请设置原价",
		},
		sellingPrice:{
			required:"请设置价格",
		},
		sellingCoinPrice:{
			required:"请设置价格(鸟币)",
		},
		stock:{
			required:"请设置套餐数量",
		}
	}
}, saveSellerPackageData);


//保存套餐数据
function saveSellerPackageData(){
	var url;
	if(ISTYPE == "add"){
		url = "sellerPackage/manage/add.jhtml";
	}else{
		url = "sellerPackage/manage/update.jhtml";
	}
	var isAdd = ISTYPE == "add" ? true : false;
	
	var result=validateCustomer(isAdd);
	if(!result){//自定义校验不通过
		return ;
	}
	
	var data = $('#sellerPackageForm').serializeArray();
	data = jsonFromt(data);
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href="sellerPackage/manage/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
	
}

$("input[name='ledgerType']").on("change", function(){
	var ledgerType = $("input[name='ledgerType']:checked").val();
//	alert(ledgerType);
	switch (parseInt(ledgerType)) {
	case 2:
        $("#percent").show();
		break;
	default:
        $("#percent").hide();
		break;
	}
	
})



/**
 * 自定义校验方法
 */
function validateCustomer(isAdd){
	var result=true;
	var sellerId=$("#sellerId").val()||$("#sellerId").attr("initValue");
	if(sellerId == null || sellerId==""){
		showWarningWindow("warning","请选择关联商户!",9999);
		result=false;
		return ;
	}
	
	var ledgerType=$("input[name='ledgerType']:checked").val();
	if(ledgerType == null || ledgerType==""){
		showWarningWindow("warning","请选择分帐类型!",9999);
		result=false;
		return ;
	}
	
	var ledgerType=$("input[name='ledgerRatio']").val();
	if(ledgerType == null || ledgerType==""){
		showWarningWindow("warning","请输入商户获得!",9999);
		result=false;
		return ;
	}
	
	var saleStartTime=$("input[name='saleStartTime']").val();
	if(saleStartTime == null || saleStartTime==""){
		showWarningWindow("warning","请选择销售开始时间!",9999);
		result=false;
		return ;
	}
	
	var saleEndTime=$("input[name='saleEndTime']").val();
	if(saleEndTime == null || saleEndTime==""){
		showWarningWindow("warning","请选择销售结束时间!",9999);
		result=false;
		return ;
	}
	
	var useStartTime=$("input[name='useStartTime']").val();
	if(useStartTime == null || useStartTime==""){
		showWarningWindow("warning","请选择使用开始时间!",9999);
		result=false;
		return ;
	}
	
	var useEndTime=$("input[name='useEndTime']").val();
	if(useEndTime == null || useEndTime==""){
		showWarningWindow("warning","请选择使用结束时间!",9999);
		result=false;
		return ;
	}
	
	if(isAdd){
		var picUrl=$("#picUrl").val();
		if(picUrl == null || picUrl==""){
			showWarningWindow("warning","请上传套餐图片!",9999);
			result=false;
			return ;
		}
	}
	var status=$("#status").val();
	if(status == null || status==""){
		showWarningWindow("warning","请选择套餐状态!",9999);
		result=false;
		return ;
	}
	return result;
}




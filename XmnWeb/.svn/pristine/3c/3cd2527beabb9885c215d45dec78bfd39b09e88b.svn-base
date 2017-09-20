var formId = "editForm";
var jsonTextInit;
var dataCount=5;//内购(外购)返回模式比例数组大小
$(function(){
	
	if($("#id").val()){
		inserTitle(' > <span>编辑返还模式','fansRankEdit',false);
	}else{
		inserTitle(' > <span>添加返还模式','fansRankEdit',false);
	}
	
	//加载关联等级
	initRankId();
	
	//初始化日期控件
	liveDateInit();
	
	//初始化基础表格显示状态 
	initBaseTableShow();
	
	//初始化编辑状态
	initEditControl();
	
	//初始化推荐奖励倍数显示
	initReferrerReward();
	
	//初始化兑换获得比例显示
	initConversionRate();
	
	// 级别图片
	$("#picUrlImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//表单校验
	validate(formId, {
		rules : {
			rankId:{
				required:true
			},
			effectiveDate:{
				required:true
			},
			referrerRatio:{
				required:true,
				digits:true,
				range:[0,100]
			},
			parentReferrerRatio:{
				required:true,
				digits:true,
				range:[0,100]
			},
			referrerReward : {
				required : true,
				digits:true,
				range:[0,100]
			},
			consumeRatio : {
				required : true,
				digits:true,
				range:[0,100]
			},
			privateRedPacketCashRatio : {
				required : true,
				number:true,
				range:[0,100]
			},
			privateRedPacketCoinRatio : {
				required : true,
				number:true,
				range:[0,100]
			},
			publicRedPacketCashRatio : {
				required : true,
				number:true,
				range:[0,100]
			},
			publicRedPacketCoinRatio : {
				required : true,
				number:true,
				range:[0,100]
			},
			conversionRate : {
				required : true,
				number:true,
				range:[0,100]
			},
			referrerLedgerType:{
				required : true
			}
		},
		messages:{
			effectiveDate:{
				required:"请填写生效日期"
			},
			referrerRatio:{
				required:"请填写直属推荐人充值分佣比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			parentReferrerRatio:{
				required:"请填写直属推荐人上级充值分佣比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			referrerReward:{
				required:"请填写推荐奖励比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			consumeRatio :{
				required:"请填写鸟币消费抵消比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			privateRedPacketCashRatio : {
				required:"请填写内购余额红包比例",
				number:"请填写1至100之间的数字",
				range:"请填写1至100之间的数字"
			},
			privateRedPacketCoinRatio : {
				required:"请填写内购鸟币红包比例",
				number:"请填写1至100之间的数字",
				range:"请填写1至100之间的数字"
			},
			publicRedPacketCashRatio : {
				required:"请填写外购余额红包比例",
				number:"请填写1至100之间的数字",
				range:"请填写1至100之间的数字"
			},
			publicRedPacketCoinRatio : {
				required:"请填写外购鸟币红包比例",
				number:"请填写1至100之间的数字",
				range:"请填写1至100之间的数字"
			},
			conversionRate : {
				required : "请填写兑换获得比例",
				number:"请填写1至100之间的数字",
				range:"请填写1至100之间的数字"
			}
		}
	}, save);
	
});

/**
 * 保存信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveRankRestitution/manage/add" + suffix;
	} else {// 修改操作
		url = "liveRankRestitution/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/liveRankRestitution/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		showWarningWindow('warning', "没做任何修改！");
	}
}



/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	var rankId=$("#rankId").val();
	
	if(rankId==null || rankId==''){
		showWarningWindow("warning","请选择关联级别!",9999);
		result=false;
		return result;
	}
	
	var effectiveDate=$("#effectiveDate").val();
	
	if(effectiveDate==null || effectiveDate==''){
		showWarningWindow("warning","请选择生效日期!",9999);
		result=false;
		return result;
	}
	
	//校验内购打赏鸟豆区间及返还比例数据
	for(var i = 0;i < dataCount; i++) {
//		console.info('i='+i+';'+$("input[name='voucherList["+i+"].denomination']").val());
		
		var reg1=new RegExp("^(0|[1-9][0-9]*)(\.[0-9]{1,4})?$");
		//内购打赏鸟豆最低区间
		var consumeLimitLowest=$("input[name='privateRedPacketDetailList["+i+"].consumeLimitLowest']").val();
		if(!(reg1.test(consumeLimitLowest))){ 
			submitDataError("input[name='privateRedPacketDetailList["+i+"].consumeLimitLowest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		//内购打赏鸟豆最高区间
		var consumeLimitHighest=$("input[name='privateRedPacketDetailList["+i+"].consumeLimitHighest']").val();
		if(!(reg1.test(consumeLimitHighest))){ 
			submitDataError("input[name='privateRedPacketDetailList["+i+"].consumeLimitHighest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		
		//鸟币红包返还最低比例
		var coinLowest=$("input[name='privateRedPacketDetailList["+i+"].coinLowest']").val();
		if(!(reg1.test(coinLowest))){ 
			submitDataError("input[name='privateRedPacketDetailList["+i+"].coinLowest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		//鸟币红包返还最高比例
		var coinHighest=$("input[name='privateRedPacketDetailList["+i+"].coinHighest']").val();
		if(!(reg1.test(coinHighest))){ 
			submitDataError("input[name='privateRedPacketDetailList["+i+"].coinHighest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		
		//余额红包返还最低比例
		var cashLowest=$("input[name='privateRedPacketDetailList["+i+"].cashLowest']").val();
		if(!(reg1.test(cashLowest))){ 
			submitDataError("input[name='privateRedPacketDetailList["+i+"].cashLowest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		//余额红包返还最高比例
		var cashHighest=$("input[name='privateRedPacketDetailList["+i+"].cashHighest']").val();
		if(!(reg1.test(cashHighest))){ 
			submitDataError("input[name='privateRedPacketDetailList["+i+"].cashHighest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		
	}
	
	//校验外购打赏鸟豆区间及返还比例数据
	for(var i = 0;i < dataCount; i++) {
//		console.info('i='+i+';'+$("input[name='voucherList["+i+"].denomination']").val());
		
		var reg1=new RegExp("^(0|[1-9][0-9]*)(\.[0-9]{1,4})?$");
		//内购打赏鸟豆最低区间
		var consumeLimitLowest=$("input[name='publicRedPacketDetailList["+i+"].consumeLimitLowest']").val();
		if(!(reg1.test(consumeLimitLowest))){ 
			submitDataError("input[name='publicRedPacketDetailList["+i+"].consumeLimitLowest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		//内购打赏鸟豆最高区间
		var consumeLimitHighest=$("input[name='publicRedPacketDetailList["+i+"].consumeLimitHighest']").val();
		if(!(reg1.test(consumeLimitHighest))){ 
			submitDataError("input[name='publicRedPacketDetailList["+i+"].consumeLimitHighest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		
		//鸟币红包返还最低比例
		var coinLowest=$("input[name='publicRedPacketDetailList["+i+"].coinLowest']").val();
		if(!(reg1.test(coinLowest))){ 
			submitDataError("input[name='publicRedPacketDetailList["+i+"].coinLowest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		//鸟币红包返还最高比例
		var coinHighest=$("input[name='publicRedPacketDetailList["+i+"].coinHighest']").val();
		if(!(reg1.test(coinHighest))){ 
			submitDataError("input[name='publicRedPacketDetailList["+i+"].coinHighest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		
		//余额红包返还最低比例
		var cashLowest=$("input[name='publicRedPacketDetailList["+i+"].cashLowest']").val();
		if(!(reg1.test(cashLowest))){ 
			submitDataError("input[name='publicRedPacketDetailList["+i+"].cashLowest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		//余额红包返还最高比例
		var cashHighest=$("input[name='publicRedPacketDetailList["+i+"].cashHighest']").val();
		if(!(reg1.test(cashHighest))){ 
			submitDataError("input[name='publicRedPacketDetailList["+i+"].cashHighest']","请填写数字(最多保留四位小数)");
			result=false;
			return false;
		}
		
	}
	
	return result;
}


//初始化关联等级下拉框
function initRankId(){
//	debugger;
	var objectOriented=$("#objectOriented").val();
	var rankType=1;
	if(objectOriented==4){
		rankType=2;
	}
	
	$("#rankId").chosenObject({
		hideValue : "id",
		showValue : "rankName",
		url : "liveFansRank/manage/getFansRanks.jhtml",
		filterVal:rankType,
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	$("#rankId").trigger('chosen:updated');
}

/**
 * 初始化基础表格显示状态
 */
function initBaseTableShow(){
//	debugger;
	var id=$("#id").val();
	if(id){
//		$("#baseTable").css('display','none');
		$("#rankId").attr('disabled',true);
		$("#rankId").trigger("chosen:updated");
		$("#objectOriented").attr('disabled',true);
		$("#effectiveDate").attr('disabled',true);
		$("#referrerReward").attr('readonly','readonly');
	}
}

/**
 * 初始编辑控制
 */
function initEditControl(){
	var id=$("#id").val();
	if(id){
		$("#rankName").attr('readonly','readonly');
	}
}


/**
 * 直播日期控件初始化
 */
function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});
}

/**
 * 绑定会员类型change事件
 */
$("#objectOriented").change(function(){
	initRankId();
	
	initReferrerReward();
	
	initConversionRate();
});

/**
 * 初始化推荐奖励显示
 */
function initReferrerReward(){
	var objectOriented=$("#objectOriented").val();
	if(objectOriented==4){
		$("#referrerRewardTr").css("display","none");
	}else{
		$("#referrerRewardTr").css("display","table-row");
	}
}

/**
 * 初始化兑换获得比例显示
 */
function initConversionRate(){
	var objectOriented=$("#objectOriented").val();
	if(objectOriented==2){
		$("#conversionRateTr").css("display","table-row");
	}else{
		$("#conversionRateTr").css("display","none");
		$("#conversionRate").val(0);
	}
}

var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			rechargeType : {
				required : true
			},
			rechAmount  :{
				required :true,
				digits:true,
				range:[1,999999999]
			},
			rechNormCoin  :{
				required :true,
				digits:true,
				range:[1,999999999]
			},rechFreeCoin  :{
				required :true,
				digits:true,
				range:[0,999999999]
			},
			productId : {
				required:true,
				maxlength:32
			},
			fansRankId :{
				required:true
			}
		},
		messages:{
			rechargeType : {
				required:"请选择充值类型"
			},
			rechAmount:{
				required:"请输入充值金额",
				digits:"充值金额必须为整数",
				range:"充值金额须设定为1-999999999之间的整数"
			},
			rechNormCoin:{
				required:"请输入兑换鸟豆",
				digits:"兑换鸟豆必须为整数",
				range:"兑换鸟豆须设定为1-999999999之间的整数"
			},
			rechFreeCoin:{
				required:"请输入赠送鸟豆",
				digits:"赠送鸟豆必须为整数",
				range:"赠送鸟豆须设定为0-999999999之间的整数"
			},
			productId : {
				required:"请输入苹果内购序列号",
				maxlength:"最多可输入32位字符"
			},
			fansRankId : {
				required:"请选择关联级别"
			}
		}
	}, liveGiftSave);
	
	// 充值类型
	$("input[name='rechargeType']").change(function() {
		rechargeTypeInit();
	});
	
	rechargeTypeInit();
	
	//绑定类型初始化
	objectOrientedInit();
	
	initFansRankId();
	
});




/**
 * 保存礼物信息
 */
function liveGiftSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveRechargeCombo/manage/add" + suffix;
	} else {// 修改操作
		url = "liveRechargeCombo/manage/update" + suffix;
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
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if (isAdd) {
						comboList.reset();
					} else {
						comboList.reload();
					}
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
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	var objectOrientedFirst=$("input[name^='objectOrienteds']:checked").val();
	if(objectOrientedFirst==null && objectOrientedFirst==undefined){
		showWarningWindow("warning","请选择绑定类型!",9999);
		result=false;
		return result;
	}
	
	return result;
	
}

/**
 * 充值类型初始化
 */
function rechargeTypeInit(){
	var rechargeType = $("input[name='rechargeType']:checked").val();
	if (rechargeType == 1) {
		$("#productIdDiv").css("display","block");
		$("#comboSourceDiv").css("display","block");
	} else {
		$("#productIdDiv").css("display","none");
		$("#comboSourceDiv").css("display","none");
	}
}

/**
 * 绑定类型初始化
 */
function objectOrientedInit(){
	var objectOriented=$("#objectOriented").val();
	var items=objectOriented.split(",");
	for(var i=0;i<items.length;i++){
//		console.log(items[i]);
		$("#objectOrientedDiv").find("input:checkbox[value='"+items[i]+"']").attr('checked','true');
	}
}

//初始化粉丝级别下拉框
function initFansRankId(){
	$("#fansRankId").chosenObject({
		hideValue : "id",
		showValue : "rankName",
		url : "liveFansRank/manage/getFansRanks.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

$('body').on("click",'#fansRankId_chosen .chosen-results li',function(){
//	debugger;
	var fansRankId =  $("#fansRankId").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "liveFansRank/manage/getFansRankInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":fansRankId},
		success : function(data){
			if(data != null){
				$("#fansRankName").val(data.rankName);
			}
		}
	});
});

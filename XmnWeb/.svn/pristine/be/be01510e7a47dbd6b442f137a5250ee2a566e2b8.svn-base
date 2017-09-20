var pageDiv;
$(document).ready(function() {
		
	inserTitle(' > 商家管理 > <span><a href="businessman/sellerIncomeFlow/init.jhtml" target="right">商家收入流水</a>','sellerIncomeFlowList',true);

	$("#export").click(function(){
		$form = $("#sellerIncomeFlowFromId").attr("action","businessman/sellerIncomeFlow/export.jhtml");
		$form[0].submit();
	});
	
	pageDiv = $('#sellerIncomeFlowList').page({
		url : 'businessman/sellerIncomeFlow/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'sellerIncomeFlowFromId'
	});
	
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/businessman/sellerIncomeFlow/init.jhtml';
			location.href =url;
		}
	});
	
});

/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}



/**
 * * 乘法函数，用来得到精确的乘法结果 * 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。 *
 * 调用：accMul(arg1,arg2) * 返回值：arg1乘以 arg2的精确结果
 */
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}


/**
 * 构建表格
 * @param data
 * @param obj
 */
function success(data, obj) {
	//obj.find('div').eq(0).html($.renderGridView(sellerModel,data));
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;商家收入流水&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#sellerIncomeFlowFromId").serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "sellerID",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		cols:[{
			title : "订单类型",
			name : "orderType",
			type : "integer",
			width : 120,
			customMethod : function(value, data) {
				if(value == 1){
					return "平台";
				}else if(value == 2){
					return "商户";
				}else if(value == 3){
					return "粉丝券";
				}else if(value == 4){
					return "套餐";
				}else{
					return "-";
				}
			}
		},{
			title : "订单号",
			name : "bid",
			type : "long",
			width : 150
		},{
			title : "商家名称",
			name : "sellerName",
			type : "string",
			width : 150
		},{
			title : "会员信息",
			name : "nname",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return value+"("+data.phoneid+")";
			}
		},{
			title : "支付方式",
			name : "payTypeText",
			type : "string",
			width : 150
		},{
			title : "订单金额/元",
			name : "money",
			type : "double",
			width : 100
		},{
			title : "分成金额/元",
			name : "sellerAmount",
			type : "double",
			width : 100
		},{
			title : "交易时间",
			name : "zdate",
			type : "double",
			width : 150
		}]
	},permissions);
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

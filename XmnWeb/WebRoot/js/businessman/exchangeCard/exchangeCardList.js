var pageDiv;
$(document).ready(function() {
	//标题
	inserTitle(' > 商家管理 > <span><a href="businessman/exchangeCard/init.jhtml" target="right">兑换专享卡记录</a>','sellerList',true);
	/**
	 * 重置
	 */
	
	//加载数据
	pageDiv = $('#exchangeCardInfoDiv').page({
		url : 'businessman/exchangeCard/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchFromId'
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	$("#export").click(function(){
		$form = $("#searchFromId").attr("action","businessman/exchangeCard/export.jhtml");
		$form[0].submit();
	});
	
});


function success(data, obj) {
	var formId = "specialTopicFromId", title = "兑换专享卡记录列表", subtitle = "个兑换专享卡记录";
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+ data.total+ '】' + subtitle + '&nbsp;</font></caption>';
	
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());
//	updateAddBtnHref("#addSpecialTopicBto", callbackParam);
	
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		/*
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "xg","del","link" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
		    cols : [{
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "businessman/specialTopic/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "del"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete(\""+data.id+"\",\""+data.uid+"\")'>" + "删除" + "</a>";
				            return value;
				    }
				 }
			]
		},
		*/
		cols : [ {
			title : "兑换订单",
			name : "orderNo",
			type : "string",
			width : 150
		}, {
			title : "会员名称",
			name : "uname",
			type : "string",
			width : 150
		}, {
			title : "会员手机",
			name : "phone",
			type : "string",
			width : 150
/*		}, {
			title : "会员级别",
			name : "relationNum",
			type : "string",
			width : 150*/
		}, {
			title : "兑换鸟币",
			name : "payCoin",
			type : "string",
			width : 150
		}, {
			title : "兑换商户",
			name : "sellername",
			type : "string",
			width : 150
		}, {
			title : "获得额度",
			name : "denomination",
			type : "string",
			width : 150
		}, {
			title : "红包额度",
			name : "redPacketLimit",
			type : "string",
			width : 150
			/*,customMethod : function(value, data) {
				//pay_coin兑换鸟币X2 － 获得额度 ＝ 红包额度  
				var redPackets = data.payCoin * 2 - data.denomination; 
				return redPackets;
			}*/
		}, {
			title : "总额度",
			name : "totalLimit",
			type : "string",
			width : 150
			/*,customMethod : function(value, data) {
				//pay_coin兑换鸟币X2 ＝ 总额度  
				var totalAmount = data.payCoin * 2; 
				return totalAmount;
			}*/
		}, {
			title : "兑换时间",
			name : "payTime",
			type : "string",
			width : 150
		} ]
	}, permissions);
}



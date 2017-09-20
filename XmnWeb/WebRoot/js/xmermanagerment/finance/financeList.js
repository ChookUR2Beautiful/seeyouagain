var financeList;
var initListUrl = "xmer/finance/init/list.jhtml";
$(function() {
	inserTitle(
			' > 寻蜜客管理 > <a href="xmer/finance/init.jhtml" target="right">个人财务管理</a>',
			'userSpan', true);
	financeList = $("#financeList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//导出
	$("#export").click(function(){
		/*$form = $("#sellerFromId").attr("action","businessman/seller/export.jhtml");
		$form[0].submit();*/
		alert('待开发...');
	});

});

function success(data, obj) {
	var formId = "shareForm", title = "钱包信息", subtitle = "条";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "uid",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		cols : [ {
			title : "用户ID",
			name : "uid",
			type : "string",
			width : 150
		}, {
			title : "他的消费金额/元",
			name : "income",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/finance/sumMoney/init.jhtml"
				},
				param : ["uid"],
				permission : "sumMoneyView"
			},
			width : 150
		}, {
			title : "他的总收入/元",
			name : "income",
			type : "string",
			width : 150
		}, {
			title : "佣金/元",
			name : "commision",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/finance/walletRecord/commision/init.jhtml"
				},
				param : ["accountid"],
				permission : "walletRecordView"
			},
			width : 150
		}, {
			title : "他的流水收入/元",
			name : "balance",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/finance/walletRecord/balance/init.jhtml"
				},
				param : ["accountid"],
				permission : "walletRecordView"
			},
			width : 150
		}/*, {
			title : "押金/元",
			name : "deposit",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/finance/walletRecord/deposit/init.jhtml"
				},
				param : ["accountid"],
				permission : "walletRecordView"
			},
			width : 150
		}*/ ]
	}, permissions);
}
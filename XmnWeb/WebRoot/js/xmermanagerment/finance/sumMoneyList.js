var sumMoneyList;
var initListUrl = "xmer/finance/sumMoney/list.jhtml";
$(function() {
	
	inserTitle( ' > 寻蜜客管理 > <a href="xmer/finance/init.jhtml" target="right">个人财务管理</a> >消费金额', 'userSpan', true);
	
	sumMoneyList = $("#sumMoneyList").page({
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
		$form = $("#searchForm").attr("action","xmer/finance/sumMoney/export.jhtml");
		$form[0].submit();
//		alert('消费金额导出待开发...');
	});
	
	$(".form-datetime").datetimepicker({
		weekStart : 1,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});

});

function success(data, obj) {
	var formId = "shareForm", title = "消费金额信息", subtitle = "条";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=false&callbackParam=" + getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "bid",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		cols : [ {
			title : "订单号",
			name : "bid",
			type : "string",
			width : 100
		}, {
			title : "商铺ID",
			name : "sellerid",
			type : "string",
			width : 100
		}, {
			title : "商铺名称",
			name : "sellername",
			type : "string",
			width : 100
		}, {
			title : "商铺负责人",
			name : "fullname",
			type : "string",
			width : 100
		}, {
			title : "商铺负责人电话",
			name : "phoneid",
			type : "string",
			width : 100
		}, {
			title : "下单时间",
			name : "sdate",
			type : "string",
			width : 100
		}, {
			title : "消费金额/元",
			name : "money",
			type : "string",
			width : 100
		}, {
			title : "商铺详情",
			name : "zdate",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/seller/getInit.jhtml"
				},
				param : ["sellerid"],
				permission : "sumMoneyView"
			},
			customMethod : function(value, data) {
				return data.sellerid != undefined && data.sellerid != "" ?  $(value).html("详情") : "-";
			},
			width : 100
		} ]
	}, permissions);
}
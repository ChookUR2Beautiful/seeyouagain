var billList, giftList;
var initListUrl = "verProfitCount/manage/init/list.jhtml";

$(function() {
	inserTitle(
			' > 打赏分红 > <a href="verProfitCount/manage/init.jhtml" target="right">V客收益统计</a>',
			'userSpan', true);
	//加载列表数据
	pageInit();
	
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
	
	//导出
	$("#exportBill").click(function(){
		var formId="searchFormBill";
		var url="verProfitCount/manage/init/getCurrentSize.jhtml";
		var size=getCurrentSize(formId,url);
		if(size>5000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}
		var path="verProfitCount/manage/export.jhtml";
		$form = $("#searchFormBill").attr("action",path);
		$form[0].submit();
	});	
	
	$("#exportLive").click(function(){
		var formId="searchFormBill";
		var url="verProfitCount/manage/init/getCurrentSize.jhtml";
		var size=getCurrentSize(formId,url);
		if(size>5000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}
		var path="verProfitCount/manage/export.jhtml";
		$form = $("#searchFormGift").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	//tab1
	billList = $("#billList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchFormBill',
		param : {
			type : "1"  //交易类型 1.签约商户收益统计,  2.推荐主播收益统计
		}
	});
	
	//tab2
	giftList = $("#giftList").page({
		url : initListUrl,
		success : giftSuccess,
		pageBtnNum : 10,
		paramForm : 'searchFormGift',
		param : {
			type : "2" //交易类型 1.签约商户收益统计,  2.推荐主播收益统计
		}
	});	
}

function success(data, obj) {
	var formId = "searchFormBill";
	var title = "签约商户收益列表", subtitle = "个收益";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		cols : [ {
			title : "订单编号",
			name : "orderNo",
			type : "string",
			width : 80
		},{
			title : "签约商户名称",
			name : "sellerName",
			type : "string",
			width : 80
		}, {
			title : "订单金额",
			name : "money",
			type : "string",
			width : 60
		},{
			title : "签约折扣",
			name : "discount",
			type : "string",
			width : 60
		}, {
			title : "V客编号",
			name : "vkeUid",
			type : "string",
			width : 80			
		}, {
			title : "签约V客",
			name : "vkeNname",
			type : "string",
			width : 100,
			customMethod : function(value, data){
				var vkeNname = data.vkeNname== undefined ? "" : data.vkeNname;
				var vkePhone = data.vkePhone == undefined ? "" : " ( " +data.vkePhone+ " )";
	            var value = vkeNname + vkePhone;
	            return value;
	        }	
		}, {
			title : "V客等级",
			name : "vkeRankname",
			type : "string",
			width : 80	
		}, {
			title : "收益分成 (%)",
			name : "vkeRatio",
			type : "string",
			width : 60,
			customMethod : function(value, data){
				var vkeRatio = data.vkeRatio == undefined ? 0 : data.vkeRatio;
//	            var value = vkeRatio + "%";
	            return vkeRatio;
	        }
		}, {
			title : "获得收益",
			name : "profit",
			type : "string",
			width : 60
		}, {
			title : "获得时间",
			name : "sdate",
			type : "string",
			width : 80
		} ]
	}, permissions);
	
	loadVkeProfitSellerTotal();
}

function giftSuccess(data, obj) {
	var formId = "searchFormGift";
	var title = "签约主播收益列表", subtitle = "个收益";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		cols : [ {
			title : "送礼编号",
			name : "orderNo",
			type : "string",
			width : 60
		}, {
			title : "签约主播",
			name : "anchorNname",
			type : "string",
			width : 80
		}, {
			title : "打赏金额(鸟豆)",
			name : "money",
			type : "string",
			width : 80
		},{
			title : "分成折扣",
			name : "discount",
			type : "string",
			width : 60			
		}, {
			title : "签约V客",
			name : "vkeNname",
			type : "string",
			width : 100,
			customMethod : function(value, data){
				var vkeNname = data.vkeNname== undefined ? "" : data.vkeNname;
				var vkePhone = data.vkePhone == undefined ? "" : " ( " +data.vkePhone+ " )";
	            var value = vkeNname + vkePhone;
	            return value;
	        }	
		}, {
			title : "V客等级",
			name : "vkeRankname",
			type : "string",
			width : 60,
		}, {
			title : "收益分成 (%)",
			name : "vkeUidRatio",
			type : "string",
			width : 60,			
			customMethod : function(value, data){
				var vkeRatio = data.vkeRatio == undefined ? 0 : data.vkeRatio;
//	            var value = vkeRatio + "%";
	            return vkeRatio;
	        }
		}, {
			title : "获得收益",
			name : "profit",
			type : "string",
			width : 60			
		}, {
			title : "获得时间",
			name : "sdate",
			type : "string",
			width : 80
		} ]
	
	}, permissions);
	
	loadVkeProfitGiftTotal();
}


/**
 * 加载累计商家总额
 */
function loadVkeProfitSellerTotal(){
	$.ajax({
		 url : "verProfitCount/manage/init/vkeProfitCountSeller.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#searchFormBill').serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
		                 + "       <td>"+data.sdateStart+" 至 "+data.sdateEnd+"</td>"	 //统计时间区间
		                 + "       <td>"+data.vkeProfitVkeCount+"</td>"		//统计V客
		                 + "       <td>"+data.vkeProfitSellerCount+"</td>"	 	//统计商家
		                 + "       <td>"+data.totalProfit+"</td>"  	//累计收益
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
			     $("#vkeProfitSellerTotal").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

/**
 * 加载累计主播打赏总额
 */
function loadVkeProfitGiftTotal(){
	$.ajax({
		 url : "verProfitCount/manage/init/vkeProfitCountGift.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#searchFormGift').serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
		                 + "       <td>"+data.sdateStart+ " 至 " +data.sdateEnd+ "</td>"	//统计时间区间
		                 + "       <td>"+data.vkeProfitVkeCount+"</td>"		//统计V客
		                 + "       <td>"+data.vkeProfitVkeCount+"</td>"	 	//统计收益
		                 + "       <td>"+data.totalProfit+"</td>"  	//累计收益
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
			     $("#vkeProfitGiftTotal").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}



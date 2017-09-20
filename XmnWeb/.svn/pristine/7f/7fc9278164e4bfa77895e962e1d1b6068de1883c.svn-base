var recordList;
var initListUrl = "liveGift/manage/update/giftDetailList.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	
	//加载页面数据
	pageInit();
	

});

/**
 * 加载页面数据
 */
function pageInit(){
	var gid=$("#gid").val();
	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5",
			gid:gid
		}
	});
}

function success(data, obj) {
	var formId = "searchForm", title = "商品礼物列表", subtitle = "个商品";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());
	
	
	var freight=$("#freight").val();

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
		},
		cols : [ {
			title : "关联商品",
			name : "pname",
			type : "string",
			width : 250
		}, {
			title : "鸟币",
			name : "birdCoin",
			type : "string",
			width : 80
		} ,{
			title : "运费",
			name : "freight",
			type : "string",
			width : 80,
			customMethod : function(value, data) {
				return freight;
			}
		}, {
			title : "显示价格",
			name : "--",
			type : "string",
			width : 80,
			customMethod : function(value, data) {
				var showPrice=0;
				try{
					var birdCoin= parseFloat(data.birdCoin);
					showPrice=birdCoin+parseFloat(freight);
				}catch(err){
					console.log(err);
				}
				return showPrice;
			}
		} ]
	}, permissions);
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
 
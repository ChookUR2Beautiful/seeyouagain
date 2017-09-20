var giftList;
var initListUrl = "liveGivedGift/manage/salary/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道 > <a href="liveGivedGift/salary/init.jhtml" target="right">主播获得打赏明细</a> ',
			'userSpan', true);
	
	initAnchorId();
	
	//加载页面数据
	pageInit();
	
	//日期控件初始化
	liveDateInit();
	
	//导出
	$("#export").click(function(){
		var path="liveGivedGift/manage/salary/export.jhtml";
		showSmConfirmWindow(function (){
			var data=$("#searchForm").serializeArray();
			data = jsonFromt(data);
			myPost(path,data);
		 },"确定要导出吗？");
	});	

});

//初始化默认主播下拉框
function initAnchorId(){
//	debugger;
	$("#anchorId2").chosenObject({
		hideValue : "id",
		showValue : "nickname",
		url : "anchor/manage/initAnchorId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

/**
 * 加载页面数据
 */
function pageInit(){
	giftList = $("#giftList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
}

function success(data, obj) {
	var formId = "searchForm", title = "打赏返利列表", subtitle = "个打赏";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveGivedGift/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [  {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 120
		},{
			title : "会员名称",
			name : "nickname",
			type : "string",
			width : 180
		},{
			title : "会员手机",
			name : "phone",
			type : "string",
			width : 180
		},{
			title : "打赏礼物",
			name : "giftName",
			type : "string",
			width : 180
		}, {
			title : "价值鸟豆",
			name : "giftPrice",
			type : "string",
			width : 180
		}, {
			title : "打赏主播",
			name : "anchorName",
			type : "string",
			width : 180
		},{
			title : "获得鸟蛋",
			name : "giveEgg",
			type : "string",
			width : 180
		}, {
			title : "打赏时间",
			name : "createTimeStr",
			type : "string",
			width : 180
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
			minView : 0,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
		});
 }
 
 
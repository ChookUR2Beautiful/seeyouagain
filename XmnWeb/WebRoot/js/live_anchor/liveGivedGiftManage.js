var recordList;
var initListUrl = "liveGivedGift/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveGivedGift/manage/init.jhtml" target="right">打赏返利管理</a> ',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//日期控件初始化
	liveDateInit();
	
	//导出
	$("#export").click(function(){
		var path="liveGivedGift/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	recordList = $("#recordList").page({
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
		cols : [ {
			title : "打赏ID",
			name : "id",
			type : "string",
			width : 120
		}, {
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
		}, {
			title : "企业级推荐人",
			name : "enterpriseNname",
			type : "string",
			width : 180
		}, {
			title : "上级",
			name : "superior",
			type : "string",
			width : 180
		},{
			title : "打赏礼物",
			name : "giftName",
			type : "string",
			width : 180
		}, {
			title : "价值鸟币",
			name : "giftPrice",
			type : "string",
			width : 180
		}, {
			title : "返还鸟币",
			name : "returnCoin",
			type : "string",
			width : 180
		}, {
			title : "打赏主播",
			name : "anchorName",
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
 
 
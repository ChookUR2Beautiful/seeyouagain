var recordList;
var initListUrl = "experienceofficer/activity/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
		' > 直播频道 > <a href="experienceofficer/activity/init.jhtml" target="right">美食体验活动管理</a> ',
		'userSpan', true);

	initExperienceofficerActivity();

	//日期控件初始化
	liveDateInit();

});

//初始化体验活动
function initExperienceofficerActivity() {

	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

}



function success(data, obj) {
//	var formId = "searchForm",
//		title = "共",
//		subtitle = "个体验活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
		+ '<font>共计【'
		+ data.total
		+ '】&nbsp;个体验场次</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
	+ getFormParam($("#searchForm").serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "update", "cancel" ], // 不需要选择checkbox处理的权限
			width : 150, // 宽度
			// 当前列的中元素
			cols : [
				{
					title : "取消", // 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "experienceofficer/activity/cancel/init.jhtml",
							position : "",
							width : "auto",
							title : "取消场次"
						},
						param : [ "id" ],
						permission : "cancel"
					},customMethod : function(value, data) {
						var result = '<a href="javascript:void(0)" title="活动已取消"  class="hidden"></a>';
						if(data.activityState!=undefined && data.activityState == 1){
							result = value;
						}
						return result;
					}					
				},{
					title : "修改", // 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "experienceofficer/activity/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改场次"
						},
						param : [ "id" ],
						permission : "update"
					}
				}
			]
		},
		cols : [ {
			title : "活动编号",
			name : "id",
			type : "string",
			width : 60
		}, {
			title : "预告",
			name : "zhiboTitle",
			type : "string",
			width : 150
		}, {
			title : "商家/活动",
			name : "sellername",
			type : "string",
			width : 100
		}, {
			title : "体验时间",
			name : "planStartDateStr",
			type : "string",
			width : 120
		}
		, {
			title : "开抢时间",
			name : "enrollTimeStr",
			type : "string",
			width : 120
		}
		, {
			title : "名额",
			name : "limitNum",
			type : "string",
			width : 60
		}, {
			title : "中奖用户",
			name : "userInfo",
			type : "string",
			width : 160,
			customMethod : function(value, data) {
				if(data!=null && data.userInfo != null){
//					return "<span width: auto;word-break: break-all;word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow:hidden;' data-toggle=\"popover\" title=\""+ data.userInfo+"\">"+ data.userInfo + "</span>";
					return "<a href='javascript:void(0)' title="+data.userInfo+">"+data.userInfo+"</a>"
				}else{ 
				 return "-";
				}
			}
		}, {
			title : "状态",
			name : "activityStateStr",
			type : "string",
			width : 100
		}]
	}, permissions);
}


/**
 * 直播日期控件初始化
 */
function liveDateInit() {
//	$('.form_datetime').datetimepicker({
//		format : 'yyyymm',
//		weekStart : 1,
//		autoclose : true,
//		startView : 3,
//		minView : 3,
//		forceParse : false,
//		language : 'zh-CN'
//	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minView :2,
		format : 'yyyy-mm-dd'
	});
}


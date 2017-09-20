var enrollList1;//全部选手
var imgRoot = $("#fastfdsHttp").val();


$(document).ready(function() {
	inserTitle('> 新食尚大赛> <a href="vstarRewardSend/manage/init.jhtml" target="right">推荐奖励管理</a>','enrollConfirm',true);
	
	pageInit();
	
	liveDateInit();
	
});

/**
 * 加载页面数据
 */
function pageInit(){
	enrollList1 = $('#enrollList1').page({
		url : 'vstarRewardSend/manage/init/list.jhtml',
		success : success1,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 10,//每页条数
		paramForm : 'searchForm1',
		param :{}
	});
	
}

/**
 * 查询全部选手信息成功回调函数
 * @param data
 * @param obj
 */
function success1(data, obj) {
	var formId = "searchForm1", title = "奖励列表", subtitle = "条信息";
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
			
		},
		cols : [ {
			title : "获奖名单",
			name : "nname",
			type : "string",
			width : 100
		},{
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 100
		} ,{
			title : "奖励类型",
			name : "rewardTypeStr",
			type : "string",
			width : 100
		},{
			title : "获得奖励",
			name : "rewardComment",
			type : "string",
			width : 100
		},{
			title : "获奖时间",
			name : "createTimeStr",
			type : "string",
			width : 100
		}]
	}, permissions);
}


/**
 * 日期控件初始化
 */
function liveDateInit(){
	 
	 $('input[name="startTime"]').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
//			startDate : new Date(),
			endDate: $("input[name='endTime']").val()
		}).on("changeDate",function() {
				$("input[name='endTime']").datetimepicker("setStartDate",$("input[name='startTime']").val());
			});
		
		$('input[name="endTime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate: $("input[name='startTime']").val()
		}).on( "changeDate", function() {
					$("input[name='startTime']").datetimepicker("setEndDate", $("input[name='endTime']").val());
				});
}

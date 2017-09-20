var recordList;
var initListUrl = "liveRecord/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveRecord/manage/init.jhtml" target="right">通告管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	liveDateInit();
	
	//导出
	$("#export").click(function(){
		var path="liveRecord/manage/export.jhtml";
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
	var formId = "shareForm",  subtitle = "个通告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;'
			+ '<font>'
			+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直播时长：'
			+ data.titleInfo.hoursFromSecond+'&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		cols : [ 
		{
			title : "日期",
			name : "planStartDateStr",
			type : "string",
			width : 150
		},{
			title : "直播类型",
			name : "liveStartTypeStr",
			type : "string",
			width : 150
		}, {
			title : "每一场时间",
			name : "zhiboDurationStr",
			type : "string",
			width : 150
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		} ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id,sequenceNo){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveRecord/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id+'&sequenceNo='+sequenceNo,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 recordList.reload();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 /**
  * 预告设置
  * @param id
  * @param zhiboType
  */
 function setAdvance(id,zhiboType){
	 $.ajax({
         url : "liveRecord/manage/setAdvance.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"zhiboType":zhiboType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }
 
/**
 * 
 * @param id
 * @param operationType
 */
 function upOrDown(id,operationType){
	 $.ajax({
         url : "liveRecord/manage/upOrDown.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"operationType":operationType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			showWarningWindow("warning", result.msg);
     		}
         }
     });
 }
 
 /**
  * 切换tab页，重新加载数据
  * @param isAll
  */
 function tabChange(isAll){
	 $("#isAll").val(isAll);
	 slide(isAll);
	 pageInit();
 }
 
 
 /**
  * 切换样式
  * @param i
  */
 function slide(i){
 	$('#myTab li').eq(i-1).addClass('active').siblings().removeClass('active');
 }
 
 /**
  * 初始化日期控件
  */
 function liveDateInit(){
	//限定直播日期
		limitedDate({
			form:"#searchForm",
			startDateName:"startTime",
			endDateName:"endTime",
			overlap:true,
			format : 'yyyy-mm-dd hh:ii',
			minuteStep:1,
			minView : 0,
		});
 }
 
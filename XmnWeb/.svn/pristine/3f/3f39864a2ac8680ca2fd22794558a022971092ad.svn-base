var liveLedgerRecordList;
var initListUrl = "liveLedgerRecord/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveLedgerRecord/manage/init.jhtml" target="right">红包记录</a>',
			'userSpan', true);
	liveLedgerRecordList = $("#liveLedgerRecordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	liveDateInit();
	
	//导出
	$("#export").click(function(){
		var path="liveLedgerRecord/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	
	
});

function success(data, obj) {
	var formId = "shareForm", title = "红包列表", subtitle = "个红包";
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
		cols : [ {
			title : "用户编号",
			name : "uid",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return value;
			}
		}, {
			title : "用户昵称",
			name : "nname",
			type : "string",
			width : 150
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 150
		}, {
			title : "分账鸟币面额",
			name : "ledgerAmount",
			type : "string",
			width : 150
		}, {
			title : "实际领取分账金额",
			name : "realLedgerAmount",
			type : "string",
			width : 150
		} , {
			title : "分账状态",
			name : "statusStr",
			type : "string",
			width : 150
		} , {
			title : "领取时间",
			name : "redpacketRocordDate",
			type : "string",
			width : 150
		} ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveLedgerRecord/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 liveLedgerRecordList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 批量删除
  */
 $('#delete').click(function(){
 	var ids = liveLedgerRecordList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveLedgerRecord/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					liveLedgerRecordList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
 
 
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
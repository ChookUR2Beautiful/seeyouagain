var anchorList;
var initListUrl = "nectarProfit/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 黄金庄园 > <a href="nectarProfit/manage/init.jhtml" target="right">花蜜收益管理</a>',
			'userSpan', true);
	
	anchorList = $("#anchorList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//导出
	$("#exportAnchor").click(function(){
		/*var size = getCurrentDataSize();
		if(size>1000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}*/
		
		var path="nectarProfit/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

function success(data, obj) {
	var formId = "searchForm";
	var title = "花蜜收益列表", subtitle = "个花蜜收益";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 100
		},{
			title : "会员昵称",
			name : "nickname",
			type : "string",
			width : 120
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 120
		},{
			title : "目前等级",
			name : "levelName",
			type : "string",
			width : 100
/*		}, {
			title : "目前花朵数",
			name : "recommendName",
			type : "string",
			width : 100
		}, {
			title : "玫瑰花田",
			name : "comsumLedger",
			type : "string",
			width : 80,
			customMethod:function(value,data){
				var result="";
				if(value){
					result=value.replace(/,/g,"<br/>");//替换所有逗号
				}
				return result;
			}
		} , {
			title : "兰花田",
			name : "expectedPriviledgeLedger",
			type : "string",
			width : 200
		} , {
			title : "葵花田",
			name : "currentConsumeLedger",
			type : "string",
			width : 200*/
		}, {
			title : "日收益花蜜",
			name : "number",
			type : "string",
			width : 200
		}, {
			title : "状态",
			name : "status",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				var result="";
				var status = data.status;  //1.待入账 2.已入账 3.入账失败 4.取消入账'
				if(status == 1){
					result="待入账";//替换所有逗号
				}else if(status == 2){
					result="已入账";//替换所有逗号
				}else if(status == 3){
					result="入账失败";//替换所有逗号					
				}else if(status == 4){
					result="取消入账";//替换所有逗号							
				}else{
					result="-";
				}
				
				return result;
			}			
		}, {
			title : "收益时间",
			name : "createTime",
			type : "string",
			width : 200
		}]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id,uid){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "nectarProfit/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id,uid:uid},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 anchorList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 

 /**
 * 获取当前查询记录数
 */
function getCurrentDataSize() {
	var formId = "searchForm";
	var total = 0;
	// 设置同步
	$.ajaxSetup({
		async : false
	});

	$.ajax({
		url : "nectarProfit/manage/init/getCurrentDataSize.jhtml",
		type : "post",
		dataType : "json",
		data : jsonFromt($('#' + formId).serializeArray()),
		success : function(result) {
			total = result;
		}
	});

	// 恢复异步
	$.ajaxSetup({
		async : true
	});

	return total;
}
var recordList;
var initListUrl = "saasTag/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 商家端数据统计 > <a href="saasTag/manage/init.jhtml" target="right">标签管理</a>',
			'userSpan', true);
	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
});

function success(data, obj) {
	var formId = "shareForm", title = "标签列表", subtitle = "个标签";
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
	    checkable : $("#checkable").val(),
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
				 /*{
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveAndroid/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete(\""+data.id+"\")'>" + "删除" + "</a>";
				            return value;
				    }
				 },*/
				 {
						title : "修改",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "saasTag/manage/update/init.jhtml",
							param : [ "id" ],
							permission : "update"
						}
				 } 
			]
		},
		cols : [ {
			title : "编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "标签名称",
			name : "name",
			type : "string",
			width : 150
		}, {
			title : "标签选项",
			name : "tagNames",
			type : "string",
			width : 350
		}, {
			title : "使用类型",
			name : "type",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				switch (value) {
				case 1:
					result = "模板标签";
					break;
				case 2:
					result = "主播标签";
					break;
				case 3:
					result = "名嘴标签";
					break;
				default:
					result = "";
					break;
				}
				return result;
			}
		} ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveAndroid/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 recordList.reload();
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
 	var ids = recordList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveAndroid/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList.reset();
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
  * 清除机器人
  */
 $('#deleteAll').click(function(){
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveAndroid/manage/deleteAll.jhtml' + '?t=' + Math.random(),
 			data : {},
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	},'确定删除所有机器人信息?');
 });
 
 
 /**
  * 清除机器人头像
  */
 $('#deleteImages').click(function(){
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveAndroid/manage/deleteImages.jhtml' + '?t=' + Math.random(),
 			data : {},
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
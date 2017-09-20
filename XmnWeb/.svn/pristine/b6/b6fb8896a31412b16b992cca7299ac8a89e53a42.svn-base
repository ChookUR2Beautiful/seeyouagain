var broadcastList;
var initListUrl = "liveBroadcast/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveBroadcast/manage/init.jhtml" target="right">广播管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	

});

/**
 * 加载页面数据
 */
function pageInit(){
	broadcastList = $("#broadcastList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
}

/**
 * 查询礼物成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var formId = "shareForm", title = "广播列表", subtitle = "个广播";
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
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
				
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveBroadcast/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+","+data.sequenceNo+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
		         {
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveBroadcast/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改通告"
						},
						param : [ "id" ],
						permission : "update"
					}
				} ,
				{
					title : "重发",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveBroadcast/manage/resend.jhtml",
						param : [ "id" ],
						permission : "resend"
					},
					customMethod : function(value, data){
						var value="";
						var params="";
						params="\"" + data.id +"\"" ;
			            value = "<a href='javascript:resend(" + params + ")'>" + "重发" + "</a>";
				        return value;
				    }
				} 
				
			]
		},
		cols : [ {
			title : "广播编号",
			name : "id",
			type : "string",
			width : 100
		}, {
			title : "广播设置",
			name : "nickname",
			type : "string",
			width : 100,
			customMethod : function(value, data) {
				if(value=='-'){
					value='全房间';
				}
				return value;
			}
		}, {
			title : "播放次数",
			name : "playAmount",
			type : "string",
			width : 100
		}, {
			title : "广播内容",
			name : "content",
			type : "string",
			width : 250
		}, {
			title : "发送时间",
			name : "sendTimeStr",
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
			 url : "liveBroadcast/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id+'&sequenceNo='+sequenceNo,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 broadcastList.reload();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 /**
  * 重新发送广播
  * @param id
  */
 function resend(id){
	 $.ajax({
         url : "liveBroadcast/manage/resend.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }

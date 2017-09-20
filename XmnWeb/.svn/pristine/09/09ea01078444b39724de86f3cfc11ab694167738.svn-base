var pageDiv;
$(document).ready(function() {
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	//标题
	inserTitle(' > 商家管理 > <span><a href="businessman/specialTopic/init.jhtml" target="right">专题管理</a>','sellerList',true);
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/businessman/specialTopic/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
	});
	
	
	pageDiv = $('#specialTopicInfoDiv').page({
		url : 'businessman/specialTopic/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'specialTopicFromId'
	});
	
});


function success(data, obj) {
	var formId = "specialTopicFromId", title = "专题列表", subtitle = "个专题";
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+ data.total+ '】' + subtitle + '&nbsp;</font></caption>';
	
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());
//	debugger;
	updateAddBtnHref("#addSpecialTopicBto", callbackParam);
	
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "xg","del","link" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
		    cols : [
				 {
					title : "复制链接",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "businessman/specialTopic/copyLinkUrl.jhtml",
						param : [ "id" ],
						permission : "link"
					},
					customMethod : function(value, data) {
						return "<a href='javascript:;' onclick='copyLinkUrl(" + data.id + ")'>复制链接</a>"
					}
				 }, 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "businessman/specialTopic/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "del"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete(\""+data.id+"\",\""+data.uid+"\")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
				{
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "businessman/specialTopic/update/init.jhtml",
						param : [ "id" ],
						permission : "xg"
					}
				} 
				
			]
		},
		cols : [ {
			title : "专题标题",
			name : "title",
			type : "string",
			width : 150
		}, {
			title : "专题类型",
			name : "topicTypeDesc",
			type : "string",
			width : 150
		}, {
			title : "关联内容",
			name : "relationNum",
			type : "string",
			width : 150,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/specialTopic/viewRelation.jhtml"
				},
				param : ["id", "topicType"],
				permission : "list"
			}
		}]
	}, permissions);
}



/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "businessman/specialTopic/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 pageDiv.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"将删除该专题的所有关联信息，确定要删除吗？");
 }
 
 
function copyLinkUrl(id){
	var linkUrl = specialTopicViewLink+"?id="+id;//this.location.href;
	
	var clipBoardContent=document.getElementById("linkInfo");
	clipBoardContent.value=linkUrl;
	clipBoardContent.select(); // 选择对象
	document.execCommand("Copy"); // 执行浏览器复制命令
//	alert("复制成功!");
	showSmReslutWindow(true, "复制链接成功!");
}

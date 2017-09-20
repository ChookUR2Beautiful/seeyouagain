var anchorList;
var initListUrl = "anchorBusiness/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="anchorBusiness/manage/init.jhtml" target="right">商家版展示管理</a>',
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
		var path="anchorBusiness/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

function success(data, obj) {
	var formId = "shareForm", title = "主播列表", subtitle = "个主播";
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
						href : "anchorBusiness/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete(\""+data.id+"\")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
		         {
					title : "完善主播信息",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "anchorBusiness/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "完善主播信息"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
			]
		},
		cols : [ {
			title : "主播编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "主播昵称",
			name : "nickname",
			type : "string",
			width : 150
		}, {
			title : "粉丝数量",
			name : "concernedNums",
			type : "string",
			width : 150
		}, {
			title : "身高",
			name : "height",
			type : "string",
			width : 150
		}, {
			title : "体重",
			name : "weight",
			type : "string",
			width : 150
		} , {
			title : "三围",
			name : "threeDimensional",
			type : "string",
			width : 150
		}  ,{
			title : "说明",
			name : "selfComment",
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
			 url : "anchorBusiness/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
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
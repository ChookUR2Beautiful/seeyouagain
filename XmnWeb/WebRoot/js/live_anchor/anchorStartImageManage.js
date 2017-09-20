var recordList;
var initListUrl = "anchorBusiness/manage/startImage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="anchorBusiness/manage/init.jhtml" target="right">商家版展示管理</a> > <a href="anchorBusiness/manage/startImage/init.jhtml" target="right">开启直播图管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//导出
	$("#export").click(function(){
		var path="anchorBusiness/manage/export.jhtml";
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
	var formId = "shareForm", title = "开启直播图列表";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title;
			
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
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "anchorBusiness/manage/startImage/delete.jhtml",// url
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
							url : "anchorBusiness/manage/startImage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改页面配图"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "页面图",
			name : "stringPageImage",
			type : "string",
			width : 150
		},{
			title : "图片",
			name : "imageUrl",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		}, {
			title : "链接",
			name : "mediaUrl",
			type : "string",
			width : 180
		} ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id,sequenceNo){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "anchorBusiness/manage/startImage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id ,
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
  * 直播日期控件初始化
  */
 function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});
 }
 
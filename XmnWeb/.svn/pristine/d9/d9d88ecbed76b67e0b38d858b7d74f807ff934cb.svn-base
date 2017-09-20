var materialAttrList;
var initListUrl = "materialAttr/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 雏鸟云设计 > <a href="materialAttr/manage/init.jhtml" target="right">规格管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//导出
	$("#export").click(function(){
		var path="materialAttr/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	materialAttrList = $("#materialAttrList").page({
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
 * 查询规格成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var formId = "shareForm", title = "规格列表", subtitle = "个规格";
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
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "materialAttr/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				 } ,
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "materialAttr/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "deleteTODO"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 }
			]
		},
		cols : [ {
			title : "排序",
			name : "sortVal",
			type : "string",
			width : 100
		}, {
			title : "规格",
			name : "name",
			type : "string",
			width : 150
		}, {
			title : "规格细项",
			name : "attrVals",
			type : "string",
			width : 350
		},{
			title : "是否定制",
			name : "isCustomize",
			type : "string",
			width : 100,
			customMethod : function(value, data){
				var recommend='';
				switch (value) {
				case '001':
					recommend='定制';
					break;
				default:
					recommend='非定制';
					break;
				}
				return recommend;
			}
		} ]
	}, permissions);
}


/**
 * 删除操作
 */
 function confirmDelete(id){
	 //TODO 有关联规格、商品时，不能删除，弹出警告信息
	 
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "materialAttr/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{'id':id},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 materialAttrList.reload();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }

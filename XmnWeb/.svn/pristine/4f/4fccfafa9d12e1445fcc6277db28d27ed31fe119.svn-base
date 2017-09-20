var recruitTagList;
var initListUrl = "dataDictionary/tag/init/list.jhtml";
$(function() {
	inserTitle(
			' > 基础数据管理 > <a href="dataDictionary/tag/init.jhtml" target="right">标签管理</a>',
			'userSpan', true);
	recruitTagList = $("#recruitTagList").page({
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
	var formId = "searchForm", title = "标签列表", subtitle = "个标签";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "brandId",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update" ],// 不需要选择checkbox处理的权限
			width : 50,// 宽度
			// 当前列的中元素
			cols : [ {
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "dataDictionary/tag/update/init.jhtml",
						position : "",
						width : "auto",
						title : "修改标签"
					},
					param : [ "id" ],
					permission : "update"
				}
			} ]
		},
		cols : [ {
			title : "操作人",
			name : "operator",
			type : "string",
			width : 150
		}, {
			title : "标签名称",
			name : "tagname",
			type : "string",
			width : 150
		}, {
			title : "标签类型",
			name : "tagTypeStr",
			type : "string",
			width : 150
		}, {
			title : "发布率",
			name : "tagCount",
			type : "string",
			width : 150
		} ]
	}, permissions);
}
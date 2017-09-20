var classifyList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	classifyList = $('#classifyList').page({
		url : 'businessman/classify/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'classifyForm'
	});
	inserTitle(' > 商家管理 > <a href="businessman/classify/init.jhtml" target="right">标签管理</a>','classifyList',true);
	
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个标签&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#classifyForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content,
			 //数据行
			cols:[{
				title : "编号",// 标题
				name : "id",
				//sort : "up",
				width : 120,// 宽度
				type:"int",//数据类型		
		},{
			title : "分类",// 标题
			name : "classifyName",
			//sort : "up",
			width : 220,// 宽度
			type:"string",//数据类型
		}, {
			title : "标签",
			name : "tagName",
			type : "string",
			width : 150
		}, {
			title : "类型",
			name : "classifyTypeStr",
			type : "string",
			width : 150
		}]},permissions);
	
}


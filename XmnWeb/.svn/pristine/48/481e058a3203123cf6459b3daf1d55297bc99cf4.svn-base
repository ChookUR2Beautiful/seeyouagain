var helpList;
$(document).ready(function() {
	helpList = $('#helpList').page({
		url : 'user_terminal/help_manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(helpList.getIds());
	});
	
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/help_manage/init.jhtml" target="right">帮助条目管理</a>','userSpan',true);

	// 条目分类
	$("#itemId").chosenObject({
		id : "itemId",
		hideValue : "id",
		showValue : "item",
		url : "user_terminal/help_manage/item/list.jhtml",
		isChosen:false
	});
	
	$("input[data-bus=reset]").click(function() {
		//清楚Select的option的select属性
		if (location.href.indexOf("?") > 0) {
			var url = contextPath + '/user_terminal/help_manage/init.jhtml';
			location.href = url;
		}
	});
	
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/help_manage/export.jhtml");
		$form[0].submit();
	});
	
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/user_terminal/help_manage/init.jhtml';
			location.href =url;
		}
		$("#itemId").trigger("chosen:updated");
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	obj.find('div').eq(0).html($.renderGridView(helpModel,data));
}



var helpModel = {
		title : "帮助条目列表",
		totalTitle:"帮助条目",
		form : "searchForm",
		checkbox : true,
		backButton : true,
		addBtn : "addHelpInfoBtn",
		handleColumn :{
					title : "操作",
					name : "id",
					queryPermissions :["delete","update"],
					column : [{
								title : "修改",
								modal :  "user_terminal/help_manage/update/init.jhtml",// url
								param : ["id"],
								permissions : "update"
							}, {
								title : "删除",
								method : "removeAbnormal",
								param : ["id"],
								permissions : "delete"
							}]
		},
		columns : [{
					title : "分类",
					name : "item",
					width : "10%"
				}, {
					title : "标题",
					name : "title",
					width : "10%"
				}, {
					title : "内容",
					name : "context",
					width : "40%"
				}, {
					title : "编辑日期",
					name : "udate",
					width : "10%"
				}],
		permissions : permissions
	}


function removeAbnormal(id){
	showSmConfirmWindow(function(){
		 $.ajax({
	         url : "user_terminal/help_manage/delete.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'id=' + id +"&t="+Math.random(),
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				helpList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	}, "你确定删除吗？");
}
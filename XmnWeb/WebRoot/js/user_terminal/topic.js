var topicList;
$(document).ready(function() {
	topicList = $('#topicList').page({
		url : 'user_terminal/topic/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(topicList.getIds());
	});
	
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
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/topic/init.jhtml" target="right">成长记列表</a>','userSpan',true);

	$("input[data-bus=reset]").click(function() {
		//清楚Select的option的select属性
		if (location.href.indexOf("?") > 0) {
			var url = contextPath + '/user_terminal/topic/init.jhtml';
			location.href = url;
		}
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	obj.find('div').eq(0).html($.renderGridView(topicModel,data));
}


/**
 * 删除
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/topic/delete.jhtml' + '?t=' + Math.random(),
			data : {"id":id},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					topicList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}



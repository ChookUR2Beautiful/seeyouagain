var systemInfoList;
$(document).ready(function() {
	systemInfoList = $('#systemInfoList').page({
		url : 'user_terminal/systemInfo/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(systemInfoList.getIds());
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
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/systemInfo/init.jhtml" target="right">系统信息发布管理</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/systemInfo/export.jhtml");
		$form[0].submit();
	});
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">系统信息发布</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:80px;">操作</th>');
	html.push('<th >创建时间</th>');
	html.push('<th >发送时间</th>');
	html.push('<th >过期时间</th>');
	html.push('<th >发送状态</th>');
	html.push('<th >阅读数</th>');
	html.push('<th >发送对象</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
		html.push('<td><a href="javascript:viod(); " data-type="ajax"   data-url="user_terminal/systemInfo/update/init.jhtml?id='+data.content[i].id+'"  data-toggle="modal" >修改</a>&nbsp;/&nbsp;<a href="javascript:remove('+data.content[i].id+')">删除</a></td>');
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].fdate ? "-" : data.content[i].fdate) + '</td>');
		html.push('<td>' + (undefined == data.content[i].edate ? "-" : data.content[i].edate) + '</td>');
		var status = "-";
		if(data.content[i].status == 0)
		{
			status = "待发送";
		}
		else if(data.content[i].status == 1)
		{
			status = "已发送";
		}
		html.push('<td>' + status + '</td>');
		html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
		html.push('<td>' + (undefined == data.content[i].object ? "-" : data.content[i].object) + '</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
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
			url : 'user_terminal/systemInfo/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					systemInfoList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


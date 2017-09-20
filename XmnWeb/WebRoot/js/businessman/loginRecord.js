var loginRecordList;
$(document).ready(function() {
	loginRecordList = $('#loginRecordList').page({
		url : 'businessman/loginRecord/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(loginRecordList.getIds());
	});
	
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	inserTitle(' > 商家管理 > <a href="businessman/loginRecord/init.jhtml" target="right">商家登录查询</a>','userSpan',true);
	
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/loginRecord/export.jhtml");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家账号登录记录</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20%;">商家</th>');
	html.push('<th style="width:20%;">登录时间</th>');
	html.push('<th style="width:20%;">登录账号</th>');
	html.push('<th style="width:10%;">账号类型</th>');
	html.push('<th style="width:10%;">手机系统</th>');
	html.push('<th style="width:20%;">备注</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			var sellername = (undefined == data.content[i].sellername ? "-" : data.content[i].sellername);
			html.push('<td title ="'+sellername+'">' + substr(sellername,15) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].account ? "-" : data.content[i].account) + '</td>');
			html.push('<td>' + (undefined == data.content[i].typeText ? "-" : data.content[i].typeText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].system ? "-" : data.content[i].system) + '</td>');
			var remarks = (undefined == data.content[i].remarks ? "-" : data.content[i].remarks);
			html.push('<td title ="'+remarks+'">' + substr(remarks,15) + '</td>');
			html.push('</tr>');
		}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
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
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/loginRecord/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					loginRecordList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


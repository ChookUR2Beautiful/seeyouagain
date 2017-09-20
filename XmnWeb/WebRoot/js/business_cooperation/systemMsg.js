var systemMsgList;
$(document).ready(function() {
	systemMsgList = $('#systemMsgList').page({
		url : 'business_cooperation/systemMsg/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(systemMsgList.getIds());
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
	
	inserTitle(' > 合作商管理 > <a href="business_cooperation/systemMsg/init.jhtml" target="right">系统信息发布管理</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","business_cooperation/systemMsg/export.jhtml");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">系统信息推送</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandle = permissions && (permissions.update || permissions.del);
	if(hasHandle){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	html.push('<th style="width:100px;">创建时间</th>');
	html.push('<th style="width:100px;">发送时间</th>');
	html.push('<th style="width:100px;">过期时间</th>');
	html.push('<th style="width:100px;">发送状态</th>');
	html.push('<th style="width:100px;">信息内容</th>');
	html.push('<th style="width:100px;">阅读数</th>');
	html.push('<th style="width:100px;">发送对象</th>');
	html.push('<th style="width:100px;">是否推送</th>');
	html.push('<th style="width:100px;">后续动作类型</th>');
	html.push('<th style="width:100px;">后续动作</th>');
	html.push('<th style="width:100px;">通知类型</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandle){
				html.push('<th><input type="checkbox" val=' + data.content[i].sid + '  /></th>');
				html.push('<td>');
				if(permissions.update){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="business_cooperation/systemMsg/update/init.jhtml?sid='+data.content[i].sid+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
				}
				if(permissions.del){
					html.push('<a href="javascript:remove('+data.content[i].sid+')">删除</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].tdate ? "-" : data.content[i].tdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].edate ? "-" : data.content[i].edate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) + '</td>');
			html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
			html.push('<td>' + (undefined == data.content[i].object ? "-" : data.content[i].object) + '</td>');
			html.push('<td>' + (undefined == data.content[i].ispushText ? "-" : data.content[i].ispushText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].actiontypeText ? "-" : data.content[i].actiontypeText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].action ? "-" : data.content[i].action) + '</td>');
			html.push('<td>' + (undefined == data.content[i].typeText ? "-" : data.content[i].typeText) + '</td>');
			html.push('</tr>');
		}
	}else{
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
function remove(sid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'business_cooperation/systemMsg/delete.jhtml' + '?t=' + Math.random(),
			data : 'sid=' + sid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					systemMsgList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


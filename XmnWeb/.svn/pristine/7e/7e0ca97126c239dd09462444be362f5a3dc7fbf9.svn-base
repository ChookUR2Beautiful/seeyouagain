var commentReplyList;
var cid = $("input[name='cid']").val();
var sellerid = $("input[name='sellerid']").val();
$(document).ready(function() {
	commentReplyList = $('#commentReplyList').page({
		url : 'businessman/commentReply/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param :{cid: cid}
	});

	$('#delete').click(function() {
		remove(commentReplyList.getIds());
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
	
	inserTitle('> <a href="businessman/commentReply/init.jhtml?cid='+cid+'" target="right">商家评论回复</a>','commentReplyList');
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">评论回复</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandle = permissions && (permissions.update || permissions.del);
	if(hasHandle){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	
	html.push('<th style="width:100px;">用户昵称</th>');
	html.push('<th style="width:200px;">回复内容</th>');
	html.push('<th style="width:100px;">回复时间</th>');
	html.push('<th style="width:80px;">回复ID</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandle){
				html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
				html.push('<td>');
				if(permissions.update){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/commentReply/update/init.jhtml?id='+data.content[i].id+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
				}
				if(permissions.del){
					html.push('<a href="javascript:remove('+data.content[i].id+')">删除</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].fid ? "-" : data.content[i].fid) + '</td>');
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
			url : 'businessman/commentReply/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					commentReplyList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


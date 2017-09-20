var commentList;
var sellerid = $("input[name='sellerid']").val();
$(document).ready(function() {
	commentList = $('#commentList').page({
		url : 'businessman/comment/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
	});

	$('#delete').click(function() {
		remove(commentList.getIds());
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
	
	inserTitle('> <a href="businessman/comment/init.jhtml?sellerid='+sellerid+'" target="right">评论管理</a>','commentList');

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/comment/export.jhtml");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家评论</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandle = permissions && (permissions.update || permissions.del);
	if(hasHandle){
//		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:150px;">操作</th>');
	}
//	html.push('<th style="width:80px;">ID</th>');
	html.push('<th style="width:100px;">商家名称</th>');
//	html.push('<th style="width:80px;">用户ID</th>');
	html.push('<th style="width:100px;">用户昵称</th>');
	html.push('<th style="width:100px;">点评内容</th>');
	html.push('<th style="width:100px;">点评时间</th>');
	html.push('<th style="width:50px;">回复次数</th>');
	if(permissions.commentPic){
		html.push('<th style="width:50px;">评论图片</th>');
	}
	html.push('<th style="width:50px;">点评总分</th>');
	html.push('<th style="width:50px;">环境分</th>');
	html.push('<th style="width:50px;">服务分</th>');
	html.push('<th style="width:50px;">口味分</th>');
	html.push('<th style="width:50px;">房间分</th>');
	html.push('<th style="width:50px;">人均消费</th>');
	html.push('<th style="width:100px;">状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(hasHandle){
//					html.push('<th><input type="checkbox" val=' + data.content[i].cid + '  /></th>');
					html.push('<td>');
					if(permissions.update){
						html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/comment/update/init.jhtml?cid='+data.content[i].cid+'" data-toggle="modal" >修改</a>&nbsp;&nbsp;');
					}
					if(permissions.del){
						html.push('<a href="javascript:remove('+data.content[i].cid+')">删除</a>&nbsp;&nbsp;');
					}
					html.push('</td>');
				}
				var sellername = (undefined == data.content[i].sellername ? "-" : data.content[i].sellername);
				html.push('<td title ="'+sellername+'">' + substr(sellername,8) + '</td>');
				var nname = (undefined == data.content[i].nname ? "-" : data.content[i].nname);
				html.push('<td title ="'+nname+'">' + substr(nname,8) + '</td>');
				
				var content = (undefined == data.content[i].content ? "-" : data.content[i].content);
				html.push('<td title ="'+content+'">' + substr(content,8) + '</td>');
				
				html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
				if(permissions.commentReply){
					html.push('<td><a href="businessman/commentReply/init.jhtml?cid='+data.content[i].cid+'">'+(data.content[i].number && data.content[i].number != 0?data.content[i].number:'-')+'</a></td>');
				}else{
					html.push('<td>'+(data.content[i].number && data.content[i].number != 0?data.content[i].number:'-')+'</td>');
				}
				if(permissions.commentPic){
					html.push('<td><a href="businessman/commentPic/init.jhtml?cid='+data.content[i].cid+'" >查看</a></td>');
				}
				html.push('<td>' + (undefined == data.content[i].total ? "-" : data.content[i].total) + '</td>');
				html.push('<td>' + (undefined == data.content[i].hbranch ? "-" : data.content[i].hbranch) + '</td>');
				html.push('<td>' + (undefined == data.content[i].fbranch ? "-" : data.content[i].fbranch) + '</td>');
				html.push('<td>' + (undefined == data.content[i].kbranch ? "-" : data.content[i].kbranch) + '</td>');
				html.push('<td>' + (undefined == data.content[i].jbranch ? "-" : data.content[i].jbranch) + '</td>');
				html.push('<td>' + (undefined == data.content[i].percapita ? "-" : data.content[i].percapita) + '</td>');
				html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
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
function remove(cid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/comment/delete.jhtml' + '?t=' + Math.random(),
			data : 'cid=' + cid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					commentList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


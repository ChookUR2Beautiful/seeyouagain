
$(document).ready(function() {	
	var type=$('#type').val();
	var urls ="";
	if(type==1){ urls="user_terminal/tPost/tPostComment/post/init/list.jhtml";}
	else if(type==2){ urls="user_terminal/tPost/tPostComment/report/init/list.jhtml";}
	else if(type==3){ urls="user_terminal/tPost/tPostComment/recycle/init/list.jhtml";}
	
	postCommentList = $('#postCommentList').page({
		//url : 'user_terminal/tPostComment/init/list.jhtml',
		url : urls,
		success : successReply,
		pageBtnNum : 10,
		param : {tid: $("input[name='tid']").val()}/*,
		paramForm : 'searchForm'*/
	});
	subjectList = null;
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successReply(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" style="width:500px;">');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">帖子评论</caption>');
	html.push('<thead>');
	html.push('<tr>');
//	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
//	html.push('<th style="width:80px;">操作</th>');
	html.push('<th style="width:100px;">用户昵称</th>');
	html.push('<th style="width:80px;">评论内容</th>');
	html.push('<th style="width:80px;">评论时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
//		html.push('<th><input type="checkbox" val=' + data.content[i].subjectid + '  /></th>');
//		html.push('<td><a href="javascript:viod(); " data-type="ajax"   data-url="business_cooperation/subject/update/init.jhtml?subjectid='+data.content[i].subjectid+'" data-width="1024" data-toggle="modal" >详情</a>&nbsp;/&nbsp;<a href="javascript:remove('+data.content[i].subjectid+')">删除</a></td>');
		html.push('<td><b>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + ':</b></td>');
		 var contents=(undefined == data.content[i].content ? "-" : data.content[i].content);
		html.push('<td title ="'+contents+'">' + substr(contents) + '</td>');
		html.push('<td>发表与：' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
/*function remove(subjectid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'business_cooperation/subject/delete.jhtml' + '?t=' + Math.random(),
			data : 'subjectid=' + subjectid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					subjectList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/


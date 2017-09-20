
$(document).ready(function() {
	subjectReplyList = $('#subjectReplyList').page({
		url : 'business_cooperation/subject/view/init/subjectReplyList.jhtml',
		success : successReply,
		pageBtnNum : 10,
		param : {subjectid: $("input[name='subjectid']").val()}/*,
		paramForm : 'searchForm'*/
	});
	subjectList = null;
	/*$('#delete').click(function() {
		remove(subjectList.getIds());
	});*/

//	inserTitle(' > 合作商管理 > <a href="business_cooperation/subject/init.jhtml" target="right">话题交流管理</a>','userSpan',true);
	
	
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">话题回复</caption>');
	html.push('<thead>');
	html.push('<tr>');
//	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
//	html.push('<th style="width:80px;">操作</th>');
//	html.push('<th style="width:100px;">员工姓名</th>');
//	html.push('<th style="width:80px;">回复内容</th>');
//	html.push('<th style="width:80px;">回复时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
//		html.push('<th><input type="checkbox" val=' + data.content[i].subjectid + '  /></th>');
//		html.push('<td><a href="javascript:viod(); " data-type="ajax"   data-url="business_cooperation/subject/update/init.jhtml?subjectid='+data.content[i].subjectid+'" data-width="1024" data-toggle="modal" >详情</a>&nbsp;/&nbsp;<a href="javascript:remove('+data.content[i].subjectid+')">删除</a></td>');
		html.push('<td><b>' + (undefined == data.content[i].fullname ? "-" : data.content[i].fullname) + ':</b></td>');
		html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) + '</td>');
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


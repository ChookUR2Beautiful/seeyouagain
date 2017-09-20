var commentPicList;
var cid = $("input[name='cid']").val();
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	commentPicList = $('#commentPicList').page({
		url : 'businessman/commentPic/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param :{cid: cid}
	});

	$('#delete').click(function() {
		remove(commentPicList.getIds());
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
	
	
	inserTitle('> <a href="businessman/commentPic/init.jhtml?cid='+cid+'" target="right">商家评论图片</a>','commentPicList');
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">评论图片</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandle = permissions && (permissions.update || permissions.del);
	if(hasHandle){
//		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	
	html.push('<th style="width:100px;">图片名称</th>');
	html.push('<th style="width:100px;">图片类型</th>');
	html.push('<th style="width:100px;">上传时间</th>');
	html.push('<th style="width:100px;">图片</th>');
	html.push('<th style="width:100px;">缩略图</th>');
	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandle){
//				html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
				html.push('<td>');
				if(permissions.update){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/commentPic/update/init.jhtml?id='+data.content[i].id+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
				}
				if(permissions.del){
					html.push('<a href="javascript:remove('+data.content[i].id+')">删除</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			
			html.push('<td>' + (undefined == data.content[i].picname ? "-" : data.content[i].picname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].pid ? "-" : data.content[i].pid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].picurl ? "-" : '<img style="width:100px;height:100px;" src="'+imgRoot+data.content[i].picurl+'"/>') + '</td>');
			html.push('<td>' + (undefined == data.content[i].picurl ? "-" : '<img style="width:100px;height:100px;" src="'+imgRoot+data.content[i].picsurl+'"/>') + '</td>');
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
			url : 'businessman/commentPic/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					commentPicList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


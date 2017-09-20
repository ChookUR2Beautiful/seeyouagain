var advertisingList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	advertisingList = $('#advertisingList').page({
		url : 'common/advertising/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(advertisingList.getIds());
	});
	
	inserTitle(' > 合作商管理 > <a href="common/advertising/init.jhtml" target="right">广告轮播管理</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","common/advertising/export.jhtml");
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">广告轮播</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:20px;"><input type="checkbox" /></th>');
	html.push('<th style="width:80px;">操作</th>');
	html.push('<th style="width:100px;">广告图片</th>');
	html.push('<th style="width:100px;">广告文本</th>');
	html.push('<th style="width:100px;">广告链接</th>');
	html.push('<th style="width:100px;">排序</th>');
	html.push('<th style="width:100px;">是否显示</th>');
	html.push('<th style="width:100px;">类型</th>');
	html.push('<th style="width:100px;">备注</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');
		html.push('<td><a href="javascript:viod(); " data-type="ajax"   data-url="common/advertising/update/init.jhtml?id='+data.content[i].id+'" data-width="55%" data-toggle="modal" >修改</a>&nbsp;/&nbsp;<a href="javascript:remove('+data.content[i].id+')">删除</a></td>');
		html.push('<td>' + (undefined == data.content[i].adbpic ? "-" : '<img style="width:50px;height:50px;" src="'+imgRoot+data.content[i].adbpic+'"/>') + '</td>');
		html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) + '</td>');
		html.push('<td>' + (undefined == data.content[i].adburl ? "-" : data.content[i].adburl) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sort ? "-" : data.content[i].sort) + '</td>');
		html.push('<td>' + (undefined == data.content[i].isshowText ? "-" : data.content[i].isshowText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].typeText ? "-" : data.content[i].typeText) + '</td>');
		html.push('<td>' + (undefined == data.content[i].remarks ? "-" : data.content[i].remarks) + '</td>');
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
			url : 'common/advertising/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					advertisingList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


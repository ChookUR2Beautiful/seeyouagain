var roleUser;
$(document).ready(function() {
	var roleUserList = $('#roleUserList');
	$(function(){
		var url  = [$(roleUserList).attr("request-init"),".jhtml"].join("");
		roleUser = $(roleUserList).page({
			url : url,
			success : success,
			pageBtnNum : 10,
			paramForm : 'searchForm',
			param:{roleId:$("#roleid").val()}
		});
	});
	
	inserTitle('<span class="text-mute">  系统管理 > </span><span class="text-mute"><a href="system_settings/role/init.jhtml" target="right">角色管理</a> </span> > 角色用户查看','roleUser',true);
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">用户</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:80px;">用户名</th>');
	html.push('<th style="width:80px;">所属角色</th>');
	html.push('<th style="width:100px;">邮箱</th>');
	html.push('<th style="width:50px;">是否启用</th>');
	html.push('<th style="width:50px;">是否锁定</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
		
			html.push('<td title ="'+data.content[i].username+'">' + (undefined == data.content[i].username ? "-" : substr(data.content[i].username,10))+ '</td>');
			html.push('<td title ="'+data.content[i].roleName+'">' + (undefined == data.content[i].roleName ? "未绑定角色" : substr(data.content[i].roleName,10)) + '</td>');
			html.push('<td title ="'+data.content[i].email+'">' + (undefined == data.content[i].email ? "-" : substr(data.content[i].email,10)) + '</td>');
			html.push('<td class="text-ellipsis">' + (data.content[i].isEnabled == true ? '是' : '否') + '</td>');
			html.push('<td class="text-ellipsis">' + (data.content[i].isLocked == true ? '是' : '否') + '</td>');
			html.push('</tr>');
		}
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}


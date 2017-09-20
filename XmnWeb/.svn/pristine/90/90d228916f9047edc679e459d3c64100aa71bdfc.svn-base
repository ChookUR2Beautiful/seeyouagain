var userList;
$(document).ready(function() {
	userList = $('#userList').page({
		url : 'system_settings/user/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(userList.getIds());
	});
	
	inserTitle(' > 系统管理 > <a href="system_settings/user/init.jhtml" target="right">用户管理</a>','userSpan',true);
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
	var isEmpty = permissions.update == ''&&permissions.xgm == '';
	if(!isEmpty){
		/*html.push('<th style="width:20px;"><input type="checkbox" /></th>');*/
		html.push('<th style="width:160px;">操作</th>');
		
	}
	if(permissions.bdj == 'true'){
		html.push('<th style="width:100px;">角色操作</th>');
	}
	html.push('<th style="width:80px;">用户名</th>');
	html.push('<th style="width:80px;">所属角色</th>');
	html.push('<th style="width:100px;">邮箱</th>');
	html.push('<th style="width:50px;">是否启用</th>');
	html.push('<th style="width:50px;">是否锁定</th>');
	html.push('<th style="width:130px;">锁定日期</th>');
	html.push('<th style="width:130px;">登录日期</th>'); 
	html.push('<th style="width:130px;">登录IP</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			
			if(!isEmpty){
				//html.push('<th><input type="checkbox" val=' + data.content[i].userId + '  /></th>');
				html.push('<td>');
				if(permissions.update == 'true'){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="system_settings/user/update/init.jhtml?userId='+data.content[i].userId+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
				}
				
				/*if(permissions.del == 'true'){
					html.push('<a href="javascript:remove('+data.content[i].userId+')">删除</a>&nbsp;&nbsp;');
				}*/
				
				if(permissions.xgm == 'true'){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="system_settings/user/changePassword/init.jhtml?uid='+data.content[i].userId+'"  data-toggle="modal" >修改密码</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
				
				
			}
			if(permissions.bdj == 'true'){
				var bdjs = undefined == data.content[i].roleName ? "绑定角色":"修改绑定角色";
				html.push('<td><a href="javascript:viod(); " data-type="ajax"   data-url="system_settings/user/bindRole/init.jhtml?uid='+data.content[i].userId+'"  data-toggle="modal" >'+bdjs+'</a></td>');
			}
			
			html.push('<td title ="'+data.content[i].username+'">' + (undefined == data.content[i].username ? "-" : substr(data.content[i].username,10))+ '</td>');
			html.push('<td title ="'+data.content[i].roleName+'">' + (undefined == data.content[i].roleName ? "未绑定角色" : substr(data.content[i].roleName,10)) + '</td>');
			html.push('<td title ="'+data.content[i].email+'">' + (undefined == data.content[i].email ? "-" : substr(data.content[i].email,10)) + '</td>');
			html.push('<td class="text-ellipsis">' + (data.content[i].isEnabled == true ? '是' : '否') + '</td>');
			html.push('<td class="text-ellipsis">' + (data.content[i].isLocked == true ? '是' : '否') + '</td>');
			html.push('<td class="text-ellipsis" title ="'+data.content[i].lockedDate+'">' + (undefined == data.content[i].lockedDate ? "-" : substr(data.content[i].lockedDate,15)) + '</td>');
			html.push('<td class="text-ellipsis" title ="'+data.content[i].loginDate+'">' + (undefined == data.content[i].loginDate ? "-" : substr(data.content[i].loginDate,15)) + '</td>');
			html.push('<td class="text-ellipsis" title ="'+data.content[i].loginIp+'">' + (undefined == data.content[i].loginIp ? "-" : substr(data.content[i].loginIp,15)) + '</td>');
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

/**
 * 删除
 */
/*function remove(userId) {
	if(!userId){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'system_settings/user/delete.jhtml' + '?t=' + Math.random(),
			data : 'userId=' + userId,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					userList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

function add() {
	showEditWindow('添加用户', 'body');
}



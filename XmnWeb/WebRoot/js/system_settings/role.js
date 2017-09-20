var roleList;
var map={};

$(document).ready(function() {
	inserTitle(' <span class="text-mute"> > 系统管理 > </span><span class="text-mute"><a href="system_settings/role/init.jhtml" target="right">角色管理</a> </span>','sellerList',true);
	roleList = $('#roleList').page({
		url : 'system_settings/role/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(roleList.getIds());
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">角色列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var cz = permissions.xg==""&&permissions.sc=="";
	if(!cz){
		html.push('<th style="width:10%;">操作</th>');
	}
	var sx = permissions.nz==""&&permissions.qx==""&&permissions.qyqx=="";
	if(!sx){
		html.push('<th style="width:30%;">角色属性操作</th>');
	}
	html.push('<th style="width:40%;">角色名称</th>');
	html.push('<th style="width:10%;">是否内置</th>');
	//html.push('<th style="width:100px;">roleId</th>');
	html.push('<th style="width:10%;">描述</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody'); 
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
		for (var i = 0; i < data.content.length; i++) {
			if(undefined != data.content[i].roleId){ 
			html.push('<tr>');
			if(!cz){
				html.push('<td style="width:120px;">');
				if(permissions.xg=='true'){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="system_settings/role/update/init.jhtml?roleId='+data.content[i].roleId+'"  data-toggle="modal" >修改</a>&nbsp;');
				}
				/*if(permissions.sc=='true'){
					var d = data.content[i].isSystem==0?'<a href="javascript:remove('+data.content[i].roleId+')">删除</a>&nbsp;&nbsp;':"&nbsp;";
					html.push(d);
				}*/
				html.push('</td>');
			}
			if(!sx){
				html.push('<td style="width:120px;">');
				if(permissions.nz=='true'){
					html.push('<a href="javascript:isSystem('+(data.content[i].isSystem==0?1:0)+","+(data.content[i].roleId)+')">'+(undefined == data.content[i].roleId ? "-" : data.content[i].isSystem==0?"内置":"不内置")+"</a>&nbsp;&nbsp;");
				}
				if(permissions.qx=='true'){
					html.push('<a href="system_settings/roleAuthority/init.jhtml?roleId='+data.content[i].roleId+callbackParam+'" >模块权限</a>&nbsp;&nbsp;');
				}
				if(permissions.qyqx=='true'){
					html.push('<a href="system_settings/dataAuthority/init.jhtml?roleId='+data.content[i].roleId+callbackParam+'" >区域权限</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			
			//html.push('<td>' + (undefined == data.content[i].roleId ? "-" : data.content[i].roleId) + '</td>');
			html.push('<td><a href=system_settings/role/init/roleUserList/init.jhtml?roleId='+data.content[i].roleId+callbackParam+' >'+data.content[i].roleName+'</a></td>');
			html.push('<td>' + (data.content[i].isSystem==0?"否":"是")+ '</td>');
			map[data.content[i].roleId]=data.content[i].description.toString();
			html.push('<td><a href="javascript:description('+data.content[i].roleId+')">查看</a></td>');
			html.push('</tr>');
		}	}
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
/*function remove(roleId) {
	if(!roleId){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'system_settings/role/delete.jhtml' + '?t=' + Math.random(),
			data : 'roleId=' + roleId,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					roleList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/
/**
 * 修改内置
 * @param {} roleId
 */
function isSystem(isSystem,roleId) {
	var content = ["确定需要修改角色为 ",isSystem==0?"不内置":"内置","?"].join("");
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'system_settings/role/updateNz.jhtml',
			data : {
					roleId: roleId,
					isSystem:isSystem
			},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					//window.location.reload();
					roleList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},content);
}

/**
 * 查看
 * @param {} description
 */
function description(id) {
	var div = $("<div>");
	$(div).text(map[id]);
	$(div).css("word-wrap","break-word");
	showEditWindow("描述",div);
}


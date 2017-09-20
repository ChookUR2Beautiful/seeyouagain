<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="editRoleForm">
		<input type="hidden"  name="roleId" value="${role.roleId}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;名称</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="roleName"  value="${role.roleName}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;描述</h5></th>	
					<th colspan="2">
						<textarea  name="description" rows="3" class="form-control" placeholder="角色描述">${role.description}</textarea>
					</th>
				</tr>
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
<script type="text/javascript">
$(document).ready(function() {

	validate("editRoleForm",{
		rules:{
			roleName:{
				required:true,
				rangelength:[1,20]
			},
			description:{
				required:true,
				maxlength:255
			}
		},
		messages:{
			roleName:{
				required:"角色名称不能为空！",
				rangelength:"名称长度为1-20字符!"
			},
			description:{
				required:"角色描述不能缺省!",
				maxlength:"角色描述内容最长255字符!"
			}
		}},formAjax);
}); 

function formAjax(){
	var url ;
	if($('#isType').val() ==  'add'){
		url = 'system_settings/role/add.jhtml' + '?t=' + Math.random();
	}else{
		url = 'system_settings/role/update.jhtml' + '?t=' + Math.random();
	}
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#editRoleForm').serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				if($('#isType').val() ==  'add'){
					roleList.reset();
				}else{
					roleList.reload();
				}
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
	return false;
}
</script>
</body>
</html>

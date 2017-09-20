<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>

<body>
	<form class="form-horizontal" role="form" id="bindRoleForm">
	<div data-placement="center" data-icon="heart" data-content="" type="button" class="messager messager-warning"></div>
		<!-- <div data-type="warning" class="messager messager-warning"><div class="messager-content"><i class="icon-warning-sign"></i> xxxxxx</div><div class="messager-actions"><button class="close action" type="button">×</button></div></div> -->
		<input type="hidden"  name="userId" value="${uid}">
		<table width="100%" class="borderBottom table table-form">
			<tbody>
				<tr>
					
						<td style="width:80px;">
									<h5>绑定角色:</h5> 
						</td>
						<td style="width:320px;">
								  <select class="form-control"  id="business" name="roleId">
								  	<option value="">请选择角色</option>
									<c:forEach items="${rList}" var = "list">
										<option value="${list.roleId}" >${list.roleName}</option>
									</c:forEach>
								  </select>
						</td>
					
				</tr>
				<c:if test="${!empty role}">
					<tr>
						
							<td style="width:80px;">
										<h5>当前角色:</h5> 
							</td>
							<td style="width:320px;">
									 <span name="content"><h5>${role.roleName}</h5></span>
							</td>
						
					</tr>
	 			</c:if>
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
		validate("bindRoleForm",{rules:{
			roleId:{
					required:true
				}
			},
			messages:{
				roleId:{
					required:"请选择角色!"
				}
			}},formAjax);
	}); 
	
	function formAjax(){
		var url ;
		$.ajax({
			cache :false,
			type : 'post',
			url : "system_settings/user/bindRole.jhtml",
			data : jsonFromt($('#bindRoleForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				 $('#triggerModal').modal('hide');
				if (data.success) {
					showSmReslutWindow("系统提示", data.msg);
				}
				userList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} 
</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<form class="form-horizontal" role="form" id="changePasswordForm">
	<div data-placement="center" data-icon="heart" data-content="" type="button" class="messager messager-warning"></div>
		<!-- <div data-type="warning" class="messager messager-warning"><div class="messager-content"><i class="icon-warning-sign"></i> xxxxxx</div><div class="messager-actions"><button class="close action" type="button">×</button></div></div> -->
		<input type="hidden"  name="userId" value="${uid}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;新密码:</h5></th>	
					<th colspan="2">
						<input type="password" class="form-control" id="newpassword" name="password" >
					</th>
				</tr>
	 			<tr>
	 					<th style="width:70px;"><h5>&nbsp;&nbsp;确认密码:</h5></th>
	 					<th colspan="2">
	 						<input type="password" class="form-control" name="repassword"  >
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
		validate("changePasswordForm",{rules:{
			password:{
					required:true,
					rangelength:[6,20]
				},
			repassword:{
					required:true,
					rangelength:[6,20],
					equalTo:"#newpassword"
				}
			},
			messages:{
				password:{
					required:"密码不能为空!",
					rangelength:"密码长度为  6-20 个字符之间"
				},
				repassword:{
					required:"确认密码不能为空!",
					rangelength:"密码长度为  6-20  个字符!",
					equalTo:"请保持和新密码一致！"
				}
			}},formAjax);
	}); 
	
	function formAjax(){
		var url ;
		$.ajax({
			cache :false,
			type : 'post',
			url : "system_settings/user/changePassword.jhtml",
			data : jsonFromt($('#changePasswordForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				 $('#triggerModal').modal('hide');
				if (data.success) {
					showSmReslutWindow("系统提示", data.msg);
					userList.reload();
				}
				
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

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form class="form-horizontal" role="form" id="editUserForm">
	<div data-placement="center" data-icon="heart" data-content="" type="button" class="messager messager-warning"></div>
		<!-- <div data-type="warning" class="messager messager-warning"><div class="messager-content"><i class="icon-warning-sign"></i> xxxxxx</div><div class="messager-actions"><button class="close action" type="button">×</button></div></div> -->
		<input type="hidden"  name="userId" value="${user.userId}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;用户名</h5></th>	
					<th colspan="2">
						<input type="text" class="form-control"  name="username" <c:if test="${!empty user.username}" >disabled="disabled"</c:if>  value="${user.username}">
						<!--  <div class="popover rigth fade in">
							<div class="arrow"></div>
				          <h3 class="popover-title"><i class="icon-warning-sign"></i></h3>
				          <div class="popover-content"></div>
	          			</div>  -->
						<!--<div class="alert with-icon alert-danger" style="display: none; margin: 5px 0; font-size: 10px; height: 15px;">
				            <i class="icon-warning-sign"></i>
				            <div class="content"></div>
				        </div>
				        -->
						
					</th>
					</tr>
	 				<tr>
	 					<th style="width:70px;"><h5>&nbsp;&nbsp;姓名</h5></th>
	 					<th colspan="2">
	 						<input type="text" class="form-control" name="name"  value="${user.name}" >
	 						
				       
	 					</th>
	 					<!-- <th style="width:70px;">
	 						<div class="alert with-icon alert-danger" style="display: none; margin: 5px 0; font-size: 10px; height: 15px;">
					            <i class="icon-warning-sign"></i>
					            <div class="content"></div>
				             </div>
	 					</th> -->
	 				</tr>
	 				<tr>
	 					<th><h5>&nbsp;&nbsp;E-mail</h5></th>
	 					<th colspan="2">
	 						<input type="text" class="form-control" name="email"  value="${user.email}" >
	 						<!-- <div class="alert with-icon alert-danger" style="display: none; margin: 5px 0; font-size: 10px; height: 15px;">
				            <i class="icon-warning-sign"></i>
				            <div class="content"></div> -->
				        </div>
	 					</th>
	 				</tr>
	 				<tr>
	 					<th><h5>&nbsp;&nbsp;是否启用</h5></th>
	 					<th style="text-align: center;">
	 						<input name="isEnabled" value="1"   ${user.isEnabled&&'update'==isType?'checked':''}   ${'add'==isType?'checked="true"':'' }type="radio"><span style="font-size: 12px;">是</span>
	 					</th>
	 					<th style="text-align: center;">
	 						<input name="isEnabled" value="0"   ${!user.isEnabled&&'update'==isType?'checked':''}  type="radio"><span style="font-size: 12px;">否</span>
	 					</th>
	 				</tr>
	 				<tr>
	 					<th><h5>&nbsp;&nbsp;是否锁定</h5></th>
	 					<th style="text-align: center;">
	 						<input name="isLocked" value="1"  ${user.isLocked&&'update'==isType?'checked':''}   type="radio"><span style="font-size: 12px;">是</span>
	 					</th>
	 					<th style="text-align: center;">
	 						<input name="isLocked" value="0"  ${!user.isLocked&&'update'==isType?'checked':''}  ${'add'==isType?'checked="true"':'' } type="radio"><span style="font-size: 12px;">否</span>
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
		validate("editUserForm",{rules:{
				username:{
					required:true,
					rangelength:[2,20],
					checkUsername:true
				},
				name:{
					required:true,
					rangelength:[1,20]
				},
				email:{
					required:true,
					email:true
				}
			},
			messages:{
				username:{
					required:"用户名未填写!",
					rangelength:"用户名长度为  2-20 个字符之间",
					checkUsername:"用户名已存在!"
				},
				name:{
					required:"姓名未填写!",
					rangelength:"姓名长度为  1-20  个字符!"
				},
				email:{
					required:"邮箱未填写!",
					email:"邮件格式有误"
				}
			}},formAjax);
		
		 $.validator.addMethod("checkUsername", function(value, element) {
			   var result = false;
		        // 设置同步
		        $.ajaxSetup({
		            async: false
		        });
		        var param = {
		        	username: value
		        };
		        $.post("system_settings/user/checkUsername.jhtml", param, function(data){
		            result = data;
		        });
		        // 恢复异步
		        $.ajaxSetup({
		            async: true
		        });
		        return result;    
			}, "帐号已存在");
		
	}); 
	
	function formAjax(){
		var url ;
		if($('#isType').val() ==  'add'){
			url = 'system_settings/user/add.jhtml' + '?t=' + Math.random();
		}else{
			url = 'system_settings/user/update.jhtml' + '?t=' + Math.random();
		}
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#editUserForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				 $('#triggerModal').modal('hide');
				if (data.success) {
					if($('#isType').val() ==  'add'){
						userList.reset();
					}else{
						userList.reload();
					}
				}
				showSmReslutWindow(data.success, data.msg);
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

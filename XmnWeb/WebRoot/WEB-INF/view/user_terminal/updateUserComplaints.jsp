<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<form class="form-horizontal" role="form" id="userComplaintsForm">
		<input type="hidden"  name="id" value="${param.id}">
		<input type="hidden"  name="phoneid" value="${param.phoneid}">
		<input type="hidden"  name="status" value="1">
		<table width="100%">
			<tbody>	
				<tr>
					<th style="width:80px;"><h5>&nbsp;&nbsp;处理结果：</h5></th>	
					<th  colspan="8"><textarea class="form-control" name="result" style="height:80px;"></textarea></th>
				</tr>
				<tr height="20px"></tr>				
 				<tr>
 					<th colspan="9" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
 			</tbody>
	 	</table>
	 </form>
</body>
</html>

<script type="text/javascript">	 
		 $(document).ready(function() {
				validate("userComplaintsForm",{
					rules:{
						result:{//处理结果
								required:true
						}
					},
					messages:{
						result:{
								required:"必填!"
						}
					}},formAjax);	
			}); 			
			function formAjax(){			
					var url='user_terminal/userComplaints/update.jhtml' + '?t=' + Math.random();
					$.ajax({
						type : 'post',
						url : url,
						data : jsonFromt($('#userComplaintsForm').serializeArray()),
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							$('#prompt').hide();
							 $('#triggerModal').modal('hide');
							if (data.success) {
								userComplaintsList.reload();
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

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<form class="form-horizontal" role="form" id="editApplyForm" style=" width :580px;overflow-y:auto; ">
		<input type="hidden"   id = "status" name="status" value="2">
		<input type="hidden"   id = "ids" name="ids" value="${param.ids}">
		<input type="hidden"   id = "type" name="type" value="${param.type}">
		<table width="100%">
			<tbody>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;未通过原因:</h5></th>
					<th colspan="2">
						<textarea name="reason" rows="10" class="form-control" placeholder="未通过原因"></textarea>
					</th>	
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保存 </button>
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	 <script type="text/javascript">
		 $(document).ready(function() {
				validate("editApplyForm",{rules:{
					reason:{
							required:true,
						}
					},
					messages:{
						reason:{
							required:"请填写未通过原因！",
						}
					}},formAjax);
				
				$('.form-datetime').datetimepicker({
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					forceParse : 0,
					showMeridian : 1,
					format : 'yyyy-mm-dd hh:ii'	
				});
				
			}); 
			
			function formAjax(){
				var url = 'businessman/sellerApply/updateState.jhtml' + '?t=' + Math.random();
				$.ajax({
					type : 'post',
					url : url,
					data : jsonFromt($('#editApplyForm').serializeArray()),
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						 $('#prompt').hide();
						 $('#triggerModal').modal('hide');
						 showSmReslutWindow(data.success, data.msg);
						 if($("#type").val()=="view"){
							 var url = contextPath + '/businessman/sellerApply/init.jhtml';
								setTimeout(function(){
									location.href =url;
								}, 1000);
						 }else{
							 sellerApplyList.reload();
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

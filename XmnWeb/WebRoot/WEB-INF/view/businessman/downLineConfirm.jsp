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
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 999; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<form class="form-horizontal" role="form" id="offlineReasonSellerForm" style=" width :580px;overflow-y:auto; ">
		<input type="hidden"   id = "isonline" name="isonline" value="${param.isonline}">
		<input type="hidden"   id = "ids" name="ids" value="${param.ids}">
		<table width="100%">
			<tbody>
				<tr>									
					<th style="width:90px;"><h5>&nbsp;&nbsp;下线原因:</h5></th>
					<th colspan="2">
						<textarea id = "offlineReason" name="offlineReason" rows="10" class="form-control" placeholder="下线原因"></textarea>
					</th>
					<th><th>	
				</tr>
 				<tr>
 					<th colspan="4" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保存 </button>
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">
		 $(document).ready(function() {
				validate("offlineReasonSellerForm",{rules:{
					offlineReason:{
							required:true,
							maxlength:300,
						}
					},
					messages:{
						offlineReason:{
							required:"请填写下线原因！",
							maxlength:"下线原因不能超过300个字符",
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
				var url = 'businessman/seller/beachOnLine.jhtml' + '?t=' + Math.random();
				$.ajax({
					type : 'post',
					url : url,
					data : jsonFromt($('#offlineReasonSellerForm').serializeArray()),
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						 $('#prompt').hide();
						 $('#triggerModal').modal('hide');
						 pageDiv.reload();
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

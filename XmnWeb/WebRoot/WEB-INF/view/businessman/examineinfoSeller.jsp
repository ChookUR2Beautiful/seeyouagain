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
	<form class="form-horizontal" role="form" id="examineinfoSellerForm" style=" width :580px;overflow-y:auto; ">
		<input type="hidden"   id = "status" name="status" value="2">
		<input type="hidden"   id = "ids" name="ids" value="${param.ids}">
		<table width="100%">
			<tbody>
				<tr>									
					<th style="width:90px;"><h5>&nbsp;&nbsp;未通过原因:</h5></th>
					<th colspan="2">
						<textarea name="examineinfo" rows="10" class="form-control" placeholder="未通过原因"></textarea>
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
	 <script type="text/javascript">
		 $(document).ready(function() {
				validate("examineinfoSellerForm",{rules:{
					examineinfo:{
							required:true,
						}
					},
					messages:{
						examineinfo:{
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
				var url = 'businessman/sellerPending/beachStatus.jhtml' + '?t=' + Math.random();
				$.ajax({
					type : 'post',
					url : url,
					data : jsonFromt($('#examineinfoSellerForm').serializeArray()),
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

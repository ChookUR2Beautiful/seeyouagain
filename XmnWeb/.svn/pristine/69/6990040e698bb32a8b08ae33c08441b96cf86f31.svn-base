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
	<form class="form-horizontal" role="form" id="bankApplyForm">
		<input type="hidden"  name="appid" value="${bankApply.appid}">
		<input type="hidden"  name="handletype" value="2">
		<table width="100%">
			<tbody>			
				<%-- <tr>
					<th style="width:120px;"><h5>&nbsp;&nbsp;处理结果 :</h5></th>
					<th colspan="2" >
					    <h5>  
					    <input type="radio" id="handletype" name ="handletype" value="0" ${bankApply.handletype==0?"checked":""}  checked="checked">已处理&nbsp;&nbsp;&nbsp;
					    <input type="radio" id="handletype" name ="handletype" value="1" ${bankApply.handletype==1?"checked":""} >处理中&nbsp;&nbsp;&nbsp;
						<input type="radio" id="handletype" name ="handletype" value="2" ${bankApply.handletype==2?"checked":""} >不通过</h5></th>
				</tr> --%>
				<tr>
					<th style="width:120px;"  id="bfyy"><h5 >&nbsp;&nbsp;拒绝原因：</h5></th>	
					<th colspan="5"><textarea rows="5" class="form-control" id="handleremark" name="handleremark" >${bankApply.handleremark}</textarea></th>
				</tr>	
				<tr style="height: 10px"></tr>		
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
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
				validate("bankApplyForm",{
					rules:{
						handleremark:{//不通过原因
								required:true,
								rangelength:[1,100]
						}
					},
					messages:{
						handleremark:{
								required:"拒绝原因不能为空!",
								rangelength:"拒绝原因为1-100个字符"
						}
					}},formAjax);
			
			}); 
		 
			function formAjax(){			
					var url='businessman/bankApply/updateNot.jhtml' + '?t=' + Math.random();
					$.ajax({
						type : 'post',
						url : url,
						data : jsonFromt($('#bankApplyForm').serializeArray()),
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							$('#prompt').hide();
							 $('#triggerModal').modal('hide');
							if (data.success) {
								bankApplyList.reload();
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

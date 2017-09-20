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
<script type="text/javascript">
	$(document).ready(function() {
		$('#ensure').click(function() {
			var url='billmanagerment/refundBillPending/confirm.jhtml' + '?t=' + Math.random();
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#refundBillForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						allBillList.reload();
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		});
	});
</script>
</head>

<body>
	<form class="form-horizontal" role="form" id="refundBillForm">
		<input type="hidden"  name="orderNumber" value="${refund.bid}">
		<input type="hidden"  name="id" value="${refund.id}">
		 <input type="hidden"  name="status"> 
		<input type="hidden"  name="money" value="${refund.money}">
		<table width="100%">
			<tbody>
			
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;退款理由：</h5></th>	
					<th colspan="8"><textarea class="form-control" name="remark" >${refund.remarks}</textarea></th>
				</tr>				
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="button" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
 			</tbody>
	 	</table>
	 </form>
</body>
</html>

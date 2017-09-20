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
	<form class="form-horizontal" role="form" id="editDisputeOrderForm">
		<input type="hidden" name="id" value="${param.id}">
		<input type="hidden" name="ids" value="${param.ids}">
		<input type="hidden" name="stateType" value="${param.stateType}">
		<input type="hidden" name="ledger_state" value="${param.stateId}">
		<input type="hidden" name="busine_state" value="${param.agree}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;部门意见：</h5></th>	
					<th colspan="2"><textarea class="form-control" rows="6" name="opinion"></textarea></th>
				</tr>
				<%-- <tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;发送部门：</h5></th>	
					<th colspan="2" align="left"><input type="checkbox" name="nextDepartmentId" value='${param.stateType}'/>${param.stateType}</th>
				</tr> --%>
 				<tr>
 					<th colspan="3" style="text-align: center;">
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">
	 $(document).ready(function() {
		 /* var agree = '${param.agree}';
		 var departmentName = '${param.departmentName}';
		 console.info(departmentName);
		 if(departmentName == "结算部"){
			 if(agree == "1"){
				 $("input[name='stateId']").val(1);
				 $("input[name='nextDepartmentId']").val(3);
			 }else if(agree == "2"){
				 $("input[name='stateId']").val(2);
				 $("input[name='nextDepartmentId']").val(5);
			 }else if(agree == "3"){
				 $("input[name='stateId']").val(13);
				 $("input[name='nextDepartmentId']").val(6);
			 }
		 }
		 if(departmentName == "核算部"){
			 if(agree == "1"){
				 $("input[name='stateId']").val(4);
				 $("input[name='nextDepartmentId']").val(4);
			 }else if(agree == "2"){
				 $("input[name='stateId']").val(5);
				 $("input[name='nextDepartmentId']").val(2);
			 }else if(agree == "3"){
				 $("input[name='stateId']").val(16);
				 $("input[name='nextDepartmentId']").val(6);
			 }
		 }
		 if(departmentName == "财务部"){
			 if(agree == "1"){
				 $("input[name='stateId']").val(7);
				 $("input[name='nextDepartmentId']").val(4);
			 }else if(agree == "2"){
				 $("input[name='stateId']").val(8);
				 $("input[name='nextDepartmentId']").val(3);
			 }else if(agree == "3"){
				 $("input[name='stateId']").val(20);
				 $("input[name='nextDepartmentId']").val(6);
			 }
		 } */
		 
		validate("editDisputeOrderForm",{
			rules:{
				opinion:{
					required:true
				}
			},
			messages:{
				opinion:{
					required:"必须填写意见!"
				}
			}},formAjax);
		}); 
		
		function formAjax(){
			var url = 'president_office/disputeOrder/updateStatus.jhtml';
			var data = JSON.stringify(jsonFromt($('#editDisputeOrderForm').serializeArray()));
			//console.info(data);
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				contentType : 'application/json',
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						divideList.reset();
						withdrawList.reset();
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

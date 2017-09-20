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
/* 	$(document).ready(function() {
		$('#ensure').click(function() {
			var url='billmanagerment/refund/update.jhtml' + '?t=' + Math.random();
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
						refundBillList.reload();
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
	
	//驳回退款申请填写原因
	$("input[name='status']").change(function(){
		var name = $(this).val();	
		if(name==5){			
			$("#refundBillForm").find("#cancel").show();
			$("#refundBillForm").find("#bfyy").show();
		}else{ // if(name==0){ 
			$("#refundBillForm").find("#cancel").hide();
			$("#refundBillForm").find("#bfyy").hide();
			$("#refundBillForm").find("#cancel").val("");
		}
	});
	 */
	
</script>
</head>

<body>
	<form class="form-horizontal" role="form" id="refundBillForm">
		<input type="hidden"  name="id" value="${refund.id}">
		<input type="hidden"  name="bid" value="${refund.bid}">
		<table width="100%">
			<tbody>
			
				<tr>
					<th style="width:120px;"><h5>&nbsp;&nbsp;处理结果 :</h5></th>
					<th colspan="2" >
					    <h5>
					    <input type="radio" id="status" name ="status" value="7" ${refund.status==7?"checked":""}  checked="checked">同意退款&nbsp;&nbsp;&nbsp;
					    <input type="radio" id="status" name ="status" value="5" ${refund.status==5?"checked":""} >驳回退款申请&nbsp;&nbsp;&nbsp;
						<%-- <input type="radio" id="status" name ="status" value="6" ${refund.status==6?"checked":""} >传送给总裁办</h5></th> --%>
				</tr>
				<tr>
					<th style="width:120px;display: none;"  id="bfyy"><h5 >&nbsp;&nbsp;驳回退款原因：</h5></th>	
					<th colspan="8"><textarea style="display: none;" class="form-control" id="processing" name="processing" >${refund.processing}</textarea></th>
				</tr>	
				<tr>
					<th style="width:120px;"><h5>&nbsp;&nbsp;备注：</h5></th>	
					<th colspan="8"><textarea class="form-control" name="remarks" ></textarea></th>
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
</body>
</html>

<script type="text/javascript">	 
		 $(document).ready(function() {
				validate("refundBillForm",{
					rules:{
						processing:{//驳回退款申请原因
								required:true
						}
					},
					messages:{
						processing:{
								required:"驳回退款申请原因不能为空!"
						}
					}},formAjax);
				
				/* $("#adbpicUpload").uploadImg({
					urlId : "adbpic",
					showImg : $("#adbpic").val()
				});
				 */
				
			}); 			
			function formAjax(){			
				//$('#ensure').click(function() {
					var url='president_office/refund/update.jhtml' + '?t=' + Math.random();
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
								refundBillList.reload();
							}
							showSmReslutWindow(data.success, data.msg);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$('#prompt').hide();
							$('#triggerModal').modal('hide');
						}
					});
			//	});
				
			}
			
			//驳回退款申请填写原因
			$("input[name='status']").change(function(){
				var name = $(this).val();	
				if(name==5){			
					$("#refundBillForm").find("#processing").show();
					$("#refundBillForm").find("#bfyy").show();
				}else{ /* if(name==0){ */
					$("#refundBillForm").find("#processing").hide();
					$("#refundBillForm").find("#bfyy").hide();
					$("#refundBillForm").find("#processing").val("");
				}
			});
			
	 </script>

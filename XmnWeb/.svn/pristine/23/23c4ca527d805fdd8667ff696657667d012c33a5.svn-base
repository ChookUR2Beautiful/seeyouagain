<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<body >
	<form class="form-horizontal" role="form" id="editActivityForm" style="height: 600px; width :550px;overflow-y:auto; ">
		<input type="hidden"  name="aid" value="${activity.aid}"> 
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h4>&nbsp;&nbsp;活动名称:</h4></th>	
					<td colspan="2"><small>${activity.aname}</small></td>
				</tr>
				<tr>
					<th style="width:90px;"><h4>&nbsp;&nbsp;活动类型:</h4></th>	
					<c:if test="${activity.type==1}"><td colspan="2"><small>抽奖返利活动</small></td></c:if>
					<c:if test="${activity.type==2}"><td colspan="2"><small>消费赠送活动</small></td></c:if>
					<c:if test="${activity.type==3}"><td colspan="2"><small>教育基金活动</small></td></c:if>
					<c:if test="${activity.type==4}"><td colspan="2"><small>消费补贴活动</small></td></c:if>
				</tr>				
				<tr>	
					<th style="width:90px;"><h4>&nbsp;&nbsp;开始时间:</h4></th>	
					<td colspan="2">
						<small>	<fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd HH:mm"/></small>					
					</td>
				</tr>
				
				<tr>	
					<th style="width:90px;"><h4>&nbsp;&nbsp;结束时间:</h4></th>	
					<td colspan="2">
						<small><fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd HH:mm"/></small>
					</td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h4>&nbsp;&nbsp;活动规则:</h4></th>	
					<td colspan="2"><small>${activity.rule}</small></td>
				</tr>	
				<tr style="height:180px ">
					<th style="width:90px;"><h4>&nbsp;&nbsp;活动描述:</h4></th>	
					<td colspan="2"><small>${activity.eescription}</small></td>
				</tr>		
				
				<tr style="height:10px "></tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
						<!-- <button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button> -->
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 
	<script type="text/javascript">
	
	 
	 $(document).ready(function() {
		 
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
		 
		 startValidate();
	
	 });
	 
	/*------------------------分隔线-------------------------------------- 	 */
	function startValidate(){
			validate("editActivityForm",{
				rules:{
					aname:{
						required:true
					},
					startDate:{
						required:true
					},
					endDate:{
						required:true
					}
				},
				messages:{
					aname:{
						required:"活动名称不能为空"
					},
					startDate:{
						required:"开始时间不能为空"
					},
					endDate:{
						required:"结束时间不能为空"
					}
				}},formAjax);
		}
	
	function formAjax(){
		
		var url ;
		if($('#isType').val() ==  'add'){
			url = 'marketingManagement/activitymanagement/add.jhtml' + '?t=' + Math.random();
		}else{
			url = 'marketingManagement/activitymanagement/update.jhtml' + '?t=' + Math.random();
		}
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#editActivityForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				 $('#triggerModal').modal('hide');
				if (data.success) {
					if($('#isType').val() ==  'add'){
						activityList.reset();
					}else{
						activityList.reload();
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

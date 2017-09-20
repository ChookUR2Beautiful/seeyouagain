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
<script type="text/javascript">
	$(document).ready(function() {
		$('#ensure').click(function() {
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'user_terminal/systemInfo/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'user_terminal/systemInfo/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editSystemInfoForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							systemInfoList.reset();
						}else{
							systemInfoList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		});
		
		//监听时间显示
		$("input[name=sendDate]").click(function(){
			var obj = $("input[name=sendDate]:checked").val();
			if(obj == 1){
				$("#fdateId").show();
			}else{
				$("input[name=fdate]").val("");
				$("#fdateId").hide();
			}
		});
		
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
		
		if($("input[name=fdate]").val()){
			$("#sendDate1").attr("checked",true);
			$("#fdateId").show();
		}else{
			$("#sendDate0").attr("checked",true);
		}
	});
</script>
</head>

<body>
	<form class="form-horizontal" role="form" id="editSystemInfoForm">
		<input type="hidden"  name="userId" value="${systemInfo.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;简介:</h5></th>
					<th colspan="2"><textarea name="content" rows="10"
							class="form-control" placeholder="简介" >${systemInfo.content}</textarea>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;发送时间:</h5></th>
					<th colspan="2">
					    <h5>
					    <input type="radio" id="sendDate0" name ="sendDate" value="0" >即时发送&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" id="sendDate1" name ="sendDate" value="1" >计划发送</h5></th>
				</tr>
				<tr style = "display: none;" id ="fdateId">	
					<th style="width:90px;"><h5>&nbsp;&nbsp;发送时间:</h5></th>
					<th colspan="1">
						<input type="text" name ="fdate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="float:left" value="<fmt:formatDate value="${systemInfo.fdate}" pattern="yyyy-MM-dd HH:mm"/>">
					</th>
				</tr>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;过期时间:</h5></th>
					<th colspan="1">
						<input type="text" name ="edate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="float:left" value="<fmt:formatDate value="${systemInfo.edate}" pattern="yyyy-MM-dd HH:mm"/>">
					</th>
					<th>
					<h5 style ="color: red;"><span>说明：过期时间需要至少晚于发送时间30分钟</span></h5>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;发送对象:</h5></th>
					<th colspan="2">
					    <h5>
					    <input type="radio" name ="user" value="1" >所有用户&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name ="user" value="0" >特定用户</h5></th>
				</tr>
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
	 						<button type="button" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
</body>
</html>

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
	<form class="form-horizontal" role="form" id="editSystemMsgForm">
		<input type="hidden"  name="sid" value="${systemMsg.sid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
			
				<tr>
					<th style="width:110px;"><h5>&nbsp;&nbsp;推送时间:</h5></th>
					<th colspan="2">
					    <h5>
					    <input type="radio" id="sendDate0" name ="sendDate" value="0" >即时发送&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" id="sendDate1" name ="sendDate" value="1" >计划发送</h5></th>
				</tr>
				<tr style = "display: none;" id ="fdateId">	
					<th style="width:110px;"><h5>&nbsp;&nbsp;</h5></th>	
					<th colspan="2"><input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="tdate" value="<fmt:formatDate value="${systemMsg.tdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;过期时间：</h5></th>	
					<th colspan="2"><input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="edate" value="<fmt:formatDate value="${systemMsg.edate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;内容：</h5></th>	
					<th colspan="2"><textarea class="form-control" name="content" >${systemMsg.content}</textarea></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;是否推送：</h5></th>	
					<th colspan="2">
						<select class="form-control" name="ispush" style="width:100%;">
							<option value="">请选择</option>
							<option value="0" ${systemMsg.ispush==0?"selected":""}>不推送</option>
							<option value="1" ${systemMsg.ispush==1?"selected":""}>推送</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;发送类型：</h5></th>	
					<th colspan="2">
						<select class="form-control" name="type" style="width:100%;">
							<option value="">请选择</option>
							<option value="0" ${systemMsg.type==0?"selected":""}>用户系统通知</option>
							<option value="1" ${systemMsg.type==1?"selected":""}>向蜜客系统通知</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;后续动作类型：</h5></th>	
					<th colspan="2">
						<select class="form-control" name="actiontype" style="width:100%;">
							<option value="">请选择</option>
							<option value="1" ${systemMsg.actiontype==1?"selected":""}>打开指定网页</option>
							<option value="2" ${systemMsg.actiontype==2?"selected":""}>打开activity</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;后续动作：</h5></th>	
					<th colspan="2"><input type="text" class="form-control" name="action" value="${systemMsg.action}"></th>
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
	 <script type="text/javascript">
	$(document).ready(function() {
		validate("editSystemMsgForm",{
			rules:{
				edate:{
						required:true
				},
				content:{
					required:true
				},
				ispush:{
					required:true
				},
				type:{
					required:true
				},
				actiontype:{
					required:true
				},
				action:{
					maxlength:100
				}
			},
			messages:{
				edate:{
					required:"日期不能为空"
				},
				content:{
					required:"内容不能为空"
				},
				ispush:{
					required:"请选择一推送类型"
				},
				type:{
					required:"请选择一个发送类型"
				},
				actiontype:{
					required:"请选择一个后续动作类型"
				},
				action:{
					maxlength:"后续动作最多100字符!"
				}
			}},formAjax);
		
		$('.form_datetime').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
		});
		
		//监听时间显示
		$("input[name=sendDate]").click(function(){
			var obj = $("input[name=sendDate]:checked").val();
			if(obj == 1){
				$("#fdateId").show();
			}else{
				$("input[name=tdate]").val("");
				$("#fdateId").hide();
			}
		});
		
		if($("input[name=tdate]").val()){
			$("#sendDate1").attr("checked",true);
			$("#fdateId").show();
		}else{
			$("#sendDate0").attr("checked",true);
		}
	});
	
	function formAjax(){
		var url ;
		if($('#isType').val() ==  'add'){
			url = 'common/systemMsg/add.jhtml' + '?t=' + Math.random();
		}else{
			url = 'common/systemMsg/update.jhtml' + '?t=' + Math.random();
		}
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#editSystemMsgForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				 $('#triggerModal').modal('hide');
				if (data.success) {
					if($('#isType').val() ==  'add'){
						systemMsgList.reset();
					}else{
						systemMsgList.reload();
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

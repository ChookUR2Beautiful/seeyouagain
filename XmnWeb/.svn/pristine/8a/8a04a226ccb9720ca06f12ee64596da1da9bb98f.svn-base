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
	<form class="form-horizontal" role="form" id="editAppPushForm" style="height: 550px; width :580px;overflow-y:auto; ">
		<input type="hidden" name="tid" value="${appPush.tid}">
		<input type="hidden" id="isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;信息标题:</h5></th>	
					<td colspan="2"><input type="text" class="form-control"  name="title"  value="${appPush.title}"></td>
				</tr>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;信息内容:</h5></th>	
					<td colspan="2">
					<textarea  name="content" cols="20" rows="6" class="form-control" placeholder="信息内容">${appPush.content}</textarea>							
					</td>
				</tr>
				<%-- <tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;发送状态:</h5></th>	
					<th colspan="2">								
					
						<select class="form-control"  name="status" value="${appPush.status}" > <!-- 发送状态|0=待发送|1=已发送 -->
						    <option value="" >请选择发送状态</option>
							<option value="0" <c:if test="${appPush.status==0}">selected</c:if> >待发送</option>
							<option value="1" <c:if test="${appPush.status==1}">selected</c:if> >已发送</option>										
						</select>
					</th>
               </tr> --%>
      		   <tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;后续动作类型:</h5></th>	
					<td colspan="2">							
							<select class="form-control"  name="type"  value="${appPush.type}">
								        <option value="" >请选择后续动作类型</option>
										<option value="1" <c:if test="${appPush.type==1}">selected</c:if> >打开应用</option>
										<option value="2" <c:if test="${appPush.type==2}">selected</c:if> >网址</option>
										<option value="3" <c:if test="${appPush.type==3}">selected</c:if> >activity</option>									
								 </select>	
					</td>	
				</tr>	
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;后续动作:</h5></th>	
					<td colspan="2"><input type="text" class="form-control"  name="action"  value="${appPush.action}"></td>
				</tr>	
				<%-- <tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;创建时间:</h5></th>
					<th colspan="2">
						<input type="text" name ="sdate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="float:left" value="<fmt:formatDate value="${appPush.sdate}" pattern="yyyy-MM-dd HH:mm"/>">
					</th>
				</tr> --%>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;推送时间:</h5></th>
					<td>
					    <h5 style="float:left;">
					    <label><input type="radio" id="sendDate0" name="sendDate" value="0" ${empty appPush.tdate?"checked":""}>即时发送</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" id="sendDate1" name="sendDate" value="1" ${empty appPush.tdate?"":"checked"}>计划发送</label>&nbsp;&nbsp;&nbsp;&nbsp;
						</h5>
					</td>
					<td>
						<input type="text" name="tdate" class="form-control form-datetime" style="display: none;width:100%;float:left;" value="<fmt:formatDate value="${appPush.tdate}" pattern="yyyy-MM-dd HH:mm"/>"/>
					</td>
				</tr>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;过期时间:</h5></th><!-- 过期时间需要至少晚于发送时间30分钟 -->
					<td colspan="2">
						<input type="text" name ="edate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="float:left" value="<fmt:formatDate value="${appPush.edate}" pattern="yyyy-MM-dd HH:mm"/>" >
					</td>							
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;提醒方式:</h5></th>	
					<td colspan="2">							
							<select class="form-control"  name="remind" value="${appPush.remind}">
								        <option value="" >请选择提醒方式</option>
										<option value="0" <c:if test="${appPush.remind==0}">selected</c:if>>声音</option>
										<option value="1" <c:if test="${appPush.remind==1}">selected</c:if>>震动</option>
										<option value="2" <c:if test="${appPush.remind==2}">selected</c:if>>呼吸灯</option>									
								 </select>	
					</td>
				</tr>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;客户端类型:</h5></th>	
					<td colspan="2">							
						<select class="form-control"  name="client" value="${appPush.client}" readonly> <!-- 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端 -->
								<option value="1" <c:if test="${appPush.client==1}">selected</c:if>>寻蜜鸟客户端</option>
								<%-- <option value="2" <c:if test="${appPush.client==2}">selected</c:if>> 商户客户端</option> --%>
								<%-- <option value="3" <c:if test="${appPush.client==3}">selected</c:if>>合作商客户端</option> --%>										
						</select>	
					</td>	
				</tr>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;内容类型:</h5></th>	
					<td colspan="2">							
					 <select class="form-control"  name="contenttype" value="${appPush.contenttype}" readonly>
						<%--<option value="1" <c:if test="${appPush.contenttype==1}">selected</c:if>>提示信</option> 
							<option value="2" <c:if test="${appPush.contenttype==2}">selected</c:if>>订单提醒</option> --%>
							<option value="3" <c:if test="${appPush.contenttype==3}">selected</c:if>>营销信息</option>
							<%-- <option value="4" <c:if test="${appPush.contenttype==4}">selected</c:if>>系统消息</option> --%>
					 </select>	
					</td>	
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;发送对象:</h5></th>	
					<td colspan="2">
						<h5>
							<label><input type="radio" name ="sendObject" value="0" checked readonly>所有用户</label>
						</h5>
					</td>
				</tr>
				<%-- <tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;发送给谁:</h5></th>	
					<th colspan="3">
					<textarea  name="object" cols="20" rows="3" class="form-control" placeholder="信息内容">${appPush.object}</textarea>							
					</th>
				</tr> --%>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
 						&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
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
		 if($("input[name='tdate']").val()){
			 $("input[name='tdate']").show();
		 }
		 if ($("#isType").val() != 'add'){
			 inputToText("#editAppPushForm");
		 }
		 
		 $("input[name='sendDate']").click(function(){
			var value = this.value;
			var input = $("input[name='tdate']");
			if(this.value == 0){
				input.hide().val("");
			}else{
				input.show();
			}
		 });
	 });
	 
	
	
	function startValidate(){
			validate("editAppPushForm",{
				rules:{
					title:{
						required:true,
						rangelength:[6,20]
					},
					status:{
						required:true
					}, 
					type:{
						required:true
					},
					action:{
						required:true,
						maxlength:100
					},
					sdate:{
						required:true
					},
					edate:{
						required:true
					},
					remind:{
						required:true
					},
					client:{
						required:true
					},
					contenttype:{
						required:true
					},
					content:{
						required:true
					}
				},
				messages:{
					title:{
						required:"信息标题不能为空!",
						rangelength:"推送目标长度为  6-20  个字符之间"
					},
					status:{
						required:"请选择一个状态!"
					}, 
					type:{
						required:"请选择一个类型!"
					},
					action:{
						required:"后续动作不能缺省",
						maxlength:"内容描述最大100个字符"
					},
					sdate:{
						required:"请选择一个日期"
					},
					edate:{
						required:"请选择一个日期"
					},
					remind:{
						required:"请选择一个提醒方式!"
					},
					client:{
						required:"请选择一个客户端类型!"
					},
					contenttype:{
						required:"请选择一个内容类型!"
					},
					content:{
						required:"内容不能为空!"
					}
				}},formAjax);
		}
	
		function formAjax(){
			var data = jsonFromt($('#editAppPushForm').serializeArray());
			if(data.sendDate==0){
				if(!checkData(dateCompare(data.edate, new Date()) >= 1000*60*30, "input[name='edate']", "过期时间应晚于推送时间30分钟")){
					return false;
				}
			}
			if(data.sendDate==1){
				if(!(checkData(data.tdate, "input[name='tdate']", "计划推送必须填写推送时间") &&
				     checkData(dateCompare(data.tdate, new Date()) >= 0, "input[name='tdate']", "推送时间应大于当前时间") &&
				     checkData(dateCompare(data.edate, data.tdate) >= 1000*60*30, "input[name='edate']", "过期时间应晚于推送时间30分钟"))){
					return false;
				}
			}
			showSmConfirmWindow(function(){
				var url;
				if ($("#isType").val() == 'add') {
					url = 'businessman/appPush/add.jhtml' + '?t='
							+ Math.random();
				} else {
					url = 'businessman/appPush/update.jhtml' + '?t='
							+ Math.random();
				}
				$.ajax({
					type : 'post',
					url : url,
					data : data,
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
						if (data.success) {
							if ($('#isType').val() == 'add') {
								appPushList.reset();
							} else {
								appPushList.reload();
							}
						}
						showSmReslutWindow(data.success, data.msg);
					},
					error : function(XMLHttpRequest, textStatus,
							errorThrown) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
					}
				});
			},"请确认推送信息是否正确，提交后无法修改！");
		}
			
		</script>
</body>
</html>

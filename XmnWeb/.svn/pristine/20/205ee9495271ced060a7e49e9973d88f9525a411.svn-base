<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<form class="form-horizontal" role="form" id="editActivityForm">
		<input type="hidden" name="aid" value="${activity.aid}"> <input
			type="hidden" id="isType" value="${isType}"> <input
			type="hidden" name="activityToken" value="${activityToken}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2"><input type="text" class="form-control"
						name="aname" value="${activity.aname}"></td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<td colspan="2"><input type="text" name="startDate" 
						<c:if test="${isType=='update'}">disabled="disabled"</c:if>
						placeholder="yyyy-MM-dd hh:mm"
						value="<fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd HH:mm"/>"
						
						class="form-control form-datetime"
						
						 style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束时间:</h5></th>
					<td colspan="2"><input type="text" name="endDate" 
					<c:if test="${isType=='update'}">disabled="disabled"</c:if>
						placeholder="yyyy-MM-dd hh:mm"
						value="<fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd HH:mm"/>"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动类型：</h5></th>
					<th colspan="2"><select class="form-control" name="type">
							<option value="">请选择</option>
							<option value="1" ${activity.type==1?"selected":""}>抽奖返利活动</option>
							<option value="2" ${activity.type==2?"selected":""}>消费赠送活动</option>
							<option value="3" ${activity.type==3?"selected":""}>教育基金活动</option>
							<option value="4" ${activity.type==4?"selected":""}>消费补贴活动</option>
					</select></th>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动规则：</h5></th>
					<td colspan="2">
						<input type="text" class="form-control"	id="rule" name="rule"  value="${activity.rule}">
					</td>
				</tr>
				<tr>
					<th style="width:90px;"><h6 style="color: red;"></h6></th>
					<td><label style="float: left; color: red; font-size: 12px">提示：活动规则填写格式为50>10(整数最多可带两位小数),表示满50送10块</label></td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动描述：</h5></th>
					<th colspan="2"><textarea name="eescription" cols="20"
							rows="6" class="form-control">${activity.eescription}</textarea>
					</th>
				</tr>


				<tr style="height:20px "></tr>
				<tr>
					<th colspan="3" style="text-align: center;">
						<button type="submit" class="btn btn-success" id="ensure">
							<span class="icon-ok"></span> 保 存
						</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal">
							<span class="icon-remove"></span> 取 消
						</button>
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
			}).on('changeDate', function(ev){
				$(this).blur();
			});
		 startValidate();
	 });
	
	/*------------------------分隔线-------------------------------------- 	 */
	/*
	 * 自定义校验方法
	*/
	/* 
	$.validator.addMethod("activityRules",function(value,element,param){
		 var check = /^[1-9]\d\>[1-9]\d$/.test(value);   /^[1-9]\d{0,}\>[1-9]\d{0,}$/
		if(0){
			return false;
		}else{
			var exp[] = value.split(">");
			if(exp[0]<exp[1]){
				return false;
			}else{
				return true;
			}
		}
	},"不符合既定规则,请参看输入框下的红色字体输入！");	
	 */
	
	 $.validator.addMethod("activityRules", function(value, element) {
		 return this.optional(element) || (/^\d+(\.{0,1}\d{0,2}){0,1}\>\d+(\.{0,1}\d{0,2}){0,1}$/.test(value));    
		}, "不符合规则");
	
	function startValidate(){
			validate("editActivityForm",{
				rules:{
					aname:{
						required:true
					},
					type:{
						required:true
					},
					startDate:{
						required:true
					},
					endDate:{
						required:true
					},
					rule:{ 
						maxlength:300,
						activityRules:true
					},
					eescription:{
						maxlength:300
					}
				},
				messages:{
					aname:{
						required:"活动名称不能为空"
					},
					type:{
						required:"活动类型不能为空"
					},
					startDate:{
						required:"开始时间不能为空"
					},
					endDate:{
						required:"结束时间不能为空"
					},
					rule:{
						/* required:"活动规则不能为空", */
						maxlength:"活动规则最大长度为300字符!",
						activityRules:"不符合既定规则,请参看输入框下的红色字体输入！"
					},
					eescription:{
						maxlength:"活动描述最大长度为300字符!"
					}
				}},formAjax);
		}
	
	function formAjax(){
		var url ;
		var data = jsonFromt($('#editActivityForm').serializeArray());
		var istype = checkType(data.type,data.rule);
		if($('#isType').val() ==  'add'){
			if(!(checkData(dateCompare(data.startDate, new Date()) >= 0, "input[name='startDate']", "活动开始时间应大于当前时间") &&
				 checkData(dateCompare(data.endDate, data.startDate) >= 0, "input[name='endDate']", "活动结束时间应晚于活动开始时间"))||!istype){
				return false;
			}
			url = 'marketingManagement/activitymanagement/add.jhtml' + '?t=' + Math.random();
			showSmConfirmWindow(function() {
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
			},"确定添加吗？添加后活动时间将不能更改！");
			
		}else{
			if(istype){
				url = 'marketingManagement/activitymanagement/update.jhtml' + '?t=' + Math.random();
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
			
		}

	}
	
	
	function checkType(type,rule){
		if(type==2){
			if(!rule||$.trim(rule).length<=0){
				checkData(false,"input[name='rule']","活动类型为赠送时规则必填!");
				return false;
			}
		}
		checkData(true,"input[name='rule']","活动规则未填写!");
		return true;
		
	}

	</script>

</body>
</html>

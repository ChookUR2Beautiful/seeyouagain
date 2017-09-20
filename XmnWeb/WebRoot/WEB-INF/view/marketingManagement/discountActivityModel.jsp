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
		<input type="hidden" name="aid" value="${activity.aid}"> 
		<input	type="hidden" id="isType" value="${isType}">
		<input type="hidden" id="isRelationSellerNum" value="${isRelationSellerNum}">
		<input	type="hidden" name="activityToken" value="${activityToken}"> 
		<input type="hidden" id="endDateOld" value="${activity.endDate}"> 
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2"><input type="text" class="form-control"
					<c:if test="${isRelationSellerNum > '0'}">readonly="disabled"</c:if>
						name="aname" value="${activity.aname}"></td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始日期:</h5></th>
					<td colspan="2"><input type="text" name="startDate" 
					<c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>
						placeholder="yyyy-MM-dd"
						value="${activity.startDate}"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束日期:</h5></th>
					<td colspan="2"><input type="text" name="endDate" 
						placeholder="yyyy-MM-dd"
						value="${activity.endDate}"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<td colspan="2"><input type="text" name="startTime" 
					<c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>
						placeholder="00:00"
						value="${activity.startTimeText}"
						class="form-control form-time" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;结束时间:</h5></th>
					<td colspan="2"><input type="text" name="endTime" 
						placeholder="00:00"
						value="${activity.endTimeText}"
						class="form-control form-time" style="width:100%;float:left">
					</td>
				</tr>
			
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;折扣补贴(%)：</h5></th>
					<td colspan="2"><input type="text" class="form-control"
					<c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if>
						name="ngiveMoney" value="${activity.ngiveMoneyStr}"></td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;补贴频率：</h5></th>
					<th colspan="2"><select class="form-control" name="rate" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if>>
							<option value="1" ${activity.rate==1?"selected":""}>首单</option>
							<option value="2" ${activity.rate==2?"selected":""}>首满</option>
							<option value="3" ${activity.rate==3?"selected":""}>每次</option>
					</select></th>
				</tr>
			
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;限制条件：</h5></th>
					<th style="font-size: 1px;">
						
						<div id="rule">
							<h5>每个账号一天在同一店内，最多可享&nbsp;
							<input type="text" class="test" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> style="width:100px; height: 25px;"  name="minMoeny"  value="${activity.minMoeny}">&nbsp;次折扣补贴
							单次补贴最高&nbsp;<input type="text" class="test" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> style="width:100px;height: 25px;"  name="maxMoeny"  value="${activity.maxMoeny}">&nbsp;元 </h5>
						</div>
					</th>
				</tr>
				
				
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动描述：</h5></th>
					<th colspan="2"><textarea name="eescription" cols="20"
							rows="6" class="form-control">${activity.eescription}</textarea>
					</th>
				</tr>
				<!-- <tr>
					<th style="width:180px;"><h5>&nbsp;与其他折扣补贴活动互斥：&nbsp;</h5></th>
					<th colspan="2" style="font-size: 1px;" >
					<h5>&nbsp;&nbsp;
					<input type="radio" ${activity.repel==0?'checked':''} name="repel" value="0">不互斥&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" ${activity.repel==1?'checked':''} name="repel" value="1">互斥</h5>
					</th>
				</tr> -->
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
		 limitedDate({form:"#editActivityForm",startDateName:"startDate",endDateName:"endDate",format:'yyyy-mm-dd'});
		 startValidate();
		 $('.form-time').datetimepicker({
				autoclose : 1,
				startView:1,
				maxView:1,
				minuteStep:1,
				format : 'hh:ii'	
		});
		
	 });
/* 	
	
	
	 $.validator.addMethod("activityRules", function(value, element) {
		 return this.optional(element) || (/^\d+(\.{0,1}\d{0,2}){0,1}\>\d+(\.{0,1}\d{0,2}){0,1}$/.test(value));    
		}, "不符合规则");
	 */
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
					startTime:{
						required:true
					},
					endTime:{
						required:true
					},
					ngiveMoney:{
						number:true,
						min:0,
						max:99.999,
						required:true
					},
					minMoeny:{
						digits:true,
						number:true,
						min:0,
						required:true
					},
					maxMoeny:{
						number:true,
						min:0,
						required:true
					},repel:{ 
						required:true
					},
					rate:{
						required:true
					},
					eescription:{
						maxlength:300
					}
				},
				messages:{
					aname:{
						required:"活动名称不能为空!"
					},
					type:{
						required:"活动类型不能为空!"
					},
					startDate:{
						required:"开始日期不能为空!"
					},
					endDate:{
						required:"结束日期不能为空!"
					},
					startTime:{
						required:"开始时间不能为空!"
					},
					endTime:{
						required:"结束时间不能为空!"
					},
					ngiveMoney:{
						number:"请输入合理数字!",
						min:"请输入大于0的值!",
						max:"请输入小于100的值!",
						required:"折扣补贴未填写!"
					},
					minMoeny:{
						number:"请输入合理数字",
						digits:"请输入正整数",
						min:"请输入大于零的值!",
						required:"补贴次数未填写!"
					},
					maxMoeny:{
						number:"请输入合理数字",
						min:"请输入大于零的值!",
						required:"补贴金额未填写!"
					},
					repel:{ 
						required:"是否互斥未填写!"
					},
					rate:{
						required:"赠送频率不能为空!"
					},
					eescription:{
						maxlength:"活动描述最大长度为300字符!"
					}
				}},formAjax);
		}
	
	function formAjax(){
		var url ;
		var endDateOld = $("#endDateOld").val();
		var endDate = $("input[name='endDate']").val();
		var data = jsonFromt($('#editActivityForm').serializeArray());
		var istype = checkType(data.type,data.rule);
		if($('#isType').val() ==  'add'){
			 if(!(checkData(dateCompare(data.startDate, new Date()) >= 0, "input[name='startDate']", "活动开始日期应大于当前日期") &&
				 checkData(dateCompare(data.endDate, data.startDate) >= 0, "input[name='endDate']", "活动结束日期应晚于活动开始日期"))||!istype){
				return false;
			} 
			url = 'marketingManagement/activityManagement/discount/add.jhtml' + '?t=' + Math.random();
			showSmConfirmWindow(function() {
				$.ajax({
					type : 'post',
					url : url,
					data :$('#editActivityForm').serializeArray(),
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						$('#prompt').hide();
						 $('#triggerModal').modal('hide');
						if (data.success) {
							if($('#isType').val() ==  'add'){
								discountList.reset();
							}else{
								discountList.reload();
							}
						}
						showSmReslutWindow(data.success, data.msg);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
					}
				});
			},"确定添加吗？");
			
		}else{
			if(istype){
				if(endDateOld > endDate){
					submitDataError("input[name='endDate']","活动结束日期应晚于当前日期!");
					return false;
				} 
				url = 'marketingManagement/activityManagement/discount/update.jhtml' + '?t=' + Math.random();
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
								discountList.reset();
							}else{
								discountList.reload();
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

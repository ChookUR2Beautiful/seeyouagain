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
		<input type="hidden" id="isType" value="${isType}">
		<input type="hidden" id="isRelationSellerNum" value="${isRelationSellerNum}">
		 <input type="hidden" id="endDateOld" value="${activity.endDate}"> 
		 <input type="hidden" name="activityToken" value="${activityToken}"> 
		 <input type="hidden" name="deleteList" id="deleteList" value=""/> 
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2"><input type="text" class="form-control"
					<c:if test="${isRelationSellerNum > '0'}">
							disabled="disabled"
						</c:if>
						name="aname" value="${activity.aname}"></td>
				</tr>
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;开始日期:</h5></th>
					<td colspan="2"><input type="text" name="startDate" 
					<c:if test="${isRelationSellerNum > '0'}">
							disabled="disabled"
						</c:if>
						placeholder="yyyy-MM-dd"
						value="${activity.startDate}"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				
			
				
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;结束日期:</h5></th>
					<td colspan="2"><input type="text" name="endDate" 
						placeholder="yyyy-MM-dd"
						value="${activity.endDate}"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<td colspan="2"><input type="text" name="startTime" 
					<c:if test="${isRelationSellerNum > '0'}">
							disabled="disabled"
						</c:if>
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
					<th style="width:180px;"><h5>&nbsp;&nbsp;活动描述：</h5></th>
					<th colspan="2"><textarea name="eescription" cols="20" rows="6" class="form-control">${activity.eescription}</textarea>
					</th>
				</tr>
				<tr style="height:20px "></tr>
				<tr>
					<th colspan="3" style="text-align: center;">
						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span> 保 存 </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span> 取 消 </button>
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
				minView:2,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd'
			}).on('changeDate', function(ev){
				$(this).blur();
			});
		 
		$('.form-time').datetimepicker({
				autoclose : 1,
				startView:1,
				maxView:1,
				minuteStep:1,
				format : 'hh:ii'	
			});
		 startValidate();
	
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
					required:"开始日期不能为空"
				},
				endDate:{
					required:"结束日期不能为空"
				},
				startTime:{
					required:"开始时间不能为空"
				},
				endTime:{
					required:"结束时间不能为空"
				},
				eescription:{
					maxlength:"活动描述最大长度为300字符!"
				}
			}
		},formAjax);
	}
	
	function formAjax(){
		var url ;
		var endDateOld = $("#endDateOld").val();
		var endDate = $("input[name='endDate']").val();
		if($('#isType').val() !=  'add'){
			updateArrayIndex();
		}
		var data = jsonFromt($('#editActivityForm').serializeArray());

		if($('#isType').val() ==  'add'){
			if(!(checkData(dateCompare(data.startDate, new Date()) >= 0, "input[name='startDate']", "活动开始日期应大于当前日期") &&
				checkData(dateCompare(data.endDate, data.startDate) >= 0, "input[name='endDate']", "活动结束日期应晚于活动开始日期"))){
				return false;
			} 
			url = 'marketingManagement/activityManagement/youhuiquan/add.jhtml' + '?t=' + Math.random();
			$("#ruleTemp").remove();
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
							youhuiquanList.reload();
						}
						showSmReslutWindow(data.success, data.msg);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
					}
				});
		} else if ($('#isType').val()) {
				if(endDateOld > endDate){
					submitDataError("input[name='endDate']","当前修改日期应晚于修改前结束日期!");
					return false;
				} 
				url = 'marketingManagement/activityManagement/youhuiquan/update.jhtml'
						+ '?t=' + Math.random();
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
						   youhuiquanList.reload();
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

		function updateArrayIndex() {
			var divObj = $("#rules");
			$.each(divObj.find("div.divClass"), function(index, obj) {
				updateInputName(obj, index);
			});
		}
		function updateInputName(obj, index) {
			$.each($(obj).find("h5 .inputClass"), function(i, innerObj) {
				var innerElement = $(innerObj);
				if (innerElement.attr("item") == 'rid') {
					var str = 'tActivityRule[' + index + '].rid';
					innerElement.attr('name', str);
				}
				if (innerElement.attr("item") == 'minMoeny') {
					var str = 'tActivityRule[' + index + '].minMoeny';
					innerElement.attr('name', str);
				}
				if (innerElement.attr("item") == 'maxMoeny') {
					var str = 'tActivityRule[' + index + '].maxMoeny';
					innerElement.attr('name', str);
				}
				if (innerElement.attr("item") == 'giveMoney') {
					var str = 'tActivityRule[' + index + '].giveMoney';
					innerElement.attr('name', str);
				}
			});

		}
		
		function checkRulesRge() {
			var divObj = $("#rules");
		 	var flag = true;
			$.each(divObj.find("div.divClass"), function(index, obj) {
				if(!checkRule(obj, index)){
					flag=false;
					return;
				}
			});
			return flag;
		}
		function checkRule(obj, index) {
		  var flag=true;
		 $.each($(obj).find("h5 .inputClass"), function(i, innerObj) {
		 		var reg = new RegExp("(([0-9]+)|([0-9]+\.[0-9]{1,2}))$");
 				var innerElement = $(innerObj);
				var numberMoney=0.00;
				if (innerElement.attr("item") == 'minMoeny') {
					if(null == innerElement.val() || "" == innerElement.val()){
						submitDataError(innerElement,"最低消费不能为空!");
						flag=false;
					}else if(!reg.test(innerElement.val())){ 
						submitDataError(innerElement,"请输入0-99999.99的数字!");
						flag=false;
					}else{
						numberMoney = Number(innerElement.val());
						if(numberMoney >= 100000){
							submitDataError(innerElement,"请输入小于0-99999.99的数字!");
							flag=false;
						}else{
							submitDatasuccess(innerElement);
						}
					}
				}
			   if (innerElement.attr("item") == 'maxMoeny') {
					numberMoney = Number(innerElement.val());
					if(innerElement.val() != null && innerElement.val()!=""){
						if(numberMoney >= 100000){
							submitDataError(innerElement,"请输入0-99999.99的数字!");
							flag=false;
						}
						if(!reg.test(innerElement.val())){ 
							submitDataError(innerElement,"请输入0-99999.99的数字!");
							flag=false;
						}
					}else{
							submitDatasuccess(innerElement);
					}	
				}
				if (innerElement.attr("item") == 'giveMoney') {
					if(null == innerElement.val() || "" == innerElement.val() || 0 == innerElement.val()){
						submitDataError(innerElement,"赠送消费不能为空!");
						flag=false;
					}else if(!reg.test(innerElement.val())){ 
						submitDataError(innerElement,"请输入0-99999.99的数字!");
						flag=false;
					}else{
						numberMoney = Number(innerElement.val());
						if(numberMoney >= 100000){
							submitDataError(innerElement,"请输入0-99999.99的数字!");
							flag=false;
						}else{
							submitDatasuccess(innerElement);
						}
					}
				} 
			}); 
			return flag;
		}
		function checkType(type, rule) {
			if ($("#rules>div").length <= 0) {
				//submitDataError(innerElement,"活动规则最少要有一条!");
				alert("活动规则最少要有一条!");
				return false;
			}
			return true;
		}
		function viod(){}
		});
	</script>
</body>
</html>

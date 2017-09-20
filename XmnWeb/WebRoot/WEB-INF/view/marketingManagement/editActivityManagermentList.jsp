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
						name="aname" value="${activity.aname}"><input type="hidden" name="type" value="${type}"></td>
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
					<th style="width:180px;"><h5>&nbsp;&nbsp;赠送频率：</h5></th>
					<th colspan="2"><select class="form-control" name="rate" <c:if test="${isRelationSellerNum > '0'}">
							disabled="disabled"
						</c:if>>
							<option value="1" ${activity.rate==1?"selected":""}>首单</option>
							<option value="2" ${activity.rate==2?"selected":""}>首满</option>
							<option value="3" ${activity.rate==3?"selected":""}>每次</option>
					</select></th>
				</tr>
				
				<!-- <tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;与其他满赠活动互斥：&nbsp;&nbsp;&nbsp;&nbsp;</h5></th>
					<th colspan="2" style="font-size: 1px;" >
					<h5>&nbsp;&nbsp;
					<input type="radio" ${activity.repel==0?'checked':''} name="repel" value="0">不互斥&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" ${activity.repel==1?'checked':''} name="repel" value="1">互斥</h5>
					</th>
				</tr> -->
				
				<tr>
					<th style="width:180px;" ><h5>&nbsp;&nbsp;活动规则：</h5></th>
					<th style="font-size: 1px;">
						<div id="ruleTemp" class="divClass" style="display:none" >
							<h5><input type="hidden" item="rid" class="inputClass" name="tActivityRule[0].rid" />消费&nbsp;&nbsp;&nbsp;
							<input type="text" item="minMoeny" <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" style="width:140px; height: 26px;"  name="tActivityRule[0].minMoeny">&nbsp;--&nbsp;
							<input type="text" item="maxMoeny"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>  class="inputClass"  style="width:140px;height: 26px;" name="tActivityRule[0].maxMoeny">元，赠送&nbsp;&nbsp;&nbsp;
							<input type="text" item="giveMoney"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" style="width:140px;height: 26px;"  name="tActivityRule[0].giveMoney"><c:if test="${type=='2'}">元</c:if><c:if test="${type=='7'}">积分</c:if>
							<a href="javascript:viod();"><font color="red" size="2px;">&nbsp;&nbsp;&nbsp;删除</font></a></h5>
						</div>
						
						<div id="rules" style="float : left;">
							<c:forEach items="${activity.tActivityRule}" varStatus="status" var="rule">
								<div class="divClass">
									<h5>
									<input type="hidden" item="rid"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" name="tActivityRule[${status.index}].rid" value="${rule.rid}">消费&nbsp;&nbsp;&nbsp;
									<input type="text" item="minMoeny"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" style="width:140px; height: 26px;"  name="tActivityRule[${status.index}].minMoeny"  value="${rule.minMoeny}">&nbsp;--&nbsp;
									<input type="text" class="inputClass"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> item="maxMoeny" style="width:140px;height: 26px;" name="tActivityRule[${status.index}].maxMoeny"  value="${rule.maxMoeny}">元，赠送&nbsp;&nbsp;&nbsp;
									<input type="text" item="giveMoney"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" style="width:140px;height: 26px;"  name="tActivityRule[${status.index}].giveMoney"  value="${rule.giveMoney}"><c:if test="${type=='2'}">元</c:if><c:if test="${type=='7'}">积分</c:if>
									<a href="javascript:;" rid="${rule.rid}" ><font color="red" size="2px;">&nbsp;&nbsp;&nbsp;删除</font></a></h5>
								</div>
							</c:forEach>
						</div>
						
					</th>
				</tr>
				
				<tr>
					<th style="width:180px;"><h6 style="color: red;"></h6></th>
					<td>
						<div id="addRuleDiv">
							<h5>
								<a href="javascript:viod();" id="addRule"><font color="blue" size="2px;">添加</font></a>&nbsp;&nbsp;&nbsp;
								<font color="red" size="2px;">注：1、消费区间后框非必填，留空表示不设上限；2、消费区间不得有交叉。</font>
							</h5>
						</div>
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
		var temp = $("#ruleTemp").clone(true).removeAttr("id").show();
		//$("#rule").find("a").remove();
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
		 
		 //增加规则
		$("#addRule").on("click",{"temp":temp},function(event){
			if($("#rules>div").length > 4){
				$('#addRuleDiv').hide();
			}else{
				var t = $(event.data.temp).clone(true);
				var count =  $("#rules>div").length;//计数器  复制了几个
				  t.find(".inputClass").each(function(index) {
				  	if(index==0){
				  	  var str='tActivityRule['+count+'].rid';
      				  $(this).attr('name',str);
				  	}
				  	if(index==1){
				  	  var str='tActivityRule['+count+'].minMoeny';
      				  $(this).attr('name',str);
				  	}
				  	if(index==2){
				  	  var str='tActivityRule['+count+'].maxMoeny';
      				  $(this).attr('name',str);
				  	}
				  	if(index==3){
				  	  var str='tActivityRule['+count+'].giveMoney';
      				  $(this).attr('name',str);
				  	}
    			  });
				$("#rules").append(t);
			}
		});
	 });
	function addRow(){
	
	}
	//删除规则
	$("#rules").on("click","a",function(event){
		if($("#rules>div").length > 3){
			$('#addRuleDiv').show();
		}
		var target = event.target;
		var rid = $(target).parent().attr("rid");
		if(rid&&''!=rid){
			$("#deleteList").val($("#deleteList").val()+','+rid);
		}
		$(target).parent().parent().parent().remove();
		
	});
	
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
				startTime:{
					required:true
				},
				endTime:{
					required:true
				},
				/* repel:{
					required:true
				}, */
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
				/* repel:{
					required:"与其他满赠活动互斥不能为空"
				}, */
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
		var checkRules = checkRulesRge();
		var data = jsonFromt($('#editActivityForm').serializeArray());
		var istype = checkType(data.type,data.rule);
		if($('#isType').val() ==  'add' && istype && checkRules){
			if(!(checkData(dateCompare(data.startDate, new Date()) >= 0, "input[name='startDate']", "活动开始日期应大于当前日期") &&
				checkData(dateCompare(data.endDate, data.startDate) >= 0, "input[name='endDate']", "活动结束日期应晚于活动开始日期"))||!istype){
				return false;
			} 
			url = 'marketingManagement/activityManagement/manzeng/add.jhtml' + '?t=' + Math.random();
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
							if($('#isType').val() ==  'add'){
								manzengList.reset();
								manzengjfList.reset();
							}else{
								manzengList.reload();
								manzengjfList.reload();
							}
						}
						showSmReslutWindow(data.success, data.msg);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
					}
				});
		} else if (istype  && checkRules) {
				if(endDateOld > endDate){
					submitDataError("input[name='endDate']","当前修改日期应晚于修改前结束日期!");
					return false;
				} 
				$("#ruleTemp").remove();
				url = 'marketingManagement/activityManagement/manzeng/update.jhtml'
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
							if ($('#isType').val() == 'add') {
								manzengList.reset();
								manzengjfList.reset();
							} else {
								manzengList.reload();
								manzengjfList.reload();
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
	</script>
</body>
</html>

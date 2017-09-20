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
		 <input type="hidden" name="deleteList" id="deleteList" value=""/> 
		 <input type="hidden" name="type" value="1"/> 
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2">
					<input type="text" class="form-control"
					<c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if>
						name="aname" value="${activity.aname}"></td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始日期:</h5></th>
					<td colspan="2"><input type="text" name="startDate" 
						<c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>
						placeholder="yyyy-MM-dd hh:mm"
						value="${activity.startDate}"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束日期:</h5></th>
					<td colspan="2"><input type="text" name="endDate" 
						placeholder="yyyy-MM-dd hh:mm"
						value="${activity.endDate}"
						class="form-control form-datetime" style="width:100%;float:left">
					</td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<td colspan="2"><input type="text" name="startTime" 
					<c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>
						placeholder="00:00"
						value="${activity.startTimeText}"
						class="form-control form-time" style="width:100%;float:left">
					</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束时间:</h5></th>
					<td colspan="2"><input type="text" name="endTime" 
						placeholder="00:00"
						value="${activity.endTimeText}"
						class="form-control form-time" style="width:100%;float:left">
					</td>
				</tr>
								
								
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;设奖方式：</h5></th>
					<th colspan="2">
						<select class="form-control" name="setWay" <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>>
							<option value="1" ${activity.setWay==1?"selected":""} data-show="scaleRule,hitRatioTr" data-hide="numberRule">按比例设置</option>
							<option value="0" ${activity.setWay==0?"selected":""} data-show="numberRule" data-hide="hitRatioTr,scaleRule">按数量设置</option>
						</select>
					</th>
				</tr>
				
				
				<tr id="hitRatioTr" >
					<th style="width:10%;"><h5>&nbsp;&nbsp;中奖比例:</h5></th>
					<th colspan="2" ><input type="text" class="form-control" name="hitRatio" value="${activity.hitRatio}" <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>></th>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;奖项设置：</h5></th>
					<th style="font-size: 1px;">
						<div id="rules" >
							<div id="scaleRule" style="display:none;">
								<label style="color: red; font-size: 12px">注：各奖项占比合计须为100%</label><br>
									
									<div id="scaleRruleTemp" class="divClass">
										<input type="hidden"  name="rid" <c:if test="${activity.setWay==1}">value="${activity.tActivityRule[0].rid }" </c:if>> 
										<h5>
										奖项名称<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;" <c:if test="${activity.setWay==1}">value="${activity.tActivityRule[0].awardName}"</c:if> name="awardName"> 
										金额<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test"  style="width:100px; height: 25px;" <c:if test="${activity.setWay==1}">value="${activity.tActivityRule[0].minMoeny}"</c:if>  name="minMoeny">-
											  <input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test"  style="width:100px;height: 25px;" <c:if test="${activity.setWay==1}">value="${activity.tActivityRule[0].maxMoeny}"</c:if> name="maxMoeny">
								  元，占比<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if>class="test" style="width:100px;height: 25px;" <c:if test="${activity.setWay==1}">value="${activity.tActivityRule[0].giveMoney}"</c:if> name="giveMoney">% 
										     <a href="javascript:;"><font color="red" dependent="scaleRules"  size="4px;">删除&nbsp;</font></a>
										</h5>
									</div>
										
								<div id="scaleRules" style="float : left;">
									<c:if test="${activity.setWay==1}">
									<c:forEach items="${activity.tActivityRule}" varStatus="status" var="rule" begin="1">
										<div class="divClass">
											<input type="hidden"  name="rid" value="${rule.rid}"> 
											<h5>
												奖项名称<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;"  name="awardName" value="${rule.awardName }"> 
												金额       <input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;" value="${rule.minMoeny }"  name="minMoeny">-
												            <input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test"  style="width:100px;height: 25px;" value="${rule.maxMoeny }"  name="maxMoeny">
												元，占比<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px;height: 25px;" value="${rule.giveMoney }"  name="giveMoney">% 
												<a href="javascript:;"><font color="red" dependent="scaleRules"  size="4px;">删除&nbsp;</font></a>
											</h5>
										</div>
									</c:forEach>
									</c:if>
								</div>
								<div id="scaleRuleAdd"><button style="float : left" id="addPic" type="button" class="btn btn-danger"><i class="icon-plus"></i></button></div>
							</div>
							
							
							<div id="numberRule" >
								
								<div id="numberRruleTemp" class="divClass">
									<input type="hidden"  name="rid" <c:if test="${activity.setWay==0}">value="${activity.tActivityRule[0].rid }" </c:if> > 
									<h5>
										奖项名称<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;" <c:if test="${activity.setWay==0}"></c:if> name="awardName"> 
										金额       <input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;" <c:if test="${activity.setWay==0}"></c:if>  name="minMoeny" >-
													<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" <c:if test="${activity.setWay==0}"></c:if> style="width:100px;height: 25px;" name="maxMoeny">
										元，数量<input type="text" <c:if test="${activity.setWay==0}"></c:if> class="test" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> style="width:100px;height: 25px;" name="giveMoney">个。
										<a href="javascript:;"><font color="red" dependent="numberRules" size="4px;">删除&nbsp;</font></a>
									</h5>
								</div>
								
								<div id="numberRules" style="float : left;">
									<c:if test="${activity.setWay==0}">
										<c:forEach items="${activity.tActivityRule}" varStatus="status" var="rule">
											<div class="divClass">
												<input type="hidden"  name="rid" value="${rule.rid}"> 
												<h5>
													奖项名称<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;"  name="awardName" value="${rule.awardName }"> 
													金额       <input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px; height: 25px;" value="${rule.minMoeny }"  name="minMoeny" >-
																<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test"  style="width:100px;height: 25px;" value="${rule.maxMoeny }" name="maxMoeny">
													元，数量<input type="text" <c:if test="${isRelationSellerNum > '0'}">readonly="true"</c:if> class="test" style="width:100px;height: 25px;" value='<fmt:formatNumber maxFractionDigits="0" value="${activity.tActivityRule[0].giveMoney }" />' name="giveMoney">个 。
													<a href="javascript:;"><font color="red" dependent="numberRules" size="4px;">删除&nbsp;</font></a>
												</h5>
											</div>
										</c:forEach>
									</c:if>
								</div>
								<div id="numberRuleAdd"><button style="float : left" id="addPic" type="button" class="btn btn-danger"><i class="icon-plus"></i></button></div>
							</div>
							</div>
					</th>
				</tr>
				
				<!-- <tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;与其他抽奖返利活动互斥：&nbsp;&nbsp;&nbsp;&nbsp;</h5></th>
					<th colspan="2" style="font-size: 1px;" >
					<h5>&nbsp;&nbsp;
					<input type="radio" ${activity.repel==0?'checked':''} name="repel" value="0">不互斥&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" ${activity.repel==1?'checked':''} name="repel" value="1">互斥</h5>
					</th>
				</tr> -->
				
				
				
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
		 		 
		 var container = getContainer(["hitRatioTr","scaleRule","numberRule"]);
		 var form = $("#editActivityForm");
		 setShowOrHideElemnt(form.find("option:selected"),container);
		 form.on("change","select",{"container":container},function(event){
			setShowOrHideElemnt($(event.target).find("option:selected"),container);
		 })
		 var deleteList = $("#deleteList");
		$.each($("#rules").find("div"),function(){
			$(this).on("click","a",{"deleteList":deleteList},function(event){
				var target = $(event.target);
				var div = $("#"+target.attr("dependent"));
				
				if(div.find("div").length >= 2){
					div.next().show();
				}
				
				var rules = $(target).parent().parent().parent();
				if(!rules.attr("id")){
					rules.remove();
				}
				var rid = rules.find("input[name=rid]").val();
				if(rid&&''!=rid){
					var list = event.data.deleteList;
					list.val(list.val()+','+rid);
				}
			}); 
		});
		 
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
		 
		 limitedDate({form:"#editActivityForm",startDateName:"startDate",endDateName:"endDate",format:'yyyy-mm-dd hh:ii'});
		 startValidate();
		 
		
	 });
	 
	 
	 function getContainer(ids){
		 var container ={};
		 $.each(ids,function(i,v){
			 container[v] = $("#"+v).html();
		 });
		 return container;
	 }
	 
	 function setShowOrHideElemnt(option,container){
		var showElement = option.attr("data-show");
		var hideElement = option.attr("data-hide");
		setShowOrHide(elementShow(showElement.split(","),container),true);
		setShowOrHide(elementHide(hideElement.split(",")),false);
	 }
	 
	 function elementShow(ids,container){
		 var l = new Array();
		 $.each(ids,function(i,v){
			var e = $("#"+v);
			if(e.find("div").length<=0 && e.find("th").length<=0){
				e.html(container[v]);
			} ; 
			l.push(e);
		 }); 
		 return l;
	 }
	 
	 function elementHide(ids){
		 var l = new Array();
		 $.each(ids,function(i,v){
			 var e = $("#"+v);
			e.empty();
			l.push(e);
		}); 
		return l;
	 }
	 
	 function setShowOrHide(elements,attr){
		 $.each(elements,function(i,element){
			 if(attr){
				 if(i==0){
					 var divs = element.find("div");
					 var ruletemp = divs.first();
					 var temp =  ruletemp.clone(true).removeAttr("id").show();
					 ruletemp.find("a").remove();
					$(":input",temp).val("");
					 $(element).on("click","#"+element.attr("id")+"Add",{"temp":temp},function(event){
					 	var rules =  $(divs[1]);
					 	var len =rules.find("div").length;
					 	var t = event.data.temp.clone(true);
					  	rules.append(t);
						if(len+1>3){
							$(this).hide();
						}
					 });
				 }
				 element.show();
			 }else{
				 element.hide();
			 }
		 });
	 }

	
	 $.validator.addMethod("activityRules", function(value, element) {
		 return this.optional(element) || (/^\d+(\.{0,1}\d{0,2}){0,1}\>\d+(\.{0,1}\d{0,2}){0,1}$/.test(value));    
		}, "不符合规则");
	 
	 $.validator.addMethod("validateMoney", function(value, element) {
		 if(value.indexOf(".") != -1){  //包含小数点
			 var content = value.split(".");
		 	 if(Number(content[0]) >= 100000){
		 		 return false;
		 	 }else if(content[1].length > 4){
		 		 return false;
		 	 }
		 	 return true;
		 }else{  //不包含小数点
			 if(Number(value) >= 100000){
				 return false;
			 }
		 	 return true;
		 }
	 }, "请输入0-99999.9999的数字");
	
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
					},startTime:{
						required:true
					},
					endTime:{
						required:true
					},
					eescription:{
						maxlength:300
					},awardName:{
						required:true
					},
					giveMoney:{
						number:true,
						min:true,
						required:true
					},
					minMoeny:{
						number:true,
					 	max:99999.9999,
					 	min:0,
					 	maxlength:10,
						required:true,
						validateMoney : true
					},
					maxMoeny:{
						number:true,
						max:99999.9999,
					 	min:0,
					 	maxlength:10,
						required:true,
						validateMoney : true
					},
					hitRatio:{
						number:true,
						min:true,
						required:true
					}	
				},
				messages:{
					aname:{
						required:"活动名称不能为空"
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
					},awardName:{
						required:"奖项名称未填写!"
					},
					giveMoney:{
						number:"请输入合理数字",
						min:"请输入大于零的值!",
						required:"奖项数量未填写!"
					},
					minMoeny:{
						number:"请输入合理数字",
						min:"请输入大于零的值!",
						required:"奖项最小金额未填写!"
					},
					maxMoeny:{
						number:"请输入合理数字",
						required:"奖项最大金额未填写!"
					},
					hitRatio:{
						number:"请输入合理数字",
						min:"请输入大于零的值!",
						required:"折扣比例未填写!"
					}
				}},formAjax);
		}
	
	function serializeList(info,names) {
		var jsonObj = {};
		var array = new Array();
		$.each(info, function(index, obj) {
			if(names[obj.name]){
				if (jsonObj[obj.name]) {
					array.push(jsonObj);
					jsonObj = {};
				}
				jsonObj[this.name] = obj.value || '';
			}
			
			
		});
		if (!$.isEmptyObject(jsonObj))
			array.push(jsonObj);
		return array;
	}
	
	
	function removeNames(info,names) {
		for(var key in info){
			if(names[key]){
				info[key] = null;
			}
		}
		return info;
	}
	
	function formAjax(){
		 var endDateOld = $("#endDateOld").val();
		var endDate = $("input[name='endDate']").val();
		var minMoeny = $("input[name='minMoeny']").val();
		var maxMoeny = $("input[name='maxMoeny']").val();

		if(Number(minMoeny) > Number(maxMoeny)){
			submitDataError("input[name='maxMoeny']","应大于前面最小值！");
			return false;
		} 		
		
		var data = $('#editActivityForm').serializeArray();
		var names =  {"minMoeny":true,"maxMoeny":true,"giveMoney":true,"awardName":true,"rid":true};
		var list = serializeList(data,names);
		data =  jsonFromt(data);
		data.tactivityrule = list;
		data = removeNames(data,names);
		
		var url ;
		if($('#isType').val() ==  'add'){
			url = 'marketingManagement/activityManagement/scratchCard/add.jhtml' + '?t=' + Math.random();
			$.ajax({
				dataType:"json",  
				 contentType : 'application/json;charset=utf-8', //设置请求头信息
				type : 'post',
				url : url,
				data :JSON.stringify(data),
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							guagualeList.reset();
						}else{
							guagualeList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		}else{
				if(endDateOld > endDate){
					submitDataError("input[name='endDate']","活动结束日期应晚于当前日期!");
					return false;
				} 
				url = 'marketingManagement/activityManagement/scratchCard/update.jhtml' + '?t=' + Math.random();
				$.ajax({
					type : 'post',
					url : url,
					data : JSON.stringify(data),
					contentType : 'application/json;charset=utf-8', //设置请求头信息
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						$('#prompt').hide();
						 $('#triggerModal').modal('hide');
						if (data.success) {
							if($('#isType').val() ==  'add'){
								guagualeList.reset();
							}else{
								guagualeList.reload();
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
	</script>


</body>
</html>

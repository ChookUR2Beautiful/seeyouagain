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
		<input type="hidden" name="aid" value="${activity.aid}"/> 
		<input type="hidden" id="isType" value="${isType}"/>
		<input type="hidden" id="isRelationSellerNum" value="${isRelationSellerNum}"/>
		 <input type="hidden" id="endDateOld" value="${activity.endDate}"/> 
		 <input type="hidden" name="activityToken" value="${activityToken}"/>
		 <input type="hidden" id="listGrade" value='"${listGrade}"'/><!-- 商家选项 -->
		 <input type="hidden" name="deleteList" id="deleteList" value=""/> 
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:180px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2"><input type="text" class="form-control"
					<c:if test="${isRelationSellerNum > '0'}">
							disabled="disabled"
						</c:if>
						id="aname" name="aname" value="${activity.aname}"><input type="hidden" name="type" value="${type}"></td>
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
					<th colspan="2"><select class="form-control" id="rate" name="rate">
							<option value="1">首单</option>
							<option value="2">首满</option>
							<option value="3">每次</option>
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
					<th style="width:180px;" >
						<h5>&nbsp;&nbsp;活动规则：
						</h5>
					</th>
					<th style="font-size: 1px;" id='grade_inputhidden'>
						<select class="form-control" id="grade" name="grade" style="width:20%;display:inline-block;" >
							   <option value="1">A类商户</option>
							   <option value="3">B类商户</option>
							   <option value="5">C类商户</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:180px;"></th>
					<th style="font-size: 1px;">
						<div id="ruleTemp" class="divClass" style="display:none" >
							<h5><input type="hidden" item="rid" class="inputClass" name="tIntegralRule[0].rid" />消费阶梯&nbsp;&nbsp;&nbsp;
							<input type="text" item="startLadder" <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" style="width:140px; height: 26px;"  name="tIntegralRule[0].startLadder">&nbsp;--&nbsp;
							<input type="text" item="endLadder"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if>  class="inputClass"  style="width:140px;height: 26px;" name="tIntegralRule[0].endLadder">元&nbsp;&nbsp;&nbsp;
							<a href="javascript:;"><font color="red" size="2px;">&nbsp;&nbsp;&nbsp;删除</font></a></h5>
						</div>
						
						<div id="rules" style="float : left;">
							<c:forEach items="${activity.tIntegralRule}" varStatus="status" var="rule">
								<div class="divClass">
									<h5>
									<input type="hidden" id="sellerGrade" value="${rule.grade}"><!-- 积分活动规则商家类别 -->
									<input type="hidden" item="rid"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" name="tIntegralRule[${status.index}].rid" value="${rule.rid}">消费阶梯&nbsp;&nbsp;&nbsp;
									<input type="text" item="startLadder"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> class="inputClass" style="width:140px; height: 26px;"  name="tIntegralRule[${status.index}].startLadder"  value="${rule.startLadder}">&nbsp;--&nbsp;
									<input type="text" class="inputClass"  <c:if test="${isRelationSellerNum > '0'}">disabled="disabled"</c:if> item="endLadder" style="width:140px;height: 26px;" name="tIntegralRule[${status.index}].endLadder"  value="${rule.endLadder}">元&nbsp;&nbsp;&nbsp;
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
								<a href="javascript:;" id="addRule"><font color="blue" size="2px;">添加</font></a>&nbsp;&nbsp;&nbsp;
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
	 	//将赠送频率设置为不可编辑并且默认值问“每次”
	 	$("#rate").attr("readonly", true);
	 	//给配置状态填充值
         var _html = '';
         _html += '<option value="3" selected="selected">每次</option>';
         $("#rate").html(_html);
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
//			if($("#rules>div").length >= 50){
//				$('#addRuleDiv').hide();
//			}else{
				var t = $(event.data.temp).clone(true);
				var count =  $("#rules>div").length;//计数器  复制了几个
				  t.find(".inputClass").each(function(index) {
				  	if(index==0){
				  	  var str='tIntegralRule['+count+'].rid';
      				  $(this).attr('name',str);
				  	}
				  	if(index==1){
				  	  var str='tIntegralRule['+count+'].startLadder';
      				  $(this).attr('name',str);
				  	}
				  	if(index==2){
				  	  var str='tIntegralRule['+count+'].endLadder';
      				  $(this).attr('name',str);
				  	}
    			  });
				$("#rules").append(t);
//			}
		});
		
	 	if ($('#isType').val() == 'add') {
	 	//初始化商家选项，已经添加过的商家类别不显示
         var listGrade = ${listGrade};
         var _html = new Array();
         _html['0'] = '';
         _html['1'] = '<option value="1">A类商户</option>';
         _html['2'] = '<option value="3">B类商户</option>';
         _html['3'] = '';
         _html['4'] = ''; 
         _html['5'] = '<option value="5">C类商户</option>';

         $.each(listGrade,function(ind,obj){
                var grade = obj.grade;
                if(grade =='1'){
                    _html.splice(1,1,'');
                }else if(grade =='3'){
                    _html.splice(2,1,'');
                }else if(grade =='5'){
                    _html.splice(5,1,'');
                }

        });
        var allHtml = '<option value="1">A类商户</option><option value="3">B类商户</option><option value="5">C类商户</option>';
        var htmlObject = _html.join('');
        
        if(htmlObject == ''){
        	//禁用
        	$('#grade').attr('disabled','disabled');
        	$("#editActivityForm").unbind();
        	$("#aname").attr("readonly", true);
        	$("input[name='endDate']").attr("readonly", true);
        	$("input[name='startDate']").attr("readonly", true);
        	$("input[name='startTime']").attr("readonly", true);
        	$("input[name='endTime']").attr("readonly", true);
        	$("textarea[name='eescription']").attr("readonly", true);
        	var _html = '';
        	_html += '<h5><font color="red" size="2px;">注：已没有可选商家类别，只能修改。</font></h5>';
        	$("#addRuleDiv").html(_html);
        	$("#ensure").remove();
        }else{
           var curHtml = htmlObject;
           $('#grade').html(curHtml);
        }
      
	 	}
	 	//修改时将商家类别选项显示出来
	 	if($('#isType').val() == 'update'){
	 		    //给配商家类别选项填充值
                 var sellerGrade = $("#sellerGrade").val();//得到control共享的值
                 var _html = '';
                 if(sellerGrade == ""){
                     _html += '<option value="" selected="selected">请选择</option>';
                 }else{
                     _html += '<option value="">请选择</option>';
                 }
                 if(sellerGrade == 1){
                     _html +='<option value="1" selected="selected">A类商户</option>';
                     $("#grade").attr("disabled","disabled");
                     $("#grade").attr("readonly", true);
                 }else{
                     _html +='<option value="1">A类商户</option>';
                 }
                 if(sellerGrade == 3){
                     _html += '<option value="3" selected="selected">B类商户</option>';
                     $("#grade").attr("disabled","disabled");
                     $("#grade").attr("readonly", true);
                 }else{
                     _html += '<option value="3">B类商户</option>';
                 }
                 if(sellerGrade == 5){
                     _html += '<option value="5" selected="selected">C类商户</option>';
                     $("#grade").attr("disabled","disabled");
                     $("#grade").attr("readonly", true);
                 }else{
                     _html += '<option value="5">C类商户</option>';
                 }
                 $("#grade").html(_html);
                 $('#grade_inputhidden').append("<input type='hidden' name='grade' value="+sellerGrade+" />");
	 	}
	 });
	//删除规则
	$("#rules").on("click","a",function(event){
		//隐藏分档选项
		//if($("#rules>div").length == 1){
		//	$("#grade").css("display","none");
		//}
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
			url = 'marketingManagement/activityManagement/manzengjifen/add.jhtml' + '?t=' + Math.random();
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
				url = 'marketingManagement/activityManagement/manzengjifen/update.jhtml'
						+ '?t=' + Math.random();
				$.ajax({
					type : 'post',
					url : url,
					data : $('#editActivityForm').serializeArray(),
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
					var str = 'tIntegralRule[' + index + '].rid';
					innerElement.attr('name', str);
				}
				if (innerElement.attr("item") == 'startLadder') {
					var str = 'tIntegralRule[' + index + '].startLadder';
					innerElement.attr('name', str);
				}
				if (innerElement.attr("item") == 'endLadder') {
					var str = 'tIntegralRule[' + index + '].endLadder';
					innerElement.attr('name', str);
				}
				/* if (innerElement.attr("item") == 'giveMoney') {
					var str = 'tIntegralRule[' + index + '].giveMoney';
					innerElement.attr('name', str);
				} */
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
				if (innerElement.attr("item") == 'startLadder') {
					var startValue = innerElement.parent().find('input[item="startLadder"]').val();//消费阶梯起始金额
					var endValue = innerElement.parent().find('input[item="endLadder"]').val();//消费阶梯结束金额
					if(null == innerElement.val() || "" == innerElement.val()){
						submitDataError(innerElement,"最低消费不能为空!");
						flag=false;
					}else if(!reg.test(innerElement.val())){ 
						submitDataError(innerElement,"请输入小于0-99999.99的数字!");
						flag=false;
					}else if(Number(startValue) > Number(endValue)){
						submitDataError(innerElement,"消费起始金额不能大于结束金额");
						flag=false;
					}else {
						numberMoney = Number(innerElement.val());
						if(numberMoney >= 100000){
							submitDataError(innerElement,"请输入小于0-99999.99的数字!");
							flag=false;
						}else{
							submitDatasuccess(innerElement);
						}
					}
				}
			   if (innerElement.attr("item") == 'endLadder') {
			   		var startValue = innerElement.parent().find('input[item="startLadder"]').val();
					var endValue = innerElement.parent().find('input[item="endLadder"]').val();
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
					}else if(Number(startValue) > Number(endValue)){
						submitDataError(innerElement,"消费结束金额不能小于起始金额");
						flag=false;
					}else {
							submitDatasuccess(innerElement);
					}
				}
				
				
				/* if (innerElement.attr("item") == 'giveMoney') {
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
				}  */
			}); 
			return flag;
		}
		function checkType(type, rule) {
			if ($("#rules>div").length <= 0) {
				//submitDataError(innerElement,"活动规则最少要有一条!");
				alert("活动规则最少要添加一条!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>

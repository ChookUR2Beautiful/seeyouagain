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
	<form class="form-horizontal" role="form" id="editActivityForm">
		<input type="hidden"  name="aid" value="${activity.aid}"> 
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:20%"><h4>&nbsp;&nbsp;活动名称:</h4></th>	
					<td><small>${activity.aname}</small></td>
				</tr>
				<tr>	
					<th style="width:20%"><h4>&nbsp;&nbsp;开始日期:</h4></th>	
					<td>
						<small>	
							<%-- <fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
							${activity.startDate}
						</small>					
					</td>
				</tr>
				
				<tr>	
					<th style="width:20%"><h4>&nbsp;&nbsp;结束日期:</h4></th>	
					<td>
						<small>
							<%-- <fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
							${activity.endDate}
						</small>
					</td>
				</tr>
				<tr>	
					<th style="width:20%"><h4>&nbsp;&nbsp;开始时间:</h4></th>	
					<td>
						<small>	
							<%-- <fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
							${(!empty activity.startTimeText)?activity.startTimeText:"-"}
						</small>					
					</td>
				</tr>
				
				<tr>	
					<th style="width:20%"><h4>&nbsp;&nbsp;结束时间:</h4></th>	
					<td>
						<small>
							<%-- <fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
							${(!empty activity.endTimeText)?activity.endTimeText:"-"}
						</small>
					</td>
				</tr>
				<c:if test="${activityType == 'manzeng'}">
				<tr>
					<th style="width:20%"><h4>&nbsp;&nbsp;赠送频率:</h4></th>	
					<td><small>${activity.rateName}</small></td>
				</tr>
				</c:if>
				
				<%-- <tr>
					<th nowrap="nowrap" style="width:20%"><h4>&nbsp;&nbsp;与其他折扣补贴活动互斥：</h4></th>
					<td>
						<c:if test="${activity.repel==0}">不互斥</c:if>
						<c:if test="${activity.repel==1}">互斥</c:if>
					</td>
				</tr> --%>
				<c:if test="${(activityType != 'youhuiquan')&&(type != '7')}">
				<tr>
					<th style="width:20%"><h4>&nbsp;&nbsp;活动规则:</h4></th>	
					<td>
						<c:if test="${empty activity.tActivityRule}">
							未设置
						</c:if>
						<c:if test="${!empty activity.tActivityRule}">
							<ol>
								<c:forEach items="${activity.tActivityRule}" var="activityRule">
									<li>
										<c:if test="${activityType == 'manzeng'}">
										消费金额在【${activityRule.minMoeny }】元到【${activityRule.maxMoeny }】元之间，赠送【${activityRule.giveMoney }】<c:if test="${type=='2'}">元</c:if><c:if test="${type=='7'}">积分</c:if> 
										</c:if>
										<c:if test="${activityType== 'guaguale'}">
											${activityRule.awardName}，金额从【${activityRule.minMoeny }】元到【${activityRule.maxMoeny }】元，<c:if test="${activity.setWay==0}">数量<fmt:formatNumber maxFractionDigits="0" value="${activityRule.giveMoney }" />个</c:if><c:if test="${activity.setWay==1}">占比${activityRule.giveMoney }%</c:if>。
										</c:if>
									</li>
								</c:forEach>
							</ol>
						</c:if>
					</td>
				</tr>	
				</c:if>
				<!-- 积分活动规则显示 -->
				<c:if test="${type == '7'}">
					<tr>
						<th style="width:20%"><h4>&nbsp;&nbsp;活动规则:</h4></th>
						<td>
							<c:if test="${empty activity.tIntegralRule}">未设置</c:if>
							<c:if test="${!empty activity.tIntegralRule}">
							  <ol>
							  	<c:if test="${activity.grade=='1'}"><div style="height: 20px;color: blue;">A类商户</div>
							  	</c:if>
							  	<c:if test="${activity.grade=='2'}"><div style="height: 20px;color: blue;">B类商户</div>
							  	</c:if>
							  	<c:if test="${activity.grade=='5'}"><div style="height: 20px;color: blue;">C类商户</div>
							  	</c:if>
							  	<c:forEach items="${activity.tIntegralRule}" var="tintegralRule">
							  		<div style="height: 20px;color: red;">
							  		<li>
							  		  消费${tintegralRule.startLadder}至${tintegralRule.endLadder}元
							  		</li>
							  		</div>
							  	</c:forEach>
							  </ol>
							</c:if>
						</td>
					</tr>
				</c:if>
				
				
				
				<tr>
					<th style="width:20%"><h4>&nbsp;&nbsp;活动描述:</h4></th>	
					<td><small>${activity.eescription}</small></td>
				</tr>		
				
 				<tr>
 					<th colspan="2" style="text-align: center;"> 
 						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>关闭 </button>
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

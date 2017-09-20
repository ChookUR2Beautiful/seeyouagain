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
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2">${activity.aname}</td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动日期:</h5></th>
					<td colspan="2">${activity.startDate}&nbsp;&nbsp;-&nbsp;&nbsp;${activity.endDate}</td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动时间:</h5></th>
					<td colspan="2">${(!empty activity.startTimeText)?activity.startTimeText:"-"}&nbsp;&nbsp;-&nbsp;&nbsp;${(!empty activity.endTimeText)?activity.endTimeText:"-"}</td>
				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;活动规则：</h5></th>
					<td colspan="2">${activity.rule}</td>
   				</tr>	
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;活动描述：</h5></th>
					<td colspan="2">${activity.eescription}</td>
   				</tr>
   				<tr>
   				<th style="width:98px;"><h5>&nbsp;&nbsp;活动类型：</h5></th>
				<td colspan="2">${activity.type}
					<c:choose>  
				       <c:when test="${activity.type==1}">  
						 抽奖类活动 
				       </c:when>  
					   <c:when test="${activity.type==2}">  
						 满赠类活动
				       </c:when>  
				       <c:when test="${activity.type==3}">  
						 未知活动 
				       </c:when>  
				       <c:when test="${activity.type==4}">  
						 未知活动  
				       </c:when>  
				       <c:when test="${activity.type==5}">  
						平台补贴类 
				       </c:when>  
				       <c:when test="${activity.type==6}">  
						 优惠券赠送活动 
				       </c:when>  
				       <c:when test="${activity.type==7}">  
						 赠送积分类  
				       </c:when>  
				       <c:otherwise>  
						其他活动
				       </c:otherwise>  
					</c:choose> 
				</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;活动简称：</h5></th>
					<td colspan="2">${activity.sname}</td>
   				</tr>			
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;赠送频率：</h5></th>
					<td colspan="2">${activity.rate}</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;是否可同时参与其他活动：</h5></th>
					<td colspan="2">
					<c:choose>  
				       <c:when test="${activity.repel==0}">  
						 可以 
				       </c:when>  
					   <c:when test="${activity.repel==1}">  
						 不可以 
				       </c:when>  
				       <c:otherwise>  
						-
				       </c:otherwise>  
					</c:choose> 
					</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;每天赠送的最大次数：</h5></th>
					<td colspan="2">${activity.rateNum}</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;活动说明：</h5></th>
					<td colspan="2">${activity.explains}</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;活动图片：</h5></th>
					<td colspan="2">
						<c:if test="${!empty activity.imgUrl}">
							<img alt="" src="">
						</c:if>
					</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;活动入口：</h5></th>
					<td colspan="2">${activity.entrance}</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;预计展示次数：</h5></th>
					<td colspan="2">${activity.showTimes}</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;申请条件：</h5></th>
					<td colspan="2">${activity.conditions}</td>
   				</tr>
   				<tr>
   					<th style="width:98px;"><h5>&nbsp;&nbsp;报名时间：</h5></th>
					<td colspan="2">${activity.bstartDate}&nbsp;&nbsp;-&nbsp;&nbsp;${activity.bendDate}</td>
   				</tr>
				<tr>
					<th style="width:98px;"><h5>&nbsp;&nbsp;折扣补贴(%)：</h5></th>
					<td colspan="2">${activity.ngiveMoneyStr}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;补贴频率：</h5></th>
					<td colspan="2">${activity.rateName}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动描述：</h5></th>
					<td colspan="2">${activity.eescription}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动创建日期：</h5></th>
					<td colspan="2">${activity.createDate}</td>
				</tr>
				<tr>
					<th colspan="3" style="text-align: center;">
						<button data-dismiss="modal" class="btn btn-default" ><span class="icon-remove"></span>关闭</button>
					</th>
				</tr>
			</tbody>
		</table>
</body>
</html>

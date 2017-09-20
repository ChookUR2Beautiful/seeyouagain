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
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
</head>
<body style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<!-- loading -->
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main"><div class="example"><div class="panel panel-primary"><div class="panel-heading">基本信息</div><div class="panel-body">
	<form class="form-horizontal" role="form" id="editAppPushForm">
        <input type="hidden" id="aid" name="aid" value="${activity.aid}">
        <input type="hidden" id="doType" name="doType" value="${activity.doType}">
        <input type="hidden" id="activityType" name="activityType" value="${activity.type}">
        <input type="hidden" id="repel" name="repel" value="${activity.repel}">
		<table width="100%" >
			<tbody>
				<tr>
					<td class="sellerTitleCss"><h5>&nbsp;&nbsp;活动名称:</h5></td>	
					<td class="sellerContentCss" >
					<div>${activity.aname}</div>
					</td>
				</tr>
				<tr>
					<td><h5>&nbsp;&nbsp;开始时间:</h5></td>	
					<td>
					    <div>${activity.startDate}</div>
					    <%-- <div><fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd"/></div> --%>
					</td>
					</tr>
					<tr>
					<td><h5>&nbsp;&nbsp;结束时间:</h5></td>	
					<td>
						<div>
							<%-- <fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd"/> --%>
							${activity.endDate}
						</div>
					</td>
				</tr>
				
				<!-- 类型为4的时候代表的是佣金补贴活动，就没有赠送规则的属性 -->
				<c:if test="${activity.type != '4'&& activity.type !='6'}">
					<tr>
					    <td class="sellerTitleCss"><h5>&nbsp;&nbsp;赠送规则:</h5></td>	
						<td class="sellerContentCss">
						<div>
							<c:if test="${empty activity.tActivityRule}">
								未设置
							</c:if>
							<c:if test="${!empty activity.tActivityRule}">
								<ol>
									<c:forEach items="${activity.tActivityRule}" var="activityRule">
										<li>
											<c:if test="${activity.type == '5'}">
											折扣补贴：	${activityRule.giveMoney }
											</c:if>
											<c:if test="${activity.type != '5'}">
											消费金额在${activityRule.minMoeny }元到${activityRule.maxMoeny }元之间，赠送${activityRule.giveMoney }元。
											</c:if>
										</li>
									</c:forEach>
								</ol>
							</c:if>
						</div>
						</td>			
					</tr>
				</c:if>
				
				<c:if test="${activity.type != '1'&& activity.type != '6'}">
				<tr>
				    <td class="sellerTitleCss"><h5>&nbsp;&nbsp;赠送频率:</h5></td>	
					<td class="sellerContentCss">
					<div>${activity.rateName}</div>
					</td>			
				</tr>
				</c:if>
				<tr>
				    <td class="sellerTitleCss"><h5>&nbsp;&nbsp;活动描述:</h5></td>	
					<td class="sellerContentCss">
					<div>${activity.eescription}</div>
					</td>			
				</tr>
			<%-- 	<tr>
				    <td class="sellerTitleCss"><h5>&nbsp;&nbsp;是否与其他活动互斥:</h5></td>	
					<td class="sellerContentCss">
					<div>${activity.repelName}</div>
					</td>			
				</tr> --%>
				<tr>
					<td class="sendObjectTr" ><h5>&nbsp;&nbsp;选择参加活动商家:</h5></td>
					<td class="sendObjectTr"  colspan="3">
						<textarea id="sellerids" class="form-control" rows="5" cols="" name="sellerids"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td class="sendObjectTr"  colspan="3">
						 <p><div id="tishi" style="overflow-y:auto;height:60;width: 80%"></div></p>
					</td>
				</tr>
	 			</tbody>
	 		</table>
	 		<div style="height:20px"></div>
			<div align="center">
			    <button class="btn btn-danger" type="submit" id="ensure" ><i class="icon-save"></i>&nbsp;保存</button>&nbsp;&nbsp;
				<button class="btn btn-warning" type="button" onclick="window.history.back();"><i class="icon-reply"></i>&nbsp;取消</button>
			</div>
	</form>			
	</div></div></div></div>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/ux/js/searchChosen.js"></script>
<script src="<%=path%>/js/marketingmanagement/activityManagerSeller.js"></script>
</body>
</html>

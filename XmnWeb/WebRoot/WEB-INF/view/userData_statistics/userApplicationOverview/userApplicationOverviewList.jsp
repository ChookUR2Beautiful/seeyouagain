<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>应用概况</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info" >
			<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">应用概况</caption>
			<thead>
	 			<tr> 
				 <th style="width:20%;">日期</th> 
				 <th style="width:15%;">启动次数</th> 
				 <th style="width:15%;">登录会员</th>  
				 <th style="width:15%;">新增会员</th>  <!-- 
				 <th style="width:100px;">平均使用时长</th> --> 
				 <th style="width:20%;">累积注册会员</th> 
				 <th style="width:15%;">交易金额（元）</th> 
	 			</tr> 
	 		</thead> 
	 		<tbody>
	 		
	 		<c:if test="${!empty list}">
		 		<c:forEach var="l" items="${ list}">
						<tr>
							<td>${l.date }</td>
							<td><c:if test="${empty l.start_no}">0</c:if><c:if test="${!empty l.start_no}">${l.start_no}</c:if></td>
							<td><c:if test="${empty l.login_user}">0</c:if><c:if test="${!empty l.login_user}">${l.login_user}</c:if></td>
							<td><c:if test="${empty l.add_user}">0</c:if><c:if test="${!empty l.add_user}">${l.add_user}</c:if></td>
							<td><c:if test="${empty l.total_user}">0</c:if><c:if test="${!empty l.total_user}">${l.total_user}</c:if></td>
							<td><c:if test="${empty l.tradingTotal}">0</c:if><c:if test="${!empty l.tradingTotal}">${l.tradingTotal}</c:if></td>
						</tr>
					</c:forEach>
				</c:if>
					
	 			<c:if test="${empty list}">
						<tr>
							<td colspan="6">暂无数据</td>
						</tr>
				</c:if>
			</tbody>
			</table>	
		</div>
	</div>
	<script src="<%=path%>/resources/zui/lib/jquery/jquery.js"></script>
	<script src="<%=path%>/resources/web/js/util.js"></script>
	<script type="text/javascript">inserTitle(' > 用户端数据统计  > 应用概况','transactionFlow',true);</script>
  </body>
</html>

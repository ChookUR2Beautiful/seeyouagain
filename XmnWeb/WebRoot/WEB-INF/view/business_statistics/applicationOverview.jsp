<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				 <th style="width:100px;">日期</th> 
				 <th style="width:130px;">新增商家</th> 
				 <th style="width:130px;">累积注册商家</th>  
				 <th style="width:130px;">交易总额(元)</th>  
				<!--  <th style="width:100px;">客户端类型</th> 
				 <th style="width:100px;">信息标题</th> 
				 <th style="width:100px;">信息内容</th> 
				 <th style="width:80px;">发送状态</th> 
				 <th style="width:100px;">后续动作类型</th> 
				 <th style="width:100px;">后续动作</th> 
				 <th style="width:80px;">提醒方式</th> 
				 <th style="width:100px;">内容类型</th> 
				 <th style="width:140px;">创建时间</th> 
				 <th style="width:140px;">过期时间</th>  -->
				
	 			</tr> 
	 		</thead> 
	 		<tbody>
	 			<c:if test="${!empty appList}">
				<c:forEach  var="list" items="${appList}">
					<tr>
					<td>${list.sdate}</td>
					<td>${list.increasedJointNum}</td>
					<td>${list.totalJointNum}</td> 
					<td>${list.amount}</td> 
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty appList}">
					<tr ><td colspan="4">暂无数据</td></tr>
				</c:if>
			</tbody>
			</table>	
		</div>
	</div>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript">inserTitle(' > 商家端数据统计  > 应用概况','transactionFlow',true);</script>
  </body>
</html>

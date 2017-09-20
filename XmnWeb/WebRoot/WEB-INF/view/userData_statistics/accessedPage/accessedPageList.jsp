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
    <title>客户端应用概况</title>
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
			<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">应用概括</caption>
			<thead>
	 			<tr> 
				 <th style="width:100px;"></th> 
				 <th style="width:130px;">启动次数</th> 
				<!--  <th style="width:130px;">启用用户</th>   -->
				 <th style="width:130px;">新增会员</th>  
				 <!-- <th style="width:100px;">平均使用时长</th> --> 
				 <th style="width:100px;">累积注册会员</th> 
				 <th style="width:100px;">交易金额（元）</th> 
	 			</tr> 
	 		</thead> 
	 		<tbody>
				<tr>
					<td>今日</td>
					<td>9524</td>
					<!-- <td>3410</td> --> 
					<td>3410</td>
					<!-- <td>00:04:54</td> -->
					<td>543,123</td>
					<td>38,987,322.34</td> 
				</tr>
				<tr>
					<td>昨日</td>
					<td>9524</td>
					<!-- <td>3410</td>  -->
					<td>3410</td>
					<!-- <td>00:04:54</td> -->
					<td>543,123</td>
					<td>38,987,322.34</td> 
				</tr>
				<tr>
					<td>前日</td>
					<td>9524</td>
					<!-- <td>3410</td>  -->
					<td>3410</td>
					<!-- <td>00:04:54</td> -->
					<td>543,123</td>
					<td>38,987,322.34</td> 
				</tr>
			</tbody>
			</table>	
		</div>
	</div>
	<script type="text/javascript">inserTitle(' > 用户端数据统计  > <span><a href="#" target="right">应用概况</a>','transactionFlow',true);</script>
  </body>
</html>

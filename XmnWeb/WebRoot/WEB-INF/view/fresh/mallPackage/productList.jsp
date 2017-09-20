<%@ page language="java" import="java.util.*,java.net.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>V客学堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>



	<div class="tab-content">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>关联产品名称</th>
					<th>规格</th>
					<th>数量</th>
					<th>产品售价</th>
					<th>产品库存</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product"  >
					<tr>
					<td>${product.productName}</td>
					<td>${product.pvValue}</td>
					<td>${product.number}</td>
					<td>${product.productInfo.cash}元+${product.productInfo.integral}鸟币</td>
					<td>${product.saleGroup!=null?product.saleGroup.stock:product.productInfo.store}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<style type="text/css">
#salePropertyFormId td{
	text-align: center;
}
#salePropertyFormId th{
	text-align: center;
}
	
</style>
</head>

<body>
	<form class="form-horizontal" role="form">
		<table width="100%" id="salePropertyFormId">
			<tbody>
				<c:choose>
					<c:when test="${!empty salePropertyList and !empty saleGroupList}">
						<tr>
							<c:forEach items="${salePropertyList}" var="saleProperty">
								<th style="width:17%;"><h4>${saleProperty.property}</h4></th>
							</c:forEach>
							<th style="width:17%;"><h4>加价金额（元）</h4></th>
							<th style="width:17%;"><h4>库存</h4></th>
						</tr>
						<c:forEach items="${saleGroupList}" var="saleGroup">
							<tr>
								<c:forEach items="${saleGroup.pvValues}" var="pvValue">
									<td><small>${pvValue}</small></td>
								</c:forEach>
								<td><small>${saleGroup.amount}</small></td>
								<td><small>${saleGroup.stock}</small></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<th><h4 style="font-style: italic;">该商品无销售属性。</h4></th>
					</c:otherwise>				
				</c:choose>
			</tbody>
		</table>
		<div style="margin: 20px auto 0px; text-align: center;">
			<button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
		</div>
	 </form>
</body>
</html>

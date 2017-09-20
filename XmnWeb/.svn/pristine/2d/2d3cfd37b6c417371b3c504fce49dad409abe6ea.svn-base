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
</head>

<body>
	<form class="form-horizontal" role="form" id="billDetailForm">
			<table border=1px;width=100%>
				<tr>
					<td style="vertical-align: top;">
						<table>
							<tbody>
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返利支付金额：</th>	
									<td style="width:400px">
										<span><small>${bill.profit}<c:if test="${not empty bill.profit}">元</c:if></small></span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;需支付金额：</th>	
									<td style="width:400px">
										<span><small>${bill.payment}<c:if test="${not empty bill.payment}">元</c:if></small></span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赠送支付金额：</th>	
									<td style="width:400px">
										<span><small>${bill.give_money}<c:if test="${not empty bill.give_money}">元</c:if></small></span>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
		 	</table>
	 </form>
</body>
</html>

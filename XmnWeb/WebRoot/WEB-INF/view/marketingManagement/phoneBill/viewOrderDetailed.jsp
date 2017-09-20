<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<form class="form-horizontal" role="form" id="viewOrderForm">
		<input type="hidden" name="sellerToken" value="${sellerAccountToken}">
		<input type="hidden"  name="userId" value="${sellerAccount.aid}">
		<input type="hidden"   id = "isType" value="${isType}">
			<table border=1px;width=100%>
				<tr>
					<td style="width:50%;vertical-align: top;">
						<table>
							<tbody>
							    <tr style="font-size: 15px !important;">
									<th style="width:150px">订单信息</th>	
								</tr>
								<tr style="font-size: 15px !important;">
									<th style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品名称：</th>	
									<td style="width:400px">
										<span>iPhone6</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品数量：</th>	
									<td>
										<span>${order.pnum}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品颜色：</th>	
									<td>
										<span>${order.pcoler}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品型号：</th>	
									<td>
										<span>${order.pmodel}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;免费选号：</th>	
									<td>
										<span>${order.checkPhone}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收货人：</th>	
									<td>
										<span>${order.getUser}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证：</th>	
									<td>
										<span>${order.getCardId}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：</th>	
									<td>
										<span>${order.getUserPhone}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收货地址：</th>	
									<td>
										<span>${order.address}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付定金：</th>	
									<td>
										<span>${order.depPrice}</span>
									</td>
								</tr>
				 			</tbody>
						</table>
					</td>
					
					<td style="width:50%;vertical-align: top">
						<table>
							<tbody>
								<tr style="font-size: 15px !important;padding-top: 0px">
									<th style="width:150px">物流信息</th>	
								</tr>
								<c:forEach items="${logistics.progresses}"  var="info">
									<tr style="font-size: 15px !important;">
										<td style="width:500px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${info.dealDescription}</td>
									</tr>
									<tr style="font-size: 15px !important;">
										<td style="width:500px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${info.dealTime}</td>
									</tr>
								</c:forEach>
				 			</tbody>
						</table>
					</td>
				</tr>
		 	</table>
	 </form>
<script type="text/javascript">
$(document).ready(function() {
}); 

</script>
</body>

</html>

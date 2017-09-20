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
	<form class="form-horizontal" role="form" id="viewWalletForm">
			<table border=1px;width=100%>
				<tr>
					<td style="vertical-align: top;">
						<table>
							<tbody>
							    <!-- <tr style="font-size: 15px !important;">
									<th style="width:200px">订单信息</th>	
								</tr> -->
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家编号：</th>	
									<td style="width:400px">
										<span>${wallet.uId}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家名称：</th>	
									<td style="width:400px">
										<span>${wallet.sellerName}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请日期：</th>	
									<td style="width:400px">
										<span>${wallet.applyDate}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生效日期：</th>	
									<td style="width:400px">
										<span>${wallet.effectDate}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注销日期：</th>	
									<td style="width:400px">
										<span>${wallet.cancelDate}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：</th>	
									<td style="width:400px">
										<span>
										<c:if test="${wallet.status==1}">正常</c:if>
										<c:if test="${wallet.status==2}">锁定</c:if>
										<c:if test="${wallet.status==3}">注销</c:if>
										</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;钱包余额：</th>	
									<td style="width:400px">
										<span>${wallet.amount}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分账余额：</th>	
									<td style="width:400px">
										<span>${wallet.balance}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;佣金余额：</th>	
									<td style="width:400px">
										<span>${wallet.commision}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赠送余额：</th>	
									<td style="width:400px">
										<span>${wallet.zbalance}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业收入余额：</th>	
									<td style="width:400px">
										<span>${wallet.sellerAmount}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">	
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每月消费限额：</th>	
									<td style="width:400px">
										<span>${wallet.quota}</span>
									</td>
								</tr>
								
								<tr style="font-size: 15px !important;">
									<th style="width:200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积分余额：</th>	
									<td style="width:400px">
										<span>${wallet.integral}</span>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
		 	</table>
	 </form>
</body>

</html>

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
	<form class="form-horizontal" role="form" id="couponOrderForm">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%" >
			<tbody>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单编号:</h4></th>
					<td style="width:33%;"><small>${couponOrder.orderSn}</small></td>
					<th style="width:17%;"><h4>&nbsp;支付方式:</h4></th>
					<td style="width:33%;"><small>${couponOrder.paymentTypeStr}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户ID:</h4></th>
					<td style="width:33%;"><small>${couponOrder.uid}</small></td>
					<th style="width:17%;"><h4>&nbsp;位数:</h4></th>
					<td style="width:33%;"><small>${couponOrder.cidNum}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户昵称:</h4></th>
					<td style="width:33%;"><small>${couponOrder.nname}</small></td>
					<th style="width:17%;"><h4>&nbsp;订单金额:</h4></th>
					<td style="width:35%;">${couponOrder.totalAmount}</td>
				</tr>
				 <tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户手机号:</h4></th>
					<td style="width:33%;"><small>${couponOrder.phoneid}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;减免:</h4></th>
					<td style="width:35%;">${couponOrder.cuser}</td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;商家编号:</h4></th>
					<td style="width:33%;"><small>${couponOrder.sellerid}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;实际支付金额:</h4></th>
					<td style="width:33%;"><small>${couponOrder.realAmount}</td>
				</tr>
				 <tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;商家名称:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${couponOrder.sellername}</small></td>					
					<th style="width:17%;"><h4>&nbsp;&nbsp;购买来源:</h4></th>
					<td style="width:35%;"><small>${couponOrder.buySourceStr}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单状态:</h4></th>
					<td style="width:33%;"><small>${couponOrder.statusStr}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;赠送预售抵用券:</h4></th>
					<td style="width:35%;"><small>${couponOrder.retrunCouponAmount}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;支付流水号:</h4></th>
					<td style="width:33%;"><small>${couponOrder.thirdSerial}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;下单时间:</h4></th>
					<td style="width:35%;"><small>${couponOrder.createTimeStr}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;支付时间:</h4></th>
					<td style="width:33%;"><small>${couponOrder.modifyTimeStr}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;</h4></th>
					<td style="width:35%;"></td>
				</tr>
				</tbody>
				</table>
				<div style="min-height: 30px; max-height: 150px; overflow: scroll;">
				<table width="100%" >
				<tbody style="min-height: 30px; overflow: hidden; max-height: 30px;">
				<tr style="border-bottom-style:solid;border-bottom-width: 1px">
					<th colspan="5" style="text-align: center;"><h3>粉丝券信息</h3></th>
				</tr>
				<tr>
				<th style="width:20%;text-align: center;"><h4>粉丝券编号</h4></th>
				<th style="width:20%;text-align: center;"><h4>粉丝券状态</h4></th>
				<th style="width:20%;text-align: center;"><h4>核销时间</h4></th>
				<th style="width:20%;text-align: center;"><h4>是否分账</h4></th>
				</tr>
				<c:if test="${!empty couponOrderDetail.cid}">
				<tr style="background-color:#DCEEF8;text-align:center;width:20%;">
					<td><small>${couponOrderDetail.cid}</small></td>
					<td><small>${couponOrderDetail.userStatusStr}</small></td>
					<td><small>${couponOrderDetail.userTimeStr}</small></td> 
					<td><small>${couponOrderDetail.accountStatus}</small></td>
				</tr>
				</c:if>
				<c:if test="${empty couponOrderDetail.cid}">
				<tr style="background-color:#DCEEF8;text-align:center;width:20%;">
					<td colspan="5"><small>暂无粉丝券信息</small></td>
				</tr>
				</c:if>
 			</tbody>
	 	</table>
	 	</div>
	 	<div style="margin: 20px auto 0px; text-align: center;">
	 	 <button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
	 	</div>
	 </form>
</body>
</html>

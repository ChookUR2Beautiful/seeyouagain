<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<table width="100%" >
			<tbody>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单编号:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.order_sn}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单状态:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.bstatus}</small></td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户id:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.uid}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户名:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.userInfoName}</small></td>
				</tr>			
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;支付方式:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.payment_type}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;运费:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.freight}</small></td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单总金额:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.amount}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;现金支付总额:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.cash}</small></td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;余额支付金额:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.balance}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;积分支付总额:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.integral}</small></td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;快递编号:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.courier_number}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;快递类型:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.courier_type_str}</small></td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;创建(拆单)时间:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.create_time_str}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;发货时间:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.delivery_time_str}</small></td>
				</tr>
				
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;配送地址:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.address}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;收货人:</h4></th>
					<td colspan="3" style="width:33%;"><small>${material.consignee}</small></td>
				</tr>
			</tbody>
		</table>
		

		<div style="min-height: 30px; max-height: 150px; overflow: scroll;">
				<table width="100%">
				<tbody style="min-height: 30px; overflow: hidden; max-height: 30px;">
				<tr style="border-bottom-style:solid;border-bottom-width: 1px">
					<th style="text-align: center;" colspan="5"><h3>购买商户</h3></th>
				</tr>
				<tr>
				<th style="width:20%;text-align: center;"><h4>商户编号</h4></th>
				<th style="width:30%;text-align: center;"><h4>商户名称</h4></th>
				</tr>
				
				<c:if test="${!empty seller && fn:length(seller)>0}">
			    	<c:forEach var="me" items="${seller}">
				       <tr style="background-color:#DCEEF8;text-align:center;width:20%;">
				        <td><small>${me.sellerid }</small></td>
				        <td><small>${me.sellername }</small></td>
				       </tr>
				     </c:forEach>
			     </c:if>
			     <c:if test="${empty seller || fn:length(seller)==0}">
			     	<tr class="text-center"> <td colspan="3">暂无数据</td></tr>
			     </c:if>
 			</tbody>
	 	</table>
	 	</div>
	 	
		<div style="min-height: 30px; max-height: 150px; overflow: scroll;">
				<table width="100%">
				<tbody style="min-height: 30px; overflow: hidden; max-height: 30px;">
				<tr style="border-bottom-style:solid;border-bottom-width: 1px">
					<th style="text-align: center;" colspan="5"><h3>商品详情</h3></th>
				</tr>
				<tr>
				<th style="width:20%;text-align: center;"><h4>商品名称</h4></th>
				<th style="width:20%;text-align: center;"><h4>商品数量</h4></th>
				<th style="width:20%;text-align: center;"><h4>商品金额</h4></th>
				</tr>
				
				<c:if test="${!empty product && fn:length(product)>0}">
			    	<c:forEach var="me" items="${product}">
				       <tr style="background-color:#DCEEF8;text-align:center;width:20%;">
				        <td><small>${me.material_name }</small></td>
				        <td><small>${me.quantity }</small></td>
		                <td><small>${me.price }</small></td>
				       </tr>
				     </c:forEach>
			     </c:if>
			     <c:if test="${empty product || fn:length(product)==0}">
			     	<tr class="text-center"> <td colspan="3">暂无数据</td></tr>
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

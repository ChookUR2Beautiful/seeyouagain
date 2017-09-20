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
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%" >
			<tbody>
			    <tr style=" border-bottom-style:solid;border-bottom-width: 1px;">
					<th colspan="4" style="text-align: center;"><h3>订单详情</h3></th>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户昵称:</h4></th>
					<td style="width:35%;"><small>${bill.nname}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户手机:</h4></th>
					<td style="width:35%;"><small>${bill.phoneid}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单号:</h4></th>
					<td style="width:35%;"><small>${bill.bid}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单金额:</h4></th>
					<td style="width:35%;"><small><c:if test="${'' ne bill.money}">￥${bill.money}</c:if>
					</small></td>
				</tr>
				<tr>
					<%-- <th style="width:15%;"><h4>&nbsp;&nbsp;所属向蜜客:</h4></th>
					<td style="width:35%;"><small>${bill.miketypeText}</small></td> --%>	
					<th style="width:15%;"><h4>&nbsp;&nbsp;支付流水号:</h4></th>
					<td style="width:35%;"><small>${bill.payid}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;返利金额:</h4></th>
					<td style="width:35%;"><small><c:choose><c:when test="${bill.rebate>=0}">￥${bill.rebate}</c:when></c:choose></small></td>
				<%-- 	<c:if test="${'' ne bill.rebate}">￥${bill.rebate}</c:if> --%>
					
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;分账状态:</h4></th>
					<td style="width:35%;"><small>${bill.hstatusText}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;平台收益:</h4></th>
					<td style="width:35%;"><small><c:if test="${'' ne bill.platformAmount}">￥${bill.platformAmount}</c:if></small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;下单时间:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${bill.orderDateStr}</small></td>					
					<th style="width:15%;"><h4>&nbsp;&nbsp;运营商收益:</h4></th>
					<td style="width:35%;"><small>-<%-- <c:if test="${'' ne bill.bpartnerAmount}">￥${bill.bpartnerAmount}</c:if> --%></small></td>
				</tr>
				<%-- <tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单状态:</h4></th>
					<td style="width:35%;"><small>${bill.statusText}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;商家营收:</h4></th>
					<td style="width:35%;"><small><c:if test="${'' ne bill.sellerAmount}">￥${bill.sellerAmount}</c:if></small></td>					
				</tr> --%>				
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;消费商家:</h4></th>
					<td style="width:35%;"><small>${bill.sellername}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;寻蜜客佣金:</h4></th>
					<td style="width:35%;"><small><c:if test="${'' ne bill.mikeAmount}">￥${bill.mikeAmount}</c:if></small></td>
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;所属商家:</h4></th>
					<td style="width:35%;"><small>${bill.genusname}</small></td>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;手续费:</h4></th>
					<td style="width:35%;"><small>-<%-- <c:if test="${'' ne bill.feesMoney}">￥${bill.feesMoney} </c:if>--%></small></td>						
				</tr>		
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;商家营收:</h4></th>
					<td style="width:35%;"><small><c:if test="${'' ne bill.sellerAmount}">￥${bill.sellerAmount}</c:if></small></td>			
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;减免金额:</h4></th>
					<td style="width:35%;"><small>￥${bill.reduction}</small></td>		
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;鸟币支付金额:</h4></th>
					<td style="width:35%;"><small>${bill.liveCoin}&nbsp;鸟币</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单状态:</h4></th>
					<td style="width:35%;"><small>${bill.statusText}</small></td>
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;消费商家所属合作商:</h4></th>
					<td style="width:35%;"><small>${bill.consumecorporate}</small></td>
				</tr>
				
				<tr style=" border-bottom-style:solid;border-bottom-width: 1px">
					<th colspan="4" style="text-align: center;"><h3>支付详情</h3></th>
				</tr>
				<tr>
				 	<th style="width:15%;"><h4>&nbsp;&nbsp;消费记录码:</h4></th>
					<td style="width:35%;"><small>${bill.codeid}</small></td> 
					<th style="width:15%;"><h4>&nbsp;&nbsp;所属区域:</h4></th>
					<td style="width:35%;"><small>${bill.tptitle}-${bill.tctitle}-${bill.tatitle}</small></td>
				</tr>
				<tr>
				    <th style="width:15%;"><h4>&nbsp;&nbsp;支付方式:</h4></th>
					<td style="width:35%;"><small>${bill.payTypeText}</small></td> 				   
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;所属商家归属合作商:</h4></th>
					<td style="width:35%;"><small>${bill.corporate}</small></td>
				</tr>
				<tr>					
					<th style="width:15%;"><h4>&nbsp;&nbsp;所属向蜜客:</h4></th>
					<td style="width:35%;"><small>${bill.miketypeText}</small></td>						
				</tr>						
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;消费记录二维码:</h4></th>
					<td style="width:35%;"><%-- <h5>${bill.codeid}</h5> --%><img alt="二维码" src="<%=basePath%>/getMatrix.jhtml?text=${bill.codeid}"></td>
				</tr>
				
				<!-- <tr style=" border-bottom-style:solid;border-bottom-width: 2px">
					<th colspan="4"></th>
				</tr> -->
 				<tr>
 					<th colspan="4" style="text-align: center;"> 
						<button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
 					</th>
 				</tr>
 			</tbody>
	 	</table>
	 </form>
</body>
</html>

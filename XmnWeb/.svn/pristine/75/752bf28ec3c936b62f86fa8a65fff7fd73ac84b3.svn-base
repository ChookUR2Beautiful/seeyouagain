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
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单编号:</h4></th>
					<td colspan="3" style="width:33%;"><small>${tbillFresh.bid}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户ID:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.uid}</small></td>
					<th style="width:17%;"><h4>&nbsp;用户昵称:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.nname}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户手机:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.phoneid}</small></td>
					<th style="width:17%;"><h4>&nbsp;商品数量:</h4></th>
					<td style="width:35%;">${tbillFresh.wareNum}</td>
				</tr>
				<tr>
					<%-- <th style="width:17%;"><h4>&nbsp;&nbsp;所属向蜜客:</h4></th>
					<td style="width:33%;"><small>${bill.miketypeText}</small></td> --%>	
					<th style="width:17%;"><h4>&nbsp;&nbsp;消费总金额:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.money}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;返利金额:</h4></th>
					<td style="width:35%;">${tbillFresh.profit}</td>
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;佣金支付金额:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.commision}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;赠送支付金额:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.give_money}</td>
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;积分支付金额:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${tbillFresh.integral}</small></td>					
					<th style="width:17%;"><h4>&nbsp;&nbsp;会员卡支付金额:</h4></th>
					<td style="width:35%;">${tbillFresh.cardpay}</td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;第三方支付金额:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.payment}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;配送方式:</h4></th>
					<td style="width:35%;">${tbillFresh.expressStr}</td>
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;收货人姓名:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.username}</small></td>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;联系手机:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.mobile}</small></td>						
				</tr>		
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;订单状态:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.bstatus}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;物流状态:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.wlstatus}</small></td>						
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;物流单号:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.logistics}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;下单时间:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.xddate}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;支付时间:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.zfdate}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;发货时间:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.fhdate}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;支付ID:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.payid}</small></td>
					<th style="width:17%;"><h4>&nbsp;支付流水号:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.number}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;现金券支付总金额:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.cuser}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;现金券面额总数:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.cdenom}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;客户端类型:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.phoneTypeStr}</small></td>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;配送地址:</h4></th>
					<td style="width:33%;"><small>${tbillFresh.address}</small></td>
				</tr>
				</tbody>
				</table>
				<div style="min-height: 30px; max-height: 150px; overflow: scroll;">
				<table width="100%" >
				<tbody style="min-height: 30px; overflow: hidden; max-height: 30px;">
				<tr style="border-bottom-style:solid;border-bottom-width: 1px">
					<th colspan="5" style="text-align: center;"><h3>商品详情</h3></th>
				</tr>
				<tr>
				<th style="width:20%;text-align: center;"><h4>产品编号</h4></th>
				<th style="width:20%;text-align: center;"><h4>商品名称</h4></th>
				<th style="width:20%;text-align: center;"><h4>商品数量</h4></th>
				<th style="width:20%;text-align: center;"><h4>商品金额</h4></th>
				<th style="width:20%;text-align: center;"><h4>商品净重</h4></th>
				</tr>
				<c:if test="${!empty tbillFresh}">
				<c:forEach var="goods" items="${tbillFresh.productList}">
				<tr style="background-color:#DCEEF8;text-align:center;width:20%;">
					<td><small>${goods.codeId}</small></td>
					<td><small>${goods.goodsName}</small></td>
					<td><small>${goods.goodsNum}</small></td> 
					<td><small>${goods.price}</small></td>
					<td><small>${goods.suttle}</small></td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty tbillFresh}">
				<tr style="background-color:#DCEEF8;text-align:center;width:20%;">
					<td colspan="5"><small>暂无商品信息</small></td>
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

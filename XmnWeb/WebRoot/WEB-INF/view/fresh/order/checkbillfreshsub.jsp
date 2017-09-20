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
		<table width="100%" >
			<tbody>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单编号:</h4></th>
					<td colspan="3" style="width:33%;"><small>${tbillFreshSub.subOrderSn}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;父订单ID:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.orderSn}</small></td>
					<th style="width:17%;"><h4>&nbsp;供应商ID:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.supplierId}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;用户ID:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.uid}</small></td>
					<th style="width:17%;"><h4>&nbsp;订单总金额:</h4></th>
					<td style="width:35%;"><small>${tbillFreshSub.totalAmount}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单优惠金额:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.preferential}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;现金支付金额:</h4></th>
					<td style="width:35%;"><small>${tbillFreshSub.cashAmount}</small></td>
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;余额支付金额:</h4></th>
					<td style="width:33%;"><small>${balanceAmount.balanceAmount}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;积分支付总额:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.integralAmount}</td>
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;运费:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${tbillFreshSub.freight}</small></td>					
					<th style="width:17%;"><h4>&nbsp;&nbsp;发货状态:</h4></th>
					<td style="width:35%;"><small>${tbillFreshSub.strStatus}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;快递单号:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.courierNumber}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;快递类型:</h4></th>
					<td style="width:35%;"><small>${tbillFreshSub.strCourierType}</small></td>
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;发货时间:</h4></th>
					<td style="width:33%;">
						<small>
							<fmt:formatDate value="${tbillFreshSub.deliveryTime}" pattern="YYYY-MM-dd HH:mm:ss"/>
						</small>
					</td>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;创建时间:</h4></th>
					<td style="width:33%;">
						<small>
							<fmt:formatDate value="${tbillFreshSub.createTime}" pattern="YYYY-MM-dd HH:mm:ss"/>
						</small>
					</td>						
				</tr>		
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;配送地址:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.address}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;联系人手机:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.mobile}</small></td>						
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;收货人:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.consignee}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;操作人:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.operator}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;备注:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.memo}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;版本号:</h4></th>
					<td style="width:33%;"><small>${tbillFreshSub.version}</small></td>
				</tr>
			</tbody>
		</table>
		<div style="min-height: 30px; max-height: 150px; overflow: scroll;">
				<table width="100%" >
				<tbody style="min-height: 30px; overflow: hidden; max-height: 30px;">
				<tr style="border-bottom-style:solid;border-bottom-width: 1px">
					<th colspan="8" style="text-align: center;"><h3>商品详情</h3></th>
				</tr>
				<tr>
					<th style="width:12%;text-align: center;"><h4>产品编号</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品名称</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品数量</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品金额</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品净重</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品属性</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品加价</h4></th>
					<th style="width:12%;text-align: center;"><h4>商品库存</h4></th>
				</tr>
				<c:if test="${!empty product}">
				<c:forEach var="goods" items="${product}">
				<tr style="background-color:#DCEEF8;text-align:center;width:12%;">
					<td><small>${goods.codeId}</small></td>
					<td><small>${goods.pname}</small></td>
					<td><small>${goods.goodsNum}</small></td> 
					<td><small>${goods.price}</small></td>
					<td><small>${goods.suttle}</small></td>
					<td><small>${goods.strAttrVal}</small></td>
					<td><small>${goods.attrMount}</small></td>
					<td><small>${goods.stock}</small></td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty product}">
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

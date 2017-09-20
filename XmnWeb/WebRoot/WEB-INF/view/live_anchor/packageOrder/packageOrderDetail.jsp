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
	th{
		text-align: center;
	}
</style>
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
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单编号:</h4></th>
					<td style="width:35%;"><small>${packageOrder.orderNo}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;支付方式:</h4></th>
					<td style="width:35%;"><small>${packageOrder.paymentTypeText}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;会员ID:</h4></th>
					<td style="width:35%;"><small>${packageOrder.uid}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;数量:</h4></th>
					<td style="width:35%;"><small>${packageOrder.nums}</small></td>
					</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户昵称:</h4></th>
					<td style="width:35%;"><small>${packageOrder.uname}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单金额(现金价):</h4></th>
					<td style="width:35%;"><small>${packageOrder.totalAmount}</small></td>				
					
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;订单金额(鸟币价)</h4></th>
					<td style="width:35%;"><small>${packageOrder.totalCoinAmount}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;第三方支付金额:</h4></th>
					<td style="width:35%;"><small>${packageOrder.cash}</small></td>			
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;余额支付金额:</h4></th>
					<td style="width:35%;"><small>${packageOrder.balance +packageOrder.commision+packageOrder.zbalance}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;鸟粉卡支付金额:</h4></th>
					<td style="width:35%;"><small>${packageOrder.beans}</small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;专享鸟币支付金额:</h4></th>
					<td style="width:35%;"><small>${packageOrder.sellerCoin}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;抵用券抵用金额:</h4></th>
					<td style="width:35%;"><small>${packageOrder.cuser}</small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;现金鸟币转换比:</h4></th>
					<td style="width:35%;"><small>${packageOrder.base}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;商家编号:</h4></th>
					<td style="width:35%;"><small>${packageOrder.sellerid}</small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;商家名称:</h4></th>
					<td style="width:35%;"><small>${packageOrder.sellername}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;购买来源:</h4></th>
					<td style="width:35%;"><small><c:if test="${packageOrder.orderSource ==1}">通过主播购买</c:if>
					<c:if test="${packageOrder.orderSource ==2}">通过商户购买</c:if>
					<c:if test="${packageOrder.orderSource ==3}">通过预告购买</c:if>
					<c:if test="${packageOrder.orderSource ==4}">其他</c:if>
					</small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单状态:</h4></th>
					<td style="width:35%;"><small>支付成功</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;赠送预售抵用券:</h4></th>
					<td style="width:35%;"><small>${packageOrder.retrunCouponAmount}</small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;支付流水号:</h4></th>
					<td style="width:35%;"><small>${packageOrder.payid}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;下单时间:</h4></th>
					<td style="width:35%;"><small>${packageOrder.createTimeStr}</small></td>
				</tr>
					<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;支付时间:</h4></th>
					<td style="width:35%;"><small>${packageOrder.payTimeStr}</small></td>
				</tr>
 			</tbody>
	 	</table>
	 	<table border="1" align="center" width="80%" style="padding-top:50px">
	 		<tr>
	 			<th style="align:center">套餐编号</th>
	 			<th>优惠券状态</th>
	 			<th>核销时间</th>
	 			<th>是否分账</th>
	 		</tr>
	 		<c:forEach items="${coupons}" var="coupon">
	 			<tr>
	 				<td align="center"><span>${coupon.pid}</span></td>
	 				<td align="center"><c:if test="${coupon.user_status == 0}">未使用</c:if><c:if test="${coupon.user_status == 2}">已使用</c:if></td>
	 				<td align="center"><c:if test="${coupon.user_status == 0}">-</c:if><c:if test="${coupon.user_status == 2}">${coupon.user_time }</c:if></td>
	 				<td align="center"><c:if test="${coupon.user_status == 0}">未分账</c:if><c:if test="${coupon.user_status == 2}">已分账</c:if></td>
	 			</tr>		
	 		</c:forEach>
	 	</table>
	 	
 					<div colspan="4" style="text-align: center;padding-top:15px"> 
						<button type="reset" class="btn btn-success" data-dismiss="modal" onclick="orderRefund();"><span class="icon-reply-all"></span>退款</button>
 					</d>
	 </form>
</body>

<!-- 退款备注 -->
	<div class="modal fade" id="refundDescriptionModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title">退款备注</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="refundDescription">
						<div class="form-group" style="margin-left:-100px">
							<label class="col-md-4 control-label">备注：</label>
							<div class="col-md-7">
							<textarea name="description" id="description" cols="45" rows="5" ></textarea> 
							</div>
						</div>

						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 30;">
								<button type="submit" class="btn btn-default">
									<span class="icon-remove"></span> 取 消
								</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-success"
									data-dismiss="modal" onclick="refund();">
									<span class="icon-ok"></span> 确认
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
     </div>

<script type="text/javascript">
contextPath = '${pageContext.request.contextPath}';

/**
 * 订单退款
 * 修改 订单状态 并 注销发放的优惠券和抵用券
 * @param bid
 */
function orderRefund(){
	$("#refundDescriptionModal").modal();
}

function refund(){
	
	var description = $("#description").val();
	var orderNo = '${packageOrder.orderNo}';
	
	var url=contextPath+"/packageOrder/manage/refund.jhtml";
	
	$.ajax({
		type : 'post',
		url : url,
		data :{'description':description,'orderNo':orderNo},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/packageOrder/manage/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
			
		},
		error : function(data) {
			window.messager.warning(data.msg);
		}
	});
}

</script>
</html>

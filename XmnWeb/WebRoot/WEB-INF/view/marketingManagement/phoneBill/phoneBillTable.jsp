<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="tableinfo" data-page="${pageable.page}" data-total="${pageable.total}">
		<br>		
			<div class="page-header">
		      	 <h2>总计:</h2>
		     </div>
		     	<table class="table table-hover table-bordered table-striped info" >
					<tbody >
						<tr class="text-center" >
						<td style="font-size: 18px !important;">用户总数</td>
						<td style="font-size: 18px !important;">订单总数</td>
						<td style="font-size: 18px !important;">已销售数量</td>
						<td style="font-size: 18px !important;">已销售总金额</td>
						<td style="font-size: 18px !important;">已付定金</td>
						
						<td style="font-size: 18px !important;">已定未售数量</td>
						<td style="font-size: 18px !important;">已定未付总金额</td>
						<td style="font-size: 18px !important;">已定未付定金</td>
					</tr>
					<tr  class="text-center">
						<td style="font-size: 18px !important;">${total.userNum}</td>
						<td style="font-size: 18px !important;">${total.orderNum}</td>
						<td style="font-size: 18px !important;">${total.sellNum }</td>	
						<td style="font-size: 18px !important;">${total.priceNum}</td>
						<td style="font-size: 18px !important;">${total.payDepPrice }</td>	
				
						
						<td style="font-size: 18px !important;">${total.notSellNum }</td>		
						<td style="font-size: 18px !important;">${total.notSellPriceNum}</td>
						<td style="font-size: 18px !important;">${total.allDepPrice }</td>	
					</tr>
					</tbody>
				</table>
		<br>


		<table class="table table-hover table-bordered table-striped info" >
							<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">iPhone6活动统计列表</caption>
							<thead>
							<tr >
								<th style="width:10%">收货人</th>
								<th style="width:10%">身份证</th>
								<th style="width:10%">收货人手机</th>
								<th style="width:10%">订购时间</th>
								<th style="width:5%">省份</th>
								<th style="width:5%">城市</th>
								<th style="width:10%">收货地址</th>
								<th style="width:5%">数量</th>
								<th style="width:5%">总金额</th>
							    <th style="width:5%">定金</th>
								<th style="width:5%">商品型号</th>
								<th style="width:10%">订单号</th>
								<th style="width:5%">支付状态</th>
								<c:if test="${null!=btnAu['marketingManagement/phoneBill/viewOrder'] && btnAu['marketingManagement/phoneBill/viewOrder']}">
									<th style="width:5%">订单详情</th>
								</c:if>
							</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pageable.content&& fn:length(pageable.content)>0}">
								<c:forEach var="list" items="${pageable.content}">
									<tr >
										<td>${list.getUser}</td>
										<td>${list.getCardId}</td>
										<td>${list.getUserPhone}</td>
										<td><fmt:formatDate value="${list.ptime}" pattern="yyyy-MM-dd HH:mm"/></td>
										<td>${list.province }</td>
										<td>${list.city}</td>
										<td>${list.address}</td>
										
										<td>${list.pnum}</td>
										<td>${list.totalPrice}</td>
										<td>${list.depPrice}</td>
										
										<td>${list.pmodel }</td>
										<td>${list.bid}</td>
										<td>
											<c:if test="${list.pstatus == 0}">
												待支付
											</c:if>
											<c:if test="${list.pstatus == 1}">
												已支付
											</c:if>
										</td>
										<c:if test="${null!=btnAu['marketingManagement/phoneBill/viewOrder'] && btnAu['marketingManagement/phoneBill/viewOrder']}">
											<td><a href="javascript:void();" data-type="ajax" data-url="marketingManagement/phoneBill/viewOrder.jhtml?pid=${list.pid}" data-width="60%" data-toggle="modal">查看</a></td>
										</c:if>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty pageable.content || fn:length(pageable.content)==0}">
									<tr ><td colspan="15">暂无数据</td></tr>
								</c:if>
							</tbody>
		</table>
</div>


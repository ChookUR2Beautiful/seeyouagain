<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="tableinfo" data-page="${pageable.page}" data-total="${pageable.total}">
	<br>		
	<div class="page-header">
        <h2>总计:</h2>
       	</div>
     	<table class="table table-hover table-bordered table-striped info" >
		<tbody >
						<tr class="text-center" >
						<td style="font-size: 18px !important;">交易量(笔)</td>
						<td style="font-size: 18px !important;">平均交易金额(元)</td>
						<td style="font-size: 18px !important;">交易金额(元)</td>
					</tr>
					<tr  class="text-center">
						<td style="font-size: 18px !important;">${total.tradingVolume}</td>
						<td style="font-size: 18px !important;">${total.averageTradingTotal}</td>
						<td style="font-size: 18px !important;">${total.tradingTotal }</td>																
					</tr>
					</tbody>
	</table>
	<br>
<table class="table table-hover table-bordered table-striped info" >
					<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家交易流水统计列表</caption>
					<thead>
					<tr >
						<th>日期</th>
						<th>交易量(笔)</th>
						<th>平均交易金额(元)</th>
						<th>交易金额(元)</th>
					</tr>
					</thead>
					<tbody>
						<c:if test="${!empty pageable.content&& fn:length(pageable.content)>0}">
						<c:forEach var="list" items="${pageable.content}">
							<tr >
								<td>${list.censusDate}</td>
								<td>${list.tradingVolume}</td>
								<td>${list.averageTradingTotal}</td>
								<td>${list.tradingTotal }</td>																
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty pageable.content || fn:length(pageable.content)==0}">
							<tr ><td colspan="4">暂无数据</td></tr>
						</c:if>
					</tbody>
				</table>
</div>
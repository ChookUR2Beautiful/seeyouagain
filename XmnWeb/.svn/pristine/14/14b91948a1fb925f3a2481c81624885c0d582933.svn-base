<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="tableinfo" data-page="<c:if test="${!empty map }">${map.page} </c:if><c:if test="${empty map }">0</c:if>" data-total="<c:if test="${!empty map }">${map.total}</c:if><c:if test="${empty map }">0</c:if>">
	<br>		
	<div class="page-header">
         	 <h2>总计:</h2>
     </div>
	<table class="table table-hover table-bordered table-striped info" >
					<tbody >
						<tr class="text-center" >
						<td style="font-size: 18px !important;">新增会员</td>
						 <td style="font-size: 18px !important;">登录会员</td> 
						<td style="font-size: 18px !important;">启动次数</td>
					<!-- 	<td style="font-size: 18px !important;">平均使用时长</td> -->
						<td style="font-size: 18px !important;">交易量(笔)</td>
						<td style="font-size: 18px !important;">交易金额(元)</td>
					</tr>
					<tr  class="text-center">
							<td  style="font-size: 18px !important;"><c:if test="${empty map.merberAction.add_user}">0</c:if><c:if test="${!empty map.merberAction.add_user}">${ map.merberAction.add_user}</c:if></td>
							<td  style="font-size: 18px !important;"><c:if test="${empty map.merberAction.login_user}">0</c:if><c:if test="${!empty map.merberAction.login_user}">${ map.merberAction.login_user}</c:if></td>
							<td  style="font-size: 18px !important;"><c:if test="${empty map.merberAction.start_no}">0</c:if><c:if test="${!empty map.merberAction.start_no}">${ map.merberAction.start_no}</c:if></td>
							<td  style="font-size: 18px !important;"><c:if test="${empty map.merberAction.tradingVolume}">0</c:if><c:if test="${!empty map.merberAction.tradingVolume}">${ map.merberAction.tradingVolume}</c:if></td>
							<td  style="font-size: 18px !important;"><c:if test="${empty map.merberAction.tradingTotal}">0</c:if><c:if test="${!empty map.merberAction.tradingTotal}">${ map.merberAction.tradingTotal}</c:if></td>
					</tr>
					</tbody>
				</table>
	<br>
<table class="table table-hover table-bordered table-striped info" >
	<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">会员行为统计列表</caption>
	<thead>
	<tr >
		<th>日期</th>
		<th>新增会员</th>
		<th>登录会员</td>
		<!-- <th>启动商家</th>-->
		<th>启动次数</th>
		<!--<th>平均使用时长</th> -->
		<th>交易量(笔)</th>
		<th>交易金额(元)</th>
		<th>累计注册会员</th>
	</tr>
	</thead>
	<tbody>
		<c:if test="${!empty map && fn:length(map.list)>0}">
		<c:forEach var="list" items="${map.list}">
			<tr >
				<td>${list.date }</td>
				<td><c:if test="${empty list.add_user}">0</c:if><c:if test="${!empty list.add_user}">${list.add_user}</c:if></td>
				<td><c:if test="${empty list.login_user}">0</c:if><c:if test="${!empty list.login_user}">${list.login_user}</c:if></td>
				<!-- <td>0</td>-->
				<td><c:if test="${empty list.start_no}">0</c:if><c:if test="${!empty list.start_no}">${list.start_no}</c:if></td>
				<!--<td>0</td> -->
				<td><c:if test="${empty list.tradingVolume}">0</c:if><c:if test="${!empty list.tradingVolume}">${list.tradingVolume}</c:if></td>
				<td><c:if test="${empty list.tradingTotal}">0</c:if><c:if test="${!empty list.tradingTotal}">${list.tradingTotal }</c:if></td>	
				<td><c:if test="${empty list.total_user}">0</c:if><c:if test="${!empty list.total_user}">${list.total_user }</c:if></td>			
																	
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty map || fn:length(map.list)==0}">
			<tr ><td colspan="10">暂无数据</td></tr>
		</c:if>
	</tbody>
</table>
</div>
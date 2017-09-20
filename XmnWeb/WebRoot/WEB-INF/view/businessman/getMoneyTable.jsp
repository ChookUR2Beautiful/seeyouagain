<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="table table-hover table-bordered table-striped info"
	data-page="${page}" data-total="${total}">
	<caption
		style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家提现列表</caption>
	<thead>
		<tr class='text-center'>
			<th>商家编号</th>
			<th>商家名称</th>
			<th>商家帐号</th>
			<!-- <th>类型</td>	 -->
			<th>账户余额</th>
			<!-- 	<th>营收总额</th>
					<th>佣金总额</th> -->
			<th>提现金额</th>
			<th>提现时间</th>
			<th>提现状态</th>
			<th>提现类型</th>
			<th>提现来源</th>
			<th>提现账号</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<c:if
			test="${!empty result &&!empty result.data&& fn:length(result.data)>0}">
			<c:forEach var="list" items="${result.data}">
				<tr class="text-center">
				    <td>${list.userid}</td>
					<td>${list.username }</td>
					<td>${empty list.loginAccount ? "-" : list.loginAccount}</td>
					<td>${list.balance}</td>
					<%-- 	<td>${list.taking}</td>
								<td>${list.commission}</td> --%>
					<td>${list.money }</td>
					<td>${list.date }</td>
					<td><c:if test="${list.state==1 }">处理中</c:if> <c:if
							test="${list.state==2 }">已到帐</c:if></td>
					<td><c:if test="${list.type==1 }">银行卡</c:if> <c:if
							test="${list.type==2 }">第三方支付账户</c:if></td>
					<td><c:if test="${list.source==1 }">从返利提现</c:if> <c:if
							test="${list.source==2 }">从佣金提现</c:if> <c:if
							test="${list.source==3 }">从营收提现</c:if></td>
					<td>${list.account }</td>
						<td>${list.opinion}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if
			test="${empty result||empty result.data || fn:length(result.data)==0}">
			<tr class="text-center">
				<td colspan="11">暂无数据</td>
			</tr>
		</c:if>
	</tbody>
</table>

<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="table table-hover table-bordered table-striped info"
	data-page="${page}" data-total="${total}">
	<caption
		style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">会员提现列表</caption>
	<thead>
		<tr>
			<th>用户编号</th>
		    <th>用户账号</th>
			<th>用户昵称</th>
			<th>用户类型</th>
			<th>提现账号</th>
			<th>流水账号</th>
			<th>提现金额</th>
			<th>账户余额</th>
			<th>提现时间</th>
			<th>提现状态</th>
			<th>提现类型</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<c:if
			test="${!empty result &&!empty result.data&& fn:length(result.data)>0}">
			<c:forEach var="list" items="${result.data}">
				<tr>
					<td>${list.userid }</td>
				    <td>${list.loginAccount }</td>
					<td>${list.username }</td>
					<td><c:if test="${list.usertype==2 }">普通会员</c:if> <c:if
							test="${list.usertype==3 }">寻蜜客</c:if></td>
					<td>${list.account}</td>
					<td>${list.flowid}</td>
					<td>${list.money }</td>
					<td>${list.balance }</td>
					<td>${list.date }</td>
					<td><c:if test="${list.state==1 }">处理中</c:if> <c:if
							test="${list.state==2 }">已到帐</c:if></td>
					<td><c:if test="${list.type==1 }">银行卡</c:if> <c:if
							test="${list.type==2 }">第三方支付账户</c:if></td>
					<td>${list.opinion}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if
			test="${empty result||empty result.data || fn:length(result.data)==0}">
			<tr>
				<td colspan="14">暂无数据</td>
			</tr>
		</c:if>
	</tbody>
</table>

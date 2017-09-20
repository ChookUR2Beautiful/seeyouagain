<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body style="overflow-x: hidden;overflow-y: auto;background:#EEE">
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>获取条件</h3>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th>消费</th>
							<th>次数限制</th>
							<th>活动区域</th>
							<th>针对用户</th>
							<th>活动开始时间</th>
							<th>活动结束时间</th>
							<th>状态</th>
						</tr>
						<tr>
							<td>${tCouponIssue.amount}</td>
							<td>${tCouponIssue.maxTimes}</td>
							<td>${tCouponIssue.areaText}</td>
							<td>${tCouponIssue.rate == 3?"全部用户":"新用户"}</td>
							<td><fmt:formatDate value="${tCouponIssue.dateStart}"
									pattern="yyyy-MM-dd HH:mm" /></td>
							<td><fmt:formatDate value="${tCouponIssue.dateEnd}"
									pattern="yyyy-MM-dd HH:mm" /></td>
							<td>${tCouponIssue.statusText}</td>
						</tr>
					</tbody>
				</table>
				<h3>订单后刮刮卡</h3>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th>优惠券</th>
							<th>发行量（张）</th>
							<th>概率（%）</th>
						</tr>
						<c:forEach items="${tCouponIssue.tCouponIssueRefs}"
							var="couponIssueRef">
							<c:if test="${couponIssueRef.type == 1}">
								<tr>
									<td>${couponIssueRef.cname}</td>
									<td>${couponIssueRef.issueVolume}</td>
									<td>${couponIssueRef.hitRatioPercent}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<h3>分享后刮刮卡</h3>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th>优惠券</th>
							<th>发行量（张）</th>
							<th>概率（%）</th>
						</tr>
						<c:forEach items="${tCouponIssue.tCouponIssueRefs}"
							var="couponIssueRef">
							<c:if test="${couponIssueRef.type == 2}">
								<tr>
									<td>${couponIssueRef.cname}</td>
									<td>${couponIssueRef.issueVolume}</td>
									<td>${couponIssueRef.hitRatioPercent}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

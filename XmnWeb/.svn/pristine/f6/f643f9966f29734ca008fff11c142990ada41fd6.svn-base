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
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body style="overflow-x: hidden;overflow-y: auto;background:#EEE">
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-body" style="margin-top:40px;">
				<form class="form-horizontal" id="updateShareForm">
					<input type="hidden" name="issueId" value="${tCouponIssue.issueId}" />
					<c:if test="${tCouponIssue.isGenerate==1}">
						<div class="form-group">
							<label class="col-md-2 control-label"></label>
							<div class="col-md-8 alert alert-warning">提示：优惠券发行后，不能修改相关信息！</div>
						</div>
					</c:if>
					<div class="form-group">
						<label class="col-md-2  control-label">消费金额：</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">满</span> <input type="text"
									class="form-control " name="amount"
									value="${tCouponIssue.amount}"
									${tCouponIssue.isGenerate==1?"disabled":""}> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">次数限制：</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">每人每天最多刮</span> <input type="text"
									class="form-control " name="maxTimes"
									value="${tCouponIssue.maxTimes}"
									${tCouponIssue.isGenerate==1?"disabled":""}> <span
									class="input-group-addon">次</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">数量限制：</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">每人每天最多领</span> <input
									type="text" class="form-control " name="maximum"
									value="${tCouponIssue.maximum}"
									${tCouponIssue.isGenerate==1?"disabled":""}> <span
									class="input-group-addon">张</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">活动区域：</label>
						<div class="col-md-3">
							<div class="input-group" id="areaInfo"
								style="width:100%;float:left;" initValue="${tCouponIssue.area}"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">针对用户：</label>
						<div class="col-md-3">
							<select class="form-control" name="rate"
								${tCouponIssue.isGenerate==1?"disabled":""}>
								<option value="3"
									<c:if test="${tCouponIssue.rate == 3}">selected</c:if>>全部用户</option>
								<option value="1"
									<c:if test="${tCouponIssue.rate == 1}">selected</c:if>>新用户</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">活动时间：</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="text" class="form-control form-datetime"
									name="dateStart" ${tCouponIssue.isGenerate==1?"disabled":""}
									<c:if test="${tCouponIssue != null }">value='<fmt:formatDate value="${tCouponIssue.dateStart}" pattern="yyyy-MM-dd HH:mm"/>'</c:if>><span
									class="input-group-addon ">到</span> <input type="text"
									class="form-control form-datetime" name="dateEnd"
									${tCouponIssue.isGenerate==1?"disabled":""}
									<c:if test="${tCouponIssue != null }">value='<fmt:formatDate value="${tCouponIssue.dateEnd}" pattern="yyyy-MM-dd HH:mm"/>'</c:if>>
							</div>
						</div>
					</div>
					<c:set var="index" value="${0}" />
					<div class="form-group">
						<label class="col-md-2 control-label">订单后刮刮卡优惠券设置：</label>
						<div class="col-md-5">
							<div id="orderCoupons">
								<c:forEach items="${tCouponIssue.tCouponIssueRefs}"
									var="couponIssueRef">
									<c:if test="${couponIssueRef.type == 1}">
										<div class="input-group" data-num="${index}">
											<input type="hidden" name="tCouponIssueRefs[${index}].type"
												value="1"> <input type="hidden"
												name="tCouponIssueRefs[${index}].refId"
												value="${couponIssueRef.refId}"><select
												class="form-control share-cid"
												name="tCouponIssueRefs[${index}].cid"
												${tCouponIssue.isGenerate==1?"disabled":""}>
												<option value="">请选择优惠券</option>
												<c:forEach items="${tCoupons}" var="tCoupon">
													<option value="${tCoupon.cid}"
														<c:if test="${couponIssueRef.cid == tCoupon.cid}">selected</c:if>>${tCoupon.cname}</option>
												</c:forEach>
											</select> <span class="input-group-addon fix-border fix-padding"></span>
											<input type="text" class="form-control share-issueVolume"
												${tCouponIssue.isGenerate==1?"disabled":""}
												name="tCouponIssueRefs[${index}].issueVolume"
												placeholder="发行量" value="${couponIssueRef.issueVolume}">
											<span class="input-group-addon">张</span> <span
												class="input-group-addon fix-border fix-padding"></span> <input
												type="text" class="form-control share-hitRatio"
												name="tCouponIssueRefs[${index}].hitRatio" placeholder="概率"
												value="${couponIssueRef.hitRatioPercent}"
												${tCouponIssue.isGenerate==1?"disabled":""}><span
												class="input-group-addon">%</span>
											<c:if test="${tCouponIssue.isGenerate==0}">
												<span class="input-group-addon fix-border fix-padding"></span>
												<span class="input-group-addon"><i
													style="cursor:pointer" class="icon icon-plus"></i> </span>
												<span class="input-group-addon fix-padding"></span>
												<span class="input-group-addon"><i
													style="cursor:pointer" class="icon icon-minus"></i></span>
											</c:if>
										</div>
										<c:set var="index" value="${index+1}" />
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div class="col-md-3">
							<div>（优惠券概率之和必须等于100%）</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">分享的刮刮卡优惠券设置：</label>
						<div class="col-md-5">
							<div id="shareCoupons">
								<c:forEach items="${tCouponIssue.tCouponIssueRefs}"
									var="couponIssueRef">
									<c:if test="${couponIssueRef.type == 2}">
										<div class="input-group" data-num="${index }">
											<input type="hidden" name="tCouponIssueRefs[${index}].type"
												value="2"> <input type="hidden"
												name="tCouponIssueRefs[${index}].refId"
												value="${couponIssueRef.refId}"><select
												class="form-control share-cid"
												name="tCouponIssueRefs[${index}].cid"
												${tCouponIssue.isGenerate==1?"disabled":""}>
												<option value="">请选择优惠券</option>
												<c:forEach items="${tCoupons}" var="tCoupon">
													<option value="${tCoupon.cid}"
														<c:if test="${couponIssueRef.cid == tCoupon.cid}">selected</c:if>>${tCoupon.cname}</option>
												</c:forEach>
											</select> <span class="input-group-addon fix-border fix-padding"></span>
											<input type="text" class="form-control share-issueVolume"
												name="tCouponIssueRefs[${index}].issueVolume"
												placeholder="发行量" value="${couponIssueRef.issueVolume}"
												${tCouponIssue.isGenerate==1?"disabled":""}> <span
												class="input-group-addon">张</span> <span
												class="input-group-addon fix-border fix-padding"></span> <input
												type="text" class="form-control share-hitRatio"
												name="tCouponIssueRefs[${index}].hitRatio" placeholder="概率"
												value="${couponIssueRef.hitRatioPercent}"
												${tCouponIssue.isGenerate==1?"disabled":""}><span
												class="input-group-addon">%</span>
											<c:if test="${tCouponIssue.isGenerate==0}">
												<span class="input-group-addon fix-border fix-padding"></span>
												<span class="input-group-addon"><i
													style="cursor:pointer" class="icon icon-plus"></i> </span>
												<span class="input-group-addon fix-padding"></span>
												<span class="input-group-addon"><i
													style="cursor:pointer" class="icon icon-minus"></i></span>
											</c:if>
										</div>
										<c:set var="index" value="${index+1 }" />
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div class="col-md-3">
							<div>（优惠券概率之和必须等于100%）</div>
						</div>
					</div>
					<div class="col-md-12 text-center">
						<button class="btn btn-danger" id="submit" type="submit">保存</button>
						&nbsp;&nbsp;
						<button onclick="window.history.back()" class="btn btn-warning"
							id="result" type="reset">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="couponIssueTemp" style="display:none;">
		<div class="input-group" data-num="index">
			<input type="hidden" name="tCouponIssueRefs[index].type"
				value="typeValue"> <select class="form-control share-cid"
				name="tCouponIssueRefs[index].cid">
				<option value="">请选择优惠券</option>
				<c:forEach items="${tCoupons}" var="tCoupon">
					<option value="${tCoupon.cid}">${tCoupon.cname}</option>
				</c:forEach>
			</select> <span class="input-group-addon fix-border fix-padding"></span> <input
				type="text" class="form-control share-issueVolume"
				name="tCouponIssueRefs[index].issueVolume" placeholder="发行量">
			<span class="input-group-addon">张</span> <span
				class="input-group-addon fix-border fix-padding"></span> <input
				type="text" class="form-control share-hitRatio"
				name="tCouponIssueRefs[index].hitRatio" placeholder="概率"><span
				class="input-group-addon">%</span> <span
				class="input-group-addon fix-border fix-padding"></span> <span
				class="input-group-addon"><i style="cursor:pointer"
				class="icon icon-plus"></i> </span><span
				class="input-group-addon fix-padding"></span> <span
				class="input-group-addon"><i style="cursor:pointer"
				class="icon icon-minus"></i></span>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
		isDisabled = ${tCouponIssue.isGenerate==1?true:false};
	</script>
	<jsp:include page="../../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/coupon/couponissue/share/updateShare.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>秒杀活动明细</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css" />
</head>
<body class="padd-fill-tb bg-color-01">
		<div class="container-wrap">
			<div class="fill-list-module resetfill-list">
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${kill.name}</span><span
							class="item-name">获得名称</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${awaryCount!=null?awaryCount:0}份</span><span
							class="item-name">奖品数量</span>
					</div>
				</div>
				<div class="list-wrap">
					<c:forEach items="${kill.awardRelations}" var="sellerCouponDetail"
						varStatus="status">
						<c:choose>
							<c:when test="${status.index==0}">
								<div class="list-item">
									<span class="item-input-wrap">${sellerCouponDetail.awardName}</span><span
										class="item-name">秒杀奖品</span>
								</div>
							</c:when>
							<c:otherwise>
								<span class="item-input-wrap">${sellerCouponDetail.awardName}</span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap"><c:if
								test="${kill.limitNumber==1}">${kill.limitNumber}次</c:if>
							<c:if test="${kill.limitNumber==0}">无限制</c:if></span><span
							class="item-name">每人限领</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${kill.secKillAmount}</span><span
							class="item-name">秒杀价格</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap"><fmt:formatDate
								value='${kill.beginDate}' type="date" dateStyle="medium" /> 至
							<fmt:formatDate value='${kill.endDate}' type="date"
								dateStyle="medium" /></span><span class="item-name">活动时间</span>
					</div>
				</div>
			<p class="fill-list-desc-activity">*秒杀金额为所有奖品的抢购价格；</p>
			<p class="fill-list-desc-activity">*奖品已领取完毕后，将不会再有活动，会自动跳过；</p>
			<p class="fill-list-desc-activity">*奖品在活动期间有效。</p>
		</div>
		<c:if test="${kill.status==0&&kill.endDate>nowDate}">
			<div class="floor-module">
	        	<a class="floor-links links-type2" href="javaScript:void(0)" onclick="viweActivity()">预览</a>
	   		 </div>		
		</c:if>
</body>
<script type="text/javascript">
		function viweActivity(){
			window.location.href="${shareUrl}";	
		}
		
</script>
</html>
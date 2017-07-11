<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>免费尝新明细</title>
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
						<span class="item-input-wrap">${freetry.name}</span><span
							class="item-name">免费尝新活动名称</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${awaryCount!=null?awaryCount:0}份</span><span
							class="item-name">奖品数量</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${freetry.prizeDrawNumber}</span><span
							class="item-name">每人可抽取次数</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap"><c:if
								test="${freetry.limitNumber==1}">${freetry.limitNumber}次</c:if>
							<c:if test="${freetry.limitNumber==0}">无限制</c:if></span><span
							class="item-name">每人限领</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" >${freetry.awardProportion}%</span><span
							class="item-name">获得概率</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${freetry.setConditionToString}</span><span
							class="item-name">重设条件</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap"><fmt:formatDate
								value='${freetry.beginDate}' type="date" dateStyle="medium" /> 至
							<fmt:formatDate value='${freetry.endDate}' type="date"
								dateStyle="medium" /></span><span class="item-name">活动时间</span>
					</div>
				</div>
				<c:forEach items="${freetry.awardRelations}"
					var="sellerCouponDetail" varStatus="status">
					<div class="list-wrap ${status.index==0?'ist-item-reset':''}">
						<div class="list-item">
							<span class="item-input-wrap">${sellerCouponDetail.amount}份</span><span
								class="item-name">${sellerCouponDetail.awardName}</span>
						</div>
					</div>
				</c:forEach>
			</div>
			<p class="fill-list-desc-activity">*当获得几率设置为0时，获得记录为0，用户每次抽奖获得谢谢惠顾；</p>
			<p class="fill-list-desc-activity">*当赠品已领取完毕后，将不会在获得，会自动跳过。</p>
			<p class="fill-list-desc-activity">*重设条件只能设置其中一项；</p>
			<p class="fill-list-desc-activity">*奖品在活动期间有效。</p>
		</div>
		<c:if test="${freetry.status==0&&freetry.endDate>nowDate}">
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
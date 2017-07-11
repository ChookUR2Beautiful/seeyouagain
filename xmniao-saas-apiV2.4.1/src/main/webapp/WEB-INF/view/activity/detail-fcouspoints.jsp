<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活动明细</title>
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
						<span class="item-input-wrap">支付满￥${fcouspoints.fullPrice}可集一个点</span><span
							class="item-name">集点活动</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${fcouspoints.isSuposition==1?'是':'否'}</span><span
							class="item-name">一次付款可集多点</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${fcouspoints.awardRelation.awardName}</span><span
							class="item-name">奖品详情</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap"><fmt:formatDate
								value='${fcouspoints.beginDate}' type="date" dateStyle="medium" /> 至
							<fmt:formatDate value='${fcouspoints.endDate}' type="date"
								dateStyle="medium" /></span><span class="item-name">活动时间</span>
					</div>
				</div>
				</div>
			<p class="fill-list-desc-activity">*会员在集点满额兑换奖品后,将自动重新集点；</p>
		</div>
		<c:if test="${fcouspoints.status==0&&fcouspoints.endDate>nowDate}">
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通抽奖-红包明细</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript">
 function viweActivity(){
	 window.location.href="${ctx}/h5/redpacket/preview?redpacketId=${requestScope.data.redpacketId}";
 };

</script>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
        <div class="fill-list-module resetfill-list" >
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${requestScope.data.redpacketName}</span><span class="item-name">红包名称</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${requestScope.data.totalAmount}</span><span class="item-name">总预算</span></div></div>
            <c:if test="${!empty requestScope.data.redpacketNumber}">
               <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${requestScope.data.redpacketNumber} 个红包</span><span class="item-name">红包个数</span></div></div>
            </c:if>
            <c:if test="${!empty requestScope.data.singleAmount}">
               <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${requestScope.data.singleAmount}</span><span class="item-name">单个红包金额</span></div></div>
            </c:if>
            <c:if test="${!empty requestScope.data.randomAmountMini}">
               <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${requestScope.data.randomAmountMini}</span><span class="item-name">最低金额</span></div></div>
            </c:if>
            <c:if test="${!empty requestScope.data.randomAmountMax}">
              <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${requestScope.data.randomAmountMax}</span><span class="item-name">最高金额</span></div></div>
            </c:if>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${requestScope.data.beginTime}-${requestScope.data.endTime}</span><span class="item-name">领取时段</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${requestScope.data.beginDate} 至 ${requestScope.data.endDate}</span><span class="item-name">活动时间</span></div></div>
        </div>
        <p class="fill-list-desc">通过给用户发红包刺激用户消费，最后返现回到商户手上。</p>
    </div>
    <div class="floor-module">
        <c:choose>
            <c:when test="${data.status eq 1}">
                <a class="floor-links links-type2" href="javaScript:void(0)" onclick="viweActivity()">预览</a>
            </c:when>
            <c:otherwise>
                <a class="floor-links links-type2" style="background-color: #CCC" href="javaScript:void(0)">预览</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
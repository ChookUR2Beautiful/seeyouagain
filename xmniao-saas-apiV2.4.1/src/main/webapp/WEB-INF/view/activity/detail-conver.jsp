<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>会员详情</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
    	 
        <div class="detailts-divhead-module">
        	<c:choose>
        		<c:when test="${empty conver.attachTime}">
        			<div class="detailts-divhead-module-wrap">
                <div class="detailts-divhead-wrap-name">此客户是普通消费客户，非本店绑定会员</div>
            </div>
        		</c:when>
        		<c:otherwise>
        			<div class="detailts-divhead-module-wrap">
                <div class="detailts-divhead-wrap-name direc-l">绑定时间:${conver.attachTime}</div>
                <div class="detailts-divhead-wrap-desc direc-r mark-desc">
                    <span class="sub-font">已绑定</span>
                </div>
            </div>
        		</c:otherwise>
        	</c:choose>
        	
            
        </div>
        <c:choose>
        	<c:when test="${empty conver.head}">
        		<c:set var="img" value="${ctx}/imgs/revenue/Binding_Member1@2x.png"/>
        	</c:when>
        	<c:otherwise>
        		<c:set var="img" value="${conver.head}"/>
        	</c:otherwise>
        </c:choose>
        <div class="data-details-module">
            <div class="data-details-content">
                 <div class="data-datails-img"><img src="<c:out value="${img}"/>" alt="#"/></div>
                 <div class="data-datails-text">${conver.usrName}</div>
            </div>
        </div>
        <div class="line-desc-divhead">
            <i class="divhead-line"></i>
            <span class="divhea-line-text line-width-04">详细信息</span>
        </div>
        <div class="fill-list-module" >
            <div class="list-wrap">
                <div class="list-item">
                    <span class="item-input-wrap"><fmt:formatDate value="${conver.firstDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></span>
                    <span class="item-name">首次消费时间</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item">
                    <span class="item-input-wrap"><fmt:formatDate value="${conver.lastDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></span>
                    <span class="item-name">最近消费时间</span>
                </div>
            </div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${conver.count}次</span><span class="item-name">消费次数</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${conver.sum}</span><span class="item-name">累计消费</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${conver.max}</span><span class="item-name">最高消费</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${conver.min}</span><span class="item-name">最低消费</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${conver.avg}</span><span class="item-name">平均消费</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${conver.phone}</span><span class="item-name">会员电话</span></div></div>
        </div>
    </div>
    <div class="floor-module">
	        	<a class="floor-links links-type2" href="tel:${conver.phone}" >拨打客户电话</a>
	   		 </div>	
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</html>
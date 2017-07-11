<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动预览页面</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
	<input type="hidden" value="${type}" id="type"/>
	<input type="hidden" value="${sellerId}" id="sellerId"/>
    <div class="fixed-module">
            <img src="${pageContext.request.contextPath}/api/v1/common/download_material?type=${type }&id=${id}"  width="100%" height="100%">
   </div>
    <div class="floor-module">
    	<!-- 现金抵用券 -->
    
        	 <a class="floor-links" href="${pageContext.request.contextPath}/api/v1/common/download_material?type=${type }&id=${id}">下载宣传物料</a>
     
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
   <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=basePath%>/css/fresh/activitypage.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/fresh/component.css"/>
</head>
<body class="bg-color-01">
    <div class="activity-banner">
        <img src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP %>/${activity.img}" alt="活动广告"/>
    </div>
    <dl class="activity-rule">
        <dt><i class="g-icon icon-bottom-array"></i>活动规则</dt>
        <dd>活动时间<fmt:formatDate value="${activity.beginDate}" type="both" pattern="yyyy.MM.dd HH:mm:ss"/>至<fmt:formatDate value="${activity.endDate}" type="both" pattern="yyyy.MM.dd HH:mm:ss"/></dd>
        <dd>${activity.remark}</dd>
    </dl>
    <div class="activity-list-module">
    <ul class="activity-picimg clearfix">
    	<c:forEach items="${products}" var="product">
	        <li class="picimg-item">
	            <a class="picimg-links" href="javascript:;">
	                <img src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP %>/${product.pic}" alt=""/>
	                <div class="picimg-info-tit"><em>精选</em>${product.pname}</div>
	                <div class="picimg-info-price">
	                    <div class="picimg-maxprice"><span>￥${product.salePrice}+${product.sellIntegral}积分</span></div>
	                    <del class="picimg-del-price">${product.basePrice}</del>
	                    <i class="g-icon icon-addbtn-01"></i>
	                </div>
	            </a>
	        </li>
    	</c:forEach>
    </ul>
    <div class="page-end" style="display: none;">已经到底了</div>
    </div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动</title>
 <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <link rel="stylesheet" href="<%=basePath%>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath%>css/main.css"/><link>
    
     <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
     <script type="text/javascript" src="<%=basePath%>js/rem.js"></script>
    
</head>

<body style="padding-bottom: 2rem;">
    <img src="<%=basePath%>images/activity_bg.png" class="bg-img" />
    <div class="btn-box">
        <a href="javascript:;" class="actionnow">马上行动</a>
    </div>
</body>

<script>
	 $('.actionnow').bind('click',function(){
		 window.location="http://172.16.130.171:9908/goBuyBirdCoinCombo";
	 });
</script>
</html>
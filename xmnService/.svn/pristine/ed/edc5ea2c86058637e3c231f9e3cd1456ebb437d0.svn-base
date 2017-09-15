<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" class="gray">
<head>
    <meta charset="UTF-8">
    <title>食尚V客</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <script type="text/javascript" src="<%=basePath%>js/remee.js"></script>
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
    <link rel="stylesheet" href="<%=basePath%>css/common.css"/><link>

</head>
<body class="card-explain" style="padding-top: 1.2rem;">
    <img src="<%=basePath%>images/img_bg_01.jpg" >
    <img src="<%=basePath%>images/img_bg_02.jpg" >
    <img src="<%=basePath%>images/img_bg_03.jpg" >
    <a href="#" class="btn" id="cardBtn">去领卡</a>
</body>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script>
 var type='${type}';
 $(function(){
	 $("#cardBtn").click(function(){
		 if (type=="2") {
			 window.location.href="http://172.16.130.171:9908/errneckhref";
		 } 
	 })	 
 })


</script>
</html>
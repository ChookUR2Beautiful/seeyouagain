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
    <title>新手福利</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
    <style>
        *{margin: 0;padding: 0;}
        img{
            width: 100%;
            vertical-align: top;
        }
        a{
            display: block;
            width: 100%;
            position: fixed;
            bottom: 0;
            left:0;
        }
    </style>
</head>
<body>
    <img src="<%=basePath%>images/bg.jpg">
    <c:if test="${uid=='null' || uid ==''}">  
    <a href="http://172.16.130.171:9908/applogin"><img src="<%=basePath%>images/btn.jpg"></a>
    </c:if>
</body>
<script>
//alert('${uid}');
</script>
</html>
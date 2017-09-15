<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>寻蜜客引导页</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${basePath }css/swiper.css">
    <style>
    html, body {
        position: relative;
        height: 100%;
    }
    body {
        background: #fff;
        font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
        font-size: 14px;
        color:#000;
        margin: 0;
        padding: 0;
    }
    .swiper-container {
        width: 100%;
        height: auto;
    }

    .swiper-container img{
        width: 100%;
        height: auto;
        vertical-align: middle;
    }
    

    </style>
</head>
<body>
    <div class="swiper-container">
        <img src="${basePath }img/lead_1.png" alt="">
        <img src="${basePath }img/lead_2.png" alt="">
        <img src="${basePath }img/lead_3.png" alt="">
        <img src="${basePath }img/lead_4.png" alt="">
        <img src="${basePath }img/lead_5.png" alt="">
    </div>

    
</body>
<html>
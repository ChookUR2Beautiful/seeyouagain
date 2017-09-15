<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
      <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xnhz-member-160808.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.css"/>
</head>
<body class="leadingpage-body">
    <header class="xnhu-membercommon-header">
        <div class="xnhu-membercommon-header-box">
            <a href="javascript:window.history.go(-1);" class="xnhu-membercommon-header-goback"></a>
            <p class="xnhu-membercommon-header-box-title">了解雏鸟云互助</p>
        </div>
    </header>
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/member/leading_page_1.png"></div>
            <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/member/leading_page_2.png"></div>
            <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/member/leading_page_3.png"></div>
            <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/member/leading_page_4.png"></div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/zepto.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
    <script>
    $(function(){
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || $('body').css('height');
        
        if(parseInt(clientHeight)){
            var cssHeight = parseInt(clientHeight) - 53 - 52;
            console.log(cssHeight);
            $('.swiper-container').css('height',cssHeight+'px');
        }
        
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true
        });
    });
        
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>视频中心</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath %>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath %>css/xmk-lead.css">
 
   <style>
        .xmkmovie-link{
            display: block;
            width: 100%;
            height: 100%;
            text-decoration: none;
        }

    </style>
</head>
<body style="padding-bottom: 50px;background:#eee;">
    <ul class="xmkmovie-listbox">
        <li>
            <a href="<%=basePath %>movieOne" class="xmkmovie-link">
                <div class="xmk-movie-img" style="background-image: url(<%=basePath %>img/movie_1_img.png);"></div>
                <div class="xmk-movie-modal"></div>
                <div class="xmk-movie-playmodal">
                    <img src="<%=basePath %>img/movie_play.png">
                </div>
                <div class="xmk-movie-txt">
                    <p class="xmk-movie-title">课程名称：寻蜜鸟创客计划分享</p>
                    <p class="xmk-movie-teacher">主讲人：潘求辉</p>
                </div>
            </a>
        </li>
        <li>
             <a href="<%=basePath %>movieTwo" class="xmkmovie-link">
                <div class="xmk-movie-img" style="background-image: url(<%=basePath %>img/movie_2_img.png);"></div>
                <div class="xmk-movie-modal"></div>
                <div class="xmk-movie-playmodal">
                    <img src="<%=basePath %>img/movie_play.png">
                </div>
                <div class="xmk-movie-txt">
                    <p class="xmk-movie-title">课程名称：寻蜜鸟商户端产品培训</p>
                    <p class="xmk-movie-teacher">主讲人：李茜</p>
                </div>
            </a>
        </li>
        <li>
             <a href="<%=basePath %>movieThree" class="xmkmovie-link">
                <div class="xmk-movie-img" style="background-image: url(<%=basePath %>img/movie_3_img.png);"></div>
                <div class="xmk-movie-modal"></div>
                <div class="xmk-movie-playmodal">
                    <img src="<%=basePath %>img/movie_play.png">
                </div>
                <div class="xmk-movie-txt">
                    <p class="xmk-movie-title">课程名称：寻蜜鸟商业模式分享</p>
                    <p class="xmk-movie-teacher">主讲人：刘勇 </p>
                </div>
            </a>
        </li>
        
    </ul>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新手指引</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/rem.js"></script>
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
        .video-box{
            position: relative;
        }
        .video{
            position: absolute;
            top: 5.3rem;
            left:0.6rem;
            width:8.59rem;
            height:4.8rem;
            text-align: center;
            border: 3px solid #00d05e;
        }
        .video img{
        	height: 1.47rem;
        	width: 1.47rem;
        	margin-top: 1.5rem;
        }
        .pu_video{
            width:4.19rem;
            height:2.35rem;
            text-align: center;
             border: 2px solid #00d05e;
        }
        .pu_video img{
        	height: 0.96rem;
        	width: 0.96rem;
        	margin-top: 0.7rem;
        }
        .video2{
        	position: absolute;
            top: 11.7rem;
            left:0.6rem;
        }
        .video3{
        	position: absolute;
            top: 11.7rem;
            left:5.07rem;
        }
        .video4{
        	position: absolute;
            top: 15.5rem;
            left:0.6rem;
        }
        .video5{
        	position: absolute;
            top: 15.5rem;
            left:5.07rem;
        }
     </style>
     
     
     
<script>
$(function(){
	$(".video_box").on('click',function(){
		$(this).hide().prev('video').trigger('play').siblings('video').trigger('pause')
		$(this).siblings(".video_box").show()
	})
	$("video").on('click',function(){
		$(this).trigger('pause').next().show()
	})
})
</script>
    
</head>
<body>
     <div class="video-box">
        <img src="<%=basePath%>img/video_bg.png" >
        <video class="video" src="http://1252387534.vod2.myqcloud.com/93289fafvodtransgzp1252387534/8af5042e9031868222991391376/f0.f30.mp4" 
        		poster="<%=basePath%>img/bg_lg.png"></video>
        <div class="video video_box"><img src="<%=basePath%>img/icon_playlg.png"/></div>
        <video class="pu_video video2" src="http://1252387534.vod2.myqcloud.com/93289fafvodtransgzp1252387534/8af507a39031868222991391410/f0.f30.mp4" 
        	poster="<%=basePath%>img/bg_xm.png"></video>
        <div class="pu_video video_box video2"><img src="<%=basePath%>img/icon_playxm.png"/></div>
        <video class="pu_video video3" src="http://1252387534.vod2.myqcloud.com/93289fafvodtransgzp1252387534/8af5086e9031868222991391475/f0.f30.mp4" 
        	poster="<%=basePath%>img/bg_xm.png"></video>
        <div class="pu_video video_box video3"><img src="<%=basePath%>img/icon_playxm.png"/></div>
        <video class="pu_video video4" src="http://1252387534.vod2.myqcloud.com/93289fafvodtransgzp1252387534/8af5086e9031868222991391475/f0.f30.mp4" 
        	poster="<%=basePath%>img/bg_xm.png"></video>
        <div class="pu_video video_box video4"><img src="<%=basePath%>img/icon_playxm.png"/></div>
        <video class="pu_video video5" src="http://1252387534.vod2.myqcloud.com/93289fafvodtransgzp1252387534/8af5086e9031868222991391475/f0.f30.mp4" 
        	poster="<%=basePath%>img/bg_xm.png"></video>
        <div class="pu_video video_box video5"><img src="<%=basePath%>img/icon_playxm.png"/></div>
    </div>
    <img src="<%=basePath%>img/bg_o1.png">
    <img src="<%=basePath%>img/bg_o2.png">
    <img src="<%=basePath%>img/bg_o3.png">
    <img src="<%=basePath%>img/bg_o4.png">
    <img src="<%=basePath%>img/bg_o5.png">
    
</body>
</html>
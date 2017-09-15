<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn" class="gray">
<head>
<meta charset="UTF-8">
<title>寻蜜客商学院</title>
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<script type="text/javascript" src="<%=basePath%>js/rem.js"></script>
<meta name="x5-fullscreen" content="true">
<meta name="full-screen" content="yes">
<link rel="stylesheet" href= "<%=basePath%>css/xmk_college.css"/>
<link rel="stylesheet" href="<%=basePath %>css/normalize.css">
<link rel="stylesheet" href="<%=basePath %>css/xmk-lead.css">
<link rel="stylesheet" href="<%=basePath %>css/showtips.css">
<link>
</head>
<body>
	<ul class="link-list bg-fff">
		<li><a href="<%=basePath%>xmkmovie" id="moviecenter"> 
			<img src="<%=basePath %>img/h_video.png"><label>视频中心</label>
		</a></li>
		<li>
			<a href="javascript:;" id="stulibbtn" > 
				<img src="<%=basePath %>img/h_books.png"><label>学习图书馆</label>
			</a>
		</li>
		<li><a
			href="<%=basePath %>feedback?sessiontoken=${param.sessiontoken}&appversion=${param.appversion}&apiversion=${param.apiversion}&systemversion=${param.systemversion}">
				<img src="<%=basePath %>img/h_view.png"><label>意见反馈</label>
		</a></li>
		<li><a href="<%=basePath%>serviceagreement"> <img
				src="<%=basePath %>img/h_protocol.png"><label>寻蜜客服务协议</label>
		</a></li>
	</ul>
	<div class="ad-box bg-fff">
		<p>首先，恭喜你</p>
		<p>成为一名寻蜜客</p>
		<p>选择了一条快捷创业之路</p>
		<img src="<%=basePath %>img/xmk_img_high.png">
		<p>今天这里的培训课程</p>
		<p>能让你正式开启你的财富之路</p>
		<p>将来，你会通过成长计划</p>
		<p>成为一名优秀的寻蜜客</p>
		<p>拥有你的创客联盟，坐享巨大收益</p>
		<img src="<%=basePath %>img/xmk_passlog.png">
	</div>
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/show.js"></script>
	<script>
	  $(function(){
          var tips = new showtips();

           $('#stulibbtn').bind('click',function(){
              tips.show('学习图书馆暂未开放，敬请期待');
          });


      });
	</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>签约</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/normalize.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/commom.css">
</head>
<body class="perfectinfo">
	<div class="perfectinfo-hrep">

		<a  href="javascript:fileImage.click();" class="uploadimage" style="background-image:url(<%=basePath%>img/hrep.jpg);"> 选择图片
            <input type="file" id="fileImage" name="imageFile" style="display:none;"/> 
            <span id="imgselector"></span>
        </a>


		<!-- <span class="perfectinfo-hrep-img" style="background-image: url(../imgs/hrep.jpg);">点击上传</span> -->
	</div>
	<ul class="perfectinfo-list">
		<form id="xmerForm">
		<li>
			<i class="perfectinfo-name"></i>
			<span>姓名：</span>
			<input type="text" class="perfectinfo-formtext" placeholder="请输入您的姓名" name="username">
		</li>
		<li>
			<i class="perfectinfo-email"></i>
			<span>邮箱：</span>
			<input type="text" class="perfectinfo-formtext" placeholder="请输入您的邮箱" name="email">
		</li>
		<li>
			<i class="perfectinfo-wx"></i>
			<span>微信：</span>
			<input type="text" class="perfectinfo-formtext" placeholder="请输入您的微信" name="wechat">
		</li>
		</form>
	</ul>

	<a href="javascript:submitForm();" class="bottomfixbtn">完成</a>
	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/zxxFile.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/upload.js"></script>
</body>

<script type="text/javascript">
	function submitForm(){
		var data = $("#xmerForm").serialize();
		var url = ${basePath}+"editXmerInfo?id="+${id};
		$ajax({
			url:url,
			type:'post',
			data:data,
			error: function(request) {
            		window.location.href=${basePath}+"error.jsp"
                },
            success: function(data) {
            	if(data != null && data.state==100){
             		window.location.href=${basePath}  		
            	}
            }
		})
	}
</script>
</html>

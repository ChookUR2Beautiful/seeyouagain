<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body>
	<div class="paysuccess-img">
		<img src="<%=basePath %>img/sign_pay_success.png">
	</div>
	<p class="paysuccess-tips">支付成功</p>
	<p class="paysuccess-cc">须知：您的资料将在一个工作日内审核完毕。通过后会以短信的形式通知您saas的账号和密码。</p>
	<p class="paysuccess-cc paysuccess-text-center">感谢您的信任！</p>

	
	<a href="javascript:;" onclick="wx.closeWindow();" style="display:block;width:40%;height:30px;line-height:30px;margin:0 auto;text-align:center;background:#f6ab00;color:#fff;font-size:14px;border-radius:5px;">完成</a>
	</p>
</body>

</html>
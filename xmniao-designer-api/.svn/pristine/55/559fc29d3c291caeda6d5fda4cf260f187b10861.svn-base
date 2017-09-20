<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String info = "遇到错误啦，请返回！";
    if (request.getSession() != null && request.getSession().getAttribute("info") != null) {
        info = request.getSession().getAttribute("info").toString();
    }
%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>错误啦</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<style>
body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
	font-family: Microsoft Yahei;
}

.grap-page {
	background: #f2f2f2;
	padding-top: 40%;
	box-sizing: border-box;
}

.error-img {
	width: 100px;
	height: auto;
	margin: 0 auto;
}

.error-img img {
	width: 100%;
	height: auto;
}

.error-normaltext {
	margin: 0px;
	color: #999;
	width: 100%;
	text-align: center;
	font-size: 20px;
}

.error-smtext {
	margin: 5px 0;
	color: #999;
	width: 100%;
	text-align: center;
	font-size: 16px;
}

@media screen and (min-width :720px) {
	.grap-page {
		background: #f2f2f2;
		padding-top: 20%;
		box-sizing: border-box;
	}
}
</style>
</head>
<body class="grap-page">
	<div class="error-img">
		<img src="${ctx}/imgs/error_img.jpg">
	</div>
	<p class="error-normaltext">呀！不小心就错误啦~</p>
	<p class="error-smtext"><%=info%></p>
</body>
</html>
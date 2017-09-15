<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>发生错误了</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <style>
        body{
            padding:0;
            margin:0;
            font-family: Microsoft Yahei;
        }
        .error-bg{
            margin-top:50px;
            width: 100%;
            height: 100px;
            text-align: center;
        }

        .error-bg img{
            width: auto;
            height: 100%;
        }

        h4{
            color:#333;
            text-align: center;
        }

        p{
            margin:0;
            text-align: center;
            color:#999;
            font-size: 14px;
            line-height: 1.5em;
        }
    </style>
</head>

<body>
    <div class="error-bg">
        <img src="<%=basePath%>/img/error_logo.jpg">
    </div>

    <h4>${data.info }</h4>
  	<!--   <p>哎呀！发生了不可知的错误</p>
    <p>系统管理员正在抢修，</p>
    <p>请点击左上角返回上级菜单，稍后再试~</p> -->

</body>
</html>
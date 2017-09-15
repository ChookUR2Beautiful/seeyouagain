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
    <title>学习图书馆</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath %>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath %>css/xmk-lead.css">
 
</head>
<body style="padding-bottom: 50px;">
    <ul class="booklib-listbox">
        <li>
            <a href="javascript:;" class="booklib-link" style="background-image:url(<%=basePath %>img/book.jpg);"></a>
        </li>
        <li>
            <a href="javascript:;" class="booklib-link" style="background-image:url(<%=basePath %>img/book.jpg);"></a>
        </li>
        <li>
            <a href="javascript:;" class="booklib-link" style="background-image:url(<%=basePath %>img/book.jpg);"></a>
        </li>

        <li>
            <a href="javascript:;" class="booklib-link" style="background-image:url(<%=basePath %>img/book.jpg);"></a>
        </li>
        <li>
            <a href="javascript:;" class="booklib-link" style="background-image:url(<%=basePath %>img/book.jpg);"></a>
        </li>
        <li>
            <a href="javascript:;" class="booklib-link" style="background-image:url(<%=basePath %>img/book.jpg);"></a>
        </li>
    </ul>
</body>
</html>
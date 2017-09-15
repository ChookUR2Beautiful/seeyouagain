<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn" style="font-size: 37.5px;">
<head>
    <meta charset="UTF-8">
    <title>邀请好友加入</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
   
    <script type="text/javascript" src="<%=basePath%>js/rem.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/common.css"/><link>
    <link rel="stylesheet" href="<%=basePath%>css/visitor.css"/><link>
   
    <style>
        img{
            width:100%;;
        }
    </style>
   
</head>
<body id="SAASIndex">
    <img src="<%=basePath %>img/SAAS_bg_01.jpg" >
    <img src="<%=basePath %>img/SAAS_bg_02.jpg" >
    <img src="<%=basePath %>img/SAAS_bg_03.jpg" >
    <div class="btn-box">
       <a href="javascript:toXmer(${param.uid});" class="share-link">机会在这里</a>
    </div>
</body>

<script type="text/javascript">
	function toXmer(uid){
		window.location.href="${basePath}validephone?uid="+uid;
	}
</script>
</html>


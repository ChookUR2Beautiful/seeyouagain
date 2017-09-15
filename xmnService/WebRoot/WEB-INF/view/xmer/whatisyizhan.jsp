<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <meta charset="UTF-8">
	<title>什么是驿站</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <style>
        body{
            font-family: Microsoft Yahei;
            margin:0px;
            padding:0;
        }

        .imgbox{
            width: 100%;
            height: 140px;
            text-align: center;
            margin-top: 30px;margin-bottom: 20px;
        }

        .imgbox img{
            height: 100%;
            width: auto;
        }
        
        .whisyz-title{

            width: 100%;
            text-align: center;
            height: 30px;
            line-height: 30px;
            color:#f6ab00;
            margin:0px 0;
            font-size: 18px;
        }

        .whisyz-cc{
            width: 100%;
            padding:0 15px;
            box-sizing: border-box;
            text-indent: 2em;
            margin:0;
            color:#999;
            font-size: 16px;
            line-height: 1.5em;
        }

        .whisyz-clacenter{
            text-align: center;
            padding: 0;
            text-indent: 0;
            margin-top:18px;
        }
    </style>
</head>

<body>
    <div class="imgbox">
        <img src="<%=path %>/img/whatisyizhan_ima.png" >
    </div>

    <!-- <p class="whisyz-title">收获丰富的额外收入</p> -->
    <p class="whisyz-cc">“驿站”是为同城寻蜜客提供的线下交流分享、
培训咨询、互助共赢资源平台；寻蜜客在此获得人
脉资源、签约技巧、个人收益等多重提升。由区域
代理商运营，提供线下物料及帮助，定期组织线下
培训、聚会等，寻蜜客可在此报名参与。</p>

<p class="whisyz-cc whisyz-clacenter">（具体服务内容请及时关注附近驿站）</p>
</body>
</html>
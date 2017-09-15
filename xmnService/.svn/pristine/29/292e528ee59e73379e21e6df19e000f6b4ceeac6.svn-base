<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<head>
    <meta charset="UTF-8">
    <title>岗位信息</title>
    <meta name="keywords" content="">   <!--关键字-->
    <meta name="description" itemprop="description" content=""> <!--网站描述-->
    
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath%>css/indexbss.css">
</head>
<body style="background:#eaeeef;">
    <div class="business-box">
        <div class="business-innerbox">
            <div class="business-innerbox-frame">
                <p class="business-innerbox-workname">${data.response.stationname}</p>
                <p class="business-innerbox-salary">${data.response.salary}元/月</p>
            </div>
            <div class="jobseekers-info resetjobseekersinfo">
                <p class="jobseekers-info-yearsold">${data.response.agemin}~${data.response.agemax}岁</p>
                <span class="jobseekers-info-eduimg"></span>
                <p class="jobseekers-info-normalinfo">${data.response.degrees}</p>
                <span class="jobseekers-info-workyear"></span>
                <p class="jobseekers-info-normalinfo">${data.response.experie }年</p>
                <span class="jobseekers-info-workplace"></span>
                <p class="jobseekers-info-normalinfo">${data.response.city }</p>
            </div>
            <ul class="jobseekers-intro-label resetjobseekerslabel">
            	<c:forEach items="${data.response.workwelfare }" var="works">
	                <li>${works.tagname }</li>
            	</c:forEach>
            </ul>
        </div>
    </div>

    <div class="business-shops-info">
        <div class="business-shops-img" style="background-image:url(${data.response.sellerpic});"></div>
        <p class="business-shops-name">${data.response.sellername }</p>
    </div>
    
    <div class="business-position-info">
        <div class="business-position-innercontent">
            <p class="business-position-rename">${data.response.contact }</p>
            <p class="business-position-rename">人数规模：${data.response.scale }人……</p>
            <p class="business-position-rename"><span class="business-position-place"></span>${data.response.scity}${data.response.sarea }${data.response.saddress}</p>

            <p class="business-position-otherp">其他热招岗位</p>
        

            <ul class="jobseekers-intro-label resetjobseekerslabel positionresetjobseekers">
            	<c:forEach items="${data.response.jobs}"  var="jobs">
            		<li>${jobs.tagname}</li>
            	</c:forEach>
            </ul>

        </div>
    </div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sessiontoken = request.getParameter("sessiontoken");
String appversion = request.getParameter("appversion");
String apiversion = request.getParameter("apiversion");
String systemversion = request.getParameter("systemversion");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>意见反馈</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath %>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath %>css/xmk-lead.css">
    <link rel="stylesheet" href="<%=basePath %>css/showtips.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/show.js"></script>
 
</head>
<body style="padding-bottom: 50px;">
    <div class="feedback-img">
        <img src="<%=basePath %>img/feedback.png">
    </div>

    <div class="feedback-txtarea">
        <textarea name="feedbacktxt" placeholder="请在此输入你的意见和建议" class="feedbacktxt"  id="feedbacktxt" ></textarea>
    </div>
    <div class="feedback-txtarea">
        <input type="submit" class="feedback-btn" value="提   交" onclick="subFeed();">
    </div>


<script type="text/javascript">
	function subFeed(){
		var tips = new showtips();
			var text = document.getElementById('feedbacktxt').value;
			var sessiontoken = '<%=sessiontoken%>';
			var appversion = '<%=appversion%>';
			var apiversion = '<%=apiversion%>';
			var systemversion ='<%=systemversion%>';
			$.ajax({
	             type: "post",
	             url:"<%=basePath%>feedBackInfo",
	             data: {'content':text,'sessiontoken':sessiontoken,'appversion':appversion,'apiversion':apiversion,'systemversion':systemversion},
	             dataType: "json",
	             success: function(data){
	             	if(data != null && data.state ==100){
	             		tips.show("提交成功");
	             		setTimeout("location.reload()",3000);
	             	}else{
	             		tips.show("提交失败");
	             		setTimeout("location.reload()",3000);
	             	}
	             },
	         	error: function (err) { 
	         		tips.show("提交异常");
	         		setTimeout("location.reload()",3000);
                 }
	         });
	}
</script>
</html>
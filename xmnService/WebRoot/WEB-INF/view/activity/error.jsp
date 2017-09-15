<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>错误页面</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <style type="text/css">
        body{
            font-family: Microsoft Yahei;
        }

        body,p,img,div{margin:0;padding:0;}
        .img{margin:50px auto 20px;text-align: center;}
        .img img{width:40px;height: auto;}
        .paragraph{text-align: center;}
        .pp-text{text-align: center;height:30px;line-height: 30px;}
        .err-btn{width: 100%;text-align: center;}
        .err-btn a{display: block;width: 30%;text-align: center;height: 40px;line-height: 40px;margin:0 auto;text-decoration: none;color:#fff;background: #49b4ff;border-radius: 20px;margin-top:50px;}
        
    </style>
</head>
<body>
    <div class="img">
        <img src="<%=basePath%>img/error_logo.jpg">
    </div>
    <p class="paragraph">${info}</p>
    <br><br>
  
</body>

</html>
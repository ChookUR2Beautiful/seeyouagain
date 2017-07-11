<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/main.css"/><link >
</head>
<body>
    <img class="logo" src="${ctx}/imgs/login/logo@2x.png" alt="logo" />
    <form action="${ctx}/api/v1/common/submitform"  method="post">
	    <div class="login-box">
	        <div class="account-box"><label class="account"></label><input name="account" type="number" placeholder="请输入绑定的手机号码" /></div>
	        <div class="account-box"><label class="password"></label><input name="password" type="text" placeholder="请输入密码" /></div>
	    </div>
	         <a href="javascript:void(0);" onclick="submitform();" class="login-btn">登录</a>
    </form>    
<!--     <p class="remember">记住账号和密码</p> -->
    
    
   
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script>
        $(function () {
            $(".remember").on("click",function () {
               $(this).toggleClass("check");
            });
        })
        function submitform(){
	      if (!$("input[name=account]").val()) {
				alert('用户名不能为空！');
				return ;
			} else if (!$("input[name=password]").val()) {
				alert('密码不能为空！');
				return ;
			}
			$("form").submit();
		}
    </script>
</body>
</html>
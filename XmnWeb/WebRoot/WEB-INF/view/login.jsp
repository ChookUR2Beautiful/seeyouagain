<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>寻蜜鸟业务管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="resources/web/css/base.css">
    <link rel="stylesheet" type="text/css" href="resources/web/css/login.css"> 
  </head>
  
  <body class="login-html">
  	<div class="wrap">
	    <div class="logo fms">
	    	<div class="img">
	       		<img src="resources/web/images/logo.png" alt="寻蜜鸟业务管理系统" />
	        </div>
	        <h1>业务管理系统</h1>
	    </div>
	    <div class="login">
	        <div class="box radius-6 fms">
	        	<form action="ulogin" method="post" autocomplete="off" >
		            <label class="radius-3"><span>用户名：</span><input type="text" name="uname"/></label>
		            <label class="radius-3"><span>密 码：</span><input type="password" name="upassword"/></label>
		            <label class="code radius-3"><span>验证码：</span><input type="text" name="ucaptcha"/><a href="javascript:;"><img id="Kaptcha" src="Kaptcha.jpg" alt="看不清？换一张！" title="看不清？换一张！" ></a></label>
		            <div class="submit"><input class="radius-3" type="submit" value="登 录" /></div>
		            <div class="forget"><input type="checkbox" value="" checked><span>记住密码</span><a href="">忘记密码？</a><div class="clear"></div></div>
		             <div id="msg" class="failure"></div>
	            </form>
	        </div>
	    </div>
	    <div class="clear"></div>
	</div>
  </body>
 <script src="<%=path%>/resources/zui/lib/jquery/jquery.js"></script>
 <script src="<%=path%>/ux/js/jquery.validate.js"></script>
 <script type="text/javascript" src="<%=path%>/js/system_settings/login.js"></script>
</html>

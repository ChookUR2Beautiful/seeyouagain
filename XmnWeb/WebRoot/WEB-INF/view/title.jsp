<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
		body { margin:0; font-size:12px; font-family:Verdana, Geneva, sans-serif; background:#EEE;}
		h1,h2,h3 { display:block; margin:0; padding:0; font-size:24px; line-height:24px; font-weight:normal;}
		img { border:0;}
		dl,dt,dd,ul,li { display: block; margin: 0; padding: 0; list-style-type: none;}
		a { text-decoration: none;}
		*:focus { outline: none; }
		.clear { clear:both;}
		.crumbs { position:fixed;height:48px; width: 100%;line-height:48px; text-indent:20px; overflow:hidden; color:#A0A0A0; background:#EEEEEE;}
		.text-muted { color: #808080 !important;}
		/* .crumbs span { color:#000;} */
		
	</style>
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
  </head>
  
  <body>
  	<div class="row crumbs">
  	<div class="col-md-10 col-sm-11" id="title">当前位置 <span name="content">&gt; 首页</span></div>
  	<div class="col-md-2 col-sm-1" id="backButtonDiv"> </div>
  </div>
  </body>
</html>

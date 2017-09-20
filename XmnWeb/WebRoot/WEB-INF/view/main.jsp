<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>业务管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <frameset rows="86,*" cols="*" frameborder="no" border="0" framespacing="0"> 
	 <frame name="top" src="top.jhtml" noresize="noresize"/> 
	 <frameset cols="220px,*">
	 	<frame name="left" src="left.jhtml" scrolling="no"/>
	    <frameset rows="40px,*">
	    	<frame name="title" src="title.jhtml" scrolling="no" />
	        <frame name="right" src="right.jhtml" />
	    </frameset>
	 </frameset> 
	</frameset><noframes></noframes>   
</html>

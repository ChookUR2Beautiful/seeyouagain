<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  
  <body>
  	<div">
  		<!-- id -- 姓名 -- 年龄<br>
  		<c:forEach var="person" items="${pslt }" >
  			 ${person._id }  -- ${person.name }  -- ${person.age }<br>
  		</c:forEach> -->
  		
  		helloWorld!!!
  	</div>
  </body>
</html>

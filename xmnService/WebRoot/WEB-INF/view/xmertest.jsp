<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寻蜜客压力测试页面</title>
	<style type="text/css">
		body,div,p{
			margin:0;
			padding:0;
			font-family:myFirstFont;
		}
		#p_text{
			margin:0,auto;
			text-align: center;
			font-size:24pt;
			text-shadow: 3px 2px 3px #380000;
		}
		#div_img{
		width:1280px;
		height:500px;
		margin:auto;
		background:url("<%=basePath%>images/success.jpg") repeat-x;
		border-radius:25px;
		-moz-border-radius:25px;
		}
		#success{
   			width: 226px;
   			 padding-left: 587px;
   			 padding-top: 187px;
	</style>
</head>
<body>
<p id="p_text">测试结果显示：</p>
<div id="div_img">
		<c:choose>
		<c:when test="${data.state == 100}">
			<img id="success" alt="" src="<%=basePath %>images/success.jpg" >
		</c:when>
		<c:otherwise>
			<img id="success" alt="" src="<%=basePath %>images/fail.jpg" class="success">
		</c:otherwise>
		</c:choose>
</div>
<p id="p_text">${data.info}</p>

</body>
</html>
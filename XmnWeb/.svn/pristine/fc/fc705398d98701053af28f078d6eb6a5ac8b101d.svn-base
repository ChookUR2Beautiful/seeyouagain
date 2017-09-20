<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

	
</head>

<body>
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<form id="examineinfoForm" style=" width :430px;overflow-y:auto; ">
		<input type="hidden"   id="status" name="status" value="2">
		<input type="hidden"   id="ids" name="ids" value="${param.ids}">
		<!-- 编辑页面类型 1：商家列表页面；2：商家待审核页面；3：分店页面 -->
		<input type = "hidden" id="editType" value="${param.editType}">
		<!-- 用于返回分店列表信息 -->
		<input type = "hidden" id="fartherSellerId" value="${param.fartherSellerId}">
		<input type = "hidden"  name="updateStatusToken" value="${param.updateStatusToken}">
		<table width="100%">
			<tbody>
				<tr>									
					<th style="width:90px;"><h5>&nbsp;&nbsp;未通过原因:</h5></th>
					<th colspan="1">
						<textarea name="examineinfo"  disabled="disabled" rows="10" class="form-control" placeholder="未通过原因">${seller.examineinfo}</textarea>
					</th>	
				</tr>

	 			</tbody>
	 		</table>
	 </form>
</body>
</html>

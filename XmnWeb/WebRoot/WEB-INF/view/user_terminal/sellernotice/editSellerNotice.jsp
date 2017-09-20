<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
</head>

<body>
	<form class="form-horizontal" role="form" id="editAdvertisingForm">
		<input type="hidden"  name="id" value="${tSellerNotice.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden" name="type" value="5">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:110px;"><h5>&nbsp;&nbsp;商家须知：</h5></th>	
					<th colspan="2">				
					<textarea  name="remark" cols="20" rows="8" class="form-control">${tSellerNotice.remark}</textarea>
					</th>
				</tr>
				<tr>
					<th style="width:110px;"><h5>&nbsp;&nbsp;是否有效：</h5></th>	
					<th colspan="2">
								<select name="STATUS" class="form-control" style="width:80%">
									<option value="">--请选择--</option>
									<option value="1"  <c:if test="${tSellerNotice.STATUS==1}">selected = "selected" </c:if>>是</option>
									<option value="0"  <c:if test="${tSellerNotice.STATUS==0}">selected = "selected" </c:if>>否</option>
								</select>
					</th>
				</tr>
				<tr height="10px"></tr>
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script src="<%=path%>/js/user_terminal/sellernotice/addOrUpdateSellerNotice.js"></script>
</body>
</html>

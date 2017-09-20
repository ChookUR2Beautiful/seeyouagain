<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>编辑报名信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty vstarEnroll}">
			<input type="hidden" name="id" value="${vstarEnroll.id}">
		</c:if>
		<input type="hidden" name="status" value="${vstarEnroll.status }">
		<input type="hidden" id="confining" name="confining" value="${vstarEnroll.confining }">
		
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-7" >
				<p id="title">是否拒绝申请</p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label" id="reason">拒绝原因：<span style="color:red;">*</span></label>
			<input type="hidden" name="refusedType" id="refusedType">
			<div class="col-md-7">
				<input name="refusedTypes"  value="1" type="checkbox"  ><span style="font-size: 12px;">图片违规</span>
				<input name="refusedTypes"  value="2" type="checkbox"  ><span style="font-size: 12px;">姓名文字带有限制内容</span>
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-warning">
					 &nbsp;&nbsp;是&nbsp;&nbsp;
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					 &nbsp;&nbsp;否&nbsp;&nbsp;
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/js/vstar/enrollEdit.js?v=1.0.11"></script>
</body>
</html>

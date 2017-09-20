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

<title>添加主播</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty anchor}">
			<input type="hidden" name="id" value="${anchor.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-3 control-label">真实姓名：</label>
			<div class="col-md-7">
				${anchor.name}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">性别：</label>
			<div class="col-md-7">
				${anchor.sex==1?"男":"女"}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">出生日期：</label>
			<div class="col-md-7">
				${anchor.birthday}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">身份证号：</label>
			<div class="col-md-7">
				${anchor.idcard}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主播手机号：</label>
			<div class="col-md-7">
				${anchor.phone}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">微信号：</label>
			<div class="col-md-7">
				${anchor.weixin}
			</div>
		</div>
	</form>
</body>
</html>

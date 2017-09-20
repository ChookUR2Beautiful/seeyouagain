<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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

<title>添加通告</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty liveSalary}">
			<input type="hidden" name="id" value="${liveSalary.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">主播信息：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<img style="width:50px;height:50px;float:left;" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>/${liveSalary.avatar}"/><div>${liveSalary.name}(${liveSalary.levelName})</div><div>${liveSalary.phone}</div>
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">当前计算工资：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<font>${liveSalary.baseSalary}</font>
			</div>
		</div>
		
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">修改后工资：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="afterSalary" value="${liveSalary.afterSalary}">
			</div>
		</div>
		<div class="form-group" i>
			<label class="col-md-4 control-label"></label>
			<div class="col-md-7">
				<input type="checkbox" style="width:20px" ${liveSalary.isTaxes==1?'checked':''} class="form-control" name="isTaxes" id="isTaxes">是否已扣税
			</div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/live_anchor/liveSalaryEdit.js"></script>
</body>
</html>

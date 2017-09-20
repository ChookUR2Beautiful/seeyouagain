<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<title>添加广播</title>

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
		<c:if test="${!empty announcement}">
			<input type="hidden" name="id" value="${announcement.id}">
		</c:if>
		
		<div class="form-group">
			<label class="col-md-4 control-label">公告内容：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<textarea rows="" cols="" class="form-control" name="content" id="content">${announcement.content}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">有效状态：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="status" value="0" type="radio" ${announcement.status==0?"checked":""} ><span style="font-size: 12px;">无效</span>
				<input name="status" value="1" type="radio" ${announcement.status==1?"checked":""} ><span style="font-size: 12px;">有效</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">应用类型：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" name="app" id="app">
					<option value="">--请选择--</option>
					<option value="1" ${announcement.app==1?"selected":""}>商户端</option>
					<option value="2" ${announcement.app==2?"selected":""}>用户端</option>
					<option></option>
				</select>
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
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/dataDictionary/announcementEdit.js"></script>
</body>
</html>

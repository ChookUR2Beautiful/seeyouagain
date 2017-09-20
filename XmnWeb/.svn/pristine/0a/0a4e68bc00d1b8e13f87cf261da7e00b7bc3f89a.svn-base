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
		<c:if test="${!empty broadcast}">
			<input type="hidden" name="id" value="${broadcast.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">广播设置：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="assignRoom" value="0" type="radio" ${broadcast.assignRoom==0?"checked":""} ><span style="font-size: 12px;">全房间</span>
				<input name="assignRoom" value="1" type="radio" ${broadcast.assignRoom==1?"checked":""} ><span style="font-size: 12px;">指定房间</span>
			</div>
		</div>
		<div class="form-group" id="anchorDiv">
			<label class="col-md-4 control-label">主播昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="recordId" name="recordId"
					initValue="${broadcast.recordId}" style="width:100%;"></select> <input
					type="hidden" class="form-control" id="nickname" name="nickname"
					value="${broadcast.nickname}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">播放次数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="playAmount" id="playAmount"
					value="${broadcast.playAmount}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">广播内容：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<textarea rows="" cols="" class="form-control" name="content" id="content">${broadcast.content}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">立即发送：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="immediate" value="0" type="radio" ${broadcast.immediate==0?"checked":""} ><span style="font-size: 12px;">否</span>
				<input name="immediate" value="1" type="radio" ${broadcast.immediate==1?"checked":""} ><span style="font-size: 12px;">是</span>
			</div>
		</div>
		<div class="form-group" id="sendTimeDiv">
			<label class="col-md-4 control-label">发送时间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control form_datetime" name="sendTime" id="sendTime" value="<fmt:formatDate value="${broadcast.sendTime}" pattern="yyyy-MM-dd HH:mm"/>"
				>
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
	<script src="<%=path%>/js/live_anchor/broadcastEdit.js"></script>
</body>
</html>

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
		<c:if test="${!empty anchorVideo}">
			<input type="hidden" name="id" value="${anchorVideo.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">标题：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="title" value="${anchorVideo.title}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">选择主播：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="anchorId" name="anchorId"
					initValue="${anchorVideo.anchorId}" style="width:100%;"></select> <input
					type="hidden" class="form-control" id="anchorName" name="anchorName"
					value="${anchorVideo.anchorName}">
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">上传封面：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="coverUrl" id="coverUrl"
					value="${anchorVideo.coverUrl}">
					<div id="coverUrlDiv"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">视频地址：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="videoUrl" id="videoUrl"
					value="${anchorVideo.videoUrl}">
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">视频排序：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="sort" value="${anchorVideo.sort}">
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">观看人数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="number" min="0" class="form-control" name="viewCount" value="${anchorVideo.viewCount}">
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
	<script src="<%=path%>/js/live_anchor/anchorVideoEdit.js"></script>
</body>
</html>

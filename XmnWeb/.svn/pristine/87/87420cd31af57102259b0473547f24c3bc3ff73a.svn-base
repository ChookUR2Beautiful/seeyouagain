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
		<%-- <input type="hidden" name="id" value="${id}"> --%>
		<c:if test="${!empty image}">
			<input type="hidden" name="id" value="${image.id}">
		</c:if>
		<div class="form-group" id="imageTypeDiv">
			<%-- <input type="hidden" name="id" value="${image.imageType}"> --%><label class="col-md-4 control-label">页面图：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="imageType" name="imageType"
					initValue="${image.stringPageImage}" style="width:100%;" >
					<option value="">--请选择--</option>
					<option value="1" ${image.imageType==1?'selected="selected"':''} >经营页面入口图</option>
					<option value="2" ${image.imageType==2?'selected="selected"':''}>开启直播banner图</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">链接：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<textarea rows="5" cols="38" name="mediaUrl">${image.mediaUrl}</textarea>
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">页面配图：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="imageUrl" id="pageImage"
					value="${image.imageUrl}">
					<div id="pageImageDiv"></div>
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
	<script src="<%=path%>/js/live_anchor/anchorStartImageEdit.js?v=1.0.11"></script>
</body>
</html>

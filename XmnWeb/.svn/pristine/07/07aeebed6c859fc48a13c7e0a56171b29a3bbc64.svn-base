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
		<c:if test="${!empty materialTagInfo}">
			<input type="hidden" name="id" value="${materialTagInfo.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">标签名称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="name" id="name"
					value="${materialTagInfo.name}">
			</div>
		</div>
		
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">标签图片：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="picUrl" id="picUrl"
					value="${materialTagInfo.picUrl}">
					<div id="picUrlImg"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">是否推荐：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="isRecommend" value="001" type="radio" ${materialTagInfo.isRecommend==001?"checked":""} ><span style="font-size: 12px;">是</span>
				<input name="isRecommend" value="002" type="radio" ${materialTagInfo.isRecommend==002?"checked":""} ><span style="font-size: 12px;">否</span>
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
	<script src="<%=path%>/js/cloud_design/materialTagEdit.js?v=1.0.1"></script>
</body>
</html>

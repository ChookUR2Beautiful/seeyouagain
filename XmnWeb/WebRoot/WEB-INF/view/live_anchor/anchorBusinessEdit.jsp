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

<title>完善主播信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<!-- 主播会员ID -->
		<input type="hidden" id="uid" name="uid" value="${anchor.uid}">
		
		<c:if test="${!empty anchor}">
			<input type="hidden" name="id" value="${anchor.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-3 control-label">主播昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nickname" id="nickname" readonly="readonly"
					value="${anchor.nickname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">粉丝数量：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="concernedNums" id="concernedNums"
					value="${anchor.concernedNums}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主播身高：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="height" id="height" placeholder="请填写身高(cm)，如：175"
					value="${anchor.heightStr}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主播体重：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="weight" id="weight" placeholder="请填写体重(kg)，如：45"
					value="${anchor.weightStr}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主播三围：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" id="threeDimensional" name="threeDimensional"  placeholder="请填写三围，如：86/64/89"
					value="${anchor.threeDimensional}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label">个人说明：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<textarea class="form-control" rows="2"
					name="selfComment" style="padding:6px;height:50px;">${anchor.selfComment}</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="ensure">
					<span class="icon-ok" ></span> 保 存
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
	<script src="<%=path%>/js/live_anchor/anchorBusinessEdit.js?v=1.0.4"></script>
</body>
</html>

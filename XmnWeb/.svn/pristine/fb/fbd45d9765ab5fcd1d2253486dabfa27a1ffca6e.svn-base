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

<title>奖励设置</title>

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
		<input  type="hidden" name="rewardConfToken" value="${rewardConfToken}">
		<c:if test="${!empty rewardConf}">
			<input type="hidden" name="id" value="${rewardConf.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">选手试播(分钟)：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="pilotTime" id="pilotTime"
					value="${rewardConf.pilotTime}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">推荐人获得(金币)：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="rewardCoin" id="rewardCoin"
					value="${rewardConf.rewardCoin}">
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="submitBtn">
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
	<script src="<%=path%>/js/vstar/rewardConfEdit.js?v=1.0.1"></script>
</body>
</html>

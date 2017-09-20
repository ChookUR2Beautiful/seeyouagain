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

<title>新时尚大赛报名审核设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>
<body>
	<form id="editForm" role="form" class="form-horizontal">
		
		<c:if test="${!empty vstarSettingInfo}">
			<input type="hidden" name="id" value="${vstarSettingInfo.id}">
		</c:if>
		
		<div class="form-group">
			<label class="col-md-5 control-label">报名自动通过审核：<span style="color:red;">*</span></label>
			<div class="col-md-5">
				<input name="autoPassFirst" value="1" type="radio" ${vstarSettingInfo.autoPassFirst==1?"checked":""} ><span style="font-size: 12px;">是</span>
				<input name="autoPassFirst" value="0" type="radio" ${vstarSettingInfo.autoPassFirst==0?"checked":""} ><span style="font-size: 12px;">否</span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-5 control-label">复赛实名制通过后自动通过审核：<span style="color:red;">*</span></label>
			<div class="col-md-5">
				<input name="autoPassSecond" value="1" type="radio" ${vstarSettingInfo.autoPassSecond==1?"checked":""} ><span style="font-size: 12px;">是</span>
				<input name="autoPassSecond" value="0" type="radio" ${vstarSettingInfo.autoPassSecond==0?"checked":""} ><span style="font-size: 12px;">否</span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label">报名时段：<span style="color:red;">*</span></label>
			<div class="col-md-3">
				<input type="text" class="form-control form-datetime" id="enrollStartTime" name="enrollStartTime"  placeholder="开始时间" readonly="readonly"
					value="${vstarSettingInfo.enrollStartTimeStr}">
			</div>
			<div class="col-md-1">至</div>
			<div class="col-md-3">
				<input type="text" class="form-control form-datetime" id="enrollEndTime" name="enrollEndTime"  placeholder="结束时间" readonly="readonly"
					value="${vstarSettingInfo.enrollEndTimeStr}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label">复赛时段：<span style="color:red;">*</span></label>
			<div class="col-md-3">
				<input type="text" class="form-control form-datetime" id="contestStartTime" name="contestStartTime"  placeholder="开始时间" readonly="readonly"
					value="${vstarSettingInfo.contestStartTimeStr}">
			</div>
			<div class="col-md-1">至</div>
			<div class="col-md-3">
				<input type="text" class="form-control form-datetime" id="contestEndTime" name="contestEndTime"  placeholder="结束时间" readonly="readonly"
					value="${vstarSettingInfo.contestEndTimeStr}">
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-md-3 control-label">试播时段：<span style="color:red;">*</span></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="liveStartTime" name="liveStartTime"  placeholder="开始时间" readonly="readonly"
					value="${vstarSettingInfo.liveStartTime}">
			</div>
			<div class="col-md-1">至</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="liveEndTime" name="liveEndTime"  placeholder="结束时间" readonly="readonly"
					value="${vstarSettingInfo.liveEndTime}">
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-md-2 control-label"></label>
			<div class="col-md-8">
				超过比赛时段后，前端需要自动关闭报名和提交审核按钮
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
	<script src="<%=path%>/js/vstar/auditSetEdit.js?v=1.0.3"></script>
</body>
</html>

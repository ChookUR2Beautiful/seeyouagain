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

<title>发放体验卡</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<form id="editFrom" role="form" class="form-horizontal">
		
		<div class="form-group">
			<label class="col-md-4 control-label">会员手机号：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="uid" name="uid"  style="width:100%;"  >
				</select> 
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">会员昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nname" id="nname" readonly="readonly">
				<input type="hidden" class="form-control" name="phone" id="phone" >
			</div>
		</div>	
				
		<div class="form-group">
			<label class="col-md-4 control-label">体验次数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nums">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">有效天数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="days">
			</div>
		</div>		
		
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="submitBtn" >
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
	<script src="<%=path%>/js/live_anchor/experienceofficer/addExperienceofficerUser.js"></script>
</body>
</html>

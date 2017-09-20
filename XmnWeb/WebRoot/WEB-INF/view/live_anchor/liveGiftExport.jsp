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

<title>导出直播送礼记录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		<div class="form-group">
			<label class="col-md-3 control-label">打赏时间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" name="startTime" value="" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" style="width:46%;float:left">
				<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
				<input type="text" name="endTime" value="" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" style="width:46%;float:left">
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="export">
					<span class="icon-download-alt" ></span> 导 出
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/live_anchor/liveGiftExport.js?v=1.0.3"></script>
</body>
</html>

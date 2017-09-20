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

<title>批量修改主播分成</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		
		<input type="hidden" id="ids" name="ids" value="${ids }">
		<input type="hidden" id="uids" name="uids" value="${uids }">
		<input type="hidden" id="fansEditToken" name="fansEditToken" value="${fansEditToken }">
		
		<div class="form-group">
			<label class="col-md-3 control-label">机器人粉丝最小值：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="fansMin" id="fansMin" placeholder="机器人粉丝最小值"
					value="">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label">机器人粉丝最大值：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="fansMax" id="fansMax" placeholder="机器人粉丝最大值"
					value="">
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
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/live_anchor/anchorFansEdit.js?v=1.0.3"></script>
</body>
</html>

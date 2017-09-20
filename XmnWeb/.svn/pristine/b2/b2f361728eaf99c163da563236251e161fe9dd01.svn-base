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

<title>添加机器人</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/rangeSlider/css/ion.rangeSlider.css" rel="stylesheet">
<link href="<%=path%>/resources/rangeSlider/css/ion.rangeSlider.skinHTML5.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		
		<div class="form-group">
			<label class="col-md-3 control-label">生成数量：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="addNum"
					value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">关注数区间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input id="concernLen" name="concernLen" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">粉丝区间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input id="fansLen"  name="fansLen"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">等级区间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input id="rankNoLen" name="rankNoLen" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">直播动态区间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input id="conditionLen" name="conditionLen" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">去过的店铺区间：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input id="storeLen" name="storeLen" />
			</div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="ensure">
					<span class="icon-ok" ></span> 确 定
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
	<script src="<%=path%>/resources/rangeSlider/js/ion.rangeSlider.min.js"></script>
	<script src="<%=path%>/js/fresh/robotAdd.js?v=1.0.3"></script>
</body>
</html>

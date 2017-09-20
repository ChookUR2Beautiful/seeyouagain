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
		<c:if test="${!empty liveGiftBagSetInfo}">
			<input type="hidden" name="id" value="${liveGiftBagSetInfo.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">礼物名称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="giftName" id="giftName" readonly="readonly"
					value="${liveGiftBagSetInfo.giftName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">礼物价格：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="giftPrice" id="giftPrice" readonly="readonly"
					value="${liveGiftBagSetInfo.giftPrice}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">获得经验：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="giftExperience" id="giftExperience" readonly="readonly"
					value="${liveGiftBagSetInfo.giftExperience}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">数量：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="giftNums" id="giftNums"
					value="${liveGiftBagSetInfo.giftNums}">
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
	<script src="<%=path%>/js/live_anchor/giftBagSetEdit.js?v=1.0.11"></script>
</body>
</html>

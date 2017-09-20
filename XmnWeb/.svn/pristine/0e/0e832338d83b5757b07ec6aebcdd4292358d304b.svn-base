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
		<input type="hidden" name="uid" value="${memberWalletInfo.uid }">
		<div class="form-group">
			<label class="col-md-4 control-label">会员昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nname" id="nname" readOnly="readonly"
					value="${memberWalletInfo.nname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">会员手机号码：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="phone" id="phone" readOnly="readonly"
					value="${memberWalletInfo.phone}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">锁定佣金余额：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="updateOption" value="0" type="radio" ${memberWalletInfo.zbalanceLock=="true"?"checked":""} ><span style="font-size: 12px;">锁定</span>
				<input name="updateOption" value="1" type="radio" ${memberWalletInfo.zbalanceLock=="false"?"checked":""} ><span style="font-size: 12px;">解锁</span>
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
	<script src="<%=path%>/js/member/memberWalletEdit.js?v=1.0.11"></script>
</body>
</html>

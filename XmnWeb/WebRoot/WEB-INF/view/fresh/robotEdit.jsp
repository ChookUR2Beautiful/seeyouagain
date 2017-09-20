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
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		
		<c:if test="${!empty robot}">
			<input type="hidden" name="id" value="${robot.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-3 control-label">机器人登录名称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="uname" id="uname"
					value="${robot.uname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">机器人昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nname" id="nname"
					value="${robot.nname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">机器人头像：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" id="avatar" name="avatar"  value="${robot.avatar}">
							<div id="avatarImg"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">电话号码：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="phone" id="phone"
					value="${robot.phone}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">性别：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="sex" value="1" type="radio" ${robot.sex==1?"checked":""} ><span style="font-size: 12px;">男</span>
				<input name="sex" value="2" type="radio" ${robot.sex==2?"checked":""} ><span style="font-size: 12px;">女</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">等级：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="levelsId" id="levelsId"
					value="${robot.levelsId}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">关注数：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="concernNum" id="concernNum"
					value="${robot.concernNum}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">粉丝：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="fansNum" id="fansNum"
					value="${robot.fansNum}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">直播动态：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="conditionNum" id="conditionNum"
					value="${robot.conditionNum}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">去过的店铺：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="storeNum" id="storeNum"
					value="${robot.storeNum}">
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
	<script src="<%=path%>/js/fresh/robotEdit.js?v=1.0.3"></script>
</body>
</html>

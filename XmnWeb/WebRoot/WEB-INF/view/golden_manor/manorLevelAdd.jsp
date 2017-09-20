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

<title>新增/修改等级</title>

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
		<input type="hidden"  name="isAdd" value="${isAdd}">
		<c:if test="${!empty manorLevel}">
			<input type="hidden" name="id" value="${manorLevel.id}">
		</c:if>
		
		<div class="form-group">
			<label class="col-md-4 control-label">关卡编号：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type ="text" class="form-control"  name="no" readonly Value="${manorLevel.no }" style="width:100%;" >
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">关卡级别：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type ="text" class="form-control"  name="name"  Value="${manorLevel.name }" style="width:100%;" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">玫瑰花田(朵)：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="roses" 
					value="${manorLevel.roses}">
			</div>
		</div>	
		
		<div class="form-group">
			<label class="col-md-4 control-label">兰花田(朵)：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="orchids" 
					value="${manorLevel.orchids}" >
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">葵花田(朵)：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name=sunflowers 
					value="${manorLevel.sunflowers }" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">日收益花蜜(滴)：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="dailyNectar" 
					value="${manorLevel.dailyNectar }">
			</div>
		</div>		
		<div class="form-group" id="levelLogoDiv">
			<label class="col-md-4 control-label">等级LOGO：<span style="color:red;">*</span></label>
			<div class="col-md-7">
			<div id="levelLogoImg" ImgValidate="true"></div>
				<input type="hidden" class="form-control" name="levelLogo" id="levelLogo"	value="${manorLevel.levelLogo}">
			</div>
		</div>
		
		<div class="form-group" id="levelPicDiv">
			<label class="col-md-4 control-label">等级图：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<div id="levelPicImg" ImgValidate="true"></div>
				<input type="hidden" class="form-control" name="levelPic" id="levelPic"	value="${manorLevel.levelPic}">
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
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/golden_manor/addManorLevel.js"></script>
</body>
</html>

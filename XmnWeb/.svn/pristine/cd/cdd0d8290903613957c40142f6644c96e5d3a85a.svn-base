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

<title>激活设置</title>

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
		<input>		     
		<div class="form-group">
			<label class="col-md-4 control-label">能量激活：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type ="text" class="form-control"  name="list[0].number"  Value="${manorLevel.number }" style="width:100%;" >
				<input type="hidden" name="list[0].type" value="${manorLevel.type }">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">阳光激活：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type ="text" class="form-control"  name="list[1].number"  Value="${manorLevel.name }" style="width:100%;" >
				<input type="hidden" name="list[1].type" value="${manorLevel.type }">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">能量续租：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="list[2].number" 
					value="${manorLevel.roses}">
				<input type="hidden" name="list[2].type" value="${manorLevel.type }">
			</div>
		</div>	
		
		<div class="form-group">
			<label class="col-md-4 control-label">阳光续租：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="list[3].number" 
					value="${manorLevel.orchids}" >
				<input type="hidden" name="list[3].type" value="${manorLevel.type }">
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
	<script src="<%=path%>/js/golden_manor/setManorActivate.js"></script>
</body>
</html>

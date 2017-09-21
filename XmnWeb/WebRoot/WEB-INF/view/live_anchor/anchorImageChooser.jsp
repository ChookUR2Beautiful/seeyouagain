<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>通告</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<form role="form" id="searchForm">
		<input type="hidden" name="anchorId" id="anchorId" value="${anchorId }">
		<input type="hidden" name="addImgId" id="addImgId" value="${addImgId }">
	</form>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div id="recordList"></div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="button" class="btn btn-success" id="ensure">
					<span class="icon-ok" ></span> 确 定
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
	  update:'${ btnAu['anchorBusiness/manage/anchorImage']}',
	  addPhoto:'${ btnAu['anchorBusiness/manage/anchorImage']}',
	  delete:'${ btnAu['anchorBusiness/manage/anchorImage']}',
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/anchorImageChooser.js?v=1.0.4"></script>
</body>
</html>
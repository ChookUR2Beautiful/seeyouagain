<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>供应商列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style>
.submit {
	float: left;
}

.btn-add {
	background: #FF5C5C;
	width: 160px;
	margin-right: 20px;
	border: 1px solid #FF5C5C;
	line-height: 20px;
	text-align: center;
	font-size: 16px;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel panel-default" style="width:75%;">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['transportFee/manage/add'] }">
				<a type="button" class="btn btn-success"  href="transportFee/manage/addView.jhtml" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
			</div>
			<div id="transportFeeList"></div>
		</div>
	</div>
	<script type="text/javascript">
	contextPath = '${pageContext.request.contextPath}';
	authorityDelete = '${ btnAu['transportFee/manage/delete']}';
	authorityEdit = '${ btnAu['transportFee/manage/edit']}';
	</script>
	<script type="text/json" id="permissions">{add:'${ btnAu['transportFee/manage/add']}',delete:'${btnAu['transportFee/manage/delete']}',edit:'${ btnAu['transportFee/manage/edit']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/cloud_design/postTemplate/postTemplateList.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

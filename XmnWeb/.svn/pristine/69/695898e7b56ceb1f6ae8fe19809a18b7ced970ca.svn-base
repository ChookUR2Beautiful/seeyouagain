<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>分类管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel panel-default" style="width:100%;">
		<div class="panel-body data">
				<div class="btn-group" style="margin-bottom: 5px;">
					<c:if test="${!empty btnAu['fresh/category/delete'] }">
						<button type="button" class="btn btn-danger" id="delete" style="width: 80px;">
							<span class="icon-remove"></span>&nbsp;删除
						</button>
					</c:if>
					<c:if test="${!empty btnAu['fresh/category/add'] }">
						<button type="button" class="btn btn-success" data-type="ajax" data-title="添加分类" data-url="fresh/category/add/init.jhtml" data-toggle="modal"  >
							<span class="icon-plus"></span>&nbsp;添加
						</button>
					</c:if>
			</div>
			<div id="categoryList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{delete:'${btnAu['fresh/category/delete']}',edit:'${ btnAu['fresh/category/edit']}',add:'${ btnAu['fresh/category/add']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
		<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="<%=path%>/js/fresh/categoryList.js"></script>
	<script type="text/javascript">
		var basePath = "${pageContext.request.contextPath}";
	</script>
</body>

</html>

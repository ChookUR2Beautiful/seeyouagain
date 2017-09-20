<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>统计</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet" />
<link href="<%=path%>/css/live_anchor/liveGivedCountManage.css?v=1.0.1"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=path%>/resources/jQueryTable/css/style.css">
</head>



<body style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;"
	class="doc-views with-navbar">

	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<table id="datatable" style="display:none;">
		<thead>
			<tr>
				<th>城市</th>
				<th>人数</th>
			</tr>
		</thead>
		<tbody id="chart1">
		</tbody>
	</table>
	<div class="table_body"></div>
	<div id="page_navigation"></div>

	<div id="container2"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<table id="datatable2" style="display:none;">
		<thead>
			<tr>
				<th>日期</th>
				<th>人数</th>
			</tr>
		</thead>
		<tbody id="chart2">
		</tbody>
	</table>
	<div class="table_body2"></div>
	<div id="page_navigation2"></div>


	<script type="text/json" id="permissions">
{
	add:'${ btnAu['fresh/module/saveModule']}',
	delete:'${btnAu['fresh/module/deleteModule']}'
}</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>

	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/resources/jQueryTable/js/table.js"></script>
	<script src="<%=path%>/resources/jQueryTable/js/jquery.page.js"></script>
	<script src="<%=path%>/resources/highcharts/highcharts.js"></script>
	<script src="<%=path%>/resources/highcharts/modules/exporting.js"></script>
	<script src="<%=path%>/resources/highcharts/modules/data.js"></script>
	<script src="<%=path%>/js/vstar/vstarEnrollChartList.js"></script>

</body>

</html>


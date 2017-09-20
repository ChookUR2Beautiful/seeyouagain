<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>直播分账记录管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel panel-default">
		<div class="panel-bod	y data">
			<input type="hidden" id="detailId" value="${id}">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
				</div>
				<thead>
					<tr>
						<th style="width:130px;">类型</th>
						<th style="width:130px;">位置</th>
						<th style="width:130px;">编号</th>
						<th style="width:130px;">状态</th>
					</tr>
				</thead>
				<tbody id="detailList">
				</tbody>
			</table>
		</div>
	</div>
	<script src="<%=path%>/js/vstar/ticketsOrder/detailList.js"></script>
</body>
</html>

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
<title>话题</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"
				id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>话题内容：</h5></td>
							<td class="col-md-2"><input type="text" class="form-control"
								name="content"></td>
							<td class="col-md-1 col-md-offset-2"><h5>员工姓名：</h5></td>
							<td class="col-md-2"><input type="text" class="form-control"
								name="fullname"></td>
							<td class="col-md-1"><h5>提交时间：</h5></td>
							<td class="col-md-2"><div class="input-group">
									<input type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateStart">
									<span class="input-group-addon fix-border">--</span><input
										type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateEnd">
								</div></td>
							<td class="col-md-3"><div class="submit text-left">
									<input class="submit radius-3"
										style="width:49%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49%;margin-right:0;" type="reset" value="重置全部"
										data-bus='reset' />
								</div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<!-- <button type="button" class="btn btn-success"  data-type="ajax"   data-url="business_cooperation/subject/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>添加</button> -->
				<%-- 	<c:if test="${null!=btnAu['business_cooperation/subject/delete'] && btnAu['business_cooperation/subject/delete']}">
				<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>删除</button>
				</c:if> --%>
				<c:if
					test="${null!=btnAu['business_cooperation/subject/export'] && btnAu['business_cooperation/subject/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>导出
					</button>
				</c:if>
				<!-- <div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="subjectList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{htjllb:'${btnAu['business_cooperation/subject/init/list']}',
											   htjlxq:'${btnAu['business_cooperation/subject/view'] }',htjldc:'${btnAu['business_cooperation/subject/export'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/business_cooperation/subject.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

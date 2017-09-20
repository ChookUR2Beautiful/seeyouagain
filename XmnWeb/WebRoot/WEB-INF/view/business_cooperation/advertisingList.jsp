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
<title>广告轮播</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
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
							<td class="col-md-1"><h5>广告文本：</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="content"></td>
							<td class="col-md-1"><h5>上线状态：</h5></td>
							<td class="col-md-3"><select class="form-control" style="width:98%"
								name="isshow">
									<option value="">全部</option>
									<option value="0">已上线</option>
									<option value="2">已下线</option>
							</select></td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>是否全国:</h5></td>
							<td class="col-md-3"><select name="isall"
								class="form-control">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
							</select></td>
							<td class="col-md-1"><h5>区域查询:</h5></td>
							<td class="col-md-3">
								<div class="input-group" id="ld" style="width:100%"></div>
							</td>
							<input type="hidden" name="type" value="3" />
							<td colspan="2" class="col-md-4"><div
									class="submit text-left">
									<input class="submit radius-3"
										style="width:49.5%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49.5%;margin-right:0;" type="reset" value="重置全部"
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
				<c:if
					test="${not empty btnAu['business_cooperation/advertising/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-url="business_cooperation/advertising/add/init.jhtml"
						data-width="70%" data-toggle="modal">
						<span class="icon-plus"></span>添加
					</button>
				</c:if>
				<%-- <c:if test="${not empty btnAu['business_cooperation/advertising/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>删除</button></c:if> --%>
				<c:if
					test="${not empty btnAu['business_cooperation/advertising/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>导出
					</button>
				</c:if>
			</div>
			<div id="advertisingList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${btnAu['business_cooperation/advertising/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_cooperation/advertising.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

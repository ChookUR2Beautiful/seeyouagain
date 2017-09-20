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
<title>评论图片</title>
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
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<input type="hidden" name="cid" value="${param.cid}" />
							<td class="col-md-1"><h5>&nbsp;&nbsp;用户：</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="nname"></td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;图片类型：</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="pid"></td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>&nbsp;&nbsp;图片名称：</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="picname"></td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;上传时间：</h5></td>
							<td class="col-md-3">
								<div class="input-group">
									<input type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateStart">
									<span class="input-group-addon fix-border">--</span><input
										type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateEnd">
								</div>
							</td>
							<td class="col-md-4">
								<div class="submit text-left">
									<input class="submit radius-3"
										style="width:49%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49%;margin-right:0;" type="reset" value="重置全部"
										data-bus='reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['businessman/commentPic/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-url="businessman/commentPic/add/init.jhtml?cid=${param.cid}"
						data-toggle="modal">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
				<c:if test="${null!=btnAu['businessman/commentPic/delete']}">
					<button type="button" class="btn btn-danger" id="delete">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if>
			</div>
			<div id="commentPicList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${btnAu['businessman/commentPic/update']}',del :'${btnAu['businessman/commentPic/delete']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/commentPic.js"></script>
</body>
</html>

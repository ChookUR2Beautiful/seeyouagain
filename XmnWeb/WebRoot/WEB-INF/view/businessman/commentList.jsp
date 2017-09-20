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
<title>商家评论</title>
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
			<form class="form-horizontal" role="form" method="post"
				id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<input type="hidden" class="form-control" name="sellerid"
								value="${param.sellerid}" />
							<td class="col-md-1"><h5>&nbsp;&nbsp;用户：</h5></td>
							<td class="col-md-2"><input type="text" class="form-control"
								name="nname" style="width:90%;"></td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;评论内容：</h5></td>
							<td class="col-md-2"><input type="text" class="form-control"
								name="content" style="width:90%;"></td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;评论时间：</h5></td>
							<td class="col-md-2">
								<div class="input-group">
									<input type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateStart">
									<span class="input-group-addon fix-border">--</span></label> <input
										type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateEnd">
								</div>
							</td>
							<!-- <tr>
							<td style="width:50px;"><h5>&nbsp;&nbsp;评论商家：</h5></td>
							<td style="width:120px;"><input type="text" class="form-control"  name="sellername" ></td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;评论内容：</h5></td>
							<td style="width:120px;"><input type="text" class="form-control"  name="content" class="col-md-2"></td>
							<td colspan="2"></td>-->
							<td class="col-md-3">
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
				<c:if test="${null!=btnAu['businessman/comment/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-url="businessman/comment/add/init.jhtml?sellerid=${param.sellerid }"
						data-toggle="modal">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
				<%-- <c:if test="${null!=btnAu['businessman/comment/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button></c:if> --%>
				<c:if test="${null!=btnAu['businessman/comment/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>&nbsp;导出
					</button>
				</c:if>
			</div>
			<div id="commentList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${btnAu['businessman/comment/update']}',del :'${btnAu['businessman/comment/delete']}',commentReply :'${btnAu['businessman/commentReply/init']}',commentPic :'${btnAu['businessman/commentPic/init']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/comment.js"></script>
</body>
</html>

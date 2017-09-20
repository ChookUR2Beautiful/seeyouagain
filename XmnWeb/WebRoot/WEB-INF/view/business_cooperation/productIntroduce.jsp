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
<title>视频讲解</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>
<div>
	<c:if test="${not empty btnAu['business_cooperation/video/init/list']}">
		<button type="button" id="videoInfoId" class="btn btn-lg">视频教学管理</button>
	</c:if>
	<c:if
		test="${not empty btnAu['business_cooperation/sellerAsk/init/list']}">
		<button type="button" id="sellerAskInfoId" class="btn btn-lg">商户问答管理</button>
	</c:if>
</div>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="videoId" style="display: none;">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" method="post"
					id="searchForm">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td class="col-md-1"><h5>视频名称：</h5></td>
								<td class="col-md-3"><input type="text"
									class="form-control" name="videoname" style="width:90%;">
								</td>
								<td class="col-md-1"><h5>状态：</h5></td>
								<td class="col-md-3"><select class="form-control"
									name="status" style="width:90%;">
										<option value="">请选择</option>
										<option value="0">停用</option>
										<option value="1">启用</option>
								</select></td>
								<td class="col-md-4"><div class="submit">
										<input class="submit radius-3" type="button" value="查询全部"
											data-bus='query' /><input class="reset radius-3"
											type="reset" value="重置全部" data-bus='reset' />
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
					<c:if test="${not empty btnAu['business_cooperation/video/add']}">
						<button type="button" class="btn btn-success" data-type="ajax"
							data-url="business_cooperation/video/add/init.jhtml"
							data-toggle="modal">
							<span class="icon-plus"></span>添加
						</button>
					</c:if>
					<%-- <c:if test="${not empty btnAu['business_cooperation/video/delete']}"><button type="button" class="btn btn-danger" id="deleteVideo"><span class="icon-remove"></span>删除</button></c:if> --%>
					<c:if
						test="${not empty btnAu['business_cooperation/video/export']}">
						<button type="button" id="exportVideo" class="btn btn-default">
							<span class="icon-download-alt"></span>导出
						</button>
					</c:if>
				</div>
				<div id="videoList"></div>
			</div>
		</div>
	</div>
	<div id="sellerAskId" style="display: none;">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" method="post"
					id="searchSellerAskForm">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td class="col-md-1"><h5>问题名称：</h5></td>
								<td class="col-md-3"><input type="text"
									class="form-control" name="askname" style="width:90%;">
								</td>
								<td class="col-md-1"><h5>提问时间:</h5></td>
								<td class="col-md-3">
									<div class="input-group">
										<input type="text" name="sdateStart"
											placeholder="yyyy-MM-dd hh:mm"
											class="form-control form-datetime"> <span
											class="input-group-addon fix-border">--</span><input
											type="text" name="sdateEnd" placeholder="yyyy-MM-dd hh:mm"
											class="form-control form-datetime">
									</div>
								</td>
								<td class="col-md-4"><div class="submit text-left">
										<input class="submit radius-3"
											style="width:49.5%;margin-right:0;" type="button"
											value="查询全部" data-bus='query' /> <input
											class="reset radius-3" style="width:49.5%;margin-right:0;"
											type="reset" value="重置全部" data-bus='reset' />
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
						test="${not empty btnAu['business_cooperation/sellerAsk/add']}">
						<button type="button" class="btn btn-success" data-type="ajax"
							data-url="business_cooperation/sellerAsk/add/init.jhtml"
							data-toggle="modal">
							<span class="icon-plus"></span>添加
						</button>
					</c:if>
					<%-- <c:if test="${not empty btnAu['business_cooperation/sellerAsk/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>删除</button></c:if> --%>
					<c:if
						test="${not empty btnAu['business_cooperation/sellerAsk/export']}">
						<button type="button" id="exportAsk" class="btn btn-default">
							<span class="icon-download-alt"></span>导出
						</button>
					</c:if>
				</div>
				<div id="sellerAskList"></div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{updateVideo:'${btnAu['business_cooperation/video/update']}',playVideo:'${btnAu['business_cooperation/video/playVideo']}',updateAsk:'${btnAu['business_cooperation/sellerAsk/update']}',viewAsk:'${btnAu['business_cooperation/sellerAsk/view']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/business_cooperation/productIntroduce.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
</body>
</html>

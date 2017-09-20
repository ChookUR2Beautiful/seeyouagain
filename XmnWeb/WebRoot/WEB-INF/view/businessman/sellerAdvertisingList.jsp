<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>广告轮播图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
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
				<input type="hidden" name="type" value="2" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:10%;"><h5>广告文本：</h5></td>
							<td style="width:23%;"><input type="text"
								class="form-control" name="content" style="width:90%;"></td>
							<td style="width:10%;"><h5>是否全国:</h5></td>
							<td style="width:23% !important;"><select name="isall"
								class="form-control" style="width:90%">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
							</select></td>
						</tr>
						<tr>
						<td style="width:10%;"><h5>上线状态：</h5></td>
							<td style="width:23%;"><select class="form-control"
								name="isshow" style="width:90%;">
									<option value="">全部</option>
									<option value="0" ${advertising.isshow==0?"selected":""}>已上线</option>
									<option value="2" ${advertising.isshow==2?"selected":""}>已下线</option>
							</select></td>
						<td style="width:10%;"><h5>区域查询:</h5></td>
							<td style="width:23% !important;">
								<div class="input-group" id="ld"
									<c:choose>
										<c:when test="${!empty param.area}">
											initValue=" ${param.area}"
										</c:when>
										<c:when test="${empty param.area && !empty param.city}">
											initValue=" ${param.city}"
										</c:when>
										<c:otherwise>
											initValue=" ${param.province}"
										</c:otherwise>
									</c:choose>
									style="width:92%"></div>
							</td>
						<td></td>
							<td colspan="2" ><div class="submit" style="text-align: left; ">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /><input class="reset radius-3" type="reset"
										value="重置全部" data-bus='reset' />
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
					test="${null!=btnAu['businessman/advertising/add'] && btnAu['businessman/advertising/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-url="businessman/advertising/add/init.jhtml"
						data-toggle="modal">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
				<%-- 				<c:if
					test="${null!=btnAu['businessman/advertising/delete'] && btnAu['businessman/advertising/delete']}">
					<button type="button" class="btn btn-danger" id="delete">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if> --%>
				<c:if
					test="${null!=btnAu['businessman/advertising/export'] && btnAu['businessman/advertising/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>&nbsp;导出
					</button>
				</c:if>
			</div>
			<div id="sellerAdvertisingList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['businessman/advertising/update']}',del:'${btnAu['businessman/advertising/delete'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/businessman/seller_advertising.js"></script>
</body>
<!-- <script src="<%=path%>/ux/js/grid.js"></script> -->
<!-- <script src="<%=path%>/js/businessman/model/advertisingModel.js"></script> -->

</html>

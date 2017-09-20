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
<title>商家折扣列表</title>
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
		<input type="hidden" id="sellerid" name="sellerid"
			value="${param.sellerid}">
		<!-- 用于查询分店列表的id -->
		<input type="hidden" id="fartherSellerId"
			value="${param.fartherSellerId}">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="sellerAgioFrom">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>开始时间:</h5></td>
							<td class="col-md-2"><div class="input-group">
									<input type="text" name="stdateStart"
										placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime"> <span
										class="input-group-addon">--</span><input type="text"
										name="stdateEnd" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime">
								</div></td>

							<td class="col-md-1"><h5>结束时间:</h5></td>
							<td class="col-md-2"><div class="input-group">
									<input type="text" name="endateStart"
										placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime"> <span
										class="input-group-addon">--</span> <input type="text"
										name="endateEnd" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime">
								</div></td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>折扣类型：</h5></td>
							<td class="col-md-2"><select class="form-control"
								name="type">
									<option value="">请选择</option>
									<option value="1">常规折扣</option>
									<option value="2">周末折扣</option>
									<option value="3">自定义折扣</option>
							</select></td>
							<td class="col-md-1"><h5>折扣状态：</h5></td>
							<td class="col-md-2"><select class="form-control"
								name="status">
									<option value="">请选择</option>
									<option value="1">启用</option>
									<option value="2">关闭</option>
							</select></td>
							<td class="col-md-2">
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
				<!-- 隐藏页面添加、删除功能 add by huang'tao 2016-07-21 -->
				<c:if
					test="${null!=btnAu['businessman/sellerAgio/add'] && btnAu['businessman/sellerAgio/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-url="businessman/sellerAgio/add/init.jhtml?sellerid=${param.sellerid}"
						data-toggle="modal" style="display:none">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
				<c:if
					test="${null!=btnAu['businessman/sellerAgio/delete'] && btnAu['businessman/sellerAgio/delete']}">
					<button type="button" class="btn btn-danger" id="delete" style="display:none">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if>
			</div>
			<div id="sellerAgioGrid"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{sc:'${btnAu['businessman/sellerAgio/delete']}',xg:'${ btnAu['businessman/sellerAgio/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/commonAgio.js"></script>
	<script src="<%=path%>/js/businessman/sellerAgioList.js"></script>
</body>
</html>

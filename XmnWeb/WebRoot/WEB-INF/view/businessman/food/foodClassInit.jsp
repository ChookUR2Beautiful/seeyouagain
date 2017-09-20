<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>商家菜品分类列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
	overflow-x: auto;
	overflow-y: auto;
	background: #EEE;
}

th {
	font-size: 12px;
}
</style>
</head>

<body class="doc-views with-navbar">
	<div class="panel">
		<div class="panel-body">
			<form id="foodClassForm">
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
				<input type="hidden" value="${sellerId}" name="sellerId" />
			</form>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${!empty  btnAu['businessman/seller/food/class/add/init']}">
					<button data-toggle="modal"
						data-url="businessman/seller/food/class/add/init.jhtml?sellerid=${sellerId}"
						data-type="ajax" class="btn btn-success" type="button">
						<span class="icon-plus"></span>添加
					</button>
				</c:if>
			</div>
			<div id="foodClassList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/food/class/update/init']}'}</script>

	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script type="text/javascript">
		var foodClassList;
		$(function() {
			inserTitle(
					'> 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> ><span>菜品分类列表</span>',
					'', true);
			foodClassList = $("#foodClassList").page({
				url : 'businessman/seller/food/class/init/list.jhtml',
				success : success,
				pageBtnNum : 10,
				paramForm : 'foodClassForm'
			});
		});
		function success(data, obj) {
			obj
					.find('div')
					.eq(0)
					.html(
							$
									.renderGridView(
											{
												title : "商家菜品类别",
												totalTitle : "商家菜品类别",
												form : "foodClassForm",
												backButton : true,
												handleColumn : {
													title : "操作",
													name : "fid",
													queryPermissions : [ "xg" ],
													column : [ {
														title : "修改",
														modal : "businessman/seller/food/class/update/init.jhtml",
														data_width : "auto",
														param : [ "fid" ],
														permissions : "xg"
													} ]
												},
												columns : [ {
													title : "类别编号",
													name : "fid",
													width : "auto"
												}, {
													title : "类别名称",
													name : "className",
													width : "auto"
												}, {
													title : "商家编号",
													name : "sellerId",
													width : "auto"
												}, {
													title : "商家名称",
													name : "sellerName",
													width : "auto"
												} ],
												permissions : permissions
											}, data));
		}
	</script>
</body>
</html>

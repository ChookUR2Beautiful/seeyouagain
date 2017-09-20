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

<title>商家菜品列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
</head>

<body class="doc-views with-navbar"
	style="overflow-x: auto;overflow-y:auto;background: #EEE;">
	<div class="panel">
		<div class="panel-body">
			<form id="foodForm">
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
				<input type="hidden" value="${sellerId}" name="sellerId" />
			</form>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty  btnAu['businessman/seller/food/add/init']}">
					<button data-toggle="modal"
						data-url="businessman/seller/food/add/init.jhtml?sellerid=${sellerId}"
						data-type="ajax" class="btn btn-success" type="button">
						<span class="icon-plus"></span>添加
					</button>
				</c:if>
			</div>
			<div id="foodList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/food/update/init']}',sc:'${ btnAu['businessman/seller/food/delete/init']}'}</script>

	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script type="text/javascript">
		var foodList;
		$(function() {
			inserTitle(
					'> 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> ><span>菜品列表</span>',
					'', true);
			foodList = $("#foodList").page({
				url : 'businessman/seller/food/init/list.jhtml',
				success : success,
				pageBtnNum : 10,
				paramForm : 'foodForm'
			});
		});
		function success(data, obj) {
			obj.find('div').eq(0).html(
				$.renderGridView({
					title : "商家菜品",
					totalTitle : "商家菜品",
					form : "foodForm",
					backButton : true,
					/* addBtn : 'addbtn', */
					handleColumn : {
						title : "操作",
						name : "id",
						queryPermissions : [ "xg","sc" ],
						column : [ {
							title : "修改",
							modal : "businessman/seller/food/update/init.jhtml",
							data_width : "auto",
							param : [ "id" ],
							permissions : "xg"
						},{
							title : "删除",
							method : "remove",
							param : ["id"],
							permissions : "sc"
						} ]
					},
					columns : [
						{
							title : "菜品名称",
							name : "foodName",
							width : "auto"
						},
						{
							title : "数据来源",
							name : "sourceText",
							width : "auto"
						},
						{
							title : "菜品序号",
							name : "num",
							width : "auto"
						},
						{
							title : "点赞数",
							name : "banNum",
							width : "auto"
						},
						{
							title : "上线状态",
							name : "statusName",
							width : "auto"
						},									
						{
							title : "菜品展示大图",
							name : "bigPic",
							customMethod : function(
									value, data) {
								return '<img src="'
										+ $("#fastfdsHttp").val()
										+ data.bigPic
										+ '" style="width:50px;height:50px;">'
							},
							width : "auto"
						},
						{
							title : "菜品展示小图",
							name : "smallPic",
							customMethod : function(
									value, data) {
								return '<img src="'
										+ $(
											"#fastfdsHttp")
											.val()
										+ data.smallPic
										+ '" style="width:50px;height:50px;">'
							},
							width : "auto"
						} ],
					permissions : permissions
				}, data));
			
		}
		/**
		 * 删除
		 */
		function remove(id) {
			if(!id){
				showWarningWindow("warning","请至少选择一条记录！");
				return;
			}
			
			showSmConfirmWindow(function() {
				$.ajax({
					type : 'post',
					url : 'businessman/seller/food/delete.jhtml' + '?t=' + Math.random(),
					data : 'id=' + id,
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						$('#prompt').hide();

						if (data.success) {
							foodList.reload();
						}

						showSmReslutWindow(data.success, data.msg);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$('#prompt').hide();
					}
				});
			});
		}

		function substr(obj,length){
			if(obj.length > length){
				obj = obj.substring(0,length) +"...";
			}
			return obj;
		}
	</script>
</body>
</html>

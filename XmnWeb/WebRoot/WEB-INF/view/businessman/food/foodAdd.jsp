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

<title>添加商家菜品</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">

<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
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
	<!-- <div class="panel">
		<div class="panel-body" style="margin-top:40px"> -->
	<form class="form-horizontal" id="foodAddForm">
		<input type="hidden" name="sellerId" value="${sellerId}">
		<input type="hidden" name="atag" value="0">
		<div class="form-group">
			<label class="col-md-3  control-label">菜名:</label>
			<div class="col-md-5">
				<input class="form-control" name="foodName" placeholder="名称长度15字以内">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3  control-label">菜品序号:</label>
			<div class="col-md-5">
				<input class="form-control" name="num">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3  control-label">预览图:</label>
			<div class="col-md-5">
				<input type="hidden" class="form-control" id="picUrl" name="bigPic" />
				<input type="hidden" class="form-control" id="breviaryId"
					name="smallPic" />
				<div id="foodBigPic"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3  control-label">是否上架:</label>
			<div class="col-md-5">
				<label class="radio-inline">是<input type="radio" value="1"
					name="datastatus"></label> <label class="radio-inline">否<input
					type="radio" value="3" name="datastatus" checked="checked"></label>
			</div>
		</div>
		<div class="form-group">
			<div style="padding:10px 0 10px 0;" class="text-center">
				<button id="updateSaveBrandSeller" type="submit"
					class="btn btn-success">
					<i class="icon-save"></i>&nbsp;保存
				</button>
				&nbsp;&nbsp;
				<button data-dismiss="modal" class="btn btn-default" type="reset">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<input id="sltflag" type="hidden" value="true" />
	<!-- 		</div>
	</div> -->
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<%-- <jsp:include page="../../common.jsp"></jsp:include> --%>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script type="text/javascript">
		$(function() {
			/* inserTitle(
					'> 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> ><span>添加菜品</span>',
					'', true); */

			$("#foodBigPic").uploadImg({
				urlId : "picUrl",
				breviaryId : "breviaryId",
				showImg : $('#picUrl').val()
			});

			validate("foodAddForm", {
				rules : {
					foodName : {
						required : true,
						maxlength : 15
					},
					cprice : {
						required : true,
						number : true,
						gt : 0,
						max : 999999
					},
					num : {
						required : true,
						integer : true,
						min : 1,
						max : 999999
					},
					fid : {
						required : true
					}
				},
			/* messages : {
				foodName : {
					required : "请输入菜品名称！",
					maxlength : "菜品名称必须在15个字以内!"
				},
				cprice : {
					required : "请输入菜品价格！",
					number : "菜品价格必须是数字",
					max : "菜品价格最大值为999999"
				},
				num : {
					required : "请输入菜品序号！",
					min : "菜品序号最小值为1",
					max : "菜品序号最大值为999999"
				},
				fid : {
					required : "菜品分类必选！"
				}
			} */
			}, formAjax);
		});
		function formAjax() {
			var data = jsonFromt($('#foodAddForm').serializeArray());
			$.ajax({
				type : 'post',
				url : "businessman/seller/food/add.jhtml" + "?t="
						+ Math.random(),
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
					showSmReslutWindow(data.success, data.msg);
					try {
						if (foodList != undefined) {
							foodList.reload();
						}
					} catch (err) {
						console.log(err);
					}
					try {
						if (pageDiv != undefined) {
							pageDiv.reload();
						}
					} catch (err) {
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}

	</script>
</body>
</html>

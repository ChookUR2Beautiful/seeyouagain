<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>修改商家菜品分类</title>

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
	<form class="form-horizontal" id="foodClassUpdateForm">
		<input type="hidden" name="fid" value="${foodClass.fid}">
		<div class="form-group">
			<label class="col-md-3 control-label">分类名称:</label>
			<div class="col-md-5">
				<input class="form-control" name="className"
					value="${foodClass.className}" placeholder="名称长度15字以内">
			</div>
		</div>
		<!-- <div class="form-group">
					<label class="col-md-3 control-label">备注:</label>
					<div class="col-md-5">
						<input class="form-control" name="">
					</div>
				</div> -->
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
	<!-- </div>
	</div> -->
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<%-- <jsp:include page="../../common.jsp"></jsp:include> --%>

	<script type="text/javascript">
		$(function() {
			/* inserTitle(
					'> 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> ><span>修改菜品分类</span>',
					'', true); */
			validate("foodClassUpdateForm", {
				rules : {
					className : {
						required : true,
						maxlength : 15
					}
				},
				messages : {
					className : {
						required : "请输入菜品分类名称！",
						maxlength :  "菜品分类必须在15个字以内!"
					}
				}
			}, formAjax);
		});

		function formAjax() {
			var data = jsonFromt($('#foodClassUpdateForm').serializeArray());
			$.ajax({
				type : 'post',
				url : "businessman/seller/food/class/update.jhtml" + "?t="
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
						if (foodClassList != undefined) {
							foodClassList.reload();
						}
					} catch (err) {
						console.log(err);
					}
					try {
						if (pageDiv != undefined) {
							pageDiv.reload();
						}
					} catch (err) {
						console.log(err);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
	</script>
</body>
</html>

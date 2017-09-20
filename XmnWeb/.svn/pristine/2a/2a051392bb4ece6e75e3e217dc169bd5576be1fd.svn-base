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

<title>编辑帮助条目</title>

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

	<form class="form-horizontal" id="helpForm">
		<input type="hidden" id="isType" value="${isType}">
		<input type="hidden" name="id" value="${helpItem.id}">

		<div class="form-group">
					<label class="col-md-3 control-label">分类:</label>
					<div class="col-md-9">
						<input class="form-control" name="item" value="${helpItem.item}">
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
			validate("helpForm", {
				rules : {
					item : {
						required : true,
					}
				},
				messages : {
					item : {
						required : "请输入帮助条目分类！",
					}
				}
			}, formAjax);
		});

		function formAjax() {
			var data = jsonFromt($('#helpForm').serializeArray());
			var url ;
			if($('#isType').val()=="add"){
				url ="user_terminal/help_manage/item/add.jhtml";
			}else{
				url ="user_terminal/help_manage/item/update.jhtml";
			}
			$.ajax({
				type : 'post',
				url : url + "?t=" + Math.random(),
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
						if (itemList != undefined) {
							itemList.reload();
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

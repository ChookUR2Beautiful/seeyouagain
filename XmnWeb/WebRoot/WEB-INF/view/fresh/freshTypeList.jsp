<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>用户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<i class="icon-reorder"></i>&nbsp;&nbsp;分类列表
		</div>
		<div id="categoryDiv" class="panel-body" request-init="${requestInit}">
		</div>

	</div>
	</div>

	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>//resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script type="text/javascript">
		inserTitle(' > 积分超市 > <span><a href="fresh/category/init.jhtml" target="right">分类管理</a>', 'category', true);
		var category = $('#categoryDiv');
		var div;
		$(function() {
			var url = [ $(category).attr("request-init"), ".jhtml" ].join("");
			category = div = $(category).page({
				url : url,
				dataType : "html",
				success : handle,
				pageBtnNum : 10
			});
	
			
		});
	
	
	
		function handle(data) {
			var table = $(category).find(".main.categorytable");
			if (table.length == 0) {
				$(category).prepend(data);
			}
			else {
				$(table).html(data);
			}
		}
		function formSubmit() {
			var form = $("body").find(".modal-body form");
			var action = $(form).attr("action");
			var method = $(form).attr("method");
			var param = jsonFromt($(form).serializeArray());
			$.ajax({
				url : action,
				type : method,
				data : param,
				cache : false
			}).done(function(data) {
				$('#triggerModal').modal('hide');
				if (data.success) {
					div.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			});
			return false;
		}
		
		function delete_fun() {
				var parent_value = [];
				$('input[name="parent"]:checked').each(function() {
					parent_value.push($(this).val());
				});
				console.log(parent_value)
				var child_value = [];
				$('input[name="child"]:checked').each(function() {
					child_value.push($(this).val());
				});
				console.log(child_value)
				if (parent_value.length == 0 && child_value.length == 0) {
					showWarningWindow("warning", "请至少选择一条记录！");
					return;
				}
				showSmConfirmWindow(function() {
					$.ajax({
						type : 'post',
						url : 'fresh/category/delete.jhtml' + '?t=' + Math.random(),
						data : {
							"parentId" : parent_value.join(","),
							"childId" : child_value.join(",")
						},
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							$('#prompt').hide();
							if (data.success) {
								category.reload();
							}
	
							showSmReslutWindow(data.success, data.msg);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$('#prompt').hide();
						}
					});
				}, "确定要删除吗？");
			}
	</script>
</body>
</html>
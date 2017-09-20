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
		<input type="hidden" name="id" value="${helpInfo.id}">
		<div class="form-group">
			<label class="col-md-3 control-label">分类名称:</label>
			<div class="col-md-5">
				<select class="form-control" id="itemId2" name="itemId" initValue="${helpInfo.itemId}" style="width:90%">
				</select>
			</div>
		</div>  
		<div class="form-group">
					<label class="col-md-3 control-label">标题:</label>
					<div class="col-md-9">
						<input class="form-control" name="title" value="${helpInfo.title}">
					</div>
		</div>
		<div class="form-group">
					<label class="col-md-3 control-label">内容:</label>
					<div class="col-md-9">
						<textarea name="context" rows="5" class="form-control">${helpInfo.context}</textarea>
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
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script type="text/javascript">
		$(function() {
			// 条目分类
			$("#itemId2").chosenObject({
				id : "itemId2",
				hideValue : "id",
				showValue : "item",
				url : "user_terminal/help_manage/item/list.jhtml",
				isChosen:false
			});
					
			validate("helpForm", {
				rules : {
					title : {
						required : true,
					},
					context : {
						required : true,
					}
				},
				messages : {
					title : {
						required : "请输入帮助条目名！",
					},
					context : {
						required : "请输入帮助条目内容！",
					}
				}
			}, formAjax);
		});

		function formAjax() {
			console.info('submit...');
			var data = jsonFromt($('#helpForm').serializeArray());
			var url;
			if($('#isType').val()=="add"){
				url ="user_terminal/help_manage/add.jhtml";
			}else{
				url ="user_terminal/help_manage/update.jhtml";
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
						if (helpList != undefined) {
							helpList.reload();
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

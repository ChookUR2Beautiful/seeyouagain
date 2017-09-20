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
<title>活动管理</title>
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
	<div id="main">
		<div class="panel">
			<div class="panel-heading">基本信息</div>
			<div class="panel-body">
				<form id="addUsersForm">
					<input type="hidden" name="issueId" value="${issue.issueId}">
					<div class="row">
						<label class="col-md-2 text-right">活动名称：</label>
						<div class="col-md-10">${issue.activityName}</div>
					</div>
					<div class="row">
						<label class="col-md-2 text-right">短信内容：</label>
						<div class="col-md-10">${issue.message}</div>
					</div>
					<div class="row">
						<label class="col-md-2 text-right">选择用户：</label>
						<div class="col-md-10">
							<textarea id="object" class="form-control" rows="5"
								name="userIds" style="width:60%"></textarea>
						</div>
					</div>
					<div class="row">
						<label class="col-md-2"></label>
						<div class="col-md-10" id="tishi"></div>
					</div>
					<div class="row text-center" style="margin-top:20px;">
						<button class="btn btn-danger" type="submit" id="ensure">
							<i class="icon-save"></i>&nbsp;保存
						</button>
						&nbsp;&nbsp;
						<button class="btn btn-warning" type="button"
							onclick="window.history.back();">
							<i class="icon-reply"></i>&nbsp;取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script type="text/javascript">
		var searchChosen = undefined;
		inserTitle(
				' > 优惠券管理  > <a href="coupon/couponissue/init.jhtml" target="right">优惠券发放管理</a> > 添加用户',
				'activityManagement', true);
		$(function() {
			if (!searchChosen) {
				searchChosen = $("#object").searchChosen({
					url : "user_terminal/appPush/init/choseMember/init.jhtml",
					separator : ","
				});
			}
			validate("addUsersForm", {
				rules : {
					userIds : {
						required : true
					}
				},
				messages : {
					userIds : {
						required : "不能为空!"
					}
				}
			}, formAjax);
		});
		function formAjax() {
			var data = jsonFromt($('#addUsersForm').serializeArray());
			if (!checkData(data.userIds, searchChosen.next(), "不能为空")) {
				return false;
			}
			showSmConfirmWindow(function() {
				var url;
				url = 'coupon/couponissue/sendshortmessage/addUsers.jhtml'
						+ '?t=' + Math.random();
				$
						.ajax({
							type : 'post',
							url : url,
							data : data,
							dataType : 'json',
							beforeSend : function(XMLHttpRequest) {
								$('#prompt').show();
							},
							success : function(data) {
								$('#prompt').hide();
								if (data.success) {
									$("#tishi").html("");
									showSmReslutWindow(data.success, data.msg);
									setTimeout(function() {
										window.history.back()
									}, 1000);
								} else {
									$("#tishi").html(
											"<font color=red>" + data.data
													+ "</font>");
									showSmReslutWindow(data.success, data.msg);
								}
							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								$('#prompt').hide();
								$('#triggerModal').modal('hide');
							}
						});
			}, "请确认信息是否正确！");
		}
	</script>
</body>
</html>
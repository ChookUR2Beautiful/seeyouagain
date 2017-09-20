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
<title>系统推送优惠券发放</title>
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
		<div class="example">
		<div class="panel panel-primary">
			<div class="panel-heading">基本信息</div>
			<div class="panel-body">
				<form id="addUsersForm" class="form-horizontal">
					<div class="row">
						<label class="col-md-2 text-right">优惠券类型：<span style="color:red;">*</span></label>
						<div class="col-md-10">
							<input name="ctype" value="0" type="radio" checked="checked" ><span style="font-size: 12px;">消费优惠劵</span>
							<input name="ctype" value="1" type="radio" ><span style="font-size: 12px;">商城优惠券</span>
							<input name="ctype" value="5" type="radio" ><span style="font-size: 12px;">平台通用优惠劵</span>
						</div>
					</div>
					
					<br>
					
					<div class="row">
						<label class="col-md-2 text-right">选择优惠券：<span style="color:red;">*</span></label>
						<div class="col-md-3" id="cidDiv">
							<select class="form-control" id="cid" name="cid"  initValue="" style="width:100%;">
							</select>
						</div>
					</div>
					
					<br>
					
					<div class="row">
						<label class="col-md-2 text-right">推送数量：<span style="color:red;">*</span></label>
						<div class="col-md-3">
							<input type="text" name="sendNum" class="form-control">
						</div>
					</div>
					
					<br>
					
					<div class="row">
						<label class="col-md-2 text-right">选择用户：<span style="color:red;">*</span></label>
						<div class="col-md-10">
							<textarea id="object" class="form-control" rows="5"
								name="userIds" style="width:60%"></textarea>
						</div>
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
		<!--  -->
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/js/coupon/couponissue/systemPushAddInit.js"></script>
</body>
</html>
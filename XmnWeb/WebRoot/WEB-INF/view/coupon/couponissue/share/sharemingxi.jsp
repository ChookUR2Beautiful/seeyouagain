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
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="sharemingxiForm">
				<input type="hidden" name="issueId" value="${issueId}"><input
					type="hidden" name="type" value="${type}">
				
				<div class="form-group">
					<label class="col-md-1 control-label">优惠券编号：</label>
					<div class="col-md-3">
						<input type="text" value="" name="cid" class="form-control">
					</div>
					<label class="col-md-1 control-label">使用状态：</label>
					<div class="col-md-3">
						<select class="form-control" name="userStatus">
							<option value="" selected="selected">全部</option>
							<option value="0">未使用</option>
							<option value="2">已使用</option>
							<option value="1">已锁定</option>
						</select>
					</div>
					<label class="col-md-1 control-label">领取时间：</label>
					<div class="col-md-3">
						<div class="input-group" style="width:93%;">
							<input type="text" name="getTimeStart"
								class="form-control form-date"> <span
								class="input-group-addon fix-border" style="width: 10%;">--</span> <input
								type="text" name="getTimeEnd" class="form-control form-date">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-1 control-label">优惠券名称：</label>
					<div class="col-md-3">
						<input type="text" value="" class="form-control" name="cname">
					</div>
					<label class="col-md-1 control-label">获取状态：</label>
					<div class="col-md-3">
						<select class="form-control" name="getStatus">
							<option value="" selected="selected">全部</option>
							<option value="0">未获取</option>
							<option value="1">已获取</option>
							<option value="2">已锁定</option>
						</select>
					</div>
					<label class="col-md-1 control-label">发行时间：</label>
					<div class="col-md-3">
						<div class="input-group" style="width:93%;">
							<input type="text" name="dateIssueStart"
								class="form-control form-date"> <span
								class="input-group-addon fix-border" style="width: 10%;">--</span> <input
								type="text" name="dateIssueEnd" class="form-control form-date">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-1 control-label">优惠券SN码：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="serial" />
					</div>
					
					<div class="submit" style="margin-right: 20px;">
						<input class="submit radius-3" type="button" value="查询全部"
							data-bus='query' style="width: 13%;"/><input class="reset radius-3" type="reset"
							value="重置全部" data-bus='reset' style="width: 13%;"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;"></div>
			<div id="sharemingxiList"></div>
		</div>
	</div>
	</div>
	<script type="text/json" id="permissions">
		{}
	</script>
	<jsp:include page="../../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script type="text/javascript">
		inserTitle(
				' > 优惠券管理  > <a href="coupon/couponissue/mansongmingxi/init.jhtml" target="right">分享发放优惠券明细</a>',
				'activityManagement', true);
	</script>
	<script src="<%=path%>/js/coupon/couponissue/share/sharemingxi.js"></script>
</body>
</html>
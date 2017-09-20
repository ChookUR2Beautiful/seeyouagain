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
<title>合作商</title>
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
			<form class="form-horizontal" role="form" method="post"
				id="searchForm">
				<input type="hidden"
					value="${not empty param.page ? param.page : '1'}" name="page" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>合作商编号:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="jointid" value="${param.jointid}"></td>
							<td class="col-md-1"><h5>法人姓名:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="legalperson" value="${param.legalperson}"></td>
							<td class="col-md-1"><h5>开通时间:</h5></td>
							<td class="col-md-3">
								<div class="input-group">
									<input type="text" name="sdateStart"
										placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime" value="${param.sdateStart}">
									<span class="input-group-addon fix-border">--</span><input
										type="text" name="sdateEnd" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime" value="${param.sdateEnd}">
								</div>
							</td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>状态：</h5></td>
							<td class="col-md-3"><select class="form-control"
								name="status" value="${param.status}">
									<option value="">全部</option>
									<option value="0" ${param.status == '0' ? "selected" : ""}>未启动</option>
									<option value="1" ${param.status == '1' ? "selected" : ""}>已启动</option>
							</select></td>

							<td class="col-md-1"><h5>企业名称:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="corporate" value="${param.corporate}"></td>
							<td class="col-md-1"><h5>联系手机:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="phoneid" value="${param.phoneid}"></td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>登录账号:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="account" value="${param.account}"></td>
							<td class="col-md-1"><h5>区域查询:</h5></td>
							<td class="col-md-3"><div class="input-group"
									style="width:100%" id="ld"
									initValue="${not empty param.area ? param.area : (not empty param.city ? param.city : param.province)}"></div>
							</td>
							<td colspan="2" class="col-md-4">
								<div class="submit text-left">
									<input class="submit radius-3"
										style="width:49.5%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49.5%;margin-right:0;" type="reset" value="重置全部"
										data-bus='reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${not empty btnAu['business_cooperation/joint/add']}">
					<a type="button" class="btn btn-success" id="addJoint"
						href="business_cooperation/joint/add/init.jhtml?isType=add"><span
						class="icon-plus"></span>添加</a>
				</c:if>
				<%-- <c:if test="${not empty btnAu['business_cooperation/joint/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>删除</button></c:if> --%>
				<c:if test="${not empty btnAu['business_cooperation/joint/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>导出
					</button>
				</c:if>
			</div>
			<div id="jointList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${btnAu['business_cooperation/joint/update']}',getWallet:'${btnAu['business_cooperation/joint/getWallet']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_cooperation/joint.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

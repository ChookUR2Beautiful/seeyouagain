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
<title>合作商业务员</title>
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
				<table style="width:100%;">
					<tbody>
						<tr>
							 <table style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="width:5%;"><h5>合作商编号:</h5></td>
                            <td style="width:18%;"><input type="text" class="form-control"	 id="jointid" name="jointid" style = "width:90%;"/></td>
                            <td style="width:5%;"><h5>企业名称:</h5></td>
                            <td style="width:18%;"><input type="text" class="form-control" id="corporate" name="corporate" style = "width:90%;"/></td>
							<td colspan="2" class="col-md-3">
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
				<c:if test="${not empty btnAu['business_cooperation/salesmanManagement/add']}">
					<a type="button" class="btn btn-success" id="addJoint"
						href="business_cooperation/salesmanManagement/add/init.jhtml?isType=add"><span
						class="icon-plus"></span>添加</a>
				</c:if>
			</div>
			<div id="salesmanList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${btnAu['business_cooperation/salesmanManagement/update']}',start:'${btnAu['business_cooperation/salesmanManagement/start']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_cooperation/salesmanManagementList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

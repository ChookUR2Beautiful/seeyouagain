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
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

tr td a {
	color: #CC3333;
}

tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
</style>
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width: 10%;"><h5>用户编号:</h5></td>
							<td style="width: 20%;"><input type="text" class="form-control" name="uid"></td>
							<td style="width: 10%;"><h5>手机号:</h5></td>
							<td style="width: 20%;"><input type="text" class="form-control" name="phone"></td>
							<td>
								<div class="submit">
									<input class="submit radius" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius" type="reset"
										value="重置全部" data-bus='reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-body">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${!empty btnAu['coupon/couponissue/sendshortmessage/userlist/init']}">
					<button type="button" class="btn btn-success" id="sendMessageBtn">
						<span class="icon-plus"></span>发送短信
					</button>
				</c:if>
			</div>
			<div id="userlist"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{userlist:'${ btnAu['coupon/couponissue/sendshortmessage/userlist/init']}',sendmessage:'${ btnAu['coupon/couponissue/sendshortmessage/sendmessage']}'}</script>
	<jsp:include page="../../../common.jsp"></jsp:include>
	<script type="text/javascript">
		var issueId = ${issueId};
	</script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script
		src="<%=path%>/js/coupon/couponissue/sendshortmessage/userList.js"></script>
</body>
</html>
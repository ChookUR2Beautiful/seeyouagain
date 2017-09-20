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
<title>广告轮播</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<style>
.submit {
	text-align: left;
}
</style>
</head>

<head>
<base href="<%=basePath%>">
<title>广告轮播</title>
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
<style>
.submit {
	text-align: left;
}
</style>
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
				<input style="display:none;" id="isshow" name="isshow" />
				<table>
					<tbody>
						<tr>
							<td class="col-md-1"><h5>&nbsp;&nbsp;广告文本：</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="content"></td>
							<td class="col-md-1"><h5>区域查询:</h5></td>
							<td class="col-md-3">
								<div class="input-group" id="ld" style="width:100%"></div>
							</td>
							<td class="col-md-1"><h5>开始时间:</h5></td>
							<td class="col-md-3">
								<div class="input-group">
									<input type="text" class="form-control form-datetime"
										name="startTimeStart" value="${param.startTimeStart}">
									<span class="input-group-addon fix-border">--</span> <input
										type="text" class="form-control form-datetime"
										name="startTimeEnd" value="${param.startTimeEnd}">
								</div>
							</td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>&nbsp;&nbsp;广告类型：</h5></td>
							<td class="col-md-3"><select class="form-control"
								name="type">
									<option value="">全部</option>
									<option value="1" ${advertising.type==1?"selected":""}>寻蜜鸟客户端美食</option>
									<%-- <option value="2" ${advertising.type==2?"selected":""}>商户客户端</option>
									<option value="3" ${advertising.type==3?"selected":""}>合作商客户端</option> --%>
									<option value="4" ${advertising.type==4?"selected":""}>寻蜜客圈子广告</option>
									<option value="6" ${advertising.type==6?"selected":""}>微信商城生鲜</option>
									<option value="7" ${advertising.type==7?"selected":""}>微信商城附近美食</option>
									<option value="8" ${advertising.type==8?"selected":""}>寻蜜鸟客户端生鲜</option>
									<option value="9" ${advertising.type==9?"selected":""}>寻蜜客主页</option>
							</select></td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;是否全国:</h5></td>
							<td class="col-md-3"><select name="isall"
								class="form-control">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
							</select></td>
							<td colspan="2" class="col-md-4">
								<div class="submit text-left">
									<input class="submit radius-3"
										style="width:49.5%;margin-right:0;" type="button" value="查询全部"
										data-bus="query"> <input class="reset radius-3"
										style="width:49.5%;margin-right:0;" type="reset" value="重置全部"
										data-bus="reset">
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
				<c:if
					test="${null!=btnAu['user_terminal/advertising/add'] && btnAu['user_terminal/advertising/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-width="800px"
						data-url="user_terminal/advertising/add/init.jhtml"
						data-toggle="modal">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
				<%--<c:if test="${null!=btnAu['user_terminal/advertising/delete'] && btnAu['user_terminal/advertising/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if> --%>
				<c:if
					test="${null!=btnAu['user_terminal/advertising/export'] && btnAu['user_terminal/advertising/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>&nbsp;导出
					</button>
				</c:if>
				<button type="button" class="btn btn-success status"
					onclick="queryStatus(this);">&nbsp;全部</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,1);">&nbsp;待上线</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,0);">&nbsp;已上线</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,2);">&nbsp;已下线</button>
			</div>
			<div id="userAdvertisingList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/advertising/update']}'<%--,del:'${btnAu['user_terminal/advertising/delete'] }'--%>,online:'${ btnAu['user_terminal/advertising/online']}',offline:'${ btnAu['user_terminal/advertising/offline']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/user_terminal/user_advertising.js"></script>
	<%-- 	<script src="<%=path%>/js/common/advertising.js"></script> --%>
</body>
</html>

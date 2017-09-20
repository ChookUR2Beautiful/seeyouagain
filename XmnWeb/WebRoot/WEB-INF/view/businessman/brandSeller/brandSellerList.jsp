<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>品牌店列表</title>

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
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="brandSellerForm">
				<%-- <input style="display:none;" name="isBrand"> --%>
				<input type="hidden" name="isBrand">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:10%;"><h5>&nbsp;&nbsp;品牌店编号:</h5></td>
							<td style="width:23% !important;"><input type="text"
								class="form-control" name="brandId" value="${param.brandId}"
								style="width:90%"></td>
							<td style="width:10%;"><h5>&nbsp;&nbsp;是否全国:</h5></td>
							<td style="width:23% !important;"><select name="isAll"
								class="form-control" style="width:89%">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
							</select></td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td style="width:10%;"><h5>&nbsp;&nbsp;品牌店名称:</h5></td>
							<td style="width:23% !important;"><input type="text"
								class="form-control" name="brandName" value="${param.brandName}"
								style="width:90%"></td>

							<td style="width:10%;"><h5>&nbsp;&nbsp;区域查询:</h5></td>
							<td style="width:23% !important;">
								<div class="input-group" id="ld"
									<c:choose>
										<c:when test="${!empty param.area}">
											initValue=" ${param.area}"
										</c:when>
										<c:when test="${empty param.area && !empty param.city}">
											initValue=" ${param.city}"
										</c:when>
										<c:otherwise>
											initValue=" ${param.province}"
										</c:otherwise>
									</c:choose>
									style="width:91%"></div>
							</td>
							<td colspan="2" style="width:34%;">
								<div class="submit">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										type="reset" value="重置全部" data-bus="reset" />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page">
				</c:if>
			</form>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['user_terminal/brandSeller/add']}">
					<a type="button" id="addbtn" class="btn btn-success"
						href="user_terminal/brandSeller/add/init.jhtml"><span
						class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<%-- <c:if test="${not empty btnAu['user_terminal/brandSeller/delete']}">
					<button type="button" class="btn btn-danger" id="delete">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if> --%>
				<c:if test="${not empty btnAu['user_terminal/brandSeller/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>&nbsp;导出
					</button>
				</c:if>
				<button type="button" class="btn btn-success status"
					onclick="queryStatus(this);">&nbsp;全部</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,0);">&nbsp;待上线</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,1);">&nbsp;已上线</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,2);">&nbsp;已下线</button>
			</div>
			<div id="brandSellerList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{add:'${btnAu['user_terminal/brandSeller/add']}',<%--del:'${btnAu['user_terminal/brandSeller/delete']}',--%>update:'${btnAu['user_terminal/brandSeller/update']}',online:'${btnAu['user_terminal/brandSeller/online']}',offline:'${btnAu['user_terminal/brandSeller/offline']}',branchSellerList:'${btnAu['user_terminal/brandSeller/branchSeller/init']}'}
    </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/user_terminal/brandSeller/brandSellerList.js"></script>

</body>
</html>

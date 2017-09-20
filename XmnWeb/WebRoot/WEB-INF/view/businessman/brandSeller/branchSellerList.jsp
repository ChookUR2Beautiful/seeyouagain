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

<title>品牌店子店列表</title>

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
				<%--<input style="display:none;" name="brandId" value="${brandId}"> --%>
				<input type="hidden" name="brandId" value="${brandId}">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>&nbsp;&nbsp;商家编号:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="sellerid" value="${param.sellerid}" style="width:90%"></td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="sellername" value="${param.sellername}" style="width:90%"></td>
							<td class="col-md-4"><div class="submit text-left">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										type="reset" value="重置全部" data-bus="reset" />
								</div></td>
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
				<button type="button" class="btn btn-success status"
					onclick="queryStatus(this,${brandId});">已添加</button>
				<button type="button" class="btn btn-default status"
					onclick="queryStatus(this,null);">未添加</button>
			</div>
			<div id="brandBranchSellerList"></div>
		</div>
	</div>
	<script type="text/javascript">
		var brandId = "${brandId}";
	</script>
	<script type="text/json" id="permissions">
		{addBranchSeller:'${btnAu['user_terminal/brandSeller/branchSeller/add']}',delBranchSeller:'${btnAu['user_terminal/brandSeller/branchSeller/delete']}'}
    </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script
		src="<%=path%>/js/user_terminal/brandSeller/brandBranchSellerList.js"></script>

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"  value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" 
				id="brandForm"	>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>级别名称:</h5></td>
							<td style="width:18%;"><input type="text"
								class="form-control" name="levelName" style="width:90%;" /></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' style="width:43%;flaot:left;margin:0 2%;" />
									<input class="reset radius-3" type="reset" value="重置全部"
										data-bus='reset' style="width:43%;flaot:left;margin:0 2%;" />
								</div>
							</td>
							<td colspan="2" style="width:18%;"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	</div>
	<div class="panel">
		<div class="panel-body">
			<div class="btn-group">
			<c:if test="${!empty btnAu['groupLevel/update'] }">
				<button type="button" class="btn" data-type="ajax"   
					data-url="groupLevel/update/init.jhtml" data-toggle="modal">添加</button>
			</c:if>
			</div>
			<div class="panel-body data">
				<div id="levelList"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/json" id="permissions">{
	  edit:'${ btnAu['groupLevel/update']}',
	}</script>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script
	src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/ux/js/scrollTablel.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=path%>/js/live_anchor/groupLevelList.js"></script>
</html>

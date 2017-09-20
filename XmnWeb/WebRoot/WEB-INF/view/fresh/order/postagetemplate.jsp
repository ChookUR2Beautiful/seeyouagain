<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>用户简历列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/resources/web/css/jquery.validate.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style>
.submit {
	float: left;
}

.btn-add {
	background: #FF5C5C;
	width: 160px;
	margin-right: 20px;
	border: 1px solid #FF5C5C;
	line-height: 20px;
	text-align: center;
	font-size: 16px;
}
</style>
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<input type="hidden" id="checkbox"
		value="${btnAu['user_terminal/banner/delete']}" />
	<input type="hidden" id="picSerUrl"
		value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<!-- 运费模板权限 -->
  	<input type="hidden" id="showPostage" value="${btnAu['fresh/postagetemplate/list']}" />
  	<input type="hidden" id="updatePostage" value="${btnAu['fresh/postagetemplate/update']}" />
  	<input type="hidden" id="deletePostage" value="${btnAu['fresh/postagetemplate/delete']}" />
  	<input type="hidden" id="copyPostage" value="${btnAu['fresh/postagetemplate/add/copy']}" />
  	<input type="hidden" id="addPostage" value="${btnAu['fresh/postagetemplate/add']}" />
	<div class="panel" style="width:60%;">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get"
				id="searchForm">
				<div class="btn-group" style="margin-bottom: 5px;">
					<c:if
						test="${not empty btnAu['fresh/postagetemplate/add']&& empty postageTemplateList}">
						<a type="button" class="btn btn-success" id="addPostageTemplate"
							href="fresh/postagetemplate/add/init.jhtml?isType=add"><span
							class="icon-plus"></span>新建模板</a>
					</c:if>
				</div>
				<div id="data"></div>
				<div id="page"></div>
			</form>
		</div>
	</div>
	<div class="hidden" id="dataTemp"></div><!-- 用于接收构建临时表格html -->
	<!-- 操作结果提示层 -->
	<div class="modal fade" id="sm_reslut_window" data-position="100px">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/fresh/order/postagetemplate.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
	<script src="<%=path%>/resources/datapicker/WdatePicker.js"
		type="text/javascript"></script>
	<!-- 分页插件 -->
	<link href="<%=path%>/resources/myPaginationV6.0/web/resources/css/style.css" rel="stylesheet" type="text/css" />
	 <link href="<%=path%>/resources/myPaginationV6.0/web/resources/js/msgbox/msgbox.css" rel="stylesheet" type="text/css"/>
	 <link href="<%=path%>/resources/myPaginationV6.0/web/resources/js/myPagination/page.css" rel="stylesheet" type="text/css"/>
	 <script src="<%=path%>/resources/myPaginationV6.0/web/resources/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
	 <script src="<%=path%>/resources/myPaginationV6.0/web/resources/js/msgbox/msgbox.js" language="javascript" type="text/javascript" ></script>
</body>
</html>

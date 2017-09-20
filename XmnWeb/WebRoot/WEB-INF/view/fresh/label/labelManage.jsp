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
<title>礼物</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;">	
		
	</div>
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">活动标签</a></li>
		<li class=""><a href="#tab2" data-toggle="tab">常规标签</a></li>
	</ul>
	
	<div class="tab-content">
		<div id="tab1" class="tab-pane in active">
			<div class="panel panel-default">
				<div class="panel-body data">
					<!-- 活动标签 -->
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if
							test="${null!=btnAu['freshLabel/manage/add'] && btnAu['freshLabel/manage/add']}">
							<button type="button" id="addFreshLabelBto"
								class="btn btn-success" data-type="ajax" data-title="添加标签"
								data-url="freshLabel/manage/add/init.jhtml?type=1"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;添加
							</button>
						</c:if>
					</div>
					<div id="labelList"></div>
				</div>
			</div>
		</div>

		<div id="tab2" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body data">
				    <!-- 常规标签 -->
					<div class="btn-group" style="margin-bottom: 5px;">

					</div>
					<div id="normalLabelList"></div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/json" id="permissions">
		{
	       update:'${ btnAu['freshLabel/manage/update']}',
		   delete:'${ btnAu['freshLabel/manage/delete']}',
           setStatus:'${ btnAu['freshLabel/manage/setStatus']}'
		}
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/fresh/label/labelManage.js?v=1.0.4"></script>
</body>
</html>

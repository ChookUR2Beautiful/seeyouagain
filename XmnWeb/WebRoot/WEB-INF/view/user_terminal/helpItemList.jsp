<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>帮助条目分类</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
	   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
 	<form class="form-horizontal" role="form" method="post"  id="searchForm">
	</form>
	<%--
  	<div class="panel">
		<div class="panel-body">
		</div>
	</div>
	 --%>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['user_terminal/help_manage/item/add']}">
					<a type="button" class="btn btn-success" id="addBtn" data-type="ajax"  data-url="user_terminal/help_manage/item/add/init.jhtml?isType=add"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<%-- 
				<c:if test="${!empty btnAu['user_terminal/help_manage/export']}">
				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;添加</button>
				</c:if>
				--%>
			</div>
			<div id="itemList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/help_manage/item/update']}',delete:'${ btnAu['user_terminal/help_manage/item/delete']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/user_terminal/helpItem.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		limitedDate({form:"#searchForm",startDateName:"sdateStart",endDateName:"sdateEnd"});
		
	});
	</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
  </body>
</html>

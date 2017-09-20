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
    <title>帮助条目</title>
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
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr >
							<td style="width:5%;"><h5>&nbsp;&nbsp;帮助分类:</h5></td>
							<td style="width:20%;">
								<select class="form-control" id="itemId" name="itemId" style="width:90%">
								 </select>
							</td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;帮助标题:</h5></td>
							<td style="width:20%;"><input type="text" class="form-control"  name="title" style="width:90%"></td>
							<td></td>
							<td>
								<div class="submit submit-sp" style="float:left; margin-left: 10px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query'  />
									<input class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>

					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['user_terminal/help_manage/add']}">
					<a type="button" class="btn btn-success" id="addBtn" data-type="ajax"  data-url="user_terminal/help_manage/add/init.jhtml?isType=add"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${!empty btnAu['user_terminal/help_manage/item/init']}">
				<a type="button" class="btn btn-success" id="item"  href="user_terminal/help_manage/item/init.jhtml" ><span class="icon-plus"></span>&nbsp;分类管理</a>
				</c:if>
				<%-- 
				<c:if test="${!empty btnAu['user_terminal/help_manage/export']}">
				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;添加</button>
				</c:if>
				--%>
			</div>
			<div id="helpList"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/help_manage/update']}',delete:'${ btnAu['user_terminal/help_manage/delete']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/user_terminal/helpInfo.js"></script>
  </body>
</html>

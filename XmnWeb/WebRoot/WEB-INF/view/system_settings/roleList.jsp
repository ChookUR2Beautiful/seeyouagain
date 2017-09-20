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
    <title>角色</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:80px;"><h5>&nbsp;&nbsp;角色名称:</h5></td>
							<td style="width:350px;"><input type="text" class="form-control"  name="roleName" value="${param.roleName }" style="width:90%"></td>
							<td align="left"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
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
				<c:if test="${!empty btnAu['system_settings/role/add']}"><button type="button" class="btn btn-success"  data-type="ajax"   data-url="system_settings/role/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button></c:if>	  	  
				<!-- 
				<div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="roleList"></div>
		</div>																																															
	</div>
	<script type="text/json" id="permissions">{xg:'${ btnAu['system_settings/role/update']}',nz:'${btnAu['system_settings/role/updateNz'] }',qx :'${btnAu['system_settings/roleAuthority'] }',qyqx :'${btnAu['system_settings/dataAuthority/init'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/system_settings/role.js"></script>
  </body>
</html>

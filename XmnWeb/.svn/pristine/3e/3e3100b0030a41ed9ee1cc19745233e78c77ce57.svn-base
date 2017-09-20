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
    <title>点赞管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">  
	<style type="text/css">
    .submit{text-align: left;}
    </style> 
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:8%;"><h5>&nbsp;&nbsp;商家名称：</h5></td>
							<td style="width:24%;"><input type="text" class="form-control"  name=sellername  style="width:80%"></td>
						
							
							<td style="width:8%;"><h5>&nbsp;&nbsp;推荐菜品：</h5></td>
							<td style="width:24%;"><input type="text" class="form-control"  name="dishes"  style="width:80%"></td>
							
							
							<td style="width:8%;"><h5>&nbsp;&nbsp; 点赞数：</h5></td>
							<td style="width:24%;">
							<input type="text" class="form-control"  name="numberSt"  style="width:40%;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" class="form-control"  name="numberEd"  style="width:40%;float:left">
							</td style="width:28%;">
							<td ><div class="submit" style="width:500px;"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['businessman/sellerDetailed/export'] && btnAu['businessman/sellerDetailed/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>
			</div>
			<div id="sellerDetailedList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['businessman/sellerDetailed/update']}',export:'${btnAu['businessman/sellerDetailed/export'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/businessman/sellerDetailed.js"></script>
  </body>
</html>

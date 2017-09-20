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
    <title>操作日志</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>&nbsp;&nbsp;操作者:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"  name="username" ></td>
						
							<td class="col-md-1"><h5>&nbsp;&nbsp;动作类型:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"  name="logNote" ></td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>&nbsp;&nbsp;操作时间:</h5></td>							
							<td class="col-md-3">
							<div class="input-group">
								<input type="text" name ="logDateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime">
								<span class="input-group-addon fix-border">--</span>
								<input type="text" name ="logDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime">
							</div>
							</td>
							<td class="col-md-1"><h5>&nbsp;&nbsp;操作描述:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"  name="logRemark"></td>
							<td class="col-md-4"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<!-- <button type="button" class="btn btn-success"  data-type="ajax"   data-url="system_settings/log/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button> -->
				<%-- <c:if test="${!empty btnAu['system_settings/log/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if> --%>
			<!-- 	<div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="logList"></div>
		</div>
	</div>
	<!-- <script type="text/json" id="permissions">{del:'${ btnAu['system_settings/log/delete']}'}</script> -->
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/system_settings/log.js"></script>
  </body>
</html>

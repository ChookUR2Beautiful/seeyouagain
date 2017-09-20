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
<title>通告</title>
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
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" id="isAll" name="isAll" value="">
				<input type="hidden" id="anchorId" name="anchorId" value="${liver.id }">
				<input type="hidden" name="currentMonth" value="${currentMonth }">
				
				<div class="form-group">
					<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播日期:</h5></td>
										<td style="width:25%;">
											<input type="text" id="startTime" name="startTime" value="" placeholder="开始时间" class="form-control form-datetime" style="width:40%;float:left">
											<label style="width:5%;float: left;">&nbsp;--&nbsp;</label>
											<input type="text" id="endTime" name="endTime" value="" placeholder="结束时间" class="form-control form-datetime" style="width:40%;float:left">
										</td>
										<!-- 开播类型 ： 0 通告开播    1 自定义开播  -->
										<td style="width:5%;"><h5>开播类型:</h5></td>
										<td style="width:30%;">
											<select class="form-control"  name="liveStartType" style = "width:66%;">
												<option value="">全部</option>
												<option value="0">通告开播</option>
												<option value="1">自定义开播</option>
											</select>
										</td>
										<td colspan="2" style="width:35%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['liveRecord/manage/export']}">
						<button type="button" id="export" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="recordList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
	  update:'${ btnAu['liveRecord/manage/update/init']}',
	  detail:'${ btnAu['liveRecord/manage/detail/init']}',
	  delete:'${ btnAu['liveRecord/manage/delete']}',
	  setAdvance:'${ btnAu['liveRecord/manage/setAdvance']}',
	  down:'${ btnAu['liveRecord/manage/down']}',
	  up:'${ btnAu['liveRecord/manage/up']}'
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/liveTimeInit.js?v=1.0.4"></script>
</body>
</html>

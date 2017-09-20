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
    <title>营销活动统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">   
  </head>
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<input type="hidden" value="${not empty param.page ? param.page : '1'}" name="page"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
							<td style="width:400px;"><select type="text" class="form-control" id="aid" name="aid" style="width:90%" initValue="${param.aid}"></td>	
							<td colspan="4"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' onclick="restOption()"></div></td>
						</tr>
						<tr><td height="10px"></td></tr>
						<tr>
						    <td colspan="6">
						    <table class="table table-hover table-bordered table-striped info">
						    <!-- <tr><th style="width:10%;">活动类型</th><th colspan="3" id="type"></th></tr> -->
						    <tr><th style="width:10%;"><h5>活动开始时间</h5></th><td style="font-size: small;vertical-align: middle;width:40%;" id="startDate"></td><th style="width:10%;"><h5>活动结束时间</h5></th><td id="endDate" style="font-size: small;vertical-align: middle;"></td></tr>
<!-- 						    <tr><th>活动描述</th><td colspan="3" id="eescription"></td></tr>
						    <tr><th>活动规则</th><td colspan="3" id="rule"></td></tr> -->
						    </table></td>
						</tr>

					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			</div>
			<div id="activityList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{check:'${ btnAu['marketingManagement/activityPrize/init']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/marketingmanagement/activityStatistics.js"></script>
</body>
</html>

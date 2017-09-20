<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商户列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
.submit{text-align: left;}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="roulleteRecordForm">
			<input type="hidden" name="activityId" value="${rRecord.activityId}">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;活动手机:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="phone" name="phone"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;奖品状态:</h5></td>
							<td style="width:25%;"><select style="width:90%" 
									class="form-control" id="useStatus" name="useStatus">
										<option value="">全部</option>
										<option value="0">未使用</option>
										<option value="1">锁定</option>
										<option value="2">已试用</option>
								</select></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;获得时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="sTime" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:36.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eTime" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:37%;float:left">
							</td>
						</tr>
							<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp; 使用时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="sTimeStr" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:36.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eTimeStr" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:37%;float:left">
							</td>
							<td></td>
							<td></td>
							<td colspan="2" style="width:30%;">
								<div class="submit">&nbsp;&nbsp;
								<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
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
			<div id="roulleteRecordDiv"></div>
		</div>
	</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/activity/roulleteDetailList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

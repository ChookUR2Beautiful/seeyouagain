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
			<form class="form-horizontal" role="form"  id="celebrityOrderForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;订单编号:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="orderNo" name="orderNo"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商户名称:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="sellername" name="sellername"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商户手机:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="phoneid" name="phoneid"  style="width:90%">
							</td>
							
						</tr>
							<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;名嘴名称:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="name" name="name"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;名嘴手机:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="phone" name="phone"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;订单状态:</h5></td>
							<td style="width:25%;"><select style="width:90%" 
									class="form-control" id="status" name="status">
										<option value="">全部</option>
										<option value="1">接&nbsp;&nbsp;单</option>
										<option value="2">品尝美食</option>
										<option value="3">撰写食评</option>
										<option value="4">等待发布</option>
										<option value="5">发布成功</option>
										<option value="6">订单取消</option>
								</select></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp; 活动时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="sdate" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:36.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="edate" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:37%;float:left">
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
			<button type="button" class="btn btn-success"
					onclick="queryBM(this,'');" name="bumen">全&nbsp;&nbsp;部</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'1');" name="bumen">接&nbsp;&nbsp;单</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'2');" name="bumen">品尝美食</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'3');" name="bumen">撰写食评</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'4');" name="bumen">等待发布</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'5');" name="bumen">发布成功</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'6');" name="bumen">订单取消</button>
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
			</div>
			<div id="celebrityOrderDiv"></div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
	context='${pageContext.request.contextPath}';
	</script>
	<script type="text/json" id="permissions">{list:'${btnAu['billmanagerment/celebraty/list']}',update:'${btnAu['billmanagerment/celebraty/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/billmanagerment/celebrityOrderList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

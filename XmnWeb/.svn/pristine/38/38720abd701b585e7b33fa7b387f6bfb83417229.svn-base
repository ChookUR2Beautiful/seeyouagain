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
			<form class="form-horizontal" role="form"  id="redPackageForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;活动名称:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="redpacketName" name="redpacketName"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商户名称:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="sellerName" name="sellerName"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp; 活动时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="beginDate" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:36.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="endDate" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:37%;float:left">
							</td>
						</tr>
							<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;红包类型:</h5></td>
							<td style="width:25%;"><select style="width:90%"
									class="form-control" id="redpacketType" name="redpacketType">
										<option value="">全部</option>
										<option value="0">分享引流红包</option>
										<option value="1">限时到店红包</option>
										<option value="2">消费满赠红包</option>
										<option value="3">推荐消费红包</option>
										<option value="4">普通抽奖红包</option>
								</select></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;红包状态:</h5></td>
							<td style="width:25%;"><select style="width:90%" 
									class="form-control" id="status" name="status">
										<option value="">全部</option>
										<option value="0">已结束</option>
										<option value="1">激活</option>
										<option value="2">活动占用</option>
										<option value="3">进行中</option>
										<option value="4">未支付</option>
								</select></td>
							
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
			<div id="redPackageDiv"></div>
		</div>
	</div>
	</div>
	<script type="text/json" id="permissions">{list:'${btnAu['businessman/redPackage/init/list']}',update:'${btnAu['businessman/redPackage/update']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/redPackage/redPackageList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

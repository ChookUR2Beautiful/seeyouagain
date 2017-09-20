<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>会员充值记录</title>
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
			<form class="form-horizontal" role="form"  id="userForm">
				<input type="hidden" name="uid" value="${rCardRecord.uid}">
				<input type="hidden" name="sellerid" value="${rCardRecord.sellerid}">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:15%;">
								<input type="text" class="form-control"  id="userName" name="userName"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;用户手机:</h5></td>
							<td style="width:15%;">
								<input type="text" class="form-control"  id="phone" name="phone"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;充值商户:</h5></td>
							<td style="width:15%;">
								<input type="text" class="form-control"  id="sellername" name="sellername"  style="width:90%">
							</td>
							<td style="width:5%"><h5>支付时间：</h5></td>
							<td style="width:20%;">
								<input type="text" name ="zDate" placeholder="yyyy-MM-dd HH:mm" class="form-control form-datetime"style="width:41%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eDate" placeholder="yyyy-MM-dd HH:mm" class="form-control form-datetime" style="width:42%;float:left"></td>
							</td>
						</tr>
						<tr>
							<td colspan="6"></td>
							<td colspan="2" style="width:50%;">
								<div class="submit">
									&nbsp;&nbsp; <input class="submit radius-3" type="button"
										value="查询全部" data-bus='query' /> <input
										class="reset radius-3" type="reset" value="重置全部"
										data-bus='reset' />
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
				<c:if test="${null!=btnAu['packageOrder/manage/export'] && btnAu['packageOrder/manage/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="userFormDiv"></div>
		</div>
	</div>
	</div>
	
 	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/valueCard/rechargeCardList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/datapicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>

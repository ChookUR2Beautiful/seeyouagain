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
			<form class="form-horizontal" role="form"  id="sellerFromId">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="sellerid" name="sellerid"  style="width:90%" value="${param.sellerid}">
							</td>
							<td style="width:5%;"><h5>所属区域：</h5></td>
							<td style="width:25%;">
									<div class="input-group" id="ld" style="width:90%">
								    </div>
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp; 加入时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="signdateStart" value="${ param.signdateStart}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:36.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="signdateEnd"  value="${ param.signdateEnd}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:37%;float:left">
							</td>
						</tr>
							<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="sellername" name="sellername"  style="width:90%" value="${param.sellername}">
							</td>
							
							<td style="width:5%;"><h5>评论总数：</h5></td>
							<td style="width:25%;">
								<input type="text" name ="commentCountStart" value="${param.commentCountStart}" class="form-control"style="width:41%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="commentCountEnd"    value="${param.commentCountEnd}" class="form-control " style="width:42%;float:left">
							</td>
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
				<!-- <button type="button" class="btn btn-success" id="add">
					<span class="icon-plus"></span>&nbsp;添加
				</button> -->
				<!-- <button type="button" class="btn btn-danger" id="delete">
					<span class="icon-remove"></span>&nbsp;删除
				</button> -->
			</div>
			<div id="sellerInfoDiv"></div>
		</div>
	</div>
	</div>
	<script type="text/json" id="permissions">{commentInit:'${btnAu['businessman/comment/init']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/commentSeller.js"></script>
</body>
</html>

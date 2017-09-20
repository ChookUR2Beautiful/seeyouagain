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
<title>推荐商家管理</title>
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
				<!-- 直播类型 -1 初始 0 预告 1 正在直播  2 回放  3 无回放 -->
				<input type="hidden" id="zhiboType" name="zhiboType" value="">
				<input type="hidden" id="anchorId" name="anchorId" value="${liver.id }">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
								<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
								<td style="width:5%;"><h5>&nbsp;&nbsp;主播手机号:</h5></td>
								<td style="width:25%;"><input type="text" class="form-control"  name="phone" style = "width:85%;"></td>
								<td style="width:5%;"><h5>商家:</h5></td>
								<td style="width:30%;"><input type="text" class="form-control"  name="sellername" style = "width:66%;"></td>
							</tr>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;直播日期:</h5></td>
								<td style="width:25%;"><input type="text" class="form-control form_datetime"  name="liveDate" style = "width:85%;"></td>
								<td style="width:5%;"><h5></h5></td>							
								<td style="width:25%;"> </td>	
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
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div id="recordList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
	  update:'${ btnAu['liveSeller/manage/update/init']}'
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/liveSellerManage.js?v=1.0.1"></script>
</body>
</html>

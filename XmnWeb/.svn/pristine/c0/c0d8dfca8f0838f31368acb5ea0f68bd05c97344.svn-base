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
<title>体验官场次</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;商户名称:</h5></td>
								<td style="width:25%;"><input class="form-control"   name="sellername" style="width:66%;"> </td>
								<td style="width:5%;"><h5>预告名称:</h5></td>
								<td style="width:30%;"><input type="text" class="form-control"  name="zhiboTitle" style = "width:66%;"></td>
								<td style="width:5%;"><h5>体验时间:</h5></td>
								<td style="width:30%;"><input type="text" placeholder="yyyy-MM-dd" class="form-control form-datetime"  name="planStartDate" style = "width:66%;"></td>

							</tr>
							<tr>
								<td style="width:5%;"><h5>状态:</h5></td>
								<td style="width:25%;"><select class="form-control" name="activityState" style = "width:66%;">
										<option value="">请选择</option>
										<option value="1">未开始</option>
										<option value="2">抢位中</option>
										<option value="3">已售罄</option>
										<option value="4">已结束</option>
										<option value="5">已取消</option>
									</select></td>	
								<td></td><td></td><td></td>
								<td colspan="2" >
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style = "width:40%;" />
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style = "width:40%;" />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${null!=btnAu['experienceofficer/activity/add'] && btnAu['livePayOrder/manage/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="添加充值订单" data-url="experienceofficer/activity/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;添加场次
					</button>
				</c:if>
			</div>
			<div id="recordList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
		 update:'${ btnAu['experienceofficer/activity/update']}',
		 cancel:'${ btnAu['experienceofficer/activity/cancel']}',
	}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/experienceofficer/liveExperienceofficerActivity.js?v=1.2"></script>
</body>
</html>

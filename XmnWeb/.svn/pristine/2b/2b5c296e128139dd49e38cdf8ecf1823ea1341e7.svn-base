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
<title>打赏返利管理</title>
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
				<input type="hidden" name="eUid" value="${eUid}">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
								<td style="width:25%;"><input type="text" name="nickname" class="form-control" style="width:46%"></td>
								<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
								<td style="width:25%;"><select class="form-control" id="anchorId2"  name="anchorId"
												initValue="${anchorId}" style="width:98%;">
											</select> </td>
								<td style="width:5%;"><h5>用户手机:</h5></td>
								<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
							</tr>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;打赏时间:</h5></td>
								<td style="width:25%;"><input type="text" name="startTime" value="" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" style="width:46%;float:left">
								<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name="endTime" value="" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" style="width:46%;float:left"></td>
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
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
			
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['liveGivedGift/manage/export']}">
						<button type="button" class="btn btn-default" id="export" ><span class="icon-download-alt"></span>&nbsp;导出
					   </button>
				</c:if>
			</div>
			<div id="giftList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{

	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
		<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/liveGivedGiftSalaryList.js"></script>
</body>
</html>

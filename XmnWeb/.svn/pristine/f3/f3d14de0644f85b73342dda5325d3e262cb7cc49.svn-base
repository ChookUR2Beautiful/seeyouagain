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
<title>企业级推荐人管理</title>
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
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员昵称：</h5></td>
								<td style="width:22%;">
									<input type="text" class="form-control" name="nickname" value="" style = "width:85%;"/>
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员手机号：</h5></td>
								<td style="width:22%;">
									<input type="text" class="form-control" name="phone" value="" style = "width:85%;"/>
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;统计时间：</h5></td>
								<td style="width:30%;">
									<input type="text" id="startTime" name="startTime" value="" placeholder="yyyy-MM-dd" class="form-control form_datetime" style="width:26%;float:left">
									<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
									<input type="text" id="endTime" name="endTime" value="" placeholder="yyyy-MM-dd" class="form-control form_datetime" style="width:26%;float:left">
								</td>
							</tr>
							<tr>
								<td style="width:8%;"><h5></h5></td>							
								<td style="width:22%;"></td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;</h5></td>
								<td style="width:22%;"></td>	
								<td colspan="2" style="width:38%;">
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
				<c:if test="${btnAu['liveReferrer/manage/export']}">
						<button type="button" id="exportAnchor" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出单页数据</button>
				</c:if>
			</div>
			<div id="anchorList"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			rechargeDetail:'${ btnAu['liveReferrer/manage/init/rechargeDetail']}',
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/live_anchor/liveReferrerManage.js"></script>
</body>
</html>

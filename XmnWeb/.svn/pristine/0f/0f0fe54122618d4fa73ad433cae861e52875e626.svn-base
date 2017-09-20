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
<title>直播管理</title>
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
	
	<!-- <ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">正在直播</a></li>
		<li class=""><a href="#tab2" data-toggle="tab">回放</a></li>
	</ul> -->
	
	<div class="tab-content">
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="searchForm">
						<!-- 直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4 无回放 -->
						<input type="hidden" id="zhiboType" name="zhiboTypeParam" value="1,2">
						<input type="hidden" id="anchorId" name="anchorId" value="${liver.id }">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播编号:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
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
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if
							test="${null!=btnAu['liveVideo/manage/robotSet'] && btnAu['liveVideo/manage/robotSet']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="机器人通用配置" data-url="liveVideo/manage/robotSet/init.jhtml"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;机器人通用配置
							</button>
						</c:if>
						<c:if
							test="${null!=btnAu['liveBroadcast/manage'] && btnAu['liveBroadcast/manage']}">
							<a type="button" class="btn btn-success" href="liveBroadcast/manage/init.jhtml">
								<span class="icon-plus"></span>&nbsp;广播消息
							</a>
						</c:if>
					</div>
					<div id="recordList"></div>
				</div>
			</div>
		</div>
		
		<div id="tab2" class="tab-pane">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="historySearchForm">
						<!-- -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4历史通告 5结束直播 -->
					<input type="hidden" id="zhiboTypeParam" name="zhiboTypeParam" value="3,4,5">
						<input type="hidden" id="anchorId" name="anchorId" value="${liver.id }">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播编号:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
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
					<div id="historyList"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
	  update:'${ btnAu['liveVideo/manage/update']}',
	  robotSet:'${ btnAu['liveVideo/manage/robotSet']}',
	  setState:'${ btnAu['liveVideo/manage/setState']}',
	  delete:'${ btnAu['liveVideo/manage/delete']}',
	  enterChatRoom:'${ btnAu['liveVideo/manage/enterChatRoom']}'
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/liveVideoManage.js?v=1.0.11"></script>
</body>
</html>

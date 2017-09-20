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
<title>竞拍活动管理</title>
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
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">全部活动</a></li>
		<li class=""><a href="#tab2" data-toggle="tab">未开始活动</a></li>
		<li class=""><a href="#tab3" data-toggle="tab">进行中活动</a></li>
		<li class=""><a href="#tab4" data-toggle="tab">已结束活动</a></li>
		<li class=""><a href="#tab5" data-toggle="tab">活动流拍</a></li>
	</ul>
	<div class="tab-content">
	
	<!-- 全部DIV start -->
	<div id="tab1" class="tab-pane in active">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="searchForm1">
					<!-- 活动状态 1 全部 、2 未开始活动 、 3进行中活动 、4 已结束活动 、 5 流拍活动 -->
					<input type="hidden" name="activityType" value="1">
					<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;活动标题：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间：</h5></td>
										<td style="width:25%;">
											<input type="text"  name="queryStartTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left">
											<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
											<input type="text"  name="queryEndTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left"></td>
										<td style="width:5%;"><h5></h5></td>
										<td style="width:30%;"></td>
									</tr>
									<tr>
										<td colspan="4" style="width:65%;"></td>
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
					<c:if
						test="${null!=btnAu['freshAuction/manage/add'] && btnAu['freshAuction/manage/add']}">
						<a type="button" class="btn btn-success"  href="freshAuction/manage/add/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
				</div>
				<div id="recordList1"></div>
			</div>
		</div>
	</div>
	<!-- 全部DIV end -->
	
	<!-- 初始div start-->
	<div id="tab2" class="tab-pane">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="searchForm2">
					<!-- 活动状态 1 全部 、2 未开始活动 、 3进行中活动 、4 已结束活动 、 5 流拍活动 -->
					<input type="hidden" name="activityType" value="2">
					<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;活动标题：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间：</h5></td>
										<td style="width:25%;">
											<input type="text"  name="queryStartTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left">
											<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
											<input type="text"  name="queryEndTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left"></td>
										<td style="width:5%;"><h5></h5></td>
										<td style="width:30%;"></td>
									</tr>
									<tr>
										<td colspan="4" style="width:65%;"></td>
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
				<div id="recordList2"></div>
			</div>
		</div>
	</div>
	<!-- 初始div end -->
	
	
	<!-- 预告div start -->
	<div id="tab3" class="tab-pane">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="searchForm3">
					<!-- 活动状态 1 全部 、2 未开始活动 、 3进行中活动 、4 已结束活动 、 5 流拍活动 -->
					<input type="hidden" name="activityType" value="3">
					<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;活动标题：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间：</h5></td>
										<td style="width:25%;">
											<input type="text"  name="queryStartTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left">
											<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
											<input type="text"  name="queryEndTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left"></td>
										<td style="width:5%;"><h5></h5></td>
										<td style="width:30%;"></td>
									</tr>
									<tr>
										<td colspan="4" style="width:65%;"></td>
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
				<div id="recordList3"></div>
			</div>
		</div>
	</div>
	<!-- 预告div end -->
	
	<!-- 直播div start -->
	<div id="tab4" class="tab-pane">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="searchForm4">
					<!-- 活动状态 1 全部 、2 未开始活动 、 3进行中活动 、4 已结束活动 、 5 流拍活动 -->
					<input type="hidden" name="activityType" value="4">
					<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;活动标题：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间：</h5></td>
										<td style="width:25%;">
											<input type="text"  name="queryStartTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left">
											<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
											<input type="text"  name="queryEndTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left"></td>
										<td style="width:5%;"><h5></h5></td>
										<td style="width:30%;"></td>
									</tr>
									<tr>
										<td colspan="4" style="width:65%;"></td>
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
				<div id="recordList4"></div>
			</div>
		</div>
	</div>
	<!-- 直播div end -->
	
	<!-- 历史通告div start -->
	<div id="tab5" class="tab-pane">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="searchForm5">
					<!-- 活动状态 1 全部 、2 未开始活动 、 3进行中活动 、4 已结束活动 、 5 流拍活动 -->
					<input type="hidden" name="activityType" value="5">
					<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;活动标题：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间：</h5></td>
										<td style="width:25%;">
											<input type="text"  name="queryStartTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left">
											<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
											<input type="text"  name="queryEndTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:30%;float:left"></td>
										<td style="width:5%;"><h5></h5></td>
										<td style="width:30%;"></td>
									</tr>
									<tr>
										<td colspan="4" style="width:65%;"></td>
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
				<div id="recordList5"></div>
			</div>
		</div>
	</div>
	<!-- 历史通告div end -->
	
	</div>
	<script type="text/json" id="permissions">{
	  update:'${ btnAu['freshAuction/manage/update']}',
	  biddingList:'${ btnAu['freshAuction/manage/bidding/list']}',
	  freshBillList:'${ btnAu['freshAuction/manage/freshBill/list']}'
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/fresh/freshAuctionManage.js"></script>
</body>
</html>

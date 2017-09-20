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
<link href="<%=path%>/css/live_anchor/liveGivedCountManage.css?v=1.0.1" rel="stylesheet" >
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
					<label class="col-md-1 control-label" >查询时间：</label>
					<div class="col-md-3" style="width: 25%;">
								<select id="timeType" name="timeType" class="form-control" style="width: 45%;">
									<option value='1' selected>昨天</option>
									<option value='2'>最近一周</option>
									<option value='3'>最近一个月</option>
									<option value='4'>自定义时间</option>
								</select>
					</div>
					<div id="timeDiv" style="display:none;">
						<label class="col-md-1 control-label" >时间区间：</label>
						<div class="col-md-3" style="width: 25%;">
									<input type="text" id="startTime" name="startTime" value="" placeholder="yyyy-mm-dd" class="form-control form_datetime" style="width:46%;float:left">
									<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
									<input type="text" id="endTime" name="endTime" value="" placeholder="yyyy-mm-dd" class="form-control form_datetime" style="width:46%;float:left">
						</div>
					</div>
					
					<div class="submit">
						<input class="submit radius-3" type="button" value="查询全部" id="querySubmit"/> 
						<input type="reset" class="reset radius-3" value="重置全部"  id="queryReset"/>
					</div>
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
						<button type="button" id="export" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="content">
				<div class="list-box" >
			            <table border="0" cellspacing="1">
			                <thead>
			                    <tr>
			                        <th width="20%">统计区间</th>
			                        <th width="10%">打赏鸟豆</th>
			                        <th width="10%">打赏金额</th>
			                        <th width="9%">打赏人数</th>
			                        <th width="14%">人均打赏金额</th>
			                        <th width="9%">主播人数</th>
			                        <th width="20%">主播人均获得（元）</th>
			                        <th width="8%">操作</th>
			                    </tr>
			                </thead>
			                <tbody id="generalDiv">
			                    <!-- <tr>
			                        <td>2016.1.1-2016.5.15</td>
			                        <td>10000000豆</td>
			                        <td>10000.00</td>
			                        <td>999</td>
			                        <td>100.00</td>
			                        <td>10</td>
			                        <td>500.00</td>
			                        <td><a href="#">查看流水</a></td>
			                    </tr> -->
			                </tbody>
			            </table>
    		</div>
		    <div class="chart-box">
		        <div class="gift-chart child-box">
		            <h2>礼物打赏统计</h2>
		            <div id="canvasDiv"></div>
		            <p>*10鸟豆=1鸟币&nbsp;&nbsp;&nbsp;&nbsp;*1积分=1鸟币&nbsp;&nbsp;&nbsp;&nbsp;*统计单位鸟币</p>
		        </div>
		        <div class="child-box">
		            <h2>主播获得打赏统计</h2>
		            <div class="list-box">
		                <table border="0" cellspacing="1">
		                    <thead>
		                        <tr>
		                            <th width="34%">主播名称</th>
		                            <th width="33%">占比</th>
		                            <th width="33%">鸟币数</th>
		                        </tr>
		                    </thead>
		                    <tbody id="anchorCountTd">
		                    </tbody>
		                </table>
		            </div>
		            <div id="canvasDiv2"></div>
		            <p>*10鸟豆=1鸟币&nbsp;&nbsp;&nbsp;&nbsp;*统计单位鸟币</p>
		        </div>
		        <div class="child-box">
		            <h2>鸟豆打赏区间人数统计</h2>
		            <div class="list-box">
		                <table border="0" cellspacing="1">
		                    <thead>
		                        <tr>
		                            <th width="30%">区间</th>
		                            <th width="20%">占比</th>
		                            <th width="20%">人数</th>
		                            <th width="20%">人均</th>
		                        </tr>
		                    </thead>
		                    <tbody id="birdBeanZoneCountTd">
		                    </tbody>
		                </table>
		            </div>
		            <div id="canvasDiv3"></div>
		            <p>*10鸟豆=1鸟币&nbsp;&nbsp;&nbsp;&nbsp;*人均单位鸟币</p>
		        </div>
		        <div class="gift-chart child-box">
		            <h2>打赏时间统计</h2>
		            <div id="canvasDiv4"></div>
		            <p>*10鸟豆=1鸟币&nbsp;&nbsp;&nbsp;&nbsp;*统计单位鸟币</p>
		        </div>
		    </div>
			
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{

	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
    <script type="application/javascript" src="<%=path%>/resources/ichart/js/ichart.1.2.1.min.js"></script>
	<script src="<%=path%>/js/live_anchor/liveGivedCountManage.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>优惠券统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
      <li class="active">
        <a href="#tab1" data-toggle="tab">按活动统计</a>
      </li>
      <li class="">
        <a href="#tab2" data-toggle="tab">按优惠券统计</a>
      </li>
    </ul>
    <div class="tab-content">
    <div id="tab1" class="tab-pane in active">
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="activityForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<th style="width:10%;"><h5>活动编号：</h5>
							</th>
							<td style="width:25%;"><input type="text"
								class="form-control" name="issueid" style="width:90%">
							</td>
							<th style="width:10%;"><h5>&nbsp;&nbsp;活动类型：</h5>
							</th>
							<td style="width:25%;"><select class="form-control"
								name="activityType">
									<option value="">全部</option>
									<option value="1">摇一摇</option>
									<option value="2">满就送</option>
									<option value="3">短信发送</option>
							</select></td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<th style="width:10%;"><h5>活动名称：</h5>
							</th>
							<td style="width:25%;"><input type="text"
								class="form-control" name="activityName" style="width:90%">
							</td>
							<th style="width:10%;"><h5>&nbsp;&nbsp;活动状态：</h5>
							</th>
							<td style="width:25%;"><select class="form-control"
								name="status">
									<option value="">全部</option>
									<option value="0">已停止</option>
									<option value="1">已启动</option>
									<option value="2">待启动</option>
							</select></td>
							<td colspan="2"><div class="submit">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /><input class="reset radius-3"
										type="reset" value="重置全部" data-bus='reset'/>
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
			<div id="activity"></div>
		</div>
	</div>
	</div>
	<div id="tab2" class="tab-pane">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="couponForm">
			<input type="hidden" name="userStatus" value="2"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:8%;"><h5>&nbsp;&nbsp;优惠券编号:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="cid" style = "width:90%;"></td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;面额:</h5></td>
							<td style="width:30%;">
								<input type="text" class="form-control"  name="denominationLow" style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" class="form-control"  name="denominationTop" style="width:42%;float:left">
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td style="width:8%;"><h5>&nbsp;&nbsp;优惠券名称:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="cname" style = "width:90%;"></td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;有效开始时间:</h5></td>
							<td style="width:30%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="startDate" style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="endDate" style="width:42%;float:left">
							</td> 
							<td colspan="2">
								<div class="submit">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
						<tr>
							<!-- <td style="width:50px;"><h5>&nbsp;&nbsp;状态:</h5></td>
							<td style="width:160px;">
								<select type="text" class="form-control"  name="withdrawType" style = "width:90%;">
									<option value="">--全部--</option>
									<option value="1">启用</option>
									<option value="2">停用</option>
									<option value="3">已过期</option>
								</select>
							</td> -->
							<!-- <td style="width:50px;"><h5>&nbsp;&nbsp;领取方式:</h5></td>
							<td style="width:160px;">
								<select type="text" class="form-control"  name="userType" style = "width:90%;">
									<option value="">--全部--</option>
									<option value="1">直接发放</option>
									<option value="2">限时发放</option>
									<option value="3">推送领取</option>
									<option value="3">满就送</option>
								</select>
							</td> -->
							<!-- <td style="width:50px"><h5>&nbsp;&nbsp;&nbsp;注册区域:</h5></td>
						   <td style="width:160px">
							    <div class="input-group" id="ysqy" style="width: 90%;">
								</div>							
							</td>  -->
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
			<div id="coupon"></div>
			
		</div>
	</div>
	</div>
	</div>
	<script type="text/json" id="permissions">{unUse:'${ btnAu['coupon/statistics/activity/unUse/init']}',used:'${btnAu['coupon/statistics/activity/used/init']}',couponUnUse:'${btnAu['coupon/statistics/coupon/couponUnUse/init']}',couponUsed:'${btnAu['coupon/statistics/coupon/couponUsed/init']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/coupon/statistics/statisticsInit.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
  </body>
</html>
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
    <title>营销活动派奖</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<input type="hidden" name="aid" value="${param.aid }"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;手机号码：</h5></td>
							<td style="width:400px;"><input type="text" class="form-control"  name="phone"  style="width:80%"></td>
							
							<td style="width:100px;"><h5>&nbsp;&nbsp;派奖状态：</h5></td>
							<td style="width:400px;">
								 <select class="form-control"  name="status" style="width:80%"> 
								        <option value="">全部</option>
										<option value="0">未派奖</option>
										<option value="1">已派奖</option>
								 </select>
							</td>
							
							<td style="width:100px;"><h5>&nbsp;&nbsp;支付时间:</h5></td>							
							<td style="width:400px;">
								<input type="text" name="zdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:38%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name="zdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:38%;float:left">
							</td>
						</tr>
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;商家名称：</h5></td>
							<td style="width:400px;"><input type="text" class="form-control"  name="sellername"  style="width:80%"></td>
							
							<td style="width:100px;"><h5>&nbsp;&nbsp;支付方式：</h5></td>
							<td style="width:400px;">
								 <select class="form-control"  name="paytype" style="width:80%"> 
								        <option value="">全部</option>
										<option value="1000000">收益支付</option>
										<option value="1000001">支付宝</option>
										<option value="1000002">U付支付</option>
										<option value="1000003">微信支付</option>
										<option value="1000004">网银支付</option>
										<option value="1000005">汇付天下</option>
										<option value="1000006">融宝支付</option>
										<option value="1000007">盛付通支付</option>
										<option value="1000011">优惠券支付</option>
										<option value="1000013">微信公众号支付</option>
										<option value="1000022">支付宝APP支付（鸟人科技）</option>
										<option value="1000023">支付宝WAP支付（鸟人科技）</option>
										<option value="1000024">微信APP支付（鸟人科技）</option>
										<option value="1000025">微信公众号支付（鸟人科技）</option>
								 </select>
							</td>
							
							<td colspan="2">
								<div class="submit submit-sp" style="float: left; margin-left: 10px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />&nbsp;&nbsp;&nbsp;
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
			</div>
			<div id="activityList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['marketingManagement/activitymanagement/update']}',del:'${btnAu['marketingManagement/activitymanagement/delete'] }',check:'${btnAu['marketingManagement/activitymanagement/check'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/marketingmanagement/activityPrize.js"></script>
</body>
</html>

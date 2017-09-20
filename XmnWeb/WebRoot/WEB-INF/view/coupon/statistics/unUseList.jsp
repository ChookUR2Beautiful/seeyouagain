<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<title></title>
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

th {
	font-size: 12px;
}
</style>
</head>
<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-block">
				<form id="unUseForm">
					<input type="hidden" name="issueid" value="${issueid}"/>
					<input type="hidden" name="userStatus" value="0"/>
					<input type="hidden" name="getStatus" value="1"/>
					<table  style="width:100%;">
						<tr style="height:20px"></tr>
						<tr>
							<th >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号码：</th>
							<td><input type="text" class="form-control"  name="phone" ></td>	
							
							<td style="width:30px;"></td>						
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领取时间:</h5></td>
							
							<td>
							<input type="text" name ="dateStart" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:43%;float:left">
							<label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label>
							<input type="text" name ="dateEnd" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td  style="text-align: right; ">
								<div class="submit"><input class="submit radius" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>	
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-block">
				<div class="panel-body data">
					<div id="unUseList"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permisions">{list:'${ btnAu['coupon/generate/init/list']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">
		var unUseList;
		$(function() {
			inserTitle(
					' >优惠券统计 > <span><a href="coupon/statistics/init.jhtml" target="right">未使用优惠券</a>',
					'couponList', true);
			$('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd hh:ii'
			});
			unUseList = $('#unUseList').page({
				url : 'coupon/statistics/activity/unUse/init/list.jhtml',
				success : function(data, obj) {
					obj.find('div').eq(0).html($.renderGridView({
						title : "已领取，未使用优惠券列表",
						totalTitle : "未使用",
						form : "unUseForm",
						columns : [{
							title : "用户编号",
							name : "uid",
						},{
							title : "手机号码",
							name : "phone"
						} /* {
							title : "用户昵称",
							name : "uid"
						}, */ /* , {
							title : "类型",
							name : "denomination"
						}, {
							title : "注册时间",
							name : "startDate"
						}, {
							title : "所属商家",
							name : "sellerid"
						}, {
							title : "注册区域",
							name : "condition"
						} */, {
							title : "优惠券SN码",
							name : "serial"
						} , {
							title : "优惠券面额",
							name : "denomination"
						}, {
							title : "获取方式",
							name : "getWayText"
						}, {
							title : "获取时间",
							name : "getTime"
						} ,{
							title : "使用金额",
							name : "userMoney"
						} , {
							title : "使用时间",
							name : "userTime"
						}, {
							title : "发行时间",
							name : "dateIssue"
						}/* , {
							title : "订单编号",
							name : "orderNo"
						} */, {
							title : "起始有效期",
							name : "startDate"
						}, {
							title : "截止有效期",
							name : "endDate"
						}],
						permissions : permisions
					}, data));
				},
				pageBtnNum : 10,
				paramForm : 'unUseForm'
			});
		});
	</script>
</body>

</html>
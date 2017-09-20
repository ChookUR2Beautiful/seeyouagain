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
				<form id=viewCouponForm>
					<input type="hidden" name=issueId value="${issueId}"/>
					<table  style="width:100%;">
						<tr style="height:20px"></tr>
						<tr>
							<th >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;优惠券编号：</th>
							<td><input type="text" class="form-control"  name="cid" ></td>	
							
							<!-- <td style="width:30px;"></td>						
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;优惠券名称:</h5></td>
							<td><input type="text" class="form-control"  name="cname" ></td>	 -->
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
					<div id="viewCouponList"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">
		var viewCouponList;
		$(function() {
			inserTitle(' >优惠券发放 > <span><a href="coupon/couponissue/viewCouponInit.jhtml" target="right">优惠券信息</a>','viewCouponList', true);
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
			viewCouponList = $('#viewCouponList').page({
				url : 'coupon/couponissue/viewCoupon/viewCouponInit/list.jhtml',
				success : function(data, obj) {
					obj.find('div').eq(0).html($.renderGridView({
						title : "优惠券信息",
						totalTitle : "优惠券信息",
						form : "viewCouponForm",
						columns : [{
							title : "优惠券编号",
							name : "cid"
						}, {
							title : "优惠券名称",
							name : "cname"
						}, {
							title : "发行量",
							name : "issuevolume"
						},{
							title : "优惠券类型",
							name :"ctypeStr"
						}
						],
					}, data));
				},
				pageBtnNum : 10,
				paramForm : 'viewCouponForm'
			});
		});
	</script>
</body>

</html>
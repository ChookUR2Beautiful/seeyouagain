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
</head>
<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
				<form id="areaForm">
					<input type="hidden" name="cid" value="${cid}"/>
					<input type="hidden" id="ctype" name="ctype" value="${ctype}"/>
				</form>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-block">
							<div class="panel-body data">
								<div id="areaList"></div>
							</div>
						</div>
					</div>
				</div>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script type="text/javascript">
		var areaList;
		$(function() {
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
			areaList = $('#areaList').page({
				url : 'coupon/statistics/coupon/viewArea/viewAreaInit/list.jhtml',
				success : function(data, obj) {
					obj.find('div').eq(0).html($.renderGridView({
						title : function(){
							if($("#ctype").val()==1){
								return "现金券区域列表";
							}else{
								return "优惠券区域列表";
							}
						},
						totalTitle : "区域列表",
						form : "areaForm",
						columns : [{
							title : "活动编号",
							name : "cid"
						}, {
							title : "活动名称",
							name : "cname"
						}, {
							title : "城市区域",
							name : "provincesText",
						}],
					}, data));
				},
				pageBtnNum : 10,
				paramForm : 'areaForm'
			});
		});
	</script>
</body>

</html>
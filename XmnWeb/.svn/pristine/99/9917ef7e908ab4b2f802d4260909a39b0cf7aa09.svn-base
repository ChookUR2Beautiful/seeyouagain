<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>提现商家列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>合作商账号:</h5></td>
							<td class="col-md-2"><input type="text" class="form-control"
								name="loginAccount"></td>
							<td class="col-md-1"><h5>合作商昵称:</h5></td>
							<td class="col-md-2"><input type="text" class="form-control"
								name="username"></td>
							<td class="col-md-1"><h5>提现时间:</h5></td>
							<td class="col-md-2">
								<div class="input-group">
									<input type="text" name="startdate" placeholder="yyyy-MM-dd"
										class="form-control form-datetime"> <span
										class="input-group-addon fix-border">--</span><input
										type="text" name="enddate" placeholder="yyyy-MM-dd"
										class="form-control form-datetime">
								</div>
							</td>
							<td class="col-md-3 ">
								<div class="submit text-left">
									<input class="submit radius-3"
										style="width:49%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49%;margin-right:0;" type="reset" value="重置全部"
										data-bus='reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="panel">
		<div class="panel-body data">
			<div id="memberProvidedDiv" request-init="${requestInit}"></div>
		</div>
	</div>
	<%--<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }'}</script> --%>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">
		inserTitle(
				' > 合作商管理 > <span><a href="business_cooperation/getMoneyJoint/init.jhtml" target="right">合作商提现信息查询</a>',
				'getMoney', true);
		var mem = $('#memberProvidedDiv');
		$(function() {
			$('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				minView : 2,
				maxView : 3,
				autoclose : true,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd'
			});
			var url = [ $(mem).attr("request-init"), ".jhtml" ].join("");
			$(mem).page({
				url : url,
				dataType : "html",
				success : handle,
				pageBtnNum : 10,
				paramForm : 'searchForm'
			});
		});
		function handle(data) {
			var table = $(mem).find("table");
			if (table.length == 0) {
				$(mem).prepend(data);
			} else {
				$(table).html(data);
			}
		}
	</script>

</body>
</html>

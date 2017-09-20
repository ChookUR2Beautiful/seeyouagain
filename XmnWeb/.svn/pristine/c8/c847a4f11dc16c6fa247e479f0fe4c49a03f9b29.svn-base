<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>申诉订单</title>
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
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<input type="hidden" name="bid" value="${param.bid}" />
							<td class="col-md-1"><h5>用户昵称:</h5></td>
							<td class="col-md-3" align="left"><input type="text"
								class="form-control" name="nname"></td>
							<td class="col-md-1"><h5>订单号:</h5></td>
							<td class="col-md-3" align="left"><input type="text"
								class="form-control" name="bid"></td>
							<td class="col-md-1"><h5>支付时间:</h5></td>
							<td class="col-md-3" align="left">
								<div class="input-group">
									<input type="text" name="zdateStart"
										placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime"> <span
										class="input-group-addon fix-border">--</span><input
										type="text" name="zdateEnd" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime">
								</div>
							</td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>支付流水号:</h5></td>
							<td class="col-md-3" align="left"><input type="text"
								class="form-control" name="payid"></td>
							<td class="col-md-1"><h5>消费商家:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="sellername"></td>
							<td class="col-md-1"><h5>支付方式:</h5></td>
							<td class="col-md-3"><select class="form-control"
								name="paytype">
									<option value="">请选择</option>
									<option value="1000000">收益支付</option>
									<option value="1000001">支付宝</option>
									<option value="1000002">U付支付</option>
									<option value="1000003">微信支付</option>
									<option value="1000004">网银支付</option>
									<option value="1000005">汇付天下</option>
									<option value="1000006">融宝支付</option>
									<option value="1000007">盛付通支付</option>
									<option value="1000008">快钱支付</option>
									<option value="1000009">通联支付</option>
									<option value="1000010">连连支付</option>
									<option value="1000011">优惠券支付</option>
									<option value="1000013">微信公众号支付</option>
									<option value="1000022">支付宝APP支付（鸟人科技）</option>
									<option value="1000023">支付宝WAP支付（鸟人科技）</option>
									<option value="1000024">微信APP支付（鸟人科技）</option>
									<option value="1000025">微信公众号支付（鸟人科技）</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="4"></td>
							<td colspan="2" class="col-md-4">
								<div class="submit text-left">
									<input class="submit radius-3"
										style="width:49.5%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49.5%;margin-right:0;" type="reset" value="重置全部"
										data-bus='reset' />
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
			<div id="refundBillList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{treatment:'${ btnAu['president_office/refund/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/president_office/refundBillList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

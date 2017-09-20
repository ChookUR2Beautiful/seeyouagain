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
<title>商家账号</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">分账列表</a></li>
		<li class=""><a href="#tab2" data-toggle="tab">提现列表</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="divideForm">
						<input type="hidden" name="type" value="0" /> <input
							type="hidden" name="destroy" value="0" />
						<table style="width:100%;">
							<tbody>
								<tr>
									<td class="col-md-1"><h5>订单号:</h5></td>
									<td class="col-md-3"><input type="text"
										class="form-control" name="id"></td>
									<td class="col-md-1"><h5>所属合作商:</h5></td>
									<td class="col-md-3"><select class="form-control"
										id="bpartner" name="bpartnerName"></select></td>
									<td class="col-md-1"><h5>订单创建时间:</h5></td>
									<td class="col-md-3">
										<div class="input-group">
											<input type="text" placeholder="yyyy-MM-dd hh:mm"
												class="form-control form_datetime" name="startTime">
											<span class="input-group-addon fix-border">--</span> <input
												type="text" placeholder="yyyy-MM-dd hh:mm"
												class="form-control form_datetime" name="endTime">
										</div>
									</td>
								</tr>
								<tr>
									<td class="col-md-1"><h5>会员编号:</h5></td>
									<td class="col-md-3"><input type="text"
										class="form-control" name="memberId"></td>
									<td class="col-md-1"><h5>消费合作商:</h5></td>
									<td class="col-md-3"><select class="form-control"
										id="cpartner" name="cpartnerName"></select></td>
									<td class="col-md-1"><h5>消费额:</h5></td>
									<td class="col-md-3">
										<div class="input-group">
											<input type="text" class="form-control" name="startExpense">
											<span class="input-group-addon fix-border">--</span> <input
												type="text" class="form-control" name="endExpense">
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4"></td>
									<td colspan="2" class="col-md-4">
										<div class="submit text-left">
											<input class="submit radius-3"
												style="width:49.5%;margin-right:0;" type="button"
												value="查询全部" data-bus='query' /> <input
												class="reset radius-3" style="width:49.5%;margin-right:0;"
												type="reset" value="重置全部" data-bus='reset' />
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
						<%-- <c:if test="${not empty btnAu['president_office/disputeOrder/updateStatus']}"><button type="button" class="btn btn-success" id="divideAgree" >通过</button></c:if>
			<c:if test="${not empty btnAu['president_office/disputeOrder/updateStatus']}"><button type="button" class="btn btn-danger" id="divideDisagree">不通过</button></c:if> --%>
					</div>
					<div id="divide"></div>
				</div>
			</div>
		</div>
		<div id="tab2" class="tab-pane">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="withdrawForm">
						<table style="width:100%;">
							<tbody>
								<tr>
									<input type="hidden" name="type" value="1" />
									<input type="hidden" name="destroy" value="0" />
									<td class="col-md-1"><h5>提现编号:</h5></td>
									<td class="col-md-3"><input type="text"
										class="form-control" name="id"></td>
									<td class="col-md-1"><h5 title="会员/商家/向蜜客/合作商名称">客户名称:</h5></td>
									<td class="col-md-3"><input type="text"
										class="form-control" name="userName"></td>
									<td class="col-md-1"><h5>提现时间:</h5></td>
									<td class="col-md-3">
										<div class="input-group">
											<input type="text" placeholder="yyyy-MM-dd hh:mm"
												class="form-control form_datetime" name="startTime">
											<span class="input-group-addon fix-border">--</span> <input
												type="text" placeholder="yyyy-MM-dd hh:mm"
												class="form-control form_datetime" name="endTime">
										</div>
									</td>
								</tr>
								<tr>
									<td class="col-md-1"><h5>提现类别:</h5></td>
									<td class="col-md-3"><select type="text"
										class="form-control" name="withdrawType">
											<option value="">--全部--</option>
											<option value="1">从返利提现</option>
											<option value="2">从佣金提现</option>
											<option value="3">从营收提现</option>
									</select></td>
									<td class="col-md-1"><h5>发起人类别:</h5></td>
									<td class="col-md-3"><select type="text"
										class="form-control" name="userType">
											<option value="">--全部--</option>
											<option value="1">商户提现</option>
											<option value="2">会员提现</option>
											<option value="3">向蜜客提现</option>
											<option value="4">合作商提现</option>
											<option value="5">连锁店</option>
									</select></td>
									<td class="col-md-1"><h5>提现金额:</h5></td>
									<td class="col-md-3">
										<div class="input-group">
											<input type="text" class="form-control" name="startWithdraw">
											<span class="input-group-addon fix-border">--</span> <input
												type="text" class="form-control" name="endWithdraw">
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4"></td>
									<td colspan="2" class="col-md-4">
										<div class="submit text-left">
											<input class="submit radius-3"
												style="width:49.5%;margin-right:0;" type="button"
												value="查询全部" data-bus='query' /> <input
												class="reset radius-3" style="width:49.5%;margin-right:0;"
												type="reset" value="重置全部" data-bus='reset' />
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
						<%-- <c:if test="${not empty btnAu['president_office/disputeOrder/updateStatus']}"><button type="button" class="btn btn-success" id="withdrawAgree" >通过</button></c:if>
			<c:if test="${not empty btnAu['president_office/disputeOrder/updateStatus']}"><button type="button" class="btn btn-danger" id="withdrawDisagree">不通过</button></c:if> --%>
					</div>
					<div id="withdraw"></div>

				</div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{xg:'${btnAu['president_office/disputeOrder/updateStatus']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/president_office/disputeOrder.js"></script>
</body>
</html>
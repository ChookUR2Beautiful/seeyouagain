<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>所有订单</title>
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
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<!-- 商家id 商家页面查看订单使用 -->
	<input type="hidden" id="selleridId" value="${param.sellerid}" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"
				id="searchBillForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td class="col-md-1"><h5>用户昵称:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="nname"></td>
							<td class="col-md-1"><h5>用户手机:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="phoneid"></td>
							<td class="col-md-1"><h5>订单号:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="stringBid"></td>
						</tr>
						<tr>
							<td class="col-md-1"><h5>订单状态:</h5></td>
							<td class="col-md-3"><select class="form-control"
								name="status">
									<option value="">请选择</option>
									<option value="0" ${bill.status==0?"selected":""}>非退款其它状态</option>
									<option value="1" ${bill.status==1?"selected":""}>待返现</option>
									<option value="2" ${bill.status==2?"selected":""}>已返现</option>
									<option value="3" ${bill.status==3?"selected":""}>已消费</option>
									<option value="4" ${bill.status==4?"selected":""}>退款中</option>
									<option value="5" ${bill.status==5?"selected":""}>已退款</option>
									<option value="6" ${bill.status==6?"selected":""}>待返现【取消退款】</option>
									<option value="7" ${bill.status==7?"selected":""}>退款中【已申诉】</option>
									<option value="8" ${bill.status==8?"selected":""}>待返现【驳回申请退款】</option>
									<option value="9" ${bill.status==9?"selected":""}>返利中</option>
									<option value="10" ${bill.status==10?"selected":""}>商家申诉失败</option>
									<option value="11" ${bill.status==11?"selected":""}>平台退款处理中</option>
									<option value="12" ${bill.status==12?"selected":""}>平台退款失败</option>
									<option value="13" ${bill.status==13?"selected":""}>商家同意退款</option>
									<option value="14" ${bill.status==14?"selected":""}>平台同意退款</option>
									<option value="15" ${bill.status==15?"selected":""}>刷单退款 </option>
							</select></td>
							<td class="col-md-1"><h5>所属商家:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="genusname"></td>
							<td class="col-md-1"><h5>下单时间:</h5></td>
							<td class="col-md-3">
								<div class="input-group">
									<input type="text" name="sdateStart"
										placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime"><span
										class="input-group-addon fix-border">--</span><input
										type="text" name="sdateEnd" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form-datetime">
								</div>
							</td>
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
			<%-- <div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['billmanagerment/allbill/export'] && btnAu['billmanagerment/allbill/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div> --%>
			<div id="allBillList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{detail:'${ btnAu['billmanagerment/allbill/view/init']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/businessman/sellerBillList.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
</body>
</html>

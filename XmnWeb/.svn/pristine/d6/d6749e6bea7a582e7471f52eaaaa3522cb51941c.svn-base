<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>添加连锁店信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
}
</style>
<link rel="stylesheet" href="<%=path%>/resources/css/jedate.css" />
<link>
<link rel="stylesheet" href="<%=path%>/resources/css/main.css" />
<link>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">订单详情</div>
				<div class="panel-body">
					<form id="celebrityOrderForm">
						<input type="hidden" id="supplierId" name="supplierId"
							value="${supplier.supplierId}" /> <input type="hidden"
							id="isType" name="isType" value="${isType}" />

						<div class="flow-box bg-fff">
							<div class="flow-stage">
								<ul>
									<li><span class="round">1</span><span>接单</span><span
										class="line"></span></li>
									<li><span class="round">2</span><span>品尝美食</span><span
										class="line"></span></li>
									<li><span class="round">3</span><span>撰写食评</span><span
										class="line"></span></li>
									<li><span class="round">4</span><span>等待发布</span><span
										class="line"></span></li>
									<li><span class="round">5</span><span>发布成功</span><span
										class="line"></span></li>
									<li><span class="round">6</span><span>订单取消</span></li>
								</ul>
							</div>
						</div>

						<table class="table" style="text-align: center;">
							<tr>
								<td class="sellerTitleCss" align="left"><label>订单编号：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>商家名称：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>食评名嘴：</label><span>123456789</span>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left"><label>支付流水号：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>商户地址：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>食评要求：</label><span>123456789</span>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left"><label>支付金额：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>商户联系人：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>突出重点：</label><span>123456789</span>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left"><label>付款方式：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>下单时间：</label><span>123456789</span>
								</td>
								<td class="sellerTitleCss" align="left"><label>针对人群：</label><span>123456789</span>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="sellerTitleCss" align="left">
									<label>更改名嘴：</label>
									<select class="form-control" style="width:25%" id="anchor">
									</select>
								</td>
								<td align="left">
								<button class="btn btn-danger" type="submit">
									<i class="icon-save"></i>&nbsp;保存
								</button>
								&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="javascript:;">
									<i class="icon-reply"></i>&nbsp;取消订单
								</button>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left" colspan="2"><label>跟进记录：</label>
								<input type="text"
									class="form-control" style="width:90%"></td>
								<td class="sellerTitleCss" align="left">
									<input type="radio" name="type" value="1"><label>商户</label>&nbsp;
									<input type="radio" name="type" value="2"><label>名嘴</label>&nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-danger"  href="javascript:;" ><span class="icon-plus"></span>&nbsp;添加跟进记录</a>
								</td>
							</tr>
						</table style="text-align: center;">
						<hr>
						<table class="table">
							<thead>
								<th>跟进时间</th>
								<th>跟进内容</th>
								<th>跟进对象</th>
								<th>跟进人</th>
							</thead>
							<tbody>
								<tr>
									<td colspan="4" align="center">暂无记录</td>
								</tr>
							</tbody>
						</table>
						<hr>
						<table class="table" style="text-align: center;">
							<div align="center">
								<button class="btn btn-danger" type="submit">
									<i class="icon-save"></i>&nbsp;下一步
								</button>
								&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="goBack()">
									<i class="icon-reply"></i>&nbsp;取消
								</button>
							</div>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/json" id="permissions">{add:'${ btnAu['supplier/manager/add']}'}</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/billmanagerment/celebrityOrderDetail.js"></script>
</body>
</html>

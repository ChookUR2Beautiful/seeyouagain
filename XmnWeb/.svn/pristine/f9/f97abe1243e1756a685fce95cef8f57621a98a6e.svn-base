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
<title>商家待审核列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
</style>

</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="sellerPendingFromId">
				<!-- 1审核中 -->
				<input type="hidden" id="aid" name="aid" value="${aid}" />
				<!-- 0不是连锁店 -->
				<input type="hidden" name="ismultiple" value="0" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:10%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
							<td style="width:23%;"><input type="text"
								class="form-control" name="sellerid" value="${param.sellerid}"
								style="width:90%"></td>
							<td style="width:10%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:23% !important;"><input type="text"
								class="form-control" name="sellername"
								value="${param.sellername}" style="width:90%"></td>
						</tr>
						<tr>
							<td style="width:10%;"><h5>&nbsp;&nbsp;商家手机号:</h5></td>
							<td style="width:23% !important;"><input type="text"
								class="form-control" name="phoneid" value="${param.phoneid}"
								style="width:90%"></td>

							<td style="width:10%;"><h5>&nbsp;&nbsp;参与状态：</h5></td>
							<td style="width:23%;"><select class="form-control"
								tabindex="2" name="isattend" style="width:89%;">
									<option value="">--请选择--</option>
									<option value="0"
										<c:if test="${!empty param.isattend}">${param.isattend==0?"selected":""}</c:if>>已参与</option>
									<option value="1"
										<c:if test="${!empty param.isattend}">${param.isattend==1?"selected":""}</c:if>>已暂停</option>
							</select></td>
							<td colspan="2" style="text-align: right; ">
								<div class="submit submit-sp" style="margin-right: 20px;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' style="width: 230px;" /> <input
										class="reset radius-3" type="reset" value="重置全部"
										data-bus='reset' style="width: 230px;" />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div id="sellerInPendingfoDiv" request-init="${requestInit}"></div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<script type="text/json" id="permissions">
{update:'${btnAu['businessman/sellerPending/update'] }',view:'${btnAu['businessman/sellerPending/getInit'] }',agio:'${btnAu['businessman/sellerPendingAgio/init'] }'
,account:'${btnAu['businessman/sellerPendingAccount/init'] }',updateIsattend:'${btnAu['marketingManagement/activityManagement/discount/initSellerRelateNum/discountUpdateSellerMarketingIsattend'] }'
,exitActivity:'${btnAu['marketingManagement/activityManagement/discount/initSellerRelateNum/discountExitActivity'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script
		src="<%=path%>/js/marketingmanagement/discountParticipatingMerchants.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var vali = {
				rules : {
					sellerid : {
						digits : true,
						range : [ 1, 2147483647 ]
					}
				},
				messages : {
					sellerid : {
						digits : "商家编号只能是数字",
						range : "最大值为2147483647"
					}
				}
			};
			validate("sellerPendingFromId", vali);
		});
	</script>
</body>
</html>

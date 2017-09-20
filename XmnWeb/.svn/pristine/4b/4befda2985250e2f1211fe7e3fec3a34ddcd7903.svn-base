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
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form id="addWaiterConfigForm">
						<input type="hidden" name="sellerSubsidyToken"
							value="${sellerSubsidyToken}"> <input type="hidden"
							id="isType" name="isType" value="${isType}" /> <input
							type="hidden" id="issuestatus" name="issuestatus" value="0" /> 
							<input type="hidden" id="tSpreadConfID" name="id" value="${TSpreadConfig.id}" />
						<table class="table" style="width: 100%;">
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>商&nbsp;家&nbsp;名&nbsp;称:</h5>
								</td>
								<td class="sellerContentCss"><label id="checkids"></label>
									<div id="sellerChange">
									<input type="hidden" id="sellerId" name="sellerId" value="">
										<input type="text" class="form-control" id="sellername"
											style="width:56%;" name="sellername" value="${TSpreadConfig.sellername}">
									</div></td>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>配&nbsp;置&nbsp;状&nbsp;态:</h5>
								</td>
								<td class="sellerContentCss"><select name="status" initValue="${TSpreadConfig.status}"
									class="form-control" style="width:40%;">
										<option value="">请选择</option>
										<option value="0">开启</option>
										<option value="1">关闭</option>
								</select></td>
							</tr>
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>订单金额:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" id="startMoney" name="startMoney"
									value="${TSpreadConfig.startMoney}" style="width:25%">元至 <input
									type="text" class="form-control" id="endMoney" name="endMoney"
									value="${TSpreadConfig.endMoney}" style="width:25%">元</td>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>服&nbsp;务&nbsp;员&nbsp;奖&nbsp;励&nbsp;:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" id="money" name="money" value="${TSpreadConfig.money}"
									style="width:40%;">元</td>
							</tr>
						</table>
						<hr>
						<div align="center" style="margin-left: 280px">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" id="cancelId">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen3.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/businessman/sellerSubsidy/addWaiterConfig.js"></script>
</body>
</html>

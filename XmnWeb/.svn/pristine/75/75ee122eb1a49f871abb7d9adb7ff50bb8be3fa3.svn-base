<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商家收入流水列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">  
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
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
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="sellerIncomeFlowFromId">
			
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:100px;"><h5>订单号：</h5></td>
							<td style="width:250px !important;">
								<input type="text" class="form-control"  name="stringBid"  value="${param.stringBid}" style="width:90%">
							</td>
							<td style="width:100px;"><h5>商家名称：</h5></td>
							<td style="width:250px !important;">
								<input type="text" class="form-control"  name="sellerName"  value="${param.sellerName}" style="width:80%">
							</td>
							<td style="width:100px;"><h5>订单类型：</h5></td>
							<td style="width:250px;">
								<select class="form-control" name="orderType">
									<option value="">全部</option>
									<option value="1">平台</option>
									<option value="2">商户</option>
									<option value="3">粉丝券</option>
									<option value="4">套餐</option>
								</select>
							</td>	
							<td>
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>							
						</tr>							
					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty  btnAu['businessman/sellerIncomeFlow/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>	  		
			</div>
			<div id="sellerIncomeFlowList"></div>
		</div>																																				
	</div>
	<script type="text/javascript">contextPath = '${pageContext.request.contextPath}';</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/businessman/model/sellerModel.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/businessman/sellerIncomeFlowList.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var vali=
			{
				rules:{
					sellerId:{
						digits:true,
						range:[1,2147483647]
					}
				},
				messages:{
					sellerId:{
						digits:"商家编号只能是数字",
						range:"最大值为2147483647"
					}
				}
			};
		validate("sellerIncomeFlowFromId",vali);
	});
	</script>
	
</body>
</html>

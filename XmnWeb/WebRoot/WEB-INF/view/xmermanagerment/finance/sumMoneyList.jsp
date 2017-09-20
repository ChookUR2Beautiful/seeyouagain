<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>钱包记录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type = "hidden" id = "uid" name = "uid" value = "${uid}" >
				<input type = "hidden" id = "order" name = "order" value = "${order}" >
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:100px;"><h5>订单号：</h5></td>
							<td style="width:400px;">
								<input type="text" class="form-control"  name="bid" style="width:100%">
							</td>
							<td style="width:100px;"><h5>商铺ID：</h5></td>							
							<td style="width:400px;">
								<input type="text" class="form-control" name="sellerid" style="width:100%"/>
							</td>
							<td style="width:100px;"><h5>商铺名称：</h5></td>
							<td style="width:400px;">
								<input type="text" class="form-control" name="sellername" style="width:90%"/>
							</td>								
						</tr>
						<tr>
							<td style="width:100px;"><h5>商铺负责人：</h5></td>
							<td style="width:400px;">
								<input type="text" class="form-control"  name="fullname" style="width:100%">
							</td>
							<td style="width:100px;"><h5>负责人电话：</h5></td>							
							<td style="width:400px;">
								<input type="text" class="form-control" name="phoneid" style="width:100%"/>
							</td>
							<td style="width:100px;"><h5>下单时间：</h5></td>
							<td style="width:400px;">
								<input type="text" name ="sdateStart"  placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label>
								<input type="text" name ="sdateEnd"  placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>								
						</tr>
						<tr>
							<td colspan="4"></td>
							<td colspan="2">
								<div class="submit" style="text-align: right;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
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
			<div class="btn-group" style="margin-bottom: 15px;">
				<c:if test="${!empty  btnAu['xmer/finance/sumMoney/export']}">
					<button type="button" id="export" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出
					</button>
				</c:if>
			</div>
			<div id="sumMoneyList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{sumMoneyView:'${ btnAu['xmer/finance/sumMoney/init']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/xmermanagerment/finance/sumMoneyList.js"></script>
</body>
</html>

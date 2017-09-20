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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;">
								<%-- <input type = "hidden" id = "accountid" name = "accountid" value = "${accountid}" > --%>
								<!-- 寻蜜客钱包ID -->
								<input type = "hidden" id = "xid" name = "xid" value = "${xid}" >
								<!-- 0 寻蜜鸟分账 1 转出  2 saas分账 3 寻蜜鸟分账返回 4 saas分账返回 -->
								<input type = "hidden" id = "rtype" name = "rtype" value = "${rtype}" >
								<input type = "hidden" id = "order" name = "order" value = "${order}" >
								<h5>订单号：</h5>
							</td>
							<td style="width:18%">
								<input type="text" class="form-control" name="remarks" />
							</td>
							<td>
									<div class="submit">
										<input class="submit radius-3" type="button" value="查询全部" data-bus='query' /> 
										<input type="reset" class="reset radius-3" 	 value="重置全部" data-bus='reset' />
									</div>
								<c:if test="${!empty page}">
									<input type="hidden" value="${page}" name="page" />
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 15px;">
				<c:if test="${!empty  btnAu['xmer/finance/walletRecord/export']}">
					<button type="button" id="export" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出
					</button>
				</c:if>
			</div>
			<div id="walletRecordList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
		walletRecordView:'${ btnAu['xmer/finance/walletRecord']}'
	}
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/xmermanagerment/finance/walletRecordList.js?v=1.0.3"></script>
</body>
</html>

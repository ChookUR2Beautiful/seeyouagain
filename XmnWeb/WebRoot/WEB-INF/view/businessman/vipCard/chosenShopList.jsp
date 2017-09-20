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
<title>商户列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
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
	<form class="form-horizontal" role="form"  id="sellerFromId">
	    <input type="hidden" name="status"  value="3"/> 
	    <input type="hidden" name="ismultiple"  value="0"/> 
	    <input type="hidden" name="fatherid" id="fatherid" value="${sellerId}">
	</form>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="row">
				<div class="col-md-1"><h5>已选择子店:</h5></div>
				<div class="col-md-11"><div id="choseDatas" class="chosen-container chosen-container-multi chosen-with-drop chosen-container-active" style="width:100%"><ul class="chosen-choices"></ul></div></div>
			</div>
			<div id="sellerInfoDiv" ></div>
		</div>																															
	</div>
	<div class="panel" style="padding-top: 10px;padding-bottom : 10px;">
		<div class="return" align="center">
			<button class="btn btn-success closeChosen" ><i class="icon-ok-sign"></i>&nbsp;保存</button>&nbsp;&nbsp;
			<button class="btn btn-default closeChosen" id="allCancel"><span class="icon-reply"></span>&nbsp;取消</button>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{xg:'${ btnAu['businessman/multipShop/update']}',zh:'${btnAu['businessman/multipShop/multipAccount'] }'}
    </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/businessman/vipCard/chosenShopList.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	});
	$("#allCancel").click(function(){
	  window.parent.searchChosen.allCancel();
	})
	</script>
</body>
</html>

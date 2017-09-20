<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>统计</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet" />
<link href="<%=path%>/css/live_anchor/liveGivedCountManage.css?v=1.0.1" rel="stylesheet" >
</head>



<body style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;"
	class="doc-views with-navbar">
	<div class="panel panel-primary">
		<div id="content">
		<div class="panel-body">
		<div class="list-box" >
			            <table border="0" cellspacing="1">
			                <thead>
			                    <tr>
			                        <th width="33%">今日订单 <font size="5" id="order_today"> ${todayOrder.num} </font> 单</th>
			                        <th width="33%">昨日订单<font size="5" id="order_yes">  ${yesOrder.num} </font>单</th>
			                        <th width="33%">总订单   <font size="5" id="order_sum"></font>  单</th>
			                    </tr>
			                </thead>
			                
			            </table>
    		</div>
    		</div>
	</div>
		<div id="container"
			style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		
	
		<div id="prompt"
			style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="module-containner-wrap container-fluid"></div>
	</div>
	
	
	<div class="panel panel-primary">
		<div id="content">
		<div class="panel-body">
		<div class="list-box" >
			            <table border="0" cellspacing="1">
			                <thead>
			                    <tr>
			                        <th width="33%">今日销售量<font size="5" > ${todayOrder.wareNum} </font> 件</th>
			                        <th width="33%">昨日销售量<font size="5" >  ${yesOrder.wareNum} </font>件</th>
			                        <th width="33%">总销售量   <font size="5" id="order_wareNum"></font>  件</th>
			                    </tr>
			                </thead>
			                
			            </table>
    		</div>
    		</div>
	</div>
		<div id="container1"
			style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		
	
		<div id="prompt"
			style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="module-containner-wrap container-fluid"></div>
	</div>
	
	<div class="panel panel-primary">
		<div id="content">
		<div class="panel-body">
		<div class="list-box" >
			            <table border="0" cellspacing="1">
			                <thead>
			                    <tr>
			                        <th width="33%">今日成交额<font size="5" > ${todayOrder.money} </font> 元</th>
			                        <th width="33%">昨日成交额<font size="5" >  ${yesOrder.money} </font>元</th>
			                        <th width="33%">总销成交额   <font size="5" id="order_money"></font>  元</th>
			                    </tr>
			                </thead>
			                
			            </table>
    		</div>
    		</div>
	</div>
		<div id="container2"
			style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		
	
		<div id="prompt"
			style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="module-containner-wrap container-fluid"></div>
	</div>


	<script type="text/json" id="permissions">
{
	add:'${ btnAu['fresh/module/saveModule']}',
	delete:'${btnAu['fresh/module/deleteModule']}'
}</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>

	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/multipleChosen.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/resources/highcharts/highcharts.js"></script>
	<script src="<%=path%>/resources/highcharts/modules/exporting.js"></script>
	<script src="<%=path%>/js/fresh/saleList.js"></script>
</body>
</html>


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
<title>兑换专享卡记录列表</title>
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
.submit{text-align: left;}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-inline" role="form" id="searchFromId">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="uname" name="uname"  style="width:80%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;用户手机:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="phone" name="phone"  style="width:80%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;充值商户:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  id="sellername" name="sellername"  style="width:80%">
							</td>
						</tr>
						<tr style="padding-top:55px; margin-top:100px;">
						    <td style="width:5%;"><h5>&nbsp;&nbsp;统计时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="startDay" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:36.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="endDay" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:36%;float:left">
							</td>

							<td colspan="2" style="width:25%;">
								<div class="submit" style="text-align: left;width:80%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;" />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;" />
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
				<c:if test="${null!=btnAu['businessman/exchangeCard/export'] && btnAu['businessman/exchangeCard/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</a></c:if>
			</div>
			<div id="exchangeCardInfoDiv"></div>
		</div>																																				
	</div>
	
	<script type="text/json" id="permissions">                                                                                                      
		{xg:'${ btnAu['businessman/specialTopic/update']}'}
    </script>
    
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script src="<%=path%>/js/businessman/exchangeCard/exchangeCardList.js"></script>
	
</body>
</html>

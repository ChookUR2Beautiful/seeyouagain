<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>特价爆品订单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<input type="hidden" name="bid" value="${bid }"/>
				<table style="width:100%;">
					<tbody>
						 <tr>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爆品编号:</h5></td>
							<td style = "width:16%;"><input type="text" class="form-control"  name="bpid" style = "width:100%;"></td>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爆品名称：</h5></td>							
							<td >
								<input type="text" class="form-control"  name="pname" style = "width:100%;">
							</td>
						</tr>
						<tr>
							<td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爆品价格:</h5></td>
							<td>
								<input type="text" class="form-control"  name="pricelow" style="width:48%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" class="form-control"  name="pricetop" style="width:48%;float:left">
							</td>
							
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;记录时间:</h5></td>
							<th >
								<input type="text" name ="startdate" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:48%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="enddate" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:48%;float:left">
							</th>
						</tr>
						
						<tr>
							<td width="40%" colspan="10"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr> 
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			</div>
			<div id="billBargainList"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/marketingmanagement/billBargainList.js"></script>
</body>
</html>

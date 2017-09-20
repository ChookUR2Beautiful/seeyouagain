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
    <title>积分商品订单</title>
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
				<%-- <input type="hidden" name="bid" value="${bid }"/> --%>
				<table style="width:100%;">
					<tbody>
						 <tr>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品编号:</h5></td>
							<td >
							    <input type="text" class="form-control"  name="bpid" style = "width:60%;">
							</td>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户编号：</h5></td>							
							<td >
								<input type="text" class="form-control"  name="uid" style = "width:60%;">
							</td>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家编号：</h5></td>							
							<td >
								<input type="text" class="form-control"  name="sellerid" style = "width:100%;">
							</td>
						</tr>
						<tr>
						 	<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积分商品名称:</h5></td>
							<td >
								<input type="text" class="form-control"  name="pname" style = "width:60%;">
							</td>
								<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户昵称：</h5></td>							
							<td >
								<input type="text" class="form-control"  name="uname" style = "width:60%;">
							</td>
								<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家名称：</h5></td>							
							<td >
								<input type="text" class="form-control"  name="sellername" style = "width:100%;">
							</td>
						</tr>
						<tr>
							<td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品价格:</h5></td>
							<td >
								<input type="text" class="form-control"  name="pricelow" style="width:28%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" class="form-control"  name="pricetop" style="width:28%;float:left">
							</td>
							
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;记录时间:</h5></td>
							<th >
								<input type="text" name ="startdate" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:28%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="enddate" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:28%;float:left">
							</th>
							<td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户手机号:</h5></td>
							<td>	
							   <input type="text" class="form-control"  name="phoneid" style = "width:100%;">					
							</td>
						</tr>
						<tr>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单编号:</h5></td>
							<td >
							    <input type="text" class="form-control"  name="bid" style = "width:60%;">
							</td>
							<td ><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付状态：</h5></td>							
							<td >
								<select class="form-control"  name="status" style = "width:60%;">
									<option value="">请选择</option>
									<option value="0">待支付</option>
									<option value="1">已支付</option>
									<option value="2">支付失败</option>
								</select>
							</td>
							<td >
							</td>							
							<td >
							</td>
						</tr>
						<tr>
							<td width="40%" colspan="6"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
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
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['billmanagerment/allbillbargain/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;订单导出</button>
				</c:if>
			</div>
			<div id="billbargainList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{check:'${btnAu['billmanagerment/allbillbargain/view']}'}</script>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
   <script src="<%=path%>/js/billmanagerment/billbargainList.js"></script>
</body>
</html>

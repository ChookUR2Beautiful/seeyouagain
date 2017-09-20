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
    <title>商家申请发票</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet"> 	
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">  
	<style type="text/css">
	.submit{text-align: left;}
	</style>
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="sellerName"  style="width:80%"></td>
													
							<!-- <td style="width:80px;"><h5>&nbsp;&nbsp;所属区域:</h5></td>
							<td style="width:180px;"><input type="text" class="form-control"  name="regionId"  style="width:150px"></td> -->
							
							<td style="width:5%;"><h5>&nbsp;&nbsp; 申请金额:</h5></td>
							<td style="width:25%;">
							<input type="text" class="form-control"  name=applyAmountSt  style="width:40%;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
						    <div></div>
							<input type="text" class="form-control"  name="applyAmountEd"  style="width:40%;float:left">
							</td>
					
							<td style="width:5%;"><h5>&nbsp;&nbsp;操作员名称:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="operationName"  style="width:76.5%"></td>
													
						</tr>
						<tr>	
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家账号:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="sellerUsername"  style="width:80%"></td>
												
							<td style="width:5%;"><h5>&nbsp;&nbsp; 申请时间:</h5></td>							
							<td style="width:25%;">
							<input type="text" name ="applyDateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:40%;float:left">
							<label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="applyDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:40%;float:left">
							</td>																																		
							<td colspan="2" style="width:30%;"><div class="submit">&nbsp;&nbsp;<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<!-- 	<button type="button" class="btn btn-success"  data-type="ajax"   data-url="businessman/orderinvoice/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button> -->
				<!-- <button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button> -->
			   <c:if test="${null!=btnAu['businessman/orderinvoice/export'] && btnAu['businessman/orderinvoice/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>	 
				
				<%-- <c:if test="${null!=btnAu['businessman/orderinvoice/delete'] && btnAu['businessman/orderinvoice/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button></c:if>	   --%>
			<!-- 	<div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="orderinvoiceList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{sc:'${btnAu['businessman/orderinvoice/delete'] }',dc :'${btnAu['businessman/orderinvoice/export'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/businessman/orderinvoice.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<%-- <script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/js/businessman/seller.js"></script> --%>
  </body>
</html>

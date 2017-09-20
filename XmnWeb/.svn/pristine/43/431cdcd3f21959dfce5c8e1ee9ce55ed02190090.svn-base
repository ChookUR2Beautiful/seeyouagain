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
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width: 10%"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家编号:</h5></td>
							<td style = "width:18%;"><input type="text" class="form-control"  name="sellerid" style = "width:80%;"></td>
<%--
							<td style="width: 10%"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;针对用户:</h5></td>
							<th style="width: 18%">
								<select class="form-control" name="user" style="width: 80%">
									<option value="">请选择</option>
									<option value="1">新用户</option>
									<!-- <option value="2">老用户</option> -->
									<option value="3">所有用户</option>
								</select>
							</th>
--%>							
							<td style="width: 10%"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width: 18%"><input type="text" class="form-control"  name="sellername" style = "width:80%;"></td>
							
							<td style="width: 10%"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:</h5></td>
							<th style="width: 18%">
								<select class="form-control" name="status" style = "width:80%;">
									<option value="">请选择</option>
									<option value="0">下架</option>
									<option value="1">上架</option>
									<option value="2">审核中</option>
									<option value="3">不通过</option>
								</select>
							</th>
						</tr>
						<tr>
							<td colspan="6">
								<div class="submit submit-sp" style="margin-right: 50px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width: 208px;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset'  style="width: 208px;"/>
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
				<c:if test="${not empty btnAu['marketingManagement/bargainProduct/add']}">
					<a id="addBto" class="btn btn-success"  href="marketingManagement/bargainProduct/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
			</div>
			<div id="bargainProductList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${btnAu['marketingManagement/bargainProduct/update']}',updateStatus:'${btnAu['marketingManagement/bargainProduct/updateStatus']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/marketingmanagement/bargainProductList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

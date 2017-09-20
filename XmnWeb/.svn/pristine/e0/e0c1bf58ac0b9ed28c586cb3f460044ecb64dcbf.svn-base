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
    <title>商家列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet"> 
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<input type="hidden" name="area"  id="area" value="${param.area}">
				<input type="hidden"  name="city" id="city" value="${param.city}">
				<input type="hidden"  name="province" id="province" value="${param.province}">
				<input type="hidden"  name="zoneid" id="zoneid" value="${param.zoneid}">
				<table style="width:100%;">
					<tbody>
						<tr>
						<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:20%" >
								<input type="text" class="form-control"  name="sellerid"  style="width:80%">
							</td>
							<td style="width:5%;"><h5>商家名称:</h5></td>
							<td style="width:20% !important;">
								<input type="text" class="form-control"  name="sellername"  style="width:80%">
							</td>
							<td style="width:5%;"><h5>商家帐号:</h5></td>
							<td style="width:20%" >
								<input type="text" class="form-control"  name="account"  style="width:80%">
							</td>
							<td  style="width:25%;"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%"/>
							<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%"/>
							</div></td>
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
			<div id="sellerList"></div>
		</div>
	</div>
	<div class="butClassID" align="center">
				<!-- <button class="btn btn-danger" type="button" id="saveSellerId" ><i class="icon-save"></i>&nbsp;保存</button>&nbsp; -->
				<button class="btn btn-warning" type="button" id="sellerBavkId"><i class="icon-reply"></i>&nbsp;返回</button>
	</div>
	<script type="text/json" id="permissions">{del:'${ btnAu['businessman/sellerApply/delete']}',export:'${btnAu['businessman/sellerApply/export'] }',more:'${btnAu['businessman/sellerApply/updateState'] }'}</script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/businessman/sellermsg/sellerChooseList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

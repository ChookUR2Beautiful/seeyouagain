<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>引流商家列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<input type="hidden" name="id" value="${param.id}"/>
							<input type="hidden" id="extensionType" name="extensionType"  value="${param.extensionType}"/>
							<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;商家编号:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="sellerId" style = "width:80%;"></td>
							<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="sellername" style = "width:80%;"></td>
							<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询电话:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="tel" style = "width:79%;"></td>
						<tr>
						</tr>
							<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;区域查询:</h5></td>							
							<td style="width:25%;"><div class="input-group" id="ld" style="width:80%" initValue="${not empty param.area ? param.area : (not empty param.city ? param.city : param.province)}"></div></td> 
							<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否开启首次:</h5></td>
							<td style="width:25%;">
								<select class="form-control" name="isFirst" style="width:80%;">
									<option value="">请选择</option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
							<td colspan="2">	
								<div class="submit submit-sp" style="margin-right: 51px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:182px;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset'  style="width:182px;"/>
								</div>
							</td>
						<tr>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">   
		<div class="panel-body data">
		<div class="btn-group" style="margin-bottom: 5px;">
			    <button type="button" class="btn btn-success"  onclick="queryBumen(this,'All');" name="bumen">全部</button>&nbsp;&nbsp;	  
	            <button type="button" class="btn btn-default"  onclick="queryBumen(this,'1');" name="bumen">摇一摇</button>&nbsp;&nbsp;	  
				<button type="button" class="btn btn-default"  onclick="queryBumen(this,'2');" name="bumen">订单推广</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-default"  onclick="queryBumen(this,'3');" name="bumen">列表推广</button>	
			</div>
			<div id="extensionSetList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['marketingManagement/extensionSet/update']}',del:'${btnAu['marketingManagement/extensionSet/delete'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<!-- 使表格可以左右拉动的插件 -->
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/marketingmanagement/extensionSetList.js"></script>
  </body>
</html>

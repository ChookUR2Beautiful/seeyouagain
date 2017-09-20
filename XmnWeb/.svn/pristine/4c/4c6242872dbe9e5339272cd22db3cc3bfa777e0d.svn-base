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
    <title>热门搜索设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td  style="width:6%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;热词来源:</h5></td>
							<th  style="width:16%;">
								<select class="form-control" name="hotType" style="width: 95%;">
									<option value="">请选择</option>
									<option value="1">手工添加</option>
									<option value="2">真实搜索</option>
								</select>
							</th>
							<td  style="width:6%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关键词:</h5></td>
							<td  style="width:16%;"><input type="text" class="form-control"  name="hotWord" style = "width:95%;"></td>
							<td  style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;城市区域：</h5></td>							
							<td  style="width:16%;">
								<div class="input-group" id="ld" style="width:96%" initValue="${not empty param.area ? param.area : (not empty param.city ? param.city : param.province)}"></div>
							</td>
							<input type="hidden" name="type" value="3"/>
							<td colspan="2">
								<div class="submit submit-sp" style="float: left; margin-left: 30px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
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
				<c:if test="${not empty btnAu['marketingManagement/hotWords/add']}"><button type="button" class="btn btn-success"  data-type="ajax"   data-url="marketingManagement/hotWords/add/init.jhtml"  data-toggle="modal" >
				<span class="icon-plus"></span>&nbsp;添加</button></c:if>
<%-- 				<c:if test="${not empty btnAu['marketingManagement/hotWords/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button></c:if>
 --%>			</div>
			<div id="hotWordsList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{<%--del:'${btnAu['marketingManagement/hotWords/delete']}',--%>update:'${btnAu['marketingManagement/hotWords/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/marketingmanagement/hotWordsList.js"></script>
</body>
</html>

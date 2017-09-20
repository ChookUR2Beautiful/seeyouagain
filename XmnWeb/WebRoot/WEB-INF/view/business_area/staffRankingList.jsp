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
    <title>业务员排行</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet"> 	
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">  
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:8%;"><h5>员工姓名:</h5></td>
							<td style="width:12%;"><input type="text" class="form-control"  name="fullname" style = "width:90%;"></td>
							<td style="width:8%;"><h5>所属合作商:</h5></td>
							<td style="width:12%;">
								<select class="form-control" id="jointid" name ="jointid" style = "width:90%;">
							    </select>
							</td>
							<td style="width:10%"><h5>区域查询:</h5></td>
						   <td style="width:20%">
							    <div class="input-group" id="ysqy" style="width: 100%;">
								</div>							
							</td >																													
							<td colspan="2" style="width:30%"><div class="submit" style="float:left; margin-left: 10px;"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			
			  <%--  <c:if test="${null!=btnAu['businessman/orderinvoice/export'] && btnAu['businessman/orderinvoice/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>	  --%>
				
			</div>
			<div id="staffRankingList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{dc :'${btnAu['businessman/orderinvoice/export'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_area/staffRanking.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

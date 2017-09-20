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
    <title>合作商区域分布</title>
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
							<td style="width:6%"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区域查询:</h5></td>
							<td style="width:40%">
							  <!--   <div class="input-group" id="sqy" style="width: 100%;">
								</div> -->
								
								<div class="input-group" id="sqy" 
									<c:choose>
									    <c:when test="${!empty param.cityNameid}">
									    	initValue=" ${param.cityNameid}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${param.shenNameid}"
									    </c:otherwise>
									 </c:choose>
								  style="width:100%">
								</div>
							
							</td>
							<td  style="text-align: right; " style="width:50%">
							<div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
							                    <input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
							</div></td>
							
							
																																
						</tr>
									
						
					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			
			  <%--  <c:if test="${null!=btnAu['businessman/orderinvoice/export'] && btnAu['businessman/orderinvoice/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>	  --%>
				
			</div>
			<div id="businessAreaList"></div>
		</div>
	</div>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script type="text/json" id="permissions">{hzsquxq :'${btnAu['business_area/businessArea/getBusinessAreaByid'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/business_area/businessArea.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

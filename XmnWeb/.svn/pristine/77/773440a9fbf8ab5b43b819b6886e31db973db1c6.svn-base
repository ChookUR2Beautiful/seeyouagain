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
    <title>城市管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	
  	<ul id="myTab" class="nav nav-tabs">
  		<c:if test="${ btnAu['common/cityManagerment/open/init']}">
	      <li <c:if test="${ btnAu['common/cityManagerment/open/init']}">class="active"</c:if> >
	        <a href="#tab1" data-toggle="tab">已开通</a>
	      </li>
      	</c:if>
      	<c:if test="${ btnAu['common/cityManagerment/lock/init']}">
	      <li <c:if test="${ empty btnAu['common/cityManagerment/open/init'] && btnAu['common/cityManagerment/lock/init']}"> class="active" </c:if> >
	        <a href="#tab2" data-toggle="tab">未开通</a>
	      </li>
      </c:if>
    </ul>
    
    <div class="tab-content">
    	<!-- 已开通 -->
	    <c:if test="${ btnAu['common/cityManagerment/open/init']}">
	    <div id="tab1" class="tab-pane <c:if test="${ btnAu['common/cityManagerment/open/init']}">in active</c:if>">
		  	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form"  id="searchFormOpen">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:5%" ><h5>&nbsp;&nbsp;所属区域：</h5></td>
									<td style="width:45%">
										<div class="input-group" id="areaLdIdOpen" style="width: 100%	">
										</div>
									</td>
									<td style="width:45%"> <div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
<%-- 					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['common/area/add'] && btnAu['common/area/add']}">
							<button type="button" class="btn btn-success"  data-type="ajax"   data-url="common/area/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
						</c:if>
					</div> --%>
					<div id="isOpenCityList"></div>
				</div>
			</div>
		</div>
		</c:if>
		
		<!-- 未开通  -->
		<c:if test="${ btnAu['common/cityManagerment/lock/init']}">
		<div id="tab2" class="tab-pane <c:if test="${  empty btnAu['common/cityManagerment/open/init'] && btnAu['common/cityManagerment/lock/init']}">in active</c:if>"> 
		  	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form"  id="searchFormLock">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:5%" ><h5>&nbsp;&nbsp;所属区域：</h5></td>
									<td style="width:45%">
										<div class="input-group" id="areaLdIdLock" style="width: 100%	">
										</div>
									</td>
									<td style="width:45%"> <div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data"> 
					<%-- <div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['common/area/add'] && btnAu['common/area/add']}">
							<button type="button" class="btn btn-success"  data-type="ajax"   data-url="common/area/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
						</c:if>
					</div> --%>
					<div id="isLockCityList"></div>
				</div>
			</div>
		</div>
		</c:if>
		
	</div>
	<script type="text/json" id="permissions">{open:'${ btnAu['common/cityManagerment/open']}', lock:'${ btnAu['common/cityManagerment/lock']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<!-- 已开通 -->
	<c:if test="${ btnAu['common/cityManagerment/open/init']}"><script src="<%=path%>/js/common/openCityList.js"></script></c:if>
	<!-- 未开通 -->
	<c:if test="${ btnAu['common/cityManagerment/lock/init']}"><script src="<%=path%>/js/common/lockCityList.js"></script></c:if>	
  </body>
</html>

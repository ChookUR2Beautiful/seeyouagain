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
    <title>区域中商圈管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<!-- <form action="" id="navigateForm"></form> -->
  	<input type="hidden" name="areaId" value="${areaId }" id="areaIdTag">
  	
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
  		<c:if test="${ btnAu['common/area/bussiness/open/init']}">
	      <li <c:if test="${ btnAu['common/area/bussiness/open/init']}">class="active"</c:if> >
	        <a href="#tab1" data-toggle="tab">已开通</a>
	      </li>
      	</c:if>
      	<c:if test="${ btnAu['common/area/bussiness/lock/init']}">
	      <li <c:if test="${ empty btnAu['common/area/bussiness/open/init'] && btnAu['common/area/bussiness/lock/init']}"> class="active" </c:if> >
	        <a href="#tab2" data-toggle="tab">未开通</a>
	      </li>
      </c:if>
    </ul>
    
    <div class="tab-content">
	    <!-- 已开通 -->
	    <c:if test="${ btnAu['common/area/bussiness/open/init']}">
		    <div id="tab1" class="tab-pane <c:if test="${ btnAu['common/area/bussiness/open/init']}">in active</c:if>">
		    <div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form"  id="searchFormOpenArea">
						<div class="form-group">
							<label class="col-md-1 control-label"  style="float: left;">商圈名称：</label>
							<div class="col-md-3" style="width: 15%;">
								<input type="text" class="form-control" name="btitle"
									value="${param.btitle}" />
							</div>
							<div class="submit">
								<input class="submit radius-3" type="button" value="查询全部"
									data-bus='query' /> <input type="reset" class="reset radius-3"
									value="重置全部" data-bus='reset' />
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
					<div id="openBussinessInArea"></div>
				</div>
			</div>
			</div>
		</c:if>
		
		<!-- 未开通-->
		<c:if test="${ btnAu['common/area/bussiness/lock/init']}">
		<div id="tab2" class="tab-pane <c:if test="${empty btnAu['common/area/bussiness/open/init'] && btnAu['common/area/bussiness/lock/init']}">in active</c:if>">
		 <div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form"  id="searchFormLockArea">
						<div class="form-group">
							<label class="col-md-1 control-label"  style="float: left;">商圈名称：</label>
							<div class="col-md-3" style="width: 15%;">
								<input type="text" class="form-control" name="btitle"
									value="${param.btitle}" />
							</div>
							<div class="submit">
								<input class="submit radius-3" type="button" value="查询全部"
									data-bus='query' /> <input type="reset" class="reset radius-3"
									value="重置全部" data-bus='reset' />
							</div>
						</div>
					</form>
				</div>
			</div>
		<div class="panel panel-default">
			<div class="panel-body data">
				<div id="lockBussinessInArea"></div>
			</div>
		</div>
		</div>
		</c:if>
	</div>
	
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<!--开通、关闭 -->
	<script type="text/json" id="permissions">{open:'${ btnAu['common/area/bussiness/open']}', lock:'${ btnAu['common/area/bussiness/lock']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script type="text/javascript">inserTitle(' > 基础数据管理 > <a href="common/area/init.jhtml" target="right">区域管理</a>','areaSpan',true);</script>
	
	<!-- 已开通 -->
	<c:if test="${ btnAu['common/area/bussiness/open/init']}"><script src="<%=path%>/js/common/openBussinessInArea.js"></script></c:if>
	<!-- 未开通 -->
	<c:if test="${ btnAu['common/area/bussiness/lock/init']}"><script src="<%=path%>/js/common/lockBussinessInArea.js"></script></c:if>
  </body>
</html>
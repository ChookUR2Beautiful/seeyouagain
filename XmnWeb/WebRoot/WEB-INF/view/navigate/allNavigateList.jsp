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
    <title>分类导航管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<form action="" id="navigateForm"></form>
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
  		<c:if test="${ btnAu['user_terminal/navigate/oneLevel/init']}">
	      <li <c:if test="${ btnAu['user_terminal/navigate/oneLevel/init']}">class="active"</c:if> >
	        <a href="#tab1" data-toggle="tab">一级导航</a>
	      </li>
      	</c:if>
      	<c:if test="${ btnAu['user_terminal/navigate/secondLevel/init']}">
	      <li <c:if test="${ empty btnAu['user_terminal/navigate/oneLevel/init'] && btnAu['user_terminal/navigate/secondLevel/init']}"> class="active" </c:if> >
	        <a href="#tab2" data-toggle="tab">二级导航</a>
	      </li>
      </c:if>
    </ul>
    
    <div class="tab-content">
	    <!-- 一级导航 -->
	    <c:if test="${ btnAu['user_terminal/navigate/oneLevel/init']}">
		    <div id="tab1" class="tab-pane <c:if test="${ btnAu['user_terminal/navigate/oneLevel/init']}">in active</c:if>">
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${!empty btnAu['user_terminal/navigate/add']}">							
							<a type="button" id="addShareBto" class="btn btn-success" href="user_terminal/navigate/add/init.jhtml">
							<span class="icon-plus"></span>&nbsp;添加</a>
						</c:if>
					</div>
					<div><small style="color:red;">温馨提醒：该区域固定5个位置，请对5个位置进行设置，设置好后将在全国范围内上线</small></div>
					<div id="oneLevelNavigate"></div>
				</div>
			</div>
			</div>
		</c:if>
		
		<!-- 二级导航-->
		<c:if test="${ btnAu['user_terminal/navigate/secondLevel/init']}">
		<div id="tab2" class="tab-pane <c:if test="${empty btnAu['user_terminal/navigate/oneLevel/init'] && btnAu['user_terminal/navigate/secondLevel/init']}">in active</c:if>">
		<div class="panel panel-default">
			<div class="panel-body data">
				<div class="btn-group" style="margin-bottom: 5px;">
					<c:if test="${!empty btnAu['user_terminal/navigate/secondLevel/add']}">
						<a type="button" id="addShareBto" class="btn btn-success" href="user_terminal/navigate/secondLevel/add/init.jhtml">
						<span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
				</div>
				<div><small style="color:red;">温馨提醒：该区域固定3个位置，请对3个位置进行设置，设置好后将在全国范围内上线</small></div>
				<div id="secondLevelNavigate"></div>
			</div>
		</div>
		</div>
		</c:if>
	</div>
	
	<!-- 修改、详情、排序 -->
	<script type="text/json" id="permissions">
	{
		oneLevelNavigateUpdate:'${ btnAu['user_terminal/navigate/update']}',
		oneLevelNavigateDetail:'${btnAu['user_terminal/navigate/detail'] }',
		oneLevelNavigateSort:'${btnAu['user_terminal/navigate/sort']}',
	
		secondLevelNavigateUpdate:'${ btnAu['user_terminal/navigate/secondLevel/update']}',
		secondLevelNavigateDetail:'${btnAu['user_terminal/navigate/secondLevel/detail'] }',
		secondLevelNavigateSort:'${btnAu['user_terminal/navigate/secondLevel/sort']}'
	}

	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script type="text/javascript">inserTitle(' > 用户端管理  > <a href="user_terminal/navigate/init.jhtml" target="right">分类导航管理</a>','nagivateSpan',true);</script>
	
	<!-- 一级导航 -->
	<c:if test="${ btnAu['user_terminal/navigate/oneLevel/init']}"><script src="<%=path%>/js/navigate/oneLevelNavigate.js"></script></c:if>
	<!-- 二级导航 -->
	<c:if test="${ btnAu['user_terminal/navigate/secondLevel/init']}"><script src="<%=path%>/js/navigate/secondLevelNavigate.js"></script></c:if>
  </body>
</html>
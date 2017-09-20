<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>搜索标签管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	
  </head>
  
  <body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  <div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
   <div class="panel">
   	<div class="row panel-body">
   		<div class="col-xs-2"><h5>选择城市：</h5></div> 
   		<div class="col-xs-2">
   			<select class="form-control" id="city">
   			<option value="">请选择</option>
	   			<c:forEach var="area" items="${ areaList}">
	   				<option value="${area.areaId }">${area.title }</option>
	   			</c:forEach>
   			</select>
   		</div>
   	</div>
   
   </div>
   
    <div class="panel">
    	<!-- 城市信息展示 -->
		<div class="panel-body" id="citySearchTagsView">
			<p class="lead text-center">请先选择城市</p>
		</div>
	</div>
	<script type="text/json" id="permissions">{setDisplaySearchTag :'${btnAu['user_terminal/searchTags/setDisplaySearchTag'] }'}</script>
   <jsp:include page="../../common.jsp"></jsp:include>
     <script src="<%=path%>/ux/js/scrollTablel.js"></script>
   <script type="text/javascript">
		$(function(){ 
			inserTitle(' > 用户端管理 > <a href="user_terminal/searchTags/init.jhtml" target="right">搜索标签管理</a>','searchTags',true);
			var citySearchTagsView = $("#citySearchTagsView");
			$("#city").on("change",function(event){
				var cityId = $(this).val();
				if(cityId && cityId.length>0){
					getView("/user_terminal/searchTags/init/getCitySearchTagsView.jhtml",{"aid"  : cityId},function(data){
						citySearchTagsView.html(data);
					});
				}else{
					citySearchTagsView.html('<p class="lead text-center">请先选择城市</p>');
				}
			});
		});
		function getView(url,param,fnc){
			 $.get(baseURI+url,param,'html'
				).done(function(data){
					fnc.call(this,data);
			 	}).fail(function(){
				  	$('#prompt').hide();
					$('#triggerModal').modal('hide');
					showSmReslutWindow(false, "请求服务器失败");
			  });
		}
		
   </script>
  </body>
</html>

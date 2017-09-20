<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>所有订单</title>
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
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchStartImageForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:8%;"><h5>&nbsp;&nbsp;客户端类型:</h5></td>
					        <td style="width:15%;"  >
							     <select class="form-control" name="type" style = "width:90%;">
									<option value="">全部</option>
									<option value="1">商户版</option>
									<option value="2">寻蜜鸟用户版</option>
									<option value="3">合作商版</option>
								</select>
							</td>	
							
 							<!--<td style="width:8%;"><h5>&nbsp;&nbsp;设备类型:</h5></td>
					        <td style="width:15%;"  >
							     <select class="form-control" name="atype" style = "width:90%;">
									<option value="">全部</option>
									<option value="1">Android</option>
									<option value="2">IOS</option>
									<option value="3">WP</option>
								</select>
							</td> -->	
							
							<td style="width:8%;"><h5>&nbsp;&nbsp;状态:</h5></td>
					        <td style="width:15%;"  >
							     <select class="form-control" name="status" style = "width:90%;">
									<option value="">全部</option>
									<option value="0">启用</option>
									<option value="1">停用</option>
								</select>
							</td>	
							<td colspan="2">
								<div class="submit">
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
			<div class="btn-group" style="margin-bottom: 15px;">
				 <!--<c:if test="${!empty btnAu['startImageManagerment/startImageSet/add']}">
				    data-toggle="modal" data-url
					<button type="button" class="btn btn-success"  data-width="50%"  href="startImageManagerment/startImageSet/add/init.jhtml">
					<span class="icon-plus"></span>&nbsp;添加</button>
				</c:if>-->
				
				<c:if test="${null!=btnAu['startImageManagerment/startImageSet/add'] && btnAu['startImageManagerment/startImageSet/add']}">
				      <a type="button"  class="btn btn-primary"  id="addStartImageSetBto" href="startImageManagerment/startImageSet/add/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加</a></c:if>
			</div>
			<div id="allStartImages"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{check:'${ btnAu['startImageManagerment/startImageSet/check']}',update:'${ btnAu['startImageManagerment/startImageSet/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/start_image/allStartImageList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

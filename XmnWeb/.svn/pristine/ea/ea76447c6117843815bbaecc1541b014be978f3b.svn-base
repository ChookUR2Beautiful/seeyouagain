<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>注册礼包管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
	   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<%--
  	<div class="panel">
		<div class="panel-body">
		</div>
	</div>
	 --%>
	<form class="form-horizontal" role="form" method="post"  id="searchForm">
	</form>
	
	<div class="panel panel-default">
		
		<div class="panel-body data">
		
		<c:if test="${!empty btnAu['user_terminal/register_gift/update']}">
			<a type="button" class="btn btn-success" id="ImgBtn" data-type="ajax"  data-url="user_terminal/register_gift/img/update/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;图片管理</a>
		</c:if>
			<div id="picList">

				<table class="table" style="text-align: center;">
					<tr>	
						<td style="width:10%;"><h5>前往注册图:</h5> </td>							
						<td style="text-align: left;width:23.3%;">	
							<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${img.registerImg}"  rel="slide" title="前往注册图" class="fancybox"  id="registerImg" target="_blank">
								<input type="hidden" value="${img.registerImg}" id="sellerUrlId">
								<img alt="前往注册图"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${img.registerImg}"   style="width: 100px;height: 100px;">			
							</a>
						</td> 
									
						<td style="width:10%;"><h5>奖励发放图:</h5> </td>							
						<td style="text-align: left;width:23.3%;">	
							<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${img.giftImg}"  rel="slide" title="奖励发放图" class="fancybox"  id="giftImg" target="_blank">
							<input type="hidden" value="${img.giftImg}" id="sellerUrlId2">
								<img alt="奖励发放图"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${img.giftImg}"   style="width: 100px;height: 100px;">			
							</a>
						</td> 
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['user_terminal/register_gift/add']}">
				<a type="button" class="btn btn-success" id="addBtn"  href="user_terminal/register_gift/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${!empty btnAu['user_terminal/register_gift/export']}">
				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="giftList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/register_gift/update']}',delete:'${ btnAu['user_terminal/register_gift/delete']}',add:'${ btnAu['user_terminal/register_gift/add']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>

	<script src="<%=path%>/js/user_terminal/registerGift.js"></script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
  </body>
</html>

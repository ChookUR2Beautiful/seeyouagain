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
    <title>广告轮播</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<style>
		.submit{text-align:left;}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
			<input type="hidden" name="type" value="5"/> 
			<input type="hidden" id="isshow" name="isshow" /> 
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;广告标题：</h5></td>
							<td style="width:27%;">
								<input type="text" class="form-control" name="content" style="width:80%;">
							</td>
							<td style="width:6%;"><h5>&nbsp;&nbsp;是否全国:</h5></td>
							<td style="width:27% !important;">
								<select name="isall" class="form-control" style="width:80%">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
							<td style="width:6%;"><h5>区域查询:</h5></td>
							<td style="width:27% !important;">
								<div class="input-group" id="ld" style="width:78%"></div>
							</td>
						</tr>
						<tr>
							<td  colspan="4" style="width:66%;"></td>
							<td colspan="2" style="width:33%;" >
								<div class="submit" style="text-align: left;">
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
				<c:if test="${null!=btnAu['user_terminal/bannerAdvertising/add'] && btnAu['user_terminal/bannerAdvertising/add']}"><button type="button" class="btn btn-success"  data-type="ajax"  data-width="700px" data-url="user_terminal/bannerAdvertising/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				&nbsp;&nbsp;</c:if>
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<button type="button" class="btn btn-success"  onclick="queryBanner(this,'');" name="bumen">全部</button>&nbsp;&nbsp;	
	            <button type="button" class="btn btn-default"  onclick="queryBanner(this,'1');" name="bumen">待上线</button>&nbsp;&nbsp;	  
				<button type="button" class="btn btn-default"  onclick="queryBanner(this,'0');" name="bumen">已上线</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-default"  onclick="queryBanner(this,'2');" name="bumen">已下线</button>	
			    </div>
			<div id="bannerAdvertisingList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/bannerAdvertising/update']}',online:'${ btnAu['user_terminal/bannerAdvertising/updateBannerStatusOnLine']}',offline:'${ btnAu['user_terminal/bannerAdvertising/updateBannerStatusOffLine']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/user_terminal/banner_advertising.js"></script>
<%-- 	<script src="<%=path%>/js/common/advertising.js"></script> --%>
  </body>
</html>

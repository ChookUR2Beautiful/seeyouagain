<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>生鲜列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/web/css/jquery.validate.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<input type="hidden" id="checkbox" value="${btnAu['user_terminal/banner/delete']}" />
  	<input type="hidden" id="picSerUrl" value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchForm">
			<input type="hidden"  name="parent" value="${parent}" />
			<input type="hidden" name="objectOriented" value="${objectOriented}" />
			
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>寻蜜客编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" name="uid" style = "width:90%;"/></td>
							<td colspan="2" style="width:5%;"></td>
							<td style="width:5%;"><h5>寻蜜客姓名:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control"  name="nname" style = "width:90%;"/>
							</td>
							<td style="width:5%;"></td>
							<td style="width:18%;">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>手机号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="phone" style = "width:90%;"/>
							</td>
							<td  colspan="2" style="width:5%;"><h5></h5></td>
						 	<td style="width:5%;"><h5></h5></td>
							<td style="width:18%;">
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
			</div>
			<div id="directPartnerList"></div>
		</div>
	</div>
     <!-- 操作结果提示层 -->
	<div class="modal fade" id="sm_reslut_window" data-position="100px">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">
{update:'${btnAu['user_terminal/banner/update']}',check:'${btnAu['user_terminal/banner/view']}',wallet:'${btnAu['user_terminal/banner/view']}',xmer:'${btnAu['user_terminal/banner/view']}',
idp:'${btnAu['user_terminal/banner/update']}'}
</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/xmermanagerment/directPartherList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>

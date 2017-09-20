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
    <title>举报商家列表</title>
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
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr >
							<td style="width:5%;"><h5>&nbsp;&nbsp;举报类型:</h5></td>
							<td style="width:20%;">
								<select class="form-control"  name="type" style="width:90%"> <!-- 发送状态|0=待发送|1=已发送 -->
						        	<option value="" >全部</option>
									<option value="1">商家基础信息有误</option>
									<option value="2">电话空号</option>
									<option value="3">地址错误</option>
									<option value="4">无法支付</option>
								 </select>
							</td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;被举报商家编号:</h5></td>
							<td style="width:10%;"><input type="text" class="form-control"  name="sellerid" value="${abnormalSeller.sellerid}" style="width:90%"></td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;被举报商家:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="sellername" value="${abnormalSeller.sellername}" style="width:90%"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;处理状态:</h5></td>
							<td >
								 <select class="form-control"  name="status" style="width:90%"> <!-- 发送状态|0=待发送|1=已发送 -->
						        	<option value="" >全部</option>
									<option value="0">待处理</option>
									<option value="1">已处理</option>
									<option value="2">虚假举报</option>
								 </select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;" nowrap="nowrap"><h5>&nbsp;&nbsp;举报时间:</h5></td>		
							<td style="width:20%;">
								<input type="text" name ="sdateStart" placeholder="yyyy-MM-dd hh:mm" value="${abnormalSeller.sdateStart}" class="form-control form-datetime"style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="sdateEnd" placeholder="yyyy-MM-dd hh:mm" value="${abnormalSeller.sdateEnd}" class="form-control form-datetime" style="width:40%;float:left">
							</td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;举报会员编号:</h5></td>
							<td style="width:10%;"><input type="text" class="form-control"  name="uid" value="${abnormalSeller.uid}" style="width:90%"></td>
							<td style="width:8%;"><h5>&nbsp;&nbsp;举报会员电话:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="phone" value="${abnormalSeller.phone}" style="width:90%"></td>
							<td></td>
							<td>
								<div class="submit submit-sp" style="float:left; margin-left: 10px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query'  />
									<input class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
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
<!-- 				<c:if test="${!empty btnAu['user_terminal/abnormal_seller/export']}"> -->
<!-- 				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button> -->
<!-- 				</c:if> -->
			</div>
			<div id="abnormalList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/abnormal_seller/update']}',delete:'${ btnAu['user_terminal/abnormal_seller/delete']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/user_terminal/abnormalSeller/abnormalSeller.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		limitedDate({form:"#searchForm",startDateName:"sdateStart",endDateName:"sdateEnd"});
		
	});
	</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
  </body>
</html>

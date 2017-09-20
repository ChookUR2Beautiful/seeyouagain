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
    <title>商家账号登录记录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">  
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
	<style type="text/css">
	.submit{text-align: left;}
	</style>
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称：</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="sellername"  style="width:90%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;账户：</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="account"  style="width:90%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;登陆时间：</h5></td>
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="sdateStart" style="width:36%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="sdateEnd" style="width:36%;float:left">
							
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;账户类型：</h5></td>
							<td style="width:25%;">
								<select class="form-control"  name="type"  style="width:90%;">
									<option value="">全部</option>
									<option value="1">商家管理账号</option>
									<option value="2">收银员账号</option>
									<option value="3">普通店员账号</option>
								</select>
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;手机系统：</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="system"  style="width:90%;"></td>
							<td colspan="2" style="width:30%;"><div class="submit">&nbsp;&nbsp;<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<c:if test="${null!=btnAu['businessman/loginRecord/export'] && btnAu['businessman/loginRecord/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>
				
			</div>
			<div id="loginRecordList"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/loginRecord.js"></script>
  </body>
</html>

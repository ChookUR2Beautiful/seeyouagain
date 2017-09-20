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
    <title>折扣设置记录</title>
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
			<form class="form-horizontal" role="form"  method="post" id="searchForm">
				 <table style="width:100%;">
				 		<tbody>
						<tr>
							<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:18%;"><input type="text" style="width:90%;" name="sellerid" class="form-control"></td>
							<td style="width:5%;"><h5>商家名称:</h5></td>
							<td style="width:18%;"><input type="text" style="width:90%;" name="sellername" class="form-control"></td>
							<td style="width:5%;"><h5>运营平台用户编号:</h5></td>
							<td style="width:18%;"><input type="text" style="width:90%;" name="uid" class="form-control"></td> 
							<td style="width:5%;"><h5>操作途径:</h5></td>
							<td align="left" style="width:24%;">
								<select class="form-control"  name="operation"  style="width:88%;">
									<option value="">全部</option>
									<option value="1">APP(商户版)</option>
									<option value="2">WEB(商户WEB版)</option>
									<option value="3">SYSTEM(运营平台)</option>
								</select>
							</td>	
							
						</tr>
						<tr>
						   	<td style="width:5%;"><h5>起始时间:</h5></td>
							<td style="width:17%;">
								<input type="text" style="width:41%;float:left" class="form-control form_datetime" placeholder="yyyy-MM-dd hh:mm" name="stdateStart">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" style="width:42%;float:left" class="form-control form_datetime" placeholder="yyyy-MM-dd hh:mm" name="stdateEnd">
							</td>
							<td style="width:5%;"><h5>结束时间:</h5></td>
							<td style="width:17%;">
								<input type="text" style="width:41%;float:left" class="form-control form_datetime" placeholder="yyyy-MM-dd hh:mm" name="endateStart">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" style="width:42%;float:left" class="form-control form_datetime" placeholder="yyyy-MM-dd hh:mm" name="endateEnd">
							</td>
							<td style="width:5%;"><h5>记录时间:</h5></td>
							<td style="width:17%;">
								<input type="text" style="width:41%;float:left" class="form-control form_datetime" placeholder="yyyy-MM-dd hh:mm" name="sdateStart">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" style="width:42%;float:left" class="form-control form_datetime" placeholder="yyyy-MM-dd hh:mm" name="sdateEnd">
							</td> 
							<td style="width:29%;" colspan="2">
								<div style="text-align: left;" class="submit">
									<input type="button" data-bus="query" value="查询全部" class="submit radius-3">
									<input type="reset" data-bus="reset" value="重置全部" class="reset radius-3">
								</div>
							</td>
						</tr>
						<tr>
					    </tr>
					</tbody>
				<!-- 	<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称：</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="sellername"  style="width:85%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;起始时间：</h5></td>
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="stdateStart" style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="stdateEnd" style="width:40%;float:left">
							</td>
							
							<td style="width:60px;"><h5>&nbsp;&nbsp;运营平台用户ID：</h5></td>
							<td style="width:180px;"><input type="text" class="form-control"  name="uid"  style="width:85%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;记录时间：</h5></td>
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="sdateStart" style="width:36%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="sdateEnd" style="width:36%;float:left">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;操作途径：</h5></td>
							<td style="width:25%;">
								<select class="form-control"  name="operation"  style="width:85%;">
									<option value="">全部</option>
									<option value="1">APP(商户版)</option>
									<option value="2">WEB(商户WEB版)</option>
									<option value="3">SYSTEM(运营平台)</option>
								</select>
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;结束时间：</h5></td>
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="endateStart" style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="endateEnd" style="width:40%;float:left">
							</td>
							<td colspan="2" style="width:30%;"><div class="submit">&nbsp;&nbsp;<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody> -->
				</table>  
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['businessman/agioRecord/export'] && btnAu['businessman/agioRecord/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>
			</div>
			<div id="agioRecordList"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	 <script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/agioRecord.js"></script>
  </body>
</html>

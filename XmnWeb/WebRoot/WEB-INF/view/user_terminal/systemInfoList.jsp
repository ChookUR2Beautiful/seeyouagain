<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>系统信息发布</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:40px;"><h5>&nbsp;&nbsp; 创建时间:</h5></td>							
							<td style="width:170px;">
								<input type="text" name ="sdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="sdateeEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:40px;"><h5>&nbsp;&nbsp; 发送时间:</h5></td>							
							<td style="width:170px;">
								<input type="text" name ="fdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="fdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:40px;"><h5>&nbsp;&nbsp; 过期时间:</h5></td>							
								<td style="width:170px;">
								<input type="text" name ="edateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="edateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:45px;"><h5>&nbsp;&nbsp;&nbsp;发送状态：</h5></td>
								<td style="width:150px;">
										<select class="form-control" name ="status" style = "width:90%;">
										    <option value = "">请选择</option>
							                <option value = "0">待发送</option>
							                <option value = "1">已发送</option>
							             </select>
							   </td>
						</tr>
						<tr>
							<td colspan="8"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<button type="button" class="btn btn-success"  data-type="ajax"   data-url="user_terminal/systemInfo/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
			</div>
			<div id="systemInfoList"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/user_terminal/systemInfo.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>系统信息推送</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">  
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:50px;"><h5>&nbsp;&nbsp;发送状态：</h5></td>
							<td style="width:180px;">
								<select class="form-control" name="status" style="width:85%;">
									<option value="">全部</option>
									<option value="0">待发送</option>
									<option value="1">已发送</option>
								</select>
							</td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;是否推送：</h5></td>
							<td style="width:180px;">
								<select class="form-control" name="ispush" style="width:85%;">
									<option value="">全部</option>
									<option value="0">不推送</option>
									<option value="1">推送</option>
								</select>
							</td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;通知类型：</h5></td>
							<td style="width:180px;">
								<select class="form-control" name="type" style="width:85%;">
									<option value="">全部</option>
									<option value="0">用户系统通知</option>
									<option value="1">向蜜客系统通知</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:50px;"><h5>&nbsp;&nbsp;创建时间：</h5></td>
							<td style="width:180px;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="sdateStart" style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="sdateEnd" style="width:40%;float:left">
							</td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;发送时间：</h5></td>
							<td style="width:180px;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="tdateStart" style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="tdateEnd" style="width:40%;float:left">
							</td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;过期时间：</h5></td>
							<td style="width:180px;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="edateStart" style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="edateEnd" style="width:40%;float:left">
							</td>
						</tr>
						<tr style="height: 20px"></tr>
						<tr>
							<td colspan="6"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<button type="button" class="btn btn-success"  data-type="ajax"   data-url="common/systemMsg/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				<!-- <div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="systemMsgList"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/common/systemMsg.js"></script>
  </body>
</html>

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
    <title>APP推送信息</title>
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
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:50px;"><h5>&nbsp;&nbsp;信息标题</h5></td>
							<td style="width:130px;"><input type="text" class="form-control"  name="title"  style="width:90%"></td>
							<input type="hidden" name="client" value="2"/>
							<!-- <td style="width:50px;"><h5>&nbsp;&nbsp;客户端类型	</h5></td>
							<td style="width:120px;">
							<input type="text" class="form-control"  name="tid"  style="width:120px;">					
								 <select class="form-control"  name="client" style="width:120px;"> 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端
								        <option value="" >请选择客户端类型</option>
										<option value="1" >寻蜜鸟客户端</option>
										<option value="2" > 商户客户端</option>
										<option value="3" >合作商客户端</option>
										
								 </select>
							</td> -->
							<td style="width:50px;"><h5>&nbsp;&nbsp;发送状态</h5></td>
							<td style="width:130px;">
							<!-- <input type="text" class="form-control"  name="tid"  style="width:120px;"> -->					
								 <select class="form-control"  name="status" style="width:90%"> <!-- 发送状态|0=待发送|1=已发送 -->
								        <option value="" >请选择发送状态</option>
										<option value="0" >待发送</option>
										<option value="1" >已发送</option>										
								 </select>
							</td>
							
							<td style="width:50px;"><h5>&nbsp;&nbsp;后续动作类型</h5></td>
							<td style="width:130px;">
							<!-- //1=打开应用|2=网址|3=activity -->
							<select class="form-control"  name="type" style="width:90%">
								        <option value="" >请选择后续动作类型</option>
										<option value="1" >打开应用</option>
										<option value="2" >网址</option>
										<option value="3" >activity</option>									
								 </select>	
							</td>
							
							<td style="width:50px;"><h5>&nbsp;&nbsp;提醒方式</h5></td>
							<td style="width:130px;">
							<!-- <input type="text" class="form-control"  name="tid"  style="width:120px;"> -->
							<!-- //提醒方式  0=声音|1=震动|2=呼吸灯 -->
							<select class="form-control"  name="remind" style="width:90%">
								        <option value="" >请选择提醒方式</option>
										<option value="0" >声音</option>
										<option value="1" >震动</option>
										<option value="2" >呼吸灯</option>									
								 </select>	
							</td>
							
						</tr>
						<tr>
							<td style="width:50px;"><h5>&nbsp;&nbsp;发送给谁</h5></td>
							<td style="width:130px;"><input type="text" class="form-control"  name="object"  style="width:90%"></td>
							
							<td style="width:50px;"><h5>&nbsp;&nbsp;信息内容</h5></td>
							<td style="width:130px;"><input type="text" class="form-control"  name="content"  style="width:90%"></td>
												
							<!-- edate;// 过期时间|说明:过期时间需要至少晚于发送时间30分钟 -->
							<td style="width:50px;"><h5>&nbsp;&nbsp;过期时间:</h5></td>							
							<td style="width:130px;">
							<input type="text" name ="edateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
							<label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="edateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
						
						</tr>
							<tr style="height: 20px"></tr>
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
				<c:if test="${null!=btnAu['businessman/appPush/add'] && btnAu['businessman/appPush/add']}"><button type="button" class="btn btn-success"  data-type="ajax" data-url="businessman/appPush/add/init.jhtml" data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				</c:if>
				<c:if test="${null!=btnAu['businessman/appPush/delete'] && btnAu['businessman/appPush/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if>
				<c:if test="${null!=btnAu['businessman/appPush/export'] && btnAu['businessman/appPush/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
				<!-- <div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="appPushList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{del:'${btnAu['businessman/appPush/delete'] }',export:'${btnAu['businessman/appPush/export'] }',update :'${btnAu['businessman/appPush/update'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/appPush.js"></script>
  </body>
</html>

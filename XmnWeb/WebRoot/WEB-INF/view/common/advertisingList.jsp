<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:50px;"><h5>&nbsp;&nbsp;广告文本：</h5></td>
							<td style="width:120px;"><input type="text" class="form-control"  name="content"  style="width:90%;"></td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;是否显示：</h5></td>
							<td style="width:120px;">
								<select class="form-control"  name="isshow" style="width:90%;">
									<option value="">全部</option>
									<option value="0" ${advertising.isshow==0?"selected":""}>显示</option>
									<option value="1" ${advertising.isshow==1?"selected":""}>不显示</option>
								</select>
							</td>
							<td style="width:50px;"><h5>&nbsp;&nbsp;广告类型：</h5></td>
							<td style="width:120px;">
								<select class="form-control"  name="type" style="width:90%;">
									<option value="">全部</option>
									<option value="1" ${advertising.type==1?"selected":""}>寻蜜鸟客户端</option>
									<option value="2" ${advertising.type==2?"selected":""}>商户客户端</option>
									<option value="3" ${advertising.type==3?"selected":""}>合作商客户端</option>
									<option value="4" ${advertising.type==4?"selected":""}>寻蜜客圈子广告</option>
								</select>
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
				<button type="button" class="btn btn-success"  data-type="ajax"   data-url="common/advertising/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				<!-- <div class="btn-group">
					<button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></button>
					<ul class="dropdown-menu"  role="menu" aria-labelledby="btnGroupDrop1">
						<li><a href="#">更多操作</a></li>
					</ul>
				</div> -->
			</div>
			<div id="advertisingList"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/common/advertising.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
  </body>
</html>

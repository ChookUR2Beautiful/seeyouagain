<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>庄园等级管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;等级名称:</h5></td>
								<td style="width:25%;"><input class="form-control"   name="name" style="width:66%;"> </td>
								<td style="width:5%;"></td>
								<td style="width:25%;"></td>
								<td style="width:5%;"><h5></h5></td>
								<td colspan="2" >
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style = "width:40%;" />
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style = "width:40%;" />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${null!=btnAu['manor/level/add'] && btnAu['manor/level/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="添加等级信息" data-url="manor/level/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>

				<c:if
					test="${null!=btnAu['manor/activate/setting'] && btnAu['manor/activate/setting']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="激活管理" data-url="manor/activate/setting/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;激活管理
					</button>
				</c:if>
			</div>
			<div id="recordList"></div>
		</div>
	</div>
	
	 <!-- 设置优惠券-->

     <div class="modal fade" id="couponSetting" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请选择奖励优惠券</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="couponFrom">
						
							<label for="courierNumber" class="col-sm-2 control-label"><h5>选择优惠券:</h5></label>
							<div class="input-group">
							 <select class="form-control"  name ="ctype" id="ctype" >
				                <option value = "5">通用券</option>
				                <option value = "2">商家券</option>
				                <option value = "1">美食券</option>
						      </select>
						      <select class="form-control" id="cid" name="cid"   >
							  </select> 							  
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="exportsubconcel" type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="exportconfirm" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
	<script type="text/json" id="permissions">{
		 update:'${ btnAu['manor/level/update']}',
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/golden_manor/manorLevel.js?V1.1"></script>
</body>
</html>

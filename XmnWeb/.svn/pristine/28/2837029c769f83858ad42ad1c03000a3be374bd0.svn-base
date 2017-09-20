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
    <title>营销活动管理</title>
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
							<td style="width:40px;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
							<td style="width:130px;"><input type="text" class="form-control"  name="aname"  style="width:90%"></td>
							
							<td style="width:40px;"><h5>&nbsp;&nbsp;活动类型：</h5></td>
							<td style="width:130px;">
								 <select class="form-control"  name="type" style="width:90%"> 
								        <option value="">请选择</option>
										<option value="1">抽奖返利活动</option>
										<option value="2">消费赠送活动</option>
										<option value="3">教育基金活动</option>	
										<option value="4">消费补贴活动</option>									
								 </select>
							</td>
							
							<td style="width:40px;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
							<td style="width:130px;">
								<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
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
				<c:if test="${!empty btnAu['marketingManagement/activitymanagement/add']}"><button type="button" class="btn btn-success"  data-type="ajax"  data-url="marketingManagement/activitymanagement/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				</c:if>
				<%-- 
				<c:if test="${!empty btnAu['marketingManagement/activitymanagement/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if>
				 --%>
			</div>
			<div id="activityList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['marketingManagement/activitymanagement/update']}',del:'${btnAu['marketingManagement/activitymanagement/delete'] }',check:'${btnAu['marketingManagement/activitymanagement/check'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/marketingmanagement/activity.js"></script>
</body>
</html>

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
    <title>用户须知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">  
	<style>
		.submit{text-align:left;}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
			<input type="hidden" name="type" value="5"/> 
			<input type="hidden" id="isshow" name="isshow" /> 
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;商家须知:</h5></td>
							<td style="width:27%;">
							<input type="text"	class="form-control" name="remark" style="width:80%;" /></td>
							<td style="width:6%;"><h5>&nbsp;&nbsp;是否有效:</h5></td>
							<td style="width:27% !important;">
								<select name="STATUS" class="form-control" style="width:80%">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
									
								</select>
							</td>
							<td colspan="2" style="width:33%;" >
								<div class="submit submit-sp">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
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
				<c:if test="${null!=btnAu['user_terminal/tsellernotice/add'] && btnAu['user_terminal/tsellernotice/add']}"><button type="button" class="btn btn-success" data-title="商家须知添加" data-type="ajax"  data-width="700px" data-url="user_terminal/tsellernotice/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				&nbsp;&nbsp;</c:if>
			</div>
			<div id="sellerNoticeList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/tsellernotice/update']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/user_terminal/sellernotice/SellerNoticeServiceList.js"></script>
    <script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
    <script type="text/javascript">
  $('.form-datetime').datetimepicker({
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	todayHighlight : 1,
	startView : 2,
	forceParse : 0,
	showMeridian : 1,
	format : 'yyyy-mm-dd hh:ii'	
    });   
  $(document).ready(function() {
	limitedDate({form:"#searchForm",startDateName:"dateCreatedStart",endDateName:"dateCreatedEnd"});
	});
    </script>
  </body>
</html>

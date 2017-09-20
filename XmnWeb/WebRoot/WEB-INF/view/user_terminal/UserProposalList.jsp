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
    <title>用户吐槽</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet"> 
		<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">  
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<style>
		.submit{text-align:left;}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
			<input type="hidden" name="type" value="0">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;会员账号：</h5></td>
							<td style="width:27%;">
								<input type="text" class="form-control" placeholder="会员手机号" name="phoneid" style="width:80%;" >
							</td>
							<td style="width:6%;"><h5>&nbsp;&nbsp;吐槽对象:</h5></td>
							<td style="width:27% !important;">
								<select name="object" class="form-control" style="width:80%">
									<option value="">--请选择--</option>
									<option value="0">产品狗</option>
									<option value="1">设计狗</option>
									<option value="2">攻城狮</option>
									<option value="3">CEO</option>
								</select>
							</td>
							<td style="width:6%;"><h5></h5></td>
							<td style="width:27% !important;"></td>
						</tr>
						<tr>
						   <td style="width:6%;"><h5>&nbsp;&nbsp;处理状态:</h5></td>
							<td style="width:27% !important;">
								<select name="status" class="form-control" style="width:80%">
									<option value="">--请选择--</option>
									<option value="0">待处理</option>
									<option value="1">已处理</option>
								</select>
							</td>
						   <td style="width:6%;"><h5>&nbsp;&nbsp;日期:</h5></td>
							<td style="width:27% !important;">
								<input type="text" name ="dateCreatedStart" placeholder="yyyy-MM-dd hh:mm" value="${param.dateCreatedStart}" class="form-control form-datetime"style="width:37.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="dateCreatedEnd" placeholder="yyyy-MM-dd hh:mm" value="${param.dateCreatedEnd}" class="form-control form-datetime" style="width:38%;float:left">
							</td>
							<td colspan="2" style="width:33%;" >
								<div class="submit submit-sp">&nbsp;&nbsp;
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
			<div id="userProposalList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/userProposal/update']}'<%--,del:'${btnAu['user_terminal/advertising/delete'] }'--%>}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/user_terminal/UserProposalList.js"></script>
<%-- 	<script src="<%=path%>/js/common/advertising.js"></script> --%>
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

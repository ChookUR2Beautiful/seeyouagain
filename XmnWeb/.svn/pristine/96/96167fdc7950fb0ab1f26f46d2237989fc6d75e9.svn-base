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
    <title>合作商</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:40px;"><h5>&nbsp;&nbsp;公司名称:</h5></td>
							<td style="width:160px;">
							<input type="text" class="form-control"  name="corporate"  style="width:90%"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;法人姓名:</h5></td>
							<td style="width:160px;">
							<input type="text" class="form-control"  name="legalperson"  style="width:90%"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp; 开通时间:</h5></td>							
							<td style="width:170px;">
							<input type="text" name ="sdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
							<label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="sdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
						</tr>
						<tr>
							<td style="width:40px;"><h5>&nbsp;&nbsp;联系手机:</h5></td>
							<td style="width:160px;">
							<input type="text" class="form-control"  name="phoneid"  style="width:90%"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;登录账号:</h5></td>
							<td style="width:160px;">
							<input type="text" class="form-control"  name="account"  style="width:90%"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;区域查询:</h5></td>
							<td style="width:160px;"><div class="input-group" id="ld" style="width:90%"></div>
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
			<div class="row">
				<div class="col-md-2"><h5>已选择合作商:</h5></div>
				<div class="col-md-10"><div id="choseDatas" class="chosen-container chosen-container-multi chosen-with-drop chosen-container-active" style="width:100%"><ul class="chosen-choices"></ul></div></div>
			</div>
			<div id="jointList"></div>
		</div>
	</div>
	<div class="return" align="center">
		    <button class="btn btn-success closeChosen" type="button" ><i class="icon-ok-sign"></i>&nbsp;保  存</button>&nbsp;&nbsp;
		    <button class="btn btn-default closeChosen" id="allCancel"><span class="icon-reply"></span>&nbsp;取消</button>
	</div> 
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_cooperation/choseJoint.js"></script>
  </body>
</html>

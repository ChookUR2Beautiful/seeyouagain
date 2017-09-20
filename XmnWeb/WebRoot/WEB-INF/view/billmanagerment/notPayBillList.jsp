<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>待支付订单</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchNotPayBillForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:60px;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:230px;"><input type="text" class="form-control"  name="nname" style = "width:75%;"></td>
							<td style="width:60px"><h5>&nbsp;&nbsp;用户手机:</h5></td>
							<td style="width:230px;"><input type="text" class="form-control"  name="phoneid" style = "width:75%;"></td>
							<td style="width:60px;"><h5>订单号:</h5></td>
							<td style="width:230px;"><input type="text" class="form-control"  name="bid" style = "width:74%;"></td>
						</tr>
						<tr>
						<td style="width:60px;"><h5>&nbsp;&nbsp;所属商家:</h5></td>
						<td style="width:230px;"><input type="text" class="form-control"  name="genusname" style = "width:75%;"></td>
						<td style="width:60px;"><h5>&nbsp;&nbsp;消费商家:</h5></td>
						<td style="width:230px;"><input type="text" class="form-control"  name="sellername" style = "width:75%;"></td>		
						<td style="width:60px;"><h5>下单时间:</h5></td>							
						<td style="width:230px;">
								<input type="text" name ="sdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:34.5%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="sdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:35%;float:left">
						</td>								
						</tr>				
						<tr>
							<td style="width:60px;"><h5>&nbsp;&nbsp;订单金额:</h5></td>
							<td style="width:230px;">
							<input type="text" class="form-control"  name="moneyStart"  style="width:35%;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" class="form-control"  name="moneyEnd"  style="width:35%;float:left">
							</td>	
							<td style="width:60px;"></td><td style="width:230px;"></td>
						  <td colspan="2" style="width:300px;">
						  <div class="submit">&nbsp;&nbsp;
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
				<c:if test="${null!=btnAu['billmanagerment/notpaybill/export'] && btnAu['billmanagerment/notpaybill/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="notPayBillList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{details:'${ btnAu['billmanagerment/notpaybill/view/init']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/billmanagerment/notPayBillList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>申诉订单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr><input type="hidden" name="bid" value="${param.bid}"/>
						   <td style="width:40px;"><h5>&nbsp;&nbsp; 申请时间:</h5></td>							
							<td style="width:160px;" align="left">
								<input type="text" name ="startSdate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="endSdate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							
							<td style="width:40px;"><h5>&nbsp;&nbsp;&nbsp;订单状态:</h5></td>
							        <td style="width:160px;"  >
								     <select class="form-control" name="handlestatu" style = "width:90%;">
										<option value="">全部</option>
										<option value="1">处理中</option>
										<option value="2">驳回</option>
										<option value="3">成功</option>
										<option value="4">取消</option>
									</select>
							      </td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;申请商家:</h5></td>
							<td style="width:160px;"><input type="text" class="form-control"  name="sellername" style = "width:90%;"></td>
							</tr>
							
								<tr>
								<td style="text-align: left;" colspan="6"><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
							</tr>
						<tr>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div id="adjustMentBill"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{updateHandlestatu:'${ btnAu['billmanagerment/adjustmentbill/update/init']}'}</script>	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/billmanagerment/adjustmentbillList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

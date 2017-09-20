<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>渠道分析</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
											
							<td style="width:100px;"><h5>&nbsp;&nbsp;筛选日期&nbsp;:</h5></td>
							
							<td>
							<input type="text" name ="startCensusDate" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:43%;float:left">
							<label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label>
							<input type="text" name ="endCensusDate" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td  style="text-align: right; ">
								<div class="submit"><input class="submit radius" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>			
						</tr>
					</tbody>
				</table>
			</form>
			<br>
			<br>
			<div class="page-header">
         	 <h2>总计:</h2>
        	</div>
        	<table class="table table-hover table-striped tablesorter table-data borderBottom" >
					<tbody >
						<tr class="text-center" >
						<td style="font-size: 18px !important;">新增用户</td>
					</tr>
					<tr  class="text-center">
						<td style="font-size: 18px !important;">351</td>
					</tr>
					</tbody>
				</table>
		</div>
	</div>
	
	<div class="panel">
		<div class="panel-body data">
			<div id="transactionFlowDiv" request-init ="${requestInit}">
				
			</div>
		</div>
	</div>
	<%--<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }'}</script> --%>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script>
		inserTitle(' > 客户端数据统计  > <span><a href="#" target="right">渠道分布</a>','transactionFlow',true);
	</script>
</body>
</html>
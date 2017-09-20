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
    <title>活动</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
				<input type="hidden" name="pid" value="${pid}"/> 
			<form class="form-horizontal" role="form" method="post" id="searchActivityForm">
				<input type="hidden" id="status" name="proceedStatus"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>活动名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="title" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>有效期:</h5></td>
							<td style="width:18%;">
									<input type="text" class="form-control" name="useStartTime" value="" onFocus="WdatePicker({maxDate:new Date(),dateFmt:'yyyy-MM-dd'})" onchange="" style="width:42%;float:left" readonly="readonly"/>
							    		<label style="float: left;">&nbsp;--&nbsp;</label>
							 		<input type="text" class="form-control" name="useEndTime" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left" readonly="readonly"/>
								</td>
					  		<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
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
				<c:if test="${null!=btnAu['fashionTickets/add']}">
					<a type="button" class="btn btn-success"  href="fashionTickets/add/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加活动</a>
				</c:if>
			</div>
			<div id="ticketsList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">
{
	end:'${btnAu['fashionTickets/end']}',
	add:'${btnAu['fashionTickets/add']}',
	edit:'${btnAu['fashionTickets/edit']}'
}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/js/vstar/fashionTickets/list.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
   <script type="text/javascript">
   	  var fashionTicketsUrl = "${fashionTicketsUrl}";
   </script>
  </body>
</html>

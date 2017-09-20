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
<title>直播分账记录管理</title>
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
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<label class="col-md-1 control-label" >统计时间：</label>
					<div class="col-md-3" style="width: 25%;">
								<input type="text" id="startTime" name="startTime" value="" placeholder="yyyy-MM-dd" class="form-control form_datetime" style="width:46%;float:left">
								<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
								<input type="text" id="endTime" name="endTime" value="" placeholder="yyyy-MM-dd" class="form-control form_datetime" style="width:46%;float:left">
					</div>
					<div class="submit">
						<input class="submit radius-3" type="button" value="查询全部"  id="querySubmit" /> 
						<input type="reset" class="reset radius-3" value="重置全部"  id="queryReset"/>
					</div>
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	
	<!-- 累计充值总额 begin -->
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info" >
			<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;累计充值金额(从2017-01-07开始统计)</caption>
			<thead>
	 			<tr> 
				 <th style="width:130px;">充值总数</th> 
				 <th style="width:130px;">充值总人数</th> 
				 <th style="width:130px;">线上充值金额</th>  
				 <th style="width:130px;">充值人数</th>  
				 <th style="width:130px;">线下充值金额</th>  
				 <th style="width:130px;">充值人数</th>  
				
	 			</tr> 
	 		</thead> 
	 		<tbody id="rechargeTotal">
			</tbody>
			</table>	
		</div>
	</div>
	<!-- 累计充值总额 end -->
	
	<!-- 每天充值总额 begin -->
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info" >
			<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;每天充值金额</caption>
			<thead>
	 			<tr> 
				 <th style="width:130px;">统计时间区间</th> 
				 <th style="width:130px;">充值总数</th> 
				 <th style="width:130px;">充值总人数</th> 
				 <th style="width:130px;">线上充值金额</th>  
				 <th style="width:130px;">充值人数</th>  
				 <th style="width:130px;">线下充值金额</th>  
				 <th style="width:130px;">充值人数</th>  
				
	 			</tr> 
	 		</thead> 
	 		<tbody id="rechargeOfTime">
			</tbody>
			</table>	
		</div>
	</div>
	<!-- 每天充值总额 end -->
	
	<!-- 根据充值金额分组统计充值信息 begin -->
	<div class="panel panel-default">
		<div class="panel-body data">
			<div id="livePayRecordList"></div>
		</div>
	</div>
	<!-- 根据充值金额分组统计充值信息 end -->
	
	
	<!-- 累计红包总额 begin -->
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info" >
			<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;累计红包总额(从2017-01-07开始统计)</caption>
			<thead>
	 			<tr> 
				 <th style="width:130px;">实际发放总额</th> 
				 <th style="width:130px;">红包个数</th> 
				 <th style="width:130px;">有效可领取总额</th>  
				 <th style="width:130px;">有效实际领取总额</th>  
				 <th style="width:130px;">红包个数</th>  
				 <th style="width:130px;">无人认领总额</th>  
				 <th style="width:130px;">红包个数</th>  
				 <th style="width:130px;">限制认领总额</th>  
				 <th style="width:130px;">红包个数</th> 
	 			</tr> 
	 		</thead> 
	 		<tbody id="redPacketTotal">
			</tbody>
			</table>	
		</div>
	</div>
	<!-- 累计红包总额 end -->
	
	<!-- 每天红包总额 begin -->
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info" >
			<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;每天红包总额</caption>
			<thead>
	 			<tr> 
				 <th style="width:150px;">统计时间区间</th> 
				 <th style="width:130px;">实际发放总额</th> 
				 <th style="width:130px;">红包个数</th> 
				 <th style="width:130px;">有效可领取总额</th>  
				 <th style="width:130px;">有效实际领取总额</th>  
				 <th style="width:130px;">红包个数</th>  
				 <th style="width:130px;">无人认领总额</th>  
				 <th style="width:130px;">红包个数</th>  
				 <th style="width:130px;">限制认领总额</th>  
				 <th style="width:130px;">红包个数</th> 
	 			</tr> 
	 		</thead> 
	 		<tbody id="redPacketOfTime">
			</tbody>
			</table>	
		</div>
	</div>
	<!-- 每天红包总额 end -->
	
	<script type="text/json" id="permissions">{
			export:'${ btnAu['liveLedgerRecord/manage/export']}'
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/liveRechargeAndRedPacketManage.js"></script>
</body>
</html>

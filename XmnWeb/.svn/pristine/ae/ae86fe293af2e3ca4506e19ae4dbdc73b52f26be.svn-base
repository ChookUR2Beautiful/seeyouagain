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
<title>订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm"
				method="post">
				<input type="hidden" name="state" id="state">
				<input type="hidden" name="activityId" value="${activityId }">
				<table style="width:100%;">
					<tbody>
						<tr>
							<input type="hidden" name="bid" value="${param.bid}" />
							<td style="width:5%;"><h5>订单编号:</h5></td>
							<td style="width:25%;" align="left"><input type="text"
								class="form-control" name="id" value="${orderNo}" style="width:80%;"></td>
							<td style="width:5%;"><h5>收货人手机号:</h5></td>
							<td style="width:25%;" align="left"><input type="text"
								class="form-control" name="receivingPhone" style="width:80%;"></td>
							<td style="width:5%;"><h5>收货人:</h5></td>
							<td style="width:25%;" align="left"><input type="text"
								class="form-control" name="receivingName" style="width:80%;"></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>下单时间:</h5></td>
							<td style="width:25%;" align="left"><input type="text"
								name="sDate" placeholder="yyyy-MM-dd hh:mm"
								class="form-control form-datetime" style="width:37.8%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label> <input
								type="text" name="eDate" placeholder="yyyy-MM-dd hh:mm"
								class="form-control form-datetime" style="width:37.8%;float:left"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;活动类型:</h5></td>
							<td style="width:25%;"><select class="form-control"
								name="activityType" style="width:80%;">
									<option value="" >请选择</option>
									<option value="1" ${activityType==1?"selected":""}>一元抢宝</option>
									<option value="2" ${activityType==2?"selected":""}>竞拍</option>
							</select></td>
							<td colspan="6"><div class="submit"
									style="position:relative;left:-90px; ">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /><input class="reset radius-3" type="reset"
										value="重置全部" data-bus='reset' />
								</div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<button type="button" class="btn btn-success"
					onclick="queryBM(this,'');" name="bumen">全&nbsp;部</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'1');" name="bumen">待付款</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'2');" name="bumen">待发货</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'3');" name="bumen">已发货</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'4');" name="bumen">已完成</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'5');" name="bumen">已关闭</button>
				&nbsp;&nbsp;
				<c:if test="${!empty btnAu['fresh/activityOrder/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>&nbsp;excel导出
					</button>
				</c:if>
			</div>
			<div id="activityOrderList"></div>
		</div>
		
		<!-- 按日期导出子订单的模态框 -->
     <div class="modal fade" id="exportModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择要导出的订单</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="exporsubform">
						<div class="form-group">
							<div>
							<label class="col-sm-2 control-label">订单状态:</label>
								<select id="state" name="state" class="form-control" style="width:50%">
									<option value="">全部</option>
									<option value="1">待付款</option>
									<option value="2">待发货</option>
									<option value="3">已发货</option>
									<option value="4">已完成</option>
									<option value="5">已关闭</option>
								</select>
							</div>
							<div>
							<label class="col-sm-2 control-label">下单时间:</label>
							 <input type="text" class="form-control"  id="exportSdate" name="sDate" value=""  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSdate\',{M:-2})||$dp.$DV(\'%y-%M-%d\',{M:-2})}',maxDate:'#F{$dp.$D(\'exportEdate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:24%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  id="exportEdate" name="eDate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:24%;float:left"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<div align="center">
						<button id="exportsubconcel" type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="exportConfirm" type="button" class="btn btn-default">导出</button>
					</div>
				</div>
			</div>
		</div>
     </div>
		
		<script type="text/json" id="permissions">{export:'${btnAu['fresh/activityOrder/export']}',view:'${btnAu['fresh/activityOrder/view']}',edit:'${btnAu['fresh/activityOrder/edit']}'}</script>
		<jsp:include page="../../common.jsp"></jsp:include>
		<script src="<%=path%>/js/fresh/activityOrder/activityOrderList.js"></script>
		<script
			src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="<%=path%>/ux/js/scrollTablel.js"></script>
		<!-- 引入时间插件 -->
		<script src="<%=path%>/resources/datapicker/WdatePicker.js"
			type="text/javascript"></script>
</body>
</html>

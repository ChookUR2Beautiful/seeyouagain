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
    <title>物料订单列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/web/css/jquery.validate.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
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
	.table-striped > tbody > tr:nth-child(odd) > td > input{
	  background-color: #f9f9f9;
	}
	.table-striped > tbody > tr:nth-child(even) > td > input{
	  background-color: ffffff;
	}
	.table-hover > tbody > tr:hover > td > input{
	  background-color: #ebf2f9;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
    <div class="tab-content">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" method="get" id="searchMaterialForm">
					<!-- 
					<input type="hidden" name="types" id="types" value="1">
					 -->
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>订单编号:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" id="order_sn" name="order_sn" placeholder="订单编号" style = "width:90%;"/></td>
								<td style="width:5%;"><h5>订单状态:</h5></td>
								<td style="width:18%;">
									<select type="text" class="form-control" id="status" name="status" style = "width:90%;">
										<option value="">--请选择--</option>
    									<option value="0">待支付</option>
										<option value="1">待发货</option>
										<option value="2">取消支付/支付失败</option>
										<option value="3">已发货(待收货)</option>
										<option value="4">已收货(订单完成)</option>
									</select>
								</td>
								<td style="width:5%;"><h5>快递单号:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" id="courier_number" placeholder="快递单号" name="courier_number" style = "width:90%;"/></td>
								<td style="width:5%;"><h5>联系手机:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机号码" style = "width:90%;"/></td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;width:100%;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
<%-- 					<c:if test="${btnAu['fresh/subOrder/export']}"> --%>
						<button type="button" id="exportSubOrder" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;订单导出</button>
<%-- 					</c:if> --%>
				</div>
				<div id="materialOrderPayList"></div>
			</div>
		</div>
	</div>
	<!-- 发货选项模态框（Modal） -->
	<div class="modal fade" id="subfhModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeFhModal" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">发货选项</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="fhform">
						<div class="form-group">
							<label for="courierType" class="col-sm-2 control-label">快递公司：</label>
							<div class="col-sm-10">
								<select id="courierType" class="form-control" style="width:240px;">
									<option value="">请选择</option>
									<c:forEach var="e" items="${express}">
										<option value="${e.expressValue }">${e.expressName }</option>
									</c:forEach>
						  		</select>
							</div>
						</div>
						<div class="form-group">
							<input type="hidden" id="id" name="id"> 
							<label for="courierNumber" class="col-sm-2 control-label">快递单号：</label>
							<div class="col-sm-10">
								<input id="courierNumberId" name="courierNumber" type="text" style="width:240px;" class="form-control" placeholder="">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="fhconcel" type="button" class="btn btn-default" data-dismiss="modal">取消发货
					</button>
					<button id="fhconfirm" type="button" class="btn btn-default">确认发货</button>
				</div>
			</div>
		</div>
     </div>
     <!-- 按日期导出子订单的模态框 -->
     <div class="modal fade" id="exportSubOrderModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请选择下单时间</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="exporsubform">
						<div class="form-group">
							<label for="courierNumber" class="col-sm-2 control-label"><h5>下单时间:</h5></label>
							<div class="col-sm-10">
							  <input type="text" class="form-control"  id="exportSubSdate" name="subexsdate" value=""  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSubEdate\',{M:-2})||$dp.$DV(\'%y-%M-%d\',{M:-2})}',maxDate:'#F{$dp.$D(\'exportSubEdate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" onblur="valiSubDate()" style="width:40%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  id="exportSubEdate" name="subexedate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSubSdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" onblur="valiSubDate()" style="width:40%;float:left"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="exportsubconcel" type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="exportconfirm" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
     <!-- 操作结果提示层 -->
	<div class="modal fade" id="sm_reslut_window" data-position="100px">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{shipment:'${btnAu['billmanagerment/material/order/shipment']}',
		confirmReceive:'${btnAu['billmanagerment/material/order/getship']}',init:'${btnAu['billmanagerment/material/order/init/list']}'}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/billmanagerment/materialOrderList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>

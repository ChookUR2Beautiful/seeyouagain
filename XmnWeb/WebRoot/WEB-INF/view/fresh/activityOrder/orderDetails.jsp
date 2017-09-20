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
<title>物料分类</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<style>
	.order-table{
		text-align:center;
		font-size: 16px;
		color: #2e2e2e;
	}
	.order-table th{
		text-align:center;
		height:50px;
	}
	.order-table td{
		height:40px;
	}
	.panel label{
	margin-right: 20px;
    font-size: 15px;
	}
</style>
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
						<table>
							<tr>
								<td>发货方式：</td>
								<td>快递</td>
							</tr>
							<tr>
								<td>物流公司：</td>
								<td>${aOrder.logisticsType}<c:if test="${aOrder.logisticsType== null}">-</c:if></td>
							</tr>
							<tr>
								<td>运单号码：</td>
								<td>${aOrder.logisticsNum}<c:if test="${aOrder.logisticsType== null}">-</c:if></td>
							</tr>
						</table>
						
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body data">
			<input type="hidden" id="id" name = "id" value="${aOrder.id}">
				<div>
					<div class="form-group">
					<label >订单状态：</label>
					<label ><c:if test="${aOrder.state == 1}">待付款</c:if>
					<c:if test="${aOrder.state == 2}">待发货</c:if>
					<c:if test="${aOrder.state == 3}">已发货</c:if>
					<c:if test="${aOrder.state == 4}">已完成</c:if>
					<c:if test="${aOrder.state == 5}">已关闭</c:if>
					</label>
				</div>
				<div class="form-group">
					<label >买家信息：</label>
					<label >${aOrder.userName}</label>
					<label >${aOrder.userPhone}</label>
				</div>
				<div class="form-group">
					<label >订单编号：</label>
					<label >${aOrder.id}</label>
					<label >收货信息：</label>
					<label >${aOrder.receivingName}</label>
					<label >${aOrder.receivingPhone}</label>
					<label >${aOrder.receivingCity}${aOrder.receivingAddress}</label>
				</div>
				</div>
				<table border="1" width="80%" class="order-table">
					<thead>
						<tr>
							<th>商品名称</th>
							<th>商品价格</th>
							<th>商品数量</th>
							<th>实收款</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${aOrder.productName}</td>
							<td>${aOrder.productPrice}</td>
							<td>${aOrder.productNum}</td>
							<td>${aOrder.amountReceived}</td>
						</tr>
					</tbody>
				</table>
				<div class="form-group">
					<label >关联活动：</label>
					<label >${aOrder.activityName}<c:if test="${aOrder.activityName== null}">-</c:if></label>
					<label >活动类型：</label>
					<label ><c:if test="${aOrder.activityType == 1}">一元夺宝</c:if>
					<c:if test="${aOrder.activityType == 2}">竞拍活动</c:if>
					</label>
				</div>
				<div class="form-group">
					<label> 快递公司：</label>
					<label >${aOrder.logisticsType}<c:if test="${aOrder.logisticsType== null}">-</c:if></label>
					<label >快递单号：</label>
					<label >${aOrder.logisticsNum}<c:if test="${aOrder.logisticsType== null}">-</c:if></label>
				</div>
				<div class="form-group">
					<label >下单日期：</label>
					<label >${aOrder.createTimeStr}</label>
					<label >支付日期：</label>
					<label >${aOrder.payTimeStr}</label>
					<label >支付方式：</label>
					<label ><c:if test="${aOrder.payType=='01'}">鸟币支付</c:if><c:if test="${aOrder.payType== null}">-</c:if></label>
				</div>
			</div>
		</div>
		<div class="form-group" align="center">
			<c:if test="${aOrder.state == 1 or aOrder.state == 2 }">
				<button class="btn btn-success" onclick="editAdressModal();">
					<i class="icon icon-edit"></i> 编辑收货地址
				</button>
			</c:if>
			<c:if test="${aOrder.state == 2}">
				<button class="btn btn-success" onclick="receiveAdressModal();" style="width:130px;">
					 <i class="icon icon-times"></i>发&nbsp;&nbsp;货
				</button>
			</c:if>
			<c:if test="${aOrder.state == 1 or (aOrder.activityType == 1 and aOrder.state !=5)}">
				<button class="btn btn-success" onclick="shutDownOrder(5);">
					 <i class="icon icon-times"></i>关闭订单
				</button>
			</c:if>
		</div>
		
		<!-- 填写物流信息 -->
		<div class="modal fade" id="sendAdressModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title">填写物流信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="sendAdress">
						<input type="hidden" id="id" name = "id">
						<div class="form-group">
							<label class="col-md-4 control-label">输入物流公司：</label>
							<div class="col-md-7">
							<select type="text" class="form-control" id="logisticsType" name="logisticsType">
										<option value="">--请选择--</option>
									<c:forEach items="${express}" var="express">
										<option value="${express.expressName}">${express.expressName}</option>
									</c:forEach>
							</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">输入物流单号：</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="logisticsNum"
									id="logisticsNum" >
							</div>
						</div>
						<input type="hidden" id="state" name="state" value="3">
						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 0;">
								<button type="submit" class="btn btn-default">
									<span class="icon-remove"></span> 取 消
								</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-success"
									data-dismiss="modal" onclick="sendAdress();">
									<span class="icon-ok"></span> 发货
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
     </div>
	
		<!-- 编辑收货地址 -->
		<div class="modal fade" id="editAdressModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title">编辑收货地址</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="editAdress">
					<input type="hidden" id="id" name = "id">
						<div class="form-group">
							<label class="col-md-4 control-label">收货人：<span style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="receivingName"
									id="receivingName" value="${aOrder.receivingName }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">收货人手机：<span style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="receivingPhone"
									id="receivingPhone" value="${aOrder.receivingPhone }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">收货城市：<span style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="receivingCity"
									id="receivingCity" value="${aOrder.receivingCity }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">收货地址：<span style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="receivingAddress"
									id="receivingAddress" value="${aOrder.receivingAddress }">
							</div>
						</div>

						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 0;">
								<button type="submit" class="btn btn-default">
									<span class="icon-remove"></span> 取 消
								</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-success"
									data-dismiss="modal" onclick="editAdress();">
									<span class="icon-ok"></span> 确认
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
     </div>
	
	<script type="text/json" id="permissions">
		{
	       update:'${ btnAu['materialCategory/manage/update']}',
	       delete:'${ btnAu['materialCategory/manage/delete']}'
		}
	</script>
	<script type="text/javascript">
	contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/fresh/activityOrder/orderDetail.js"></script>
</body>
</html>

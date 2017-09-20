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
<title>商家账号</title>
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
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<input type="hidden" id="accountType" value="${param.accountType}" />
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" name="sellerid" value="${param.sellerid}" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<!-- <td class="col-md-1"><h5>账户ID:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"  name="aid" style = "width:90%;"></td> -->
							<td class="col-md-1"><h5>帐号:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="account"></td>
							<td class="col-md-1"><h5>帐号昵称:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="nname"></td>
							<td class="col-md-1"><h5>真实姓名:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="fullname"></td>
						</tr>
						<tr>
							<%-- <td class="col-md-1"><h5>账户类型:</h5></td>
							<td class="col-md-3">
								<select class="form-control" name="type" style = "width:90%;">
									<option value="">请选择</option>
									<option value="1" ${sellerAccount.type==1?"selected":""}>商家管理账号</option>
									<option value="2" ${sellerAccount.type==2?"selected":""}>收银员账号</option>
									<option value="3" ${sellerAccount.type==3?"selected":""}>普通店员账号</option>
								</select>
							</td> --%>
							<td class="col-md-1"><h5>添加时间:</h5></td>
							<!-- <td style="width:120px;"><input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="sdateStart"></td>
							<td class="col-md-1"><h5>至:</h5></td>
							<td style="width:120px;"><input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="sdateEnd"></td> -->
							<td class="col-md-3"><div class="input-group">
									<input type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateStart">
									<span class="input-group-addon fix-border">--</span></label> <input
										type="text" placeholder="yyyy-MM-dd hh:mm"
										class="form-control form_datetime" name="sdateEnd">
								</div></td>

							<td class="col-md-1"><h5>联系人手机:</h5></td>
							<td class="col-md-3"><input type="text" class="form-control"
								name="phone"></td>
							<td colspan="4" class="col-md-4">
								<div class="submit text-left">
									<input class="submit radius-3"
										style="width:49.5%;margin-right:0;" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										style="width:49.5%;margin-right:0;" type="reset" value="重置全部"
										data-bus='reset' />
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
			<c:if test="${null!=btnAu['businessman/sellerAccount/add'] && btnAu['businessman/sellerAccount/add']}">
				<button type="button" class="btn btn-success"  data-type="ajax"   data-url="businessman/sellerAccount/add/init.jhtml?sellerid=${param.sellerid}&accountType=${param.accountType}"  data-toggle="modal" >
					<span class="icon-plus"></span>&nbsp;添加
				</button>
			</c:if>
			
			<c:if test="${null!=btnAu['businessman/sellerAccount/delete'] && btnAu['businessman/sellerAccount/delete']}">
				<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
			</c:if>
			<div id="sellerAccountList"></div>
		</div>
	</div>
	
	<!-- 操作询问层 -->
	<div class="modal fade" id="sm_confirm_window" style="z-index: 9999;" data-position="100px">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
		        <h4 class="modal-title">操作提示</h4>
		      </div>
		      <div class="modal-body">
		     	 <div class="alert with-icon  alert-warning">
					<i class="icon-warning-sign"></i> 
					<div class="content">
						<p>该手机号已有对应的寻蜜鸟会员账号，操作人员请联系该服务员确认该寻蜜鸟会员账号是否归该服务员所有并进行绑定！</p>
					</div>
				</div>
				<div class="">
					<h4>对应会员账号信息</h4>
					<div class="content">
						<table class="table table-condensed">
						<tr>
							<td width="12%"><h5>会员呢称:</h5></h5></td>
							<td width="24%"><h5 id="usr_nname"></h5></td>
							<td width="12%"><h5>钱包余额:</h5></td>
							<td width="20%"><h5 id="wallet_amount"></h5></td>
							<td width="12%"><h5>分账余额:</h5></td>
							<td width="20%"><h5 id="wallet_balance"></h5></td>
						</tr>
						<tr>
							<td ><h5>注册时间:</h5></td>
							<td ><h5 id="usr_regtime"></h5></td>
							<td><h5>佣金余额:</h5></td>
							<td><h5 id="wallet_commision"></h5></td>
							<td><h5>赠送余额:</h5></td>
							<td><h5 id="wallet_zbalance"></h5></td>
						</tr>
						<tr>
							<td ><h5>会员状态:</h5></t>
							<td><h5 id="usr_status"></h5></td>
							<td><h5>积分余额:</h5></td>
							<td><h5 id="wallet_integral"></h5></td>
						</tr>
						</table>
					</div>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default unbinding" >不 绑 定</button>&nbsp;&nbsp;
		        <button type="button" class="btn btn-primary binding" >绑    定</button>
		      </div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{sc:'${btnAu['businessman/sellerAccount/delete']}',xg:'${ btnAu['businessman/sellerAccount/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/businessman/sellerAccount.js"></script>
</body>
</html>

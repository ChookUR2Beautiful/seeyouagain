<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商家服务员推广</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}

.submit {
	text-align: left;
}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div class="tab-content">
		<ul id="myTab" class="nav nav-tabs">
			<c:if test="${ btnAu['businessman/sellerSubsidy/config']}">
				<li
					<c:if test="${ btnAu['businessman/sellerSubsidy/config']}">class="active"</c:if>>
					<a href="#tab1" data-toggle="tab">商家服务员推广配置</a>
				</li>
			</c:if>

			<c:if test="${ btnAu['businessman/sellerSubsidy/manager']}">
				<li
					<c:if test="${ empty btnAu['businessman/sellerSubsidy/config'] && btnAu['businessman/sellerSubsidy/manager']}"> class="active" </c:if>>
					<a href="#tab2" data-toggle="tab">商家服务员推广管理</a>
				</li>
			</c:if>
		</ul>
		<c:if test="${ btnAu['businessman/sellerSubsidy/config']}">
			<div id="tab1"
				class="tab-pane <c:if test="${ btnAu['businessman/sellerSubsidy/config']}">in active</c:if>">
				<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="WaiterConfigForm">
							<input type="hidden" name="ismultiple" value="1" />
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
										<td style="width:25% !important;"><input type="text"
											class="form-control" name="sellerId" value=""
											style="width:80%"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;奖励金额:</h5></td>
										<td style="width:25% !important;"><input type="text"
											class="form-control" name="money" value="" style="width:80%">
										</td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
										<td style="width:25% !important;"><input type="text"
											class="form-control" value="" name="sellername"
											style="width:80%"></td>

										<td style="width:5%;"><h5>&nbsp;&nbsp;状态:</h5></td>
										<td style="width:25% !important;"><select
											class="form-control" tabindex="-1" name="status"
											style="width:80%;">
												<option value="">--请选择--</option>
												<option value="0">开启</option>
												<option value="1">关闭</option>
										</select></td>
										<td colspan="3" style="width:30%;">
											<div class="submit">
												&nbsp;<input class="submit radius-3" type="button"
													value="查询全部" data-bus='query' /> <input
													class="reset radius-3" type="reset" value="重置全部"
													data-bus='reset' />
											</div>
										</td>
									</tr>

									<tr style="height: 20px"></tr>

									<tr>

									</tr>
								</tbody>
							</table>
							<c:if test="${!empty param.page}">
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
				</div>
				<!-- 服务员推广配置数据列表 -->
				<div class="panel panel-default">
					<div class="panel-body data">
						<div class="btn-group" style="margin-bottom: 5px;">
							<c:if
								test="${!empty btnAu['businessman/sellerSubsidy/waiterConfig/add']}">
								<a id="addWaiterConfig" class="btn btn-success" href="businessman/sellerSubsidy/waiterConfig/add/init.jhtml?isType=add" ><span
									class="icon-plus"></span>添加</a>
							</c:if>
						</div>
						<div id="waiterConfigInfoDiv"></div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${ btnAu['businessman/sellerSubsidy/manager']}">
			<div id="tab2"
				class="tab-pane <c:if test="${ empty btnAu['businessman/sellerSubsidy/config'] && btnAu['businessman/sellerSubsidy/manager']}">in active</c:if>">
				<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="SellerSpreadForm">
							<input type="hidden" name="ismultiple" value="1" />
							<input type="hidden" id="wxapiUrl" name="wxapiUrl" value="${wxapiUrl}"/>
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
										<td style="width:25% !important;"><input type="text"
											class="form-control" name="sellerid"
											value="${param.sellerid}" style="width:80%"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;推广地址：</h5></td>
										<td style="width:25% ;text-align: left;">
										    <input type="text" class="form-control" name="url" style="width:80%">
									 	</td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;服务员帐号:</h5></td>
										<td style="width:25% !important;"><input type="text"
											class="form-control" name="account" value=""
											placeholder="登陆手机号" style="width:76.5%"></td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
										<td style="width:25% !important;"><input type="text"
											class="form-control" value="${param.sellername}"
											name="sellername" style="width:80%"></td>

										<td style="width:5%;"><h5>&nbsp;&nbsp;推广状态：</h5></td>
										<td style="width:25% !important;"><select
											class="form-control" tabindex="-1" name="tgStatus"
											style="width:80%;">
												<option value="">--请选择--</option>
												<option value="0">正常</option>
												<option value="1">停止</option>
										</select></td>
										<td colspan="2" style="width:30%;">
											<div class="submit">
												&nbsp;<input class="submit radius-3" type="button"
													value="查询全部" data-bus='query' /> <input
													class="reset radius-3" type="reset" value="重置全部"
													data-bus='reset' />
											</div>
										</td>
									</tr>

									<tr style="height: 20px"></tr>

									<tr>

									</tr>
								</tbody>
							</table>
							<c:if test="${!empty param.page}">
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>

				</div>

				<div class="panel panel-default">
					<div class="panel-body data">
						<div class="btn-group" style="margin-bottom: 5px;">
							<c:if
								test="${!empty btnAu['businessman/sellerSubsidy/spread/add']}">
								<a id="addSread" class="btn btn-success"
									href="businessman/sellerSubsidy/spread/add/init.jhtml?isType=add"><span
									class="icon-plus"></span>添加</a>
							</c:if>
							<c:if
								test="${!empty btnAu['businessman/sellerSubsidy/spread/export']}">
								<button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
							</c:if>
							<c:if
								test="${!empty btnAu['businessman/sellerSubsidy/spread/exportBill']}">
								<%--<button type="button" id="exportBill" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出订单</button> --%>
								<button type="button" class="btn btn-default" data-target="#exportModal"  data-toggle="modal" >
									<span class="icon-download-alt"></span>&nbsp;excel导出订单</button>
							</c:if>
						</div>
						<div id="sellerSpreadInfoDiv"></div>
					</div>
				</div>
			</div>
		</c:if>
	</div>
	<!-- tab-content -->
	<!-- 编辑层--用于二维码显示 -->
	<div class="modal fade" id="sm_edit_window" data-position="200px">
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
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="exportModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               	服务员推广订单导出
	            </h4>
	         </div>
	         <div class="modal-body">
        		 <form class="borderBottom table table-form" action="businessman/sellerSubsidy/spread/exportBill.jhtml"  role="form"  id="dcForm">
					<table style="width:100%;">
						<tbody>
							<tr>	
								<td style="width:50px;"><h5>&nbsp;&nbsp;&nbsp;导出月份</h5></td>
								<td style="width:230px;">
									<input type="text" name ="date" placeholder="yyyy-mm" class="form-control form-datetime2" readonly>
								</td>
							</tr>
						</tbody>
						  <tfoot>
				      		<tr>
					          	<td colspan="4" class="text-center">
						            	<button class="btn btn-default" type="submit" ><i class="icon-download-alt" data-loading="稍候..."></i>&nbsp;导 出</button>
										<button type="reset" class="btn btn-warning" data-dismiss="modal"><i class="icon-remove"></i>&nbsp;取 消 </button>
								</td>
					        </tr>
				      </tfoot>
					</table>
				</form>
	         </div>
	      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

	<script type="text/json" id="permissions">{update:'${ btnAu['businessman/sellerSubsidy/waiterConfig/update']}',start:'${ btnAu['businessman/sellerSubsidy/spread/start']}'}</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>//resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script	src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/businessman/sellerSubsidyList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var vali = {
				rules : {
					sellerid : {
						digits : true,
						range : [ 1, 2147483647 ]
					}
				},
				messages : {
					sellerid : {
						digits : "商家编号只能是数字",
						range : "最大值为2147483647"
					}
				}
			};
			validate("SellerSpreadForm", vali);
		});
	</script>
	<!-- 商家服务员推广配置js -->
	<c:if test="${ btnAu['businessman/sellerSubsidy/config']}">
		<script src="<%=path%>/js/businessman/sellerSubsidy/waiterConfig.js"></script>
	</c:if>
	<!-- 商家服务员推广js -->
	<c:if test="${ btnAu['businessman/sellerSubsidy/manager']}">
		<script src="<%=path%>/js/businessman/sellerSubsidy/sellerSpread.js"></script>
	</c:if>
</body>
</html>

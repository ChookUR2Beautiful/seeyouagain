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
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="packageOrderForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:60px;"><h5>&nbsp;&nbsp;订单号:</h5></td>
							<td style="width:230px;"><input type="text" class="form-control"  name="orderNo" style = "width:75%;"></td>
							<td style="width:60px;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:230px;"><input type="text" class="form-control"  name="uname" style = "width:75%;"></td>
							<td style="width:60px"><h5>&nbsp;&nbsp;用户手机:</h5></td>
							<td style="width:230px;"><input type="text" class="form-control"  name="phone" style = "width:75%;"></td>
						</tr>
						<tr>
						<td style="width:60px;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
						<td style="width:230px;"><input type="text" class="form-control"  name="sellername" style = "width:75%;"></td>
						<td style="width:60px;"><h5>&nbsp;&nbsp;支付方式:</h5></td>
						<td style="width:230px;"><select class="form-control" name="paymentType" style = "width:75%;">
										<option value="">请选择</option>
										<option value="1000000">收益支付</option>
										<option value="1000001">支付宝</option>
										<option value="1000002">U付支付</option>
										<option value="1000003">微信支付</option>
										<option value="1000004">网银支付</option>
										<option value="1000005">汇付天下</option>
										<option value="1000006">融宝支付</option>
										<option value="1000007">盛付通支付</option>
										<option value="1000008">快钱支付</option>
										<option value="1000009">通联支付</option>
										<option value="1000010">连连支付</option>
										<option value="1000011">优惠券支付</option>
										<option value="1000013">微信公众号支付</option>
										<option value="1000014">支付宝服务窗支付</option>
										<option value="1000020">鸟币支付</option>
										<option value="1000022">支付宝APP支付（鸟人科技）</option>
										<option value="1000023">支付宝WAP支付（鸟人科技）</option>
										<option value="1000024">微信APP支付（鸟人科技）</option>
										<option value="1000025">微信公众号支付（鸟人科技）</option>
									</select></td>		
						<td style="width:60px;"><h5>&nbsp;&nbsp;套餐名称:</h5></td>
						<td style="width:230px;"><input type="text" class="form-control"  name="title" style = "width:75%;"></td>		
						</tr>				
						<tr>
							<td style="width:60px;"><h5>&nbsp;&nbsp;购买来源:</h5></td>
							<td style="width:230px;"><select class="form-control" name="orderSource" style = "width:75%;">
										<option value="">请选择</option>
										<option value="1">ios</option>
										<option value="2">android</option>
										<option value="3">微信</option>
									</select></td>		
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
				<c:if test="${null!=btnAu['packageOrder/manage/export'] && btnAu['packageOrder/manage/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="packageOrderList"></div>
		</div>
	</div>
	
	<!-- 退款备注 -->
	<div class="modal fade" id="refundDescriptionModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title">退款备注</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="refundDescription">
					<input type="hidden" name="orderNo" id="orderNo">
						<div class="form-group" style="margin-left:-100px">
							<label class="col-md-4 control-label">备注：</label>
							<div class="col-md-7">
							<textarea name="description" id="description" cols="45" rows="5" ></textarea> 
							</div>
						</div>

						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 30;">
								<button type="submit" class="btn btn-default">
									<span class="icon-remove"></span> 取 消
								</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-success"
									data-dismiss="modal" onclick="refund();">
									<span class="icon-ok"></span> 确认
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
     </div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<script type="text/json" id="permissions">{view:'${ btnAu['packageOrder/manage/view']}',refund:'${ btnAu['packageOrder/manage/refund']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/js/live_anchor/packageOrder/packageOrderList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

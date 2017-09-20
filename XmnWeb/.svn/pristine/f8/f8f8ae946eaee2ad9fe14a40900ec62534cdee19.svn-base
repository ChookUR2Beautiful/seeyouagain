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
<title>直播频道粉丝券订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" name="eUid" value="${eUid }">
				<div class="form-group">
					<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;订单编号：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="orderSn" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
										<td style="width:5%;"><h5>用户手机：</h5></td>
										<td style="width:30%;"><input type="text" class="form-control"  name="phoneid" style = "width:66%;"></td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control form_datetime"  name="sellername" style = "width:85%;"></td>
										<td style="width:5%;"><h5>支付方式：</h5></td>							
										<td style="width:30%;"> 
											<select class="form-control" name="paymentType" style = "width:85%;">
												<option value="">全部</option>
												<option value="1000001">支付宝SDK支付</option>
												<option value="1000003">微信SDK支付</option>
												<option value="1000013">公众号支付 </option>
												<option value="1000000">钱包支付</option>
												<option value="1000004">AppStore支付</option>
												<option value="1000015">鸟粉卡支付</option>
												<option value="1000022">支付宝APP支付（鸟人科技）</option>
												<option value="1000024">微信APP支付（鸟人科技）</option>
											</select> 
										</td>	
										<td style="width:5%;"><h5>&nbsp;&nbsp;券名称：</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="cname" style = "width:66%;"></td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>订单状态：</h5></td>							
										<td style="width:25%;"> 
											<select class="form-control" name="status" style = "width:85%;">
												<option value="">全部</option>
												<option value="0">待支付</option>
												<option value="1">已支付</option>
												<option value="2">支付失败</option>
												<option value="3">取消支付</option>
												<option value="4">退款成功</option>
											</select> 
										</td>	
										<td style="width:5%;"><h5>购买来源</h5></td>
										<td style="width:25%;">
											<select class="form-control" name="buySource" style = "width:85%;">
												<option value="">全部</option>
												<option value="1">通过主播购买</option>
												<option value="2">通过商户购买</option>
												<option value="3">通过预告购买 </option>
											</select> 
										</td>
										<td colspan="2" style="width:30%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['liveCouponOrder/manage/export']}">
						<button type="button" id="export" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="livePayOrderList"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			export:'${ btnAu['liveCouponOrder/manage/export']}',
			update:'${ btnAu['liveCouponOrder/manage/update']}'
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/liveCouponOrderManage.js"></script>
</body>
</html>

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
    <title>所有订单</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchBillForm">
				<input type="hidden" name="aid" value="${aid }">
				<table style="width:100%;">
					<tbody>
						<tr>
							
							<td style="width:40px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单号:</h5></td>
							<td style="width:160px;"><input type="text" class="form-control"  name="bid" style = "width:90%;"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;用户手机:</h5></td>
							<td style="width:160px;"><input type="text" class="form-control"  name="phoneid" style = "width:90%;"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;参与商家:</h5></td>
							<td style="width:160px;"><input type="text" class="form-control"  name="genusname" style = "width:90%;"></td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;支付方式:</h5></td>
							        <td style="width:160px;"  >
								     <select class="form-control" name="paytype" style = "width:90%;">
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
										<option value="1000022">支付宝APP支付（鸟人科技）</option>
										<option value="1000023">支付宝WAP支付（鸟人科技）</option>
										<option value="1000024">微信APP支付（鸟人科技）</option>
										<option value="1000025">微信公众号支付（鸟人科技）</option>
									</select>
						     </td>
						</tr>
						<tr>
							<td style="width:40px;"><h5>&nbsp;&nbsp;到账状态:</h5></td>
							<td style="width:160px;">
								 	<select class="form-control" name="isaccount" style = "width:90%;">
										<option value="">全部</option>
										<option value="1">已到帐</option>
										<option value="0">未到账</option>
									</select>
							</td>						
							<td style="width:50px;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付时间:</h5></td>							
							<td style="width:160px;">
									<input type="text" name ="zdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input type="text" name ="zdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42.6%;float:left">
							</td>				
						</tr>
						<tr>
							<td colspan="8" style="text-align: right; "><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>						
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			
			</div>
			<div id="allBillList" request-init ="${requestInit}"></div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/marketingmanagement/commissionActivityPrize.js"></script>
	
  </body>
</html>

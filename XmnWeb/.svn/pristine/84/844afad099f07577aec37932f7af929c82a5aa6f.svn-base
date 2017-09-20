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
    <title>疑似刷单</title>
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
				<input type="hidden" name="bid" value="${bid }"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>用户昵称:</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="nname" style = "width:90%;"></td>
							<td style="width:5%;"><h5>用户手机:</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="phoneid" style = "width:90%;"></td>
							<td style="width:5%;"><h5>订单号:</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="stringBid" style = "width:91%;"></td>
							<td style="width:5%;"><h5>订单状态:</h5></td>
							<td style="width:19%;">
								<select class="form-control" name="status" style = "width:94.5%;">
									<option value="">请选择</option>
									<option value="0" ${bill.status==0?"selected":""}>非退款其它状态</option>
									<option value="1" ${bill.status==1?"selected":""}>待返现</option>
									<option value="2" ${bill.status==2?"selected":""}>已返现</option>
									<option value="3" ${bill.status==3?"selected":""}>已消费</option>
									<option value="4" ${bill.status==4?"selected":""}>退款中</option>
									<option value="5" ${bill.status==5?"selected":""}>已退款</option>
									<option value="6" ${bill.status==6?"selected":""}>待返现【取消退款】</option>
									<option value="7" ${bill.status==7?"selected":""}>退款中【已申诉】</option>
									<option value="8" ${bill.status==8?"selected":""}>待返现【驳回申请退款】</option>
									<option value="9" ${bill.status==9?"selected":""}>返利中 </option>
									<option value="10" ${bill.status==10?"selected":""}>商家申诉失败 </option>
									<option value="11" ${bill.status==11?"selected":""}>平台退款处理中 </option>
									<option value="12" ${bill.status==12?"selected":""}>平台退款失败 </option>
									<option value="13" ${bill.status==13?"selected":""}>商家同意退款 </option>
									<option value="14" ${bill.status==14?"selected":""}>平台同意退款 </option>
									<option value="15" ${bill.status==15?"selected":""}>刷单退款 </option>
								</select>
							</td>
							
						</tr>
						<tr>
							<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="genussellerid" style = "width:90%;" placeholder="所属商家编号"></td>
							<td style="width:5%;"><h5>所属商家:</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="genusname" style = "width:90%;"></td>
												
							<td style="width:5%;"><h5>支付时间:</h5></td>							
							<td style="width:19%;">
									<input type="text" name ="zdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input type="text" name ="zdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42.6%;float:left">
							</td>
								<td style="width:5%;"><h5>支付方式:</h5></td>
							        <td style="width:23%;"  >
								     <select class="form-control" name="paytype" style = "width:94.5%;">
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
										<option value="1000015">鸟币支付</option>
										<option value="1000022">支付宝APP支付（鸟人科技）</option>
										<option value="1000023">支付宝WAP支付（鸟人科技）</option>
										<option value="1000024">微信APP支付（鸟人科技）</option>
										<option value="1000025">微信公众号支付（鸟人科技）</option>
									</select>
						     </td>			
						</tr>
						<tr>
						<td style="width:5%;"><h5>商家编号:</h5></td>
						<td style="width:19%;"><input type="text" class="form-control"  name="sellerid" style = "width:90%;" placeholder="消费商家编号"></td>	
						<td style="width:5%;"><h5>消费商家:</h5></td>
						<td style="width:19%;"><input type="text" class="form-control"  name="sellername" style = "width:90%;"></td>	
						<td style="width:5%;"><h5>用户编号:</h5></td>
						  <td style="width:19%;"><input type="text" class="form-control"  name="uid" style = "width:91%;"></td>
						  <td style="width:5%;"><h5>订单金额:</h5></td>
						  <td style="width:23%;">
						  <input type="text" class="form-control"  name="moneyStart"  style="width:44%;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
						  <input type="text" class="form-control"  name="moneyEnd"  style="width:45%;float:left">
						</td>	
						</tr>
						<tr>
						  <td style="width:5%;"><h5>支付流水号:</h5></td>
						  <td style="width:19%;" align="left"><input type="text" class="form-control"  name="payid" style = "width:90%;"></td>        
						  <td style="width:5%;"><h5>区域查询:</h5></td>
						  <td style="width:19%;"><div class="input-group" id="ld"  style="width:91%">
						  </div></td>
						 	<td style="width:5%;"><h5>是否首单:</h5></td>
							        <td style="width:19%;">
								     <select class="form-control" name="isFirst" style = "width:91%;">
										<option value="">请选择</option>
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
						</td>	
						<td style="width:5%;"><h5>是否爆品:</h5></td>
							        <td style="width:23%;">
								     <select class="form-control" name="isBargain" style = "width:95%;">
										<option value="">请选择</option>
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
						</td>
						<tr>
						   <td style="width:5%;" nowrap="nowrap"><h5>第三方支付账号:</h5></td>
						<td style="width:19%;"><input type="text" class="form-control"  name="thirdUid" style = "width:90%;" placeholder="第三方支付编号"></td>	
							<td style="width:5%;"><h5>刷单判定:</h5></td>
							        <td style="width:19%;">
								     <select class="form-control" name="normal" style = "width:90%;">
										<option value="">请选择</option>
										<option value="0">未判定</option>
										<option value="1">正常订单</option>
										<option value="2">异常订单</option>
									</select>
						</td>
						<td colspan="4" style="text-align: right; "><div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>						
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['billmanagerment/clickfarmingbill/export'] && btnAu['billmanagerment/clickfarmingbill/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="clickfarmingBillList"></div>
			<div id="scrollTablel"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{detail:'${ btnAu['billmanagerment/allbill/view/init']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/billmanagerment/clickfarmingbillList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

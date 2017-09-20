<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>退款已处理订单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="refundBillPendingHistoryListForm">
				  <table>
				 		<tbody>
				 		    <tr>
							<td style="width:5%;"><h5>用户昵称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="nname" style = "width:90%;"></td>
							<td style="width:5%;"><h5>用户手机:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="phoneid" style = "width:90%;"></td>
							<td style="width:5%;"><h5>订单号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="bid" style = "width:90%;"></td>
							<td style="width:5%;"><h5>支付时间:</h5></td>							
							<td style="width:18%;">
									<input type="text" name ="zdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:41%;float:left">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input type="text" name ="zdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
						  </tr>
					 		<tr>
							<td style="width:5%;"><h5>支付流水号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="payid" style = "width:90%;"></td>
						    <td style="width:5%;"><h5>消费商家:</h5></td>
						    <td style="width:18%;"><input type="text" class="form-control"  name="sellername" style = "width:90%;"></td>				
                            <td style="width:5%;"><h5>派奖状态:</h5></td>
							<td style="width:18%;">
								     <select class="form-control" name="isactivity" style = "width:90%;">
										<option value="">请选择</option>
										<option value="0">未派</option>
										<option value="1">已派</option>
										<option value="2">不用派</option>
									</select>
						    <td style="width:5%;"><h5>支付方式:</h5></td>
							<td style="width:18%;"  >
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
					 		<td style="width:5%;"><h5>退款处理时间:</h5></td>							
							<td style="width:18%;">
									<input type="text" name ="quitDateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input type="text" name ="quitDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							<td colspan="4" style="width:46%;"/>
							<td colspan="2" style="width:29%;">
							<div class="submit" style="text-align: left;">
							<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;"/>
							<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;"/>
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
				<button type="button" class="btn btn-success"  onclick="queryStatus(this,'');" name="status">全部</button>&nbsp;&nbsp;	  
				<button type="button" class="btn btn-default"  onclick="queryStatus(this,'8');" name="status">退款中</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-default"  onclick="queryStatus(this,'10');" name="status">退款失败</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-default"  onclick="queryStatus(this,'9');" name="status">退款成功</button>	
			    <c:if test="${!empty btnAu['billmanagerment/refundBillPendingHistory/export']}">
			     <button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="refundBillPendingHistoryList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">
      {details:'${ btnAu['billmanagerment/refundBillPendingHistory/init/list']}'}
    </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/billmanagerment/refundBillPendingHistoryList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

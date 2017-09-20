<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<title>充值详情</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="billDetailForm">
		<table width="100%" >
			<tbody>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户编号:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.uid}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户昵称:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.nname}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户手机号:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.phoneId}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;卡序号:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.cardNo}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;卡名称:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.cardName}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;会员卡ID:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.noId}</small></td>
					
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;充值金额:</h4></th>
					<td style="width:35%;"><small>￥${tRechargeRecord.amount}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;到账金额:</h4></th>
					<td style="width:35%;"><small>￥${tRechargeRecord.profit}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;充值订单号:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${tRechargeRecord.orderId}</small></td>					
					<th style="width:15%;"><h4>&nbsp;&nbsp;充值时间:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.rdateStr}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;充值状态:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${tRechargeRecord.payStatusStr}</small></td>					
					<th style="width:15%;"><h4>&nbsp;&nbsp;到账时间:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.udateStr}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;支付流水号:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.number}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;第三方支付账号:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.thirdUid}</small></td>
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;支付方式:</h4></th>
					<td style="width:35%;"><small>${tRechargeRecord.payTypeStr}</small></td>
					<th style="width:15%;" nowrap="nowrap"><h4></h4></th>
					<td style="width:35%;"><small></small></td>						
				</tr>		

 				<tr>
				<th colspan="4" style="text-align: center;"> 
					<button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
				</th>
 				</tr>
 			</tbody>
	 	</table>
	 </form>
</body>
</html>

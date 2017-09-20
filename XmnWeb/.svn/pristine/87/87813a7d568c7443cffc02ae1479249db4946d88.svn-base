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
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="billDetailForm">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%" >
			<tbody>
				<tr>
					<th style="width:17%;"><h4>充值方案申请表ID:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.id}</small></td>
					<th style="width:17%;"><h4>&nbsp;发行编号:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.batchId}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;发行卡名称:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.batchName}</small></td>
					<th style="width:17%;"><h4>&nbsp;商家编号:</h4></th>
					<td style="width:35%;">${tissueCardApply.sellerid}</td>
				</tr>
				<tr>
					<%-- <th style="width:17%;"><h4>&nbsp;&nbsp;所属向蜜客:</h4></th>
					<td style="width:33%;"><small>${bill.miketypeText}</small></td> --%>	
					<th style="width:17%;"><h4>&nbsp;&nbsp;商家名称:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.sellername}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;结算价:</h4></th>
					<td style="width:35%;">${tissueCardApply.cash}</td>
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;面额:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.price}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;售价:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.retail}</td>
				</tr>
					<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;开始有效期:</h4></th>
					<td style="width:35%;" nowrap="nowrap"><small>${tissueCardApply.statrperiod}</small></td>					
					<th style="width:17%;"><h4>&nbsp;&nbsp;结束有效期:</h4></th>
					<td style="width:35%;">${tissueCardApply.endperiod}</td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;发行数量:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.number}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;申请状态:</h4></th>
					<td style="width:35%;">${tissueCardApply.cardstatusStr}</td>
				</tr>
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;审核不通过原因:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.reason}</small></td>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;已售卡面额:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.amount}</small></td>						
				</tr>		
				<tr>
					<th style="width:15%;" nowrap="nowrap"><h4>&nbsp;&nbsp;售卡收益:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.profit}</small></td>
					<th style="width:17%;"><h4>本批次已卖卡数量:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.num}</small></td>						
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;发行时间:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.rdate}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;更新时间:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.udate}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;数据状态:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.dstatusStr}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;默认充值方案:</h4></th>
					<td style="width:33%;"><small>${tissueCardApply.isDefaultStr}</small></td>
				</tr>
				</tbody>
				</table>
				<div style="min-height: 30px; max-height: 150px; overflow: scroll;">
				
	 	</div>
	 	<div style="margin: 20px auto 0px; text-align: center;">
	 	 <button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
	 	</div>
	 </form>
</body>
</html>

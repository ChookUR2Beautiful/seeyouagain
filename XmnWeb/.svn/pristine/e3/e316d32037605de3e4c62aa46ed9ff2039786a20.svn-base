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
			    <tr style=" border-bottom-style:solid;border-bottom-width: 1px;">
					<th colspan="4" style="text-align: center;"><h3>积分商品订单详情</h3></th>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;订单编号:</h4></th>
					<td style="width:35%;"><small>${billbargain.bid}</small></td>
					<th style="width:15%;"><h4>&nbsp;商品编号:</h4></th>
					<td style="width:35%;"><small>${billbargain.bpid}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;积分商品名称:</h4></th>
					<td style="width:35%;"><small>${billbargain.pname}</small></td>
					<th style="width:15%;"><h4>&nbsp;&nbsp;商品价格:</h4></th>
					<td style="width:35%;"><small>${billbargain.price}</small></td>
				</tr>
				
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户编号:</h4></th>
					<td style="width:35%;"><small>${billbargain.uid}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户昵称:</h4></th>
					<td style="width:35%;"><small>${billbargain.uname}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;商家编号:</h4></th>
					<td style="width:35%;"><small>${billbargain.sellerid}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;商家名称:</h4></th>
					<td style="width:35%;"><small>${billbargain.sellername}</small></td>
				</tr>
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;用户手机号:</h4></th>
					<td style="width:35%;"><small>${billbargain.phoneid}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;支付状态:</h4></th>
					<td style="width:35%;"><small>${billbargain.statusStr}</small></td>
				</tr>
				
				<tr>
					<th style="width:15%;"><h4>&nbsp;&nbsp;兑换状态:</h4></th>
					<td style="width:35%;"><small>${billbargain.bstatusStr}</small></td>				
					<th style="width:15%;"><h4>&nbsp;&nbsp;备  注:</h4></th>
					<td style="width:35%;"><small>${billbargain.remark}</small></td>
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

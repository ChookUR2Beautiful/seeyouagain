<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动名称:</h5></th>
					<td colspan="2">${activity.aname}</td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始日期:</h5></th>
					<td colspan="2">${activity.startDate}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束日期:</h5></th>
					<td colspan="2">${activity.endDate}</td>
				</tr>
				
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<td colspan="2">${(!empty activity.startTimeText)?activity.startTimeText:"-"}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束时间:</h5></th>
					<td colspan="2">${(!empty activity.endTimeText)?activity.endTimeText:"-"}</td>
				</tr>
			
				<tr>
					<th style="width:98px;"><h5>&nbsp;&nbsp;折扣补贴(%)：</h5></th>
					<td colspan="2">${activity.ngiveMoneyStr}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;补贴频率：</h5></th>
					<td colspan="2">${activity.rateName}</td>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;活动描述：</h5></th>
					<td colspan="2">${activity.eescription}</td>
				</tr>
				<tr>
					<th colspan="3" style="text-align: center;">
						<button data-dismiss="modal" class="btn btn-default" ><span class="icon-remove"></span>关闭</button>
					</th>
				</tr>
			</tbody>
		</table>
</body>
</html>

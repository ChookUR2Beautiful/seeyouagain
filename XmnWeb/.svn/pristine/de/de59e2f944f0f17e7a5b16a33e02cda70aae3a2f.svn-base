<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<title>会员卡查看</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<style type="text/css">
	table{
		text-align:left;
		width:80%;
	}
	td {
		line-height:1;
		padding:0px 10px;
	}
	h5{
		margin:5px;
	}
	.stitle,.scontent{
		float :left;
		padding:5px 0;
		text-align:center;
	}
	
	.stitle{
	
	}
	.scontent{
		
	}
	.scontent h5,td>h5{
		font-weight: normal;
	}
</style>
</head>

<body>
	<form class="form-horizontal" role="form" id="billDetailForm">
		<table class="table-col"><!-- table-bordered -->
		<tr>
<!--
				<td><h5>卡序号：</h5>&nbsp;&nbsp;&nbsp;${reqVipCardBean.id}</td>
 --> 						
				<td width="40%">
					<div class="stitle"><h5>会员卡申请表ID：</h5></div>
					<div class="scontent"><h5>${tCardApply.id}</h5></div>
				</td>
				<td width="40%">
				    <div class="stitle"><h5>商家编号：</h5></div>
					<div class="scontent"><h5>${tCardApply.sellerid}</h5></div>
				</td>
<!-- 							
				<td width="12%"><h5>卡序号：</h5></td>
				<td width="8%"><h5></h5></td>	
				<td width="20%">${reqVipCardBean.id}</td>	
				<td width="10%"></td><td width="20%"></td>
				<td width="20%"></td></td><td width="10%"></td>			
 -->								
		</tr>
		<tr>
			<td width="40%">
					<div class="stitle"><h5>商家名称：</h5></div>
					<div class="scontent"><h5>${tCardApply.sellername}</h5></div>
			</td>
			<td width="40%">
					<div class="stitle"><h5>使用说明：</h5></div>
					<div class="scontent"><h5>${tCardApply.instructions}</h5></div>
			</td>
		</tr>
		<tr>
			<td width="40%">
					<div class="stitle"><h5>申请类型：</h5></div>
					<div class="scontent"><h5>${tCardApply.applytypeStr}</h5></div>
			</td>
			<td width="40%">
					<div class="stitle"><h5>审核状态：</h5></div>
					<div class="scontent"><h5>${tCardApply.applystatusStr}</h5></div>
			</td>
		</tr>
		<tr>
			<td width="40%" colspan="2">
					<div class="stitle"><h5>审核不通过原因：</h5></div>
					<div class="scontent"><h5>${tCardApply.reason}</h5></div>
			</td>
		</tr>
		</table>
	 </form>
</body>
</html>

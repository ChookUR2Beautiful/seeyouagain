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
					<div class="stitle"><h5>卡序号：</h5></div>
					<div class="scontent"><h5>${reqVipCardBean.id}</h5></div>
				</td>
				<td width="40%" rowspan="2" valign="top">
				<div class="form-group" >
					<c:if test="${!empty reqVipCardBean.sellerLogo}">
		 				<label for="cardLogo" class="col-sm-5 control-label" style="margin-top:10px;" ><h5>卡Logo：</h5></label>
					    <div class="col-sm-7">
					      	<!-- <a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${reqVipCardBean.sellerLogo}"  title="商家LOGO" class="fancybox"  id="cardLogo543"> -->
								<img alt="商家图片"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${reqVipCardBean.sellerLogo}"   style="width: 100px;height: 100px;" />			
							<!-- </a> -->
					   </div>
					</c:if>
					<c:if test="${empty reqVipCardBean.sellerLogo}">
		 				<label  class="col-sm-5 control-label" ><h5>卡Logo：</h5></label>
					    <label  class="col-sm-2 control-label" ><h5><b>无</b></h5></label>
					</c:if>
				</div>
				</td>
				<td></td>
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
					<div class="stitle"><h5>卡名称：</h5></div>
					<div class="scontent"><h5>${reqVipCardBean.cardName}</h5></div>
				</td>
				<td></td>
		</tr>
		<tr>
<!--							
				<td><h5>适用商户：</h5>&nbsp;&nbsp;&nbsp;(总${childList.size()}家)</td>
 -->
				<td colspan="2">
					<div class="stitle"><h5>适用商户：</h5></div>
					<div class="scontent"><h5>(总${childList.size()}家)</h5></div>
				</td>
<!--  							
				<td><h5>适用商户：</h5></td>
				<td></td>
				<td>(总${childList.size()}家)</td>	
 -->							
		</tr>
		<tr>
			<td colspan="2">
				<table class="table table-hover table-bordered table-striped info">
					<thead>
						<tr>
							<th width="25%">商家编号</th>
							<th width="75%">商家名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${childList}" var="child" varStatus="status">
							<tr>
								<td style="text-align: center;">${child.msellerId}</td>
								<td style="text-align: center;">${child.msellerName}</td>
							</tr>		
						</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
<!--							
				<td><h5>是否开启充值功能：</h5>&nbsp;&nbsp;&nbsp;<c:if test="${reqVipCardBean.isPay==0}">已开启</c:if>
		      		<c:if test="${reqVipCardBean.isPay==1}">未开启</c:if></td>
 --> 
				<td colspan="2">
					<div class="stitle"><h5>是否开启充值功能：</h5></div>
					<div class="scontent"><h5><c:if test="${reqVipCardBean.isPay==0}">已开启</c:if>
    					<c:if test="${reqVipCardBean.isPay==1}">未开启</c:if></h5></div>
				</td>
<!--  							
				<td colspan="2"><h5>是否开启充值功能：</h5></td>
				<td><c:if test="${reqVipCardBean.isPay==0}">已开启</c:if>
     				<c:if test="${reqVipCardBean.isPay==1}">未开启</c:if>
     			</td>	
 -->		      				
		</tr>
		<tr>
			<td><div><h5>充值方案：</h5></div></td>
		</tr>
		<tr>
			<td colspan="2">
				<table class="table table-hover table-bordered table-striped info">
					<thead>
						<tr>
							<th style="width:40%; text-align: center;">充值</th>
							<th style="width:40%;text-align: center;">到账</th>
							<th style="width:20%;text-align: center;">默认方案</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${reqVipCardBean.planList}" var="plan" varStatus="status">
							<tr>
								<td style="text-align: center;">${plan.price}</td>
								<td style="text-align: center;">${plan.retail}</td>
								<td style="text-align: center;">
									<c:if test="${plan.isDefault==1}">默认</c:if>
								</td>
							</tr>		
						</c:forEach>
					</tbody>
				</table>
			</td>
			
		</tr>
		<tr>
<!--						
			<td><h5>上线状态：</h5>&nbsp;&nbsp;&nbsp;<c:if test="${reqVipCardBean.cardStatus==0}">待上线</c:if>
			      	<c:if test="${reqVipCardBean.cardStatus==1}">已上线</c:if>
			      	<c:if test="${reqVipCardBean.cardStatus==2}">已下线</c:if></td>
 --> 						
<!-- 
			<td><h5>上线状态：</h5></td>
			<td><c:if test="${reqVipCardBean.cardStatus==0}">待上线</c:if>
			      	<c:if test="${reqVipCardBean.cardStatus==1}">已上线</c:if>
			      	<c:if test="${reqVipCardBean.cardStatus==2}">已下线</c:if>
			</td>	
 -->
			<td colspan="2">
				<div class="stitle"><h5>上线状态：</h5></div>
				<div class="scontent"><h5><c:if test="${reqVipCardBean.cardStatus==0}">待上线</c:if>
		      	<c:if test="${reqVipCardBean.cardStatus==1}">已上线</c:if>
		      	<c:if test="${reqVipCardBean.cardStatus==2}">已下线</c:if></h5></div>
			</td>						
		</tr>
		</table>
	 </form>
</body>
</html>

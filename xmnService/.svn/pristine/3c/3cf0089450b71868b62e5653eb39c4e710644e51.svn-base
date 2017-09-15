<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>签约</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/normalize.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/commom.css">
</head>
<body class="payconfirm">
	<div class="payconfirm-val">
		<p class="payconfirm-p">订单金额<span>￥${data.response.amount}</span></p>
	</div>
	<div class="payconfirm-tips">
		<p>感谢您选择加入寻蜜鸟平台！</p>
		<p>以下是您的商铺信息请核对后确认支付</p>
	</div>
	<ul class="payconfirm-info-list">
		<li>
			<p class="payconfirm-info-content">
				<i class="payconfirm-info-shopsname"></i>
				<span class="payconfirm-info-title">店铺名称：</span>
				<span class="payconfirm-info-val">${data.response.sellername}</span>
			</p>
		</li>
		<li>
			<p class="payconfirm-info-content">
				<i class="payconfirm-info-address"></i>
				<span class="payconfirm-info-title">详细地址：</span>
				<span class="payconfirm-info-val">${data.response.province}${data.response.city}${data.response.area}${data.response.address}</span>
			</p>
		</li>
		<li>
			<p class="payconfirm-info-content">
				<i class="payconfirm-info-principal"></i>
				<span class="payconfirm-info-title">负责人：</span>
				<span class="payconfirm-info-val">${data.response.fullname}</span>
			</p>
		</li>
		<li>
			<p class="payconfirm-info-content">
				<i class="payconfirm-info-leadingofficial"></i>
				<span class="payconfirm-info-title">负责人电话：</span>
				<span class="payconfirm-info-val">${data.response.phone}</span>
			</p>
		</li>
		<li>
			<p class="payconfirm-info-content">
				<i class="payconfirm-info-discountset"></i>
				<span class="payconfirm-info-title">设置折扣：</span>
				<span class="payconfirm-info-val">${data.response.agio}折</span>
			</p>
		</li>
		<li>
			<p class="payconfirm-info-content">
				<i class="payconfirm-info-signtime"></i>
				<span class="payconfirm-info-title">签约日期：</span>
				<span class="payconfirm-info-val">${data.response.svalidity}-${data.response.evalidity}</span>
			</p>
		</li>
	</ul>
	<a href="${data.response.payBasePath}/pay/payType?orderid=${data.response.orderid}&apiversion=${param.apiversion}&systemversion=${param.systemversion}&sessiontoken=${param.sessiontoken}&appversion=${param.appversion}&type=${param.type}" class="bottomfixbtn">确认支付</a>
</body>

</html>
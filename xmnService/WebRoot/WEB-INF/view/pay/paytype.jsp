<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<script src="<%=basePath %>js/jquery.min.js"></script>
</head>
<body class="paytype">
	<div class="paytype-row">
		<p class="paytype-money"><span class="paytype-money-title">金额：</span><span class="paytype-money-val">￥${data.response.amount }</span></p>
	</div>
	<div class="paytype-button">
		<a href="javascript:;" onclick="dopay();">
			<span></span>
			<p>立即支付</p>
		</a>
	</div>

</body>
<script type="text/javascript">
var url = '<%=basePath %>payment/do_saas_sold?ordersn=${data.response.ordersn}';
var appId = '';
var timeStamp = '';
var nonceStr = '';
var package_ = '';
var signType = '';
var paySign = '';
var flag = 0;
function dopay(){
	console.log("开始支付了");
	if(!flag){
		flag = 1;
		$.ajax({
		    type: "GET",
		    url: "<%=basePath %>payment/do_saas_sold?ordersn=${data.response.ordersn }",
		    data: {username:$("#username").val(), content:$("#content").val()},
		    dataType: "json",
		    success: function(data){
		    	if(data.result == 'success'){
		    		if(data.o.state==200){
		    			appId = data.o.result.appId;
		    			timeStamp = data.o.result.timeStamp;
		    			nonceStr = data.o.result.nonceStr;
		    			package_ = data.o.result['package'];
		    			signType = data.o.result.signType;
		    			paySign = data.o.result.paySign;
		    			onReady();
		    			
		    		}else{
		    			alert("支付请求出错，请重试。");
		    		}
		    	}else{
		    		alert("支付请求出错，请重试。");
		    	}
		    }
		});
	}
	}


   function onBridgeReady(){
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	           "appId":appId,
	           "timeStamp":timeStamp,
	           "nonceStr" :nonceStr, 
	           "package":package_,     
	           "signType" :signType,
	           "paySign" :paySign
	       },
	       function(res){
	           if(res.err_msg == 'get_brand_wcpay_request:ok'){
	        	   window.location.href="<%=basePath%>toPaySuccess";
	           }else if(res.err_msg == 'get_brand_wcpay_request:cancel'){
	           		flag = 0;
	        	   alert('您已取消支付，请重新支付。');
	           }else if(res.err_msg == 'get_brand_wcpay_request:fail'){
	        	   window.location.href="<%=basePath%>toPayFail";
	           }else{
	        	   window.location.reload();
	           }
	       }
	   ); 
	}
	
   function onReady(){
	   if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}
   }
	

</script>
</html>
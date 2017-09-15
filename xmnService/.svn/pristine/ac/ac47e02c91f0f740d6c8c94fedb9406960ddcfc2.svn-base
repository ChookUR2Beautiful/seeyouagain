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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/normalize.css?v=<%=new Date().getTime()%>">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/commom.css?v=<%=new Date().getTime()%>">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/paydetail.css?v=<%=new Date().getTime()%>">
	
</head>
<body>
	<p class="sign-pay-title">押金</p>
	<ul class="sign-type-list">
		<c:forEach items="${data.response.saasPackageList}" var="saasPackage" varStatus="info">
			<li <c:if test="${info.count == 1}">class="active"</c:if> id="${saasPackage.id }">
				<span>${info.count}</span>
				<a href="javascript:;">
					<p class="sign-type-num"><span class="sign-type-val">${saasPackage.nums }</span><span class="sign-type-unit">套</span></p>
					<p class="sign-type-money">${saasPackage.price }元/${saasPackage.agio }折</p>
					<p class="sign-type-soucemoney" style="text-decoration: line-through;">原价:${saasPackage.mprice }元</p>
					<p class="sign-type-perselect"><span>${saasPackage.point}%</span>的寻蜜客选择</p>
				</a>
			</li>
		</c:forEach> 
	</ul>
	<input type="hidden" id="sessiontoken" value="${data.response.sessiontoken}">
	<input type="hidden" id="parentid" value="${data.response.parentid}">
	<div class="sign-type-chec">
		<p class="sign-type-chec-local">买的越多优惠越多</p>
		<c:forEach items="${data.response.discountList}" var="discount">
		<p class="sign-type-chec-total">认购${discount.count}套软件每套优惠${discount.discountPrice}元</p>
		</c:forEach>
	</div>
	<span class="sign-type-line"></span>
<!-- 	<div class="sign-type-pay">
		<a href="javascript:toPay();" class="sign-type-paybtn">
			<span class="sign-type-wx"></span>
			<p>微信支付</p>
		</a>
			<a href="javascript:toOtherPay();" class="sign-type-paybtn">
			<span class="sign-type-wx"></span>
			<p>微信代付</p>
		</a>
	</div> -->
	
	   <div class='pay-type'>
        <a href="javascript:toPay();">
            <img src="<%=basePath %>img/wxshare.png">
            <p>微信支付</p>
        </a>
        <a href="javascript:toOtherPay();">
            <img src="<%=basePath %>img/friendpay.png">
            <p>好友代付</p>
        </a>
    </div>

	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js?v=<%=new Date().getTime()%>"></script>
	
	<script>
	var appId = '';
	var timeStamp = '';
	var nonceStr = '';
	var package_ = '';
	var signType = '';
	var paySign = '';
	
	$(function(){
		$('.sign-type-list li a').bind('click',function(){
			$('.sign-type-list li').removeClass('active');
			$(this).parent().addClass('active');
		});
	});
	
	window.onbeforeunload=function(){
		location.reload(location);
	};
	var flag = 0;
	function toPay(){
		var activeli = $(".active");
		if(!flag){
		  flag = 1;
		  $.ajax({
		    type: "GET",
		    url: "<%=basePath%>pay/paySaasPackage",
		    data: {sessiontoken:$("#sessiontoken").val(), id:activeli[0].id,parentid:$("#parentid").val()},
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
		    		alert(data.info);
		    	}
		    }
			});
		}
		
	}
	
	function toOtherPay(){
		var activeli = $(".active");
		if(!flag){
		  flag = 1;
		  $.ajax({
		    type: "GET",
		    url: "<%=basePath%>placePayOrder",
		    data: {sessiontoken:$("#sessiontoken").val(), id:activeli[0].id,parentid:$("#parentid").val()},
		    dataType: "json",
		    success: function(data){
		    	if(data.state=100){
		    		location.href="<%=basePath%>pay/toOtherPay?orderid="+data.response.orderid+"&parentid="+$("#parentid").val();
		    	}else{
		    		
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
	    	  /*  alert("err_code:"+res.err_code+"\n err_desc:"+res.err_desc+"\n err_msg:"+res.err_msg); */
	           if(res.err_msg == 'get_brand_wcpay_request:ok'){
	        	   window.location.href="<%=basePath%>toSignSuccess";
	           }else if(res.err_msg == 'get_brand_wcpay_request:cancel'){
	           		flag = 0;
	        	   alert('您已取消支付，请重新支付。');
	           }else if(res.err_msg == 'get_brand_wcpay_request:fail'){
	           		window.location.href="<%=basePath%>toSignPayFail";
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
</body>
</html>

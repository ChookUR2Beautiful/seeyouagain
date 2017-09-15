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
    <title>寻蜜客-SAAS软件-代付</title>
    <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath %>css/normalize.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/paydetail.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/showtips.css"/>
</head>
<body>
	
	
    <span class="specline"></span>
    <div class="paydetail-userinfo">
        <div class="paydetail-userinfo-box">
<%--             <i style="background-image: url(<%=basePath %>img/headimg.png);"></i>
 --%>            <div class="paydetail-userinfo-name">
                <p>${name}</p>
                <span>${phone }</span>
            </div>
        </div>
        <p class="paydetail-tips">向你发起代付请求</p>
    </div>
    <span class="specline"></span>
    <div class="paydetail-bugnumbox">
        <div class="paydetail-header">
            <span class="paydetail-label">${agio }折</span>
            <p class="paydetail-header-num">${nums }<span>套</span></p>
            <p class="paydetail-sourceprice">原价：￥${price }</p>
            <p class="paydetail-price">￥${agioPrice }</p>
        </div>
        <p class="paydetail-time">
            <span>创建时间</span>
            <i>${createTime }</i>
        </p>
    </div>
    <span class="specline"></span>

    
    <div class="paydetail-share paydetail-paybtn">
    <div class="paydetail-payinfo">
        <p class="paydetail-payinfo">输入代付好友手机</p>
        <input type="text" class="paydetail-payphone" id="friendphone">
    </div>
        <a href="javascript:toPay();" class="paydetail-wxsharebtn">
            <img src="<%=basePath %>img/wxshare.png">
            <span>微信支付</span>
        </a>
    </div>
</body>
	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/show.js"></script>
	<script>
	var appId = '';
	var timeStamp = '';
	var nonceStr = '';
	var package_ = '';
	var signType = '';
	var paySign = '';
	var flag = 0;
	
	function toPay(){
		var tips = new showtips();
        var phoneReg = /^1\d{10}$/g;
        var phone = $('#friendphone').val();
		
          if(phoneReg.test(phone)){
        	  if(!flag){
        		  flag = 1;
        		  $.ajax({
        		    type: "GET",
        		    url: "<%=basePath%>pay/placePay",
        		    data: {orderid:'${orderid}',parentid:'${parentid}',phone:phone,consumerType:1},
        		    dataType: "json",
        		    success: function(data){
        		    	//alert();
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
        		    			tips.show("支付请求出错，请重试。");
        		    		}
        		    	}else{
        		    		tips.show(data.result);
        		    		  flag = 0;
        		    	}
        		    }
        			});
        		}
          }else{
        	  tips.show("请输入正确的手机号码！");
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

</html>
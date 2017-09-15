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
	<title>寻蜜客-SAAS软件套餐-代付</title>
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
<body class="paydetail-body">
	
	
	
	<div style='margin:0 auto;width:0px;height:0px;overflow:hidden;'>
	<img src="<%=basePath %>img/bird.png">
	</div>	
  <div class="paydetail-header">
        <span class="paydetail-label">${agio }折</span>
        <p class="paydetail-header-num">${nums }<span>套</span></p>
        <p class="paydetail-sourceprice">原价：￥${price }</p>
        <p class="paydetail-price">￥${agioPrice }</p>
    </div>

    <div class="paydetail-payinfo">
        <p class="paydetail-payinfo">输入代付好友手机</p>
        <input type="text" class="paydetail-payphone" id="friendphone">
    </div>

    <div class="paydetail-share">
        <p class="paydetail-payinfo">分享给代付好友</p>
        <a href="javascript:;" class="paydetail-wxsharebtn"  onclick="shareFriend();">
            <img src="<%=basePath %>img/wxshare.png">
            <span>微信好友</span>
        </a>
     
    </div>
    <div class="modalback" onclick="closeModal(this);"></div>
    <div class="modal-wxtips" onclick="closeModal(this);"><img src="<%=basePath %>img/modal.png"></div>
    <a onclick="shareFriend();" href="javascript:;" class="paydetail-confirmbtn">发送</a>
	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/show.js"></script>
 	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
 	<script>
 	/* 	$(function(){
 			wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: "${appId}", // 必填，公众号的唯一标识
				    timestamp: "${timestamp}", // 必填，生成签名的时间戳
				    nonceStr: "${nonceStr}", // 必填，生成签名的随机串
				    signature: "${signature}",// 必填，签名，见附录1
				    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
 			
 		}); */

        function shareFriend()
        {	
            var tips = new showtips();
            var phoneReg = /^1\d{10}$/g;
            var phone = $('#friendphone').val();
            var shareConfig = {
                title: '分享', // 分享标题
                desc: '分享描述', // 分享描述
                link: '<%=basePath %>pay/toOtherPay?orderid=${orderid}&parentid=${parentid}', // 分享链接
                imgUrl: '', // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                success: function () { 
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () { 
                    // 用户取消分享后执行的回调函数
                }
            }

            if(phoneReg.test(phone)){
            	$.ajax({
          		    type: "GET",
          		    url: "<%=basePath%>otherPayPhone",
          		    data: {orderid:'${orderid}',phone:phone},
          		    dataType: "json",
          		    success: function(data){
          		    	if(data.state==100){
          		    		 $('.modal-wxtips').show();
          	               	 $('.modalback').show();
          	         /*  	wx.config({
          				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
          				    appId: data.response.appId, // 必填，公众号的唯一标识
          				    timestamp: data.response.timestamp, // 必填，生成签名的时间戳
          				    nonceStr: data.response.nonceStr, // 必填，生成签名的随机串
          				    signature: data.response.signature,// 必填，签名，见附录1
          				    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
          				});
          	      
          	                    wx.ready(function(){
          	                    wx.onMenuShareTimeline(shareConfig);//分享到朋友圈
          	                    wx.onMenuShareAppMessage(shareConfig);//分享给朋友
          	                    wx.onMenuShareQQ(shareConfig);//分享到QQ */
          	             		     wx.ready(function(){
            	                    wx.onMenuShareTimeline(shareConfig);//分享到朋友圈
            	                    wx.onMenuShareAppMessage(shareConfig);//分享给朋友
            	                    wx.onMenuShareQQ(shareConfig);//分享到QQ 
          	                }); 
          		    	}else{
          		    		alert(data.info);
          		    	}
          		    }
          			});
                
            }else{
                tips.show('请输入正确的手机号码')
            }
        }


        function closeModal(e)
        {
            $('.modal-wxtips').hide();
            $('.modalback').hide();
        }
    </script>


</body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uid = request.getParameter("uid");
%>
<!DOCTYPE html>
<html lang="zh-cn" class="gray">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <script type="text/javascript" src="<%=basePath %>js/rem.js"></script>
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
	<link rel="stylesheet" href="<%=basePath %>css/showtips.css"/><link>
    <link rel="stylesheet" href="<%=basePath %>css/valideCommon.css"/><link>
    <link rel="stylesheet" href="<%=basePath %>css/login.css"/><link>
</head>
<body>
    <div class="bg-fff invite-login">
        <p>你的好友${data.name}<br />${data.phone}邀请您加入寻蜜客</p>
        <p class="hint">在吃喝玩乐中顺便赚钱</p>
    </div>
    <p class="sawtooth"></p>
    
    <form action="<%=basePath%>pay/paySaasOrder" method="post" id="phoneForm">  <!-- from表单，进入创建saas订单接口  -->
	<input type="hidden" name="uid" value="${param.uid}">
    <ul class="login-box bg-fff">
        <li>
            <label>手机号</label>
            <input type="tel" maxlength="11" placeholder="请输入你的手机号码" class="valpphone" oninput="checkInput();" name="phone"/>
            <a href="javascript:;" id="getCode">获取验证码</a>
        </li>
        <li>
            <label>验证码</label>
            <input type="tel"  maxlength="4" placeholder="请输入验证短信4位数验证码" class="no-pd vcode" oninput="checkInput();" name="phoneCode"/>
        </li>
        <li style="display:none;" class="psw-row">
            <label>设置密码</label>
            <input type="password" maxlength="22" placeholder="请设置账号登录密码" class="no-pd pwd" name="psw" oninput="checkInput();"/>
        </li>
    </ul>
    </form>
  
    <p class="hint" id="tips"></p>
    <a href="javascript:;" class="next-btn">下一步</a>
    <script type="text/javascript" src="<%=basePath %>js/jquery-2.1.1.min.js"></script>
    <%-- <script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script> --%>
    <script type="text/javascript" src="<%=basePath %>js/login.js"></script>
    
    <script>
    
	    function checkInput(){
			var phone = $('.valpphone').val();
			var vcode = $('.vcode').val();
			var pwd = $('.pwd').val();
			var reg = /^1\d{10}$/g;
			$('#tips').empty();
			if($('.psw-row').css('display') == 'block'){
				var pwd = $('.pwd').val();
				if(reg.test(phone) && vcode.length==4 && pwd.length>5 && hasNext==1){
					$('.next-btn').addClass('pass').unbind().click(nextStep);
				}else{
					$('.next-btn').removeClass('pass').unbind();
				}
			}else {
				if(reg.test(phone) && vcode.length==4 && hasNext==1){
					$('.next-btn').addClass('pass').unbind().click(nextStep);
				}else{
					$('.next-btn').removeClass('pass').unbind();
				}
			}
			
		}
    	
		var phoneStatus = 0;
		var hasNext = 0;
		$(function(){
			var isGetVcode = 0;
			$('#getCode').bind('click',function(){
				isGetVcode == 0 && buttonEvent();
			});
			
			var Timer = null;
			var time = 60;
			function setTime(){
				time --;
				$('#getCode').css({'color' : '#ccc' , 'border' : '1px solid #ccc'}).html(time+'S');
				Timer = setTimeout(function(){
					setTime();
				},1000);
				
				if(time < 1){
					$('#getCode')
					.css({'color': '#f6ab00' , 'border' : ' 1px solid #f6ab00'})
					.html('重新获取');
					time = 60;
					clearTimeout(Timer);
					isGetVcode = 0;
					return;
				}
			}
			function buttonEvent(){
				$('#tips').empty();
				
				var phone = $('.valpphone').val();
				var sendType=1;
				var reg = /^1\d{10}$/g;
				if(reg.test(phone)){
					//验证成功，取验证码

            		/*弹出层*/
            		var randNum = Math.random();
					var vcodesrc = '${basePath}getVerify?phone='+phone;		/* 获取图片验证码 */
					var contentHtml = '<div class="valphone-row">';
					contentHtml += '<div class="valphone-input-box valphone-img" ><input type="text" placeholder="请输入验证码" id="validevcode" class="valphone-vcode" name="phoneCode" >';
					contentHtml += '<a href="javascript:;" onClick="setVcodeimg(this);" class="valphone-input-codeimg">';
					contentHtml += '<img datasrc="'+vcodesrc+'" src="'+vcodesrc +'&v='+randNum +'">';
					contentHtml += '</a></div></div>';
					new dialogWithbtn({
						title: '请输入验证码',
						content: contentHtml,
						confirmTxt: '确定',
						confirmFun: function(vcode){
							$('#tips').empty();
							var vcode =$("#validevcode").val();
							
							if(vcode != ''){
							 	$.ajax({
						             type: "get",
						             url:"${basePath}xmerGetPhoneCode",	/* 发送手机验证码之前 验证图片验证码 */
						             data: {
						            	 'phone':phone,
						            	 'sendType':1,
						            	 'code': vcode
						           	 },
						             dataType: "json",
						             success: function(data){
						            	 if(data.state==100||data.state==108){
						            		 setTime();
						            		isGetVcode = 1;
						     				hasNext = 1;
						            		$("#validevcode").remove();
						            		$('.confirmdiagmodal').remove();
											$('.dialogwithbtn-box').remove();
											if(data.state == 108){
							             		$('.psw-row').css('display','block');
							             	}
						            	 }else{
						            		 $('#tips').html(data.info);
						            	 }
						            	 
						            	 
						             }
								}); 
							}else{
								$('#tips').html('请输入验证码');
							}
						},
						cancelFun:function(){
							
						}
					});
					
					
					
				}else{
					$('#tips').html('手机号码有误，请重新输入');
					isGetVcode=0;
				}
			}
			
				
		});
		
		function nextStep(){ 	//点击下一步的时候
			var phone = $('.valpphone').val();
			var phoneCode = $('.vcode').val();
			var psw = $('.pwd').val();
			var uid = ${param.uid};
			var reg = /^1\d{10}/g;
			var pswReg = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
			$('#tips').empty();
			if(!reg.test(phone)){
				$('#tips').html('手机号码有误，请重新输入');
				return;
			}
			
			if(!pswReg.test(psw) && $('.psw-row').css('display') == 'block'){
				$('#tips').html('密码格式错误，请输入6-22位密码');
				return;
			}

			if(phoneCode == ''){
				$('#tips').html('请输入验证码');
				return;
			}
		
		$.ajax({
			type: "post",
            url:"${basePath}invitedJoinXMER",
            data: {'phone':phone,'phoneCode':phoneCode,'uid':uid},
            dataType: "json",
            success: function(data){
            	if(data != null && data.state==100){
            		submitForm();//提交表单
            	}else if(data != null && data.state==301){
            		$('#tips').html(data.info);
            	}
            }
		});
		
		}; 
		
		function submitForm(json){
			$("#phoneForm").submit(); 
		}
		
		function setVcodeimg(_this)
		{
			var imgEle = $(_this).find('img');
			var imgSrc = imgEle.attr('datasrc');
			var radomNum = GetRandomNum(1,10000);
			imgEle.attr('src',imgSrc+"&v="+radomNum);
		}
		
		function GetRandomNum(Min,Max)
		{   
			var Range = Max - Min;   
			var Rand = Math.random();   
			return(Min + Math.round(Rand * Range));   
		}  
		
		
		
		function getLanguageCode(){
			if(phoneStatus == 0){
				var phone = $('.valpphone').val();
				console.log(phone);
				 $.ajax({
					 type: "post",
		             url:"<%=basePath%>languageCode",
		             data: {'phone':phone},
		             dataType: "json",
		             success: function(data){
		             	if(data != null && data.state==100){
		             		phoneStatus = 1;
		             	}
		             }
					});
			}
		}
	</script>
</body>
</html>
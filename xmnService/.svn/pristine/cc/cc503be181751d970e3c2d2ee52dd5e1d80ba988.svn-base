<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uid = request.getParameter("uid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>手机号码验证</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/normalize.css?v=<%=new Date().getTime()%>">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/commom.css?v=<%=new Date().getTime()%>">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/showtips.css?v=<%=new Date().getTime()%>">
</head>

<body>
	<div class="valphone-box">
		<form action="<%=basePath%>pay/paySaasOrder" method="post" id="phoneForm">
		<input type="hidden" name="uid" value="${param.uid}">
		<div class="valphone-row">
			<span class="valphone-label">手机号</span>
			<div class="valphone-input-box">
				<input type="text" placeholder="输入您的手机号码" class="valphone-phone valpphone" name="phone">
				<span>+86</span>
			</div>
		</div>

		<div class="valphone-row">
			<span class="valphone-label" style="margin-top:6px;">短信验证码</span>
			<div class="valphone-input-box">
				<input type="text" placeholder="请输入验证码" class="valphone-vcode" id="valphonevcode" name="phoneCode" >
				<a href="javascript:;" class="valphone-getvcode">获取验证码</a>
			</div>
		</div>
		
		<div class="valphone-row psw-row" style="display:none;">
			<span class="valphone-label" style="width:65px;">设置密码</span>
			<div class="valphone-input-box">
				<input type="password" placeholder="输入您的密码"  class="valphone-phone valpsw"  name="psw"  style="padding-left:15px;">
			</div>
		</div>
		
		<div class="getlancode-box" style="display:none;">
			<span class="getlancode-tips">收不到验证码？请点击</span>
			<a href="javascript:getLanguageCode();" class="valphone-getLanguageCode getlancode"><i></i><span>语音验证码</span></a>
		</div>
		<style>
		.valphone-input-codeimg{
			
			margin-left: 8px;
			height: 50px;
			
			width:120px;
		}
		
			.valphone-input-codeimg iframe{
				width:80px;
				border:none;
				overflow:hidden;
			}
		</style>
		<a href="javascript:;" class="valphone-nextstep">下一步</a>
		</form>
		<div style="width:100%;height:auto;text-align:center;margin-top:30px;">
			<img src="<%=basePath %>img/xmn_sucai_img.png" style="width:180px;height:auto;"/>
			<p style="margin-top:20px;font-size:20px;color:#666;line-height:1.8em;">
				您的好友${data.name}<br/>
				${data.phone}邀请您加入寻蜜客<br/>
				在吃喝玩乐中顺便赚钱
			</p>
		</div>	
	</div>
	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js?v=<%=new Date().getTime()%>"></script>
	<script type="text/javascript" src="<%=basePath %>js/show.js?v=<%=new Date().getTime()%>"></script>
	
	<script>
		var tips = new showtips();
		var phoneStatus = 0;
		$(function(){
			var isGetVcode = 0;
			$('.valphone-getvcode').bind('click',function(){
				isGetVcode == 0 && buttonEvent();
			});
			
			var Timer = null;
			var time = 60;
			function setTime(){
				time --;
				$('.valphone-getvcode').css({'color' : '#ccc' , 'border' : '1px solid #ccc'}).html(time+'秒后获取');
				Timer = setTimeout(function(){
					setTime();
				},1000);
				
				if(time < 1){
					$('.valphone-getvcode')
					.css({'color': '#f6ab00' , 'border' : ' 1px solid #f6ab00'})
					.html('重新获取');
					time = 60;
					clearTimeout(Timer);
					isGetVcode = 0;
					return;
				}
				
				if(time < 30){
					$('.getlancode-box').show();
 				}
			}
			function buttonEvent(){
				isGetVcode = 1;
				var phone = $('.valpphone').val();
				var sendType=1;
				var reg = /^1\d{10}$/g;
				if(reg.test(phone)){
					//验证成功，取验证码
					//loading.show();

            		/*弹出层*/
            		var randNum = Math.random();
					var vcodesrc = '${basePath}getVerify?phone='+phone;
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
							
							var vcode =$("#validevcode").val();
							
							if(vcode != ''){
						 	/* 	loading.show(); */
							 	$.ajax({
						             type: "get",
						             url:"${basePath}xmerGetPhoneCode",
						             data: {
						            	 'phone':phone,
						            	 'sendType':1,
						            	 'code': vcode
						           	 },
						             dataType: "json",
						             success: function(data){
						            	 if(data.state==100||data.state==108){
						            		 loading.hide();
						            		 setTime();
						            		$("#validevcode").remove();
						            		$('.confirmdiagmodal').remove();
											$('.dialogwithbtn-box').remove();
											if(data.state == 108){
							             		$('.psw-row').css('display','block');
							             	}
						            	 }else{
						            		 loading.hide();
						            		 tips.show(data.info);
						            	 }
						            	 
						            	 
						             }
								}); 
							}else{
								tips.show('请输入验证码');
							}
						},
						cancelFun:function(){
							
						}
					});
					
					
					
				}else{
					tips.show('手机号码有误，请重新输入');
					isGetVcode=0;
				}
			}
				$('.valphone-nextstep').bind('click',function(){
					var phone = $('.valpphone').val();
					var phoneCode = $('#valphonevcode').val();
					var psw = $('.valpsw').val();
					var uid = ${param.uid};
					var reg = /^1\d{10}/g;
					var pswReg = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
					if(!reg.test(phone)){
						tips.show('手机号码有误，请重新输入');
						return;
					}
					
					if(!pswReg.test(psw) && $('.psw-row').css('display') == 'block'){
						tips.show('密码格式错误，请输入6-22位密码');
						return;
					}
	
					if(phoneCode == ''){
						tips.show('请输入验证码');
						return;
					}
					loading.show();
				submitForm();//提交表单
				
				/* $.ajax({
					 type: "post",
		             url:"${basePath}invitedJoinXMER",
		             data: {'phone':phone,'phoneCode':phoneCode,'uid':uid},
		             dataType: "json",
		             success: function(data){
		             	if(data != null && data.state==100){
		             		var json = JSON.stringify(data.response);
		             		submitForm(json)
		             	}
		             }
					}); */
				}); 
				
				
		});
		function submitForm(json){
			$("#phoneForm").submit(); 
			/* $.ajax({
				 type: "post",
		             url:"${basePath}getInvitedJoinXMER",
		             data: {'response':json},
		             dataType: "html",
		             success:function(){
		             	alert("成功");
		             }
			}) */
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
		             		loading.hide();
		             	}
		             }
					});
			}
		}
	</script>
</body>
</html>
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
	<title>邀请好友</title>
	<meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${basePath }css/normalize.css">
	<link rel="stylesheet" type="text/css" href="${basePath }css/commom.css">
	<script src="${basePath }js/jquery.min.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	  $("#bottomfixbtn").click(function(){
		var url = "${basePath }addCK";
	  	$.ajax({
	  		type:'post',
	  		url:url,
	  		data:$('#addcreateman').serialize(),
	  		success: function(data) {
                if(data.state == 100){
                	//添加创客成功
                	alert("添加创客成功");
                }else {
					//添加创客失败
                	alert("添加创客失败");
				}
            }
	  		});
	  });
	});
</script>
<body>
	<p class="invitef-title">创客</p>
	<div class="invitef-img">
		<img src="${basePath }img/sign_guide_sharefashion.png">
	</div>
	<p class="addcreateman-p">收获丰富的额外收入</p>
	<p class="addcreateman-intro">寻蜜鸟的大家庭，充满了优质的用户与商户，利用您的闲暇时间认识最有趣、最时髦的伙伴。加入这个共享潮流，改变大家餐饮方式。</p>
	<form action="" id="addcreateman">
		<input type="text" name="phone" class="addcreateman-form-text" placeholder="输入创客手机">
		<input type="hidden" name="appversion" value="${param.appversion }"/>
		<input type="hidden" name="systemversion" value="${param.systemversion }"/>
		<input type="hidden" name="apiversion" value="${param.apiversion }"/>
		<input type="hidden" name="sessiontoken" value="${param.sessiontoken }"/>
	</form>
	<a href="javascript:;" id="bottomfixbtn" class="bottomfixbtn">添加创客</a>
	
</body>
</html>
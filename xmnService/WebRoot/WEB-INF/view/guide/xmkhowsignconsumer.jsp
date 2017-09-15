<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>如何签约客户</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath %>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath %>css/xmk-lead.css">
 
</head>
<body style="padding-bottom: 50px;">
    <p class="xmkhowsign-normaltxt marginTop10">
        吃喝玩乐中，三部曲拿下你的客户：
    </p>

    <p class="xmkhowsign-normaltxt">
        <span>第一步：</span>了解商户的真实需求
    </p>

    <div class="xmk-img xmk-maxwidth">
        <img src="<%=basePath %>img/xmk_life_1.png">
    </div>
    
    <p class="xmkhowsign-normaltxt">
        <span>第二步：</span>向商户说明加入寻蜜鸟平台带来的好处牢记软件两大重点功能，清晰描述，吸引客户：
    </p>
    
    <h4 class="xmkhowsign-titletxt">重点功能<span>①——引流</span></h4>
    
    <div class="xmk-img xmk-maxwidth">
        <img src="<%=basePath %>img/xmk_life_2.png">
    </div>

    <p class="xmkhowsign-normaltxt">
        寻蜜鸟促销功能和积分超市，帮助你的客户实现客户引流和回流。<br/>
        <span>引流：</span>通过寻蜜鸟平台，实现引流，如促销模板、优惠券发放、平台活动。<br/>
        <span>回流：</span>会员消费后，寻蜜鸟赠送1：1平台积分，积分兑换商家产品后，带来回流二次消费。<br/>
    </p>
    
    <h4 class="xmkhowsign-titletxt">重点功能<span> ②——赚钱</span></h4>

    
    <div class="xmk-img xmk-maxwidth">
        <img src="<%=basePath %>img/xmk_life_3.png">
    </div>
    
    <a href="javascript:;" class="xmk-checklink">《点击查看分账规则》</a>
    <a href="javascript:;" class="xmk-checklink">《点击了解寻蜜鸟及功能详解》</a>
</body>
</html>
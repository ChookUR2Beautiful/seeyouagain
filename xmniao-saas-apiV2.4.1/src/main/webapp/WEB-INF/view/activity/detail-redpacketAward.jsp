<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>领取详情</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/swiper.css">
    <link rel="stylesheet" href="${ctx}/css/marketing.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
        <div class="expendtails-module">
            <div class="expendtails-wrap">
                <div class="expend-user-loge"><img src="${record.head}" alt="会员头像" id="head" /></div>
                <div class="expend-user-name">${record.usrName}</div>
                <div class="expend-user-state"><span>${record.vipName}</span></div>
            </div>
        </div>
        <div class="fill-list-module" >
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">普通抽奖红包</span><span class="item-name">红包类型</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${record.denomination}</span><span class="item-name">红包金额</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap"><fmt:formatDate value="${record.getTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></span><span class="item-name">领取时间</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${record.isShare==1?"已分享":"未分享"}</span><span class="item-name">是否分享</span></div></div>
        </div>
    </div>
</body>
<script src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		if(!$("#head").attr("src")){
			$("#head").attr("src","${ctx}/imgs/activity/Binding-Member2@2x.png");
		}
	})
</script>

</html>
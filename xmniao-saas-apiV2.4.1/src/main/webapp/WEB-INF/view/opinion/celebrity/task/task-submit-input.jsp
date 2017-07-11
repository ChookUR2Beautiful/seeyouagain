<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>描述你的需求</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
        <div class="fill-list-module" >
            <div class="list-divhead">请选择适合您的网红</div>
            <a class="list-wrap">
                <div class="list-item in-icon-box"><i class="icon-wrap icon-arrow-right"></i>
                    <span href="#" class="item-input-wrap">${celebrity.name}</span>
                    <span class="item-name">主播网红</span>
                </div>
            </a>
            <a class="list-wrap">
                <div class="list-item in-icon-box"><i class="icon-wrap icon-arrow-right"></i>
                    <span href="#" class="item-input-wrap">晒照</span>
                    <span class="item-name">选择服务</span>
                </div>
            </a>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥1000.00</span><span class="item-name">预计费用</span></div></div>
            <div class="list-divhead">简单描述您的需求</div>
            <div class="list-wrap"><div class="list-item"><input type="text" class="item-input-entry" placeholder="一句话描述您的需求"></div></div>
            <div class="list-divhead">说说您的具体要求</div>
            <div class="list-wrap"><div class="list-item"><textarea class="item-input-textarea" placeholder="一句话描述您的需求" rows="3"></textarea></div></div>
            <div class="list-divhead">时间设置</div>
            <a class="list-wrap">
                <div class="list-item in-icon-box"><i class="icon-wrap icon-arrow-right"></i>
                    <span class="item-name">预计到店时间</span>
                </div>
            </a>
        </div>
    </div>
    <div class="floor-module">
        <a class="floor-links links-type2" href="submitmiaosha.html">提交浏览</a>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</html>
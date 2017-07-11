<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>网红晒照</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="../../css/normalize.css">
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/marketingRank.css"/>
</head>
<body class="bg-color-01 padd-fill-tb">
    <div class="container-wrap">
        <div class="screenterm-module">
            <div class="sub-divhead">价格区间（元）</div>
            <div class="screen-item-wrap">
                <div class="prece-item screen-input-price"><span class="screen-item-clumn"><input type="text"/></span><span class="screen-item-clumn screen-item-mark">-</span><span class="screen-item-clumn"><input type="text"/></span></div>
                <div class="screen-item"><span class="screen-item-clumn screen-clumn-active"><strong>10-100</strong><em>24%的选择</em></span><span class="screen-item-clumn"><strong>10-100</strong><em>24%的选择</em></span><span class="screen-item-clumn"><strong>10-100</strong><em>24%的选择</em></span></div>
            </div>
            <div class="sub-divhead">粉丝量</div>
            <div class="screen-item-wrap">
                <div class="screen-item"><span class="screen-item-clumn screen-clumn-active"><strong>1万一下</strong></span><span class="screen-item-clumn"><strong>1万一下</strong></span><span class="screen-item-clumn"><strong>1万一下</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>1万一下</strong></span><span class="screen-item-clumn"><strong>1万一下</strong></span><span class="screen-item-clumn"><strong>1万一下</strong></span></div>
            </div>
            <div class="sub-divhead">地区</div>
            <div class="screen-item-wrap" id="district-module">
                <div class="screen-item"><span class="screen-item-clumn screen-clumn-active"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span><span class="screen-item-clumn"><strong>广州</strong></span></div>
            </div>
            <div class="sub-divhead">媒体性别</div>
            <div class="screen-item-wrap">
                <div class="screen-item"><span class="screen-item-clumn screen-clumn-active"><strong>不限</strong></span><span class="screen-item-clumn"><strong>男</strong></span><span class="screen-item-clumn"><strong>男</strong></span></div>
            </div>
            <div class="sub-divhead">好友圈子</div>
            <div class="screen-item-wrap">
                <div class="screen-item"><span class="screen-item-clumn screen-clumn-active"><strong>不限</strong></span><span class="screen-item-clumn"><strong>财经圈</strong></span><span class="screen-item-clumn"><strong>时尚圈</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>传媒圈</strong></span><span class="screen-item-clumn"><strong>财经圈</strong></span><span class="screen-item-clumn"><strong>时尚圈</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>传媒圈</strong></span><span class="screen-item-clumn"><strong>财经圈</strong></span><span class="screen-item-clumn"><strong>时尚圈</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>传媒圈</strong></span><span class="screen-item-clumn"><strong>财经圈</strong></span><span class="screen-item-clumn"><strong>时尚圈</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>传媒圈</strong></span><span class="screen-item-clumn"><strong>财经圈</strong></span><span class="screen-item-clumn"><strong>时尚圈</strong></span></div>
            </div>
            <div class="sub-divhead">媒体主年龄</div>
            <div class="screen-item-wrap">
                <div class="screen-item"><span class="screen-item-clumn screen-clumn-active"><strong>不限</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span></div>
                <div class="screen-item"><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span><span class="screen-item-clumn"><strong>20岁以下</strong></span></div>

            </div>
        </div>
    </div>
    <div class="floor-module">
        <div class="floor-links-col-2"><a href="javascript:;" class="floor-item btn-white" id="btn-reset">重置</a><a href="javascript:;" class="floor-item btn-gray" id="btn-submit">确定</a></div>
    </div>
</body>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript">
    $(function() {
        $(".screenterm-module").on('click','.screen-item .screen-item-clumn',function(){
            console.log($(this));
            $(this).parents(".screen-item-wrap").find(".screen-item .screen-item-clumn").siblings().removeClass("screen-clumn-active");
            $(this).addClass("screen-clumn-active");
        });

        $("#btn-reset").click(function(){
           $(".screenterm-module .screen-item .screen-clumn-active").filter(function(){
                $(this).removeClass("screen-clumn-active");
            });
        })
    });
</script>
</html>
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
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
    <link rel="stylesheet" href="${ctx}/css/component.css">

    <style type="text/css">
        .end{
            font-size: 12px;
            text-align: center;
            padding:2px 0;
            margin:3px 0;
        }
    </style>


</head>
<body class="bg-color-01 padd-fill-tb">
    <div class="container-wrap container-wrap-padding">
        <div class="sort-fixed-module col-sm-3">
           <span class="active"><a href="javascript:;">价格<i class="icon-wrap icon-arrow-b"></i></a></span>
           <span><a href="javascript:;">粉丝量<i class="icon-wrap icon-arrow-b"></i></a></span>
           <span><a href="javascript:;">筛选<i class="icon-wrap icon-arrow-b"></i></a></span>
        </div>
        <div class="content-slide-wrap wanghong-list-module">
            <a href="javascript:;" class="slide-item">
                <div class="item-links item-pad-100">
                    <div class="item-content">
                        <img class="item-content-img" src="../../imgs/activityRank/wanghong-img-01.jpg" alt="#"/>
                    </div>
                    <div class="item-content-wrap">
                        <div class="item-content-tit"><span class="item-content-name">权志龙 <i class="icon-wrap icon-age-man"></i></span></div>
                        <div class="item-content-address"><span class="item-content-name">上海<i class="icon-wrap icon-adress-03"></i></span></div>
                        <div class="item-content-circle"><span class="item-content-circle-text">好友圈子：传媒圈、名媛圈、模特圈...</span></div>
                        <div class="item-content-indent"><span class="item-content-indent-text">订单数量：50次</span></div>
                        <div class="item-content-data col-sm-3">
                            <div class="content-data-clumn"><strong>8697</strong><span>粉丝量</span></div>
                            <div class="content-data-clumn"><strong>￥500.00</strong><span>晒照报价</span></div>
                            <div class="content-data-clumn"><strong>￥1500.00</strong><span>分享报价</span></div>
                        </div>
                    </div>
                    <div class="item-posit-radio">
                        <input name="diyongradio" type="radio" class="reset-radio-input" id="markeingRadio-1">
                        <label class="reset-radio" for="markeingRadio-1"></label>
                    </div>
                </div>
            </a>
            <a href="javascript:;" class="slide-item">
                <div class="item-links item-pad-100">

                    <div class="item-content">
                        <img class="item-content-img" src="../../imgs/activityRank/wanghong-img-01.jpg" alt="#"/>
                    </div>
                    <div class="item-content-wrap">
                        <div class="item-content-tit"><span class="item-content-name">权志龙 <i class="icon-wrap icon-age-woman"></i></span></div>
                        <div class="item-content-address"><span class="item-content-name">上海<i class="icon-wrap icon-adress-03"></i></span></div>
                        <div class="item-content-circle"><span class="item-content-circle-text">好友圈子：传媒圈、名媛圈、模特圈...</span></div>
                        <div class="item-content-indent"><span class="item-content-indent-text">订单数量：50次</span></div>
                        <div class="item-content-data col-sm-3">
                            <div class="content-data-clumn"><strong>8697</strong><span>粉丝量</span></div>
                            <div class="content-data-clumn"><strong>￥500.00</strong><span>晒照报价</span></div>
                            <div class="content-data-clumn"><strong>￥1500.00</strong><span>分享报价</span></div>
                        </div>
                    </div>
                    <div class="item-posit-radio">
                        <input name="diyongradio" type="radio" class="reset-radio-input" id="markeingRadio-2">
                        <label class="reset-radio" for="markeingRadio-2"></label>
                    </div>
                </div>
            </a>
        </div>

    </div>
    <div class="floor-module">
        <a class="floor-links" href="javascript:;">我要晒照</a>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        var tabIndex = '' ;
        $(".slide-tab-wrap span").each(function(index,ele){
            if ( $(this).hasClass("active" )) {
                tabIndex = $(this).index();
                $(".swiper-container .swiper-wrapper .swiper-slide").siblings().hide();
                $(".swiper-container .swiper-wrapper .swiper-slide").eq(tabIndex).show()
                return false;
            }
        })
        $(".slide-tab-wrap span").click(function(){
            var conIndex= $(this).index();
            $(this).addClass("active").siblings().removeClass("active");
            $(".swiper-container .swiper-wrapper .swiper-slide").siblings().hide();
            $(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).show();
        })


        $(function(){
            // 请求查询网红列表数据
            $.post("${ctx}/h5/opinion/celebrity/list",function (data) {
                if(data.state == 0){
                    $.each(data.result, function(i,item){
                        alert(item.name);
                    });
                }else{
                    // 请求失败
                }
            });
        })
    })

</script>
</html>
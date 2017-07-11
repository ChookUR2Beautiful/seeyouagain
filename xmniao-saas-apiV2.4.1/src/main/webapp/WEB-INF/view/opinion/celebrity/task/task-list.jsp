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
        <div class="slide-tab-wrap slide-tab-fixed">
           <span class="active"><a href="javascript:;">进行中</a></span>
           <span><a href="javascript:;">已发布</a></span>
        </div>
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" id="menberbag-underway">
                    <div class="content-slide-wrap user-review-list">
                        <div class="slide-item">
                            <div class="item-links">
                                <div class="item-content item-pad-60">
                                    <img class="item-content-img" src="${ctx}/imgs/activityRank/user-img-01.png" alt="#"/>
                                    <div class="item-content-wrap">
                                        <div class="item-content-tit"><span class="item-content-money">￥1000.00</span><span class="item-content-name">权志龙 <i class="icon-wrap icon-age-man"></i></span></div>
                                        <div class="item-content-text col-sm-2"><span class="item-content-cost">晒照费用</span><span class="item-content-desc">要求面谈时间：2016.10.01</span></div>
                                    </div>
                                </div>
                                <div class="item-floor">
                                        <div class="item-floor-clumn">
                                            当前进度：接单 - 到店面谈 - 网红拍照 - 等待发布 - 发布成功
                                        </div>
                                </div>
                            </div>
                        </div>
                        <div class="slide-item">
                            <div class="item-links">
                                <div class="item-content item-pad-60">
                                    <img class="item-content-img" src="${ctx}/imgs/activityRank/user-img-01.png" alt="#"/>
                                    <div class="item-content-wrap">
                                        <div class="item-content-tit"><span class="item-content-money">￥1000.00</span><span class="item-content-name">权志龙 <i class="icon-wrap icon-age-man"></i></span></div>
                                        <div class="item-content-text col-sm-2"><span class="item-content-cost">分享费用</span><span class="item-content-desc">要求面谈时间：2016.10.01</span></div>
                                    </div>
                                </div>
                                <div class="item-floor">
                                    <div class="item-floor-clumn">
                                        当前进度：接单 - 到店面谈 - 网红拍照 - 等待发布 - 发布成功
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide" id="menberbag-end">
                    <div class="content-slide-wrap user-review-list">
                        <div class="slide-item">
                            <div class="item-links">
                                <div class="item-content item-pad-60">
                                    <img class="item-content-img" src="${ctx}/imgs/activityRank/user-img-01.png" alt="#"/>
                                    <div class="item-content-wrap">
                                        <div class="item-content-tit"><span class="item-content-money">￥1000.00</span><span class="item-content-name">权志龙 <i class="icon-wrap icon-age-man"></i></span></div>
                                        <div class="item-content-text col-sm-2"><span class="item-content-cost">晒照费用</span><span class="item-content-desc">要求面谈时间：2016.10.01</span></div>
                                    </div>
                                </div>
                                <div class="item-floor">
                                    <div class="item-floor-clumn">
                                        当前进度：接单 - 到店面谈 - 网红拍照 - 等待发布 - 发布成功
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="slide-item">
                            <div class="item-links">
                                <div class="item-content item-pad-60">
                                    <img class="item-content-img" src="${ctx}/imgs/activityRank/user-img-01.png" alt="#"/>
                                    <div class="item-content-wrap">
                                        <div class="item-content-tit"><span class="item-content-money">￥1000.00</span><span class="item-content-name">权志龙 <i class="icon-wrap icon-age-man"></i></span></div>
                                        <div class="item-content-text col-sm-2"><span class="item-content-cost">分享费用</span><span class="item-content-desc">要求面谈时间：2016.10.01</span></div>
                                    </div>
                                </div>
                                <div class="item-floor">
                                    <div class="item-floor-clumn">
                                        当前进度：接单 - 到店面谈 - 网红拍照 - 等待发布 - 发布成功
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="floor-module">
        <a class="floor-links" href="javascript:;">发起任务</a>
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
    })
</script>
</html>
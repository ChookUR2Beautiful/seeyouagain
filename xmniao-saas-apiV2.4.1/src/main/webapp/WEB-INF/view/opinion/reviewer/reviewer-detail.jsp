<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>食评名嘴</title>
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
    <div class="container-wrap">
        <div class="minzui-datails-head-module">
            <div class="minzui-head-img">
                <img src="${imgHost}${reviewer.avatar}" alt="#"/>
                <div class="minzui-head-img-text">${reviewer.name}</div>
            </div>
            <div class="minzui-head-data col-sm-2">
                <div class="minzui-head-clumn">
                    <strong>￥${reviewer.reviewPrice}</strong>
                    <span>食评费用</span>
                </div>
                <div class="minzui-head-clumn">
                    <strong>${reviewer.orderNum}</strong>
                    <span>食评次数</span>
                </div>
            </div>
        </div>
        <div class="sub-divhead">名嘴简介</div>
        <div class="minzui-content-info">
            <%--<div class="minzui-content-p">姜波，男，老北京旗人民俗美食家，国家级面点大师刘俊卿老先生入室弟子，高级点心技师，蜜供姜第五代传人，著名北京京剧表演艺术家吴吟秋先生之徒，中国烹饪学院会员，北京烹饪学院会员。</div>--%>
            <%--<div class="minzui-content-p">北京国际职业教育学校1994届烹饪专业毕业生，北京民俗美食家，故宫博物院紫禁城建福宫餐饮总监兼行政总厨。</div>--%>
            <div class="minzui-content-p">${reviewer.describe}</div>

        </div>
        <div class="sub-divhead">历史食评</div>
        <div class="minzui-history-wrap">
            <a href="javascript:;" class="minzui-content-wrap"><div class="item-content-picimg-wrap">
                <a href="${ctx}/h5/opinion/reviewer/article?articleId=${ariticle.id}">
                <img src="${imgHost}${ariticle.image}" alt=""/>
                </a>
                <div class="item-content-picimg-wrap-text"><span>${ariticle.sellerName  }</span></div>
                <div class="item-content-view-size">
                    <strong>${ariticle.views}</strong>
                    <span>人在看</span>
                </div>
            </div>
            <div class="item-content-picimg-desc">${ariticle.name}</div></a>
        </div>

    </div>
    <div class="floor-module">

        <a class="floor-links" href="${ctx}/h5/opinion/reviewer/order/submit?reviewerId=${reviewer.id}">我要食评</a>
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
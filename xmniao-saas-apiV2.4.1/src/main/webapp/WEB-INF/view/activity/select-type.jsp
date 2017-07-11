<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>选择促销优惠种类</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/swiper.css">
    <link rel="stylesheet" href="${ctx}/css/discount2.css?v=1.0"/>
      <link rel="stylesheet" href="${ctx}/css/component.css">
</head>
<body class="packer-body">
    <div class="container-wrap" style="height: 100%;">
        <div class="swiper-container marketing-slide" id="marketing-domule">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(${ctx}/imgs/activity/zhuanpan-ad@2x.png);"></div>
                        <div class="marke-info-text">
                            <img src="${ctx}/imgs/activity/zhuanpan_text_1.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>转盘抽奖活动强大奖品体系，支持实物，卡券，优惠券等多种奖品形式！</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <a class="links-btn" href="#" id="roullete">发布活动</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">看看使用效果</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(${ctx}/imgs/activity/changxin-ad@2x.png);"></div>
                        <div class="marke-info-text">
                            <img src="${ctx}/imgs/activity/zhuanpan_text_2.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>试吃促销通过消费者预先切身品尝产品，再结合终端导购人员介绍，迅速让消费者了解产品的独特卖点！</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <a class="links-btn" href="#" id="freetry">发布活动</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">看看使用效果</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(${ctx}/imgs/activity/miaosha-ad@2x.png);"></div>
                        <div class="marke-info-text">
                            <img src="${ctx}/imgs/activity/zhuanpan_text_3.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>通过分享限时秒杀互动，例如一元抢购，9.9秒杀等，提高消费者对店铺的认识！</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <a class="links-btn" href="#" id="kill">发布活动</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">看看使用效果</a>
                            </div>
                        </div>
                    </div>
                </div>
                 <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(${ctx}/imgs/revenue/jidian_ad_1.jpg);"></div>
                        <div class="marke-info-text">
                            <img src="${ctx}/imgs/revenue/jidian_text_1.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>针对本店会员创建集点活动，支付满额自动集点，集点满额后可兑换奖品，并重新循环集点，最终引导用户频繁消费!</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <a class="links-btn" href="promotion_list2.html" id="fcouspoints">发布活动</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">看看使用效果</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/swiper.min.js"></script>
<script src="${ctx}/js/component.js"></script>
<script type="text/javascript">
     function todo(){
        	Tips.show("敬请期待!");
        }

    $(function(){
        var mySwiper = new Swiper('.swiper-container', {
            // 如果需要分页器
            pagination: '.swiper-pagination',
            slidesPerView: 'auto',
            centeredSlides: true,
            paginationClickable: true
        });

        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || window.innerHeight;

        var clientWidth = document.body.clientWidth || document.documentElement.clientWidth || window.innerWidth;

        var widthPecent = 0.83;
        var curWidth = clientWidth * widthPecent * 0.88;
        var imgProportion = 600 / 1000;
        var imgHeight = curWidth / imgProportion;

        var initPadding = (clientHeight - imgHeight) / 2;
        
        $('.marke-operation').css({'position':'absolute','width': '100%','left': '0',bottom:(initPadding + 3)+'px'});

        var textMarTop = 230 * imgHeight / 1000;
        // $('.marke-info-wrap').css('marginTop',textMarTop + 'px');
        $('.swiper-contentbox').css('paddingTop',initPadding);

        /*20161111*/
        if( parseInt(clientWidth) / parseInt(clientHeight) > 0.70 ){
            $('.marke-info-text').css('marginTop','16px');
        }
        /*20161111 END*/
        
        $.post("${ctx}/h5/activity/record/has_activity","activityType=1",function(data,status){
        	if(data.state==0){
        		$("#freetry").text("发布活动");
        		$("#freetry").attr("href","${ctx}/h5/activity/freetry/input")
        	}else{
        		$("#freetry").text("进行中");
        		$("#freetry").attr("href","${ctx}/h5/activity/freetry/list")
        	}
        })
        
        $.post("${ctx}/h5/activity/record/has_activity","activityType=2",function(data,status){
        	if(data.state==0){
        		$("#roullete").text("发布活动");
        		$("#roullete").attr("href","${ctx}/h5/activity/roullete/input")
        	}else{
        		$("#roullete").text("进行中");
        		$("#roullete").attr("href","${ctx}/h5/activity/roullete/list")
        	}
        })
        
        $.post("${ctx}/h5/activity/record/has_activity","activityType=3",function(data,status){
        	if(data.state==0){
        		$("#kill").text("发布活动");
        		$("#kill").attr("href","${ctx}/h5/activity/kill/input")
        	}else{
        		$("#kill").text("进行中");
        		$("#kill").attr("href","${ctx}/h5/activity/kill/list")
        	}
        })
        
        
         $.post("${ctx}/h5/activity/record/has_activity","activityType=4",function(data,status){
        	if(data.state==0){
        		$("#fcouspoints").text("发布活动");
        		$("#fcouspoints").attr("href","${ctx}/h5/activity/fcouspoints/input");
        	}else{
        		$("#fcouspoints").text("进行中");
        		$("#fcouspoints").attr("href","${ctx}/h5/activity/fcouspoints/list");
        	}
        })
      
       
        
    });
    
    
</script>
</html>
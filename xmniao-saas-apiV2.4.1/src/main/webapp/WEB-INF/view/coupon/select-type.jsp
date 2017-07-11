<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择促销优惠种类</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/discount.css?v=1.0"/>
</head>
<body class="packer-body">
    <div class="container-wrap">
        <div class="swiper-container marketing-slide" id="marketing-domule">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(../../imgs/redpacket/xianjin-ad@2x.png);"></div>
                        <div class="marke-info-text">
                            <img src="../../imgs/activity/discount_text_1.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>可以创建现金抵用劵，创建后，商户可以自助发放给客户，促进客户消费，提高返单量。</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <a class="links-btn" href="#" onclick="createCash()">查看优惠</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">查看使用效果</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(../../imgs/redpacket/manjian-ad@2x.png);"></div>
                        <div class="marke-info-text">
                            <img src="../../imgs/activity/discount_text_2.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>创建满就减，通过消费达到限定金额后，可在支付中减免限定金额！</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <!-- <a class="links-btn" href="./form/fullcut.html">查看优惠</a> -->
                                <a class="links-btn" href="#" onclick="createFullcut()">查看优惠</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">查看使用效果</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="swiper-contentbox">
                        <div class="marke-img-wrap" style="background-image: url(../../imgs/redpacket/zengpin-ad@2x.png);"></div>
                        <div class="marke-info-text">
                            <img src="../../imgs/activity/discount_text_3.png">
                        </div>
                        <div class="marke-info-wrap">
                            <p>用户获得赠品券后，只需到店消费出示赠品券的二维码或6位编码进行验证即可免费获得赠品</p>
                        </div>
                        <div class="marke-operation">
                            <div class="marke-btn-wrap">
                                <!-- <a class="links-btn" href="promotion_list3.html">查看优惠</a> -->
                                <a class="links-btn" href="#" onclick="createFreebie()">查看优惠</a>
                            </div>
                            <div class="marke-links-wrap">
                                <a class="check-links" href="#">查看使用效果</a>
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
<script type="text/javascript">
    function createCash(){
    	window.location="${pageContext.request.contextPath}/h5/coupon/select?couponType=3";
    }
    function createFreebie(){
    	window.location="${pageContext.request.contextPath}/h5/coupon/select?couponType=4";
    }
    function createFullcut(){
    	window.location="${pageContext.request.contextPath}/h5/coupon/select?couponType=5";
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

        var textMarTop = 230 * imgHeight / 1000;
        // $('.marke-info-wrap').css('marginTop',textMarTop + 'px');
        $('.swiper-contentbox').css('paddingTop',initPadding);
        $('.marke-operation').css({'position':'absolute','width': '100%','left': '0',bottom:(initPadding + 3)+'px'});
        /*20161111*/
        if( parseInt(clientWidth) / parseInt(clientHeight) > 0.70 ){
            $('.marke-info-text').css('marginTop','16px');
        }
        /*20161111 END*/
    });
</script>
</html>
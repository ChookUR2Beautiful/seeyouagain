<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>满就减</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
</head>
<body class="bg-color-01 padd-fill-tb">
<input type="hidden" value="${type}" id="type"/>
     <div class="container-wrap container-wrap-padding">
        <div class="slide-tab-wrap slide-tab-fixed">
           <span class="active"><a href="javascript:;">进行中</a></span>
           <span><a href="javascript:;">已结束</a></span>
        </div>
        <div class="swiper-container" id="bag-list-module">
            <div class="swiper-wrapper">
                <div class="swiper-slide" id="menberbag-underway">
                    <div class="content-slide-wrap" id="div-start"></div>
                </div>
                <div class="swiper-slide" id="menberbag-end">
                    <div class="content-slide-wrap" id="div-end"></div>
                </div>
            </div>
        </div>

    </div>
    <div class="floor-module">
        <a class="floor-links" href="${pageContext.request.contextPath}/h5/coupon/input?couponType=${type}">创建满就减</a>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/baseUtil.js"></script>
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
    
    function detail(id){
		window.location="${pageContext.request.contextPath}/h5/coupon/detail?type=8&couponId="+id;
		
	}
    //加载列表数据
    $(function(){
    	var couponType = $("#type").val();
    	//进行中
    	$.get("${pageContext.request.contextPath}/h5/coupon/list_fullcut?status=1",function(data){
    		if(data.state==0){
    			if(data.result==undefined){
    				var str="<div class='emptydata'>";
    				str+="<i></i>";
					str+="<p>您当前没相关数据</p>";
					str+="</div>";
					$("#menberbag-underway").append(str);
    			}else{
	    			$.each(data.result, function(i, v) {
	    			var item = ["<div class='slide-item'>",
	    			"<a class='item-links' href='#' onclick='detail("+v.id+")'><div class='item-head'><span class='tit-desc'>",
	    			"<i class='icon-wrap icon-jian'></i>",v.cname,"</span></div>",
	                "<div class='item-content'><span class='content-size'><b>",v.joinNum,"</b><em>参与人数</em></span><span class='content-time'><b>",(v.joinNum*v.offsetAmount),"</b><em>累计减免</em></span></div>",
	                "<div class='item-floor'><span class='floor-limit'>",
	                "<em class='limit-sum'>",v.startTime,"-",v.endTime,"</em></span><span class='floor-time'><div class='more-links'><i></i><i></i><i></i></div>",
	                "</span></div></a><a class='icon-wrap share-links' href='javascript:;' id='"+v.id+"' value='"+v.cname+"' onclick='share(this,8)'></a>",
	                "</div>"].join("");
	    			//进行中
	    			$("#div-start").append(item);
	    			});
    			}
    		}else{
    			alert("加载数据异常！");
    		}
    	});
    	//已结束
    	$.get("${pageContext.request.contextPath}/h5/coupon/list_fullcut?status=0",function(data){
    		if(data.state==0){
    			if(data.result==undefined){
    				var str="<div class='emptydata'>";
    				str+="<i></i>";
					str+="<p>您当前没相关数据</p>";
					str+="</div>";
					$("#menberbag-end").append(str);
    			}else{
	    			$.each(data.result, function(i, v) {
	    			var item = ["<div class='slide-item'>",
	    			"<a class='item-links' href='#' onclick='detail("+v.id+")'><div class='item-head'><span class='tit-desc'>",
	    			"<i class='icon-wrap icon-jian-2'></i>",v.cname,"</span></div>",
	                "<div class='item-content'><span class='content-size'><b>",v.joinNum,"</b><em>参与人数</em></span><span class='content-time'><b>",(v.joinNum*v.offsetAmount),"</b><em>累计减免</em></span></div>",
	                "<div class='item-floor'><span class='floor-limit'>",
	                "<em class='limit-sum'>",v.startTime,"-",v.endTime,"</em><div class='more-links'><i></i><i></i><i></i></div>",
	                "</span></div></a><a class='icon-wrap share-links' href='#'></a>",
	                "</div>"].join("");
	                //已结束
	    			$("#div-end").append(item);
	    			});
    			}
    		}else{
    			alert("加载数据异常！");
    		}
    	});
    });
</script>
</html>
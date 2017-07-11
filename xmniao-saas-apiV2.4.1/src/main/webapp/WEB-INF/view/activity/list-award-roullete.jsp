<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>选择奖品</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css?v=1.0.0">
<link rel="stylesheet" href="${ctx}/css/marketing.css?v=1.0.0" />
     <link rel="stylesheet" href="${ctx}/css/component.css">

</head>
<body class="bg-color-01 padd-fill-tb">
<form id="listawary" action="${ctx}/h5/activity/roullete/input" method="post">
	<input type="hidden" name="awardProportion" value="${roullete.awardProportion}"/>
	<input type="hidden" name="beginDate" value="<fmt:formatDate value='${roullete.beginDate}' type="date" dateStyle="medium"/> "/>
	<input type="hidden" id="endDate" name="endDate" value="<fmt:formatDate value='${roullete.endDate}' type="date" dateStyle="medium"/>" />
	<input type="hidden" name="name" value="${roullete.name}"/>
	<input type="hidden" name="prizeDrawNumber" value="${roullete.prizeDrawNumber}"/>
	<input type="hidden" name="setCondition" value="${roullete.setCondition}"/>
	<input type="hidden" name="limitNumber" value="${roullete.limitNumber}"/>
	<div class="container-wrap container-wrap-padding">
		<div class="slide-tab-wrap sm-col-3 slide-tab-fixed">
			<span class="active" ><a href="javascript:;">红包<sub ${fn:length(redpackets)>0?"class='tab-show'":""} >${fn:length(redpackets)}</sub></a></span>
			<span><a href="javascript:;" >抵用券<sub  ${fn:length(diyongCoupons)>0?"class='tab-show'":""} >${fn:length(diyongCoupons)}</sub></a></span> 
			<span><a href="javascript:;">赠品券<sub ${fn:length(zhengpinCoupons)>0?"class='tab-show'":""} >${fn:length(zhengpinCoupons)}</sub></a></span>
		</div>
		<div class="swiper-container reset-swiper" id="bag-list-module">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div class="content-slide-wrap">
						<c:forEach items="${redpackets}" var='redpacket'>
							<div class="slide-item">
								<div class="item-links" href="promotion_datadetails1.html">
									<div class="item-head">
										<span class="tit-desc"><i class="icon-wrap icon-xiang"></i>${redpacket.redpacketName}</span>
									</div>
									<div class="item-content">
										<span class="content-size"> <b>${redpacket.getRedpacketNumber}</b>
											<em>领取个数</em>
										</span> <span class="content-time"> <b>${redpacket.views}</b>
											<em>浏览次数</em>
										</span>
									</div>
									<div class="item-floor">
										<span class="floor-limit"> <em class="limit-total">生成总额：${redpacket.totalAmount}</em>
											<em class="limit-sum">剩余总额：${redpacket.totalAmount-redpacket.getRedpacket}</em>
										</span> <span class="floor-time"> <em class="time-date"> <fmt:formatDate
													value="${redpacket.beginDate}" pattern="yyyy.MM.dd" />-<fmt:formatDate
													value="${redpacket.endDate}" pattern="yyyy.MM.dd" /></em>
										</span>
									</div>
									<div class="set-btn-wrap sm-col-3">
										<span class="set-name">数量：</span>
										<input type="hidden"
											id="awardState"  /> <input
											type="hidden" id="cid" value="${redpacket.id}" />
											<input type="hidden" id="couponType" value="${redpacket.couponType}"/> 
											<input type="hidden" id="couponName" value="${redpacket.redpacketName}"/>
											<input type="hidden" id="couponEndDate" value="<fmt:formatDate value="${redpacket.endDate}" pattern="yyyy-MM-dd" />"/> 
										<a href="javascript:;" name="setAward"
											class="btn-item set-item">设置为奖品</a>
										<span class="set-input"><input type="text"
											id="sendNum" value="" placeholder="" onkeyup="sendNumOnkeyup(this,100000)"/></span>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
				<c:if test="${empty redpackets}">
	                        <div class="emptydata">
									<i></i>
									<p>您当前没相关数据</p>
							</div>
					   </c:if>	
				<div class="swiper-slide">
					<div class="content-slide-wrap">
						<c:forEach items="${diyongCoupons}" var='diyongCoupon'>
							<div class="slide-item">
								<div class="item-links" href="promotion_datadetails1.html">
									<div class="item-head">
										<span class="tit-desc"><i class="icon-wrap icon-xian"></i>${diyongCoupon.cname}</span>
									</div>
									<div class="item-content">
										<span class="content-size"> <b>${diyongCoupon.sendNum}</b>
											<em>领取个数</em>
										</span> <span class="content-time"> <b>${diyongCoupon.useNumber}</b>
											<em>使用次数</em>
										</span>
									</div>
									<div class="item-floor">
										<span class="floor-limit"> <em class="limit-total">生成总数：${diyongCoupon.maximum}</em>
											 <em class="limit-sum">剩余数量：${diyongCoupon.maximum-diyongCoupon.sendNum}</em>
										</span> <span class="floor-time"><em class="time-date"> ${fn:substring(diyongCoupon.startDate, 0, 10)}-${fn:substring(diyongCoupon.endDate, 0, 10)}</em>
										</span>
									</div>
									<div class="set-btn-wrap sm-col-3">
										<span class="set-name">数量：</span> <input type="hidden"
											id="awardState" value="${diyongCoupon.awardState}" /> <input
											type="hidden" id="cid" value="${diyongCoupon.cid}" />
											<input type="hidden" id="couponType" value="${diyongCoupon.couponType}"/> 
											<input type="hidden" id="couponName" value="${diyongCoupon.cname}"/> 
											<input type="hidden" id="couponEndDate" value="${fn:substring(diyongCoupon.endDate, 0, 10)}"/>
										<a href="javascript:;" name="setAward"
											class="btn-item set-item">设置为奖品</a>
										<span class="set-input"><input type="text"
											id="sendNum" value="" placeholder="" onkeyup="sendNumOnkeyup(this,${diyongCoupon.maximum-diyongCoupon.sendNum})"/></span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<c:if test="${empty diyongCoupons}">
	                        <div class="emptydata">
									<i></i>
									<p>您当前没相关数据</p>
							</div>
					   </c:if>	
				</div>
				<div class="swiper-slide">
					<div class="content-slide-wrap">
						<c:forEach items="${zhengpinCoupons}" var='zhengpinCoupon'>
							<div class="slide-item">
								<div class="item-links" href="promotion_datadetails1.html">
									<div class="item-head">
										<span class="tit-desc"><i class="icon-wrap icon-zeng"></i>${zhengpinCoupon.cname}</span>
									</div>
									<div class="item-content">
										<span class="content-size"> <b>${zhengpinCoupon.sendNum}</b>
											<em>领取个数</em>
										</span> <span class="content-time"> <b>${zhengpinCoupon.useNumber}</b>
											<em>使用次数</em>
										</span>
									</div>
									<div class="item-floor">
										<span class="floor-limit"> <em class="limit-total">生成总数：${zhengpinCoupon.maximum}</em>
											 <em class="limit-sum">剩余数量：${zhengpinCoupon.maximum-zhengpinCoupon.sendNum}</em>
										</span> <span class="floor-time"><em class="time-date"> ${fn:substring(zhengpinCoupon.startDate, 0, 10)}-${fn:substring(zhengpinCoupon.endDate, 0, 10)}</em>
										</span>
									</div>
									<div class="set-btn-wrap sm-col-3">
										<span class="set-name">数量：</span> <input type="hidden"
											id="awardState" value="${zhengpinCoupon.awardState}" /> <input
											type="hidden" id="cid" value="${zhengpinCoupon.cid}" />
											<input type="hidden" id="couponType" value="${zhengpinCoupon.couponType}"/> 
											<input type="hidden" id="couponName" value="${zhengpinCoupon.cname}"/> 
											<input type="hidden" id="couponEndDate" value="${fn:substring(zhengpinCoupon.endDate, 0, 10)}"/>
										<a href="javascript:;" name="setAward"
											class="btn-item set-item">设置为奖品</a>
										<span class="set-input"><input type="text"
											id="sendNum" value="" placeholder="" contenteditable="false" onkeyup="sendNumOnkeyup(this,${zhengpinCoupon.maximum-zhengpinCoupon.sendNum})"/></span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<c:if test="${empty zhengpinCoupons}">
	                        <div class="emptydata">
									<i></i>
									<p>您当前没相关数据</p>
							</div>
					   </c:if>	
				</div>
			</div>
		</div>
 
	</div>
	</form>
	<div class="floor-module">
		<div class="floor-links-col-2">
			<a href="javascript:;" class="floor-item btn-white" id="btn-found" >创建红包</a><a
				href="javascript:;" class="floor-item btn-gray" id="awardSubmit">提交
			</a>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
 <script type="text/javascript" src="${ctx}/js/activity/common.js"></script>
<script type="text/javascript">
	var minSum = 6;
	var maxSum = 6;
	$(function(){
		var tabIndex = '' ;
        $(".slide-tab-wrap span").each(function(index,ele){
            if ( $(this).hasClass("active" )) {
                tabIndex = $(this).index();
                var indexText =   $(this).text();
                $(".swiper-container .swiper-wrapper .swiper-slide").siblings().removeClass("swiper-slide-active").hide();
                $(".swiper-container .swiper-wrapper .swiper-slide").eq(tabIndex).addClass("swiper-slide-active").show()
                $("#btn-found").html('创建'+indexText);
               
                return false;
            }
        })
        $(".slide-tab-wrap span").click(function(){
            var conIndex= $(this).index();
            var indexText =   $(this).text();
            $(this).addClass("active").siblings().removeClass("active");
            $(".swiper-container .swiper-wrapper .swiper-slide").siblings().removeClass("swiper-slide-active").hide();
            $(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).addClass("swiper-slide-active").show();
            $("#btn-found").html('创建'+indexText);
            if(indexText.indexOf("红包")>=0){
				$("#btn-found").text("创建红包").val(5);
				}else if(indexText.indexOf("抵用券")>=0){
				$("#btn-found").text("创建抵用券").val(3);
				}else if(indexText.indexOf("赠品券")>=0){
				$("#btn-found").text("创建赠品券").val(4);
			}
        })
	
		$("#btn-found").text("创建红包").val(5);
		//对象数组转成js数组
		var arr=${roullete!=null?roullete.sellerCouponDetailsJson:""};
		if(arr!=null){
			$.each(arr,function(i,item){
				var input = $("input[id='cid'][value="+arr[i].id+"]");
				var par = $(input).parent();
				if(par.find("#couponType").val()==arr[i].awardType){
					par.find("#sendNum").val(arr[i].amount);
					par.find("[name=setAward]").trigger("click");
				}
			});
		}
		
		
		
	
	});
	
	
	$("#btn-found").bind("click",function(){
		var awardType = $(this).val();
		if(awardType==5){
			var coupon=$("#couponType[value=5]");
			if(coupon.length){
				Tips.show("已有抽奖红包,不能再创建");
				return;
			}
		}	
		$("#listawary").attr("action","${ctx}/h5/activity/roullete/input_award?awardType="+awardType).submit();
	});
	
	
	
</script>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>集邮免单活动</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/css/marketingRank.css" />
</head>

<body>
	<input type="hidden" id="endDate" name="endDate" value="<fmt:formatDate value='${endDate}' type="date" dateStyle="medium"/>" />
	<div class="container-wrap draw-slide-module" id="">
		<div class="slide-tab-wrap sm-col-2">
			<span class="active"><a href="javascript:;"
				data-slide="diyongjuan">抵用券<sub
					${fn:length(diyongCoupons)>0?"class='tab-show'":""}>${fn:length(diyongCoupons)}</sub></a></span>
			<span><a href="javascript:;" data-slide="zengpin">赠品券<sub
					${fn:length(zhengpinCoupons)>0?"class='tab-show'":""}>${fn:length(zhengpinCoupons)}</sub></a></span>
		</div>
		<div class="swiper-container reset-swiper draw-list-content">

			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div class="content-slide-wrap">
						<c:forEach items="${diyongCoupons}" var='diyongCoupon' >
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
											<em class="time-date">
												${fn:substring(diyongCoupon.startDate, 0, 10)}-${fn:substring(diyongCoupon.endDate, 0, 10)}</em>
										</span> <span class="floor-time"><em class="limit-sum">剩余数量：${diyongCoupon.maximum-diyongCoupon.sendNum}</em>
										</span>
									</div>
									<div class="item-posit-radio">
										<input name="diyongradio" type="radio"
											class="reset-radio-input" id="markeingRadio-${diyongCoupon.cid}" /> <label
											class="reset-radio" for="markeingRadio-${diyongCoupon.cid}"></label> <input
											type="hidden" id="awardState"
											value="${diyongCoupon.awardState}" /> <input type="hidden"
											id="cid" value="${diyongCoupon.cid}" /> <input type="hidden"
											id="couponType" value="${diyongCoupon.couponType}" /> <input
											type="hidden" id="couponName" value="${diyongCoupon.cname}" />
										<input type="hidden" id="sendNum"
											value="${diyongCoupon.maximum-diyongCoupon.sendNum}" />
											 <input
											type="hidden" id="couponEndDate"
											value="${fn:substring(diyongCoupon.endDate, 0, 10)}" />
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
											<em class="time-date">
												${fn:substring(zhengpinCoupon.startDate, 0, 10)}-${fn:substring(zhengpinCoupon.endDate, 0, 10)}</em>
										</span> <span class="floor-time"><em class="limit-sum">剩余数量：${zhengpinCoupon.maximum-zhengpinCoupon.sendNum}</em>
										</span>
									</div>
									<div class="item-posit-radio">
										<input name="diyongradio" type="radio"
											class="reset-radio-input" id="mark-zenpinRadio-${zhengpinCoupon.cid}" /> <label
											class="reset-radio" for="mark-zenpinRadio-${zhengpinCoupon.cid}"></label> <input
											type="hidden" id="awardState"
											value="${zhengpinCoupon.awardState}" /> <input type="hidden"
											id="cid" value="${zhengpinCoupon.cid}" /> <input
											type="hidden" id="couponType"
											value="${zhengpinCoupon.couponType}" /> <input type="hidden"
											id="couponName" value="${zhengpinCoupon.cname}" /> <input
											type="hidden" id="couponEndDate"
											value="${fn:substring(zhengpinCoupon.endDate, 0, 10)}" />
											<input type="hidden" id="sendNum"
											value="${zhengpinCoupon.maximum-zhengpinCoupon.sendNum}" />
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
	<div class="floor-module">
		<div class="floor-links-col-2">
			<a href="javascript:;" class="floor-item btn-white" id="btn-found" >创建抵用券</a><a
				href="javascript:;" class="floor-item btn-gray" id="btn-submit">提交
			</a>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript" src="${ctx}/js/activity/common.js"></script>
<script type="text/javascript">
	var minSum = 1;
	var maxSum = 1;
	$("#btn-found").val(3);
	$(function(){
        $(".slide-tab-wrap span").each(function(index,ele){
            if ( $(this).hasClass("active" )) {
                tabIndex = $(this).index();
                var indexText =   $(this).text();
                $(".swiper-container .swiper-wrapper .swiper-slide").siblings().removeClass("swiper-slide-active").hide();
                $(".swiper-container .swiper-wrapper .swiper-slide").eq(tabIndex).addClass("swiper-slide-active").show()
                return false;
            }
        })
		$(".slide-tab-wrap span").click(function() {
		var conIndex = $(this).index();
		var indexText = $(this).text();
		$(this).addClass("active").siblings().removeClass("active");
		$(".swiper-container .swiper-wrapper .swiper-slide").siblings().removeClass("swiper-slide-active").hide();
		$(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).addClass("swiper-slide-active").show();
		$("#btn-found").html('创建' + indexText);
		if (indexText.indexOf("红包") >= 0) {
			$("#btn-found").text("创建红包").val(5);
		}
		else if (indexText.indexOf("抵用")>= 0) {
			$("#btn-found").text("创建抵用券").val(3);
		}
		else if (indexText.indexOf("赠品券") >= 0) {
			$("#btn-found").text("创建赠品券").val(4);
		}
		})
		
		$("input[name='diyongradio']").on("click",function(){
		var activityEndDate=$("#endDate").val()+" 23:59:00";
		var endDate = $(this).parent().find("#couponEndDate").val()+" 23:59:00";
		if(new Date(endDate).getTime()<new Date(Date.parse(activityEndDate.replace(/-/g, "/")))){
			$(this).removeAttr("checked");
			Tips.show("奖品结束时间必须大于活动时间");
			return;
		}else{
			 $(this).attr("checked","checked");
			$("#btn-submit").attr("class","floor-item btn-blue");
		}
	});
		
		var arr=JSON.parse('${awardRelation}');
		if(arr){
				var input = $("input[id='cid'][value="+arr.awardId+"]");
				var par = $(input).parent();
				if(par.find("#couponType").val()==arr.awardType){
					//par.find("input[name='diyongradio']").trigger("click");
					var activityEndDate=$("#endDate").val()+" 23:59:00";
					var endDate =par.find("#couponEndDate").val()+" 23:59:00";
					if(new Date(endDate).getTime()<new Date(Date.parse(activityEndDate.replace(/-/g, "/")))){
						par.find("input[name='diyongradio']").removeAttr("checked");
						Tips.show("奖品结束时间必须大于活动时间");
						return;
					}else{
						 par.find("input[name='diyongradio']").attr("checked","checked");
						$("#btn-submit").attr("class","floor-item btn-blue");
					}
				}
		}
		
	})	
	
	
	$("#btn-found").on("click",function(){
		var awardType = $("#btn-found").val();
		myPost("${ctx}/h5/activity/fcouspoints/input_award",{"awardType":awardType});
	});
	
	
	$("#btn-submit").bind("click",function(){
		var awardState=$("input[name='diyongradio']:checked");
		var obj;
		if(awardState.length>0){
				var par = awardState.parent();
				var awardId= par.find("#cid").val();
				var amount=par.find("#sendNum").val();
				var awardType=par.find("#couponType").val();
				var awardName=par.find("#couponName").val();
				var couponEndDate=par.find("#couponEndDate").val();
				obj={"awardId":awardId,"amount":amount,"awardType":awardType,"awardName":awardName,"couponEndDate":couponEndDate};
		}else{
			Tips.show("请选择奖品");
			return;
		}
		myPost("${ctx}/h5/activity/fcouspoints/input",obj);
	});
	
	
</script>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head >
    <meta charset="UTF-8">
    <title>选择奖品</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/swiper.css">
    <link rel="stylesheet" href="${ctx}/css/marketing.css">
     <link rel="stylesheet" href="${ctx}/css/component.css">
</head>
<body class="bg-color-01 padd-fill-tb">
<form id="listawary" action="${ctx}/h5/activity/freetry/input" method="post">
	<input type="hidden" name="awardProportion" value="${freetry.awardProportion}"/>
	<input type="hidden" name="beginDate" value="<fmt:formatDate value='${freetry.beginDate}' type="date" dateStyle="medium"/> "/>
	<input type="hidden" id="endDate" name="endDate" value="<fmt:formatDate value='${freetry.endDate}' type="date" dateStyle="medium"/>" />
	<input type="hidden" name="name" value="${freetry.name}"/>
	<input type="hidden" name="prizeDrawNumber" value="${freetry.prizeDrawNumber}"/>
	<input type="hidden" name="setCondition" value="${freetry.setCondition}"/>
	<input type="hidden" name="limitNumber" value="${freetry.limitNumber}"/>
    <div class="container-wrap">
        <div class="list-details-module"  id="bag-list-module">
            <div class="content-slide-wrap" id="menberbag-underway">
            <c:forEach items="${sellerCouponDetails}" var='sellerCouponDetail'>
                <div class="slide-item">
                    <div class="item-links" href="promotion_datadetails1.html">
                        <div class="item-head">
                            <span class="tit-desc"><i class="icon-wrap icon-zeng"></i>${sellerCouponDetail.cname}</span>
                        </div>
                        <div class="item-content">
                                    <span class="content-size">
                                        <b>${sellerCouponDetail.sendNum}</b>
                                        <em>领取个数</em>
                                    </span>
                                    <span class="content-time">
                                        <b>${sellerCouponDetail.useNumber}</b>
                                        <em>使用次数</em>
                                    </span>
                        </div>
                        <div class="item-floor">
                                    <span class="floor-limit">
                                        <em class="limit-total">生成总数：${sellerCouponDetail.maximum}</em>
                                       <em class="time-date"> ${fn:substring(sellerCouponDetail.startDate, 0, 10)}-${fn:substring(sellerCouponDetail.endDate, 0, 10)}</em>
                                    </span>
                                    <span class="floor-time">
                                         <em class="limit-sum">剩余数量：${sellerCouponDetail.maximum-sellerCouponDetail.sendNum}</em>
                                    </span>
                        </div>
                        <div class="set-btn-wrap sm-col-3">
                            <span class="set-name">数量：</span>
                            <input type="hidden" id="awardState" value="${sellerCouponDetail.awardState}"/>
                            <input type="hidden" id="cid"  value="${sellerCouponDetail.cid}"/>
                            <input type="hidden" id="couponType" value="${sellerCouponDetail.couponType}"/> 
                            <input type="hidden" id="couponName" value="${sellerCouponDetail.cname}"/> 
                        	<input type="hidden" id="couponEndDate" value="${fn:substring(sellerCouponDetail.endDate, 0, 10)}"/>
                           <a href="javascript:;" name="setAward"   class="btn-item set-item"  >设置为奖品</a>
                            <span class="set-input"><input type="text" id="sendNum" value=""  placeholder="" onkeyup="sendNumOnkeyup(this,${sellerCouponDetail.maximum-sellerCouponDetail.sendNum})"/></span>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <c:if test="${empty sellerCouponDetails}">
	                        <div class="emptydata">
									<i></i>
									<p>您当前没相关数据</p>
							</div>
					   </c:if>	
    </div>
   </form>
    <div class="floor-module">
        <div class="floor-links-col-2"><a href="javascript:;" class="floor-item btn-white" id="createCoupon">创建赠品券</a><a href="javascript:;" class="floor-item btn-gray" id="awardSubmit">提交</a></div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.0.min.js"></script>
 <script type="text/javascript" src="${ctx}/js/component.js"></script>
 <script type="text/javascript" src="${ctx}/js/activity/common.js"></script>
<script type="text/javascript">
	var minSum = 1;
	var maxSum = 6;
	
	$(function(){
		//对象数组转成js数组
		var arr=${freetry!=null?freetry.sellerCouponDetailsJson:""};
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
	
	$("#createCoupon").on("click",function(){
		$("#listawary").attr("action","${ctx}/h5/activity/freetry/input_award").submit();
	});
	
	
	
</script>
</html>
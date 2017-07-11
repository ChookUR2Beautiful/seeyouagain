<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据详情</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
<link rel="stylesheet" href="${ctx}/css/component.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
	<input type="hidden" value="${couponId}" id="couponId"/>
	<input type="hidden" value="${type}" id="type"/>
	<input type="hidden" value="${detailData.cname}" id="cname"/>
    <div class="container-wrap">
        <div class="datadetails-info-module">
            <div class="datadetails-info-wrap">
                <div class="datadetails-ht">
                    <strong><em>￥</em>${detailData.activeAmount}</strong>
                    <span>刺激消费</span>
                </div>
                <div class="datadetails-hb con-col-3">
                	<c:if test="${type==6||type==7}">
            			<span><strong id="awardNum">${detailData.sendNum}</strong><font>获取次数</font></span>
            		</c:if>
            		<c:if test="${type==8}">
            			<span><strong id="joinNum">${detailData.joinNum}</strong><font>获取次数</font></span>
            		</c:if>
                    <span><strong id="newUserNum">${detailData.newUserNum}</strong><font>新会员数</font></span>
                    <span><strong id="viewNum">${detailData.viewNum}</strong><font>曝光次数</font></span>
                </div>
            </div>
        </div>
        <div class="activity-module">
            <div class="activity-item">
            	<c:if test="${type==6}">
            		<i class="icon-wrap icon-xian"></i>
            	</c:if>
                <c:if test="${type==7}">
            		<i class="icon-wrap icon-zeng"></i>
            	</c:if>
            	<c:if test="${type==8}">
            		<i class="icon-wrap icon-jian"></i>
            	</c:if>
               	 活动已进行<span id="passedDays">${detailData.passedDays}</span>
               	 天，距结束<span id="lastDays">${detailData.lastDays}</span>天~
            </div>
        </div>
        <div class="list-default-module"> 	
            <a class="list-item item-icon-right" href="${pageContext.request.contextPath}/h5/coupon/preview?type=${type-3}&couponId=${id}">
            	<c:if test="${type==6}">
            		现金抵用券明细 
            	</c:if>
            	<c:if test="${type==7}">
            		赠品券明细 
            	</c:if>
            	<c:if test="${type==8}">
            		满就减明细 
            	</c:if>
            	<i class="icon-wrap icon-arrow-right"></i>
            </a>
            <c:if test="${type==6}">
            	<a class="list-item item-icon-right" href="${pageContext.request.contextPath}/h5/coupon/preview?type=${type}&couponId=${id}">
            	领取明细 <i class="icon-wrap icon-arrow-right"></i>
            </a>
            </c:if>
            <c:if test="${type==7}">
            	<a class="list-item item-icon-right" href="${pageContext.request.contextPath}/h5/coupon/preview?type=${type}&couponId=${id}">
            	领取明细 <i class="icon-wrap icon-arrow-right"></i>
            </a>
            </c:if>
            <c:if test="${type==8}">
            	<a class="list-item item-icon-right" href="${pageContext.request.contextPath}/h5/coupon/preview?type=${type}&couponId=${id}">
            	领取明细 <i class="icon-wrap icon-arrow-right"></i>
            </a>
            </c:if>
        </div>
    </div>
    <c:if test="${isShow!=1}">
    <div class="floor-module" id="detail-floor-ios">
        <div class="floor-links-col-2">
        <a href="javascript:;" class="floor-item btn-white" id="stop">终止活动</a>
        <!-- 6现金抵用券 7赠品券 8满减送 -->
        <a class="floor-item btn-blue" href="${pageContext.request.contextPath}/api/v1/common/download_material?type=${type }&id=${id}" id="fullcut-metarial">下载宣传物料</a>
        </div>
    </div>
      </c:if>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript">
	$(function(){
		//设置文件title
		document.title = $("#cname").val();
		var type = $("#type").val();
		var id = $("#couponId").val();
		$("").on('click',function(){
			console.log(1111)
	
		})
		//终止活动
		$("#stop").click(function(){
			new dialog({
				content:'确定终止活动？',
				buttonNum:2,
				firstBtnText:'取消',
				firstBtnEvent:function(){
				},
				secondBtnText:'确定',
				secondBtnEvent:function(){
					$.ajax({
	        			url: "${pageContext.request.contextPath}/h5/coupon/stop?id=${id}&type=${type}",
	        			type: "post",
	        			success:function(data){
	        				if(data.state == 0){
	        					window.location= basePath + "/h5/coupon/select?couponType=${type-3}";
	        				}else{
	        					 Tips.show(data.info);
	        				}
	        			}
	        		}); 	
				}
			});
		});
	})
</script>
</html>
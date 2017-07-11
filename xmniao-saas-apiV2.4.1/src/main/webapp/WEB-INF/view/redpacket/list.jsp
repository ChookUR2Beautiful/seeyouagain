<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员红包</title>
<meta name="renderer" content="webkit"> 
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/baseUtil.js"></script>

<style>
.end {
	font-size: 12px;
	text-align: center;
	padding: 2px 0;
	margin: 3px 0;
}
</style>
</head>
<body class="bg-color-01 padd-fill-tb">
     <div class="container-wrap container-wrap-padding">    
          <div class="slide-tab-wrap slide-tab-fixed">
	           <span class="active"><a href="javascript:void(0);" data-slide="jinxing">进行中</a></span>
	           <span><a href="javascript:void(0);" data-slide="jiesu">已结束</a></span>
          </div>
        
          <div class="swiper-container" id="bag-list-module">
            <div class="swiper-wrapper">
                <!-- 进行中 -->
                <div class="swiper-slide" id="menberbag-underway">
                    <div class="content-slide-wrap">
                        <c:forEach items="${requestScope.data}" var="redpacketIng">
                           <c:if  test="${!empty redpacketIng  && (redpacketIng.status eq 1 || redpacketIng.status eq 2  || redpacketIng.status eq 3)}">
                            <input type="hidden" id="totalCountIng" value="${requestScope.totalCountIng}"/>
	                        <div class="slide-item">
	                            <a class="item-links" href="javascript:void(0)" onclick="detailData(${redpacketIng.sellerid},${redpacketIng.redpacketId},${redpacketIng.redpacketType},${redpacketIng.status})">
	                                <div class="item-head">
	                                    <span class="tit-desc">
	                                       <c:choose>
												<c:when test="${redpacketIng.redpacketType eq 0}">
													<i class="icon-wrap icon-xiang"></i>
												</c:when>
												<c:when test="${redpacketIng.redpacketType eq 1}">
													<i class="icon-wrap icon-xian-3"></i>
												</c:when>
												<c:when test="${redpacketIng.redpacketType eq 2}">
													<i class="icon-wrap icon-jian"></i>
												</c:when>
												<c:when test="${redpacketIng.redpacketType eq 3}">
													<i class="icon-wrap icon-tui"></i>
												</c:when>
												<c:when test="${redpacketIng.redpacketType eq 4}">
													<i class="icon-wrap icon-chou"></i>
												</c:when>
												<c:otherwise>
												
												</c:otherwise>
										   </c:choose> 
	                                       ${redpacketIng.redpacketName}
	                                    </span>
	                                </div>
	                                <div class="item-content">
	                                    <span class="content-size">
	                                        <b> ${redpacketIng.recordNumber}</b>
	                                        <em>领取个数</em>
	                                    </span>
	                                    <span class="content-time">
	                                        <b>
	                                            <c:choose>
													<c:when test="${!empty redpacketIng.views}">
														${redpacketIng.views}
													</c:when>
													<c:otherwise>
														0
													</c:otherwise>
												</c:choose> 
	                                        </b>
	                                        <em>浏览次数</em>
	                                    </span>
	                                </div>
	                                <div class="item-floor">
	                                    <span class="floor-limit">
	                                        <em class="limit-total">红包总额：￥${redpacketIng.totalAmount}</em>
	                                        <em class="limit-sum">
	                                                                                                                                剩余金额：￥
	                                            <c:choose> 
													<c:when test="${!empty redpacketIng.chargeBalance}">
														${redpacketIng.chargeBalance}
													</c:when>
													<c:otherwise>
													
													</c:otherwise>
										        </c:choose> 
	                                        </em>
	                                    </span>
	                                    <span class="floor-time">
	                                        <em class="time-date">${redpacketIng.beginDate}-${redpacketIng.endDate}</em>
	                                        <div class="more-links"><i></i><i></i><i></i></div>
	                                    </span>
	                                </div>
	                            </a>
	                            <!-- 分享功能 -->
<%--								share('${redpacketIng.redpacketName}',${redpacketIng.redpacketId},${redpacketIng.redpacketType},${redpacketIng.status})--%>
	                            <a id='${redpacketIng.redpacketId}' value='${redpacketIng.redpacketName}'
								   class="icon-wrap share-links"
								href="javascript:void(0)"
								   onclick="share(this,${redpacketIng.downType})"></a>
	                        </div>
	                       </c:if>
                       </c:forEach>
                       <c:if test="${!empty requestScope.totalCountIng && requestScope.totalCountIng eq 0}">
	                        <div class="emptydata">
									<i></i>
									<p>您当前没相关数据</p>
							</div>
					   </c:if>	
                    </div>
                </div>
                <!-- 进行中 --> 
                
                <!-- 已结束 -->
                <div class="swiper-slide" id="menberbag-end">
                    <div class="content-slide-wrap">
                      <c:forEach items="${requestScope.data}" var="redpacketEnd">
                        <c:if  test="${!empty redpacketEnd  &&  redpacketEnd.status eq  0}">
                            <input type="hidden" id="totalCountEnd" value="${requestScope.totalCountEnd}"/>
	                        <div class="slide-item">
	                            <a class="item-links" href="javascript:void(0)" onclick="detailData(${redpacketEnd.sellerid},${redpacketEnd.redpacketId},${redpacketEnd.redpacketType},${redpacketEnd.status})">
	                                <div class="item-head">
	                                    <span class="tit-desc">
	                                        <c:choose>
													<c:when test="${redpacketEnd.redpacketType eq 0}">
														<i class="icon-wrap icon-xiang-2"></i>
													</c:when>
													<c:when test="${redpacketEnd.redpacketType eq 1}">
														<i class="icon-wrap icon-xian-03"></i>
													</c:when>
													<c:when test="${redpacketEnd.redpacketType eq 2}">
														<i class="icon-wrap icon-jian-2"></i>
													</c:when>
													<c:when test="${redpacketEnd.redpacketType eq 3}">
														<i class="icon-wrap icon-tui-2"></i>
													</c:when>
													<c:when test="${redpacketEnd.redpacketType eq 4}">
														<i class="icon-wrap icon-chou-2"></i>
													</c:when>
													<c:otherwise>
													
													</c:otherwise>
										   </c:choose> 
	                                       ${redpacketEnd.redpacketName}
	                                     </span>
	                                </div>
	                                <div class="item-content">
	                                    <span class="content-size">
	                                        <b>${redpacketEnd.recordNumber}</b>
	                                        <em>领取个数</em>
	                                    </span>
	                                    <span class="content-time">
	                                        <b>
	                                         <c:choose>
												<c:when test="${!empty redpacketEnd.views}">
													${redpacketEnd.views}
												</c:when>
												<c:otherwise>
													0
												</c:otherwise>
											  </c:choose> 
	                                        </b>
	                                        <em>浏览次数</em>
	                                    </span>
	                                </div>
	                                <div class="item-floor">
	                                    <span class="floor-limit">
	                                        <em class="limit-total">红包总额：￥${redpacketEnd.totalAmount}</em>
	                                        <em class="limit-sum">
	                                                                                                                                    剩余金额：￥
	                                            <c:choose> 
													<c:when test="${!empty redpacketEnd.chargeBalance}">
														${redpacketEnd.chargeBalance}
													</c:when>
													<c:otherwise>
													
													</c:otherwise>
										        </c:choose>                                                                                       
	                                        </em>
	                                    </span>
	                                    <span class="floor-time">
	                                        <em class="time-date">${redpacketEnd.beginDate}-${redpacketEnd.endDate}</em>
	                                        <div class="more-links"><i></i><i></i><i></i></div>
	                                    </span>
	                                </div>
	                            </a>
	                            <!-- 分享功能 -->
<%--	                            <a class="icon-wrap share-links" href="javascript:void(0)" onclick="(${redpacketEnd.redpacketName},${redpacketEnd.redpacketId},${redpacketEnd.redpacketType},${redpacketEnd.status})"></a>--%>
	                        </div>
                        </c:if>
                       </c:forEach>
                       <c:if test="${!empty requestScope.totalCountEnd && requestScope.totalCountEnd eq 0}">
	                        <div class="emptydata">
									<i></i>
									<p>您当前没相关数据</p>
							</div>
					   </c:if>	
                    </div>
                </div>
                <!-- 已结束 -->
            </div>
         </div>
     </div>
    
     <div class="floor-module">
        <a class="floor-links" href="javascript:void(0)" onclick="clicks()">创建红包活动</a>
     </div>
</body>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript" src="${ctx}/js/redpacket/memberbag.js?v=1.0"></script>

<script type="text/javascript">
	var sellerid="${requestScope.sellerid}"; //商家id
	 $(function(){
		var tabIndex = '' ;
	        $(".slide-tab-wrap span").each(function(index,ele){
	            if ( $(this).hasClass("active" )) {
	                 tabIndex = $(this).index();
	                $(".swiper-container .swiper-wrapper .swiper-slide").siblings().removeClass("swiper-slide-active").hide();
	                $(".swiper-container .swiper-wrapper .swiper-slide").eq(tabIndex).addClass("swiper-slide-active").show()
	                return false;
	            }
	        })
	        $(".slide-tab-wrap span").click(function(){
	            var conIndex= $(this).index();
	            $(this).addClass("active").siblings().removeClass("active");
	            $(".swiper-container .swiper-wrapper .swiper-slide").siblings().removeClass("swiper-slide-active").hide();
	            $(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).addClass("swiper-slide-active").show();
	        })
	})
	//数据详情
	function detailData(sellerid,redpacketId,redpacketType,status){
		window.location.href="${ctx}/h5/redpacket/detail?sellerid="+sellerid+"&redpacketId="+redpacketId+"&redpacketType="+redpacketType+"&status="+status;
	};
	
	//创建红包活动事件
	function clicks(){
	   window.location.href="${ctx}/h5/redpacket/select_type?sellerid="+sellerid;
	};
	
	$(document).ready(function(){
		
	})

</script>
</html>
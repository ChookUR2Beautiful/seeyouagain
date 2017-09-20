<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>促销优惠</title>
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
</head>
<body class="padd-fill-tb bg-color-01">
<input type="hidden" value="${couponId}" id="couponId"/>
<input type="hidden" value="${sellerId}" id="sellerId"/>
<input type="hidden" value="${type}" id="type"/>
<input type="hidden" value="${avatar}" id="avatar"/>
<input type="hidden" value="${cname}" id="cname"/>
    <div class="container-wrap">
        <div class="datadetails-info-module">
            <div class="datadetails-info-wrap">
                <div class="getdetails-con">
                    <c:if test="${type==6}">
                      <span class="getdetails-coll">
                    	<strong>
                    		<span id="sendNum">${sendNum}</span><em>个</em>
                    	</strong>
                    	<font>抵用券总数</font>
                      </span>
                      <span class="getdetails-coll">
                    	<strong>
                    		<span id="lastNum">${lastNum}</span><em>个</em>
                    	</strong>
                    	<font>剩余数</font>
                      </span>
                    </c:if>
                    <c:if test="${type==7}">
                       <span class="getdetails-coll">
                    	<strong>
                    		<span id="sendNum">${sendNum}</span><em>个</em>
                    	</strong>
                    	<font>赠品券总数</font>
                       </span>
                    	<span class="getdetails-coll">
                    		<strong>
                    			<span id="lastNum">${lastNum}</span><em>个</em>
                    		</strong>
                    		<font>剩余数</font>
                    	</span>
                    </c:if>	
                    <c:if test="${type==8}">
                    	<span class="getdetails-coll">
                    	    <strong>
                    		<span id="reductionAmount">
                    			<c:if test="${reductionAmount==null}">
                    				￥0
                    			</c:if>
                    			<c:if test="${reductionAmount!=null}">
                    				￥${reductionAmount}
                    			</c:if>
                    		</span>
                    	    </strong>
                    		<font>减免金额</font>
                        </span>
                    	<span class="getdetails-coll">
                    		<strong>
                    			<c:if test="${joinNum==null}">
                    				0
                    			</c:if>
                    			<c:if test="${joinNum!=null}">
                    				${joinNum}
                    			</c:if>
                    		</strong>
                    		<font>参与人数</font>
                    	</span>
                    </c:if>	
                </div>
            </div>
        </div>

        <div class="list-default-module list-col-3" id="list-record">
        </div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/swiper.min.js"></script>
<script type="text/javascript" src="${ctx}/js/baseUtil.js"></script>
<script type="text/javascript">
    //加载列表数据
    $(function(){
		var type = $("#type").val();
		var couponId = $("#couponId").val();
		var sellerId = $("#sellerId").val();
		var avatar = $("#avatar").val();
		//修改title
		document.title = $("#cname").val();
		//用户领取列表信息
		var url = "";
		if(type!=8){
			url = "${pageContext.request.contextPath}/h5/coupon/award_list?couponId="+couponId+"&sellerId="+sellerId;
		}else{
			url = "${pageContext.request.contextPath}/h5/coupon/award_fullreduction_list?aid="+couponId;
		}
    	$.get(url,function(data){
    		if(data.state==0){
    			if(type==6||type==7){//type = 6|| type =7 加载现金抵用券、赠品券领取列表
    				$.each(data.result, function(i, v) {
    					var useTime = v.use_time?v.use_time:"未使用";
    					var isBind = v.isBind==0?"绑定会员":"普通会员";
    					var avatarUrl = v.avatar==''?"<i class='icon-wrap icon-crown'>":"<i class='icon-wrap icon-crown' style='background-image: url("+v.avatar+");'></i>";
    					var item = ["<a class='list-item' href='${ctx}/h5/coupon/detail_order?type="+type+"&recordId="+v.cuid+"&sellerId="+sellerId+"'><div class='list-icon-wrap'>"+avatarUrl+"</i></div>",
                				"<div class='list-col-wrap'><span class='item-coll'>",
                				"<strong>",v.name,"</strong><strong>" ,isBind,"</strong></span>",
                				"<span class='item-coll'>",
                				"<font>领取：",v.get_time,"</font>",
                				"<font>使用：",useTime,"</font>",
								"</span></div></a>"].join("");
						$("#list-record").append(item);
					});
    			}else{
    				$.each(data.result, function(i, v) {
    					var img=v.avatar?v.avatar:basePath+'/imgs/redpacket/Binding-Member2@2x.png';
    					item = ["<a class='list-item' href='${ctx}/h5/coupon/detail_order?type="+type+"&recordId="+v.id+"'><div class='list-icon-wrap'><i class='icon-wrap icon-consumer' style='background-image: url("+img+");'></i></div>",
                				"<div class='list-col-wrap'><span class='item-coll'>",
                				"<strong>",v.cname,"</strong><strong>订单号:"+v.bid+"</strong></span>",
                				"<span class='item-coll'>",
                				"<font>",v.get_time,"</font><font>-￥",v.money,"</font>",
								"</span></div></a>"].join("");
						$("#list-record").append(item);
					});
    			}
    		}else{
    			alert("加载数据异常！");
    		}
    	});
    });
</script>
</html>
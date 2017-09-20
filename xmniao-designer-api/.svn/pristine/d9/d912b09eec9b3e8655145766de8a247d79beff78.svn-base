<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>领取明细</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
        <div class="expendtails-module">
            <div class="expendtails-wrap">
                <div class="expend-user-loge">
                    <c:choose>
                        <c:when test="${empty requestScope.data.avatar}">
                            <img src="${ctx}/imgs/redpacket/Binding-Member2@2x.png"/>
                        </c:when>
                        <c:otherwise>
                            <img src="${requestScope.data.avatar}" alt="会员头像"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="expend-user-name">
                    <%--<c:choose>--%>
						<%--<c:when test="${!empty data.nickname}">${data.nickname}</c:when>--%>
						<%--<c:otherwise></c:otherwise>--%>
					<%--</c:choose>--%>
                    ${data.nickname}
                </div>
                <div class="expend-user-state">
                    <c:choose>
						<c:when test="${requestScope.data.redpacketRecord.isBinding eq 1}">
                            <span>绑定会员</span>
                        </c:when>
						<c:otherwise>
                            <span>普通会员</span>
                        </c:otherwise>
					</c:choose>
                </div>
            </div>
        </div>
        
        <div class="fill-list-module" >
            <div class="list-wrap">
               <div class="list-item">
                   <c:choose>
						<c:when test="${data.redpacketType eq 0 }">
							 <span class="item-input-wrap">分享引流红包</span>
					    </c:when>
						<c:when test="${data.redpacketType eq 1 }">
						    <span class="item-input-wrap"> 限时到店红包</span>
					    </c:when>
						<c:when test="${data.redpacketType eq 2 }">
						    <span class="item-input-wrap">  消费满赠红包</span>
					    </c:when>
						<c:when test="${data.redpacketType eq 3 }">
						    <span class="item-input-wrap"> 推荐消费红包</span>
					    </c:when>
						<c:when test="${data.redpacketType eq 4 }">
						    <span class="item-input-wrap">  普通抽奖红包</span>
					    </c:when>
						<c:otherwise></c:otherwise>
				   </c:choose>
                   <span class="item-name">红包类型</span>
              </div>
            </div>
            
            <div class="list-wrap">
               <div class="list-item">
                    <c:choose>
						<c:when test="${requestScope.data.redpacketRecord.denomination >0 }"><span class="item-input-wrap">￥${requestScope.data.redpacketRecord.denomination}</span></c:when>
						<c:otherwise>0.00</c:otherwise>
					</c:choose>
	               <span class="item-name">红包金额</span>
               </div>
            </div>
            
            <div class="list-wrap">
              <div class="list-item">
                  <span class="item-input-wrap">${requestScope.data.recordTime}</span>
                  <span class="item-name">领取时间</span>
              </div>
            </div>
           
            <div class="list-wrap">
               <div class="list-item">
                  <c:choose>
					<c:when test="${requestScope.data.redpacketRecord.isShare eq 1 }"><span class="item-input-wrap">是</span></c:when>
					<c:otherwise> <span class="item-input-wrap">否</span></c:otherwise>
				  </c:choose>
	              <span class="item-name">是否分享</span>
              </div>
           </div>
           
        </div>
    </div>
</body>


</html>
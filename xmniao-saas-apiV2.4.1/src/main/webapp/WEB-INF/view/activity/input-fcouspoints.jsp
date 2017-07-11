<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>集点免单活动</title>
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
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
    <style type="text/css">
        .end {
            font-size: 12px;
            text-align: center;
            padding: 2px 0;
            margin: 3px 0;
        }
    </style>
</head>
<body class="padd-fill-tb bg-color-01">
		<input type="hidden" id="awardId" value="${awardRelation.awardId}">
		<input type="hidden" id="amount" value="${awardRelation.amount}">
		<input type="hidden" id="awardType" value="${awardRelation.awardType}">
		<input type="hidden" id="awardName" value="${awardRelation.awardName}">
		<input type="hidden" id=couponEndDate value="${awardRelation.couponEndDate}">
<section id="draw1">
    <div class="container-wrap">
        <div class="set-count-module">
            <div class="set-count-head"><span>设置目标点数</span></div>
            <div class="set-count-con">
                <ul class="set-count-current">
                    <li name="points_but"><span>1</span></li>
                    <li name="points_but"><span>2</span></li>
                    <li name="points_but"><span>3</span></li>
                    <li name="points_but"><span>4</span></li>
                    <li  name="points_but"><span>5</span></li>
                    <li name="points_but"><span>6</span></li>
                    <li name="points_but"><span>7</span></li>
                    <li name="points_but"><span>8</span></li>
                    <li name="points_but"><span>9</span></li>
                    <li name="points_but"><span>10</span></li>
                </ul>
            </div>
            <div class="set-count-floot"><span>* 寻蜜鸟大数据分析表明，选择5点可使回头客明细增加，活动效果更优！</span></div>
        </div>
        <div class="fill-list-module">
            <div class="list-wrap">
                <div class="list-item">
                    <span class="item-input-wrap">
                      支付满￥<input name="sizeName" id="full_price"  value="${fcouspoints.fullPrice}" type="text" placeholder="0" id="jizan-list-1" class="made-input-w15 amount"/>&nbsp;可集一个点
                    </span>
                    <span class="item-name">集点条件</span>
                </div>
            </div>
           <input type="hidden" name="is_suposition" id="is_suposition" value="${fcouspoints.isSuposition}"  />
            <div class="list-wrap">
                <div class="list-item">
                        <span class="item-input-wrap">
                            <div id="every-switch" class="sass-switch" style="margin-top: -4px;"></div>
                        </span>
                    <span class="item-name">一次性付款可集多点</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item in-icon-box"><i class="icon-wrap icon-arrow-right"></i>
                    <span class="item-input-wrap">
                        <a id="draw2-1" href="javascript:;">已设置奖品为&nbsp;<font class="list-defind" id="jizan-list-2">${awaryCount}</font>&nbsp;件</a>
                    </span>
                    <span class="item-name">设置奖品</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item in-icon-box activity-date-contain"><i class="icon-wrap icon-arrow-right"></i>
                    <div class="activeity-date-module">
                        <span class="aciveity-data-con aciveity-data-start">
                            <a href="javascript:;" class="activity-time-select" id="activity-starttime" initTime="${initTime}">
                                <c:choose>
                            <c:when test="${empty fcouspoints.beginDate}">
                            <p>${initFormat}</p>
                            </c:when>
                            <c:when test="${empty initFormat}">
                            	<i>选择日期</i>
                            </c:when>
                            <c:otherwise>
	                        <p><fmt:formatDate value="${fcouspoints.beginDate}" pattern="yyyy.MM.dd" /></p>
                            </c:otherwise>
                        </c:choose>
                            </a>
                            <c:choose>
                            <c:when test="${empty fcouspoints.beginDate}">
                            <input type="hidden" name="beginDate" id="beginDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:when test="${empty initFormat}">
                            	<input type="hidden" name="beginDate" id="beginDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:otherwise>
	                        <input type="hidden" name="beginDate" id="beginDate" value="<fmt:formatDate value="${fcouspoints.beginDate}" pattern="yyyy-MM-dd" />"/>
                            </c:otherwise>
                        </c:choose>
                        </span>
                        <span class="aciveity-data-desc">-</span>
                        <span class="aciveity-data-con aciveity-data-end">
                            <a href="javascript:;" class="activity-time-select" id="activity-endtime">
                                <c:choose>
                            <c:when test="${empty fcouspoints.endDate}">
                            <p>${initFormat}</p>
                            </c:when>
                            <c:when test="${empty initFormat}">
                            	<i>选择日期</i>
                            </c:when>
                            <c:otherwise>
	                        <p><fmt:formatDate value="${fcouspoints.endDate}" pattern="yyyy.MM.dd" /></p>
                            </c:otherwise>
                        </c:choose>
                            </a>
                             <c:choose>
                            <c:when test="${empty fcouspoints.beginDate}">
                            <input type="hidden" name="endDate" id="endDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:when test="${empty initFormat}">
                            	<input type="hidden" name="endDate" id="endDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:otherwise>
	                        <input type="hidden" name="endDate" id="endDate" value="<fmt:formatDate value="${fcouspoints.endDate}" pattern="yyyy-MM-dd" />"/>
                            </c:otherwise>
                        </c:choose>
                        </span>
                    </div>
                    <span class="item-name">活动时间</span>
                </div>
            </div>
        </div>
        <p class="fill-list-desc">
            * 会员在集点满额后可兑换奖品，并重新循环集点，引导用户频繁消费。
        </p>
    </div>
    <div class="floor-module">
        <a class="floor-links links-type2" href="javascript:;" id="step2-2submit">提交浏览</a>
    </div>
</section>

<section id="draw2">

    <div class="container-wrap">
        <div class="set-count-module">
            <div class="set-count-head"><span>设置目标点数</span></div>
            <div class="set-count-con">
                <ul class="set-count-select" id="draw2_select">
                    
                </ul>
            </div>
        </div>
        <div class="fill-list-module">
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="jizan-list-1-1"></span><span class="item-name">集点条件</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="jizan-list-off"></span><span class="item-name">一次付款可集多点</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="jizan-list-2-1"></span><span class="item-name">奖品详情</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="jizan-list-3-1"></span><span class="item-name">活动时间</span>
                </div>
            </div>

        </div>
        <p class="fill-list-desc">
            *会员在集点满额兑换奖品后，将自动重新集点！
        </p>
    </div>
    <div class="floor-module">
        <a class="floor-links links-type2" id="step2InitSubmit" href="javascript:;">提交</a>
    </div>
</section>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript" src="${ctx}/js/activity/input-fcouspoint.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
	var switchStatus = "${fcouspoints.isSuposition}"==="1";
	$("#is_suposition").val(switchStatus?1:0);
	var step="${step}";
	var everySwitch = new sassSwitch({
	
    ele: '#every-switch',
    open: switchStatus
});

var pointsNumber=5;
if("${fcouspoints.pointsNumber}"){
	pointsNumber="${fcouspoints.pointsNumber}";
}else{
	pointsNumber=5
}

function myPost(url,args){
	    var body = $(document.body),
	        form = $("<form method='post'></form>"),
	        input;
	    form.attr({"action":url});
	    $.each(args,function(key,value){
	        input = $("<input type='hidden'>");
	        input.attr({"name":key});
	        input.val(value);
	        form.append(input);
	    });

	    form.appendTo(document.body);
	    form.submit();
	    document.body.removeChild(form[0]);
	}
    
  $("input").attr("AUTOCOMPLETE","off");
</script>
</html>
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
    <meta charset="UTF-8">
    <title>创建免费尝新活动</title>
<%@ include file="/common/taglibs.jsp"%>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketing.css"/>
    <link rel="stylesheet" href="${ctx}/css/component.css">
</head>
<body class="padd-fill-tb bg-color-01">
<form id="formid" method="post">
<c:forEach items="${freetry.awardRelations}" var="awardRelation" varStatus="status">
		<input type="hidden" name="sellerCouponDetails[${status.index}].cid" value="${awardRelation.id}">
		<input type="hidden" name="sellerCouponDetails[${status.index}].sendNum" value="${awardRelation.amount}">
		<input type="hidden" name="sellerCouponDetails[${status.index}].couponType" value="${awardRelation.awardType}">
		<input type="hidden" name="sellerCouponDetails[${status.index}].couponName" value="${awardRelation.awardName}">
		<input type="hidden" name="sellerCouponDetails[${status.index}].couponEndDate" value="${awardRelation.couponEndDate}">
	</c:forEach>
<section id="step1" style="display: none;">
    <div class="container-wrap">
        <div class="activitysum-module">
            <div class="activitysum-wrap">
                <div class="activitysum-name">获得几率</div>
                <div class="activitysum-input"><span><input name="awardProportion" id="awardProportion" value="${freetry.awardProportion}" type="text" placeholder="请输入获得几率100%最高" /></span></div>
            </div>
        </div>
       <%--  <div class="activeity-date-module">
            <div class="activeity-dived">活动时间</div>
            <div class="activeity-date-con">
                <span class="aciveity-data-con">
                <input  type="text" class="datepicker" id="activity-starttime" value="<fmt:formatDate value="${freetry.endDate}" pattern="MM月dd日" />"   placeholder="选择日期" readonly/><i class="icon-wrap icon-arrow-right"></i></span><span class="aciveity-data-desc">至</span><span class="aciveity-data-con"><input  type="text" class="datepicker" id="activity-endtime" placeholder="选择日期" value="<fmt:formatDate value="${freetry.endDate}" pattern="MM月dd日" /> "  readonly/><i class="icon-wrap icon-arrow-right" ></i></span>
            </div>
        </div> --%>
	
		<div class="activeity-date-module">
            <div class="activeity-dived">活动时间</div>
            <div class="activeity-date-con">
                <span class="aciveity-data-con aciveity-data-start">
                	<!-- <input  type="text" class="datepicker" id="activity-starttime" value="${currentDate}"   placeholder="选择日期" readonly/> -->
                	<a href="javascript:void(0);" class="activity-time-select" id="activity-starttime" initTime="${initTime}">
                        <c:choose>
                            <c:when test="${empty freetry.beginDate}">
                            <p>${currentDate}</p>
                            </c:when>
                            <c:when test="${empty currentDate}">
                            	<i>选择日期</i>
                            </c:when>
                            <c:otherwise>
	                        <p><fmt:formatDate value="${freetry.beginDate}" pattern="MM月dd日" /></p>
                            </c:otherwise>
                        </c:choose>
                    </a>
                     <c:choose>
                            <c:when test="${empty freetry.beginDate}">
                            <input type="hidden" name="beginDate" id="beginDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:when test="${empty currentDate}">
                            	<input type="hidden" name="beginDate" id="beginDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:otherwise>
	                        <input type="hidden" name="beginDate" id="beginDate" value="<fmt:formatDate value="${freetry.beginDate}" pattern="yyyy-MM-dd" />"/>
                            </c:otherwise>
                        </c:choose>
                	<i class="icon-wrap icon-arrow-right"></i>
                </span>
                <span class="aciveity-data-desc">至</span>
                <span class="aciveity-data-con aciveity-data-end">
                	<!-- <input  type="text" class="datepicker" id="activity-endtime" placeholder="选择日期"  readonly/> -->
                	<a href="javascript:void(0);" class="activity-time-select" id="activity-endtime">
                        <c:choose>
                        	<c:when test="${empty freetry.endDate}">
		                        <i>选择日期</i> 
		                        <p></p>
                        	</c:when>
                        	<c:otherwise>
                        		<p><fmt:formatDate value="${freetry.endDate}" pattern="MM月dd日" /></p>
                        	</c:otherwise>
                        </c:choose>
                    </a>
                     <input type="hidden" name="endDate" id="endDate" value="<fmt:formatDate value="${freetry.endDate}" pattern="yyyy-MM-dd" />"/>
                	<i class="icon-wrap icon-arrow-right" ></i>
                </span>
            </div>
        </div>
		
	
        <div class="ativety-data-name">
            <div class="activeity-dived">免费尝新活动名称</div>
            <div class="activeity-input"><input type="text" name="name" id="activityName" value="${freetry.name}" placeholder="例如：红烧肉免费试吃" /></div>
        </div>
    </div>
    <div class="floor-module">
        <a class="floor-links links-disabled" id="step1Submit" href="javascript:void(0);">下一步</a>
    </div>
    <div id="container"></div>
</section>
<section id="step2" style="display: none;">
    <div class="container-wrap">
        <div class="fill-list-module" >
            <div class="list-divhead">奖品设置</div>
            <div class="list-wrap"><div class="list-item in-icon-box"><i class="icon-wrap icon-arrow-right"></i>
                    <span class="item-input-wrap">
                        <a id="list_award" href="javascript:void(0);">已设置奖品为<font class="list-defind">${awaryCount==null?0:awaryCount}</font>件</a>
                    </span><span class="item-name">设置奖品</span></div></div>
            <div id="item-content"><div class="list-wrap"><div id="list-ft-color" class="list-item"><span class="item-input-wrap"><input  id="prizeDrawNumber" name="prizeDrawNumber" type="text" placeholder="0"  value="${freetry.prizeDrawNumber}"/>次</span><span class="item-name">每人可抽取次数</span></div></div></div>
            <input type="hidden" name="limitNumber" id="limitNumber" value="${freetry.limitNumber}"  />
             <div class="list-wrap">
                    <div class="list-item">
                        <span class="item-input-wrap">
                            <div id="every-switch" class="sass-switch" style="margin-top: -4px; height: 5px"></div>
                        </span>
                        <span class="item-name">是否限制每人领取一次</span>
                    </div>
                </div>
             <div class="list-wrap">
				<div class="list-item select-box">
					<i class="icon-wrap icon-arrow-right"></i> <span
						class="item-input-wrap"> <a id="select-reward-box"
						class="select-text"  href="javascript:void(0);">${freetry.setConditionToString==null?"重设次数":freetry.setConditionToString}</a>
					</span> <span class="item-name">重设条件</span>
				</div>
			</div>
			<input type="hidden" name="setCondition" id="setCondition" value="${freetry.setCondition}"/>
          
        </div>
        <p class="fill-list-desc-activity">*当获得几率设置为0时，获得记录为0，用户每次抽奖获得谢谢惠顾；</p>
			<p class="fill-list-desc-activity">*当赠品已领取完毕后，将不会在获得，会自动跳过。</p>
			<p class="fill-list-desc-activity">*重设条件只能设置其中一项；</p>
			<p class="fill-list-desc-activity">*奖品在活动期间有效。</p>
    </div>
    <div class="floor-module">
        <a  id="step2Submit" class="floor-links links-type2" href="javascript:;">提交浏览</a>
    </div>
</section>
    <section id="step3" style="display: none;">
		<div class="container-wrap">
			<div class="fill-list-module resetfill-list">
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3name">${freetry.name}</span><span
							class="item-name">免费尝新活动名称</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" >${awarySum!=null?awarySum:0}份</span><span
							class="item-name">奖品数量</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3prizeDrawNumber">${freetry.prizeDrawNumber}</span><span
							class="item-name">每人可抽取次数</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3limitNumber"><c:if
								test="${freetry.limitNumber==1}">${freetry.limitNumber}次</c:if>
							<c:if test="${freetry.limitNumber==0}">无限制</c:if></span><span
							class="item-name">每人限领</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3awardProportion">${freetry.awardProportion}%</span><span
							class="item-name">获得概率</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3setConditionToString">${freetry.setConditionToString}</span><span
							class="item-name">重设条件</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3date"><fmt:formatDate
								value='${freetry.beginDate}' type="date" dateStyle="medium" /> 至
							<fmt:formatDate value='${freetry.endDate}' type="date"
								dateStyle="medium" /></span><span class="item-name">活动时间</span>
					</div>
				</div>
				<c:forEach items="${freetry.awardRelations}"
					var="sellerCouponDetail" varStatus="status">
					<div class="list-wrap ${status.index==0?'ist-item-reset':''}">
						<div class="list-item">
							<span class="item-input-wrap">${sellerCouponDetail.amount}份</span><span
								class="item-name">${sellerCouponDetail.awardName}</span>
						</div>
					</div>
				</c:forEach>
			</div>
			<p class="fill-list-desc-activity">*当获得几率设置为0时，获得记录为0，用户每次抽奖获得谢谢惠顾；</p>
			<p class="fill-list-desc-activity">*当赠品已领取完毕后，将不会在获得，会自动跳过。</p>
			<p class="fill-list-desc-activity">*重设条件只能设置其中一项；</p>
			<p class="fill-list-desc-activity">*奖品在活动期间有效。</p>
		</div>
		<div class="floor-module">
			<a class="floor-links links-type2" id="submitButton"
				href="javascript:;">提交</a>
		</div>
	</section>
	</form>
	</body>
<script src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script src="${ctx}/js/component.js"></script>
<script src="${ctx}/js/activity/input-freetry.js"></script>
<script type="text/javascript" src="${ctx}/js/activity/common.js"></script>   
<script type="text/javascript">
	var path="${ctx}";
	var awaryCount = "${awaryCount}";
	var switchStatus = "${freetry.limitNumber==1}"==="true";
	$("#limitNumber").val(switchStatus?1:0);
	var step="${step}";
	var everySwitch = new sassSwitch({
	
    ele: '#every-switch',
    open: switchStatus
});
	var beginDate=new Date(Date.parse($("#beginDate").val().replace(/-/g, "/")));
$('#activity-starttime').attr("initTime",beginDate.getFullYear() + '年' + (parseInt(beginDate.getMonth())+parseInt(1)) + '月' + beginDate.getDate() + '日')
if(($("#endDate").val())){
	var endDate=new Date(Date.parse($("#endDate").val().replace(/-/g, "/")));
	$('#activity-endtime').attr("initTime",endDate.getFullYear() + '年' + (parseInt(endDate.getMonth())+parseInt(1))+ '月' + endDate.getDate() + '日')
}
</script>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加优惠券页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="add coupon">
<meta http-equiv="description" content="add coupon init">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

th {
	font-size: 12px;
}
</style>
</head>

<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" id="editCouponForm">
				<input type="hidden" name="ctype" value="${coupon.ctype}" />
				<input type="hidden" id="cid" name="cid" value="${coupon.cid}">
				<div class="form-group" style="margin-top:40px;">
					<label class="col-md-2 control-label">优惠券名称：</label>
					<div class="col-md-3">
						<input type="text" name="cname" maxlength="15" placeholder="15字内"
							class="form-control" value="${coupon.cname}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">面额：</label>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" name="denomination" class="form-control"
								value="${coupon.denomination}" /><span
								class="input-group-addon">元</span>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label">时间：</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <label
								class="radio-inline">领取有效天数 <input type="radio"
									name="swichtime" onchange="swichtimeYes();"
									<c:if test="${!empty coupon.dayNum and coupon.dayNum!=0}">checked="checked"</c:if>
									value="1" />
							</label>
							</span>
							<div id="customConditionDiv">
								<input class='form-control' type="text" name="dayNum"
									value="${coupon.dayNum}" />
							</div>
							<span class="input-group-addon"> <label
								class="radio-inline">设置有效日期区间 <input type="radio"
									id="showTimex2" name="swichtime" value="2"
									<c:if test="${empty coupon.dayNum }">checked="checked"</c:if>
									onchange="swichtimeNo();" />
							</label>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group" id="dates"
					<c:if test="${empty coupon.coupnValidities}">style="display: none;"</c:if>>
					<label class="col-md-2 control-label">有效日期：</label>
					<div class="col-md-8">
						<c:if test="${!empty coupon.coupnValidities}">
							<c:forEach items="${coupon.coupnValidities}" var="coupnValidity"
								varStatus="status">
								<div class="input-group">
									<span class="input-group-addon">开始日期：</span> <input readonly
										type="text" name="coupnValidities[${status.index}].startDate"
										class="date-start form-control"
										value='<fmt:formatDate value="${coupnValidity.startDate }" pattern="yyyy-MM-dd"/>' />
									<span class="input-group-addon">结束日期：</span> <input readonly
										type="text" name="coupnValidities[${status.index}].endDate"
										id="" class="date-end form-control"
										value='<fmt:formatDate value="${coupnValidity.endDate}" pattern="yyyy-MM-dd"/>' /><span
										class="input-group-addon fix-border fix-padding"></span><span
										class="input-group-addon">开始时间：</span> <input type="text"
										readonly name="coupnValidities[${status.index}].startTime"
										class="time-start form-control "
										value="${coupnValidity.startTimeText}" /> <span
										class="input-group-addon">结束时间：</span> <input type="text"
										readonly name="coupnValidities[${status.index}].endTime"
										class="time-end form-control"
										value="${coupnValidity.endTimeText}" /> <span
										class="input-group-addon"><i class="icon icon-plus"
										style="cursor:pointer" onclick="addDate(this);"></i></span> <span
										class="input-group-addon"><i class="icon icon-minus"
										style="cursor:pointer" onclick="removeDate(this);"></i></span>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label">使用条件：</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <label
								class="radio-inline">满 <input type="radio"
									name="conditionRadio" onchange="conditionYes();"
									id="customConditionRadio" value="1"
									<c:if test="${coupon.condition>0}"> checked="checked" </c:if> />
							</label>
							</span>
							<div id="customConditionDiv">
								<input class='form-control' type="text" name="condition"
									value="${coupon.condition}" />
							</div>
							<span class="input-group-addon">元(起)使用</span> <span
								class="input-group-addon"> <label class="radio-inline">无条件使用
									<input type="radio" name="conditionRadio" value="0"
									<c:if test="${coupon.condition == 0}"> checked="checked" </c:if>
									id="customConditionRadio1" onchange="conditionNo();" />
							</label>
							</span>
						</div>
					</div>
				</div>

        <%--<div class="form-group">--%>
					<%--<label class="col-md-2 control-label"></label>--%>
				<input name="useNum" type="hidden" id="useNum" min="1" placeholder="1" value="${coupon.useNum}">
				<%--</div>--%>

				<div class="form-group">
					<label class="col-md-2 control-label">是否可以叠加使用：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio" name="overlay" onchange="switchOverlay(this)" value="1" <c:if test="${coupon.overlay == 1}">checked="checked"</c:if>/></label>
							<label class="radio-inline">否<input type="radio" name="overlay" onchange="switchOverlay(this)" value="0" <c:if test="${coupon.overlay == 0}">checked="checked"</c:if>/></label>
						</div>
					</div>
				</div>


				<div class="form-group" id="showAll">
					<label class="col-md-2 control-label">是否平台通用：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio"
								name="showAll" onchange="showAllYes();" id="allArea" value="1"
								<c:if test="${coupon.showAll == 1}"> checked="checked" </c:if> /></label>
							<label class="radio-inline">否<input type="radio"
								name="showAll" id="specifyArea" value="0"
								<c:if test="${coupon.showAll == 0}"> checked="checked" </c:if>
								onchange="showAllNo();" /></label>
						</div>
					</div>

				</div>

				<div class="form-group" id="allSeller">
					<label class="col-md-2 control-label">是否全部商家：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio"
								name="isAllSeller" value="1"
								<c:if test="${coupon.isAllSeller == 1 or empty coupon.isAllSeller}"> checked="checked" </c:if>
								onchange="isAllSellerYes();" /></label><label class="radio-inline">否<input
								type="radio" name="isAllSeller" value="0"
								<c:if test="${coupon.isAllSeller == 0}"> checked="checked" </c:if>
								onchange="isAllSellerNo();" /></label>
						</div>
					</div>
				</div>
				<div class="form-group" id="cities">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
						<c:if test="${!empty coupon.couponCities}">
							<c:forEach items="${coupon.couponCities}" var="couponCity">
								<div class="input-group">
									<div class="ld" style="width: 100%;float:left;"
										initvalue="${couponCity.city}"></div>
									<span class="input-group-addon"><i
										class="icon icon-plus" style="cursor:pointer"
										onclick="addAreaLd(this) ;"></i></span> <span
										class="input-group-addon"><i class="icon icon-minus"
										style="cursor:pointer" onclick="removeAreaLd(this);"></i></span>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty coupon.couponCities}">
							<div class="input-group">
								<div class="ld" style="width: 100%;float:left;"></div>
								<span class="input-group-addon"><i class="icon icon-plus"
									style="cursor:pointer" onclick="addAreaLd(this) ;"></i></span> <span
									class="input-group-addon"><i class="icon icon-minus"
									style="cursor:pointer" onclick="removeAreaLd(this);"></i></span>
							</div>
						</c:if>
					</div>
				</div>
				<div class="form-group" id="trades">
					<label class="col-md-2 control-label">行业选择：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">包含所选行业<input checked="true"
								type="radio" name="trade" value="1" onchange="tradeInclude();"
								<c:if test="${fn:length(coupon.includeTrade)>0}">checked="checked"</c:if> /></label><label
								class="radio-inline">排除所选行业<input type="radio"
								name="trade" value="0" onchange="tradeExclude();"
								<c:if test="${fn:length(coupon.excludeTrade)>0}">checked="checked"</c:if> /></label>
						</div>
					</div>
				</div>
				<div class="form-group" id="includeTrade">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
						<c:if test="${!empty coupon.includeTrade}">
							<c:forEach items="${coupon.includeTrade}" var="trade">
								<div class="input-group">
									<div class="includeTradeSelect" style="width : 100%"
										initvalue="${trade.sellerid}"></div>
									<span class="input-group-addon"><i
										class="icon icon-plus" style="cursor:pointer"
										onclick="addIncludeTrade(this) ;"></i></span> <span
										class="input-group-addon"><i class="icon icon-minus"
										style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty coupon.includeTrade}">
							<div class="input-group">
								<div class="includeTradeSelect" style="width : 100%"></div>
								<span class="input-group-addon"><i class="icon icon-plus"
									style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> <span
									class="input-group-addon"><i class="icon icon-minus"
									style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
							</div>
						</c:if>
					</div>
				</div>

				<div class="form-group" id="excludeTrade">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
						<c:if test="${!empty coupon.excludeTrade}">
							<c:forEach items="${coupon.excludeTrade}" var="trade">
								<div class="input-group">
									<div class="excludeTradeSelect" style="width : 100%"
										initvalue="${trade.sellerid}"></div>
									<span class="input-group-addon"><i
										class="icon icon-plus" style="cursor:pointer"
										onclick="addExcludeTrade(this) ;"></i></span> <span
										class="input-group-addon"><i class="icon icon-minus"
										style="cursor:pointer" onclick="removeExcludeTrade(this);"></i></span>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty coupon.excludeTrade}">
							<div class="input-group">
								<div class="excludeTradeSelect" style="width : 100%"></div>
								<span class="input-group-addon"><i class="icon icon-plus"
									style="cursor:pointer" onclick="addExcludeTrade(this) ;"></i></span> <span
									class="input-group-addon"><i class="icon icon-minus"
									style="cursor:pointer" onclick="removeExcludeTrade(this);"></i></span>
							</div>
						</c:if>
					</div>
				</div>
				<div class="form-group" id="includeSellers">
					<label class="col-md-2 control-label">包含所选商家：</label>
					<div class="col-md-5">
						<textarea rows="5" style="width:100%;" name="includeSellerids"></textarea>
					</div>
				</div>
				<div class="form-group" id="excludeSellers">
					<label class="col-md-2 control-label">排除所选商家：</label>
					<div class="col-md-5">
						<textarea rows="5" style="width:100%;" name="excludeSellerids"></textarea>
					</div>
					<div class="col-md-2" style="display:none;color:red;" id="msg"></div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">优惠劵详情图：</label>
					<div class="col-md-2">
						<div class="input-group">
							<input type="hidden" class="form-control" id="picURL" name="pic"
								value="${coupon.pic}" />
							<div id="pic"></div>
						</div>
					</div>
					<label class="col-md-2 control-label">优惠劵列表图：</label>
					<div class="col-md-2 ">
						<div class="input-group">
							<input type="hidden" class="form-control" id="breviaryURL"
								name="breviary" value="${coupon.breviary}" />
							<div id="breviary"></div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">优惠劵使用规则：</label>
					<div class="col-md-5">
						<textarea name="rule" class="form-control">${coupon.rule}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
						<div class="alert alert-warning">
							<strong>提示：</strong> 提交后需要编辑发放设置后，优惠券才能生效
						</div>
					</div>

				</div>
				<div class="col-md-12 text-center">
					<button id="ensure" type="submit" class="btn btn-danger">
						<i class="icon-save"></i>&nbsp;保存
					</button>
					&nbsp;&nbsp;
					<button onclick="window.history.back();" type="button"
						class="btn btn-warning">
						<i class="icon-reply"></i>&nbsp;取消
					</button>
				</div>
			</form>
		</div>
	</div>
	<div class="hidden dateTemp">
		<div class="input-group">
			<span class="input-group-addon">开始日期：</span> <input readonly
				type="text" name="coupnValidities[index].startDate"
				class="form-control date-start" /> <span class="input-group-addon">结束日期：</span>
			<input type="text" readonly name="coupnValidities[index].endDate"
				class="form-control date-end" /><span
				class="input-group-addon fix-border fix-padding"></span><span
				class="input-group-addon">开始时间：</span> <input type="text" readonly
				name="coupnValidities[index].startTime"
				class="form-control time-start" /> <span class="input-group-addon">结束时间：</span>
			<input type="text" name="coupnValidities[index].endTime" readonly
				class="form-control time-end" /> <span class="input-group-addon"><i
				class="icon icon-plus" style="cursor:pointer"
				onclick="addDate(this);"></i></span> <span class="input-group-addon"><i
				class="icon icon-minus" style="cursor:pointer"
				onclick="removeDate(this);"></i></span>
		</div>
	</div>
	<div class="cityTemp hidden ">
		<div class="input-group">
			<div class="ld" style="width: 100%;float:left;"></div>
			<span class="input-group-addon"><i class="icon icon-plus"
				style="cursor:pointer" onclick="addAreaLd(this);"></i></span> <span
				class="input-group-addon"><i class="icon icon-minus"
				style="cursor:pointer" onclick="removeAreaLd(this)"></i></span>
		</div>
	</div>

	<div class="includeTradeSelectTemp hidden ">
		<div class="input-group">
			<div class="includeTradeSelect" style="width : 100%"></div>
			<span class="input-group-addon"><i class="icon icon-plus"
				style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> <span
				class="input-group-addon"><i class="icon icon-minus"
				style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
		</div>
	</div>
	<div class="excludeTradeSelectTemp hidden ">
		<div class="input-group">
			<div class="excludeTradeSelect" style="width : 100%"></div>
			<span class="input-group-addon"><i class="icon icon-plus"
				style="cursor:pointer" onclick="addExcludeTrade(this) ;"></i></span> <span
				class="input-group-addon"><i class="icon icon-minus"
				style="cursor:pointer" onclick="removeExcludeTrade(this);"></i></span>
		</div>
	</div>
	<div class="form-group" id="allSellerTemp" style="display: none">
		<label class="col-md-2 control-label" >是否全部商家：</label>
		<div class="col-md-3">
			<div class="input-group">
				<label class="radio-inline">是<input type="radio"
					name="isAllSeller" value="1" checked="checked"
					onchange="isAllSellerYes();" /></label><label class="radio-inline">否<input
					type="radio" name="isAllSeller" value="0"
					onchange="isAllSellerNo();" /></label>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<script type="text/json" id="permisions">{add:'${ btnAu['coupon/generate/add']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/ux/js/searchChosen2.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<%-- <script src="<%=path%>/js/coupon/generate/edit.js"></script> --%>
	<script src="<%=path%>/js/coupon/generate/couponUpdate.js"></script>
</body>
</html>

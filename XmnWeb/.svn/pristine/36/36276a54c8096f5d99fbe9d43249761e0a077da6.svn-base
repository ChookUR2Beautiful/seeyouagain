<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<c:if test="${!empty coupon.cid}">
					<input type="hidden" id="cid" name="cid" value="${coupon.cid}">
				</c:if>
				<div class="form-group" style="margin-top:40px;">
					<label class="col-md-2 control-label">优惠券名称：</label>
					<div class="col-md-3">
						<input type="text" name="cname" maxlength="15" placeholder="15字内" class="form-control"
							<c:if test="${!empty coupon.cname}">value="${coupon.cname}"</c:if> />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">面额：</label>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" name="denomination" class="form-control"
								<c:if test="${!empty coupon.denominationInt}">value="${coupon.denominationInt}"</c:if> /><span
									class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<c:if test="${!empty coupon}">
				<div class="form-group" >
					<label class="col-md-2 control-label">时间：</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
							<label class="radio-inline">领取有效天数
							<input type="radio"	name="swichtime" data-input="1"  <c:if test="${!(empty coupon) and (coupon.dayNum>0)}">checked="checked"</c:if> value="1" />
							</label>
							</span>
							<div id="customConditionDiv">
							<input class='form-control <c:if test="${!(empty coupon) and (coupon.condition != 0)}">customConditionInput</c:if>' type="text" name="dayNum" value="${coupon.dayNum}"  />
							</div>
								<span class="input-group-addon">
								<label class="radio-inline">设置有效日期区间
								<input type="radio" id="showTimex2" name="swichtime" <c:if test="${!(empty coupon) and (!(coupon.dayNum>0))}">checked="checked"</c:if> value="2" />
								</label>
								</span>
						</div>
					</div>
               </div>
              </c:if>
              
              <c:if test="${empty coupon}">
				<div class="form-group" >
					<label class="col-md-2 control-label">时间：</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
							<label class="radio-inline">领取有效天数
							<input type="radio"	name="swichtime" data-input="1" 	checked="checked" value="1" />
							</label>
							</span>
							<div id="customConditionDiv">
							<input class='form-control <c:if test="${!(empty coupon) and (coupon.condition != 0)}">customConditionInput</c:if>' type="text" name="dayNum" value="${coupon.dayNum}"  />
							</div>
								<span class="input-group-addon">
								<label class="radio-inline">设置有效日期区间
								<input type="radio" id="showTimex2" name="swichtime"  value="2" />
								</label>
								</span>
						</div>
					</div>
               </div>
              </c:if>
               
				<div class="form-group" id="dates" style="display: none;">
					<label class="col-md-2 control-label">有效日期：</label>
					<div>
						<div class="col-md-8">
							<c:if test="${!empty coupon.coupnValidities}">
								<c:forEach items="${coupon.coupnValidities}" var="coupnValidity"
									varStatus="status">
									<%-- <input type="hidden"
										name="coupnValidities[${status.index}].cvid"
										value="${coupnValidity.cvid }"> --%>
									<div class="input-group">
										<span class="input-group-addon">开始日期：</span> <input
											readonly type="text" name="coupnValidities[${status.index}].startDate"
											class="date-start form-control"
											value='<fmt:formatDate value="${coupnValidity.startDate }" pattern="yyyy-MM-dd"/>' />
										<span class="input-group-addon">结束日期：</span> <input
											readonly type="text" name="coupnValidities[${status.index}].endDate"
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
											style="cursor:pointer"></i></span> <span class="input-group-addon"><i
											class="icon icon-minus" style="cursor:pointer"></i></span>
									</div>
								</c:forEach>
							</c:if>
							<c:if test="${empty coupon.coupnValidities}">
								<div class="input-group">
									<span class="input-group-addon ">开始日期：</span> <input
										readonly type="text" name="coupnValidities[0].startDate"
										class="form-control date-start" /> <span
										class="input-group-addon">结束日期：</span> <input type="text"
										readonly name="coupnValidities[0].endDate" 
										class="form-control date-end" /><span
										class="input-group-addon fix-border fix-padding"></span><span
										class="input-group-addon">开始时间：</span> <input type="text"
										readonly name="coupnValidities[0].startTime"
										class="form-control time-start" /> <span
										class="input-group-addon">结束时间：</span> <input type="text"
										readonly name="coupnValidities[0].endTime"
										class="form-control time-end" /> <span
										class="input-group-addon"><i class="icon icon-plus"
										style="cursor:pointer"></i></span> <span class="input-group-addon"><i
										class="icon icon-minus" style="cursor:pointer"></i></span>
								</div>
							</c:if>
						</div>
					</div>
				</div>
				<c:if test="${!empty coupon}">
				<div class="form-group">
					<label class="col-md-2 control-label">使用条件：</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
							<label class="radio-inline">满
							<input type="radio"
								name="conditionRadio" data-input="1" id="customConditionRadio"
								<c:if test="${!(empty coupon) and (coupon.condition != 0)}">checked="checked"</c:if>
								<c:if test="${!(empty coupon)and (coupon.condition != 0)}">value="${coupon.condition}"</c:if> /></label></span>
							    <div id="customConditionDiv">
							    <input id="condition"  name="condition" class='form-control <c:if test="${!(empty coupon) and (coupon.condition != 0)}">customConditionInput</c:if>' type="text" 
								<c:if test="${!(empty coupon) and (coupon.condition != 0)}">value="${coupon.condition}"</c:if> />
								</div>
								<span class="input-group-addon">元(起)使用</span> 
								<span class="input-group-addon">
								<label class="radio-inline">无条件使用
								<input type="radio"	name="conditionRadio" value="0" id="customConditionRadio1" <c:if test="${coupon.condition == 0}">checked="checked"</c:if> />
								</label>
								</span>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${empty coupon}">
				<div class="form-group">
					<label class="col-md-2 control-label">使用条件：</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
							<label class="radio-inline">满
							<input type="radio"	name="conditionRadio" data-input="1" id="customConditionRadio"/>
							</label>
							</span>
							<div id="customConditionDiv"><input class='form-control' id="condition" name="condition" type="text" /></div>
							    <span class="input-group-addon">元(起)使用</span> 
							    <span class="input-group-addon">
								<label class="radio-inline">无条件使用
								<input type="radio" name="conditionRadio" value="0" id="customConditionRadio1"/>
								</label>
								</span>
						</div>
					</div>
				</div>
				</c:if>
				<div class="form-group">
					<label class="col-md-2 control-label">每次可同时使用：</label>
					<div class="col-md-3">
					<div class="input-group">
						<select class="form-control" name="useNum">
							<option value="1">1</option>
							<%--<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option> --%>
						</select>
							<%-- <input type="text" class="form-control" name="useNum"
								<c:if test="${!empty coupon.useNum }">value="${coupon.useNum }"</c:if> /> --%><span
								class="input-group-addon">张</span>
					</div>
				</div>
				</div>
				<c:if test="${!empty coupon}">
				<div class="form-group" id="showAll">
					<label class="col-md-2 control-label">是否平台通用：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio"
								name="showAll" id="allArea" value="1"
								<c:if test="${coupon.showAll == 1}">checked="checked"</c:if> /></label>
							<label class="radio-inline">否<input type="radio"
								name="showAll" id="specifyArea" value="0"
								<c:if test="${coupon.showAll == 0}">checked="checked"</c:if> /></label>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${empty coupon}">
				<div class="form-group" id="showAll">
					<label class="col-md-2 control-label">是否平台通用：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio"
								name="showAll" id="allArea" value="1"
								checked="checked" /></label>
							<label class="radio-inline">否<input type="radio"
								name="showAll" id="specifyArea" value="0"
								 /></label>
						</div>
					</div>
				</div>
				</c:if>
				
				
				
				<c:if test="${!empty coupon}">
				<div class="form-group" id="allSeller">
					<c:if test="${coupon.showAll != 1}">
					<label class="col-md-2 control-label">是否全部商家：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio"
								name="isAllSeller" value="1"
								<c:if test="${coupon.isAllSeller == 1}">checked="checked"</c:if> /></label>
							<label class="radio-inline">否<input type="radio"
								name="isAllSeller" value="0"
								<c:if test="${coupon.isAllSeller == 0}">checked="checked"</c:if>  /></label>
						</div>
					</div>
					</c:if>
				</div>
				</c:if>
				<c:if test="${empty coupon}">
				<div class="form-group" id="allSeller">
				</div>
				</c:if>
				<c:if test="${empty coupon}">
				<div class="form-group" id="cities">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5"></div>
				</div>
				</c:if>
				<c:if test="${!empty coupon }">
				<div class="form-group" id="cities">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
						<c:if test="${coupon.showAll == 0 and coupon.isAllSeller == 1}">
						<c:if test="${!empty coupon.couponCities}">
							<c:forEach items="${ coupon.couponCities}" var="couponCity"
								varStatus="status">
								<%-- <input type="hidden" name="couponCities[${status.index}].ccid"
									value="${couponCity.ccid}"> --%>
								<div class="input-group">
									<div class="updateld" style="width: 100%;float:left;"
										initvalue="${couponCity.city}"></div>
									<span class="input-group-addon"><i
										class="icon icon-plus" style="cursor:pointer"></i></span> <span
										class="input-group-addon"><i class="icon icon-minus"
										style="cursor:pointer"></i></span>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${!empty coupon and empty coupon.couponCities}">
							<div class="input-group">
								<div class="updateld" style="width: 100%;float:left;"></div>
								<span class="input-group-addon"><i class="icon icon-plus"
									style="cursor:pointer"></i></span> <span class="input-group-addon"><i
									class="icon icon-minus" style="cursor:pointer"></i></span>
							</div>
						</c:if>
						</c:if>
					</div>
				</div>
				</c:if>
				<c:if test="${!empty coupon}">
				<div class="form-group" id="sellers">
					<c:if test="${coupon.showAll == 0 and coupon.isAllSeller == 0}">
					<label class="col-md-2 control-label">选择商家：</label>
					<div class="col-md-8">
						<textarea id="sellerids" rows="5" name="sellerids"
							class="col-md-8"></textarea>
					</div>
					</c:if>
				</div>
				</c:if>
				<c:if test="${empty coupon}">
				<div class="form-group" id="sellers"></div>
				</c:if>
				<div class="form-group">
					<label class="col-md-2 control-label">优惠劵详情图：</label>
					<div class="col-md-4">
						<div class="input-group">
							<input type="hidden" class="form-control" id="picURL" name="pic"
								<c:if test="${!empty coupon.pic}">value="${coupon.pic}"</c:if> />
							<div id="pic"></div>
						</div>
					</div>
					<label class="col-md-2 control-label">优惠劵列表图：</label>
					<div class="col-md-4">
						<div class="input-group">
							<input type="hidden" class="form-control" id="breviaryURL"
								<c:if test="${!empty coupon.breviary}">value="${coupon.breviary}"</c:if>
								name="breviary" />
							<div id="breviary"></div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">优惠劵使用规则：</label>
					<div class="col-md-5">
						<textarea  name="rule" class="form-control" >${coupon.rule}</textarea>
					</div>
				</div>
				<div class="col-md-12 text-center">
					<button id="ensure" type="submit" class="btn btn-danger"><i class="icon-save"></i>&nbsp;保存</button>&nbsp;&nbsp;
					<button onclick="window.history.back();" type="button" class="btn btn-warning"><i class="icon-reply"></i>&nbsp;取消</button>
				</div>
			</form>
		</div>
	</div>
	<div class="hidden dateTemp">
		<div class="input-group">
			<span class="input-group-addon">开始日期：</span> <input readonly type="text"
				name="coupnValidities[index].startDate"
				class="form-control date-start" /> <span class="input-group-addon">结束日期：</span>
			<input type="text" readonly name="coupnValidities[index].endDate"
				class="form-control date-end" /><span
				class="input-group-addon fix-border fix-padding"></span><span
				class="input-group-addon">开始时间：</span> <input type="text" readonly
				name="coupnValidities[index].startTime"
				class="form-control time-start" /> <span class="input-group-addon">结束时间：</span>
			<input type="text" name="coupnValidities[index].endTime"
				readonly class="form-control time-end" /> <span
				class="input-group-addon"><i class="icon icon-plus"
				style="cursor:pointer"></i></span> <span class="input-group-addon"><i
				class="icon icon-minus" style="cursor:pointer"></i></span>
		</div>
	</div>
	<div class="cityTemp hidden ">
		<div class="input-group">
			<div class="ld" style="width: 100%;float:left;"></div>
			<span class="input-group-addon"><i class="icon icon-plus"
				style="cursor:pointer"></i></span> <span class="input-group-addon"><i
				class="icon icon-minus" style="cursor:pointer"></i></span>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
		var type ="add";
		if(${!empty coupon}){
		type="update";
		}
	</script>
	<script type="text/json" id="permisions">{add:'${ btnAu['coupon/generate/add']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/coupon/generate/edit.js"></script>
</body>
</html>

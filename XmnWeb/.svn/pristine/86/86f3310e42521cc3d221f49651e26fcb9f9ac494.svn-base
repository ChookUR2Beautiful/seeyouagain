<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>注册礼物信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">礼物信息</div>
				<!-- height: 600px; width :900px;  -->
				<form class="form-horizontal" role="form" id="editGiftForm" style="overflow-y:auto; ">
				<input type="hidden"   id="id" name="id" value="${registerGift.id}">
				<input type="hidden"   id="couponId" name="couponId" value="${registerGift.couponId}"> 
				<input type="hidden"   id = "isType" value="${isType}">

				<table class="table" style="width:100%">
					<tbody>
						<tr style="height:20px"></tr>
						<tr>
							<td style="width:20%;"><h5>&nbsp;&nbsp;奖励类型:</h5></td>
							<td style="width:30%;">
							<c:choose> 
							  <c:when test="${isType == 'update'}">   
							   <select class="form-control" id="giftType" name="giftType" style="width:90%" onchange="changeGiftType();"  readonly ="readonly" > 
									<c:if test="${registerGift.giftType == 1}"><option value="1" selected >积分</option></c:if>
									<c:if test="${registerGift.giftType == 2}"><option value="2" selected>平台优惠券</option></c:if>
									<c:if test="${registerGift.giftType == 3}"><option value="3" selected>鸟豆</option></c:if>
								</select>
							  </c:when> 
							  <c:otherwise>   
							   	<select class="form-control" id="giftType" name="giftType" style="width:90%" onchange="changeGiftType();" > 
									<option value="1" <c:if test="${registerGift.giftType == 1}">selected</c:if>>积分</option>
									<option value="2" <c:if test="${registerGift.giftType == 2}">selected</c:if>>平台优惠券</option>
									<option value="3" <c:if test="${registerGift.giftType == 3}">selected</c:if>>鸟豆</option>
								 </select>  
							  </c:otherwise> 
							</c:choose> 
															 
							</td>
							<td style="width:20%;"></td>
							<td style="width:30%;"></td>
						</tr>
						<tr class="coupon">
							<td style="width:20%;"><h5>&nbsp;&nbsp;面额(元):</h5></td>
							<td style="text-align: left;">
								<h5>
								<input name="coupon.denomination" type="text" value="${registerGift.coupon.denomination}" id="denomination" class="form-control" placeholder="优惠券面额" style="width:200px"/> 
								</h5>
							</td>	
						</tr>
						<tr class="coupon">
							<td style="width:20%;"><h5>&nbsp;&nbsp;数量(张):</h5></td>
							<td  align="left">
								<h5>
								<input name="num" type="text" value="${registerGift.num}" id="num" class="form-control" placeholder="优惠券数量" style="width:200px"/> 
								</h5>
							</td>
						</tr>
						<tr class="coupon">
							<td style="width:20%;"><h5>&nbsp;&nbsp;使用条件:</h5></td>
							<td  align="left">
								<div class="input-group">
										<span class="input-group-addon"> 
										<label class="radio-inline">满 
										<input type="radio"	name="conditionRadio" onchange="conditionInputRead();"
												id="customConditionRadio" value="1"  <c:if test="${registerGift.coupon.condition>0}"> checked="checked"</c:if> />
										</label>
										</span>
										<div id="customConditionDiv">
											<input class='form-control' type="text" name="coupon.condition" value="${registerGift.coupon.condition}"/>
										</div>
										<span class="input-group-addon">元(起)使用</span> <span
											class="input-group-addon"> <label class="radio-inline">无条件使用
												<input type="radio" name="conditionRadio" value="0"
												id="customConditionRadio1" onchange="conditionInputReadOnly();" <c:if test="${registerGift.coupon.condition==0}"> checked="checked"</c:if> <c:if test="${empty registerGift.coupon}">checked</c:if> />
										</label>
										</span>
									</div>
							</td>
						</tr>
						<tr class="coupon">
							<td style="width:20%;"><h5>&nbsp;&nbsp;有效期限(天):</h5></td>
							<td  align="left">
								<h5>
								<input name="coupon.dayNum" type="text" value="${registerGift.coupon.dayNum}" id="dayNum" class="form-control" placeholder="有效期" style="width:200px"/> 
								</h5>
							</td>
						</tr>
						<tr class="integral">
							<td style="width:20%;"><h5>&nbsp;&nbsp;面额:</h5></td>
							<td  align="left">
								<h5>
								<input name="quota" type="text" value="${registerGift.quota}" id="quota" class="form-control" placeholder="积分(鸟豆)面额" style="width:200px"/> 
								</h5>
							</td>
						</tr>
						<tr style="height:20px "></tr>
		 				<tr>
		 					<td colspan="2" style="text-align: center;"> 
		 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存    </button>
								<button type="button" class="btn btn-success" id="backId"><span class="icon-reply-all"></span>  取  消  </button>
		 					</td>
		 				</tr>
			 			</tbody>
			 		</table>
	 				</form>
	 			</div>
			</div>
		</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/js/user_terminal/editRegisterGift.js"></script>

</body>
</html>

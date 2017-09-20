<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>编辑直播粉丝券信息</title>
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
	border-bottom: none !important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form id="editForm">
					<input type="hidden"  name="addToken" value="${addToken}">
						<c:if test="${!empty coupon}">
							<input type="hidden" name="cid" id="cid" value="${coupon.cid}">
						</c:if>
						<table class="table" style="text-align: center;width:80%">
							<tr>
								<td class="sellerTitleCss">
									<h5>券名:<span style="color:red;">*</span></h5></td>
								<td >
									<input type="text"  class="form-control" name="cname" value="${coupon.cname}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>关联商户:<span style="color:red;">*</span></h5></td>
								<td >
									<select class="form-control" id="sellerid" name="sellerid"
										initValue="${coupon.sellerid}" style="width:100%;"></select>
            
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>设置价格(元):<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="denomination" id = "denomination" 
									value="${coupon.denomination}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>原价(元):<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="originalPrice" id = "originalPrice" 
									value="${coupon.originalPrice}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>粉丝券数量:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="defaultMaxNum" id="defaultMaxNum" 
									value="${coupon.defaultMaxNum}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>粉丝券描述:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="validityDesc" id="validityDesc" 
									value="${coupon.validityDesc}">
								</td>
							</tr>
							
							<!-- 赠送预售抵用券 -->
							<tr>
								<td class="sellerTitleCss">
									<h5>赠送预售抵用券:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="col-md-3">
										<input name="haveFree" value="1" type="radio" ${coupon.haveFree==1?"checked":""} ><span style="font-size: 12px;">是</span>
										<input name="haveFree" value="2" type="radio" ${coupon.haveFree==2?"checked":""} ><span style="font-size: 12px;">否</span>
									</div>
								</td>
							</tr>
							
							<tr id="voucherTr">
								<td class="sellerTitleCss">
									<h5>设置抵用券面值:<span style="color:red;">*</span></h5>
								</td>
								<td class="sellerContentCss" align="left">
									<div id="datas">
										<div class="col-sm-6 plandiv" id="plandiv">
												<c:if test="${empty coupon.voucherList}">
												<div class="input-group" >
													<input  type="text"	name="voucherList[0].denomination" id="" class="form-control" style="width:175px;" onblur="priceBlur(this)"/>
													<span	class="input-group-addon">元 &nbsp;&nbsp;&nbsp;&nbsp;满</span> 
													<input  type="text"	name="voucherList[0].condition" id="" class="form-control" style="width:175px;" onblur="retailBlur(this)"/>
													<span	class="input-group-addon">元可用</span> 
													<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>
													<span   class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
													</span>
													</div>
												</c:if>
												<c:if test="${!empty coupon.voucherList}">
													<c:forEach items="${coupon.voucherList}" var="voucher" varStatus="status">
													<div class="input-group" >
														<input  type="hidden"	name="voucherList[${status.index}].id" id="" class="form-control"   value="${voucher.id}" >
														<input  type="hidden"	name="voucherList[${status.index}].cid" id="" class="form-control"   value="${voucher.cid}" >
														<input  type="hidden"	name="voucherList[${status.index}].giftCid" id="" class="form-control"   value="${voucher.giftCid}" >
														<input  type="text"	name="voucherList[${status.index}].denomination" id="" class="form-control" style="width:175px;" onblur="priceBlur(this)" value="${voucher.denomination}" >
														<span	class="input-group-addon">元 &nbsp;&nbsp;&nbsp;&nbsp;满</span> 
														<input  type="text"	name="voucherList[${status.index}].condition" id="" class="form-control" style="width:175px;" onblur="retailBlur(this)" value="${voucher.condition}">
														<span	class="input-group-addon">元可用</span> 
														<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus" style="cursor:pointer"></i></span>
														<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
														</span>
														</div>
													</c:forEach>
												</c:if>
										</div>	
									</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>销售时间:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
									<input type="text" class="form-control form-datetime" id="saleStartTime" readonly="readonly"
										name="saleStartTime" value="${coupon.saleStartTimeStr}">
									<span class="input-group-addon fix-border">--</span> <input
										type="text" class="form-control form-datetime" id="saleEndTime"  readonly="readonly"
										name="saleEndTime" value="${coupon.saleEndTimeStr}">
								</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>使用时间:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
									<input type="text" class="form-control form-datetime" id="startDate"  readonly="readonly"
										name="startDate" value="${coupon.startDateStr}">
									<span class="input-group-addon fix-border">--</span> <input
										type="text" class="form-control form-datetime" id="endDate"  readonly="readonly"
										name="endDate" value="${coupon.endDateStr}">
								</div>
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss">
									<h5>不可用时间:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
									<input type="text" class="form-control form-datetime" id="forbidStartTime"  readonly="readonly"
										name="forbidStartTime" value="${coupon.forbidStartTimeStr}">
									<span class="input-group-addon fix-border">--</span> <input
										type="text" class="form-control form-datetime" id="forbidEndTime"  readonly="readonly"
										name="forbidEndTime" value="${coupon.forbidEndTimeStr}">
								</div>
								</td>
							</tr>
							
							<tr id="htmlContentTr">
								<td class="sellerTitleCss">
									<h5>粉丝券描述H5内容:<span style="color:red;">*</span></h5></td>
								<td class="htmlContentCss">
									<textarea class="ckeditor" id="fansDescription" name="fansDescription">${coupon.fansDescription }</textarea>
								</td>
							</tr>
							
							<tr style="display:none;">
								<td class="sellerTitleCss">
									<h5>预告介绍:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="introduce" id="introduce" 
									value="${coupon.introduce}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>使用须知:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="rule" id="rule" 
									value="${coupon.rule}">
								</td>
							</tr>
							
							<tr class="on-off">
								<td class="sellerTitleCss" >
									<h5>预售状态:<span style="color:red;">*</span></h5></td>
								<td >
										<select class="form-control" id="sendStatus" name="sendStatus" style="width:100%;">
											<option value="1" ${couponAnchor.sendStatus==1?"selected":""}>出售中</option>
											<option value="2" ${couponAnchor.sendStatus==2?"selected":""}>下架</option>
											<option value="3" ${couponAnchor.sendStatus==3?"selected":""}>已售罄</option>
										</select> 

								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>是否重点推荐:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="col-md-3">
										<input name="isRecom" value="1" type="radio" ${coupon.isRecom==1?"checked":""} ><span style="font-size: 12px;">是</span>
										<input name="isRecom" value="0" type="radio" ${coupon.isRecom==0?"checked":""} ><span style="font-size: 12px;">否</span>
									</div>
								</td>
							</tr>
							

							<tr id="dayNumTr" style="display:none;">
								<td colspan="2">
									<table class="table" style="text-align: center;width:100%">
										<tr>
											<td>
												<h5>抵用券有效期:<span style="color:red;">*</span></h5>
											</td>
											<td>
												<select class="form-control" name="dayNum">
														<option value="">--请选择--</option>
														<option value="15" <c:if test="${coupon.dayNum==15}">selected</c:if>>半个月</option>
														<option value="30" <c:if test="${coupon.dayNum==30}">selected</c:if>>1个月</option>
														<option value="60" <c:if test="${coupon.dayNum==60}">selected</c:if>>2个月</option>
														<option value="90" <c:if test="${coupon.dayNum==90}">selected</c:if>>3个月</option>
														<option value="120" <c:if test="${coupon.dayNum==120}">selected</c:if>>4个月</option>
														<option value="150" <c:if test="${coupon.dayNum==150}">selected</c:if>>5个月</option>
														<option value="180" <c:if test="${coupon.dayNum==180}">selected</c:if>>6个月</option>
												</select>
											</td>
											<td style="text-align:right;">
												<h5>购买当天可用:<span style="color:red;">*</span></h5></td>
											<td>
													<input name="invalidToday" value="0" type="radio" ${coupon.invalidToday==0?"checked":""} ><span style="font-size: 12px;">是</span>
													<input name="invalidToday" value="1" type="radio" ${coupon.invalidToday==1?"checked":""} ><span style="font-size: 12px;">否</span>
											</td>
										</tr>
									</table>
								 </td>
							</tr>
							
						</table>
						<div align="center">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button"
								onclick="window.history.back();">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="hidden dataTemp">
		<div class="input-group">
			<input  type="text"	name="voucherList[index].denomination" id="" class="form-control" style="width:175px;" onblur="priceBlur(this)"/>
			<span	class="input-group-addon">元 &nbsp;&nbsp;&nbsp;&nbsp;满</span>  
			<input  type="text"	name="voucherList[index].condition" id="" class="form-control" style="width:175px;" onblur="retailBlur(this)"/>
			<span	class="input-group-addon">元可用</span> 
			<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>
			<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
			</span>
		</div>
	</div>

	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	
	<script src="<%=path%>/js/live_anchor/couponEdit.js"></script>
	
</body>
</html>

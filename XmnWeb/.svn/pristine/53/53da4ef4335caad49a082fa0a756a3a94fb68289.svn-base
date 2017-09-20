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
<title>编辑积分商品信息</title>
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
<%-- <link href="<%=path%>/ux/kindeditor/kindeditor.min.css" rel="stylesheet"> --%>
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
					<form id="multipShopForm">
						<input type="hidden" name="multipShopToken"
							value="${bargainProductToken}"> <input type="hidden"
							id="isType" name="isType" value="${isType}" /> <input
							type="hidden" id="bpid" name="bpid"
							value="${bargainProduct.bpid}" /> <input type="hidden"
							id="selleridid" name="sellerid"
							value="${bargainProduct.sellerid}" /> <input type="hidden"
							id="quotanumInit" name="quotanumInit"
							value="${bargainProduct.quotanum}" />
							<input type="hidden" name="user"
							value="3">
						<table class="table" style="text-align: center;width:100%">
							<tr>
								<td class="sellerTitleCss" style="width:10%;">
									<h5>特价活动标题:</h5></td>
								<td style="width:25%;"><input type="text"
									class="form-control" name="activityname"
									value="${bargainProduct.activityname}">
								</td>
								<td class="sellerTitleCss" style="width:10%;">
									<h5>原价(元):</h5></td>
								<td class="sellerContentCss" style="width:20%;"><input type="text"
									class="form-control" name="originalprice" id = "originalprice" 
									value="${bargainProduct.originalprice}">
								</td>
								<td class="sellerTitleCss" style="width:10%;">
									<h5>是否支持返利:</h5></td>
								<td class="sellerContentCss" style="width:20%;"><select class="form-control"
									name="isrebate" value="${bargainProduct.isrebate}">
										<option value="">请选择</option>
										<option value="0"
											<c:if test="${bargainProduct.isrebate==0}">selected</c:if>>否</option>
										<option value="1"
											<c:if test="${bargainProduct.isrebate==1}">selected</c:if>>是</option>
								</select></td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>积分商品名称:</h5></td>
								<td ><input type="text"
									class="form-control" name="pname"
									value="${bargainProduct.pname}">
								</td>
								<td class="sellerTitleCss">
									<h5>采购价(元):</h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="purchasePrice" id="purchasePrice" 
									value="${bargainProduct.purchasePrice}">
								</td>
								<td class="sellerTitleCss">
									<h5>是否支持退款:</h5></td>
								<td class="sellerContentCss"><select class="form-control"
									name="refund" value="${bargainProduct.refund}">
										<option value="">--请选择--</option>
										<option value="0"
											<c:if test="${bargainProduct.refund==0}">selected</c:if>>不可以</option>
										<option value="1"
											<c:if test="${bargainProduct.refund==1}">selected</c:if>>可以</option>
								</select></td>
								
							</tr>

							<tr id="id_sellerid">
								<td class="sellerTitleCss">
									<h5>商家名称</br><font color="red">(输入商家名模糊搜索):</font></h5>
								</td>
							 	<td class="sellerContentCss">
									<label id="checkids"></label>
							 		<select class="form-control" id="sellerid" name="sellerid" initValue="${bargainProduct.sellerid}" style="width:100%;"></select>
									<input type="hidden" form-control" name="sellername" value="${bargainProduct.sellername}">
									<!-- 下面一个隐藏域作用是记录用户在点击修改某个爆品时原始的商家ID，以供用户通过模糊查询选择商家的时候作同一个商家不能添加2次以上的限制 -->
									<input type="hidden" id="originalSellerId" value="${bargainProduct.sellerid}">
								</td>
								<td class="sellerTitleCss">
									<h5>现价(元):</h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" id="price" name="price" readonly="readonly" 
									value="${bargainProduct.price}">
								</td>
								<td class="sellerTitleCss">
									<h5>状态:</h5></td>
								<td class="sellerContentCss"><select class="form-control"
									name="status" value="${bargainProduct.status}">
										<option value="0"
											<c:if test="${bargainProduct.status==0}">selected</c:if>>下架</option>
										<option value="1"
											<c:if test="${bargainProduct.status==1}">selected</c:if>>上架</option>
										<option value="2"
											<c:if test="${bargainProduct.status==2}">selected</c:if>>审核中</option>
										<option value="3"
											<c:if test="${bargainProduct.status==3}">selected</c:if>>不通过</option>
								</select></td>
							</tr>
							<tr>
							
								<td class="sellerTitleCss">
									<h5>是否限量:</h5></td>
								<td>
								<h5 style="float:left;">
										<input type="hidden" id="quotaValue" value="${bargainProduct.quota}"> 
										<label><input type="radio" value="0" name="quota" 
											<c:if test="${empty bargainProduct.quota || bargainProduct.quota==0 }">checked="checked"</c:if>
											value="${bargainProduct.activityname}">&nbsp;不限制&nbsp;</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label><input type="radio" value="1" name="quota"
											<c:if test="${bargainProduct.quota==1}">checked="checked"</c:if>
											value="${bargainProduct.activityname}">&nbsp;限制</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									</h5>
									<h5>
									<div class="input-group" id="allNum" <c:if test="${empty bargainProduct.quota || bargainProduct.quota==0 }">style="display:none"</c:if>>
										<input type="text" name="quotanum" class="form-control" />
										<span class="input-group-addon">份</span>
										<span class="input-group-addon">
										<label class="radio-inline">总限量 <input type="radio"
												name="everyday" value="0" 
												<c:if test="${empty bargainProduct.everyday || bargainProduct.everyday==0 }">checked="checked"</c:if> >
										</label>
										<label class="radio-inline">每天限量
												<input type="radio" name="everyday" value="1" 
												<c:if test="${bargainProduct.everyday==1 }">checked="checked"</c:if> >
										</label>
										</span>
									</div>
									</h5>
								</td>
								<td class="sellerTitleCss">
									<h5>销售日期:</h5></td>
								<td colspan="1"><input type="text"
									class="form-control form-datetime"
									data-date-format="yyyy-mm-dd" name="startdate"
									placeholder="开始日期" style="width:46%;float:left"
									value="<fmt:formatDate value="${bargainProduct.startdate}" pattern="yyyy-MM-dd"/>">
									<label style="float: left;">&nbsp;--&nbsp;</label> <input
									type="text" class="form-control form-datetime"
									data-date-format="yyyy-mm-dd" name="enddate" placeholder="结束日期"
									style="width:46%;float:left"
									value="<fmt:formatDate value="${bargainProduct.enddate}" pattern="yyyy-MM-dd"/>">
								</td>


								<td class="sellerTitleCss">
									<h5>销售时间:</h5></td>
								<td colspan="1">
									<div class="form-group" id="dates">
										<div>
											<div class="col-md-8" style="width:100%; float: left;">
												<c:if test="${!empty bargainProduct.bargainPrice}">
													<c:forEach items="${bargainProduct.bargainPrice}"
														var="bargainPrice" varStatus="status">
														<div class="input-group">
															<span class="input-group-addon">开始时间：</span> <input
																type="text" readonly
																name="bargainPrice[${status.index}].startTime"
																class="time-start form-control "
																value="${bargainPrice.startTimeText}" /> <span
																class="input-group-addon">结束时间：</span> <input
																type="text" readonly
																name="bargainPrice[${status.index}].endTime"
																class="time-end form-control"
																value="${bargainPrice.endTimeText}" /> <span
																class="input-group-addon"><i
																class="icon icon-plus" style="cursor:pointer"></i>
															</span> <span class="input-group-addon"><i
																class="icon icon-minus" style="cursor:pointer"></i>
															</span>
														</div>
													</c:forEach>
												</c:if>
												<c:if test="${empty bargainProduct.bargainPrice}">
													<div class="input-group">
														<span class="input-group-addon">开始时间：</span> <input
															type="text" readonly name="bargainPrice[0].startTime"
															class="form-control time-start" /> <span
															class="input-group-addon">结束时间：</span> <input type="text"
															readonly name="bargainPrice[0].endTime"
															class="form-control time-end" /> <span
															class="input-group-addon"><i
															class="icon icon-plus" style="cursor:pointer"></i>
														</span> <span class="input-group-addon"><i
															class="icon icon-minus" style="cursor:pointer"></i>
														</span>
													</div>
												</c:if>
											</div>
										</div>
									</div></td>


								<%-- <td class="sellerTitleCss">
									<h5>销售时间:</h5>
								</td>
								<td colspan="1">
									<input	type="text"  class="form-control form-time"	name="startdate" placeholder="开始时间"	style="width:46%;float:left"
										placeholder="00:00" value="<fmt:formatDate value="${bargainProduct.startdate}" />">
									<label style="float: left;">&nbsp;--&nbsp;</label> 
									<input	type="text" class="form-control form-time"	name="enddate" placeholder="结束时间"	style="width:46%;float:left"
										 value="<fmt:formatDate value="${bargainProduct.enddate}" />">
								</td> --%>
							</tr>

							<tr>
								<td class="sellerTitleCss">
									<h5>商品图片:</h5></td>
								<td class="sellerContentCss">
									<div class="col-md-9">
										<div id='productImage' ImgValidate="true"></div>
										<input type="hidden" id="image" name="image"
											value="${bargainProduct.image}" />
									</div>
								</td>
								<td class="sellerTitleCss">
									<h5>积分价:</h5>
								</td>
								<td class="sellerContentCss" align="left" colspan="3">
								<input type="text"class="form-control" style="width:10%" id="cash" name="cash" readonly="true" value="${bargainProduct.cash}">元<font color="red" class="removecc">(人民币)&nbsp;&nbsp;</font>&nbsp;+&nbsp;
								<input type="text"class="form-control" style="width:10%" id="integral" name="integral" readonly="true" value="${bargainProduct.integral}">个<font color="red" class="removecc">(积分)</font>
								</td>
							</tr>
<%--							
							<tr>
								<td class="sellerTitleCss">
									<h5>详情介绍:</h5></td>
								<td class="sellerContentCss" colspan="4">
									<div class="col-md-9">
										<div class="block-content collapse in">
											<textarea id="editor_id" name="content" style="width:700px;height:300px;">${bargainProduct.html}</textarea>
						                </div>
									</div>
								</td>
							</tr>
--%>							
							<tr>
								<td class="sellerTitleCss">
									<h5>详情介绍:</h5></td>
								<td class="sellerContentCss" colspan="4">
									<div class="col-md-9">
										<div class="block-content collapse in">
										     <textarea id="ckeditor_standard" class="ckeditor" name="html" width="">${bargainProduct.html}</textarea>
						                </div>
									</div>
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

	<div class="hidden dateTemp">
		<div class="input-group" style="width:100%;float:left">
			<span class="input-group-addon">开始时间：</span> <input type="text"
				readonly name="bargainPrice[index].startTime"
				class="form-control time-start" /> <span class="input-group-addon">结束时间：</span>
			<input type="text" name="bargainPrice[index].endTime" readonly
				class="form-control time-end" /> <span class="input-group-addon"><i
				class="icon icon-plus" style="cursor:pointer"></i>
			</span> <span class="input-group-addon"><i class="icon icon-minus"
				style="cursor:pointer"></i>
			</span>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/marketingmanagement/editBargainProduct.js"></script>
	
	<!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>	

<%--	
	<script src="<%=path%>/ux/kindeditor/kindeditor.min.js"></script>
	<script src="<%=path%>/ux/kindeditor/lang/zh_CN.js"></script>
--%>	
</body>
</html>

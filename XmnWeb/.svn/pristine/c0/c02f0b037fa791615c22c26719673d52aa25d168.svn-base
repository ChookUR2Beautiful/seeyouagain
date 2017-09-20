<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加套餐</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">添加套餐</div>
				<div class="panel-body">
					<form id="sellerPackageForm" role="form" class="form-horizontal">
						<input type="hidden" name="multipShopToken" value="${multipShopToken}">
						<input type="hidden" id="isType" name="isType" value="${isType}"/> 
						<input type="hidden" id="id" name="id" value="${myPackageInfo.id}"/>
						
						<div class="form-group">
							<label class="col-md-3 control-label">套餐标题：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="title" name="title"
									value="${myPackageInfo.title}" style="width:41%;float:left">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">关联商户: <span
								style="color:red;">*</span></label>
						    <div class="col-md-7">
									<select class="form-control" id="sellerId" name="sellerid" 
										initValue="${myPackageInfo.sellerid}" style="width:41%;float:left"></select>
							</div>
						</div>
						<hr />
						
						<div class="form-group">
							<label class="col-md-3 control-label">设置价格(元): <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="sellingPrice" name="sellingPrice"
									value="${myPackageInfo.sellingPrice}" style="width:41%;float:left">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">设置价格(鸟币): <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="sellingCoinPrice" name="sellingCoinPrice"
									value="${myPackageInfo.sellingCoinPrice}" style="width:41%;float:left">
							</div> 
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">原价(元): <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="originalPrice" name="originalPrice"
									value="${myPackageInfo.originalPrice}" style="width:41%;float:left">
							</div>
						</div>
						<hr />
						
						<div class="form-group">
							<label class="col-md-3 control-label">套餐数量: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="stock" name="stock"
									value="${myPackageInfo.stock}" style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">分帐: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input  type="radio" name = "ledgerType" value = "1" ${myPackageInfo.ledgerType==1?'checked':''}><span style="font-size: 12px;">固定金额分账</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input  type="radio" name = "ledgerType" value = "2" ${myPackageInfo.ledgerType==2?'checked':''}><span style="font-size: 12px;">按比例分账</span> 
							</div> 
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商户获得: <span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<div class="row">
									<div class="col-md-7">
										<input type="text" class="form-control" id="ledgerRatio"
											name="ledgerRatio" value="${myPackageInfo.ledgerRatio}" style="width: 28%; float: left"> 
											<font id="percent" color="red">&nbsp;&nbsp;(%)</font>
									</div>
								</div>
								<h5><label style="color:red;">*固定金额分账填写具体金额，按比例分账填写百分比，剩余部分均为平台获得</label></h5>
							</div>
						</div>
						<hr />
						
						<div class="form-group">
							<label class="col-md-3 control-label">赠送预售抵用券: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input  type="radio" name = "sendercoupon" value = "1" <c:if test="${!empty myPackageInfo.voucherList}">checked</c:if> ><span style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input  type="radio" name = "sendercoupon" value = "2" ><span style="font-size: 12px;">否</span> 
							</div> 
						</div>
						<div class="form-group" id = "voucherTr">
							<label class="col-md-3 control-label">设置抵用券面值: <span
								style="color:red;">*</span></label>
							<div class="col-md-3" id="datas">
								<div class="plandiv" id="plandiv">  
	                                 <c:if test="${empty myPackageInfo.voucherList}">
										<div class="input-group" style="float:left; margin-left: 2px;">
											<input  type="text"	name="voucherList[0].denomination" id="" class="form-control" style="width:100px;float:left" onblur="priceBlur(this)"/>
											<span	class="input-group-addon" >元 &nbsp;&nbsp;&nbsp;&nbsp;满</span> 
											<input  type="text"	name="voucherList[0].condition" id="" class="form-control" style="width:100px;float:left" onblur="retailBlur(this)"/>
											<span	class="input-group-addon">元可用</span> 
											<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>
											<span   class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer;"></i>
											</span>
										</div>
									</c:if>
									<c:if test="${!empty myPackageInfo.voucherList}">
										<c:forEach items="${myPackageInfo.voucherList}" var="voucher" varStatus="status">
										<div class="input-group" >
											<input  type="hidden"	name="voucherList[${status.index}].id" id="" class="form-control" style="width:100px;"  value="${voucher.id}" >
											<input  type="hidden"	name="voucherList[${status.index}].pid" id="" class="form-control" style="width:100px;"  value="${voucher.pid}" >
											<input  type="hidden"	name="voucherList[${status.index}].giftCid" id="" class="form-control" style="width:100px;"  value="${voucher.giftCid}" >
											<input  type="text"	name="voucherList[${status.index}].denomination" id="" class="form-control" style="width:100px;" onblur="priceBlur(this)" value="${voucher.denomination}" >
											<span	class="input-group-addon">元 &nbsp;&nbsp;&nbsp;&nbsp;满</span> 
											<input  type="text"	name="voucherList[${status.index}].condition" id="" class="form-control" style="width:100px;" onblur="retailBlur(this)" value="${voucher.condition}">
											<span	class="input-group-addon">元可用</span> 
											<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus" style="cursor:pointer"></i></span>
											<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
											</span>
										</div>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
						<hr/>
							
						<div class="form-group">
							<label class="col-md-3 control-label">销售时间: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control form_datetime" name ="saleStartTime" value="<fmt:formatDate value="${myPackageInfo.saleStartTime}"
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:30%;float:left"  readonly="readonly">
								<label style="float: left;">&nbsp;&nbsp;至&nbsp;&nbsp;</label>
								<input type="text" class="form-control form_datetime" name ="saleEndTime" value="<fmt:formatDate value="${myPackageInfo.saleEndTime}" 
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:30%;float:left"  readonly="readonly">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">使用时间: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control form_datetime" name ="useStartTime" value="<fmt:formatDate value="${myPackageInfo.useStartTime}" 
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:30%;float:left"  readonly="readonly">
								<label style="float: left;">&nbsp;&nbsp;至&nbsp;&nbsp;</label>
								<input type="text" class="form-control form_datetime" name ="useEndTime" value="<fmt:formatDate value="${myPackageInfo.useEndTime}" 
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:30%;float:left"  readonly="readonly">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">不可用时间: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control form_datetime" name ="forbidStartTime" value="<fmt:formatDate value="${myPackageInfo.forbidStartTime}" 
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:30%;float:left"  readonly="readonly">
								<label style="float: left;">&nbsp;&nbsp;至&nbsp;&nbsp;</label>
								<input type="text" class="form-control form_datetime" name ="forbidEndTime" value="<fmt:formatDate value="${myPackageInfo.forbidEndTime}" 
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:30%;float:left"  readonly="readonly">
							</div>
						</div>
						<hr/> 
						
						<div class="form-group">
							<label class="col-md-3 control-label">套餐图片: <span
								style="color:red;">*</span></label>
							<div >
							    <div id='picUrlid' style="position:relative; left: 10px; float:left; margin-top: 10px;"> </div>
								<input type="hidden" id="picUrl" name="sellerPackagePicList[0].picUrl"
									value="${myPackageInfo.sellerPackagePicList[0].picUrl}"/>
							</div>
							

							<div >
								<div id='picUrl2id' style="position:relative; float:left; margin-top: 10px; margin-left: 10px;" > </div>
								<input type="hidden" id="picUrl2" name="sellerPackagePicList[1].picUrl"
									value="${myPackageInfo.sellerPackagePicList[1].picUrl}" />
							</div>
							<div >
								<div id='picUrl3id' style="position:relative; float:left; margin-top: 10px; " ></div>
								<input type="hidden" id="picUrl3" name="sellerPackagePicList[2].picUrl"
									value="${myPackageInfo.sellerPackagePicList[2].picUrl}" />
							</div>
							
							<div >
								<div id='picUrl4id' style="position:relative; float: left; margin-top: 10px; " ></div>
								<input type="hidden" id="picUrl4" name="sellerPackagePicList[3].picUrl"
									value="${myPackageInfo.sellerPackagePicList[3].picUrl}" />
							</div>
							<div >
								<div id='picUrl5id' style="position:relative; float:left; margin-top: 10px; " ></div>
								<input type="hidden" id="picUrl5" name="sellerPackagePicList[4].picUrl"
									value="${myPackageInfo.sellerPackagePicList[4].picUrl}" />
							</div>
							<div >
								<div id='picUrl6id' style="position:relative; float:left; margin-top: 10px; " ></div>
								<input type="hidden" id="picUrl6" name="sellerPackagePicList[5].picUrl"
									value="${myPackageInfo.sellerPackagePicList[5].picUrl}" />
							</div>
							
						</div>
						<hr/> 
						
						<div class="form-group">
							<label class="col-md-3 control-label">套餐分类描述: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<textarea id="content" class="ckeditor" name="content">${myPackageInfo.content}</textarea>
							</div> 
						</div>
						<hr />
						
						<div class="form-group">
							<label class="col-md-3 control-label">温馨提示: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<textarea id="notice" name="notice" rows="5" style="width:100%;">${myPackageInfo.notice}</textarea>
							</div> 
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">套餐状态：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<select class="form-control" id="status" name="status"
									style="width:41%;float:left">
								    <option value="">请选择</option> 
									<option value="1" ${myPackageInfo.status==1?"selected":""}>出售</option>
									<option value="2" ${myPackageInfo.status==2?"selected":""}>下架</option>
									<option value="3" ${myPackageInfo.status==3?"selected":""}>已售罄</option>									
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">是否设为重点推荐: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input  type="radio" name = "highlyRecommended" value = "1" ${myPackageInfo.highlyRecommended==1?'checked':''}><span style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input  type="radio" name = "highlyRecommended" value = "0" ${myPackageInfo.highlyRecommended==0?'checked':''}><span style="font-size: 12px;">否</span> 
							</div> 
						</div>
						
						<hr/>
						<div align="center">
							<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" id = "backButton"  onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;重置</button>
						</div>
						
						
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="hidden dataTemp">
		<div class="input-group" style="float:left; margin-left: 2px;">
			<input  type="text"	name="voucherList[index].denomination" id="" class="form-control" style="width:100px;" onblur="priceBlur(this)"/>
			<span	class="input-group-addon">元 &nbsp;&nbsp;&nbsp;&nbsp;满</span>  
			<input  type="text"	name="voucherList[index].condition" id="" class="form-control" style="width:100px;" onblur="retailBlur(this)"/>
			<span	class="input-group-addon">元可用</span> 
			<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>
			<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
			</span>
		</div>
	</div>
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
    
    <!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
    
    <script src="<%=path%>/js/live_anchor/sellerPackage/editSellerPackage.js"></script> 
</body>
</html>

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
							<input type="hidden" name="multipShopToken" value="${bargainProductToken}"> 
							<input type="hidden" id="isType" name="isType" value="${isType}" /> 
							<input type="hidden" id="bpid" name="bpid" value="${bargainProduct.bpid}" /> 
							<input type="hidden" id="selleridid" name="sellerid" value="${bargainProduct.sellerid}" /> 
							<!-- 是否限额，0不限制，1限制 -->
							<input type="hidden" name="quota" value="0">
							<!-- 限制数量，不限制填0 -->
							<input type="hidden" id="quotanumInit" name="quotanumInit" value="${bargainProduct.quotanum}" />
							<!-- 针对用户，1新用户，2老用户，3所有用户 -->
							<input type="hidden" name="user" value="3">
						<table class="table" style="text-align: center;width:50%">
							<tr id="sellerInfoDiv">
								<td class="sellerTitleCss">
									<h5>商家名称:</br><font color="red">(输入商家名模糊搜索)</font></h5>
								</td>
							 	<td class="sellerContentCss">
									<label id="checkids"></label>
							 		<select class="form-control" id="sellerid" name="sellerid" initValue="${bargainProduct.sellerid}">
							 		</select>
									<input type="hidden" form-control" name="sellername" value="${bargainProduct.sellername}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>商品名称:</h5></td>
								<td >
									<input type="text"  class="form-control" name="pname" value="${bargainProduct.pname}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>原价(元):</h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="originalprice" id = "originalprice" 
									value="${bargainProduct.originalprice}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>结算价(元):</h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="purchasePrice" id="purchasePrice" 
									value="${bargainProduct.purchasePrice}">
								</td>
							</tr>

							<tr>
								<td class="sellerTitleCss">
									<h5>状态:</h5></td>
								<td class="sellerContentCss" >
									<select class="form-control" name="status" value="${bargainProduct.status}">
											<option value="0"
												<c:if test="${bargainProduct.status==0}">selected</c:if>>下架</option>
											<option value="1"
												<c:if test="${bargainProduct.status==1}">selected</c:if>>上架</option>
											<option value="2"
												<c:if test="${bargainProduct.status==2}">selected</c:if>>审核中</option>
											<option value="3"
												<c:if test="${bargainProduct.status==3}">selected</c:if>>不通过</option>
									</select>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>积分价:</h5>
								</td>
								<td class="sellerContentCss" align="left">
								<input type="text"class="form-control" style="width:36%" id="cash" name="cash" readonly="true" value="${bargainProduct.cash}">元<font color="red" class="removecc">(人民币)&nbsp;&nbsp;</font>&nbsp;+&nbsp;
								<input type="text"class="form-control" style="width:36%" id="integral" name="integral" readonly="true" value="${bargainProduct.integral}">个<font color="red" class="removecc">(积分)</font>
								</td>
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

	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/marketingmanagement/bargainProductEdit.js?v=1.0.2"></script>
	
</body>
</html>

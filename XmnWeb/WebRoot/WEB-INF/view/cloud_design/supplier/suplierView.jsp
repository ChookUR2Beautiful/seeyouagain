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
<title>添加连锁店信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
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
				<div class="panel-heading">添加供应商/设计单位</div>
				<div class="panel-body">
					<form id="multipShopForm">
					<input type="hidden" id="supplierId" name="supplierId" value="${supplier.supplierId}" />
					<input type="hidden" id="isType" name="isType" value="${isType}"/>
						<table class="table" style="text-align: center;">
							<tr>
								<td class="sellerTitleCss" align="left">
									<h5>类型：</h5>
								</td>

								<td class="sellerTitleCss" style="text-align: left;"><select
									class="form-control" id="type" name="type"
									style="width:100%;"  initValue="${supplier.type}">
										<option value="1" <c:if test="${supplier.type == 1}">selected</c:if>>供应商</option>
										<option value="2" <c:if test="${supplier.type == 2}">selected</c:if>>设计单位</option>
										<option value="3" <c:if test="${supplier.type == 3}">selected</c:if>>个人</option>
								</select></td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left">
									<h5>单位:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="name" placeholder="单位名称"
									value="${supplier.name}"></td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left">
									<h5>单位所在地:</h5>
								</td>
								<td class="sellerContentCss">
									<div class="input-group" style="width:100%; text-align: left"
										id="areaSelect" initValue="${supplier.area}"></div>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left">
									<h5>地址:</h5>
								</td>
								<td><input type="text" class="form-control" name="address"
									placeholder="地址" value="${supplier.address}"></td>
							</tr>
							<tr>

								<td class="sellerTitleCss" align="left">
									<h5>负责人:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="contacts" placeholder="请填写真实姓名"
									value="${supplier.contacts}"></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="mobile" placeholder="请填写目前联系方式"
									value="${supplier.mobile}"></td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left">
									<h5>其他联系方式:</h5>
								</td>
								<td><input type="text" class="form-control"
									name="wxin" placeholder="微信号" value="${supplier.wxin}"></td>
								<td><input type="text" class="form-control" name="qq"
									placeholder="QQ号" value="${supplier.qq}"></td>
							</tr>
							<tr>
								<td class="sellerTitleCss" align="left">
									<h5>联系邮箱:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="email" placeholder="邮箱"
									value="${supplier.email}"></td>
							</tr>

						</table style="text-align: center;">
						<hr>
						<table class="table">
							<tr>
								<td class="sellerTitleCss" align="left"  width="10%"><h5>运费设置</h5></td>
							</tr>
							<tr>
								<td align="left">
									<h5>运费模板:</h5>
								</td>
								<td class="sellerContentCss"><select class="form-control"
									id="postTemplateId" name="postTemplateId" style="width:100%;"
									initValue="${supplier.postTemplateId}">
										<option value="">=====请选择====</option>
										<c:forEach items="${postTemplate}" var="template">
										<c:if test="${template.supplierId == null or template.id == supplier.postTemplateId}">
										<option value="${template.id}" <c:if test="${template.id eq supplier.postTemplateId}">selected</c:if>>${template.templateName}</option>
										</c:if>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
							<td></td>
							<td align="left"><a><font size="1" color="gray">刷新|</font></a>
							<c:if test="${!empty btnAu['transportFee/manager/add'] }">
							<a href="javascript:addTempalte();"> <font size="1" color="blue">新建运费模板</font></a>
							</c:if>
							<c:if test="${empty btnAu['transportFee/manager/add'] }">
							<a href="javascript:;"> <font size="1" color="gray">新建运费模板</font></a>
							</c:if>
							</td>
							</tr>
						</table>
						<hr>
						<table class="table" style="text-align: center;">
							<div align="center">
								<button class="btn btn-danger" type="submit">
									<i class="icon-save"></i>&nbsp;保 存
								</button>
								&nbsp;&nbsp;
								<button class="btn btn-warning" type="button"
									onclick="goBack()">
									<i class="icon-reply"></i>&nbsp;取消
								</button>
							</div>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{add:'${ btnAu['supplier/manager/add']}'}</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
		function goBack(){
			history.back();
		}
		
		function addTempalte(){
			window.location.href=contextPath+"/transportFee/manage/addView.jhtml";
		}
		
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/cloud_design/supplier/editSupplier.js"></script>
</body>
</html>

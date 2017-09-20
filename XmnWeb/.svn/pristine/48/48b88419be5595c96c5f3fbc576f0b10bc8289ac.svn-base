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
<title>编辑SaaS文章信息</title>
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

<link href="<%=path%>/resources/webuploader/webuploader.image.css?v=1.0.1" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
}

.htmlContentCss {
	width: 73% !important;
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
						<input type="hidden" name="isType" id="isType" value="${isType}">
						<input type="hidden" name="id" id="id" value="${article.id}">
						<input type="hidden" name="status" id="status" value="${article.status}">
						<table class="table" style="text-align: center;width:80%">
							<tr>
								<td class="sellerTitleCss">
									<h5>文章名称:<span style="color:red;">*</span></h5></td>
								<td >
									<input type="text"  class="form-control" name="name" value="${article.name}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>文章分类:<span style="color:red;">*</span></h5></td>
								<td >
									<select class="form-control" name="type">
										<option value="">--请选择--</option>
										<option value="1" ${article.type==1?"selected":""}>名嘴食评</option>
										<option value="2" ${article.type==2?"selected":""}>网红晒图</option>
									</select>
								</td>
							</tr>
							
							<tr id="imageTr">
								<td class="sellerTitleCss">
									<h5>缩略图:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input type="hidden" class="form-control" id="image" name="image"  value="${article.image}">
												<div id="imageDiv"></div>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>关联作者:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<select class="form-control" id="celebrityId" name="celebrityId"  style="width:100%;" initValue="${article.celebrityId}">
									</select>
									<input type="hidden" class="form-control" id="celebrityName" name="celebrityName" value="${article.celebrityName}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>关联商户:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<select class="form-control" id="sellerId" name="sellerId"  style="width:100%;" initValue="${article.sellerId}">
									</select>
									<!-- 商户名称 -->
									<input type="hidden" class="form-control" id="sellerName" name="sellerName" value="${article.sellerName}">
									<!-- 商圈名称 -->
									<input type="hidden" class="form-control" id="zoneName" name="zoneName" value="${article.zoneName}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>关联订单号:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<%-- <select class="form-control" id="orderNo" name="orderNo"  style="width:100%;" initValue="${article.orderNo}" placeholder="请选择关联的订单号">
									</select> --%>
									<input type="text" class="form-control" name="orderNo" id="orderNo"  value="${article.orderNo}" placeholder="请填写关联的订单号">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>观看人数:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="views" id="views" 
									value="${article.views}">
								</td>
							</tr>
							
							<tr id="htmlContentTr">
								<td class="sellerTitleCss">
									<h5>文章详情:<span style="color:red;">*</span></h5></td>
								<td class="htmlContentCss">
									<textarea class="ckeditor" id="ckeditor_standard" name="htmlContent">${article.htmlContent }</textarea>
								</td>
							</tr>
							
							<tr id="photoTr">
								<td class="sellerTitleCss">
									<h5>相册详情:<span style="color:red;">*</span></h5></td>
								<td class="htmlContentCss">
									<div id="wrapper">
												<div class="page-body">
													<div id="post-container" class="container">
													    <div class="page-container">
															<div id="uploader" class="wu-example">
															    <div class="queueList">
															        <div id="dndArea" class="placeholder">
															            <div id="filePicker"></div>
															            <p>或将照片拖到这里，单次最多可选300张</p>
															        </div>
															    </div>
															    <div class="statusBar" style="display:none;">
															        <div class="progress">
															            <span class="text">0%</span>
															            <span class="percentage"></span>
															        </div><div class="info"></div>
															        <div class="btns">
															            <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
															        </div>
															    </div>
															    <input type="hidden" id="relativePath" name="relativePath"/>
															</div>
													    </div>
													</div>
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
	
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	
	<!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	
	<script src="<%=path%>/js/business_statistics/saasArticleEdit.js"></script>
	
</body>
</html>

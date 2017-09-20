<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加专区商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/uploader/zui.uploader.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none !important;
	margin: 0;
}

input[type="number"] {
	-moz-appearance: textfield;
}
</style>
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"
		value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="panel panel-primary">
			<div class="panel-heading">产品基本信息</div>
			<div class="panel-body">
				<form id="editFrom" role="form"
					class="form-horizontal scrollbar-hover">
					<input type="hidden" value="${mallPackage.id}" name="id"
						id="fashionTicketId" />
					<div class="form-group">
						<label class="col-md-3 control-label">产品名称：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="title" name="title"
								value="${mallPackage.title}" style="width:60%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">产品原价：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="originalPrice"
								name="originalPrice" value="${mallPackage.originalPrice}"
								style="width:60%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">产品排序：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="sort" name="sort"
								value="${mallPackage.sort}" style="width:60%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">鸟币售价：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="price" name="price"
								value="${mallPackage.price}" style="width:60%;float:left" /><label
								class="col-md-3 control-label">*只支持鸟币</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">缩略图：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="hidden" class="form-control" id="packageImgMine"
								name="packageImgMine" value="${mallPackage.packageImgMine}">
							<div id="packageImgMineImg"></div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">产品图：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<button type="button" id="toAddBtn" class="btn btn-info icon-plus"
								data-type="ajax" 
								data-title="添加相册"
								data-url="imageAddBatch/init.jhtml"
								data-toggle="modal" data-width="1000px">&nbsp;添加</button>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-7" id="imgData">
							<c:forEach items="${mallPackage.imgUrlsArr}" var="imgUrl">
								<img  style="width:100px;height:100px;"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP %>${imgUrl}" initValue="${imgUrl}" />
							</c:forEach>
						</div>
					</div>

					<div id="details">
						<div id="videoBody">
							<div class="form-group">
								<label class="col-md-3 control-label">商品：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<button type="button" class="btn btn-success" data-type="ajax"
										data-url="fresh/mallPackage/init/product.jhtml"
										data-toggle="modal" data-width="950px">
										<span class="icon-plus"></span>&nbsp;添加
									</button>
								</div>
							</div>
							<d iv class="form-group"> <label
								class="col-md-3 control-label"></label>
							<div class="col-md-7">
								<table
									class="table table-hover table-bordered table-striped info">
									<thead>
										<tr>
											<th style="width:130px;">关联产品名称</th>
											<th style="width:130px;">产品售价</th>
											<th style="width:130px;">产品库存</th>
											<th style="width:130px;">规格</th>
											<th style="width:130px;">数量</th>
											<th style="width:130px;">操作</th>
										</tr>
									</thead>
									<tbody id="productList">
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">套餐描述：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<div class="block-content collapse in">
								<textarea id="content" class="ckeditor" name="content" width="">${mallPackage.html}</textarea>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">产品状态：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<select class="form-control" name="status">
								<option value="0" ${mallPackage.status==0?'selected="selected"':''} >待上线</option>
								<option value="1" ${mallPackage.status==1?'selected="selected"':''}>已上线</option>
								<option value="2" ${mallPackage.status==2?'selected="selected"':''}>已售罄</option>
								<option value="3" ${mallPackage.status==3?'selected="selected"':''}>已下线</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">购买后获得等级：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<select  class="form-control" style="width:30%;float: left;" id="rankType" name="rankType" initValue="${mallPackage.rankId}" >
								<option value="0" ${mallPackage.rankId==0?'selected="selected"':''}>无等级</option>
								<option value="2" ${mallPackage.rankId!=0&&mallPackage.rankId!=null?'selected="selected"':''}  >V客类型</option>
							</select>
							
							<select  class="form-control" style="width:30%" id="rankId" name="rankId" >
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="text-center" style="padding:10px 0 10px 0;">
							<button type="submit" class="btn btn-success" id="ensure">
								<span class="icon-ok"></span> 保 存
							</button>
							&nbsp;&nbsp; <a class="btn btn-warning"
								href="fresh/mallPackage/init.jhtml"><span
								class="icon-remove"></span>&nbsp;取消</a>
						</div>
					</div>
			</div>
			</form>
		</div>
	</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	<script src="<%=path%>/js/fresh/mallPackage/packageEdit.js?v=1.0.4"></script>
	<script type="text/javascript">
		$(function(){
			$('#rankType').trigger("change");
			var productIds ='${mallPackage.productIds}';
			if(productIds){
				products=productIds.split(",");
				loadProducts();
			}
		});
	</script>
</body>
</html>

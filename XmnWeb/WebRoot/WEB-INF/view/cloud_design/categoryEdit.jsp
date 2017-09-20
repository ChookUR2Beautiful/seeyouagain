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
<title>添加物料分类信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
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
				<div class="panel-heading">分类信息</div>
				<div class="panel-body">
					<form  id="editForm" class="form-horizontal">
					<input type="hidden" id="isType" name="isType" value="${isType}">
						<table class="table" style="text-align: center; width:80%;" border='0'>
							<tr >
								<td style="width:20%;" >
									<h5>分类名称:</h5>
								</td>
								<td ><input type="text"
									class="form-control" name="name" placeholder="分类名称"
									value="${categoryInfo.name}">
									<!-- 物料分类主键 -->
									<input type="hidden" id="id" name="id" value="${categoryInfo.id}">
								</td>
								<td></td>
							</tr>
							<tr >
								<td style="width:20%;" >
									<h5>分类图片:</h5>
								</td>
								<td >
									<input type="hidden" class="form-control" name="picUrl" id="picUrl"
										value="${categoryInfo.picUrl}">
										<div id="picUrlImg"></div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td style="width:20%;">
									<h5>关联规格:</h5>
								</td>
								<td>
									<select class="chosen-select form-control" id="attrsMultiple" name="attrsMultiple" initValue="${categoryInfo.attrIds }" data-placeholder="请选择关联规格">
									</select>
									<input type="hidden" id="attrs" name="attrs" >
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<td style="width:20%;" >
									<h5>关联标签:</h5>
								</td>
								<td >
									<select class="chosen-select form-control" id="tagsMultiple" name="tagsMultiple"  initValue="${categoryInfo.tagIds }" data-placeholder='请选择关联标签' >
									</select>
									<input type="hidden" id="tags" name="tags" >
								</td>
								<td></td>
							</tr>
							<tr>
								<td style="width:20%;" >
									<h5>排序:</h5>
								</td>
								<td ><input type="text"
									class="form-control" name="orderVal" placeholder="排序值越小，排序越靠前"
									value="${categoryInfo.orderVal}"></td>
								<td></td>
							</tr>
						</table>
						
						<div align="center">
								<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}'; </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/multipleChosen.js"></script>
    <script src="<%=path%>/js/cloud_design/categoryEdit.js"></script> 
</body>
</html>

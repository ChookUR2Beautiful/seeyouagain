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
<title>添加物料规格信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
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
				<div class="panel-heading">规格信息</div>
				<div class="panel-body">
					<form  id="categoryAttrForm" class="form-horizontal">
						<input type="hidden" id="isType" name="isType" value="${isType }">
						<table class="table" style="text-align: center; width:80%;" border='0'>
							<tr >
								<td style="width:20%;" >
									<h5>规格名称:</h5>
								</td>
								<td ><input type="text"
									class="form-control" name="name" placeholder="规格名称"
									value="${categoryAttrInfo.name}">
									<!-- 物料规格主键 -->
									<input type="hidden" id="id" name="id" value="${categoryAttrInfo.id}">
								</td>
								<td></td>
							</tr>
							<tr>
								<td style="width:20%;">
									<h5>规格细项:</h5>
								</td>
								<td>
									<select class="chosen-select form-control" id="attrValIds" name="attrValIds" multiple
										initValue="${categoryAttrInfo.attrValIds }"  data-placeholder="请添加规格细项">
									</select>
									<input type="hidden" id="attrValIdsExisting" value="">
									<input type="hidden" id="attrVals" name="attrVals" value="">
								</td>
								<td>
									<!-- TODO 20161014 -->
									<c:if test="${null!=btnAu['materialAttrVal/manage'] && btnAu['materialAttrVal/manage']}">
										<button type="button" class="btn btn-primary" id="addAttrVal" style="float:left;margin-left: 10px;"
												data-type="ajax" data-title="添加规格细项" data-url="materialAttrVal/manage/add/init.jhtml"
												data-toggle="modal" data-width="auto">
											<span class="icon-plus"></span>&nbsp;添加
										</button>
									</c:if>
								</td>
							</tr>
							<tr>
								<td style="width:20%;" >
									<h5>关联分类:</h5>
								</td>
								<td >
									<select class="chosen-select form-control" id="categorysMultiple" name="categorysMultiple"  
										initValue="${categoryAttrInfo.categoryIds }" data-placeholder='请选择关联分类' >
									</select>
									<!-- 关联分类键值对：001_海报 -->
									<input type="hidden" id="categoryVals" name="categoryVals" value=""> 
								</td>
								<td></td>
							</tr>
							<tr>
								<td style="width:20%;" >
									<h5>排序:</h5>
								</td>
								<td ><input type="text"
									class="form-control" name="sortVal" placeholder="排序值越大，排序越靠前"
									value="${categoryAttrInfo.sortVal}"></td>
								<td></td>
							</tr>
							<tr>
								<td style="width:20%;" >
									<h5>定制属性:</h5>
								</td>
								<td style="text-align:left;" >
									<input name="isCustomize" value="001" type="radio" ${categoryAttrInfo.isCustomize==001?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="isCustomize" value="002" type="radio" ${categoryAttrInfo.isCustomize==002?"checked":""} ><span style="font-size: 12px;">否</span>
								</td>
								<td></td>
							</tr>
							<tr id="isMultipleTr" style="display:none;">
								<td style="width:20%;" >
									<h5>是否多选:</h5>
								</td>
								<td style="text-align:left;" >
									<input name="isMultiple" value="001" type="radio" ${categoryAttrInfo.isMultiple==001?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="isMultiple" value="002" type="radio" ${categoryAttrInfo.isMultiple==002?"checked":""} ><span style="font-size: 12px;">否</span>
								</td>
								<td></td>
							</tr>
							<tr id="isCustomizableTr" style="display:none;">
								<td style="width:20%;" >
									<h5>是否支持自定义:</h5>
								</td>
								<td style="text-align:left;" >
									<input name="isCustomizable" value="001" type="radio" ${categoryAttrInfo.isCustomizable==001?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="isCustomizable" value="002" type="radio" ${categoryAttrInfo.isCustomizable==002?"checked":""} ><span style="font-size: 12px;">否</span>
								</td>
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
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/multipleChosen.js"></script>
    <script src="<%=path%>/js/cloud_design/materialAttrEdit.js"></script> 
</body>
</html>

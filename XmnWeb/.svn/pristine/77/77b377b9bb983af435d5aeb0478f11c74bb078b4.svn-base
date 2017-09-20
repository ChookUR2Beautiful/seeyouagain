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
<title>添加SaaS标签</title>
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
				<div class="panel-heading">标签信息</div>
				<div class="panel-body">
					<form  id="saasCategoryForm" class="form-horizontal">
						<input type="hidden" id="isType" name="isType" value="${isType }">
						<input type="hidden" id="id" name="id" value="${saasCategory.id}">
						<table class="table" style="text-align: center; width:80%;" border='0'>
							<tr >
								<td style="width:20%;" >
									<h5>标签名称:</h5>
								</td>
								<td ><input type="text"
									class="form-control" name="name" placeholder="标签名称"
									value="${saasCategory.name}">
								</td>
								<td></td>
							</tr>
							<tr>
								<td style="width:20%;">
									<h5>标签选项:</h5>
								</td>
								<td>
									<select class="chosen-select form-control" id="tagIds" name="tagIds" multiple
										initValue="${saasCategory.tagIds }"  data-placeholder="请添加标签选项">
									</select>
									<!-- 已存在的标签选项 -->
									<input type="hidden" id="tagIdsExisting" value="">
									<!-- 标签选项键值对,多个以逗号分隔 -->
									<input type="hidden" id="tagVals" name="tagVals" value="">
								</td>
								<td>
									<!-- TODO 20161209 -->
									<c:if test="${null!=btnAu['saasTag/manage/saasTagEdit'] && btnAu['saasTag/manage/saasTagEdit']}">
										<button type="button" class="btn btn-primary" id="saasTagEdit" style="float:left;margin-left: 10px;"
												data-type="ajax" data-title="添加标签选项" data-url="saasTag/manage/saasTagEdit.jhtml"
												data-toggle="modal" data-width="auto">
											<span class="icon-plus"></span>&nbsp;添加
										</button>
									</c:if>
								</td>
							</tr>
							
							<tr>
								<td style="width:20%;" >
									<h5>标签属性:</h5>
								</td>
								<td style="text-align:left;" >
									<input name="type" value="1" type="radio" ${saasCategory.type==1?"checked":""} ><span style="font-size: 12px;">模板标签</span>
									&nbsp;&nbsp;
									<input name="type" value="2" type="radio" ${saasCategory.type==2?"checked":""} ><span style="font-size: 12px;">主播标签</span>
									&nbsp;&nbsp;
									<input name="type" value="3" type="radio" ${saasCategory.type==3?"checked":""} ><span style="font-size: 12px;">名嘴标签</span>
								</td>
								<td></td>
							</tr>
							
							<tr>
								<td style="width:20%;" >
									<h5>排序:</h5>
								</td>
								<td ><input type="text"
									class="form-control" name="order" placeholder="排序值越小，排序越靠前"
									value="${saasCategory.order}"></td>
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
    <script src="<%=path%>/js/business_statistics/saasCategoryEdit.js"></script> 
</body>
</html>

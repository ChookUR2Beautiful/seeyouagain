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
<title>编辑分类导航信息</title>
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
					
					<form id="secondLevelNagivateEditForm">
						
						<input type="hidden"  name="nId" value="${categoryNavigate.NId}">
						<input type="hidden"  id = "isType" value="${isType}">
						<input type="hidden"  name = "type" value="${categoryNavigate.type}">
						
						<c:if test="${null != isType && 'add' == isType}">
							<input type="hidden"  id="order"  name = "order" value="${maxOrder }">
						</c:if>
						
						<table class="table">
							<tr>
								<td class="sellerTitleCss">
									<h5>导航标题:</h5>
								</td>
								<td class="sellerContentCss">
									<input type="text" class="form-control"  name="title"  value="${categoryNavigate.title}">
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss">
									<h5>行业类别:</h5>
								</td>
								<td class="sellerContentCss">
									<input type="hidden" name="category" value="${categoryNavigate.category}" id="category"/> 
									<input type="hidden" name="genre" value="${categoryNavigate.genre}" id="genre"/>
									<div class="input-group" id="tradeSelect" style="width : 103%" initValue="${categoryNavigate.genre}"></div>
								</td>
							</tr>
							
							<c:if test="${null != isType && 'update' == isType}">
								<tr>
									<td class="sellerTitleCss">
										<h5>导航位置:</h5>
									</td>
									<td class="sellerContentCss">
										<input type="text" class="form-control"  name="order"  value="${categoryNavigate.order}" disabled="disabled">
									</td>
								</tr>
							</c:if>
							<tr>
								<td class="sellerTitleCss">
									<h5>导航图片:</h5>
								</td>
								<td class="sellerContentCss">
									<div id="img_high_upload" ImgValidate="true"></div><small style="color:red;">高分辨率图片(640*160)</small>
									<input type="hidden" class="form-control" id="img_high" name="img_high"  value="${categoryNavigate.img_high}">
								</td>
								<td class="sellerContentCss">
									<div id="img_middle_upload" ImgValidate="true"></div><small style="color:red;">中分辨率图片(640*160)</small>
									<input type="hidden" class="form-control" id="img_middle" name="img_middle"  value="${categoryNavigate.img_middle}">
								</td>
								<td class="sellerContentCss">
									<div id="img_low_upload" ImgValidate="true"></div><small style="color:red;">低分辨率图片(640*160)</small>
									<input type="hidden" class="form-control" id="img_low" name="img_low"  value="${categoryNavigate.img_low}">
								</td>
							</tr>
						</table>
						<div align="center">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" onclick="window.history.back();">
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
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/navigate/secondLevelNagivateEdit.js"></script>
</body>
</html>

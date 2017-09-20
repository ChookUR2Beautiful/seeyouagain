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
<title>添加V客学堂资料</title>
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
				<div class="panel-heading">V客学堂-H5活动</div>
				<div class="panel-body">
					<form  id="editForm" class="form-horizontal">
					<input type="hidden" name="vstarContentToken" value="${vstarContentToken}">
					<input type="hidden" id="isType" name="isType" value="${isType}">
					<input type="hidden" id="id" name="id" value="${vstarContent.id}">
					<!-- 资源内容类型,1内容资料,2 H5活动,3图片素材 -->
					<input type="hidden" name="contentType" value="2">
						<table class="table" style="text-align: center; width:80%;" border='0'>
							<tr >
								<td style="width:20%;" >
									<h5>教学标题:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="title" placeholder="教学标题"
									value="${vstarContent.title}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>副标题:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="viceTitle" placeholder="副标题"
									value="${vstarContent.viceTitle}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>教学分类:<span style="color:red;">*</span></h5>
								</td>
								<td >
									<select  class="form-control" name="vstarDictId" id="vstarDictId" initValue="${vstarContent.vstarDictId }" >
									</select>
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>封面图片:<span style="color:red;">*</span></h5>
								</td>
								<td >
									<input type="hidden" class="form-control" name="coverImg" id="coverImg"
										value="${vstarContent.coverImg}">
										<div id="coverImgDiv"></div>
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>分享图标:<span style="color:red;">*</span></h5>
								</td>
								<td >
									<input type="hidden" class="form-control" name="shareImg" id="shareImg"
										value="${vstarContent.shareImg}">
										<div id="shareImgDiv"></div>
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>分享标题:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="shareTitle" placeholder="分享标题"
									value="${vstarContent.shareTitle}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>分享描述:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="shareDescription" placeholder="分享描述"
									value="${vstarContent.shareDescription}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>活动链接:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="activityUrl" placeholder="活动链接"
									value="${vstarContent.activityUrl}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>排序:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="sortVal" placeholder="0-999，排序值越大，越靠前"
									value="${vstarContent.sortVal}">
								</td>
								<td></td>
							</tr>
							
							
						</table>
						
						<div align="center">
								<button class="btn btn-danger" type="submit" id="submitBtn" ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
								
								<button class="btn btn-warning" type="button" onclick="window.history.back();">
									<i class="icon-reply"></i>&nbsp;取消
								</button>
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
    <script src="<%=path%>/js/vstar/vstarContentH5Edit.js"></script> 
</body>
</html>

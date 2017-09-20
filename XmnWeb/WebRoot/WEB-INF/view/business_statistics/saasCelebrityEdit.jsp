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
<title>编辑SaaS角色信息</title>
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
						<c:if test="${!empty celebrity}">
							<input type="hidden" name="id" id="id" value="${celebrity.id}">
						</c:if>
						<!-- 网红类型 1:主播   2:名嘴 -->
						<input type="hidden" name="type" id="type" value="2">
						<table class="table" style="text-align: center;width:50%">
							<tr>
								<td class="sellerTitleCss">
									<h5>姓名:<span style="color:red;">*</span></h5></td>
								<td >
									<input type="text"  class="form-control" name="name" value="${celebrity.name}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>头像:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input type="hidden" class="form-control" id="avatar" name="avatar"  value="${celebrity.avatar}">
												<div id="avatarImg"></div>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>食评费用:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="reviewPrice" id = "reviewPrice" 
									value="${celebrity.reviewPrice}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>食评次数:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="reviewNum" id="reviewNum" 
									value="${celebrity.reviewNum}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>地区:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group" id="areaLdId" initValue="${celebrity.area}" style="width: 104%;margin-left: -12px;">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>联系地址:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="address" id="address" 
									value="${celebrity.address}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>联系方式:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="phone" id="phone" 
									value="${celebrity.phone}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>联系邮箱:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="email" id="email" 
									value="${celebrity.email}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>简介:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input type="text"
									class="form-control" name="describe" id="describe" 
									value="${celebrity.describe}">
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
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_statistics/saasCelebrityEdit.js"></script>
	
</body>
</html>

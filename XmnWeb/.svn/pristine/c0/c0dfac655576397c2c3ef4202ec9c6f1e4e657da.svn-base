<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加编辑分享页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="add coupon">
<meta http-equiv="description" content="add coupon init">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

th {
	font-size: 12px;
}
</style>
</head>

<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" id="editShareForm">
				<c:if test="${!empty share.sid}">
					<input type="hidden" id="sid" name="sid" value="${share.sid}">
				</c:if>
				<div class="form-group" style="margin-top:40px;">
					<label class="col-md-2 control-label">分享标题：</label>
					<div class="col-md-3">
						<input type="text" name="title"  class="form-control"
							<c:if test="${!empty share.title}">value="${share.title}"</c:if> />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">分享链接：</label>
					<div class="col-md-3">
						<input type="text" name="link" class="form-control"
							<c:if test="${!empty share.link}">value="${share.link}"</c:if> />
					</div>
				</div>

				<c:if test="${!empty share}">
				<div class="form-group" id="range">
					<label class="col-md-2 control-label">分享范围：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">全国<input type="radio" name="range" id="allArea" value="1" <c:if test="${share.range == 1}">checked="checked"</c:if> /></label>
							<label class="radio-inline">指定城市<input type="radio" name="range" id="specifyArea" value="2" <c:if test="${share.range == 2}">checked="checked"</c:if> /></label>
							<label class="radio-inline">指定商家<input type="radio" name="range" id="specifySeller" value="3" <c:if test="${share.range == 3}">checked="checked"</c:if>  /></label>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${empty share}">
				<div class="form-group" id="range">
					<label class="col-md-2 control-label">分享范围：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">全国<input type="radio"	name="range" id="allArea" value="1"	checked="checked" /></label>
							<label class="radio-inline">指定城市<input type="radio" name="range" id="specifyArea" value="2"/></label>
							<label class="radio-inline">指定商家<input type="radio" name="range" id="specifySeller" value="3"/></label>
						</div>
					</div>
				</div>
				</c:if>
				<%-- <c:if test="${!empty coupon}">
				<div class="form-group" id="allSeller">
					<c:if test="${coupon.showAll != 1}">
					<label class="col-md-2 control-label">是否全部商家：</label>
					<div class="col-md-3">
						<div class="input-group">
							<label class="radio-inline">是<input type="radio" name="range" value="1" <c:if test="${coupon.isAllSeller == 1}">checked="checked"</c:if> /></label>
							<label class="radio-inline">否<input type="radio" name="range" value="0" <c:if test="${coupon.isAllSeller == 0}">checked="checked"</c:if>  /></label>
						</div>
					</div>
					</c:if>
				</div>
				</c:if> --%>
				<c:if test="${empty share}">
				<div class="form-group" id="allSeller">
				</div>
				</c:if> 
				<c:if test="${empty share}">
				<div class="form-group" id="cities">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5"></div>
				</div>
				</c:if>
				<c:if test="${!empty share}">
				<div class="form-group" id="cities">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
					<c:if test="${share.range == 2}">
						<c:if test="${!empty share.shareRange}">
							<c:forEach items="${ share.shareRange}" var="shareRange" varStatus="status">
								<div class="input-group">
									<div class="updateld" style="width: 100%;float:left;" initvalue="${shareRange.rangecontent}"></div>
									<span class="input-group-addon"><i
										class="icon icon-plus" style="cursor:pointer"></i></span> <span
										class="input-group-addon"><i class="icon icon-minus"
										style="cursor:pointer"></i></span>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${!empty share and empty share.shareRange}">
							<div class="input-group">
								<div class="updateld" style="width: 100%;float:left;"></div>
								<span class="input-group-addon"><i class="icon icon-plus"
									style="cursor:pointer"></i></span> <span class="input-group-addon"><i
									class="icon icon-minus" style="cursor:pointer"></i></span>
							</div>
						</c:if>
					</c:if>
					</div>
				</div>
				</c:if>
				<c:if test="${!empty share}">
				<div class="form-group" id="sellers">
					<c:if test="${share.range == 3}">
					<label class="col-md-2 control-label">选择商家：</label>
					<div class="col-md-8"><label id="checkids"></label>
						<textarea id="sellerids" rows="5" name="shareRange[0].rangecontent" class="col-md-8" <c:if test="${!empty shareRange[0].rangecontent}">value="${shareRange[0].rangecontent}"</c:if>></textarea>
					</div>
					</c:if>
				</div>
				</c:if>
				<c:if test="${empty share}">
				<div class="form-group" id="sellers"></div>
				</c:if>
				<div class="col-md-12 text-center">
					<button id="ensure" type="submit" class="btn btn-danger"><i class="icon-save"></i>&nbsp;保存</button>&nbsp;&nbsp;
					<button onclick="window.history.back();" type="button" class="btn btn-warning"><i class="icon-reply"></i>&nbsp;取消</button>
				</div>
			</form>
		</div>
	</div>
	<div class="cityTemp hidden " id="cityTemp">
		<div class="input-group">
			<div class="ld" style="width: 100%;float:left;"></div>
			<span class="input-group-addon"><i class="icon icon-plus"
				style="cursor:pointer"></i></span> <span class="input-group-addon"><i
				class="icon icon-minus" style="cursor:pointer"></i></span>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
		var type ="add";
		<c:if test="${!empty share}">type ="update";</c:if>
	</script>
	<script type="text/json" id="permisions">{add:'${ btnAu['coupon/generate/add']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/user_terminal/editShare.js"></script>
</body>
</html>

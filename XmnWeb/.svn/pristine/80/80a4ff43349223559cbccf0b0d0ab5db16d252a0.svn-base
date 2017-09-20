<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<title>添加悬浮广告</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}

.submit {
	text-align: left;
}
</style>
</head>

<body
	style="overflow-x: auto; overflow-y: auto; min-width: 1024px; background: #EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">添加悬浮广告</div>
				<div class="panel-body">
				    <form id="liveFloatAdvertiseForm" role="form" class="form-horizontal">
						<c:if test="${liveFloatAdvertiseInfo != null }">
							<input type="hidden" name="id" value="${liveFloatAdvertiseInfo.id}">
						</c:if>
						<input type="hidden" id="isType" name="isType" value="${isType}" />
						<div class="form-group">
							<label class="col-md-4 control-label">广告名称：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="title" placeholder="广告名称" id="title"
									value="${liveFloatAdvertiseInfo.title}"  style="width:45%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">广告有效时间：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" name="startTime"  value="<fmt:formatDate value="${liveFloatAdvertiseInfo.startTime}" 
								    pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width: 20%; float: left"> 
								<label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label>
								<input type="text" name="endTime" value="<fmt:formatDate value="${liveFloatAdvertiseInfo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width: 20%; float: left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">跳转URL：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="redirectUrl" placeholder="跳转URL" id="redirectUrl"
									value="${liveFloatAdvertiseInfo.redirectUrl}"  style="width:45%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4  control-label">图片： <span
								style="color: red;">*</span></label>
							<div id='activityImg' ImgValidate="true" style="position: relative; left: 10px; float: left; margin-top: 10px;"></div>
							<input type="hidden" id="picUrl" name=picUrl value="${liveFloatAdvertiseInfo.picUrl}" />
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">显示位置：<span
								style="color: red;">*</span></label>
							<div class="col-md-8" id="objectOrientedDiv">
								<input name="liveFloatAdvertisePositionList[0].showPosition"
									value="0" <c:if test="${authority.check}">checked="checked"</c:if>
									type="checkbox"><span style="font-size: 12px;">美食主页</span>
								<input name="liveFloatAdvertisePositionList[1].showPosition"
									value="1" type="checkbox"><span style="font-size: 12px;">直播主页</span>
								<input name="liveFloatAdvertisePositionList[2].showPosition"
									value="2" type="checkbox"><span style="font-size: 12px;">我的主页</span>
								<input name="liveFloatAdvertisePositionList[3].showPosition" value="3"
								 type="checkbox"><span style="font-size: 12px;">直播间</span>
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">排序：<span
								style="color: red;">*</span></label>
							<div class="col-lg-8 col-xs-8">
								<input type="number" class="form-control" id="sort"
									placeholder="排序" name="sort" value="${liveFloatAdvertiseInfo.sort}" style="width: 120px;">
							</div>
						</div>
                        <hr/>
	                    <div align="center">
							<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" id = "backButton"  onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;重置</button>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
    <input name="showPosition" id="showPosition" type="hidden" value="${liveFloatAdvertiseInfo.showPosition}">

	
    <script type="text/javascript"> contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>

	<script
		src="<%=path%>/js/live_anchor/liveFloatAdvertise/editLiveFloatAdvertise.js"></script>
</body>
</html>

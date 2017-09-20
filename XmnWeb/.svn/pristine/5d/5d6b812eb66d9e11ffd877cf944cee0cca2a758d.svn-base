<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加专题</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
	
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
.submit{text-align: left;}
</style>	
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
    <div id="main">
		<div class="panel panel-primary">
			<div class="panel-heading">添加悬浮广告</div>
			<div class="panel-body">
				<form id="floatAdvertForm" role="form">
					<c:if test="${floatAdvertInfo != null }">
						<input type="hidden" name="id" value="${floatAdvertInfo.id}">
					</c:if>
					<input type="hidden" id="isType" name="isType" value="${isType}"/> 
					<table class="table">
					    <tr>
							<th>请选择粉丝券:<span style="color: red;"> * </span></th>
							<td><select class="form-control"  id="cid" name="cid"
											initValue="${floatAdvertInfo.cid}"></select></td>

							<th>粉丝券使用时间:<span style="color: red;"> * </span></th>
							<td><span id = "couponTime"></span></td>
						</tr>

						<tr>
							<th>广告有效时间:<span style="color: red;"> * </span></th>
                            <td >
                                <input type="text" class="form-control form_datetime" name ="startTime"  value="<fmt:formatDate value="${floatAdvertInfo.startTime}"
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:30%;float:left"  readonly="readonly">
								<label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label> 
                                <input type="text" class="form-control form_datetime" name ="endTime"  value="<fmt:formatDate value="${floatAdvertInfo.endTime}"
								       pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:30%;float:left"  readonly="readonly">
							<th>广告图:<span style="color: red;"> *</span></th>
							<td>
							    <div id='picUrlid'> </div> 
								<input type="hidden" id="picUrl" name="picUrl"
									value="${floatAdvertInfo.picUrl}" />
							</td>
						</tr>
						<tr id="datas" >
							<th>投放主播:<span style="color: red;"> *</span></th>
							<td style="text-decoration:none" >
							    <select class="chosen-select form-control" id="anchorIdNames" name="anchorIdNames" 
										initValue="${floatAdvertInfo.anchorIds }"  data-placeholder="请添加投放主播">
								</select>
							    <!-- 已存在的标签选项 --> 
							    <input type="hidden" id="anchorIds" name="anchorIds" > 
							</td>
							<td>
							 
							</td>
						</tr>
					</table>

					<div align="center">
						<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
						<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;重置</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/ux/js/multipleChosen.js"></script>
    
    <script src="<%=path%>/js/live_anchor/floatAdvert/editFloatAdvert.js"></script> 
</body>
</html>

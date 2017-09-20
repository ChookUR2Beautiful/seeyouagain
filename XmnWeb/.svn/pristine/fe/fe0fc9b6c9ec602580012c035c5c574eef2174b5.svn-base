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
<title>添加连锁店信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
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
					<form id="editXmerForm">
						<input type="hidden" name="sellerSubsidyToken"
							value="${sellerSubsidyToken}"> 
							<input type="hidden" id="isType" name="isType" value="${isType}" />
							<input type="hidden" id="sex" value="${bxmer.sex}" />
						<table class="table" style="width: 100%;">
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5></h5>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>头&nbsp;&nbsp;&nbsp;&nbsp;像:</h5>
								</td>
								<td class="sellerContentCss">
								    <div class="input-group">
							           <input type="hidden" class="form-control" id="picURL" name="avatar" value="${bxmer.avatar}" />
							           <div id="pic" style="width: 130px; float: left;margin-left: 40%"></div>
							        </div>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>性&nbsp;&nbsp;&nbsp;&nbsp;别:</h5>
								</td>
								<td class="sellerContentCss">
									<label class="radio-inline">男
								<input type="radio" name="sex" value="1" checked="checked" />
								</label> 
								<label class="radio-inline">女
								<input type="radio" name="sex" value="2" />
								</label>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5></h5>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5></h5>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>寻蜜客姓名:</h5>
								</td>
								<td class="sellerContentCss" >
								    <input type="text" class="form-control" name="name"   value="${bxmer.name}" style="width:63%;">
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>手机号码:</h5>
								</td>
								<td class="sellerContentCss">
								 <input type="text" class="form-control" name="phoneid"   value="${bxmer.phoneid}" style="width:63%;"/>
								</td>
								
							</tr>
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5></h5>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>加入时间:</h5>
								</td>
								<td class="sellerContentCss">
								 <input type="text" class="form-control" name="sdate"   value="${bxmer.sdateStr}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:63%;float:left"/>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>年&nbsp;&nbsp;&nbsp;&nbsp;龄:</h5>
								</td>
								<td class="sellerContentCss">
									<input type="text" class="form-control"  id="age" name="age" value="${bxmer.age}" style="width:63%;float:left"/>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5></h5>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</h5>
								</td>
								<td class="sellerContentCss">
								 <input type="text" class="form-control" name="email"   value="${bxmer.email}"style="width:63%;"/>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>&nbsp;&nbsp;微信号:</h5>
								</td>
								<td class="sellerContentCss">
								<input type="text" class="form-control" name="weixinid"   value="${bxmer.weixinid}" style="width:63%;"/>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5></h5>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>寻蜜客编号:</h5>
								</td>
								<td class="sellerContentCss">
								 <input type="text" class="form-control" id="uid" name="uid"   value="${bxmer.uid}" style="width:63%;"/>
								</td>
								<td class="sellerTitleCss" style="text-align: right;">
								<h5>成&nbsp;&nbsp;&nbsp;&nbsp;就:</h5>
								</td>
								<td class="sellerContentCss">
								<input type="text" class="form-control" name="achievement"   value="${bxmer.achievement}" style="width:63%;"/>
								</td>
							</tr>
						</table>
						<hr>
						<div align="center">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" id="cancelId">
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
	<script src="<%=path%>/ux/js/searchChosen3.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/xmermanagerment/addAndUpdateXmer.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
</body>
</html>

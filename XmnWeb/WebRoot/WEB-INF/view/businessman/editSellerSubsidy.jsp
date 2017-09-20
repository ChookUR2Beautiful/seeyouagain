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
<title>添加连锁店信息</title>
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
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form  id="sellerSpreadForm" >
						<input type="hidden" name="sellerSubsidyToken" value="${sellerSubsidyToken}">
						<input type="hidden" id="isType" name="isType" value="${isType}"/>
						<input type="hidden" id="issuestatus" name="issuestatus" value="0"/>
						<input type="hidden" id="idsubsidy" name="idsubsidy" value="${sellerSubsidyList.idsubsidy}"/>
						<table class="table" style="width: 100%;" >
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>商&nbsp;家&nbsp;名&nbsp;称:</h5>
								</td>
								<td class="sellerContentCss">
								<label id="checkids"></label> 
								<div id="sellerChange">
								<input type="hidden" id="sellerid" name="sellerid" value="">
								<input type="text" form-control" id="sellername" style="width:50%;" name="sellername" value="">
								</div>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>服务员帐号:</h5></td>
								<td class="sellerContentCss">
								<select	class="form-control" id="phoneid" name="phoneid"
									style="width:40%;">
								</select>
								</td>
							</tr>
							<!-- <tr>
								<td class="sellerTitleCss" style="text-align: right;">
									<h5>每单佣金比例<font color="red">&nbsp;(%)</font>:</h5></td>
								<td class="sellerContentCss" colspan="1"><input type="text"  class="form-control" id="scale" name="scale" value="" style="width:40%;">
								</td>
							</tr> -->
							
						</table>
						<hr>
						<div align="center" style="margin-left: 280px">
								<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" id="backId"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen3.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/js/businessman/editSellerSubsidy.js"></script> 
</body>
</html>

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
<title>添加合作商业务员信息</title>
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
					<form id="addStaffForm">
						<input type="hidden" name="sellerSubsidyToken"
							value="${sellerSubsidyToken}"> <input type="hidden"
							id="isType" name="isType" value="${isType}" /> <input
							type="hidden" id="issuestatus" name="issuestatus" value="0" /> 
							<input type="hidden" id="tSpreadConfID" name="id" value="${TSpreadConfig.id}" />
							<input type="hidden" id="jointidUpt" name="jointidUpt" value="${staff.jointid}" />
							<input type="hidden" id="corporateUpt" name="corporateUpt" value="${staff.corporate}" />
							<input type="hidden" id="staffid" name="staffid" value="${staff.staffid}" />
						<table class="table" style="text-align: center;" >
						<tr>
							<td>
								<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合作商:</h5> 
							</td>
							<td>
								<select	class="form-control" id="jointid" name="jointid" value="">
								</select>
							</td>
							
							<td>
									<h5>业务员账号：</h5> 
							</td>
							<td>
								  <input type="text" class="form-control" id="phoneid" name="phoneid" placeholder="输入业务员手机号" value="${staff.phoneid}">
							</td>
							<td>
									<h5>登录密码：</h5> 
							</td>
							<td>
								   <input  type="password" class="form-control" name="password">
						   </td>
						</tr>
						<tr>
							<td>
								<h5>业务员姓名:</h5> 
							</td>
							<td>
								 <input type="text" class="form-control" name="fullname"  value="${staff.fullname}">
							</td>
							
							<td >
									<h5>业务员昵称：</h5> 
							</td>
							<td>
								  <input type="text" class="form-control" name="nickname"   value="${staff.nickname}">
							</td>
							<td>
								<h5>业务员性别：</h5>
							</td>
							<td colspan="5" align="left">
								<label class="radio-inline">男
								<input type="radio"	name="sex" value="1" checked="checked" />
								</label> 
								<label class="radio-inline">女
								<input type="radio" name="sex" value="2" />
								</label>
							</td>
						</tr>
						<tr>
							<td>
									<h5>负责区域：</h5> 
							</td>
							<td colspan="2" style="text-align: left;">
							  <div class="input-group" id="areaInfo" style="width: 80%;float:left;" initValue="${staff.area}"></div>
							  <h5><font style="color: red;float:left;">(区域可多选)</font></h5>
							 </td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<hr>
						<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" id="cancelId">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						
							</td>
						</tr>
						</table>
						
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
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/business_cooperation/addSalesman.js"></script>
</body>
</html>

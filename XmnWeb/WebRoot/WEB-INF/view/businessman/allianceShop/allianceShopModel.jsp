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
<title>添加联盟店信息</title>
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
					<form  id="allianceShopForm" method="post" action="${requestUrl}">
						<input type="hidden" name="allianceShopToken" value="${allianceShopToken}">
						<c:if test="${!empty allianceShop}"><input type="hidden" id="id" name="id" value="${allianceShop.id}"></c:if>
						<table class="table" >
								<tr>
									<td style="width: 200px">
											<h5>联盟店名称:</h5> 
									</td>
									<td style="width: 550px">
									     <input type="text" class="form-control" name="allianceName"  placeholder="联盟商名称" value="${allianceShop.allianceName}">
									</td>
									<td style="width: 200px">
											<h5>联系人手机:</h5> 
									</td>
									<td style="width: 550px">
										 <input type="text" class="form-control" name="phoneid"  placeholder="联系人手机" value="${allianceShop.phoneid}">
									</td>
								</tr>
								<tr>
									<td style="width: 200px">
											<h5>区域:</h5> 
									</td>
									<td style="width: 550px">
										<div class="input-group" style="width:100%; text-align: left" id="areaSelect" initValue="${allianceShop.area}">
										</div>
									</td>
									<td style="width: 200px">
											<h5>商圈:</h5> 
									</td>
									<td style="width: 550px">
										<select class="form-control"  id="zoneid" name="zoneid" initValue="${allianceShop.zoneid}">
											<option value="">请先选择区域再选择商圈</option>
										</select>
										
									</td>
								</tr>
								<tr>
									<td style="width: 200px">
											<h5>详细地址:</h5> 
									</td>
									<td colspan="3">
										<textarea class="form-control"  name="address" placeholder="详细地址">${allianceShop.address}</textarea>
									</td> 
								</tr>
								<tr>
									<td style="width: 200px">
										<h5>描述:</h5> 
									</td>
									<td  colspan="3">
										 <textarea class="form-control"  name="description" placeholder="描述">${allianceShop.description}</textarea>
									</td>
								</tr>
						</table>
						<c:if test="${empty allianceShop}">
						<hr>
						<table class="table">
								<tr>
									<td style="width: 200px">
											<h5>登录帐号:</h5> 
									</td>
									<td style="width: 550px">
									     <input type="text" class="form-control" name="account"  placeholder="登录帐号">
									</td>
									<td align="left" style="width: 200px" colspan="2">
											<h5><font color="red">&nbsp;（初始登录密码为联系人手机后六位）</font></h5> 
									</td>
								</tr>
								<tr>
									<td style="width: 200px">
											<h5>帐号昵称:</h5> 
									</td>
									<td style="width: 550px">
										 <input type="text" class="form-control" name="nname"  placeholder="帐号昵称">
									</td>
									<td style="width: 200px">
											<h5>真实姓名:</h5> 
									</td>
									<td style="width: 550px">
										<input type="text" class="form-control" name="fullname"  placeholder="真实姓名">
									</td>
								</tr>
								<tr>
									<td style="width: 200px">
											<h5>联系人手机:</h5> 
									</td>
									<td style="width: 550px">
										<input type="text" class="form-control" name="phone"  placeholder="联系人手机">
									</td> 
									<td style="width: 200px">
										<h5>是否启用:</h5> 
									</td>
									<td style="width: 550px">
										<select class="form-control" name="status">
											<option selected="selected" value="0">启用</option>
											<option value="1">不启用</option>
										</select>
			 						</td>
								</tr>
						</table>
						</c:if>
						<hr>
						<table class="table">
								<tr>
									<td style="width: 200px">
										<h5>子店:</h5> 
									</td>
									<td  >
										<textarea id="object" name="ids" rows="10" class="form-control"></textarea>
									</td>
								</tr>	
						</table>
						<div align="center">
								<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保存</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/js/businessman/allianceShop/allianceShopModel.js"></script> 
</body>
</html>

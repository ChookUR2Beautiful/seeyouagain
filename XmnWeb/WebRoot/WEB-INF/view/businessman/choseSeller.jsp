<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商户列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
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
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<input type="hidden" id="listUrl" value="${listUrl}" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="sellerFromId">
			    <input type="hidden" name="status"  value="3"/> 
			    <input type="hidden" name="ismultiple"  value="0"/>
			    <input type="hidden" name="aid"  value="${param.aid}"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>商家名称:</h5></td>
							<td style="width:25% !important;">
								<input type="text" class="form-control"  name="sellername"  style="width:90%">
							</td>
							<td style="width:6%;"><h5>行业查询：</h5></td>
							<td style="width:25% !important;">
								<div class="input-group" id="tradeSelect" style="width:91%"></div>
							</td>
							<td style="width:6%;"><h5>商家手机号:</h5></td>
							<td style="width:27% !important;">
								<input type="text" class="form-control"  name="phoneid"  style="width:90%">
							</td>
													
						</tr>
						<tr>
							<td style="width:6%;"><h5>商家编号:</h5></td>
							<td style="width:25%;">
								<input type="text" class="form-control"  name="sellerid"  style="width:90%">
							</td>
							<c:if test="${empty cityids}">
							<td style="width:6%;"><h5>区域查询：</h5></td>
							<td style="width:25%;">
								<div class="input-group" id="ld" style="width:93%">
								</div>
							</td>
							</c:if>
							<c:if test="${!empty cityids}">
							<input type="hidden" name="cityids"  value="${cityids}">
							</c:if>
							<td style="width:6%;"><h5>商家等级：</h5></td>
							<td style="width:27%;">
									<select class="form-control" tabindex="2" name ="sellerGrade" style = "width:90%;">
									    <option value = "">--请选择--</option>
						                <option value = "1" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==1?"selected":""}</c:if>>A&nbsp;&nbsp;级</option> 
						                <option value = "2" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==2?"selected":""}</c:if>>B+级</option>
						                <option value = "3" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==3?"selected":""}</c:if>>B&nbsp;&nbsp;级</option>
						                <option value = "4" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==4?"selected":""}</c:if>>C+级</option> 
						                <option value = "5" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==5?"selected":""}</c:if>>C&nbsp;&nbsp;级</option>
						             </select>
						   </td>
						</tr>	
						<tr>
						    <td colspan="4" style="width:62%;"></td>
							<td colspan="2" style="width:33%;">					
								<div class="submit" style="text-align: left;"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;"/>
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;"/>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="row">
				<div class="col-md-1"><h5>已选择商家:</h5></div>
				<div class="col-md-11"><div id="choseDatas" class="chosen-container chosen-container-multi chosen-with-drop chosen-container-active" style="width:100%"><ul class='chosen-choices <c:if test="${param.type==2}">chosen-2</c:if>'></ul></div></div>
			</div>
			<div id="sellerInfoDiv" ></div>
		</div>																															
	</div>
	<div class="panel" style="padding-top: 10px;padding-bottom : 10px;">
		<div class="return" align="center">
			<button class="btn btn-success closeChosen" type="button" ><i class="icon-ok-sign"></i>&nbsp;保存</button>&nbsp;&nbsp;
			<button class="btn btn-default closeChosen" id="allCancel"><span class="icon-reply"></span>&nbsp;取消</button>
		</div>
	</div>
	<script type="text/javascript">
	var type = "${param.type}";
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/businessman/choseSeller.js"></script>
</body>
</html>

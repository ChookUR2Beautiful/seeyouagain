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
<title>商家待审核列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
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
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="sellerPendingFromId">
			<!-- 1审核中 -->
			<input type="hidden" name="status" value="1"/>
			<!-- 0不是连锁店 -->
			<input type="hidden" name="ismultiple" value="0"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td><h5>&nbsp;&nbsp;商家编号:</h5></td>
							<td>
								<input type="text" class="form-control"  name="sellerid" value="${param.sellerid}" style="width:90%">
							</td>
							<td><h5>&nbsp;&nbsp;商圈查询：</h5></td>
							<td>
									<select class="form-control"  id="zoneid" name ="zoneid" initValue="${param.zoneid}" style = "width:77%;">
						            </select>
							</td>
							
							
							<td style="width:100px;"><h5>&nbsp;&nbsp;商家手机号:</h5></td>
							<td style="width:400px !important;">
								<input type="text" class="form-control"  name="phoneid"  value="${param.phoneid}" style="width:75.5%">
							</td>
						</tr>
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:400px !important;">
								<input type="text" class="form-control"  name="sellername"  value="${param.sellername}" style="width:90%">
							</td>
							<td><h5>&nbsp;&nbsp;行业查询：</h5></td>
							<td>
									<div class="input-group" id="tradeSelect" style = "width:92%;"
									 <c:choose>
									    <c:when test="${!empty param.genre}">
									      initValue="${param.genre}"
									    </c:when>
									    <c:otherwise>  
									    	initValue="${param.category}"
									    </c:otherwise>
									 </c:choose>
									 ></div>
							</td>
							<td style="width:100px;"><h5>&nbsp;&nbsp;商家等级：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="sellerGrade" style = "width:76%;">
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
							<td style="width:100px;"><h5>&nbsp;&nbsp;归属合作商：</h5></td>
							<td>
									<select class="form-control" id="jointid" name ="jointid" initValue="${param.jointid}" style = "width:90%;">
							    	</select>
						    </td>
						    <td style="width:100px;"><h5>&nbsp;&nbsp;区域查询：</h5></td>
							<td style="width:400px !important;">
								<div class="input-group" id="ld" 
									<c:choose>
									    <c:when test="${!empty param.area}">
									      initValue=" ${param.area}"
									    </c:when>
									
									    <c:when test="${empty param.area && !empty param.city}">
									    	initValue=" ${param.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${param.province}"
									    </c:otherwise>
									 </c:choose>
								  style="width:92%">
								</div>
							</td>
						    <td colspan="2" >
								<div class="submit" style="text-align: left; ">&nbsp;&nbsp;<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>

					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['businessman/sellerPending/updateSellerStatus'] && btnAu['businessman/sellerPending/updateSellerStatus']}">
					<button type="button" class="btn btn-success" id="passId"><span class="icon-ok-sign"></span>&nbsp;批量通过</button>
				</c:if>
				<c:if test="${null!=btnAu['businessman/sellerPending/updateSellerStatus'] && btnAu['businessman/sellerPending/updateSellerStatus']}">
					<button type="button" class="btn btn-danger" id="notPassId"><span class="icon-remove-sign"></span>&nbsp;批量不通过
				</button> </c:if>
				<c:if test="${null!=btnAu['businessman/sellerPending/export'] && btnAu['businessman/sellerPending/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="sellerInPendingfoDiv"></div>
		</div>
	</div>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script type="text/json" id="permissions">{updateSellerStatus:'${btnAu['businessman/sellerPending/updateSellerStatus'] }',update:'${btnAu['businessman/sellerPending/update'] }',view:'${btnAu['businessman/sellerPending/getInit'] }',agio:'${btnAu['businessman/sellerPendingAgio/init'] }',account:'${btnAu['businessman/sellerPendingAccount/init'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js" type="text/javascript"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js" type="text/javascript"></script>
	<script src="<%=path%>/ux/js/ld2.js" type="text/javascript"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js" type="text/javascript"></script>
	<script src="<%=path%>/js/businessman/sellerPending.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var vali=
			{
				rules:{
					sellerid:{
						digits:true,
						range:[1,2147483647]
					}
				},
				messages:{
					sellerid:{
						digits:"商家编号只能是数字",
						range:"最大值为2147483647"
					}
				}
			};
		validate("sellerPendingFromId",vali);
	});
	</script>
</body>
</html>

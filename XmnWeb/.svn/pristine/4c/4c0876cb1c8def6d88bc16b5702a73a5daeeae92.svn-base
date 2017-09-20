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
<title>连锁店列表</title>
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
.submit{text-align: left;}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="sellerFromId">
			  <input type="hidden" name="ismultiple"  value="1"/>
				<table style="width:100%;">
                <%-- <tbody>
						<tr>
							<td style="width:5%;"><h5>连锁店编号:</h5></td>
							<td style="width:25% !important;">
							<input type="text" class="form-control"  name="sellerid"  value="${param.sellerid}" style="width:80%">
							</td>
							<td style="width:5%;"><h5>区域查询：</h5></td>
							<td style="width:25%;">
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
								  style="width:81%">
								</div>
							</td>
							<td style="width:5%;"><h5>连锁店手机号:</h5></td>
							<td style="width:25% !important;">
								<input type="text" class="form-control"  name="phoneid"  value="${param.phoneid}" style="width:76%">
							</td>								
						</tr>
						<tr>
						    <td style="width:5%;"><h5>连锁店名称:</h5></td>
							<td style="width:25% !important;">
							<input type="text" class="form-control"  value="${param.sellername}" name="sellername"  style="width:80%">
							</td>
					
							<td style="width:5%;"><h5>参考地标：</h5></td>
							<td style="width:25% !important;">
							<input type="text" class="form-control"  name="landmark"  value="${param.landmark}" style="width:80%">
							</td>	
							<td colspan="2" style="text-align: right; ">
								<div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody> --%>
                    <!-- 20170315版本更改 -->
					<tbody>
						<tr>
						    <td style="width:5%;"><h5>连锁店名称:</h5></td>
							<td style="width:20% !important;">
							    <input type="text" class="form-control"  value="${param.sellername}" name="sellername"  style="width:80%">
							</td>
				            <td style="width:5%;"><h5>联系人手机:</h5></td>
							<td style="width:20% !important;">
								<input type="text" class="form-control"  name="phoneid"  value="${param.phoneid}" style="width:76%">
							</td>
							<td style="width:6%;"><h5>关联门店名称:</h5></td>
							<td style="width:20% !important;">
								<input type="text" class="form-control"  name="subSellername"  style="width:76%">  
							</td>
							<td colspan="2" style="text-align: right; ">
								<div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								</div>
							</td>
							<td colspan="2" style="text-align: right; ">
							    <div class="submit">
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
				<c:if test="${null!=btnAu['businessman/multipShop/add'] && btnAu['businessman/multipShop/add']}"><a type="button" id="addSellerBto" class="btn btn-success"  href="businessman/multipShop/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a></c:if>
			</div>
			<div id="sellerInfoDiv"></div>
		</div>																																				
	</div>
	<script type="text/json" id="permissions">
		{xg:'${ btnAu['businessman/multipShop/update']}',zh:'${btnAu['businessman/multipShop/multipAccount'] }',bindcard:'${btnAu['businessman/multipShop/bindCardInit/init']}'}
    </script>
    <script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/businessman/multipleshop/multipShopList.js"></script>
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
		validate("sellerFromId",vali);
	});
	</script>
	
</body>
</html>

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
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	
	<div class="panel">
		<div class="panel-body">
             <form class="form-horizontal" role="form" id="sellerPackageFromId">
				<input type="hidden" id="uid" name="uid" value="${uid}">
				<div class="form-group">
	                <table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:100px;"><h5>虚拟货币：</h5></td>
								<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="status" value="${param.status}" style = "width:75%;">
							                <option value = "">全部</option>
							                <option value = "1" ${param.status==1?"selected":""}>鸟豆</option>
							                <option value = "2" ${param.status==2?"selected":""}>鸟币</option>
							        </select>
							   </td>
							   <td style="width: 100px;"><h5>类型：</h5></td>
							   <td style="width: 400px;">
							       <select class="form-control" tabindex="2" name="status" value="${param.status}" style="width: 75%;">
										<option value="">全部</option>
										<option value="1" ${param.status==1?"selected":""}>增加</option>
										<option value="2" ${param.status==2?"selected":""}>扣减</option>
								   </select>
							   </td>
							   <td style="width: 100px;"><h5>渠道：</h5></td>
							   <td style="width: 400px;">
							       <select class="form-control" tabindex="2" name="status" value="${param.status}" style="width: 75%;">
										<option value="">全部</option>
										<option value="1" ${param.status==1?"selected":""}>平台充值</option>
										<option value="2" ${param.status==2?"selected":""}>打赏</option>
										<option value="3" ${param.status==3?"selected":""}>打赏返还</option>
										<option value="4" ${param.status==4?"selected":""}>红包返还</option>
								   </select>
							   </td>
							   <td colspan="2" style="text-align: right; ">
									<div class="submit">
									   <input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									   <input  class="reset radius-3" type="reset" value="导出"  data-bus = 'reset' />
									</div>
								</td>
							</tr>
							
						</tbody>
					</table>
					
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>


	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<!-- -->
				<c:if
					test="${null!=btnAu['sellerPackage/manage/add'] && btnAu['sellerPackage/manage/add']}">
					<a type="button" class="btn btn-primary" id="addSellerPackageBto" href="sellerPackage/manage/add/init.jhtml?isType=add"><span
						class="icon-plus"></span>&nbsp;添加</a>
				</c:if>

			</div>
			<div id="purseListDiv"></div>
		</div>
	</div>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/liveMemberPurseList.js"></script>
	
</body>
</html>

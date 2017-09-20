<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>供应商列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel" style="width:75%;">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchSupplierForm">
				 <input type="hidden" name="bid" value="${bid}"/> 
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:10%;"><h5>供应商名称:</h5></td>
							<td style="width:20%;"><input type="text" class="form-control"  name="supplierName" value="${param.supplierName}" style = "width:90%;"/></td>
							<td style="width:10%;"><h5>供应商联系人:</h5></td>
							<td style="width:20%;"><input type="text" class="form-control"  name="contacts" value="${param.contacts}" style = "width:90%;"/></td>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td style="width:10%;"><h5>供应商联系电话:</h5></td>
							<td style="width:20%;"><input type="text" class="form-control"  name="phone" value="${param.phone}" style = "width:90%;"/></td>
							<td style="width:10%;"><h5>供应商地址:</h5></td>
							<td style="width:20%;"><input type="text" class="form-control"  name="address" value="${param.address}" style = "width:90%;"/></td>
							<td colspan="2">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
							<td colspan="2"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default" style="width:75%;">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['fresh/supplier/add'] }">
					<button type="button" 
						class="btn btn-success"  
						data-type="ajax" 
						data-url="fresh/supplier/add/init.jhtml" 
						data-toggle="modal"><span class="icon-plus">
					</span>&nbsp;添加</button>
				</c:if>
			</div>
			<div id="supplierList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{add:'${ btnAu['fresh/supplier/add']}',delete:'${btnAu['fresh/supplier/delete']}',edit:'${ btnAu['fresh/supplier/edit']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/fresh/supplier/supplierList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

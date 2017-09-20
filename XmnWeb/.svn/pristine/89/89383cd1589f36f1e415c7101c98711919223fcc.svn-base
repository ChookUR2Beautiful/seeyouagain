<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>物料列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">

<link  href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/> 
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="searchForm">
					<div class="form-group">
						<table style="width:100%;">
									<tbody>
										<tr>
											<td style="width:5%;"><h5>&nbsp;&nbsp;商品编号：</h5></td>
											<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
											<td style="width:5%;"><h5>&nbsp;&nbsp;商品名称：</h5></td>
											<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
											<td style="width:5%;"><h5>售价：</h5></td>
											<td style="width:30%;"><input type="text" class="form-control"  name="salePrice" style = "width:66%;"></td>
										</tr>
										<tr>
											<td style="width:5%;"><h5>&nbsp;&nbsp;分类：</h5></td>							
											<td style="width:25%;"> 
												<select class="form-control" name="categoryId" style = "width:85%;">
													<option value="">全部</option>
												</select> 
											</td>
											<td style="width:5%;"><h5>&nbsp;&nbsp;规格：</h5></td>							
											<td style="width:25%;"> 
												<select class="form-control" name="status" style = "width:85%;">
													<option value="">全部</option>
												</select> 
											</td>
											<td style="width:5%;"><h5>规格细项：</h5></td>							
											<td style="width:30%;"> 
												<select class="form-control" name="status" style = "width:66%;">
													<option value="">全部</option>
												</select> 
											</td>
										</tr>
										<tr>
											<td style="width:5%;"><h5>&nbsp;&nbsp;标签：</h5></td>							
											<td style="width:25%;"> 
												<select class="form-control" name="status" style = "width:85%;">
													<option value="">全部</option>
												</select> 
											</td>	
											<td style="width:5%;"></td>
											<td style="width:25%;"></td>
											<td colspan="2" style="width:30%;">
												<div class="submit" style="text-align: left;">
													<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
													<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
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
					<c:if
						test="${null!=btnAu['materialTag/manage/add'] && btnAu['materialTag/manage/add']}">
						<a type="button" class="btn btn-success" href="materials/manage/add/init.jhtml" >
							<span class="icon-plus"></span>&nbsp;添加
						</a>
					</c:if>
					<c:if test="${!empty btnAu['materialTag/manage/update'] && btnAu['materialTag/manage/update']}">
						<button type="button" class="btn btn-info" id="putaway" ><span class="icon-hand-up"></span>&nbsp;上架</button>
					</c:if>
					<c:if test="${!empty btnAu['materialTag/manage/update'] && btnAu['materialTag/manage/update']}">
						<button type="button" class="btn btn-danger" id="removeOffshelf" ><span class="icon-hand-down"></span>&nbsp;下架</button>
					</c:if>
				</div>
				<div id="materialList" class="good-table-wrap"></div>
			</div>
		</div>
	
	<script type="text/json" id="permissions">
		{
	       update:'${ btnAu['materialTag/manage/update']}',
	       delete:'${ btnAu['materialTag/manage/delete']}'
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/cloud_design/materialManage.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>V客学堂</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
	<!-- 导航栏 -->
	<ul id="myTab" class="nav nav-tabs">
	
	</ul>
	
	<div class="tab-content">
		<!-- 实名审核star -->
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm1">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;产品名称：</h5></td>
												<td style="width:25%;">
													<input class="form-control" name="title" value="">
												</td>
												<!-- <td style="width:5%;"><h5>&nbsp;&nbsp;产品名称：</h5></td>
												<td style="width:25%;">
													<input class="form-control" name="productName" value="">
												</td> -->
												<td style="width:5%;"><h5>&nbsp;&nbsp;产品编号：</h5></td>
												<td style="width:25%;">
													<input class="form-control" name="id" value="">
												</td>
												</tr>
												<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;产品状态：</h5></td>
												<td style="width:25%;">
													<select class="form-control" name="status">
														<option value="" >=请选择=</option>
														<option value="0" >待上线</option>
														<option value="1" >已上线</option>
														<option value="2" >已售罄</option>
														<option value="3" >已下线</option>
													</select >
												</td>
												<td style="width:5%;"><h5>产品售价:</h5></td>
												<td style="width:18%;">
														<input type="text" class="form-control" name="minPrice" value=""  onchange="" style="width:42%;float:left" />
												    		<label style="float: left;">&nbsp;--&nbsp;</label>
												 		<input type="text" class="form-control" name="maxPrice" value=""  style="width:42%;float:left" />
													</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
												<td colspan="2" style="width:35%;">
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
						test="${null!=btnAu['fresh/mallPackage/edit']}">
						<a type="button" class="btn btn-success" href="fresh/mallPackage/edit/init.jhtml" >
							<span class="icon-plus"></span>&nbsp;添加
						</a>
						<button type="button" class="btn btn-info" id="putaway" ><span class="icon-hand-up"></span>&nbsp;上线</button>
						<button type="button" class="btn btn-danger" id="removeOffshelf" ><span class="icon-hand-down"></span>&nbsp;下线</button>
					</c:if>
					
					</div>
					<div id="enrollList1"></div>
				</div>
			</div>
		</div>
		<!-- 实名审核end -->
		
		
		
	</div>
	
	
	<script type="text/json" id="permissions">
		{update:'${ btnAu['fresh/mallPackage/edit']}'}
    </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/common/Base64.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/js/fresh/mallPackage/packageList.js"></script>
  </body>
</html>

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
    <title>选手排名统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
	<div class="tab-content">
		<!-- 全部star -->
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm1">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;"></td>
											</tr>
											<tr>
												<td colspan="4"></td>
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
							test="${null!=btnAu['VstarReward/manage/add'] && btnAu['VstarReward/manage/add']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="奖励设置" data-url="VstarReward/manage/update/init.jhtml"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;奖励设置
							</button>
						</c:if>
					</div>
					<div id="enrollList1"></div>
				</div>
			</div>
		</div>
		<!-- 全部end -->
		
	</div>
	
	
	<script type="text/json" id="permissions">
		{update:'${ btnAu['VstarReward/manage/update']}'}
    </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/vstar/vstarRewardManage.js"></script>
  </body>
</html>

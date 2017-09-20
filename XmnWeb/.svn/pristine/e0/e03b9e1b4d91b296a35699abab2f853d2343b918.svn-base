	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>商户活动申请</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet"> 
	<style type="text/css">
	.submit{text-align: left;}
	</style>
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	 	<ul id="myTab" class="nav nav-tabs">
<%-- 		<c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">
	      <li <c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">class="active"</c:if> >
	        <a href="#tab1" data-toggle="tab">平台活动申请</a>
	      </li>
      	</c:if>
      	<c:if test="${ btnAu['marketingManagement/activityManagement/discount/init']}">
	      <li <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && btnAu['marketingManagement/activityManagement/discount/init']}"> class="active" </c:if> >
	        <a href="#tab2" data-toggle="tab">自定义活动申请</a>
	      </li>
      </c:if> --%>
	      <li class="active">
	        <a href="#tab1" data-toggle="tab">平台活动申请</a>
	      </li>
	      <li >
	        <a href="#tab2" data-toggle="tab">自定义活动申请</a>
	      </li>

    </ul>
  	
  	
  	 <div class="tab-content">
    
    <!-- 平台活动 -->
    <%-- <c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}"> --%>
    <c:if test="true">
	    <div id="tab1" class="tab-pane in active"> <%-- <c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">in active</c:if>"> --%>
  	
		  	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" method="post"  id="platformForm">
						<input type="hidden" name="type" value="1">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:5%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
									<td style="width:15%;"><input type="text"
											class="form-control" name="sellerid"
											value="${sellerid}" style="width:90%;">
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
									<td style="width:15%;;"><input type="text"
										class="form-control" name="sellername"
										value="${sellername}" style="width:90%;"></td>									
									<td style="width:5%;"><h5>&nbsp;&nbsp; 申请日期:</h5></td>
									<td style="width:25%;;"><input type="text"
										name="sdateStart" value="${sdateStart}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
										<label style="float: left;">&nbsp;--&nbsp;</label> <input
										type="text" name="sdateEnd" value="${sdateEnd}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp;&nbsp;状态：</h5></td>
									<td style="width:25%;;">
									<select class="form-control"
										name="status" style="width:78%;" >
											<option value="">请选择</option>
											<option value="0">待审核</option>
											<option value="1">审核通过</option>
											<option value="2">审核未通过</option>
									</select>
									</td>
								</tr>
								<tr>
									<td style="width:5%;"><h5>&nbsp;&nbsp;联系电话:</h5></td>
									<td style="width:15%;"><input type="text"
											class="form-control" name="phone"
											value="${phone}" style="width:90%;">
									</td>
<%-- 									
									<td style="width:5%;"><h5>&nbsp;&nbsp;活动编号:</h5></td>
									<td style="width:15%;">
									<input type="text" class="form-control" name="activityId" value="${activityId}" style="width:90%;">
									</td>
--%>									
									<td style="width:5%;"><h5>&nbsp;&nbsp;活动名称:</h5></td>
									<td style="width:15%;">
									<input type="text" class="form-control" name="activityName" value="${activityName}" style="width:90%;">
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp; 处理时间:</h5></td>
									<td style="width:25%;;"><input type="text"
										name="edateStart" value="${edateStart}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
										<label style="float: left;">&nbsp;--&nbsp;</label> <input
										type="text" name="edateEnd" value="${edateEnd}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
									</td>
									<td colspan="2" style="width:30%;"><div class="submit" >&nbsp;&nbsp;&nbsp;<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
								</tr> 
							</tbody>
						</table>
					</form>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['marketingManagement/activityApply/censor'] && btnAu['marketingManagement/activityApply/censor']}">
						<button type="button" class="btn btn-success" id="passId"><span class="icon-ok-sign"></span>&nbsp;通过</button>
						<button type="button" class="btn btn-warning" id="notPassId"><span class="icon-remove-sign"></span>&nbsp;不通过</button>
						</c:if>
						
						<c:if test="${null!=btnAu['marketingManagement/activityApply/export'] && btnAu['marketingManagement/activityApply/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
						</c:if>
					</div>
					<div id="activityApplyList"></div>
				</div>
			</div>
		</div>
	</c:if>

    <!-- 自定义活动 -->
    <%--<c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}"> --%>
    <c:if test="true">
	    <div id="tab2" class="tab-pane"> <%--<c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init2']}">in active</c:if>"> --%>
  	
		  	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" method="post"  id="customizeForm">
						<input type="hidden" name="type" value="2">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:5%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
									<td style="width:15%;"><input type="text"
											class="form-control" name="sellerid"
											value="${sellerid}" style="width:90%;">
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
									<td style="width:15%;;"><input type="text"
										class="form-control" name="sellername"
										value="${sellername}" style="width:90%;"></td>									
									<td style="width:5%;"><h5>&nbsp;&nbsp; 申请日期:</h5></td>
									<td style="width:25%;;"><input type="text"
										name="sdateStart" value="${sdateStart}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
										<label style="float: left;">&nbsp;--&nbsp;</label> <input
										type="text" name="sdateEnd" value="${sdateEnd}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp;&nbsp;</h5></td>
									<td style="width:25%;;">

									</td>
								</tr>
								<tr>
									<td style="width:5%;"><h5>&nbsp;&nbsp;联系电话:</h5></td>
									<td style="width:15%;"><input type="text"
											class="form-control" name="phone"
											value="${phone}" style="width:90%;">
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp;状态:</h5></td>
									<td style="width:15%;;">
									<select class="form-control"
										name="status" style="width:90%;" >
											<option value="">请选择</option>
											<option value="0">待回复</option>
											<option value="1">已回复</option>
									</select>
									</td>
									<td style="width:5%;"><h5>&nbsp;&nbsp; 处理时间:</h5></td>
									<td style="width:25%;;"><input type="text"
										name="edateStart" value="${edateStart}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
										<label style="float: left;">&nbsp;--&nbsp;</label> <input
										type="text" name="edateEnd" value="${edateEnd}"
										placeholder="yyyy-MM-dd"
										class="form-control form-datetime" style="width:43%;float:left">
									</td>
									<td colspan="2" style="width:30%;"><div class="submit" >&nbsp;&nbsp;&nbsp;<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
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
<%--
						<c:if test="${null!=btnAu['marketingManagement/activityApply/censor'] && btnAu['marketingManagement/activityApply/censor']}">
						<button type="button" class="btn btn-success" id="passIdCustomize"><span class="icon-ok-sign"></span>&nbsp;通过</button>
						<button type="button" class="btn btn-warning" id="notPassIdCustomize"><span class="icon-remove-sign"></span>&nbsp;不通过</button>
						<span style="color:red; font:italic bold 14px/20px arial,sans-serif;  vertical-align:middle;  ">商户自定义活动申请审核通过后，需操作人员，手动添加该活动</span>
						</c:if>
						
						<c:if test="${null!=btnAu['marketingManagement/activityApply/export'] && btnAu['marketingManagement/activityApply/export']}"><button type="button" id="exportCustomize" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
						</c:if>
--%>						
					</div>
					<div id="customizeApplyList"></div>
				</div>
			</div>
		</div>
	</c:if>
	</div>
	
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="refuseModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               	申请不通过
	            </h4>
	         </div>
	         <div class="modal-body">
	         	
				<form class="form-horizontal" role="form" id="refuseForm" style=" width :580px;overflow-y:auto; ">
				<input type="hidden"   id = "callType" name="callType" value="">
				<input type="hidden"   id = "status" name="status" value="2">
				<input type="hidden"   id = "ids" name="ids" value="${param.ids}">
				<table width="100%">
					<tbody>
						<tr>	
							<th style="width:90px;"><h5>&nbsp;&nbsp;未通过原因:</h5></th>
							<th colspan="2">
								<textarea name="reason" rows="10" id="reason" class="form-control" placeholder="未通过原因"></textarea>
							</th>	
						</tr>
						<tr>
							<th colspan="3" style="text-align: center;"> 
								<button type="button" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保存 </button>
								<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
							</th>
						</tr>
					</tbody>
				</table>
				</form>
	         </div>
	      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

	<script type="text/json" id="permissions">{view:'${ btnAu['marketingManagement/activityApply/init']}',deal:'${ btnAu['marketingManagement/activityApply/censor']}',export:'${btnAu['businessman/sellerApply/export'] }',response:'${btnAu['marketingManagement/activityApply/response'] }'}</script>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/marketingmanagement/activityApply.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

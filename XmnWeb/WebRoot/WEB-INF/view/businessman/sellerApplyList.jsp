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
    <title>商户信息修改申请</title>
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
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家编号:</h5></td>
							<td style="width:25%;"><input type="text"
								class="form-control" name="sellerid"
								value="${param.sellerid}" style="width:90%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp; 申请时间:</h5></td>
							<td style="width:25%;;"><input type="text"
								name="stdateStart" value="${param.stdateStart}"
								placeholder="yyyy-MM-dd hh:mm"
								class="form-control form-datetime" style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label> <input
								type="text" name="stdateEnd" value="${param.stdateEnd}"
								placeholder="yyyy-MM-dd hh:mm"
								class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;&nbsp;状态：</h5></td>
							<td style="width:25%;;">
							<select class="form-control"
								name="status" style="width:78%;" >
									<option value="">请选择</option>
									<option value="0" ${param.status=='0'?"selected":""}>待审核</option>
									<option value="1" ${param.status=='1'?"selected":""}>审核通过</option>
									<option value="2" ${param.status=='2'?"selected":""}>审核未通过</option>
									<option value="3" ${param.status=='3'?"selected":""}>撤销申请修改</option>
							</select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:25%;;"><input type="text"
								class="form-control" name="sellername"
								value="${param.sellername}" style="width:90%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp; 处理时间:</h5></td>
							<td style="width:25%;;"><input type="text"
								name="endateStart" value="${param.endateStart}"
								placeholder="yyyy-MM-dd hh:mm"
								class="form-control form-datetime" style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label> <input
								type="text" name="endateEnd" value="${param.endateEnd}"
								placeholder="yyyy-MM-dd hh:mm"
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
				<%-- <c:if test="${null!=btnAu['businessman/sellerApply/delete'] && btnAu['businessman/sellerApply/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if> --%>
				<c:if test="${null!=btnAu['businessman/sellerApply/updateState'] && btnAu['businessman/sellerApply/updateState']}">
				<button type="button" class="btn btn-success" id="passId"><span class="icon-ok-sign"></span>&nbsp;通过</button>
				<button type="button" class="btn btn-warning" id="notPassId"><span class="icon-remove-sign"></span>&nbsp;不通过</button>
				</c:if>
				
				<c:if test="${null!=btnAu['businessman/sellerApply/export'] && btnAu['businessman/sellerApply/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="sellerApplyList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{view:'${ btnAu['businessman/sellerApply/view/init']}',del:'${ btnAu['businessman/sellerApply/delete']}',export:'${btnAu['businessman/sellerApply/export'] }',more:'${btnAu['businessman/sellerApply/updateState'] }'}</script>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/businessman/sellerApply.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

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
<title>打赏返利管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" name="eUid" value="${eUid}">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
								<td style="width:25%;"><select class="form-control" id="anchorId"  name="anchorId"
												initValue="" style="width:98%;">
											</select> </td>
								<td style="width:5%;"><h5>主播手机:</h5></td>
								<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
								<td style="width:5%;"><h5>发放状态:</h5></td>
								<td style="width:230px;"><select class="form-control" name="status" style = "width:75%;">
										<option value="">请选择</option>
										<option value="0">未发放</option>
										<option value="1">已发放</option>
									</select></td>	
							</tr>
							<tr>
								<td style="width:5%;"><h5>主播类型:</h5></td>
								<td style="width:230px;"><select class="form-control" name="signType" style = "width:75%;">
										<option value="">请选择</option>
										<option value="1">签约主播</option>
										<option value="2">兼职主播</option>
										<option value="3">测试账号</option>
									</select></td>	
								<td style="width:5%;"><h5>&nbsp;&nbsp;统计时间:</h5></td>
								<td style="width:25%;"><input type="text" name="countTime" value="" placeholder="yyyy-MM" class="form-control form_datetime" style="width:46%;float:left">
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
				<c:if test="${btnAu['liveSalary/export']}">
						<button type="button" class="btn btn-default" id="export" ><span class="icon-download-alt"></span>&nbsp;导出
					   </button>
				</c:if>
			</div>
			<div id="recordList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
		 update:'${ btnAu['liveSalary/update']}',
	 	 update2:'${ btnAu['liveSalary/confirmSalary']}',
	 	 export:'${ btnAu['liveSalary/export']}'
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
		<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/liveSalaryList.js"></script>
</body>
</html>

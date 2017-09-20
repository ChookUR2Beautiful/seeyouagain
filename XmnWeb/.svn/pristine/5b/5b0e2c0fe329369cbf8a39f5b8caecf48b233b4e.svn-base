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
<title>提现税费管理【V客】列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">

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
	<div class="panel panel-default">
		<div class="panel-body data">

			<div id="liveDepositorsTaxesInfoList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{xg:'${ btnAu['liveDepositorsTaxes/manage/update'] }'}
    </script>
    
    <div class="modal fade" id="editliveDepositorsTaxesModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">【V客】修改提现税费</h4>
				</div>
				<div class="modal-body example" style="height: 260px;">
					<form class="form-horizontal" id="recommendSpecialForm">
					    <input type="hidden" id="type" name="type" />
						<div class="form-group">
							<label class="col-md-3 control-label">费率类型: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input  type="radio" name = "rateType" value = "1" ><span style="font-size: 12px;">按比例</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input  type="radio" name = "rateType" value = "2" ><span style="font-size: 12px;">固定金额</span> 
							</div> 
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">手续费: <span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<div class="row">
									<div class="col-md-7">
										<input type="text" class="form-control" id="rate" placeholder="请输入0-100之间的数"
											name="rate" style="width: 88%; float: left"> 
									</div>
								</div>
								<%-- <h5><label style="color:red;">*固定金额填写具体金额，按比例分账填写百分比</label></h5> --%>
							</div>
						</div>
						<!-- <div class="form-group">
							<div class="col-sm-offset-3 col-sm-6"><label style="color:red;">*&nbsp;&nbsp;固定金额填写具体金额，按比例填写百分比(%)</label></div>
						</div> -->
						<div class="form-group">
							<!-- <label class="col-md-2 control-label"></label> -->
							<div class="col-sm-offset-3 col-sm-8">
								<div class="alert alert-warning">
									<strong>提示：</strong> 固定金额填写具体金额，&nbsp;按比例填写0%到100%
								</div>
							</div>
		
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" id="depositorsTaxesModalSubmit">保存</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div>
    
    
    
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>

	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script src="<%=path%>/js/live_anchor/depositorsTaxes/liveDepositorsTaxesList.js?v=1.0.5"></script>
	
</body>
</html>

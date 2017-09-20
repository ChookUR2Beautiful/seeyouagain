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
<title>体验官配置</title>
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
			<form class="form-horizontal" role="form" id="editFrom">
					<input class="form-control" type="hidden"  name="id" style="width:66%;" value="${config.id}" >
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:30%;text-align: right;"><h5>是否免费领取:&nbsp;&nbsp;</h5></td>
								<td style="width:60%;">
								<h5>&nbsp;&nbsp;&nbsp;&nbsp;
								<label></label><input  type="radio"  name="isFree" value="0" ${config.isFree==0?'checked':''}> 否</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label><input  type="radio"  name="isFree" value="1" ${(config.isFree==null || config.isFree==1)?'checked':''} >是</label></h5>
								</td>
							</tr>
							<tr id="price_id">
								<td style="width:30%;text-align: right;"><h5>资格卡金额:&nbsp;&nbsp;</h5></td>
								<td style="width:60%;"><input class="form-control"   name="price" style="width:66%;" value="${config.price}" > </td>
							</tr>
							<tr>
								<td style="width:30%;text-align: right;"><h5>可用次数:&nbsp;&nbsp;</h5></td>
								<td style="width:60%;"><input type="text" class="form-control"  name="nums" style = "width:66%;" value="${config.nums}"></td>
							</tr>
							<tr>
								<td style="width:30%;text-align: right;"><h5>有效天:&nbsp;&nbsp;</h5></td>
								<td style="width:60%;"><input type="text" class="form-control"  name="days" style = "width:66%;" value="${config.days}"></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;"> 
			 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
									<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
		 						</td>
							</tr>
						</tbody>
					</table>
			</form>
		</div>
	</div>
	

	<script type="text/json" id="permissions">{
		 update:'${ btnAu['experienceofficer/user/activity/update']}',
	}</script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/experienceofficer/liveExperienceofficerSetting.js"></script>
</body>
</html>

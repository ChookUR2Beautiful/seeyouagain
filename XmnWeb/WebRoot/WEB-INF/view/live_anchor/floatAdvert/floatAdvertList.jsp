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
<title>悬浮广告管理</title>
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

</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="floatAdvertFromId">
				<table style="">
					<tr>
					  	<td style="width:6%;"><h5>券名:</h5></td>
						<td style="width:20% !important;">
						    <input type="text" class="form-control"  value="${param.cname}" name="cname"  style="width:80%">
						</td>
						<td style="width:6%;"><h5>广告时间:</h5></td>
						<td style="width:20% !important;">
						    <input type="text" name ="startTime" value="${param.startTime}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:40%;float:left" >
						    <label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label>
						    <input type="text" name ="endTime" value="${param.endTime}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:40%;float:left">
						</td>
						<td style="width:6%;"><h5>状态:</h5></td>
						<td style="width:20% !important;">
						    <select class="form-control" tabindex="2" name ="status" value="${param.status}" style = "width:55%;">
							    <option value = "">--请选择--</option>
				                <!-- <option value = "0">未验证</option> -->
				                <option value = "1" ${param.status==1?"selected":""}>上线</option>
				                <option value = "2" ${param.status==2?"selected":""}>未开始</option>
				                <option value = "3" ${param.status==3?"selected":""}>已结束</option>
				                <option value = "4" ${param.status==4?"selected":""}>已下线</option>
							</select>
						</td>
						<!-- <td colspan="2">
							style="text-align: right; "
							<div class="submit" style="float: left;">
								<input class="submit radius-3" type="button" value="查询全部" data-bus='query' /> 
								<input class="reset radius-3" type="reset" value="重置全部" data-bus='reset' />
							</div>
						</td> -->
				        <td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
								</div>
						</td> 
	
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['floatAdvert/manage/add'] && btnAu['floatAdvert/manage/add']}">
				    <a type="button" id="addFloatAdvertBto" class="btn btn-success"  href="floatAdvert/manage/add/init.jhtml?isType=add" >
				    <span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${ btnAu['liveAndroid/manage/delete']}">
					<button type="button" class="btn btn-danger" id="deleteBto">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if>
				
				<c:if test="${!empty btnAu['floatAdvert/manage/beachOnLine']}"> <button type="button" class="btn btn-info" id="beachOnLine" ><span class="icon-hand-up"></span>&nbsp;上线</button></c:if>
				<c:if test="${!empty btnAu['floatAdvert/manage/downLine']}"><button type="button" class="btn btn-warning" id="beachDownLine" ><span class="icon-hand-down"></span>&nbsp;下线</button></c:if>
			
			</div>
			<div id="floatAdvertInfoDiv"></div>
		</div>																																				
	</div>
	
	<script type="text/json" id="permissions">{
			update:'${ btnAu['floatAdvert/manage/update/init']}',
			delete:'${ btnAu['floatAdvert/manage/delete']}'
		}
	</script>
	
    <script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script src="<%=path%>/js/live_anchor/floatAdvert/floatAdvertList.js"></script>
	
</body>
</html>

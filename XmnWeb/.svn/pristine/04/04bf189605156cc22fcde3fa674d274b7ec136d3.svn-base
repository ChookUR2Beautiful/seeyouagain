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
<title>钱包记录列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
</head>

<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
		
			<form class="form-horizontal" role="form" id="livePurseFromId">
				<input type="hidden" id="uid" name="uid" value="${uid}">
				<div class="form-group">
	                <table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:100px;"><h5>虚拟货币：</h5></td>
								<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="type" value="${param.type}" style = "width:75%;">
							                <option value = "">全部</option>
							                <option value = "1" ${param.status==1?"selected":""}>鸟豆</option>
							                <option value = "2" ${param.status==2?"selected":""}>鸟币</option>
							        </select>
							   </td>
							   <td style="width: 100px;"><h5>类型：</h5></td>
							   <td style="width: 400px;">
							       <select class="form-control" tabindex="2" name="option" value="${param.option}" style="width: 75%;">
										<option value="">全部</option>
										<option value="1" ${param.status==1?"selected":""}>增加</option>
										<option value="2" ${param.status==2?"selected":""}>扣减</option>
								   </select>
							   </td>
							   <td style="width: 100px;"><h5>渠道：</h5></td>
							   <td style="width: 400px;">
							       <select class="form-control" tabindex="2" name="rtype" value="${param.rtype}" style="width: 75%;">
										<option value="">全部</option>
										<option value="1" ${param.status==1?"selected":""}>平台充值</option>
										<option value="2" ${param.status==2?"selected":""}>打赏</option>
										<option value="3" ${param.status==3?"selected":""}>打赏返还</option>
										<option value="4" ${param.status==4?"selected":""}>红包返还</option>
								   </select>
							   </td>
							   <!-- <td colspan="2" style="width: 600px; text-align: right; " >
									<div class="submit">
									   <input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									   <input  class="reset radius-3" type="reset" value="导出"  data-bus = 'reset' />
									</div>
								</td> -->
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;width:100%;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
				<%-- <c:if
					test="${null!=btnAu['liveMember/manage/add'] && btnAu['liveMember/manage/add']}">
					<a type="button" class="btn btn-success"  href="liveMember/manage/add/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${btnAu['liveMember/manage/export']}">
						<button type="button" id="exportAnchor" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if> --%>
				<button type="button" id="exportlivePurseList" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
			</div>
			<div id="livePurseInfo"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			add:'${ btnAu['liveMember/manage/add']}',
			update:'${ btnAu['liveMember/manage/update']}'
		}
	</script>
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/livePurseList.js?v=1.0.5"></script>
</body>
</html>

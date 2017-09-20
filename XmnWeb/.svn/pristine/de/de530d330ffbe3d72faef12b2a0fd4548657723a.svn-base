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
<title>精彩视频</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<table style="width:100%;">
				<tbody >
						<tr>
							<td style="width:5%;"><h5>主播昵称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" name="anchorName"
							value="" /></td>
							<td style="width:5%;"><h5>标题:</h5></td>
							<td style="width:18%;">
									<input type="text" class="form-control" name="title"
							value="" />
								</td>
							<td style="width:5%;"><h5>状态:</h5></td>
							<td style="width:18%;">
									<select class="form-control"  name="status">
										<option value="">--请选择--</option>
										<option value="001">上线</option>
										<option value="002">下线</option>
									</select>
								</td>
					  		<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>	
					    </tr>
					 </tbody>
					 </table>
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
					test="${null!=btnAu['liveAnchorVideo/manage/add'] && btnAu['liveAnchorVideo/manage/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="添加精彩视频" data-url="liveAnchorVideo/manage/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
				<c:if test="${ btnAu['liveAnchorVideo/manage/delete']}">
					<button type="button" class="btn btn-danger" id="delete">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if>
				<c:if test="${!empty btnAu['liveAnchorVideo/manage/update'] && btnAu['liveAnchorVideo/manage/update']}">
						<button type="button" class="btn btn-info" id="putaway" ><span class="icon-hand-up"></span>&nbsp;上线</button>
				</c:if>
				<c:if test="${!empty btnAu['liveAnchorVideo/manage/update'] && btnAu['liveAnchorVideo/manage/update']}">
					<button type="button" class="btn btn-warning" id="removeOffshelf" ><span class="icon-hand-down"></span>&nbsp;下线</button>
				</c:if>
				<c:if test="${!empty btnAu['liveAnchorVideo/manage/update'] && btnAu['liveAnchorVideo/manage/update']}">
					<button type="button" class="btn" id="setHome" ><i class="icon icon-smile"></i>&nbsp;设为首页推荐</button>
				</c:if>
			</div>
			<div id="recordList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
	  add:'${ btnAu['liveAnchorVideo/manage/add']}',
	  update:'${ btnAu['liveAnchorVideo/manage/update']}',
	  delete:'${ btnAu['liveAnchorVideo/manage/delete']}',
	}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/anchorVideoManage.js"></script>
</body>
</html>

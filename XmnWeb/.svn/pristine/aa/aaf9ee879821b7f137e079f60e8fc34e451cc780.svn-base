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
<title>标签</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<label class="col-md-1 control-label">标签名称：</label>
					<div class="col-md-3" style="width: 15%;">
						<input type="text" class="form-control" name="tagname"
							value="${tagname}" />
					</div>
					<label class="col-md-1 control-label">标签类型：</label>
					<div class="col-md-3" style="width: 15%;">
						<select id="tagtype" name="tagtype" initValue="${tagtype}" class="form-control"style="width:100%;">
										<option value="">请选择</option>
										<option value= "0" >岗位标签</option>
										<option value= "1" >自我评价</option>
										<option value= "2" >培训</option>
										<option value= "3" >福利</option>
										<option value= "4" >岗位要求</option>
						</select>
					</div>
					<div class="submit">
						<input class="submit radius-3" type="button" value="查询全部"
							data-bus='query' /> <input type="reset" class="reset radius-3"
							value="重置全部" data-bus='reset' />
					</div>
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 15px;">
				<c:if
					test="${null!=btnAu['dataDictionary/tag/add'] && btnAu['dataDictionary/tag/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="添加标签" data-url="dataDictionary/tag/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;添加
					</button>
				</c:if>
			</div>
			<div id="recruitTagList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['dataDictionary/tag/update']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/dataDictionary/recruitTagList.js"></script>
</body>
</html>

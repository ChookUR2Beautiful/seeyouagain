<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>直播分账记录管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>

<body	
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>

		<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['dynamicVideo/edit']}">
						<a type="button" style="float:left" id="addPackageBtn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-type="ajax"  data-url="dynamicVideo/edit/dynamic/init.jhtml"><span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
					<span style="text-align:center">大赛动态</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">标题</th>
						<th style="width:130px;">图片</th>
						<th style="width:130px;">地区</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">位置</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="dynamicList">
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['dynamicVideo/edit']}">
						<a type="button" style="float:left" id="addSpecial_btn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-type="ajax"  data-url="dynamicVideo/edit/video/init.jhtml"><span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
					<span style="text-align:center">精彩回顾</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">视频名称</th>
						<th style="width:130px;">主播</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="videoList">
				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="editSortModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">排序:</h4>
				</div>
				<div class="modal-body example" style="height: 150px;">
					<form class="form-horizontal" id="chooseForm">
						<div class="form-group">
							<label for="exampleInputPassword5" class="col-sm-2">修改排序</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="editSort_inp"
									name="editSort_inp" style="width:200px;">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" id="editSortSubmit">保存</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script type="text/json" id="permissions" >{
			edit:'${ btnAu['dynamicVideo/edit']}',
			delete:'${ btnAu['dynamicVideo/delete']}'
		}
	</script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>

	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
		<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
		<script src="<%=path%>/resources/web/js/util.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>	
		<script src="<%=path%>/ux/js/multipleChosen.js"></script>
	<script src="<%=path%>/js/vstar/dynamicVideo/dynamicVideoList.js"></script>
	<script type="text/javascript">
		$("input[type=number]").attr("min",0);
	</script>
</body>
</html>

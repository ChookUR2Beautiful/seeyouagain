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
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<label class="col-md-1 control-label">地区：</label>
					<div class="col-md-3" style="width: 25%;">
						<div class="input-group" id="formLd" style="width:83%"
							<c:choose>
									    <c:when test="${!empty freshInfo.city}">
									    	initValue=" ${freshInfo.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${freshInfo.province}"
									    </c:otherwise>
									 </c:choose>>
						</div>
					</div>
					<div class="submit">
						<input class="submit radius-3" type="button" value="查询全部"
							id="querySubmit" /> <input type="reset" class="reset radius-3"
							value="重置全部" id="queryReset" />
					</div>
				</div>

			</form>
		</div>
	</div>

		<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['businessman/recommend/addPackage']}">
						<a type="button" style="float:left" id="addPackageBtn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-type="ajax"  data-url="businessman/recommend/activityEdit.jhtml"><span class="icon-plus"></span>&nbsp;添加活动</a>
					</c:if>
					<span style="text-align:center">活动模块</span>
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
				<tbody id="activityList">
				</tbody>
			</table>
		</div>
	</div>

	<%-- <div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['businessman/recommend/addPackage']}">
						<a type="button" style="float:left" id="addPackageBtn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-target="#packageModal"><span class="icon-plus"></span>&nbsp;添加套餐</a>
					</c:if>
					<span style="text-align:center">今天吃啥好</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">套餐名称</th>
						<th style="width:130px;">商家</th>
						<th style="width:130px;">地区</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="packageList">
				</tbody>
			</table>
		</div>
	</div> --%>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['businessman/recommend/addSpecial']}">
						<a type="button" style="float:left" id="addSpecial_btn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-target="#specialModal"><span class="icon-plus"></span>&nbsp;添加专题</a>
					</c:if>
					<span style="text-align:center">网红推荐</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">专题名称</th>
						<th style="width:130px;">类型</th>
						<th style="width:130px;">地区</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="specialList">
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['businessman/recommend/addSpecial']}">
						<a type="button" style="float:left" id="addSpecial_btn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-type="ajax"  data-url="businessman/recommend/topicEdit.jhtml"><span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
					<span style="text-align:center">为你优选</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">主题</th>
						<th style="width:130px;">关联标签</th>
						<th style="width:130px;">地区</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="topicList">
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['businessman/recommend/addSpecial']}">
						<a type="button" style="float:left" id="addSpecial_btn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-type="ajax"  data-url="businessman/recommend/commentEdit.jhtml"><span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
					<span style="text-align:center">网红头条</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">商户</th>
						<th style="width:130px;">地区</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="commentList">
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
					<c:if test="${ btnAu['businessman/recommend/addSpecial']}">
						<a type="button" style="float:left" id="addSpecial_btn"
							class="btn btn-success" data-position="0" data-toggle="modal"
							data-type="ajax"   data-url="businessman/recommend/mustbuyEdit.jhtml"><span class="icon-plus"></span>&nbsp;添加</a>
					</c:if>
					<span style="text-align:center">必买清单</span>
				</div>
				<thead>
					<tr>
						<th style="width:130px;">类型</th>
						<th style="width:130px;">关联分类</th>
						<th style="width:130px;">排序</th>
						<th style="width:130px;">操作</th>
					</tr>
				</thead>
				<tbody id="mustbuyList">
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="modal fade" id="specialModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">添加专题推荐</h4>
				</div>
				<div class="modal-body example" style="height: 250px;">
					<form class="form-horizontal" id="chooseForm">
						<div class="form-group">
							<label for="exampleInputPassword5" class="col-sm-2">添加专题推荐</label>
							<div class="col-sm-10">
								<select class="form-control" id="special_choose"
									name="special_choose" style="width:41%;float:left"></select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-2 col-xs-3 reset-form-name">
								<em class="em-tips-font">*</em>排序：
							</div>
							<div class="col-lg-9 col-xs-9">
								<input type="number" class="form-control" id="homeSort"
									name="homeSort" style="width:100px;">
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-2 col-xs-3 reset-form-name">
								<em class="em-tips-font">*</em>地区：
							</div>
							<div class="col-lg-9 col-xs-9">
								<div class="input-group" id="ld" style="width:83%"
									<c:choose>
									    <c:when test="${!empty freshInfo.city}">
									    	initValue=" ${freshInfo.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${freshInfo.province}"
									    </c:otherwise>
									 </c:choose>>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								id="specialModalSubmit">保存</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" id="packageModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">添加套餐推荐</h4>
				</div>
				<div class="modal-body example" style="height: 250px;">
					<form class="form-horizontal" id="chooseForm">
						<div class="form-group">
							<label for="exampleInputPassword5" class="col-sm-2">添加套餐推荐</label>
							<div class="col-sm-10">
								<select class="form-control" id="package_choose"
									name="package_choose" style="width:41%;float:left"></select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-2 col-xs-3 reset-form-name">
								<em class="em-tips-font">*</em>排序：
							</div>
							<div class="col-lg-9 col-xs-9">
								<input type="number" class="form-control" id="homeSort"
									name="homeSort" style="width:100px;">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								id="packageModalSubmit">保存</button>
						</div>
					</form>
				</div>

			</div>
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
			addSpecial:'${ btnAu['businessman/recommend/addSpecial']}',
			deleteSpecial:'${ btnAu['businessman/recommend/delete']}',
			addPackage:'${ btnAu['businessman/recommend/addPackage']}',
			deletePackage:'${ btnAu['businessman/recommend/deletePackage']}'
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
	<script src="<%=path%>/js/businessman/recommend/recommendList.js"></script>
	<script type="text/javascript">
		$("input[type=number]").attr("min",0);
	</script>
</body>
</html>

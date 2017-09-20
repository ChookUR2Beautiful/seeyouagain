<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"  value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" 
				id="brandForm"	>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>品牌名称:</h5></td>
							<td style="width:18%;"><input type="text"
								class="form-control" name="name" style="width:90%;" /></td>
							<td style="width:5%;"><h5>所属分类：</h5></td>
							<td style="width:18%">
								<div class="container">
        <div class="dropdown">
            <select  role="button" data-toggle="dropdown" class="btn form-control" data-target="#" name="typeId" >
               <option  value="" style="display:none;" >请选择</option>
            	<option id="dLabel"  value="" style="display:none;" ></option>
            </select>
            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu" >
            	<li><a href="javascript:;" id="" onclick="confirmType(this)">请选择</a></li>
            	<c:forEach items="${freshTypes}" var="freshType">
				 <li class="dropdown-submenu">
                    <a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${freshType.id}">${freshType.name}</a>
                    <c:if test="${freshType.childs!=null}">
                    <ul class="dropdown-menu">
                    <c:forEach items="${freshType.childs}" var="type">
                        <li><a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${type.id}">${type.name}</a></li>
                       </c:forEach>
                    </ul>
                    </c:if>
                </li>
				</c:forEach>
            </ul>
        </div>
    </div>

							<%-- <select class="form-control"
								name="typeId" style="width:90%">
									<option value="">选择分类</option>
									<c:forEach items="${freshTypes}" var="freshType">
										<option value="${freshType.id}">${freshType.name}</option>
									</c:forEach>
							</select> --%></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' style="width:43%;flaot:left;margin:0 2%;" />
									<input class="reset radius-3" type="reset" value="重置全部"
										data-bus='reset' style="width:43%;flaot:left;margin:0 2%;" />
								</div>
							</td>
							<td colspan="2" style="width:18%;"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	</div>
	<div class="panel">
		<div class="panel-body">
		<c:if test="${!empty btnAu['fresh/brand/delete'] }">
			<button class="btn btn-danger " id="delete" style="width: 5%;" type="button">删除</button>
		</c:if>
			<div class="btn-group">
			<c:if test="${!empty btnAu['fresh/brand/add'] }">
				<button type="button" class="btn" data-type="ajax"   
					data-url="fresh/brand/add/init.jhtml" data-toggle="modal">添加品牌</button>
			</c:if>
			<c:if test="${!empty btnAu['fresh/brand/importBrand/importData'] }">
				<button type="button" class="btn" id="" data-toggle="modal"
					data-url="fresh/brand/import/init.jhtml" data-type="ajax">
					<span class="icon-download-alt"></span>导入品牌
				</button>
			</c:if>
			<c:if test="${!empty btnAu['fresh/brand/export'] }">
				<button type="button" class="btn" id="exportBrand">
					<span class="icon-download-alt"></span>导出品牌
				</button>
			</c:if>
			</div>
			<div class="panel-body data">
				<div id="brandList"></div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="exportBrandModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<form id="exportForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请选择要导出的品牌</h4>
				</div>
				<div class="modal-body" style="height:200px;padding:50px;">
					<div class="form-group" style="height:30px;">
						<label for="courierNumber" class="col-sm-3 control-label"><h5>选择品牌分类:</h5></label>
						<div class="col-md-7 col-sm-8">	
							<div class="dropdown">
					            <select  role="button" data-toggle="dropdown" class="btn form-control" data-target="#" name="typeId" value=""
					               href="javascript:;"  >
					               <option  value="" style="display:none;"></option>
					               <option id="importType"  value="" style="display:none;"></option>
					            </select>
					            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu" >
					            	<c:forEach items="${freshTypes}" var="freshType">
									 <li class="dropdown-submenu">
					                    <a tabindex="-1" href="javascript:;" onclick="confirmImportType(this)" id="${freshType.id}">${freshType.name}</a>
					                    <c:if test="${freshType.childs!=null}">
					                    <ul class="dropdown-menu">
					                    <c:forEach items="${freshType.childs}" var="type">
					                        <li><a tabindex="-1" href="javascript:;" onclick="confirmImportType(this)" id="${type.id}">${type.name}</a></li>
					                       </c:forEach>
					                    </ul>
					                    </c:if>
					                </li>
									</c:forEach>
					            </ul>
					        </div>
						</div>
					</div>
					<div class="form-group" style="height:30px;">
						<label for="courierNumber" class="col-sm-3 control-label"><h5>品牌编号:</h5></label>
						<div class="col-md-7 col-sm-10">
							<input type="number" class="form-control" id="minNum" name="minNum"
								 value="" style="width:40%;float:left" /> <label
								style="float: left;">&nbsp;--&nbsp;</label> <input type="number"
								class="form-control" id="maxNum"  name="maxNum"
								value="" style="width:40%;float:left" />

						</div>
					</div>
					<div class="form-group" style="height:30px;">
						<label for="courierNumber" class="col-sm-3 control-label"><h5>所在页码:</h5></label>
						<div class="col-md-7 col-sm-10">
							<input type="number" class="form-control" id="minPage" name="minPage"
								value="" style="width:40%;float:left" /> <label
								style="float: left;">&nbsp;--&nbsp;</label> <input type="number"
								class="form-control" id="maxPage"  name="maxPage"
								value="" style="width:40%;float:left" />
						</div>
					</div>
				</div>
				<!-- <div class="form-group">
				
				</div> -->
				<div class="modal-footer">
					<button id="exportsubconcel" type="button" class="btn btn-default"
						data-dismiss="modal">取消</button>
					<button id="export" type="button" class="btn btn-default">导出</button>
				</div>
			</div>
		</div>
		</form>
	</div>
</body>
<script type="text/json" id="permissions">{
	  edit:'${ btnAu['fresh/category/edit']}',
	  add:'${ btnAu['fresh/brand/add']}',
	  delete:'${ btnAu['fresh/brand/delete']}',
	  import:'${ btnAu['fresh/brand/importBrand/importData']}',
	  export:'${ btnAu['fresh/brand/export']}'
	}</script>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script
	src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/ux/js/scrollTablel.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=path%>/js/fresh/brandList.js"></script>
<script type="text/javascript">
	var basePath = "${pageContext.request.contextPath}";
	function formSubmit() {
		alert();
	}
	$("#exportBrand").on("click", function() {
		$("#exportBrandModal").modal();
	});
	
	function confirmType(item){
		$("#dLabel").text($(item).text()).val($(item).attr("id")).attr("selected","selected");
	}
	function confirmImportType(item){
		$("#importType").text($(item).text()).val($(item).attr("id")).attr("selected","selected");
	}
	$("input[data-bus=reset]").click(function(){
		$("#dLabel").text("请选择").val("");
	});	
</script>
</html>

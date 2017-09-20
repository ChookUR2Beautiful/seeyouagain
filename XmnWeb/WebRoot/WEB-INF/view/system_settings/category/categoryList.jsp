<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>用户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a>发现美食</a></li>
		<%-- <c:if test="${null!=btnAu['system_settings/freshType/init/list'] && btnAu['system_settings/freshType/init/list']}">
			<li class=""><a href="fresh/manage/freshType/init.jhtml">积分商城</a></li>
		</c:if> --%>
	</ul>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<i class="icon-reorder"></i>&nbsp;&nbsp;分类列表
		</div>
		<div id="categoryDiv" class="panel-body" request-init="${requestInit}">
		</div>
			
		</div>
	</div>

	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>//resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
	<script type="text/javascript">
	//inserTitle(' > 系统管理 > <span><a href="system_settings/category/init.jhtml" target="right">分类管理</a>','category',true);
	inserTitle(' > 基础数据管理 > <span><a href="system_settings/category/init.jhtml" target="right">分类管理</a>','category',true);
	var category = $('#categoryDiv');
	var div;
	$(function(){
		var url  = [$(category).attr("request-init"),".jhtml"].join("");
		div = $(category).page({
			url : url,
			dataType:"html",
			success : handle,
			pageBtnNum : 10
		});
		$(category).on("click",".removeBtn",removeCategory);
	});
	
	function removeCategory(e){
		var id = $(this).attr("data-removeId");
		showSmConfirmWindow(function() {
				$.ajax({
			        type: "POST",
			        url: "system_settings/category/delete.jhtml",
			        data: {"id":id},
			        dataType: "json",
			        success: function(result){
						showSmReslutWindow(result.success, result.msg);
						if(result.success){
							div.reload();
						}
			         }
			    });
		},"确定要删除？");
	}
	
	function handle(data){
			var table  =$(category).find(".main.categorytable");
			if(table.length==0){
				$(category).prepend(data);
			}else{
				$(table).html(data);
			}
	}
	function formSubmit(){
		var form =$("body").find(".modal-body form");
		var action = $(form).attr("action");
		var method = $(form).attr("method");
		var param = jsonFromt($(form).serializeArray());
		$.ajax({
			url : action,
			type : method,
			data :param,
			cache:false
		}).done(function ( data ) {
			$('#triggerModal').modal('hide');
			if(data.success){
				div.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		});
		return false;
	}
	</script>
</body>
</html>
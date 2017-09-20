<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title>添加活动</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.min-1.7.0.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/uploader/zui.uploader.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none !important;
	margin: 0;
}

input[type="number"] {
	-moz-appearance: textfield;
}
</style>
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"
		value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="panel panel-primary">
			<div class="panel-heading">添加图文教学</div>
			<div class="panel-body">
				<form id="editFrom" role="form"
					class="form-horizontal scrollbar-hover">
					<input type="hidden" value="${content.id}" name="id"
						id="fashionTicketId" />
					<div class="form-group">
						<label class="col-md-3 control-label">教学标题：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="title" name="title"
								value="${content.title}" style="width:60%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">副标题：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="viceTitle"
								name="viceTitle" value="${content.viceTitle}"
								style="width:60%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">教学分类：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<select class="form-control" id="vstarDictId" name="vstarDictId">
								<c:forEach items="${dicts}" var="dict">
									<option value="${dict.id}"
										${content.vstarDictId==dict.id?'selected="selected"':''}>${dict.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">封面图片：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="hidden" class="form-control" id="coverImg"
								name="coverImg" value="${content.coverImg}">
							<div id="coverImgImg"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">教学内容：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<div class="block-content collapse in">
								<textarea id="content" class="ckeditor" name="content"
									width="">${content.contentText}</textarea>
							</div>
						</div>
					</div>

					<div id="details">
						<div id="videoBody">
							<div class="form-group">
								<label class="col-md-3 control-label">教学视频：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<button type="button" class="btn btn-success" data-type="ajax"
										data-url="VstarContent/manage/init/video.jhtml"
										data-toggle="modal">
										<span class="icon-plus"></span>&nbsp;添加
									</button>
								</div>
							</div>
							<d iv class="form-group"> <label
								class="col-md-3 control-label"></label>
							<div class="col-md-7">
								<table
									class="table table-hover table-bordered table-striped info">
									<thead>
										<tr>
											<th style="width:130px;">标题</th>
											<th style="width:130px;">描述</th>
											<th style="width:130px;">链接</th>
											<th style="width:130px;">截图</th>
											<th style="width:130px;">操作</th>
										</tr>
									</thead>
									<tbody id="videoList">
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">教学附件：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<div id='uploaderExample2' name="multipartFile" class="uploader"
								data-url="<%=path%>/VstarContent/manage/fileUpload.jhtml">
								<div class="uploader-message text-center">
									<div class="content"></div>
									<button type="button" class="close">×</button>
								</div>
								<div class="uploader-files file-list file-list-lg"
									data-drag-placeholder="请拖拽文件到此处">
								</div>
								<div class="uploader-actions">
									<div class="uploader-status pull-right text-muted"></div>
									<button type="button" class="btn btn-link uploader-btn-browse">
										<i class="icon icon-plus"></i> 选择文件
									</button>
									<button type="button" class="btn btn-link uploader-btn-start">
										<i class="icon icon-cloud-upload"></i> 开始上传
									</button>
								</div>
							</div>
						</div>
					</div>


					<div class="form-group">
						<label class="col-md-3 control-label">排序：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="number" class="form-control" id="sortVal"
								name="sortVal" min="0" value="${content.sortVal}"
								style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<div class="text-center" style="padding:10px 0 10px 0;">
							<button type="submit" class="btn btn-success" id="ensure">
								<span class="icon-ok"></span> 保 存
							</button>
							&nbsp;&nbsp; <a class="btn btn-warning"
								href="VstarContent/manage/init.jhtml"><span class="icon-remove"></span>&nbsp;取消</a>
						</div>
					</div>
			</div>
			</form>
		</div>
	</div>
	</div>
	<jsp:include page="../common.zui-1.7.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/uploader/zui.uploader.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/js/vstar/contentMainEdit.js?v=1.0.4"></script>
	<script type="text/javascript">
		 $(function(){
		 	if('${content.id}'){
		 		$.post("VstarContent/manage/main/edit/init/getContentEditMsg.jhtml",{"id":'${content.id}'},function(data,status){
		 			var staticFiles = new Array();
		 			//获取所有附件id和所有视频id
		 			if(status == 'success'){
		 				$.each(data.attachment,function(i,item){
		 					fileArr.push(item.id);
		 					var file={
									"id":item.id,
								"contentId":item.id,
								"type":item.fileType,
								"name":item.fileName,
								"lastModifiedDate":item.updateTime,
								"size":item.fileSize
							}
							staticFiles.push(file);
		 				});
		 				options.staticFiles=staticFiles;
						$('#uploaderExample2').uploader(options);
		 				$.each(data.video,function(i,item){
		 					videos.push(item.id);
		 				});
		 				loadVideo();
		 			}
		 			
		 		});
		 	}else{
				$('#uploaderExample2').uploader(options);
			}
		 });
	</script>
</body>
</html>

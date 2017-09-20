<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="videoFrom" role="form" class="form-horizontal">
		<c:if test="${!empty video}">
			<input type="hidden" name="id" id="videoId" value="${video.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">视频标题（分享时使用标题）：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="title"
					value="${video.title}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">视频描述（分享时使用描述）：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="description" id="description"
					value="${video.description}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">视频链接：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="videoUrl" id="videoUrl" 
					value="${video.videoUrl}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">视频截图（分享时使用图片和未打开是读取额图片）：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" id="videoImg" name="videoImg"
								value="${video.videoImg}">
							<div id="videoImgImg"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	
		<script  src="<%=path%>/js/vstar/contentVideoEdit.js"></script>


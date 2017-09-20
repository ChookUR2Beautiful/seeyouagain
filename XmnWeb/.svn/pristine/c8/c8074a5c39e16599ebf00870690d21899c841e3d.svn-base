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
			<label class="col-md-4 control-label">标题：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="title"
					value="${video.title}">
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
			<label class="col-md-4 control-label">时长：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="number" min="1" class="form-control" name="duration" id="duration" 
					value="${video.duration}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">单个视频教学收费：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="checkbox"  id="is_pay" ${video.zbalance!=null&&video.amount!=null?'checked=checked':''} >
			</div>
		</div>
		<div ${video.zbalance!=null&&video.amount!=null?'':'style="display:none;"'}  id="pay_model">
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-7">
				<input type="number" min="0.01" class="form-control" placeholder="鸟币" name="zbalance" value="${video.zbalance}"  style="width:50%"  /> 
				<input type="number" min="0.01" class="form-control" placeholder="金额" name="amount" value="${video.amount}"  style="width:50%"  /> 
				<input type="checkbox" id="look" ${video.experienceTime!=null?'checked=checked':''} />免费试看<input type="number" min="1" placeholder="分钟"  class="form-control" name="experienceTime" value="${video.experienceTime}" style="width:50%"> 
			</div>
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
	
		<script  src="<%=path%>/js/vstar/vstarLiverContent/liverContentVideoEdit.js"></script>


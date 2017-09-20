<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty groupLevel}">
			<input type="hidden" name="id" id="id" value="${catehomeActivity.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">精彩视频：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="rid" name="rid"
									style="width:41%;float:left" initValue="${catehomeComment.commontId}"></select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">排序：<span style="color:red;">*</span></label>
			<div class="col-md-7" >
				<input type="number" class="form-control" name="homeSort" id="homeSort" min="0"
					value="${catehomeActivity.sort}">
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
	<script type="text/javascript" src="<%=path%>/js/vstar/dynamicVideo/videoEdit.js">

</script>

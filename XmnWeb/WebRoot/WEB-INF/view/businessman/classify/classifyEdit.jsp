<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty classifyTag}">
			<input type="hidden" name="id" value="${classifyTag.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">类型标签：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="classifyType" value="1" type="radio"
									${classifyTag.classifyType==1?"checked":""}><span
									style="font-size: 12px;">商家标签</span>&nbsp;&nbsp;&nbsp;
								<input name="classifyType" value="2" type="radio"
									${classifyTag.classifyType==2?"checked":""}><span
									style="font-size: 12px;">主播标签</span>&nbsp;&nbsp;&nbsp;
								<input name="classifyType" value="3" type="radio"
									${classifyTag.classifyType==3?"checked":""}><span
									style="font-size: 12px;">直播标签</span>&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">分类：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="classifyName" id="classifyName"
					value="${classifyTag.classifyName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">标签：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="tagName" id="tagName"
					value="${classifyTag.tagName}">
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
	<script type="text/javascript" src="<%=path%>/js/businessman/classify/classifyEdit.js">
<!--

//-->
</script>

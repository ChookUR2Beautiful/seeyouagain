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
			<input type="hidden" name="id" id="id" value="${sellerTopic.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">标题：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input class="form-control" id="topicName"  name="topicName"
												 style="width:98%;"/>
			</div>
		</div>
		
		<div class="form-group" >
			<label class="col-md-4 control-label">图片：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="logoUrl" id="logoUrl"
					value="${sellerTopic.logoUrl}">
					<div id="picUrlImg"></div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">标签：<span style="color:red;">*</span></label>
			<div class="col-md-7">
							<select class="chosen-select form-control" id="tagIds" name="tagIds" multiple
										initValue="${catehomeActivity.tagIds }"  data-placeholder="请选择商户标签">
									</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">排序：<span style="color:red;">*</span></label>
			<div class="col-md-7" >
				<input type="number" class="form-control" name="sort" id="sort" min="0"
					value="${catehomeActivity.sort}">
			</div>
		</div>
		<div class="form-group">
							<label class="col-md-4 control-label">地区<span style="color:red;">*</span></label>		
							<div class="col-md-7" >
								<div class="input-group" id="activityLd" style="width:83%"
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
	<script type="text/javascript" src="<%=path%>/js/businessman/cateTopicEdit.js">

</script>

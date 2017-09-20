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
			<input type="hidden" name="id" id="id" value="${dynamic.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">位置：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="location" value="1" type="radio" ${dynamic.location==1?"checked":""} ><span style="font-size: 12px;">主</span>
									&nbsp;&nbsp;
									<input name="location" value="2" type="radio" ${dynamic.location==2?"checked":""} ><span style="font-size: 12px;">次</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">标题：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input class="form-control" id="title"  name="title"
												 style="width:98%;"/>
			</div>
		</div>
		
		<div class="form-group" >
			<label class="col-md-4 control-label">图片：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="imageUrl" id="imageUrl"
					value="${dynamic.imageUrl}">
					<div id="picUrlImg"></div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">链接：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="jumpUrl" id="jumpUrl"
					value="${dynamic.jumpUrl}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">排序：<span style="color:red;">*</span></label>
			<div class="col-md-7" >
				<input type="number" class="form-control" name="sort" id="sort" min="0"
					value="${dynamic.sort}">
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
			<label class="col-md-4 control-label">描述：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="content" id="content"
					value="${dynamic.content}">
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
	<script type="text/javascript" src="<%=path%>/js/vstar/dynamicVideo/dynamicEdit.js">

</script>

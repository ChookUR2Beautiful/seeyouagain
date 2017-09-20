<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${freshType!=null}">
			<input type="hidden" value="${freshType.id}" name="id" />
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">上一级分类：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control"
								name="fid" style="width:90%">
									<option value="">选择分类</option>
									<c:choose>
										<c:when test="${freshType.fid==0}">
											<option value="0" selected="selected">--顶级--</option>
										</c:when>
										<c:otherwise>
												<option value="0" >--顶级--</option>
											</c:otherwise>
									</c:choose>
									
									<c:forEach items="${freshTypes}" var="freshType1" >
										<c:choose>
											<c:when test="${freshType1.id==freshType.fid}">
												<option value="${freshType1.id}" selected="selected">${freshType1.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${freshType1.id}">${freshType1.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">分类名称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="name" id="name"
					value="${freshType.name}">
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">分类图片：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="showSmallImg" id="showSmallImg"
					value="${freshType.showSmallImg}">
					<div id="picUrlImg"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">分类排序：<span style="color:red;">*</span></label>
		<div class="col-md-7">
				<input type="text" class="form-control" name="sort" id="sort"
					value="${freshType.sort}">
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
	<script src="<%=path%>/js/fresh/categoryEdit.js"></script>
	<script type="text/javascript">
		var basePath = "${pageContext.request.contextPath}";
	</script>
</body>
</html>

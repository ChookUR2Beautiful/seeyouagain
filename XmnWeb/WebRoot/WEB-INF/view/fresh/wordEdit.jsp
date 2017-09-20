<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${freshWord!=null}">
			<input type="hidden" value="${freshWord.id}" name="id" />
		</c:if>
		
		<div class="form-group">
			<label class="col-md-4 control-label">关键字：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="word" id="word"
					value="${freshWord.word}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">排序：<span style="color:red;">*</span></label>
		<div class="col-md-7">
				<input type="text" class="form-control" name="sort" id="sort"
					value="${freshWord.sort}">
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
		<input value="2" name="type"  type="hidden"/>
	</form>
	<script src="<%=path%>/js/fresh/wordEdit.js"></script>
	<script type="text/javascript">
		var basePath = "${pageContext.request.contextPath}";
	</script>
</body>
</html>

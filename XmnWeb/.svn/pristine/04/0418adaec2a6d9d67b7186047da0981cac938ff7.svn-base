<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加银行</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<form id="editTagFrom" role="form" class="form-horizontal">
		<c:if test="${!empty tag}">
			<input type="hidden" name="id" value="${tag.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-2 control-label">标签名称：</label>
			<div class="col-md-10">
				<input type="text" class="form-control" name="tagname"
					value="${tag.tagname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">标签类型：</label>
			<div class="col-md-10">
				<select id="tagtype" name="tagtype" initValue="${tag.tagtype}" class="form-control"style="width:100%;">
										<option value= "" }>请选择</option>
										<option value= "0" ${tag.tagtype==0?"selected":""}>岗位标签</option>
										<option value= "1" ${tag.tagtype==1?"selected":""}>自我评价</option>
										<option value= "2" ${tag.tagtype==2?"selected":""}>培训</option>
										<option value= "3" ${tag.tagtype==3?"selected":""}>福利</option>
										<option value= "4" ${tag.tagtype==4?"selected":""}>岗位要求</option>
				</select>
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
	<%-- <jsp:include page="../../common.jsp"></jsp:include> --%>
	<script src="<%=path%>/js/dataDictionary/recruitTagEdit.js"></script>
</body>
</html>

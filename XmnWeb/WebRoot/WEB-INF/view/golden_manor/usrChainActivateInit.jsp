<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<form id="videoFrom" role="form" class="form-horizontal">
	<div class="form-group">
		<label class="col-md-4 control-label">操作用户：<span
			style="color:red;">*</span></label>
		<div class="col-md-7" >
			<select class="form-control" id="childId" name="childId" style="width:50%;" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-4 control-label">庄园状态：<span
			style="color:red;">*</span></label>
		<div class="col-md-7" id="parentName">
			
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-4 control-label">用户能量：<span
			style="color:red;">*</span></label>
		<div class="col-md-7" id="energyName">
			
		</div>
	</div>
	<div class="form-group">
		<div class="text-center" style="padding:10px 0 10px 0;">
			<button type="submit" id="submitProduct" class="btn btn-success">
				<span class="icon-ok"></span> 激活
			</button>
			&nbsp;&nbsp;
			<button type="reset" class="btn btn-default" data-dismiss="modal">
				<span class="icon-remove"></span> 取 消
			</button>
		</div>
	</div>
</form>

<script src="<%=path%>/js/golden_manor/usrChainActivateInit.js"></script>
</body>

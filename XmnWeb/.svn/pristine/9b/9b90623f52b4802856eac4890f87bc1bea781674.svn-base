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

<title>发行优惠券</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div class="container-fluid">
		<form class="form-horizontal" id="addCouponDetailForm">
			<div class="row">
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label">优惠券：</label>
					<div class="col-md-6">
					<select class="form-control"  id="cid" name ="cid" initValue="${param.cid}" style = "width:100%;">
				    </select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group" >
					<label class="col-md-2 col-md-offset-2 control-label">发行量：</label>
					<div class="col-md-6">
						<input class="form-control" type="text" name="totalVolume" style="width:90%;"/>
					</div>
				</div>
				<div class="col-md-12 text-center">
					<button type="submit" class="btn btn-success" id="ensure">
						<span class="icon-ok"></span> 保存
					</button>
					<button type="reset" class="btn btn-default" data-dismiss="modal">
						<span class="icon-remove"></span> 取 消
					</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/coupon/couponissue/addCouponDetail.js"></script>
</body>
</html>

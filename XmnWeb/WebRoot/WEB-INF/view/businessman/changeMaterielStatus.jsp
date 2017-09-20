<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>

<body>
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/businessman/seller/changeMaterielStatus.jhtml?sellerid=${sellerid}&uid=${uid}"
		method="post">
		<div data-placement="center" data-icon="heart" data-content=""
			type="button" class="messager messager-warning"></div>
		<!-- <div data-type="warning" class="messager messager-warning"><div class="messager-content"><i class="icon-warning-sign"></i> xxxxxx</div><div class="messager-actions"><button class="close action" type="button">×</button></div></div> -->
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:80px;"><h5>&nbsp;&nbsp;商家&nbsp;ID:</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						value="${sellerid}" readonly="true"/></th>
				</tr>
				<tr>
					<th style="width:80px;"><h5>&nbsp;寻蜜客ID:</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						value="${uid }" readonly="true"/></th>

				</tr>
				<tr>
					<th style="width:80px;"><h5>寻蜜客邮箱:</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						value="${email }" readonly="true"></th>

				</tr>
				<tr>
					<th colspan="3" style="text-align: center;"><c:choose>
							<c:when test="${status==0}">


								<button type="submit" class="btn btn-success" id="ensure">
									<span class="icon-ok"></span> 发 送
								</button>&nbsp;&nbsp;
								<button type="button" class="btn btn-default"
									data-dismiss="modal" aria-label="Close">
									<span class="icon-remove"></span> 取 消
								</button>

							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-default"
									data-dismiss="modal" aria-label="Close">
									<span class="icon-remove"></span> 确 定
								</button>
							</c:otherwise>
						</c:choose></th>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

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

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="editIsFirstForm">
		<div class="panel">
			<table class="table-hover">
				<thead>
					<tr>
						<th class="col-md-2">商家编号</th>
						<th class="col-md-2">商家名称</th>
						<th class="col-md-2">是否开启首次</th>
						<th class="col-md-2">审核状态</th>
						<th class="col-md-2">上线状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sellers }" var="seller" varStatus="stats">
						<input type="hidden" name="sellerList[${stats.index}].sellerid"
							value="${seller.sellerid}">
						<tr>
							<td>${seller.sellerid}</td>
							<td>${seller.sellername}</td>
							<td><select class="form-control"
								name="sellerList[${stats.index}].isFirst"><option
										<c:if test="${seller.isFirst == 1}" >selected</c:if>
										value="1">是</option>
									<option <c:if test="${seller.isFirst == 0}" >selected</c:if>
										value="0">否</option></select></td>
							<td>${seller.statusText}</td>
							<td>${seller.isonlineText}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panel" style="padding-top: 10px;padding-bottom : 10px;">
			<div class="text-center">
				<button type="submit" class="btn btn-success" id="ensure">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		validate("editIsFirstForm", {}, formAjax);
	});
	function formAjax() {
		$.ajax({
			type : 'post',
			url : "businessman/sellerDetailed/updateIsFirstBatch.jhtml" + "?t="
					+ Math.random(),
			data : jsonFromt($('#editIsFirstForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				pageDiv.reload();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	}
</script>
</html>

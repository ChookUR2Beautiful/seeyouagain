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
	<form class="form-horizontal" role="form" id="editFlatAginoForm">
		<div class="panel">
			<table class="table-hover">
				<thead>
					<tr>
						<th class="col-md-2">商家编号</th>
						<th class="col-md-2">商家名称</th>
						<th class="col-md-2" nowrap="nowrap">用户折扣占比(%)</th>
						<th class="col-md-2" nowrap="nowrap">平台补贴折扣(%)</th>
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
							<td>${seller.yledger}</td>
							<td class="col-md-2"><input type="text"
								name="sellerList[${stats.index}].flatAgio"
								value="${seller.flatAgioIntValue}" class="form-control flatAgio"
								data-yledger="${seller.yledger}" placeholder="请输入0-100之间的整数,不输入默认为0"></td>
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
	/* $.validator.addMethod("checkFlatAgio", function(value, element, params) {
		var yledger = $(element).attr("data-yledger");
		console.log("yledger = "+yledger +"; value = "+value);
		if (value >= 0) {
			value = parseFloat(value);
			if (value < yledger) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}, "平台补贴折扣必须大于用户占比！"); */
	$.validator.messages.required='请填平台补贴折扣';
	$.validator.messages.digits='请输入合理整数';
	$.validator.messages.min='请输入0-99之间的整数';
	$.validator.messages.max='请输入0-99之间的整数';

	$.validator.addClassRules("flatAgio", {
		required : true,
		digits:true, 
		//checkFlatAgio:true,
		min:0,
		max:99
	});

	$(function() {
		validate("editFlatAginoForm", {}, formAjax);
	});
	function formAjax() {
		$.ajax({
			type : 'post',
			url : "businessman/seller/updateFlatAgioBatch.jhtml" + "?t="
					+ Math.random(),
			data : jsonFromt($('#editFlatAginoForm').serializeArray()),
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

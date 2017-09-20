<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'editAdvertising' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>

<body>
	<form class="form-horizontal" role="form" id="responseForm">
		<input type="hidden" name="ids" value="${activityApply.id}"> 
		<table width="90%">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;商家名称：</h5></th>
					<th colspan="2"><h5>${activityApply.sellername}</h5></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;活动想法：</h5></th>
					<th colspan="2" style="font-size:14px;">
						${activityApply.eescription}
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;联系方式：</h5></th>
					<th colspan="2"><h5>${activityApply.phone}</h5></th>
				</tr>
				<tr>
				<th colspan="3"><th>
				</tr>
				<c:if test="${activityApply.status != 0}">
				<tr>
					<th style="width:70px; color:red;"><h5>&nbsp;&nbsp;平台回复：</h5></th>
					<th colspan="2" style="font-size:14px;color:red;">
						${activityApply.reason}
					</th>
				</tr>
				</c:if>
				<c:if test="${activityApply.status == 0 
					&& null!=btnAu['marketingManagement/activityApply/response'] 
					&& btnAu['marketingManagement/activityApply/response']}"> 	<%--有回复权限时才显示且未回复时，才显示 --%>
				<tr>
					<th style="width:70px; color:red;"><h5>&nbsp;&nbsp;平台回复：</h5></th>
					<th colspan="2"><textarea name="reason" cols="20" rows="3"
							class="form-control">${activityApply.reason}</textarea>
						<input type="hidden" name="status" value="1">
					</th>
				</tr>
				<tr>
					<th colspan="3" style="text-align: center;">
						<button type="submit" class="btn btn-success" id="ensure">
							<span class="icon-ok"></span> 保 存
						</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal">
							<span class="icon-remove"></span> 取 消
						</button>
					</th>
				</tr>
				</c:if>
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript">
	$(function() {

		validate("responseForm", {
			rules : {
				reason : {
					required : true,
					maxlength : 100
				}
			},
			reason : {
				remarks : {
					required : "请输入回复内容!",
					maxlength : "备注的最大长度为100字符!"
				}
			}
		}, formAjax);
	});

	function formAjax() {
		var data = jsonFromt($('#responseForm').serializeArray());
		$.ajax({
			type : 'post',
			url :'marketingManagement/activityApply/response.jhtml' + '?t='+ Math.random(),
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					customizeApplyList.reload();
				}
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

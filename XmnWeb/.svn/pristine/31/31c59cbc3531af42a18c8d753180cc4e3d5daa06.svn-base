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
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>
<body>
	<form id="editBankFrom" role="form" class="form-horizontal">
		<c:if test="${!empty bank}">
			<input type="hidden" name="id" value="${bank.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-2 control-label">银行logo：</label>
			<div class="col-md-10" style="width:180px;">
				<input type="hidden" class="form-control" id="logo" name="logo"  value="${bank.logo}">
				<div id="logoUpload"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">银行名称：</label>
			<div class="col-md-10">
				<input type="text" class="form-control" name="bankName"
					value="${bank.bankName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">英文缩写：</label>
			<div class="col-md-10">
				<input type="text" class="form-control" name="abbrev"
					value="${bank.abbrev}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">排序：</label>
			<div class="col-md-10">
				<input type="text" class="form-control" name="sorting"
					value="${bank.sorting}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注：</label>
			<div class="col-md-10">
				<textarea name="explains" class="form-control">${bank.explains}</textarea>
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
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script type="text/javascript">
		var formId = "editBankFrom";
		var jsonTextInit;
		$(function() {
			var dataformInit = $("#" + formId).serializeArray();
			jsonTextInit = JSON.stringify({
				dataform : dataformInit
			});
			validate(formId, {
				rules : {
					bankName : {
						required : true
					},
					abbrev : {
						required : true
					},
					sorting : {
						required : true,
						integer : true,
						min : 0
					}
				}
			}, bankSave);
			
			$("#logoUpload").uploadImg({
				urlId : "logo",
				showImg : $("#logo").val()
			});
		});
		function bankSave() {
			var url;
			var suffix = ".jhtml";
			var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true
					: false;
			if (isAdd) {//添加操作
				url = "dataDictionary/bank/add" + suffix;
			} else {//修改操作
				url = "dataDictionary/bank/update" + suffix;
			}
			var dataform = $("#" + formId).serializeArray();
			var jsonText = JSON.stringify({
				dataform : dataform
			});
			if (isAdd || jsonTextInit != jsonText) {//添加或者修改改了东西才会发送请求
				$.ajax({
					type : 'post',
					url : url,
					data : jsonFromt($('#' + formId).serializeArray()),
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
						if (data.success) {
							if (isAdd) {
								bankList.reset();
							} else {
								bankList.reload();
							}
						}
						showSmReslutWindow(data.success, data.msg);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
					}
				});
			} else {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				showSmReslutWindow(false, "没做任何修改！");
			}
		}
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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

<title>编辑帮助条目</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
	overflow-x: auto;
	overflow-y: auto;
	background: #EEE;
}

th {
	font-size: 12px;
}
</style>
</head>

<body class="doc-views with-navbar">

	<form class="form-horizontal" id="picForm">
		<input type="hidden" name="giftImgId" value="${img['giftImgId']}"/>
		<input type="hidden" name="registerImgId" value="${img['registerImgId']}"/>
		<div class="form-group">
			<label class="col-md-3 control-label">前往注册图:</label>
			<div class="col-md-9">
				<input type="hidden" id="registerImgeditid"
					name="registerImg" value="${img['registerImg']}" />
				<div id=registerImgdivid  ImgValidate="true"></div>	
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">奖励发放图:</label>
			<div class="col-md-9">
				<input type="hidden" id="giftImgeditid"
					name="giftImg" value="${img['giftImg']}" />
				<div id=giftImgdivid  ImgValidate="true"></div>	
			</div>
		</div>
		
		<div class="form-group">
			<div style="padding:10px 0 10px 0;" class="text-center">
				<button id="updateSaveBrandSeller" type="submit"
					class="btn btn-success">
					<i class="icon-save"></i>&nbsp;保存
				</button>
				&nbsp;&nbsp;
				<button data-dismiss="modal" class="btn btn-default" type="reset">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form> 

	<script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>

	<script type="text/javascript">
		$(function() {
			$("#registerImgdivid").uploadImg({
				urlId : "registerImgeditid",
				showImg : $("#registerImgeditid").val()
			});

			$("#giftImgdivid").uploadImg({
				urlId : "giftImgeditid",
				showImg : $("#giftImgeditid").val()
			});
			console.info("shdwoghwohg");
			/* inserTitle(
					'> 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> ><span>修改菜品分类</span>',
					'', true); */
			validate("picForm", {
				rules : {
					item : {
						required : true,
					}
				},
				messages : {
					item : {
						required : "请输入帮助条目分类！",
					}
				}
			}, formAjax);
		});

		function formAjax() {
			var data = jsonFromt($('#picForm').serializeArray());
			if(!valiImgData('#picForm',data)){
				
				var url ="user_terminal/register_gift/img/update.jhtml";

				$.ajax({
					type : 'post',
					url : url + "?t=" + Math.random(),
					data : data,
					dataType : 'json',
					beforeSend : function(XMLHttpRequest) {
						$('#prompt').show();
					},
					success : function(data) {
						$('#prompt').hide();
						$('#triggerModal').modal('hide');
						var url2 = contextPath + '/user_terminal/register_gift/init.jhtml';
		        		location.href =url2;
						showSmReslutWindow(data.success, data.msg);
						
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			}

		}
		function validateImg(){
			
		}
	</script> 

</body>
</html>

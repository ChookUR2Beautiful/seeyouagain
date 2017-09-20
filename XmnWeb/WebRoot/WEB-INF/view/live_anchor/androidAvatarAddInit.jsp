<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>编辑商家信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">

<style type="text/css">
td {
	border-bottom: none !important;
}
</style>

</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main" style="min-height: 703px;">

		<!-- 头像信息 -->
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">头像信息</div>
				<div class="panel-body">
					<form id="androidAvatarForm">
						<table class="table" style="text-align: center;">
							<tr>
								<td>
									<table class="table" style="text-align: center;">
										<tr>
											<td colspan="10" style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:30px;">
												共计【${avatarCount }】个头像
											</td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>机器人头像:</h5> 
											</td>
											<td colspan="9" align="left"><input type="hidden"	id="robotAvatarsNum" ">
												<button class="btn btn-danger" type="button" id="addPic" style="float : left">
													<i class="icon-plus"></i></button> <br />
												<div id="robotAvatarTemp"	style="display: none;float : left;text-align: center;">
													<button class="btn btn-warning removebtn" type="button" style="float: left;"><i class="icon-remove"></i></button>
													<!-- 图片ID -->
													<input name="id" type="hidden" /> 
													<!-- 图片类型,1 主播 2 机器人 -->
													<input name="imageType" type="hidden" value="2"/> 
													<!-- 创建时间 -->
													<input name="createTime" type="hidden" />
													<!-- 图片URL地址 -->
													<input name="anchorImage" type="hidden" /> 
													<div class="pic"></div>
												</div>
												<div id="robotAvatars" style="float : left;">
												</div>
											</td>
										</tr>
									</table>
								</td>
								<td rowspan="2"
									style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br>
									<button class="btn btn-danger" type="submit">
										<i class="icon-save"></i>&nbsp;保存
									</button>
									
									<a class="btn btn-warning" href = "liveAndroid/manage/init.jhtml" type="button"><i class="icon-reply-all"></i>&nbsp;返回</a>	
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/live_anchor/androidAvatarAddInit.js"></script>
</body>
</html>

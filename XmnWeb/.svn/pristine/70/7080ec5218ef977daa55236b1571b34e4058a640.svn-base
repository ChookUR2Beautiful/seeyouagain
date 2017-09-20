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
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">

<link href="<%=path%>/resources/webuploader/webuploader.image.css?v=1.0.1" rel="stylesheet">

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
				<div class="panel-heading">图片信息</div>
				<div class="panel-body">
					<form id="androidAvatarForm">
						<table class="table" style="text-align: center;">
							<tr>
								<td>
									<table class="table" style="text-align: center;">
										<tr>
											<div id="wrapper">
												<div class="page-body">
													<div id="post-container" class="container">
													    <div class="page-container">
															
															<p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮.</p>
															
															<div id="uploader" class="wu-example">
															    <div class="queueList">
															        <div id="dndArea" class="placeholder">
															            <div id="filePicker"></div>
															            <p>或将照片(大小不超过1M)拖到这里，单次最多可选300张</p>
															        </div>
															    </div>
															    <div class="statusBar" style="display:none;">
															        <div class="progress">
															            <span class="text">0%</span>
															            <span class="percentage"></span>
															        </div><div class="info"></div>
															        <div class="btns">
															            <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
															        </div>
															    </div>
															    <input type="hidden" id="relativePath" name="relativePath"/>
															</div>
													    </div>
													</div>
												</div> 
										    </div>
										</tr>
									</table>
								</td>
								<td rowspan="2"
									style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br>
									<button type="button" class="btn btn-danger" id="ensureAdd">
										<span class="icon-ok" ></span> 确 定
									</button>
									
									<button type="reset" class="btn btn-default" data-dismiss="modal">
										<span class="icon-remove"></span> 取 消
									</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>
	
	<script type="text/javascript">
    	/**添加全局站点信息*/
    </script>
	<script src="<%=path%>/js/live_anchor/anchorImageAddBatch.js"></script>
</body>
</html>

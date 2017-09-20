<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>添加邀请分享信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">邀请分享信息</div>
				<div class="panel-body">
					<form  id="editForm" class="form-horizontal">
					<input type="hidden" id="isType" name="isType" value="${isType}">
					<input type="hidden" id="id" name="id" value="${liveShareInfo.id}">
						<table class="table" style="text-align: center; width:80%;" border='0'>
							<tr >
								<td style="width:20%;" >
									<h5>分享标题:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="title" placeholder="分享标题"
									value="${liveShareInfo.title}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>分享内容:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="content" placeholder="分享内容"
									value="${liveShareInfo.content}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>选择使用位置:<span style="color:red;">*</span></h5>
								</td>
								<td >
								<!-- 分享链接位置：001 商家详情，002 直播间，003 首页， 004商家列表页 -->
									<select  class="form-control" name="position" placeholder="选择使用位置" >
										<option value="">--请选择--</option>
										<option value="001" ${liveShareInfo.position=="001"?"selected":"" }>商家详情</option>
										<option value="002" ${liveShareInfo.position=="002"?"selected":"" }>直播间</option>
										<option value="003" ${liveShareInfo.position=="003"?"selected":"" }>首页</option>
										<option value="004" ${liveShareInfo.position=="004"?"selected":"" }>商家列表页</option>
									</select>
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>选择页面类型:<span style="color:red;">*</span></h5>
								</td>
								<td >
								<!-- 分享页面类型：001 下载页， 002 已有页面，3 外部链接 -->
									<select  class="form-control" id="pageType" name="pageType" placeholder="选择页面类型" >
										<option value="">--请选择--</option>
										<option value="001" ${liveShareInfo.pageType=="001"?"selected":"" }>下载页</option>
										<option value="002" ${liveShareInfo.pageType=="002"?"selected":"" }>已有页面</option>
										<option value="003" ${liveShareInfo.pageType=="003"?"selected":"" }>外部链接</option>
									</select>
								</td>
								<td></td>
							</tr>
							
							<tr id="pageContentTr">
								<td style="width:20%;" >
									<h5>选择页面:<span style="color:red;">*</span></h5>
								</td>
								<td >
								<!-- 分享页面：001 商家详情页，002 直播分享页 ，003 回放分享页 ，004 寻蜜客邀请页， 005 积分商品详情页 -->
									<select  class="form-control" id="pageContent" name="pageContent" placeholder="选择页面" >
										<option value="">--请选择--</option>
										<option value="001" ${liveShareInfo.pageContent=="001"?"selected":"" }>商家详情页</option>
										<option value="002" ${liveShareInfo.pageContent=="002"?"selected":"" }>直播分享页</option>
										<option value="003" ${liveShareInfo.pageContent=="003"?"selected":"" }>回放分享页</option>
										<option value="004" ${liveShareInfo.pageContent=="004"?"selected":"" }>寻蜜客邀请页</option>
										<option value="005" ${liveShareInfo.pageContent=="005"?"selected":"" }>积分商品详情页</option>
									</select>
								</td>
								<td></td>
							</tr>
							
							<tr id="shareLinkTr">
								<td style="width:20%;" >
									<h5>填写链接:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" id="shareLink" name="shareLink" placeholder="填写链接"
									value="${liveShareInfo.shareLink}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>选择使用平台:<span style="color:red;">*</span></h5>
								</td>
								<td >
								<!-- 应用类型：001 用户端，002 微信端 -->
									<select  class="form-control" name="appType" placeholder="选择使用平台" >
										<option value="">--请选择--</option>
										<option value="001" ${liveShareInfo.appType=="001"?"selected":"" }>用户端</option>
										<option value="002" ${liveShareInfo.appType=="002"?"selected":"" }>微信端</option>
									</select>
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>默认图片:</h5>
								</td>
								<td >
									<input type="hidden" class="form-control" name="picUrl" id="picUrl"
										value="${liveShareInfo.picUrl}">
										<div id="picUrlImg"></div>
								</td>
								<td></td>
							</tr>
						</table>
						
						<div align="center">
								<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}'; </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script src="<%=path%>/js/live_anchor/liveShareEdit.js"></script> 
</body>
</html>

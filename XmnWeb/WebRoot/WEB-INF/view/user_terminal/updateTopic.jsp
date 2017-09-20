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
<title>添加话题信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	
	<!-- 页面复用  添加属性 isType:  -->
	<!-- isType=add,添加页面;isType=update,修改页面;isType=check,查看页面; -->
	<!-- 修改页面提供页面的数据修改，删除功能，查看回复功能。隐藏域 id = "pageType" 为访问回复列表进行删除回复功能 -->
	<!-- 查看页面提供评论回复的功能，并显示评论和回复数据 -->
	
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">话题基本信息</div>
				<!-- height: 600px; width :900px;  -->
				<form class="form-horizontal" role="form" id="editTopicForm" style="overflow-y:auto; ">
				<input type="hidden"   id="topic_Id" name="topic.id" value="${topicGlobal.topic.id}"> 
				<input type="hidden"   id = "isType" value="${isType}">
				<input type="hidden"   id = "topicText" value="${topicGlobal.topic.content}">
				<%-- 移除评论列表后，删除该代码
				<input type="hidden"   id = "pageType" value="${pageType}">
				 --%>
				<table class="table" style="width:100%">
					<tbody>
						<tr style="height:20px"></tr>
						<tr>
							<td style="width:130px;"><h5>&nbsp;&nbsp;话题内容:</h5></td>
							<td colspan="2"><textarea id="topicContent" name="topic.content"  style="width:1000px;height:200px;resize:none">${topicGlobal.topic.content}</textarea></td>
						</tr>
						<tr>
							<td style="width:130px;"><h5>&nbsp;&nbsp;是否显示:</h5></td>
							<td style="text-align: left;">
								<h5>
								<c:if test="${isType=='add'}">
									<input  name="topic.status" value="1"type="radio" checked="checked" ><span style="font-size: 12px;" >是</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="topic.status" value="0" type="radio"  ><span style="font-size: 12px;">否</span>
								</c:if>
								
								<c:if test="${isType=='update'}">
									<input  name="topic.status" value="1"type="radio" <c:if test="${topicGlobal.topic.status==1 }">checked="checked"</c:if> ><span style="font-size: 12px;" >是</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="topic.status" value="0" type="radio" <c:if test="${topicGlobal.topic.status==0 }">checked="checked"</c:if> ><span style="font-size: 12px;">否</span>
								</c:if>
								</h5>
							</td>	
						</tr>
						<tr>
						<td style="width:130px;" nowrap="nowrap">
							<h5>&nbsp;&nbsp;批量添加图片:<br/><span style="color: red;" >（图片大小不超过150KB）</span></h5>
							
						</td>
							<td colspan="9" align="left">
								<input type="hidden" id="topicPicsNum" value="${fn:length(topicGlobal.topicImgList)}">
								<button class="btn btn-danger" type="button" id="addPic" style="float : left"><i class="icon-plus"></i></button><br/>
								<div id="topicPicTemp" style="display: none;float : left;text-align: center;">
									<button class="btn btn-warning removebtn" type="button" ><i class="icon-remove"></i></button>
									<input name="imgId" type="hidden" />
									<input name="topicId" type="hidden" />
									<input name="topicImg" type="hidden" />
									<div class="pic"></div>
								</div>
								<div id="topicPics" style="float : left;">
								<c:forEach items="${topicGlobal.topicImgList}" varStatus="status" var="pic">
									<div style="float : left;text-align: center;">
										<button class="btn btn-warning removebtn" type="button" ><i class="icon-remove"></i></button>
										<input name="topicImgList[${status.index }].id" type="hidden" value="${pic.id}"/>
										<input name="topicImgList[${status.index }].topicId" type="hidden" value="${pic.topicId}" />
										<input name="topicImgList[${status.index }].img" type="hidden" value="${pic.img}" id="topicImg${status.index}" class="form-control" placeholder="图片描述" style="width:200px"/> 
										<div class="pic"></div>
									</div>
								</c:forEach>
								</div>
							</td>
						</tr>
						<!-- 	
						<tr id = "commentlist" style = "display: none">
							<td colspan="9" align="left">
								<div id="topicCommentList">
								</div>
							</td>
						</tr>
						 -->
						<tr style="height:20px "></tr>
		 				<tr>
		 					<td colspan="2" style="text-align: center;"> 
		 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  发   布    </button>
								<button class="btn btn-warning" type="button" id="backId"><i class="icon-reply"></i>&nbsp;&nbsp;取  消</button>
		 					</td>
		 				</tr>
			 			</tbody>
			 		</table>
	 				</form>
	 			</div>
			</div>
		</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/js/user_terminal/updateTopic.js"></script>

</body>
</html>

<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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
<title>查看话题信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>

</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary" style="border-bottom: 0px;">
			    
				<div class="panel-heading">查看话题信息</div>
				<div class="panel-body">
				<form class="form-horizontal" role="form" id="checkTopicForm" style="overflow-y:auto; ">
					<input type="hidden"   id = "pageType" value="${pageType}">
					<input type="hidden" id ="topicId" name="topic.id" value="${topicGlobal.topic.id}">
					<input type="hidden" id = "commentType" value="0">
					<input type="hidden" id = "topicText" value="${topicGlobal.topic.content}">
					<table width="100%">
						<tbody>
							<tr>
								<td style="width:90px;"><h5>&nbsp;&nbsp;话题内容:</h5></td>
								<td colspan="2" height="150"><textarea id="topicContent" name="topic.content" disabled="disabled"  style="width:800px;height:200px;resize:none">${topicGlobal.topic.content}</textarea></td>
							</tr>
							
							<tr>
							<td style="width:90px;"><h5>&nbsp;&nbsp;话题图片:</h5></td>
							<td>
							<c:if test="${!empty topicGlobal&&!empty topicGlobal.topicImgList}">
								
									<c:forEach items="${topicGlobal.topicImgList}" varStatus="status" var="pic">
										<img style="float:left;margin: 2px" alt="" width="200" height="150" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${pic.img}">
									</c:forEach>
									
							</c:if>
							
							</td>
							</tr>
							
							<!-- 做个文本域，用于回复信息 -->
							<!-- 
							<tr>
								<td style="width:90px;"><h5>&nbsp;&nbsp;回复话题:</h5></td>
								<td>
								<textarea id="topicComment" name="topicComment.content" style="width:800px;height:200px;resize:none "></textarea>
								</td>
							</tr>
							 -->
							<tr style="height:20px;"></tr>
							
							<tr>
								<td colspan="3" style="text-align: center;"> 
			 						<!-- 
			 						<button type="button" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  回   复  </button>
			 						 -->
			 					</td>
							</tr>
							
								
							<tr  >
								<td colspan="9" align="left">
									<div id="topicCommentList">
									</div>
								</td>
							</tr>
							<tr style="height:20px "></tr>
				 			</tbody>
				 		</table>
	 			</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{delComm :'${btnAu['user_terminal/topic/deleteComment'] }',reply:'${ btnAu['user_terminal/topic/reply']}',}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/user_terminal/checkComm.js"></script>
</body>
</html>

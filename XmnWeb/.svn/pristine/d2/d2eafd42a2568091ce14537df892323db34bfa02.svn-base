<%@ page language="java" import="java.util.*,com.xmniao.xmn.core.util.FastfdsConstant" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="editSubjectForm">
		<input type="hidden" name="tid" value="${tposts.tid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden"   id = "type" value="${type}">
		<table>
				<tr>
					<td style="width:50%;">
						<table width="100%" class="table table-hover table-bordered table-striped info">
							<tbody>
								<tr>
									<td style="width:120px;"><b>发表时间：</b><td><b><fmt:formatDate value="${tposts.sdate }" pattern="yyyy-MM-dd HH:mm:ss"/></b></td>
								</tr>
								<tr style="height:200px;vertical-align:top;">
									<td>话题内容：</td><td>${tposts.content }</td>
								</tr>
				 			<%-- 	<tr>
				 					<td colspan="2"><a href="javascript:" onclick="userSubject(${tposts.staffid});")>查看ta所有的帖子</a></td>
				 				</tr> --%>
				 			
				 				<tr style="height:250px;vertical-align:top;">
				 					<td>图片：</td><td>
				 					<%-- <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licensefurl}" title="营业执照副本附件"  class="fancybox"  id="yyzzfm">
							  	       <img alt="营业执照副本附件" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licensefurl}" style="width: 100px;height: 100px;">   
							       </a> --%>
							       
							    <c:forEach items="${piclist}" var="piclists" >
				 					<img alt="" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${piclists.picurl }" style="width: 100px;height: 100px;">
				 				</c:forEach>			 					
				 					</td>
				 				</tr>
				 		
				 				<tr>
				 					<td>用户名：</td><td>${tposts.nname }</td>
				 				</tr>	
				 				<tr>
				 					<td>点赞数：</td><td>${tposts.number }</td>
				 				</tr>			 				
				 				<tr>
				 					<td>评论数：</td><td>${tposts.count }</td>
				 				</tr>
				 			</tbody>
				 		</table>
					</td>
					<td style="width:50%;vertical-align:top;border-left: 2px solid #e5e5e5;">
						<div id="postCommentList"></div>
					</td>
				</tr>
			</table>
			
	 </form>
	 <script src="<%=path%>/js/user_terminal/postComment.js"></script>
</body>
</html>

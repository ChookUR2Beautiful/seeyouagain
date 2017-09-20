<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#ensure').click(function() {
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'business_cooperation/subject/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'business_cooperation/subject/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editSubjectForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							subjectList.reset();
						}else{
							subjectList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		});
		
		
	});
</script>
</head>

<body>
	<form class="form-horizontal" role="form" id="editSubjectForm">
		<input type="hidden" name="subjectid" value="${subject.subjectid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table>
				<tr>
					<td style="width:50%;">
						<table width="100%" class="table table-hover table-bordered table-striped info">
							<tbody>
								<tr>
									<td style="width:120px;"><b>发表时间：</b><td><b><fmt:formatDate value="${subject.sdate }" pattern="yyyy-MM-dd HH:mm:ss"/></b></td>
								</tr>
								<tr style="height:450px;vertical-align:top;">
									<td>话题内容：</td><td>${subject.content }</td>
								</tr>
				 				<tr>
				 					<td colspan="2"><a href="javascript:" onclick="userSubject(${subject.staffid});")>查看ta所有的帖子</a></td>
				 				</tr>
				 				<tr>
				 					<td>用户名：</td><td>${subject.fullname }</td>
				 				</tr>
				 				<tr>
				 					<td>区域：</td><td>${subject.area }</td>
				 				</tr>
				 				<tr>
				 					<td>评论数：</td><td>${subject.number }</td>
				 				</tr>
				 			</tbody>
				 		</table>
					</td>
					<td style="width:50%;vertical-align:top;border-left: 2px solid #e5e5e5;">
						<div id="subjectReplyList"></div>
					</td>
				</tr>
			</table>
			
	 </form>
	 <script src="<%=path%>/js/business_cooperation/subjectReply.js"></script>
</body>
</html>

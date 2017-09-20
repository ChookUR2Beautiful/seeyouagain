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
				url = 'businessman/commentReply/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'businessman/commentReply/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editCommentReplyForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							commentReplyList.reset();
						}else{
							commentReplyList.reload();
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
		
		$('.form_datetime').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
		});
	});
</script>
</head>

<body>
	<form class="form-horizontal" role="form" id="editCommentReplyForm">
		<input type="hidden"  name="userId" value="${commentReply.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<input type="hidden" class="form-control"  name="id"  value="${commentReply.id}">
				<input type="hidden" class="form-control"  name="cid"  value="${param.cid!=null?param.cid:commentReply.cid}"></th>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;用户ID：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="uid"  value="${commentReply.uid}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;用户昵称：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="nname"  value="${commentReply.nname}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;父回复ID：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="fid"  value="${commentReply.fid}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;回复时间：</h5></th>	
					<th colspan="2"><input type="text" class="form-control form_datetime"  name="sdate"  value="<fmt:formatDate value="${commentReply.sdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;回复内容：</h5></th>	
					<th colspan="2">
						<textarea class="form-control"  name="content">${commentReply.content}</textarea>
					</th>
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="button" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>
 						&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
</body>
</html>

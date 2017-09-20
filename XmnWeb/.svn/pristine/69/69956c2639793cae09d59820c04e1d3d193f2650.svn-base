<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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
<body >
	<form class="form-horizontal" role="form" id="checkTopicForm" style="width :900px;overflow-y:auto; ">
		<input type="hidden" id="pid" name="pid" value="${fatherComment.id}"> 
		<input type="hidden" id="replys" value="${commentList}">
		<input type="hidden" id="topicId" value="${fatherComment.topicId}">
		<input type="hidden" id="replyType" value="1">
		<input type="hidden" id="replyDetail" value="${param.replyDetail}">
		
		
		<table width="100%">
			<tbody>
				<%-- 
				<tr>
					<td style="width:10%;"><h5>&nbsp;&nbsp;评论内容:</h5></td>
					<td colspan="2" height="150"><textarea id="PContent" name="PContent" disabled="disabled" style="width:800px;height:100px;resize:none">${fatherComment.content}</textarea></td>
				</tr>
				 --%>
				<tr  >
					<td colspan="9" align="left">
						<div id="replyList">
						
						</div>
					</td>
				</tr>
				<tr style="height:20px"></tr>
				<!-- 回复评论 -->
				<tr id="replyContentTr">
					<td style="width:10%;"><h5>&nbsp;&nbsp;回复评论:</h5></td>
					<td>
					<textarea id="replycontent" name="reply.content" style="width:800px;height:100px;resize:none "></textarea>
					</td>
				</tr>
				<tr style="height:20px "></tr>
				<tr id = "replyButton">
					<td colspan="3" style="text-align: center;"> 
 						<button type="button" class="btn btn-success" id="ensure111"><span class="icon-ok"></span>  回   复  </button>
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  返  回  </button>
 					</td>
				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 
	<script type="text/javascript">
	var replyList;
	  $(document).ready(function() {
		  
		 if($("#replyDetail").val() != "0" ){
			 
			 replyList = $('#replyList').page({
					url : 'user_terminal/topic/init/replyList.jhtml',
					success : success,
					pageBtnNum : 10,
					param : {pid : $("#pid").val(),type:1}
					
				});
			 $("#replyContentTr").hide();
			 $("#replyButton").hide();
		 }else{
			 
		 }
		 
		 $('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd hh:ii'	
		 	});
		/* var tpl = "<tr><td style='width:70px;'>{nname}</td><td style='width:300px;'>{content}</td><td style='width:180px;'>{time}</td></tr>" */
			  $('#ensure111').click(function(){
				
				 	var replycontent = $('#replycontent').val();
				 	var comment = $.trim(replycontent);
					if($('#replyType').val() ==1)
					if(comment!=null && comment != ""){
				 		$.ajax({
							type : 'post',
							url : 'user_terminal/topic/reply.jhtml' + '?t=' + Math.random(),
							data : {content:comment,pid:$('#pid').val(),type:1,topicId:${fatherComment.topicId}},
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									/* var tpltemp =tpl;
									var param = data.data;
									for(var key in param){
										var replaceEment ="{"+key+"}";
										tpltemp = tpltemp.replace(replaceEment,param[key]);
										
									} */
									/* $("#commentInfo").append(tpltemp);
									$("#topicComment").val(""); */
									$("#replycontent").val(""); 
									showSmReslutWindow(data.success, "回复成功！点击查看可浏览所有回复！");
									$('#prompt').hide();
									$('#triggerModal').modal('hide');
								}
							}
				 		});
					 }else{
						 alert("回复内容不能为空！");
					 }
						
				 });  
	 });
	 /*
	  * 删除回复
	 */
	 
	 function removeReply(id) {
			showSmConfirmWindow(function() {
				$.ajax({
					type : 'post',
					url : 'user_terminal/topic/deleteComment.jhtml' + '?t='
							+ Math.random(),
					data : 'commentId=' + id,
					dataType : 'json',
					success : function(data) {
						 replyList.reload();
						
					},
				});

			});
		}
	
	 /* 分页显示数据*/
	  
	 function success(data, obj) {
			var html = [];
			html.push('<table class="table table-hover table-bordered table-striped info" id="replyInfo">');
			html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">回复列表</caption>');
			html.push('<thead>');
			html.push('<tr>');
			html.push('<th style="width:15%;">回复人昵称</th>');
			html.push('<th style="width:45%;">回复内容</th>');
			html.push('<th style="width:25%;">回复时间</th>');
			
			if(permissions.delComm=='true'){
/* 				html.push('<th style="width:10%;">操作</th>');
 */			}
			
			
			html.push('</tr>');
			html.push('</thead>');
			html.push('<tbody');
			if(null != data && data.content.length > 0){
				for (var i = 0; i < data.content.length; i++) {
					html.push('<tr>');
					html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname)+ '</td>')
					html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) +'</td>');
					html.push('<td>' + (undefined == data.content[i].time ? "-" : data.content[i].time) +'</td>');
					
					
					if(permissions.delComm=='true'){
						
					/* 	html.push('<td>');
 						html.push('<input type="hidden" id = "commentId" val=' + data.content[i].id + '/><a href="javascript:removeReply('+data.content[i].id+')">删除</a>&nbsp;&nbsp;');
 						html.push('</td>'); */
						
					}
					
					/* 
					html.push('<a  data-type="ajax" data-width="950px"  data-url="user_terminal/topic/reply/init.jhtml?pid='+data.content[i].id +'"  data-toggle="modal" >查看回复</a>&nbsp;&nbsp;');
					 */
					
					
					html.push('</tr>');
				}
			}
			else
			{
				html.push('<tr>');
				html.push('<td colspan="20" align="center"">暂无数据</td>');
				html.push('</tr>');
			}
			html.push('</tbody>');
			html.push('</table>');
			obj.find('div').eq(0).html(html.join(''));
		} 
	 
		

		</script>
	
</body>
</html>
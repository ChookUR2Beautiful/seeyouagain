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
	<form class="form-horizontal" role="form" id="editCommentForm">
		<input type="hidden"  name="userId" value="${comment.cid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<input type="hidden" class="form-control"  name="cid"  value="${comment.cid}"></th>
				<input type="hidden" class="form-control"  name="sellerid"  value="${param.sellerid!=null?param.sellerid:comment.sellerid}"></th>
				<%-- <tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;用户ID：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="uid"  value="${comment.uid}"></th>
				</tr> --%>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;用户昵称：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="nname"  value="${comment.nname}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;点评时间：</h5></th>	
					<th colspan="2"><input type="text" class="form-control form_datetime"  name="sdate"  value="<fmt:formatDate value="${comment.sdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;点评总分：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="total"  value="${comment.total}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;环境分：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="hbranch"  value="${comment.hbranch}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;服务分：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="fbranch"  value="${comment.fbranch}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;口味分：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="kbranch"  value="${comment.kbranch}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;房间分：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="jbranch"  value="${comment.jbranch}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;人均消费：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="percapita"  value="${comment.percapita}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;状态：</h5></th>	
					<th colspan="2">
						<select class="form-control"  name="status">
							<option value="">请选择</option>
							<option value="0" ${comment.status==0?"selected":""}>未审核</option>
							<option value="1" ${comment.status==1?"selected":""}>已审核</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;评论内容：</h5></th>	
					<th colspan="2"><textarea class="form-control"  name="content">${comment.content}</textarea></th>
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>
 						&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">
	 $(document).ready(function() {
			validate("editCommentForm",{
				rules:{
					 uid:{
						required:true,
						rangelength:[0,11],
						digits:true
					}, 
					nname:{
						required:true,
						rangelength:[2,20]
					},
					sdate:{
						required:true
					},
					total:{
						required:true,
						number:true,
						maxlength:2,
						digits:true
					},
					hbranch:{
						required:true,
						number:true,
						maxlength:2
					},
					fbranch:{
						required:true,
						number:true,
						maxlength:2
					},
					kbranch:{
						required:true,
						number:true,
						maxlength:2
					},
					jbranch:{
						required:true,
						number:true,
						maxlength:2
					},
					percapita:{
						required:true,
						number:true,
						digits:true
					},
					status:{
						required:true
					},
					content:{
						required:true
					}
				},
				messages:{
					uid:{
						required:"用户名未填写!",
						rangelength:"用id长度为0-11个字符之间",
						digits:"用户id必须是整数!"
					},
					nname:{
						required:"昵称未填写!",
						rangelength:"昵称长度为2-8个字符!"
					},
					sdate:{
						required:"点评时间不能为空,请选择一个时间!",
					},
					total:{
						required:"点评总分不能为空!",
						number:"点评总分为数字!",
						maxlength:"最大长度是2位",
						digits:"点评总分必须是整数"
					},
					hbranch:{
						required:"环境评分不能为空!",
						number:"环境评分为数字!",
						maxlength:"最大长度是2位",
						digits:"环境评分必须是整数"
					},
					fbranch:{
						required:"服务评分不能为空!",
						number:"服务评分为数字!",
						maxlength:"最大长度是2位",
						digits:"服务评分必须是整数"
					},
					kbranch:{
						required:"口味评分不能为空!",
						number:"口味评分为数字!",
						maxlength:"最大长度是2位",
						digits:"口味评分必须是整数"
					},
					jbranch:{
						required:"房间评分不能为空!",
						number:"房间评分为数字!",
						maxlength:"最大长度是2位",
						digits:"房间评分必须是整数"
					},
					percapita:{
						required:"人均消费不能为空",
						number:"人均消费应为数字",
						digits:"人均消费应为整数"
					},
					status:{
						required:"请选择一个状态"
					},
					content:{
						required:"评价内容不能为空",
					}
				}},formAjax);
		}); 
		
		function formAjax(){
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'businessman/comment/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'businessman/comment/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editCommentForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							commentList.reset();
						}else{
							commentList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		}
	 </script>
</body>
</html>

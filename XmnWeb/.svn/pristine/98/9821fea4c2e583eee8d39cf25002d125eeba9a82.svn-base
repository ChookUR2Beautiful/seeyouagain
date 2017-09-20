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
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
<script src="<%=path%>/resources/upload/upload.js"></script>
</head>

<body>
	<form class="form-horizontal" role="form" id="editCommentPicForm">
		<input type="hidden"  name="userId" value="${commentPic.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<input type="hidden" class="form-control"  name="id"  value="${commentPic.id}">
				<input type="hidden" class="form-control"  name="cid"  value="${param.cid!=null?param.cid:commentPic.cid}"></th>
				<tr>
					<th style="width:20%;"><h5>&nbsp;&nbsp;图片名称:</h5></th>
					<th colspan="2"><input type="text" class="form-control"  name="picname"  value="${commentPic.picname}"></th>
				</tr>
				<tr>
					<th ><h5>&nbsp;&nbsp;图片类型:</h5></th>
					<th colspan="2"><input type="text" class="form-control"  name="pid"  value="${commentPic.pid}"></th>
				</tr>
				<tr>
					<th ><h5>&nbsp;&nbsp;上传时间:</h5></th>
					<th colspan="2"><input type="text" class="form-control form_datetime"  name="sdate"  value="<fmt:formatDate value="${commentPic.sdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></th>
				</tr>
				<tr>
					<th ><h5>&nbsp;&nbsp;图片:</h5></th>
					<td style="width:70px;">
						<div id="pic"></div>
						<input type="hidden" class="form-control" id="picurl" name="picurl"  value="${commentPic.picurl}"/>
					</td>
					
				</tr>
				<tr>
					<th ><h5>&nbsp;&nbsp;图片缩略图:</h5></th>
					<td >
						<div id="pics"></div>
						<input type="hidden" class="form-control" id="picsurl" name="picsurl"  value="${commentPic.picsurl}"/>
					</td>
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
			validate("editCommentPicForm",{rules:{
				picname:{
						required:true,
						rangelength:[2,20]
					},
					pid:{
						required:true,
					 	digits:true
					},
					sdate:{
						required:true,
					},
					picurl:{
						required:true,
						maxlength:100
					},
					picsurl:{
						required:true,
						maxlength:100
					}
				},
				messages:{
					picname:{
						required:"图片名称不能缺省!",
						rangelength:"图片名称长度为2-20个字符"
					},
					pid:{
						required:"图片类型不能缺省!",
					 	digits:"图片类型应为整数!"
					},
					sdate:{
						required:"上传时间不能为空!",
					},
					picurl:{
						required:"图片不能为空!",
						maxlength:"图片路径最多100个字符！"
					},
					picsurl:{
						required:"图片不能为空!",
						maxlength:"图片路径最多100个字符！"
					}
				}},formAjax);
			
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
			
			
			$("#pic").uploadImg({
				urlId : "picurl",
				showImg : $("#picurl").val()
			});
			
			$("#pics").uploadImg({
				urlId : "picsurl",
				showImg : $("#picsurl").val()
			});
			
		}); 
		
		function formAjax(){
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'businessman/commentPic/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'businessman/commentPic/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editCommentPicForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							commentPicList.reset();
						}else{
							commentPicList.reload();
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

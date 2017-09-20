<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
</head>

<body>
	<form class="form-horizontal" role="form" id="editVideoForm">
		<input type="hidden"  name="vid" value="${video.vid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th colspan="3" ><div id="uploadVideId"></div></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;视频名称:</h5></th>
					<th colspan="2"><input type="text" class="form-control" id="videoname"
						name="videoname" value="${video.videoname}"readonly="readonly"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;URL:</h5></th>
					<th colspan="2"><input type="text" class="form-control" id="vurl"
						name="vurl" value="${video.vurl}" readonly="readonly"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;视频大小:</h5></th>
					<th colspan="2"><input type="text" class="form-control" id="size"
						name="size" value="${video.size}" readonly="readonly"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;状态:</h5></th>
					<th colspan="2">
					    <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <input  type="radio" name="status" value="1" ${video.status==1?"checked":""}>启用&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="status" value="0" ${video.status==0?"checked":""}>停用</h5></th>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;简介:</h5></th>
					<th colspan="2"><textarea name="introduce" rows="10"
							class="form-control" placeholder="简介" >${video.introduce}</textarea>
					</th>
				</tr>
				<tr>
					<th colspan="3" style="text-align: center;">
						<button type="submit" class="btn btn-success" id="ensure">
							<span class="icon-ok"></span> 保  存

						</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal">
							<span class="icon-remove"></span> 取 消
						</button>
					</th>
				</tr>
			</tbody>
		</table>
	</form>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 

<script type="text/javascript">
function formAjax(){
	var url ;
	if($('#isType').val() ==  'add'){
		url = 'business_cooperation/video/add.jhtml' + '?t=' + Math.random();
	}else{
		url = 'business_cooperation/video/update.jhtml' + '?t=' + Math.random();
	}
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#editVideoForm').serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				if($('#isType').val() ==  'add'){
					videoList.reset();
				}else{
					videoList.reload();
				}
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
	return false;
}

$(function() {
	//初始化上传图片
	$("#uploadVideId").uploadFile({
			urlId : "vurl",
			nameId : "videoname",
			sizeId:	"size"
		}); 
	
	 validate("editVideoForm",{rules:{
			videoname:{
				required:true,
				maxlength:100
			},
			vurl:{
				required:true,
				maxlength:100
			},
			size:{
				required:true,
			 	digits:true,
			 	maxlength:16
			},
			status:{
				required:true
			},
			introduce:{
				maxlength:300
			}
		},
		messages:{
			videoname:{
				required:"视频名称不能为空!",
				maxlength:"视频名称最大长度100字符"
			},
			vurl:{
				required:"视频地址不能缺省",
				maxlength:"地址长度最大100字符"
			},
			size:{
				required:"视频大小不能为空",
			 	digits:"大小必须为整数",
			 	maxlength:"视频大小最大16位数字"
			},
			status:{
				required:"请选择一个状态"
			},
			introduce:{
				maxlength:"简介最多支持300字符!"
			}
		}},formAjax);
});
</script>
</body>
</html>

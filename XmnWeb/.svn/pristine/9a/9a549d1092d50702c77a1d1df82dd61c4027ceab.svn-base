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

</head>

<body>
	<form class="form-horizontal" role="form" id="editSellerAskForm">
		<input type="hidden"  name="aid" value="${sellerAsk.aid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;问题名称</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						name="askname" value="${sellerAsk.askname}"></th>
				</tr>
				<tr>
					<th style="width:90px;"><h5>&nbsp;&nbsp;问题答案:</h5></th>
					<th colspan="2"><textarea name="content" rows="10"
							class="form-control" placeholder="问题答案" >${sellerAsk.content}</textarea>
					</th>
				</tr>
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
<script type="text/javascript">
$(document).ready(function() {

	validate("editSellerAskForm",{
		rules:{
			askname:{
				required:true,
				maxlength:200
			},
			content:{
				required:true
			}
		},
		messages:{
			askname:{
				required:"问题名称不能缺省",
				maxlength:"问题名称最多200字符!"
			},
			content:{
				required:"问题内容不能缺省!"
			}
		}},SellerAskFormAjax);
}); 

function SellerAskFormAjax(){
	

	var url ;
	if($('#isType').val() ==  'add'){
		url = 'business_cooperation/sellerAsk/add.jhtml' + '?t=' + Math.random();
	}else{
		url = 'business_cooperation/sellerAsk/update.jhtml' + '?t=' + Math.random();
	}
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#editSellerAskForm').serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				if($('#isType').val() ==  'add'){
					sellerAskList.reset();
				}else{
					sellerAskList.reload();
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

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
	<form class="form-horizontal" role="form" method="post" id="editSellerDetailedForm">
		<input type="hidden"  name="sellerid" value="${sellerDetailed.sellerid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr> 
					<th style="width:70px;"><h5>&nbsp;&nbsp;点赞数:</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="number"  value="${sellerDetailed.number}"></th>
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
 						&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
</body>
<script type="text/javascript">
var  valiinfo={
	"editSellerDetailedForm":{
		rules:{
			number:{
				required:true,
				number:true,
				range:[0,2147483647]
			},
		},
		messages:{
			number:{
				required:"点赞数不能为空!",
				number:"点赞数只能是数字!",
				range:"数值大小为0-2147483647之间的数字"
			},
		}
	}
}

function validate(form,data){
	return $(form).validate({
		rules:data.rules,
		messages:data.messages,
			onkeyup:false,
			onclick:false,
			errorPlacement: function(error, element) {
				  $(element).popover("destroy");
				  $(element).popover({
					    	content:["<h5><i class='icon-warning-sign'>&nbsp;&nbsp;",$(error).text(),"&nbsp;&nbsp;</i></h5>"].join(""),
					    	placement:"top",
					    	container:element.parent(),
					    	trigger:"focus",
					    	html:true
					    }).show();
				  $(element).css("border","1px solid #FF3030");
			},
			success: function(label) {
				var name = $(label).attr("for");
				var element = $("input[name="+name+"]");
				$(element).css("border","1px solid #ccc");
				$(element).popover("destroy");
			},
			submitHandler : function(form) { 
				formSubmit($(form).attr("id"));
		 		return false;
		 	}
	});
}

var formId=["editSellerDetailedForm"];
$(function(){
	for(var i=0;i<formId.length;i++){
		var form = ["#",formId[i]].join("");
		changes(form,validate(form,valiinfo[formId[i]]));
	}
});	

function changes(form,vali){
	$(":input",form).not(':button, :submit, :reset').on("blur",function(){
		var val =$(this).val();
		if(vali&&val.length<=0){
			vali.element(this);
		}
	});	
}

function formSubmit(form){
	switch (form) {
	case "editSellerDetailedForm":
		editSellerDetailed();
		break;
	}
}

function editSellerDetailed(){
	var url ;
	if($('#isType').val() ==  'add'){
		url = 'businessman/sellerDetailed/add.jhtml' + '?t=' + Math.random();
	}else{
		url = 'businessman/sellerDetailed/update.jhtml' + '?t=' + Math.random();
	}
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#editSellerDetailedForm').serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				if($('#isType').val() ==  'add'){
					sellerDetailedList.reset();
				}else{
					sellerDetailedList.reload();
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
</html>

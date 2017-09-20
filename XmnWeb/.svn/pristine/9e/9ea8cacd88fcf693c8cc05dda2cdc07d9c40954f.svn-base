<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	
</head>

<body>
	<form id="editFlatAgioFrom" style=" width :580px;overflow-y:auto; ">
		<input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
		<!-- 1 表示商家页面，2 表示商家待审核页面 -->
		<input type="hidden"   id = "type" value="${param.type}">
		<table width="100%">
			<tbody>
				<tr>		
					<th style="width:120px;"><h5>&nbsp;&nbsp;用户占比（%）:</h5></th>
					<th colspan="2">
						<input type="text" class="form-control" readonly="readonly" name="yledger" value="${sellerAgio.yledger}">
					</th>
				</tr>
				
				<tr>		
					<th style="width:120px;"><h5>&nbsp;&nbsp;平台补贴（%）:</h5></th>
					<th colspan="2">
						<input type="text" class="form-control"  name="flatAgio"  value="${selleridList.flatAgio}">
					</th>	
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保存 </button>
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">
	 function formAjax(form){
		 var type = $("#type").val();
		 if(type == 1){
			 var url = 'businessman/seller/update/flatAgio.jhtml' + '?t=' + Math.random(); 
		 }
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editFlatAgioFrom').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					 showSmReslutWindow(data.success, data.msg);
					 
					 $('#prompt').hide();
					 $('#triggerModal').modal('hide');
					 pageDiv.reload();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		}
		 $(document).ready(function() {
			   //平台补贴校验
				$.validator.addMethod("checkFlatAgio",function(value,element,params){
					if(value > 0){
						value = parseFloat(value);
						if(value < $("input[name=yledger]").val()){
				 			return  false;
				 		}else{
				 			return true;
				 		}
					}else{
						return true;
					}
			   },"平台补贴必须大于用户占比！");
				
				$.validator.addMethod("accountBy",function(value,element,params){
			 		if(value.length > 5){
			 			return  false;
			 		}
			 		if(value >= params[0] && value <= params[1]){
			 			return true;
			 		}else{
			 			return false;
			 		}
			   },"请输入0-90之间的数");
			 
			 
			 	validate("editFlatAgioFrom",{
			 		rules:{
			 			flatAgio:{
			 				number:true,
			 				checkFlatAgio:true,
			 				accountBy:[0,90]
			 			}
					},
					messages:{
						flatAgio:{
							number:"请输入合理数字",
							checkFlatAgio:"平台补贴必须大于用户占比！",
							accountBy:"请输入0-90之间的数"
						}
					}},formAjax);
			}); 
	 </script>
</body>
</html>

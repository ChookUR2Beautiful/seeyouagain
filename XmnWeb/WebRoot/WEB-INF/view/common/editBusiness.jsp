<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<form class="form-horizontal" role="form" id="editBusinessForm">
		<input type="hidden"  name="bid" value="${business.bid}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<c:if test="${isType=='add'}">
					<tr>
						<th style="width:70px;"><h5>城市</h5></th>	
						<th colspan="2">
							<div class="input-group" id="addBusiness" style="width: 100%" initValue = "${business.taareaid}"></div>
						</th>
					</tr>
				</c:if>
				<tr>
					<th style="width:70px;"><h5>商圈</h5></th>	
					<th colspan="2">
						<input type="text" class="form-control"  placeholder="商圈" name="title" value="${business.title}">
					</th>
				</tr>
					<tr>
							<td class = "sellerTitleCss">
									<h5>经度:</h5> 
							</td>
							<td class="sellerContentCss">
								 <input type="text" name = "longitude" class="form-control"  placeholder="经度" value = "${business.longitude}"> 
							</td>
										
						</tr>
						<tr>
							<td></td>
							<td class="sellerContentCss">
								  <span><h5 style="color:red;"><font>请使用高德地图经度（范围 : 73.240&lt;经度&lt;135.150）</font></h5></span> 
							</td>
						</tr>
						<tr>
							<td class = "sellerTitleCss">
									<h5>纬度:</h5> 
							</td>
							<td class="sellerContentCss">
								  <input type="text" name = "latitude" class="form-control"  placeholder="纬度" value = "${business.latitude}">
							</td>
							
						</tr>
						<tr>
							<td></td>
							<td class="sellerContentCss">
								  <span><h5 style="color: red;"><font>请使用高德地图纬度（范围 : 3.3120&lt;纬度&lt;53.1980）</font></h5></span> 
							</td>
						</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span> 保  存 </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span> 取  消</button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 
	 <script type="text/javascript">
	 
	 var valiinfo={
				rules:{
					title:{
						required:true
					},
					longitude :{
						required:true,
						number:true,
						landmarkCheck:[73.240,135.150]
					},
					latitude :{
						required:true,
						number:true,
						landmarkCheck:[3.3120,53.1980]
					}	
				},
				messages:{
					title:{
						 required:"商圈未填写!"
					},
					longitude:{
						required:"经度未填写!",
						number:"地标请输入数字类型"
					},
					latitude:{
						required:"纬度未填写!",
						number:"地标请输入数字类型"
					}
				}
			}
	 
	
	 
	 $.validator.addMethod("landmarkCheck", function(value, element,params) {
		 var len = value.length;
		 if(len > 16){
			 return false;
		 }else if((value <= params[0]) || (value >= params[1]) ){
			 return false;
		 }else {
			 var indexOf = value.indexOf(".");
			 if(indexOf>0){
				 var numStr = value.substr(indexOf+1);
				 return !(numStr.length > 12);
			 }
			 return true;
		 }  
		}, "请输入有效的经纬度!");
	 
		$(document).ready(function() {
				validate("editBusinessForm",valiinfo,busave); 
				var addBusiness = $("#addBusiness");
				if($(addBusiness).length>0){
					var addBusiness = $(addBusiness).areaLd({
						//isChosen : true,
						commonChange : function($dom, level){
							
						},
						showConfig : [{name:"tpareaid",tipTitle:"--省--"},{name:"tcareaid",tipTitle:"--市--"},{name:"taareaid",tipTitle:"--区--"}]
					});	
				}
		});
	 
		 function busave(){
			var success=false;
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'common/business/add.jhtml' + '?t=' + Math.random();
				var selectAray = ["tpareaid","tcareaid","taareaid"];
				success = checkSelect("#editBusinessForm","#addBusiness",selectAray,false);
			}else{
				url = 'common/business/update.jhtml' + '?t=' + Math.random();
				success=true;
			}
			if(success){
				showSmConfirmWindow(function(){
					$.ajax({
						type : 'post',
						url : url,
						data : jsonFromt($('#editBusinessForm').serializeArray()),
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							$('#prompt').hide();
							 $('#triggerModal').modal('hide');
							if (data.success) {
								if($('#isType').val() ==  'add'){
									businessList.reset();
								}else{
									businessList.reload();
								}
							}
							showSmReslutWindow(data.success, data.msg);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$('#prompt').hide();
							$('#triggerModal').modal('hide');
						}
					});
				},"确认已使用的是高德地图经纬度？");
			}
		 }
	 </script>
</body>
</html>

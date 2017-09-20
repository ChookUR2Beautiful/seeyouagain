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
	<form class="form-horizontal" role="form" id="editAreaForm">
		<input type="hidden"  name="areaId" value="${area.areaId}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
			<c:if test="${empty area}">
				<tr>
					<th style="width:70px;"><h5>城市</h5></th>	
					<th colspan="2">
						<div class="input-group" id="addArea" style="width: 100%" initValue = "${area.pid}"></div>
					</th>
				</tr>
			</c:if>
			<c:if test="${!empty area}">
					<tr>
						<th style="width:70px;"><h5>城市:</h5></th>	
						<c:if test="${!empty area.ptitle}">
							<th >
								<select class="form-control" disabled="disabled">
									<option>${area.ptitle}</option>
								</select>
							</th>
						</c:if>
						<c:if test="${!empty area.ctitle}">
							<th >
								<select class="form-control" disabled="disabled">
									<option>${area.ctitle}</option>
								</select>
							</th>
						</c:if>
					</tr>
			</c:if>
				<tr>
			
					<th style="width:70px;"><h5>区域:</h5></th>	
					<th colspan="2">
						<input type="text" class="form-control"  placeholder="区域" name="title" value="${area.title}">
					</th>
				</tr>
				
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span> 保  存 </button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span> 取  消 </button>
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
			}
			
		},
		messages:{
			title:{
				 required:"区域未填写"
			}
		}
	}
	$(document).ready(function() {
		var area = $("#addArea");
		if($(area).length>0){
			var addArea = $(area).areaLd({
				//isChosen : true,
				commonChange : function($dom, level){
					
				},
				showConfig : [{name:"pareaId",tipTitle:"--省--"},{name:"pid",tipTitle:"--市--"}]
			});
		}
		validate("editAreaForm",valiinfo,areaSave); 
	});
	
	function areaSave(){
		var success=false;
		 var url ;
		if($('#isType').val() ==  'add'){
			url = 'common/area/add.jhtml' + '?t=' + Math.random();
			var selectAray = ["pareaId","pid"];
			success = checkSelect("#editAreaForm","#addArea",selectAray,false);
		}else{
			url = 'common/area/update.jhtml' + '?t=' + Math.random();
			success=true;
		}
		if(success){
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editAreaForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							areaList.reset();
						}else{
							areaList.reload();
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
	}
</script>
</body>
</html>

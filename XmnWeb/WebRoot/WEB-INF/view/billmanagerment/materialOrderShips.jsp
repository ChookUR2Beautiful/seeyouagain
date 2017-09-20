<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>
<script type="text/javascript">

$(function() {
	advertisingValidate();
});

function getWidths() {
	return [ "20%", "23%", "48%" ];
}

/**
 * 表单验证
 */
function advertisingValidate() {
	validate("editMaterialForm", {
		rules : {
			courier_type : {
				required : true
			},
			courier_number : {
				required : true,
				digits : true
			}
		},
		messages : {
			courier_type : {
				required : "请选择物流商!"
			},
			courier_number : {
				required : "物流单号不能为空！",
				digits : "物流单号只能为整数!"
			}
		}
	}, formAjax);
}


//带2位小数字点
$.validator.addMethod("doublearea",function(value,element,params){
		var len = value.length;
		 if(len>12){return false;
		 }else if( value >= 1000000000 || value < 0){
			 return false;
		 }else {
			 var indexOf = value.indexOf(".");
			 if(indexOf>0){
				 var numStr = value.substr(indexOf+1);
				 return !(numStr.length > 2);
			 }
			 return true;
		 }
	 },"请填写数值,最大值为999999999.99");


validate("editMaterialForm",validate,formAjax); 


/**
 * Ajax 请求
 */
function formAjax() {
	var data = jsonFromt($("#editMaterialForm").serializeArray());
	var url ="billmanagerment/material/order/shipment/save.jhtml";
	$.ajax({
		type : "post",
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				$("#prompt").hide();
				$("#triggerModal").modal("hide")
				
				materialOrderPayList.reset();
				materialOrderPayList.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		},error : function(data){
			alert(data.msg);
		}
	});
}

</script>
<body>
	<form class="form-horizontal" role="form" id="editMaterialForm">
		<input type="hidden"  name="id" value="${id}">
		<table width="100%">
			<tbody>
				<tr>
					<th colspan="2"  style="width:70px;"><h5>&nbsp;&nbsp;物流名称：</h5></th>	
					<th colspan="2">
						<select class="form-control"  name="courier_type"  id="courier_type" style = "width:68%;">
							<option value="">请选择</option>
							<c:forEach items="${express}" var="exp">
							     <option value="${exp.expressValue }">${exp.expressName }</option>	
							</c:forEach>
						</select>
					</th>
				</tr>
				<tr>
					<th colspan="2" style="width:70px;"><h5>&nbsp;&nbsp;物流订单：</h5></th>	
					<th colspan="2"><input style="width:450px;" type="text" class="form-control"  name="courier_number" id = "courier_number"  value="${material.price}"></th>
				</tr>
				<tr style="height: 30PX">
					<th ></th><th colspan="3"></th>
				</tr>
 				<tr>
	 					<th colspan="4" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  确认发货  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取消发货  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
	 <script src="<%=path%>/resources/upload/upload.js"></script>
	 <script src="<%=path%>/js/marketingmanagement/materialAddOrEdit.js"></script>
</body>
</html>

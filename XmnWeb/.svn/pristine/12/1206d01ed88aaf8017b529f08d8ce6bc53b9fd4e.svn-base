<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body>
	<form class="form-horizontal" role="form" id="editMaterialForm">
		<input type="hidden"  name="id" value="${material.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;物料图片：</h5></th>	
					<th>
						<div style="width:180px;">
							<input type="hidden" class="form-control" id="thumbnail" name="thumbnail"  value="${material.thumbnail}">
							<div id="adbpicUpload"></div>
							<div style="width:180px;float:left;"><small style="color:red;">物料图片(640*160)</small></div>
						</div>
					</th>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;物料名称：</h5></th>	
					<th colspan="3"><input type="text" class="form-control" name="name"  value="${material.name}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;价格：</h5></th>	
					<th colspan="3"><input type="text" class="form-control"  name="price"  value="${material.price}"></th>
				</tr>
<!-- 				<tr> -->
<!-- 					<th style="width:70px;"><h5>&nbsp;&nbsp;销售数量：</h5></th>	 -->
<!-- 					<th colspan="3"><input type="text" class="form-control"  name="sold_quantity"  value="${material.sold_quantity}"></th> -->
<!-- 				</tr> -->
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;上架状态：</h5></th>	
					<th colspan="3">
						<select class="form-control"  name="status" style = "width:83%;">
							<option value="">请选择</option>
							<option value="1" ${material.status==1?"selected":""}>上架</option>
							<option value="0" ${material.status==0?"selected":""}>下架</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;是否必买：</h5></th>	
					<th colspan="3">
						<select class="form-control" name="ismust" style = "width:83%;">
							<option value="">请选择</option>
							<option value="1" ${material.ismust==1?"selected":""}>是</option>
							<option value="0" ${material.ismust==0?"selected":""}>否</option>
						</select>
					</th>
				</tr>
				<tr style="height: 30PX">
					<th ></th><th colspan="3"></th>
				</tr>
 				<tr>
	 					<th colspan="4" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
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

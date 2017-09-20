<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>查看区域合作商</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">


<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">

	<dir></dir>
	
	<div id="main" style="min-height: 903px;">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">合作商区域详情</div>
				<div class="panel-body">
					<form action=""  role="form">
					<table class="table" style="text-align: center;">
				    <tr><td>
						<table class="table" style="text-align: center;">
						<tr>
							<td style="width:70px; text-align: left; ">
									<h5 style="width: 100%"><font style="font-size: 16px">所属省:</font></h5> 
							</td>
							<td style="width:20%; text-align: left;" >
									${getusinessAreaList.title}
								<%--  <input disabled="disabled" type="text" name = "title" class="form-control"  value = "${getusinessAreaList.title}">  --%>
							</td>		
							<td style="width: 70%"></td>								
						</tr>
						
						<tr>
							<td style="width:70px; text-align: left;">
									<h5 style="width: 100%"><font style="font-size: 16px;">所属城市:</font></h5> 
							</td>
							<td style="width:20%; text-align: left;">	
							    ${getusinessAreaList.cityName}							
								<%--  <input disabled="disabled" type="text" class="form-control" name="cityName"  value="${getusinessAreaList.cityName}"> --%>
							</td>
													
						</tr>
						
						<%-- <tr>
							<td style="width:80px;  text-align: left;">
									<h5><font style="font-size: 16px;">所属团队:</font></h5> 
							</td>
							<td style="width:20%; text-align: left;">
							     ${getusinessAreaList.teamName}
								 <input disabled="disabled" type="text" class="form-control" name="teamName"  value="${getusinessAreaList.teamName}">
							</td>
													
						</tr> --%>
						
						<tr>
	<table class="table table-hover table-bordered table-striped info" >

	<thead>
	<tr>
	<th style="width:140px; text-align: center;">区域数</th>
	<th style="width:140px;text-align: center;">商圈</th>
	<th style="width:100px;text-align: center;">合作商数</th>
	<th style="width:100px;text-align: center;">已签约数</th>
	<th style="width:100px;text-align: center;">签到数</th>
	<th style="width:100px;text-align: center;">审核中</th>
<!-- 	<th style="width:100px;">未验证</th>	 -->
<!-- 	<th style="width:100px;">会员数</th>
	<th style="width:100px;">寻蜜客</th>
	<th style="width:100px;">消费流水</th>
	<th style="width:100px;">返利总额</th>
	<th style="width:100px;">佣金总额</th> -->
		
	</tr>
	</thead>
	<tbody>
		<tr>
		<td style="text-align: center;">${getusinessAreaList.areaCount}</td>
		<td style="text-align: center;">${getusinessAreaList.businessCount} </td>
		<td style="text-align: center;">${getusinessAreaList.jointCount}</td>
		<td style="text-align: center;">${getusinessAreaList.sellerSignCount}</td>
		<td style="text-align: center;">${getusinessAreaList.registerRecordCount}</td>
		<td style="text-align: center;">${getusinessAreaList.sellerAuditCount}</td>
	<%-- 	<td>${getusinessAreaList.sellerNoValidationCount}</td> --%>
<%-- 		<td>${getusinessAreaList.cityName}</td>
		<td>${getusinessAreaList.cityName}</td>
		<td>${getusinessAreaList.cityName}</td>
		<td>${getusinessAreaList.cityName}</td>
		<td>${getusinessAreaList.cityName}</td> --%>
		</tr>

	</tbody>
	</table>
						
						
			</tr>
																																		
					<%-- 	<tr>
						<td style="width:80px;" >
									<h5>审批说明:</h5> 
							</td>
							<td style="width:150px;"  colspan = "10">
									<textarea disabled="disabled" name="examineinfo" rows="3" class="form-control">${selleridList.examineinfo}</textarea>
							</td>
						</tr>		 --%>							
				
						</table>
						</td>											
						</tr></table>
					</form>
				</div>
			</div>
		</div>
				
		
		
		
	
	<div tabindex="-1" class="modal fade in msg-model" data-position="100px" role="dialog" aria-hidden="true" aria-labelledby="mySmallModalLabel" style="display: none;">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button class="close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		          <h4 class="modal-title" id="mySmallModalLabel">系统提示</h4>
		        </div>
		        <div class="modal-body text-center" id="sysmsg">
		        </div>
		        <div class="modal-footer">
		            <button type="button"  class="btn btn-default" 
		               data-dismiss="modal" id="close-modal-sm">关闭
		            </button>
		         </div>
		      </div>
		    </div>
  	</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
    <script src="<%=path%>/ux/js/ld2.js"></script>

    <script type="text/javascript">
 	
   $(function(){
	
	   			 
	});    
  </script>
  <%--  <script src="<%=path%>/js/businessman/viewSeller.js"></script>  --%>
   
</body>
</html>

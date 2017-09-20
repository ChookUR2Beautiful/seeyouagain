<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>取消场次</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<form id="editFrom" role="form" class="form-horizontal">
		<input type="hidden" name="id" value="${activity.id }">
		<input type="hidden" name="hasSold" value="${hasSold }">
<%--					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:30%;text-align: right;" colspan="2"><h5>
								<c:if test="${!empty hasSold}">已有体验官报名成功了该场次，是否需要给他们安排其他场次：</c:if>
								<c:if test="${empty hasSold}">暂无体验官报名成功该场次，是否确定取消该场次：</c:if></h5></td>
								<td style="width:40%;">
								<h5>&nbsp;&nbsp;&nbsp;&nbsp;
								<label></label><input  type="radio"  name="doChange" value="0" ${config.isFree==0?'checked':''}> 否</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label><input  type="radio"  name="doChange" value="1" ${(config.isFree==null || config.isFree==1)?'checked':''} >是</label></h5>
								</td>
							</tr>
							<tr id="new_activity_id">
								<td style="width:30%;text-align: right;"><h5>更换场次:&nbsp;&nbsp;</h5></td>
								<td style="width:70%;"colspan="2">
									<select class="form-control" id="newActivityId" name="newActivityId"  style="width:100%;"  >
									</select> 
								 </td>
							</tr>
							<tr>
								<td style="width:30%;text-align: right;"><h5>是否确定取消该场次:&nbsp;&nbsp;</h5></td>
								<td style="width:60%;"></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;"> 
			 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
									<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
		 						</td>
							</tr>
						</tbody>
					</table> --%>
		<c:if test="${ hasSold==1}">
			<div class="form-group">
			<label class="col-md-8 control-label">已有体验官报名成功了该场次，是否需要给他们安排其他场次：<span style="color:red;">*</span></label>
			<div class="col-md-4">
				<div class="input-group">
					<label class="radio-inline">是<input type="radio"
						name="isChange" value="1" checked="checked" /></label> 
					<label class="radio-inline">否<input	type="radio" 
						name="isChange"  value="0"/></label>
				</div>
			</div>
			</div>
			<div class="form-group" id="isChangeActivity">
				<label class="col-md-3 control-label">更换场次:<span style="color:red;">*</span></label>
				<div class="col-md-8">
					<label id="newActivity"></label>
					<select class="form-control" id="newActivityId" name="newActivityId"  style="width:100%;"  >
					</select>
				</div>
			</div>
		</c:if>
		<c:if test="${hasSold==0}">
			<div class="form-group">
			<label class="col-md-8 control-label">暂无体验官报名成功该场次，是否确定取消该场次？</label>
			</div>
		</c:if>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="submitBtn" >
					<span class="icon-ok"></span> 确 定
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/experienceofficer/cancelExperienceofficerActivity.js?v=1.4"></script>
</body>
</html>

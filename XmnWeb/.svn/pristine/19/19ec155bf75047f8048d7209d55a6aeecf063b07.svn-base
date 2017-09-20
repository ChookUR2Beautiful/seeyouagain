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

<title>批量修改通告基础信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<form id="editFrom" role="form" class="form-horizontal">
		
		<input type="hidden" id="ids" name="ids" value="${ids }">
		<input type="hidden" id="updateBaseInfoBatchToken" name="updateBaseInfoBatchToken" value="${updateBaseInfoBatchToken }">
		<div class="form-group">
			<label class="col-md-4 control-label">通告标题：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="zhiboTitle"
					id="zhoboTitle" value="${liveRecord.zhiboTitle}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">直播类型：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="liveTopic" value="1" type="radio" checked="checked" ><span style="font-size: 12px;">商家</span>
				<input name="liveTopic" value="2" type="radio"  ><span style="font-size: 12px;">活动</span>
			</div>
		</div>
		
		<div class="form-group" id="sellerDiv">
			<label class="col-md-4 control-label">商铺：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="sellerid" name="sellerid"
					initValue="${liveRecord.sellerid}" style="width:100%;"></select> <input
					type="hidden" class="form-control" id="sellername"
					name="sellername" value="${liveRecord.sellername}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label" id="sellerAliasLabel">商家别名：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="sellerAlias"
					id="sellerAlias" value="${liveRecord.sellerAlias}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">直播地点：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="zhiboAddress"
					id="zhiboAddress" value="${liveRecord.zhiboAddress}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">直播计划时间：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control form_datetime" style="width:46%;"
					id="planStartDate" name="planStartDate"
					value="${liveRecord.planStartDateStr}" readonly="readonly">
				<span>--</span>
				<input type="text" class="form-control form_datetime" style="width:46%;"
					id="planEndDate" name="planEndDate"
					value="${liveRecord.planEndDateStr}" readonly="readonly">
			</div>
		</div>
		
		<div class="form-group" id="isAppointDiv">
			<label class="col-md-4 control-label">指定观众观看：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="isAppoint" value="1" type="radio" ${liveRecord.isAppoint==1?"checked":""} ><span style="font-size: 12px;">是</span>
				<input name="isAppoint" value="0" type="radio" ${liveRecord.isAppoint==0||liveRecord.isAppoint==null?"checked":""} ><span style="font-size: 12px;">否</span>
			</div>
		</div>
		
		<div class="form-group" id="telphonesDiv" style="display:none;">
			<label class="col-md-4 control-label">指定观众电话号码：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<textarea class="form-control" rows="2" 
					style="padding:6px;height:50px;"  id="telphones" name="telphones" placeholder="指定观众电话号码 ,以英文逗号','分割">${liveRecord.telphones}</textarea>
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="ensure">
					<span class="icon-ok" ></span> 保 存
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
	<script src="<%=path%>/js/live_anchor/updateBaseInfoBatch.js?v=1.0.3"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加套餐</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="panel panel-info">
			<div class="panel-heading">添加主播等级</div>
			<div class="panel-body">
				<input type="hidden" id="isType" name="isType" value="${isType}"/> 
				<!-- 主播 -->
				<form id="liveLevelForm" role="form" class="form-horizontal">
				    <input type="hidden" id="id" name="id" value="${liveLevelInfo.id}"/> 
					<div class="form-group">
						<label class="col-md-3 control-label">级别名称：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="levelName" name="levelName" placeholder="级别名称"
								value="${liveLevelInfo.levelName}" style="width:41%;float:left">
						    <input type="hidden" class="form-control" id="oldLevelName" name="oldLevelName"  value = "${liveLevelInfo.levelName}"/>
						</div>
					</div>
					<!-- <hr> -->
					<div class="form-group">
						<label class="col-md-3 control-label">级别薪酬: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="levelIncome" name="levelIncome" placeholder="级别薪酬"
								value="${liveLevelInfo.levelIncome}" style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">浮动绩效: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="floatPerformance" name="floatPerformance" placeholder="浮动绩效"
								value="${liveLevelInfo.floatPerformance}" style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">收入上限: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="topIncome" name="topIncome" readOnly="true"  placeholder="自动计算无需填写"
								value="${liveLevelInfo.topIncome}" style="width:41%;float:left">
						</div> 
					</div>
					<hr>
					<div class="form-group">
						<label class="col-md-3 control-label">完成率100%场次: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="percentComplete" name="percentComplete" placeholder="完成率100%场次"
								value="${liveLevelInfo.percentComplete}" style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">完成率80%场次: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="percentComplete80" name="percentComplete80" placeholder="完成率80%场次"
								value="${liveLevelInfo.percentComplete80}" style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">完成率60%场次: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="percentComplete60" name="percentComplete60" placeholder="完成率60%场次"
								value="${liveLevelInfo.percentComplete60}" style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">完成率40%场次: <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="percentComplete40" name="percentComplete40"  placeholder="完成率40%场次"
								value="${liveLevelInfo.percentComplete40}" style="width:41%;float:left">
						</div>
					</div>	
					<div class="form-group">
						<label class="col-md-3 control-label">送礼分成(%): <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="giftAllot" name="giftAllot"  placeholder="送礼分成"
								value="${liveLevelInfo.giftAllot}" style="width:41%;float:left">
						</div>
					</div>
                    <div class="form-group">
						<label class="col-md-3 control-label">有效时长(分钟): <span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="liveHours" name="liveHours"  placeholder="有效时长"
								value="${liveLevelInfo.liveHours}" style="width:41%;float:left">
						</div>
					</div>						
					<hr />
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-6">
							<p>1、实发薪酬1=（级别保底薪酬*完成率）+浮动奖罚*完成率*（80%）、实发薪酬2=（级别保底薪酬*完成率）*（80%）；说明：1为礼物收入超出保底情况，2为礼物收入不足保底情况</p>
							<p>2、签约主播需在电商、旅游、美食、娱乐直播领域独家合作；秀场类、游戏类直播不设限制</p>
							<p>3、签约主播需保障平台工时最低要求，优先安排通告，通告不足的，以兼播时长补足</p>
							<p>4、签约主播主要安排商户通告，档期不足的，协调优秀兼播协助做通告</p>
							<p>5、工会合作约定总场次，核算总费用，主播不予提现，统一结算给工会，工会开发票后自行分配</p>
							<p>6、所有主播均需遵守鸟人直播日常管理条例开展具体工作</p>
							<p>7、级别薪酬及浮动绩效均体现为礼物收入人民币：鸟蛋=1:1000</p>
						</div>
					</div>

					<hr />
					<div align="center">
						<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
						<!-- <button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button> -->
						<button type="reset" class="btn btn-info" data-dismiss="modal"><span class="icon-remove"></span> 取 消</button>
					</div>
				</form>
	
				
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
 
    <script src="<%=path%>/js/live_anchor/liveLevel/editLiveLevel.js"></script> 
</body>
</html>

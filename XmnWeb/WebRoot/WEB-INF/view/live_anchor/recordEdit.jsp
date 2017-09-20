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

<title>添加通告</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<style type="text/css">
img.upload-btn{
	vertical-align: top;
}
#box span{
	display: inline-block;
    padding: 3px 10px;
    border: 1px solid;
    border-radius: 5px;
    position:relative;
    margin-right: 30px;
}
#box span em,#datas .img-list li em{
	position: absolute;
	right: -15px;
	top: 0;
	color: #c13535;
	cursor: pointer;
}
.img-list{
	display:inline-block;
}
#datas .img-list{
	font-size:0;
}
#datas .img-list li{
	position:relative;
	display:inline-block;
	margin-right:20px;
}
.img-list li img{
	width:100px;
	height:100px;
}
</style>
</head>
<body>
<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
					<div class="panel-body">
						<form id="editFrom" role="form" class="form-horizontal">
						<input type="hidden"  name="addToken" value="${addToken}">
						<input type="hidden"  id="targetUrl" value="${targetUrl}">
						<input type="hidden"  id="appointAnchor" value="${appointAnchor}">
							<c:if test="${!empty liveRecord}">
								<input type="hidden" name="id" value="${liveRecord.id}">
								<input type="hidden" name="zhiboType" id="zhiboTypeEdit" value="${liveRecord.zhiboType}">
								<input type="hidden" id="setSellerSequNo" value="${setSellerSequNo}">
								<input type="hidden" name="operationType" id="operationType"  value="${liveRecord.operationType}">
							</c:if>
							<h3>通告信息</h3>
							<div class="form-group">
								<label class="col-md-4 control-label">通告标题：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="zhiboTitle"
										id="zhoboTitle" value="${liveRecord.zhiboTitle}">
								</div>
							</div>
							<div class="form-group" id="liveTopicDiv">
								<label class="col-md-4 control-label">直播类型：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="liveTopic" value="1" type="radio" ${liveRecord.liveTopic==1?"checked":""} ><span style="font-size: 12px;">商家</span>
									<input name="liveTopic" value="2" type="radio" ${liveRecord.liveTopic==2?"checked":""} ><span style="font-size: 12px;">活动</span>
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
								<label class="col-md-4 control-label" id="sellerAliasLabel">商家别名 ：<span
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
									<input type="text" class="form-control form_datetime" style="width:49%;"
										id="planStartDate" name="planStartDate"
										value="${liveRecord.planStartDateStr}" readonly="readonly">
									<span>--</span>
									<input type="text" class="form-control form_datetime" style="width:49%;"
										id="planEndDate" name="planEndDate"
										value="${liveRecord.planEndDateStr}" readonly="readonly">
								</div>
							</div>
							
							<div class="form-group" id="isAppointDiv">
								<label class="col-md-4 control-label">指定观众观看：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="isAppoint" value="1" type="radio" ${liveRecord.isAppoint==1?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="isAppoint" value="0" type="radio" ${liveRecord.isAppoint==0?"checked":""} ><span style="font-size: 12px;">否</span>
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
							
							<div class="form-group" id="passwordDiv">
								<label class="col-md-4 control-label">直播间密码：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="anchorRoomPassword"
										id="anchorRoomPassword" value="" placeholder="请填写6位数字密码">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label">显示其他商家信息：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="showOtherSeller" value="0" type="radio" ${liveRecord.showOtherSeller==0?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="showOtherSeller" value="1" type="radio" ${liveRecord.showOtherSeller==1?"checked":""} ><span style="font-size: 12px;">否</span>
								</div>
							</div>
							
							<hr>
							
							<!-- 选择主播模块开始 -->
							<h3>选择主播</h3>
							<div class="form-group" id="anchorDiv">
								<label class="col-md-4 control-label">选择主播：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<select class="form-control" id="anchorId_" name="anchorId"
										initValue="${liveRecord.anchorId}" style="width:100%;">
									</select> 
										<input type="hidden" class="form-control" id="nname" name="nname" value="${liveRecord.nname}">
										<input type="hidden" class="form-control" id="sex" name="sex" value="${liveRecord.sex}">
								</div>
							</div>
							
							<div class="form-group" id="sequenceNoDiv">
								<label class="col-md-4 control-label">直播推荐排序号：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="sequenceNo"
										id="sequenceNo" placeholder="排序值越小,排序越靠前"
										value="${liveRecord.sequenceNo}">
								</div>
							</div>
							
							<div class="form-group" id="liveRecordTagDiv">
								<label class="col-md-4 control-label">直播标签：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<select class="form-control" id="classifyId" style="width:45%;">
									</select>
									<select class="form-control" id="tagId" style="width:45%;">
									</select>
									<button type="button" class="btn btn-success" id="addTagBtn" >
										 确认
									</button>
									<p id="box" style="margin-top: 20px;">
									<c:forEach items="${recordTagConfList }" var="recordTagConf">
										<span name='${recordTagConf.tagId }'>${recordTagConf.tagName }<em class='icon-remove'></em></span>
									</c:forEach>
									</p>
									<br>
									<input type="hidden" id="tagIds" name="tagIds" value="${liveRecord.tagIds }">
								</div>
							</div>
							
							
							<div class="form-group" id="zhiboPlaybackUrlDiv" style="display:none;">
								<label class="col-md-4 control-label">回放地址URL：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="zhiboPlaybackUrl"
										id="zhiboPlaybackUrl" placeholder="请填写回放地址"
										value="${liveRecord.zhiboPlaybackUrl}">
								</div>
							</div>

						<div class="form-group" id="zhiboCoverDiv">
							<label class="col-md-4 control-label">上传封面：<span
								style="color: red;">*</span></label>
							<div class="col-md-7" id="datas">
								<div style="position: relative; margin-top: 10px;">

									<img src="<%=path%>/resources/upload/add.png"
										class="upload-btn" style="width: 100px; height: 100px;" />
									<ul class="img-list">
										<c:forEach items="${liveRecord.bannerList }" var="banner">
											<li><img src='${banner.picUrlStr}' /><em
												class='icon-remove'></em></li>
										</c:forEach>
									</ul>
									<input type="hidden" class="pic-url-list" name="picUrls"
										value="">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">是否自定义分享描述：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input name="isCustomShare" value="1" type="radio" ${liveRecord.isCustomShare==1?"checked":""}><span
									style="font-size: 12px;">是</span> &nbsp;&nbsp;&nbsp;&nbsp; 
								<input name="isCustomShare" value="2" type="radio" ${liveRecord.isCustomShare==2?"checked":""}><span
									style="font-size: 12px;">否</span>
							</div>
						</div>
						<div class="form-group" id="customShareTitleInfo" style="display: none;">
							<label class="col-md-4 control-label" id="customShareTitleLabel">分享标题：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="customShareTitle"
									id="customShareTitle" value="${liveRecord.customShareTitle}">
							</div>
						</div>
						<div class="form-group" id="customShareDescInfo" style="display: none;">
							<label class="col-md-4 control-label" id="customShareDescLabel">分享描述：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="customShareDesc"
									id="customShareDesc" value="${liveRecord.customShareDesc}">
							</div>
						</div>
						
						<!-- 配置机器人star -->
						<div class="form-group">
							<label class="col-md-4 control-label">是否配置机器人：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input name="isRobotInit" value="1"
									type="radio" ${liveRecord.isRobotInit==1?"checked":""}><span
									style="font-size: 12px;">是</span> &nbsp;&nbsp;&nbsp;&nbsp; 
								<input name="isRobotInit" value="2"
									type="radio" ${liveRecord.isRobotInit==2?"checked":""}><span
									style="font-size: 12px;">否</span>
							</div>
						</div>
						
						<div class="form-group" name="robotRangeDiv" style="display: none;">
							<label class="col-md-4 control-label">新增1个用户随机增加机器人：<span
								style="color: red;">*</span></label>
							<div class="col-md-3">
								<input class="form-control" name="robotSetMixNums" value="${liveRecord.robotSetMixNums }" placeholder="最小值" onblur="robotNumBlur(this)">
							</div>
							
							<label class="col-md-1 control-label">至</label>
							<div class="col-md-3">
								<input class="form-control" type="input"name="robotSetMaxNums" value="${liveRecord.robotSetMaxNums }" placeholder="最大值" onblur="robotNumBlur(this)">
							</div>
						</div>
						
						<div class="form-group" name="robotConfDiv" style="display: none;">
							<label class="col-md-4 control-label">初始机器人：<span
								style="color: red;">*</span></label>
							<div class="col-md-3">
								<input type="text" class="form-control"
									name="robotMinNums" value="${liveRecord.robotMinNums }" onblur="robotNumBlur(this)">
							</div>
							
							<label class="col-md-1 control-label">最高上限机器人：<span
								style="color: red;">*</span></label>
							<div class="col-md-3">
								<input type="text" class="form-control"
									name="robotMaxNums" value="${liveRecord.robotMaxNums }" onblur="robotNumBlur(this)">
							</div>
						</div>
						
						<div class="form-group" name="robotMultipleDiv" style="display: none;">
							<label class="col-md-4 control-label">显示倍数：<span
								style="color: red;">*</span></label>
							<div class="col-md-3">
								<input type="text" class="form-control"
									name="multiple" value="${liveRecord.multiple }" onblur="robotNumBlur(this)">
							</div>
						</div>
						
						<!-- 配置机器人end -->

						<!-- 粉丝券信息 begin -->
						<div id="couponDiv">
							<hr>
								<!-- 设置粉丝券信息 -->
								<h3>设置粉丝券</h3>
								<div class="form-group">
									<label class="col-md-4 control-label">是否绑定粉丝券:<span
										style="color:red;">*</span></label>
									<div class="col-md-7">
										<input name="haveCoupon" value="1" type="radio" ${liveRecord.haveCoupon==1?"checked":""} ><span style="font-size: 12px;">是</span>
										<input name="haveCoupon" value="0" type="radio" ${liveRecord.haveCoupon==0?"checked":""} ><span style="font-size: 12px;">否</span>
									</div>
								</div>
								
								<div class="form-group on-off" >
									<input type="hidden" id="cid" name="cid" value="${couponAnchor.cid }">
									<label class="col-md-4 control-label">券名:<span
										style="color:red;">*</span></label>
									<div class="col-md-7" id="cname">
									</div>
								</div>
								
								<div class="form-group on-off">
									<label class="col-md-4 control-label">关联商户:<span
										style="color:red;">*</span></label>
									<div class="col-md-7" id="couponSellerName">
									</div>
								</div>
								
								<div class="form-group on-off">
									<label class="col-md-4 control-label">设置价格(元):<span
										style="color:red;">*</span></label>
									<div class="col-md-7" id="denomination">
										0
									</div>
								</div>
								
								<div class="form-group on-off">
									<label class="col-md-4 control-label">原价(元):<span
										style="color:red;">*</span></label>
									<div class="col-md-7" id="originalPrice">
										0
									</div>
								</div>
								
								<div class="form-group on-off">
									<label class="col-md-4 control-label">粉丝券数量:<span
										style="color:red;">*</span></label>
									<div class="col-md-7" id="defaultMaxNum">
										0
									</div>
								</div>
								
								<div class="form-group on-off">
									<label class="col-md-4 control-label">赠送预售抵用券:<span
										style="color:red;">*</span></label>
									<div class="col-md-7">
										<input name="haveFree" value="1" type="radio" disabled="true"><span style="font-size: 12px;">是</span>
										<input name="haveFree" value="2" type="radio" disabled="true"><span style="font-size: 12px;">否</span>
									</div>
								</div>
								
								<div class="form-group on-off" id="voucherDiv">
									<label class="col-md-4 control-label">设置抵用券面值:<span
										style="color:red;">*</span></label>
									<div class="col-md-7">
											<!-- 抵用券列表 -->
											<div class="col-sm-6 plandiv" id="plandiv">
											</div>	
									</div>
								</div>
								
								<div class="form-group on-off">
									<label class="col-md-4 control-label">使用时间:<span
										style="color:red;">*</span></label>
									<div class="col-md-7" id="useTimeZone">
									
									</div>
								</div>
							</div>
							<!-- 粉丝券信息 end -->
							
							
							<div class="form-group">
								<div class="text-center" style="padding:10px 0 10px 0;">
									<button type="submit" class="btn btn-success">
										<span class="icon-ok"></span> 保 存
									</button>
									&nbsp;&nbsp;
									<a class="btn btn-warning" href="${targetUrl}?"><span class="icon-remove"></span>&nbsp;取消</a>
								</div>
							</div>
						</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/recordEdit.js?v=1.0.10"></script>
</body>
</html>

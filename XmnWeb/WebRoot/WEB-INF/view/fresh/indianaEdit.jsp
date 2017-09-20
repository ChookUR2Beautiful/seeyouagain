<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../common.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加活动</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
	<style type="text/css">
		input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button{
    -webkit-appearance: none !important;
    margin: 0;
}
input[type="number"]{-moz-appearance:textfield;}
	</style>
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"  value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
			<div class="panel panel-primary">
				<div class="panel-heading">添加活动</div>
				<div class="panel-body">
					<form id="editFrom" role="form"
						class="form-horizontal scrollbar-hover">
						<input type="hidden" value="${activity.id}" name="id" id="activityId"/>
						<div class="form-group">
							<label class="col-md-3 control-label">选择商品：<span
								style="color:red;">*</span></label>
							<div class="col-md-7" style="width:25%;">
								<select class="form-control" id="brandId" name="codeId"
									style="width:41%;float:left" initValue="${activity.codeId}"></select>
							</div>
							<select class="form-control" id="group"
								style="width:20%;float:left">
							</select>
						</div>
						<div id="product_div" style="display:none;">
						<hr/>
							<div class="form-group">
							<label class="col-md-3 control-label">图片：</label>
							<div class="col-md-7">
								<img style="float:left" id="breviary" src="docs/img/img1.jpg" width="200px" height="200px" class="img-rounded" alt="图片">	
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商品名称：</label>
							<div class="col-md-7" id="goodsName">
								蓝月亮菊花清香洗衣液护手250m
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商品规格：</label>
							<div class="col-md-7" id="pro_group">
								<input type="text" class="form-control" id="pvValue" name="pvValue"
									value="${activity.pvValue}" style="width:41%;float:left" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">售价：</label>
							<div class="col-md-7" id="price">
								￥9.80 + 6.00 积分
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">库存：</label>
							<div class="col-md-7" id="store">
								10
							</div>
						</div>
						
						<hr/>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">夺宝标题：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="title" name="title"
									value="${activity.title}" style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商品总价：</label>
							<div class="col-md-7" id="place_sum">
								<input type="text" class="form-control" id="basePrice" name="basePrice"
									value="${activity.basePrice}" style="width:41%;float:left" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">夺宝份数：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="number" class="form-control" id="point"
									name="point" value="${activity.point}"
									style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">一份价格：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="number" class="form-control" id="pointPrice"
									name="pointPrice" value="${activity.pointPrice}"
									style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">夺宝期数：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="number" class="form-control" id="boutNum"
									name="boutNum" value="${activity.boutNum}"
									style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">开始时间(第一期)：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" id="begin_time" name="beginTime"
									placeholder="yyyy-MM-dd hh:mm"
									class="form-control form-datetime" style="width:41%;float:left"
									value="<fmt:formatDate value="${activity.beginTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>"
									readonly="readonly" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">结束时间(所有期数)：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" id="end_time" name="endTime"
									placeholder="yyyy-MM-dd hh:mm"
									class="form-control form-datetime" style="width:41%;float:left"
									value="<fmt:formatDate value="${activity.endTime}" type="both" pattern="yyyy-MM-dd HH:mm" />"
									readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">补全机制：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								活动结束人数不足时，自动使用机器人补全，并抽机器人获得奖品，设置了预设获奖人，在人数不够的情况下也会自动补全机器人并抽取预设获奖人作为获奖者
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">算法公式：</label>
							<div class="col-md-7">人数足够时公式
								1、积分超市有效订单最新50条的时间求和（时、分、秒、毫秒，如12:12:21.234为121221234）
								2、求和总数除于份数获得余数加上1123000001(前4位活动ID+分数编号) 3、最后结果为中奖号码
								公式举例：（最后50个时间求和/需求人次）获得的余数+1123000001
								(5641414581/7288)获得余数5101+1123000001=11230005102（中奖号码） 人数不足够时公式
								1、按价格剩余部分，每10份随机拆分3-5机器人 2、随机在机器人中选中一个成为中奖号码</div>
						</div>
						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 0;">
								<button type="submit" class="btn btn-success" id="ensure">
									<span class="icon-ok"></span> 保 存
								</button>
								&nbsp;&nbsp; <a class="btn btn-warning"
									href="fresh/indiana/init.jhtml"><span
									class="icon-remove"></span>&nbsp;取消</a>
							</div>
						</div>
					</form>
				</div>
			</div>
	</div>
<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/js/fresh/indianaEdit.js?v=1.0.4"></script>
	<script type="text/javascript">
		var pvIds="${activity.pvIds}";
		var chooseCodeId="${activity.codeId}";
		$("input[type='number']").on("mousewheel",function(){
			return false;
		});
	</script>
</body>
</html>

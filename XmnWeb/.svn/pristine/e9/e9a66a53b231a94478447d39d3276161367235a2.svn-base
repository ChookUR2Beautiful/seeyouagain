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
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
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
					<form id="editForm" role="form"
						class="form-horizontal scrollbar-hover">
						<c:if test="${!empty activity}">
							<input type="hidden" name="id" value="${activity.id}"  />
						</c:if>
						<div class="form-group">
							<label class="col-md-3 control-label">选择商品：<span
								style="color:red;">*</span></label>
							<div class="col-md-7" style="width:25%;">
								<select class="form-control" id="brandId" name="codeid" initValue="${activity.codeid}"
									style="width:41%;float:left"></select>
								<input type="hidden" name="initCodeId" value="${activity.codeid}">
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
								<img style="float:left" id="breviary" src="" width="200px" height="200px" class="img-rounded" alt="图片">	
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
							<div class="col-md-7">
								<input type="text" id="store" name="store"  class="form-control"
									value="" readonly="readonly" style="width:41%;">
							</div>
						</div>
						
						<hr/>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">竞拍标题：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="title" name="title"
									value="${activity.title}" style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商品总价：</label>
							<div class="col-md-7" id="place_sum"></div>
							<input type="hidden" id="basePrice" name="basePrice" value="${activity.basePrice }">
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">起拍价：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="start_price"
									name="startPrice" value="${activity.startPrice}" placeholder="起拍价"
									style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">加价幅度：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="range" placeholder="每次加价幅度"
									name="scope" value="${activity.scope}"
									style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">保险价：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="insurancePrice" placeholder="最终拍卖价低于保险价将会流拍"
									name="insurancePrice" value="${activity.insurancePrice}"
									style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">开始时间：<span
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
							<label class="col-md-3 control-label">结束时间：<span
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
							<div class="text-center" style="padding:10px 0 10px 0;">
									<button type="submit" class="btn btn-success">
										<span class="icon-ok"></span> 保 存
									</button>
									&nbsp;&nbsp;
									<a class="btn btn-warning" href="freshAuction/manage/init.jhtml?"><span class="icon-remove"></span>&nbsp;取消</a>
							</div>
						</div>
					</form>
				</div>
			</div>
	</div>
<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/js/fresh/auctionEdit.js?v=1.0.4"></script>
</body>
</html>

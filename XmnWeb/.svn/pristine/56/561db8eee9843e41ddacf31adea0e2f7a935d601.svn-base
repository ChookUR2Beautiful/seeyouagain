<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">添加活动</div>
				<div class="panel-body">
					<form id="editFrom" role="form" class="form-horizontal">
						<input type="hidden" value="${activity.id}" name="id" />
						<div class="form-group">
							<label class="col-md-3 control-label">活动标题：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="title" name="title"
									value="${activity.title}" style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">开始时间：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" id="begin_time" name="startTime"
									placeholder="yyyy-MM-dd hh:mm"
									class="form-control form-datetime" style="width:41%;float:left"
									value="<fmt:formatDate value="${activity.startTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>"
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
							<label class="col-md-3 control-label">活动图片：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="hidden" class="form-control" id="img" name="image"
									value="${activity.image}">
								<div id="activityImg"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">商品标签：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<select class="form-control"
												name="labelId"  id="label"
												style="width:41%;float:left" initValue="${activity.labelId}"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">购买限制：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input name="orderLimit" value="0" type="radio"
									${activity.orderLimit==null?"checked":""}><span
									style="font-size: 12px;">无购买限制</span>&nbsp;&nbsp;&nbsp;&nbsp; <input
									name="orderLimit" value="1" type="radio"
									${activity.orderLimit>0?"checked":""}><span
									style="font-size: 12px;">有购买限制</span>&nbsp;&nbsp;&nbsp;&nbsp; <span
									id="orderLimitSpan"
									style="${activity.orderLimit==null?'display:none;':''}">单件商品，每个用户限购<input
									type="number" class="form-control" id="orderLimit"  min="1"
									name="orderLimit" value="${activity.orderLimit}"
									style="width:8%;">件
								</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"><button
									class="btn btn-default" type="button" id="add_bout">增加场次</button></label>
							<div class="col-md-7"></div>
						</div>
						<div id="bout_list">
							<c:forEach items="${activity.activityCommons}" var="activityCommon"
								varStatus="status">
								<div id="bout_modul${status.index+1}" class="bout_modul">
									<hr />
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
										<div class="col-md-7">
											<button class="btn btn-default" type="button"
												style="float:right" name="delete_modul_bit">删除场次</button>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">活动时间：<span
											style="color:red;">*</span></label>
										<div class="col-md-7">
											<input type="text" name="beginDate${status.index+1}"
												class="form-control form-datetime"
												style="width:20%;float:left"
												value="<fmt:formatDate value="${activityCommon.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm"/>"
												readonly="readonly" />&nbsp;&nbsp;-&nbsp;&nbsp; <input
												type="text" name="endDate${status.index+1}"
												class="form-control form-datetime" style="width:20%"
												value="<fmt:formatDate value="${activityCommon.endDate}" type="both" pattern="yyyy-MM-dd HH:mm"/>"
												readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">选择商品：<span
											style="color:red;">*</span></label>
										<div class="col-md-7">
											<select class="form-control"
												name="productInfoChoose${status.index+1}"
												style="width:41%;float:left"></select>
										</div>
									</div>
									<div class="form-group" style="width: 50%;margin-left: 26%;"
										id="propertyTab">
										<table class="table table-bordered" class="propertyTable">
											<thead style="background-color: beige;">
												<tr>
													<td>商品编号</td>
													<td>商品名</td>
													<td>活动价格</td>
													<td>活动库存</td>
													<td>排序</td>
													<td>操作</td>
												</tr>

											</thead>
											<tbody name="kill_tbody">
												<c:if test="${!empty activityCommon.activityProductVo}">
													<c:forEach items="${activityCommon.activityProductVo}"
														var="product">
														<tr id="${product.pid}">
															<td>${product.codeId}</td>
															<td>${product.name}</td>
															<td>${product.sellIntegral}积分+${product.salePrice}</td>
															<td>${product.beforeStore}</td>
															<td>${product.sort}</td>
															<td data-row="0" data-index="0" data-flex="false"
																data-type="string" style="width: 130px; height: 59px;"><a
																href="javascript:;" onclick="editGroup(${product.pid})">编辑</a>&nbsp;&nbsp;<a
																href="javascript:;"
																onclick="deleteGroup(${product.pid})">删除</a></td>
															<input type="hidden" value='${product.json}'>
														</tr>
													</c:forEach>
												</c:if>
											</tbody>
										</table>
									</div>
								</div>
						</c:forEach>
						</div>
						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 0;">
								<button type="submit" class="btn btn-success" id="ensure">
									<span class="icon-ok"></span> 保 存
								</button>
								&nbsp;&nbsp; <a class="btn btn-warning"
									href="fresh/kill/init.jhtml"><span class="icon-remove"></span>&nbsp;取消</a>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
	<div id="bout_modul" style="display:none;">
		<hr />
		<div class="form-group">
			<label class="col-md-3 control-label"></label>
			<div class="col-md-7">
				<button class="btn btn-default" type="button" style="float:right"
					name="delete_modul_bit">删除场次</button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">活动时间：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" name="beginDate"
					class="form-control form-datetime" style="width:20%;float:left"
					value="" readonly="readonly" />&nbsp;&nbsp;-&nbsp;&nbsp; <input
					type="text" name="endDate" class="form-control form-datetime"
					style="width:20%" value="" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">选择商品：<span
				style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" name="productInfo"
					style="width:41%;float:left"></select>
			</div>
		</div>
		<div class="form-group" style="width: 50%;margin-left: 26%;"
			id="propertyTab">
			<table class="table table-bordered" class="propertyTable">
				<thead style="background-color: beige;">
					<tr>
						<td>商品编号</td>
						<td>商品名</td>
						<td>活动价格</td>
						<td>活动库存</td>
						<td>排序</td>
						<td>操作</td>
					</tr>

				</thead>
				<tbody name="kill_tbody">
				</tbody>
			</table>
		</div>
	</div>
	<button type="button" class="btn" data-type="ajax" id="but"
		data-url="fresh/activity/group/init.jhtml" data-toggle="modal"
		style="display:none;" />

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
	<script src="<%=path%>/js/fresh/killEdit.js?v=1.0.4"></script>
</body>
</html>

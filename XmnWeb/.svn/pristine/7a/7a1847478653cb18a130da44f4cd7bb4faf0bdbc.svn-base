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
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">添加活动</div>
				<div class="panel-body">
					<form id="editFrom" role="form" class="form-horizontal">
						<input type="hidden" value="${activity.id}" name="id"/>
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
								<input type="text" id="begin_time" name="beginDate"
									placeholder="yyyy-MM-dd hh:mm"
									class="form-control form-datetime" style="width:41%;float:left"
									value="<fmt:formatDate value="${activity.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm"/>" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">结束时间：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" id="end_time" name="endDate"
									placeholder="yyyy-MM-dd hh:mm"
									class="form-control form-datetime" style="width:41%;float:left"
									value="<fmt:formatDate value="${activity.endDate}" type="both" pattern="yyyy-MM-dd HH:mm" />" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">活动图片：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="hidden" class="form-control" id="img" name="img"
									value="${activity.img}">
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
						<hr />
						<div class="form-group">
							<label class="col-md-3 control-label">选择商品：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<select class="form-control" id="brandId" name="brandId"
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
								<tbody id="productTB">
									<c:if test="${!empty activityProducts}">
										<c:forEach items="${activityProducts}" var="product">
											<tr id="${product.pid}">
												<td>${product.codeId}</td>
												<td>${product.name}</td>
												<td>${product.sellIntegral}积分+${product.salePrice}</td>
												<td>${product.beforeStore}</td>
												<td>${product.sort}</td>
												<td data-row="0" data-index="0" data-flex="false"
													data-type="string" style="width: 130px; height: 59px;"><a
													href="javascript:;" onclick="editGroup(${product.pid})">编辑</a>&nbsp;&nbsp;<a
													href="javascript:;" onclick="deleteGroup(${product.pid})">删除</a></td>
												<input type="hidden"
													value='${product.json}'>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<hr />
						<div class="form-group">
							<label class="col-md-3 control-label">活动规则：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<textarea class="form-control" rows="3" name="remark">${activity.remark}</textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="text-center" style="padding:10px 0 10px 0;">
								<button type="submit" class="btn btn-success" id="ensure">
									<span class="icon-ok"></span> 保 存
								</button>
								&nbsp;&nbsp; <a class="btn btn-warning"
									href="fresh/activity/list/init.jhtml"><span
									class="icon-remove"></span>&nbsp;取消</a>
							</div>
						</div>
					</form>
				</div>
			</div>
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
	<script src="<%=path%>/js/fresh/activityEdit.js?v=1.0.4"></script>
</body>
</html>

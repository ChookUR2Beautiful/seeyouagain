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
<title>短信发送优惠券修改</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="add coupon">
<meta http-equiv="description" content="add coupon init">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

th {
	font-size: 12px;
}
</style>
</head>
<body style="overflow-x: auto;overflow-y: auto;background:#EEE" 	class="doc-views with-navbar">
	<div class="panel panel-block">
	 <div><h5>&nbsp;&nbsp;短信发送优惠券修改：</h5></div>
		<div class="panel-body">
			<form class="form-horizontal" id="editCouponForm">
			<input type="hidden" name="status" value="${tCouponIssue.status}">
			<input type="hidden" name="issueId" value="${tCouponIssue.issueId}">
			     <div class="form-group">
					<label class="col-md-2 control-label">活动名称：</label>
					<div class="col-md-5">
					           <input type="text" name="activityName" class="form-control"  value="${tCouponIssue.activityName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">短信内容：</label>
					<div class="col-md-5">
						<textarea  name="message" class="form-control">${tCouponIssue.message}</textarea>
					</div>
				</div>
				<c:if test="${tCouponIssue.status==2}">
				<div class="form-group">
					<label class="col-md-2 control-label">选择优惠券：</label>
					<div class="col-md-5">
					<c:forEach   items="${tCouponIssue.tCouponIssueRefs}" var="tCouponIssueRef" varStatus="status">
					     <input type="hidden" name="tCouponIssueRefs[0].refId" class="form-control" value="${tCouponIssueRef.refId}">
						<select  name="tCouponIssueRefs[0].cid" class="form-control" value="${tCouponIssueRef.cid}" >
								<option value="">--请选择--</option>
								<c:forEach items="${tCoupons}"	var="tCoupon">
								<option value="${tCoupon.cid}"  ${tCoupon.cid ==tCouponIssueRef.cid ? "selected" : ""} >${tCoupon.cname}</option>
								</c:forEach>
								</select>
					</c:forEach>
					</div>
				</div>
                </c:if>
                <c:if test="${tCouponIssue.status !=2}">
                <div class="form-group">
					<label class="col-md-2 control-label">选择优惠券：</label>
					<div class="col-md-5">
					<c:forEach   items="${tCouponIssue.tCouponIssueRefs}" var="tCouponIssueRef" varStatus="status">
					     <input type="hidden" name="tCouponIssueRefs[0].refId" class="form-control" value="${tCouponIssueRef.refId}">
						 <select  name="tCouponIssueRefs[0].cid" class="form-control" value="${tCouponIssueRef.cid}" disabled="disabled">
								<option value="">--请选择--</option>
								<c:forEach items="${tCoupons}"	var="tCoupon">
								<option value="${tCoupon.cid}"  ${tCoupon.cid ==tCouponIssueRef.cid ? "selected" : ""} >${tCoupon.cname}</option>
								</c:forEach>
								</select>
						<input type="hidden" name="tCouponIssueRefs[0].cid" value="${tCouponIssueRef.cid}">
					</c:forEach>
					</div>
				</div>
				</c:if>
              <%--  <div class="form-group">
					<label class="col-md-2 control-label">发行量：</label>
					<div class="col-md-5">
					 <c:forEach   items="${tCouponIssue.tCouponIssueRefs}" var="tCouponIssueRef" varStatus="status">
					           <input type="hidden" name="tCouponIssueRefs[0].refId" class="form-control" value="${tCouponIssueRef.refId}">
					           <input type="text" name="tCouponIssueRefs[0].issueVolume" class="form-control" value="${tCouponIssueRef.issueVolume}">
					 </c:forEach>
					</div>
				</div>  --%>
				
				<div class="form-group">
					<label class="col-md-2 control-label">备注：</label>
					<div class="col-md-5">
						<textarea  name="remark" class="form-control">${tCouponIssue.remark}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label"></label>
					<div class="col-md-5">
						<button type="submit" id="submit" class="btn btn-danger">保存</button>
						&nbsp;&nbsp;
						<button type="reset" id="result" class="btn btn-warning" onclick="window.history.back()">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script type="text/javascript">
	var dateCount = 0, cityCount = 0, dateSize = 10, citySize = 10
		$(function() {
			inserTitle(
					' >优惠券管理 > <span><a href="coupon/couponissue/init.jhtml.jhtml" target="right">优惠券发放管理</a> >短信发送优惠券修改',
					'couponList', true)

		validate("editCouponForm", {
		      rules: {
		            activityName:{
                              required: true,
                              minlength:3,
                              maxlength:60
                              },
                    message:{
                              required: true,
                              minlength:3,
                              maxlength:300
                              },
      'tCouponIssueRefs.cid':{
                              required: true,
                             
                              },
                      remark:{
                              required: true,
                              minlength:3,
                              maxlength:300
                              },
 'tCouponIssueRefs[0].issueVolume':{
                              required: true,
                              digits:true,
                              min:0,
                              max:999999
                              }

		      },messages: {
		             activityName:{
                              required:"不能为空",
                              minlength:"长度不能小于3个字符",
                              maxlength:"长度不能大于60个字符"
                          } ,
                     message: {
                              required:"不能为空",
                              minlength:"长度不能小于3个字符",
                              maxlength:"长度不能大于300个字符"
                               },
        'tCouponIssueRefs.cid': {
                               required:"不能为空",
                               },
                      remark: {
                              required:"不能为空",
                              minlength:"长度不能小于3个字符",
                              maxlength:"长度不能大于300个字符"
                               },
'tCouponIssueRefs[0].issueVolume':{
                              required: "不能为空",
                              digits:"请输入整数",
                              min:"最小为1",
                              max:"最大为999999"
                              }
		      }
		   }, formAjax)
		});
		function formAjax() {
			var data = jsonFromt($('#editCouponForm').serializeArray());
			var url = "coupon/couponissue/sendshortmessage/update.jhtml?activityType=3";
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					showSmReslutWindow(data.success, data.msg);
					// 添加成功后跳转到列表页面
					var url = contextPath +'/coupon/couponissue/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
	</script>
</body>
</html>

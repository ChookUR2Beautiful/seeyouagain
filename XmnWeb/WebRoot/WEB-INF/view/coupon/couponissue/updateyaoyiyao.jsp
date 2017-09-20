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
<title>摇一摇活动修改</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="add coupon">
<meta http-equiv="description" content="add coupon init">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
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
	 <div><h5>&nbsp;&nbsp;摇一摇活动修改：</h5></div>
		<div class="panel-body">
			<form class="form-horizontal" id="editCouponForm">
			<input type="hidden" name="issueId" value="${tCouponIssue.issueId}">
			<div class="form-group">
					<label class="col-md-2 control-label">活动名称：</label>
					<div class="col-md-5">
					           <input type="text" name="activityName" class="form-control" value="${tCouponIssue.activityName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">摇中概率<font color="red">(%)</font>：</label>
					<div class="col-md-5">
						<input type="text" name="hitRatio" class="form-control"  value="${tCouponIssue.intHitRatio}">
					</div>
				</div>
				<div class="form-group" id="dates">
					<label class="col-md-2 control-label">请选择：</label>
                    <div>
						<div class="col-md-8">
						 <c:forEach   items="${tCouponIssue.tCouponIssueRefs}" var="tCouponIssueRef" varStatus="status">
							<div class="input-group">
							    <input type="hidden" name="tCouponIssueRefs[${status.index}].refId" value="${tCouponIssueRef.refId}">
								<span class="input-group-addon form-date">选择优惠券：</span> 
								<input type="hidden"  name="tCouponIssueRefs[${status.index}].cid"  value="${tCouponIssueRef.cid}" />
								<select  class="form-control cid" value="${tCouponIssueRef.cid}"  disabled="disabled">
								<option value="">--请选择--</option>
								<c:forEach items="${tCoupons}"	var="tCoupon">
								<option value="${tCoupon.cid}" ${tCoupon.cid ==tCouponIssueRef.cid ? "selected" : ""} >${tCoupon.cname}</option>
								</c:forEach>
								</select>
								<span	class="input-group-addon">发行量：</span> 
								<input  type="text"	name="tCouponIssueRefs[${status.index}].issueVolume"  class="form-control form-date issueVolume" value="${tCouponIssueRef.issueVolume}" readonly="readonly"/>
								<!-- <span	class="input-group-addon"><i class="icon icon-plus"	style="cursor:pointer"></i></span>
								<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer"></i>
								</span> -->
								
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
	
				<div class="form-group">
					<label class="col-md-2 control-label">限制条件：</label>
					<div class="col-md-5">
						<label>每人每天最多摇&nbsp;:</label>
						<input type="text" name="maxTimes" value="${tCouponIssue.maxTimes}"><label>&nbsp;次；每人限领:&nbsp;</label><input type="text" name="maximum" value="${tCouponIssue.maximum}"><label>&nbsp;张</label>
					</div>
				</div>
				
<%-- 				<div class="form-group">
					<label class="col-md-2 control-label">是否启动：</label>
					<div class="col-md-5">
						</label><input type="radio" name="status" value="1" ${tCouponIssue.status==1?"checked":""} checked="checked">&nbsp;<label for="denomination1">启动</lable>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label><input type="radio" name="status" value="0" ${tCouponIssue.status==0?"checked":""} >&nbsp;<label for="denomination2">停止</lable>
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-md-2 control-label">备注：</label>
					<div class="col-md-5">
						<textarea  name="remark" class="form-control" >${tCouponIssue.remark}</textarea>
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
		<div class="hidden dateTemp">
		<div class="input-group">
			<span class="input-group-addon form-date">选择优惠券：</span>
			<select  name="tCouponIssueRefs[index].cid" class="form-control cid">
			<option value="">--请选择--</option>
			<c:forEach items="${tCoupons}"	var="tCoupon">
			<option value="${tCoupon.cid}">${tCoupon.cname}</option>
			</c:forEach>
			</select>
			<span class="input-group-addon">发行量：</span>
			<input type="text" name="tCouponIssueRefs[index].issueVolume" class="form-control form-date issueVolume" />
			<span	class="input-group-addon fix-border fix-padding"></span>
			<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer"></i></span>
			<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer"></i>
			</span>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script type="text/javascript">
	var dateCount = 0, cityCount = 0, dateSize = 10, citySize = 10
		$(function() {
			inserTitle(
					' >优惠券管理 > <span><a href="coupon/couponissue/init.jhtml.jhtml" target="right">优惠券发放管理</a> >摇一摇活动修改',
					'couponList', true)
		$("#dates").on(
					"click",
					".icon-plus",
					function() {
						if ($(this).parents(".col-md-8").find(".input-group")
								.size() < dateSize) {
							dateCount++;
							$(this).parents(".input-group").after(
									$(".dateTemp").html().replace(/index/g,
											dateCount));

						}
					});
		$("#dates").on(
					"click",
					".icon-minus",
					function() {
						if ($(this).parents(".col-md-8").find(".input-group")
								.size() > 1) {
							$(this).parents(".input-group").remove();
						} else {

						}
					});
		validate("editCouponForm", {
		     rules: {
		            hitRatio: {
                              required: true,
                              digits:true,
                              min:0,
                              max:100
                              },
                    maxTimes:{
                              required: true,
                              digits:true,
                              min:0,
                              max:99999
                              },
                    maximum:{
                              required: true,
                              digits:true,
                              min:0,
                              max:99999
                              }
		      },messages: {
		             hitRatio: {
                               required:"不能为空",
                               digits:"请输入正整数！",
                               min:"最小值为0",
                               max:"最大值为100"
                               },
                     maxTimes: {
                               required:"不能为空",
                               digits:"请输入正整数！",
                               min:"最小值为0",
                               max:"最大值为99999"
                               },
                    maximum: {
                               required:"不能为空",
                               digits:"请输入正整数！",
                               min:"最小值为0",
                               max:"最大值为99999"
                               }
		      }
		   }, formAjax)
		});
		function formAjax() {
		var checkRules = checkRule();
		if(!checkRules){
		    return checkRules;
		}
			var data = jsonFromt($('#editCouponForm').serializeArray());
			var url = "coupon/couponissue/yaoyiyao/update.jhtml?activityType=1";
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
		function checkRule(){
		var divObj = $("#dates");
		var flag=true;
		$.each(divObj.find(".cid"), function(i, innerObj) {
		 var innerElement = $(innerObj);
		   if(innerElement.val().length==0){
		   submitDataError(innerElement,"优惠券不能为空!");
		   flag=false;
		   }
		})
		$.each(divObj.find(".issueVolume"), function(i, innerObj) {
		 var innerElement = $(innerObj);
		   if(innerElement.val().length==0){
		   submitDataError(innerElement,"发行量不能为空!");
		   flag=false;
		   }
		   if(!(/^[0-9]{1,6}$/g.test(innerElement.val()))){
		   submitDataError(innerElement,"发行量为1到6位正整数!");
		   flag=false;
		   }
		})
		return  flag
		}
	</script>
</body>
</html>

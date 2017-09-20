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
<title>添加满就送活动</title>
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
	 <div><h5>&nbsp;&nbsp;添加满就送活动：</h5></div>
		<div class="panel-body">
			<form class="form-horizontal" id="editCouponForm">
			<input type="hidden" name="status" value="2">
			     <div class="form-group">
					<label class="col-md-2 control-label">活动名称：</label>
					<div class="col-md-5">
					     <input type="text" name="activityName" class="form-control" >
					</div>
				</div>
				<div class="form-group" id="dates">
					<label class="col-md-2 control-label">请选择：</label>
					<div>
						<div class="col-md-8">
							<div class="input-group">
							   <span class="input-group-addon">消费满：</span>
			                   <input type="text" name="tCouponIssueRefs[0].amount"  class="form-control form-date amount" />
								<span class="input-group-addon form-date">就送：</span> 
								<select  name="tCouponIssueRefs[0].cid" class="form-control cid">
								<option value="">--请选择--</option>
								<c:forEach items="${tCoupons}"	var="tCoupon">
								<option value="${tCoupon.cid}">${tCoupon.cname}</option>
								</c:forEach>
								</select>
								<span class="input-group-addon"></span>
			                    <input type="text" name="tCouponIssueRefs[0].num"  class="form-control form-date num" />
								<span	class="input-group-addon">张&nbsp;&nbsp;&nbsp;&nbsp;发行量：</span> 
								<input  type="text"	name="tCouponIssueRefs[0].issueVolume" id="" class="form-control form-date issueVolume" />
								<span	class="input-group-addon">张</span> 
								<span	class="input-group-addon"><i class="icon icon-plus"	style="cursor:pointer"></i></span>
								<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer"></i>
								</span>
							</div>
						</div>
					</div>
				</div>
				
	            <div class="form-group">
					<label class="col-md-2 control-label">满送频率：</label>
					<div class="col-md-5">
					            <select  name="rate" class="form-control">
								<option value="">--请选择--</option>
								<option value="1">--首单--</option>
								<option value="2">--首满--</option>
								<option value="3">--每次--</option>
								</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label">限制条件：</label>
					<div class="col-md-5">
						<label>每人每天最多送&nbsp;:</label>
						<input type="text" name="maxTimes" ><label>&nbsp;次；每人限领:&nbsp;</label><input type="text" name="maximum" ><label>&nbsp;张</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label">活动时间：</label>
					<div class="col-md-5">
						<input type="text" name ="dateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left" readonly="readonly">
						<label style="float: left;">&nbsp;--&nbsp;</label>
						<input type="text" name ="dateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42.6%;float:left" readonly="readonly">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label">备注：</label>
					<div class="col-md-5">
						<textarea  name="remark" class="form-control"></textarea>
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
		   <span class="input-group-addon">消费满：</span>
            <input type="text" name="tCouponIssueRefs[index].amount"  class="form-control form-date amount" />
			<span class="input-group-addon form-date">就送：</span> 
			<select  name="tCouponIssueRefs[index].cid" class="form-control cid">
			<option value="">--请选择--</option>
			<c:forEach items="${tCoupons}"	var="tCoupon">
			<option value="${tCoupon.cid}">${tCoupon.cname}</option>
			</c:forEach>
			</select>
			<span class="input-group-addon"></span>
            <input type="text" name="tCouponIssueRefs[index].num"  class="form-control form-date num" />
			<span	class="input-group-addon">张&nbsp;&nbsp;&nbsp;&nbsp;发行量：</span> 
			<input  type="text"	name="tCouponIssueRefs[index].issueVolume" id="" class="form-control form-date issueVolume" />
			<span	class="input-group-addon">张</span> 
			<span	class="input-group-addon"><i class="icon icon-plus"	style="cursor:pointer"></i></span>
			<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer"></i>
			</span>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script type="text/javascript">
	var dateCount = 0, cityCount = 0, dateSize = 10, citySize = 10
		$(function() {
			inserTitle(
					' >优惠券管理 > <span><a href="coupon/couponissue/init.jhtml.jhtml" target="right">优惠券发放管理</a> >添加满就送活动',
					'couponList', true);
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
	$(".form-datetime").datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minuteStep: 1,
		format : 'yyyy-mm-dd hh:ii:ss'
	  });
	  limitedDate({form:"#editCouponForm",startDateName:"dateStart",endDateName:"dateEnd",format : 'yyyy-mm-dd hh:ii:ss'});
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
                              },
                   dateStart:{
                              required: true
                              },
                    dateEnd:{
                              required: true
                              },
                       rate:{
                              required: true
                          },
               activityName:{
                              required: true,
                              minlength:3,
                              maxlength:60
                          }
		      },messages: {
		             hitRatio: {
                               required:"不能为空",
                               digits:"请输入整数！",
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
                               },
                    dateStart:{
                              required:"不能为空"
                              },
                    dateEnd:{
                              required:"不能为空"
                              },
                    rate:{
                              required:"不能为空"
                              },
                   activityName:{
                              required:"不能为空",
                              minlength:"长度不能小于3个字符",
                              maxlength:"长度不能大于60个字符"
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
			var url = "coupon/couponissue/mansong/add.jhtml?activityType=2";
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
		   return flag;
		   }
		   if(!(/^[0-9]{1,6}$/g.test(innerElement.val()))){
		   submitDataError(innerElement,"发行量为1到6位正整数!");
		   flag=false;
		   }
		})
		$.each(divObj.find(".amount"), function(i, innerObj) {
		 var innerElement = $(innerObj);
		   if(innerElement.val().length==0){
		   submitDataError(innerElement,"消费不能为空!");
		   flag=false;
		   return flag;
		   }
		   if(!(/^[0-9]{1,6}$/g.test(innerElement.val()))){
		   submitDataError(innerElement,"消费为1到6位正整数!");
		   flag=false;
		   }
		})
		$.each(divObj.find(".num"), function(i, innerObj) {
		 var innerElement = $(innerObj);
		   if(innerElement.val().length==0){
		   submitDataError(innerElement,"赠送量不能为空!");
		   flag=false;
		   return flag;
		   }
		   if(!(/^[0-9]{1,6}$/g.test(innerElement.val()))){
		   submitDataError(innerElement,"赠送量为1到6位正整数!");
		   flag=false;
		   }
		})
		return  flag
		}
	</script>
</body>
</html>

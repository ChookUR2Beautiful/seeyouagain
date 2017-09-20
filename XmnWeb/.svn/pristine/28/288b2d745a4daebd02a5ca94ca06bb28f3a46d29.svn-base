<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<title></title>
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
<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
	<div class="panel">
		<div class="panel-body">
			<form id="couponForm" role="form" class="form-horizontal">
			    <table style="width:100%;">
					<tbody>
						<tr>
						  <td style="width:5%;"><h5>优惠券编号:</h5></td>
						  <td style="width:25%;"><input type="text" class="form-control"  name="cid" value="${param.cid}" style = "width:80%;"></td>
						  <td style="width:5%;"><h5>面额：</h5></td>
						  <td style="width:25%;">
						   <input type="text" class="form-control"  name="denominationStart" value="${param.denominationStart}" style="width:39%;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
						  <input type="text" class="form-control"  name="denominationEnd"  value="${param.denominationEnd}" style="width:39%;float:left">
						  </td>
						  <td style="width:5%;"><h5>是否平台通用:</h5></td>
						  <td style="width:25%;">
						  <select name="showAll" class="form-control" style="width:80%;">
							<option value="">--请选择--</option>
							<option value="1"
								<c:if test="${!empty param.showAll}">${param.showAll==1?"selected":""}</c:if>>是</option>
							<option value="0"
								<c:if test="${!empty param.showAll}">${param.showAll==0?"selected":""}</c:if>>否</option>
						    </select>
						  </td>
						</tr>
						<tr>
						  <td style="width:5%;"><h5>优惠券名称：</h5></td>
						  <td style="width:25%;"><input type="text" class="form-control"  name="cname" value="${param.cname}" style = "width:80%;"></td>
						  <td style="width:5%;"><h5>开始日期：</h5></td>
						  <td style="width:25%;">
						  <input type="text" name ="startDateStart" value="${param.startDateStart}" placeholder="yyyy-MM-dd" class="form-control date-start" onFocus="" style="width:39%;float:left">
						   <label style="float: left;">&nbsp;--&nbsp;</label>
						  <input type="text" name ="startDateEnd" value="${param.startDateEnd}"  placeholder="yyyy-MM-dd" class="form-control date-end" onFocus="" style="width:39%;float:left">
						  </td>
						  <td style="width:5%;"><h5>优惠券类型：</h5></td>  
						  <td style="width:25%;">
						  	<select name="ctype" class="form-control" style="width:80%;">
							<option value="">--请选择--</option>
							<option value="0">消费优惠劵</option>
							<option value="1">商城优惠劵</option>
									<option value="5">平台通用优惠劵</option>
						    </select>
						  </td>
						</tr>
						<tr>
						  <td colspan="4"></td>
						  <td colspan="2" style="width:30%;">
						  	<div class="submit" style="text-align: left;">
							<input class="submit radius-3" type="button" value="查询全部" data-bus='query' style="width:39%;"/> 
							<input type="reset" class="reset radius-3" value="重置全部" data-bus='reset' style="width:40%;"/>
						    </div>
						  </td>
						</tr>
						</tbody>
					</table>
			
			
			
			
			
			
			
			
			
				<%-- <div class="form-group">
					
					<label class="col-md-1 control-label">优惠券编号：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="cid"
							value="${param.cid}" />
					</div>
					
					<label class="col-md-1 control-label">面额：</label>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" class="form-control" name="denominationStart"
								value="${param.denominationStart}"> <span
								class="input-group-addon fix-border">--</span> <input
								type="text" class="form-control" name="denominationEnd"
								value="${param.denominationEnd}">
						</div>
					</div>
					
					<label class="col-md-1 control-label">是否平台通用：</label>
					<div class="col-md-3" style="width: 21%">
						<select name="showAll" class="form-control">
							<option value="">--请选择--</option>
							<option value="1"
								<c:if test="${!empty param.showAll}">${param.showAll==1?"selected":""}</c:if>>是</option>
							<option value="0"
								<c:if test="${!empty param.showAll}">${param.showAll==0?"selected":""}</c:if>>否</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-1 control-label">优惠券名称：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="cname"
							value="${param.cname}" />
					</div>
					
					<label class="col-md-1 control-label">开始日期：</label>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" class="form-control form-date"
								name="startDateStart" value="${param.startDateStart}"> <span
								class="input-group-addon fix-border">--</span> <input
								type="text" class="form-control form-date" name="startDateEnd"
								value="${param.startDateEnd}">
						</div>
					</div>
					<div class="form-group">
						<div class="submit submit-sp" style="float: left; margin-left: 45px;">
							<input class="submit radius-3" type="button" value="查询全部" data-bus='query' /> 
							<input type="reset" class="reset radius-3" value="重置全部" data-bus='reset' />
						</div>
					</div>
				</div> --%>
				
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>

	<div class="panel">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<!-- 添加优惠券按钮 -->
				<c:if test="${ btnAu['coupon/generate/add']}">
					<a type="button" id="addCoupnBto" class="btn btn-success"
						href="coupon/generate/add/init.jhtml?isType=add"><span
						class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				
			</div>
			<div id="couponList"></div>
		</div>
	</div>
<script type="text/javascript">
	contextPath = '${pageContext.request.contextPath}'
</script>
<script type="text/json" id="permissions">{list:'${ btnAu['coupon/generate/init/list']}',update:'${ btnAu['coupon/generate/update']}',viewSellers:'${ btnAu['coupon/generate/viewSellers/init']}'}</script>
<jsp:include page="../../common.jsp"></jsp:include>
<script src="<%=path%>/resources/page/page.js"></script>
<script src="<%=path%>/ux/js/grid.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript">
var couponList;
$(function() {
	// 区域
	/* var ld = $("#ld").areaLd({
		isChosen : true,
		showConfig : [ {
			name : "province",
			tipTitle : "--省--"
		}, {
			name : "city",
			tipTitle : "--市--"
		} ]
	}); */
	$("input[data-bus=reset]").click(function() {
		//清楚Select的option的select属性
		if (location.href.indexOf("?") > 0) {
			var url = contextPath + '/coupon/generate/init.jhtml';
			location.href = url;
		}
		//只要重置按钮一按下，就立即执行清空chosen的内容
		setTimeout(function() {
			$("#ld").find("select").trigger("chosen:updated");
		}, 0);
	});
	$(".form-date").datetimepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		minView : "month",
	});
	inserTitle(
			' >优惠券管理 > <span><a href="coupon/generate/init.jhtml" target="right">优惠券生成</a>',
			'couponList', true);
	couponList = $('#couponList').page({
		url : 'coupon/generate/init/list.jhtml',
		success : function(data, obj) {
			obj.find('div').eq(0).html($.renderGridView({
				title : "优惠券列表",
				totalTitle : "优惠券",
				form : "couponForm",
				backButton : true,
				addBtn : "addCoupnBto",
				handleColumn : {
					title : "操作",
					name : "cid",
					handlePermissions : [ "update" ],//需要用到checkbox
					queryPermissions : [ "update" ],//不需要用到checkbox
					column : [ {
						title : "修改",
						href : "coupon/generate/update/init.jhtml",
						param : [ "cid","ctype" ],
						permissions : "update"
					} ]
				},
				columns : [
						{
							title : "优惠券编号",
							name : "cid"
						},
						{
							title : "优惠券名称",
							name : "cname"
						},
						{
							title : "优惠券商家",
							name : "countOfSeller",
							isA : true,
							a : {
								href : "coupon/generate/viewSellers/init.jhtml",
								param : [ "cid" ]
							},
							permissions : "viewSellers",
							customMethod : function(
									value,
									data) {
								return data.countOfSeller != 0 ? value
										: "-";
							}
						},
						{
							title : "面额",
							name : "denomination"
						},
						{
							title : "开始日期",
							name : "startDate"
						},
						{
							title : "结束日期",
							name : "endDate"
						},
						{
							title : "是否平台通用",
							name : "citysText",
							customMethod : function(
									value,
									data) {
								return data.showAll == 1 ? "是"
										: "否";
							},
						},
						{
							title : "是否全部商家",
							name : "citysText",
							customMethod : function(
									value,
									data) {
								return data.isAllSeller == undefined ? "-" :(data.isAllSeller==1? "是": "否");
							},
						},
						{
							title : "区域",
							name : "cid",
							
							customMethod : function(value,data) {
							var cid = data.cid;
							var ctype = data.ctype;
							value = "<a href='javascript:viod(); ' data-type='ajax' data-position='250px' data-width='40%' data-url='coupon/statistics/coupon/viewArea/viewAreaInit.jhtml?cid="
										+ cid
										+ "&ctype="+ ctype + "'  data-toggle='modal' >查看</a>";
								return data.showAll == 1 ? "全国": value;
							},
						},
						{
							title : "使用条件",
							name : "condition",
							customMethod : function(
									value,
									data) {
								return (data.condition == 0 ? "无条件使用"
										: "满【"
												+ value
												+ "】元使用");
							}
						},
						{
							title : "每次可同时使用张数",
							name : "useNum"
						} ,
						{
							title : "领取有效天数",
							name : "dayNum"
						},
						 {
							title : "优惠券类型",
							name : "ctypeStr"
						}
						],
				permissions : permissions
			}, data));
		},
		pageBtnNum : 10,
		paramForm : 'couponForm'
	});	
  //初始化时间
	var date = {
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		minView : "month",
	}, time = {
		format : "hh:ii",
		autoclose : true,
		// todayBtn : true,
		minView : "hour",
		maxView : "hour",
		startView : "day",
		minuteStep : 1,
		initialDate : new Date("1899-12-31")
	};
	$(".date-start").each(
			function(index, element) {
				// 初始化结束时间
				$(element).siblings(".date-end").datetimepicker($.extend({
					startDate : new Date()
				}, date)).on(
						"changeDate",
						function() {
							$(element).datetimepicker("setEndDate",
									$(element).siblings(".date-end").val());
						});

				// 初始化开始时间
				$(element).datetimepicker($.extend({
					startDate : new Date()
				}, date)).on(
						"changeDate",
						function() {
							$(element).siblings(".date-end").datetimepicker(
									"setStartDate", $(element).val());
						});

			});
	$(".time-start").each(
			function(index, element) {
				$(element).siblings(".time-end").datetimepicker(
						$.extend({}, time)).on(
						"changeDate",
						function() {
							$(element).datetimepicker("setEndDate",
									$(element).siblings(".time-end").val());
						});

				$(element).datetimepicker($.extend({}, time)).on(
						"changeDate",
						function() {
							$(element).siblings(".time-end").datetimepicker(
									"setStartDate", $(element).val());
						});
			});
	
});
</script>
</body>
</html>
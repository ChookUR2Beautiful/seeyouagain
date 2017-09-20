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
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
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
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-block">
				<form id="couponSellersForm">
					<input type="hidden" name="cid" value="${cid}" />
					<table class="table table-condensed table-borderless ">
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:400px !important;"><input type="text"
								class="form-control" name="sellername"
								value="${param.sellername}" style="width:90%"></td>

							<td style="width:100px;"><h5>&nbsp;&nbsp;商家等级：</h5></td>
							<td style="width:400px;"><select class="form-control"
								tabindex="2" name="sellerGrade" style="width:90%;">
									<option value="">--请选择--</option>
									<option value="1"
										<c:if test="${!empty param.sellerGrade}">${param.sellerGrade==1?"selected":""}</c:if>>A&nbsp;&nbsp;级</option>
									<option value="2"
										<c:if test="${!empty param.sellerGrade}">${param.sellerGrade==2?"selected":""}</c:if>>B+级</option>
									<option value="3"
										<c:if test="${!empty param.sellerGrade}">${param.sellerGrade==3?"selected":""}</c:if>>B&nbsp;&nbsp;级</option>
									<option value="4"
										<c:if test="${!empty param.sellerGrade}">${param.sellerGrade==4?"selected":""}</c:if>>C+级</option>
									<option value="5"
										<c:if test="${!empty param.sellerGrade}">${param.sellerGrade==5?"selected":""}</c:if>>C&nbsp;&nbsp;级</option>
								</select>
							</td>
								
							<td style="width:100px;"><h5>&nbsp;&nbsp;区域查询：</h5></td>
							<td style="width:400px !important;">
								<div class="input-group" id="ld"
									<c:choose>
									    <c:when test="${!empty param.area}">
									      initValue=" ${param.area}"
									    </c:when>
									    <c:when test="${empty param.area && !empty param.city}">
									    	initValue=" ${param.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${param.province}"
									    </c:otherwise>
									 </c:choose>
									style="width:91%"></div>
							</td>
						</tr>
						
						<tr>
							<td><h5>&nbsp;&nbsp;商家编号:</h5></td>
							<td><input type="text" class="form-control" name="sellerid"
								value="${param.sellerid}" style="width:90%"></td>	
							
							<td style="width:100px;"><h5>&nbsp;&nbsp;商家手机号:</h5></td>
							<td style="width:400px !important;"><input type="text"
								class="form-control" name="phoneid" value="${param.phoneid}"
								style="width:90%"></td>
								
							<td><h5>&nbsp;&nbsp;行业查询：</h5></td>
							<td>
								<div class="input-group" id="tradeSelect" style="width:91%;"
									<c:choose>
									    <c:when test="${!empty param.genre}">
									      initValue="${param.genre}"
									    </c:when>
									    <c:otherwise>  
									    	initValue="${param.category}"
									    </c:otherwise>
									 </c:choose>></div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="submit" style="margin-right: 25px;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' style="width: 14.2%"/> <input class="reset radius-3"
										type="reset" value="重置全部" data-bus='reset' style="width: 14.2%"/>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-block">
				<div class="panel-body data">
					<div id="couponSellersList"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{list:'${ btnAu['coupon/generate/init/list']}',update:'${ btnAu['coupon/generate/update']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script type="text/javascript">
		var couponSellersList;
		$(function() {
			inserTitle(
					' >优惠券管理 > <span><a href="coupon/generate/init.jhtml" target="right">优惠券生成</a> > 查看优惠券商家',
					'couponList', true);
			//行业类别
			$("#tradeSelect").tradeLd({
				isChosen : true,
				showConfig : [ {
					name : "category",
					tipTitle : "请选择",
					width : '48%'
				}, {
					name : "genre",
					tipTitle : "请选择",
					width : '49%'
				} ]
			});
			var ld = $("#ld").areaLd({
				isChosen : true
			});
			
			$("input[data-bus=reset]").click(function(){
				setTimeout(function(){
					$("#ld").find("select").trigger("chosen:updated");
					$("#tradeSelect").find("select").trigger("chosen:updated");
				}, 0);
			});

			couponSellersList = $("#couponSellersList").page({
				url : "coupon/generate/viewSellers/list.jhtml",
				success : function(data, obj) {
					obj.find('div').eq(0).html($.renderGridView({
						title : "优惠券商家信息",
						totalTitle : "商家",
						form : "couponSellersForm",
						columns : [ {
							title : "商家编号",
							name : "sellerid"
						}, {
							title : "商家名称",
							name : "sellername"
						}, {
							title : "商家地址",
							name : "address"
						}, {
							title : "商家等级",
							name : "sellerGradeStr"
						}, {
							title : "所属行业",
							name : "tradename"
						}, {
							title : "商家手机号",
							name : "phoneid"
						}, {
							title : "区域",
							name : "title"
						}, {
							title : "商圈",
							name : "btitle"
						}, {
							title : "上线状态",
							name : "isonlineText"
						}, {
							title : "上下线时间",
							name : "dateOperate"
						} ]
					}, data));
				},
				pageBtnNum : 10,
				paramForm : 'couponSellersForm'
			});
		});
	</script>
</body>

</html>
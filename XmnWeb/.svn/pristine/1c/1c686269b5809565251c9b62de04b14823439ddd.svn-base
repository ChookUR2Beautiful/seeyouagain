<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>直播粉丝券</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
	
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:8%;"><h5>&nbsp;&nbsp;粉丝券名称：</h5></td>
								<td style="width:22%;">
									<input type="text" class="form-control" name="cname" value="" style = "width:85%;"/>
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;粉丝券状态：</h5></td>
								<td style="width:22%;">
									<select class="form-control"  name="status" style="width:85%;">
											<option value="">--请选择--</option>
											<option value="1">出售中</option>
											<option value="2" >下架</option>
											<option value="3" >已售罄</option>
									</select> 
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;销售时间：</h5></td>
								<td style="width:30%;">
									<input type="text"  name="saleStartTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:26%;float:left">
									<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
									<input type="text"  name="saleEndDateTime" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:26%;float:left">
								</td>
							</tr>
							<tr>
								<td style="width:8%;"><h5>&nbsp;&nbsp;使用时间：</h5></td>							
								<td style="width:22%;">
									<input type="text"  name="startDate" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:40%;float:left">
									<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
									<input type="text"  name="endDate" value="" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:40%;float:left">
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;</h5></td>
								<td style="width:22%;"></td>	
								<td colspan="2" style="width:38%;">
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${null!=btnAu['liveCoupon/manage/add'] && btnAu['liveCoupon/manage/add']}">
					<a type="button" class="btn btn-success"  href="liveCoupon/manage/add/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${!empty btnAu['liveCoupon/manage/update'] && btnAu['liveCoupon/manage/update']}">
						<button type="button" class="btn btn-info" id="putaway" ><span class="icon-hand-up"></span>&nbsp;上架</button>
				</c:if>
				<c:if test="${!empty btnAu['liveCoupon/manage/update'] && btnAu['liveCoupon/manage/update']}">
					<button type="button" class="btn btn-info" id="removeOffshelf" ><span class="icon-hand-down"></span>&nbsp;下架</button>
				</c:if>
				<c:if test="${!empty btnAu['liveCoupon/manage/update'] && btnAu['liveCoupon/manage/update']}">
					<button type="button" class="btn btn-warning" id="setRecommend" ><span class="icon-hand-up"></span>&nbsp;设为推荐</button>
				</c:if>
				<c:if test="${!empty btnAu['liveCoupon/manage/update'] && btnAu['liveCoupon/manage/update']}">
					<button type="button" class="btn btn-warning" id="cancelRecommend" ><span class="icon-hand-down"></span>&nbsp;取消推荐</button>
				</c:if>
			</div>
			<div id="couponList"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">
		{
	       add:'${ btnAu['liveCoupon/manage/add']}',
	       delete_:'${ btnAu['liveCoupon/manage/delete']}',
	       update:'${ btnAu['liveCoupon/manage/update']}'
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/couponManage.js"></script>
</body>
</html>

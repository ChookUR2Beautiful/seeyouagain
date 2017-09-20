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
<title>礼物</title>
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
	
	<div class="tab-content">
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="searchForm">
						<div class="form-group">
							<label class="col-md-1 control-label" >礼物名称：</label>
							<div class="col-md-3" style="width: 15%;">
								<input type="text" class="form-control" name="giftName"
									value="" />
							</div>
							<!-- 1、普通礼物，2、商品礼物，3、套餐礼物 -->
							<label class="col-md-1 control-label" >类型：</label>
							<div class="col-md-3" style="width: 15%;">
								<select class="form-control" name="giftKind">
									<option value="">--请选择--</option>
									<option value="1">普通礼物</option>
									<option value="2">商品礼物</option>
									<option value="3">套餐礼物</option>
								</select>
							</div>
							<div class="submit">
								<input class="submit radius-3" type="button" value="查询全部"
									data-bus='query' /> 
								<input type="reset" class="reset radius-3"
									value="重置全部" data-bus='reset' />
							</div>
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
							test="${null!=btnAu['liveGift/manage/add/init'] && btnAu['liveGift/manage/add/init']}">
							<a type="button" class="btn btn-success"  href="liveGift/manage/add/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加</a>
						</c:if>
					</div>
					<div id="giftList"></div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/json" id="permissions">
		{
	       update:'${ btnAu['liveGift/manage/update']}',
	       setStatus:'${ btnAu['liveGift/manage/setStatus']}',
	       updateGiftBagSet:'${ btnAu['liveGift/manage/updateGiftBagSet']}',
		   deleteGiftBagSet:'${ btnAu['liveGift/manage/deleteGiftBagSet']}'
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/giftManage.js?v=1.0.4"></script>
</body>
</html>

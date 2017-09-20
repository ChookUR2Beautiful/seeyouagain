<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>V客充值奖励管理列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
.submit{text-align: left;}
</style>

</head>
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchFromId">
				<table style="width: 100%;">
					<tbody>
						<tr>
							<td style="width: 6%;"><h5>&nbsp;&nbsp;用户编号:</h5></td>
							<td style="width: 10% !important;"><input type="text"
								class="form-control" value="" name="uid" style="width: 70%"></td>
								
							<td style="width: 6%;"><h5>&nbsp;&nbsp;手机号码:</h5></td>
							<td style="width: 10% !important;"><input type="text"
								class="form-control" value="" name="phone" style="width: 70%"></td>
								
							<td style="width: 6%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width: 15% !important;"><input type="text"
								class="form-control" value="" name="nickname" style="width: 80%"></td>								
						</tr>
						<tr>
							<td style="width: 6%;"><h5>&nbsp;&nbsp;会员等级：</h5></td>
							<td style="width: 20%;"><select class="form-control" name="ledgerLevel" id="ledgerLevel" style="width: 60%;"> 
							</select></td>
							<td></td><td></td><td></td>						
							<td colspan="2" style="text-align: right;">
								<div class="submit">
									<input class="submit radius-3" type="button" value="查询全部" data-bus='query' style="width: 43%;" /> 
									<input class="reset radius-3" type="reset" value="重置全部" data-bus='reset' style="width: 43%;"/>
								</div>
							</td>
						</tr>

					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	
	<!-- 累计充值总额 begin -->
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info" >
				<%-- <caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;统计总数</caption> --%>
				<thead>
		 			<tr> 
					 <th style="width:130px;">V客总数</th> 
					 <th style="width:130px;">一星V客数</th> 
					 <th style="width:130px;">二星V客数</th> 
					 <th style="width:130px;">三星V客数</th> 
					 <th style="width:130px;">五星V客数</th> 
					 <th style="width:130px;">累计推荐主播</th>  
					 <th style="width:130px;">获得主播收益</th>  
					 <th style="width:130px;">累计签约商户</th>  
					 <th style="width:130px;">获得商户流水</th>  
		 			</tr> 
		 		</thead> 
		 		<tbody id="vkeProfitTotal">
			</tbody>
			</table> 

		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['recommendMember/manage/update']}">
						<!-- <button type="button" id="addRealtion" class="btn btn-success" data-position="100" data-toggle="modal" data-target="#addRelationModal"> -->
						<a type="button" style="float: left" id="addAdvancebtn" class="btn btn-success" data-position="100px"  data-width="auto" href="javascript:void(0);" onclick="editVkeRelationship()" >
						<span class="icon-plus"></span>&nbsp;添加关系</button></a>
				</c:if>
				<c:if test="${btnAu['recommendMember/manage/export']}">
						<button type="button" id="exportVkeMember" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="rechargeRewardInfoList"></div>
		</div>
	</div>

	
	<!-- modal 窗口部分开始 -->
	<div class="modal fade" id="recommendLiveListModal">
		<div class="modal-dialog" style="width: 820px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">推荐主播</h4>
				</div>
				<!-- style="height: 220px;" -->
				<div class="modal-body example" >
					<table class="table table-bordered" class="propertyTable" >
						<thead>
							<tr class="text-center">
								<th class="text-center"><div class="header">主播名称</div></th>
								<th class="text-center"><div class="header">主播手机</div></th>
								<th class="text-center"><div class="header">主播累计礼物(鸟蛋)</div></th>
								<th class="text-center"><div class="header">获得主播收益</div></th>
								<th class="text-center"><div class="header">操作</div></th>
							</tr>
						</thead>
						<tbody id="liveListInfoTB">
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	
	<div class="modal fade" id="recommendSellerListModal">
		<div class="modal-dialog" style="width: 820px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">推荐商户</h4>
				</div>
				<!-- style="height: 220px;" -->
				<div class="modal-body example" >
					<table class="table table-bordered" class="propertyTable">
						<thead>
							<tr class="text-center">
								<th class="text-center"><div class="header">商户名称</div></th>
								<th class="text-center"><div class="header">负责人</div></th>
								<th class="text-center"><div class="header">商户累计流水</div></th>
								<th class="text-center"><div class="header">获得商户收益</div></th>
								<th class="text-center"><div class="header">操作</div></th>
							</tr>
						</thead>
						<tbody id="sellerListInfoTB">
						
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	
	<!-- 添加关系 -->
	<div class="modal fade" id="addRelationModal">
		<div class="modal-dialog" style="width: 620px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">添加关系</h4>
				</div>
				<!-- style="height: 220px;" -->
				<div class="modal-body example" >
					<form class="form-horizontal" id="freshmanRecommendForm">
						<input type="hidden" name="rtype" value="" />
						<div class="form-group">
						    <label class="col-md-4 control-label">类型： <span
								style="color: red;">*</span></label>
							<div class="col-sm-8">
								<input name="dealType" value=1 type="radio" ><span
									style="font-size: 12px;">绑定主播</span> &nbsp;&nbsp;
								<input name="dealType" value=2 type="radio" ><span
									style="font-size: 12px;">绑定商户</span>
							</div>
						</div>	
						<div class="form-group">
							<label class="col-md-4 control-label">绑定V客： <span
								style="color: red;">*</span></label>
							<div class="col-sm-8" id="vkeIdSelect">
								<select class="form-control" id="vkeId"
									style="width: 70%;"></select> 
							</div>
						</div>
						<div class="form-group" id="liveRecordTagDiv">
							<label class="col-md-4 control-label">V客签约名额： </label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="limitNumber" id="limitNumber"
									style="width: 100px;" readonly="readonly">
							</div>
						</div>
							
						<!-- 绑定主播-->
						<div id ="liveDiv" style="display:block;" >
							<div class="form-group">
								<label class="col-md-4 control-label">绑定主播：<span
								style="color: red;">*</span></label>
								<div class="col-lg-8 col-xs-8" id="liveIdSelect">
									<select class="form-control" id="liveId" style="width: 70%;">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">主播推荐人：&nbsp;&nbsp;</label>
								<div class="col-lg-8 col-xs-8">
									<input type="text" class="form-control" name="vkeNameForLive"
										placeholder="无推荐人" style="width: 70%; float: left"
										readonly="readonly">
								</div>
							</div>
						</div>
						<!-- 绑定商户 -->
					    <div id ="sellerDiv" style="display:none;" >
							<div class="form-group">
								<label class="col-md-4 control-label">签约商户：<span
								style="color: red;">*</span></label>
								<div class="col-lg-8 col-xs-8"  id="sellerIdSelect">
									<select class="form-control" id="sellerId" style="width: 70%;">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">签约人：&nbsp;&nbsp;</label>
								<div class="col-lg-8 col-xs-8">
									<input type="text" class="form-control" name="vkeNameForSeller"
										placeholder="无推荐人" style="width: 70%; float: left"
										readonly="readonly">
								</div>
							</div>
						</div>
					
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								id="addRelationSubmit">绑定</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					 </form>
				</div>
			</div>
		</div>
	</div>	
	
	<script type="text/json" id="permissions">
		{xg:'${ btnAu['recommendMember/manage/update'] }'}
    </script>
    
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/reward_dividends/recommendMemberManage.js?v=1.0.5"></script>
	
</body>
</html>

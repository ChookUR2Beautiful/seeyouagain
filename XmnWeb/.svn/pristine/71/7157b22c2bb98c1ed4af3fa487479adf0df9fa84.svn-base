<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>编辑粉丝级别信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form id="editForm">
						<c:if test="${!empty fansRank}">
							<input type="hidden" name="id" id="id" value="${fansRank.id}">
						</c:if>
						<table class="table" style="text-align: center;width:50%">
							<tr>
								<td class="sellerTitleCss">
									<h5>级别类型:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input name="rankType" value="1" type="radio" ${fansRank.rankType==1?"checked":""} ><span style="font-size: 12px;">壕赚等级</span>
									<input name="rankType" value="2" type="radio" ${fansRank.rankType==2?"checked":""} ><span style="font-size: 12px;">V客等级</span>
								</td>
							</tr>
						
							<tr id="parentIdTr">
								<td class="sellerTitleCss">
									<h5>上一级别:<span style="color:red;">*</span></h5></td>
								<td >
									<select class="form-control" name="parentId" id="parentId" initValue="${fansRank.parentId }">
										<%-- <option value="0" ${fansRank.parentId==0?"selected":"" }>--顶级--</option>
										<option value="1" ${fansRank.parentId==1?"selected":"" }>普通粉丝</option>
										<option value="2" ${fansRank.parentId==2?"selected":"" }>VIP粉丝</option>
										<option value="3" ${fansRank.parentId==3?"selected":"" }>钻石粉丝</option> --%>
									</select>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>级别名称:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input type="text" class="form-control" id="rankName" name="rankName"  value="${fansRank.rankName}" placeholder="请输入级别名称">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>升级条件:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
								        <input type="text" class="form-control" name="rewardLowest" value="${fansRank.rewardLowest}" placeholder="最低充值金额">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								        <input type="text" class="form-control" name="rewardHighest" value="${fansRank.rewardHighest}" placeholder="最高充值金额">
							        </div>
								</td>
							</tr>
							
							<%-- <tr>
								<td class="sellerTitleCss">
									<h5>直属推荐人:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="referrerRatio" placeholder="充值分佣百分比，如：10"
									value="${fansRank.referrerRatio}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>直属推荐人上级:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="parentReferrerRatio" placeholder="充值分佣百分比，如：5"
									value="${fansRank.parentReferrerRatio}">
								</td>
							</tr>
							
							
							<tr >
								<td class="sellerTitleCss">
									<h5>推荐奖励倍数:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="referrerReward" placeholder="请输入整数，如：6"
									value="${fansRank.referrerReward}">
								</td>
							</tr>
							
							<tr style="display:none;">
								<td class="sellerTitleCss">
									<h5>鸟币消费抵消:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="consumeRatio" placeholder="鸟币消费抵消百分比，如：30"
									value="30">
								</td>
							</tr>
							
							
							<tr>
								<td class="sellerTitleCss">
									<h5>红包返还比例:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
								        <input type="text" class="form-control" name="redPacketLowest" value="${fansRank.redPacketLowest}" placeholder="红包返还最低比例">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								        <input type="text" class="form-control" name="redPacketMidLower" value="${fansRank.redPacketMidLower}" placeholder="红包金额中低占比">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								        <input type="text" class="form-control" name="redPacketMidHigher" value="${fansRank.redPacketMidHigher}" placeholder="红包金额中高占比">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								        <input type="text" class="form-control" name="redPacketHighest" value="${fansRank.redPacketHighest}" placeholder="红包返还最高比例">
							        </div>
								</td>
							</tr> --%>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>级别图片:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="form-group">
										<div class="col-md-7">
											<input type="hidden" class="form-control" id="picUrl" name="picUrl"  value="${fansRank.picUrl}" >
														<div id="picUrlImg"></div>
										</div>
									</div>
								</td>
							</tr>
							
							
							
						</table>
						<div align="center">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button"
								onclick="window.history.back();">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/live_anchor/fansRankEdit.js"></script>
	
</body>
</html>

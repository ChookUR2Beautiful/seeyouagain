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
					<input type="hidden" name="fansRankDetailEditToken" value="${fansRankDetailEditToken }">
						<c:if test="${!empty fansRankDetail}">
							<input type="hidden" name="id" id="id" value="${fansRankDetail.id}">
						</c:if>
						<table class="table" style="text-align: center;width:80%" id="baseTable">
							<tr>
								<td class="sellerTitleCss">
									<h5>会员类型:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<select class="form-control" name="objectOriented" id="objectOriented" >
										<option value="1" ${fansRankDetail.objectOriented==1?"selected":"" }>VIP客户</option>
										<option value="2" ${fansRankDetail.objectOriented==2?"selected":"" }>商户会员</option>
										<option value="3" ${fansRankDetail.objectOriented==3?"selected":"" }>直销客户</option>
										<option value="4" ${fansRankDetail.objectOriented==4?"selected":"" }>营业厅会员</option>
									</select>
								</td>
							</tr>
							
							<tr id="parentIdTr">
								<td class="sellerTitleCss">
									<h5>关联等级:<span style="color:red;">*</span></h5></td>
								<td >
									<select class="form-control" name="rankId" id="rankId" initValue="${fansRankDetail.rankId }">
									</select>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>生效日期:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control form_datetime" id="effectiveDate" name="effectiveDate" placeholder="生效日期，如：2017-01-01" readonly="readonly"
									value="${fansRankDetail.effectiveDateStr}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>推荐奖励:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input name="referrerLedgerType" value="1" type="radio" ${fansRankDetail.referrerLedgerType==1?"checked":""} ><span style="font-size: 12px;">余额</span>
									<input name="referrerLedgerType" value="0" type="radio" ${fansRankDetail.referrerLedgerType==0?"checked":""} ><span style="font-size: 12px;">鸟币</span>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>直属推荐人:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="referrerRatio" placeholder="充值分佣百分比，如：10"
									value="${fansRankDetail.referrerRatio}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>直属推荐人上级:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="parentReferrerRatio" placeholder="充值分佣百分比，如：5"
									value="${fansRankDetail.parentReferrerRatio}">
								</td>
							</tr>
							
							
							<tr id="referrerRewardTr">
								<td class="sellerTitleCss">
									<h5>推荐奖励倍数:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" id="referrerReward" name="referrerReward" placeholder="请输入整数，如：6"
									value="${fansRankDetail.referrerReward}">
								</td>
							</tr>
							
							<tr id="conversionRateTr" style="display:none;">
								<td class="sellerTitleCss">
									<h5>兑换获得比例:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" id="conversionRate" name="conversionRate" placeholder="请输入百分比，如：110"
									value="${fansRankDetail.conversionRate}">
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
						</table>
						<hr>
						<h3>内购</h3>
						<table class="table" style="text-align: center;width:80%">
							<input type="hidden" name="">
							<tr>
								<td class="sellerTitleCss">
									<h5>红包返还比例:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
								        <input type="text" class="form-control" name="privateRedPacketCashRatio" value="${fansRankDetail.privateRedPacketCashRatio}" placeholder="余额红包比例">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								        <input type="text" class="form-control" name="privateRedPacketCoinRatio" value="${fansRankDetail.privateRedPacketCoinRatio}" placeholder="鸟币红包比例">
							        </div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<table class="table-bordered table-hover" style="text-align: center;width:100%;">
										<tr>
											<td class="sellerContentCss">
												打赏鸟豆区间
											</td>
											<td class="sellerContentCss">
												鸟币红包返还比例
											</td>
											<td class="sellerContentCss">
												余额红包返还比例
											</td>
										</tr>
										<c:if test="${empty fansRankDetail.privateRedPacketDetailList}">
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="privateRedPacketDetailList[0].dividendsRole" value="1">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[0].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[0].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[0].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[0].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[0].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[0].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="privateRedPacketDetailList[1].dividendsRole" value="1">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[1].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[1].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[1].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[1].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[1].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[1].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="privateRedPacketDetailList[2].dividendsRole" value="1">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[2].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[2].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[2].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[2].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[2].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[2].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="privateRedPacketDetailList[3].dividendsRole" value="1">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[3].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[3].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[3].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[3].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[3].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[3].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="privateRedPacketDetailList[4].dividendsRole" value="1">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[4].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[4].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[4].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[4].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[4].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[4].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										</c:if>
										
										<c:if test="${!empty fansRankDetail.privateRedPacketDetailList}">
										<c:forEach items="${fansRankDetail.privateRedPacketDetailList}" var="privateRedPacketDetail" varStatus="status">
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="privateRedPacketDetailList[${status.index }].dividendsRole" value="1">
													<input type="hidden" name="privateRedPacketDetailList[${status.index }].id" value="${privateRedPacketDetail.id }">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[${status.index }].consumeLimitLowest" value="${privateRedPacketDetail.consumeLimitLowest }" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[${status.index }].consumeLimitHighest" value="${privateRedPacketDetail.consumeLimitHighest }" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[${status.index }].coinLowest" value="${privateRedPacketDetail.coinLowest }" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[${status.index }].coinHighest" value="${privateRedPacketDetail.coinHighest }" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="privateRedPacketDetailList[${status.index }].cashLowest" value="${privateRedPacketDetail.cashLowest }" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="privateRedPacketDetailList[${status.index }].cashHighest" value="${privateRedPacketDetail.cashHighest }" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										</c:forEach>
										</c:if>
									</table>
								</td>
							</tr>
						</table>
						
						<hr>
						<h3>外购</h3>
						<table class="table" style="text-align: center;width:80%">
							<tr>
								<td class="sellerTitleCss">
									<h5>红包返还比例:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<div class="input-group">
								        <input type="text" class="form-control" name="publicRedPacketCashRatio" value="${fansRankDetail.publicRedPacketCashRatio}" placeholder="余额红包比例">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								        <input type="text" class="form-control" name="publicRedPacketCoinRatio" value="${fansRankDetail.publicRedPacketCoinRatio}" placeholder="鸟币红包比例">
							        </div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<table class="table-bordered table-hover" style="text-align: center;width:100%;">
										<tr>
											<td class="sellerContentCss">
												打赏鸟豆区间
											</td>
											<td class="sellerContentCss">
												鸟币红包返还比例
											</td>
											<td class="sellerContentCss">
												余额红包返还比例
											</td>
										</tr>
										<c:if test="${empty fansRankDetail.publicRedPacketDetailList}">
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="publicRedPacketDetailList[0].dividendsRole" value="2">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[0].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[0].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[0].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[0].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[0].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[0].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="publicRedPacketDetailList[1].dividendsRole" value="2">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[1].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[1].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[1].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[1].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[1].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[1].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="publicRedPacketDetailList[2].dividendsRole" value="2">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[2].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[2].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[2].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[2].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[2].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[2].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="publicRedPacketDetailList[3].dividendsRole" value="2">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[3].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[3].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[3].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[3].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[3].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[3].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="publicRedPacketDetailList[4].dividendsRole" value="2">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[4].consumeLimitLowest" value="" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[4].consumeLimitHighest" value="" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[4].coinLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[4].coinHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[4].cashLowest" value="" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[4].cashHighest" value="" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										</c:if>
										
										<c:if test="${!empty fansRankDetail.publicRedPacketDetailList}">
										<c:forEach items="${fansRankDetail.publicRedPacketDetailList}" var="publicRedPacketDetail" varStatus="status">
										<tr>
											<td>
												<div class="input-group">
												<!-- 分红角色 1内购 2外购 -->
													<input type="hidden" name="publicRedPacketDetailList[${status.index }].dividendsRole" value="2">
													<input type="hidden" name="publicRedPacketDetailList[${status.index }].id" value="${publicRedPacketDetail.id}">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[${status.index }].consumeLimitLowest" value="${publicRedPacketDetail.consumeLimitLowest }" placeholder="最低区间">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[${status.index }].consumeLimitHighest" value="${publicRedPacketDetail.consumeLimitHighest }" placeholder="最高区间">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[${status.index }].coinLowest" value="${publicRedPacketDetail.coinLowest }" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[${status.index }].coinHighest" value="${publicRedPacketDetail.coinHighest }" placeholder="最高比例">
										        </div>
											</td>
											<td>
												<div class="input-group">
											        <input type="text" class="form-control" name="publicRedPacketDetailList[${status.index }].cashLowest" value="${publicRedPacketDetail.cashLowest }" placeholder="最低比例">
											        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
											        <input type="text" class="form-control" name="publicRedPacketDetailList[${status.index }].cashHighest" value="${publicRedPacketDetail.cashHighest }" placeholder="最高比例">
										        </div>
											</td>
											
										</tr>
										</c:forEach>
										</c:if>
									</table>
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
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/live_anchor/fansRankDetailEdit.js"></script>
	
</body>
</html>

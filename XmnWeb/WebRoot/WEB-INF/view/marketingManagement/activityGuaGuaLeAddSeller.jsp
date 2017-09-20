<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<!-- loading -->
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="editAppPushForm">
						<input type="hidden" id="aid" name="aid" value="${activity.aid}">
						<table width="100%">
							<tbody>
								<tr>
									<td class="sellerTitleCss"><h5>&nbsp;&nbsp;活动名称:</h5></td>
									<td class="sellerContentCss">
										<div>${activity.aname}</div>
									</td>

								</tr>
								<tr>
									<td><h5>&nbsp;&nbsp;活动开始时间:</h5></td>
									<td>
										<div>${activity.startDate}</div>
									</td>
								</tr>
								<tr>
									<td><h5>&nbsp;&nbsp;活动结束时间:</h5></td>
									<td><div>${activity.endDate}</div></td>
								</tr>
								<tr>
									<td class="sellerTitleCss"><h5>&nbsp;&nbsp;设置方式:</h5></td>
									<td class="sellerContentCss">
										<div>
											<c:if test="${activity.setWay==0}">按数量设置</c:if>
											<c:if test="${activity.setWay==0}">按数量设置</c:if>
											<c:if test="${empty activity.setWay}">未设置</c:if>
										</div>
									</td>
								</tr>
								<tr>
									<td class="sellerTitleCss"><h5>&nbsp;&nbsp;中奖比例:</h5></td>
									<td class="sellerContentCss">
										<div>${activity.hitRatio}</div>
									</td>
								</tr>
								<tr>
									<td class="sellerTitleCss"><h5>&nbsp;&nbsp;奖项设置:</h5></td>
									<td class="sellerContentCss">
										<div>
											<c:if test="${empty activity.tActivityRule}">
												未设置
											</c:if>
											<c:if test="${!empty activity.tActivityRule}">
												<ol>
													<c:forEach items="${activity.tActivityRule}"
														var="activityRule">
														<li>${activityRule.awardName
															},金额从${activityRule.minMoeny }元到${activityRule.maxMoeny }元，占比${activityRule.giveMoney}
														</li>
													</c:forEach>
												</ol>
											</c:if>
										</div>
									</td>
								</tr>
								<tr>
									<td class="sellerTitleCss"><h5>&nbsp;&nbsp;活动描述:</h5></td>
									<td class="sellerContentCss">
										<div>${activity.eescription}</div>
									</td>
								</tr>
								<tr>
									<td class="sellerTitleCss" nowrap="nowrap"><h5>&nbsp;&nbsp;是否与其他抽奖返利活动互斥:</h5></td>
									<td class="sellerContentCss">
										<div>${activity.repelName}</div>
									</td>
								</tr>
								<tr>
									<td class="sendObjectTr" style="display:none;"><h5>&nbsp;&nbsp;选择参加活动商家:</h5></td>
									<td class="sendObjectTr" style="display:none;" colspan="3">
										<textarea id="object" class="form-control" rows="5" cols=""
											name="sellerids"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
						<div style="height:20px"></div>
						<div align="center">
							<button class="btn btn-danger" type="submit" id="ensure">
								<i class="icon-save"></i>&nbsp;发送
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
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/js/marketingmanagement/activityManagerSeller.js"></script>
	<script type="text/javascript">
		$(function() {
			inserTitle(
					' > 营销活动管理  > <a href="marketingManagement/activityManagement/manzeng/init.jhtml" target="right">活动管理</a> >添加刮刮乐活动商家',
					'activityManagement', true);
		})
	</script>
</body>
</html>

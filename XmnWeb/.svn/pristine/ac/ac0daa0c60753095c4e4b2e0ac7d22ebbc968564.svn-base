<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<title>返还配置</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">

<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<%-- <link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> --%>
</head>
<body style="overflow-x: auto; overflow-y: auto; min-width: 1024px; background: #EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="panel panel-info">
			<div class="panel-heading">返还配置</div>
			<div class="panel-body">
				<ul id="choice" class="nav nav-tabs">
					<li class="active"><a href="#choice1" data-toggle="tab">A模式</a></li>
					<li class=""><a href="#choice2" data-toggle="tab">B模式</a></li>
				</ul>
				<!-- ul开始 -->
				<div class="tab-content">
				    <input type="hidden" id="isType" name="isType" value="${isType}">
					<!-- A模式-->
					<div id="choice1" class="tab-pane in active">
						<div class="panel">
							<div class="panel-body">
								<form class="form-horizontal" role="form" id="excitationProjectAForm">
								    <input type="hidden" name="projectName" value="A">
									<c:if test="${!empty liveFansRankList && fn:length(liveFansRankList)>0}">
									    <c:set var="index" value="0" /> 
										<c:forEach var="list" items="${liveFansRankList}">
										    <input type="hidden" name="verExcitationProjectList[${index }].rankId" value="${list.id}">
										    <input type="hidden" name="verExcitationProjectList[${index }].projectName" value="A">
											<div class="form-group">
												<label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<span class="group-label"><h3>${list.rankName}</h3>
													</span>
												</div>
											</div>
											<!-- 公用开始 -->
											<div class="form-group " id="dates">
											    <label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<div class="input-group" id="cidDiv[${index}]">
													    <select name="tCoupon[${index }].ctype" class="form-control ctype" >
															<option value="">--优惠券类型--</option>
															<option value="0">消费优惠劵</option>
															<option value="1">商城优惠券</option>
															<option value="5">平台通用优惠劵</option>
														</select><span class="input-group-addon"></span> 
														<select name="tCoupon[${index }].cid" class="form-control coupon">
															<option value="">--请选择优惠券--</option>
														</select> 
														<span class="input-group-addon">数量:</span> 
														<input type="number" name="tCoupon[${index }].num" placeholder="数量" 
															class="form-control form-date" />
														<span class="input-group-addon "><i class="icon icon-plus" style="cursor: pointer" id = "${index }" >添加</i></span>
													</div>
												</div>
											</div>
											<div class="form-group" id="tableDates">
											    <label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<table class="table table-bordered"  class="propertyTable">
														<thead >
															<tr class="text-center">
																<th class="text-center"><div class="header">优惠劵</div></th>
																<th class="text-center"><div class="header">数量</div></th>
																<th class="text-center"><div class="header">操作</div></th>
															</tr>
														</thead>
														<tbody id="productInfoTB${index}" >
															<c:if test="${!empty excitationDetail && fn:length(excitationDetail)>0}">
																<c:forEach var="relation" items="${excitationDetail}">
																    <c:if test="${relation.projectName == 'A' && relation.rankId == list.id}">
																		<tr class="text-center" id="${relation.did}">
																			<td>${relation.cname }</td>
																			<td>${relation.num }</td>
																			<td data-row="0" data-index="0" data-flex="false"
																                data-type="string" style="width: 130px; height: 59px;"><a
																                href="javascript:;" onclick="deleteGroup(${relation.did})">删除</a></td>
																			<input type="hidden" name="couponinfo" value='${relation.productJson}'/>
																		</tr>
																	</c:if>
																</c:forEach>
															</c:if>

														</tbody>
													</table>
												</div>
											</div>
											<!-- 结束 -->
                                            <c:set var="index" value="${index+1}" /> 
                                            
                                            <hr/>
										</c:forEach>
									</c:if>
									
									<div align="center">
										<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
										<button class="btn btn-warning" type="button" id = "backButton"  onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;重置</button>
									</div>
								</form>
							</div>
						</div>
					
					</div>

					<!-- B模式-->
					<div id="choice2" class="tab-pane">
						<div class="panel">
							<div class="panel-body">
								<form class="form-horizontal" role="form" id="excitationProjectBForm">
								    <input type="hidden" name="projectName" value="B">
									<c:if test="${!empty liveFansRankList && fn:length(liveFansRankList)>0}">
									    <c:set var="zindex" value="0" /> 
										<c:forEach var="list" items="${liveFansRankList}">
										    <input type="hidden" name="verExcitationProjectList[${zindex }].rankId" value="${list.id}">
										    <input type="hidden" name="verExcitationProjectList[${zindex }].projectName" value="B">
											<div class="form-group">
												<label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<span class="group-label"><h3>${list.rankName}</h3>
													</span>
												</div>
											</div>
											<!-- 公用开始 -->
											<div class="form-group" id="dates">
												<label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<div class="input-group">
														<%-- <select name="tCouponTabB[${zindex }].cid" class="form-control cid">
															<option value="">--请选择优惠券--</option>
															<c:forEach items="${tCoupons}" var="tCoupon">
																<option value="${tCoupon.cid}">${tCoupon.cname}</option>
															</c:forEach>
														
														</select> --%> 
														
														<select name="tCouponTabB[${zindex }].ctype" class="form-control ctype" >
															<option value="">--优惠券类型--</option>
															<option value="0">消费优惠劵</option>
															<option value="1">商城优惠券</option>
															<option value="5">平台通用优惠劵</option>
														</select><span class="input-group-addon"></span> 
														<select name="tCouponTabB[${zindex }].cid" class="form-control cid">
														    <option value="">--请选择优惠券--</option>
														</select> 
														<span class="input-group-addon">数量:</span>
														<input type="number" name="tCouponTabB[${zindex }].num" 
															placeholder="数量" id="" class="form-control form-date issueVolume" /> 
														<span class="input-group-addon "><i class="icon icon-plus" style="cursor: pointer" id = "${zindex }" >添加</i></span>

													</div>
												</div>
											</div>
											<div class="form-group" id="tableDates">
											    <label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<table class="table table-bordered" data-page="${page}" data-total="${total}" class="propertyTable">
														<thead>
															<tr class="text-center">
																<th class="text-center"><div class="header">优惠劵</div></th>
																<th class="text-center"><div class="header">数量</div></th>
																<th class="text-center"><div class="header">操作</div></th>
															</tr>
														</thead>
														<tbody id="productInfoTabB${zindex }">
															<c:if test="${!empty excitationDetail && fn:length(excitationDetail)>0}">
																<c:forEach var="relation" items="${excitationDetail}">
																    <c:if test="${relation.projectName=='B' && relation.rankId==list.id && !empty relation.cname}">
																		<tr class="text-center" id="${relation.did}">
																			<td>${relation.cname }</td>
																			<td>${relation.num }</td>
																			<td data-row="0" data-index="0" data-flex="false"
																                data-type="string" style="width: 130px; height: 59px;"><a
																                href="javascript:;" onclick="deleteGroup(${relation.did})">删除</a></td>
																			<input type="hidden" name="couponinfo" value='${relation.productJson}'/>
																		</tr>
																	</c:if>
																</c:forEach>
															</c:if>

														</tbody>
													</table>
												</div>
											</div>
											<div class="form-group">
												<!-- <div class="col-sm-offset-3 col-sm-6"> -->
												<label class="col-md-3 control-label"></label>
												<div class="col-md-7">
													<div class="input-group">
														<span class="input-group-addon">红酒价格:</span>
                                                        <c:if test="${!empty excitationDetail && fn:length(excitationDetail)>0}">
                                                            <input type="text" class="form-control" dir='rtl' placeholder="v客充值奖励红酒价格" name="verExcitationProjectList[${zindex }].worth"
																<c:forEach var="relation" items="${excitationDetail}">
																    <%-- <c:choose>
																		<c:when test="${relation.projectName=='B' && relation.type==2 && relation.rankId==list.id}"> 
																		    value="${relation.worth}" 
																		</c:when>
																		<c:otherwise>
																		    value="0.00" 
																		</c:otherwise>
																	</c:choose> --%>	
																	<c:if test="${relation.projectName=='B' && relation.type==2 && relation.rankId==list.id}"> 	
																	      value="${relation.worth}" 
																	</c:if>			
																</c:forEach>	
															style="width: 15%; float: left">
														</c:if>		
														<c:if test="${empty excitationDetail}">
														      <input type="text" class="form-control" dir='rtl' placeholder="v客充值奖励红酒价格"
																	           name="verExcitationProjectList[${zindex }].worth" value="${relation.worth}" style="width: 15%; float: left">
		                                                </c:if>
													</div>
												</div>
											</div>
											<hr/>
											
											<!-- 结束 -->
											<c:set var="zindex" value="${zindex+1}" /> 
										</c:forEach>
									</c:if>
									<!-- <hr/> -->
									<div align="center">
										<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
										<button class="btn btn-warning" type="button" id = "backButton"  onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;重置</button>
									</div>
								</form>
							</div>
						</div>
						
					</div>
					
				</div>
			</div>


			<script type="text/javascript">
				contextPath = '${pageContext.request.contextPath}'
			</script>
			<jsp:include page="../common.jsp"></jsp:include>
			<script src="<%=path%>/ux/js/searchChosen.js"></script>
			<%-- <script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script> --%>
			<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
			<script src="<%=path%>/resources/upload/upload.js"></script>
			<script src="<%=path%>/ux/js/ld2.js"></script>
			
			<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>

			<script src="<%=path%>/js/reward_dividends/editRechargeReward.js"></script>
</body>
</html>

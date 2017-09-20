<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加专题</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">专题信息</div>
				<div class="panel-body">
					<form id="specialTopicForm" role="form" class="form-horizontal">
						<input type="hidden" name="multipShopToken" value="${multipShopToken}">
						<input type="hidden" id="isType" name="isType" value="${isType}"/>
						<input type="hidden" name="id" value="${specialTopicData.id}"> 
						
						<div class="form-group">
							<label class="col-md-3 control-label">专题标题：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="title" name="title"
									value="${specialTopicData.title}" style="width:41%;float:left">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">专题描述: <span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="description" name="description"
									value="${specialTopicData.description}" style="width:41%;float:left">
							</div>
						</div>
						
                        <div class="form-group">
                            <label class="col-md-3 control-label">专题BANNER: <span
								style="color:red;">*</span></label>
							<div id='activityImg' ImgValidate="true"  style="position:relative; left: 10px; float:left; margin-top: 10px;"></div>
							<input type="hidden" id="picUrl" name="picUrl"
									value="${specialTopicData.picUrl}" />
						</div>
						<hr />
						
						<div class="form-group">
							<label class="col-md-3 control-label">专题内容：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<textarea id="content" class="ckeditor" name="content"
												width="">${specialTopicData.content}</textarea>
							</div> 
						</div>
						<hr />
						
						<div class="form-group">
							<label class="col-md-3 control-label">专题视频：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="videoUrl" name="videoUrl"
									value="${specialTopicData.videoUrl}" style="width:41%;float:left">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">关联内容：<span
								style="color:red;">*</span></label>
							<div class="col-md-7">
								<select class="form-control" id="topicType" name="topicType" style="width:41%;float:left">
								    <!-- <option value="">请选择</option> -->
									<option value="1" ${specialTopicData.topicType==1?"selected":""}>商户</option>
									<option value="2" ${specialTopicData.topicType==2?"selected":""}>连锁店</option>
									<option value="3" ${specialTopicData.topicType==3?"selected":""}>区域代理</option>
									<option value="4" ${specialTopicData.topicType==4?"selected":""}>主播</option>
									<option value="5" ${specialTopicData.topicType==5?"selected":""}>预告</option>
									<option value="6" ${specialTopicData.topicType==6?"selected":""}>粉丝劵</option>
									<option value="7" ${specialTopicData.topicType==7?"selected":""}>套餐</option>
									<option value="8" ${specialTopicData.topicType==8?"selected":""}>商品</option>	
									
								</select>
							</div>
						</div>
						<hr/>
						
						<!-- *****************************************分割线begin********************************************* -->
						<!-- 商户信息 -->
						<div id = "sellerPanel"  >
							<div class="form-group">
								<label class="col-md-3 control-label">商户：</label>
								<div class="col-md-7">
									<select class="form-control" id="sellerId" name="sellerId"
										style="width:41%;float:left"></select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联商户：</label>
								<div class="col-md-7">
									<table class="table table-bordered" class="propertyTable">
										<thead style="background-color: beige;">
											<tr>
												<td>商品编号</td>
												<td>商户名称</td>
												<td>商户地址</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody id="sellerTB">
											<c:if test="${specialTopicData.topicType==1 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
													    <td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationAddress}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a>
														</td>
														<input type="hidden"  value='${relation.relationInfoJson}'/>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>								
								</div>
							</div>
							<hr />
						</div>
						
						
						<!-- 连锁店 -->
						<div id = "multipPanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">连锁店：</label>
								<div class="col-md-7">
									<select class="form-control" id="multipId" name="multipId"
										style="width: 41%; float: left"></select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联连锁店：</label>
								<div class="col-md-7">
	                                <table class="table table-bordered" >
										<thead style="background-color: beige;">
											<tr>
												<td>连锁店编号</td>
												<td>连锁店名称</td>
												<td>关联门店</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody id="multipTB">
											<c:if test="${specialTopicData.topicType==2 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationNum}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>								
								</div>
							</div>
							<hr />
						</div>
						
						<!-- 区域代理 -->
						<div id = "alliancePanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">区域代理：</label>
								<div class="col-md-7">
									<select class="form-control" id="allianceId" name="allianceId"
										style="width:41%;float:left"></select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">关联区域代理：</label>
								<div class="col-md-7">
                                    <table class="table table-bordered" >
										<thead style="background-color: beige;">
											<tr>
												<td>区域代理编号</td>
												<td>区域代理名称</td>
												<td>关联门店</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody id="allianceTB">
											<c:if test="${specialTopicData.topicType==3 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationNum}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
								    </table>
								</div>
							</div>
							<hr />
						</div>
						
						<!-- 主播-->
						<div id = "livePanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">主播：</label>
								<div class="col-md-7">
									<select class="form-control" id="liveId" name="liveId"
										style="width:41%;float:left"></select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联主播：</label>
								<div class="col-md-7">
	                                <table class="table table-bordered" >
										<thead style="background-color: beige;">
											<tr>
												<td>主播编号</td>
												<td>主播名称</td>
												<td>主播电话</td>
												<td>操作</td>
											</tr>
											
										</thead>
										<tbody id="liveTB">
											<c:if test="${specialTopicData.topicType==4 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.phone}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									 </table>
								 </div>
							</div>
							<hr />
						</div>
						
						<!-- 预告 -->
						<div id = "beforlivePanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">预告 ：</label>
								<div class="col-md-7">
									<select class="form-control" id="beforliveId"
										name="beforliveId" style="width: 41%; float: left"></select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联预告 ：</label>
								<div class="col-md-7">
									<table class="table table-bordered" class="propertyTable">
										<thead style="background-color: beige;">
											<tr>
												<td>预告编号</td>
												<td>预告标题</td>
												<td>关联商铺</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody id="beforeliveTB">
											<c:if test="${specialTopicData.topicType==5 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationName}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<hr />
						</div>
						
						<!-- 粉丝劵 -->
						<div id = "fansCouponPanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">粉丝劵 ：</label>
								<div class="col-md-7">
									<select class="form-control" id="fansCouponId" name="fansCouponId"
										style="width:41%;float:left"></select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联连锁店：</label>
								<div class="col-md-7">
	                                <table class="table table-bordered" class="propertyTable">
										<thead style="background-color: beige;">
											<tr>
												<td>粉丝劵编号</td>
												<td>粉丝劵标题</td>
												<td>关联商铺</td>
												<td>操作</td>
											</tr>
											
										</thead>
										<tbody id="fansCouponTB">
											<c:if test="${specialTopicData.topicType==6 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationName}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>								
								</div>
							</div>
							<hr />
						</div>
						
						<!-- 套餐 -->
						<div id = "sellerPackagePanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">套餐：</label>
								<div class="col-md-7">
									<select class="form-control" id="sellerPackageId" name="sellerPackageId"
										style="width:41%;float:left"></select>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-md-3 control-label">关联套餐  ：</label>
								<div class="col-md-7">
									<table class="table table-bordered" class="propertyTable">
										<thead style="background-color: beige;">
											<tr>
												<td>套餐编号</td>
												<td>套餐标题</td>
												<td>关联商铺</td>
												<td>操作</td>
											</tr>
											
										</thead>
										<tbody id="sellerPackageTB">
											<c:if test="${specialTopicData.topicType==7 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationName}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<hr />
						</div>
						
						<!-- 商品 -->
						<div id = "productInfoPanel" hidden >
							<div class="form-group">
								<label class="col-md-3 control-label">商品 ：</label>
								<div class="col-md-7">
									<select class="form-control" id="productInfoId" name="productInfoId"
										style="width:41%;float:left"></select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">关联商品  ：</label>
								<div class="col-md-7">
	                                <table class="table table-bordered" class="propertyTable">
										<thead style="background-color: beige;">
											<tr>
												<td>商品编号</td>
												<td>商品名称</td>
												<td>商品库存</td>
												<td>操作</td>
											</tr>
										</thead>
										<tbody id="productInfoTB">
											<c:if test="${specialTopicData.topicType==8 && !empty specialTopicData.specialTopicRelation}">
												<c:forEach items="${specialTopicData.specialTopicRelation}" var="relation">
													<tr id="${relation.subId}">
														<td>${relation.subId}</td>
														<td>${relation.subName}</td>
														<td>${relation.relationStore}</td>
														<td data-row="0" data-index="0" data-flex="false"
															data-type="string" style="width: 130px; height: 59px;"><a
															href="javascript:;" onclick="deleteGroup(${relation.subId})">删除</a></td>
														<input type="hidden"  value='${relation.relationInfoJson}'>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>								
								</div>
							</div>
							<hr />
						</div>
						<!-- *****************************************分割线end********************************************* -->
						
						<hr/>
						<div align="center">
							<button class="btn btn-danger" type="submit"  ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
							<!-- <button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;重置</button> -->
							<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
					
					<!-- 连锁店条件 -->
				    <form class="form-horizontal" role="form"  id="sellerFromId">
			            <input type="hidden" name="ismultiple"  value="1"/>
			        </form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript"> contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
    
    <!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
    
    <script src="<%=path%>/js/businessman/specialTopic/editSpecialTopic.js"></script> 
</body>
</html>

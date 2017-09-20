<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>首页管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet" />
<style type="text/css">
.border {
	float: right;
	margin-bottom: 20px;
	margin-right: 20px;
	margin-top: 10px;
}

.prer_btn {
	float: right;
	font-size: 20;
}
</style>
</head>



<body style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;"
	class="doc-views with-navbar">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<ul id="myTab" class="nav nav-tabs">
				<li  ${type==0?"class='active'":''}><a href="javascript:;" onclick="loadingType(0)">首页</a></li>
				<c:forEach items="${freshTypes}" var="freshType">
					<li  ${freshType.id==type?"class='active'":''}><a href="javascript:;" onclick="loadingType(${freshType.id})" >${freshType.name}</a></li>
				</c:forEach>
			</ul>
		</div>


		<div id="prompt"
			style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="module-containner-wrap container-fluid">
			<div class="form-horizontal" id="editForm">
				<div class="row" id="panel">
					
					<c:if test="${null!=btnAu['fresh/module/saveModule']}">
					<div
						style="width: 200px;float: left;margin-left: 40px;margin-top: 10px;">
						<nav class="menu" data-toggle="menu" style="width: 200px">
						<ul class="nav nav-primary">
							<li class="nav-parent"><a href="javascript:;"><i
									class="icon-list-ul"></i> 添加模块</a>
								<ul class="nav">
									<li><a href="javascript:;"
										onclick="addModule('banner_module')">滚动banner</a></li>
									<c:if test="${type==0}">
									<li><a href="javascript:;"
										onclick="addModule('icon_module')">图标入口</a></li>
									</c:if>
									<c:if test="${type==0}">
									<li><a href="javascript:;"
										onclick="addModule('activity_module')">活动位入口</a></li>
									</c:if>
									<li><a href="javascript:;"
										onclick="addModule('product_module')">商品模块</a></li>
									<li><a href="javascript:;"
										onclick="addModule('brand_module')">热销品牌模块</a></li>
									<c:if test="${type==0}">
									<li><a href="javascript:;"
										onclick="addModule('dedi_module')">专场模块</a></li>
										</c:if>
									<li><a href="javascript:;"
										onclick="addModule('boutique_module')">精选模块</a></li>
								</ul></li>
						</ul>
						</nav>
					</div>
					</c:if>
					<div id="banner_border">
						<c:if test="${!empty bannerImages}">
							<div class="col-lg-10 col-xs-8 border" id="banner_module">
								<div class="compile-info-module border-defualt-module mg-b-12">
									<h3>
										滚动banner<em class="em-tips-font">*</em>
										<div class="prer_btn">
											<div class="btn-group" style="margin-bottom: 5px;">
												<button type="button" class="btn btn-success"
													onclick="addBanner(this)">
													<span class="icon-plus"></span>&nbsp;添加banner
												</button>
												<button type="button" class="btn btn-danger"
													onclick="deleteBanner(this)">
													<span class="icon-remove"></span>&nbsp;删除banner
												</button>
											</div>
											<div class="btn-group" style="margin-bottom: 5px;">
											   <c:if test="${null!=btnAu['fresh/module/deleteModule']}">
												<button type="button" class="btn btn-danger"
													onclick="deleteModule(this,null,1)">
													<span class="icon-remove"></span>&nbsp;删除
												</button>
												</c:if>
												<c:if test="${null!=btnAu['fresh/module/saveModule']}">
												<button type="button" class="btn btn-primary"
													onclick="saveModule(this)">
													<span class="icon-save"></span>&nbsp;保存
												</button>
												</c:if>
											</div>
										</div>
									</h3>
									<c:forEach items="${bannerImages}" var="bannerImage"
										varStatus="status">
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>图片：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="hidden" class="form-control"
													name="picUrl${status.index}" id="picUrl${status.index}"
													value="${bannerImage.imageUrl}">
												<div id="picUrlImg${status.index}"></div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>链接：
											</div>
											<div class="col-lg-9 col-xs-9">
												<div class="input-group">
													<input type="text" class="form-control"  jumpType="${bannerImage.jumpType}"
														name="bannerUrl${status.index}"
														value="${bannerImage.jumpUrl==null?bannerImage.remark:bannerImage.jumpUrl}">
													<span class="input-group-btn">
														<button class="btn btn-default chooseUrlBtn" type="button"
															data-position="0" data-toggle="modal"
															data-target="#myModal">选择链接</button>
													</span>
												</div>
											</div>
										</div>
										<c:if test="${!status.last}">
											<hr>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</div>
					<div id="icon_border">
						<c:if test="${!empty iconImages}">
							<div class="col-lg-10 col-xs-8 border" id="icon_module">
								<div class="compile-info-module border-defualt-module mg-b-12">
									<h3>
										图标模块<em class="em-tips-font">*</em>
										<div class="prer_btn">
											<div class="btn-group" style="margin-bottom: 5px;">
												<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
												<button type="button" class="btn btn-danger"
													onclick="deleteModule(this,null,2)">
													<span class="icon-remove"></span>&nbsp;删除
												</button>
												</c:if>
												<c:if test="${null!=btnAu['fresh/module/saveModule']}">
												<button type="button" class="btn btn-primary"
													onclick="saveModule(this)">
													<span class="icon-save"></span>&nbsp;保存
												</button>
												</c:if>
											</div>
										</div>
									</h3>
									<c:forEach items="${iconImages}" var="iconImage"
										varStatus="status">
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>图标${status.index+1}：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="hidden" class="form-control"
													id="iconUrl${status.index}" name="iconUrl${status.index}"
													value="${iconImage.imageUrl}">
												<div id="iconUrlImg${status.index}"></div>

											</div>

										</div>

										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>链接：
											</div>
											<div class="col-lg-9 col-xs-9">
												<div class="input-group">
													<input type="text" class="form-control"  jumpType="${iconImage.jumpType}"
														name="iconBackUrl${status.index}"
														value="${iconImage.jumpUrl==null?iconImage.remark:iconImage.jumpUrl}">
													<span class="input-group-btn">
														<button class="btn btn-default chooseUrlBtn" type="button"
															data-position="0" data-toggle="modal"
															data-target="#myModal">选择链接</button>
													</span>
												</div>
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>标题:
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control"
													placeholder="请输入标题..." name="iconTitle${status.index}"
													value="${iconImage.title}">
											</div>

										</div>

										<c:if test="${!status.last}">
											<hr>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</div>
					<div id="activity_border">
						<c:if test="${!empty activityImages}">
							<div class="col-lg-10 col-xs-8 border" id="activity_module">
								<div class="compile-info-module border-defualt-module mg-b-12">
									<h3>
										活动位模块<em class="em-tips-font">*</em>
										<div class="prer_btn">
											<div class="btn-group" style="margin-bottom: 5px;">
												<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
												<button type="button" class="btn btn-danger"
													onclick="deleteModule(this,null,3)">
													<span class="icon-remove"></span>&nbsp;删除
												</button>
												</c:if>
												<c:if test="${null!=btnAu['fresh/module/saveModule']}">
												<button type="button" class="btn btn-primary"
													onclick="saveModule(this)">
													<span class="icon-save"></span>&nbsp;保存
												</button>
												</c:if>
											</div>
										</div>
									</h3>
									<c:forEach items="${activityImages}" var="activityImage"
										varStatus="status">
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>图标${status.index+1}：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="hidden" class="form-control"
													id="activityUrl${status.index}" name="activityUrl${status.index}"
													value="${activityImage.imageUrl}">
												<div id="activityUrlImg${status.index}"></div>
											</div>
					
										</div>
					
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>链接：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="radio"  name="activityJumpType${status.index+1}" ${activityImage.jumpType==4?'checked':''} value="4"/>一元夺宝&nbsp;&nbsp;&nbsp;
												<input type="radio"  name="activityJumpType${status.index+1}" ${activityImage.jumpType==5?'checked':''} value="5"/>竞拍&nbsp;&nbsp;&nbsp;
											</div>
					
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>标题:
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control" placeholder="请输入标题..."
													name="activityTitle${status.index+1}" value="${activityImage.title}">
											</div>
					
										</div>
					

										<c:if test="${!status.last}">
											<hr>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</div>
					<form id="product_form">
						<div id="product_border">
							<c:if test="${!empty productModule}">
								<div class="col-lg-10 col-xs-8 border" id="product_module">
									<div class="compile-info-module border-defualt-module mg-b-12">

										<h3>
											商品模块<em class="em-tips-font">*</em>
											<div class="prer_btn">
												<div class="btn-group" style="margin-bottom: 5px;">
													<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
													<button type="button" class="btn btn-danger"
														onclick="deleteModule(this,${productModule.id})">
														<span class="icon-remove"></span>&nbsp;删除
													</button>
													</c:if>
													<c:if test="${null!=btnAu['fresh/module/saveModule']}">
													<button type="submit" class="btn btn-primary">
														<span class="icon-save"></span>&nbsp;保存
													</button>
													</c:if>
												</div>
											</div>
										</h3>

										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>模块名称：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control" name="moduleName"
													value="${productModule.moduleName}">
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>商品类型：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input name="productType" id="productType_choice" value="2"
													${productModule.productType==2?"checked='checked'":''}
													type="radio"><span
													style="font-size: 12px;margin-right: 20px;">热销商品</span> 
														<input name="productType"  value="3"  id="productType_kill"
													${productModule.productType==3?"checked='checked'":''}
													type="radio"><span
													style="font-size: 12px;margin-right: 20px;">秒杀商品</span> 
													<input
													name="productType" id="productType_activity" value="4"
													${productModule.productType==4?"checked='checked'":''}
													type="radio"><span
													style="font-size: 12px;margin-right: 20px;">通用活动商品</span>
													<input
													name="productType" id="productType_jixuan" value="5"
													${productModule.productType==5?"checked='checked'":''}
													type="radio"><span
													style="font-size: 12px;margin-right: 20px;">精选商品</span>
											</div>

										</div>
										<div class="form-group" id="activityChoose_form"
											${productModule.productType==4?'':"style='display:none;'"}>
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>选择活动：
											</div>
											<div class="col-lg-9 col-xs-9">
												<select class="form-control" id="activityChoose"
													name="activityId" initValue="${productModule.activityId}"
													style="width:100%;"></select>
											</div>

										</div>
										<div class="form-group" id="killChoose_form"
											${productModule.productType==3?'':"style='display:none;'"}>
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>选择活动：
											</div>
											<div class="col-lg-9 col-xs-9">
												<select class="form-control" id="killChoose"
													name="killId" initValue="${productModule.activityId}"
													style="width:100%;"></select>
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>显示数量：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control"
													value="${productModule.showNum}" name="showNum"
													style="width:100px;">
											</div>
										</div>
										<input type="hidden" name="typeId" value="${type}" /> <input
											type="hidden" name="moduleType" value="1" /> <input
											type="hidden" name="id" value="${productModule.id}">
									</div>
								</div>

							</c:if>
						</div>
					</form>
					<div id="brand_border">
						<c:if test="${!empty hotBrands}">
							<div class="col-lg-10 col-xs-8 border" id="brand_module">
								<div class="compile-info-module border-defualt-module mg-b-12">
									<h3>
										热销品牌模块<em class="em-tips-font">*</em>
										<div class="prer_btn">
											<div class="btn-group" style="margin-bottom: 5px;">
												<button type="button" class="btn btn-success"
													onclick="addBrand(this)">
													<span class="icon-plus"></span>&nbsp;添加品牌
												</button>
												<button type="button" class="btn btn-danger"
													onclick="deleteBrand(this)">
													<span class="icon-remove"></span>&nbsp;删除品牌
												</button>
											</div>
											<div class="btn-group" style="margin-bottom: 5px;">
											<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
												<button type="button" class="btn btn-danger"
													onclick="deleteModule(this,null,-1)">
													<span class="icon-remove"></span>&nbsp;删除
												</button>
												</c:if>
												<c:if test="${null!=btnAu['fresh/module/saveModule']}">
												<button type="button" class="btn btn-primary"
													onclick="saveModule(this)">
													<span class="icon-save"></span>&nbsp;保存
												</button>
												</c:if>
											</div>
										</div>
									</h3>
									<c:forEach items="${hotBrands}" var="hotBrand"
										varStatus="status">
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>品牌：
											</div>
											<div class="col-lg-9 col-xs-9">
												<select class="form-control" name="brandId${status.index}"
													initValue="${hotBrand.brandId}" style="width:100%;"></select>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</div>
					<div id="dedi_border">
						<c:if test="${!empty dediModules}">
							<c:forEach items="${dediModules}" var="dediModule"
								varStatus="status">
								<div class="col-lg-10 col-xs-8 border" id="dedi_module">
									<div class="compile-info-module border-defualt-module mg-b-12">
										<h3>
											专场模块<em class="em-tips-font">*</em>
											<div class="prer_btn">
												<div class="btn-group" style="margin-bottom: 5px;">
												<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
													<button type="button" class="btn btn-danger"
														onclick="deleteModule(this,${dediModule.id})">
														<span class="icon-remove"></span>&nbsp;删除
													</button>
													</c:if>
													<c:if test="${null!=btnAu['fresh/module/saveModule']}">
													<button type="button" class="btn btn-primary"
														onclick="saveModule(this)">
														<span class="icon-save"></span>&nbsp;保存
													</button>
													</c:if>
												</div>
											</div>

										</h3>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>模块名称：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control"
													value="${dediModule.moduleName}" name="moduleName">
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>图片：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="hidden" class="form-control" name="dediUrl"
													value="${dediModule.imageUrl}">
												<div></div>

											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>链接：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control"
													value="${dediModule.jumpUrl}" name="jumpUrl">
											</div>

										</div>
										<div class="form-group" name="activity_choose" ${dediModule.activityProductType==2?'style="display:none;"':''}>
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>选择活动：
											</div>
											<div class="col-lg-9 col-xs-9">
												<select class="form-control" name="activityId"
													initValue="${dediModule.activityId}" style="width:100%;"></select>
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>商品部分：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input name="activityProductType${status.index}" 
													value="1" type="radio" value="1"
													${dediModule.activityProductType==1?'checked="checked"':''}><span
													style="font-size: 12px;margin-right: 20px;">活动商品</span> <input
													name="activityProductType${status.index}"  value="2"
													${dediModule.activityProductType==2?'checked="checked"':''}
													type="radio"><span
													style="font-size: 12px;margin-right: 20px;">手动添加</span>
											</div>

										</div>
										<div id="activity_product"
											${dediModule.activityProductType==1?'style="display:none;"':''}>
											<div class="form-group">
												<div class="col-lg-3 col-xs-3 reset-form-name">
													<em class="em-tips-font"></em>
												</div>
												<div class="col-lg-9 col-xs-9">
													<button class="btn btn-default chooseDediBtn" type="button"
														data-position="0" data-toggle="modal"
														data-target="#dediModal">选择商品</button>
												</div>

											</div>

											<div class="form-group" style="width: 50%;margin-left: 26%;"
												id="propertyTab">
												<table class="table table-bordered" class="propertyTable">
													<thead style="background-color: beige;">
														<tr>
															<td>商品编号</td>
															<td>商品名</td>
															<td>排序</td>
															<td>操作</td>
														</tr>

													</thead>
													<tbody name="tbody">
														<c:forEach items="${dediModule.activityProducts}" var="activityProduct">
														<tr id="${activityProduct.codeId}">
															<td>${activityProduct.codeId}</td>
															<td>${activityProduct.pname}</td>
															<td>${activityProduct.sort}</td>
															<td data-row="0" data-index="0" data-flex="false"
																data-type="string" style="width: 130px; height: 59px;">&nbsp;&nbsp;<a
																href="javascript:;" onclick="deleteGroup(this)">删除</a></td>
															<input type="hidden"
																value='${activityProduct.productJsonString}'>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>显示数量：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="number" class="form-control" value="${dediModule.showNum}"
													name="showNum" style="width:100px;">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>排序：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="number" class="form-control" value="${dediModule.sort}"
													name="sort" style="width:100px;">
											</div>
										</div>
										<input type="hidden"  value="${dediModule.id}" name="id"/>
									</div>
								</div>
						</c:forEach>
						</c:if>
					</div>
					<form id="boutique_form">
						<div id="boutique_border">
							<c:if test="${!empty boutiqueModule}">
								<div class="col-lg-10 col-xs-8 border" id="boutique_module">
									<div class="compile-info-module border-defualt-module mg-b-12">
										<h3>
											精选模块<em class="em-tips-font">*</em>
											<div class="prer_btn">
												<div class="btn-group" style="margin-bottom: 5px;">
												<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
													<button type="button" class="btn btn-danger"
														onclick="deleteModule(this,${boutiqueModule.id})">
														<span class="icon-remove"></span>&nbsp;删除
													</button>
												</c:if>
												<c:if test="${null!=btnAu['fresh/module/saveModule']}">
													<button type="submit" class="btn btn-primary">
														<span class="icon-save"></span>&nbsp;保存
													</button>
												</c:if>
												</div>
											</div>
										</h3>

										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>模块名称：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control" name="moduleName"
													value="${boutiqueModule.moduleName}">
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>商品类型：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input name="productType" value="5" type="radio"
													${boutiqueModule.productType==5?'checked="checked"':''}><span
													style="font-size: 12px;margin-right: 20px;">精选商品</span> <input
													name="productType" value="2" type="radio"
													${boutiqueModule.productType==2?'checked="checked"':''}><span
													style="font-size: 12px;margin-right: 20px;">热销商品</span>
											</div>

										</div>
										<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>显示数量：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="text" class="form-control" name="showNum"
													value="${ boutiqueModule.showNum}" style="width:100px;">
											</div>
										</div>
										<input type="hidden" name="id" value="${boutiqueModule.id}" />
										<input type="hidden" name="moduleType" value="2" /> <input
											type="hidden" name="typeId" value="${type}" />
									</div>
								</div>


							</c:if>
						</div>
					</form>

				</div>

				<!-- <div class="row">
					<div class="col-lg-12 text-center">
						<div class="floot-submint-wrap">
							<button class="btn btn-danger" type="submit">
								<span class="glyphicon glyphicon-floppy-disk"></span> &nbsp;保存
							</button>
							&nbsp;&nbsp; <a class="btn btn-warning"
								href="materials/manage/init.jhtml?"><span
								class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
						</div>
					</div>
				</div> -->
			</div>
		</div>
		<div id="copyModule">
			<div class="col-lg-10 col-xs-8 border" id="banner_module"
				style="display:none;">
				<div class="compile-info-module border-defualt-module mg-b-12">
					<h3>
						滚动banner<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
								<button type="button" class="btn btn-success"
									onclick="addBanner(this)">
									<span class="icon-plus"></span>&nbsp;添加banner
								</button>
								<button type="button" class="btn btn-danger"
									onclick="deleteBanner(this)">
									<span class="icon-remove"></span>&nbsp;删除banner
								</button>
							</div>
							<div class="btn-group" style="margin-bottom: 5px;">
								<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
								</c:if>
								<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="button" class="btn btn-primary"
									onclick="saveModule(this)">
									<span class="icon-save"></span>&nbsp;保存
								</button>
								</c:if>
							</div>
						</div>
					</h3>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="picUrl">
							<div></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="bannerUrl">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>
					</div>
					<hr />
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="picUrl">
							<div></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="bannerUrl">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>
					</div>
					<hr />
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="picUrl">
							<div></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="bannerUrl">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>
					</div>
					<hr />
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="picUrl">
							<div></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="bannerUrl">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>
					</div>
					<hr />
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="picUrl">
							<div></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="bannerUrl">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>
					</div>
					<hr />
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="picUrl">
							<div></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="bannerUrl">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-10 col-xs-8 border" style="display:none;"
				id="icon_module">
				<div class="compile-info-module border-defualt-module mg-b-12">
					<h3>
						图标模块<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
							<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
								</c:if>
							<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="button" class="btn btn-primary"
									onclick="saveModule(this)">
									<span class="icon-save"></span>&nbsp;保存
								</button>
							</c:if>
							</div>
						</div>
					</h3>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图标1：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="iconUrl1">
							<div></div>

						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="iconBackUrl1">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>标题:
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" placeholder="请输入标题..."
								name="iconTitle1" value="">
						</div>

					</div>

					<hr>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图标2：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="iconUrl2">
							<div></div>

						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="iconBackUrl2">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>标题:
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" name="iconTitle2" class="form-control"
								placeholder="请输入标题..." value="">
						</div>

					</div>
					<hr>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图标3：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="iconUrl3">
							<div></div>

						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="iconBackUrl3">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>标题:
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" name="iconTitle3" class="form-control"
								placeholder="请输入标题..." value="">
						</div>

					</div>

					<hr>


					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图标4：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="iconUrl4">
							<div></div>

						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<div class="input-group">
								<input type="text" class="form-control" name="iconBackUrl4">
								<span class="input-group-btn">
									<button class="btn btn-default chooseUrlBtn" type="button"
										data-position="0" data-toggle="modal" data-target="#myModal">选择链接</button>
								</span>
							</div>
						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>标题:
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" name="iconTitle4" class="form-control"
								placeholder="请输入标题..." value="">
						</div>

					</div>

				</div>
			</div>
			
			
			<div class="col-lg-10 col-xs-8 border" style="display:none;"
				id="activity_module">
				<div class="compile-info-module border-defualt-module mg-b-12">
					<h3>
						活动位模块<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
							<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
								</c:if>
							<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="button" class="btn btn-primary"
									onclick="saveModule(this)">
									<span class="icon-save"></span>&nbsp;保存
								</button>
							</c:if>
							</div>
						</div>
					</h3>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图标1：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="activityUrl1">
							<div></div>

						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="radio"  checked="checked"
								name="JumpType1" value="4">一元夺宝&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio"  
								name="JumpType1" value="5">竞拍&nbsp;&nbsp;&nbsp;&nbsp;
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>标题:
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" placeholder="请输入标题..."
								name="activityTitle1" value="">
						</div>

					</div>

					<hr>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图标2：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="activityUrl2">
							<div></div>

						</div>

					</div>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="radio"  
								name="JumpType2" value="4">一元夺宝&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio"  checked="checked"
								name="JumpType2" value="5">竞拍&nbsp;&nbsp;&nbsp;&nbsp;
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>标题:
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" name="activityTitle2" class="form-control"
								placeholder="请输入标题..." value="">
						</div>

					</div>
					</div>
				</div>
			
	
			
			<div class="col-lg-10 col-xs-8 border" style="display:none;"
				id="product_module">
				<div class="compile-info-module border-defualt-module mg-b-12">

					<h3>
						商品模块<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
								<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
								</c:if>
								<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="submit" class="btn btn-primary">
									<span class="icon-save"></span>&nbsp;保存
								</button>
								</c:if>
							</div>
						</div>
					</h3>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>模块名称：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" name="moduleName"
								value="">
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>商品类型：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input name="productType" id="productType_choice" value="2"
								type="radio"><span
								style="font-size: 12px;margin-right: 20px;">热销商品</span>
									<input name="productType"  value="3" id="productType_kill"
								type="radio"><span
								style="font-size: 12px;margin-right: 20px;">秒杀商品</span>
								 <input
								name="productType" id="productType_activity" value="4"
								type="radio"><span
								style="font-size: 12px;margin-right: 20px;">通用活动商品</span>
								<input
								name="productType" id="productType_jixuan" value="5"
								type="radio"><span
								style="font-size: 12px;margin-right: 20px;">精选商品</span>
						</div>

					</div>
					<div class="form-group" id="activityChoose_form">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>选择活动：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" id="activityChoose"
								name="activityId" initValue="" style="width:100%;"></select>
						</div>

					</div>
					<div class="form-group" id="killChoose_form">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>选择活动：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" id="killChoose"
								name="killId" initValue="" style="width:100%;"></select>
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>显示数量：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" value="" name="showNum"
								style="width:100px;">
						</div>
					</div>
					<input type="hidden" name="typeId" value="${type}" /> <input
						type="hidden" name="moduleType" value="1" />
				</div>
			</div>
			<div class="col-lg-10 col-xs-8 border" style="display:none;"
				id="brand_module">
				<div class="compile-info-module border-defualt-module mg-b-12">
					<h3>
						热销品牌模块<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
								<button type="button" class="btn btn-success"
									onclick="addBrand(this)">
									<span class="icon-plus"></span>&nbsp;添加品牌
								</button>
								<button type="button" class="btn btn-danger"
									onclick="deleteBrand(this)">
									<span class="icon-remove"></span>&nbsp;删除品牌
								</button>
							</div>
							<div class="btn-group" style="margin-bottom: 5px;">
							<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
							</c:if>
							<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="button" class="btn btn-primary"
									onclick="saveModule(this)">
									<span class="icon-save"></span>&nbsp;保存
								</button>
							</c:if>
							</div>
						</div>
					</h3>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>品牌：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="brandId" initValue=""
								style="width:100%;"></select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-10 col-xs-8 border" style="display:none;"
				id="dedi_module">
				<div class="compile-info-module border-defualt-module mg-b-12">
					<h3>
						专场模块<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
							<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
							</c:if>
							<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="button" class="btn btn-primary"
									onclick="saveModule(this)">
									<span class="icon-save"></span>&nbsp;保存
								</button>
							</c:if>							
							</div>
						</div>

					</h3>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>模块名称：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" value=""
								name="moduleName">
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>图片：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="hidden" class="form-control" name="dediUrl">
							<div></div>

						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>链接：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" value="" name="jumpUrl">
						</div>

					</div>
					<div class="form-group" name="activity_choose" >
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>选择活动：
						</div>
						<div class="col-lg-9 col-xs-9">
							<select class="form-control" name="activityId"
								style="width:100%;"></select>
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>商品部分：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input name="activityProductType" id="product_activity" value="1"
								type="radio" value="1"><span
								style="font-size: 12px;margin-right: 20px;">活动商品</span> <input
								name="activityProductType" id="product_mine" value="2"
								type="radio"><span
								style="font-size: 12px;margin-right: 20px;">手动添加</span>
						</div>

					</div>
					<div id="activity_product" style="display:none;">
						<div class="form-group">
							<div class="col-lg-3 col-xs-3 reset-form-name">
								<em class="em-tips-font"></em>
							</div>
							<div class="col-lg-9 col-xs-9">
								<button class="btn btn-default chooseDediBtn" type="button"
									data-position="0" data-toggle="modal" data-target="#dediModal">选择商品</button>
							</div>

						</div>

						<div class="form-group" style="width: 50%;margin-left: 26%;"
							id="propertyTab">
							<table class="table table-bordered" class="propertyTable">
								<thead style="background-color: beige;">
									<tr>
										<td>商品编号</td>
										<td>商品名</td>
										<td>排序</td>
										<td>操作</td>
									</tr>

								</thead>
								<tbody name="tbody">

								</tbody>
							</table>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>显示数量：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="number" class="form-control" value="" name="showNum"
								style="width:100px;">
						</div>
					</div>
					<div class="form-group">
											<div class="col-lg-3 col-xs-3 reset-form-name">
												<em class="em-tips-font">*</em>排序：
											</div>
											<div class="col-lg-9 col-xs-9">
												<input type="number" class="form-control" 
													name="sort" style="width:100px;">
											</div>
										</div>
				</div>
			</div>
			<div class="col-lg-10 col-xs-8 border" style="display:none;"
				id="boutique_module">
				<div class="compile-info-module border-defualt-module mg-b-12">
					<h3>
						精选模块<em class="em-tips-font">*</em>
						<div class="prer_btn">
							<div class="btn-group" style="margin-bottom: 5px;">
							<c:if test="${null!=btnAu['fresh/module/deleteModule']}">
								<button type="button" class="btn btn-danger"
									onclick="deleteModule(this)">
									<span class="icon-remove"></span>&nbsp;删除
								</button>
							</c:if>
							<c:if test="${null!=btnAu['fresh/module/saveModule']}">
								<button type="submit" class="btn btn-primary">
									<span class="icon-save"></span>&nbsp;保存
								</button>
							</c:if>
							</div>
						</div>
					</h3>

					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>模块名称：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" name="moduleName"
								value="">
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>商品类型：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input name="productType" value="5" type="radio"
								checked="checked"><span
								style="font-size: 12px;margin-right: 20px;">精选商品</span> <input
								name="productType" value="2" type="radio"><span
								style="font-size: 12px;margin-right: 20px;">热销商品</span>
						</div>

					</div>
					<div class="form-group">
						<div class="col-lg-3 col-xs-3 reset-form-name">
							<em class="em-tips-font">*</em>显示数量：
						</div>
						<div class="col-lg-9 col-xs-9">
							<input type="text" class="form-control" name="showNum" value=""
								style="width:100px;">
						</div>
					</div>
					<input type="hidden" name="moduleType" value="2" /> <input
						type="hidden" name="typeId" value="${type}" />
				</div>
			</div>
		</div>
		<div class="form-group" id="brand_group" style="display:none;">
			<div class="col-lg-3 col-xs-3 reset-form-name">
				<em class="em-tips-font">*</em>品牌：
			</div>
			<div class="col-lg-9 col-xs-9">
				<select class="form-control" name="brandId" style="width:100%;"></select>
			</div>
		</div>
</div>
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">选择链接</h4>
					</div>
					<div class="modal-body example" style="height: 250px;">
						<form class="form-horizontal" id="chooseForm">
							<div class="form-group">
								<label for="exampleInputPassword5" class="col-sm-2">类型</label>
								<div class="col-sm-10">
									<input type="radio" checked="checked" name="url_choose" value="1" /><span style="width:50px">商品</span>
									<input type="radio"  name="url_choose" value="2" />秒杀
								</div>
							</div>
							<div class="form-group" id="url_choose_product">
								<label for="exampleInputPassword5" class="col-sm-2">选择商品</label>
								<div class="col-sm-10">
									<select class="form-control" id="brand" name="brand"
										style="width:41%;float:left"></select>
								</div>
							</div>
							<div class="form-group" id="url_choose_activity" style="display:none;">
								<label for="exampleInputPassword5" class="col-sm-2">选择活动</label>
								<div class="col-sm-10">
									<select class="form-control" id="kill" name="kill"
										style="width:41%;float:left"></select>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-primary" id="chooseSubmit">保存</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

		<div class="modal fade" id="dediModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">选择商品</h4>
					</div>
					<div class="modal-body example" style="height: 200px;">
						<form class="form-horizontal" id="chooseForm">
							<div class="form-group">
								<label for="exampleInputPassword5" class="col-sm-2">选择商品</label>
								<div class="col-sm-10">
									<select class="form-control" id="dediProduct"
										name="dediProduct" style="width:41%;float:left"></select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-2 col-xs-3 reset-form-name">
									<em class="em-tips-font">*</em>排序：
								</div>
								<div class="col-lg-9 col-xs-9">
									<input type="text" class="form-control" id="dediSort"
										style="width:100px;">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-primary"
									id="dediModalSubmit">保存</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

	<script type="text/json" id="permissions">
{
	add:'${ btnAu['fresh/module/saveModule']}',
	delete:'${btnAu['fresh/module/deleteModule']}'
}</script>
		<script type="text/javascript">
			contextPath = '${pageContext.request.contextPath}';
		</script>
		
		<jsp:include page="../common.jsp"></jsp:include>
		<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
		<script src="<%=path%>/ux/js/scrollTablel.js"></script>
		<script src="<%=path%>/resources/upload/upload.js"></script>
		<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
		<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
		<script src="<%=path%>/ux/js/multipleChosen.js"></script>
		<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
		<script src="<%=path%>/js/fresh/moduleList.js"></script>
		<script type="text/javascript">
		var typeId = "${type}";
	</script>
</body>
</html>

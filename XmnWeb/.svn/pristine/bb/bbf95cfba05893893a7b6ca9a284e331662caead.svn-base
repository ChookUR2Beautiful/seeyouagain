<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>添加物料分类信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/>

</head>
<body style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;" class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="module-containner-wrap container-fluid">
		<form class="form-horizontal" id="editForm">
		    <div class="row">
		        	<input type="hidden" name="isType" id="isType" value="${isType }">
		        	<input type="hidden" name="id" id="id" value="${materialInfo.id }">
		        	<input type="hidden" name="materialAttrVals" id="materialAttrVals" >
		            <div class="col-lg-6 col-xs-6">
		                <div class="compile-info-module border-defualt-module mg-b-10">
		                    <h3>编辑基本信息<em class="em-tips-font">*</em></h3>
		
		                    <div class="form-group">
		                        <div class="col-lg-3 col-xs-3 reset-form-name"><em class="em-tips-font">*</em>商品分类：</div>
		                        <div class="col-lg-9 col-xs-9">
		                            <select class="form-control" id="categoryId" name="categoryId" initValue="${materialInfo.categoryId }">
		                            </select>
		                        </div>
		
		                    </div>
		                    <div class="form-group">
		                        <div class="col-lg-3 col-xs-3 reset-form-name"><em class="em-tips-font">*</em>标　　签：</div>
		                        <div class="col-lg-9 col-xs-9">
		                            <select class="form-control" id="tagId" name="tagId" initValue="${materialInfo.tagId }" >
		                            </select>
		                        </div>
		
		                    </div>
		                    <div class="form-group">
		                        <div class="col-lg-3 col-xs-3 reset-form-name"><em class="em-tips-font">*</em>商品名称：</div>
		                        <div class="col-lg-9 col-xs-9">
		                            <input type="text" name="title" class="form-control" placeholder="请输入产品名" value="${materialInfo.title }" >
		                        </div>
		
		                    </div>
		                    <div class="form-group">
		                        <div class="col-lg-3 col-xs-3 reset-form-name"><em class="em-tips-font">*</em>商品描述：</div>
		                        <div class="col-lg-9 col-xs-9">
		                            <textarea class="form-control" rows="3" name="remark">${materialInfo.remark }</textarea>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <div class="col-lg-3 col-xs-3 reset-form-name"><em class="em-tips-font">*</em>完成时间：</div>
		                        <div class="col-lg-9 col-xs-9">
		                            <input type="text" class="form-control" name="finishtime" placeholder="请输入时间" value="${materialInfo.finishtime }">
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <div class="col-lg-3 col-xs-3 reset-form-name"><em class="em-tips-font">*</em>商品主图：</div>
		                        <div class="col-lg-9 col-xs-9">
		                            <input type="hidden" class="form-control" name="picUrl" id="picUrl"
											value="${materialInfo.picUrl}">
											<div id="picUrlImg"></div>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <h4 class="col-lg-2 col-xs-2 ">商品BANNER图</h4>
		                        	<div class="col-lg-10 col-xs-10 uploadings-desc">（建议尺寸：750*340px;格式：gif、png、jpg、大小：2Mb以内）</div>
		                    </div>
		                    <div class="form-group">
		                        <div class="col-lg-6 col-xs-6 uploadings-wrap">
									<input type="hidden" class="form-control" name="bannerList[0].picUrl" id="picUrl0"
											value="${materialInfo.bannerList[0].picUrl}">
											<div id="picUrlImg0"></div>
	                            </div>
		                        <div class="col-lg-6 col-xs-6 uploadings-wrap"><input type="hidden" class="form-control" name="bannerList[1].picUrl" id="picUrl1"
											value="${materialInfo.bannerList[1].picUrl}">
											<div id="picUrlImg1"></div>
								</div>
		                        <div class="col-lg-6 col-xs-6 uploadings-wrap"><input type="hidden" class="form-control" name="bannerList[2].picUrl" id="picUrl2"
											value="${materialInfo.bannerList[2].picUrl}">
											<div id="picUrlImg2"></div>
								</div>
		                        <div class="col-lg-6 col-xs-6 uploadings-wrap"><input type="hidden" class="form-control" name="bannerList[3].picUrl" id="picUrl3"
											value="${materialInfo.bannerList[3].picUrl}">
											<div id="picUrlImg3"></div>
								</div>
		                        <div class="col-lg-6 col-xs-6 uploadings-wrap"><input type="hidden" class="form-control" name="bannerList[4].picUrl" id="picUrl4"
											value="${materialInfo.bannerList[4].picUrl}">
											<div id="picUrlImg4"></div>
								</div>
		                        <div class="col-lg-6 col-xs-6 uploadings-wrap"><input type="hidden" class="form-control" name="bannerList[5].picUrl" id="picUrl5"
											value="${materialInfo.bannerList[5].picUrl}">
											<div id="picUrlImg5"></div>
								</div>
		                    </div>
		                </div>
		            </div>
		            <div class="col-lg-6 col-xs-6">
		                <div class="compile-norms-module border-defualt-module mg-b-10">
		                    <h3>编辑商品规格<em class="em-tips-font">*</em></h3>
		                    	
		                    	<!-- 关联规格内容区域 -->
								<div id="relatedAttrDiv">
								
		                        </div>
		                        <div class="form-group">
		                            <div class="col-lg-2 col-xs-2 col-sm-2 reset-form-name">商品原价：</div>
		                            <div class="col-lg-3 col-xs-3 col-sm-3">
		                                <input type="number" class="form-control" name="basePrice" placeholder="￥0.00" value="${materialInfo.basePrice }">
		                            </div>
		                        </div>
		                        <div class="form-group" style="display:none;">
		                            <div class="col-lg-2 col-xs-2 col-sm-2 reset-form-name"><em class="em-tips-font">*</em>默认售价：
		                            </div>
		                            <div class="col-lg-3 col-xs-3 col-sm-3" >
		                                <input type="number" class="form-control" name="salePrice" placeholder="￥0.00" value="${materialInfo.salePrice }">
		                            </div>
		                           <!--  <div class="col-lg-7 col-xs-7 col-sm-7 text-right">
		                                <button type="button" class="btn btn-primary" id="add-norms">添加</button>
		                            </div> -->
		                        </div>
		                        <div class="form-group">
		                            <div class="col-lg-12 col-xs-12">
		                                <div class="compile-norms-table-list">
		                                    <table class="table table-bordered text-center">
		                                    	<c:if test="${!empty materialInfo.materialAttrList}">
			                                        <thead>
			                                        <tr>
			                                            <c:forEach items="${materialInfo.materialAttrList}" var="materialAttr" varStatus="attrIndex">
			                                            	<th>${materialAttr.name }</th>
			                                            </c:forEach>
			                                            <th>规格售价</th>
			                                            <!-- <th>操作</th> -->
			                                        </tr>
			                                        </thead>
		                                        </c:if>
		                                        <tbody>
		                                        <c:if test="${!empty materialInfo.materialAttrGroupList}">
													<c:forEach items="${materialInfo.materialAttrGroupList}" var="materialAttrGroup" varStatus="gvs">
														<tr>
															<c:forEach items="${materialAttrGroup.materialAttrValList}" var="pvValue" varStatus="pvs">
																<td>${pvValue}
																	<input name="materialAttrGroupList[${gvs.index}].materialAttrValList[${pvs.index}]" type="hidden" value="${pvValue}"/>
																</td>
															</c:forEach>
															<td><input class="form-control" name="materialAttrGroupList[${gvs.index}].amount" value="${materialAttrGroup.amount}" style="height: 28px;"/>
																<input name="materialAttrGroupList[${gvs.index}].id" value="${materialAttrGroup.id}" type="hidden" />
																<input name="materialAttrGroupList[${gvs.index}].materialAttrIds" value="${materialAttrGroup.materialAttrIds}" type="hidden" />
																<input name="materialAttrGroupList[${gvs.index}].materialAttrVals" value="${materialAttrGroup.materialAttrVals}" type="hidden" />
															</td>
															<!-- <td>
				                                                <button type="button" class="btn btn-danger table-btn-delete">删除</button>
				                                            </td> -->
														<tr>
													</c:forEach>
												</c:if>
		                                        </tbody>
		                                    </table>
		                                </div>
		                            </div>
		                        </div>
		                        <!-- <div class="form-group">
		                            <div class="col-lg-12 col-xs-12 text-center">
		                                <button type="button" class="btn btn-warning">添加规格</button>
		                                <button type="button" class="btn btn-info">动态刷新</button>
		                                <button type="button" class="btn btn-primary">保存</button>
		                            </div>
		                        </div> -->
		                </div>
		            </div>
		    </div>
		    <div class="row">
		        <div class="col-lg-12 col-xs-12">
		            <div class="compile-page-module border-defualt-module mg-b-10">
		                <h3>编辑页面模板<em class="em-tips-font">*</em>
			                <button type="button" id="addMaterialTemplate" class="btn btn-success" href="materialTemplate/manage/add/init.jhtml" >
								<span class="icon-plus"></span>&nbsp;添加模板
							</button>
						</h3>
		                <div class="complile-page-module-wrap">
		                    <table class="table table-bordered text-center">
		                        <thead>
		                        <tr>
		                            <th>规格名称</th>
		                            <th>操作</th>
		                        </tr>
		                        </thead>
		                        <tbody>
		                        <c:forEach items="${materialInfo.materialCarouselList}" var="materialCarousel">
		                        	<tr>
			                            <td>${materialCarousel.remark }</td>
			                            <td>
			                                <a  class="btn btn-primary" href="materialTemplate/manage/update/init.jhtml?id=${materialCarousel.id }&materialId=${materialCarousel.materialId}">编辑模板</a>
			                                <button type="button" class="btn btn-info modal-btn-links" data-imglinks="${materialCarousel.picUrl }" data-toggle="modal" data-target=".bs-example-modal-lg">预览</button>
			                            </td>
			                        </tr>
		                        </c:forEach>
		                        </tbody>
		                    </table>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-lg-12 col-xs-12">
		            <div class="compile-picimg-module border-defualt-module mg-b-10">
		                <h3>图文详情</h3>
		
		                <div class="compile-picimg-module-wrap mg-b-10">
		                    <textarea class="ckeditor" id="ckeditor_standard" name="content" rows="3">${materialInfo.content }</textarea>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-lg-12 col-xs-12">
		            <div class="compile-freight-module border-defualt-module mg-b-10">
		                <h3>运费设置<em class="em-tips-font">*</em></h3>
		
		                <div class="compile-freight-module-wrap">
		                        <div class="radio col-lg-1 col-xs-2 reset-form-name">
		                            <label>
		                                	绑定供应商
		                            </label>
		                        </div>
		                        <div class="form-group col-lg-10 col-xs-9">
		                        <select class="form-control" id="supplierId" name="supplierId" initValue="${materialInfo.supplierId }">
		                        </select>
	                        	</div>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-lg-12 text-center">
		            <div class="floot-submint-wrap">
		                <button class="btn btn-danger" type="submit">
												<span class="glyphicon glyphicon-floppy-disk"></span>
												&nbsp;保存
						</button>&nbsp;&nbsp;
		                <a class="btn btn-warning" href="materials/manage/init.jhtml?"><span class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
		            </div>
		        </div>
		    </div>
	    </form>
	</div>
	
	<!--模态框-->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                <h4 class="modal-title" id="myModalLabel">图片标题</h4>
	            </div>
	            <div class="modal-body">
	               <div class="modal-content-wrap text-center"> <img src="" alt=""/></div>
	            </div>
	        </div>
	    </div>
	</div>
	
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}'; </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/multipleChosen.js"></script>
	
	<!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	
    <script src="<%=path%>/js/cloud_design/materialEdit.js?v=1.0.1"></script> 
</body>
</html>

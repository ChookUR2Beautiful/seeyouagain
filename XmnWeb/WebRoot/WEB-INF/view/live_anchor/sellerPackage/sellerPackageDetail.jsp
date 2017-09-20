<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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

<title>添加专题</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<!-- 图片弹出样式 -->
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />

</head>

<body style="overflow-x: auto; overflow-y: auto; min-width: 1024px; background: #EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">套餐明细</h3>
		</div>
		
		<div class="panel-body" id="main">
		
		    <div class="panel panel-default">
				<div class="panel-body data">  
				
				<form class="form-horizontal" role="form" id="sellerPackageDetailForm">
				    <input type="hidden" name="multipShopToken" value="${multipShopToken}">
					<input type="hidden" id="id" name="id" value="${sellerPackageInfo.id}"/>
					<input type="hidden" id="status" name="status" value="${sellerPackageInfo.status}"/>
					<input type="hidden" id="highlyRecommended" name="highlyRecommended" value="${sellerPackageInfo.highlyRecommended}"/>
				
					<div class="form-group">
						<label class="col-md-3 control-label">套餐标题: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>${sellerPackageInfo.title}</h5>
						</div>
					</div>
					<div class="form-group">
						<!-- <div class="stitle">
						<h5>关联商户：<span style="color:red;">*</span></h5>
					</div> -->
						<label class="col-md-3 control-label">关联商户: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>${sellerPackageInfo.sellername}</h5>
						</div>
					</div>
	
					<div class="form-group">
						<label class="col-md-3 control-label">设置价格(元): <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>${sellerPackageInfo.sellingPrice}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">设置价格(鸟币): <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>${sellerPackageInfo.sellingCoinPrice}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">原价(元): <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>${sellerPackageInfo.originalPrice}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">套餐数量: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>${sellerPackageInfo.stock}</h5>
						</div>
					</div>
	                <hr />
	                
					<div class="form-group">
						<label class="col-md-3 control-label">分帐: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>
								<c:if test="${sellerPackageInfo.ledgerType==1}">固定金额分账</c:if>
								<c:if test="${sellerPackageInfo.ledgerType==2}">按比例分账</c:if>
							</h5>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">商户获得: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<div class="row">
								<div class="col-md-7">
									<h5>${sellerPackageInfo.ledgerRatio}
										<c:if test="${sellerPackageInfo.ledgerType==2}">
											<font id="percent" color="red">&nbsp;&nbsp;%</font>
										</c:if>
									</h5>
								</div>
							</div>
						</div>
					</div>
					<hr />
					
					<div class="form-group">
						<label class="col-md-3 control-label">销售时间: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<span style="margin-top: 25px"><fmt:formatDate value="${sellerPackageInfo.saleStartTime}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;-&nbsp;<fmt:formatDate value="${sellerPackageInfo.saleEndTime}" pattern="yyyy-MM-dd HH:mm"/></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">使用时间: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
						    <span><fmt:formatDate value="${sellerPackageInfo.useStartTime}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;-&nbsp;<fmt:formatDate value="${sellerPackageInfo.useEndTime}" pattern="yyyy-MM-dd HH:mm"/></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">不可用时间: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
						    <span><fmt:formatDate value="${sellerPackageInfo.forbidStartTime}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;-&nbsp;<fmt:formatDate value="${sellerPackageInfo.forbidEndTime}" pattern="yyyy-MM-dd HH:mm"/></span>
							<%-- <h5>${sellerPackageInfo.forbidStartTime}</h5> <h5>${sellerPackageInfo.forbidEndTime}</h5> --%>
						</div>
					</div>
					<hr />
					
					<div class="form-group">
						<label class="col-md-3 control-label">套餐图片: <span
							style="color: red;">*</span></label>						
						<c:if test="${!empty sellerPackageInfo.sellerPackagePicList}">
							<div class="col-sm-7">
							    <!--图片显示 -->
								<c:forEach items="${sellerPackageInfo.sellerPackagePicList}"  var="list" varStatus="status" >
						           <a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${list.picUrl}"  rel="slide" title="套餐图片" class="fancybox"  id = "picLink">
									   <img alt="套餐图片"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${list.picUrl}" style="width: 100px;height: 100px;">			
								   </a>
							    </c:forEach>
							</div>
						</c:if>
					</div>
	                <hr />
	
					<div class="form-group">
						<label class="col-md-3 control-label">套餐分类描述: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<textarea id="content" class="ckeditor" name="content" disabled="disabled" >${sellerPackageInfo.content}</textarea>
						</div>
					</div>
	
					<div class="form-group">
						<label class="col-md-3 control-label">温馨提示: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<textarea id="content" name="notice" rows="5" style="width: 100%;"  readonly="readonly" >${sellerPackageInfo.notice}</textarea>
						</div>
					</div>
	
					<div class="form-group">
						<label class="col-md-3 control-label">是否设为重点推荐: <span
							style="color: red;">*</span></label>
						<div class="col-md-7">
							<h5>
								<c:if test="${sellerPackageInfo.highlyRecommended==0}">否</c:if>
								<c:if test="${sellerPackageInfo.highlyRecommended==1}">是</c:if>
							</h5>
						</div>
					</div>
				</form>
				
			    </div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">  
                <!--  class="btn-group"  -->
					<div  align="center">
						<c:if test="${null!=btnAu['sellerPackage/manage/onLine'] && btnAu['sellerPackage/manage/onLine']}">
						    <button type="button" class="btn btn-success " id="handelOnLine" ><span class="icon-hand-up"></span>&nbsp;&nbsp;上架&nbsp;&nbsp;</button>
							<!-- <a type="button" class="btn btn-info" href="businessman/vipCard/addView.jhtml?isType=add"><span class="icon-plus"></span>&nbsp;上架</a> -->
								
						</c:if>
						<c:if test="${null!=btnAu['sellerPackage/manage/downLine'] && btnAu['sellerPackage/manage/downLine']}">
							<button type="button" class="btn btn-danger " id="handelDownLine" ><span class="icon-hand-down"></span>&nbsp;&nbsp;下架&nbsp;&nbsp;</button>
						</c:if>
					</div>
				</div>
			</div>

		</div>
		
	</div>
	
	<script type="text/javascript">contextPath = '${pageContext.request.contextPath}'</script>
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
	
	 <!--图片弹出  -->
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.js?v=2.1.5"></script>	
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712//source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>	
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/lib/jquery.mousewheel-3.0.6.pack.js"></script> 
		
	<!-- 图片缩放 -->   
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/shiftzoom.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/geodata.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/cvi_tip_lib.js"></script>

	<script src="<%=path%>/js/live_anchor/sellerPackage/sellerPackageDetail.js"></script>

	<script type="text/javascript">
		$(function() {
			$('.fancybox').fancybox();
		});
	</script>
	
</body>
</html>

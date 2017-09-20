<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>编辑分类导航信息</title>
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

<!-- 图片弹出样式 -->
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />

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
				<div class="panel-heading">详细信息</div>
				<div class="panel-body">
					
						<table class="table">
							<tr>
								<td class="sellerTitleCss">
									<h5>导航标题:</h5>
								</td>
								<td class="sellerContentCss">
									<input type="text" class="form-control"  name="title"  value="${categoryNavigate.title}" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss">
									<h5>行业类别:</h5>
								</td>
								<td class="sellerContentCss">
									<input type="hidden" name="category" value="${categoryNavigate.category}" id="category"/> 
									<input type="hidden" name="genre" value="${categoryNavigate.genre}" id="genre"/>
									<div class="input-group" id="tradeSelect" style="width : 103%" initValue="${categoryNavigate.genre}" ></div>
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss">
									<h5>导航位置:</h5>
								</td>
								<td class="sellerContentCss">
									<input type="text" class="form-control"  name="order"  value="${categoryNavigate.order}" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="sellerTitleCss">
									<h5>导航图片:</h5>
								</td>
								<td class="sellerContentCss" id="high">
									<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${categoryNavigate.img_high}"  rel="slide" title="导航图片" class="fancybox"  id="img_high">
										<img alt="高分辨率图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${categoryNavigate.img_high}"   style="width: 100px;height: 100px;">
									</a>
									<br>
									<small style="color:red;">高分辨率图片(640*160)</small>
								</td>
								<td class="sellerContentCss" id="middle">
									<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${categoryNavigate.img_middle}"  rel="slide" title="导航图片" class="fancybox"  id="img_middle">
										<img alt="高分辨率图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${categoryNavigate.img_middle}"   style="width: 100px;height: 100px;">
									</a>
									<br>
									<small style="color:red;">中分辨率图片(640*160)</small>
								</td>
								<td class="sellerContentCss" id="low">
									<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${categoryNavigate.img_low}"  rel="slide" title="导航图片" class="fancybox"  id="img_low">
										<img alt="高分辨率图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${categoryNavigate.img_low}"   style="width: 100px;height: 100px;">
									</a>
									<br>
									<small style="color:red;">低分辨率图片(640*160)</small>
								</td>
							</tr>
						</table>
						<div align="center">
							<button class="btn btn-warning" type="button" onclick="window.history.back();">
								<i class="icon-reply"></i>&nbsp;返回
							</button>
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
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
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
	
	<script type="text/javascript">
 	
   $(function(){
	   	$('.fancybox').fancybox();
	
		//图片为空时隐藏
		var img_high='${categoryNavigate.img_high}';//导航高清图
		if( img_high==""){
			$("#high").hide();				
		}else{
			$("#high").show();		
		} 
	   		
		var img_middle='${categoryNavigate.img_middle}';//导航中清图
		if( img_middle==""){
			$("#middle").hide();				
		}else{
			$("#middle").show();		
		} 
		
		var img_low='${categoryNavigate.img_low}';//导航低清图
		if( img_low==""){
			$("#low").hide();				
		}else{
			$("#low").show();		
		} 
		
		// 行业类别
		$("#tradeSelect").tradeLd({
			isDisabled : true,
			showConfig : [{name:"category",tipTitle:"请选择",width:'48%'},{name:"genre",tipTitle:"请选择",width:'49%'}]
		});
	});    
  </script>
</body>
</html>

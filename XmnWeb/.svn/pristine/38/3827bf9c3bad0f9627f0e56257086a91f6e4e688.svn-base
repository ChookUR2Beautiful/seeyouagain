<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>编辑微图助力模板页面</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/css/cloud_design/goodPage.css?v=1.0.1" rel="stylesheet"/>

</head>
<body>
	<div class="module-containner-wrap container-fluid">
	    <form class="form-horizontal container-hd-module mg-b-10" id="materialTemplateForm">
	    <input type="hidden" id="isType" name="isType" value="${isType }">
	    <input type="hidden" id="id" name="id" value="${pageInfo.id}">
	    <!-- 模板Id -->
	    <input type="hidden" id="templateId" name="templateId" value="${templateId }">
	    <br><br><br>
	    <div class="row">
	    		<div class="col-lg-12 col-xs-12 mg-b-10">
	                <div class="good-template-bg-module">
	                    <div class="reset-form-name"><em class="em-tips-font">*</em>页面宽高：</div>
	                    <div class="col-lg-2 col-xs-2 text-left">
	                        <input type="text" class="form-control"  id="goodimgWidth" name="width" placeholder="请输入页面宽度" value="${pageInfo.width }"/>
	                    </div>
	                    <div class="col-lg-2 col-xs-2 text-left">
	                        <input type="text" class="form-control"  id="goodimgHeight" name="height" placeholder="请输入页面高度" value="${pageInfo.height}"/>
	                    </div>
	                    <div class="col-lg-2 col-xs-2 text-left">
	                    	<input type="hidden" class="form-control" id="imgSize" name="imgSize"  value="">
	                        <button type="button" class="btn btn-primary" id="btn-imgPreserve">应用</button>
	                    </div>
	                </div>
	            </div>
	            <div class="col-lg-12 col-xs-12 mg-b-10">
	                <div class="good-template-bg-module">
	                    <div class="reset-form-name"><em class="em-tips-font">*</em>页面背景：</div>
	                    <div class="col-lg-12 col-xs-12 text-left">
	                        <input type="hidden" class="form-control" id="backgroundImage" name="backgroundImage"  value="${pageInfo.backgroundImage }">
							<div id="backgroundImageDiv"></div>
	                    </div>
	                    <div class="col-lg-12 col-xs-12 uploadings-desc">（建议尺寸：750*340px;格式：gif、png、jpg、大小：2Mb以内）</div>
	                </div>
	                
	            </div>
	            <div class="col-lg-12 col-xs-12">
	                <div class="good-template-picimg-module">
	                    <div class="good-template-info-picimg">
	                        <div class="good-template-info-picimg-wrap" style="background:  no-repeat; background-size: 100% 100%;">
	
	
	                        </div>
	                    </div>
	
	                    <div class="col-lg-12 col-xs-12">
	                        <div class="good-template-info-addmodule">
	                            <div class="good-template-info-addmodule-hd">
	                                <button type="button" class="btn btn-primary" id="addtextframe">添加文案框架</button>
	                                <button type="button" class="btn btn-success" id="addimgframe">添加图片框架</button>
	                            </div>
	                            <div class="good-template-info-addmodule-bd">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	    </div>
	    <div class="row">
	        <div class="col-lg-12 col-xs-12 text-center">
	            <div class="floot-submint-wrap">
	                <button type="button" id="cancelBtn" class="btn btn-warning">取消编辑</button>
	                <button type="submit" class="btn btn-primary">提交保存</button>
	            </div>
	        </div>
	    </div>
	    </form>
	</div>
	
	
	<script type="text/javascript">   
		contextPath = '${pageContext.request.contextPath}'; 
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/cloud_design/DragResize.js"></script>
    <script src="<%=path%>/js/business_statistics/saasPageEdit.js"></script> 
</body>
</html>
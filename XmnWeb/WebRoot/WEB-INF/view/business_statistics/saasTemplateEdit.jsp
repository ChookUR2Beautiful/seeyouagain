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
<title>编辑SaaS模板信息</title>
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
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="example">
		<form id="editForm">
			<c:if test="${!empty templateInfo}">
							<input type="hidden" name="id" id="id" value="${templateInfo.id}">
			</c:if>
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
						<table class="table" style="text-align: center;width:50%">
							<tr>
								<td class="sellerTitleCss">
									<h5>模板名称:<span style="color:red;">*</span></h5></td>
								<td >
									<input type="text"  class="form-control" name="title" value="${templateInfo.title}">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>模板标签:<span style="color:red;">*</span></h5></td>
								<td >
									<select class="form-control" id="templateTags" name="templateTags" data-placeholder="请选择关联标签" initValue=${ templateInfo.relationTagIds}>
									</select>
									<!-- 将多选框数组值转化为字符串 -->
									<input type="hidden" id="templateTagVals" name="templateTagVals">
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>缩略图:<span style="color:red;">*</span></h5></td>
								<td class="sellerContentCss">
									<input type="hidden" class="form-control" id="coverImage" name="coverImage"  value="${templateInfo.coverImage}">
												<div id="coverImageDiv"></div>
								</td>
							</tr>
							
							<tr>
								<td class="sellerTitleCss">
									<h5>推荐排序:<span style="color:red;">*</span></h5></td>
								<td >
									<input type="text" class="form-control" id="serialNo" name="serialNo" data-placeholder="请填写推荐排序" value=${ templateInfo.serialNo}>
								</td>
							</tr>
							
						</table>
						
				</div>
			</div>
			
			<div class="panel panel-primary">
				<div class="panel-heading">模板页面</div>
					<div class="panel-body">
						<div class="col-lg-12 col-xs-12">
				            <div class="compile-page-module border-defualt-module mg-b-10">
				                <h3>
					                <button type="button" id="addPageBtn" class="btn btn-success" href="saasPage/manage/add/init.jhtml" >
										<span class="icon-plus"></span>&nbsp;添加页面
									</button>
								</h3>
				                <div class="complile-page-module-wrap">
				                    <table class="table table-bordered text-center">
				                        <thead>
				                        <tr>
				                            <th>页数</th>
				                            <th>模板图片</th>
				                           <!--  <th>模块数</th> -->
				                            <th>操作</th>
				                        </tr>
				                        </thead>
				                        <tbody>
				                        <c:forEach items="${templateInfo.pageList}" var="pageInfo" varStatus="pageSerial">
				                        	<tr>
					                            <td>第${pageInfo.page }页</td>
					                            <td><img style="width:50px;height:50px;" src="${pageInfo.backgroundImageStr }"/></td>
					                            <%-- <td>${pageInfo.moduleSize }</td> --%>
					                            <td>
					                                <a  class="btn btn-primary" href="saasPage/manage/update/init.jhtml?id=${pageInfo.id }">编辑页面</a>
					                            </td>
					                        </tr>
				                        </c:forEach>
				                        </tbody>
				                    </table>
				                </div>
				            </div>
				        </div>
					</div>
			</div>
			<div align="center">
							<button class="btn btn-danger" type="submit"> <i class="icon-save"></i>&nbsp;保 存 </button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" onclick="window.history.back();"> <i class="icon-reply"></i>&nbsp;取消 </button>
			</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/multipleChosen.js"></script>
	<script src="<%=path%>/js/business_statistics/saasTemplateEdit.js"></script>
	
</body>
</html>

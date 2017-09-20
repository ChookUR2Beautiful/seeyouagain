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
<title>添加V客学堂资料</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<style type="text/css">
	/**上传插件容器样式 **/
	#filePicker .box{
		width:100%  !important;
	}
	
	td {
		border-bottom: none!important;
	}
	
	*{margin:0;padding:0;}
	.list-box{
		font-size: 0;
		overflow: auto;
	    max-height: 480px;
	    margin-bottom: 20px;
		
	}
	.list-box .box{
		width: 320px;
	    display: inline-block;
	    position: relative;
	    margin-right:15px;
	    margin-bottom:15px;
	    border: 1px solid #d6d6d6;
   		padding: 10px;
   		height: 420px;
   		vertical-align: top;
	}
	.list-box .box .img-box{
		height: 410px;
   		width: 100%;
	}
	.img-box img{
		width:40px;
		position:absolute;
		right:0;
		top:0;
		z-index:100;
	}
	.img-box img.hide{
		display:none;
	}
	.list-box .box .img-list{
		position: absolute;
	    top: 342px;
	    left: 0;
	    width: 100%;
	}
	.list-box .box .img-list img{
		width: 50px;
   		height: 50px;
	    margin-left: 10px;
	}
	.list-box .box p{
		margin:0;
		font-size: 14px;
	    overflow: hidden;
	    margin-top: 10px;
	}
	.list-box .box p.num{
		position: absolute;
	    width: 100%;
	    height: 30px;
	    line-height: 30px;
	    text-align: center;
	    left: 0;
	    top: 0;
	    background: rgba(0,0,0,0.6);
	    color: #fff;
	    margin: 0;
	}
	.list-box .box p span{
		float: right;
	}
	.list-box .box .btn-box{
		font-size:0;
	}
	.list-box .box .btn-box a{
		display: inline-block;
		width:141px;
		height:35px;
		line-height:35px;
		text-align:center;
		font-size: 14px;
		border: 1px solid #999;
   		color: #333;
   		cursor: pointer;
	}
	.list-box .box .btn-box a.pass-btn{
		margin-right:15px;
	}
	.list-box .box .btn-box a.restrict-btn{
		width:100%;
	}
	.list-box p.nodata-hint{
		font-size: 16px;
	    text-align: center;
	    height: 100px;
	    line-height: 100px;
	}
	
	.good-table-imgmodule{
		padding:0;
		height: auto;
	}
	.totalTips{
		position: absolute;
	    top: -10px;
	    right: -10px;
	    background: #ff0303;
	    border-radius: 50%;
	    padding: 3px 6px;
	    font-size: 12px;
	    color: #fff;
	    z-index: 10;
	}
		
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">V客学堂-图片素材</div>
				<div class="panel-body">
					<form  id="editForm" class="form-horizontal">
					<input type="hidden" name="vstarContentToken" value="${vstarContentToken}">
					<input type="hidden" id="isType" name="isType" value="${isType}">
					<input type="hidden" id="id" name="id" value="${vstarContent.id}">
					<!-- 资源内容类型,1内容资料,2 H5活动,3图片素材 -->
					<input type="hidden" name="contentType" value="3">
						<table class="table" style="text-align: center; width:80%;" border='0'>
							<tr >
								<td style="width:20%;" >
									<h5>教学标题:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="title" placeholder="教学标题"
									value="${vstarContent.title}">
								</td>
								<td></td>
							</tr>
							
							<tr >
								<td style="width:20%;" >
									<h5>副标题:<span style="color:red;">*</span></h5>
								</td>
								<td ><input type="text"
									class="form-control" name="viceTitle" placeholder="副标题"
									value="${vstarContent.viceTitle}">
								</td>
								<td></td>
							</tr>
							<tr >
								<td style="width:20%;" >
									<h5>上传图片:<span style="color:red;">*</span></h5>
								</td>
								<td >	
										<h5 id="addTips" style="margin-left:-770px;display:none;">保存基础信息后才可上传图片</h5>
										<button type="button" id="toAddBtn"  class="btn btn-info" data-type="ajax" style="margin-left:-888px;display:none;"
											data-title="添加相册" data-url="VstarContent/manage/add/vstarImageAddBatch/init.jhtml"
											data-toggle="modal" data-width="1000px"> &nbsp;添加
										</button>
								</td>
								<td></td>
							</tr>
							
							
						</table>
						
						<div class="panel panel-default" id="imgsDiv" style="display:none;">
							<div class="panel-body data">
								<div class="btn-group" style="margin-bottom: 5px;">
										<c:if test="${!empty btnAu['VstarContent/manage/update'] && btnAu['VstarContent/manage/update']}">
											<button type="button" class="btn btn-success" id="checkAll" >&nbsp;全选</button>
										</c:if>
										<c:if test="${!empty btnAu['VstarContent/manage/update'] && btnAu['VstarContent/manage/update']}">
										<button type="button" class="btn btn-danger" id="delete" >&nbsp;删除</button>
										</c:if>
								</div>
								<div id="vstarImgList"></div>
							</div>
						</div>
						
						<div align="center">
								<button class="btn btn-danger" type="submit" id="submitBtn" ><i class="icon-save"></i>&nbsp;保  存</button>&nbsp;&nbsp;
								
								<button class="btn btn-warning" type="button"  onclick="window.history.back();">
									<i class="icon-reply"></i>&nbsp;取消
								</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}'; </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script src="<%=path%>/js/vstar/vstarContentImgEdit.js"></script> 
</body>
</html>

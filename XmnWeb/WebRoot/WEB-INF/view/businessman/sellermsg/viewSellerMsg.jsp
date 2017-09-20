<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>添加商家信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<jsp:include page="../../common.jsp"></jsp:include>
<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script> 
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/js/businessman/sellermsg/viewSellerMsg.js"></script>
<script src="<%=path%>/js/common/IDCard.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script> 
<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary" style="border-bottom: 0px;">
			    <input type="hidden"   id = "isType" value="${param.isType}">
			    <input type="hidden"   id = "area" value="${sellerMsg.area}">
			    <input type="hidden"   id = "city" value="${sellerMsg.city}">
			    <input type="hidden"   id = "province" value="${sellerMsg.province}">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
				<form  id="editsellerMsgForm" role="form">
				    <input type="hidden"  id ="msgId" name="msgId" value="${sellerMsg.msgId}">
					<table class="table" style="text-align: left;" >
						<tr>
							<td class = "sellerTitleCss">
								<h5>标题:</h5> 
							</td>
							<td colspan = "3">
							    <h5><span>${sellerMsg.title}</span></h5>
							</td>
						</tr>
						<tr>
							<td class = "sellerTitleCss" >
									<h5>副标题：</h5> 
							</td>
							<td colspan = "3">
									<h5><span>${sellerMsg.subtitle}</span></h5>
							</td>
						</tr>
						<tr>
							<td  class="sellerTitleCss">
									<h5>是否即时推送:</h5> 
							</td>
							<td class="sellerContentCss">
								<h5>
									<input  type="radio" name="isSendImmediate" disabled="disabled" checked="checked" value="1" ${sellerMsg.isSendImmediate==1?"checked":""}>是
									&nbsp;&nbsp;&nbsp;&nbsp;
							        <input type="radio" name="isSendImmediate" disabled="disabled" value="0" ${sellerMsg.isSendImmediate==0?"checked":""} >否
							     </h5>
							</td>
						</tr>
						<tr>
							<td  class="sellerTitleCss" style="display: none;" id="sendDateId">
									<h5>开始时间:</h5> 
							</td>
							<td class="sellerContentCss" style="display: none;" id="sendDateContentId">
							   <h5><span><fmt:formatDate value="${sellerMsg.dateSend}" pattern="yyyy-MM-dd HH:mm"/></span></h5>
							</td>
							
							<td  class="sellerTitleCss" style="display: none;" id="endId">
									<h5>结束时间:</h5> 
							</td>
							<td class="sellerContentCss" style="display: none;" id="endContentId">
							 	<h5><span><fmt:formatDate value="${sellerMsg.dateEndSend}" pattern="yyyy-MM-dd HH:mm"/></span></h5>
							</td>
						</tr>
						
						<tr>
							<td class = "sellerTitleCss" ><h5>消息图标：</h5></td>
							<td class="sellerContentCss">
								<img alt="商家图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${sellerMsg.urlMessageImg}"   style="width: 100px;height: 100px;">
						    </td>
						   
						   <td class = "sellerTitleCss" ><h5>文章焦点图标：</h5></td>
							<td class="sellerContentCss">
								<img alt="商家图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${sellerMsg.urlArticleFocusImg}"   style="width: 100px;height: 100px;">
						   </td>
						</tr>
						
						
						<tr>
							<td class = "sellerTitleCss">
								<h5>推送对象:</h5> 
							</td>
							<td class = "sellerContentCss" style="text-align: left" colspan = "2">
								<div class="input-group" id="ld" style="width:100%" ></div>
							</td>
							<td class = "sellerTitleCss" style="text-align: left;">
								<select class="form-control"  id="zoneid" name ="zoneid" disabled="disabled" style = "width:100%;" initValue="${sellerMsg.zoneid}">
						        </select>
							</td>
							<td style="text-align: left">
								<button type="button" class="btn btn-default"  id= "viewSeller" ><span class="icon-plus"></span>&nbsp;查看</button>
							</td>
						</tr>
							
						<tr>
							<td style="width:80px;" >
									<h5>文章内容：</h5> 
							</td>
							<td colspan = "3">
								<textarea id="content" class="form-control" rows="10" name="content" disabled="disabled" >${sellerMsg.content}</textarea>
							</td>
						</tr>
						</table>
						<!-- <div align="center">
								<button class="btn btn-warning" type="button" id="backId"><i class="icon-reply"></i>&nbsp;返回</button>
						</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

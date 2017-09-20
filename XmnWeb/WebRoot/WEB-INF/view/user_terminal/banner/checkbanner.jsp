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
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
    <input type="hidden" id="isType" name="isType" value="${isType}" />
	<form class="form-horizontal" role="form" id="billDetailForm">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden" id="checkBannerStyle" value="${banner.bannerStyle}" />
		<table width="100%" >
			<tbody>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;导航图编号:</h4></th>
					<td colspan="3" style="width:33%;"><small>${banner.id}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;导航图位置信息:</h4></th>
					<td style="width:33%;"><small>${banner.positionStr}</small></td>
					<th style="width:17%;"><h4>&nbsp;展示风格:</h4></th>
					<td style="width:33%;"><small>${banner.bannerStyleStr}</small></td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;导航图描述:</h4></th>
					<td style="width:33%;"><small>${banner.remarks}</small></td>
					<th style="width:17%;"><h4>&nbsp;上线状态:</h4></th>
					<td style="width:35%;">${banner.statusStr}</td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;全国:</h4></th>
					<td style="width:33%;"><small>${banner.isAll == '1'?'是':'否'}</small></td>
					<th style="width:17%;"><h4>&nbsp;省:</h4></th>
					<td style="width:35%;">${banner.provinceStr == null?'-':banner.provinceStr}</td>
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;市:</h4></th>
					<td style="width:33%;"><small>${banner.cityStr == null?'-':banner.cityStr}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;创建时间:</h4></th>
					<td style="width:33%;"><small>${banner.createTimeStr}</small></td>	
				</tr>
				<tr>
					<th style="width:17%;"><h4>&nbsp;&nbsp;更新时间:</h4></th>
					<td style="width:35%;">${banner.updateTimeStr}</td>
				</tr>
				<tr>
				   	<th style="width:17%;"><h4>&nbsp;&nbsp;导航图:</h4></th>
					<td style="width:33%;">
						<div id="pic1Div" style="display:block;">
								<div class="input-group">
						           <img class="img-thumbnail" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${banner.picurl1}" style="width: 100px;height: 100px;"/>
						           <div id="pic1" style="width: 130px; float: left;margin-left: 40%"></div>
					            </div>
					            <span>导航图内容类型:</span>${banner.typeStr1}<br>
						        <span>导航图内容:</span>${banner.content1}<br>
						        <div id="sortid1"><span>导航图排序:</span>${banner.sort1}</div>
						        <span id="logRequired1">是否需要登录:</span>
						        	<c:if test="${banner.logRequired1 == 0}">否</c:if>
						        	<c:if test="${banner.logRequired1 == 1}">是</c:if>
						</div>
					</td>
					<th style="width:17%;"></th>
					<td style="width:33%;">
						<div id="pic2Div" style="display:block;">
							<div class="input-group">
								<img class="img-thumbnail" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${banner.picurl2}" style="width: 100px;height: 100px;"/>
							    <div id="pic2" style="width: 130px; float: left;margin-left: 40%"></div>
					        </div>
				            <span>导航图内容类型:</span>${banner.typeStr2}<br>
						    <span>导航图内容:</span>${banner.content2}<br>
						    <div id="sortid2"><span>导航图排序:</span>${banner.sort2}</div>
						    <span id="logRequired2">是否需要登录:</span>
								<c:if test="${banner.logRequired2 == 0}">否</c:if>
								<c:if test="${banner.logRequired2 == 1}">是</c:if>
						</div>
					</td>	
				</tr>
				</tbody>
				</table>
	 	<div style="margin: 20px auto 0px; text-align: center;">
	 	 <button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
	 	</div>
	 </form>
</body>
<script src="<%=path%>/js/user_terminal/banner/checkbanner.js"></script>
</html>
<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
</script>

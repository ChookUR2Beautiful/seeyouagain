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
				    <td style="width:20%;">
				    	<div id="pic1Div" style="display:block;">
					        <div class="input-group">
					           <img class="img-thumbnail" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${bxmer.avatar}" style="width: 100px;height: 100px;"/>
					           <div id="pic" style="width: 130px; float: left;margin-left: 40%"></div>
				             </div>
						</div>
				    </td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;寻蜜客姓名:</h4></th>
					<td style="width:30%;"><small>${bxmer.name}</small></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;手机号码:</h4></th>
					<td style="width:30%;"><small>${bxmer.phoneid}</small></td>
				</tr>
				<tr>
					<td style="width:20%;"></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;性别:</h4></th>
					<td style="width:30%;"><small>${bxmer.sexStr}</small></td>
					<th style="width:17%;"><h4>&nbsp;年龄:</h4></th>
					<td style="width:30%;"><small>${bxmer.age}</small></td>
				</tr>
				<tr>
					<td style="width:20%;"></td>
					<th style="width:17%;"><h4>&nbsp;邮箱:</h4></th>
					<td style="width:30%;"><small>${bxmer.email}</small></td>
					<th style="width:17%;"><h4>&nbsp;微信号:</h4></th>
					<td style="width:30%;"><small>${bxmer.weixinid}</small></td>
				</tr>
				<tr>
					<td style="width:20%;"></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;加入时间:</h4></th>
					<td style="width:30%;"><small>${bxmer.sdateStr}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;</h4></th>
					<td style="width:10%;"></td>
				</tr>
				<tr>
					<td style="width:20%;"></td>
					<th style="width:17%;"><h4>&nbsp;&nbsp;寻蜜客编号:</h4></th>
					<td style="width:30%;"><small>${bxmer.uid}</small></td>				
					<th style="width:17%;"><h4>&nbsp;&nbsp;成就:</h4></th>
					<td style="width:30%;"><small>${bxmer.achievement}</small></td>
				</tr>
				</tbody>
				</table>
	 	<div style="margin: 20px auto 0px; text-align: center;">
	 	 <button type="reset" class="btn btn-success" data-dismiss="modal"><span class="icon-reply-all"></span>  返 回  </button>
	 	</div>
	 </form>
</body>
</html>
<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
</script>

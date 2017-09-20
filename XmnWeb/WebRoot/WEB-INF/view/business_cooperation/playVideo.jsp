<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<%=path%>/js/ckplayer6.6/ckplayer/ckplayer.js" charset="utf-8"></script>
</head>
<body>
	<embed src="<%=path%>/js/ckplayer6.6/ckplayer/ckplayer.swf" flashvars="f=<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${vurl}" quality="high" width="100%" height="400" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
</body>
</html>

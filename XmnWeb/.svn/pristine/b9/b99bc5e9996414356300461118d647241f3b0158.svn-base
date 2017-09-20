<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>直播聊天室</title>
    <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=path%>/resources/chatRoom/normalize.css"/>
    <link rel="stylesheet" href="<%=path%>/resources/chatRoom/infoframe.css"/>

	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>
<body>

<div class="module-containner-wrap container-fluid">
	   <input type="hidden" id="liveRecordId" value="${liveRecordId}" />
	   <input type="hidden" id="maxId" value="${maxId}" />
	   <div class="row">
		       <div class="col-md-5 col-lg-5 col-sm-5 col-xs-4">
				       <div class="horse-info-module">
				        <div class="horse-info-position">
				        <!-- 聊天室消息内容区域 -->
				            <div class="horse-info-wrap">
				            	<!-- <div class="horse-info-item">
				                    <div class="horse-info-content"><span class="horse-info-name">用户名称</span><span
				                            class="horse-info-text">内容test..</span>
				                    </div>
				                </div> -->
				            </div>
				        </div>
				    </div>
		   	   </div>
		       <div class="col-md-7 col-lg-7 col-sm-7 col-xs-8">
			       <div class="add-usre-module">
				        <div class="add-user-wrap">
				            <div class="add-user-header">
				                <span class="user-header-box" data-toggle="modal" data-target="#myModal"><i></i>添加用户</span>
				            </div>
				            <div class="add-user-list">
				                <!-- <div class="add-user-item">
				                    <div class="add-user-name">用户昵称：</div>
				                    <div class="add-user-content">
				                        <div class="user-content-btn"><span class="add-send btn-item">发送</span><span class="add-detele btn-item">删除</span></div>
				                        <input type="text" class="user-content-ingput"/>
				                    </div>
				                </div> -->
				            </div>
				        </div>
			    </div>
		    </div>
	   </div>
</div>
<!--模态框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 5%;" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">请输入手机号：</h4>
            </div>
            <div class="modal-body">
                <p><input type="text" class="modal-input-module"/></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addUserList">确定</button>
            </div>
        </div>
    </div>
</div>

	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/js/live_anchor/liveChatRoom.js"></script>
</body>
</html>
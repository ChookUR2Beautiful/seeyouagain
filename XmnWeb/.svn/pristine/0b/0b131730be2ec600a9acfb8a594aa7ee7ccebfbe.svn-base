<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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
<title>合作商业务员</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
    rel="stylesheet">
<link
    href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
    rel="stylesheet">
</head>

<body
    style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
    class="doc-views with-navbar">
    <div id="prompt"
        style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
    <div class="panel">
        <div class="panel-body">
            <form class="form-horizontal" role="form" method="post"
                id="searchForm">
                <input type="hidden" id="fastfdsHttp"  value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
        	    <input class="form-control" type="hidden" id="sellerid" name ="sellerid" value="${sellerid}" />
        	     <input class="form-control" type="hidden" id="sellername" name ="sellername" value="${sellername}" />
                <table style="width:100%;">
                    <tbody>
                        <tr>
                             <table style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="width:5%;"><h5>达人姓名:</h5></td>
                            <td style="width:18%;"><input type="text" class="form-control"  id="name" name="name" style = "width:90%;"/></td>
                            <td style="width:5%;"><h5>评论内容:</h5></td>
                            <td style="width:18%;"><input type="text" class="form-control" id="content" name="content" style = "width:90%;"/></td>
                            <td colspan="2" class="col-md-3">
                                <div class="submit text-left">
                                    <input class="submit radius-3"
                                        style="width:49.5%;margin-right:0;" type="button" value="查询全部"
                                        data-bus='query' /> <input class="reset radius-3"
                                        style="width:49.5%;margin-right:0;" type="reset" value="重置全部"
                                        data-bus='reset' />
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body data">
            <div class="btn-group" style="margin-bottom: 5px;">
                <c:if test="${not empty btnAu['businessman/expert/add']}">
                    <a type="button" class="btn btn-success" id="addExpertComment"
                        href="businessman/expert/add/init.jhtml?isType=add"><span
                        class="icon-plus"></span>添加</a>
                </c:if>
                <c:if test="${!empty  btnAu['businessman/expert/sellerlabel']}">
					<a style="padding: 4px 10px;" type="button" class="btn" id="sellerlabel" href="businessman/expert/sellerlabel/init.jhtml?sellerid=${sellerid}&sellername=${sellername}">商家标签管理&nbsp;
					<span id="lableCount"  class="label label-badge label-success"></span></a>
				</c:if>
            </div>
            <div id="expertCommentList"></div>
        </div>
    </div>
    <script type="text/json" id="permissions">{update:'${btnAu['businessman/expert/update']}',del:'${btnAu['businessman/expert/delete']}'}</script>
    <jsp:include page="../../common.jsp"></jsp:include>
    <script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
    <script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/js/businessman/expertmanage/expertManageList.js"></script>
    <script src="<%=path%>/ux/js/scrollTablel.js"></script>
</body>
</html>

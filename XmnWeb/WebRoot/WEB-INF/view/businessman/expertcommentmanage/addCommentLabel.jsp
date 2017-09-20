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
<title>添加连锁店信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<link href="<%=path%>/resources/webuploader/webuploader.css"
    rel="stylesheet">
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
    <div id="main">
        <div class="example">
            <div class="panel panel-primary">
                <div class="panel-heading">基本信息</div>
                <div class="panel-body">
                    <form id="addCommentLabelForm">
                        <input type="hidden" name="sellerSubsidyToken"
                            value="${sellerSubsidyToken}"> <input type="hidden"
                            id="isType" name="isType" value="${isType}" /> <input
                            type="hidden" id="issuestatus" name="issuestatus" value="0" /> 
                            <input type="hidden" id="id" name="id" value="${commentLabel.id}" />
                        <table class="table" style="text-align: center;" >
                        <tr>
                            <td>
                                <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家名称:</h5> 
                            </td>
                            <td>
                            	<input type="hidden" class="form-control" id="sellerid" name="sellerid" value="${sellerid}">
                                <input type="text" class="form-control" id="sellername" name="sellername" value="${sellername}">
                            </td>

                            <td>
                                    <h5>标签名称：</h5> 
                            </td>
                            <td>
                                  <input type="text" class="form-control" id="labelname" name="labelname"   value="${commentLabel.labelname}">
                            </td>
                            <td>
                                    <h5>点赞数：</h5> 
                            </td>
                            <td>
                                   <input type="text" class="form-control" id="znum" name="znum"   value="${commentLabel.znum}">
                           </td>
                        </tr>
                        <tr>
                            <td colspan="6" align="center">
                                <hr>
                        <button class="btn btn-danger" type="submit">
                                <i class="icon-save"></i>&nbsp;保 存
                            </button>
                            &nbsp;&nbsp;
                            <button class="btn btn-warning" type="button" id="cancelId">
                                <i class="icon-reply"></i>&nbsp;取消
                            </button>

                            </td>
                        </tr>
                        </table>

                    </form>
                </div>
            </div>
        </div>
    </div>
     <script type="text/javascript">
        contextPath = '${pageContext.request.contextPath}'
    </script>
    <jsp:include page="../../common.jsp"></jsp:include>
    <script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script
        src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
    <script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script>
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/js/businessman/expertmanage/addCommentLabel.js"></script>
</body>
</html>

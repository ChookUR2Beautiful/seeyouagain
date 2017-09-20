<%@ page language="java" import="com.xmniao.xmn.core.util.FastfdsConstant" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>My JSP 'list.jsp' starting page</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
  <link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
  <link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
  <link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
        style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
        class="doc-views with-navbar">
<input type="hidden" id="fastfdsHttp" value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>"/>
<div class="panel">
  <div class="panel-body">
    <table style="width:100%;">
      <tbody>
      <tr>
        <td style="width:18%;">  选择商品：</td>
        <td>

          <div class="col-md-7" style="width:25%;">
            <select class="form-control" id="brandId" name="codeId"
                    style="width:41%;float:left" initValue="${activity.codeId}"></select>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
</div>
<div class="panel">
  <div class="panel-body">
    <div class="panel-body data">
      <div id="levelList"></div>
    </div>
  </div>
</div>
</body>
<script type="text/json" id="permissions">{
	  edit:'${ btnAu['groupLevel/update']}',
	}


</script>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script
        src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/ux/js/scrollTablel.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<%--<script type="text/javascript" src="<%=path%>/js/live_anchor/groupLevelList.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/live_anchor/leaderPerformance.js"></script>
</html>

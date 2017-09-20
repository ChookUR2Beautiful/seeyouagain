<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"
		value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="brandForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>商户名称:</h5></td>
							<td style="width:18%;"><input type="text"
								class="form-control" name="sellername" style="width:90%;" /></td>
							<td style="width:5%;"><h5>主播手机号:</h5></td>
							<td style="width:18%;"><input type="text"
								class="form-control" name="liverPhone" style="width:90%;" /></td>
							</tr>
							<tr>
							<td style="width:5%;"><h5>主播昵称:</h5></td>
							<td style="width:18%;"><input type="text"
								class="form-control" name="liverName" style="width:90%;" /></td>
						
						
							<td style="width:5%;"><h5>状态:</h5></td>
							<td style="width:18%;"><select class="form-control"
								name="reviewState" id="reviewState">
									<option value="">--请选择--</option>
									<option value="0">待审核</option>
									<option value="1">已通过</option>
									<option value="2">已拒绝</option>
									<option value="3">已下架</option>
							</select>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' style="width:43%;flaot:left;margin:0 2%;" />
									<input class="reset radius-3" type="reset" value="重置全部"
										data-bus='reset' style="width:43%;flaot:left;margin:0 2%;" />
								</div>
							</td>
							<td colspan="2" style="width:18%;"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	</div>
	<div class="panel">
		<div class="panel-body">
			<div class="panel-body data">
				<div id="commentList"></div>
			</div>
		</div>
	</div>
	
<div class="modal fade" id="query">	
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
        <h4 class="modal-title">审核提示</h4>
      </div>
      <div class="modal-body">
        <p>是否拒绝商户点评原因</p>
        <input type="hidden" id="experienceCommentId" />
        <textarea class="form-control" rows="" cols="" id="refuseRemark"></textarea>
      </div>
      <div                                                                                                                                                                                                                                                                                                                                                       v class="modal-footer" style="text-align: center;">
        <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
        <button type="button" class="btn btn-primary" id="queryClick" >是</button>
      </div>
    </div>
  </div>
</div>
	
</body>
<script type="text/json" id="permissions">{
	  recommend:'${ btnAu['businessman/experience/comment/updateIsRecommend']}',
	  reviewState:'${ btnAu['businessman/experience/comment/updateReviewState']}',
	  edit:true
	}</script>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script
	src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/ux/js/scrollTablel.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/businessman/experienceCommentList.js"></script>
<script type="text/javascript">
	 var sellerType = "${sellerType}";
	 var sellerid = "${sellerid}";
</script>
</html>


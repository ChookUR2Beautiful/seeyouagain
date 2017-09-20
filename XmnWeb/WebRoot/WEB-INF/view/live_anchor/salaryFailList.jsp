<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>直播分账记录管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<table class="table table-hover table-bordered table-striped info">
				<div
					style="background:#EED8D8; color:#703636; font-size:16px; line-height:40px;text-align:center">
				</div>
				<thead>
					<tr>
						<th style="width:130px;">主播信息</th>
						<th style="width:130px;">统计月份</th>
						<th style="width:130px;">失败信息</th>
					</tr>
				</thead>
				<tbody id="salaryFailList">
				</tbody>
			</table>
		</div>
		<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="button" id="submit" class="btn btn-success">
					<span class="icon-ok"></span> 全部重新生成
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
	</div>
	<div class="modal fade" id="myModal">
  <div class="modal-dialog"  style="width: 500px;height: 200px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
        <h4 class="modal-title">标题</h4>
      </div>
      <div class="modal-body">
        <p id="msg">主题内容...</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">我知道了</button>
      </div>
    </div>
  </div>
</div>
	<script src="<%=path%>/js/live_anchor/salaryFailList.js"></script>
</body>
</html>

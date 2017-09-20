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
<title>庄园会员管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link  href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/> 
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
.submit{text-align: left;}
</style>
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" id="uidViewJunior" name="uidViewJunior" value="${uidViewJunior}">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员昵称：</h5></td>
								<td style="width:29%;">
									<input type="text" class="form-control" name="nickname" value="" style = "width:60%;"/>
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员手机号：</h5></td>
								<td style="width:29%;">
									<input type="text" class="form-control" name="phone" value="" style = "width:60%;"/>
								</td>
								
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['liveMember/manage/export']}">
						<button type="button" id="exportAnchor" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
				<c:if test="${btnAu['manorMember/manage/usrChain']}">
						<button type="button" class="btn btn-success" data-type="ajax"
										data-url="manorMember/manage/usrChain/init.jhtml"
										data-toggle="modal" data-width="950px">
										<span class="icon-plus"></span>&nbsp;绑定会员关系
									</button>
				</c:if>
				<c:if test="${btnAu['manorMember/manage/usrChain']}">
						<button type="button" class="btn btn-success" data-type="ajax"
										data-url="manorMember/manage/usrChain/activate/init.jhtml"
										data-toggle="modal" data-width="950px">
										<span class="icon-plus"></span>&nbsp;激活庄园
									</button>
				</c:if>
			</div>
			<div id="anchorList" class="good-table-wrap"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			update:'${ btnAu['manorMember/manage/update']}'
		}
	</script>
	
	<!-- modal start -->
    <div class="modal fade" id="lowerLevelModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">仓库储存收益</h4>
				</div>
				<!-- style="height: 260px;" -->
				<div class="modal-body example" >
					<form class="form-horizontal" id="lowerLevelForm">
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-10">
								<table class="table table-bordered">
									<thead style="background-color: beige;">
										<tr class="text-center">
											<th class="text-center"><div class="header">用户编号</div></th>
											<th class="text-center"><div class="header">用户名称</div></th>
											<th class="text-center"><div class="header">当前贡献花朵</div></th>
											<th class="text-center"><div class="header">累计贡献花朵</div></th>
										</tr>
									</thead>
			
									<tbody id="lowerLevelTB">

									</tbody>
								</table>
							</div>
						</div>
						
						<!-- 结束 -->
						<div class="modal-footer">
							<!-- <button type="button" class="btn btn-primary" id="editStoreProfitSubmit">保存</button> -->
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 
    
	<!-- modal end -->
	
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/golden_manor/manorMemberManage.js?v=1.0.5"></script>
</body>
</html>

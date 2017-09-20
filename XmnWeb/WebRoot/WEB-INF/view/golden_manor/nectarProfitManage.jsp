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
<title>花蜜收益管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link  href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/> 
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
				<%-- <input type="hidden" id="uidViewJunior" name="uidViewJunior" value="${uidViewJunior}"> --%>
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
								<td style="width:8%;"><h5>&nbsp;&nbsp;状态：</h5></td>
							    <td style="width:29%;">
									<select class="form-control" name ="status" value="" style = "width:85%;">
									    <option value = "">全部</option>  <!-- 1.待领取 2.已领取 3.领取失败 4.已取消 -->
						                <option value = "1" >待领取</option>
						                <option value = "2" >已领取</option>
						                <option value = "3" >领取失败</option>
						                <option value = "4" ${param.status==2?"selected":""}>已取消</option>
						            </select>
							    </td>
							</tr>
							<tr>
								<td colspan="2" style="width:29%;">
								</td>
								<td colspan="2" style="width:29%;">
								</td>
							    <td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
									</div>
								</td>
							</tr>
							<!-- <tr>
								<td style="width:8%;"><h5></h5></td>							
								<td style="width:22%;"> 
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;</h5></td>
								<td style="width:22%;"></td>	
								
							</tr> -->
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
			</div>
			<div id="anchorList" class="good-table-wrap"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			add:'${ btnAu['liveMember/manage/add']}'
		}
	</script>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/golden_manor/nectarProfitManage.js?v=1.0.5"></script>
</body>
</html>

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
<title>会员收益关系链管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link  href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/> 

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
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员编号：</h5></td>
								<td style="width:22%;">
									<input type="text" class="form-control" name="uid" value="" style = "width:85%;"/>
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员昵称：</h5></td>
								<td style="width:22%;">
									<input type="text" class="form-control" name="nname" value="" style = "width:85%;"/>
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;会员手机号：</h5></td>
								<td style="width:30%;">
									<input type="text" class="form-control" name="phone" value="" style = "width:58%;"/>
								</td>
							</tr>
							<tr>
								<!--会员渠道来源 1.VIP 2.商家 3.直销 4.V客  -->
								<td style="width:8%;"><h5>&nbsp;&nbsp;渠道类型：</h5></td>
								<td style="width:22%;">
									<select class="form-control" name="objectOriented" style = "width:85%;">
										<option value="">--请选择--</option>
										<option value="1">VIP会员</option>
										<option value="2">商家会员</option>
										<option value="3">直销会员</option>
										<option value="4">V客会员</option>
									</select> 
								</td>	
								<td style="width:8%;"><h5></h5></td>
								<td style="width:30%;">
								</td>
								<td style="width:8%;"><h5></h5></td>							
								<td style="width:22%;"> 
								</td>
							</tr>
							<tr>
								<td style="width:8%;"><h5></h5></td>							
								<td style="width:22%;"> 
								</td>
								<td style="width:8%;"><h5>&nbsp;&nbsp;</h5></td>
								<td style="width:22%;"></td>	
								<td colspan="2" style="width:38%;">
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
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
				<c:if
					test="${null!=btnAu['bursRelationChain/manage/add'] && btnAu['bursRelationChain/manage/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="添加关系链" data-url="bursRelationChain/manage/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;添加关系链
					</button>
				</c:if>
				<c:if test="${btnAu['bursRelationChain/manage/export'] }">
						<button type="button" id="exportAnchor" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="anchorList" class="good-table-wrap"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			add:'${ btnAu['bursRelationChain/manage/add']}',
			bindSuperiorInfo:'${ btnAu['bursRelationChain/manage/bindSuperiorInfo']}'
		}
	</script>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/reward_dividends/bursRelationChainManage.js?v=1.0.1"></script>
</body>
</html>

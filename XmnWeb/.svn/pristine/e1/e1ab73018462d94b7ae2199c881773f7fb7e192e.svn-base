<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
	<head>
		<base href="<%=basePath%>">
		<title>商家会员卡信息</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
		<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
		<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
		<!-- <link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> -->
			
		<style type="text/css">
		body {
			margin: 0;
			font-size: 12px;
			font-family: 'Microsoft Yahei', '微软雅黑';
			overflow-y: auto;
			padding: 10px 30px;
		}
		td{
			height:40px;
		}

		.btn-add{
		    background: #FF5C5C;
	    	width: 160px;
			margin-right: 20px;
			border: 1px solid #FF5C5C;
			line-height: 20px;
			text-align: center;
			font-size:16px;
		}
		</style>
	
	</head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a>商家会员卡信息</a></li>
		<c:if test="${null!=btnAu['businessman/vipCard/cardholderList'] && btnAu['businessman/vipCard/cardholderList']}">
			<li class=""><a href="businessman/vipCard/cardholderListView.jhtml">会员卡列表</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/prepaidList'] && btnAu['businessman/vipCard/prepaidList']}">
			<li class=""><a href="businessman/vipCard/prepaidListView.jhtml">会员卡充值记录</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/audit'] && btnAu['businessman/vipCard/audit']}">
			<li class=""><a href="businessman/vipCard/vipCardAudit.jhtml">会员卡审核</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/issueaudit'] && btnAu['businessman/vipCard/issueaudit']}">
			<li class=""><a href="businessman/vipCard/issueCardAudit.jhtml">充值方案审核</a></li>
		</c:if>
	</ul>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="selectVipCardForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>卡序号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="id" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>卡名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="cardName" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>充值状态:</h5></td>
							<td style="width:18%;">
								<select class="form-control" name="isPay" style = "width:83%;">
									<option value="">全部</option>
									<option value="0">已开启</option>
									<option value="1">未开启</option>
								</select>
							</td>
							<td style="width:5%;"><h5>会员卡状态:</h5></td>
							<td style="width:24%;">
								<select class="form-control" name="cardStatus" style = "width:83%;">
									<option value="">全部</option>
									<option value="0">待上线</option>
									<option value="1">已上线</option>
									<option value="2">已下线</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="sellerId" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>商家名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="sellerName" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>会员卡张数:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control"  name="minActiveNum"  style="width:39%;float:left"/>
							    	<label style="float: left;">&nbsp;--&nbsp;</label>
							  	<input type="text" class="form-control"  name="maxActiveNum"  style="width:39%;float:left"/>
							</td>
						  	<td style="width:5%;"><h5>卡总余额:</h5></td>
						 	<td style="width:18%;">
							  <input type="text" class="form-control"  name="minNotUsedAmount"  style="width:39%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  name="maxNotUsedAmount"  style="width:39%;float:left"/>
							</td>	
						</tr>
						<tr>
							<td style="width:5%;"></td>
						 	<td style="width:18%;">
							<td style="width:5%;"></td>
						 	<td style="width:18%;">
							<td style="width:5%;"></td>
						 	<td style="width:18%;">
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
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
				<c:if test="${null!=btnAu['businessman/vipCard/add'] && btnAu['businessman/vipCard/add']}"><a type="button" class="btn btn-danger btn-add"  href="businessman/vipCard/addView.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${null!=btnAu['businessman/vipCard/export'] && btnAu['businessman/vipCard/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="vipCardList"></div>
			<div id="scrollTablel"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{	view:'${ btnAu['businessman/vipCard/list']}',
			update:'${ btnAu['businessman/vipCard/update']}',
			supporSeller:'${ btnAu['businessman/vipCard/listSupporSellerList']}'}
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/businessman/vipCard/listVipCard.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

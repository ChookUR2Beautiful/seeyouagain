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
		<title>充值记录列表</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
		<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
		<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
		<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
			
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
		</style>
	
	</head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
		<c:if test="${null!=btnAu['businessman/vipCard/list'] && btnAu['businessman/vipCard/list']}">
			<li class=""><a href="businessman/vipCard/listView.jhtml">商家会员卡信息</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/cardholderList'] && btnAu['businessman/vipCard/cardholderList']}">
			<li class=""><a href="businessman/vipCard/cardholderListView.jhtml">会员卡列表</a></li>
		</c:if>
		<li class="active"><a>会员卡充值记录</a></li>
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
			<form class="form-horizontal" role="form" method="get" id="selectPrepaidForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>用户编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="uid" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>用户昵称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="nname" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>用户手机:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="phoneId" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>卡序号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="id" style = "width:90%;"/></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>会员卡编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="noId" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>卡名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="cardName" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>充值金额:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control"  name="minAmount"  style="width:39%;float:left"/>
								<label style="float: left;">&nbsp;--&nbsp;</label>	
								<input type="text" class="form-control"  name="maxAmount"  style="width:39%;float:left"/>
							</td>
							<td style="width:5%;"><h5>到账金额:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control"  name="minProfit"  style="width:39%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  	<input type="text" class="form-control"  name="maxProfit"  style="width:39%;float:left"/>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>充值订单号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="orderId" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>充值时间:</h5></td>
							<td style="width:18%;"><input type="text" id="rdate" name="rdate" class="form-control form-datetime" style = "width:90%;" onClick="WdatePicker()">
							</td>
							<td style="width:5%;"><h5>支付流水号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="number" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>第三方支付账号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="thirdUid" style = "width:90%;"/></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5></h5></td>
							<td style="width:18%;"></td>
						  	<td style="width:5%;"><h5></h5></td>
						 	<td style="width:18%;"></td>	
							<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style = "width:42%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style = "width:42%;"/>
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
				<c:if test="${null!=btnAu['businessman/vipCard/export'] && btnAu['businessman/vipCard/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="prepaidList"></div>
			<div id="scrollTablel"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{detail:'${ btnAu['businessman/vipCard/prepaidList']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/businessman/vipCard/listPrepaid.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/datapicker/WdatePicker.js" ></script>
  </body>
</html>

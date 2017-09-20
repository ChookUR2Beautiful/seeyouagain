<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>商家签约订单列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/web/css/jquery.validate.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
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
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="saasSoldOrderFormId">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>订单号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" value="${param.ordersn}" name="ordersn" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" value="${param.sellerid}" name="sellerid" style = "width:90%;"/>
							<td style="width:5%;"><h5>商家名称:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" value="${param.sellername}" name="sellername" style = "width:90%;"/>
							</td>
							<td style="width:18%;">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>寻蜜客ID:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" value="${param.uid}" name="uid" style = "width:90%;"/>
							</td>
						 	<td style="width:5%;"><h5>签店SAAS渠道:</h5></td>
							<td style="width:18%;">
							<select class="form-control" name="saasChannel" style = "width:90%;">
									<option value="">请选择</option>
									<option value="1" >常规SAAS</option>
									<option value="2" >EC报单</option>
									<option value="3" >V客兑换</option>
									<option value="4" >接受V客赠送</option>
								</select>
							</td>
							<td style="width:5%;"><h5>saas套餐订单编号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" value="${param.saasOrdersn}" name="saasOrdersn" style = "width:90%;"/>
							</td>
						</tr>
						<tr>
						 	<td style="width:5%;"><h5>支付时间:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control" id="sDate" name="sDate" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control" id="eDate" name="eDate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sDate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							</td>
							<td style="width:5%;"></td>
							<td style="width:18%;">
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['xmer/manage/saassoldorder/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="saasSoldOrderList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{refund:'${btnAu['xmer/manage/saassoldorder/refund']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/xmermanagerment/saasSoldOrderList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>

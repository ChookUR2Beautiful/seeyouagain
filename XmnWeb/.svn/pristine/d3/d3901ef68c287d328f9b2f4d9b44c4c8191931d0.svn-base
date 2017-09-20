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
    <title>寻蜜客套餐订单</title>
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
  	<input type="hidden" id="path" value="<%=path%>"/>
  	<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab1" data-toggle="tab">常规寻蜜客</a>
			</li>
			<li>
				<a href="#tab2" data-toggle="tab">EC报单</a>
			</li>
			<li>
				<a href="#tab3" data-toggle="tab">V客兑换</a>
			</li>
			<li>
				<a href="#tab4" data-toggle="tab">V客赠送</a>
			</li>
	</ul>
	<div class="tab-content">
		<%--常规购买 --%>
	<div class="tab-pane active" id="tab1">
	      	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" method="get" id="saasOrderFormId1">
						<input style="display:none;" id="status" name="status"/>
						<input type="hidden"  name="saasChannel" value="1"/>
						<table style="width:100%;">
							<tbody>
								<tr>
								<td style="width:5%;"><h5>订单号:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" value="${param.ordersn}" name="ordersn" style = "width:90%;"/></td>
								<td style="width:5%;"><h5>押金套餐ID：</h5></td>
								<td style="width:18%"><input type="text" class="form-control"  name="dpid"  value="${param.dpid}" style="width:90%"></td>
								<td style="width:5%;"><h5>用户ID:</h5></td>
								<td style="width:18%;">
								<input type="text" class="form-control" value="${param.uid}" name="uid" style = "width:90%;"/>
								</td>
								<td colspan="2" style="width:18%;"></td>
							</tr>
							<tr>
								<td style="width:5%;"><h5>签约数量:</h5></td>
								<td style="width:18%;">
								<input type="text" class="form-control" value="${param.nums}" name="nums" style = "width:90%;"/>
								</td>
							 	<td style="width:5%;"><h5>订单金额:</h5></td>
								<td style="width:18%;">
								<input type="text" class="form-control" value="${param.amount}" name="amount" style = "width:90%;"/>
								</td>
								<td style="width:5%;"><h5>套餐单价:</h5></td>
								<td style="width:18%;">
								<input type="text" class="form-control" value="${param.price}" name="price" style = "width:90%;"/>
								</td>	
								<td colspan="2" style="width:18%;"></td>			
							</tr>
							<tr>
								<td style="width:5%;"><h5>剩余套餐库存数量:</h5></td>
								<td style="width:18%;">
								<input type="text" class="form-control" value="${param.stock}" name="stock" style = "width:90%;"/>
								</td>
							 	<td style="width:5%;"><h5>支付时间:</h5></td>
								<td style="width:18%;">
									<input type="text" class="form-control" id="sDate" name="sDate" value="${param.sDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
								    <label style="float: left;">&nbsp;--&nbsp;</label>
								  <input type="text" class="form-control"  id="eDate" name="eDate" value="${param.eDdate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sDate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
								</td>
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;width:100%;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
									</div>
								</td>	
								<td colspan="2" style="width:18%;"></td>				
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
					<c:if test="${btnAu['xmer/manage/saasorder/export']}"><button type="button" id="export1" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
					</c:if>
				</div>
				<div id="saasOrderList1"></div>
			</div>
		</div>
	</div>
	<div class="tab-pane" id="tab2"><%--EC报单 --%>
	      	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" method="get" id="saasOrderFormId2">
						<input style="display:none;" id="status" name="status"/>
						<input type="hidden"  name="saasChannel" value="2"/>
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:5%;"><h5>订单号:</h5></td>
									<td style="width:18%;"><input type="text" class="form-control" value="${param.ordersn}" name="ordersn" style = "width:90%;"/></td>
									<td style="width:5%;"><h5>EC报单编号：</h5></td>
									<td style="width:18%"><input type="text" class="form-control" value="${param.mbTransNo}" name="mbTransNo" style = "width:90%;"/></td>
									<td style="width:5%;"><h5>EC会员编号:</h5></td>
									<td style="width:18%;">
									<input type="text" class="form-control" value="${param.mbEcno}" name="mbEcno" style = "width:90%;"/>
									</td>
									<td colspan="2" style="width:18%;"></td>
								</tr>
								<tr>
									<td style="width:5%;"><h5>用户ID:</h5></td>
									<td style="width:18%;">
									<input type="text" class="form-control" value="${param.uid}" name="uid" style = "width:90%;"/>
									</td>
								 	<td style="width:5%;"><h5>报单时间:</h5></td>
									<td style="width:18%;">
										<input type="text" class="form-control" id="sDate" name="sDate" value="${param.sDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
									    <label style="float: left;">&nbsp;--&nbsp;</label>
									  <input type="text" class="form-control"  id="eDate" name="eDate" value="${param.eDdate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sDate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
									</td>
									<td colspan="2" style="width:29%;">
										<div class="submit" style="text-align: left;width:100%;">
											<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
											<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
										</div>
									</td>	
									<td colspan="2" style="width:18%;"></td>				
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
						<c:if test="${btnAu['xmer/manage/saasorder/export']}"><button type="button" id="export2" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
						</c:if>
					</div>
					<div id="saasOrderList2"></div>
				</div>
			</div>
	  </div>

	  <div class="tab-pane" id="tab3"><%--V客兑换 --%>
	      	<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" method="get" id="saasOrderFormId3">
						<input style="display:none;" id="status" name="status"/>
						<input type="hidden"  name="saasChannel" value="3"/>
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:5%;"><h5>订单号:</h5></td>
									<td style="width:18%;"><input type="text" class="form-control" value="${param.ordersn}" name="ordersn" style = "width:90%;"/></td>
									<td style="width:5%;"><h5>兑换时间:</h5></td>
									<td style="width:18%;">
										<input type="text" class="form-control" id="sDate" name="sDate" value="${param.sDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
									    <label style="float: left;">&nbsp;--&nbsp;</label>
									  <input type="text" class="form-control"  id="eDate" name="eDate" value="${param.eDdate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sDate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
									</td>
									<td style="width:5%;"><h5>用户ID:</h5></td>
									<td style="width:18%;">
									<input type="text" class="form-control" value="${param.uid}" name="uid" style = "width:90%;"/>
									</td>
									<td colspan="2" style="width:18%;"></td>
								</tr>					
								<tr>
								 	<td style="width:5%;"><h5></h5></td>
									<td style="width:18%;">
									</td>
									<td style="width:5%;"><h5></h5></td>
									<td style="width:18%;">
									</td>
									<td colspan="2" style="width:29%;">
										<div class="submit" style="text-align: left;width:100%;">
											<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
											<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
										</div>
									</td>	
									<td colspan="2" style="width:18%;"></td>				
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
						<c:if test="${btnAu['xmer/manage/saasorder/export']}"><button type="button" id="export3" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
						</c:if>
					</div>
					<div id="saasOrderList3"></div>
				</div>
			</div>

	</div>
	<div class="tab-pane" id="tab4"> <%--领取V客赠送 --%>
	  	<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" method="get" id="saasOrderFormId4">
					<input style="display:none;" id="status" name="status"/>
					<input type="hidden"  name="saasChannel" value="4"/>
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>订单号:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" value="${param.ordersn}" name="ordersn" style = "width:90%;"/></td>
								<td style="width:5%;"><h5>赠送人ID：</h5></td>
								<td style="width:18%">
									<input type="text" class="form-control" value="${param.fromUid}" name="fromUid" style = "width:90%;"/>
								</td>
								<td style="width:5%;"><h5>用户ID:</h5></td>
								<td style="width:18%;">
								<input type="text" class="form-control" value="${param.uid}" name="uid" style = "width:90%;"/>
								</td>
								<td colspan="2" style="width:18%;"></td>
							</tr>					
							<tr>
							 	<td style="width:5%;"><h5>赠送时间:</h5></td>
								<td style="width:18%;">
									<input type="text" class="form-control" id="sDate" name="sDate" value="${param.sDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
								    <label style="float: left;">&nbsp;--&nbsp;</label>
								  <input type="text" class="form-control"  id="eDate" name="eDate" value="${param.eDdate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sDate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
								</td>
								<td style="width:5%;"><h5></h5></td>
								<td style="width:18%;">
								</td>
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;width:100%;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
									</div>
								</td>	
								<td colspan="2" style="width:18%;"></td>				
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
					<c:if test="${btnAu['xmer/manage/saasorder/export']}"><button type="button" id="export4" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
					</c:if>
				</div>
				<div id="saasOrderList4"></div>
			</div>
		</div>
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/xmermanagerment/saasOrderList.js"></script>
	<script src="<%=path%>/js/xmermanagerment/ecsaasOrderList.js"></script>
	<script src="<%=path%>/js/xmermanagerment/versaasOrderList.js"></script>
	<script src="<%=path%>/js/xmermanagerment/versendsaasOrderList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>

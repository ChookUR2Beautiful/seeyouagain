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
  	    <c:if test="${null!=btnAu['businessman/vipCard/list'] && btnAu['businessman/vipCard/list']}">
			<li class=""><a href="businessman/vipCard/listView.jhtml">商家会员卡信息</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/cardholderList'] && btnAu['businessman/vipCard/cardholderList']}">
			<li class=""><a href="businessman/vipCard/cardholderListView.jhtml">会员卡列表</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/prepaidList'] && btnAu['businessman/vipCard/prepaidList']}">
			<li class=""><a href="businessman/vipCard/prepaidListView.jhtml">会员卡充值记录</a></li>
		</c:if>
		<c:if test="${null!=btnAu['businessman/vipCard/audit'] && btnAu['businessman/vipCard/audit']}">
			<li class=""><a href="businessman/vipCard/vipCardAudit.jhtml">会员卡审核</a></li>
		</c:if>
		<li class="active"><a>充值方案审核</a></li>
		
	</ul>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal"  role="form" method="get" id="selectVipCardForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="sellerid" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>商家名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="sellername" style = "width:90%;"/></td>
						  	<td style="width:5%;"><h5>审核状态:</h5></td>
						 	<td style="width:18%;">
								<select class="form-control" name="cardstatus" style = "width:83%;">
									<option value="">全部</option>
									<option value="0">待审核</option>
									<option value="1">审核通过</option>
									<option value="3"> 审核不通过</option>
								</select>
							</td>
							<td style="width:5%;">
							<td style="width:18%;">
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
							<td colspan="2" style="width:23%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style = "width:42%;" />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style = "width:42%;" />
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
				<c:if test="${null!=btnAu['businessman/vipCard/updateIssueState'] && btnAu['businessman/vipCard/updateIssueState']}">
				<button type="button" class="btn btn-success" id="passId"><span class="icon-ok-sign"></span>&nbsp;通过</button>
				<button type="button" class="btn btn-warning" id="notPassId"><span class="icon-remove-sign"></span>&nbsp;不通过</button>
				</c:if>
			</div>
			<div id="issueCardList"></div>
			<div id="scrollTablel"></div>
		</div>
	</div>
	 <!-- 模态框（Modal） -->
	<div class="modal fade" id="refuseModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               	申请不通过
	            </h4>
	         </div>
	         <div class="modal-body">
	         	
				<form class="form-horizontal" role="form" id="refuseForm" style=" width :580px;overflow-y:auto; ">
				<input type="hidden"   id = "optype" name="optype" value="2">
				<input type="hidden"   id = "ids" name="ids" value="${param.ids}">
				<table width="100%">
					<tbody>
						<tr>	
							<th style="width:90px;"><h5>&nbsp;&nbsp;未通过原因:</h5></th>
							<th colspan="2">
								<textarea name="reason" rows="10" id="reason" class="form-control" placeholder="未通过原因"></textarea>
							</th>	
						</tr>
						<tr>
							<th colspan="3" style="text-align: center;"> 
								<button type="button" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保存 </button>
								<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
							</th>
						</tr>
					</tbody>
				</table>
				</form>
	         </div>
	      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<script type="text/json" id="permissions">
		{	view:'${ btnAu['businessman/vipCard/list']}',
			supporSeller:'${ btnAu['businessman/vipCard/listSupporSellerList']}'}
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/businessman/vipCard/issueCardAudit.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>

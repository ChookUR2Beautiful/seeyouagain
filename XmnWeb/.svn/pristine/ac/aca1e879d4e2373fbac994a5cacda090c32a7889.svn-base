<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>商家银行卡信息管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchBankApplyForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<input type="hidden" id="handletype" name="handletype"  value="${param.handletype}"/>
							<td style="width:5%;"><h5>&nbsp;&nbsp;开户姓名:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="fullname" style = "width:85%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;手机预留号:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="bankphone" style = "width:85%;"></td>
							<td style="width:5%;"><h5>商家编号:</h5></td>
							<td style="width:30%;"><input type="text" class="form-control"  name="aid" style = "width:66%;"></td>
						</tr>
						<tr>
						<td style="width:5%;"><h5>&nbsp;&nbsp;开户行名称:</h5></td>
						<td style="width:25%;"><input type="text" class="form-control"  name="bank" style = "width:85%;"></td>
						<td style="width:5%;"><h5>&nbsp;&nbsp;申请时间:</h5></td>							
						<td style="width:25%;">
								<input type="text" name ="bdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="bdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:40%;float:left">
							</td>	
						<td style="width:5%;"><h5>申请类型:</h5></td>
							<td style="width:30%;">
								<select class="form-control" name="applytype" style = "width:66%;">
									<option value="">请选择</option>
								    <option value="0">修改</option> 
									<option value="1">新增</option>								
								</select>
						</td>
						</tr>
						<tr>	
							<td colspan="4" style="width:60%;"></td>
							<td colspan="2" style="width:35%;"><div class="submit" style="text-align: left;"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
	            <button type="button" class="btn btn-success"  onclick="queryBumen(this,'');" name="bumen">全部</button>&nbsp;&nbsp;	  
				<button type="button" class="btn btn-default"  onclick="queryBumen(this,'1');" name="bumen">待审核</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-default"  onclick="queryBumen(this,'0');" name="bumen">审核通过</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-default"  onclick="queryBumen(this,'2');" name="bumen">未通过</button>
			</div>
			<div id="bankApplyList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{notup:'${ btnAu['businessman/bankApply/updateNot']}',up:'${ btnAu['businessman/bankApply/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/businessman/bankApplyList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

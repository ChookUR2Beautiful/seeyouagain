	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>消息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet"> 
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>标题：</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="title"  style="width:90%;"></td>
							<td style="width:5%;"><h5>开始时间：</h5></td>							
							<td style="width:19%;">
								<input type="text" name ="dateSendStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="dateSendEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:5%;" nowrap="nowrap"><h5>推送人数：</h5></td>
							<td style="width:19%;">
							<input type="text" class="form-control"  name="sendNumStart"  style="width:45%;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" class="form-control"  name="sendNumEnd"  style="width:45%;float:left">
							</td>
							<td style="width:5%;" nowrap="nowrap"><h5>创建时间：</h5></td>							
							<td style="width:23%;">
								<input type="text" name ="cdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="cdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>	
						</tr>
						
						<tr>
						<td style="width:5%;"><h5>副标题：</h5></td>
							<td style="width:19%;"><input type="text" class="form-control"  name="subtitle"  style="width:90%;"></td>
							
							<td style="width:5%;"><h5>结束时间：</h5></td>							
							<td style="width:19%;">
								<input type="text" name ="dateEndSendS" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="dateEndSendE" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:5%;"><h5>已读人数：</h5></td>
							<td style="width:19%;">
								<input type="text" class="form-control"  name="readNumStart"  style="width:45%;float:left">
							    <label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" class="form-control"  name="readNumEnd"  style="width:45%;float:left">
							</td>
							
							<td style="width:4%;"><h5>商圈查询：</h5></td>
							<td style="width:24%;">
								<select class="form-control"  id="zoneid" name ="zoneid">
					            </select>
							</td>
							
						</tr>
					      <tr>
						    <td colspan="6" style="width:72%;"></td>
							<td colspan="2" style="width:28%;"><div class="submit" style="text-align: left;"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' /><input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' /></div></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			    <c:if test="${null!=btnAu['businessman/sellerMsg/add'] && btnAu['businessman/sellerMsg/add']}">
			    	<a type="button" id="addbtn" class="btn btn-success"  href="businessman/sellerMsg/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a>
			    </c:if>
				<%-- <c:if test="${null!=btnAu['businessman/sellerMsg/delete'] && btnAu['businessman/sellerMsg/delete']}">
					<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if> --%>
			</div>
			<div id="sellerMsgList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{view:'${ btnAu['businessman/sellerMsg/view/init']}',del:'${ btnAu['businessman/sellerMsg/delete']}',update:'${btnAu['businessman/sellerMsg/update'] }'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/businessman/sellermsg/sellerMsgList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
  </body>
</html>

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
    <title>APP推送信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">   
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<input type="hidden" value="${not empty param.page ? param.page : '1'}" name="page"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:8%;"><h5>&nbsp;&nbsp;信息标题:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="title"  style="width:90%" value="${param.title}"></td>
							<input type="hidden" name="client" value="${client}"/>
							<c:choose>
								<c:when test="${client==1}"><c:set scope="page" var="clientStr" value="user_terminal"></c:set></c:when>
								<c:when test="${client==2}"><c:set scope="page" var="clientStr" value="businessman"></c:set></c:when>
								<c:when test="${client==3}"><c:set scope="page" var="clientStr" value="business_cooperation"></c:set></c:when>
							</c:choose>
							<!-- <td style="width:50px;"><h5>&nbsp;&nbsp;客户端类型	</h5></td>
							<td style="width:120px;">
							<input type="text" class="form-control"  name="tid"  style="width:120px;">					
								 <select class="form-control"  name="client" style="width:120px;"> 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端
								        <option value="" >请选择客户端类型</option>
										<option value="1" >寻蜜鸟客户端</option>
										<option value="2" > 商户客户端</option>
										<option value="3" >合作商客户端</option>
										
								 </select>
							</td> -->
							<td style="width:8%;"><h5>&nbsp;&nbsp;后续动作类型:</h5></td>
							<td style="width:25%;">
								<!-- //1=打开应用|2=网址|3=activity -->
								<select class="form-control"  name="type" style="width:90%">
								        <option value="" >请选择后续动作类型</option>
										<option value="1" ${param.type == "1" ? "selected" : ""}>打开应用</option>
										<option value="2" ${param.type == "2" ? "selected" : ""}>网址</option>
										<%-- <option value="3" ${param.type == "3" ? "selected" : ""}>activity</option> --%>									
								 </select>	
							</td>
							
							<!-- edate;// 过期时间|说明:过期时间需要至少晚于发送时间30分钟 -->
							<td style="width:8%;"><h5>&nbsp;&nbsp;推送结束时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" name ="edateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:34.5%;float:left" value="${param.edateStart}">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="edateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:34.5%;float:left" value="${param.edateEnd}">
							</td>
						</tr>
						
						<tr>
							<td style="width:8%;"><h5>&nbsp;&nbsp;信息内容:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="content" style="width:90%" value="${param.content}"></td>
							
							<td style="width:8%;"><h5>&nbsp;&nbsp;发送状态:</h5></td>
							<td style="width:25%;">
							<!-- <input type="text" class="form-control"  name="tid"  style="width:120px;"> -->					
								 <select class="form-control"  name="status" style="width:90%"> <!-- 发送状态|0=待发送|1=已发送 -->
								        <option value="">请选择发送状态</option>
										<option value="0" ${param.status == "0" ? "selected" : ""}>待发送</option>
										<option value="1" ${param.status == "1" ? "selected" : ""}>已发送</option>										
								 </select>
							</td>
							
							<td style="width:8%;"><h5>&nbsp;&nbsp;提醒方式:</h5></td>
							<td style="width:26%;">
								<!-- <input type="text" class="form-control"  name="tid"  style="width:120px;"> -->
								<!-- //提醒方式  0=声音|1=震动|2=呼吸灯 -->
								<select class="form-control"  name="remind" style="width:74%">
								        <option value="" >请选择提醒方式</option>
										<option value="0" ${param.remind == "0" ? "selected" : ""}>声音</option>
										<option value="1" ${param.remind == "1" ? "selected" : ""}>震动</option>
										<option value="2" ${param.remind == "2" ? "selected" : ""}>呼吸灯</option>									
								 </select>	
							</td>
						</tr>
						<tr>
						<td colspan="4"></td>
							<td colspan="2">
								<div class="submit" style="text-align: left; margin-left: 10px;">
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
				<c:set scope="page" var="add" value="${clientStr}/appPush/add"></c:set>
				<c:set scope="page" var="del" value="${clientStr}/appPush/delete"></c:set>
				<c:set scope="page" var="export" value="${clientStr}/appPush/export"></c:set>
				<c:set scope="page" var="update" value="${clientStr}/appPush/update"></c:set>
				<c:if test="${not empty btnAu[add]}"><a type="button" id="addAppPush" class="btn btn-success" href="${clientStr}/appPush/add/init.jhtml"><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<%-- <c:if test="${not empty btnAu[del]}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if> --%>
				<c:if test="${not empty btnAu[export]}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="appPushList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{<%--del:'${btnAu[del]}',--%>update :'${btnAu[update]}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/common/appPush.js"></script>
  </body>
</html>

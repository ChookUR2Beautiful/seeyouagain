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
    <title>商家账号</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
<!--   <form class="form-horizontal" role="form"  id="zForm">
		<input type="hidden" id="bid" name=" bid" value="0"/>
		<input type="hidden" id="name" name="name" value="0"/>
		<input type="hidden" id="phone" name="phone" value="0"/>
		<input type="hidden" name=" issueType" value="0"/>
		<input type="hidden" id="userType" name="userType" value="0"/>
		<input type="hidden" id="issueRecord" name="issueRecord" value="0"/>
		<input type="hidden" id="result" name="result" value="0"/>
		<input type="hidden" id="isSend" name="isSend" value="0"/>
		<input type="hidden" id="dateCreate" name="dateCreate" value="0"/>
  </form> -->
  
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
  	  <li class="">
      </li>
      <h5>选择问题类型:</h5>
      <li class="active">
        <a href="#tab1" data-toggle="tab">订单问题</a> 
      </li>
      <li class="">
        <a href="#tab2" data-toggle="tab">咨询</a>
      </li>
      <li class="">
        <a href="#tab3" data-toggle="tab">投诉</a>
      </li>
      <li class="">
        <a href="#tab4" data-toggle="tab">其他问题</a>
      </li>
    </ul>
    <div class="tab-content">
	 <jsp:include page="../service_management/orderIssue.jsp"></jsp:include>
	 <jsp:include page="../service_management/consultIssue.jsp"></jsp:include>
	 <jsp:include page="../service_management/complainIssue.jsp"></jsp:include>
	 <jsp:include page="../service_management/elseIssue.jsp"></jsp:include>
	 
	</div>
	 <script type="text/json" id="permissions">{xg:'${btnAu['president_office/disputeOrder/updateStatus']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/service_management/phoneRecord.js"></script>
	<script src="<%=path%>/js/service_management/orderIssue.js"></script>
	<script src="<%=path%>/js/service_management/consultIssue.js"></script>
	<script src="<%=path%>/js/service_management/complainIssue.js"></script>
	<script src="<%=path%>/js/service_management/elseIssue.js"></script>
  </body>
</html>
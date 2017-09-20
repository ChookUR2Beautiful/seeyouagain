<%--
  Created by IntelliJ IDEA.
  User: Joney
  Date: 2017/6/2
  Time: 9:56
--%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
  <title>V客充值奖励红酒订单管理</title>

  <link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  <style type="text/css">
    label.col-md-1 {
      padding-left: 30px;
      padding-right: 0px;
    }
  </style>
</head>
<body>
<div class="panel">
  <div class="panel-body">
    <form class="form-horizontal" id="searchParam">
      <div class="form-group">
        <label for="input-orderNo" class="col-md-1">订单号</label>
        <div class="col-md-2"><input id="input-orderNo" name="orderNo" type="number" class="form-control"> </div>

        <label for="input-uid" class="col-md-1">用户编号</label>
        <div class="col-md-2"><input id="input-uid" name="uid" type="number" class="form-control"></div>

        <label for="input-phone" class="col-md-1">手机号码</label>
        <div class="col-md-2"><input id="input-phone" name="phone" type="number" class="form-control"></div>

        <label for="input-status" class="col-md-1">订单状态</label>
        <div class="col-md-2">
          <select name="status" id="input-status" class="form-control">
            <option value="" selected="selected"></option>
            <option value="1" >用户确认</option>
            <option value="2">系统确认</option>
            <option value="3">系统取消</option>
          </select>
        </div>

        <label for="input-createTimeBegin" class="col-md-1">下单时间 从:</label>
        <div class="col-md-2"><input id="input-createTimeBegin" name="createTimeBegin" type="text" class="form-control form-datetime"></div>
        <label for="input-createTimeAfter" class="col-md-1">下单时间 至:</label>
        <div class="col-md-2"><input id="input-createTimeAfter" name="createTimeAfter" type="text" class="form-control form-datetime"></div>

      </div>

      <div class="form-group" style="float: right">
        <button class="btn btn-primary" type="button" onclick="initOrderList()">确认筛选</button>
        <button class="btn">重置条件</button>
      </div>
    </form>
  </div>
</div>

<div class="panel">
  <div class="panel-body">
    <div id="div-orderList"></div>
  </div>
</div>


<%--<div id="address">--%>
  <%--This is 地址--%>

<%--</div>--%>
<div class="modal modal-for-page fade" id="address" style="display: none;" aria-hidden="true">
  <div class="modal-dialog" style="margin-top: 239.333px;">
    <div class="modal-content">
      <div class="modal-body">
        收货人:  <span id="address-name"></span><br/>
        收货电话: <span id="address-phone"></span><br/>
        收货地址: <span id="address-detail"></span><br/>

      </div>
    </div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/resources/page/page.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/ux/js/scrollTablel.js"></script>
<script src="<%=path%>/js/reward_dividends/rechargeRewardOrderManager.js"></script>


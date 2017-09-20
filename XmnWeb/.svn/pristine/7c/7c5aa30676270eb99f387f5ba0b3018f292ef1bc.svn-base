<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>新时尚大赛发放奖励</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body>
  <div class="container-fluid">
    <div class="panel panel-primary">
      <div class="panel-heading">发放奖励</div>
      <div class="panel-body">
      <form id="submitForm" class="form-horizontal">
      
      	<!-- 获取方式，1摇一摇，2满返，3短信获取 4直接发放,5：订单后刮优惠劵；6:分享后刮优惠劵;7.系统推送; 8.庄园激活奖励;9.新时尚大赛奖励 -->
      	<input type="hidden" id="getWay" name="getWay" value="9">

        <%--优惠卷类型--%>
        <div class="form-group " >
          <label class="col-sm-1">奖励类型：<span style="color:red;">*</span></label>
          <div class="col-sm-3">
          <label class="radio-inline"> <input id="ctype-default" type="radio" name="ctype" value="0" onchange="onchangeCtype(this)" typeName="消费优惠劵">消费优惠劵</label>
          <label class="radio-inline"> <input type="radio" name="ctype" value="1" onchange="onchangeCtype(this)" typeName="商城优惠券">商城优惠券</label>
          <label class="radio-inline"> <input type="radio" name="ctype" value="5" onchange="onchangeCtype(this)" typeName="平台通用优惠劵">平台通用优惠劵</label>
          <label class="radio-inline"> <input type="radio" name="ctype" value="6" onchange="onchangeCtype(this)" typeName="鸟币">鸟币</label>
          </div>
        </div>


        <input id="cids" type="hidden">

        <%--选择优惠劵 --%>
        <div class="form-group coupon-info">
          <label for="cidDiv" class="col-sm-1"> 选择优惠券：<span style="color:red;">*</span></label>

          <div class="col-md-3" id="cidDiv">
            <select class="form-control" id="cid" name="cid"  initValue="" style="width:100%;"></select>
          </div>

          <div class="col-sm-1">
            <div class="input-group">
              <span class="input-group-addon"></span>
              <input type="number"  class="form-control" min="1" value="1" id="useNum">
              <span class="input-group-addon">张</span>
            </div>
          </div>

          <div class="col-sm-1">
            <span class="btn btn-default" id="button-addCoupon">添加</span>
          </div>
        </div>


        <div class="form-group coupon-info">
          <div class="col-sm-1"></div>
          <div class="col-sm-4">
            <table class="table table-hover table-bordered table-striped info">
              <thead>
              <tr>
                <th>类型</th>
                <th style="display:none;">优惠券ID</th>
                <th>优惠劵名称</th>
                <th>数量</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody id="couponListTbody">

              </tbody>
            </table>
          </div>
        </div>
        
        <%--用户类型--%>
        <div class="form-group bird-info">
          <label class="col-sm-1">鸟币金额：<span style="color:red;">*</span></label>
          <div class="col-sm-3">
            <input class="form-control" id="birdCoin" name="birdCoin" value="" onblur="birdCoinBlur(this)">
          </div>
        </div>
        
        <%--用户类型--%>
        <div class="form-group" style="display:none;">
          <label class="col-sm-1">用户类型：</label>
          <div class="col-sm-3">
            <label class="radio-inline"> <input type="radio" name="userType" value="0" onchange="onchangeUserType(this)" typeName="按会员类型">按会员类型</label>
            <label class="radio-inline"> <input id="userType-default"type="radio" name="userType" value="1" onchange="onchangeUserType(this)" typeName="按具体用户">按具体用户</label>
          </div>
        </div>

        <div class="form-group">
          <label class="col-md-1">所选用户：<span style="color:red;">*</span></label>
          <div class="col-sm-4">
           <!-- <textarea id="selectUsers" class="form-control" rows="5" name="excludeUsers" ></textarea> -->
           <select class="chosen-select form-control" id="selectUsers" name="selectUsers" multiple
				initValue='${userItems}'  data-placeholder="请选择用户">
			</select>
			
          </div>
        </div>
        <div group="specific">
          <div class="col-md-1"></div>
          <span style="color: red;">*所选用户才能收到奖励</span>
        </div>

      </form>
      
      <div class="panel panel-default">
			<div class="panel-body data">
				<div class="btn-group" style="margin-bottom: 5px;">
				</div>
				<div id="enrollList1"></div>
			</div>
		</div>

        <div class="row text-center" style="margin-top:20px;">
          <button class="btn btn-danger" type="submit" id="ensure">
            <i class="icon-save"></i>&nbsp;保存
          </button>
          &nbsp;&nbsp;
          <button class="btn btn-warning" type="button"
                  onclick="window.history.back();">
            <i class="icon-reply"></i>&nbsp;取消
          </button>
        </div>

      </div>
    </div>
  </div>



  <jsp:include page="../common.jsp"></jsp:include>
  <script src="<%=path%>/ux/js/grid.js"></script>
  <script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
  <%-- <script src="<%=path%>/ux/js/searchChosen.js"></script> --%>
  <script src="<%=path%>/ux/js/multipleChosen.js"></script>
  <script src="<%=path%>/ux/js/scrollTablel.js"></script>
  <script src="<%=path%>/js/vstar/vstarRewardSend.js?v=1.0.1"></script>

</body>
</html>
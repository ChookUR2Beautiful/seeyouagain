<%--
  Created by IntelliJ IDEA.
  User: Joney
  Date: 2017/5/15
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<html>
<head>
  <title>优惠劵添加页面</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="add coupon">
  <meta http-equiv="description" content="add coupon init">
  <link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
  <link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
  <link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
  <link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">

  <style>
    body {
      margin: 0;
      font-size: 12px;
      font-family: 'Microsoft Yahei', '微软雅黑';
      overflow-y: auto;
      padding: 10px 30px;
    }

    th {
      font-size: 12px;
    }
  </style>
</head>
<body style="overflow-x: auto;overflow-y: auto;background:#EEE" class="doc-views with-navbar">
<ul class="nav nav-tabs">
  <li class="active"><a href="###" data-target="#tab1" data-toggle="tab">消费优惠劵-</a></li>
  <li><a href="###" data-target="#tab2" data-toggle="tab">商场优惠劵</a></li>
  <li><a href="###" data-target="#tab3" data-toggle="tab">平台通用优惠劵</a></li>
</ul>
<div class="tab-content">

  <!--消费优惠劵-添加标签页-->
  <div id="tab1" class="tab-pane fade in active">
    <div class="panel"><div class="panel-body">
      消费优惠劵-添加标签页
      <form class="form-horizontal" >
        <%--优惠劵类型 0:消费优惠劵--%>
        <input type="hidden" name="ctype" value="0"/>

          <%--优惠劵名称--%>
        <div class="form-group" style="margin-top:40px;">
          <label class="col-md-2 control-label">优惠券名称：</label>
          <div class="col-md-3">
            <input type="text" name="cname" maxlength="15" placeholder="限制长度15字" class="form-control"/>
          </div>
        </div>

          <%--优惠劵面额--%>
        <div class="form-group">
          <label class="col-md-2 control-label">面额：</label>
          <div class="col-md-3"><div class="input-group">
              <input type="number" min="0" name="denomination" class="form-control" placeholder="优惠劵金额"/>
              <span class="input-group-addon">元</span>
          </div></div>
        </div>

          <%--时间设置--%>
        <div class="form-group">
          <label class="col-md-2 control-label">时间：</label>
          <div class="col-md-3"><div class="input-group">
            <span class="input-group-addon">
              <label class="radio-inline">领取有效天数
                <input type="radio" name="swichtime"  checked="checked" value="1"/>
              </label>
            </span>
            <div >
              <input class='form-control'  type="number" name="dayNum" min="1"/>
            </div>
            <span class="input-group-addon">
              <label class="radio-inline">设置有效日期区间
                <input type="radio" id="showTimex2" name="swichtime" value="2"/>
              </label>
            </span>
          </div></div>
        </div>

          <%--有效时间--%>
        <div class="form-group"  valid-date id="dates" style="display: none;">
          <label class="col-md-2 control-label">有效日期：</label>
          <div class="col-md-8">
            <div class="input-group">
              <span class="input-group-addon">开始日期：</span> <input readonly="" type="text" name="coupnValidities[0].startDate" class="form-control date-start">
              <span class="input-group-addon">结束日期：</span> <input type="text" readonly="" name="coupnValidities[0].endDate" class="form-control date-end"><span class="input-group-addon fix-border fix-padding"></span><span class="input-group-addon">开始时间：</span> <input type="text" readonly="" name="coupnValidities[0].startTime" class="form-control time-start">
              <span class="input-group-addon">结束时间：</span> <input type="text" name="coupnValidities[0].endTime" readonly="" class="form-control time-end">
            </div>
          </div>
        </div>
          <%--使用条件--%>
        <div class="form-group">
          <label class="col-md-2 control-label">使用条件：</label>
          <div class="col-md-3">
            <div class="input-group">
                  <span class="input-group-addon">
                    <label class="radio-inline">满
                      <input type="radio" name="conditionRadio" onchange="conditionYes();" id="customConditionRadio" value="1" checked="checked"/>
                    </label>
                  </span>
              <div id="customConditionDiv">
                <input class='form-control' type="number" min="0"  name="condition"/>
              </div>
              <span class="input-group-addon">元(起)使用</span>
              <span class="input-group-addon">
                  <label class="radio-inline">无条件使用
                      <input type="radio" name="conditionRadio" value="0" id="customConditionRadio1" onchange="conditionNo();"/>
                  </label>
                  </span>
            </div>
          </div>
        </div>

          <%--每次可同时使用张数--%>
        <div class="form-group">
          <label class="col-md-2 control-label">每次可同时使用：</label>
          <div class="col-md-3">
            <div class="input-group">
              <input class="form-control" name="useNum" type="number" min="1" placeholder="1">
              <span class="input-group-addon">张</span>
            </div>
          </div>
        </div>

        <%--是否平台通用--%>
        <div class="form-group" id="showAll">
          <label class="col-md-2 control-label">是否平台通用：</label>
          <div class="col-md-3">
            <div class="input-group">
              <label class="radio-inline">是<input type="radio" name="showAll" onchange="showAllYes();" id="allArea" value="1" checked="checked"/></label>
              <label class="radio-inline">否<input type="radio" name="showAll" id="specifyArea" value="0" onchange="showAllNo();"/> </label>
            </div>
          </div>
        </div>


        <div class="form-group" id="cities">
          <label class="col-md-2 control-label"></label>
          <div class="col-md-5">
            <div class="input-group">
              <div class="ld" style="width: 100%;float:left;"></div>
              <span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer"
                                                 onclick="addAreaLd(this) ;"></i></span> <span
                    class="input-group-addon"><i class="icon icon-minus"
                                                 style="cursor:pointer"
                                                 onclick="removeAreaLd(this);"></i></span>
            </div>
          </div>
        </div>
        <div class="form-group" id="allSeller">
          <label class="col-md-2 control-label">是否全部商家：</label>
          <div class="col-md-3">
            <div class="input-group">
              <label class="radio-inline">是<input checked="true"
                                                  type="radio" name="isAllSeller" value="1"
                                                  onchange="isAllSellerYes();"/></label><label
                    class="radio-inline">否<input
                    type="radio" name="isAllSeller" value="0"
                    onchange="isAllSellerNo();"/></label>
            </div>
          </div>
        </div>
        <div class="form-group" id="trades">
          <label class="col-md-2 control-label">行业选择：</label>
          <div class="col-md-3">
            <div class="input-group">
              <label class="radio-inline">包含所选行业<input
                      checked="true" type="radio" name="trade" value="1"
                      onchange="tradeInclude();"/></label><label
                    class="radio-inline">排除所选行业<input
                    type="radio" name="trade" value="0"
                    onchange="tradeExclude();"/></label>
            </div>
          </div>
        </div>
        <div class="form-group" id="includeTrade">
          <label class="col-md-2 control-label"></label>
          <div class="col-md-5">
            <div class="input-group">
              <div class="includeTradeSelect" style="width : 100%"></div>
              <span class="input-group-addon"><i
                      class="icon icon-plus" style="cursor:pointer"
                      onclick="addIncludeTrade(this) ;"></i></span> <span
                    class="input-group-addon"><i class="icon icon-minus"
                                                 style="cursor:pointer"
                                                 onclick="removeIncludeTrade(this);"></i></span>
            </div>
          </div>
        </div>
        <div class="form-group" id="excludeTrade">
          <label class="col-md-2 control-label"></label>
          <div class="col-md-5">
            <div class="input-group">
              <div class="excludeTradeSelect" style="width : 100%"></div>
              <span class="input-group-addon"><i
                      class="icon icon-plus" style="cursor:pointer"
                      onclick="addExcludeTrade(this) ;"></i></span> <span
                    class="input-group-addon"><i class="icon icon-minus"
                                                 style="cursor:pointer"
                                                 onclick="removeExcludeTrade(this);"></i></span>
            </div>
          </div>
        </div>
        <div class="form-group" id="includeSellers">
          <label class="col-md-2 control-label">包含所选商家：</label>
          <div class="col-md-5">
            <textarea rows="5" style="width:100%;" name="includeSellerids"></textarea>
          </div>
        </div>
        <div class="form-group" id="excludeSellers">
          <label class="col-md-2 control-label">排除所选商家：</label>
          <div class="col-md-5">
            <textarea rows="5" style="width:100%;" name="excludeSellerids"></textarea>
          </div>
          <div class="col-md-2" style="display:none;color:red;" id="msg"></div>
        </div

          <%--优惠劵图片--%>
        <div class="form-group">
          <label class="col-md-2 control-label">优惠劵详情图：</label>
          <div class="col-md-2">
            <div class="input-group">
              <input type="hidden" class="form-control" id="picURL" name="pic"/>
              <div id="pic"></div>
            </div>
          </div>
          <label class="col-md-2 control-label">优惠劵列表图：</label>
          <div class="col-md-2">
            <div class="input-group">
              <input type="hidden" class="form-control" id="breviaryURL" name="breviary"/>
              <div id="breviary"></div>
            </div>
          </div>
        </div>

          <%--优惠劵使用规则--%>
        <div class="form-group">
          <label class="col-md-2 control-label">优惠劵使用规则：</label>
          <div class="col-md-5">
            <textarea name="rule" class="form-control"></textarea>
          </div>
        </div>

        <div class="form-group">
          <label class="col-md-2 control-label"></label>
          <div class="col-md-5">
            <div class="alert alert-warning">
              <strong>提示：</strong> 提交后需要编辑发放设置后，优惠券才能生效
            </div>
          </div>
        </div>
      </form>
    </div></div>


    <!-- 保存/取消按钮 -->
    <div class="col-md-12 text-center">
      <button type="submit" class="btn btn-danger"><i class="icon-save"></i>&nbsp;保存 </button>
      <button onclick="window.history.back();" type="button" class="btn btn-warning"><i class="icon-reply"></i>&nbsp;取消 </button>
    </div>

  </div>




  <!--商场优惠劵-添加标签页-->
  <div id="tab2" class="tab-pane fade">
    商场优惠劵-添加标签页

    <!-- 保存/取消按钮 -->
    <div class="col-md-12 text-center">
      <button type="submit" class="btn btn-danger"><i class="icon-save"></i>&nbsp;保存 </button>
      <button onclick="window.history.back();" type="button" class="btn btn-warning"><i class="icon-reply"></i>&nbsp;取消 </button>
    </div>
  </div>

  <!--平台通用优惠劵-添加标签页-->
  <div id="tab3" class="tab-pane fade">
    平台通用优惠劵-添加标签页

    <!-- 保存/取消按钮 -->
    <div class="col-md-12 text-center">
      <button type="submit" class="btn btn-danger"><i class="icon-save"></i>&nbsp;保存 </button>
      <button onclick="window.history.back();" type="button" class="btn btn-warning"><i class="icon-reply"></i>&nbsp;取消 </button>
    </div>
  </div>
</div>

</body>
</html>
<jsp:include page="../../common.jsp"></jsp:include>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/ux/js/searchChosen.js"></script>
<script src="<%=path%>/ux/js/searchChosen2.js"></script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        // 激活ZUI标签页
        $('[data-tab]').on('shown.zui.tab');

        // 切换时间条件,显示/隐藏有效期选择
        $("[name='swichtime']").change(function(){
            $("[valid-date]").toggle();
            $(this).parent('input').val("");
        })


    })


</script>

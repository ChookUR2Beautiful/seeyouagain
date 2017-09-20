<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>添加优惠券页面</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="add coupon">
  <meta http-equiv="description" content="add coupon init">
  <link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
  <link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
  <link href="<%=path%>/resources/webuploader/webuploader.css"
        rel="stylesheet">
  <link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
  <link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
        rel="stylesheet">
  <link
          href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
          rel="stylesheet">
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

<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
      class="doc-views with-navbar">
<div class="tab-content">
  <ul id="myTab" class="nav nav-tabs">
    <c:if test="${ btnAu['coupon/generate/couponPage']}">
      <li
              <c:if test="${ btnAu['coupon/generate/couponPage']}">class="active"</c:if>>
        <a href="#tab1" data-toggle="tab">消费优惠劵</a>
      </li>
    </c:if>

    <c:if test="${ btnAu['coupon/generate/freshcouponPage']}">
      <li
              <c:if test="${ empty btnAu['coupon/generate/couponPage'] && btnAu['coupon/generate/freshcouponPage']}"> class="active" </c:if>>
        <a href="#tab2" data-toggle="tab">商城优惠劵</a>
      </li>
    </c:if>
    <li>
      <a href="#tab3" data-toggle="tab">平台通用优惠劵</a>
    </li>
  </ul>
  <c:if test="${ btnAu['coupon/generate/couponPage']}">
    <div id="tab1"
         class="tab-pane <c:if test="${ btnAu['coupon/generate/couponPage']}">in active</c:if>">
      <div class="panel">
        <div class="panel-body">
          <form class="form-horizontal" id="editCouponForm">
            <input type="hidden" name="ctype" value="0"/>
            <div class="form-group" style="margin-top:40px;">
              <label class="col-md-2 control-label">优惠券名称：</label>
              <div class="col-md-3">
                <input type="text" name="cname" maxlength="15"
                       placeholder="15字内" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label">面额：</label>
              <div class="col-md-3">
                <div class="input-group">
                  <input type="text" name="denomination" class="form-control"/><span
                        class="input-group-addon">元</span>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-2 control-label">时间：</label>
              <div class="col-md-3">
                <div class="input-group">
										<span class="input-group-addon"> <label
                            class="radio-inline">领取有效天数 <input type="radio"
                                                               name="swichtime"
                                                               onchange="swichtimeYes();"
                                                               checked="checked" value="1"/>
										</label>
										</span>
                  <div id="customConditionDiv">
                    <input class='form-control' type="text" name="dayNum"/>
                  </div>
                  <span class="input-group-addon"> <label
                          class="radio-inline">设置有效日期区间 <input type="radio"
                                                               id="showTimex2" name="swichtime"
                                                               value="2"
                                                               onchange="swichtimeNo();"/>
										</label>
										</span>
                </div>
              </div>
            </div>

            <div class="form-group" id="dates" style="display: none;">
              <label class="col-md-2 control-label">有效日期：</label>
              <div class="col-md-8"></div>
            </div>

            <div class="form-group">
              <label class="col-md-2 control-label">使用条件：</label>
              <div class="col-md-3">
                <div class="input-group">
										<span class="input-group-addon"> <label
                            class="radio-inline">满 <input type="radio"
                                                          name="conditionRadio"
                                                          onchange="conditionYes();"
                                                          id="customConditionRadio" value="1"
                                                          checked="checked"/>
										</label>
										</span>
                  <div id="customConditionDiv">
                    <input class='form-control' type="text" name="condition"/>
                  </div>
                  <span class="input-group-addon">元(起)使用</span> <span
                        class="input-group-addon"> <label class="radio-inline">无条件使用
												<input type="radio" name="conditionRadio" value="0"
                               id="customConditionRadio1" onchange="conditionNo();"/>
										</label>
										</span>
                </div>
              </div>
            </div>
              <%--每次可同时使用张数--%>
              <input class="form-control" name="useNum" type="hidden" min="1" placeholder="1" value="999">

            <div class="form-group">
              <label class="col-md-2 control-label">是否可以叠加使用：</label>
              <div class="col-md-3">
                <div class="input-group">
                  <label class="radio-inline">是<input type="radio" name="overlay" value="1" onchange="onchangeOverlay(this)" checked="checked"/></label>
                  <label class="radio-inline">否<input type="radio" name="overlay" value="0" onchange="onchangeOverlay(this)" /></label>
                </div>
              </div>
            </div>


            <div class="form-group" id="showAll">
              <label class="col-md-2 control-label">是否平台通用：</label>
              <div class="col-md-3">
                <div class="input-group">
                  <label class="radio-inline">是<input type="radio"
                                                      name="showAll" onchange="showAllYes();"
                                                      id="allArea"
                                                      value="1" checked="checked"/></label> <label
                        class="radio-inline">否<input
                        type="radio" name="showAll" id="specifyArea" value="0"
                        onchange="showAllNo();"/></label>
                </div>
              </div>
            </div>

            <div class="form-group" id="cities">
              <label class="col-md-2 control-label"></label>
              <div class="col-md-5">
                <div class="input-group">
                  <div class="ld" style="width: 100%;float:left;"></div>
                  <span class="input-group-addon"><i
                          class="icon icon-plus" style="cursor:pointer"
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
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label">优惠劵详情图：</label>
              <div class="col-md-2">
                <div class="input-group">
                  <input type="hidden" class="form-control" id="picURL"
                         name="pic"/>
                  <div id="pic"></div>
                </div>
              </div>
              <label class="col-md-2 control-label">优惠劵列表图：</label>
              <div class="col-md-2">
                <div class="input-group">
                  <input type="hidden" class="form-control" id="breviaryURL"
                         name="breviary"/>
                  <div id="breviary"></div>
                </div>
              </div>
            </div>
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
            <div class="col-md-12 text-center">
              <button id="ensure" type="submit" class="btn btn-danger">
                <i class="icon-save"></i>&nbsp;保存
              </button>
              &nbsp;&nbsp;
              <button onclick="window.history.back();" type="button"
                      class="btn btn-warning">
                <i class="icon-reply"></i>&nbsp;取消
              </button>
            </div>
          </form>
        </div>
      </div>
      <div class="hidden dateTemp">
        <div class="input-group">
          <span class="input-group-addon">开始日期：</span> <input readonly
                                                              type="text"
                                                              name="coupnValidities[index].startDate"
                                                              class="form-control date-start"/>
          <span
                  class="input-group-addon">结束日期：</span> <input type="text"
                                                                readonly
                                                                name="coupnValidities[index].endDate"
                                                                class="form-control date-end"/><span
                class="input-group-addon fix-border fix-padding"></span><span
                class="input-group-addon">开始时间：</span> <input type="text"
                                                              readonly
                                                              name="coupnValidities[index].startTime"
                                                              class="form-control time-start"/>
          <span
                  class="input-group-addon">结束时间：</span> <input type="text"
                                                                name="coupnValidities[index].endTime"
                                                                readonly
                                                                class="form-control time-end"/>
          <span class="input-group-addon"><i
                  class="icon icon-plus" style="cursor:pointer"
                  onclick="addDate(this);"></i></span> <span class="input-group-addon"><i
                class="icon icon-minus" style="cursor:pointer"
                onclick="removeDate(this);"></i></span>
        </div>
      </div>
      <div class="cityTemp hidden ">
        <div class="input-group">
          <div class="ld" style="width: 100%;float:left;"></div>
          <span class="input-group-addon"><i class="icon icon-plus"
                                             style="cursor:pointer" onclick="addAreaLd(this);"></i></span>
          <span
                  class="input-group-addon"><i class="icon icon-minus"
                                               style="cursor:pointer"
                                               onclick="removeAreaLd(this)"></i></span>
        </div>
      </div>

      <div class="includeTradeSelectTemp hidden ">
        <div class="input-group">
          <div class="includeTradeSelect" style="width : 100%"></div>
          <span class="input-group-addon"><i class="icon icon-plus"
                                             style="cursor:pointer"
                                             onclick="addIncludeTrade(this) ;"></i></span> <span
                class="input-group-addon"><i class="icon icon-minus"
                                             style="cursor:pointer"
                                             onclick="removeIncludeTrade(this);"></i></span>
        </div>
      </div>
      <div class="excludeTradeSelectTemp hidden ">
        <div class="input-group">
          <div class="excludeTradeSelect" style="width : 100%"></div>
          <span class="input-group-addon"><i class="icon icon-plus"
                                             style="cursor:pointer"
                                             onclick="addExcludeTrade(this) ;"></i></span> <span
                class="input-group-addon"><i class="icon icon-minus"
                                             style="cursor:pointer"
                                             onclick="removeExcludeTrade(this);"></i></span>
        </div>
      </div>


      <div class="includeTradeSelectTemp3 hidden ">
        <div class="input-group">
          <div class="includeTradeSelect" style="width : 100%"></div>
          <span class="input-group-addon"><i class="icon icon-plus"
                                             style="cursor:pointer"
                                             onclick="addIncludeTrade(this) ;"></i></span> <span
                class="input-group-addon"><i class="icon icon-minus"
                                             style="cursor:pointer"
                                             onclick="removeIncludeTrade3(this);"></i></span>
        </div>
      </div>

      <div class="excludeTradeSelectTemp3 hidden ">
        <div class="input-group">
          <div class="excludeTradeSelect" style="width : 100%"></div>
          <span class="input-group-addon"><i class="icon icon-plus"
                                             style="cursor:pointer"
                                             onclick="addExcludeTrade(this) ;"></i></span> <span
                class="input-group-addon"><i class="icon icon-minus"
                                             style="cursor:pointer"
                                             onclick="removeExcludeTrade3(this);"></i></span>
        </div>
      </div>
    </div>
  </c:if>
  <!-- 商城优惠劵页签内容 -->
  <c:if test="${ btnAu['coupon/generate/freshcouponPage']}">
    <div id="tab2"
         class="tab-pane <c:if test="${ empty btnAu['coupon/generate/couponPage'] && btnAu['coupon/generate/freshcouponPage']}">in active</c:if>">
      <div class="panel">
        <div class="panel-body">
          <form class="form-horizontal" id="editCouponForm2">
            <!-- 商城优惠劵类型隐藏域 -->
            <input type="hidden" name="ctype" value="1"/>
            <div class="form-group" style="margin-top:40px;">
              <label class="col-md-2 control-label">优惠券名称：</label>
              <div class="col-md-3">
                <input type="text" name="cname" maxlength="15"
                       placeholder="15字内" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label">面额：</label>
              <div class="col-md-3">
                <div class="input-group">
                  <input type="text" name="denomination" class="form-control"/><span
                        class="input-group-addon">元</span>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label">时间：</label>
              <div class="col-md-3">
                <div class="input-group">
										<span class="input-group-addon"> <label
                            class="radio-inline">领取有效天数 <input type="radio"
                                                               id="swichtime1" name="swichtime"
                                                               onchange="swichtimeYes2();"
                                                               checked="checked" value="1"/>
										</label>
										</span>
                  <div id="customConditionDiv">
                    <input class='form-control' id="dayNum2" type="text"
                           name="dayNum2"/>
                  </div>
                  <span class="input-group-addon"> <label
                          class="radio-inline">设置有效日期区间 <input type="radio"
                                                               id="showTimex2" name="swichtime"
                                                               value="2"
                                                               onchange="swichtimeNo2();"/>
										</label>
										</span>
                </div>
              </div>
            </div>
            <div class="form-group" id="dates2" style="display: none;">
              <label class="col-md-2 control-label">有效日期：</label>
              <div class="col-md-8"></div>
            </div>

            <div class="form-group">
              <label class="col-md-2 control-label">使用条件：</label>
              <div class="col-md-3">
                <div class="input-group">
										<span class="input-group-addon"> <label
                            class="radio-inline">满 <input type="radio"
                                                          name="conditionRadio"
                                                          onchange="conditionYes2();"
                                                          id="customConditionRadio2" value="1"
                                                          checked="checked"/>
										</label>
										</span>
                  <div id="customConditionDiv">
                    <input class='form-control' id="condition2" type="text"
                           name="condition2"/>
                  </div>
                  <span class="input-group-addon">元(起)使用</span> <span
                        class="input-group-addon"> <label class="radio-inline">无条件使用
												<input type="radio" name="conditionRadio" value="0"
                               id="customConditionRadio1" onchange="conditionNo2();"/>
										</label>
										</span>
                </div>
              </div>
            </div>

              <%--每次可同时使用张数--%>
              <input class="form-control" name="useNum" type="hidden" min="1" placeholder="1" value="999">


            <div class="form-group">
              <label class="col-md-2 control-label">是否可以叠加使用：</label>
              <div class="col-md-3">
                <div class="input-group">
                  <label class="radio-inline">是<input type="radio" name="overlay" onchange="onchangeOverlay(this)" value="1" checked="checked"/></label> <label
                        class="radio-inline">否<input type="radio" name="overlay" onchange="onchangeOverlay(this)" value="0"  /></label>
                </div>
              </div>
            </div>

            <div class="form-group" id="showAll">
              <label class="col-md-2 control-label">是否平台通用：</label>
              <div class="col-md-3">
                <div class="input-group">
                  <label class="radio-inline">是<input type="radio"
                                                      name="showAll" onchange="showAllYes1();"
                                                      id="allArea"
                                                      value="1" checked="checked"/></label> <label
                        class="radio-inline">否<input
                        type="radio" name="showAll" id="specifyArea" value="0"
                        onchange="showAllNo1();"/></label>
                </div>
              </div>
            </div>

            <div class="form-group" id="cities2">
              <label class="col-md-2 control-label"></label>
              <div class="col-md-5">
                <div class="input-group">
                  <div class="ld" style="width: 386px;"></div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-2 control-label">优惠券详情图：</label>
              <div class="col-md-2">
                <div class="input-group">
                  <input type="hidden" class="form-control" id="picURL2"
                         name="pic"/>
                  <div id="pic2"></div>
                </div>
              </div>
              <label class="col-md-2 control-label">优惠券列表图：</label>
              <div class="col-md-2">
                <div class="input-group">
                  <input type="hidden" class="form-control" id="breviaryURL2"
                         name="breviary"/>
                  <div id="breviary2"></div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-2 control-label">优惠券使用规则：</label>
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
            <div class="col-md-12 text-center">
              <button id="ensure" type="submit" class="btn btn-danger">
                <i class="icon-save"></i>&nbsp;保存
              </button>
              &nbsp;&nbsp;
              <button onclick="window.history.back();" type="button"
                      class="btn btn-warning">
                <i class="icon-reply"></i>&nbsp;取消
              </button>
            </div>

          </form>
        </div>
      </div>
      <!-- 商城优惠劵有效日期 -->
      <div class="hidden dateTemp2">
        <div class="input-group">
          <span class="input-group-addon">开始日期：</span> <input readonly
                                                              type="text"
                                                              name="coupnValidities[index].startDate"
                                                              class="form-control date-start"/>
          <span
                  class="input-group-addon">结束日期：</span> <input type="text"
                                                                readonly
                                                                name="coupnValidities[index].endDate"
                                                                class="form-control date-end"/>
          <span
                  class="input-group-addon fix-border fix-padding"></span> <span
                class="input-group-addon">开始时间：</span> <input type="text"
                                                              readonly
                                                              name="coupnValidities[index].startTime"
                                                              class="form-control time-start"/>
          <span
                  class="input-group-addon">结束时间：</span> <input type="text"
                                                                name="coupnValidities[index].endTime"
                                                                readonly
                                                                class="form-control time-end"/>
        </div>
      </div>
      <div class="cityTemp2 hidden ">
        <div class="input-group">
          <div class="ld" style="width: 100%;float:left;"></div>
          <span class="input-group-addon"><i class="icon icon-plus"
                                             style="cursor:pointer"
                                             onclick="addAreaLd1(this);"></i></span> <span
                class="input-group-addon"><i class="icon icon-minus"
                                             style="cursor:pointer"
                                             onclick="removeAreaLd1(this)"></i></span>
        </div>
      </div>
    </div>
  </c:if>

  <div id="tab3" class="tab-pane"> <div class="panel"> <div class="panel-body">
    <form class="form-horizontal" id="editCouponForm3">

      <input type="hidden" name="ctype" value="5"/>
      <!-- 优惠劵名称 -->
      <div class="form-group" style="margin-top:40px;">
        <label class="col-md-2 control-label">优惠券名称：</label>
        <div class="col-md-3">
          <input type="text" name="cname" maxlength="15" placeholder="15字内" class="form-control"/>
        </div>
      </div>

      <!-- 优惠劵面额 -->
      <div class="form-group">
        <label class="col-md-2 control-label">面额：</label>
        <div class="col-md-3">
          <div class="input-group">
            <input type="number" name="denomination" min="1" class="form-control"/><span class="input-group-addon">元</span>
          </div>
        </div>
      </div>

      <!-- 时间 -->
      <div class="form-group">
        <label class="col-md-2 control-label">时间：</label>
        <div class="col-md-3">
          <div class="input-group">
          <span class="input-group-addon">
            <label class="radio-inline">领取有效天数
              <input type="radio"  name="swichtime" onchange="switchtimeOnchange(this);" checked="checked" value="1"/>
            </label>
          </span>
            <div id="customConditionDiv">
              <input class='form-control' type="number" min="1" name="dayNum"/>
            </div>
            <span class="input-group-addon">
              <label class="radio-inline">设置有效日期区间
                <input type="radio" id="showTimex2" name="swichtime" value="2" onchange="switchtimeOnchange(this);"/>
              </label>
            </span>
          </div>
        </div>
      </div>

      <div class="form-group" id="dates3" style="display: none;">
        <label class="col-md-2 control-label">有效日期：</label>
        <div class="col-md-8"></div>
      </div>

      <!-- 使用条件 -->
      <div class="form-group">
        <label class="col-md-2 control-label">使用条件：</label>
        <div class="col-md-3">
          <div class="input-group">
            <span class="input-group-addon">
              <label class="radio-inline">满
                <input type="radio" name="conditionRadio" onchange="conditionRadioOnchange(this);"
                       id="customConditionRadio" value="1" checked="checked"/>
              </label>
            </span>
            <div id="customConditionDiv"><input class='form-control' min="0" type="number" name="condition" value="0"/>
            </div>
            <span class="input-group-addon">元(起)使用</span> <span class="input-group-addon"> <label
                  class="radio-inline">无条件使用
            <input type="radio" name="conditionRadio" value="0" id="customConditionRadio1"
                   onchange="conditionRadioOnchange(this);"/>
          </label> </span>
          </div>
        </div>
      </div>

      <%--每次可同时使用张数--%>
      <input class="form-control" name="useNum" type="hidden" min="1" placeholder="1" value="999">


      <div class="form-group">
        <label class="col-md-2 control-label">是否可以叠加使用：</label>
        <div class="col-md-3">
          <div class="input-group">
            <label class="radio-inline">是<input type="radio" name="overlay" onchange="onchangeOverlay(this)" value="1" checked="checked"/></label> <label
                  class="radio-inline">否<input type="radio" name="overlay" onchange="onchangeOverlay(this)" value="0" /></label>
          </div>
        </div>
      </div>


      <!-- 是否平台通用 -->
      <div class="form-group" id="showAll">
        <label class="col-md-2 control-label">是否平台通用：</label>
        <div class="col-md-3">
          <div class="input-group">
            <label class="radio-inline">是<input type="radio" name="showAll" onchange="showAllOnchange(this);" id="allArea" value="1" checked="checked"/>
            </label>
            <label class="radio-inline">否<input type="radio" name="showAll" id="specifyArea" value="0" onchange="showAllOnchange(this);"/>
            </label>
          </div>
        </div>
      </div>

      <!-- 地区选择-->
      <div class="form-group" id="cities3" style="display: none;">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-5">
          <div class="input-group">
            <div class="ld" style="width: 100%;float:left;"></div>
            <span class="input-group-addon">
              <i class="icon icon-plus" style="cursor:pointer" onclick="addAreaLd(this) ;"></i>
            </span>
            <span class="input-group-addon">
              <i class="icon icon-minus" style="cursor:pointer" onclick="removeAreaLd(this);"></i>
            </span>
          </div>
        </div>
      </div>

      <!-- 是否全部商家-->
      <div class="form-group" id="allSeller3" style="display: none;">
        <label class="col-md-2 control-label">是否全部商家：</label>
        <div class="col-md-3">
          <div class="input-group">
            <label class="radio-inline">是<input checked="true" type="radio" name="isAllSeller" value="1" onchange="isAllSellerOnchange(this);"/></label><label
                  class="radio-inline">否<input type="radio" name="isAllSeller" value="0" onchange="isAllSellerOnchange(this);"/></label>
          </div>
        </div>
      </div>


      <!-- 行业选择-->
      <div class="form-group" id="trades3" style="display: none">
        <label class="col-md-2 control-label">行业选择：</label>
        <div class="col-md-3">
          <div class="input-group">
            <label class="radio-inline">包含所选行业<input checked="true" type="radio" name="trade" value="1" onchange="tradeInclude3();"/>
            </label>
            <label class="radio-inline">排除所选行业<input type="radio" name="trade" value="0" onchange="tradeExclude3();"/>
            </label>
          </div>
        </div>
      </div>

      <!--包含行业-->
      <div class="form-group" id="includeTrade3" style="display: none">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-5">
          <div class="input-group">
            <div class="includeTradeSelect" style="width : 100%"></div>
            <span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addIncludeTrade(this) ;"> </i></span>
            <span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
          </div>
        </div>
      </div>

      <%--排除的行业--%>
      <div class="form-group" id="excludeTrade3" style="display: none">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-5">
          <div class="input-group">
            <div class="excludeTradeSelect" style="width : 100%"></div>
            <span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addExcludeTrade(this) ;"></i></span>
            <span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="removeExcludeTrade(this);"></i></span>
          </div>
        </div>
      </div>

      <%--包含的商家--%>
      <div class="form-group" id="includeSellers3" style="display: none">
        <label class="col-md-2 control-label">包含所选商家：</label>
        <div class="col-md-5">
          <textarea rows="5" style="width:100%;" name="includeSellerids"></textarea>
        </div>
      </div>

      <%--排除的商家--%>
      <div class="form-group" id="excludeSellers3" style="display: none">
        <label class="col-md-2 control-label">排除所选商家：</label>
        <div class="col-md-5">
          <textarea rows="5" style="width:100%;" name="excludeSellerids"></textarea>
        </div>
        <div class="col-md-2" style="display:none;color:red;" id="msg"></div>
      </div>


      <!-- 优惠劵 详情/列表 图 -->
      <div class="form-group">
        <label class="col-md-2 control-label">优惠劵详情图：</label>
        <div class="col-md-2">
          <div class="input-group">
            <input type="hidden" class="form-control" id="picURL3" name="pic"/>
            <div id="pic3"></div>
          </div>
        </div>
        <label class="col-md-2 control-label">优惠劵列表图：</label>
        <div class="col-md-2">
          <div class="input-group">
            <input type="hidden" class="form-control" id="breviaryURL3" name="breviary"/>
            <div id="breviary3"></div>
          </div>
        </div>
      </div>

      <!-- 使用规则 -->
      <div class="form-group">
        <label class="col-md-2 control-label">优惠劵使用规则：</label>
        <div class="col-md-5">
          <textarea name="rule" class="form-control"></textarea>
        </div>
      </div>


      <!-- 提示 -->
      <div class="form-group">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-5">
          <div class="alert alert-warning">
            <strong>提示：</strong> 提交后需要编辑发放设置后，优惠券才能生效
          </div>
        </div>
      </div>



      <div class="col-md-12 text-center">
        <button id="ensure3" type="submit" class="btn btn-danger">
          <i class="icon-save"></i>&nbsp;保存
        </button>
        &nbsp;&nbsp;
        <button onclick="window.history.back();" type="button"
                class="btn btn-warning">
          <i class="icon-reply"></i>&nbsp;取消
        </button>
      </div>
    </form>

  </div>
  </div>
  </div>
</div>
<!-- tab_content -->
<script type="text/javascript">
    contextPath = '${pageContext.request.contextPath}';
    var type = "add";
</script>
<!-- <script type="text/json" id="permisions">{add:'${ btnAu['coupon/generate/add']}'}</script> -->
<!--<script type="text/json" id="permisions">{add:'${ btnAu['coupon/generate/freshcoupon/add']}'}</script>-->
<jsp:include page="../../common.jsp"></jsp:include>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/ux/js/searchChosen.js"></script>
<script src="<%=path%>/ux/js/searchChosen2.js"></script>
<script
        src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.js"></script>
<script src="<%=path%>/js/coupon/generate/couponAdd.js"></script>
</body>
</html>

<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>描述你的需求</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
    <link rel="stylesheet" href="${ctx}/css/component.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
        <div class="fill-list-module" >
            <div class="list-divhead">请选择适合您的名嘴</div>
            <a class="list-wrap" href="${ctx}/h5/opinion/reviewer/list">
                <div class="list-item in-icon-box"><i class="icon-wrap icon-arrow-right"></i>
                    <span href="#" class="item-input-wrap">${reviewer.name}</span>
                    <span class="item-name">食评名嘴</span>
                </div>
            </a>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥
            ${reviewer.reviewPrice}</span><span
                    class="review_price-name">预计食评费用</span></div></div>
            <div class="list-divhead">简单描述您的需求</div>
            <div class="list-wrap"><div class="list-item"><input type="text" class="item-input-entry" placeholder="一句话描述您的需求，例如：XXXX菜品点评" id="demand"/></div></div>
            <div class="list-divhead">说说您的具体要求</div>
            <div class="list-wrap" id="emphasis">
                <div class="list-item">
                    <span class="item-input-wrap">
                        <div class="activeity-radio-input sm-col-3" >
                            <span class="activeity-radio-item limit-fsize">
                                <input name="radioplay" type="radio" class="reset-radio-input" id="wxz-radio" option="1" checked/>
                                <label class="reset-radio" for="wxz-radio">
                                    <span class="item-radio-discount">
                                        <strong>餐厅</strong>
                                    </span>
                                </label>
                            </span>
                            <span class="activeity-radio-item limit-fsize">
                                <input name="radioplay" type="radio"  class="reset-radio-input" id="xfmhd-radio" option="2" />
                                <label class="reset-radio" for="xfmhd-radio">
                                    <span class="item-radio-discount">
                                        <strong>美食</strong>
                                    </span>
                                </label>
                            </span>
                        </div>
                    </span>
                    <span class="item-name">突出重点</span>
                </div>
            </div>
            <div class="list-wrap" id="crowd">
                <div class="list-item">
                    <span class="item-input-wrap">
                        <div class="activeity-radio-input sm-col-3" >
                            <span class="activeity-radio-item limit-fsize">
                                <input name="radiorenqun" type="radio" class="reset-radio-input" id="teding-radio" option="1" checked/>
                                <label class="reset-radio" for="teding-radio">
                                    <span class="item-radio-discount">
                                        <strong>特定人群</strong>
                                    </span>
                                </label>
                            </span>
                            <span class="activeity-radio-item limit-fsize">
                                <input name="radiorenqun" type="radio"  class="reset-radio-input" id="tongyong-radio" option="2" />
                                <label class="reset-radio" for="tongyong-radio">
                                    <span class="item-radio-discount">
                                        <strong>通用人群</strong>
                                    </span>
                                </label>
                            </span>
                        </div>
                    </span>
                    <span class="item-name">针对人群</span>
                </div>
            </div>
            <div class="list-divhead">时间设置</div>
            <div class="list-wrap">
                <div class="list-item in-icon-box activity-date-contain">
                    <div class="activeity-date-module">
                        <span class="aciveity-data-con aciveity-data-start">
                            <a href="javascript:;" class="activity-time-select" id="activity-starttime">
                                <i>选择日期</i>
                                <p></p>
                            </a>
                        </span>
                    </div>
                    <i class="icon-wrap icon-arrow-right"></i>
                    <span class="item-name">预计到店时间</span>
                </div>
            </div>
        </div>
        <p class="fill-list-desc">
            *通过名嘴食评加强店铺美食对用户的感染力，丰富店铺内容，加大宣传，最终刺激用户到店消费
        </p>
    </div>
    <div class="floor-module">
        <a class="floor-links links-type2" id='submitbutton' href="javascript:submitOrder();">提交浏览</a>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript" src="${ctx}/js/baseUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/opinion/reviewer-order-input.js"></script>
<script>
    $(function(){
        $('#activity-starttime').bind('click',function(){
            var _this = this;
            var endTime = $('#activity-endtime').attr('initTime');
            var startTime = $(_this).attr('initTime');
            var initTime;
            if(startTime) initTime = startTime;
            else initTime = endTime;
            new datePicker({
                initTime: initTime,
                successDestory: false,
                compareTime: endTime,
                operation: '<=',
                success: function(year,month,day,datePicker,compareStatus){
                    if(!compareStatus) Tips.show('请选择正确的时间');
                    else{
                        $(_this)
                                .attr('initTime',year + '年' + month + '月' + day + '日')
                                .html(year+'-'+month + '-' + day);
                        datePicker.destoryDatePicker();
                    }
                }
            });
        });
    })

   function submitOrder(){

       // 获取要求到店时间
       var arrivalTime = $("#activity-starttime").text();
       var k=/^(\d{4})-([\d]+)-([\d]+)$/
       if(!k.test(arrivalTime)){
           Tips.show("未选择到店时间");
           return null;
       }
       // 获取描述需求
       var demand = $("#demand").val();
       if(!demand){
           Tips.show("未填写需求描述");
           return null;
       }

       // 获取突出重点
       var emphasis = $("#emphasis :checked").attr("option");
       var crowd = $("#crowd :checked").attr("option");



       // 提交订单
        $.post("${ctx}/h5/opinion/reviewer/order/submit",
                {
                    // 名嘴ID
                    celebrityId : ${reviewer.id},
                    // 要求到店时间
                    arrivalTime : arrivalTime,
                    // 需求描述
                    demand : demand ,
                    // 突出重点
                    emphasis : emphasis,
                    // 针对人群
                    crowd : crowd
                },
                function(data){
                    // 订单创建成功
                    if (data.state == 0) { // 返回状态为 0
                        Tips.show("订单创建成功!");
//                        window.setTimeout("redirect()",2000);

                        // 订单创建成功 调用APP进入支付页面
                        if (isIOS()) {  // IOS 设备
                            setupWebViewJavascriptBridge(function (bridge) {
                                bridge.callHandler(
//                                        'objectCallback',
                                        'objectCallbackReviewerOrder',
                                        {'orderId':data.result.id  , "orderType": 2},
                                        function (response) {log(response)});
                            });

                        } else {        // Android 设备
                            //Tips.show("调用安卓支付");
                            document.location = "xmn://com.xmn.merchant/PaymentActivity?orderId=" + data.result.id + "&orderType=2";
                        }
                    } else {        // 状态码 不为0
                        Tips.show("创建订单失败,请稍后重试!");
                    }
                }
        );
   }
   function redirect(){
       window.location.href='${ctx}/h5/opinion/reviewer/order/list';
   }


   /*这段代码是固定的，必须要放到js中*/
    function setupWebViewJavascriptBridge(callback) {
        if (window.WebViewJavascriptBridge) {
            return callback(WebViewJavascriptBridge);
        }
        if (window.WVJBCallbacks) {
            return window.WVJBCallbacks.push(callback);
        }
        window.WVJBCallbacks = [callback];
        var WVJBIframe = document.createElement('iframe');
        WVJBIframe.style.display = 'none';
        WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
        document.documentElement.appendChild(WVJBIframe);
        setTimeout(function() {
            document.documentElement.removeChild(WVJBIframe)
        }, 0)
    };

</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建满减活动</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
    <style>
        .fill-list-module .list-hide{
            display: none;
        }

        .step3-title{
            position: absolute;
            width: 80px;
            left:0;
            top:12px;
        }

        .fill-list-module .list-wrap .step3-content{
            float:none;
            padding-left: 100px;
            height: auto;
            display: block;
            text-align: right;
        }

        .sel-coupon{
            display: block;
            position: absolute;
            top:0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 6;
        }
        .end{
            font-size: 12px;
            text-align: center;
            padding:2px 0;
            margin:3px 0;
        }
    </style>
</head>
<body class="padd-fill-tb bg-color-01">
    <section id="step1" style="display: none;">
        <div class="container-wrap">
            <div class="activitysum-module">
                <div class="activitysum-wrap">
                    <div class="activitysum-name">消费并支付满</div>
                    <div class="activitysum-input"><span><input type="text" class="amount" placeholder="输入消费并支付满金额" id="totalmoney"/></span></div>
                </div>
            </div>
            <div class="activeity-date-module">
                <div class="activeity-dived">活动时间</div>
                    <div class="activeity-date-con">
                    <span class="aciveity-data-con aciveity-data-start">
                        <!-- <input type="text" placeholder="选择日期" id="activity-starttime" readonly/> -->
                        <a href="javascript:;" class="activity-time-select" id="activity-starttime">
                            <i>选择日期</i> 
                            <p></p>
                        </a>
                        <i class="icon-wrap icon-arrow-right"></i>
                    </span>
                    <span class="aciveity-data-desc">至</span>
                    <span class="aciveity-data-con aciveity-data-end">
                        <!-- <input type="text" placeholder="选择日期" id="activity-endtime" readonly/> -->
                        <a href="javascript:;" class="activity-time-select" id="activity-endtime">
                            <i>选择日期</i> 
                            <p></p>
                        </a>
                        <i class="icon-wrap icon-arrow-right"></i>
                    </span>
                </div>
            </div>
            <div class="ativety-data-name">
                <div class="activeity-dived">满就减名称</div>
                <div class="activeity-input"><input type="text" placeholder="例如：满100抵10元" id="activity-name" /></div>
            </div>
            <div class="ativety-data-type">
                <div class="activeity-dived">红包金额类型</div>
                <div class="activeity-radio-input">
                    <span class="activeity-radio-item">
                        <input name="fullradio" type="radio" class="reset-radio-input" id="manjjian-radio" checked/>
                        <label class="reset-radio" for="manjjian-radio">
                            <span class="item-radio-discount">
                                <strong>满就减</strong>
                            </span>

                        </label>
                    </span>
                    <!-- 
                    <span class="activeity-radio-item">
                        <input name="fullradio" type="radio"  class="reset-radio-input" id="manjson-radio" />
                        <label class="reset-radio" for="manjson-radio">
                            <span class="item-radio-discount">
                                <strong>满就送</strong>
                            </span>
                        </label>
                    </span>
                     -->
                </div>
            </div>
        </div>
        <div class="floor-module">
            <a class="floor-links links-disabled" href="javascript:;" id="gesteptwo">下一步</a>
        </div>
    </section>
    <section id="step2-1" style="display: none;">
        <div class="container-wrap">
            <div class="fill-list-module">
                <div class="list-divhead">满就减设置</div>
                <div class="list-wrap">
                    <div class="list-item">
                        <span class="item-input-wrap">
                            <div id="sass-switch" class="sass-switch" style="margin-top: -4px;"></div>
                        </span>
                        <span class="item-name">是否允许多级递减</span>
                    </div>
                </div>
                <div class="list-wrap levone">
                    <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请输入减免金额" id="normalreduction"/></span><span class="item-name">减免金额</span></div>
                </div>
                <div class="list-wrap levone">
                    <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写5000元以内的金额" id="normalconsum"/></span><span class="item-name">消费并支付满</span></div>
                </div>
                <div class="morelevel list-hide">
                    <div class="list-wrap ">
                        <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写一级减免金额" id="levonereduction"/></span><span class="item-name">一级减免金额</span></div>
                    </div>
                    <div class="list-wrap">
                        <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写5000元以内的金额" id="levoneconsum"/></span><span class="item-name">消费并支付满</span></div>
                    </div>
                    <div class="list-wrap  list-item-reset">
                        <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写二级减免金额" id="levtworeduction"/></span><span class="item-name">二级减免金额</span></div>
                    </div>
                    <div class="list-wrap ">
                        <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写5000元以内的金额" id="levtwoconsum"/></span><span class="item-name">消费并支付满</span></div>
                    </div>
                    <div class="list-wrap  list-item-reset">
                        <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写三级减免金额" id="levthreereduction"/></span><span class="item-name">三级减免金额</span></div>
                    </div>
                    <div class="list-wrap">
                        <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" class="amount" placeholder="请填写5000元以内的金额" id="levthreeconsum"/></span><span class="item-name">消费并支付满</span></div>
                    </div>
                </div>
                
                <div class="list-wrap">
                    <div class="list-item">
                        <span class="item-input-wrap">
                            <div id="every-switch" class="sass-switch" style="margin-top: -4px;"></div>
                        </span>
                        <span class="item-name">是否限制每人参与一次</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="floor-module">
            <a class="floor-links links-type2" href="javascript:;" id="step2-1submit">提交浏览</a>
            <!-- <a class="floor-links links-type2" href="submitfixsumbag.html">提交浏览</a> -->
        </div>
    </section>
    
    <section id="step3" style="display: none;">
        <div class="container-wrap">
            <div class="fill-list-module resetfill-list">
                <div class="list-wrap" id="step3-dynamic">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-activityname"></span><span class="item-name">满就减名称</span></div>
                </div>
				<div id="step3_init"></div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="everylimit"></span><span class="item-name">是否限制每人参与一次</span></div>
                </div>

                
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-date"></span><span class="item-name">活动时间</span></div>
                </div>
            </div>
            <p class="fill-list-desc">
                * 满就减创建活动以后，顾客消费过程中满特定金额后可以进行减免
            </p>
        </div>
        <div class="floor-module">
            <a class="floor-links links-type2" href="javascript:;" id="submitform">提交</a>
        </div>
    </section>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/component.js"></script>
    <script type="text/javascript" src="${ctx}/js/coupon/fullcut.js"></script>
    <script type="text/javascript" src="${ctx}/js/util.js"></script>
</body>
</html>
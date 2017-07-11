<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建赠品券</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css" />
</head>
<body class="padd-fill-tb bg-color-01">
	<input type="hidden" id = "adlistUrl" value="${adlistUrl }"/>
	<input type="hidden" id = "sellerId" value="${sellerId }"/>
    <section id="step1" style="display: none;">
        <div class="container-wrap">
            <div class="activitysum-module">
                <div class="activitysum-wrap">
                    <div class="activitysum-name">发放数量（张）</div>
                    <div class="activitysum-input"><span><input type="number" placeholder="请输入数量" id="totalNum" value="" /></span></div>
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
                <div class="activeity-dived">赠品券名称</div>
                <div class="activeity-input">
                    <input id="gift-name" type="text" placeholder="例如：新用户免费赠送凉菜一份" value="" />
                </div>
            </div>
            
        </div>
        <div class="floor-module">
            <a class="floor-links links-disabled" href="javascript:;" id="gesteptwo">下一步</a>
        </div>
    </section>
    <section id="step2" style="display: none;">
        <div class="container-wrap">
            <div class="fill-list-module">
                <div class="list-divhead">赠品券设置</div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp2-totalNum"></span><span class="item-name">发放数量</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp2-giftname"></span><span class="item-name">赠品名称</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="text" id="gift-descript"/></span><span class="item-name">赠品描述</span></div>
                </div>
                <div class="list-divhead">领取条件</div>
                <div class="list-wrap">
                    <div class="list-item">
                        <div class="activeity-radio-input sm-col-3">
                            <span class="activeity-radio-item limit-fsize">
                        <input name="radioplay" type="radio" condition="0" class="reset-radio-input" id="wxz-radio" checked/>
                        <label class="reset-radio" for="wxz-radio">
                            <span class="item-radio-discount">
                                <strong>无限制</strong>
                            </span>
                            </label>
                            </span>
                            <span class="activeity-radio-item limit-fsize">
                        <input name="radioplay" type="radio" condition="1" class="reset-radio-input" id="xfmhd-radio" />
                        <label class="reset-radio" for="xfmhd-radio">
                            <span class="item-radio-discount">
                                <strong>消费满获得</strong>
                            </span>
                            </label>
                            </span>
                            <span class="activeity-radio-item limit-fsize">
                        <input name="radioplay" type="radio" condition="2" class="reset-radio-input" id="xyhbd-radio" />
                        <label class="reset-radio" for="xyhbd-radio">
                            <span class="item-radio-discount">
                                <strong>新用户绑定获得</strong>
                            </span>
                            </label>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="list-wrap" id="div-consumeAndPay">
                    <div class="list-item"><span class="item-input-wrap"><input name="sumName" type="number" placeholder="请填写1000元以内的金额" id="paycondition"/></span><span class="item-name">消费并支付满</span></div>
                </div>
                <div class="list-divhead">限领条件</div>
                <div class="list-wrap">
                    <div class="list-item">
                        <span class="item-input-wrap">
                            <div id="every-switch" class="sass-switch" style="margin-top: -4px; height: 5px"></div>
                        </span>
                        <span class="item-name">是否限制每人参与一次</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="floor-module">
            <a class="floor-links links-type2" href="javascript:;" id="step2submit">提交浏览</a>
        </div>
    </section>
    
    <section id="step3" style="display: none;">
        <div class="container-wrap">
            <div class="fill-list-module resetfill-list">
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-giftname"></span><span class="item-name">赠品名称</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-descript"></span><span class="item-name">赠品描述</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-totalnum"></span><span class="item-name">发放数量</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-getcondition"></span><span class="item-name">获得条件</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-limitcondition"></span><span class="item-name">限领条件</span></div>
                </div>
                <div class="list-wrap">
                    <div class="list-item"><span class="item-input-wrap" id="stp3-date"></span><span class="item-name">活动时间</span></div>
                </div>
            </div>
            <p class="fill-list-desc">
				* 赠品劵，客户到店消费满特定条件后，自动赠送一张赠品劵，下次消费时自动使用该赠品劵
            </p>
        </div>
        <div class="floor-module">
            <a class="floor-links links-type2" href="javascript:;" id="submitform">提交</a>
        </div>
    </section>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/component.js"></script>
    <script type="text/javascript" src="${ctx}/js/coupon/gift.js"></script>
</body>
</html>
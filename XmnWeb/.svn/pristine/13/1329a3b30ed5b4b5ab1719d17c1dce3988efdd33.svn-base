var searchChosen = undefined, searchChosen2 = undefined;
var searchChooseURL = "marketingManagement/activityManagement/init/choseSeller/init.jhtml";
var allSeller, cities, cities2, trades, includeSellers, excludeSellers, includeTrade, excludeTrade;
var includeTrade3, excludeTrade3,includeSellers3,excludeSellers3,cities3,allSeller3;
var includeSellerids = undefined, excludeSellerids = undefined;
var trades3;
var datesIndex;
var includeTradeIndex;
var excludeTradeIndex;
var areaIndex;
function cacheDivs() {
    allSeller = $("#allSeller").html();
    cities = $("#cities").html();
    cities2 = $("#cities2").html();
    includeSellers = $("#includeSellers").html();
    excludeSellers = $("#excludeSellers").html();
    includeTrade = $("#includeTrade").html();
    excludeTrade = $("#excludeTrade").html();
    trades = $("#trades").html();

    includeTrade3 = $("#includeTrade3").html();
    excludeTrade3 = $("#excludeTrade3").html();
    includeSellers3 = $("#includeSellers3").html();
    excludeSellers3 = $("#excludeSellers3").html();
    cities3 = $("#cities3").html();
    trades3 = $("#trades3").html();
    allSeller3 = $("#allSeller3").html();

}
/**
 * 有效日期,选中：自定义天数
 */
function swichtimeYes() {
    // console.log("有效日期,选择自定义天数");
    dayNumInputRead();
    $("#dates").hide();
    $("#dates").find(".col-md-8").empty();
}

/**
 * 商城优惠劵（有效日期,选中：自定义天数）
 */
function swichtimeYes2() {
    // console.log("有效日期,选择自定义天数");
    dayNumInputRead2();
    $("#dates2").hide();
    $("#dates2").find(".col-md-8").empty();
}
function swichtimeYes3() {
    // console.log("有效日期,选择自定义天数");
    dayNumInputRead2();
    $("#dates3").hide();
    $("#dates3").find(".col-md-8").empty();
}
/**
 * 有效日期,选中：具体时间
 */
function swichtimeNo() {
    // console.log("有效日期选择具体时间");
    dayNumInputReadOnly();
    $("#dates").show();
    $("#dates").find(".col-md-8").html(replaceDateTempIndex(0));
    datesIndex = datesSize();
    initDate();
}

/**
 * 有效日期,选中：具体时间
 */
function swichtimeNo1() {
    // console.log("有效日期选择具体时间");
    dayNumInputReadOnly2();
    $("#dates3").show();
    $("#dates3").find(".col-md-8").html(replaceDateTempIndex2(0));
    datesIndex = datesSize();
    initDate();
}

/**
 * 商城优惠劵页签（有效日期,选中：具体时间）
 */
function swichtimeNo2() {
    // console.log("有效日期选择具体时间");
    dayNumInputReadOnly2();
    $("#dates2").show();
    $("#dates2").find(".col-md-8").html(replaceDateTempIndex2(0));
    datesIndex = datesSize();
    initDate();
}
/**
 * 将.dateTemp中的index替换成具体的数值
 */
function replaceDateTempIndex(index) {
    return $(".dateTemp").html().replace(/index/g, index);
}
/**
 * 商城优惠劵页签（将.dateTemp2中的index替换成具体的数值）
 */
function replaceDateTempIndex2(index) {
    return $(".dateTemp2").html().replace(/index/g, index);
}

function datesSize() {
    return $("#dates > .col-md-8").find(".input-group").length
}

function canAddDate() {
    return datesSize() < 10;
}

function canRemoveDate() {
    return datesSize() > 1;
}
/**
 * 添加有效时间
 *
 * @param object
 */
function addDate(object) {
    if (canAddDate()) {
        $(object).parents(".col-md-8").append(replaceDateTempIndex(datesIndex));
        datesIndex++;
        initDate();
    }
}

/**
 * 删除有效时间
 *
 * @param object
 */
function removeDate(object) {
    if (canRemoveDate()) {
        $(object).parents(".input-group").remove();
    }
}

function initDate() {
    var date = {
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        minView: "month",
    }, time = {
        format: "hh:ii",
        autoclose: true,
        startDate: "1899-12-31 00:00",
        endDate: "1899-12-31 23:59",
        minView: "hour",
        maxView: "hour",
        startView: "day",
        minuteStep: 1,
        initialDate: "1899-12-31"
    };
    $(".date-start").each(
        function (index, element) {
            // 初始化结束时间
            $(element).siblings(".date-end").datetimepicker($.extend({
                startDate: new Date()
            }, date)).on(
                "changeDate",
                function () {
                    $(element).datetimepicker("setEndDate",
                        $(element).siblings(".date-end").val());
                });

            // 初始化开始时间
            $(element).datetimepicker($.extend({
                startDate: new Date()
            }, date)).on(
                "changeDate",
                function () {
                    $(element).siblings(".date-end").datetimepicker(
                        "setStartDate", $(element).val());
                });

        });
    $(".time-start").each(
        function (index, element) {
            $(element).siblings(".time-end").datetimepicker(
                $.extend({}, time)).on(
                "changeDate",
                function () {
                    $(element).datetimepicker("setEndDate",
                        $(element).siblings(".time-end").val());
                });

            $(element).datetimepicker($.extend({}, time)).on(
                "changeDate",
                function () {
                    $(element).siblings(".time-end").datetimepicker(
                        "setStartDate", $(element).val());
                });
        });
}
/**
 * 消费优惠劵--领取有效天数变为不可编辑
 */
function dayNumInputReadOnly() {
    $("input[name='dayNum']").val("").attr("readOnly", true);
}
/**
 * 商城优惠劵--领取有效天数变为不可编辑
 */
function dayNumInputReadOnly2() {
    $("#dayNum2").val("").attr("readOnly", true);
}
function dayNumInputReadOnly3() {
    $("#dayNum3").val("").attr("readOnly", true);
}
/**
 * 消费优惠劵--领取有效天数变为可编辑
 */
function dayNumInputRead() {
    $("input[name='dayNum']").attr("readOnly", false);
}
/**
 * 商城优惠劵--领取有效天数变为可编辑
 */
function dayNumInputRead2() {
    $("#dayNum2").attr("readOnly", false);
}

/**
 * 商城优惠劵--领取有效天数变为可编辑
 */
function dayNumInputRead3() {
    $("#dayNum3").attr("readOnly", false);
}
/**
 * 消费优惠劵--使用条件,选中：有条件使用
 */
function conditionYes() {
    conditionInputRead();
}
/**
 * 商城优惠劵--使用条件,选中：有条件使用
 */
function conditionYes2() {
    conditionInputRead2();
}

/**
 * 消费优惠劵--使用条件,选中：无条件使用
 */
function conditionNo() {
    conditionInputReadOnly();
}
/**
 * 商城优惠劵--使用条件,选中：无条件使用
 */
function conditionNo2() {
    conditionInputReadOnly2();
}
/**
 * 消费优惠劵--使用条件变为不可编辑
 */
function conditionInputReadOnly() {
    $("input[name='condition']").val(0).attr("value", 0)
        .attr("readOnly", true);
}

/**
 * 商城优惠劵--使用条件变为不可编辑
 */
function conditionInputReadOnly2() {
    $("#condition2").val(0)
        .attr("readOnly", true);
}

/**
 * 消费优惠劵--使用条件变为可编辑
 */
function conditionInputRead() {
    $("input[name='condition']").attr("readOnly", false);
}
/**
 * 商城优惠劵--使用条件变为可编辑
 */
function conditionInputRead2() {
    $("#condition2").attr("readOnly", false);
}

/**
 * 是否平台通用,选中：是
 */
function showAllYes() {
    // console.log("是否平台通用,选中：是");
    $("#allSeller").empty().hide();
    $("#cities").empty().hide();
    $("#trades").empty().hide();
    $("#includeTrade").empty().hide();
    $("#excludeTrade").empty().hide();
    $("#includeSellers").empty().hide();
    $("#excludeSellers").empty().hide();
}

/**
 * 是否平台通用，选中：否
 */
function showAllNo() {
    console.log("是否平台通用,选中：否");
    $("#allSeller").html(allSeller).show();
    if ($("input[name='isAllSeller']").val() == 1) {
        isAllSellerYes();
    } else {
        isAllSellerNo();
    }
}
/**
 * 是否平台通用，选中：否
 */
function showAllNo1() {
//	 console.log("是否平台通用1,选中：否");
    $("#cities2").html(cities2).show();
    initAreaLd2();
    areaIndex = citiesSize();
}

function showAllNo2() {
    console.log("是否平台通用,选中：否");
    $("#allSeller3").html(allSeller3).show();
    if ($("input[name='isAllSeller']").val() == 1) {
        isAllSellerYes3();
    } else {
        isAllSellerNo2();
    }

}

function isAllSellerYes3() {
    // console.log("是否全部商家，选中：是");
    $("#trades3").empty().hide();
    $("#includeTrade3").empty().hide();
    $("#excludeTrade3").empty().hide();
    $("#includeSellers3").empty().hide();
    $("#excludeSellers3").empty().hide();
    $("#cities3").html(cities3).show();
    initAreaLd3();
    areaIndex = citiesSize3();
}
function isAllSellerNo2() {

}

/**
 * 是否全部商家，选中：是
 */
function isAllSellerYes() {
    // console.log("是否全部商家，选中：是");
    $("#trades").empty().hide();
    $("#includeTrade").empty().hide();
    $("#excludeTrade").empty().hide();
    $("#includeSellers").empty().hide();
    $("#excludeSellers").empty().hide();
    $("#cities").html(cities).show();
    initAreaLd();
    areaIndex = citiesSize();

}

/**
 * 是否全部商家，选中：否
 */
function isAllSellerNo() {
    // console.log(" 是否全部商家，选中：否");
    $("#cities").empty().hide();
    $("#trades").html(trades).show();
    if ($("input[name='trade']").val() == 1) {
        $("#includeTrade").html(includeTrade).show();
    } else {
        $("#excludeTrade").html(excludeTrade).show();
    }
    $("#includeSellers").html(includeSellers).show();
    $("#excludeSellers").html(excludeSellers).show();
    initTrade();
    initSellerChosen();
    includeTradeIndex = includeTradeSize();
    excludeTradeIndex = excludeTradeSize();
}
function isAllSellerNo3(){
    // console.log(" 是否全部商家，选中：否");
    $("#cities3").empty().hide();
    $("#trades3").html(trades).show();
    // if ($("input[name='trade']").val() == 1) {
    //     $("#includeTrade3").html(includeTrade3).show();
    // } else {
    //     $("#excludeTrade3").html(excludeTrade3).show();
    // }
    $("#includeSellers3").html(includeSellers3).show();
    $("#excludeSellers3").html(excludeSellers3).show();
    initTrade();
    initSellerChosen();
    includeTradeIndex = includeTradeSize();
    excludeTradeIndex = excludeTradeSize();
}


/**
 * 是否平台通用,选中：是
 */
function showAllYes1() {
//	 console.log("是否平台通用1,选中：是");
    $("#cities2").empty().hide();
}

function showAllYes2() {
//	 console.log("是否平台通用1,选中：是");
    $("#cities3").empty().hide();
    $("#allSeller3").empty().hide();
}

/**
 * 行业选择，选中：包含的行业
 */
function tradeInclude() {
    // console.log(" 行业选择，选中：包含的行业");
    $("#includeTrade").html(includeTrade).show();
    $("#excludeTrade").empty().hide();
    initTrade();
    includeTradeIndex = includeTradeSize();
}

/**
 * 行业选择，选中：包含的行业
 */
function tradeInclude() {
    // console.log(" 行业选择，选中：包含的行业");
    $("#includeTrade3").html(includeTrade).show();
    $("#excludeTrade3").empty().hide();
    initTrade();
    includeTradeIndex = includeTradeSize();
}

/**
 * 行业选择，选中：排除的行业
 *
 */
function tradeExclude() {
    // console.log(" 行业选择，选中：排除的行业");
    $("#excludeTrade").html(excludeTrade3).show();
    $("#includeTrade").empty().hide();
    initTrade3();
    excludeTradeIndex = excludeTradeSize();
}

/**
 * 行业选择，选中：排除的行业
 *
 */
function tradeExclude3() {
    // console.log(" 行业选择，选中：排除的行业");
    $("#excludeTrade3").html(excludeTrade3).show();
    $("#includeTrade3").empty().hide();
    initTrade3();
    excludeTradeIndex = excludeTradeSize();
}

/**
 * 添加区域选择组件
 */
function addAreaLd(object) {
    if (canAddAreaLd()) {
        // console.log("添加区域选择组件");
        // console.log($(object).parents(".input-group"));
        $(object).parents(".input-group").after($(".cityTemp").html()).next()
            .find(".ld").each(function () {
            // console.log(this);
            $(this).areaLd({
                isChosen: true,
                separator: ",",
                showConfig: [{
                    name: "couponCities[" + areaIndex + "].provinces",
                    tipTitle: "--省--"
                }, {
                    name: "couponCities[" + areaIndex + "].city",
                    tipTitle: "--市--"
                }]
            });
        });
        areaIndex++;
    }
}

/**
 * 添加区域选择组件
 */
function addAreaLd1(object) {
    if (canAddAreaLd1()) {
        // console.log("添加区域选择组件");
        // console.log($(object).parents(".input-group"));
        $(object).parents(".input-group").after($(".cityTemp2").html()).next()
            .find(".ld").each(function () {
            // console.log(this);
            $(this).areaLd({
                isChosen: true,
                separator: ",",
                showConfig: [{
                    name: "couponCities[" + areaIndex + "].provinces",
                    tipTitle: "--省--"
                }, {
                    name: "couponCities[" + areaIndex + "].city",
                    tipTitle: "--市--"
                }]
            });
        });
        areaIndex++;
    }
}

/**
 * 删除区域选择组件
 */
function removeAreaLd(object) {
    console.log("删除区域选择组件");
    if (canRemoveAreaLd()) {
        $(object).parents(".input-group").remove();
    }
}
function citiesSize() {
    return $("#cities > .col-md-5").find(".input-group").length;
}
function citiesSize3() {
    return $("#cities3 > .col-md-5").find(".input-group").length;
}

function canAddAreaLd() {
    return citiesSize() < 10 ? true : false;
}

function canRemoveAreaLd() {
    return citiesSize() > 1 ? true : false;
}


/**
 * 删除区域选择组件
 */
function removeAreaLd1(object) {
//	 console.log("删除区域选择组件");
    if (canRemoveAreaLd1()) {
        $(object).parents(".input-group").remove();
    }
}
function citiesSize1() {
    return $("#cities2 > .col-md-5").find(".input-group").length;
}

function canAddAreaLd1() {
    return citiesSize1() < 10 ? true : false;
}

function canRemoveAreaLd1() {
    console.log(citiesSize1());
    return citiesSize1() > 1 ? true : false;
}

/**
 * 添加包含所选行业
 *
 * @param object
 */
function addIncludeTrade(object) {
    if (canAddIncludeTrade()) {
        console.log("添加包含所选行业");
        var categoryName = "includeTrade[" + includeTradeIndex + "].category";
        var selleridName = "includeTrade[" + includeTradeIndex + "].sellerid";
        $(object).parents(".input-group").last().after(
            $(".includeTradeSelectTemp").html()).next().find(
            ".includeTradeSelect").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
        includeTradeIndex++;
    }
}
/**
 * 删除包含所选行业
 *
 * @param object
 */
function removeIncludeTrade(object) {
    if ($(object).parent().parent().parent().children().length > 1) {
        console.log("删除包含所选行业");
        $(object).parents(".input-group").remove();
    }
}
function removeIncludeTrade3(object) {
    if (canRemoveIncludeTrade3()) {
        console.log("删除包含所选行业");
        $(object).parents(".input-group").remove();
    }
}

/**
 * 取得已经添加的包含所选行业的行业数量
 *
 * @returns
 */
function includeTradeSize() {
    return $("#includeTrade > .col-md-5").find(".input-group").length;
}

function includeTradeSize3() {
    return $("#includeTrade3 > .col-md-5").find(".input-group").length;
}



function canAddIncludeTrade() {
    return includeTradeSize() < 10;
}

function canRemoveIncludeTrade() {
    return includeTradeSize() > 1;
}
function canRemoveIncludeTrade3() {
    return includeTradeSize3() > 1;
}
/**
 * 取得已经添加的不包含所选行业的行业数量
 *
 * @returns
 */
function excludeTradeSize() {
    return $("#excludeTrade > .col-md-5").find(".input-group").length;
}
function excludeTradeSize3() {
    return $("#excludeTrade3 > .col-md-5").find(".input-group").length;
}

function canAddExcludeTrade() {
    return excludeTradeSize() < 10;
}

function canRemoveExcludeTrade() {
    return excludeTradeSize() > 1;
}

/**
 * 添加不包含所选行业
 *
 * @param object
 */
function addExcludeTrade(object) {
    if (canAddExcludeTrade()) {
        // console.log("添加不包含所选行业");
        var categoryName = "excludeTrade[" + excludeTradeIndex + "].category";
        var selleridName = "excludeTrade[" + excludeTradeIndex + "].sellerid";
        $(object).parents(".input-group").last().after(
            $(".excludeTradeSelectTemp").html()).next().find(
            ".excludeTradeSelect").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
    }
}

/**
 * 删除不包含所选行业
 *
 * @param object
 */
function removeExcludeTrade(object) {
    if (canRemoveExcludeTrade()) {
        // console.log("添加不包含所选行业");
        $(object).parents(".input-group").remove();
    }
}

function areaLd(object, index) {
    $(object).areaLd({
        isChosen: true,
        separator: ",",
        showConfig: [{
            name: "couponCities[" + index + "].provinces",
            tipTitle: "--省--"
        }, {
            name: "couponCities[" + index + "].city",
            tipTitle: "--市--"
        }]
    });
}

function initSellerChosen() {
    includeSellerids = $("textarea[name='includeSellerids']").searchChosen({
        url: searchChooseURL + "?type=1",
        initId: "sellerid",
        initTitle: "sellername"
    });

    excludeSellerids = $("textarea[name='excludeSellerids']").searchChosen2({
        url: searchChooseURL + "?type=2",
        initId: "sellerid",
        initTitle: "sellername"
    });
}

function initAreaLd() {
    areaLd($("#cities").find(".ld"), 0);
}

function initAreaLd2() {
    areaLd($("#cities2").find(".ld"), 0);
}
function initAreaLd3() {
    areaLd($("#cities3").find(".ld"), 0);
}

function initAreaLdAndChosen() {
    initAreaLd();
    initSellerChosen();

}

function initRadios() {
    if ($("input[name='showAll']").val() == 1) {
        showAllYes();
        showAllYes1();
    } else {
        showAllNo();
        if ($("input[name='isAllSeller']").val() == 1) {
            isAllSellerYes();
        } else {
            isAllSellerNo();
        }
    }
}
function initTrade() {
    initIncludeTrade(0);
    initExcludeTrade(0);
}


function initTrade3() {
    initIncludeTrade3(0);
    initExcludeTrade3(0);
}
function initIncludeTrade(index) {
    if ($("#includeTrade").find(".includeTradeSelect").length > 0) {
        var categoryName = "includeTrade[" + index + "].category";
        var selleridName = "includeTrade[" + index + "].sellerid";
        $("#includeTrade").find(".includeTradeSelect").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
    }
}


function initIncludeTrade3(index) {
    if ($("#includeTrade3").find(".includeTradeSelect").length > 0) {
        var categoryName = "includeTrade[" + index + "].category";
        var selleridName = "includeTrade[" + index + "].sellerid";
        $("#includeTrade3").find(".includeTradeSelect3").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
    }
}

function initExcludeTrade(index) {
    if ($("#excludeTrade").find(".excludeTradeSelect").length > 0) {
        var categoryName = "excludeTrade[" + index + "].category";
        var selleridName = "excludeTrade[" + index + "].sellerid";
        $("#excludeTrade").find(".excludeTradeSelect").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
    }
}

function initIncludeTrade3(index) {
    if ($("#includeTrade3").find(".includeTradeSelect").length > 0) {
        var categoryName = "includeTrade[" + index + "].category";
        var selleridName = "includeTrade[" + index + "].sellerid";
        $("#includeTrade3").find(".includeTradeSelect").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
    }
}
function initExcludeTrade3(index) {
    if ($("#excludeTrade3").find(".excludeTradeSelect").length > 0) {
        var categoryName = "excludeTrade[" + index + "].category";
        var selleridName = "excludeTrade[" + index + "].sellerid";
        $("#excludeTrade3").find(".excludeTradeSelect").tradeLd({
            showConfig: [{
                name: categoryName,
                tipTitle: "请选择",
                width: "50%"
            }, {
                name: selleridName,
                tipTitle: "请选择",
                width: "50%"
            }]
        });
    }
}


/**
 * 验证及提交消费优惠劵表单
 */
function validateAndSubmit() {
    addClassRules();
    validate("editCouponForm", {
        rules: {
            cname: {
                required: true
            },
            denomination: {
                required: true,
                digits: true,
                min: 0,
                max: 99999
            },
            condition: {
                required: function () {
                    // console.log($("input[name='conditionRadio']:checked").val());
                    if ($("input[name='conditionRadio']:checked").val() == 1) {
                        return true;
                    } else {
                        return false;
                    }
                },
                gt: 0,
                max: 99999
            },
            useNum: {
                required: true,
                digits: true
            },
            showAll: {
                required: true
            },
            dayNum: {
                required: function () {
                    if ($("input[name='swichtime']:checked").val() == 1) {
                        return true;
                    } else {
                        return false;
                    }
                },
                integer: true,
                gt: 0,
                max: 99999
            }

        },
    }, function () {
        var data = jsonFromt($('#editCouponForm').serializeArray());
        if (checkDatas(data.includeSellerids, data.excludeSellerids)) {
            var url = "coupon/generate/add.jhtml";
            $.ajax({
                type: 'post',
                url: url,
                data: data,
                dataType: 'json',
                beforeSend: function (XMLHttpRequest) {
                    $('#prompt').show();
                },
                success: function (data) {
                    showSmReslutWindow(data.success, data.msg);
                    // 添加成功后跳转到列表页面
                    var url = contextPath + '/coupon/generate/init.jhtml';
                    setTimeout(function () {
                        location.href = url;
                    }, 1000);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }
    });
}
/**
 * 验证及提交商城优惠劵添加表单
 */
function freshCouponSubmit() {
    addClassRules();
    validate("editCouponForm2", {
        rules: {
            cname: {
                required: true
            },
            denomination: {
                required: true,
                digits: true,
                min: 0,
                max: 99999
            },
            condition2: {
                required: function () {
                    // console.log($("input[name='conditionRadio']:checked").val());
                    if ($("input[name='conditionRadio']:checked").val() == 1) {
                        return true;
                    } else {
                        return false;
                    }
                },
                gt: 0,
                max: 99999
            },
            useNum: {
                required: true,
                digits: true
            },
            showAll: {
                required: true
            },
            dayNum2: {
                required: function () {
                    if ($("input[name='swichtime']:checked").val() == 1) {
                        return true;
                    } else {
                        return false;
                    }
                },
                integer: true,
                gt: 0,
                max: 99999
            }

        },
    }, function () {
        var data = jsonFromt($('#editCouponForm2').serializeArray());
        if (checkDatas2(data.includeSellerids, data.excludeSellerids)) {
            var url = "coupon/generate/freshcoupon/add.jhtml";
            $.ajax({
                type: 'post',
                url: url,
                data: data,
                dataType: 'json',
                beforeSend: function (XMLHttpRequest) {
                    $('#prompt').show();
                },
                success: function (data) {
                    showSmReslutWindow(data.success, data.msg);
                    // 添加成功后跳转到列表页面
                    var url = contextPath + '/coupon/generate/init.jhtml';
                    setTimeout(function () {
                        location.href = url;
                    }, 1000);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }else {
            console.log('数据检查 未通过')
        }
    });
}



/**
 * 平台通用劵 提交请求
 */
function platformCommonSubmit() {
    console.log("平台通用劵 提交!");
    addClassRules();
    validate("editCouponForm3", {

        rules: {
            cname: {
                required: true
            },
            denomination: {
                required: true,
                digits: true,
                min: 0,
                max: 99999
            },
            condition: {
                required: function () {
                    // console.log($("input[name='conditionRadio']:checked").val());
                    if ($("input[name='conditionRadio']:checked").val() == 1) {
                        return true;
                    } else {
                        return false;
                    }
                },
                gt: 0,
                max: 99999
            },
            useNum: {
                required: true,
                digits: true
            },
            showAll: {
                required: true
            },
            dayNum: {
                required: function () {
                    if ($("input[name='swichtime']:checked").val() == 1) {
                        return true;
                    } else {
                        return false;
                    }
                },
                integer: true,
                gt: 0,
                max: 99999
            }

        },
    }, function () {
        var data = jsonFromt($('#editCouponForm3').serializeArray());
        // if (checkDatas3(data.includeSellerids, data.excludeSellerids)) {
        if(true){
            var url = "coupon/generate/add.jhtml";
            $.ajax({
                type: 'post',
                url: url,
                data: data,
                dataType: 'json',
                beforeSend: function (XMLHttpRequest) {
                    $('#prompt').show();
                },
                success: function (data) {
                    showSmReslutWindow(data.success, data.msg);
                    // 添加成功后跳转到列表页面
                    var url = contextPath + '/coupon/generate/init.jhtml';
                    setTimeout(function () {
                        location.href = url;
                    }, 1000);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }else {
            console.log("数据检查未通过");
        }
    });
}


function addClassRules() {
    jQuery.validator.addClassRules({
        "customConditionInput": {
            required: true,
            number: true,
            min: 0
        }
    });
}
/**
 * 校验表单消费优惠劵数据
 * @param includeSellerIds
 * @param excludeSellerIds
 * @returns {Boolean}
 */
function checkDatas(includeSellerIds, excludeSellerIds) {
    return checkValidaty()
        && checkCities()
        && checkTrade()
        && checkSellers()
        && checkIncludeSellerIdAndExcludeSellerIdHasSame(includeSellerIds,
            excludeSellerIds);
}

/**
 * 检查平台通用劵数据
 * @param includeSellerIds
 * @param excludeSellerIds
 * @returns {Boolean|*}
 */
function checkDatas3(includeSellerIds, excludeSellerIds) {
    return checkValidaty3()
        && checkCities3()
        && checkTrade3()
        && checkSellers3()
        && checkIncludeSellerIdAndExcludeSellerIdHasSame(includeSellerIds,
            excludeSellerIds);
}


/**
 * 校验表单生鲜优惠券数据
 * @param includeSellerIds
 * @param excludeSellerIds
 * @returns {Boolean}
 */
function checkDatas2(includeSellerIds, excludeSellerIds) {
    return checkValidaty()
        && checkCities2()
        && checkTrade()
        && checkSellers()
        && checkIncludeSellerIdAndExcludeSellerIdHasSame(includeSellerIds,
            excludeSellerIds);
}
/**
 * 校验有效日期是否填写
 *
 * @returns {Boolean}
 */
function checkValidaty() {
    if ($("input[name='swichtime']:checked").val() == 1) {
        return true;
    }
    var reuslt = true;
    var selectAray = [".date-start", ".date-end", ".time-start", ".time-end"];
    $.each(selectAray, function (index, name) {
        $("#dates").find("input" + name).each(function () {
            var val = $(this).val();
            if (val == "") {
                submitDataError($(this), "请选择");
                reuslt = false;
            }
        });

    });
    return reuslt;
}

/**
 * 校验有效日期是否填写
 *
 * @returns {Boolean}
 */
function checkValidaty3() {
    if ($("#div3 input[name='swichtime']:checked").val() == 1) {
        return true;
    }
    var reuslt = true;
    var selectAray = [".date-start", ".date-end", ".time-start", ".time-end"];
    $.each(selectAray, function (index, name) {
        $("#dates3").find("input" + name).each(function () {
            var val = $(this).val();
            if (val == "") {
                submitDataError($(this), "请选择");
                reuslt = false;
            }
        });

    });
    return reuslt;
}


/**
 * 校验区域是否填写
 *
 * @returns {Boolean}
 */
function checkCities() {
    var reuslt = true;
    if ($('input[name="showAll"]:checked').val() == 1) {
        return reuslt;
    }
    var selectAray = ["provinces", "city"];
    $.each(selectAray, function (index, name) {
        $("#cities").find('select[name$="' + name + '"]').each(
            function () {
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#cities", name,
                        '#editCouponForm', true);
                    reuslt = false;
                }
            });

    });
    return reuslt;
}


/**
 * 校验区域是否填写
 *
 * @returns {Boolean}
 */
function checkCities3() {
    var reuslt = true;
    if ($('#div3 input[name="showAll"]:checked').val() == 1) {
        return reuslt;
    }
    var selectAray = ["provinces", "city"];
    $.each(selectAray, function (index, name) {
        $("#cities3").find('select[name$="' + name + '"]').each(
            function () {
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#cities", name,
                        '#editCouponForm', true);
                    reuslt = false;
                }
            });

    });
    return reuslt;
}

/**
 * 校验区域是否填写(生鲜优惠券)
 *
 * @returns {Boolean}
 */
function checkCities2() {
    var reuslt = true;
    var selectAray = ["provinces", "city"];
    $.each(selectAray, function (index, name) {
        $("#cities2").find('select[name$="' + name + '"]').each(
            function () {
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#cities2", name,
                        '#editCouponForm2', true);
                    reuslt = false;
                }
            });

    });
    return reuslt;
}

function checkTrade() {
    var reuslt = true;
    if ($('input[name="isAllSeller"]:checked').val() == 1) {
        return reuslt;
    }
    if ($('input[name="showAll"]:checked').val() == 1) {
        return true;
    }
    var selectAray = ["category", "sellerid"];
    $.each(selectAray, function (index, name) {
        $("#includeTrade").find('select[name$="' + name + '"]').each(
            function () {
                // console.log($(this));
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#includeTrade", name,
                        '#editCouponForm', false);
                    reuslt = false;
                }
            });
        $("#excludeTrade").find('select[name$="' + name + '"]').each(
            function () {
                // console.log($(this));
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#excludeTrade", name,
                        '#editCouponForm', false);
                    reuslt = false;
                }
            });

    });
    return reuslt;
}
function checkTrade3() {
    var reuslt = true;
    if ($('#div3 input[name="isAllSeller"]:checked').val() == 1) {
        return reuslt;
    }
    if ($('#div3 input[name="showAll"]:checked').val() == 1) {
        return true;
    }
    var selectAray = ["category", "sellerid"];
    $.each(selectAray, function (index, name) {
        $("#includeTrade").find('select[name$="' + name + '"]').each(
            function () {
                // console.log($(this));
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#includeTrade", name,
                        '#editCouponForm', false);
                    reuslt = false;
                }
            });
        $("#excludeTrade").find('select[name$="' + name + '"]').each(
            function () {
                // console.log($(this));
                var val = $(this).val();
                if (val == "") {
                    setErrorMark($(this), "#excludeTrade", name,
                        '#editCouponForm', false);
                    reuslt = false;
                }
            });

    });
    return reuslt;
}
function checkSellers() {
    if ($('input[name="showAll"]:checked').val() == 1) {
        return true;
    }
    if ($("input[name='isAllSeller']:checked").val() == 1) {
        return true;
    }
    return checkIncludeSellers() && checkExcludeSellers();
}


function checkSellers3() {
    if ($('#div3 input[name="showAll"]:checked').val() == 1) {
        return true;
    }
    if ($("#div3 input[name='isAllSeller']:checked").val() == 1) {
        return true;
    }
    return checkIncludeSellers() && checkExcludeSellers();
}

function checkIncludeSellers() {
    var result = checkData($("textarea[name='includeSellerids']").val(),
        includeSellerids.next(), "不能为空");
    // console.log(result);
    return result;
}

function checkExcludeSellers() {
    var result = checkData($("textarea[name='excludeSellerids']").val(),
        excludeSellerids.next(), "不能为空");
    // console.log(result);
    return result;
}

function checkIncludeSellerIdAndExcludeSellerIdHasSame(includeSellerIds,
                                                       excludeSellerIds) {
    var result = true;
    if (includeSellerIds != undefined && includeSellerIds != undefined) {
        var includeSellerIdArray = includeSellerIds.substr(0,
            includeSellerIds.length - 1).split(",");
        var excludeSellerIdArray = excludeSellerIds.substr(0,
            excludeSellerIds.length - 1).split(",");
        for (var i in includeSellerIdArray) {
            if ($.inArray(includeSellerIdArray[i], excludeSellerIdArray) > -1) {
                result = false;
                $("#msg").html("包含所选商家和排除所选商家存在相同的商家，请修改，以确保不存在相同的商家。").show();
                break;
            } else {
                continue;
            }
        }
    }
    return result;
}
function initPic() {
    $("#pic").uploadImg({
        urlId: "picURL",
        showImg: $('#picURL').val()
    });
    $("#breviary").uploadImg({
        urlId: "breviaryURL",
        showImg: $('#breviaryURL').val()
    });
    //商城优惠劵
    $("#pic2").uploadImg({
        urlId: "picURL2",
        showImg: $('#picURL2').val()
    });
    $("#breviary2").uploadImg({
        urlId: "breviaryURL2",
        showImg: $('#breviaryURL2').val()
    });

    //平台通用优惠劵
    $("#pic3").uploadImg({
        urlId: "picURL3",
        showImg: $('#picURL3').val()
    });
    $("#breviary3").uploadImg({
        urlId: "breviaryURL3",
        showImg: $('#breviaryURL3').val()
    });
}
// -------------------------------------------------------------

$(function () {
    cacheDivs();
    initAreaLdAndChosen();
    initRadios();
    //表单验证及提交
    validateAndSubmit();
    freshCouponSubmit();
    platformCommonSubmit();
    //上传图片初始化
    initPic();
    //商城优惠劵适用区域
    areaLd($("#cities2").find(".ld"), 0);
});



/** 时间 条件类型切换 */
function switchtimeOnchange(input){
    // input 的 value值
    var swichtime = $(input).val();
    if(swichtime == 1){
        // 让dayNum 可写
        $("input[name='dayNum']").val("").attr("readOnly", false)

        $("#dates3").hide();
        $("#dates3").find(".col-md-8").empty();
    }else if(swichtime == 2){
        // 让dayNum 输入框不可写
        $("input[name='dayNum']").val("").attr("readOnly", true);

        // console.log("有效日期选择具体时间");
        dayNumInputReadOnly();
        $("#dates3").show();
        $("#dates3").find(".col-md-8").html(replaceDateTempIndex2(0));
        datesIndex = datesSize();
        initDate();

    }
}

/** 使用条件 类型切换 */
function conditionRadioOnchange(input){
    var condition = $(input).val();
    if(condition == 1){
    console.log(condition);
        $("input[name='condition']").attr("readOnly",false);
    }
    else if(condition == 0) {
        console.log(condition);
        $("input[name='condition']").val("0").attr("readOnly",true);
    }
}

/** 全平台通用 切换*/
function showAllOnchange(input){
    var showAll = $(input).val();
    // 是否平台通用 : 是
    if(showAll == 1){
        $("#cities3").empty().hide()
        $("#allSeller3").hide();
    }
    // 是否平台通用 : 否
    else if(showAll == 0){
        $("#allSeller3").show();
        $("#cities3").html(cities2).show()
        initAreaLd3()
    }
}

/** 是否全部商家 切换*/
function isAllSellerOnchange(input){
    var isAllSeller = $(input).val()
    // 是否全部商家 是
    if(isAllSeller == 1){
        // $("#allSeller3").show();
        // $("#cities3").html(cities2).show()
        // initAreaLd3()

        // console.log("是否全部商家，选中：是");
        $("#trades3").empty().hide();
        $("#includeTrade3").empty().hide();
        $("#excludeTrade3").empty().hide();
        $("#includeSellers3").empty().hide();
        $("#excludeSellers3").empty().hide();
        $("#cities3").html(cities3).show();
        initAreaLd3();
        areaIndex = citiesSize3();



    }
    // 是否全部商家 否
    else if(isAllSeller == 0){
        console.log(" 是否全部商家，选中：否");
        $("#cities3").empty().hide();
        $("#trades3").html(trades3).show();

        if ($("input[name='trade']").val() == 1) {
            $("#includeTrade3").html(includeTrade).show();
        } else {
            $("#excludeTrade3").html(excludeTrade).show();
        }
        $("#includeSellers3").html(includeSellers).show();
        $("#excludeSellers3").html(excludeSellers).show();
        initTrade3();
        initSellerChosen();
        includeTradeIndex = includeTradeSize3();
        excludeTradeIndex = excludeTradeSize3();

    }
}




function onchangeOverlay(element){
    // 选中的值
    var overlay = $(element).val()
    // 该优惠劵的表单
    var $form = $(element).parents("form");
    var $useNum = $form.find("input[name='useNum']")
    var $showAllYes = $form.find("input[name='showAll'][value='1']")
    var $showAllNo = $form.find("input[name='showAll'][value='0']")

    if (overlay == 1) {
        $useNum.val(999);
        // $showAllYes.removeAttr("disabled");

    } else if (overlay == 0) {
        $useNum.val(1);
        // $showAllNo.click();
        // $showAllYes.attr("disabled", "disabled");
    }

}

// 初始化
// $("input[name='overlay'][value='0']").click();

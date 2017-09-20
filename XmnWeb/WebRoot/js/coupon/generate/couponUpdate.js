var searchChosen = undefined, searchChosen2 = undefined;
var searchChooseURL = "marketingManagement/activityManagement/init/choseSeller/init.jhtml";
var allSeller, allSellerTemp, cities, trades, includeSellers, excludeSellers, includeTrade, excludeTrade;
var includeSellerids = undefined, excludeSellerids = undefined;
var datesIndex;
var includeTradeIndex;
var excludeTradeIndex;
var areaIndex;
function cacheDivs() {
	allSeller = $("#allSeller").html()
	allSellerTemp = $("#allSellerTemp").html();
	cities = $("#cities").html();
	includeSellers = $("#includeSellers").html();
	excludeSellers = $("#excludeSellers").html();
	includeTrade = $("#includeTrade").html();
	excludeTrade = $("#excludeTrade").html();
	trades = $("#trades").html();
}

function resetAllSeller() {
	$("#allSeller").find("input[name='isAllSeller'][value='1']").attr(
			"checked", true);
	$("#allSeller").find("input[name='isAllSeller'][value='0']").attr(
			"checked", false);
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
 * 将.dateTemp中的index替换成具体的数值
 */
function replaceDateTempIndex(index) {
	return $(".dateTemp").html().replace(/index/g, index);
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
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		minView : "month",
	}, time = {
		format : "hh:ii",
		autoclose : true,
		// todayBtn : true,
		minView : "hour",
		maxView : "hour",
		startView : "day",
		minuteStep : 1,
		initialDate : new Date("1899-12-31")
	};
	$(".date-start").each(
			function(index, element) {
				// 初始化结束时间
				$(element).siblings(".date-end").datetimepicker($.extend({
					startDate : new Date()
				}, date)).on(
						"changeDate",
						function() {
							$(element).datetimepicker("setEndDate",
									$(element).siblings(".date-end").val());
						});

				// 初始化开始时间
				$(element).datetimepicker($.extend({
					startDate : new Date()
				}, date)).on(
						"changeDate",
						function() {
							$(element).siblings(".date-end").datetimepicker(
									"setStartDate", $(element).val());
						});

			});
	$(".time-start").each(
			function(index, element) {
				$(element).siblings(".time-end").datetimepicker(
						$.extend({}, time)).on(
						"changeDate",
						function() {
							$(element).datetimepicker("setEndDate",
									$(element).siblings(".time-end").val());
						});

				$(element).datetimepicker($.extend({}, time)).on(
						"changeDate",
						function() {
							$(element).siblings(".time-end").datetimepicker(
									"setStartDate", $(element).val());
						});
			});
}
/**
 * 领取有效天数变为不可编辑
 */
function dayNumInputReadOnly() {
	$("input[name='dayNum']").val("").attr("readOnly", true);
}

/**
 * 领取有效天数变为可编辑
 */
function dayNumInputRead() {
	$("input[name='dayNum']").attr("readOnly", false);
}

/**
 * 使用条件,选中：有条件使用
 */
function conditionYes() {
	// console.log("使用条件,选中：有条件使用");
	conditionInputRead();
}

/**
 * 使用条件,选中：无条件使用
 */
function conditionNo() {
	// console.log("使用条件,选中：无条件使用");
	conditionInputReadOnly();
}

/**
 * 使用条件变为不可编辑
 */
function conditionInputReadOnly() {
	$("input[name='condition']").val(0).attr("value", 0).attr("readOnly", true);
}

/**
 * 使用条件变为可编辑
 */
function conditionInputRead() {
	$("input[name='condition']").attr("readOnly", false);
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
	// console.log("是否平台通用,选中：否");
	$("#allSeller").html(allSellerTemp).show();
	if ($("input[name='isAllSeller']").val() == 1) {
		isAllSellerYes();
	} else {
		isAllSellerNo();
	}

}

/**
 * 是否平台通用，选中：否
 */
function showAllNoInit() {
	// console.log("是否平台通用,选中：否");
	$("#allSeller").html(allSeller).show();
	if ($("input[name='isAllSeller']").val() == 1) {
		isAllSellerYes();
	} else {
		isAllSellerNo();
	}

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
	if ($("input[name='trade']:checked").val() == 1) {
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
 * 行业选择，选中：排除的行业
 * 
 */
function tradeExclude() {
	// console.log(" 行业选择，选中：排除的行业");
	$("#excludeTrade").html(excludeTrade).show();
	$("#includeTrade").empty().hide();
	initTrade();
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
				.find(".ld").each(function() {
					// console.log(this);
					$(this).areaLd({
						isChosen : true,
						separator : ",",
						showConfig : [ {
							name : "couponCities[" + areaIndex + "].provinces",
							tipTitle : "--省--"
						}, {
							name : "couponCities[" + areaIndex + "].city",
							tipTitle : "--市--"
						} ]
					});
				});
		areaIndex++;
	}
}

/**
 * 删除区域选择组件
 */
function removeAreaLd(object) {
	// console.log("删除区域选择组件");
	if (canRemoveAreaLd()) {
		$(object).parents(".input-group").remove();
	}
}
function citiesSize() {
	return $("#cities > .col-md-5").find(".input-group").length;
}

function canAddAreaLd() {
	return citiesSize() < 10 ? true : false;
}

function canRemoveAreaLd() {
	return citiesSize() > 1 ? true : false;
}

/**
 * 添加包含所选行业
 * 
 * @param object
 */
function addIncludeTrade(object) {
	if (canAddIncludeTrade()) {
		// console.log("添加包含所选行业");
		var categoryName = "includeTrade[" + includeTradeIndex + "].category";
		var selleridName = "includeTrade[" + includeTradeIndex + "].sellerid";
		$(object).parents(".input-group").last().after(
				$(".includeTradeSelectTemp").html()).next().find(
				".includeTradeSelect").tradeLd({
			showConfig : [ {
				name : categoryName,
				tipTitle : "请选择",
				width : "50%"
			}, {
				name : selleridName,
				tipTitle : "请选择",
				width : "50%"
			} ]
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
	if (canRemoveIncludeTrade()) {
		// console.log("删除包含所选行业");
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

function canAddIncludeTrade() {
	return includeTradeSize() < 10;
}

function canRemoveIncludeTrade() {
	return includeTradeSize() > 1;
}
/**
 * 取得已经添加的不包含所选行业的行业数量
 * 
 * @returns
 */
function excludeTradeSize() {
	return $("#excludeTrade > .col-md-5").find(".input-group").length;
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
			showConfig : [ {
				name : categoryName,
				tipTitle : "请选择",
				width : "50%"
			}, {
				name : selleridName,
				tipTitle : "请选择",
				width : "50%"
			} ]
		});

		excludeTradeIndex++;
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
	$(object).each(function() {
		$(this).areaLd({
			isChosen : true,
			separator : ",",
			showConfig : [ {
				name : "couponCities[" + index + "].provinces",
				tipTitle : "--省--"
			}, {
				name : "couponCities[" + index++ + "].city",
				tipTitle : "--市--"
			} ]
		});
	});
}

function initSellerChosen() {
	var cid = $("input[name='cid']").val();
	includeSellerids = $("textarea[name='includeSellerids']")
			.searchChosen(
					{
						url : searchChooseURL + "?type=1",
						initUrl : contextPath
								+ "/coupon/generate/update/getIncludeSellers.jhtml?cid="
								+ cid,
						initId : "sellerid",
						initTitle : "sellername"
					});

	excludeSellerids = $("textarea[name='excludeSellerids']")
			.searchChosen2(
					{
						url : searchChooseURL + "?type=2",
						initUrl : contextPath
								+ "/coupon/generate/update/getExcludeSellers.jhtml?cid="
								+ cid,
						initId : "sellerid",
						initTitle : "sellername"
					});
}

function initAreaLd() {
	if ($("#cities").find(".ld").length > 0) {
		areaLd($("#cities").find(".ld"), 0);
	}
}

function initAreaLdAndChosen() {
	if ($("input[name='isAllSeller']:checked").val() == 1) {
		initAreaLd();
	}
	initSellerChosen();

}

function initRadios() {
	// console.log($("input[name='swichtime']:checked").val());
	if ($("input[name='swichtime']:checked").val() == 2) {
		dayNumInputReadOnly();
	}
	if ($("input[name='conditionRadio']:checked").val() == 0) {
		conditionInputReadOnly();
	}
	if ($("input[name='showAll']:checked").val() == 1) {
		showAllYes();
	} else {
		showAllNoInit();
		if ($("input[name='isAllSeller']:checked").val() == 1) {
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
function initIncludeTrade(index) {
	if ($("#includeTrade").find(".includeTradeSelect").length > 0) {
		var i = index;
		$("#includeTrade").find(".includeTradeSelect").each(function() {
			var categoryName = "includeTrade[" + i + "].category";
			var selleridName = "includeTrade[" + i++ + "].sellerid";
			$(this).tradeLd({
				showConfig : [ {
					name : categoryName,
					tipTitle : "请选择",
					width : "50%"
				}, {
					name : selleridName,
					tipTitle : "请选择",
					width : "50%"
				} ]
			});
		});

	}
}
function initExcludeTrade(index) {
	if ($("#excludeTrade").find(".excludeTradeSelect").length > 0) {
		var i = index;
		$("#excludeTrade").find(".excludeTradeSelect").each(function() {
			var categoryName = "excludeTrade[" + i + "].category";
			var selleridName = "excludeTrade[" + i++ + "].sellerid";
			$(this).tradeLd({
				showConfig : [ {
					name : categoryName,
					tipTitle : "请选择",
					width : "50%"
				}, {
					name : selleridName,
					tipTitle : "请选择",
					width : "50%"
				} ]
			});
		});
	}
}

function validateAndSubmit() {
	addClassRules();
	validate("editCouponForm", {
		rules : {
			cname : {
				required : true
			},
			denomination : {
				required : true,
				number : true,
				min : 0,
				max : 99999
			},
			condition : {
				required : function() {
					// console.log($("input[name='conditionRadio']:checked").val());
					if ($("input[name='conditionRadio']:checked").val() == 1) {
						return true;
					} else {
						return false;
					}
				},
				gt : 0,
				max : 99999
			},
			useNum : {
				required : true,
				number : true
			},
			showAll : {
				required : true
			},
			dayNum : {
				required : function() {
					if ($("input[name='swichtime']:checked").val() == 1) {
						return true;
					} else {
						return false;
					}
				},
				integer : true,
				gt : 0,
				max : 99999
			}

		},
	}, function() {
		var data = jsonFromt($('#editCouponForm').serializeArray());
		if (checkDatas(data.includeSellerids, data.excludeSellerids)) {
			var url = "coupon/generate/update.jhtml";
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					showSmReslutWindow(data.success, data.msg);
					// 添加成功后跳转到列表页面
					var url = contextPath + '/coupon/generate/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
	});
}
function addClassRules() {
	jQuery.validator.addClassRules({
		"customConditionInput" : {
			required : true,
			number : true,
			min : 0
		}
	});
}

function checkDatas(includeSellerIds, excludeSellerIds) {
	return checkValidaty()
			&& checkCities()
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
	var selectAray = [ ".date-start", ".date-end", ".time-start", ".time-end" ];
	$.each(selectAray, function(index, name) {
		$("#dates").find("input" + name).each(function() {
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
	var selectAray = [ "provinces", "city" ];
	$.each(selectAray, function(index, name) {
		$("#cities").find('select[name$="' + name + '"]').each(
				function() {
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

function checkTrade() {
	var reuslt = true;
	if ($('input[name="showAll"]:checked').val() == 1) {
		return true;
	}
	if ($('input[name="isAllSeller"]:checked').val() == 1) {
		return reuslt;
	}
	var selectAray = [ "category", "sellerid" ];
	$.each(selectAray, function(index, name) {
		$("#includeTrade").find('select[name$="' + name + '"]').each(
				function() {
					// console.log($(this));
					var val = $(this).val();
					if (val == "") {
						setErrorMark($(this), "#includeTrade", name,
								'#editCouponForm', false);
						reuslt = false;
					}
				});
		$("#excludeTrade").find('select[name$="' + name + '"]').each(
				function() {
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

function initPic() {
	$("#pic").uploadImg({
		urlId : "picURL",
		showImg : $('#picURL').val()
	});
	$("#breviary").uploadImg({
		urlId : "breviaryURL",
		showImg : $('#breviaryURL').val()
	});
}

function checkIncludeSellerIdAndExcludeSellerIdHasSame(includeSellerIds,
		excludeSellerIds) {
	var result = true;
	if (includeSellerIds != undefined && includeSellerIds != undefined) {
		var includeSellerIdArray = includeSellerIds.substr(0,
				includeSellerIds.length - 1).split(",");
		var excludeSellerIdArray = excludeSellerIds.substr(0,
				excludeSellerIds.length - 1).split(",");
		for ( var i in includeSellerIdArray) {
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
// -------------------------------------------------------------

$(function() {
	datesIndex = datesSize();
	cacheDivs();
	initAreaLdAndChosen();
	initRadios();
	initPic();
	validateAndSubmit();

    switchOverlay($("input[name='overlay']:checked"));

});



function switchOverlay(input){
    var overlay = $(input).val();
    var $useNum = $("#useNum");

    if (overlay == 1) {
        $useNum.val(999)
        // $("#allArea").removeAttr("disabled");
    } else if (overlay == 0) {
        $useNum.val(1)
        // $("#specifyArea").click();
        // $("#allArea").attr("disabled", "disabled");

    }

}
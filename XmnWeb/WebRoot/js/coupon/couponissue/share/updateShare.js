/**
 * 添加分享发放优惠券
 */
var config = {};
$(function() {
	inserTitle(
			' >优惠券管理 > <span><a href="coupon/couponissue/init.jhtml.jhtml" target="right">优惠券发放管理</a> >修改分享发放',
			'couponList', true);
	initConfig();
	initView();

	$("#orderCoupons").on(
			"click",
			".icon-plus",
			function() {

				if (config.orderCouponCounter < config.orderCouponSize) {
					config.orderCouponCounter++;
					var fitIndex = getMostFitIndex(getIndexArray([
							"orderCoupons", "shareCoupons" ], ".input-group",
							"data-num"));
					var content = config.couponIssue
							.replace(/index/g, fitIndex);
					content = content.replace(/typeValue/g, 1);
					appendContent("orderCoupons", content);
				}
			});
	$("#orderCoupons").on("click", ".icon-minus", function() {
		if (getSize("orderCoupons", "input-group") > 1) {
			removeParent(this);
			config.orderCouponCounter--;
		}
	});

	$("#shareCoupons").on(
			"click",
			".icon-plus",
			function() {
				if (config.shareCouponCounter < config.shareCouponSize) {
					config.shareCouponCounter++;
					console.log("add shareCoupon");
					var fitIndex = getMostFitIndex(getIndexArray([
							"orderCoupons", "shareCoupons" ], ".input-group",
							"data-num"));
					var content = config.couponIssue
							.replace(/index/g, fitIndex);
					content = content.replace(/typeValue/g, 2);
					appendContent("shareCoupons", content);
				}
			});
	$("#shareCoupons").on("click", ".icon-minus", function() {
		if (getSize("shareCoupons", "input-group") > 1) {
			removeParent(this);
			config.shareCouponCounter--;
		}
	});
	classRules();
	validate("updateShareForm", {
		rules : {
			amount : {
				required : true,
				number : true,
				gt : 0
			},
			maxTimes : {
				required : true,
				integer : true,
				gt : 0
			},
			maximum : {
				required : true,
				integer : true,
				gt : 0
			},
			dateStart : {
				required : true
			},
			dateEnd : {
				required : true,
			}
		}
	/*
	 * , messages : { amount : { required : "消费金额必填" }, maxTimes : { required :
	 * "次数限制", }, dateStart : { required : "活动开始时间必填" }, dateEnd : { required :
	 * "活动结束时间必填", } }
	 */
	}, formAjax)

});

function formAjax() {
	var url = "coupon/couponissue/share/update.jhtml"
	var data = jsonFromt($('#updateShareForm').serializeArray());
	// 更新area
	if (data) {
		var areaInfo = $("#areaInfo");
		if (areaInfo.length > 0) {
			var areas = $("#areaInfo").find("select[name='area']").val() != null ? $(
					"#areaInfo").find("select[name='area']").val().toString()
					: null;
			data.area = areas;
		}
	}
	if (customValidate()) {
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
				var url = contextPath + '/coupon/couponissue/init.jhtml';
				setTimeout(function() {
					location.href = url;
				}, 1000);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
}

function classRules() {
	jQuery.validator.addClassRules("share-cid", {
		valueNotEquals : ""
	});
	jQuery.validator.addClassRules("share-issueVolume", {
		issueVolumeRequired : true,
		integer : true,
		gt : 0
	});
	jQuery.validator.addClassRules("share-hitRatio", {
		hitRatioRequired : true,
		gt : 0,
		range : [ 0, 100 ],
		is100 : true
	});
	$.validator.addMethod("valueNotEquals", function(value, element, arg) {
		return arg != value;
	}, $.validator.messages.required);
	$.validator.addMethod("cidRequired", $.validator.methods.required,
			$.validator.messages.required);
	$.validator.addMethod("issueVolumeRequired", $.validator.methods.required,
			$.validator.messages.required);
	$.validator.addMethod("hitRatioRequired", $.validator.methods.required,
			$.validator.messages.required);
	$.validator.addMethod("is100", function(value, element, arg) {
		var $orderCoupons = $(element).first().parents("#orderCoupons");
		var $shareCoupons = $(element).first().parents("#shareCoupons");
		var $coupons;
		if ($orderCoupons.length > 0) {
			$coupons = $orderCoupons;
		}
		if ($shareCoupons.length > 0) {
			$coupons = $shareCoupons
		}
		var sum = 0;
		$($coupons).find(".share-hitRatio").each(function() {
			sum = sum + parseInt($(this).val());
		});
		if (sum == 100) {
			return true
		} else {
			return false;
		}
	}, "请确保输入值使优惠券概率之和为100%");
}

/**
 * 自定义验证条件
 */
function customValidate() {
	var result;
	// 验证区域是否填写
	var IsAreaSuccess = function() {
		var selectAray = [ "province", "city", "area" ];
		return checkSelect('#updateShareForm', "#areaInfo", selectAray, true);
	}();
	//
	result = IsAreaSuccess != null ? IsAreaSuccess : true;
	return result;
}

/**
 * 初始化视图
 */
function initView() {
	// 初始化区域
	$("#areaInfo").areaLd({
		isChosen : true,
		isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		separator : ",",
		isDisabled : isDisabled,
		// isReadOnly : true,
		showConfig : [ {
			name : "province",
			tipTitle : "--省--",
			width : "25%"
		}, {
			name : "city",
			tipTitle : "--市--",
			width : "25%"
		}, {
			name : "area",
			tipTitle : "",
			width : "47%"
		} ]
	});

	// 优惠券下拉选择
	$(".coupon").each(function() {
		$(this).chosenObject({
			id : "cid",
			hideValue : "value",
			showValue : "cname",
			url : "coupon/couponissue/queryYouHuiQuan.jhtml",
		// isChosen : true
		});
	});

	$('input[name="dateStart"]').datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function() {
				$("input[name='dateEnd']").datetimepicker("setStartDate",
						$("input[name='dateStart']").val());
			});
	$('input[name="dateEnd"]').datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function() {
				$("input[name='dateStart']").datetimepicker("setEndDate",
						$("input[name='dateEnd']").val());
			});

	// 修改的时候要处理下初始值
	$("input[name='dateStart']").datetimepicker("setEndDate",
			$("input[name='dateEnd']").val());
	$("input[name='dateEnd']").datetimepicker("setStartDate",
			$("input[name='dateStart']").val());
}

/**
 * 取得存在的下标数组
 */
function getIndexArray(pselectors, cselector, att) {
	var array = [];
	for ( var i in pselectors) {
		$("#" + pselectors[i]).find(cselector).each(function() {
			array.push(parseInt($(this).attr(att)));
		})
	}
	return array;
}

function replaceContent(content, reg, replaceC) {
	// $(content).attr(replaceC);
	content.replace(reg, replaceC);
}

/**
 * ***********************************通用方法--开始*************************************
 */

/**
 * 初始化
 * 
 * @returns
 */
function initConfig() {
	/*
	 * config.shareCoupon = '<div class="input-group" data-index="1">' +
	 * $("#shareCoupons").find(".input-group").html() + '<div>';
	 * config.orderCoupon = '<div class="input-group" data-index="0">' +
	 * $("#orderCoupons").find(".input-group").html() + '<div>';
	 */
	config.couponIssue = $("#couponIssueTemp").html();
	config.shareCouponCounter = 1;
	config.orderCouponCounter = 1;
	config.shareCouponSize = 5;
	config.orderCouponSize = 5;

}
/**
 * 为元素追加内容
 * 
 * @param id
 * @param content
 */
function appendContent(id, content) {
	$('#' + id).append(content);
}

/**
 * 移除父元素
 * 
 * @param id
 */
function removeParent(element) {
	$(element).parents(".input-group").remove();
}

/**
 * 取得id元素下的class为clazz的元素的个数
 * 
 * @param id
 * @param clazz
 */
function getSize(id, clazz) {
	return $("#" + id).find("." + clazz).length;
}

/**
 * 取得最合适的下标值
 */
function getMostFitIndex(valueArray) {
	var maxValue = getMaxValue(valueArray);
	for (var i = 0; i <= maxValue; i++) {
		// 不包含
		if (isContained(i, valueArray) == -1) {
			return i;
		} else {
			continue;
		}
	}
	var result;
	result = maxValue + 1
	return result;
}

/**
 * 取得数组中的最大值
 * 
 * @param intValueArray
 * @returns
 */
function getMaxValue(valueArray) {
	return Math.max.apply(null, valueArray);
}

/**
 * 数组中是否包含某值
 * 
 * @param value
 * @param valueArray
 * @returns -1 不包含在数组中，其他的值为包含
 */
function isContained(value, valueArray) {
	var result = $.inArray(value, valueArray);
	return result;
}

/**
 * ************************************通用方法--结束************************************
 */

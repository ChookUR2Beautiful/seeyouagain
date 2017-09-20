var cities;
var datesIndex;
var areaIndex;
/**
 * 读取元素的HTML结构
 */
function cacheDivs() {
	cities = $("#cities2").html();
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
/**
 * 商城优惠劵--领取有效天数变为可编辑
 */
function dayNumInputRead2() {
	$("#dayNum2").attr("readOnly", false);
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
 * 商城优惠劵--领取有效天数变为不可编辑
 */
function dayNumInputReadOnly2() {
	$("#dayNum2").val("").attr("readOnly", true);
}

function datesSize() {
	return $("#dates2 > .col-md-8").find(".input-group").length
}
/**
 * 商城优惠劵（将.dateTemp2中的index替换成具体的数值）
 */
function replaceDateTempIndex2(index) {
	return $(".dateTemp2").html().replace(/index/g, index);
}

/**
 * 商城优惠劵--使用条件,选中：有条件使用
 */
function conditionYes2() {
	// console.log("使用条件,选中：有条件使用");
	conditionInputRead2();
}
/**
 * 商城优惠劵--使用条件变为可编辑
 */
function conditionInputRead2() {
	$("#condition2").attr("readOnly", false);
}
/**
 * 商城优惠劵--使用条件,选中：无条件使用
 */
function conditionNo2() {
	// console.log("使用条件,选中：无条件使用");
	conditionInputReadOnly2();
}
/**
 * 商城优惠劵--使用条件变为不可编辑
 */
function conditionInputReadOnly2() {
	$("#condition2").val(0).attr("value", 0)
			.attr("readOnly", true);
}
/**
 * 初始化单选按钮
 */
function initRadios() {
	if ($("input[name='swichtime']:checked").val() == 2) {
		dayNumInputReadOnly2();
	}
	if ($("input[name='conditionRadio']:checked").val() == 0) {
		conditionInputReadOnly2();
	}
}
/**
 * 初始化时间
 */
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
 * 是否平台通用,选中：是
 */
function showAllYes1() {
//	 console.log("是否平台通用1,选中：是");
	$("#cities2").empty().hide();
}

/**
 * 是否平台通用，选中：否
 */
function showAllNo1() {
//	 console.log("是否平台通用1,选中：否");
	 $("#cities2").html(cities).show();
		initAreaLd();
//		areaIndex = citiesSize();
}


/**
 * 初始化适用区域
 */
function initAreaLd() {
	areaLd($("#cities2").find(".ld"), 0);
}
function areaLd(object, index) {

	if(document.getElementById("allArea").checked){
		return;
	}
	
	$(object).areaLd({
		isChosen : true,
		separator : ",",
		showConfig : [ {
			name : "couponCities[" + index + "].provinces",
			tipTitle : "--省--"
		}, {
			name : "couponCities[" + index + "].city",
			tipTitle : "--市--"
		} ]
	});
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
 * 初始化上传图片
 */
function initPic() {
	//商城优惠劵
	$("#pic2").uploadImg({
		urlId : "picURL2",
		showImg : $('#picURL2').val()
	});
	$("#breviary2").uploadImg({
		urlId : "breviaryURL2",
		showImg : $('#breviaryURL2').val()
	});
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
		$("#cities2").find('select[name$="' + name + '"]').each(
				function() {
					var val = $(this).val();
					if (val == "") {
						setErrorMark($(this), "#cities2", name,
								'#editCouponForm', true);
						reuslt = false;
					}
				});

	});
	return reuslt;
}
/**
 * 验证及提交商城优惠劵添加表单
 */
function freshCouponSubmit(){
	addClassRules();
	validate("editCouponForm2", {
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
				digits : true
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
		var data = jsonFromt($('#editCouponForm2').serializeArray());
			var url = "coupon/generate/freshcoupon/update.jhtml";
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
					// 更新成功后跳转到列表页面
					var url = contextPath + '/coupon/generate/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
	});
}
/**
 * 样式规则
 */
function addClassRules() {
	jQuery.validator.addClassRules({
		"customConditionInput" : {
			required : true,
			number : true,
			min : 0
		}
	});
}
//------------
/**
 * 页面加载完成时调用的函数
 */
$(function() {
	cacheDivs();
	//初始化单选按钮
	initRadios();
	//初始化适用区域
	initAreaLd();
	//表单验证及提交
	freshCouponSubmit();
	//上传图片初始化
	initPic();

    onchangeOverlay($("input[name='overlay']:checked"))
});


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
var searchChosen = undefined;
var dateCount = 0, cityCount = 0, dateSize = 10, citySize = 10;
var sellersDiv = '<label class="col-md-2 control-label">包含所选商家：</label><div class="col-md-8"><textarea style="display: block;" id="sellerids" rows="5" name="sellerids" class="col-md-8"></textarea></div>'
	+'<label class="col-md-2 control-label">排除所选商家：</label><div class="col-md-8"><textarea style="display: block;" id="excludesellerids" rows="5" name="excludesellerids" class="col-md-8"></textarea></div>', 
allSellers = '<label class="col-md-2 control-label">是否全部商家：</label><div class="col-md-3"><div class="input-group"><label class="radio-inline">是<input checked="true" type="radio" name="isAllSeller" value="1" /></label><label class="radio-inline">否<input type="radio"name="isAllSeller" value="0"/></label></div></div>' ;
var searchChooseURL = "marketingManagement/activityManagement/init/choseSeller/init.jhtml", url;
function addClassRules() {
	jQuery.validator.addClassRules({
		"customConditionInput" : {
			customConditionInputRequired : true,
			customConditionInputNumber : true,
			customConditionInputMin : 0
		}
	});
}
$(function() {

	$.validator.addMethod("dateRequired", $.validator.methods["required"],
			"日期必填");
	$.validator.addMethod("timeRequired", $.validator.methods["required"],
			"时间必填");
	$.validator.addMethod("customConditionInputRequired",
			$.validator.methods["required"], "自定义使用条件必填");
	$.validator.addMethod("customConditionInputNumber",
			$.validator.methods["number"], "必须是数字");
	$.validator.addMethod("customConditionInputMin",
			$.validator.methods["min"], "必须是大于0");
	addClassRules();

	$("#pic").uploadImg({
		urlId : "picURL",
		showImg : $('#picURL').val()
	});
	$("#breviary").uploadImg({
		urlId : "breviaryURL",
		showImg : $('#breviaryURL').val()
	});

	initDate();

	$("#dates")
			.on(
					"click",
					".icon-plus",
					function() {
						if ($(this).parents(".col-md-8").find(".input-group")
								.size() < dateSize) {
							dateCount++;
							$(this).parents(".input-group").after(
									$(".dateTemp").html().replace(/index/g,
											dateCount));
							initDate();
						}
					});
	$("#dates").on("click", ".icon-minus", function() {
		if ($(this).parents(".col-md-8").find(".input-group").size() > 1) {
			$(this).parents(".input-group").remove();
		} else {

		}
	});

	$("#cities")
			.on(
					"click",
					".icon-plus",
					function() {
						if ($(this).parents(".col-md-5").find(".input-group")
								.size() < citySize) {
							cityCount++;
							$(this)
									.parents(".input-group")
									.after($(".cityTemp").html())
									.next()
									.find(".ld")
									.each(
											function() {
												$(this)
														.areaLd(
																{
																	isChosen : true,
																	separator : ",",
																	showConfig : [
																			{
																				name : "couponCities["
																						+ cityCount
																						+ "].provinces",
																				tipTitle : "--省--"
																			},
																			{
																				name : "couponCities["
																						+ cityCount
																						+ "].city",
																				tipTitle : "--市--"
																			} ],
																	lastChange : function() {
																		if ($(
																				'input[name="isAllSeller"]:checked')
																				.val() == 0) {
																			rebuildChooseSeller();
																		}
																	}
																});
											});
						}
					});
	$("#cities").on("click", ".icon-minus", function() {
		if ($(this).parents(".col-md-5").find(".input-group").size() > 1) {
			$(this).parents(".input-group").remove();
			if ($('input[name="isAllSeller"]:checked').val() == 0) {
				rebuildChooseSeller();
			}
		}
	});

	validate("editCouponForm", {
		rules : {
			cname : {
				required : true
			},
			denomination : {
				required : true,
				digits : true,
				min : 0,
				max : 99999
			},
			condition : {
				required : true
			},
			useNum : {
				required : true,
				digits : true
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
		messages : {
			cname : {
				required : "优惠券名称必填"
			},
			denomination : {
				required : "优惠券面额必填",
				digits : "请输入正整数",
				min : "优惠券面额不能小于0",
				max : "优惠券面额不能大于99999"
			},
			condition : {
				required : "使用条件必填"
			},
			useNum : {
				required : "每次可同时使用数量必填",
				digits : "每次可同时使用数量必须是正整数"
			},
			showAll : {
				required : "显示区域必填"
			},
			dayNum : {
				required : "领取有效天数必填",
				digits : "请输入正整数",
				min : "领取有效天数不能小于0",
				max : "领取有效天数不能大于99999"
			}
		}
	}, formAjax)
	setCondition();
	$("#customConditionDiv > input").on("change", function() {
		$("#customConditionRadio").attr("value", $(this).val());
	});

	function conditionRadioCheck() {
		var conditionRadio = $("input[name='conditionRadio']:checked").val();
		if (conditionRadio == 0) {
			$("#condition").val(0).attr("readonly", "readonly");
		} else {
			$("#condition").removeAttr("readonly");
		}
	}
	conditionRadioCheck();
	$("input[name='conditionRadio']").on("change", function() {
		conditionRadioCheck();
	});

	function divshowOrHide() {
		var swichtime = $("input[name='swichtime']:checked").val();
		if (swichtime == 1) {
			$("#showday").val("");
			$("#showday").show();
			$("#dates").hide();
		} else {
			$("#dates").show();
			$("#showday").hide();
		}
	}
	divshowOrHide();
	$("input[name='swichtime']").on("change", function() {
		divshowOrHide();
	});
	if (type == "add") {
		inserTitle(
				' >优惠券管理 > <span><a href="coupon/generate/init.jhtml" target="right">优惠券生成</a> >添加优惠券',
				'couponList', true);

		// rebuildChooseSeller();
		$("#allSeller,#cities,#sellers").hide();

		// 是全国通用的则区域和商家的信息清空
		$("#allArea").on("change", function() {
			// 清空区域信息
			$("#cities").find(".col-md-5").empty();
			// 清空商家控件
			$("#sellers").empty();
			$("#allSeller").empty();
		});

		// 不是全国通用的则要显示出【是否全部商家】
		$("#specifyArea").on("change", function() {
			$("#allSeller,#cities,#sellers").show();
			$("#allSeller").html(allSellers);
			$("#cities").find(".col-md-5").html($(".cityTemp").html());
			$("#cities").find(".ld").areaLd({
				isChosen : true,
				separator : ",",
				showConfig : [ {
					name : "couponCities[" + 0 + "].provinces",
					tipTitle : "--省--"
				}, {
					name : "couponCities[" + 0 + "].city",
					tipTitle : "--市--"
				} ],
				lastChange : function() {
					if ($('input[name="isAllSeller"]:checked').val() == 0) {
						rebuildChooseSeller();
					}
				}
			});
			// 【是全部商家】
			$("#allSeller").find(':radio[value="1"]').on("change", function() {
				$("#sellers").empty();// 移除商家选择组件
				$("#cities").find(".col-md-5").html($(".cityTemp").html());// 添加区域组件的html
				$("#cities").find(".ld").areaLd({// 重新构建区域组件
					isChosen : true,
					separator : ",",
					showConfig : [ {
						name : "couponCities[" + 0 + "].provinces",
						tipTitle : "--省--"
					}, {
						name : "couponCities[" + 0 + "].city",
						tipTitle : "--市--"
					} ],
					lastChange : function() {
						if ($('input[name="isAllSeller"]:checked').val() == 0) {
							rebuildChooseSeller();
						}
					}
				});
			});
			// 【不是全部商家】
			$("#allSeller").find(':radio[value="0"]').on("change", function() {
				$("#cities").find(".col-md-5").empty();// 移除区域选择组件
				cityCount = 0;
				$("#sellers").html(sellersDiv);
				if ($('input[name="isAllSeller"]:checked').val() == 0) {
					rebuildChooseSeller();
				}
			});
		});

	} else {

		// 设置标的值
		cityCount = $("#cities").find(".input-group").size() > 0 ? $("#cities")
				.find(".input-group").size() - 1 : 0;
		dateCount = $("#dates").find(".input-group").size() > 0 ? $("#dates")
				.find(".input-group").size() - 1 : 0;

		inserTitle(
				' >优惠券管理 > <span><a href="coupon/generate/init.jhtml" target="right">优惠券生成</a> >修改优惠券',
				'couponList', true);

		// 是全国通用的则区域和商家的信息清空
		$("#allArea").on("change", function() {
			// $("#allSeller,#cities,#sellers").show();
			// 清空区域信息
			$("#cities").find(".col-md-5").empty();
			// 清空商家控件
			$("#sellers").empty();
			$("#allSeller").empty();
		});

		$("#specifyArea").on("change", function() {
			$("#allSeller,#cities,#sellers").show();
			$("#allSeller").html(allSellers);
			$("#cities").find(".col-md-5").html($(".cityTemp").html());
			$("#cities").find(".ld").areaLd({
				isChosen : true,
				separator : ",",
				showConfig : [ {
					name : "couponCities[" + 0 + "].provinces",
					tipTitle : "--省--"
				}, {
					name : "couponCities[" + 0 + "].city",
					tipTitle : "--市--"
				} ],
				lastChange : function() {
					if ($('input[name="isAllSeller"]:checked').val() == 0) {
						rebuildChooseSeller();
					}
				}
			});
			// 【是全部商家】
			$("#allSeller").find(':radio[value="1"]').on("change", function() {
				$("#sellers").empty();// 移除商家选择组件
				$("#cities").find(".col-md-5").html($(".cityTemp").html());// 添加区域组件的html
				$("#cities").find(".ld").areaLd({// 重新构建区域组件
					isChosen : true,
					separator : ",",
					showConfig : [ {
						name : "couponCities[" + 0 + "].provinces",
						tipTitle : "--省--"
					}, {
						name : "couponCities[" + 0 + "].city",
						tipTitle : "--市--"
					} ],
					lastChange : function() {
						if ($('input[name="isAllSeller"]:checked').val() == 0) {
							rebuildChooseSeller();
						}
					}
				});
			});
			// 【不是全部商家】
			$("#allSeller").find(':radio[value="0"]').on("change", function() {
				$("#cities").find(".col-md-5").empty();// 移除区域选择组件
				cityCount = 0;
				rebuildChooseSeller();
			});
		});

		if ($("#allSeller").html()) {
			$("#allSeller").find(':radio[value="1"]').on("change", function() {
				$("#sellers").empty();

				$("#cities").find(".col-md-5").html($(".cityTemp").html());
				$("#cities").find(".ld").areaLd({
					isChosen : true,
					separator : ",",
					showConfig : [ {
						name : "couponCities[" + 0 + "].provinces",
						tipTitle : "--省--"
					}, {
						name : "couponCities[" + 0 + "].city",
						tipTitle : "--市--"
					} ],
					lastChange : function() {
						if ($('input[name="isAllSeller"]:checked').val() == 0) {
							rebuildChooseSeller();
						}
					}
				});
			});
			// 【不是全部商家】
			$("#allSeller").find(':radio[value="0"]').on("change", function() {
				$("#cities").find(".col-md-5").empty();
				cityCount = 0;
				rebuildChooseSeller();
			});
		}

		if ($("#cities").html()) {
			$(".updateld").each(function(index, item) {
				var ld = $(this).areaLd({
					isChosen : true,
					separator : ",",
					showConfig : [ {
						name : "couponCities[" + index + "].provinces",
						tipTitle : "--省--"
					}, {
						name : "couponCities[" + index + "].city",
						tipTitle : "--市--"
					} ],
					lastChange : function() {
						if ($('input[name="isAllSeller"]:checked').val() == 0) {
							rebuildChooseSeller();
						}
					}
				});
			});
		}
		if ($.trim($("#sellers").html()).length > 0) {
			searchChosen = null;
			$("#sellers").empty();
			$("#sellers").html(sellersDiv);
			searchChosen = $("#sellerids")
					.searchChosen(
							{
								url : searchChooseURL + getCityids(),
								initUrl : "coupon/generate/update/getSellers.jhtml?cid="
										+ $("#cid").val(),
								initId : "sellerid",
								initTitle : "sellername"
							});
			
			/***/
			var searchChosen1 = $("#excludesellerids")
			.searchChosen(
					{
						url : searchChooseURL + getCityids(),
						initUrl : "coupon/generate/update/getSellers.jhtml?cid="
								+ $("#cid").val(),
						initId : "sellerid",
						initTitle : "sellername"
					});
		}
	}
});

/**
 * 重新构建选择商家控件
 */
function rebuildChooseSeller() {
	searchChosen = null;
	// 清空原有的
	$("#sellers").empty();
	$("#sellers").html(sellersDiv);
	searchChosen = $("#sellerids").searchChosen({
		url : searchChooseURL + getCityids()
	});
}

function initDate() {
	var date = {
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		minView : "month",
	// pickerPosition : "bottom-left"
	}, time = {
		format : "hh:ii",
		autoclose : true,
		// todayBtn : true,
		minView : "hour",
		maxView : "hour",
		startView : "day",
		minuteStep : 1,
		// pickerPosition : "bottom-left",
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

				/*
				 * $(element).siblings(".date-end").datetimepicker("setStartDate",$(element).val());
				 * $(element).siblings(".date-end").datetimepicker('update');
				 */
				// 初始化开始时间
				$(element).datetimepicker($.extend({
					startDate : new Date()
				}, date)).on(
						"changeDate",
						function() {
							$(element).siblings(".date-end").datetimepicker(
									"setStartDate", $(element).val());
						});

				/* $(element).datetimepicker('update'); */
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
				/*
				 * $(element).siblings(".time-end").datetimepicker("setStartDate",$(element).val());
				 * $(element).siblings(".time-end").datetimepicker('update');
				 */

				$(element).datetimepicker($.extend({}, time)).on(
						"changeDate",
						function() {
							$(element).siblings(".time-end").datetimepicker(
									"setStartDate", $(element).val());
						});
				/* $(element).datetimepicker('update'); */
			});
}

function formAjax() {

	if (checkValidaty()
			&& ($('input[name="showAll"]:checked').val() == 1 || checkCities())) {
		var data = jsonFromt($('#editCouponForm').serializeArray());
		var url;
		if (type = "update") {
			url = "coupon/generate/update.jhtml";
		} else {
			url = "coupon/generate/add.jhtml";
		}
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
}

// 输入的值改变就会改变radio的值
function setCondition() {
	$(":radio[name='condition']").on("change", function() {
		var input = "<input class='form-control' type='text'/>";
		if ($(this).attr("data-input") == 1) {
			$("#customConditionDiv > input").addClass("customConditionInput");
			addClassRules();
			var $this = $(this);
			$("#customConditionDiv > input").on("change", function() {

				$this.attr("value", $(this).val());
			});
		} else {
			$("#customConditionDiv").empty();
			$("#customConditionDiv").html(input);
		}
	});
	$("#customConditionDiv > input").on("change", function() {
		$("#customConditionRadio").attr("value", $(this).val());
	});
}

/**
 * 取得所有所选城市的id以逗号拼接的值
 * 
 * @returns
 */
function getCityids() {
	var ids = [];
	$.each($('#cities').find('select[name$=".city"]'), function(index, value) {
		ids.push($(value).val());
	});
	return "?cityids=" + ids.join(",");
}

function addShowAll() {
	$("#allArea").on(
			"change",
			function() {
				$("#cities").find(".col-md-5").empty();
				cityCount = 0;// 清零

				if ($('input[name="showAll"]')
						&& $('input[name="showAll"]').val() === 1) {
					url = searchChooseURL;
				} else {
					url = searchChooseURL + getCityids();
				}
				searchChosen.destory();
				searchChosen = $("#sellerids").searchChosen({
					url : url,
				});
			});

	$("#specifyArea").on(
			"change",
			function() {
				$("#cities").find(".col-md-5").html($(".cityTemp").html());
				$("#cities").find(".ld")
						.areaLd(
								{
									isChosen : true,
									separator : ",",
									showConfig : [
											{
												name : "couponCities[" + 0
														+ "].provinces",
												tipTitle : "--省--"
											},
											{
												name : "couponCities[" + 0
														+ "].city",
												tipTitle : "--市--"
											} ],
									lastChange : function() {
										searchChosen.destory();
										if ($('input[name="showAll"]')
												&& $('input[name="showAll"]')
														.val() === 1) {
											url = searchChooseURL;
										} else {
											url = searchChooseURL
													+ getCityids();
										}
										searchChosen = $("#sellerids")
												.searchChosen({
													url : url
												});
									}
								});
				if ($('input[name="showAll"]')
						&& $('input[name="showAll"]').val() === 1) {
					url = searchChooseURL;
				} else {
					url = searchChooseURL + getCityids();
				}
				searchChosen.destory();
				searchChosen = $("#sellerids").searchChosen({
					url : url,
				});
			});
}

function updateShowAll() {
	$("#allArea").on("change", function() {
		$("#cities").find(".col-md-5").toggle();
		searchChosen.destory();
		url = searchChooseURL;
		searchChosen = $("#sellerids").searchChosen({
			url : url,
		});
	});

	$("#specifyArea").on("change", function() {
		$("#cities").find(".col-md-5").toggle();
		searchChosen.destory();
		url = searchChooseURL + getCityids();
		searchChosen = $("#sellerids").searchChosen({
			url : url
		});
	});
}

/**
 * 校验区域是否填写
 * 
 * @returns {Boolean}
 */
function checkCities() {
	var reuslt = true;
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

/**
 * 校验有效日期是否填写
 * 
 * @returns {Boolean}
 */
function checkValidaty() {
	var reuslt = true;
	if ($("input[name='swichtime']:checked").val() == 1) {
		return true;
	}
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

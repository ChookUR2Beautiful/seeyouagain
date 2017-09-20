$(function() {
	var type = $("#brandSellerFrom").find("input[name='type']").val();
	var str;
	if (type == "add") {
		str = '> 用户端管理 > <span><a href="user_terminal/brandSeller/init.jhtml" target="right">品牌店管理</a><span> ><span>添加品牌店</span>';
	} else {
		str = '> 用户端管理  > <span><a href="user_terminal/brandSeller/init.jhtml" target="right">品牌店管理</a><span> ><span>修改品牌店</span>';
	}

	inserTitle(str, 'brandSeller', true);

	$("#brandSellerLogoId").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	$("#picMiddleDiv").uploadImg({
		urlId : "picMiddle",
		showImg : $('#picMiddle').val()
	});
	$("#picLowDiv").uploadImg({
		urlId : "picLow",
		showImg : $('#picLow').val()
	});
	$("#brandImageHighDiv").uploadImg({
		urlId : "brandImageHigh",
		showImg : $('#brandImageHigh').val()
	});
	$("#brandImageMiddleDiv").uploadImg({
		urlId : "brandImageMiddle",
		showImg : $('#brandImageMiddle').val()
	});
	$("#brandImageLowDiv").uploadImg({
		urlId : "brandImageLow",
		showImg : $('#brandImageLow').val()
	});

	validate("brandSellerFrom", {
		rules : {
			brandName : {
				required : true
			},
			bewrite : {

			},
			isBrand : {

			},
			dateStart : {

			},
			rebate : {
				required : true,
				number : true,
				min : 0,
				max : 100
			},
			sort : {
				digits : true
			},
			agio : {
				number : true,
				min : 0,
				max : 100
			},
			isAll : {
				required : true
			}

		},
		messages : {
			brandName : {
				required : "请输入品牌商名称！"
			},
			rebate : {
				required : "请输入返利！",
				number : "只能输入数字！",
				min : "数值只能是0到100！",
				max : "数值只能是0到100！"
			},
			sort : {
				digits : "只能输入正整数！"
			},
			isAll : {
				required : "请选择一种区域类型!"
			}
		}
	}, formAjax);

	$("input[name='dateStart']").datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function(ev) {
				$("input[name='dateEnd']").datetimepicker("setStartDate",
						$("input[name='dateStart']").val());
			});

	$("input[name='dateEnd']").datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function(ev) {
				$("input[name='dateStart']").datetimepicker("setEndDate",
						$("input[name='dateEnd']").val());
			});

	$("input[name='dateEnd']").datetimepicker(
			"setStartDate",
			$("input[name='dateStart']").val() != "" ? $(
					"input[name='dateStart']").val() : new Date());

	// 取得全局的区域DIV
	var areaDivCon = $("#areaDivCon");

	/**
	 * 初始化是否显示区域DIV
	 */
	if (type == "add") {
		$("#areaDivCon").remove();
	} else {// 修改的话，是全国的话就初始化区域
		console.log($("#brandSellerFrom").find("input:radio:checked").val());
		if ($("#brandSellerFrom").find("input[name='isAll']:checked").val() == 0) {// 是特定的区域就会显示
			initAreaLd($("#areaInfo"));
		} else {
			$("#areaDivCon").remove();
		}
	}

	$("#specifyArea").on("change", function() {
		$("#areaDivOp").after(areaDivCon);
		initAreaLd($("#areaInfo"));
	});

	$("#allArea").on("change", function() {
		$(areaDivCon).find("#areainfo").empty();
		$("#areaDivCon").remove();
	});

});

/**
 * 将区域div转化为联动区域
 */
function initAreaLd(id) {
	$(id).areaLd({
		isChosen : true,
		isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		separator : ",",
		showConfig : [ {
			name : "province",
			tipTitle : "--省--",
			width : "22%"
		}, {
			name : "city",
			tipTitle : "--市--",
			width : "22%"
		}, {
			name : "area",
			tipTitle : "",
			width : "50%"
		} ]
	});
}

/**
 * form 提交
 * 
 * @param form
 */
function formAjax(form) {

	// 校验区域选择是否选择，没有选择不能提交
	var success;
	if ($("#brandSellerFrom").find("input[name='isAll']:checked").val() == 1) {// 全国

	} else {// 特定区域
		success = function() {
			var selectAray = [ "province", "city" ];
			return checkSelect('#brandSellerFrom', "#areaInfo", selectAray,
					true);
		}();
	}
	if (success == null || success) {
		var url = $(form).find("input[name='type']").val() == 'add' ? 'user_terminal/brandSeller/add.jhtml'
				+ '?t=' + Math.random()
				: 'user_terminal/brandSeller/update.jhtml' + '?t='
						+ Math.random();
		var data = jsonFromt($('#brandSellerFrom').serializeArray());
		if (data) {
			var areaInfo = $("#areaInfo");
			if (areaInfo.length > 0) {
				var areas = $("#areaInfo").find("select[name='area']").val() != null ? $(
						"#areaInfo").find("select[name='area']").val()
						.toString()
						: null;
				data.area = areas;
			}
		}
		$
				.ajax({
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
						var url = contextPath
								+ '/user_terminal/brandSeller/init.jhtml';
						setTimeout(function() {
							location.href = url;
						}, 1000);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
	}
}

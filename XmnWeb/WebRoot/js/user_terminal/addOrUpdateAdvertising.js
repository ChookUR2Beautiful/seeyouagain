$.validator.addMethod("isURL", function(value, element) {
	var strRegex = '^((https|http|ftp|rtsp|mms)?://)'
	/*
	 * + '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?'
	 * //ftp的user@ + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184 +
	 * '|' // 允许IP和DOMAIN（域名） + '([0-9a-z_!~*\'()-]+.)*' // 域名- www. +
	 * '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名 + '[a-z]{2,6})' // first
	 * level domain- .com or .museum + '(:[0-9]{1,4})?' // 端口- :80 + '((/?)|' //
	 * a slash isn't required if there is no file name +
	 * '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'
	 */;
	var re = new RegExp(strRegex);
	return value ? (re.test(value)) : true;
}, "请输入正确的链接");

$(function() {
	initStartDateAndEndDate("editAdvertisingForm", "startTime", "endTime");
	advertisingValidate();
	var initAreaInfo = areaInfoHandler(function(areaInfo) {
		initAreaLd(areaInfo);
		var widths = getWidths();
		areaInfo.find(".chosen-container").each(function(i, v) {
			$(this).css("width", widths[i]);
		});
		return areaInfo.attr("initValue");
	});
	$("#specifyArea").on("click", {
		"initAreaInfo" : initAreaInfo
	}, function(event) {
		if ($(this).is(":checked")) {
			removeAreaInfo();
			createAreaInfo(event.data.initAreaInfo);
			initAreaLd($("#areaInfo"));
		}
	});
	$("#allArea").on("click", removeAreaInfo);

	// 广告图片
	$("#adbpicUpload").uploadImg({
		urlId : "adbpic",
		showImg : $("#adbpic").val()
	});

	// 分享图片
	$("#shareImgUpload").uploadImg({
		urlId : "share_img",
		showImg : $("#share_img").val()
	});

	$("#picMiddleUpload").uploadImg({
		urlId : "pic_middle",
		showImg : $("#pic_middle").val()
	});
	$("#picLowUpload").uploadImg({
		urlId : "pic_low",
		showImg : $("#pic_low").val()
	});

});

function getWidths() {
	return [ "20%", "23%", "48%" ];
}

/**
 * 区域处理
 * 
 * @param {}
 *            fn
 */
function areaInfoHandler(fn) {
	var areaInfo = $("#areaInfo");
	if (areaInfo.length > 0) {
		return fn.call(this, areaInfo);
	}
	return null;
}

function removeAreaInfo() {
	var tr = $("#areaTable tr");
	if (tr.length == 2) {
		$(tr[1]).remove();
	}
}

function createAreaInfo(initAreaInfo) {
	var div = $("<div>").addClass("input-group").attr("id", "areaInfo").css({
		"width" : "80%",
		"float" : "left"
	});
	if (initAreaInfo) {
		div.attr("initValue", initAreaInfo);
	}
	var h5 = $("<h5>");
	var font = $("<font>").css({
		"color" : "red",
		"float" : "left"
	}).text("(区域可多选)").appendTo(h5);
	var td = $("<td>").attr("colspan", 3).append(div, h5);
	var tr = $("<tr>").append(td);
	$("#areaTable tr:last").after(tr);
}

/**
 * 表单验证
 */
function advertisingValidate() {
	validate("editAdvertisingForm", {
		rules : {
			content : {
				required : true
			},
			adburl : {
				required : true,
				isURL : true
			},
			sort : {
				required : true,
				digits : true,
				range : [ 0, 2147483647 ]
			},
			isshow : {
				required : true
			},
			type : {
				required : true
			},
			share_cont : {
				maxlength : 300
			},
			remarks : {
				maxlength : 100
			},
			isall : {
				required : true
			},
			shareUrl : {
				isURL : true
			},
			startTime : {
				required : true
			},
			endTime : {
				required : true
			}
		},
		messages : {
			content : {
				required : "广告文本不能为空!"
			},
			adburl : {
				required : "广告链接不能为空!",
				isURL : "请输入正确的URL格式"
			},
			sort : {
				required : "排序值不能为空！",
				digits : "排序值只能为整数!",
				range : "取值为1-2147483647!"
			},
			isshow : {
				required : "请选择是否显示!"
			},
			type : {
				required : "请选择类型!"
			},
			share_cont : {
				maxlength : "分享文案的最大长度为300字符!"
			},
			remarks : {
				maxlength : "备注的最大长度为100字符!"
			}
		}
	}, formAjax);
}

/**
 * 初始化联动信息
 * 
 * @param {}
 *            areaInfo
 */
function initAreaLd(areaInfo) {
	var widths = getWidths();
	areaInfo.areaLd({
		isChosen : true,
		isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		separator : ",",
		showConfig : [ {
			name : "province",
			tipTitle : "--省--",
			width : widths[0]
		}, {
			name : "city",
			tipTitle : "--市--",
			width : widths[1]
		}, {
			name : "area",
			tipTitle : "",
			width : widths[2]
		} ]
	});
}

/**
 * Ajax 请求
 */
function formAjax() {
	var success = areaInfoHandler(function(areaInfo) {
		var selectAray = [ "province", "city" ];
		return checkSelect('#editAdvertisingForm', "#areaInfo", selectAray,
				true);
	});

	if (success == null || success) {
		var url;
		if ($('#isType').val() == 'add') {
			url = 'user_terminal/advertising/add.jhtml' + '?t=' + Math.random();
		} else {
			url = 'user_terminal/advertising/update.jhtml' + '?t='
					+ Math.random();
		}

		var data = jsonFromt($('#editAdvertisingForm').serializeArray());
		if (data) {
			data.area = areaInfoHandler(function(areaInfo) {
				var areas = $(areaInfo).find("select[name='area']").val();
				return areas ? areas.toString() : null;
			});
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
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if ($('#isType').val() == 'add') {
						userAdvertisingList.reset();
					} else {
						userAdvertisingList.reload();
					}
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	}
}
/**
 * 初始化日期控件，并设置开始时间和结束时间的选择限制（开始时间不能大于结束时间，结束时间不能小于开始时间）
 * 
 * @param startInputName
 * @param endInputName
 */
function initStartDateAndEndDate(form,startInputName, endInputName) {
	var format = "yyyy-mm-dd hh:ii:ss";
	$("#"+form).find("input[name='" + startInputName + "']").datetimepicker({
		autoclose : true,
		format : format,
		startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function(ev) {
				$("#"+form).find("input[name='" + endInputName + "']").datetimepicker(
						"setStartDate",
						$("#"+form).find("input[name='" + startInputName + "']").val());
			});

	$("#"+form).find("input[name='" + endInputName + "']").datetimepicker({
		autoclose : true,
		format : format,
		startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function(ev) {
				$("#"+form).find("input[name='" + startInputName + "']").datetimepicker(
						"setEndDate",
						$("#"+form).find("input[name='" + endInputName + "']").val());
			});

	$("#"+form).find("input[name='" + endInputName + "']").datetimepicker(
			"setStartDate",
			$("#"+form).find("input[name='" + startInputName + "']").val() != "" ? $("#"+form).find(
					"input[name='" + startInputName + "']").val() : new Date());
}
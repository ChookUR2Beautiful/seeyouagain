$(document).ready(function() {
	// 营业执照正面
	$("#licenseurlImg").uploadImg({
		urlId : "licenseurl",
		showImg : $('#licenseurl').val()
	});
	
	// 身份证件
	$("#idnumberurlImg").uploadImg({
		urlId : "idnumberurl",
		showImg : $('#idnumberurl').val()
	});

	// 头像
	$("#headurlImg").uploadImg({
		urlId : "headurl",
		showImg : $('#headurl').val()
	});
	
	if ($('#isType').val() == 'add') {
		inserTitle(' > <span><a href="business_cooperation/joint/add/init.jhtml?isType=add" target="right">添加合作商信息</a>','addJointInfo', false);
	} else {
		inserTitle(' > <span><a href="business_cooperation/joint/update/init.jhtml?jointid='+ $('#jointid').val()+ '" target="right">编辑合作商信息</a>','editJointInfo', false);
		// 初始化密码赋值
		if ($('#staffid').val()) {
			$('input[name=password]').val("000000");
			$('input[name=password]').attr("readonly", true);
			$('input[name=account]').attr("readonly", true);
		}
	}

	$("#tradeSelect").tradeLd({
		showConfig : [ {tipTitle : "请选择"}, {name : "industry",tipTitle : "请选择"} ]
	});

	var areaInfo = $("#areaInfo").areaLd({
		isChosen : true,
		isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		lastChange : checkArea,
		isDisabled : $("#isType").val() == "update",
		showConfig : [ {
			name : "province",
			tipTitle : "--省--",
			width : '20%'
		}, {
			name : "city",
			tipTitle : "--市--",
			width : '20%'
		}, {
			name : "area",
			tipTitle : "",
			width : '55%'
		} ]
	});
	/*
	 * $("#areaInfo").on("change","select[name=province]",function(event){
	 * console.info(event); console.info(this);
	 * console.info(this[0].text); });
	 */
	
	/**
	 * add by lifeng 20160727 15:48:20
	 * 如果是编辑页面则不能修改saas总套数...
	 */
	if ($('#isType').val() == 'update') {
		$("#saasnum").attr("readonly","readonly");
		$("#saasagio").attr("readonly","readonly");
		$("#saasamount").attr("readonly","readonly");
	}
});

function checkArea($dom) {
	var areas = $dom.val();
	var flag = true;
	var initArea = $("#areaInfo").attr("initValue");
	if (initArea) {
		for ( var i in areas) {
			if (initArea.indexOf(areas[i]) >= 0) {
				delete areas[i];
			}
		}
	}
	for ( var j in areas) {
		if (areas[j]) {
			$.ajax({
				type : 'post',
				url : "common/area/init/check.jhtml",
				async : false,
				data : {
					area : areas[j]
				},
				dataType : 'json',
				success : function(data) {
					if (data > 0) {
						flag = false;
					}
				}
			});
			if (!flag) {
				var areaName = $dom.find("option[value='" + areas[j] + "']")
						.prop("selected", false).text();
				showWarningWindow("warning", areaName + "已属于其他合作商！");
				break;
			}
		}
	}

}

/**
 * 保存合作商基本信息
 */
var saveJoint = function() {
	var selectAray = [ "province", "city", "area" ];
	var success = checkSelect('#editJointForm', "#areaInfo", selectAray, true);
	if (success) {
		var url;
		var data = jsonFromt($('#editJointForm').serializeArray());
		// 添加和编辑时获取城市和区域信息
		if (data) {
			var area = $("#areaInfo").find("select[name='area']").val()
					.toString();
			data.area = area;
		}
		if ($('#isType').val() == 'add') {
			url = 'business_cooperation/joint/add.jhtml' + '?t='
					+ Math.random();
		} else {
			url = 'business_cooperation/joint/update.jhtml' + '?t='
					+ Math.random();
		}
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				var url = contextPath
						+ '/business_cooperation/joint/init.jhtml';

				setTimeout(function() {
					location.href = url;
				}, 1000);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
}

/**
 * 保存员工信息
 */
function saveStaffInfo() {
	var url;
	if ($('#isType').val() == 'add') {
		if (!$('#jointidStff').val()) {
			showWarningWindow("warning", "请先添加合作商基本信息！");
			return;
		}
		url = 'business_cooperation/joint/add/Staff.jhtml' + '?t='
				+ Math.random();
	} else {
		url = 'business_cooperation/joint/update/Staff.jhtml' + '?t='
				+ Math.random();
	}
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#staffForm').serializeArray()),
		dataType : 'json',
		success : function(data) {
			showSmReslutWindow(data.success, data.msg);
			$('input[name=password]').val("000000");

			if (data.data) {
				$('input[name=staffid]').val(data.data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	}, "json");
}

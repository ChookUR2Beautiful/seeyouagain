var ISTYPE;
ISTYPE = $("#isType").val();
$(document).ready(function() {
	// 初始化合作商下拉框
	jointListInit();
	// 初始化业务员添加区域
	var areaInfo = $("#areaInfo").areaLd({
		isChosen : true,
		isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		lastChange : checkArea,// 每改变一次区域，验证是否已经存在其他合作商
		// isDisabled : $("#isType").val() == "update",//修改时不可选
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
			width : '50%'
		} ]
	});
	// 验证提交添加或修改的数据
	initValidator();
});
/**
 * 初始化合作商下拉框
 */
function jointListInit() {
	if (ISTYPE == "add") {
		$
				.ajax({
					url : "business_cooperation/salesmanManagement/add/init/jointListInit.jhtml",
					dataType : "json",
					method : "get",
					success : function(data) {
						$("#jointid").empty().append(
								'<option value="">请选择合作商</option>').attr(
								"readonly", false);
						;
						for (var i = 0; i < data.length; i++) {
							$("#jointid").append(
									'<option value="' + data[i].jointid + '">'
											+ data[i].corporate + '</option>');
						}
					}
				});
	} else {// 如果是修改编辑页面，将合作商和业务员账号ID设置为不可编辑
		var jointidUpt = $("#jointidUpt").val();
		var corporateUpt = $("#corporateUpt").val();
		$("#jointid").attr("readonly", true);
		$("#jointid").append(
				'<option value="' + jointidUpt + '">' + corporateUpt
						+ '</option>');
		$("#phoneid").attr("readonly", true);
	}
}
/**
 * 验证某个区域是否有合作商
 * 
 * @param $dom
 */
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
 * 触发取消
 */
$("#cancelId").on("click", function() {
	muBack();
});
/**
 * 取消方法
 */
function muBack() {
	var url = contextPath
			+ '/business_cooperation/salesmanManagement/init.jhtml';
	location.href = url;
}
/**
 * 验证并且提交添加或修改的数据
 */
var formId = [ "addStaffForm" ];
function initValidator() {
	for (var i = 0; i < formId.length; i++) {
		validate(formId[i], valiinfo[formId[i]], formSubmit(formId[i]));
	}
}
/**
 * 验证方法
 */
var valiinfo = {
	"addStaffForm" : {
		rules : {
			phoneid : {
				required : true,
				remote : function() {
					if (ISTYPE == "add") {
						return {
							url : "business_cooperation/salesmanManagement/add/init/vailstaff.jhtml",// 后台处理程序
							type : "post", // 数据发送方式
							dataType : "json", // 接受数据格式
							data : {
								phoneid : function() {
									return $("#phoneid").val();
								}
							}
						}
					}
					return false;
				},
				phoneidRule : true
			},
			jointid : {
				required : true
			},
			password : {
				required : function() {
					if (ISTYPE == "update") {
						return false;
					}
					return true;
				},
				digits : true,
				minlength : 6,
				maxlength : 6
			},
			fullname : {
				required : true
			},
			nickname : {
				required : true
			},
			province : {
				required : true
			},
			city : {
				required : true
			}
		},
		messages : {
			phoneid : {
				required : "业务员账号不能为空！",
				remote : "该业务员已存在！",
				phoneidRule : "请输入正确的手机号！"
			},
			jointid : {
				required : "请选择合作商！"
			},
			password : {
				required : "登录密码不能为空！",
				digits : "请输入数字！",
				minlength : "请输入6位数字！",
				maxlength : "请输入6位数字！"
			},
			fullname : {
				required : "业务员姓名不能为空！"
			},
			nickname : {
				required : "业务员昵称不能为空！"
			},
			province : {
				required : "请选择省！"
			},
			city : {
				required : "请选择市！"
			}
		}

	}
};
/**
 * 业务员账号正则
 */
$.validator.addMethod("phoneidRule", function(value, element) {
	var length = value.length;
	var phoneid = /^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/;
	return this.optional(element) || (length == 11 && phoneid.test(value));
}, "请输入正确的手机号！");
/**
 * 合作商业务员保存修改
 */
var UpdateSavaStaff = function() {
	var url;
	if (ISTYPE == "add") {
		url = "business_cooperation/salesmanManagement/add/staff.jhtml";
	} else {
		url = "business_cooperation/salesmanManagement/update/staff.jhtml";
	}
	var data = $('#addStaffForm').serializeArray();
	// 添加和编辑时获取城市和区域信息
	if (data) {
		var area = $("#areaInfo").find("select[name='area']").val().toString();
		data.area = area;
	}
	// 校验区域
	if (!checkids()) {
		return false;
	}
	// form转成json
	data = jsonFromt(data);
	// post提交请求
	$.post(url, data, function(result) {
		if (result.success) {
			showSmReslutWindow(result.success, result.msg);
			setTimeout(function() {
				muBack();
			}, 1000);
		} else {
			window.messager.warning(result.msg);
		}
	}, "json");
}
/**
 * 转换from表单为json数据格式
 */
function jsonFromt(data) {
	var json = {};
	for (var i = 0; i < data.length; i++) {
		json[data[i].name] = data[i].value;
	}
	return json;
}

/**
 * 提交表单方法
 */
var formHandle = {
	"addStaffForm" : UpdateSavaStaff
};
function formSubmit(form) {
	return formHandle[form];
}
/**
 * 区域校验
 */
function checkids() {// 区域校验
	var areaNums = $("#areaInfo ul li span").length;
	console.log(areaNums);
	if (areaNums != 0) {
		return true;
	}
	submitDataError($("#areaInfo"), "请选择区域!");
	return false;
};
var ISTYPE;
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	if (ISTYPE == "add") {
		inserTitle(' > 添加悬浮广告信息', 'addFloatAvertList', false);
	} else {
		inserTitle(' > 编辑悬浮广告信息', 'editFloatAvertList', false);
	}
	
    //初始化页面
	pageInit();
	/**
	 * 返回
	 */
	$("#backId").on("click", function() {
		muBack();
	});
	
	showPositionInit();

});


function pageInit() {
	// 添加图片信息
	uploadImg();
	// debugger;
	/* 销售时间 */
	$('input[name="startTime"]').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep : 30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		endDate : $("input[name='endTime']").val()
	}).on("changeDate",function() {
		$("input[name='endTime']").datetimepicker("setStartDate",
				$("input[name='startTime']").val());
    });

	$("input[name='endTime']").datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep : 30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : $("input[name='saleStartTime']").val()
	}).on("changeDate", function() {
		$("input[name='saleStartTime']").datetimepicker("setEndDate",
				$("input[name='endTime']").val());
	});

}

/**
 * 广告图上传
 */
function uploadImg() {
	// 广告图
	$("#activityImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
}

/**
 * 绑定类型初始化
 */
function showPositionInit(){
	var showPositionStr = $("#showPosition").val();
	var items=showPositionStr.split(",");
	for(var i=0; i<items.length; i++){
//		console.log(items[i]);
		$("#objectOrientedDiv").find("input:checkbox[value='"+items[i]+"']").attr('checked','true');
	}
}


// ****************************************保存数据方法********************************
validate("liveFloatAdvertiseForm", {
	rules : {
		title : {
			required : true
		},
		startTime : {
			required : true
		},
		endTime : {
			required : true
		},		
		picUrl : {
			required : true
		},
		redirectUrl : {
			required : true
		}
	},
	messages : {
		title : {
			required : "请输入广告名称",
		},
		startTime : {
			required : "请设置广告有效开始时间",
		},
		endTime : {
			required : "请设置广告有效结束时间",
		},		
		picUrl : {
			required : "请设置广告图",
		},
		redirectUrl : {
			required : "请设置跳转URL",
		}
	}
}, save);

/**
 * 自定义校验方法
 */
function validateCustomer() {
	var startTime = $("input[name='startTime']").val();
	if (startTime == null || startTime == "") {
		showWarningWindow("warning", "请选择广告开始时间!", 9999);
		return false;
	}

	var endTime = $("input[name='endTime']").val();
	if (endTime == null || endTime == "") {
		showWarningWindow("warning", "请选择广告结束时间!", 9999);
		return false;
	}

	return true;
}

// 保存套餐数据
function save() {
	var url;
	if (ISTYPE == "add") {
		url = "liveFloatAdvertise/manage/add.jhtml";
	} else {
		url = "liveFloatAdvertise/manage/update.jhtml";
	}
	var result = validateCustomer();
	if (!result) {// 自定义校验不通过
		return;
	}
	alert(ISTYPE);
	var data = $('#liveFloatAdvertiseForm').serializeArray();
	data = jsonFromt(data);
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href = "liveFloatAdvertise/manage/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});

}
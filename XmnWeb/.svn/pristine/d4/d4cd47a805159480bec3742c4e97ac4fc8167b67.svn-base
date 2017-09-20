var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			addNum : {
				required : true,
				digits:true,
				range : [ 1, 5000]
			}
		},
		messages:{
			addNum:{
				required:"生成数量不能为空!",
				digits :"生成数量必须为整数",
				range:"生成数量取值为1-5000之间"
			}
		}
	}, save);
	
});


$("#concernLen").ionRangeSlider({
	type: "double",
	min: 40,
	max: 100,
	from: 40,
	to: 100,
	step: 1,
	grid: true,
});
$("#fansLen").ionRangeSlider({
	type: "double",
	min: 40,
	max: 46,
	from: 40,
	to: 46,
	step: 1,
	grid: true,
});
$("#rankNoLen").ionRangeSlider({
	type: "double",
	min: 1,
	max: 80,
	from: 1,
	to: 80,
	step: 1,
	grid: true,
});
$("#conditionLen").ionRangeSlider({
	type: "double",
	min: 1,
	max: 80,
	from: 1,
	to: 80,
	step: 1,
	grid: true,
});
$("#storeLen").ionRangeSlider({
	type: "double",
	min: 1,
	max: 80,
	from: 1,
	to: 80,
	step: 1,
	grid: true,
		
});

/**
 * 生成机器人信息
 */
function save() {
	var url;
	var suffix = ".jhtml";
	url = "fresh/robot/add" + suffix;
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#' + formId).serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
			if (data.success) {
					robotList.reset();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}

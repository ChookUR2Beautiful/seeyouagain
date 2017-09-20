var valiinfo = {
	rules : {
		hotWord : {
			required : true
		},
		hotOrder : {
			required : true,
			digits:true,
			max:9999999,
		}
	},
	messages : {
		hotWord : {
			required : "关键字不能为空"
		},
		hotOrder : {
			required : "排序不能为空,且必需为整数",
			digits: "排序必需为整数",
			max:"排序长度不能超过7位"
		}
	}
}

$.validator.addMethod("landmarkCheck", function(value, element, params) {
	var len = value.length;
	if (len > 16) {
		return false;
	} else if (value > params[1] || value < params[0]) {
		return false;
	} else {
		var indexOf = value.indexOf(".");
		if (indexOf > 0) {
			var numStr = value.substr(indexOf + 1);
			return !(numStr.length > 12);
		}
		return true;
	}

	return (this.optional(element) || (str.length <= 10));
}, "请检查输入内容！");

$(document).ready(function() {
	validate("editBusinessForm", valiinfo, busave);
	var areaId = $("#areaId");
	if ($(areaId).length > 0) {
		var areaId = $(areaId).areaLd({
			//isChosen : true,
			commonChange : function($dom, level) {

			},
			showConfig : [ {
				name : "tpareaid",
				tipTitle : "--省--"
			}, {
				name : "areaId",
				tipTitle : "--市--"
			}]
		});
	}
});

function busave() {
	var success = false;
	var url;
	if ($('#isType').val() == 'add') {
		url = 'marketingManagement/hotWords/add.jhtml' + '?t=' + Math.random();
		var selectAray = [ "tpareaid", "areaId" ];
		success = checkSelect("#editBusinessForm", "#areaId", selectAray, false);
	} else {
		url = 'marketingManagement/hotWords/update.jhtml' + '?t='
				+ Math.random();
		success = true;
	}
	if (success) {
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#editBusinessForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if ($('#isType').val() == 'add') {
						hotWordsList.reset();
					} else {
						hotWordsList.reload();
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
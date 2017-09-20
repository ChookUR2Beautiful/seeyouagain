var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var ISADD = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			height : {
				required : true,
				digits:true
			},
			weight : {
				required : true,
				digits:true
			},
			threeDimensional : {
				required : true,
				threeDimensionalRule:true
			},
			selfComment : {
				required : true
			}
		},
		messages:{
			height : {
				required : "请填写身高！",
				digits : "身高只能为数值！"
			},
			weight : {
				required : "请填写身高！",
				digits : "体重只能为数值！"
			},
			threeDimensional : {
				required : "请填写三围！",
				threeDimensionalRule : "请输入正确的三围信息，如：85/79/90！"
			}
		}
	}, anchorSave);
	
});


/**
 * 主播三围校验
 */
$.validator.addMethod("threeDimensionalRule", function(value, element) {
	var phone = /^[0-9]{2,3}\/[0-9]{2,3}\/[0-9]{2,3}$/;
	return this.optional(element) || (phone.test(value));
}, "请输入正确的三围信息！");


/**
 * 保存主播信息
 */
function anchorSave() {
	var url;
	var suffix = ".jhtml";
//	url = "anchorBusiness/manage/updateThreeDimensional" + suffix;
	url = "anchorBusiness/manage/update" + suffix;
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	if (jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
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
					anchorList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}
}

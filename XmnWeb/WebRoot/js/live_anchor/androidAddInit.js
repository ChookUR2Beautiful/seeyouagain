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
			androidNum : {
				required : true,
				digits:true,
				range : [ 1, 5000]
			}
		},
		messages:{
			androidNum:{
				required:"生成数量不能为空!",
				digits :"生成数量必须为整数",
				range:"生成数量取值为1-5000之间"
			}
		}
	}, androidSave);
	
});


/**
 * 生成机器人信息
 */
function androidSave() {
	var url;
	var suffix = ".jhtml";
	url = "liveAndroid/manage/add" + suffix;
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
					androidList.reset();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}

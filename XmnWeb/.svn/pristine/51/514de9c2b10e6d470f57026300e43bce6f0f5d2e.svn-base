var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
var liveRecordSelected;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			no:{
				required: true,
				digits:true,
				min:0,
			},
			name:{
				required: true,
			},
			roses : {
				required : true,
				digits:true,
				min:0,
			},
			orchids:{
				required:true,
				digits:true,
				min:0,
			},
			sunflowers:{
				required:true,
				digits:true,
				min:0,
			},
			warehousingCapacity:{
				required:true,
				digits:true,
				min:0,
			},
			dailyNectar:{
				required:true,
				digits:true,
				min:0,
			}
		},
		messages:{
		}
	}, save);
	

});




/**
 * 保存信息
 */
function save() {
	var url;
	var suffix = ".jhtml";
		url = "manor/activate/setting" + suffix;
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
				//提交后禁用提交按钮，防止重复提交表单
				$('#submitBtn').attr("disabled","disabled");
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if (isAdd) {
						recordList.reset();
					} else {
						recordList.reload();
					}
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
	}
}



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
			afterSalary:{
				required: true,
				range:[1,100000000000000000]
			}
		},
		messages:{
			sort:{
				required:"请输入排序值",
			}
		}
	}, anchorImageSave);
	

	
	// 页面图片
	$("#coverUrlDiv").uploadImg({
		urlId : "coverUrl",
		showImg : $('#coverUrl').val()
	});
	
});




/**
 * 保存礼物信息
 */
function anchorImageSave() {
	var suffix = ".jhtml";
	var	url = "liveSalary/update" + suffix;
	
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	if (jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		var data=jsonFromt($('#' + formId).serializeArray());

		var isTaxes=$("[name='isTaxes']:checked");
		if(isTaxes.length){
			data.isTaxes=1;
		}else{
			data.isTaxes=0;
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
				recordList.reload();
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









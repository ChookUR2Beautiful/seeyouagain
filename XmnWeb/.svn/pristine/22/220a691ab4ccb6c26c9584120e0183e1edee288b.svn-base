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
			ledgerRatio : {
				range : [ 0, 1]
			},
			saleCouponRatio : {
				range : [ 0, 1]
			},
			useCouponRation : {
				range : [ 0, 1]
			}	
		},
		messages:{
			ledgerRatio:{
				range : "只能为0-1之间的数"
			},
			saleCouponRatio:{
				range : "只能为0-1之间的数"
			},
			useCouponRation:{
				range : "只能为0-1之间的数"
			}
		}
	}, save);
	
	
});


/**
 * 保存信息
 */
function save() {
	var url;
	var suffix = ".jhtml";
	url = "anchor/manage/updateBatchRatio" + suffix;
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var validateResult=customerValidate();
	if(!validateResult){
		return ;
	}
	
	if (jsonTextInit != jsonText) {// 新增或修改数据才会发送请求
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
//		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 自定义校验函数
 */
function customerValidate(){
	var result=true;
	return result;
}

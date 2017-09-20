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
			fansMin : {
				required : true,
				digits:true,
				range : [ 0, 5000]
			},
			fansMax : {
				required : true,
				digits:true,
				range : [ 0, 5000]
			}	
		},
		messages:{
			fansMin:{
				range : "只能为0-5000之间的数"
			},
			fansMax:{
				range : "只能为0-5000之间的数"
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
	url = "anchor/manage/configureFansBatch" + suffix;
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
					var targetUrl="anchor/manage/init.jhtml";
					var url = contextPath +'/'+targetUrl;
					setTimeout(function() {
						location.href = url;
					}, 1000);
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

/**
 * 自定义校验函数
 */
function customerValidate(){
	debugger;
	var result=true;
	
	var fansMin=$("#fansMin").val();
	var fansMax=$("#fansMax").val();
	
	if(parseInt(fansMax) <= parseInt(fansMin)){
		showWarningWindow("warning","最大值需大于最小值!",9999);
		result=false;
		return result;
	}
	
	return result;
}

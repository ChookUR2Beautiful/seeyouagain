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
			content : {
				required:true
			},
			status : {
				required:true
			}
		},
		messages:{
			content:{
				required:"请输入广播内容"
			},
			status : {
				required:"请选择有效状态"
			}
		}
	}, broadcastSave);
	
});




/**
 * 保存广播信息
 */
function broadcastSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "announcement/manage/add" + suffix;
	} else {// 修改操作
		url = "announcement/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
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
		showWarningWindow("warning", "没做任何修改！",9999);
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	var app =$("#app").val();
	if(app == null || app==""){
		showWarningWindow("warning","请选择应用类型!",9999);
		rsult=false;
		return ;
	}
	
	return result;
}


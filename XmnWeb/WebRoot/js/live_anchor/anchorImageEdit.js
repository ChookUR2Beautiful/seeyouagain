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
			imageComment : {
				required : true
			},
			sort:{
				required: true,
				digits:true,
				range:[1,99]
			}
		},
		messages:{
			imageComment:{
				required:"请输入文字说明"
			},
			sort:{
				required:"请输入排序值",
				digits:"请输入整数",
				range:"输入值必须为1-99之间的整数"
			}
		}
	}, anchorImageSave);
	
	// 页面图片
	$("#anchorImageDiv").uploadImg({
		urlId : "anchorImage",
		showImg : $('#anchorImage').val()
	});
	
});




/**
 * 保存礼物信息
 */
function anchorImageSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "anchorBusiness/manage/anchorImage/addPhoto" + suffix;
	} else {// 修改操作
		url = "anchorBusiness/manage/anchorImage/updatePhoto" + suffix;
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
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	
	var zhiboCover=$("#anchorImage").val();
	if(zhiboCover == null || zhiboCover==""){
		showWarningWindow("warning","请上传页面配图 !",9999);
		rsult=false;
		return ;
	}
	
	return result;
}


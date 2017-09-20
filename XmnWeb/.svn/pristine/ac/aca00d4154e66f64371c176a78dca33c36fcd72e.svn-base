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
			mediaUrl : {
				required : true
			}
		},
		message:{
			mediaUrl:{
				required:"请输入链接内容"
			}
		}
	}, pageImageSave);
	
	// 页面图片
	$("#pageImageDiv").uploadImg({
		urlId : "pageImage",
		showImg : $('#pageImage').val()
	});
	/*initImageType();*/
});

/*function initImageType(){
	$("#imageType").chosenObject({
		id: "imageType",
		hideValue : "stringPageImage",
		showValue : "stringPageImage",
		url : "anchorBusiness/manage/startImage/initImageType.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}*/


/**
 * 保存礼物信息
 */
function pageImageSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "anchorBusiness/manage/startImage/add" + suffix;
	} else {// 修改操作
		url = "anchorBusiness/manage/startImage/update" + suffix;
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
	
	var zhiboCover=$("#pageImage").val();
	if(zhiboCover == null || zhiboCover==""){
		showWarningWindow("warning","请上传页面配图 !",9999);
		rsult=false;
		return ;
	}
	var imageType=$("#imageType").val();
	if(imageType == null || imageType==""){
		showWarningWindow("warning","请选择图片类型 !",9999);
		rsult=false;
		return ;
	}
	return result;
}


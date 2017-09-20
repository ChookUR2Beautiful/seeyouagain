var ISTYPE

$(document).ready(function() {
	ISTYPE = $("#isType").val();
	/*var id = $('#id').val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="freshLabel/manage/add/init.jhtml target="right">添加标签信息</a>','addFreshLabel',false);
	}else{
		inserTitle(' > 编辑标签信息','editFreshLabel', false);
	}*/
	
	/**
	 * 返回
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
});

var jsonTextInit;

$(function() {
	jsonTextInit = JSON.stringify({
		dataform : $("#editFrom").serializeArray()
	});
	validate("editFrom", {
		rules : {
			labelName : {
				required : true
			}
			
		},
		messages:{
			labelName:{
				required:"请输入标签名称"
			}
		}
	}, labelInfoSave);
	
	// 礼物图片
	$("#freshLabelImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	
});




/**
 * 保存礼物信息
 */
function labelInfoSave() {
	var url;
	var suffix = ".jhtml";
	if (ISTYPE == "add") {// 添加操作
		url = "freshLabel/manage/add" + suffix;
	} else {// 修改操作
		url = "freshLabel/manage/update" + suffix;
	}
	
	var labelType = $("#type").val();
//	var initListUrl  = "freshLabel/manage/init/list.jhtml?type="+labelType;
	var initListUrl  = "freshLabel/manage/init.jhtml";
	var data = $('#editFrom').serializeArray();
	var jsonText = JSON.stringify({
		dataform : data
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	if (ISTYPE == "add" || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				window.location.href=initListUrl;
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	} else {
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	
	var zhiboCover=$("#picUrl").val();
	if(zhiboCover == null || zhiboCover==""){
		showWarningWindow("warning","请上传标签图片!",9999);
		rsult=false;
		return ;
	}
	
	return result;
}


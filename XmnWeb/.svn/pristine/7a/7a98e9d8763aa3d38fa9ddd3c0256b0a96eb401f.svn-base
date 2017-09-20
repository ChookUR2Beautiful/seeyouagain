var formId = "editForm";
var jsonTextInit;
$(function(){
	
	if($("#id").val()){
		inserTitle(' > <span>编辑模板','saasTemplateEdit',false);
	}else{
		inserTitle(' > <span>添加模板','saasTemplateEdit',false);
	}
	
	templateTagsInit();
	
	// 头像
	$("#coverImageDiv").uploadImg({
		urlId : "coverImage",
		showImg : $('#coverImage').val()
	});
	
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//表单校验
	validate(formId, {
		rules : {
			title : {
				required : true
			},
			serialNo:{
				required:true,
				digits:true,
				range:[0,999]
			}
		},
		messages:{
			title:{
				required:"请输入模板名称"
			},
			serialNo:{
				required:"请填写推荐排序",
				digits:"请填写0-999之间的整数",
				range:"请填写0-999之间的整数"
			}
		}
	}, save);
	
});

/**
 * 保存信息
 */
function save() {
	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "saasTemplate/manage/add" + suffix;
	} else {// 修改操作
		url = "saasTemplate/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	var templateTags = $("#templateTags").val();
	if(templateTags){
		$("#templateTagVals").val(templateTags.join(','));
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
				if (data.success) {
					$('#prompt').hide();
					
					var url = contextPath +'/saasTemplate/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		showWarningWindow('warning', "没做任何修改！");
	}
}



/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	
	var coverImage=$("#coverImage").val();
	if(coverImage==null || coverImage ==""){
		showWarningWindow("warning","请上传缩略图!",9999);
		result=false;
	}
	
	
	return result;
}


/**
 * 模板标签初始化
 */
function templateTagsInit(){
	$("#templateTags").multipleChosen({
		hideValue : "id",
		showValue : "name",
		url : "saasTag/manage/getTagsByType.jhtml",
		filterVal:"1",//1:模板标签  2:主播标签 3:名嘴标签
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%"
	});
}

/**
 * 绑定添加模板页面事件
 */
$("#addPageBtn").on("click", function() {
	var id=$("#id").val();
	if(id==null || id ==''){
		showWarningWindow("warning","请先保存基本信息!",9999);
		return ;
	}
	addSaasPage(id);
});

/**
 * 跳转到添加模板页面
 */
function addSaasPage(templateId) {
	var url = contextPath + '/saasPage/manage/add/init.jhtml';
	var params='';
	if(templateId){
		params='?templateId='+templateId;
	}
	location.href = url+params;
}

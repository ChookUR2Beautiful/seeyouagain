var formId = "editForm";
var ISTYPE;
var jsonTextInit;
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加H5活动</span>','add',false);
	}else{
		inserTitle(' > <span>编辑H5活动</span>','edit',false);
	}
	
	vstarDictIdInit();
	
	// 封面图片
	$("#coverImgDiv").uploadImg({
		urlId : "coverImg",
		showImg : $('#coverImg').val()
	});
	
	//分享图标
	$("#shareImgDiv").uploadImg({
		urlId : "shareImg",
		showImg : $('#shareImg').val()
	});
	
	//表单初始化数据
	jsonTextInit();
	
	//表单校验
	validate(formId, {
		rules : {
			title : {
				required : true,
				maxlength: 200
			},
			viceTitle : {
				required : true,
				maxlength: 200
			},
			shareTitle:{
				required:true,
				maxlength: 200
			},
			shareDescription:{
				required:true,
				maxlength: 200
			},
			activityUrl:{
				required:true,
				maxlength: 200
			},
			sortVal:{
				required:true,
				digits:true,
				range:[0,999]   
			}
		},
		messages:{
			title:{
				required:"请输入教学标题",
				maxlength:"最多填写200个字符"
			},
			viceTitle : {
				required:"请输入副标题",
				maxlength:"最多填写200个字符"
			},
			shareTitle:{
				required:"请输入分享标题",
				maxlength:"最多填写200个字符"
			},
			shareDescription:{
				required:"请输入分享描述",
				maxlength:"最多填写200个字符"
			},
			activityUrl:{
				required:"请输入活动链接",
				maxlength:"最多填写200个字符"
			}
		}
	}, save);
	
});

/**
 * 保存物料规格信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = ISTYPE == "add" ? true:false;
	if (isAdd) {// 添加操作
		url = "VstarContent/manage/add" + suffix;
	} else {// 修改操作
		url = "VstarContent/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	
	var valiImg = valiImgData("#"+formId,jsonFromt($("#" + formId).serializeArray()));
	if(valiImg){
		return false;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
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
				if (data.success) {
					var url = contextPath +'/VstarContent/manage/init.jhtml';
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
		showWarningWindow("warning", "没做任何修改！");
	}
}


/**
 * 表单数据初始化
 */
function jsonTextInit(){
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
}

/**
 * 初始化教学分类
 */
function vstarDictIdInit(){
	$("#vstarDictId").chosenObject({
		hideValue : "id",
		showValue : "name",
		url : "VstarContent/manage/vstarDictIdInit.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}


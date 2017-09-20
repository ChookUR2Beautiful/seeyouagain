var formId = "editForm";
var ISTYPE;
var jsonTextInit;
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加邀请分享信息</span>','add',false);
	}else{
		inserTitle(' > <span>编辑邀请分享信息</span>','edit',false);
	}
	
	pageTypeInit();
	
	// 分类图片
	$("#picUrlImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	
	//表单初始化数据
	jsonTextInit();
	
	//表单校验
	validate(formId, {
		rules : {
			title : {
				required : true,
				maxlength: 255
			},
			content : {
				required : true,
				maxlength: 255
			},
			position:{
				required:true
			},
			pageType:{
				required:true
			},
			pageContent:{
				required:true
			},
			shareLink:{
				required:true
			},
			appType:{
				required:true
			}
		},
		messages:{
			title:{
				required:"请输入分享标题",
				maxlength:"最多填写255个字符"
			},
			content : {
				required:"请输入分享内容",
				maxlength:"最多填写255个字符"
			},
			position:{
				required:"选择使用位置"
			},
			pageType:{
				required:"选择页面类型"
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
		url = "liveShare/manage/add" + suffix;
	} else {// 修改操作
		url = "liveShare/manage/update" + suffix;
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
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/liveShare/manage/init.jhtml';
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
 * 页面类型改变触发,分享页面类型：001 下载页， 002 已有页面，3 外部链接
 */
$("#pageType").change(function() {
	pageTypeInit();
});

function pageTypeInit(){
	var pageType=$("#pageType").val();
	if(pageType=='001'){
		$("#pageContentTr").css("display","none");
		$("#pageContent").val('');
		$("#shareLinkTr").css("display","none");
		$("#shareLink").val('');
	}else if(pageType=='002'){
		$("#pageContentTr").css("display","table-row");
		$("#shareLinkTr").css("display","none");
		$("#shareLink").val('');
	}else if(pageType=='003'){
		$("#pageContentTr").css("display","none");
		$("#pageContent").val('');
		$("#shareLinkTr").css("display","table-row");
	}
}


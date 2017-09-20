var formId = "editForm";
var ISTYPE;
var jsonTextInit;
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加商品信息</span>','add',false);
	}else{
		inserTitle(' > <span>编辑商品信息</span>','edit',false);
	}
	
	
	//关联规格初始化
	attrsInit();
	
	
	//关联标签初始化
	tagsInit();
	
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
			name : {
				required : true
			},
			orderVal : {
				required : true,
				digits:true,
				range:[1,99]
			}
		},
		messages:{
			name:{
				required:"请输入分类",
			},
			orderVal : {
				required : "请输入排序号",
				digits:"请输入1至99之间的整数",
				range:"请输入1至99之间的整数"
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
		url = "materialCategory/manage/add" + suffix;
	} else {// 修改操作
		url = "materialCategory/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var attrs=$("#attrsMultiple").val();
	if(attrs){
		attrs=attrs.join();
		$("#attrs").val(attrs);
	}
	
	var tags=$("#tagsMultiple").val();
	if(tags){
		tags=tags.join();
		$("#tags").val(tags);
	}
	
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
					var url = contextPath +'/materialCategory/manage/init.jhtml';
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
 * 关联规格初始化
 */
function attrsInit(){
	$("#attrsMultiple").multipleChosen({
		hideValue : "id",
		showValue : "name",
		url : "materialAttr/manage/getAttrs.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%"
	});
}


/**
 * 关联标签初始化
 */
function tagsInit(){
	$("#tagsMultiple").multipleChosen({
		hideValue : "id",
		showValue : "name",
		url : "materialTag/manage/getTags.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%"
	});
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


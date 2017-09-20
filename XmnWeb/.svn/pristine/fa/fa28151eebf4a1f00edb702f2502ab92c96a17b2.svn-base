var formId = "categoryAttrForm";
var ISTYPE;
var jsonTextInit;
$(document).ready(function() {
	
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="materialAttr/manage/add/init.jhtml? isType=add" target="right">添加规格信息</a>','addMaterialAttr',false);
	}else{
		inserTitle(' > <span><a href="materialAttr/manage/update/init.jhtml  target="right">编辑规格信息</a>','editMaterialAttr',false);
	}
	
	
	// 定制属性
	$("input[name='isCustomize']").change(function() {
		isMultipleInit();
	});
	
	//定制属性多选初始化
	isMultipleInit();
	
	//规格细项初始化
	attrValIdsInit();
	
	//表单初始化数据
	jsonTextInit();
	
	//初始化已存在规格细项ID
	initAttrValIdsExisting();
	
	//关联分类初始化
	initCategoryIds();
	
	//表单校验
	validate(formId, {
		rules : {
			giftName : {
				required : true
			}
		},
		message:{
			giftExperience:{
				required:"请输入获得经验",
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
	var isAdd = ISTYPE=='add' ? true : false;
	if (isAdd) {// 添加操作
		url = "materialAttr/manage/add" + suffix;
	} else {// 修改操作
		url = "materialAttr/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	//设置规格细项键值对信息
	setAttrVals();
	
	//设置关联分类键值对信息
	setCategoryVals();
	
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
					var url = contextPath +'/materialAttr/manage/init.jhtml';
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
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 设置规格细项键值对信息
 */
function setAttrVals(){
	var attrValIds=$("#attrValIds").val();
	if(attrValIds){
		var attrVals=attrValIds.join();
		$("#attrVals").val(attrVals);
	}
}

/**
 * 设置关联分类键值对信息
 */
function setCategoryVals(){
	var categorysMultiple= $("#categorysMultiple").val();
	if(categorysMultiple){
		var categoryVals=categorysMultiple.join();
		$("#categoryVals").val(categoryVals);
	}
}

/**
 * 定制属性多选初始化
 */
function isMultipleInit(){
	var isCustomize = $("input[name='isCustomize']:checked").val();
	if (isCustomize == '001') {
		$("#isMultipleTr").css("display","table-row");
		$("#isCustomizableTr").css("display","table-row");
	} else {
		$("#isMultipleTr").css("display","none");
		$("#isCustomizableTr").css("display","none");
		//清空是否多选的选中状态
		$("input[name='isMultiple']:checked ").attr("checked",false);
		$("input[name='isCustomizable']:checked ").attr("checked",false);
	}
}

/**
 * 物料规格细项初始化
 */
function attrValIdsInit(addData){
	//物料规格Id
	var categoryAttrId=$("#id").val();
	
	$("#attrValIds").multipleChosen({
		hideValue : "id",
		showValue : "val",
		url : "materialAttrVal/manage/getCategoryAttrVals.jhtml",
		filterVal : categoryAttrId,//过滤的值 (filterVal=categoryAttrId)
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		addData : addData,//多选下拉框新添加的数据项(isMultiple为true时生效)
		width:"100%"
	});
}

/**
 * 初始化分类
 */
function initCategoryIds(){
	$("#categorysMultiple").multipleChosen({
		hideValue : "id",
		showValue : "name",
		url : "materialCategory/manage/getCategorys.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%"
	});
}

/**
 * 首次加载，初始化已存在的规格细项ID
 */
function initAttrValIdsExisting(){
	var attrValIds=$("#attrValIds").val();
	$("#attrValIdsExisting").val(attrValIds);
}

/**
 * 规格细项修改触发，已被商品使用的规格细项不可删除
 */
$('#attrValIds').on('change', function(){
    // 用户改变了选择，快快处理
	var attrValIdsExisting=$("#attrValIdsExisting").val();
	var attrValIds = $("#attrValIds").val();
	if(attrValIdsExisting !="" && attrValIdsExisting != undefined && attrValIds !="" && attrValIds != undefined){
		var attrValArray1 = attrValIdsExisting.split(',');//数据库已保存的规格细项
		var attrValArray2 = attrValIds;//表单待保存的规格细项
		for(var i=0;i<attrValArray1.length;i++){
			var isDeleted=true;//数据库保存的规格细项被删除
			for(var j=0;j<attrValArray2.length;j++){
				console.info('i= '+i +'; j= '+ j + '; attrValArray1[i] = ' +attrValArray1[i] +'; attrValArray2[j] = ' +attrValArray2[j]);
				if(attrValArray1[i]==attrValArray2[j]){
					isDeleted=false;
					break;
				}
			}
			
			if(isDeleted){
				showSmReslutWindow(false, "已被商品使用，不可删除。");
				attrValIdsInit();
				$('#attrValIds').trigger('chosen:updated');
				return ;
			}
		}
	}
	
});

/**
 * 表单数据初始化
 */
function jsonTextInit(){
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
}


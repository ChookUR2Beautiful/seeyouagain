var formId = "saasCategoryForm";
var ISTYPE;
var jsonTextInit;
$(document).ready(function() {
	
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span><a href="saasTag/manage/add/init.jhtml? isType=add" target="right">添加规格信息</a>','addMaterialAttr',false);
	}else{
		inserTitle(' > <span><a href="saasTag/manage/update/init.jhtml  target="right">编辑规格信息</a>','editsaasTag',false);
	}
	
	//标签选项初始化
	tagIdsInit();
	
	//表单初始化数据
	jsonTextInit();
	
	//初始化已存在规格细项ID
	initAttrValIdsExisting();
	
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
		url = "saasTag/manage/add" + suffix;
	} else {// 修改操作
		url = "saasTag/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	//设置标签细项键值对信息
	setTagVals();
	
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
					var url = contextPath +'/saasTag/manage/init.jhtml';
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
 * 设置标签细项键值对信息
 */
function setTagVals(){
	var tagIds=$("#tagIds").val();
	if(tagIds){
		var tagVals=tagIds.join();
		$("#tagVals").val(tagVals);
	}
}


/**
 * 标签选项初始化
 */
function tagIdsInit(addData){
	//SaaS标签Id
	var categoryId=$("#id").val();
	
	$("#tagIds").multipleChosen({
		hideValue : "id",
		showValue : "name",
		url : "saasTag/manage/getSaasTags.jhtml",
		filterVal : categoryId,//过滤的值 (filterVal=categoryId)
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		addData : addData,//多选下拉框新添加的数据项(isMultiple为true时生效)
		width:"100%"
	});
}


/**
 * 首次加载，初始化已存在的规格细项ID
 */
function initAttrValIdsExisting(){
	var attrValIds=$("#tagIds").val();
	$("#tagIdsExisting").val(attrValIds);
}

/**
 * 规格细项修改触发，已被商品使用的规格细项不可删除
 */
$('#tagIds').on('change', function(){
    // 用户改变了选择，快快处理
	var attrValIdsExisting=$("#tagIdsExisting").val();
	var attrValIds = $("#tagIds").val();
	if(attrValIdsExisting !="" && attrValIdsExisting != undefined && attrValIds !="" && attrValIds != undefined){
		var attrValArray1 = attrValIdsExisting.split(',');//数据库已保存的规格细项
		var attrValArray2 = attrValIds;//表单待保存的规格细项
		for(var i=0;i<attrValArray1.length;i++){
			var isDeleted=true;//数据库保存的规格细项被删除
			for(var j=0;j<attrValArray2.length;j++){
//				console.info('i= '+i +'; j= '+ j + '; attrValArray1[i] = ' +attrValArray1[i] +'; attrValArray2[j] = ' +attrValArray2[j]);
				if(attrValArray1[i]==attrValArray2[j]){
					isDeleted=false;
					break;
				}
			}
			
			if(isDeleted){
				showSmReslutWindow(false, "已被使用，不可删除。");
				//TODO 删除校验
				console.log('删除数据库已保存选项:'+attrValArray1[i]);
				tagIdsInit();
				$('#tagIds').trigger('chosen:updated');
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


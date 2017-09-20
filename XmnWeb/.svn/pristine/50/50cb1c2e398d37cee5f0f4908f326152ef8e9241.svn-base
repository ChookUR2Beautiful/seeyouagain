var formId = "editForm";
var ISTYPE;
var jsonTextInit;
$(document).ready(function() {
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加商品信息</span>','materialEdit',false);
	}else{
		inserTitle(' > <span>编辑商品信息</span>','materialEdit',false);
		tagsInit();
	}
	
	//关联分类初始化
	categoryInit();
	
	//物料规格初始化
	materialAttrInit();
	
	//绑定供应商初始化
	supplierInit();
	
	
	//主图
	$("#picUrlImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	
	//banner图
	bannerInit();
	
	//清除ckEditor实例
	if(CKEDITOR.instances['ckeditor_standard']){
        CKEDITOR.instances['ckeditor_standard'].destroy(true);
	}
	
    $("textarea#ckeditor_standard").ckeditor({
    	
    });
    
	
    //表单初始化数据
	jsonTextInit();
	
	//表单校验
	validate(formId, {
		rules : {
			categoryId : {
				required : true
			},
			title:{
				required:true,
				maxlength:20
			},
			remark:{
				required:true,
				maxlength:40
			},
			finishtime:{
				required : true,
				range:[1,9]
			},
			basePrice:{
				required : true,
				number:true,
				doublearea:[10,2]
			},
			salePrice : {
				required : true,
				number:true,
				doublearea:[10,2]
			}
		},
		messages:{
			categoryId:{
				required:"请输入分类",
			},
			title:{
				required:"请输入商品名称",
				maxlength:"商品名称最多20个字符"
			},
			remark:{
				required:"请输入商品描述",
				maxlength:"商品描述最多40个字符"
			},
			finishtime:{
				required :"请输入完成时间(天)",
				range:"请输入1至9之间的整数"
			},
			basePrice:{
				required : "请输入售价",
				number:"请输入有效金额",
				doublearea:"最多保留两位小数"
			},
			salePrice : {
				required : "请输入售价",
				number:"请输入有效金额",
				doublearea:"最多保留两位小数"
			}
		}
	}, save);
	
});

/**
 * 保存物料规格信息
 */
function save() {
//	debugger;
	/*var jsonData=jsonFromt($('#' + formId).serializeArray());
	console.log(jsonData);*/
	var url;
	var suffix = ".jhtml";
	var isAdd = ISTYPE == "add" ? true:false;
	if (isAdd) {// 添加操作
		url = "materials/manage/add" + suffix;
	} else {// 修改操作
		url = "materials/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	//组装商品规格信息
	setMaterialAttrVals();
	
	
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
					var url = contextPath +'/materials/manage/init.jhtml';
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
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	var categoryId=$("#categoryId").val()||$("#categoryId").attr("initValue");
	if(categoryId == null || categoryId==""){
		showWarningWindow("warning","请选择商品分类!",9999);
		rsult=false;
		return ;
	}
	
	var tagId=$("#tagId").val()||$("#tagId").attr("initValue");
	if(tagId == null || tagId==""){
		showWarningWindow("warning","请选择标签!",9999);
		rsult=false;
		return ;
	}
	
	var supplierId=$("#supplierId").val()||$("#supplierId").attr("initValue");
	if(supplierId == null || supplierId==""){
		showWarningWindow("warning","请绑定供应商!",9999);
		rsult=false;
		return ;
	}
	return result;
}

/**
 * 关联分类初始化
 */
function categoryInit(){
	$("#categoryId").chosenObject({
		hideValue : "id",
		showValue : "name",
		url : "materialCategory/manage/getCategorys.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
}


/**
 * 商品物料规格初始化
 */
function materialAttrInit(){
//	debugger;
	if(ISTYPE=='add'){
		return ;
	}
	var materialId =  $("#id").val();
	$.ajax({
		type: "POST",
		url : "materials/manage/getShoppingMaterialAttrs.jhtml?t=new Date()",
		dataType : "json",
		data: {"materialId":materialId},
		success : function(data){
				var content='';
				//加载规格列表
				 $.each(data.data, function (n, obj) { 
//					 console.log(obj);
					 content +='<div class="form-group compile-norms-options">'
			                 + '   <div class="checkbox col-lg-2 col-xs-3 reset-form-name" data-norms-index= "0">'
				             + '        <label>'
				             + '            <input type="checkbox" name="freight-name" data-value="' +obj.name +' "  data-id="'+  obj.categoryAttrId  +'" value="'+ obj.categoryAttrId +'_'+ obj.name +'" checked="checked">' 
				             + 						obj.name	
				             + '        </label>'
				             + '    </div>'
				             + '    <div class="col-lg-10 col-xs-9">'
				             + '        <select  data-placeholder="请选择规格细项" class="chosen-select form-control"  id="attrValId'+ obj.categoryAttrId +'"  initValue="'+ obj.attrValIds +'" tabindex="-1"> '
				             +'         </select>'  
				             +'      </div>' 
				             +'</div>';
					 
					 
		          });  
		        $("#relatedAttrDiv").html(content);
		        
		        //加载规格细项数据
		        relationAttrInit(data.data,false);
		}
	});
}


/**
 * 商品物料关联规格组件初始化(加载规格细项数据)
 */
function relationAttrInit(data,isChanged){
	 if(data){
		 $.each(data, function (n, obj) {
			 var categoryAttrId='';
			 if(isChanged){
				 categoryAttrId=obj.materialAttrId;
			 }else{
				 categoryAttrId=obj.categoryAttrId;
			 }
				
			$("#attrValId"+ categoryAttrId).multipleChosen({
				hideValue : "id",
				showValue : "val",
				url : "materialAttrVal/manage/getCategoryAttrVals.jhtml",
				filterVal : categoryAttrId,//过滤的值 (filterVal=categoryAttrId)
				isChosen:true,//是否支持模糊查询
				isCode:false,//是否显示编号
				isHistorical:false,//是否使用历史已加载数据
				isMultiple : true,//是否支持多选
				addData : null,//多选下拉框新添加的数据项(isMultiple为true时生效)
				width:"100%"
			});
			
			var triggerId="#attrValId"+ categoryAttrId;
			
			attrValIdChangetrigger(triggerId);
			
		 });
	 }
	 
}

/**
 * 注册规格细项change事件
 */
function attrValIdChangetrigger(triggerId){
	var $this=$(triggerId);
	$(triggerId).on('change', function(){
		var checkState = $this.parents(".compile-norms-options").find("input[name='freight-name']:checked");
	    if($(checkState).is(":checked")){
	        initNormsFn();
	    }
	});
}
/**
 * 选择商家分类后,带出该分类下的所有规格
 */
$('body').on("click",'#categoryId_chosen .chosen-results li',function(){
//	debugger;
	categoryChange();
	$("#tagId").attr('initValue','');
	tagsInit();
	$("#tagId").trigger('chosen:updated');
});

function categoryChange(){
//	debugger;
	var categoryId =  $("#categoryId").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "materialAttr/manage/getRelationAttrs.jhtml?t=new Date()",
		dataType : "json",
		data: {"categoryId":categoryId},
		success : function(data){
				var content='';
				//加载规格列表
				 $.each(data.data, function (n, obj) { 
					 content +='<div class="form-group compile-norms-options">'
			                 + '   <div class="checkbox col-lg-2 col-xs-3 reset-form-name" data-norms-index= "0">'
				             + '        <label>'
				             + '            <input type="checkbox" name="freight-name" data-value="'+ obj.materialAttrVal +'" data-id="'+ obj.materialAttrId +'" value="'+ obj.materialAttrId +'_' + obj.materialAttrVal +'" checked="checked">' 
				             + 						obj.materialAttrVal	
				             + '        </label>'
				             + '    </div>'
				             + '    <div class="col-lg-10 col-xs-9">'
				             + '        <select  data-placeholder="请选择规格细项" class="chosen-select form-control"  id="attrValId'+ obj.materialAttrId +'" name="" initValue="" tabindex="-1"> '
				             +'         </select>'  
				             +'     </div>' 
				             +'</div>';
					 
		          });  
		        $("#relatedAttrDiv").html(content);
		        
		        //重新初始化关联规格组件
		        relationAttrInit(data.data,true);
		}
	});
}

/**
 * 关联标签初始化
 */
function tagsInit(){
//	debugger;
	var categoryId=$("#categoryId").val()||$("#categoryId").attr("initValue");
	$("#tagId").chosenObject({
		hideValue : "materialTagId",
		showValue : "materialTagVal",
		url : "materialTag/manage/getRelationTags.jhtml",
		filterVal:categoryId,
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
}

/**
 * 绑定供应商初始化
 */
function supplierInit(){
	$("#supplierId").chosenObject({
		hideValue : "supplierId",
		showValue : "name",
		url : "supplier/manager/getDesignerSuppliers.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
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

/**
 * banner图初始化
 */
function bannerInit(){
	for(var i=0;i<6;i++){
		$("#picUrlImg"+i).uploadImg({
			urlId : "picUrl"+i,
			showImg : $('#picUrl'+i).val()
		});
	}
}

/**
 * 2位浮点数校验
 */
$.validator.addMethod("doublearea",function(value,element,params){
		 var len = value.length;
		 if(len>12){
			 return false;
		 }
		 if( value >= 1000000000 || value < 0){
			 return false;
		 }
		 var indexOf = value.indexOf(".");
		 if(indexOf>0){
			 var numStr = value.substr(indexOf+1);
			 return !(numStr.length > 2);
		 }
		 return true;
		 
	 },"请填写数值,最大值为999999999.99");

/**
 * 组装商品规格信息
 */
function setMaterialAttrVals(){
	var materialAttrs='';
	var materialAttrId='';//规格ID
	var materialAttrVals='';//指定规格的规格细项
	$("input[name='freight-name']:checked").each(function(){
		var temp=$(this).val();
		materialAttrId=temp.split('_')[0];
		if($("#attrValId"+materialAttrId).val()){
			materialAttrVals=$("#attrValId"+materialAttrId).val().join();
		}
		materialAttrs+=temp+':'+materialAttrVals+';';
	});
	$("#materialAttrVals").val(materialAttrs);
}

/**
 * 绑定添加模板事件
 */
$("#addMaterialTemplate").on("click", function() {
	var id=$("#id").val();
	if(id==null || id ==''){
		showWarningWindow("warning","请先保存商品基本信息!",9999);
		return ;
	}
	addMaterialTemplate(id);
});

/**
 * 跳转到添加模板页面
 */
function addMaterialTemplate(materialId) {
	var url = contextPath + '/materialTemplate/manage/add/init.jhtml';
	var params='';
	if(materialId){
		params='?materialId='+materialId;
	}
	location.href = url+params;
}

/***
 *  绑定商品规格表格【删除】行事件
 */
$(document).on('click','.table-btn-delete',function(){
     $(this).parents("tr").remove();
 });

//模态框
$(document).on('click','.modal-btn-links',function(){
    var imgUrl = $(this).attr("data-imglinks");
    $("#myModal .modal-content-wrap img").attr("src",imgUrl);
    $('#myModal').modal('show');
});

$('#myModal').on('hidden.bs.modal', function (e) {
    $(this).find(".modal-content-wrap img").attr("src",'');
});


/**
 * 绑定规格细项change事件(此方法已废弃)
 */
$('select.chosen-select').on('change', function(){
    debugger;
    var checkState = $(this).parents(".compile-norms-options").find("input[name='freight-name']:checked");
    if($(checkState).is(":checked")){
        initNormsFn();
    }
});

/**
 * 绑定物料规格click事件
 */
$(document).on('click','.compile-norms-options input[name="freight-name"]',function(){
    initNormsFn();
});


/**
 * 初始化规格表格
 * @returns {Boolean}
 */
function initNormsFn(){
    $(".compile-norms-table-list").empty();
    var compileTitleArray=[];
    var compileValueArray = [];
    $(".compile-norms-module .compile-norms-options").each(function(){
    	
        var checkState =  $(this).find("input[name='freight-name']:checked");
        if($(checkState).is(":checked")){
            var inputVal =  $(this).find(".chosen-select").val();
            if(inputVal!=""&&inputVal!=null){
                var lableText =  $(this).find(".checkbox label input").attr('data-value');
                var lableIds =$(this).find("input[name='freight-name']").attr("data-id");
                var lableobj ={
                    "attrIds":lableIds,
                    "attrName":lableText
                };
                compileTitleArray.push(lableobj);
                compileValueArray.push(inputVal);
            }

        }
    });
    
    if(compileTitleArray.length==0||compileValueArray.length==0) return false;
    var res = combine(compileValueArray.reverse());
    
    //thead
    var th = "";
    var tableIds = "";
    for(var k=0; k<compileTitleArray.length; k++) {
        th += "<th>"+ compileTitleArray[k].attrName +"</th>";
        tableIds += compileTitleArray[k].attrIds+',';
    }
    th = "<thead>"+th+"<th>规格售价</th>"+"</thead>";
    var attrIds=tableIds.substr(0,tableIds.length-1);

    var str = "";
    var len = res[0].length;
    for (var i=0; i<res.length; i++) {
        var tmp = "";
        var listValue = "";
        var attrVal="";
        var attrVals="";
        for(var j=0; j<len; j++) {
        	attrId=res[i][j].split('_')[0];
        	attrVal=res[i][j].split('_')[1];
            tmp += "<td>"+attrVal+"</td>";
            listValue+=attrVal+',';
        }
        attrVals=listValue.substring(0, listValue.length-1);
        str += "<tr> " 
        	+	"<input type='hidden' name='materialAttrGroupList["+i+"].materialAttrIds' value='"+attrIds+"' /> " 
        	+	"<input type='hidden' name='materialAttrGroupList["+i+"].materialAttrVals' value='"+attrVals+"' /> " 
	        + 		tmp 
	        + 	"<td><input name='materialAttrGroupList["+i+"].amount' type='number' class='form-control' placeholder='金额' value='0'></td>" 
//	        + 	"<td><button type='button' class='btn btn-danger table-btn-delete'>删除</button></td>" 
	        + "</tr>";
    }

    str = "<table class='table table-bordered text-center'>" +th+str+"</table>";
    $(".compile-norms-table-list").empty().append(str);
}

/*递归返回二维数组*/
function combine(arr) {
    /*数组格式*/
//    var arr = [['a','b'],['e','f','g']];
    var r = [];
    (function f(t, a, n) {
        if (n == 0) return r.push(t);
        for (var i = 0; i < a[n-1].length; i++) {
            f(t.concat(a[n-1][i]), a, n - 1);
        }
    })([], arr, arr.length);
    return r;
}

$(function() {
	advertisingValidate();
	// 物料图片
	$("#adbpicUpload").uploadImg({
		urlId : "thumbnail",
		showImg : $("#thumbnail").val()
	});

});

function getWidths() {
	return [ "20%", "23%", "48%" ];
}

/**
 * 表单验证
 */
function advertisingValidate() {
	validate("editMaterialForm", {
		rules : {
			name : {
				required : true
			},
//			sold_quantity : {
//				required : true,
//				digits : true,
//				range : [ 0, 2147483647 ]
//			},
			price : {
				required : true,
				number:true,
				doublearea:[10,2]
			},
			status : {
				required : true
			},
			ismust : {
				required : true
			}
		},
		messages : {
			name : {
				required : "物料名不能为空!"
			},
//			sold_quantity : {
//				required : "销售量不能为空！",
//				digits : "销售量只能为整数!",
//				range : "数值范围1-2147483647!"
//			},
			price : {
				required : "请输入价格!",
				number:"价格必须为数字类型",
				doublearea:"必须为2位小数点的数据"
			},
			status : {
				required : "请选择上下架状态!"
			},
			ismust : {
				required : "请选择是否必须购买!"
			}
		}
	}, formAjax);
}


//带2位小数字点
$.validator.addMethod("doublearea",function(value,element,params){
		var len = value.length;
		 if(len>12){return false;
		 }else if( value >= 1000000000 || value < 0){
			 return false;
		 }else {
			 var indexOf = value.indexOf(".");
			 if(indexOf>0){
				 var numStr = value.substr(indexOf+1);
				 return !(numStr.length > 2);
			 }
			 return true;
		 }
	 },"请填写数值,最大值为999999999.99");


validate("editMaterialForm",validate,formAjax); 


/**
 * Ajax 请求
 */
function formAjax() {
	var data = jsonFromt($('#editMaterialForm').serializeArray());
	var url ;
	if($('#isType')=='add'){
		url ="marketingManagement/material/init/add/save.jhtml";
	}else{
		url ="marketingManagement/material/init/edit/save.jhtml";	
	}
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide')
				
				matetialList.reset();
				matetialList.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		},error : function(data){
			alert(data.msg);
		}
	});
}
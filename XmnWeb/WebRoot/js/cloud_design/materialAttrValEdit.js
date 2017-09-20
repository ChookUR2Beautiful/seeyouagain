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
			attrVals : {
				required : true
			}
		},
		messages:{
			attrVals:{
				required:"请输入规格细项",
			}
		}
	}, attrValSave);
	
});




/**
 * 保存待添加物料规格细项信息
 */
function attrValSave() {
//	debugger;
	var initData=$("#attrVals2Add").val();
	//TODO 规格细项校验
	attrValIdsInit(initData);
	$("#attrValIds").trigger("chosen:updated");
	$('#prompt').hide();
	$('#triggerModal').modal('hide');
	formId="categoryAttrForm";//将formId重置为父页面表单id
}




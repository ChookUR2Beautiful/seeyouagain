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
			tags2Add : {
				required : true
			}
		},
		messages:{
			tags2Add:{
				required:"请输入标签选项",
			}
		}
	}, attrValSave);
	
});




/**
 * 保存待添加物料规格细项信息
 */
function attrValSave() {
//	debugger;
	var initData=$("#tags2Add").val();
	//TODO 规格细项校验
	tagIdsInit(initData);
	$("#tagIds").trigger("chosen:updated");
	$('#prompt').hide();
	$('#triggerModal').modal('hide');
	formId="saasCategoryForm";//将formId重置为父页面表单id
}




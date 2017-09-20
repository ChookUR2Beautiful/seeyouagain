$(function(){
	
	$("#commontId").chosenObject({
		hideValue : "id",
		showValue : "sellername",
		url : "businessman/recommend/commentChoose.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	validate("editFrom",{
		rules : {
			sort : {
				required : true
			}
			
		},
		messages:{
		}
	},save);
	
	function save(){
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		if(!data.typeId){
			showWarningWindow("warning", "请选择商品类型!", 9999);
			return;
		}
		$.ajax({
			type : 'post',
			url : "businessman/recommend/addMustbuy.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				loadMustbuy();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
	
	
});
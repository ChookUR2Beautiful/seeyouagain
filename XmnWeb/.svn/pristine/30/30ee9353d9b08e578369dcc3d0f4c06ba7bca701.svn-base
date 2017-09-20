$(function(){

	validate("editFrom",{
		rules : {
			classifyName : {
				required : true
			},
			tagName : {
				required : true
			}
		},
		messages:{
			
		}
	},save);
	
	function save(){
		var classifyType=$("[name=classifyType]:checked");
		if(!classifyType.length){
			showWarningWindow("warning","请选择标签类型!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : "businessman/classify/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					classifyList.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});
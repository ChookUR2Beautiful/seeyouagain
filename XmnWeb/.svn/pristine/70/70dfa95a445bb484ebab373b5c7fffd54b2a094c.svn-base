$(function(){
	$("#picUrlImg").uploadImg({
		urlId : "logo",
		showImg : $('#logo').val()
	});
	
	validate("editFrom",{
		rules : {
			typeId : {
				required : true
			},
			name : {
				required : true
			},
			remark : {
				required : true
			}
		},
		messages:{
			typeId:{
				required:"请输入分类",
			},
			name:{
				required:"请输入名称",
			},
			remark:{
				required:"请输入品牌说明",
			}
		}
	},save);
	
	function save(){
		if(!$("#logo").val()){
			showWarningWindow("warning","请上传品牌图片!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : basePath+"/fresh/brand/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					brandList.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});
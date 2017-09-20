$(function(){
	$("#picUrlImg").uploadImg({
		urlId : "showSmallImg",
		showImg : $('#showSmallImg').val()
	});
	
	validate("editFrom",{
		rules : {
			fid : {
				required : true
			},
			name : {
				required : true
			},
			showSmallImg : {
				required : true
			},
			sort : {
				required : true,
				digits:true,
				range:[1,99]
			}
		},
		messages:{	
			fid:{
				required:"请输入分类",
			},
			name:{
				required:"请输入名称",
			},
			showSmallImg:{
				required:"请选择图片",
			},
			sort : {
				required : "请输入排序号",
				digits:"请输入1至99之间的整数",
				range:"请输入1至99之间的整数"
			}
		}
	},save);
	
	function save(){
		if(!$("#showSmallImg").val()){
			showWarningWindow("warning","请上传标签图片!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : basePath+"/fresh/category/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					console.log(category);
					category.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});
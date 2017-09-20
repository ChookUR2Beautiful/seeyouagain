$(function(){
	$("#picUrlImg").uploadImg({
		urlId : "showSmallImg",
		showImg : $('#showSmallImg').val()
	});
	
	validate("editFrom",{
		rules : {
			word : {
				required : true
			},
			sort : {
				required : true,
				digits:true,
				range:[1,99]
			}
		},
		messages:{
			word:{
				required:"请输入关键字",
			},
			sort : {
				required : "请输入排序号",
				digits:"请输入1至99之间的整数",
				range:"请输入1至99之间的整数"
			}
		}
	},save);
	
	function save(){
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : basePath+"/fresh/word/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function() {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					wordList.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});
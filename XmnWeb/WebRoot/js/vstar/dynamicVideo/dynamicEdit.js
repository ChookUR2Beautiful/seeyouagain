$(function(){
	var chooseUrl;
	
	$("#picUrlImg").uploadImg({
		urlId : "imageUrl",
		showImg : $('#imageUrl').val()
	});
	
	$("#activityLd").cityLd({
		isChosen : false 
	});
	
	validate("editFrom",{
		rules : {
			title : {
				required : true
			},
			jumpUrl : {
				required : true
			},
			sort : {
				required : true
			}
			
		},
		messages:{
		}
	},save);
	
	function save(){
		if(!$("#imageUrl").val()){
			showWarningWindow("warning","请上传等级图片!",9999);
			return;
		}
		if(!$("[name='location']:checked").length){
			showWarningWindow("warning","请选择图片位置!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		if(!data.province||!data.city){
			showWarningWindow("warning","请选择城市!",9999);
			return;
		}
		data.provinceId=data.province;
		data.cityId=data.city;
		
		$.ajax({
			type : 'post',
			url : "dynamicVideo/edit/dynamic.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				loadActivity();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});
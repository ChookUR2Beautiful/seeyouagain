
	

/*$("#videoImgImg").uploadImg({
	urlId : "videoImg",
	showImg : $('#videoImg').val()
});
*/
	
	$("#childId").chosenObject({
		hideValue : "uid",
		showValue : "phone",
		url : "manorMember/manage/usrChain/choose.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
	
	
	
	$("#childId").on('change',function(){
		$.post('manorMember/manage/usrChain/activate/getManorState.jhtml',{"uid":$(this).val()},function(data,status){
			if(status=='success'){
				$("#parentName").text(data.state);
				$("#energyName").text(data.activate);
			}
			
		});
	});
	

	validate("videoFrom",{
		rules : {
			
		},
		messages:{
			
		}
	},save);
		
	
	function save(){
		
		if(!$("#childId").val()){
			showWarningWindow("warning", "请选择修改用户!", 9999);
			return
		}
		if($("#energyName").text()<360){
			showWarningWindow("warning", "该用能量不足!", 9999);
			return
		}
		var paramData = {
				"uid":$("#childId").val(),
		}
		$.ajax({
			type : 'post',
			url : "manorMember/manage/usrChain/activate/activateManor.jhtml",
			data : paramData,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#triggerModal').modal('hide');
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				if (data.success) {
					showSmReslutWindow(data.success, data.msg);
				}else{
			    	showSmReslutWindow(data.success, data.msg);
			    }			
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
		
		
		
	}

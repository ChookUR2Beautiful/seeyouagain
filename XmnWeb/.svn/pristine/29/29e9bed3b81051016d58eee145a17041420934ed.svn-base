
	

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
	
	$("#parentId").chosenObject({
		hideValue : "uid",
		showValue : "phone",
		url : "manorMember/manage/usrChain/parentChoose.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
	
	$("#childId").on('change',function(){
		$.post('manorMember/manage/usrChain/getManorParent.jhtml',{"uid":$(this).val()},function(data,status){
			if(status=='success'){
				if(data){
					$("#parentName").text('有上级');
				}else{
					$("#parentName").text('无上级');
				}
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
		
		if(!($("#childId").val()&&$("#parentId").val())){
			showWarningWindow("warning", "请选择修改用户!", 9999);
			return
		}
		if($("#parentName").text()=='有上级'){
			showWarningWindow("warning", "该用户已有上级!", 9999);
			return
		}
		var paramData = {
				"childId":$("#childId").val(),
				"parentId":$("#parentId").val()
		}
		$.ajax({
			type : 'post',
			url : "manorMember/manage/usrChain/BindingParent.jhtml",
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

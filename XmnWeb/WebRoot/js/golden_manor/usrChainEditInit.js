
	

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
		url : "manorMember/manage/usrChain/edit/choose.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
	
	$("#childId").on('change',function(){
		$.post('manorMember/manage/usrChain/edit/hasChlid.jhtml',{"uid":$(this).val()},function(data,status){
			if(status=='success'){
				if(data){
					$("#parentName").text('不可移动');
				}else{
					$("#parentName").text('可移动');
				}
			}
			
		});
	});
	
	
	$("#parentId").on('change',function(){
		$.post('manorMember/manage/usrChain/edit/getFlowerBranch.jhtml',{"uid":$(this).val()},function(data,status){
			$("#flowerBranchChoose").html("<option>-请选择-</option>");
			if(status=='success'){
				$.each(data,function(i,item){
					var option=$("<option>").val(item.location);
					if(item.childSum>0){
						option.attr("disabled","disabled");
					}
					var showStr="("+(item.childSum>0?'不可移':'可移')+")";
					if(item.location==0){
						option.text('左'+showStr);
					}else if(item.location==1){
						option.text('中'+showStr);
					}else if(item.location==2){
						option.text('右'+showStr);
					}
					option.appendTo("#flowerBranchChoose");
				});
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
		
		if(!($("#childId").val()&&$("#parentId").val()&&$("#flowerBranchChoose").val())){
			showWarningWindow("warning", "请选择修改用户!", 9999);
			return
		}
		if($("#parentName").text()=='不可移动'){
			showWarningWindow("warning", "该用户有下级,不可移动!", 9999);
			return
		}
		var paramData = {
				"childId":$("#childId").val(),
				"parentId":$("#parentId").val(),
				"location":$("#flowerBranchChoose").val()
		}
		$.ajax({
			type : 'post',
			url : "manorMember/manage/usrChain/Edit/BindingManorParent.jhtml",
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

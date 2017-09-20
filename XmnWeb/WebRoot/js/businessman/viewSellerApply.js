$(document).ready(function() {	
	$('.fancybox').fancybox();
	var id = $("#id").val();
//	inserTitle(' > <span><a href="businessman/sellerApply/view/init.jhtml?id='+id+'" target="right">查看商家申请修改信息</a>','viewsellerApplyInfo',false);
	
	inserTitle(' > 查看商家申请修改信息','viewsellerApplyInfo',false);
	
	//区域
	$("#ld").areaLd({
		isChosen : true,
		isDisabled : true
	});
	//商圈
	$("#zoneid").chosenObject({
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true,
		defaultValue:"-- 请选择商圈 --"
	});
	
	
	//区域
	$("#areaSelectApply").areaLd({
		isChosen : true,
		isDisabled : true
	});
	//商圈
	$("#zoneApplyid").chosenObject({
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true,
		defaultValue:"-- 请选择商圈 --"
	});
	
		
	/**
	 * 根据状态显示审核通过或者不通过按钮
	 */
	//审核中
    if($("#status").val()==0){
    	 $("#auditYes").show();
		 $("#auditNo").show();
	 }
     
    
    
    
    /**
     * 审批通过
     */	
	 $("#auditYes").click(function(){
		 pass(id,1);
	 });
		
		
	/**
	 * 通过
	 */
	function pass(id,status) {
		showSmConfirmWindow(function() {
			$.ajax({
				type : 'post',
				url : 'businessman/sellerApply/updateState.jhtml',
				data : {"ids":id,"status":status},
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					showSmReslutWindow(data.success, data.msg);
					 var url = contextPath + '/businessman/sellerApply/init.jhtml';
						setTimeout(function(){
							location.href =url;
						}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
				}
			});
		},"你确定执行通过？");
	}
	
	/**
	 * 批量不通过
	 */
	$('#auditNo').click(function(){
		var ids = $("#id").val();
		if(!ids){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var modalTrigger = new ModalTrigger({
			type : 'ajax',
			url : 'businessman/sellerApply/updateState/init.jhtml?ids=' + ids +'&type=view',
			toggle : 'modal'
		});
		modalTrigger.show();
	});
	
	/**
	 * 添加返回按钮
	 */
	 $("#backId").click(function(){
		 var url = contextPath + '/businessman/sellerApply/init.jhtml';
		 location.href =url;
	 });
	
});

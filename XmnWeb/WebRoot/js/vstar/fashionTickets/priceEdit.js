$(function(){
	var supportSelectSeats= $("input[name='supportSelectSeats']:checked").val();
	
		$("#sid").chosenObject({
			hideValue : "id",
			showValue : "seatName",
			url : "fashionTickets/getSeatsChooseByIds.jhtml?ids="+seats.toString(),
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%"
		});
	
	
	validate("priceFrom",{
		rules : {
			buyNum : {
				required : true
			},
			price : {
				required : true
			}
		},
		messages:{
			
		}
	},save);
	
	function save(){
		if($("#sid").length){
			if(!$("#sid").val()){
				showWarningWindow("warning","请选择座位！");
	 			return;
			}
		}
		var data = $('#priceFrom').serializeArray();
		data = jsonFromt(data);
		$.ajax({
			type : 'post',
			url : "fashionTickets/add/price/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					loadPrice();
			    }else{
			    	showSmReslutWindow(data.success, data.msg);
			    }			
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});
$(function(){
	$("#userId").chosenObject({
		hideValue : "uid",
		showValue : "phone",
		url : "burs/getUserChoose.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	$("#addInput").on("click",function(){
		var chooseInput=$("#chooseInput").clone();
		chooseInput.attr("id","");
		var seatSelect=chooseInput.find("[name='seatSelect']");
		seatSelect.chosenObject({
			hideValue : "id",
			showValue : "title",
			url : "fashionTickets/init/getSellIngTickets.jhtml",
			isChosen:true,//是否支持模糊查询
			isCode:true,//是否显示编号
			isHistorical:false,//是否使用历史已加载数据
			width:"100%"
		}).on("change",function(){
			 $.post("fashionTickets/init/getSellingByTicketsId.jhtml",{id: $(this).val()},function(data,status){
				 var seatSelect2=chooseInput.find("[name='seatSelect_2']");
				 seatSelect2.html('');
				 $.each(data,function(i,item){
					 $("<option>").val(item.id).text(item.seatName+"大桌"+item.zoneNo+"号"+item.seatNo+"位").appendTo(seatSelect2);
				 });
			 })
		})
		chooseInput.find("[name='deleteBut']").on("click",function(){
			chooseInput.remove();
		});
		chooseInput.appendTo($("#orderInputs")).show();
	});
	
	$("#submit").on("click",function(){
		var userId=$("#userId").val();
		if(!userId){
			showWarningWindow("warning","请选择用户!",9999);
			return;
		}
		var orders=new Array();
		$.each($("[name='seatSelect_2']"),function(i,item){
			 var op=$(item).val();
			 if(op){
				 orders.push(op);
			 }
		});
		if(orders.length==0){
			showWarningWindow("warning","请选择门票!",9999);
			return;
		}	
		$.ajax({
			type : 'post',
			url : "ticketsOrder/add/give/order.jhtml",
			data : {"userId":userId,"detailIds":orders.toString()},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				if(data.success){
					$('#triggerModal').modal('hide');
					orderList.reload();
					showSmReslutWindow(data.success, data.msg);
				}else{
					showSmReslutWindow(data.success, data.msg);
				}
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
		
	});
	
	
});


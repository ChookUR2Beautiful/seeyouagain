$(function(){
	
	validate("seatFrom",{
		rules : {
			seatName : {
				required : true
			},
			seats : {
				required : true
			},
			zoneRangeMin : {
				required : true
			},
			num : {
				required : true
			}
		},
		messages:{
			
		}
	},save);
	
	function save(){
		var data = $('#seatFrom').serializeArray();
		data = jsonFromt(data);
		data.seatIds=seats.toString();
		$.ajax({
			type : 'post',
			url : "fashionTickets/add/seat/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if(!$("#fashionTicketSeatId").val()){
						seats.push(data.data.id)
					}
					loadSeat();
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
$("input[name='supportSelectSeats']").trigger("click");
addUrl="fashionTickets/edit.jhtml"
var seatSelect= $("input[name='supportSelectSeats']:checked").val();
$("#totalSeats").attr("readonly","readonly");
$('#saleStartTime').trigger("changeDate");
$('#saleEndTime').trigger("changeDate");
$('#useStartTime').trigger("changeDate");
$('#useEndTime').trigger("changeDate");
$(function(){
	$.post("fashionTickets/init/getSeats.jhtml", {
		id : $("#fashionTicketId").val()
	}, function(data, status) {
		if (status == "success") {
			if(seatSelect==0){
				var d=data[0];
				seats=new Array();
				seats.push(d.id);
				$("#totalSeats").val(d.totalSeats);
			}else{
				$.each(data, function(i, item) {
					seats.push(item.id);
				});
			}
			loadSeat();
			loadPrice();
		}
	});
});
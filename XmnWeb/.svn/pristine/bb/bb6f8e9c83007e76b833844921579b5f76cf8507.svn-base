$.post("ticketsOrder/init/getDetail/list.jhtml",{id:$("#detailId").val()},function(result,status){
	if (result.success) {
		 $("#detailList").html("");
		 var content='';
			//加载统计区间表单数据
		 if(!result.data){
			 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
		 }
		 $.each(result.data,function(i,item){
			 content +="<tr>"
                + "       <td>"+item.seatName+"</td>"  				
                + "       <td>"+item.seatNo+"号"+item.zoneNo+"座"+"</td>"				 	
                + "       <td>"+item.id+"</td>"	 		
                + "       <td>"+item.orderStatusStr+"</td>"  				
                + "</tr>" ;
		 })
	       $("#detailList").html(content);
	 } else {
		 showSmReslutWindow(result.success, result.msg);
	 }
});

/*$.ajax({
		 url : "ticketsOrder/init/getDetail/list.jhtml,
		 type : "post",
		 dataType : "json",
		 data:{id:$("#detailId").val()},
		 success : function(result) {
			 if (result.success) {
				 $("#detailList").html("");
				 var content='';
					//加载统计区间表单数据
				 if(!result.data){
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
				 $.each(result.data,function(i,item){
					 content +="<tr>"
		                 + "       <td>"+item.seatName+"</td>"  				
		                 + "       <td>"+item.seatNo+"号"+item.zoneNo+"座"+"</td>"				 	
		                 + "       <td>"+item.id+"</td>"	 		
		                 + "       <td>"+item.orderStatusStr+"</td>"  				
		                 + "</tr>" ;
				 })
			       $("#detailList").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });

*/
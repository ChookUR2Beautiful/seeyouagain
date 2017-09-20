var modalType;
var modalId;
$(function() {
	loadSalaryFail();
});



function loadSalaryFail(data){
	$.ajax({
		 url : "liveSalary/getFailList.jhtml",
		 type : "post",
		 dataType : "json",
		 data:data,
		 success : function(result) {
			 if (result.success) {
				 $("#specialList").html("");
				 var content='';
					//加载统计区间表单数据
				 if(!result.data){
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
				 $.each(result.data,function(i,item){
					 content +="<tr>"
		                 + "       <td>"+item.name+"("+item.phone+")</td>"  				
		                 + "       <td>"+item.countTime+"</td>"				 	
		                 + "       <td>"+item.msg+"</td>"  				
		                 + "</tr>" ;
				 })
			       $("#salaryFailList").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

$("#submit").on("click",function(){
	$.ajax({
		 url : "liveSalary/salaryAgain.jhtml",
		 type : "post",
		 dataType : "json",
		 beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
		 success : function(result) {
			 $('#prompt').hide();
			 if (result.success) {
				 showSmConfirmWindow(function (){
					 loadSalaryFail();
				 },result.msg);
				 //$('#myModal').attr("style","width: 300px;height: 200px;")
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
});



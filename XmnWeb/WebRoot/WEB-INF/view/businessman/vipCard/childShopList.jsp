<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="main" id="supporedShop" request-init="${requestInit}">
	
 </div>
 <script>
 	var supporedShop = $('#supporedShop');
	var div;
	$(function(){
		var url  = [$(supporedShop).attr("request-init"),".jhtml"].join("");
		div = $(supporedShop).page({
			url : url,
			dataType:"html",
			success : handle,
			pageBtnNum : 10,
			param:{sellerId:'${sellerId}'}
		});
	});
	
	function handle(data){
		var table  =$(supporedShop).find("table.supporedShoptable");
		if(table.length==0){
			$(supporedShop).prepend(data);
		}else{
			$(table).html(data);
		}
	}
 </script>




<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="main" id="areaInbusiness" request-init="${requestInit}">
	
 </div>
 <script>
 	var areaInbusiness = $('#areaInbusiness');
	var div;
	$(function(){
		var url  = [$(areaInbusiness).attr("request-init"),".jhtml"].join("");
		div = $(areaInbusiness).page({
			url : url,
			dataType:"html",
			success : handle,
			pageBtnNum : 10,
			param:{areaId:'${areaId}'}
		});
	});
	
	function handle(data){
		var table  =$(areaInbusiness).find("table.areaInbusinesstable");
		if(table.length==0){
			$(areaInbusiness).prepend(data);
		}else{
			$(table).html(data);
		}
	}
 </script>




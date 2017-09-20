var livePayRecordList;
var initListUrl = "liveRechargeAndRedPacket/manage/count/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
var formId="searchForm";
var modalType;
var modalId;
$(function() {
	inserTitle( ' >  新食尚大赛  > <a href="dynamicVideo/init.jhtml" target="right">大赛动态管理</a>', 'userSpan', true);
	loadVideo();
	loadActivity()

	
   
});


function loadVideo(data){
	$.ajax({
		 url : "dynamicVideo/init/vedioList.jhtml",
		 type : "post",
		 dataType : "json",
		 data:data,
		 success : function(result) {
			 if (result.success) {
				 $("#videoList").html("");
				 var content='';
					//加载统计区间表单数据
				 if(!result.data){
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
				 $.each(result.data,function(i,item){
					 content +="<tr>"
		                 + "       <td>"+item.title+"</td>"  				
		                 + "       <td>"+item.anchorName+"</td>"				 	
		                 + "       <td>"+item.homeSort+"</td>"	 		
		                 + '       <td><a href="javascript:;" onclick="editSort('+item.id+',2,'+item.homeSort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.delete?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',2)" class="data">删除</a></td>':'')			
		                 + "</tr>" ;
				 })
			       $("#videoList").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}



function loadActivity(data){
	$.ajax({
		url : "dynamicVideo/init/dynamicList.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if (result.success) {
				$("#dynamicList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.title+"</td>"  				
						+ "       <td><img style='width:50px;height: 50px;' src='"+imgRoot+item.imageUrl+"' /></td>"				 	
						+ "       <td>"+(item.isAll==1?'全国':item.provinceName+","+item.cityName)+"</td>"	 		
						+ "       <td>"+item.sort+"</td>"  				
						+ "       <td>"+item.locationStr+"</td>"  				
						+ '       <td><a href="javascript:;" onclick="editSort('+item.id+',1,'+item.sort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.delete?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',1)" class="data">删除</a></td>':'') 				
						+ "</tr>" ;
				})
				$("#dynamicList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}


function editSort(id,type,sort){
	modalType=type;
	modalId=id;
	$("#editSort_inp").val(sort);
	$('#editSortModal').modal();
}

$("#editSortSubmit").on("click",function(){
	var sort=$("#editSort_inp").val();
	if(sort<0||sort==""){
		showWarningWindow("warning", "请正确填写排序!", 9999);
		return;
	}
	var url;
	if(modalType==1){
		url="dynamicVideo/edit/dynamic/sort.jhtml";
	}else if(modalType==2){
		url="dynamicVideo/edit/video/sort.jhtml";
	}
	$.ajax({
		 url : url,
		 type : "post",
		 dataType : "json",
		 async: false,
		 data:{"id":modalId,"sort":sort},
		 success : function(data) {
			 if (data.success) {
				 $('#editSortModal').modal('hide');
				 load(modalType);
			 }
		 }
	});
	modalType=null;
	modalId=null;
});

function load(modalType){
	 if(modalType==1){
		 loadActivity();
	 }else if(modalType==2){
		 loadVideo();
	 }
}

function deleteSpecial(id,type){
	var url;
	if(type==1){
		url="dynamicVideo/delete/dynamic.jhtml";
	}else if(type==2){
		url="dynamicVideo/delete/video.jhtml";
	}
	$.ajax({
		 url : url,
		 type : "post",
		 dataType : "json",
		 async: false,
		 data:{"id":id},
		 success : function(data) {
			 if (data.success) {
				load(type);
			 }
		 }
	});
}


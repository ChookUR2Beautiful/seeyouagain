var livePayRecordList;
var initListUrl = "liveRechargeAndRedPacket/manage/count/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
var formId="searchForm";
var modalType;
var modalId;
$(function() {
	inserTitle( ' >  商家管理  > <a href="businessman/recommend.jhtml" target="right">美食首页管理</a>', 'userSpan', true);
	$("#formLd").cityLd({
		isChosen : false
	});
	loadSpecial();
	loadActivity()
	loadTopic();
	loadComment();
	loadMustbuy();
	//loadPackage;();
	var ld = $("#ld").cityLd({
		isChosen : false
	});
	
    $("#special_choose").chosenObject({
		hideValue : "id",
		showValue : "title",
		url : "businessman/recommend/specialChoose.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
    $("#package_choose").chosenObject({
    	hideValue : "id",
    	showValue : "title",
    	url : "businessman/recommend/packageChoose.jhtml",
    	isChosen : true, //是否支持模糊查询
    	isCode : true, //是否显示编号
    	isHistorical : false, //是否使用历史已加载数据
    	width : "100%"
    });
});




$("#specialModalSubmit").on("click",function(){
	var id=$("#special_choose").val();
	if(!id){
		showWarningWindow("warning", "请选择专题!", 9999);
		return;
	}
	var province=$("#specialModal").find("[name=province]").val();
	var city=$("#specialModal").find("[name=city]").val();
	var homeSort=$("#specialModal").find("[name=homeSort]").val();
	var data={
			"id":id,
			"homeSort":homeSort
	}
	if(province&&city){
		data.provinceId=province;
		data.cityId=city;
	}
	$.ajax({
		 url : "businessman/recommend/addSpecial.jhtml",
		 type : "post",
		 dataType : "json",
		 data:data,
		 success : function(result) {
			 if(result.success){
				 loadSpecial();
			 }
		 }
	});
	$('#specialModal').modal('hide');
});
$("#packageModalSubmit").on("click",function(){
	var id=$("#package_choose").val();
	if(!id){
		showWarningWindow("warning", "请选择套餐!", 9999);
		return;
	}
	var homeSort=$("#packageModal").find("[name=homeSort]").val();
	var data={
			"id":id,
			"homeSort":homeSort
	}
	$.ajax({
		url : "businessman/recommend/addPackage.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if(result.success){
				loadPackage();
			}
		}
	});
	$('#packageModal').modal('hide');
});


function loadSpecial(data){
	$.ajax({
		 url : "businessman/recommend/recommendList.jhtml",
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
		                 + "       <td>"+item.title+"</td>"  				
		                 + "       <td>"+(item.topicTypeStr?item.topicTypeStr:'-')+"</td>"				 	
		                 + "       <td>"+(item.isNationwide==1?'全国':item.provinceName+","+item.cityName)+"</td>"	 		
		                 + "       <td>"+item.homeSort+"</td>"  				
		                 + '       <td><a href="javascript:;" onclick="editSort('+item.id+',1,'+item.homeSort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.deleteSpecial?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',1)" class="data">删除</a></td>':'')			
		                 + "</tr>" ;
				 })
			       $("#specialList").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

function loadPackage(data){
	$.ajax({
		url : "businessman/recommend/packageList.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if (result.success) {
				$("#packageList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.title+"</td>"  				
						+ "       <td>"+(item.subName?item.subName:'-')+"</td>"				 	
						+ "       <td>"+(item.isNationwide==1?'全国':item.provinceName+","+item.cityName)+"</td>"	 		
						+ "       <td>"+item.homeSort+"</td>"  				
						+ '       <td><a href="javascript:;" onclick="editSort('+item.id+',2,'+item.homeSort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.deletePackage?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',2)" class="data">删除</a></td>':'') 				
						+ "</tr>" ;
				})
				$("#packageList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}

function loadActivity(data){
	$.ajax({
		url : "businessman/recommend/activityList.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if (result.success) {
				$("#activityList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.title+"</td>"  				
						+ "       <td><img style='width:50px;height: 50px;' src='"+imgRoot+item.imageUrl+"' /></td>"				 	
						+ "       <td>"+(item.isAll==1?'全国':item.provinceName+","+item.cityName)+"</td>"	 		
						+ "       <td>"+item.sort+"</td>"  				
						+ "       <td>"+item.locationStr+"</td>"  				
						+ '       <td><a href="javascript:;" onclick="editSort('+item.id+',3,'+item.sort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.deletePackage?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',3)" class="data">删除</a></td>':'') 				
						+ "</tr>" ;
				})
				$("#activityList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}


function loadTopic(data){
	$.ajax({
		url : "businessman/recommend/topicList.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if (result.success) {
				$("#topicList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.topicName+"</td>"  				
						+ "       <td>"+item.tagIdStr+"</td>"  				
						+ "       <td>"+(item.isAll==1?'全国':item.provinceName+","+item.cityName)+"</td>"	 		
						+ "       <td>"+item.sort+"</td>"  				
						+ '       <td><a href="javascript:;" onclick="editSort('+item.id+',4,'+item.sort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.deletePackage?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',4)" class="data">删除</a></td>':'') 				
						+ "</tr>" ;
				})
				$("#topicList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}


function loadComment(data){
	$.ajax({
		url : "businessman/recommend/commentList.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if (result.success) {
				$("#commentList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.sellername+"</td>"  				
						+ "       <td>"+item.area+"</td>"	 		
						+ "       <td>"+item.sort+"</td>"  				
						+ '       <td><a href="javascript:;" onclick="editSort('+item.id+',5,'+item.sort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.deletePackage?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',5)" class="data">删除</a></td>':'') 				
						+ "</tr>" ;
				})
				$("#commentList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}


function loadMustbuy(data){
	$.ajax({
		url : "businessman/recommend/mustbuyList.jhtml",
		type : "post",
		dataType : "json",
		data:data,
		success : function(result) {
			if (result.success) {
				$("#mustbuyList").html("");
				var content='';
				//加载统计区间表单数据
				$.each(result.data,function(i,item){
					content +="<tr>"
						+ "       <td>"+item.typeStr+"</td>"  				
						+ "       <td>"+item.typeName+"</td>"	 		
						+ "       <td>"+item.sort+"</td>"  				
						+ '       <td><a href="javascript:;" onclick="editSort('+item.id+',6,'+item.sort+')" class="data">修改排序</a>&nbsp;&nbsp;&nbsp;&nbsp;'+(permissions.deletePackage?'<a href="javascript:;" onclick="deleteSpecial('+item.id+',6)" class="data">删除</a></td>':'') 				
						+ "</tr>" ;
				})
				$("#mustbuyList").html(content);
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
		url="businessman/recommend/updateSort.jhtml";
	}else if(modalType==2){
		url="businessman/recommend/updatePackageSort.jhtml";
	}else if(modalType==3){
		url="businessman/recommend/updateActivitySort.jhtml"
	}else if(modalType==4){
		url="businessman/recommend/updateTopicSort.jhtml"
	}else if(modalType==5){
		url="businessman/recommend/updateCommentSort.jhtml"
	}else if(modalType==6){
		url="businessman/recommend/updateMustbuySort.jhtml"
	}
	$.ajax({
		 url : url,
		 type : "post",
		 dataType : "json",
		 async: false,
		 data:{"id":modalId,"homeSort":sort},
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
		 loadSpecial();
	 }else if(modalType==2){
		 loadPackage();
	 }else if(modalType==3){
		 loadActivity();
	 }else if(modalType==4){
		 loadTopic();
	 }else if(modalType==5){
		 loadComment();
	 }else if(modalType==6){
		 loadMustbuy();
	 }
}

function deleteSpecial(id,type){
	var url;
	if(type==1){
		url="businessman/recommend/delete.jhtml";
	}else if(type==2){
		url="businessman/recommend/deletePackage.jhtml";
	}else if(type==3){
		url="businessman/recommend/deleteActivity.jhtml"
	}else if(type==4){
		url="businessman/recommend/deleteTopic.jhtml"
	}else if(type==5){
		url="businessman/recommend/deleteComment.jhtml"
	}else if(type==6){
		url="businessman/recommend/deleteMustbuy.jhtml"
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

//查询全部
 $("#querySubmit").click(function(){
	var province=$("#searchForm").find("[name=province]").val();
	var city=$("#searchForm").find("[name=city]").val();
	var data;
	if(province&&city){
		data={
			"provinceId":province,
			"cityId":city
		}
	}
	loadSpecial(data);
	loadPackage(data);
	loadActivity(data);
	loadTopic(data);
	loadComment(data);
	loadMustbuy(data);
 });	

 //重置
 $("#queryReset").click(function(){
	 loadSpecial();
		loadPackage();
		loadActivity();
		loadTopic();
		loadComment();
		loadMustbuy();
 });	
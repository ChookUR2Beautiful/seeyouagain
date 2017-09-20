var categoryList;
var initListUrl = "materialCategory/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 积分超市 > <a href="fresh/activityOrder/init.jhtml" target="right">活动订单  > 订单详情</a>',
			'categoryList', true);
	
	$(".modal-body input[name=id]").val($("#id").val());
	
});

function editAdressModal(){
	$("#editAdressModal").modal();
//	$(".modal-body input[name=id]").val($("#id").val());
}

/**
 * 更新收货地址
 */
function editAdress(){
	
	var receivingCity = $("#receivingName").val();//收货人
	if(receivingName =='' || receivingName ==undefined){
		showSmReslutWindow(false,"收货人未填写");
		return;
	}
	var receivingPhone = $("#receivingPhone").val();//收货人手机
	
	if(receivingPhone =='' || receivingPhone ==undefined){
		showSmReslutWindow(false,"收货人手机未填写");
		return;
	}
	var receivingCity = $("#receivingCity").val();//收货城市
	
	if(receivingCity =='' || receivingCity ==undefined){
		showSmReslutWindow(false,"收货城市未填写");
		return;
	}
	var receivingAddress = $("#receivingAddress").val();//收货地址
	
	if(receivingAddress =='' || receivingAddress ==undefined){
		showSmReslutWindow(false,"收货地址未填写");
		return;
	}
	
	 var url = 'fresh/activityOrder/update.jhtml';
	 var data = $('#editAdress').serializeArray();
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/fresh/activityOrder/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}

function receiveAdressModal(logisticsType){
	$("#sendAdressModal").modal();
}

/**
 * 发货
 */
function sendAdress(){
	var url = 'fresh/activityOrder/update.jhtml';
	var data = $('#sendAdress').serializeArray();
	showSmConfirmWindow(function (){
		$.ajax({
			type : 'post',
			url : url,
			data :data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					setTimeout(function(){
	        			location.href =contextPath+'/fresh/activityOrder/init.jhtml';
	        		}, 1000);
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
		 },"确认发货？");
}
/**
 * 关闭订单
 */
function shutDownOrder(state){
	var id = $("#id").val();
	var url = 'fresh/activityOrder/update.jhtml';
	showSmConfirmWindow(function (){
	$.ajax({
		type : 'post',
		url : url,
		data :{'id':id,'state':state},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				setTimeout(function(){
        			location.href =contextPath+'/fresh/activityOrder/init.jhtml';
        		}, 1000);
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
	 },"确定关闭订单吗？");
}

var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	inserTitle(' > 雏鸟云设计 > <a href="materialOrder/manage/init.jhtml" target="right">订单管理</a> > <a href="javascript:;">订单详情</a>','materialOrderList',true);
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","materialOrder/manage/export.jhtml");
		$form[0].submit();
	});
	
	
	//上传初稿
	$('#startdivs').uploadFile({
		urlId : "starturl",
		accept: {
	        title: 'file',
	        extensions: 'zip,tar,jar,rar,7z,cab,iso,uue',
	        mimeTypes: 'application/*'
	    }
	});
	
	//上传终稿
	$('#enddivs').uploadFile({
		urlId : "endurl",
		accept: {
			title: 'file',
			extensions: 'zip,tar,jar,rar,7z,cab,iso,uue',
			mimeTypes: 'application/*'
		}
	});
	
	
});


/**
 * 复制地址
 */
function copyAddress(){
    document.getElementById("address").select();
    document.execCommand("copy",false,null);
}

function shopView(orderNo,type) {
	var imgRoot = $("#fastfdsHttp").val();
	var url='materialOrder/manage/init/shopView.jhtml?orderNo='+orderNo+'&type='+type+ '&t=' + Math.random();
	$.ajax({
		type : 'post',
		url : url,
		data :[orderNo,type],
		dataType : 'json',
		success : function(data) {
			if(data.data.type == '001'){
				$("#group").html(data.data.group);
				$("#num").html(data.data.nums+"份");
				$("#price").html("￥"+data.data.amount+"(含运费"+data.data.freight+"元)");
				$("#title").html(data.data.title);
				$("#createTime").html(data.data.createTime);
				
				var html=[];
		
				for(var i=0;i<data.data.mgroupSize;i++){
					for(var j=0;j<data.data.mgroup[i].picSize;j++){
						
						if(data.data.mgroup[i].pics[j].type=='001'){
							html.push("<li><img width='90px' src='"+imgRoot+data.data.mgroup[i].pics[j].picUrl+"'/></li>");
						}
					}
				}
			
				$("#commonUrl").html(html);
				
				$(".shade-box,#modle1").show();
			}else{
				$("#budget").html(data.data.budget);
				$("#mainColor").html(data.data.mainColor);
				$("#secColor").html(data.data.secColor);
				$("#materialType").html(data.data.materialType);
				$("#finishTime").html(data.data.finishTime);
				$("#remarks").html(data.data.remark);
				
				var html=[];
				for(var i=0;i<data.data.urlSize;i++){
					html.push("<li><img width='90px' src='"+imgRoot+data.data.customizeUrls[i]+"' /></li>");
				}
				$("#customizeUrls").html(html);
				
				$(".shade-box,#modle2").show();
			}
			
			 //点击关闭遮罩层
		    $(".close-shade").on("click",function () {
		        $(".shade-box,.shade-content").hide();
		    });
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}

/**
 * 订单价格修改
 */
 function changePrice(){
	 
 		var data = $('#orderForm').serializeArray();
			data = jsonFromt(data);
 		$.ajax({
 			type : 'post',
 			url : 'materialOrder/manage/update/changePrice.jhtml' + '?t=' + Math.random(),
 			data : data,
 			dataType : 'json',
 			success : function(data){
 				if (data.success) {
						setTimeout(function(){
							location.href =contextPath+'/materialOrder/manage/init.jhtml';
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
 			},
 			error : function() {
 				window.messager.warning(data.msg);
 			}
 		});
	 
}
 	
    /**
     * 转换from表单
     */
    function jsonFromt(data){
    	var json = {};
    	for(var i=0; i<data.length; i++){
    		json[data[i].name] = data[i].value;
    	}
    	return json;
    }
    
    /**
     * 发货
     */
    function deliver(){
    	
    	if($("input[name='postCompany']").val()=="" || $("input[name='postCompany']").val() == undefined ){
    		showSmReslutWindow(false,"快递公司未填写");
    		return;
    	}
    	if($("input[name='courierNumber']").val()=="" || $("input[name='courierNumber']").val() == undefined){
    		showSmReslutWindow(false,"快递单号未填写");
    		return;
    	}
    	
    		var data = $('#orderForm').serializeArray();
			data = jsonFromt(data);
    		$.ajax({
    			type : 'post',
    			url : 'materialOrder/manage/update/deliver.jhtml' + '?t=' + Math.random(),
    			data : data,
    			dataType : 'json',
    			success : function(data){
    				if (data.success) {
						setTimeout(function(){
							location.href =contextPath+'/materialOrder/manage/init.jhtml';
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
    			},
    			error : function() {
    				window.messager.warning(data.msg);
    			}
    		});
    }
    
    /**
     * 发货
     */
    function updateSaleStatus(){
    		var data = $('#orderForm').serializeArray();
			data = jsonFromt(data);
    		$.ajax({
    			type : 'post',
    			url : 'materialOrder/manage/update/updateSaleStatus.jhtml' + '?t=' + Math.random(),
    			data : data,
    			dataType : 'json',
    			success : function(data){
    				if (data.success) {
						setTimeout(function(){
							location.href =contextPath+'/materialOrder/manage/init.jhtml';
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
    			},
    			error : function() {
    				window.messager.warning(data.msg);
    			}
    		});
    }
    
    /**
     * 保存初稿或终稿
     */
    function saveMaterialPic(){
    	var data = $('#orderForm').serializeArray();
    	data = jsonFromt(data);
    	$.ajax({
    		type : 'post',
    		url : 'materialOrder/manage/update/saveMaterialPic.jhtml' + '?t=' + Math.random(),
    		data : data,
    		dataType : 'json',
    		success : function(data){
    			if (data.success) {
    				setTimeout(function(){
    					location.href =contextPath+'/materialOrder/manage/init.jhtml';
    				}, 1000);
    			}			
    			showSmReslutWindow(data.success, data.msg);
    		},
    		error : function() {
    			window.messager.warning(data.msg);
    		}
    	});
    }
    
    /**
     * 操作售后
     */
    function addAfterSale(){
    		var orderNo = $("input[name='orderNo']").val();
    		var data = $('#orderForm').serializeArray();
			data = jsonFromt(data);
    		$.ajax({
    			type : 'post',
    			url : 'materialOrder/manage/update/addAfterSale.jhtml' + '?t=' + Math.random(),
    			data : data,
    			dataType : 'json',
    			success : function(data){
    				if (data.success) {
						setTimeout(function(){
							location.href =contextPath+'/materialOrder/manage/init/view.jhtml?orderNo='+orderNo;
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
    			},
    			error : function() {
    				window.messager.warning(data.msg);
    			}
    		});
    }
    
    /**
     * 更新售后记录
     */
    function updateAfterSale(){
    		var data = $('#orderForm').serializeArray();
			data = jsonFromt(data);
    		$.ajax({
    			type : 'post',
    			url : 'materialOrder/manage/update/updateAfterSale.jhtml' + '?t=' + Math.random(),
    			data : data,
    			dataType : 'json',
    			success : function(data){
    				if (data.success) {
						setTimeout(function(){
							location.href =contextPath+'/materialOrder/manage/init.jhtml';
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
    			},
    			error : function() {
    				window.messager.warning(data.msg);
    			}
    		});
    }
    /**
     * 删除
     */
    function remove(orderNo) {
    	showSmConfirmWindow(function() {
    		$.ajax({
    			type : 'post',
    			url : 'materialOrder/manage/update/delete.jhtml' + '?t=' + Math.random(),
    			data : {"orderNo":orderNo},
    			dataType : 'json',
    			success : function(data) {
    				setTimeout(function(){
						location.href =contextPath + "/materialOrder/manage/init.jhtml"+ "?t=" + Math.random();
	        		}, 1000);
    				showSmReslutWindow(data.success, data.msg);
    			},
    			error : function() {
    				window.messager.warning(data.msg);
    			}
    		});
    	},"确定删除吗？");
    }

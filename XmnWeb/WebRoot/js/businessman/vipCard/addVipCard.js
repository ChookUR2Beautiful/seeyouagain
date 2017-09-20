$(function(){
	var dateCount = 0, cityCount = 0, dateSize = 10, citySize = 10;
	var reqUrl="businessman/vipCard/";

	 var valiinfo={
				rules:{
					cardName:{
						required:true,
					},
					sellerId:{
						required:true,
						remote:{
							type : 'post',
							url : reqUrl+'add/ajaxSellerStatus.jhtml' + '?t=' + Math.random(),
							dataType : 'json',
							data:{
								sellerId: function() {
						            return $("#sellerId").val();
						        }
							},
						}
					}
				},
				messages:{
					cardName:{
						 required:"请输入会员卡的名称",
					},
					sellerId:{
						required:"请选择适用商户",
					}
				}
		};
	 
	 //带2位小数字点
	 $.validator.addMethod("doublearea",function(value,element,params){
			var len = value.length;
			 if(len>12){return false;
			 }else if( value >= 1000000000 || value < 0){
				 return false;
			 }else {
				 var indexOf = value.indexOf(".");
				 if(indexOf>0){
					 var numStr = value.substr(indexOf+1);
					 return !(numStr.length > 2);
				 }
				 return true;
			 }
		 },"请填写数值,最大值为999999999.99");
	 

	 validate("editVipCardInfo",valiinfo,busave); 
		
	 function busave(){
		 for(var i = 0;i < dateCount+1; i++) {
				if($("input[name='planList["+i+"].price']").val()==''){ 
					submitDataError("input[name='planList["+i+"].price']","充值金额不能为空");
					return false;
				}
				if($("input[name='planList["+i+"].retail']").val()==''){ 
					submitDataError("input[name='planList["+i+"].retail']","到账金额不能为空");
					return false;
				}
//				alert(isfloatNum($("input[name='planList["+i+"].price']").val()));
//				if(!isfloatNum($("input[name='planList["+i+"].price']").val())){
//					submitDataError("input[name='planList["+i+"].price']","充值金额只能为数据类型");
//					return false;
//				}
//				if(!isfloatNum($("input[name='planList["+i+"].retail']").val())){
//					submitDataError("input[name='planList["+i+"].retail']","到账金额只能为数据类型");
//					return false;
//				}
			}
		 
		 	if($("#childSeller").val()==''){
		 		submitDataError("input[name='childSeller']","适用门店不能为空");
		 		return false;
		 	}
		 
			var success=true;
			var url;
			if ($('#isType').val() == 'add') {
				url = reqUrl+"add.jhtml" + "?t=" + Math.random();
			}else{
				url = reqUrl+"update.jhtml" + "?t=" + Math.random();
			}
			if(success){
					$.ajax({
						type : 'post',
						url : url,
						data : jsonFromt($('#editVipCardInfo').serializeArray()),
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							
							if(data.success){
								showSmReslutWindow(data.success, data.msg);
								if ($('#isType').val() == 'add') {
									var callbackParam="isBackButton=true&callbackParam=";
									setTimeout(function(){
					        			location.href =contextPath + "/" + reqUrl+"addView.jhtml?"+ callbackParam;
					        		}, 1000)
								}else{
									//var callbackParam="&isBackButton=true&callbackParam="+getParam("callbackParam",window.location.search.substr(1));
									var url = contextPath + "/"+reqUrl+"listView.jhtml";//+ callbackParam;
									
									setTimeout(function(){
					        			location.href =url;
					        		}, 1000);
								}
							}else{
								showSmReslutWindow(data.success, data.msg);
							}
							
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							showSmReslutWindow(false, errorThrown);
						}
					});
		}
	}
	
	 
	$("#logoUpload").uploadImg({
		urlId : "sellerLogo",
		showImg : $("#sellerLogo").val()
	});
	 
	if ($('#isType').val() == 'add') {
		inserTitle(' > <span>新增会员卡信息','addProductInfo', false);
	} else {
		dateCount = $("#dates").find(".input-group").size()>0?$("#dates").find(".input-group").size()-1:0;
		inserTitle(' > <span>编辑会员卡信息','editProductInfo', false);
	}
	
	
	$('#sellerId').change(function(){
		var inputHtml='<input type="text" class="form-control" name="childSeller" id="childSeller">';
		$("#sellerName").val($("#sellerId option:selected").text());
		
		$("#pollShop").empty();
		$("#pollShop").append(inputHtml);
		splitShop();
//		alert($("#sellerId").val());
//		if($("#sellerId").val()==null){
//			//disable
//		}else{
//			//允许。。。
//			$("#searchChosen").
//		}
	});
	
	addSelect();
	
	//挑选子店
	splitShop();
	
	$("#dates").on(
		"click",
		".icon-plus",
		function() {
			if ($(this).parents(".plandiv").find(".input-group")
					.size() < dateSize) {
				dateCount++;
				$(this).parents(".input-group").after(
						$(".dateTemp").html().replace(/index/g,
								dateCount));
			}
		});
	$("#dates").on(
		"click",
		".icon-minus",
		function() {
			if ($(this).parents(".plandiv").find(".input-group")
					.size() > 1) {
				/*
				 * 若当前删除项是默认选中项，则重新设置第一项为默认项
				 */
				var isSel = $(this).parent().parent().children(".inputRadio").children(".issueDefault").is(':checked');
				$(this).parents(".input-group").remove();
				if(isSel){
					$($(".plandiv :radio").eq(0)).prop('checked',true);
				}
			} else {

			}
		});

	// 产品图片
//	$("#breviaryUpload").uploadImg({
//		urlId : "breviary",
//		showImg : $("#breviary").val()
//	});	
	
	function isfloatNum(str){
		var reg="/^((-/d+(/./d+)?)|(0+(/.0+)?))$/";
		return reg.test(str);
		
	}
	
	//获取所有连锁总店
	function addSelect(){ 
		$.ajax({ 
			url : reqUrl+'list/getMulShopList.jhtml', 
			data: {}, 
			dataType : 'json',
			success : function(result){ 
				var dataArray = result;
				var html = [];
				if(dataArray && $.isArray(dataArray) && dataArray.length > 0){
					for(var i = 0;i < dataArray.length; i++){
						html.push('<option value="'+dataArray[i].sellerid+'">'+dataArray[i].sellername+'</option>');
					}
				}
				$("#sellerId").append(html);
				
				
			} 
		}); 
	}
	
	
	/**
	 * 挑选子店
	 */
	function splitShop(){
		if ($('#isType').val() == 'add') {
		searchChosen = $("#childSeller").searchChosen({
			 url : reqUrl+"list/chosenView.jhtml?isChose=true&sellerId="+($('#sellerId').val()),
			 initUrl : reqUrl+"list/findSellerByFatherid.jhtml?fatherid="+ ($('#sellerId').val()),
			 initId :"sellerid",
			 initTitle :"sellername"
		 });
		}else{
			searchChosen = $("#childSeller").searchChosen({
				 url : reqUrl+"list/chosenView.jhtml?isChose=true&sellerId="+($('#sellerId').val()),
				 initUrl : reqUrl+"list/findSelectedSellerByFatherid.jhtml?sellerId="+ ($('#sellerId').val()),
				 initId :"sellerid",
				 initTitle :"sellername"
			 });			
		}
	}
	
	/**
	 * 操作提示
	 */
	function showSmReslutWindow(isflag, content) {
		$('#sm_reslut_window').find('.modal-title').html('操作提示');
		if (isflag) {
			$('#sm_reslut_window').find('.modal-body').html('<div class="alert with-icon alert-success"> <i class="icon-ok"></i> <div class="content">' + content + '</div></div>');
		} else {
			$('#sm_reslut_window').find('.modal-body').html('<div class="alert with-icon  alert-danger"><i class="icon-remove-sign"></i> <div class="content">' + content + '</div></div>');
		}
		$('#sm_reslut_window').modal();
		setTimeout(function(){
			$('#sm_reslut_window').modal('hide');
		}, 2000);
	}
	
	// 时间对象的格式化;
	Date.prototype.format = function(format){
		 var o = {
		  "M+" : this.getMonth()+1, //month
		  "d+" : this.getDate(), //day
		  "h+" : this.getHours(), //hour
		  "m+" : this.getMinutes(), //minute
		  "s+" : this.getSeconds(), //second
		  "q+" : Math.floor((this.getMonth()+3)/3), //quarter
		  "S" : this.getMilliseconds() //millisecond
		 };
		   
	  	if(/(y+)/.test(format)) {
		  format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		}
		  
		  for(var k in o) {
			  if(new RegExp("("+ k +")").test(format)) {
			 	 format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
			  }
		  }
		 return format;
	};
});
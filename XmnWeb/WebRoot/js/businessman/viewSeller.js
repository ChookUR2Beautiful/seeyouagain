var VIEWTYPE = $('#viewType').val();
$(document).ready(function() {	
	var operation = $("input[name='liveLedgerOperating']:checked").val();
	console.info("operation"+operation);
	if(operation==0){
		$(".liveLedgerTable").hide();
	}
	if(operation==1){
		$(".liveLedgerTable").show();
	}
	
	var sellerid = $('#selleridid').val();
	if(null==sellerid||''==sellerid){
		showSmReslutWindow(true, "商家信息不存在！");
	}
	
	if(VIEWTYPE == "viewSellerPending"){
		inserTitle(' > <span><a href="businessman/sellerPending/getInit.jhtml?sellerid='+sellerid+'" target="right">查看商家信息</a>','editsellerInfo',false);
		$("#sellerButton").hide();
		$("#sellerPendingButton").show();
	}else{
		inserTitle(' > <span><a href="businessman/seller/getInit.jhtml?sellerid='+sellerid+'" target="right">查看商家信息</a>','editsellerInfo',false);
		$("#sellerButton").show();
		$("#sellerPendingButton").hide();
	}
	
	
	
	limitedDate({form:"#sellerForm",startDateName:"svalidity",endDateName:"evalidity",format : 'yyyy-mm-dd'});
	
	
	/**
	 * 初始化选择数据
	 */
    initChosen();
		
	/**
	 * 根据状态显示审核通过或者不通过按钮
	 */
    showStatus();
    
    /**
     * 审批初始化
     */
    examine();
    
    /**
     * add by lifeng 2016年6月7日 13:51
	 * 初始化对应的寻蜜客信息下拉列表
	 */
	initXmerInfo();
	//
		
});


/**
 * 审批初始化
 */
function examine(){
    /**
     * 审批通过
     */	
	 $("#auditYes").click(function(){
		 
            if($("#sellerGrade").val().length<1){
            	showSmReslutWindow(false, "请补充商家等级信息后，再审核！");
            	return ;
            }
            // add by lifeng 
            /*if($("#jointid").val().length<1){
            	showSmReslutWindow(false, "请绑定合作商后，再审核！");
            	return ;
            }*/
            if($("#zoneid").val().length<1){
            	showSmReslutWindow(false, "请补充所属商圈后，再审核！");
            	return ;
            }
            if($('#areaSelect [selected=""]').length<1){
            	showSmReslutWindow(false, "请补充商家所在区域后，再审核！");
            	return ;
            }
            if($('#tradeSelect [selected=""]').length<1){
            	showSmReslutWindow(false, "请补充商家经营行业后，再审核！");
            	return ;
            }
            if($("#sellerLandmarkLidId").val().length<1){
            	showSmReslutWindow(false, "请补充商家经纬度信息后，再审核！");
            	return ;
            }
            if($("#sellerAgioId").val().length<1){
            	showSmReslutWindow(false, "请补充商家折扣后，再审核！");
            	return ;
            }
            if($("#sellerUrlId").val().length<1){
            	showSmReslutWindow(false, "请补充商家LOGO后，再审核！");
            	return ;
            }
            if($("#sellerPicUrlId").val().length<1){
            	showSmReslutWindow(false, "请补充商家图片信息后，再审核！");
            	return ;
            }
            // if($("#pledger").val()==null){
            // 	showSmReslutWindow(false, "请计算平台占比后，再审核！");
            // 	return ;
            // }
            // if($("#pledger").val()!=null){
            // 	if(parseFloat($("#pledger").val()) < 0.0){
            // 		showSmReslutWindow(false, "请计算平台占比后，再审核！");
            //     	return ;
            // 	}
            // }
            //
            
			showSmConfirmWindow(function() {
				
					var selleridid=$("#selleridid").val();
					var token = $("#updateStatusToken").val();
					var date = {ids:selleridid,status:3,updateStatusToken:token};
					ratify(date);
			},"确定要通过该商家的审核？");
	 });
	 
	  /**
	   * 暂停合作
	   */	
	 $("#stop").click(function(){
			showSmConfirmWindow(function() {
					var selleridid=$("#selleridid").val();
					var token = $("#updateStatusToken").val();
					var date = {ids:selleridid,status:5,updateStatusToken:token};
					
					ratify(date);
			},"确定要与该商家暂停合作？");
	});
	 
	/**
     * 恢复合作
     */	
	 $("#recover").click(function(){
			showSmConfirmWindow(function() {
					var selleridid=$("#selleridid").val();
					var token = $("#updateStatusToken").val();
					var date = {ids:selleridid,status:3,updateStatusToken:token};
					ratify(date);
			},"确定要恢复与该商家的合作？");
	 });
	 
		
	/**
	  * 审批不通过(弹出不通原因框)
	 */	
	$("#auditNo").click(function(){
		var ids=$("#selleridid").val();
		var token = $("#updateStatusToken").val();
		 var url;
		 if(VIEWTYPE == "viewSellerPending"){
			 url = 'businessman/sellerPending/updateSellerStatus/state/init.jhtml?ids=' + ids+'&updateStatusToken='+token;
		 }else{
			 url = 'businessman/seller/updateSellerStatus/init.jhtml?ids=' + ids +'&updateStatusToken='+token;
		 }
		
		var modalTrigger = new ModalTrigger({
			type : 'ajax',
			url : url,
			toggle : 'modal',
			position: ''
		});
		modalTrigger.show();
	
	});
	
	/**
     * 注销下架
     */	
	 $("#revoke").click(function(){
			showSmConfirmWindow(function() {
					var selleridid=$("#selleridid").val();
					var token = $("#updateStatusToken").val();
					var date = {ids:selleridid,status:6,updateStatusToken:token};
					ratify(date);
			},"确定要注销该商家的信息？(对已签约SAAS订单商家，该操作将返还1套SAAS软件给签约寻蜜客，请谨慎操作！)");
	 });
}
	
 /**
  * 审核商家方法
  */
 function ratify(date){
	 var url;
	 if(VIEWTYPE == "viewSellerPending"){
		 url = "businessman/sellerPending/updateSellerStatus.jhtml";
	 }else{
		 url = "businessman/seller/updateSellerStatus.jhtml";
	 }
	 $.ajax({
	        type: "POST",
	        url: url,
	        data: date,
	        dataType: "json",
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
	        success: function(result){
	        	$('#prompt').hide();
	        	if(result.success == true){
	        		 var rUrl;
	        		 if(VIEWTYPE == "viewSellerPending"){
	        			 rUrl = contextPath + "/businessman/sellerPending/init.jhtml";
	        		 }else{
	        			 rUrl = contextPath+"/businessman/seller/init.jhtml";
	        		 }
	        		showSmReslutWindow(result.success, result.msg);
	        		setTimeout(function(){
	        			location.href = rUrl;
	        		}, 1000);
				}else{
					showSmReslutWindow(result.success, result.msg);
				}
	         },
	 		error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
	    });
 }

/**
 * 根据状态显示审核通过或者不通过按钮
 */
function showStatus(){
	//暂停合作（已签约就可以暂停合作）
    if($("#statuschage").val()==3){
		  $("#stop").show();
	 }
     //未通过
	 else if($("#statuschage").val()==2){
//		 $("#auditYes").show();
	 //未签约
	 }else if($("#statuschage").val() == 4){
		  //$("#auditYes").show();
	 //恢复合作
	 }else if($("#statuschage").val() == 5){
		  $("#recover").show();
	 //审核中
	 }else if($("#statuschage").val() == 1){
		  $("#auditYes").show();
		  $("#auditNo").show();
	 }
    if($("#isonlineId").val()== 0){//0：未上线 1：已上线 3：已下线
//		$("#auditNo").show();
	}
    
    //已签约，且为“待上线”的商家记录，且签约时间至今>=7天，增加“审核不通过”功能
/*    if($("#signdateId").val()){
    	var signdate =  $("#signdateId").val();
    	var minusDate =  (new Date().getTime()-new Date($("#signdateId").val()).getTime())/(24*60*60*1000);
    	if(minusDate >= 7 && $("#statuschage").val()==3){
    		if($("#isonlineId").val()==2 || $("#isonlineId").val()== 0){
    			$("#auditNo").show();
    		}
    	}
    }*/
    if($("#statuschage").val()==0 || $("#statuschage").val()==1
    	|| $("#statuschage").val()==3 || $("#statuschage").val()==4
    	|| $("#statuschage").val()==5){
    	$("#revoke").show();
    }
   
}


/**
 * 初始化选择数据
 */
function initChosen(){
		
		   //总店商家
		$("#fatherid").chosenObject({
			hideValue : "sellerid",
			showValue : "sellername",
			url : "businessman/seller/getFatherSeller.jhtml",
			isChosen:false,
			isCode:true
		});
		
		//联盟店
		$("#allianceId").chosenObject({
			hideValue : "id",
			showValue : "allianceName",
			url : "businessman/allianceShop/init/list.jhtml",
			isChosen:false
		});
		
		//营业执照正面(查看页面调用)
		$("#sellerHeadedit1").uploadImg({
			urlId : "licenseurleditid",
			showImg : $('#licenseurleditid').val(),
			editable:false			
		});
	//营业执照反面(查看页面调用)
		$("#sellerHeadedit2").uploadImg({
			urlId : "licensefurleditid",
			showImg : $('#licensefurleditid').val(),
			editable:false	
		});
	//申请修改中的头像不可编辑
	$("#sellerHeadeditlogo").uploadImg({
		urlId : "editlogoId",
		showImg : $('#editlogoId').val(),
		editable:false	
	
		});
	
	$("#tradeSelect").tradeLd({
		isDisabled : true,
		showConfig : [{name:"category",tipTitle:"暂无数据"},{name:"genre",tipTitle:"暂无数据"}],
		commonChange : function($dom, level){
			if(level == 0){
				$(":input[name=typename]").val($dom.find("option:selected").text());
				$(":input[name=tradename]").val("");
			}else if(level == 1){
				$(":input[name=tradename]").val($dom.find("option:selected").text());
			}
		}
	});
/*	if ($("#includeTrade").find(".includeTradeSelect").length > 0) {
			var i = 0;
			$("#includeTrade").find(".includeTradeSelect").each(function() {
				$(this).tradeLd({
					isDisabled : true,
					showConfig : [ {
						name : "traderRefs[" + i + "].category",
						tipTitle : "请选择",
						width : "50%"
					}, {
						name : "traderRefs[" + i++ + "].genre",
						tipTitle : "请选择",
						width : "50%"
					} ],
					commonChange : function($dom, level){
						if(level == 0){
							$(":input[name=typename]").val($dom.find("option:selected").text());
							$(":input[name=tradename]").val("");
						}else if(level == 1){
							$(":input[name=tradename]").val($dom.find("option:selected").text());
						}
					}
				});
			});
		}
*/	
	var isInit = true;
	var jointChosen = undefined;
	$("#areaSelect").areaLd({
		isDisabled : true,
		showConfig : [{name:"category",tipTitle:"请选择",width:"50%"},{name:"genre",tipTitle:"请选择",width:"50%"}],
		commonChange : function($dom, level){
			if(level != 2 || !$dom.val()){
				$("#business").empty().append('<option value="">请先选择区域再选择商圈</option>');
			}
		},
		showConfig : [{name:"province",tipTitle:"--省--",width:'33.3%'},{name:"city",tipTitle:"--市--",width:'33.3%'},{name:"area",tipTitle:"--区--",width:'33.4%'}],
		lastChange : function($dom){
			if($dom.val()){
				$.ajax({
					url : "common/business/BusinessList.jhtml?areaId="+($dom.val()),
					dataType:"json",
					method : "get", 
					success:function(data){
						var businessV = $("#zoneid").attr("initValue");
						$("#zoneid").empty().append('<option value="">请选择商圈</option>');
						for(var i=0;i<data.length;i++){
							$("#zoneid").append('<option value="'+data[i].bid+'" '+(data[i].bid==businessV?'selected':'')+'>'+data[i].title+'</option>');
						}
					}
				});
				$.ajax({
					url : "businessman/seller/getJointInfoByArea.jhtml?areaId="+($dom.val()),
					dataType:"json",
					method : "get", 
					success:function(data){
						if(!isInit){
							$("#jointid").attr("initValue", "");
						}
						if(data){
							$("#jointid").empty().append('<option value="'+data.jointid+'" selected>'+data.corporate+'</option>').attr("readonly", true);
							$("#jointid_chosen").hide();
							$("#jointid").show();
							getStaffs(data.jointid);
						}else{
							$("#jointid").empty().append('<option value="">此区域暂无合作商</option>').attr("readonly", true);
							$("#staffid").empty().append('<option value="">此区域暂无合作商</option>').attr("readonly", true);
						}
						isInit = false;
					}
				});
			}else{
				$("#jointid").empty().append('<option value="">请先选择区域</option>').attr("readonly", true);
				$("#staffid").empty().append('<option value="">请先选择区域</option>').attr("readonly", true);
			}
		}
	});
	
	function getStaffs(jointid){
		var initValue = $("#staffid").attr("initValue");
		$.ajax({
			url : "business_cooperation/staff/getStaffsByJointid.jhtml?jointid=" + jointid,
			dataType:"json",
			method : "get", 
			success:function(data){
				$("#staffid").empty().append('<option value="">请选择业务员</option>');
				for(var i=0;i<data.length;i++){
					$("#staffid").append('<option value="'+data[i].staffid+'" '+(initValue == data[i].staffid ? 'selected' : '')+'>'+data[i].fullname+'</option>');
				}
			}
		});
	}
}

/*
 * (add by lf 2016年6月6日 19:47:10)
 * 加载列表数据
 */
function initXmerInfo(){
	//寻蜜客
	$("#uid").chosenObject({
		hideValue : "uid",
		showValue : "phoneid",
		url : "businessman/seller/initXmer.jhtml",
		isChosen:true,
		isCode:true,
		width:"100%",
		defaultValue:"--未选择寻蜜客--"//默认的显示值
	});
	$("#uid").on("change",function(){
		var value =  $(this).find("option:selected").text();
		$("input[name='xmerPhone']").val(value);
	});
}
/*$(document).ready(function() {	
	
	var sellerid = $('#selleridid').val();
	inserTitle(' > <span><a href="businessman/sellerPending/getInit.jhtml?sellerid='+sellerid+'" target="right">查看商家信息</a>','editsellerInfo',false);
	
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
	
	var isInit = true;
	var jointChosen = undefined;
	$("#areaSelect").areaLd({
		isDisabled : true,
		showConfig : [{name:"province",tipTitle:"暂无数据"},{name:"city",tipTitle:"暂无数据"},{name:"area",tipTitle:"暂无数据"}],
		commonChange : function($dom, level){
			if(level != 2 || !$dom.val()){
				$("#business").empty().append('<option value="">请先选择区域再选择商圈</option>');
			}
		},
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
							if(!jointChosen){
								jointChosen = $("#jointid").chosenObject({
									id : "jointid",
									hideValue : "jointid",
									showValue : "corporate",
									url : "business_cooperation/joint/jointList.jhtml",
									isChosen:false
								});
							}else{
								jointChosen.initData();
								$("#jointid_chosen").show();
								$("#jointid").hide().trigger('chosen:updated');
							}
							if(isInit){
								getStaffs($("#jointid").attr("initValue"));
							}else{
								$("#staffid").empty().append('<option value="">请先选择合作商</option>');
							}
						}
						isInit = false;
					}
				});
			}else{
				$("#jointid").empty().append('<option value="">请先选择区域</option>').attr("readonly", false);
				$("#jointid_chosen").hide();
				$("#jointid").show();
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

	//身份证附件正面(查看页面调用)
	$("#identityzurldiv").uploadImg({
			urlId : "identityzurlid",
			showImg : $('#identityzurlid').val(),
			editable:false			
		});
	  //身份证附件反面(查看页面调用)
	$("#identityfurldiv").uploadImg({
			urlId : "identityfurlid",
			showImg : $('#identityfurlid').val(),
			editable:false	
		});
		
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'	
	});

		
	//根据状态显示审核通过或者不通过按钮
	 if($("#statuschage").val()==3){
	  $("#auditYes").hide();
	 }
	 else if($("#statuschage").val()==2){
	  $("#auditNo").hide();
	 }
	 *//**
	    * 审批通过
	    *//*	
	 $("#auditYes").click(function(){
			showSmConfirmWindow(function() {
			var selleridid=$("#selleridid").val();			
			$.ajax({
			        type: "POST",
			        url: "businessman/sellerPending/updateSellerStatus.jhtml",
			        data: {
			        	ids:selleridid,
			        	status:3
			        },
			        dataType: "json",
			        success: function(result){
			        	if(result.success == true){
			        		var url = contextPath+"/businessman/sellerPending/init.jhtml";
			        		showSmReslutWindow(result.success, result.msg);
			        		setTimeout(function(){
			        			location.href =url;
			        		}, 1000);	        		
						}else{
							showSmReslutWindow(result.success, result.msg);
						}
			         }
			    });
			},"确定要通过该商家的审核？");
		});
		
		
		*//**
		    * 审批不通过(弹出不通原因框)
		    *//*	
		$("#auditNo").click(function(){
			var ids=$("#selleridid").val();
			var modalTrigger = new ModalTrigger({
				type : 'ajax',
				url : 'businessman/sellerPending/updateSellerStatus/state/init.jhtml?ids=' + ids+'&editType='+$('#editType').val()+'&fartherSellerId='+$('#fartherSellerId').val(),
				toggle : 'modal'
			});
			modalTrigger.show();
		
		});
		
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
		
});
*/
	$(document).ready(function() {
		
		var value = $("input[name=isSendImmediate]:checked").val();
		if(value == 0){
			$("#sendDateId").show();
			$("#sendDateContentId").show();
			$("#endId").show();
			$("#endContentId").show();
		}else{
			$("#sendDateId").hide();
			$("#sendDateContentId").hide();
			$("#endId").hide();
			$("#endContentId").hide();
		}
		
		//初始化信息
		initInfo()
		 //title
		 if($('#isType').val() ==  'add'){
			inserTitle(' > <span>添加商家站内消息','addJointInfo',false);
		 }else{
			inserTitle(' > <span>编辑商家站内消息','editJointInfo',false);
		 }
		 
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
		
		//校验
		validateInfo();
		
		//查看商家
		viewObject();
		
		//根据选中的信息来显示时间
		checkSendDate()
	});
	
	/**
	 * 根据选中的信息来显示时间
	 */
	function checkSendDate(){
		$("input[name=isSendImmediate]").on("click",function(){
			var value = $("input[name=isSendImmediate]:checked").val();
			if(value == 0){
				$("#sendDateId").show();
				$("#sendDateContentId").show();
				$("#endId").show();
				$("#endContentId").show();
			}else{
				$("#sendDateId").hide();
				$("#sendDateContentId").hide();
				$("#endId").hide();
				$("#endContentId").hide();
			}
		});
	}
	
	/**
	 * 初始化信息
	 */
	function initInfo(){
		
		
		
		//消息图标
		 $("#urlMessageImgId").uploadImg({
			urlId : "urlMessageImg",
			showImg : $('#urlMessageImg').val()
		}); 
		//文章焦点图标
		 $("#urlArticleFocusImgId").uploadImg({
			urlId : "urlArticleFocusImg",
			showImg : $('#urlArticleFocusImg').val()
		}); 
			//编辑初始化商家信息
			if($("#area").val()){
				$("#ld").attr("initValue",$("#area").val());
			}else if($("#city").val()){
				$("#ld").attr("initValue",$("#city").val());
			}else if($("#province").val()){
				$("#ld").attr("initValue",$("#province").val());
			}
		 
		//区域
		$("#ld").areaLd({
			isChosen : false,
			lastChange : function($dom){
				if($dom.val()){
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
					}
				}
			}
		});
		 
		
		/**
		 * 返回
		 */
		 $("#backId").on("click",function(){
			 backList();
		 });
		
	}
	
	/**
	 * 查看数据
	 */
	var zoneid = "";
	function  viewObject(){
		$('#viewSeller').click(function(){
			var area = $("#editsellerMsgForm").find("select[name='area']").val().toString();
			area = (undefined == area ? "" : area);
			var city = $("#editsellerMsgForm").find("select[name='city']").val().toString();
			city = (undefined == city ? "" : city)
			var province = $("#editsellerMsgForm").find("select[name='province']").val().toString();
			province = (undefined == province ? "" : province)
			if(province!==""){
				if($("#zoneid").val()){
					zoneid = $("#zoneid").val();
				}
				var modalTrigger = new ModalTrigger({
					type : 'ajax',
					url : 'businessman/sellerMsg/init/sellerInit.jhtml?area='+area+'&city='+city+'&province='+province+'&zoneid='+zoneid,
					toggle : 'modal',
					width:"80%",
					title:"商家列表"
				});
				modalTrigger.show();
			}else{
				showWarningWindow("warning","请至少选择一个区域!");
			}
		});
	}
	
	/**
	 * 保存站内消息
	 */
   var saveSellseMsg = function (){
		    var url ;
			var data = jsonFromt($('#editsellerMsgForm').serializeArray());
			var area = $("#editsellerMsgForm").find("select[name='area']").val().toString();
			area = (undefined == area ? "" : area);
			var city = $("#editsellerMsgForm").find("select[name='city']").val().toString();
			city = (undefined == city ? "" : city)
			var province = $("#editsellerMsgForm").find("select[name='province']").val().toString();
			province = (undefined == province ? "" : province)
			if($("#zoneid").val()){
				zoneid = $("#zoneid").val();
			}
			
			data.area = area;
			data.city = city;
			data.province = province;
			data.zoneid = zoneid;
			
			if(!valiImgData('#editsellerMsgForm',data)){
				if((data.dateSend && data.dateEndSend) && !databj(data.dateSend,data.dateEndSend)){
					dataError("#dateEndSend","结束日期应晚于开始日期");
					return false;
				}else{
					showSmConfirmWindow(function() {
						datasuccess("#dateEndSend");
						if($('#isType').val() ==  'add'){
							url = 'businessman/sellerMsg/add.jhtml' + '?t=' + Math.random();
						}else{
							url = 'businessman/sellerMsg/update.jhtml' + '?t=' + Math.random();
						}
						$.ajax({
							type : 'post',
							url : url,
							data : data,
							dataType : 'json',
							success : function(data) {
								showSmReslutWindow(data.success, data.msg);
								 var url = contextPath + '/businessman/sellerMsg/init.jhtml';
									setTimeout(function(){
										location.href =url;
									}, 1000);
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
							}
						});
					},"您确定要发送该消息？");
				}
		   }
	}
   /**
    * 比较时间
    * @param sdate
    * @param enddate
    * @returns {Boolean}
    */
   function databj(sdate,enddate){
		if(sdate && enddate){
			var s = dateParse(sdate).getTime(); 
			var e = dateParse(enddate).getTime();
			return e>s;
		}
	}
    
   /**
    * 返回
    */
   function backList(){
	   var url = contextPath + '/businessman/sellerMsg/init.jhtml';
	   location.href =url;
   }
    /**
     * 校验
     */
    function validateInfo(){
    	var valiinfo={"editsellerMsgForm":{rules:{
    		    title:{
		    			required:true, 
		    			rangelength:[2,250]
		    	},
		    	province:{
		    			required:true
		    	},
		    	subtitle:{
	    			rangelength:[2,250]
		    	},
		    	content:{
	    			required:true, 
	    			maxlength:4000
		    	},
		    	dateSend:{
		    		required:function(){
		    			if($("input:radio:checked").val()==0){
		    				return true;
		    			}else{
		    				return false;
		    			}
		    		}
		    	},
		    	dateEndSend:{
		    		required:function(){
		    			if($("input:radio:checked").val()==0){
		    				return true;
		    			}else{
		    				return false;
		    			}
		    		}
		    	}
		    
	    	},
	    	messages:{
	    		title:{
	    			required:"标题未填写", 
	    			rangelength:"标题长度为  2-250  个字符"
	    		},
	    		province:{
		    			required:"请至少选择一个区域"
		    	},
	    		subtitle:{
	    			rangelength:"副标题长度为  2-250  个字符"
	    		},
	    		content:{
	    			required:"文章内容未填写", 
	    			maxlength:"文章内容长度为 4000  个字符"
	    		},
	    		dateSend:{
		    		required:"开始时间未填写"
		    	},
		    	dateEndSend:{
		    		required:"结束时间未填写"	
		    	}
	    	}}
    	};
    	 
    	var formId=["editsellerMsgForm"];
    	var formHandle={
    			"editsellerMsgForm":saveSellseMsg
    	}
		for(var i=0;i<formId.length;i++){
			validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
		}
    	function formSubmit(form){
    		return formHandle[form];
    	}
    	$("input:radio").click(function(){//清理开始时间和结束时间
    		if($("input:radio:checked").val()==1){
    			$("#dateSend").val("");
    			$("#dateEndSend").val("");
    			console.info("dateEndSend:"+$("#dateEndSend").val());
    		}
    	})
    }


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
			inserTitle(' > <span><a href="businessman/sellerMsg/add/init.jhtml?isType=add" target="right">添加商家站内消息</a>','addJointInfo',false);
		 }else if($('#isType').val() ==  'update'){
			inserTitle(' > <span><a href="businessman/sellerMsg/update/init.jhtml?msgId='+$('#msgId').val()+'" target="right">编辑商家站内消息</a>','editJointInfo',false);
		 }else{
			 inserTitle(' > <span>查看商家站内消息','editJointInfo',false);
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
			isChosen : true,
			isDisabled : true
		});
		 
		//商圈
		$("#zoneid").chosenObject({
			id : "zoneid",
			hideValue : "bid",
			showValue : "title",
			url : "common/business/businessInfo.jhtml",
			isChosen:true,
			defaultValue:"-- 请选择商圈 --"
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
		});
	}
	
	/**
	 * 保存站内消息
	 */
   var saveSellseMsg = function (){
	   showSmConfirmWindow(function() {
		   var url ;
			var data = jsonFromt($('#editsellerMsgForm').serializeArray());
			var area = $("#editsellerMsgForm").find("select[name='area']").val().toString();
			var city = $("#editsellerMsgForm").find("select[name='city']").val().toString();
			var province = $("#editsellerMsgForm").find("select[name='province']").val().toString();
			data.area = area;
			data.city = city;
			data.province = province;
			
			if(!valiImgData('#editsellerMsgForm',data)){
				if((data.dateSend && data.dateEndSend) && !databj(data.dateSend,data.dateEndSend)){
					dataError("#dateEndSendId","结束日期应晚于开始日期");
					return false;
				}else{
					datasuccess("#dateEndSendId");
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
				}
		   }
	   },"您确定要发送该消息？");
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
		    	subtitle:{
	    			rangelength:[2,250]
		    	},
		    	content:{
	    			required:true, 
	    			maxlength:4000
		    	}
	    	},
	    	messages:{
	    		title:{
	    			required:"标题未填写", 
	    			rangelength:"标题长度为  2-250  个字符"
	    		},
	    		subtitle:{
	    			rangelength:"副标题长度为  2-250  个字符"
	    		},
	    		content:{
	    			required:"文章内容未填写", 
	    			maxlength:"文章内容长度为 4000  个字符"
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
    }


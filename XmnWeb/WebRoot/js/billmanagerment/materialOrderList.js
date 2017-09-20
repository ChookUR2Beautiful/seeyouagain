var materialOrderPayList;
var orderNoPayList;
$(document).ready(function() {
	materialOrderPayList = $('#materialOrderPayList').page({
		url : 'billmanagerment/material/order/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchMaterialForm'
	});
	
	inserTitle(' > 订单管理 > <a href="billmanagerment/material/order/init.jhtml" target="right">物料订单</a>','allbillSpan',true);
	
	validate();//验证发货单号
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条物料订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchPayBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[
		    {
				title : " 订单编号",// 标题
				name : "order_sn",
				width : 170,// 宽度
				type:"string"//数据类型
				
			},{
				title : "订单状态",// 标题
				name : "bstatus",
				//sort : "up",
				width : 160,// 宽度
				type:"string"//数据类型
				
			},{
				title : "用户ID",// 标题
				name : "uid",
				width : 150,// 宽度
				type:"number"//数据类型
			},{
				title : "商户ID",// 标题
				name : "mid",
				width : 150,// 宽度
				type:"number"//数据类型
//			},{
//				title : "商户名称",// 标题
//				name : "sellername",
//				width : 200,// 宽度
//				type:"string"//数据类型
			},{
				title : "订单总额",// 标题
				name : "amount",
				width : 100,// 宽度
				type:"number"//数据类型
				
			},{
				title : "现金支付总额",// 标题
				name : "cash",
				//sort : "up",
				width : 120,// 宽度
				type:"number"//数据类型
				
			},{
				title : "余额支付总额",// 标题
				name : "balance",
				//sort : "up",
				width : 120,// 宽度
				type:"number"//数据类型
				
			},{
				title : "积分支付总额",// 标题
				name : "integral",
				width : 120,// 宽度
				leng : 8,//显示长度
				type:"number"//数据类型
				
			},{
				title : "支付方式",// 标题
				name : "payment_type",
				//sort : "up",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "下单时间",// 标题
				name : "create_time_str",
				//sort : "up",
				width : 170,// 宽度
				type:"string"//数据类型
				
			},{
				title : "更新时间",// 标题
				name : "modify_time_str",
				//sort : "up",
				width : 170,// 宽度
				type:"string"//数据类型
			},{
				title : "发货时间",// 标题
				name : "delivery_time_str",
				//sort : "up",
				width : 170,// 宽度
				type:"string"//数据类型
			},{
				title : "用户手机号",// 标题
				name : "mobile",
				//sort : "up",
				width : 120,// 宽度
				leng : 8,//显示长度
				type:"string"//数据类型
			},
			{
				title : "物流单号",// 标题
				name : "courier_number",
				//sort : "up",
				width : 150,// 宽度
				type:"string"//数据类型
			}, 
			{
				title : "配送方式",// 标题
				name : "courier_type_str",
				//sort : "up",
				width : 120,// 宽度
				type:"string",//数据类型
			},{
				title : "收货人",// 标题
				name : "consignee",
				//sort : "up",
				width : 150,// 宽度
				type:"string"//数据类型
			},
			{
				title : "配送地址",// 标题
				name : "address",
				//sort : "up",
				width : 500,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(value!=null){
						var content  = '<input class="form-control" value = "' + value + '" style = "width:80%; height:26px; text-align :center;" disabled="disabled"/>';
						content += '&nbsp;&nbsp;&nbsp;<a onclick = "updateAddress(this);" style="cursor: pointer;">修改</a>';
						content += '&nbsp;&nbsp;<a onclick = "saveAddress(this,'+ data.id +');" style="cursor: pointer;">保存</a>';
						return content;
					}else{ 
						return "-";
					}
				}
			}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["shipment", "confirmReceive", "init"],// 不需要选择checkbox处理的权限
				width : 250,// 宽度
				// 当前列的中元素
				cols : [{
							title : "下载贴图照片",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "downLoadStick.jhtml",// url
								param : ["mid","sellername"],// 参数
								permission : "init"// 列权限
						    }
						},
						{
							title : "订单详情",// 标题
							linkInfoName : "modal",
							linkInfo : {
								modal : {
									url : "billmanagerment/material/order/init/edit.jhtml",// url
									position:"60px",// 模态框显示位置
									width:"800px"
								},
								param : ["id"],// 参数
								permission : "init"// 列权限
							}
				      },{
						title : "确认收货",// 标题
						linkInfoName : "href",
						linkInfo : {
						href : "billmanagerment/material/order/getship.jhtml",// url
						param : ["id"],// 参数
						permission : "confirmReceive"// 列权限
					    },
						customMethod : function(value, data){
							if(data.status==3){
								return "<a href=\"javascript:confirmReceive('"+data.id+"')\">" + "确认收货" + "</a>";
							}else{
								return '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
							}
						}
					  },{
						title : "发货",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "billmanagerment/material/order/shipment.jhtml",// url
								position:"60px",// 模态框显示位置
								width:"800px"
							},
							param : ["id"],// 参数
							permission : "shipment"
							} ,// 列权限
							customMethod : function(value, data){
								if(data.status==1){
									return "<a href=\"javascript:shipment('"+data.id+"')\">" + "发货" + "</a>";
									
								}else{
									return '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
								}
							}
					    }
				      ]
	}},permissions);
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

/**
 * 日期插件
 */
$(function() {
	var today = new Date();
	var todaystr = addDate(today, 0);
	function addDate(date, days) {
		var d = new Date(date);
		d.setDate(d.getDate() + days);
		var month = d.getMonth() + 1;
		var day = d.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var val = d.getFullYear() + "-" + month + "-" + day;
		return val;
	}
});
/**设置隐藏域的值
 * @param bid
 */
function test(bid){
	$("#fhModal").modal();
	$("#ddnumber").val(bid);
}

 /**
  * 导出订单
  */
	$("#exportSubOrder").click(function(){
		$("#exportSubOrderModal").modal();
	});
	/**
	 * 确认导出
	 */
	$("#exportconfirm").click(function() {
		var sdate = $("#exportSubSdate").val();
		var edate = $("#exportSubEdate").val();
		if(valiSubDate()){
			if((null != sdate && "" != sdate)||(null != edate && "" != edate)){
				//post提交请求
				var url = "billmanagerment/material/order/init/list/export.jhtml";
				var data = "exsdate="+sdate+"&exedate="+edate;
					$form = $("#exporsubform").attr("action","billmanagerment/material/order/init/export.jhtml");
					$form[0].submit();
					$("#exportSubSdate").val("");
					$("#exportSubEdate").val("");
					$("#exportSubSdate").css('border','1px solid #ccc');
					$("#exportSubEdate").css('border','1px solid #ccc');
					destroyPopover($("#exportSubSdate"));
					destroyPopover($("#exportSubEdate"));
					$('#exportSubOrderModal').modal('hide')
			}
		}else{
			if((null == sdate || "" == sdate)&&(null == edate || "" == edate)){
				alert("下单时间不能为空!");
			}
			return false;
		}
	
})

  /**
   * 验证导出下单时间
   */
  function valiSubDate(){
  	$("#exportSubSdate").css('border','1px solid #ccc');
  	$("#exportSubEdate").css('border','1px solid #ccc');
  	destroyPopover($("#exportSubSdate"));
  	destroyPopover($("#exportSubEdate"));
  	var sdate = $("#exportSubSdate").val();
  	var edate = $("#exportSubEdate").val();
  	if((null != sdate && "" != sdate)||(null != edate && "" != edate)){
  		if((null != sdate && "" != sdate)&&(null != edate && "" != edate)){
  			var date1 = new Date(sdate);
  			var sdateMonth = date1.getMonth()+1;
  			var date2 = new Date(edate);
  			var edateMonth = date2.getMonth()+1;
  			if(true){
  				$("#exportSubSdate").css('border','1px solid #ccc');
  				$("#exportSubEdate").css('border','1px solid #ccc');
  				destroyPopover($("#exportSubSdate"));
  				destroyPopover($("#exportSubEdate"));
  				return true;
  			}else{
  				submitDataError($("#exportSubSdate"),"暂时不支持跨月导出!");
  				submitDataError($("#exportSubEdate"),"暂时不支持跨月导出!");
  				return false;
  			}
  		}else{
  			return true;
  		}
  	}
  }

/**
 * 取消导出
 */
$("#exportsubconcel").click(function(){
	$("#exportSubSdate").val("");
	$("#exportSubEdate").val("");
	$("#exportSubSdate").css('border','1px solid #ccc');
	$("#exportSubEdate").css('border','1px solid #ccc');
	destroyPopover($("#exportSubSdate"));
	destroyPopover($("#exportSubEdate"));
	$('#exportSubOrderModal').modal('hide')
});
	
/**
 * 取消模态框
 */
$("#fhconcel").click(function(){
	$('#subfhModal').modal('hide');
	$("#courierType").val("");
	$("#courierNumberId").val("");
	$("label.error").hide();
	$("#courierNumberId").attr("placeholder",'');
	$("#courierType").css('border','1px solid #ccc');
	destroyPopover($("#courierType"));
});
	
/**
 * 关闭导出模态框按钮
 */
$("#exportSubOrderModal").click(function (){
	$("#exportSdate").val("");
	$("#exportEdate").val("");
});

/**
 * 确定收货操作
 */
 function confirmReceive(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
	         url : "billmanagerment/material/order/getship.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'id=' + id,
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				materialOrderPayList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"确定已收到货物？");
 }
 
 /**
  * 确定收货操作
  */
  function shipment(id){
	$("#subfhModal").modal();
	$("#id").val(id);
 	 
  }

  /**
   * 关闭发货模态框清空表单数据
   */
  $("#closeFhModal").click(function(){
  	$("#courierNumberId").val("");
  	$("label.error").hide();
  	$('#subfhModal').modal('hide');
  	$("#courierType").val("");
  	$("#courierNumberId").attr("placeholder",'');
  	$("#courierType").css('border','1px solid #ccc');
  	destroyPopover($("#courierType"));
  });
  /**
   * 模态框
   */
  $("#fhconfirm").click(function() {
  	var r = $('#fhform').valid();
  	var courierType = $("#courierType").val();
  	if(courierType==""){
  		submitDataError($("#courierType"),"请选择快递公司!");
  		return false;
  	}
  	console.log($("#courierNumberId"));
  	if(r){
  		$.ajax({
  			url : "billmanagerment/material/order/shipment/save.jhtml",
  			type : "post",
  			dataType : "json",
  			data:'id=' + $("#id").val() + '&courier_type='+ $("#courierType option:selected").val() + '&courier_number='+ $("#courierNumberId").val(),
  			success : function(data) {
  				if (data.success) {
  					showSmReslutWindow(data.success, data.msg);
	     			$("#courierNumberId").val("");
  					$("#courierType").val("");
  					$("#courierNumberId").attr("placeholder",'');
  					$('#subfhModal').modal('hide');
	     			setTimeout(function() {
	     				materialOrderPayList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(data.msg);
	     		}
  			}
  		});
  	}else{
//  		alert("请正确填写物流单号再提交");
  		return false;
  	}
  })
	
  
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
  
  /**
   * 验证发货表单
   */
  function validate(){
  	//验证发货表单
      $("#fhform").validate({
	  	 debug: true,
	  	 onfocusout:function(element) {$(element).valid()},
	  	 errorElement: "label",
	  	 errorClass:"error",
	  	 rules:{
	  		 courierNumber:{
	               required:true,
	               wlnumRule:true
	           }
	       },
	       messages:{
	      	 courierNumber:{
	               required:"物流单号不能为空"
	           }                                  
	       }
     });
  }
  
  /**
   * 物流单号正则
   */
  $.validator.addMethod("wlnumRule", function(value, element) { 
  	var length = value.length;
  	var courierType = $("#courierType").val();
  	var courierNumber = /^[A-Za-z0-9]{7,21}$/;
  	return this.optional(element) || ((7 <= length <= 20) && courierNumber.test(value));
  },"物流单号格式错误");
  
  
  /**
   * 当选择运输方式之后改变格式提示
   */
  $("#courierType").change(function(){
  	var courierType = $("#courierType").val();
  	console.log(courierType);
  	if("sfexpress"==courierType){
  		$("#courierNumberId").attr("placeholder",'例：020900000189');
  	}else if("zto"==courierType){
  		$("#courierNumberId").attr("placeholder",'例：618148513844');
  	}else if("yto"==courierType){
  		$("#courierNumberId").attr("placeholder",'例：7340687080');
  	}else if("yunda"==courierType){
  		$("#courierNumberId").attr("placeholder",'例：1100032620849');
  	}else if("sto"==courierType){
  		$("#courierNumberId").attr("placeholder",'以36、46、88等开头');
  	}else if("ttkdex"==courierType){
  		$("#courierNumberId").attr("placeholder",'例：00001300004129');
  	}else if("jd"==courierType){
  		$("#courierNumberId").attr("placeholder",'例：1681533252 或 12150374219');
  	}else if(""==courierType){
  		$("#courierNumberId").attr("placeholder",'');
  		$("label.error").hide();
  	}
  });
  
  /**
   * 查看二维码
   * @param 
   */
  function doorMatrixView(mid,sellername){
  		var data = 'sellerid=' + mid + '&sellername='+ encodeURIComponent(sellername) + '&aid=';
  		var base = $("base").attr("href");//获取base标签的href属性值 
  		showCodeWindow('<img width="512" alt="二维码" src="'+base+'/getDoorMatrix.jhtml?'+data+'">');
  }
  
  function tableMatrixView1(mid,sellername){
		var data = 'sellerid=' + mid + '&sellername='+ encodeURIComponent(sellername) + '&aid=';
		var base = $("base").attr("href");//获取base标签的href属性值 
		showCodeWindow('<img width="512" alt="二维码" src="'+base+'/getTableMatrix1.jhtml?'+data+'">');
  }
  
  function tableMatrixView2(mid,sellername){
		var data = 'sellerid=' + mid + '&sellername='+ encodeURIComponent(sellername) + '&aid=';
		var base = $("base").attr("href");//获取base标签的href属性值 
		showCodeWindow('<img width="512" alt="二维码" src="'+base+'/getTableMatrix2.jhtml?'+data+'">');
  }
  
  /**
   * 弹出二维码模态框
   */
  function showCodeWindow(content) {
  	$('#lg_edit_window').find('.modal-title').html('二维码');
  	$('#lg_edit_window').find('.modal-body').html('<div class="content" align="center">' + content + '</div>');
  	$('#lg_edit_window').modal();
  }
  
  function updateAddress(_this){
	  var par = $(_this).parent();
	  par.find("input").removeAttr("disabled").focus().select();
  }

  function saveAddress(_this,id){
	  var par = $(_this).parent();
	  var address = par.find("input").val();
	  console.log(address);
	  console.log("saveAddress");
	  $.ajax({
			url : "billmanagerment/material/order/address/update.jhtml",
			type : "post",
			dataType : "json",
			data : {"id":id,"address":address},
			success : function(data) {
				if (data.success) {
					showSmReslutWindow(data.success, data.msg);
	     			setTimeout(function() {
	     				materialOrderPayList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(data.msg);
	     		}
			}
		});
  }
  
  
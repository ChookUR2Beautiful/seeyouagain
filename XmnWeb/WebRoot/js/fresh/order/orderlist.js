var orderList;
$(document).ready(function() {
	orderList = $('#orderList').page({
		url : 'fresh/order/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchBillForm'
	});
	
	inserTitle(' > 订单管理 > <a href="fresh/order/init.jhtml" target="right">所有订单</a>','allbillSpan',true);
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "商品数量",// 标题
					name : "wareNum",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : " 订单编号",// 标题
				name : "bid",
				width : 150,// 宽度
				type:"number"//数据类型
				
		},{
			title : "订单状态",// 标题
			name : "bstatus",
			//sort : "up",
			width : 160,// 宽度
			type:"string"//数据类型
			
		},{
			title : "用户手机号",// 标题
			name : "phoneid",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "用户ID",// 标题
			name : "uid",
			width : 150,// 宽度
			type:"number"//数据类型
		},{
			title : "支付总额",// 标题
			name : "money",
			width : 100,// 宽度
			type:"string"//数据类型
			
		},{
			title : "返利支付",// 标题
			name : "profit",
			//sort : "up",
			width : 100,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "佣金支付",// 标题
			name : "commision",
			//sort : "up",
			width : 100,// 宽度
			type:"string"//数据类型
			
		},{
			title : "赠送支付",// 标题
			name : "give_money",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
			
		},
		{
			title : "会员卡支付",// 标题
			name : "cardpay",
			//sort : "up",
			width : 100,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "第三方支付",// 标题
			name : "payment",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
			
		},
		{
			title : "积分支付",// 标题
			name : "integral",
			//sort : "up",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
			
		},
		{
			title : "支付方式",// 标题
			name : "paytype",
			//sort : "up",
			width : 100,// 宽度
			type:"number"//数据类型
			/*customMethod : function(value, data) {
				if(data.pstatus==0){
					return "待上线";
				} 
				if(data.pstatus==1){
					return "已上线";
				} 
				if(data.pstatus==2){
					return "已售罄";
				}
				if(data.pstatus==3){
					return "已下线";
				}
				return "-";
			}*/
		},
		{
			title : "支付编号",// 标题
			name : "payid",
			//sort : "up",
			width : 180,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "支付流水号",// 标题
			name : "number",
			//sort : "up",
			width : 280,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				if(data.number != 'null' && data.number != null && data.number != undefined){
					return data.number;
				}else{
					return "-";
				}
			}
		},
		{
			title : "客户端类型",// 标题
			name : "phoneTypeStr",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "下单时间",// 标题
			name : "xddate",
			//sort : "up",
			width : 170,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "支付时间",// 标题
			name : "zfdate",
			//sort : "up",
			width : 170,// 宽度
			type:"string"//数据类型
			/*customMethod : function(value, data) {
				if(data.choice==0){
					return "否";
				} 
				if(data.choice==1){
					return "是";
				} 
				return "-";
			}*/
		},
		{
			title : "物流单号",// 标题
			name : "logistics",
			//sort : "up",
			width : 170,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				if(data.logistics!=null){
					return "<a href='javascript:kuaidi(\""+data.express+"\",\""+data.logistics+"\")' data-toggle=\"popover\" title=\"点击查询物流信息\">"+ data.logistics + "</a>";
				}else{ 
				 return "-";
				}
			}
		},
		{
			title : "配送方式",// 标题
			name : "expressStr",
			//sort : "up",
			width : 170,// 宽度
			type:"string",//数据类型
		},
		{
			title : "物流状态",// 标题
			name : "wlstatus",
			//sort : "up",
			width : 80,// 宽度
			type:"string",//数据类型
		},
		{
			title : "发货时间",// 标题
			name : "fhdate",
			//sort : "up",
			width : 170,// 宽度
			type:"string",//数据类型
		},
		{
			title : "收货时间",// 标题
			name : "shdate",
			//sort : "up",
			width : 170,// 宽度
			type:"string",//数据类型
		},
		{
			title : "收货人姓名",// 标题
			name : "username",
			//sort : "up",
			width : 170,// 宽度
			type:"string"//数据类型
		},
		{
			title : "收货人电话",// 标题
			name : "mobile",
			//sort : "up",
			width : 170,// 宽度
			type:"string"//数据类型
		},
		{
			title : "配送地址",// 标题
			name : "address",
			//sort : "up",
			width : 500,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				if(data.logistics!=null){
					return "<a href='javascript:;'  disabled='disabled' style='color:#000000;display: block;width: auto;word-break: break-all;word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow:hidden;' data-toggle=\"popover\" title=\""+value+"\">"+ value + "</a>";
				}else{ 
				 return "-";
				}
			}
		},
		/*{
			title : "会员卡编号",// 标题
			name : "noId",
			//sort : "up",
			width :170,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data){
				if(data.noId==0){
					return "-";
				}else if(data.noId==null){
					return "-";
				}else if(data.noId==undefined){
					return "-";
				}else{
					return data.noId;
				}
			}
		},*/
		/*{
				title : "用户手机",// 标题
				name : "phoneid",
				width : 50,// 宽度
				leng : 8,//显示长度
				type:"stirng",//数据类型
				isLink : true,// 表示当前列是否是超链接 true:是 false：不是
				// 只有当isLink为true时 才有效
				link : {
					linkInfoName : "href", // href , modal ,method
					linkInfo : {
						href : "billmanagerment/allbill/view/init", // 表示超链接调用的连接
					},
					param : ["bid"],// 参数
					permission : "detail"// 单列权限
				}
			}*/],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["check","orderRefund"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
						title : "退款",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "fresh/order/refund.jhtml",// url
							param : ["id"],// 参数
							permission : "orderRefund"// 列权限
						},
						customMethod : function(value, data){
	                        if((data.status==6)||(data.status==8)){
	                            var value1 = "<a href='javascript:orderRefund(\"" + data.bid + "\",\"" + data.money + "\")'>" + "退款" + "</a>";
	                            return value1;
	                        }else{
	                        	var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
								return value2;
	                        }
	                    }
					},
					{
						title : "订单详情",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "fresh/order/check.jhtml",// url
								position:"60px",// 模态框显示位置
								width:"800px"
							},
							param : ["bid"],// 参数
							permission : "check"// 列权限
					    }
				      }]
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

/**
 * 退款操作
 */
 function orderRefund(bid,money){
	 showSmConfirmWindow(function (){
		 $.ajax({
	         url : "fresh/order/refund.jhtml",
	         type : "post",
	         dataType : "json",
	         async:false,
	         data:'orderNumber=' + bid + '&money='+ money + '&remark=""' + '&bid='+ bid,
	         success : function(result) {
	        	 if (result.success) {
//	        		 orderList.reload();
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				orderList.reload();
	     			}, 3000);
	     			
					//支付宝打开页面（response不为空才打开）
					var response = result.data;
					console.info("res:"+response);
					if(null!=response && ""!=response.trim()){
						
						var body = window.document.body;
						var responseHtml = $.parseHTML(response);
						$.each(responseHtml,function(){
							if(this.nodeName.toLowerCase()=="form"){
								$(body).append($(this));
								var id = $(this).attr("id");
								$(this).attr("target","_blank");
								var $form = $(body).find($(this));
								if($form.length>0){
									$form.submit();
									$form.remove();
								} 
								return;
							}
						}); 
					}
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"确定要退款吗？");
	 
 }

/**
 * 导出订单
 */
$("#export").click(function(){
	$("#exportModal").modal();
});

/**
 * 确认导出
 */
$("#exportconfirm").click(function() {
	var sdate = $("#exportSdate").val();
	var edate = $("#exportEdate").val();
	if(valiDate()){
		if((null != sdate && "" != sdate)||(null != edate && "" != edate)){
			//post提交请求
			var url = "fresh/order/checkdata.jhtml";
			var data = "exsdate="+sdate+"&exedate="+edate;
			$.post(url, data, function(result) {
				if (result.success) {
					$("#exportModal").modal('hide');
					$form = $("#exporform").attr("action","fresh/order/export.jhtml?exsdate="+sdate+"&exedate="+edate);
					$form[0].submit();
					$("#exportSdate").val("");
					$("#exportEdate").val("");
				} else {
					window.messager.warning(result.msg);
				}
			}, "json");
			
		}
	}else{
		if((null == sdate || "" == sdate)&&(null == edate || "" == edate)){
			alert("下单时间不能为空!");
		}
		return false;
	}
	
})
/**
 * 取消导出
 */
$("#exportconcel").click(function(){
	$("#exportSdate").val("");
	$("#exportEdate").val("");
	$("#exportSdate").css('border','1px solid #ccc');
	$("#exportEdate").css('border','1px solid #ccc');
	destroyPopover($("#exportSdate"));
	destroyPopover($("#exportEdate"));
	$('#exportModal').modal('hide')
});
/**
 * 关闭导出模态框按钮
 */
$("#closeExportModal").click(function (){
	$("#exportSdate").val("");
	$("#exportEdate").val("");
});
/**
 * 验证导出下单时间
 */
function valiDate(){
	$("#exportSdate").css('border','1px solid #ccc');
	$("#exportEdate").css('border','1px solid #ccc');
	destroyPopover($("#exportSdate"));
	destroyPopover($("#exportEdate"));
	var sdate = $("#exportSdate").val();
	var edate = $("#exportEdate").val();
	if((null != sdate && "" != sdate)||(null != edate && "" != edate)){
		if((null != sdate && "" != sdate)&&(null != edate && "" != edate)){
			var date1 = new Date(sdate);
			var sdateMonth = date1.getMonth()+1;
			var date2 = new Date(edate);
			var edateMonth = date2.getMonth()+1;
			if(sdateMonth == edateMonth){
				$("#exportSdate").css('border','1px solid #ccc');
				$("#exportEdate").css('border','1px solid #ccc');
				destroyPopover($("#exportSdate"));
				destroyPopover($("#exportEdate"));
				return true;
			}else{
				submitDataError($("#exportSdate"),"暂时不支持跨月导出!");
				submitDataError($("#exportEdate"),"暂时不支持跨月导出!");
				return false;
			}
		}else{
			return true;
		}
	}
}
	 
	
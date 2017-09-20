var orderList;
$(document).ready(function() {
	orderList = $('#materialOrderNoPayList').page({
		url : 'billmanagerment/material/order/init/list.jhtml',
		success : successSubOrder,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchBillNoPayForm'
	});
	
	inserTitle(' > 订单管理 > <a href="billmanagerment/material/order/init/list.jhtml" target="right">所有订单</a>','allbillSpan',true);
	
	
	
	
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successSubOrder(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条生鲜订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillNoPayForm").serialize());
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
				width : 150,// 宽度
				type:"string"//数据类型
				
			},{
				title : "订单状态",// 标题
				name : "bstatus",
				//sort : "up",
				width : 110,// 宽度
				type:"string"//数据类型
				
			},{
				title : "用户ID",// 标题
				name : "uid",
				width : 150,// 宽度
				type:"number"//数据类型
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
				width : 100,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.payment_type=='1000001'){
						return "支付宝SDK支付";
					} 
					if(data.payment_type=='1000003'){
						return "微信SDK支付";
					} 
					if(data.payment_type=='1000013'){
						return "公众号支付";
					}
					if(data.payment_type=='1000000'){
						return "钱包支付";
					}
					if(data.payment_type=='1000022'){
						return "支付宝APP支付（鸟人科技）";
					}
					if(data.payment_type=='1000023'){
						return "支付宝WAP支付（鸟人科技）";
					}
					if(data.payment_type=='1000024'){
						return "微信APP支付（鸟人科技）";
					}
					if(data.payment_type=='1000025'){
						return "微信公众号支付（鸟人科技）";
					}
					return "-";
				}
			},{
				title : "下单时间",// 标题
				name : "create_time_str",
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
				width : 120,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.logistics!=null){
						return "<a href='javascript:kuaidi(\""+data.express+"\",\""+data.logistics+"\")' data-toggle=\"popover\" title=\"点击查询物流信息\">"+ data.logistics + "</a>";
					}else{ 
					 return "-";
					}
				}
			},
//			{
//				title : "配送方式",// 标题
//				name : "expressStr",
//				//sort : "up",
//				width : 170,// 宽度
//				type:"string",//数据类型
//			},
			{
				title : "配送地址",// 标题
				name : "address",
				//sort : "up",
				width : 120,// 宽度
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
				queryPermission : ["update","refund"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
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
///**
// * 验证导出下单时间
// */
//function valiDate(){
//	$("#exportSdate").css('border','1px solid #ccc');
//	$("#exportEdate").css('border','1px solid #ccc');
//	destroyPopover($("#exportSdate"));
//	destroyPopover($("#exportEdate"));
//	var sdate = $("#exportSdate").val();
//	var edate = $("#exportEdate").val();
//	if((null != sdate && "" != sdate)||(null != edate && "" != edate)){
//		if((null != sdate && "" != sdate)&&(null != edate && "" != edate)){
//			var date1 = new Date(sdate);
//			var sdateMonth = date1.getMonth()+1;
//			var date2 = new Date(edate);
//			var edateMonth = date2.getMonth()+1;
//			if(sdateMonth == edateMonth){
//				$("#exportSdate").css('border','1px solid #ccc');
//				$("#exportEdate").css('border','1px solid #ccc');
//				destroyPopover($("#exportSdate"));
//				destroyPopover($("#exportEdate"));
//				return true;
//			}else{
//				submitDataError($("#exportSdate"),"暂时不支持跨月导出!");
//				submitDataError($("#exportEdate"),"暂时不支持跨月导出!");
//				return false;
//			}
//		}else{
//			return true;
//		}
//	}
//}
	 
	
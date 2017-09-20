var billbargainList;
$(document).ready(function() {
	billbargainList = $('#billbargainList').page({
		url : 'billmanagerment/allbillbargain/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
	
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minView :2,
		format : 'yyyy-mm-dd'
	});
	inserTitle(' > 订单管理 > <a href="billmanagerment/allbillbargain/init.jhtml" target="right"> 积分商品订单列表</a>','bargainProductSpan',true);	
});

$(function(){
	 //重置
   $("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
});
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条积分商品订单&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
    	tableClass :"table-bordered table-striped info",
    	callbackParam : callbackParam,
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
				title : "订单编号",// 标题
				name : "bid",
				width : 200,// 宽度
				leng : 3,//显示长度
				type:"string",//数据类型					
				},{
					title : "商品编号",// 标题
					name : "bpid",
					width : 150,// 宽度
					type:"string"//数据类型
					
			    },{
					title : "积分商品名称",// 标题
					name : "pname",
					width : 110,// 宽度
					type:"string"//数据类型
		
				},{
					title : "商品价格",// 标题
					name : "price",
					width : 120,// 宽度
					leng : 8,//显示长度
					type:"string"//数据类型
				},{
					title : "数量",// 标题
					name : "num",
					width : 150,// 宽度
					type:"number"//数据类型
				},{
					title : "实际支付金额",// 标题
					name : "amount",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "积分支付金额",// 标题
					name : "integral",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "余额支付金额",// 标题
					name : "balance",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "订单总金额",// 标题
					name : "allAmount",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "用户编号",// 标题
					name : "uid",
					width : 150,// 宽度
					type:"number"//数据类型
				},{
					title : "用户昵称",// 标题
					name : "uname",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "用户手机",// 标题
					name : "phoneid",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "商家编号",// 标题
					name : "sellerid",
					width : 150,// 宽度
					type:"number"//数据类型
				},{
					title : "商家名称",// 标题
					name : "sellername",
					width : 150,// 宽度
					type:"string"//数据类型
				},{
					title : "记录时间",// 标题
					name : "sdateStr",
					width : 200,// 宽度
					type:"string"//数据类型
					
				},{
					title : "支付状态",// 标题
					name : "statusStr",
					width : 100,// 宽度
					type:"string"//数据类型
					
				},{
					title : "兑换状态",// 标题
					name : "bstatusStr",
					width : 100,// 宽度
					type:"string"//数据类型
					
				},
				{
					title : "分账后剩余金额",// 标题
					name : "xmnaioMoney",
					width : 150,// 宽度
					type:"string"//数据类型
					
				},
				{
					title : "用户所属商户分账金额",// 标题
					name : "bSerllerMoney",
					width : 160,// 宽度
					type:"string"//数据类型
					
				},
				{
					title : "经销商分账金额",// 标题
					name : "sellerAreaMoney",
					width : 160,// 宽度
					type:"string"//数据类型
					
				},
				{
					title : "商品成本",// 标题
					name : "sellerBackMoney",
					width : 110,// 宽度
					type:"string"//数据类型
					
				},
				{
					title : "分账状态",// 标题
					name : "hstatusText",
					width : 230,// 宽度
					type:"string"//数据类型
					
				},
				{
					title : "备注",// 标题
					name : "remark",
					//sort : "up",
					width : 100,// 宽度
					type:"stirng"//数据类型
				}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["check"],// 不需要选择checkbox处理的权限
			width : 130,// 宽度
			// 当前列的中元素
			cols : [
					  {
						title : "查看",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "billmanagerment/allbillbargain/view/init.jhtml",// url
								position:"60px",// 模态框显示位置
								width:"800px"
							},
							param : ["bid"],// 参数
							permission : "check"// 列权限
					    }
				      }/*,{
							title : "退款",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "billmanagerment/allbillbargain/refund.jhtml",// url
								param : ["id"],// 参数
								permission : "refund"// 列权限
							},
							customMethod : function(value, data){
		                        if((data.hstatus==9)){
		                            var value1 = "<a href='javascript:refund(\""+data.bid+"\",\""+data.allAmount+"\")'>" + "退款" + "</a>";
		                            return value1;
		                        }else{
		                        	var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
									return value2;
		                        }
		                    }
						}*/
			      ]
               }},permissions);
        }
/**
 * 导出订单
 */
	$("#export").click(function(){
		var url = "billmanagerment/allbillbargain/export.jhtml";
		$form = $("#searchForm").attr("action",url);
		$form[0].submit();
	});
	/**
	 * 退款操作
	 */
	/* function refund(bid,allAmount){
		 showSmConfirmWindow(function (){
			 $.ajax({
		         url : "billmanagerment/allbillbargain/refund.jhtml",
		         type : "post",
		         dataType : "json",
		         data:'orderNumber=' + subOrderSn + '&money='+ totalAmount + '&remark=""' + '&bid='+ subOrderSn,
		         success : function(result) {
		        	 if (result.success) {
		     			showSmReslutWindow(result.success, result.msg);
		     			setTimeout(function() {
		     				orderList.reload();
		     			}, 3000);
		     		} else {
		     			window.messager.warning(result.msg);
		     		}
		         }
		     });
		 },"确定要退款吗？");
	 }*/
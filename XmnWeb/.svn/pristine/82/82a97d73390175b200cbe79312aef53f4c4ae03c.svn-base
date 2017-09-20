var livePayOrderList;
var initListUrl = "liveCouponOrder/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveCouponOrder/manage/init.jhtml" target="right">直播券订单记录</a>',
			'userSpan', true);
	livePayOrderList = $("#livePayOrderList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//导出
	$("#export").click(function(){
		var path="liveCouponOrder/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	
	
});

function success(data, obj) {
	var formId = "shareForm", title = "直播券订单列表", subtitle = "个订单，",subtitle1 = "位，";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font>'
			+ '<font>【' + data.titleInfo.cidNumSum + '】' + subtitle1 + '&nbsp;</font>'
			+ '<font>总金额【' + data.titleInfo.totalAmountSum + '】'  + '&nbsp;</font>'
			+ '</caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
	    checkable : true,
	    // 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveCouponOrder/manage/update/init.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px"
						},
						param : ["id"],// 参数
						permission : "update"// 列权限
				    }
			      }
			]
		},
		cols : [ {
			title : "订单编号",
			name : "orderSn",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				return value;
			}
		},{
			title : "会员信息",
			name : "userInfo",
			type : "string",
			width : 150
		},{
			title : "粉丝券信息",
			name : "cnameInfo",
			type : "string",
			width : 150
		},{
			title : "购买来源",
			name : "buySourceStr",
			type : "string",
			width : 150
		}, {
			title : "订单状态",
			name : "status",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result="-";
				switch (value) {
				case 0:
					result = "待支付";
					break;
				case 1:
					result = "已支付";
					break;
				case 2:
					result = "支付失败";
					break;
				case 3:
					result = "已取消";
					break;
				default:
					break;
				}
				return result;
			}
		},/*   {
			title : "支付流水号",
			name : "payid",
			type : "string",
			width : 150
		}, */  {
			title : "支付方式",
			name : "paymentTypeStr",
			type : "string",
			width : 200,
		}, {
			title : "位数",
			name : "cidNum",
			type : "string",
			width : 100
		}, {
			title : "订单金额",
			name : "totalAmount",
			type : "string",
			width : 150
		}, {
			title : "实际支付金额",
			name : "realAmount",
			type : "string",
			width : 150
		}, {
			title : "下单时间",
			name : "createTimeStr",
			type : "string",
			width : 160
		}/*,{
			title : "余额支付金额",
			name : "balance",
			type : "string",
			width : 150
		} ,{
			title : "佣金支付金额",
			name : "commision",
			type : "string",
			width : 150
		} ,{
			title : "赠送支付金额",
			name : "zbalance",
			type : "string",
			width : 150
		} ,{
			title : "鸟豆支付金额",
			name : "beans",
			type : "string",
			width : 150
		} ,{
			title : "积分支付总额",
			name : "integral",
			type : "string",
			width : 150
		} , {
			title : "赠送预售抵用券",
			name : "retrunCouponAmount",
			type : "string",
			width : 150
		} , {
			title : "赠送积分",
			name : "returnIntegral",
			type : "string",
			width : 150
		}  , {
			title : "支付时间",
			name : "modifyTimeStr",
			type : "string",
			width : 160
		} , {
			title : "通告粉丝券配置编号",
			name : "anchorCid",
			type : "string",
			width : 160
		} , {
			title : "粉丝券ID",
			name : "cid",
			type : "string",
			width : 160
		} , {
			title : "粉丝券名称",
			name : "cname",
			type : "string",
			width : 160
		} , {
			title : "使用状态",
			name : "userStatusStr",
			type : "string",
			width : 160
		} , {
			title : "使用时间",
			name : "userTimeStr",
			type : "string",
			width : 160
		} , {
			title : "锁定时间",
			name : "lockTimeStr",
			type : "string",
			width : 160
		} , {
			title : "订单来源",
			name : "orderSourceStr",
			type : "string",
			width : 150
		}*/  ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "livePayOrder/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 livePayOrderList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 批量删除
  */
 $('#delete').click(function(){
 	var ids = livePayOrderList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'livePayOrder/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					livePayOrderList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
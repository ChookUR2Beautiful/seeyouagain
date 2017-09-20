var walletRecordList;
var commisionView="";
var balanceView="";
var depositView="";
//var initListUrl = "xmer/finance/walletRecord/list.jhtml";
var initListUrl = "xmer/finance/xmerWalletRecord/list.jhtml";
$(function() {
	inserTitle( ' > 寻蜜客管理 > <a href="xmer/manage/init.jhtml" target="right">寻蜜客成员管理</a> >佣金', 'walletRecordList', true);
	
	walletRecordList = $("#walletRecordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
	});
	
	//导出
	$("#export").click(function(){
		var path="xmer/finance/xmerWalletRecord/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});
	
	/**
	 * 初始化列访问权限,通过moneyType控制列隐藏
	 */
	initColsWidth();
	
});

/**
 * 控制列隐藏
 */
function initColsWidth(){
//	debugger;
	var rtype=$("#rtype").val();
	switch (rtype)
	{
		case "2"://佣金
			commisionView="walletRecordView";
		  break;
		case "0"://流水
			balanceView="walletRecordView";
		  break;
		default :
		  break;
	}
};

function success(data, obj) {
	var title = "钱包信息", subtitle = "条";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : null,
		data : data.content,
		caption : captionInfo,
		cols : [ {
			title : "订单号",
			name : "remark",
			type : "string",
			width : 150
		}, {
			title : "下单时间",
			name : "sdateStr",
			type : "string",
			width : 150
		}, {
			title : "佣金/元",
			name : "profit",
			type : "string",
			width : 150,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "javascript:void(0);"
				},
				permission : commisionView
			}
		}, {
			title : "流水收入/元",
			name : "profit",
			type : "string",
			width : 150,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "javascript:void(0);"
				},
				permission : balanceView
			}
		}, {
			title : "押金返还/元",
			name : "deposit",
			type : "string",
			width : 150,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "javascript:void(0);"
				},
				permission :depositView
			}
		}, {
			title : "类型",
			name : "rtypeStr",
			type : "string",
			width : 100
		} ]
	}, permissions);
	
}
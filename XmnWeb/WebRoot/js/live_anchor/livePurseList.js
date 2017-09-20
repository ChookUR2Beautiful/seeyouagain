var pageDiv;

$(document).ready(function() {
	/*加载数据*/
	pageDiv = $('#livePurseInfo').page({
		url: 'liveMember/manage/viewLivePurseInfo/list.jhtml',
		success: success,
		pageBtnNum: 10,
//		pageSize:10,
		paramForm: 'livePurseFromId'
	});
	
	inserTitle(' > 会员钱包记录信息','editsellerInfo',false);
	
});


//导出
$("#exportlivePurseList").click(function(){
	var path="liveMember/manage/exportPurseInfoList.jhtml";
	$form = $("#livePurseFromId").attr("action", path);
	$form[0].submit();
});	


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;全部钱包记录 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】条记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#sellerPackageFromId").serialize());
//	debugger;
	updateAddBtnHref("#addSellerPackageBto", callbackParam);

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data: data.content, 
		caption : captionInfo,
		checkable : false,
		cols:[{
			title : "类型",
			name : "option",
			// sort : "up",
			type : "string",
			width : 80,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case 1:
					result='增加';
					break;
				case 2:
					result='扣减';
					break;
				default:
					result='-';
					break;
				}
				return result;
			}
		}, {
			title : "虚拟货币",
			name : "type",
			// sort : "up",
			type : "string",
			width : 80,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case 1:
					result='鸟豆';
					break;
				case 2:
					result='鸟币';
					break;
				default:
					result='-';
					break;
				}
				return result;
			}
		}, {
			title : "数量",
			name : "money",
			type : "string",
			width : 80,
			leng : 8
		}, {
			title : "渠道",
			name : "rtype",
			type : "string",
			width : 80,
			leng : 8,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case 0:
					result='平台充值';
					break;
				case 2:				
				case 16:
					result='打赏';
					break;
				case 3:
					result='打赏返还';
					break;
				case 7:
					result='购买套餐';
					break;
				case 8:
					result='购买粉丝劵';
					break;		
				case 9:
					result='购买商品';
					break;
				case 14:
					result='推荐人充值返还';
					break;					
				case 15:
					result='买单消费';
					break;	
				case 19:
					result='红包返还';
					break;					
				default:
					result= data.rtype;
					break;
				}
				
				return result;
			}
		}, {
			title : "时间",
			name : "createTime",
			type : "string",
			width : 80
		}]
	},permissions);
	
};

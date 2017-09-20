var xmerList3;
$(document).ready(function() {
	xmerList3 = $('#xmerList3').page({
		url : 'xmer/manage/init/newlist.jhtml',
		success : success3,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForm3'
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success3(data, obj) {
	var picTitle;
	var typeTitle;
	var contentTitle;
	var sortTiltle;
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;平台寻蜜客总数：'+data.total+'人&nbsp;&nbsp;商户端出售总数：'+(data.titleInfo.sellCount)+'套&nbsp;&nbsp;平台店铺流水总额：'+(data.titleInfo.sellerCrrentCount)+'元</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm3").serialize());
	obj.find('div').eq(0).scrollTablel({
			checkable : false,
	    	identifier : "",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "会员编号",// 标题
					name : "uid",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型
			},{
				title : "会员名称",// 标题
				name : "nname",
				width : 150,// 宽度
				type:"string"//数据类型
				
			},{
				title : "手机号",// 标题
				name : "phone",
				//sort : "up",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "状态",// 标题
				name : "status",
				//sort : "up",
				width : 120,// 宽度
				leng : 8,//显示长度
				type:"number",//数据类型
					customMethod : function(value, data) {
						if(data.status==0){
							return "启用";
						} 
						if(data.status==1){
							return "停用";
						} 
						return "-";
					}
			},{
				title : "我的伙伴",// 标题
				name : "partner",
				//sort : "up",
				leng : 8,//显示长度
				type:"string",//数据类型
				isLink : true,
				link : {
					linkInfoName : "href",
					linkInfo : {
						href : "xmer/manage/directPartner/init.jhtml"
					},
					param : ["uid"],
					customParam:["objectOriented=7"],
					permission : "dp",
				},
				customMethod : function(value, data) {
					if(data.partner != null && data.partner != ""){
						return $(value).html(data.partner);
					}
					return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0</a>');
				},
				width : 120
			},{
				title : "我的店铺",// 标题
				name : "seller",
				//sort : "up",
				leng : 8,//显示长度
				type:"string",//数据类型
				isLink : true,
				link : {
					linkInfoName : "href",
					linkInfo : {
						href : "xmer/manage/xmerSeller/init.jhtml"
					},
					param : ["uid"],
					customParam:["saasType=3"],
					permission : "xmer",
				},
				customMethod : function(value, data) {
					if(data.seller != null && data.seller != ""){
						return $(value).html(data.seller);
					}
					return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0</a>');
				},
				width : 120
	
			},{
				title : "剩余软件套数/签约套数",// 标题
				name : "stockTotal",
				//sort : "up",
				width : 180,// 宽度
				leng : 8,//显示长度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.allSaas != null && data.allSaas != ""){
						var str;
						if(data.unUsedSaas != null && data.unUsedSaas != ""){
							str=data.unUsedSaas+"/"+data.allSaas;
						}else{
							str="-/"+data.allSaas;
						}
						return str;
					}
					return "-";
				}
			}]},permissions);
}
function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

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
	
$("#export3").click(function(){
		var url = "xmer/manage/export2.jhtml";
		$form = $("#searchForm3").attr("action",url);
		$form[0].submit();
	});

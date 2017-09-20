var vipCardList;
var reqUrl="businessman/vipCard/";
$(document).ready(function() {
	vipCardList = $('#vipCardList').page({
		url : reqUrl+'list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'selectVipCardForm'
	});
	
	inserTitle(' > 商户会员卡管理 > <a href="businessman/vipCard/listView.jhtml" target="right">商家会员卡信息</a>','allVipCardSpan',true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】种商户会员卡&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#selectVipCardForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	//identifier : "bid",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "卡序号",// 标题
					name : "id",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : "卡名称",// 标题
				name : "cardName",
				width : 180,// 宽度
				type:"string"//数据类型
			},{
				title : "商家编号",// 标题
				name : "sellerId",
				width : 100,// 宽度
				type:"string"//数据类型
				
			},{
				title : "商家名称",// 标题
				name : "sellerName",
				width : 150,// 宽度
				type:"string"//数据类型
				
			},{
				title : "适用商户数",// 标题
				name : "sellerNum",
				//sort : "up",
				width : 100,// 宽度
				type:"number",//数据类型
				isLink : true,
				link : {
					required : true,
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : reqUrl+"list/supporSellerListView.jhtml",
							position : "60px",
							width : "auto",	
							hight : "auto",
							title : "适用商户" 
						}
					},
					param : ["sellerId"],
					permission : "supporSeller",
				}
			},{
				title : "充值功能",// 标题
				name : "isPay",
				width : 100,// 宽度
				leng : 8,//显示长度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.isPay==0){
						return "开启";
					} 
					if(data.isPay==1){
						return "关闭";
					} 
					return "-";
				}
			},{
				title : "充值方案",// 标题
				name : "prepaidScheme",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "会员特权",// 标题
				name : "rights",
				width : 200,// 宽度
				type:"string"//数据类型
				
			},{
				title : "会员卡张数",// 标题
				name : "activeNum",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
				
			},{
				title : "卡总余额",// 标题
				name : "notUsedAmount",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
				
			},{
				title : "卡充值总额",// 标题
				name : "amount",
				width : 100,// 宽度
				leng : 8,//显示长度
				type:"number"//数据类型
				
			},
			{
				title : "已使用金额",// 标题
				name : "usedAmount",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
				
			},
			{
				title : "状态",// 标题
				name : "cardStatus",
				//sort : "up",
				width : 100,// 宽度
				leng : 8,//显示长度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.cardStatus==0){
						return "待上线";
					} 
					if(data.cardStatus==1){
						return "已上线";
					} 
					if(data.cardStatus==2){
						return "已下线";
					} 
					return "-";
				}
			}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["view","update"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : reqUrl+"list/detailView.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "商家会员卡详情" 	
						},
						param : ["sellerId"],// 参数
						permission : "view"// 列权限
					}
				},
				{
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : reqUrl+"updateView.jhtml",// url
						param : ["sellerId"],// 参数
						permission : "update"// 列权限
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

// 时间对象的格式化;
Date.prototype.format = function(format){
	 var o = {
	  "M+" : this.getMonth()+1, //month
	  "d+" : this.getDate(), //day
	  "h+" : this.getHours(), //hour
	  "m+" : this.getMinutes(), //minute
	  "s+" : this.getSeconds(), //second
	  "q+" : Math.floor((this.getMonth()+3)/3), //quarter
	  "S" : this.getMilliseconds() //millisecond
	 };
	   
  	if(/(y+)/.test(format)) {
	  format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	  
	  for(var k in o) {
		  if(new RegExp("("+ k +")").test(format)) {
		 	 format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		  }
	  }
	 return format;
};
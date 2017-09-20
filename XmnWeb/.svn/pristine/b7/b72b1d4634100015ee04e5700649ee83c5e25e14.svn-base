var vipCardList;
var reqUrl="businessman/vipCard/";
$(document).ready(function() {
	vipCardList = $('#prepaidList').page({
		url : reqUrl+'prepaidList.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'selectPrepaidForm'
	});
	
	inserTitle(' > 商户会员卡管理 > <a href="businessman/vipCard/prepaidListView.jhtml" target="right">会员卡充值记录</a>','allbillSpan',true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;商户会员卡充值记录</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#selectPrepaidForm").serialize());
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
					title : "用户编号",// 标题
					name : "uid",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : "用户昵称",// 标题
				name : "nname",
				width : 150,// 宽度
				type:"string"//数据类型
				
			},{
				title : "用户手机",// 标题
				name : "phoneId",
				//sort : "up",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "卡序号",// 标题
				name : "cardNo",
				width : 100,// 宽度
				leng : 8,//显示长度
				type:"number",//数据类型
			},{
				title : "卡名称",// 标题
				name : "cardName",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "会员卡编号",// 标题
				name : "noId",
				width : 150,// 宽度
				type:"string"//数据类型
				
			},{
				title : "充值面额",// 标题
				name : "amount",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
				
			},{
				title : "实收金额",// 标题
				name : "cash",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
			},{
				title : "到账金额",// 标题
				name : "profit",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型	
			},{
				title : "充值状态",// 标题
				name : "payStatus",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.payStatusStr!=null){
						return data.payStatusStr;
					} 
					return "-";
				}
			},{
				title : "充值订单号",// 标题
				name : "orderId",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"number"//数据类型
				
			},
			{
				title : "充值时间",// 标题
				name : "rdateStr",
				//sort : "up",
				width : 160,// 宽度
				type:"number"//数据类型
				
			},
			{
				title : "到账时间",// 标题
				name : "udateStr",
				//sort : "up",
				width : 160,// 宽度
				type:"number"//数据类型
				
			},
			{
				title : "支付流水号",// 标题
				name : "number",
				//sort : "up",
				width : 120,// 宽度
				leng : 8,//显示长度
				type:"string"//数据类型
			},
			{
				title : "第三方支付账号",// 标题
				name : "thirdUid",
				width : 180,// 宽度
				leng : 8,//显示长度
				type:"number"//数据类型
				
			},
			{
				title : "支付方式",// 标题
				name : "payTypeStr",
				//sort : "up",
				width : 160,// 宽度
				type:"string"//数据类型
				
			},],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["detail"],// 不需要选择checkbox处理的权限
				width : 100,// 宽度
				// 当前列的中元素
				cols : [{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : reqUrl+"prepaidList/prepaidDetailView.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "查看充值记录" 	
						},
						param : ["id","orderId","orderId","batchId","noId","cardType",
						        	"uid","amount","profit","cash","type",	"payStatus","payType",
						        	"payId","number","thirdUid","rdate","udate","depict","nname",
						        	"phoneId","cardName","rdateStr","payTypeStr","udateStr","cardNo"],// 参数
						permission : "detail"// 列权限
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
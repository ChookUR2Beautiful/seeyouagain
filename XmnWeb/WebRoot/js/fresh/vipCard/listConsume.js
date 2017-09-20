var vipCardList;
$(document).ready(function() {
	vipCardList = $('#consumeList').page({
		url : 'fresh/card/consumeList.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'selectConsumeForm'
	});
	
	inserTitle(' > 生鲜会员卡管理 > <a href="fresh/card/consumeListView.jhtml" target="right">会员卡消费记录</a>','allbillSpan',true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;生鲜会员卡消费记录</font></caption>';
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
				title : "会员卡编号",// 标题
				name : "noId",
				width : 120,// 宽度
				type:"string"//数据类型
			},{
				title : "卡名称",// 标题
				name : "cardName",
				width : 150,// 宽度
				type:"string"//数据类型
			},{
				title : "消费订单号",// 标题
				name : "bid",
				width : 150,// 宽度
				leng : 8,//显示长度
				type:"number"//数据类型
				
			},{
				title : "消费金额",// 标题
				name : "amount",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
				
			},{
				title : "订单金额",// 标题
				name : "money",
				//sort : "up",
				width : 100,// 宽度
				type:"number"//数据类型
			},{
				title : "消费时间",// 标题
				name : "rdateStr",
				//sort : "up",
				width : 160,// 宽度
				type:"string"//数据类型
				
			},],
			//操作列
			/*handleCols : {
				title : "操作",// 标题
				queryPermission : ["detail"],// 不需要选择checkbox处理的权限
				width : 100,// 宽度
				// 当前列的中元素
				cols : [{
					title : "查看",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "fresh/card/prepaidDetailView.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "查看充值记录" 	
						},
						param : ["id","orderId","orderId","batchId","noId","cardType",
						        	"uid","amount","profit","cash","type",	"payStatus","payType",
						        	"payId","number","thirdUid","rdate","udate","depict","nname",
						        	"phoneId","cardName","rdateStr","payTypeStr","udateStr"],// 参数
						permission : "detail"// 列权限
					}
				}] 
	}*/},permissions);
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
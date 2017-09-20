var vipCardList;
$(document).ready(function() {
	vipCardList = $('#cardholderList').page({
		url : 'fresh/card/userList.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'selectCardholderForm'
	});
	
	inserTitle(' > 生鲜会员卡管理 > <a href="fresh/card/userListView.jhtml" target="right">会员卡信息列表</a>','allbillSpan',true);

	 var valiinfo={
				rules:{
					id:{
						digits:true,
					},
					uid:{
						digits:true,
					},
					noId:{
						digits:true,
					},
					minAmount:{
						number:true,
						doublearea:[10,2]
					},
					maxAmount:{
						number:true,
						doublearea:[10,2]
					},
				},
				messages:{
					id:{
						digits:"卡序号须为数字类型",
					},
					uid:{
						digits:"用户编号须为数字类型",
					},
					noId:{
						digits:"会员卡编号须为数字类型",
					},
					minAmount:{
						number:"金额须为数字类型",
						doublearea:"金额须为2位小数点内的数字",
					},
					maxAmount:{
						number:"金额须为数字类型",
						doublearea:"金额须为2位小数点内的数字",
					},
				}
		};
	 
	 //带2位小数字点
	 $.validator.addMethod("doublearea",function(value,element,params){
			var len = value.length;
			 if(len>12){return false;
			 }else if( value >= 1000000000 || value < 0){
				 return false;
			 }else {
				 var indexOf = value.indexOf(".");
				 if(indexOf>0){
					 var numStr = value.substr(indexOf+1);
					 return !(numStr.length > 2);
				 }
				 return true;
			 }
		 },"请填写数值,最大值为999999999.99");
	 
//	 validate("selectCardholderForm",valiinfo,success); 
});



/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;余额【￥'
		+(0 == data.content.length ? "0" : data.content[0].totalNoConsumeAmount)+'】&nbsp;&nbsp;充值总额【￥'
		+(0 == data.content.length ? "0" : data.content[0].totalAmount)+'】&nbsp;&nbsp;已使用金额【￥'
		+(0 == data.content.length ? "0" : data.content[0].totalConsumeAmount)
		+'】&nbsp;&nbsp;共计【'+data.total+'】张会员卡&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#selectCardholderForm").serialize());
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
					title : "序列号",// 标题
					name : "id",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : "会员卡编号",// 标题
				name : "noId",
				width : 120,// 宽度
				type:"number"//数据类型
				
		},{
			title : "卡余额",// 标题
			name : "amount",
			//sort : "up",
			width : 100,// 宽度
			type:"number",//数据类型
		},{
			title : "充值总额",// 标题
			name : "total",
			width : 100,// 宽度
			type:"number",//数据类型
		},{
			title : "已使用金额",// 标题
			name : "consumeTotal",
			width : 100,// 宽度
			type:"number"//数据类型
		},{
			title : "卡状态",// 标题
			name : "cardStatus",
			width : 100,// 宽度
			type:"number",//数据类型
			customMethod : function(value, data) {
				if(data.cardStatus==1){
					return "已激活";
				} 
				if(data.cardStatus==2){
					return "已注销";
				} 
				if(data.cardStatus==3){
					return "已锁定";
				} 
				return "-";
			}
		},{			
			title : "卡名称",// 标题
			name : "cardName",
			width : 150,// 宽度
			type:"string"//数据类型
		},{			
			title : "用户编号",// 标题
			name : "uid",
			//sort : "up",
			width : 120,// 宽度
			type:"number"//数据类型
			
		},{
			title : "用户昵称",// 标题
			name : "nname",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},{
			title : "用户手机",// 标题
			name : "phoneid",
			width : 120,// 宽度
			type:"string"//数据类型
		},{
			title : "激活时间",// 标题
			name : "rdateStr",
			width : 150,// 宽度
			type:"string"//数据类型
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["detail","lock"],// 不需要选择checkbox处理的权限
			width : 100,// 宽度
			// 当前列的中元素
			cols : [{
				title : "锁定解锁会员卡",// 标题
				linkInfoName : "method",
				linkInfo : {
					method : "lockOrNot",
					param : ["noId"],
					permission : "lock"
				},
				customMethod : function(value, data){
					console.log(value);
					var result;
					if(data.cardStatus != undefined && data.cardStatus == 3){//解锁
						result = value.replace(/lockOrNot/,"unlock");
						result = result.replace(/锁定解锁会员卡/,"解锁")
					}else if(data.cardStatus != undefined && data.cardStatus == 1){
						result = value.replace(/lockOrNot/,"lock");
						result = result.replace(/锁定解锁会员卡/,"锁定");
					}else{
						result = '<a href="javascript:void(0);" title="不符合锁定或解锁条件" class="hidden"></a>'
					}
					return result;
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
 * 锁定会员卡
 */
function lock(noId){
	showSmConfirmWindow(function(){
		lockOrNot(3,noId);
	},"确定要锁定该会员卡？");
	
}

/**
 * 解锁会员卡
 */
function unlock(noId){
	showSmConfirmWindow(function(){
		lockOrNot(1,noId);
	},"确定要解锁该会员卡？");
}

/**
 * 
 * @param islock  1：解锁 ，3：锁定; noId 会员卡编号
 */
function lockOrNot(islock,noId){
	if(islock!=undefined && noId!=undefined){
		var data ={"noId":noId,"cardStatus":islock}; 
		var url = "fresh/card/userList/lock.jhtml";
		$.ajax({
			type : 'post',
			url : url + "?t="
					+ Math.random(),
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				try {
					if (data.success!=undefined && data.success==true &&　vipCardList != undefined) {
						vipCardList.reload();
					}
				} catch (err) {
					console.log(err);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			}
		});
	}
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
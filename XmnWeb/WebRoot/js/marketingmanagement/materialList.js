var matetialList;
$(document).ready(function() {
	
	matetialList = $('#matetialList').page({
		url : 'marketingManagement/material/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForms'
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minuteStep:1,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	inserTitle(' > 物料管理 > <a href="marketingManagement/material/init.jhtml" target="right">所有物料</a>','allbillSpan',true);
	
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】种产品&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForms").serialize());
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
					title : "物料名称",// 标题
					name : "name",
					//sort : "up",
					width : 100,// 宽度
					type:"String",//数据类型					
			},{
				title : "缩略图",// 标题
				name : "thumbnailUrl",
				width : 150,// 宽度
				type:"string",//数据类型
					customMethod:function(value,data){
						return '<img style="width:50px;height:50px;" src="'+data.thumbnailUrl+'"/>';
					}
				
			},{
				title : "物料价格",// 标题
				name : "price",
				//sort : "up",
				width : 200,// 宽度
				type:"string"//数据类型
				
			},{
				title : "上架状态",// 标题
				name : "status",
				//sort : "up",
				width : 120,// 宽度
				leng : 8,//显示长度
				type:"number",//数据类型
					customMethod : function(value, data) {
						if(data.status==1){
							return "上架";
						} 
						if(data.status==0){
							return "下架";
						} 
						return "-";
					}
			},{
				title : "是否必买",// 标题
				name : "ismust",
				width : 150,// 宽度
				type:"number",//数据类型
					customMethod : function(value, data) {
						if(data.ismust==1){
							return "是";
						} 
						if(data.ismust==0){
							return "否";
						} 
						return "-";
					}
			},{
				title : "销售数量",// 标题
				name : "sold_quantity",
				width : 150,// 宽度
				type:"number"//数据类型
				
			},{
				title : "添加日期",// 标题
				name : "create_time_str",
				//sort : "up",
				width : 150,// 宽度
				type:"stirng"//数据类型
			
			}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["eidt"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
//				{
					title : "编辑",// 标题
					linkInfoName : "modal",
					linkInfo : {
							modal : {
								url : "marketingManagement/material/init/edit.jhtml",// url
								position:"60px",// 模态框显示位置
								width:"800px"
							},
						param : ["id"],// 参数
						permission : "eidt"// 列权限
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
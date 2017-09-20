var hotSaleList;
$(document).ready(function() {
	hotSaleList = $('#hotSaleList').page({
		url : 'fresh/hotsalemanage/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchHotSaleForm'
	});
	
	/*$("#ptypeld").freshLd({
		showConfig : [ {name : "classa", tipTitle : "--&nbsp;类别&nbsp;--"}]
	});*/
	// 省市
	/*var ld = $("#ld").cityLd({
		isChosen : false
	});
	*/
	//初始化日期选择控件
	initDates();
	
	inserTitle(' > 精选管理 > <a href="fresh/hotsalemanage/init.jhtml" target="right">所有精选产品</a>','allbillSpan',true);
	
	$("input[data-bus=reset]").click(function(){
		$("#dLabel").text("请选择").val("");
		$("#ld").find("select").trigger("chosen:updated");
	});	
	
	//批量取消精选
	batchCancelHotSale();
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】种产品&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
    	checkable :true,
    	identifier : "pid",
    	tableClass :"table-bordered table-striped info",
    	callbackParam : callbackParam,
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
			title : "排序",// 标题
			name : "choiceSort",
			//sort : "up",
			width : 80,// 宽度
			leng : 3,//显示长度
			type:"number",//数据类型				
		},{
			title : "产品编号",// 标题
			name : "codeId",
			//sort : "up",
			width : 100,// 宽度
			leng : 3,//显示长度
			type:"number",//数据类型					
		},{
			title : " 产品名称",// 标题
			name : "pname",
			width : 200,// 宽度
			type:"string"//数据类型
				
		},{
			title : " 产品销售属性",// 标题
			name : "pname",
			type:"string",//数据类型
			isLink : true,
			link : {
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "fresh/manage/viewSaleProperty.jhtml",
						position : "200px",
						width : "auto",	
						title : "商品销售属性" 
					}
						
				},
				param : ["codeId"],
				permission : "init",
			},
			customMethod : function(value, data) {
				return $(value).html("产品销售属性");
			},
			width : 200// 宽度
		},{
			title : "产品标准号",// 标题
			name : "standard",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "产品类型",// 标题
			name : "classaStr",
			width : 150,// 宽度
			type:"string"//数据类型
		},{
			title : "产品原价(元)",// 标题
			name : "price",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},{
			title : "销售价(元)",// 标题
			name : "combinePrice",
			//sort : "up",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "采购价(元)",// 标题
			name : "purchasePrice",
			//sort : "up",
			width : 180,// 宽度
			type:"string"//数据类型
		
		},{
			title : "产品净重(克)",// 标题
			name : "suttle",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
				
		},{
			title : "快递计重(kg)",// 标题
			name : "expWeight",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
				
		},{
			title : "快递模板",// 标题
			name : "expTitle",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
				
		},{
			title : "产品配料",// 标题
			name : "batching",
			//sort : "up",
			width : 160,// 宽度
			type:"string"//数据类型
			
		},{
			title : "保质期(天)",// 标题
			name : "quality",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"number"//数据类型
			
		},{
			title : "生产日期",// 标题
			name : "productionStr",
			//sort : "up",
			width : 160,// 宽度
			type:"string"//数据类型
			
		},{
			title : "品牌名称",// 标题
			name : "brandName",
			//sort : "up",
			width : 110,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
			
		},{
			title : "产品状态",// 标题
			name : "pstatus",
			//sort : "up",
			width : 100,// 宽度
			type:"number",//数据类型
			customMethod : function(value, data) {
				if(data.pstatus==0){
					return "待上线";
				} 
				if(data.pstatus==1){
					return "已上线";
				} 
				if(data.pstatus==2){
					return "已售罄";
				}
				if(data.pstatus==3){
					return "已下线";
				}
				return "-";
			}
		},
		{
			title : "产地",// 标题
			name : "place",
			//sort : "up",
			width : 110,// 宽度
			type:"string"//数据类型
			
		},{
			title : "库存总数(份)",// 标题
			name : "store",
			//sort : "up",
			width : 110,// 宽度
			type:"number"//数据类型
		},{
			title : "已销售量(份)",// 标题
			name : "sales",
			//sort : "up",
			width : 120,// 宽度
			type:"number"//数据类型
			
		},{
			title : "是否精选",// 标题
			name : "choice",
			//sort : "up",
			width : 120,// 宽度
			type:"number",//数据类型
			customMethod : function(value, data) {
				if(data.choice==0){
					return "否";
				} 
				if(data.choice==1){
					return "是";
				} 
				return "-";
			}
		},{
			title : "生产厂名",// 标题
			name : "cname",
			//sort : "up",
			width : 80,// 宽度
			type:"string",//数据类型
		},{
			title : "供应商名称",// 标题
			name : "supplierName",
			//sort : "up",
			width : 150,// 宽度
			type:"string",//数据类型
		},{
			title : "供应商联系人",// 标题
			name : "contacts",
			width : 150,// 宽度
			type:"string",//数据类型
		},{
			title : "供应商联系电话",// 标题
			name : "supplierPhone",
			width : 120,// 宽度
			type:"string",//数据类型
		},{
			title : "供应商地址",// 标题
			name : "supplierAddress",
			width : 180,// 宽度
			type:"string",//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["init"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "修改排序",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "fresh/hotsalemanage/init/updateChoiceSort.jhtml",// url
						param : ["pid"],// 参数
						permission : "init"// 列权限
					},
					customMethod : function(value, data){
						if(data.pid != null){
							var value1 = "<a href=\"javascript:updateChoiceSort('"+data.pid+"')\">" + "修改排序" + "</a>";
							return value1;
						}else{
							var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
							return value2;
						}
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

/**
 * datetimepicker 转化
 */
function initDates() {
	$("input.form-datetime").datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd',
		todayBtn : true,
		minView:2
	});
}

/**
 * add by lifeng 20160721
 * 批量取消精选
 */	
function batchCancelHotSale(){
	$("#cancelHotSale").click(function(){
		if(!hotSaleList.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var data = {"ids":hotSaleList.getIds(),"choice":0};
		requestBeach(data,"确定取消精选？");
	})
}
//发送请求
function requestBeach(data,title){
	showSmConfirmWindow(function() {
		$.ajax({
	        type: "POST",
	        url: "fresh/manage/batch.jhtml",
	        data: data,
	        dataType: "json",
	        success: function(result){
				showSmReslutWindow(result.success, result.msg);
				hotSaleList.reload();
	         }
	    });
	},title);
}

//弹出修改热卖产品的排序模态框
function updateChoiceSort(pid){
	$("#updateChoiceSortModal").modal();
	$("#choicePid").val(pid);
}

/**
 * 确定修改精选排序
 */
$("#updateChoiceSortConfirm").click(function() {
	var choiceSort = $("#choiceSortId").val();
	var reg = /^\d+$/;
	if(!reg.test(choiceSort)){
		submitDataError($("#choiceSortId"),"请输入整数排序数值!");
		return false;
	}
	$.ajax({
		url : "fresh/hotsalemanage/init/updateChoiceSort.jhtml",
		type : "post",
		dataType : "json",
		data:{"pid":$("#choicePid").val(),"choiceSort":choiceSort},
		success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				hotSaleList.reload();
				$("#choiceSortId").val("");
				$("#choiceSortId").attr("placeholder",'输入非负整数数值');
				$('#updateChoiceSortModal').modal('hide');
		}
	});
})



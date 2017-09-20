var vipCardList;
var reqUrl="businessman/vipCard/";
var parm =[];
$(document).ready(function() {
	vipCardList = $('#issueCardList').page({
		url : reqUrl+'issuecardapplylist.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'selectVipCardForm'
	});
	
	inserTitle(' > 商户会员卡管理 > <a href="businessman/vipCard/issueCardAudit.jhtml" target="right">充值方案审核</a>','allVipCardSpan',true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个充值方案记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#selectVipCardForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	checkable : true,
	    	checkdisenable:"disableCheck",
	    	identifier :"id",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "充值方案申请表ID",// 标题
					name : "id",
					//sort : "up",
					width : 180,// 宽度
					type:"number",//数据类型					
		    },{
					title : "发行编号",// 标题
					name : "batchId",
					//sort : "up",
					width : 180,// 宽度
					type:"String",//数据类型					
			},{
				title : "发行卡名称",// 标题
				name : "batchName",
				width : 180,// 宽度
				type:"string"//数据类型
			},{
				title : "商家编号",// 标题
				name : "sellerid",
				width : 180,// 宽度
				type:"string"//数据类型
				
			},{
				title : "商家名称",// 标题
				name : "sellername",
				width : 180,// 宽度
				type:"string"//数据类型
			},{
				title : "面额",// 标题
				name : "price",
				//sort : "up",
				width : 180,// 宽度
				type:"String"//数据类型
			},{
				title : "售价",// 标题
				name : "retail",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "结算价",// 标题
				name : "cash",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "开始有效期",// 标题
				name : "statrperiod",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "结束有效期",// 标题
				name : "endperiod",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "发行数量",// 标题
				name : "number",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "申请状态",// 标题
				name : "cardstatusStr",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "审核不通过原因",// 标题
				name : "reason",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "已售卡面额",// 标题
				name : "amount",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "售卡收益",// 标题
				name : "profit",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "本批次已卖卡数量",// 标题
				name : "num",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "发行时间",// 标题
				name : "rdate",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "更新时间",// 标题
				name : "udate",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "数据状态",// 标题
				name : "dstatusStr",
				width : 240,// 宽度
				type:"string"//数据类型
			},{
				title : "默认充值方案",// 标题
				name : "isDefaultStr",
				width : 240,// 宽度
				type:"string"//数据类型
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
							url : reqUrl+"issuecardapplylist/detailView.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "充值方案审核申请记录" 	
						},
						param : ["id"],// 参数
						permission : "view"// 列权限
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
 * 批量通过
 */
$('#passId').click(function() {
	var ids = vipCardList.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	pass(ids);
});
/**
 * 批量不通过
 */
$('#notPassId').click(function(){
	var ids = vipCardList.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	$('#reason').val("");
	$('#refuseForm>#ids').val(ids);
	$('#refuseModal').modal();
});
/*
 * 确认拒绝
 */
$("#ensure").click(function() {
	$('#refuseModal').modal('hide');
	var data = $('#refuseForm').serializeArray();
	//form转成json
	 data = jsonFromt(data);
	 //post提交请求
	 $.post(reqUrl+'issuecensor.jhtml', data, function(result) {
	     if (result.success) {
	         //弹出成功后的提示
	         showSmReslutWindow(result.success, result.msg);
	         //设置提示停留时间并回调的函数
	         setTimeout(function() {
	             muBack();
	         }, 1000);
	     } else {
	         window.messager.warning(result.msg);
	     }
	 }, "json");

	function jsonFromt(data) {
	 var json = {};
	 for (var i = 0; i < data.length; i++) {
	     json[data[i].name] = data[i].value;
	 }
	 return json;
	}
});

/**
 * 批量通过
 */
function pass(ids) {
	parm[0]=ids;
	showSmConfirmWindow(dealApply,"你确定执行批量通过？");
}
var dealApply = function() {
	$.ajax({
		type : 'post',
		url : reqUrl+'issuecensor.jhtml',
		data : {"ids":parm[0]},
		dataType : 'json',
		success : function(data) {
			showSmReslutWindow(data.success, data.msg);
		}
	});
}
/**
 * 弹出操作提示结果
 */
function showSmReslutWindow(isflag, content) {
    $('#sm_reslut_window').find('.modal-title').html('操作提示');
    if (isflag) {
        $('#sm_reslut_window').find('.modal-body').html('<div class="alert with-icon alert-success"> <i class="icon-ok"></i> <div class="content">' + content + '</div></div>');
    } else {
        $('#sm_reslut_window').find('.modal-body').html('<div class="alert with-icon  alert-danger"><i class="icon-remove-sign"></i> <div class="content">' + content + '</div></div>');
    }
    $('#sm_reslut_window').modal();
    $('#sm_reslut_window').find('.close').on("click",function(){
    	vipCardList.reload();
    });
    if(isflag){
    	setTimeout(function(){
	        $('#sm_reslut_window').modal('hide');
	        vipCardList.reload();
	    }, 2000);
    }
}
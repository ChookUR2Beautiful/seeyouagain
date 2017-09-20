var vipCardList;
var reqUrl="businessman/vipCard/";
var parm =[];
$(document).ready(function() {
	vipCardList = $('#vipCardList').page({
		url : reqUrl+'cardapplylist.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'selectVipCardForm'
	});
	
	inserTitle(' > 商户会员卡管理 > <a href="businessman/vipCard/vipCardAudit.jhtml" target="right">会员卡审核</a>','allVipCardSpan',true);

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个审核申请记录&nbsp;</font></caption>';
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
					title : "会员卡申请表ID",// 标题
					name : "id",
					//sort : "up",
					width : 180,// 宽度
					type:"number",//数据类型					
		    },{
					title : "商家编号",// 标题
					name : "sellerid",
					//sort : "up",
					width : 180,// 宽度
					type:"String",//数据类型					
			},{
				title : "商家名称",// 标题
				name : "sellername",
				width : 180,// 宽度
				type:"string"//数据类型
			},{
				title : "使用说明",// 标题
				name : "instructions",
				width : 180,// 宽度
				type:"string"//数据类型
				
			},{
				title : "申请类型",// 标题
				name : "applytype",
				width : 180,// 宽度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.applytype==1){
						return "开通会员卡";
					} 
					if(data.applytype==2){
						return "修改使用说明";
					}
					return "-";
				}
			},{
				title : "审核状态",// 标题
				name : "applystatus",
				//sort : "up",
				width : 180,// 宽度
				type:"String",//数据类型
				customMethod : function(value, data) {
					if(data.applystatus==0){
						return "待审核 ";
					} 
					if(data.applystatus==1){
						return "审核通过";
					}
					if(data.applystatus==2){
						return "审核不通过";
					}
					if(data.applystatus==3){
						return "已删除";
					} 
					return "-";
				}
			},{
				title : "审核不通过原因",// 标题
				name : "reason",
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
							url : reqUrl+"cardapplylist/detailView.jhtml",// url
							position:"60px",// 模态框显示位置
							width:"800px",
							title : "会员卡审核申请记录" 	
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
	$('#applystatus').val("2");
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
	 $.post(reqUrl+'censor.jhtml', data, function(result) {
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
		url : reqUrl+'censor.jhtml',
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
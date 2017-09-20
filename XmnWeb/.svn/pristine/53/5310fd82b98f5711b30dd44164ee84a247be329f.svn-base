var manzengList;
var discountList;
var guagualeList;
$(document).ready(function() {
	//满赠活动
	manzengList = $('#manzeng').page({
		url : 'marketingManagement/activityManagement/manzeng/init/list.jhtml',
		success : manzengSuccess,
		pageBtnNum : 10,
		paramForm : 'manzengForm',
		param:{
			type:"2"
		}
	});
	//折扣补贴
	discountList = $('#discount').page({
		url : 'marketingManagement/activityManagement/discount/init/list.jhtml',
		success : discountSuccess,
		pageBtnNum : 10,
		paramForm : 'discountForm'
	});
	//刮刮卡
	guagualeList = $('#guaguale').page({
		url : 'marketingManagement/activityManagement/init/list.jhtml',
		success : guagualeSuccess,
		pageBtnNum : 10,
		paramForm : 'guagualeForm'
	});
	//优惠券活动
	guagualeList = $('#youhuiquan').page({
		url : 'marketingManagement/activityManagement/youhuiquan/init/list.jhtml',
		success : youhuiquanSuccess,
		pageBtnNum : 10,
		paramForm : 'youhuiquanForm'
	});

	$("input[data-bus=reset]").click(function(){
//		location.reload();
		$("#cpartner").trigger("chosen:updated");
		$("#bpartner").trigger("chosen:updated");
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	inserTitle(' > 营销活动管理  > <a href="marketingManagement/activityManagement/manzeng/init.jhtml" target="right">活动管理</a>','activityManagement',true);
	
	$("#guaguakaactivityManager").on("click",function(){
		var aids= new Array();
		var aids=guagualeList.getIds().split(",");
		if(!guagualeList.getIds()){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		if(aids.length>1){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		var aid = guagualeList.getIds();
		$("#guagualeAid").val(aid);
	   $("#guagualeAddSellerForm").get(0).submit();
	});
});

function manzengSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">满赠活动列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:5%;">选择</th>');
		html.push('<th style="width:8%;">操作</th>');
	}

	html.push('<th style="width:10%;">活动名称</th>');
	html.push('<th style="width:10%;">开始时间</th>');
	html.push('<th style="width:10%;">结束时间</th>');
/*	html.push('<th style="width:10%;">赠送规则</th>');
*/	html.push('<th style="width:10%;">赠送频率</th>');
	html.push('<th style="width:10%;">活动描述</th>');
	
	html.push('<th style="width:10%;">参与商家</th>');
/*	html.push('<th style="width:10%;">参与用户</th>');
*/	html.push('<th style="width:10%;">已中奖金额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<th><input type="checkbox" val=' + data.content[i].aid + '  /></th>'); 
		}
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.update=='true'){
			
				html.push('<a href="javascript:viod(); " data-type="ajax"  data-width="60%" data-url="marketingManagement/activityManagement/manzeng/update/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
		    }
			if(permissions.check=='true'){
				
				html.push('<a href="javascript:viod(); " data-type="ajax" data-width="50%" data-url="marketingManagement/activityManagement/manzeng/check/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
			}
			/*if(permissions.del=='true' && (data.content[i].isRelationSeller ==0)){
				
					html.push('<a href="javascript:remove('+data.content[i].aid+')">删除</a>');
				
				
			}*/
			html.push('</td>');
		}
			var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#manzengForm").serialize());

			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].startDate ? "-" : data.content[i].startDate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');
					
/*		     var rules=(undefined == data.content[i].rule ? "-" : data.content[i].rule);
			html.push('<td title ="'+rules+'">' + substr(rules) + '</td>');*/
			html.push('<td>' + (undefined == data.content[i].rateName ? "-" : data.content[i].rateName) + '</td>');

			 var eescriptions=(undefined == data.content[i].eescription ? "-" : data.content[i].eescription);
			html.push('<td title ="'+eescriptions+'">' + substr(eescriptions) + '</td>');
			
			var isRelateNum=(undefined == data.content[i].isRelationSeller ? "0" : data.content[i].isRelationSeller);
			if(isRelateNum==0){
				html.push('<td>'+isRelateNum+'</td>');
			}else{
				html.push('<td><a id="SendoutAmount" href="marketingManagement/activityManagement/initSellerRelateNum/init.jhtml?aid='+data.content[i].aid + callbackParam+'">'+isRelateNum+' </a>&nbsp;&nbsp;</td>');
			}
/*			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
*/			
			var StrgiveMoneyCount=(undefined == data.content[i].giveMoneyCount ? "0" : data.content[i].giveMoneyCount);
			if(StrgiveMoneyCount==0){
				html.push('<td>'+StrgiveMoneyCount+'</td>');
			}else{
				html.push('<td><a id="SendoutAmount" href="marketingManagement/activityPrize/init.jhtml?aid='+data.content[i].aid + callbackParam+'">￥'+StrgiveMoneyCount+' </a>&nbsp;&nbsp;</td>');
			}
			

			html.push('</tr>');
		}
		
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}
/**
 * 批量设置平台补贴折扣
 */
function activityManager(){
		var aids= new Array();
		var aids=manzengList.getIds().split(",");
		if(!manzengList.getIds()){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		if(aids.length>1){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		var aid = manzengList.getIds();
		$("#aid").val(aid);
       $("#addForm").get(0).submit();
}

function discountSuccess(data, obj) {
	var model = {
	title : "折扣补贴活动列表",
	totalTitle : "折扣补贴活动",
	form : "discountForm",
	checkbox : true,
	handleColumn : {
		title : "操作",
		name : "aid",
		queryPermissions : ["update","check"],//不需要用到checkbox
		handlePermissions : ["update"],// 需要批量处理的权限 需要设置checkbox为true
		column : [{
					title : "修改",
					modal : "marketingManagement/activityManagement/discount/update/init.jhtml",
					param : ["aid"],
					permissions : "update",
					data_width:"70%"
				}, {
					title : "查看",
					modal : "marketingManagement/activityManagement/discount/discountActivityDetails.jhtml",
					param : ["aid"],
					permissions : "check",
					data_width:"50%"
				}]
	},
	columns : [{
				title : "活动名称",
				name : "aname",
				width : "120px"
			}, {
				title : "开始时间",
				name : "startDate",
				width : "50px",
				leng:8
			}, {
				title : "结束时间",
				name : "endDate",
				width : "50px",
				leng:8
			},  {
				title : "补贴折扣",
				name : "ngiveMoney",
				width : "50px",
				leng:8
			}, {
				title : "补贴频率",
				name : "rateName",
				width : "50px",
				leng:8
			},/*{
				title : "限制条件",
				name : "repel",
				width : "50px",
				leng:8
			},*/{
				title : "活动描述",
				name : "eescription",
				width : "50px",
				leng:8
			},{
				title : "与其他折扣补贴活动互斥",
				name : "repelName",
				width : "50px",
				leng:8
			},{
				title : "参与商家",
				name : "isRelationSeller",
				width : "50px",
				permissions : "check",
				isA : true,
				a : {
					must:true,
					href : "marketingManagement/activityManagement/discount/discountParticipatingMerchants/init.jhtml",
					param : ["aid"]
				},
				leng:8
			},{
				title : "参与用户",
				name : "nname",
				width : "50px"
			},{
				title : "已补贴金额",
				name : "giveMoneyCount",
				width : "50px",
				permissions : "check",
				isA : true,
				a : {
					must:true,
					href : "marketingManagement/activityManagement/discount/giveMoney/init.jhtml",
					param : ["aid"]
				},
				leng:8
			}],
			permissions : permissions
	}

	obj.find('div').eq(0).html($.renderGridView(model,data));
    $("#discount").find("table thead tr th").first().html("选择");
}

function activitySubsidy(){
	var aids= new Array();
	var aids=discountList.getIds().split(",");
	if(!discountList.getIds()){
		showWarningWindow("warning","请选择一条记录！");
		return;
	}
	if(aids.length>1){
		showWarningWindow("warning","请选择一条记录！");
		return;
	}
	var aid = discountList.getIds();
	$("#aid").val(aid);

	$("#addForm").get(0).action="marketingManagement/activityManagement/activityManagerSeller/init.jhtml?doType=discount";
    $("#addForm").get(0).submit();
}

function guagualeSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">抽奖返利活动列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	if(!isEmptyObject(permissions)){
		html.push('<th>选择</th>');
		html.push('<th>操作</th>');
	}

	html.push('<th>活动名称</th>');
//	html.push('<th>活动类型</th>');
	html.push('<th>开始时间</th>');
	html.push('<th>结束时间</th>');
//	html.push('<th>活动规则</th>');
	html.push('<th>中奖比例</th>');
//	html.push('<th>中奖金额</th>');
	html.push('<th>活动描述</th>');
	html.push('<th>与其他抽奖返利活动互斥</th>');
//	html.push('<th>参与用户</th>');
	html.push('<th>已返利金额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<th><input type="checkbox" val=' + data.content[i].aid + '  /></th>'); 
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			
			if(permissions.update=='true'){
			
				html.push('<a href="javascript:viod(); " data-type="ajax" data-width="60%" data-url="marketingManagement/activityManagement/scratchCard/update/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
		    }
			if(permissions.check=='true'){
				
				html.push('<a href="javascript:viod(); " data-type="ajax" data-url="marketingManagement/activityManagement/check/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
			}
			if(permissions.del=='true' && (data.content[i].isRelationSeller ==0)){
				
					html.push('<a href="javascript:remove('+data.content[i].aid+')">删除</a>');
				
				
			}
			html.push('</td>');
		}
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#guagualeForm").serialize());
		
			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			/*var type=data.content[i].type;
			var types="";
			if(type==1){types="抽奖返利活动";}else if(type==2){types="消费赠送活动";}else if(type==3){types="教育基金活动";}else if(type==4){types="消费补贴活动";}else{types="";}
			html.push('<td>' + (undefined == types ? "-" : types) + '</td>');*/
			html.push('<td>' + (undefined == data.content[i].startDate ? "-" : data.content[i].startDate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].hitRatio ? "-" : data.content[i].hitRatio) + '</td>');//中奖比例
//			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');//中奖金额
			var eescriptions=(undefined == data.content[i].eescription ? "-" : data.content[i].eescription);
			html.push('<td title ="'+eescriptions+'">' + substr(eescriptions) + '</td>');//活动描述
			html.push('<td>' + (undefined == data.content[i].repelName ? "-" : data.content[i].repelName) + '</td>');//与其他抽奖返利活动互斥
//			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');//参与用户
			var StrgiveMoneyCount=(undefined == data.content[i].giveMoneyCount ? "0" : data.content[i].giveMoneyCount);
			if(StrgiveMoneyCount==0){
				html.push('<td>'+StrgiveMoneyCount+'</td>');
			}else{
				html.push('<td><a id="SendoutAmount" href="marketingManagement/activityPrize/init.jhtml?aid='+data.content[i].aid + callbackParam+'">￥'+StrgiveMoneyCount+' </a>&nbsp;&nbsp;</td>');
			}//已返利金额
			
			html.push('</tr>');
		}
		
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}
function youhuiquanSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">满赠活动列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	if(!isEmptyObject(permissions)){
		html.push('<th style="width:5%;">选择</th>');
		html.push('<th style="width:8%;">操作</th>');
	}

	html.push('<th style="width:10%;">活动名称</th>');
	html.push('<th style="width:10%;">开始时间</th>');
	html.push('<th style="width:10%;">结束时间</th>');
/*	html.push('<th style="width:10%;">赠送规则</th>');
*/	html.push('<th style="width:10%;">赠送频率</th>');
	html.push('<th style="width:10%;">活动描述</th>');
	
	html.push('<th style="width:10%;">参与商家</th>');
/*	html.push('<th style="width:10%;">参与用户</th>');
*/	html.push('<th style="width:10%;">已中奖金额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<th><input type="checkbox" val=' + data.content[i].aid + '  /></th>'); 
		}
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.update=='true'){
			
				html.push('<a href="javascript:viod(); " data-type="ajax"  data-width="60%" data-url="marketingManagement/activityManagement/manzeng/update/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
		    }
			if(permissions.check=='true'){
				
				html.push('<a href="javascript:viod(); " data-type="ajax" data-width="50%" data-url="marketingManagement/activityManagement/manzeng/check/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
			}
			/*if(permissions.del=='true' && (data.content[i].isRelationSeller ==0)){
				
					html.push('<a href="javascript:remove('+data.content[i].aid+')">删除</a>');
				
				
			}*/
			html.push('</td>');
		}
			var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#manzengForm").serialize());

			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].startDate ? "-" : data.content[i].startDate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');
					
/*		     var rules=(undefined == data.content[i].rule ? "-" : data.content[i].rule);
			html.push('<td title ="'+rules+'">' + substr(rules) + '</td>');*/
			html.push('<td>' + (undefined == data.content[i].rateName ? "-" : data.content[i].rateName) + '</td>');

			 var eescriptions=(undefined == data.content[i].eescription ? "-" : data.content[i].eescription);
			html.push('<td title ="'+eescriptions+'">' + substr(eescriptions) + '</td>');
			
			var isRelateNum=(undefined == data.content[i].isRelationSeller ? "0" : data.content[i].isRelationSeller);
			if(isRelateNum==0){
				html.push('<td>'+isRelateNum+'</td>');
			}else{
				html.push('<td><a id="SendoutAmount" href="marketingManagement/activityManagement/initSellerRelateNum/init.jhtml?aid='+data.content[i].aid + callbackParam+'">'+isRelateNum+' </a>&nbsp;&nbsp;</td>');
			}
/*			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
*/			
			var StrgiveMoneyCount=(undefined == data.content[i].giveMoneyCount ? "0" : data.content[i].giveMoneyCount);
			if(StrgiveMoneyCount==0){
				html.push('<td>'+StrgiveMoneyCount+'</td>');
			}else{
				html.push('<td><a id="SendoutAmount" href="marketingManagement/activityPrize/init.jhtml?aid='+data.content[i].aid + callbackParam+'">￥'+StrgiveMoneyCount+' </a>&nbsp;&nbsp;</td>');
			}
			

			html.push('</tr>');
		}
		
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

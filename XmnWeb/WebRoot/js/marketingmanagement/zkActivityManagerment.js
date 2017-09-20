var discountList;
$(document).ready(function() {
	limitedDate({form:"#discountForm",startDateName:"sDateBegin",endDateName:"eDateEnd",format:'yyyy-mm-dd hh:ii'});
	//折扣补贴
	discountList = $('#discount').page({
		url : 'marketingManagement/activityManagement/discount/init/list.jhtml',
		success : discountSuccess,
		pageBtnNum : 10,
		paramForm : 'discountForm'
	});
	
});


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
		queryPermissions : ["zkupdate","zkcheck"],//不需要用到checkbox
		handlePermissions : ["zktjsj"],// 需要批量处理的权限 需要设置checkbox为true
		column : [{
					title : "修改",
					modal : "marketingManagement/activityManagement/discount/update/init.jhtml",
					param : ["aid"],
					permissions : "zkupdate",
					data_width:"800px",//模态框宽度
					
				}, {
					title : "查看",
					modal : "marketingManagement/activityManagement/discount/discountActivityDetails.jhtml",
					param : ["aid"],
					permissions : "zkcheck",
				}]
	},
	columns : [{
				title : "活动编号",
				name : "aid",
			}, {
				title : "活动名称",
				name : "aname",
			},{
				title : "开始日期",
				name : "startDate",
			}, {
				title : "结束日期",
				name : "endDate",
			}, {
				title : "开始时间",
				name : "startTimeText",
			}, {
				title : "结束时间",
				name : "endTimeText",
			},  {
				title : "补贴折扣(%)",
				name : "ngiveMoneyStr",
				customMethod : function(value, data) {
					return (value>0 ? value+"": "-");
				},
			}, {
				title : "补贴频率",
				name : "rateName",
			},/*{
				title : "限制条件",
				name : "repel",
				width : "50px",
				leng:8
			},*/{
				title : "活动描述",
				name : "eescription",
			}/*,{
				title : "与其他折扣补贴活动互斥",
				name : "repelName",
				width : "50px",
			}*/,{
				title : "参与商家",
				name : "isRelationSeller",
				permissions : "zkcysj",
				isA : true,
				a : {
					must:true,
					href : "marketingManagement/activityManagement/discount/discountParticipatingMerchants/init.jhtml",
					param : ["aid"]
				},
				customMethod : function(value, data) {
					return (data.isRelationSeller == 0 ? 0 : value);
				},
			},{
				title : "参与用户",
				name : "participateNum",
			},{
				title : "已补贴金额",
				name : "giveMoneyCount",
				permissions : "zkbtje",
				isA : true,
				a : {
					must:true,
					href : "marketingManagement/activityManagement/discount/giveMoney/init.jhtml",
					param : ["aid"]
				},
				customMethod : function(value, data) {
					return (data.giveMoneyCount == 0 ? "0" : value);
				},
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
	var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#discountForm").serialize());
	$("#addForm").get(0).action="marketingManagement/activityManagement/discount/activityManagerSeller/init.jhtml?doType=discount"+callbackParam;
    $("#addForm").get(0).submit();
}

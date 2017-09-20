var commission;
$(document).ready(function() {
	limitedDate({form:"#commissionForm",startDateName:"sDateBegin",endDateName:"eDateEnd",format:'yyyy-mm-dd hh:ii'});
	//佣金补贴
	commission = $('#commission').page({
		url : 'marketingManagement/activityManagement/commission/init/list.jhtml',
		success : commissionSuccess,
		pageBtnNum : 10,
		paramForm : 'commissionForm'
	});
});

/**
 * 批量设置平台佣金补贴
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

//构建表格
function commissionSuccess(data, obj) {
	var model = {
	title : "佣金补贴活动列表",
	totalTitle : "佣金补贴活动",
	form : "commissionForm",
	checkbox : true,
	handleColumn : {
		title : "操作",
		name : "aid",
		queryPermissions : ["commissionupdate","commissioncheck"],//不需要用到checkbox
		handlePermissions : ["commissiontjsj"],// 需要批量处理的权限 需要设置checkbox为true
		column : [{
					title : "修改",
					modal : "marketingManagement/activityManagement/commission/update/init.jhtml",
					param : ["aid"],
					permissions : "commissionupdate",
					data_width:"800px",//模态框宽度
				}, {
					title : "查看",
					modal : "marketingManagement/activityManagement/commission/commissionActionDetail.jhtml",
					param : ["aid"],
					permissions : "commissioncheck",
				}]
	},
	columns : [{
				title : "活动编号",
				name : "aid",
			},{
				title : "活动名称",
				name : "aname",
			},{
				title : "开始日期",
				name : "startDate",
			},{
				title : "结束日期",
				name : "endDate",
			},{
				title : "开始时间",
				name : "startTimeText",
			},{
				title : "结束时间",
				name : "endTimeText",
			},{
				title : "补贴折扣(%)",
				name : "ngiveMoneyStr",
			},{
				title : "补贴频率",
				name : "rateName",
			},{
				title : "活动描述",
				name : "eescription",
			},{
				title : "参与商家",
				name : "isRelationSeller",
				permissions : "commissioncysj",
				isA : true,
				a : {
					must:true,
					href : "marketingManagement/activityManagement/commission/commissionParticiptingMerchants/init.jhtml",
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
				permissions : "commissionkbtje",
				isA : true,
				a : {
					must:true,
					href : "marketingManagement/activityManagement/commission/activityPrize/init.jhtml",
					param : ["aid"]
				},
				customMethod : function(value, data) {
					return (data.giveMoneyCount == 0 ? "0" : value);
				},
			}],
			permissions : permissions
	}
	obj.find('div').eq(0).html($.renderGridView(model,data));
    $("#commission").find("table thead tr th").first().html("选择");
}

//为指定佣金补贴活动添加商家
function commissionActivitySubsidy(){
	var aids= new Array();
	var aids=commission.getIds().split(",");
	if(!commission.getIds()){
		showWarningWindow("warning","请选择一条记录！");
		return;
	}
	if(aids.length>1){
		showWarningWindow("warning","请选择一条记录！");
		return;
	}
	var aid = commission.getIds();
	$("#aid").val(aid);
	var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#commissionForm").serialize());
	$("#addForm").get(0).action="marketingManagement/activityManagement/commission/activityManagerSeller/init.jhtml?doType=commission"+callbackParam;
    $("#addForm").get(0).submit();
}
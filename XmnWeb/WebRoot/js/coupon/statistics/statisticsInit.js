var activityList;
var couponList;
$(document).ready(function() {
	/*
	 * 按活动统计列表
	 */
	activityList = $('#activity').page({
		url : 'coupon/statistics/activity/init/list.jhtml',
		success : activitySuccess,
		pageBtnNum : 10,
		paramForm : 'activityForm'
	});
	/*
	 * 按优惠券统计列表
	 */
	couponList = $('#coupon').page({
		url : 'coupon/statistics/coupon/init/list.jhtml',
		success : couponSuccess,
		pageBtnNum : 10,
		paramForm : 'couponForm'
	});
	
	
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	/*var ld = $("#ysqy").areaLd({
		isChosen : true,
		showConfig : [ {
			name : "province",
			tipTitle : "--省--"
		}, {
			name : "city",
			tipTitle : "--市--"
		}, {
			name : "region",
			tipTitle : "--区--"
		} ]
	});*/
	inserTitle(' > 优惠券管理  > <a href="coupon/statistics/init.jhtml" target="right"> 优惠券统计</a>','disputeOrder',true);
});

function activitySuccess(data, obj) {
	var model = {
		title : "活动统计列表",
		totalTitle : "活动",
		form : "activityForm",
		backButton : true,
		addBtn : "addCoupnBto",
		columns : [
				{
					title : "活动编号",
					name : "issueid",
					width : "120px",
				},{
					title : "活动名称",
					name : "activityName",
					width : "120px",
				},{
					title : "优惠券",
					name : "issueid",
					width : "50px",
					customMethod : function(value, data) {
						var issueId = data.issueid;
						value = "<a title='点击查看优惠券信息' href='coupon/couponissue/viewCoupon/viewCouponInit.jhtml?issueId="+issueId+"&isBackButton=true&callbackParam=activityName=,activityType=,status=,page=1''>查看</a>";
						return  value;
					}
				}, {
					title : "发行量",
					name : "issueVolume",
					width : "50px",
				},{
					title : "活动状态",
					name : "status",
					width : "50px",
					customMethod : function(value,data) {
						var status = "-";
						if (value == 0) {
							status = "已停止";
						} else if (value == 1) {
							status = "已启动";
						} else if (value == 2) {
							status = "待启动";
						}
						return status;
					}
				},{
					title : "已领取",
					name : "gotNum",
					width : "50px",
				},{
					title : "未使用",
					name : "unUseNum",
					width : "50px",
					isA : true,
					a : {
						href : "coupon/statistics/activity/unUse/init.jhtml",
						param : ["issueid"],
						data_position:"200px"
				
					},
					permissions : "unUse",
					customMethod : function(value, data) {
						return (data.unUseNum > 0 ? value : "-");
					}
				},{
					title : "已使用数",
					name : "usedNum",
					width : "50px",
					isA : true,
					a : {
						href : "coupon/statistics/activity/used/init.jhtml",
						param : ["issueid"],
						data_position:"200px"
					},
					permissions : "used",
					customMethod : function(value, data) {
						return (data.usedNum > 0 ? value : "-");
					}
				}, {
					title : "已使用金额",
					name : "usedAmount",
					width : "50px",
				} ],
		permissions : permissions
	};

	obj.find('div').eq(0).html($.renderGridView(model, data));
}

function couponSuccess(data, obj) {
	var model = {
		title : "优惠券统计列表",
		totalTitle : "优惠券",
		form : "couponForm",
		backButton : true,
		addBtn : "addCoupnBto",
		columns : [{
				title : "优惠券编号",
				name : "cid",
			},{
				title : "优惠券名称",
				name : "cname",
			}, {
				title : "优惠券商家",
				name : "sellerNum",
				isA : true,
				a : {
					href : "coupon/generate/viewSellers/init.jhtml",
					param : [ "cid" ]
				},
				permissions : "unUse",
				customMethod : function(value,data) {
					return data.sellerNum!=0?value:"0";
				}
			}, {
				title : "是否全国",
				name : "showall",
				customMethod : function(value, data) {
					var showall = "-";
					if (value == 0) {
						showall = "否";
					} else if (value == 1) {
						showall = "是";
					}
					return showall;
				}
			}, {
				title : "区域",
				name : "cid",
				customMethod : function(value,data) {
					var cid = data.cid;
					value = "<a href='javascript:viod(); ' data-type='ajax' data-position='250px' data-width='40%' data-url='coupon/statistics/coupon/viewArea/viewAreaInit.jhtml?cid="+cid+"'  data-toggle='modal' >查看</a>";
					return data.showall == 1 ? "全国": value;
				}
				
				/*customMethod : function(value, data) {
					var cid = data.cid;
					value = "<a href='javascript:viod(); ' data-type='ajax' data-position='250px' data-width='40%' data-url='coupon/statistics/coupon/viewArea/viewAreaInit.jhtml?cid="+cid+"'  data-toggle='modal' >查看</a>";
					return  value;
				}*/
			}, {
				title : "面额",
				name : "denomination",
			}, {
				title : "使用条件",
				name : "condition",
				customMethod : function(value, data) {
					value = "满【"+value+"】元使用";
					return (data.condition > 0 ? value : "无条件使用");
				}
			}/*, {
				title : "领取方式",
				name : "getWay",
				width : "50px",
				customMethod : function(value, data) {
					value = "-";
					if(value.getWay == 1 ){
						value = "摇一摇";
					}if(value.getWay == 2 ){
						value = "满返";
					}if(value.getWay == 3 ){
						value = "短信获取";
					}if(value.getWay == 4 ){
						value = "直接发放";
					}
					return value;
				}
			}*/, {
				title : "有效开始时间",
				name : "startDate",
			}, {
				title : "有效结束时间",
				name : "endDate",
			}, {
				title : "发行量",
				name : "issueVolume",
			}/*, {
				title : "状态",
				name : "cname",
				width : "50px",
			}*/, {
				title : "已领取",
				name : "gotNum",
			}, {
				title : "未使用",
				name : "unUseNum",
				isA : true,
				a : {
					href : "coupon/statistics/coupon/couponUnUse/init.jhtml",
					param : ["cid","userStatus"],
					data_position:"200px"
			
				},
				permissions : "couponUnUse",
				customMethod : function(value, data) {
					return (data.unUseNum > 0 ? value : "-");
				}
			}, {
				title : "已使用数",
				name : "usedNum",
				isA : true,
				a : {
					href : "coupon/statistics/coupon/couponUsed/init.jhtml",
					param : ["cid","userStatus"],
					data_position:"200px"
				},
				permissions : "couponUsed",
				customMethod : function(value, data) {
					return (data.usedNum > 0 ? value : "-");
				}
			}, {
				title : "已使用金额",
				name : "usedAmount",
			} ],
		permissions : permissions
	};

	obj.find('div').eq(0).html($.renderGridView(model, data));
}